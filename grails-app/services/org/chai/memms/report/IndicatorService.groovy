package org.chai.memms.report



import java.lang.reflect.Type

import grails.converters.*
import org.codehaus.groovy.grails.web.json.*


import org.chai.memms.inventory.Equipment
import org.chai.memms.inventory.EquipmentStatus
import org.chai.memms.report.utils.*
import com.google.gson.*

import org.chai.memms.Inventory.*
import org.chai.location.DataLocationType
import org.chai.location.DataLocation

import groovy.util.XmlParser
import groovy.xml.XmlUtil
import org.chai.memms.util.Utils;

class IndicatorService {
	static transactional = true

	def sessionFactory


	def dataSource  //Auto Injected


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
				indicatorWriterFromXml()
				for(DataLocation location:dataLocations){
					if(location!=null){
						if(ExecutorProvider.isLong(""+location.id)){
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
	public List<DataLocation> getDataLications(){
		def dataLocations=null
		dataLocations=DataLocation.findAll()
		return dataLocations
	}
	public static def newIndicatorCategory(def names,def code,def minYellowValue,def maxYellowValue){
		def category = new IndicatorCategory(names:names,code:code,minYellowValue:minYellowValue,maxYellowValue:maxYellowValue)
		//Utils.setLocaleValueInMap(category,names,"Names")
		return category.save(failOnError:true)
	}
	public void indicatorWriterFromXml(){
		println"======================ok========================================11"
		if(!IndicatorCategory.count()){
			String indicatorCategoryFileContent = new File('web-app/resources/reportData/indicatorCategories.xml').text
			def categories = new XmlParser().parseText(indicatorCategoryFileContent)
			categories.category.each{
				println"the name isssssssssssssssssssssssssssssssssssssssss :"+it.name.text()
				def indicatorCategory =newIndicatorCategory(it.name.text(),it.attribute("categoryCode"),it.minYellowValue.text(),it.maxYellowValue.text())

				println" what is it ?:"+indicatorCategory
			}
		}
		println"heloooooooooooooooooooooo"
		String indicatorFileContent = new File('web-app/resources/reportData/indicators.xml').text
		println"heloooooooooooooooooooooo1"
		def indicators = new XmlParser().parseText(indicatorFileContent)
		println"heloooooooooooooooooooooo2"
		if(!Indicator.count()){
			println"heloooooooooooooooooooooo3"
			IndicatorCategory category=null
			indicators.indicator.each{
				category=IndicatorCategory.findByCode(it.attribute("categoryCode"))
				println"heloooooooooooooooooooooo3yessssssssssssssssssssssssss"+it.attribute("categoryCode")
				if(category!=null){
					println"heloooooooooooooooooooooo33"
					String indTypeValue=it.type.text()
					def scripts=it.scriptFormula
					println"formula ok :"+it.formula.text()
					def indicator = new Indicator(names:it.name.text(),code:it.attribute("indicatorCode"),indicatorCategory:category,formula:it.formula.text(),type:it.type.text())
					println"heloooooooooooooooooooooo33savr"
					indicator.save(failOnError: true)
					println"heloooooooooooooooooooooo33444444444444444444"
					Indicator indicatorr=Indicator.findByCode(it.attribute("indicatorCode"))

					if(scripts!=null&&indicatorr!=null){
						println"heloooooooooooooooooooofoundddddddddddddddddddddddddoo33"
						def queryParserHelpers=QueryParserHelper.findByIndicator(indicatorr)

						if(queryParserHelpers==null){
							scripts.excutableScript.each{

								def queryParserHelper=new QueryParserHelper(executableScript:it.text(),classDomaine:it.attribute("classDomaine")
								,useCountFunction:it.attribute("useCountFunction")
								,followOperand:it.attribute("followOperand")
								, isDenominator:it.attribute("isDenominator")
								, isIntermidiateVariable:it.attribute("isIntermidiateVariable")
								,isDynamicFinder:it.attribute("isDynamicFinder"),indicator:indicatorr,type:indTypeValue)
								queryParserHelper.save(failOnError: true)

							}
						}
					}
				}else
					println"category not found heloooooooooooooooooooooo3"
			}
		}else{
			println"heloooooooooooooooooooooo4"
			IndicatorCategory category=null
			Indicator oldInd=null
			indicators.indicator.each{
				println"heloooooooooooooooooooooo411"
				category=IndicatorCategory.findByCode(it.attribute("categoryCode"))
				if(category!=null){

					oldInd=Indicator.findByCode(it.attribute("indicatorCode"))
					if(oldInd==null){
						println" the indicator is null"
						def indicator = new Indicator(names:it.name.text(),code:it.attribute("indicatorCode"),indicatorCategory:category,formula:it.formula.text(),type:it.type.text())

						indicator.save(failOnError: true)
						println" created new indicator"
					}
					else if(oldInd!=null){
						println" found current indicator to change formula"
						oldInd.formula=it.scriptFormula.text()
						oldInd.indicatorCategory=category
						oldInd.save(failOnError: true)
					}else
						println" It will not change the formula"

					def scripts=it.scriptFormula
					println"heloooooooooooooooooooooo44444444444444444"
					Indicator indicatorr=Indicator.findByCode(it.attribute("indicatorCode"))
					String indTypeValue=it.type.text()
					if(scripts!=null&&indicatorr!=null){

						def queryParserHelpers=QueryParserHelper.findByIndicator(indicatorr)

						if(queryParserHelpers==null){

							scripts.excutableScript.each{
								def queryParserHelper=new QueryParserHelper(executableScript:it.text(),classDomaine:it.attribute("classDomaine")
								,useCountFunction:it.attribute("useCountFunction")
								,followOperand:it.attribute("followOperand")
								, isDenominator:it.attribute("isDenominator")
								, isIntermidiateVariable:it.attribute("isIntermidiateVariable")
								,isDynamicFinder:it.attribute("isDynamicFinder"),indicator:indicatorr,type:indTypeValue)
								queryParserHelper.save(failOnError: true)

							}
						}
					}


				}

			}
		}

	}


	public void indicatorValueCalculator(List<IntermediateVariable> listOfComputedIntermidiateVariables,DataLocation location){
		// in memory keeping of numerator and denominator queries
		//String okkkkkkkkkkkkk="[{excutableScript:"select equ.code from Equipment as equ inner join equ.status as equipmentStatus where equipmentStatus.status='INSTOCK' and equ.dataLocation='locationidentifier'",classDomaine:"EquipmentStatus",useCountFunction:"true",followOperand:"add",isDenominator:"false",isIntermidiateVariable:"false",isDynamicFinder:"false"},{excutableScript:"select equ.code from Equipment as equ inner join equ.status as equipmentStatus where equipmentStatus.status='OPERATIONAL' and equ.dataLocation='locationidentifier'",classDomaine="EquipmentStatus",useCountFunction:"true",followOperand:"add",isDenominator:"false",isIntermidiateVariable:"false",isDynamicFinder:"false"},{excutableScript:"select equ.code from Equipment as equ inner join equ.status as equipmentStatus where equipmentStatus.status='INSTOCK' or equipmentStatus.status='OPERATIONAL' or equipmentStatus.status='UNDERMAINTENANCE' and equ.dataLocation='locationidentifier'",classDomaine:"EquipmentStatus",useCountFunction:"true",followOperand:"add",isDenominator:"true",isIntermidiateVariable:"false",isDynamicFinder:"false"}]"
		//List<QueryParserHelper> listOfGsonisedQueries=jsonParser(scriptformulaAsJson)
		List<QueryParserHelper> numeratorQueries=new ArrayList<QueryParserHelper>()
		List<QueryParserHelper> denominatorQueries=new ArrayList<QueryParserHelper>()

		String indicatorFileContent = new File('web-app/resources/reportData/indicators.xml').text
		def indicators = new XmlParser().parseText(indicatorFileContent)
		if(location!=null){
			if(location.id>0){
				println"Creating the facility report"
				Date today=new Date()
				String reportCod=ExecutorProvider.idGenerator(""+today.toString())
				DataLocationReport facilityReport=new DataLocationReport(code:reportCod,dataLocation:location,generatedAt:today)
				facilityReport.save(failOnError: true)
				println" the report is created ok"
				indicators.indicator.each{
					int totalAtNumerator=0
					int totalAtDenominator=0
					double indicatorValue=0.0
					println"the indicator code ok :"+it.attribute("indicatorCode")
					Indicator currentIndicator=Indicator.findByCode(it.attribute("indicatorCode"))
					if(currentIndicator!=null){

						println"ready to calculate  indicator values for thi indicator :"+currentIndicator



						String indType=it.type.text()


						def scripts=it.scriptFormula

						scripts.excutableScript.each{

							QueryParserHelper helper=new QueryParserHelper()
							println" parsing and contructing queies"
							helper.executableScript=it.text()
							helper.classDomaine=it.attribute("classDomaine")
							helper.useCountFunction=Boolean.parseBoolean(it.attribute("useCountFunction"))
							helper.followOperand=it.attribute("followOperand")
							helper.isDenominator=Boolean.parseBoolean(it.attribute("isDenominator"))
							helper.isIntermidiateVariable=Boolean.parseBoolean(it.attribute("isIntermidiateVariable"))
							helper.isDynamicFinder=Boolean.parseBoolean(it.attribute("isDynamicFinder"))
							helper.type=indType
							if(helper.isDenominator){
								denominatorQueries.add(helper)
							}else if(!helper.isDenominator){
								numeratorQueries.add(helper)
							}

						}

						//}



						// execute numerators against the db  by applying mathematical operators
						totalAtNumerator=getSumOfNumerators(numeratorQueries,location)


						// execute denominators against the db  by applying mathematical operators


						totalAtDenominator=getSumOfDenominators(denominatorQueries,location)



						// calculating the indicator value

						if(totalAtDenominator>0)
							indicatorValue=totalAtNumerator/totalAtDenominator
						else
							indicatorValue=totalAtNumerator

						println" ===================================total of numerators:"+totalAtNumerator
						println" =================total of denominators:"+totalAtDenominator
						println" ===============================Indicator value is :"+indicatorValue


						DataLocationReport savedFacilityReport=DataLocationReport.findByCode(reportCod)
						if(savedFacilityReport!=null){
							println"Found report object:"+savedFacilityReport
							String indicatorValueCode=ExecutorProvider.idGenerator(today.toString()+""+indicatorValue)
							IndicatorValue indicatorValueObj=new IndicatorValue(code:indicatorValueCode,value:indicatorValue,dataLocationReport:savedFacilityReport,generatedAt:today,indicator:currentIndicator)
							indicatorValueObj.save(failOnError: true)
							println"Indicator value created ok"
						}
					}else
						println" Indicator not found "

				}
			}else
				println"Invalid location object"
		}else
			println"the location is null"
		//return indicatorValue
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
						if(numerator.type.equalsIgnoreCase("Normal")){
							if(!numerator.isDynamicFinder){
								//String queryBe="select equ.code from Equipment as equ inner join equ.status as equipmentStatus where equ.currentStatus=equipmentStatus.status and equipmentStatus.status='INSTOCK' and equ.dataLocation='locationidentifier'"
								//String ontherr=queryBe.replace('locationidentifier',""+locationidentifier+"")
								//def testQuery=session.createQuery(ontherr);
								//println" twageragejeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee wawo:"+testQuery.list()
								def query = session.createQuery(validQueryLocation)

								def results = query.list()

								def c = Equipment.createCriteria()
								def resultss = c.list {
									like("currentStatus", "OPERATIONAL%")
									and {eq("dataLocation.id", "18") }


								}

								println"with creiteria:"+resultss
								if(!numerator.useCountFunction){


									//uniqueNum=className.findAll("from EquipmentStatus where Status='INSTOCK' or Status='OPERATIONAL' or Status='UNDERMAINTENANCE'").size()
									//uniqueNum=className.findAll(numerator.executableScript).size()

									uniqueNum=results[0]


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
						totalAtNumerator=totalAtNumerator+uniqueNum
					}else{
						println"Can't find the class name to query"
					}

				}else{
					println" invalid query passed"
				}
				uniqueNum=0
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace()
		}
		println"Total numerators:"+totalAtNumerator
		return totalAtNumerator
	}
	public int getSumOfDenominators(List<QueryParserHelper> denominatorHelpers,DataLocation location){
		ClassFinder finder=new ClassFinder()
		Class className = null

		int totalAtDenominator=0

		try{
			for(QueryParserHelper denominator:denominatorHelpers){
				int uniqueDenom=0

				if(denominator.classDomaine!=null&& denominator.executableScript!=null){
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
						totalAtDenominator=totalAtDenominator+uniqueDenom
					}else{
						println"Can't find the class name to query"
					}

				}else{
					println" invalid query passed"
				}
				uniqueDenom=0
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace()
		}
		println"Total numerators:"+totalAtDenominator

		return totalAtDenominator
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
