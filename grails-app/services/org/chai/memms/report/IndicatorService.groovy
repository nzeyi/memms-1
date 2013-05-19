package org.chai.memms.report

import org.chai.memms.inventory.EquipmentStatus.Status;

import java.lang.reflect.Type

import grails.converters.*
import org.codehaus.groovy.grails.web.json.*


import org.chai.memms.Warranty
import org.chai.memms.Period
import org.chai.memms.report.utils.*
import com.google.gson.*


import org.chai.memms.inventory.Equipment;
import org.chai.memms.inventory.EquipmentStatus;


import org.chai.location.DataLocationType
import org.chai.location.DataLocation

import groovy.util.XmlParser
import groovy.xml.XmlUtil
import org.chai.memms.util.Utils;
import org.chai.memms.Warranty;
import java.text.SimpleDateFormat
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Interval
import org.joda.time.Months;
import org.joda.time.PeriodType;
import org.joda.time.Years
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
class IndicatorService {
	static transactional = true

	def sessionFactory


	def dataSource  //Auto Injected
	def intermediateVariableService

	public void reportingEngine(){
		try{


			Date startTime=new Date()

			println"========================================================================================"
			println"========================================================================================"
			println"======= Data Location Report Executor Engine Started At :"+startTime+"================="
			println"========================================================================================"
			println"========================================================================================"

			List<DataLocation> dataLocations=getDataLications()
			List<IntermediateVariable> intermidiateValiables
			if(dataLocations!=null){

				for(DataLocation location:dataLocations){
					if(location!=null){
						if(ExecutorProvider.isLong(""+location.id)){
							intermidiateValiables=intermediateVariableService.intermediateVariableValueCalculator(location)

							indicatorValueCalculator(intermidiateValiables,location)
						}else
							println"Invalid location"


					}else{
						println"location not found"
					}

				}
			}else{
				print"No Data Location found ; can't calculate indicator values"
			}
			Date enddTime=new Date()
			println"========================================================================================"
			println"========================================================================================"
			println"======= Data Location Report Executor Engine Terminated At :"+enddTime+"==================="
			println"========================================================================================"
			println"========================================================================================"
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace()
		}

	}


