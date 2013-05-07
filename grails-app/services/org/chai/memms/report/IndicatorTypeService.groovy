package org.chai.memms.report



import grails.converters.*
import org.codehaus.groovy.grails.web.json.*


import org.chai.memms.inventory.EquipmentStatus;
import org.chai.memms.report.utils.*
import com.google.gson.*;
import com.google.gson.reflect.TypeToken
import org.chai.memms.Inventory.*
import org.chai.location.DataLocationType;
import org.chai.location.DataLocation;



class IndicatorTypeService {
	static transactional = true
	def sessionFactory;


	def dataSource  //Auto Injected


	public  List<IndicatorType> getIndicatorTypes(){
		def indicators=IndicatorType.findAll()

		return indicators
	}

	public List<QueryParserHelper>  jsonParser(String scriptformulaAsJson){
		double indicatorValue=0

		Gson gson = new Gson()
		List<QueryParserHelper> listOfGsonisedQueries =null


		try{
			JsonParser parser = new JsonParser()
			JsonArray Jarray = parser.parse(scriptformulaAsJson).getAsJsonArray()

			listOfGsonisedQueries = new ArrayList<QueryParserHelper>()

			for(JsonElement jsonObject : Jarray )
			{
				QueryParserHelper helperGson = gson.fromJson( jsonObject , QueryParserHelper.class)

				listOfGsonisedQueries.add(helperGson);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace()
		}
		return listOfGsonisedQueries
	}

	public double indicatorValueCalculator(List<IntermediateVariable> listOfComputedIntermidiateVariables,String scriptformulaAsJson){
		// in memory keeping of numerator and denominator queries
		List<QueryParserHelper> listOfGsonisedQueries=jsonParser(scriptformulaAsJson)
		List<QueryParserHelper> numeratorQueries=new ArrayList<QueryParserHelper>()
		List<QueryParserHelper> denominatorQueries=new ArrayList<QueryParserHelper>()
		double indicatorValue=0.0

		for(QueryParserHelper helper:listOfGsonisedQueries){
			if(helper.isDenominator){
				denominatorQueries.add(helper)
			}else if(!helper.isDenominator){
				numeratorQueries.add(helper)
			}

		}


		// execute numerators against the db  by applying mathematical operators
		int totalAtNumerator=getSumOfNumerators(numeratorQueries)


		// execute denominators against the db  by applying mathematical operators


		int totalAtDenominator=getSumOfDenominators(denominatorQueries)


		// calculating the indicator value

		if(totalAtDenominator>0)
			indicatorValue=totalAtNumerator/totalAtDenominator
		else
			indicatorValue=totalAtNumerator

		println" ===================================total of numerators:"+totalAtNumerator
		println" =================total of denominators:"+totalAtDenominator
		println" ===============================Indicator value is :"+indicatorValue

		return indicatorValue
	}

	public int getSumOfNumerators(List<QueryParserHelper> numeratorHelpers){
		ClassFinder finder=new ClassFinder()
		Class className = null

		int totalAtNumerator=0

		try{
			for(QueryParserHelper numerator:numeratorHelpers){
				int uniqueNum=0

				if(numerator.domainclass!=null&& numerator.excutableScript){
					className = finder.findClassByName(numerator.domainclass)


					if(className!=null){

						if(!numerator.isDynamicFinder){

							if(numerator.useCountFunction){


								//uniqueNum=className.findAll("from EquipmentStatus where Status='INSTOCK' or Status='OPERATIONAL' or Status='UNDERMAINTENANCE'").size()
								uniqueNum=className.findAll(numerator.excutableScript).size()


							}else{

								uniqueNum=className.findAll(numerator.excutableScript).size()

							}

						}else if(numerator.isDynamicFinder){
							println" I will use dynamic query here"
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
		return totalAtNumerator
	}
	public int getSumOfDenominators(List<QueryParserHelper> denominatorHelpers){
		ClassFinder finder=new ClassFinder()
		Class className = null
		int totalAtDenominator=0
		try{
			for(QueryParserHelper denomunator:denominatorHelpers){
				println" in denominator queries"
				int uniqueden=0
				if(denomunator.domainclass!=null&& denomunator.excutableScript){
					className = finder.findClassByName(denomunator.domainclass)


					if(className!=null){
						if(!denomunator.isDynamicFinder){

							if(denomunator.useCountFunction){


								uniqueden=className.findAll(denomunator.excutableScript).size()



							}else{

								uniqueden=className.findAll(denomunator.excutableScript).size()

							}

						}else if(denomunator.isDynamicFinder){
							println" I will use dynamic query here"
						}
						// update the sum after query excution in all possible cases at denominator area
						totalAtDenominator=totalAtDenominator+uniqueden
					}else{
						println"Can't find the class name to query"
					}
				}else{
					println"invalid query here"
				}



			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace()
		}
		return totalAtDenominator
	}

	List<DataLocation> getDataLications(){
		def dataLocations=null
		dataLocations=DataLocation.findAll()
		return dataLocations
	}

	public void reportingEngine(){
		def dataLocationTypes=getDataLications()
		def indicators=getIndicatorTypes();

		def intermidiateValiables
	}
}
