package org.chai.memms.report



import java.lang.reflect.Type;

import grails.converters.*
import org.codehaus.groovy.grails.web.json.*


import org.chai.memms.inventory.Equipment;
import org.chai.memms.inventory.EquipmentStatus;
import org.chai.memms.report.utils.*
import com.google.gson.*;
import com.google.gson.reflect.TypeToken
import org.chai.memms.Inventory.*
import org.chai.location.DataLocationType;
import org.chai.location.DataLocation;

import groovy.util.XmlParser
import groovy.xml.XmlUtil

class IndicatorTypeService {
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
	public void indicatorWriterFromXml(){

		if(!CategoryType.count()){
			String indicatorCategoryFileContent = new File('web-app/resources/reportData/indicatorCategories.xml').text
			def categories = new XmlParser().parseText(indicatorCategoryFileContent)
			categories.category.each{

				def categoryType = new CategoryType(name:it.name.text(),code:it.attribute("categoryCode"),minYellowValue:it.minYellowValue.text(),maxYellowValue:it.maxYellowValue.text())

				categoryType.save(failOnError: true)
			}
		}
		String indicatorFileContent = new File('web-app/resources/reportData/indicators.xml').text
		def indicators = new XmlParser().parseText(indicatorFileContent)
		if(!IndicatorType.count()){
			//			String indicatorFileContent = new File('web-app/resources/reportData/indicators.xml').text
			//			def indicators = new XmlParser().parseText(indicatorFileContent)
			CategoryType category=null
			indicators.indicator.each{
				category=CategoryType.findByCode(it.attribute("categoryCode"))
				if(category!=null){
					println"formula :"+it.scriptFormula.text()
					println"with no text() :"+it.scriptFormula
					def scripts=it.scriptFormula
					def indicator = new IndicatorType(indicatorName:it.name.text(),code:it.attribute("indicatorCode"),categoryType:category,formula:it.scriptFormula.text(),finder:it.attribute("indicatorCode"))

					indicator.save(failOnError: true)

					IndicatorType indicatortype=IndicatorType.findByCode(it.attribute("indicatorCode"))
					if(scripts!=null&&indicatortype!=null){
						def queryParserHelpers=QueryParserHelper.findByIndicatorType(indicatortype)

						if(queryParserHelpers==null){
							scripts.excutableScript.each{

								def queryParserHelper=new QueryParserHelper(excutableScript:it.text(),classDomaine:it.attribute("classDomaine")
								,useCountFunction:it.attribute("useCountFunction")
								,followOperand:it.attribute("followOperand")
								, isDenominator:it.attribute("isDenominator")
								, isIntermidiateVariable:it.attribute("isIntermidiateVariable")
								,isDynamicFinder:it.attribute("isDynamicFinder"),indicatorType:indicatortype)
								queryParserHelper.save(failOnError: true)

							}
						}
					}
				}
			}
		}else{
			println"ok ok some indicators ========================="
			//			String indicatorFileContent = new File('web-app/resources/reportData/indicators.xml').text
			//			def indicators = new XmlParser().parseText(indicatorFileContent)
			CategoryType category=null
			IndicatorType oldInd=null
			indicators.indicator.each{
				category=CategoryType.findByCode(it.attribute("categoryCode"))
				if(category!=null){

					oldInd=IndicatorType.findByCode(it.attribute("indicatorCode"))
					if(oldInd==null){
						println" the indicator is null"
						def indicator = new IndicatorType(indicatorName:it.name.text(),code:it.attribute("indicatorCode"),categoryType:category,formula:it.scriptFormula.text(),finder:it.attribute("indicatorCode"))

						indicator.save(failOnError: true)
						println" created new indicator"
					}
					else if(oldInd!=null){
						println" found current indicator to change formula"
						oldInd.formula=it.scriptFormula.text()
						oldInd.categoryType=category
						oldInd.save(failOnError: true)
					}else
						println" will not change the formula"

					def scripts=it.scriptFormula

					IndicatorType indicatortype=IndicatorType.findByCode(it.attribute("indicatorCode"))

					if(scripts!=null&&indicatortype!=null){
						println" get indicator type"
						def queryParserHelpers=QueryParserHelper.findByIndicatorType(indicatortype)

						if(queryParserHelpers==null){
							println" adding formula"
							scripts.excutableScript.each{
								def queryParserHelper=new QueryParserHelper(excutableScript:it.text(),classDomaine:it.attribute("classDomaine")
								,useCountFunction:it.attribute("useCountFunction")
								,followOperand:it.attribute("followOperand")
								, isDenominator:it.attribute("isDenominator")
								, isIntermidiateVariable:it.attribute("isIntermidiateVariable")
								,isDynamicFinder:it.attribute("isDynamicFinder"),indicatorType:indicatortype)
								queryParserHelper.save(failOnError: true)
								println" i am adding script to this :"+indicatortype
							}
						}
						println"end ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo crated category"
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
					double indicatorValue=0.0
					println"the indicator code ok :"+it.attribute("indicatorCode")
					IndicatorType currentIndicatorType=IndicatorType.findByCode(it.attribute("indicatorCode"))
					if(currentIndicatorType!=null){

						println"ready to calculate  indicator values for thi indicator :"+currentIndicatorType


						//if(it.attribute("indicatorCode").equals("MANA_EQUIPMENT_SHARE_OF_OPERATIONAL_EQUIPMENT")){
						//println"Indicator name :"+it.name.text()
						//println"Indicator code :"+it.attribute("indicatorCode")



						def scripts=it.scriptFormula

						scripts.excutableScript.each{

							QueryParserHelper helper=new QueryParserHelper()
							println" parsing and contructing queies"
							helper.excutableScript=it.text()
							helper.classDomaine=it.attribute("classDomaine")
							helper.useCountFunction=Boolean.parseBoolean(it.attribute("useCountFunction"))
							helper.followOperand=it.attribute("followOperand")
							helper.isDenominator=Boolean.parseBoolean(it.attribute("isDenominator"))
							helper.isIntermidiateVariable=Boolean.parseBoolean(it.attribute("isIntermidiateVariable"))
							helper.isDynamicFinder=Boolean.parseBoolean(it.attribute("isDynamicFinder"))

							if(helper.isDenominator){
								denominatorQueries.add(helper)
							}else if(!helper.isDenominator){
								numeratorQueries.add(helper)
							}

						}

						//}



						// execute numerators against the db  by applying mathematical operators
						int totalAtNumerator=getSumOfNumerators(numeratorQueries,location)


						// execute denominators against the db  by applying mathematical operators


						int totalAtDenominator=getSumOfDenominators(denominatorQueries,location)



						// calculating the indicator value

						if(totalAtDenominator>0)
							indicatorValue=totalAtNumerator/totalAtDenominator
						else
							indicatorValue=totalAtNumerator

						println" ===================================total of numerators:"+totalAtNumerator
						println" =================total of denominators:"+totalAtDenominator
						println" ===============================Indicator value is :"+indicatorValue

						println" Ready to create any indicator value object for the indicator:"+currentIndicatorType+"with this value :"+indicatorValue+" on :"+new Date()

						DataLocationReport savedFacilityReport=DataLocationReport.findByCode(reportCod)
						if(savedFacilityReport!=null){
							println"Found report object:"+savedFacilityReport
							String indicatorValueCode=ExecutorProvider.idGenerator(today.toString()+""+indicatorValue)
							IndicatorValue indicatorValueObj=new IndicatorValue(code:indicatorValueCode,value:indicatorValue,dataLocationReport:savedFacilityReport,generatedAt:today,indicatorType:currentIndicatorType)
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

				if(numerator.classDomaine!=null&& numerator.excutableScript!=null){
					className = finder.findClassByName(numerator.classDomaine)


					if(className!=null){
						def session = sessionFactory.getCurrentSession()
						//DataLocation location=DataLocation.findById(18)
						int locationidentifier=location.id

						String validQueryLocation=numerator.excutableScript.replace('locationidentifier',""+locationidentifier+"")
						if(!numerator.isDynamicFinder){


							def query = session.createQuery(validQueryLocation)

							def results = query.list()

							println" how many numerator results ok hanooo:"+results.size()
							Object numberInObject=Equipment.executeQuery("select code from Equipment eq where eq.id=1")
							if(numberInObject!=null){
								if(!numerator.useCountFunction){


									//uniqueNum=className.findAll("from EquipmentStatus where Status='INSTOCK' or Status='OPERATIONAL' or Status='UNDERMAINTENANCE'").size()
									//uniqueNum=className.findAll(numerator.excutableScript).size()

									uniqueNum=results[0]
									println" don't apply count: "+results

								}else{
									println" befor apply count: "
									//uniqueNum=numberInObject.count

									uniqueNum=results.size()
									println" counted is ok number: "+uniqueNum

								}
							}else
								println"The query returned null"

						}else if(numerator.isDynamicFinder){
							uniqueNum=className.findAll(numerator.excutableScript).size()
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

				if(denominator.classDomaine!=null&& denominator.excutableScript!=null){
					className = finder.findClassByName(denominator.classDomaine)


					if(className!=null){
						def session = sessionFactory.getCurrentSession()
						//DataLocation location=DataLocation.findById(18)
						int locationidentifier=location.id

						String validQueryLocation=denominator.excutableScript.replace('locationidentifier',""+locationidentifier+"")
						if(!denominator.isDynamicFinder){


							def query = session.createQuery(validQueryLocation)

							def results = query.list()

							println" how many numerator results ok hanooo:"+results.size()
							Object numberInObject=Equipment.executeQuery("select code from Equipment eq where eq.id=1")
							if(numberInObject!=null){
								if(!denominator.useCountFunction){


									//uniqueNum=className.findAll("from EquipmentStatus where Status='INSTOCK' or Status='OPERATIONAL' or Status='UNDERMAINTENANCE'").size()
									//uniqueNum=className.findAll(numerator.excutableScript).size()

									uniqueDenom=results[0]
									println" don't apply count: "+results

								}else{
									println" befor apply count: "
									//uniqueNum=numberInObject.count

									uniqueDenom=results.size()
									println" counted is ok number: "+uniqueDenom

								}
							}else
								println"The query returned null"

						}else if(denominator.isDynamicFinder){
							uniqueDenom=className.findAll(denominator.excutableScript).size()
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
	public List<QueryParserHelper>  jsonParser(String scriptformulaAsJson){
		double indicatorValue=0

		Gson gson = new Gson()
		List<QueryParserHelper> listOfGsonisedQueries =null
		println" starting josnonizing ok "

		try{

			JsonParser parser = new JsonParser()
			JsonArray jarray = parser.parse(scriptformulaAsJson).getAsJsonArray()

			listOfGsonisedQueries = new ArrayList<QueryParserHelper>()

			//println"json array is listggggggggggg22222222222222222:"+yourClassList

			for(JsonElement jsonObject : jarray)

			{
				//println" json object is:"+jsonObject
				//println" json object issssssssssssssssssssssss:"+jsonObject.getAsJsonObject()
				QueryParserHelper helperGson = gson.fromJson( jsonObject , QueryParserHelper.class)

				println" json object helper is:"+helperGson
				listOfGsonisedQueries.add(helperGson);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace()
		}

		return listOfGsonisedQueries
	}


}