	public void indicatorValueCalculator(List<IntermediateVariable> listOfComputedIntermidiateVariables,DataLocation location){



		List<QueryParserHelper> numeratorQueries=null

		List<QueryParserHelper> denominatorQueries=null


		//String indicatorFileContent = new File('web-app/resources/reportData/indicators.xml').text
		//def indicators = new XmlParser().parseText(indicatorFileContent)
		def indicators = getIndicators()
		if(location!=null){
			if(location.id>0){


				Date today=new Date()
				String reportCod=ExecutorProvider.idGenerator(""+today.toString())

				DataLocationType type=location.type
				DataLocationReport facilityReport=new DataLocationReport(code:reportCod,dataLocation:location,dataLocationType:type,generatedAt:today)
				facilityReport.save(failOnError: true)

				for(Indicator currentIndicator:indicators){
					//indicators.indicator.each{


					//Indicator currentIndicator=Indicator.findByCode(it.attribute("indicatorCode"))
					if(currentIndicator!=null){
						numeratorQueries=new ArrayList<QueryParserHelper>()
						denominatorQueries=new ArrayList<QueryParserHelper>()
						int totalAtNumerator=0
						int totalAtDenominator=0
						double indicatorValue=0.0






						//String indType=it.type.text()


						//def scripts=it.scriptFormula
						String indType=currentIndicator.type


						def scripts=currentIndicator.queryParserHelpers

						for(QueryParserHelper helper:scripts){
							//scripts.excutableScript.each{

							//QueryParserHelper helper=new QueryParserHelper()

							//helper.executableScript=it.text()
							//helper.classDomaine=it.attribute("classDomaine")
							//helper.useCountFunction=Boolean.parseBoolean(it.attribute("useCountFunction"))
							//helper.followOperand=it.attribute("followOperand")
							//helper.isDenominator=Boolean.parseBoolean(it.attribute("isDenominator"))
							//helper.isIntermidiateVariable=Boolean.parseBoolean(it.attribute("isIntermidiateVariable"))
							//helper.isDynamicFinder=Boolean.parseBoolean(it.attribute("isDynamicFinder"))
							//helper.type=indType
							if(helper.isDenominator){
								denominatorQueries.add(helper)
							}else if(!helper.isDenominator){
								numeratorQueries.add(helper)
							}

						}




						// execute numerators against the db  by applying mathematical operators
						totalAtNumerator=getSumOfNumerators(numeratorQueries,location)


						// execute denominators against the db  by applying mathematical operators


						totalAtDenominator=getSumOfDenominators(denominatorQueries,location,listOfComputedIntermidiateVariables)




						if(totalAtDenominator>0)
							indicatorValue=totalAtNumerator/totalAtDenominator
						else
							indicatorValue=totalAtNumerator

						//println" ===================================total of numerators:"+totalAtNumerator
						//println" =================total of denominators:"+totalAtDenominator
						//println" ===============================Indicator value is :"+indicatorValue


						DataLocationReport savedFacilityReport=DataLocationReport.findByCode(reportCod)
						if(savedFacilityReport!=null){
							//if(currentIndicator.type!=null){
							//if(currentIndicator.type.equals("Normal")){
							//FOR NORMAL INDICATORS
							String indicatorValueCode=ExecutorProvider.idGenerator(today.toString()+""+indicatorValue)
							IndicatorValue indicatorValueObj=new IndicatorValue(code:indicatorValueCode,computedValue:indicatorValue,dataLocationReport:savedFacilityReport,generatedAt:today,indicator:currentIndicator)
							indicatorValueObj.save(failOnError: true)
							//}else if(currentIndicator.type.equals("Special")){
							//FOR SPECIAL INDICATORS


							//indicatorValueCalculatorFactory(currentIndicator.code,listOfComputedIntermidiateVariables,location,savedFacilityReport)
							//}
							//}
						}
					}else
						println" Indicator not found "

				}
			}else
				println"Invalid location object"
		}else
			println"the location is null"

	}
	public int getSumOfNumerators(List<QueryParserHelper> numeratorHelpers,DataLocation location){
		ClassFinder finder=new ClassFinder()
		Class className = null

		int totalAtNumerator=0

		try{
			for(QueryParserHelper numerator:numeratorHelpers){
				int uniqueNum=0

				if(numerator.classDomaine!=null&& numerator.executableScript!=null){
					className = finder.findClassByName(numerator.classDomaine)


					if(className!=null){
						def session = sessionFactory.getCurrentSession()
						//DataLocation location=DataLocation.findById(18)
						int locationidentifier=location.id
						String validQueryLocation=numerator.executableScript.replace('locationidentifier',""+locationidentifier+"")
						String validQueryLessThanOperatorAdded=""
						if(validQueryLocation.contains("lessthan"))
							validQueryLessThanOperatorAdded=validQueryLocation.replace('lessthan','<')
						else
							validQueryLessThanOperatorAdded=validQueryLocation
						if(numerator.type.equalsIgnoreCase("Normal")){
							if(!numerator.isDynamicFinder){


								def query=null
								if(validQueryLessThanOperatorAdded.contains("currentDate")){
									//println"numerator query here is :"+validQueryLocation
									query = session.createQuery(validQueryLessThanOperatorAdded).setParameter("currentDate", new Date())
									//println"numerator query here is :"+validQueryLessThanOperatorAdded
								}
								else
									query = session.createQuery(validQueryLessThanOperatorAdded)

								def results = query.list()




								if(!numerator.useCountFunction){




									uniqueNum=results[0]



								}else{


									uniqueNum=results.size()
									//println"result from count:"+results.size()


								}


							}else if(numerator.isDynamicFinder){
								uniqueNum=className.findAll(numerator.executableScript).size()
							}

						}else if(numerator.type.equalsIgnoreCase("Special")){

							println" i will call the factory to process this special indicator"
						}
						// update the sum after query excution in all possible cases
						totalAtNumerator=totalAtNumerator+uniqueNum
					}else{
						println"Can't find the class name to query"
					}

				}else{
					println" numerator invalid query passed"
				}
				uniqueNum=0
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace()
		}
		//println"Total numerators:"+totalAtNumerator
		return totalAtNumerator
	}
	public int getSumOfDenominators(List<QueryParserHelper> denominatorHelpers,DataLocation location,List<IntermediateVariable> intermediateVariables){
		//println"twinjiyemo ok :"+intermediateVariables
		ClassFinder finder=new ClassFinder()
		Class className = null

		int totalAtDenominator=0

		try{
			
			for(QueryParserHelper denominator:denominatorHelpers){
				int uniqueDenom=0


				
				if(denominator.classDomaine!=null&& denominator.executableScript!=null){
					
					if(!denominator.isIntermidiateVariable){
						className = finder.findClassByName(denominator.classDomaine)
						

						if(className!=null){
							def session = sessionFactory.getCurrentSession()
							//DataLocation location=DataLocation.findById(18)
							int locationidentifier=location.id

							String validQueryLocation=denominator.executableScript.replace('locationidentifier',""+locationidentifier+"")
							if(denominator.type.equalsIgnoreCase("Normal")){
								if(!denominator.isDynamicFinder){


									def query = session.createQuery(validQueryLocation)

									def results = query.list()




									if(!denominator.useCountFunction){




										uniqueDenom=results[0]


									}else{



										uniqueDenom=results.size()


									}


								}else if(denominator.isDynamicFinder){
									uniqueDenom=className.findAll(denominator.executableScript).size()
								}
							}else if(denominator.type.equalsIgnoreCase("Special")){
								println" i will call the factory to process this special indicator by passing the indicator finder/code"
							}
							// update the sum after query excution in all possible cases

						}else{
							println"Can't find the class name to query"
						}
					}else if(denominator.isIntermidiateVariable){
						
						def intermediateId=denominator.intermediateVariable.id
						
						for(IntermediateVariable intermVal:intermediateVariables)
							if(intermVal.id==intermediateId){
								uniqueDenom=intermVal.computedValue
								println"resultttttttttttttttttttttdbid:"+intermVal.id+"ttttinmemid"+intermediateId+"tttttttt:"+uniqueDenom

								break
							}
					}
				}else{
					println" invalid query passed"
				}



				totalAtDenominator=totalAtDenominator+uniqueDenom
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace()
		}
		//println"Total numerators:"+totalAtDenominator

		return totalAtDenominator
	}



	public double indicatorValueCalculatorFactory(String engineFinder,List<QueryParserHelper> denominatorHelpers,DataLocation location){
		double calculatedIndicatorValue=0
		if(engineFinder.equals(ExecutorProvider.MANA_EQUIPMENT_SHARE_OF_EQUIPMENT_UNDER_ACTIVE_WARRANTY_OR_UNDER_ACTIVE_SERVICE_PROVIDER_CONTRACT)){
			//println"call to the equipement under active waranty or under service provider contract"
			Indicator indicator=Indicator.findByCode(engineFinder)
			println"indicator  :"+indicator
			calculatedIndicatorValue=getIndicatorValueForActiveWarantOrServiceProviderContarct(dataLocation)
			//println"indicator value :"+calculatedIndicatorValue
			//Date today=new Date()
			//String indicatorValueCode=ExecutorProvider.idGenerator(today.toString()+""+calculatedIndicatorValue)
			//IndicatorValue indicatorValueObj=new IndicatorValue(code:indicatorValueCode,computedValue:calculatedIndicatorValue,dataLocationReport:datalocationReport,generatedAt:today,indicator:indicator)
			//indicatorValueObj.save(failOnError: true)
			//println"?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????? call to the equipement under active waranty or under service provider contract"
		}else if(engineFinder.equalsIgnoreCase(ExecutorProvider.MANA_EQUIPMENT_SHARE_OF_EQUIPMENT_WITH_LIFETIME_EXPIRING_IN_LESS_THAN_2YEARS)){
			println"MANA_EQUIPMENT_SHARE_OF_EQUIPMENT_WITH_LIFETIME_EXPIRING_IN_LESS_THAN_2YEARS"

		}
		else if(engineFinder.equalsIgnoreCase(ExecutorProvider.PRIV_MAINT_AVERAGE_IN_EWAITING_FOR_SPAREPARTS)){
			println"PRIV_MAINT_AVERAGE_IN_EWAITING_FOR_SPAREPARTS"

		}
		return calculatedIndicatorValue

	}


	public  double getIndicatorValueForActiveWarantOrServiceProviderContarct(DataLocation location){

		int expireryCounter=0
		double indicatorValue=0.0
		// sild 13  (total number equipment with STATUS=(Operational; Partially operational, Under maintenance) and (Current Date – Warranty Start Date) < (Warranty period)) or (Current Date – Contract Start Date) < (Contract period))/(total number equipment with STATUS=(Operational; Partially operational, Under maintenance))
		def criteria = Equipment.createCriteria()
		List<Equipment> equipements= criteria.list{


			or{
				eq('currentStatus',EquipmentStatus.Status.UNDERMAINTENANCE)
				eq('currentStatus',EquipmentStatus.Status.OPERATIONAL)
				eq('currentStatus',EquipmentStatus.Status.PARTIALLYOPERATIONAL)



			}
			and{ eq('dataLocation',location) }


		}

		int denominator=equipements.size()




		Date currentDate=new Date()


		int numberOfEquipementsUnderActiveWarenty=0
		for(Equipment equipement:equipements){
			def numberOfMonthsWarrantSatartDate=0
			def warentyPeriod=0
			def serviceContractPeriod=0
			def numberOfMonthsServiceContractStartDate=0
			if(equipement.warrantyPeriod!=null)
				warentyPeriod=equipement.warrantyPeriod.numberOfMonths
			if(equipement.serviceContractPeriod!=null)
				serviceContractPeriod=equipement.serviceContractPeriod.numberOfMonths

			//	def warrantyStartdate=equipement.warranty.startDate


			SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
			DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
			if(equipement.warranty.startDate!=null){
				String ancientDate =formater.format(equipement.warranty.startDate)

				//warranty start date isues
				String nowDate = formater.format(currentDate)

				DateTime oldDate = formatter.parseDateTime(ancientDate)
				DateTime newDate = formatter.parseDateTime(nowDate)
				final Months warrantyMonthsUntilNow = Months.monthsBetween(oldDate, newDate)
				numberOfMonthsWarrantSatartDate=warrantyMonthsUntilNow.months
			}
			// contract start date isues
			if(equipement.serviceContractStartDate!=null){
				String ancientDateCotractStart=formater.format(equipement.serviceContractStartDate)
				DateTime oldDateContractStart = formatter.parseDateTime(ancientDateCotractStart)
				DateTime nowDateFromContractStart = formatter.parseDateTime(nowDate)
				final Months serviceContractStartDateMonthsUntilNow = Months.monthsBetween(oldDateContractStart, nowDateFromContractStart)
				numberOfMonthsServiceContractStartDate=serviceContractStartDateMonthsUntilNow.months
			}
			//println"monthsssssssssssssssssssssss :"+numberOfMonthsWarrantSatartDate+"<> waranty period"+warentyPeriod




			if(numberOfMonthsWarrantSatartDate<warentyPeriod ||numberOfMonthsServiceContractStartDate< serviceContractPeriod)
				numberOfEquipementsUnderActiveWarenty++
		}

		println"the deno value :"+denominator+" equuipement :"+Equipment.id
		println"the nom value :"+numberOfEquipementsUnderActiveWarenty
		if(denominator>0)
			indicatorValue=numberOfEquipementsUnderActiveWarenty/denominator
		else
			indicatorValue=numberOfEquipementsUnderActiveWarenty

		println"the value :"+indicatorValue





		return indicatorValue
	}


	public List<DataLocation> getDataLications(){
		def dataLocations=null
		dataLocations=DataLocation.findAll()
		return dataLocations
	}
	public  void newIndicatorCategory(Map<String,String> names,def code,def minYellowValue,def maxYellowValue){
		try{
			def category = new IndicatorCategory(code:code,minYellowValue:minYellowValue,maxYellowValue:maxYellowValue)
			Utils.setLocaleValueInMap(category,names,"Names")
			category.save(failOnError:true,flush:true)
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace()

		}
	}
	public void indicatorWriterFromXml(){

		if(!IndicatorCategory.count()){
			String indicatorCategoryFileContent = new File('web-app/resources/reportData/indicatorCategories.xml').text
			def categories = new XmlParser().parseText(indicatorCategoryFileContent)
			categories.category.each{

				Map<String,String> names=new HashMap<String,String>()
				names.put('en', it.name.text())
				names.put('fr', "To be added later")
				newIndicatorCategory(names,it.attribute("categoryCode"),it.minYellowValue.text(),it.maxYellowValue.text())

			}
		}

		intermediateVariableService.intermediateVariableWriter()

		String indicatorFileContent = new File('web-app/resources/reportData/indicators.xml').text

		def indicators = new XmlParser().parseText(indicatorFileContent)

		if(!Indicator.count()){

			IndicatorCategory category=null
			indicators.indicator.each{
				category=IndicatorCategory.findByCode(it.attribute("categoryCode"))

				if(category!=null){

					String indTypeValue=it.type.text()
					def scripts=it.scriptFormula
					println"formula ok :"+it.formula.text()
					def indicator = new Indicator(names:it.name.text(),code:it.attribute("indicatorCode"),indicatorCategory:category,formula:it.formula.text(),type:it.type.text())

					indicator.save(failOnError: true)

					Indicator indicatorr=Indicator.findByCode(it.attribute("indicatorCode"))

					if(scripts!=null&&indicatorr!=null){

						def queryParserHelpers=QueryParserHelper.findByIndicator(indicatorr)

						if(queryParserHelpers==null){
							scripts.excutableScript.each{


								IntermediateVariable intermediateVariable=null
								if(Boolean.parseBoolean(it.attribute("isIntermidiateVariable"))&&!(it.attribute("internediateVariableCode").equalsIgnoreCase("NA"))){
									intermediateVariable=IntermediateVariable.findByCode(it.attribute("internediateVariableCode"))
								}
								def queryParserHelper=new QueryParserHelper(executableScript:it.text(),classDomaine:it.attribute("classDomaine")
								,useCountFunction:it.attribute("useCountFunction")
								,followOperand:it.attribute("followOperand")
								, isDenominator:it.attribute("isDenominator")
								, isIntermidiateVariable:it.attribute("isIntermidiateVariable")
								,isDynamicFinder:it.attribute("isDynamicFinder"),indicator:indicatorr,type:indTypeValue,intermediateVariable:intermediateVariable)
								queryParserHelper.save(failOnError: true)

							}
						}
					}
				}else
					println"category not found heloooooooooooooooooooooo3"
			}
		}else{

			IndicatorCategory category=null
			Indicator oldInd=null
			indicators.indicator.each{

				category=IndicatorCategory.findByCode(it.attribute("categoryCode"))
				if(category!=null){

					oldInd=Indicator.findByCode(it.attribute("indicatorCode"))
					if(oldInd==null){

						def indicator = new Indicator(names:it.name.text(),code:it.attribute("indicatorCode"),indicatorCategory:category,formula:it.formula.text(),type:it.type.text())

						indicator.save(failOnError: true)

					}
					else if(oldInd!=null){

						oldInd.formula=it.scriptFormula.text()
						oldInd.indicatorCategory=category
						oldInd.save(failOnError: true)
					}else
						println" It will not change the formula"

					def scripts=it.scriptFormula

					Indicator indicatorr=Indicator.findByCode(it.attribute("indicatorCode"))
					String indTypeValue=it.type.text()
					if(scripts!=null&&indicatorr!=null){

						def queryParserHelpers=QueryParserHelper.findByIndicator(indicatorr)

						if(queryParserHelpers==null){
							IntermediateVariable intermediateVariable=null
							if(Boolean.parseBoolean(it.attribute("isIntermidiateVariable"))&&!(it.attribute("internediateVariableCode").equalsIgnoreCase("NA"))){
								intermediateVariable=IntermediateVariable.findByCode(it.attribute("internediateVariableCode"))
							}
							scripts.excutableScript.each{
								def queryParserHelper=new QueryParserHelper(executableScript:it.text(),classDomaine:it.attribute("classDomaine")
								,useCountFunction:it.attribute("useCountFunction")
								,followOperand:it.attribute("followOperand")
								, isDenominator:it.attribute("isDenominator")
								, isIntermidiateVariable:it.attribute("isIntermidiateVariable")
								,isDynamicFinder:it.attribute("isDynamicFinder"),indicator:indicatorr,type:indTypeValue,intermediateVariable:intermediateVariable)
								queryParserHelper.save(failOnError: true)

							}
						}
					}


				}

			}
		}

	}


	public void testQuery(){
		println"heloooooooooooooooooooooooooooooooooooooooooooooooooooo"
		//		def c = Equipment.createCriteria()
		//		DataLocation location=DataLocation.findById("16")
		//		String queryOk="select equ.code from Equipment as equ where (equ.currentStatus='OPERATIONAL'  or equ.currentStatus='UNDERMAINTENANCE'  or equ.currentStatus='PARTIALLYOPERATIONAL') and equ.obsolete='1' and dateDiff(equ.purchaseDate,NOW()) and equ.dataLocation=locationidentifier"
		//		println"location id :"+location.id
		//		String validQueryLocation=queryOk.replace('locationidentifier',""+location.id+"")
		//		def session = sessionFactory.getCurrentSession()
		//		def query = session.createQuery(validQueryLocation)

		/*SELECT purch.customer.company AS name,
		 purch.duedate AS duedate,
		 current_date AS today,
		 DATEDIFF(current_date, purch.duedate) AS overdue,
		 FROM Purchase AS purch
		 WHERE
		 DATEDIFF(current_date, purch.duedate) >= :duevar*/

		//(total number equipment with STATUS=(Operational; Partially operational, Under maintenance) and (Current Date – date of first inventory updation/DATE OF PURCHASE) >(Expected life time – 2years)/(total number equipment with STATUS = (Operational; Partially operational, Under maintenance))
		DataLocation location=DataLocation.findById("16")
		def session = sessionFactory.getCurrentSession()
		def query = session.createQuery("select equ.code from Equipment as equ where (equ.currentStatus='OPERATIONAL' or equ.currentStatus='UNDERMAINTENANCE' or equ.currentStatus='PARTIALLYOPERATIONAL') and (((equ.expectedLifeTime.numberOfMonths-24)*30)<(DATEDIFF(:currentDate,equ.purchaseDate))) and equ.dataLocation=16").setParameter("currentDate", new Date());





		def results = query.list()

		println" nomgeyenoibimenyetso grater than in two years ok *30 :"+results
		println" added location start date ooooooo count is  :"+results.size()
	}

	public def getIndicators(){
		return Indicator.findAll()
	}

	public def searchIndicator(String text,Map<String, String> params) {
		text = text.trim()
		def criteria = Indicator.createCriteria()
		return criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			or{
				ilike("code","%"+text+"%")
				ilike("names","%"+text+"%")
				ilike("type","%"+text+"%")
			}
		}
	}

}