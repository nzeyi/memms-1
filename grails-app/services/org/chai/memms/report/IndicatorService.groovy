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
	def indicatorCategoryService
	//def indicatorColorCriterionService
	def memmsReportService

	public void reportingEngine(){
		try{


			Date startTime=new Date()

			println"========================================================================================"
			println"========================================================================================"
			println"======= Data Location Report Executor Engine Started At :"+startTime+"================="
			println"========================================================================================"
			println"========================================================================================"
			String memmsReportCode=ExecutorProvider.idGenerator(""+startTime.toString())

			MemmsReport memmsReport=new MemmsReport(code:memmsReportCode,createdAt:startTime)
			MemmsReport memmsReportS=memmsReport.save(failOnError: true)
			if(memmsReportS!=null){
				List<DataLocation> dataLocations=getDataLications()
				List<IntermediateVariable> intermidiateValiables
				if(dataLocations!=null){

					for(DataLocation location:dataLocations){
						if(location!=null){

							intermidiateValiables=intermediateVariableService.intermediateVariableValueCalculator(location)
							for(IndicatorCategory category:indicatorCategoryService.getIndicatorCategories()){
								if(category!=null)
									indicatorValueCalculator(intermidiateValiables,location,category,memmsReportS)
							}


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
			}else{

				println"failed to create the report object!"
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace()
		}

	}


	public void indicatorValueCalculator(List<IntermediateVariable> listOfComputedIntermidiateVariables,DataLocation location,IndicatorCategory catory,MemmsReport memmsReport){



		List<QueryParserHelper> numeratorQueries=null

		List<QueryParserHelper> denominatorQueries=null



		def indicators = getIndicatorsByCategory(catory)
		if(location!=null){
			if(location.id>0){


				Date today=new Date()
				String reportCod=ExecutorProvider.idGenerator(""+today.toString())

				DataLocationType type=location.type
				DataLocationReport facilityReport=new DataLocationReport(code:reportCod,dataLocation:location,dataLocationType:type,generatedAt:today,indicatorCategory:catory,memmsReport:memmsReport)


				facilityReport.save(failOnError: true)

				for(Indicator currentIndicator:indicators){
					
					if(currentIndicator!=null){
						numeratorQueries=new ArrayList<QueryParserHelper>()
						denominatorQueries=new ArrayList<QueryParserHelper>()
						double totalAtNumerator=0.0
						double totalAtDenominator=0.0
						double indicatorValue=0.0






						
						String indType=currentIndicator.type


						def scripts=currentIndicator.queryParserHelpers

						for(QueryParserHelper helper:scripts){

							if(helper.isDenominator){
								denominatorQueries.add(helper)
							}else if(!helper.isDenominator){
								numeratorQueries.add(helper)
							}

						}




						
						totalAtNumerator=getSumOfNumerators(numeratorQueries,location,listOfComputedIntermidiateVariables)


					


						totalAtDenominator=getSumOfDenominators(denominatorQueries,location,listOfComputedIntermidiateVariables)




						if(totalAtDenominator>0)
							indicatorValue=totalAtNumerator/totalAtDenominator
						else if(totalAtDenominator<=0){
							if(totalAtNumerator>0)
								indicatorValue=totalAtNumerator/totalAtNumerator
							else
								indicatorValue=0
						}

						if(currentIndicator.code.equals("MON_MEMMS_AVERAGE_NUMBER_OF_EQUIPMENT_PER_FACILITY")){
							println"ok total numerators :"+totalAtNumerator
							println" ok total denominators :"+totalAtDenominator
							println"ok indicator value :"+indicatorValue
						}
						


						DataLocationReport savedFacilityReport=DataLocationReport.findByCode(reportCod)
						if(savedFacilityReport!=null){
							
							String indicatorValueCode=ExecutorProvider.idGenerator(today.toString()+""+indicatorValue)
							IndicatorValue indicatorValueObj=new IndicatorValue(code:indicatorValueCode,computedValue:indicatorValue,dataLocationReport:savedFacilityReport,generatedAt:today,indicator:currentIndicator)
							indicatorValueObj.save(failOnError: true)
							
						}
					}else
						println" Indicator not found "

				}
			}else
				println"Invalid location object"
		}else
			println"the location is null"

	}
	public int getSumOfNumerators(List<QueryParserHelper> numeratorHelpers,DataLocation location,List<IntermediateVariable> intermediateVariables){
		ClassFinder finder=new ClassFinder()
		Class className = null

		double totalAtNumerator=0.0

		try{
			for(QueryParserHelper numerator:numeratorHelpers){
				double uniqueNum=0.0

				if(numerator.classDomaine!=null&& numerator.executableScript!=null){
					className = finder.findClassByName(numerator.classDomaine)

					if(!numerator.isIntermidiateVariable){
						if(className!=null|| className.equals("DataLocation")){
							def session = sessionFactory.getCurrentSession()
							
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
									//println" the query is numerator :"+validQueryLessThanOperatorAdded
									if(validQueryLessThanOperatorAdded.contains("currentDate")){

										query = session.createQuery(validQueryLessThanOperatorAdded).setParameter("currentDate", new Date())

									}
									else
										query = session.createQuery(validQueryLessThanOperatorAdded)

									def results = query.list()

									if(!numerator.useCountFunction){
										if(results[0]!=null)
											uniqueNum=results[0]
										else{
											println" indicator result is null"+validQueryLessThanOperatorAdded
										}

									}else{

										uniqueNum=results.size()

									}

								}else if(numerator.isDynamicFinder){
									uniqueNum=className.findAll(numerator.executableScript).size()
								}

							}else if(numerator.type.equalsIgnoreCase("Special")){

								println" i will call the factory to process this special indicator"
							}
							// update the sum after query excution in all possible cases

						}else{
							println"Can't find the class name to query"+numerator.indicator.code
						}
					}else if(numerator.isIntermidiateVariable){

						def intermediateId=numerator.intermediateVariable.id

						for(IntermediateVariable intermVal:intermediateVariables){
							if(intermVal.id==intermediateId){
								uniqueNum=intermVal.computedValue

								break
							}
						}


						println" intermidiate variable :"+intermVal
					}else{
						println"unclassified ok"
					}




				}else{
					println" numerator invalid query passed"
				}

				if(numerator.followOperand.equals("add")){
					//println"in========================================================================= adition"
					totalAtNumerator=totalAtNumerator+uniqueNum
				}
				else if(numerator.followOperand.equals("subb"))
					totalAtNumerator=totalAtNumerator-uniqueNum
				else if(numerator.followOperand.equals("mult"))
					totalAtNumerator=totalAtNumerator*uniqueNum
				uniqueNum=0
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace()
		}
		//println"Total numerators:"+totalAtNumerator
		return totalAtNumerator
	}
	public double getSumOfDenominators(List<QueryParserHelper> denominatorHelpers,DataLocation location,List<IntermediateVariable> intermediateVariables){
		//println"twinjiyemo ok :"+intermediateVariables
		ClassFinder finder=new ClassFinder()
		Class className = null

		double totalAtDenominator=0

		try{

			for(QueryParserHelper denominator:denominatorHelpers){
				double uniqueDenom=0



				if(denominator.classDomaine!=null&& denominator.executableScript!=null){

					if(!denominator.isIntermidiateVariable){
						className = finder.findClassByName(denominator.classDomaine)


						if(className!=null){
							def session = sessionFactory.getCurrentSession()
							//DataLocation location=DataLocation.findById(18)
							int locationidentifier=location.id

							String validQueryLocation=denominator.executableScript.replace('locationidentifier',""+locationidentifier+"")
							//println" query denom:"+validQueryLocation
							if(denominator.type.equalsIgnoreCase("Normal")){
								if(!denominator.isDynamicFinder){


									def query = session.createQuery(validQueryLocation)

									def results = query.list()

									if(!denominator.useCountFunction){




										uniqueDenom=results[0]


									}else{



										uniqueDenom=results.size()


									}

									println"number 1:"+uniqueDenom
								}else if(denominator.isDynamicFinder){
									uniqueDenom=className.findAll(denominator.executableScript).size()
								}
							}else if(denominator.type.equalsIgnoreCase("Special")){
								println" i will call the factory to process this special indicator by passing the indicator finder/code"
							}
							

						}else{
							println"Can't find the class name to query"+denominator.indicator.code
						}
					}else if(denominator.isIntermidiateVariable){

						def intermediateId=denominator.intermediateVariable.id

						for(IntermediateVariable intermVal:intermediateVariables){
							if(intermVal.id==intermediateId){
								uniqueDenom=intermVal.computedValue
								

								break
							}
						}
					}
					println"number 2:"+uniqueDenom
				}else{
					println" invalid query passed"
				}


				if(denominator.followOperand.equals("add"))
					totalAtDenominator=totalAtDenominator+uniqueDenom
				else if(denominator.followOperand.equals("subb"))
					totalAtDenominator=totalAtDenominator-uniqueDenom
				else if(denominator.followOperand.equals("mult"))
					totalAtDenominator=totalAtDenominator*uniqueDenom
				totalAtDenominator=totalAtDenominator+uniqueDenom
				println"number total den:"+totalAtDenominator
				uniqueDenom=0
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace()
		}
		
		return totalAtDenominator
	}



	public double indicatorValueCalculatorFactory(String engineFinder,List<QueryParserHelper> denominatorHelpers,DataLocation location){
		double calculatedIndicatorValue=0
		if(engineFinder.equals(ExecutorProvider.MANA_EQUIPMENT_SHARE_OF_EQUIPMENT_UNDER_ACTIVE_WARRANTY_OR_UNDER_ACTIVE_SERVICE_PROVIDER_CONTRACT)){
			//println"call to the equipement under active waranty or under service provider contract"
			Indicator indicator=Indicator.findByCode(engineFinder)
			
			calculatedIndicatorValue=getIndicatorValueForActiveWarantOrServiceProviderContarct(dataLocation)
			
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
			



			if(numberOfMonthsWarrantSatartDate<warentyPeriod ||numberOfMonthsServiceContractStartDate< serviceContractPeriod)
				numberOfEquipementsUnderActiveWarenty++
		}

		
		if(denominator>0)
			indicatorValue=numberOfEquipementsUnderActiveWarenty/denominator
		else
			indicatorValue=numberOfEquipementsUnderActiveWarenty

		





		return indicatorValue
	}


	public List<DataLocation> getDataLications(){
		def dataLocations=null
		dataLocations=DataLocation.findAll()
		return dataLocations
	}

	public void indicatorWriterFromXml(){


		indicatorCategoryService.indicatorCategoryWritter()

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
					IndicatorColorCriterion indicatorColorCriterion=IndicatorColorCriterion.findByCode(it.attribute("indicatorCode"))
					if(indicatorColorCriterion!=null){
						Map<String,String> names=new HashMap<String,String>()
						names.put('en', it.name.text())
						names.put('fr', it.name.text())
						def indicator = new Indicator(code:it.attribute("indicatorCode"),indicatorCategory:category,formula:it.formula.text(),type:it.type.text(),indicatorColorCriterion:indicatorColorCriterion)
						Utils.setLocaleValueInMap(indicator,names,"Names")
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
					}else{

						println" indicator was null"
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
					IndicatorColorCriterion indicatorColorCriterion=IndicatorColorCriterion.findByCode(it.attribute("indicatorCode"))
					if(indicatorColorCriterion!=null){
						oldInd=Indicator.findByCode(it.attribute("indicatorCode"))
						if(oldInd==null){

							def indicator = new Indicator(code:it.attribute("indicatorCode"),indicatorCategory:category,formula:it.formula.text(),type:it.type.text(),indicatorColor:indicatorColorCriterion)
							Map<String,String> names=new HashMap<String,String>()
							names.put('en', it.name.text())
							names.put('fr', it.name.text())
							Utils.setLocaleValueInMap(indicator,names,"Names")
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
				}else{
					println"null  indicator"
				}
			}
		}
		
	}


	public void testQuery(){
		println"heloooooooooooooooooooooooooooooooooooooooooooooooooooo"
		//avg(wo.travelTime.numberOfMinutes)
		String queryOk="select fac.id from DataLocation as fac"
		
		DataLocation location=DataLocation.findById("16")
		if(location!=null){
			String validQueryLocation=queryOk.replace('locationidentifier',""+location.id+"")
			def session = sessionFactory.getCurrentSession()
			def query = session.createQuery(validQueryLocation);





			def results = query.list()

			println" location :"+results
			
		}
	}

	public def getIndicatorByCode(String code){
		return Indicator.findByCode(code)
	}
	public def getIndicatorsByCategory(IndicatorCategory category){
		return Indicator.findAllByIndicatorCategory(category)
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