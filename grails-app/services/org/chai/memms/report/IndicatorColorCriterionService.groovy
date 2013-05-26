package org.chai.memms.report;
import java.util.Map;
import org.chai.memms.util.Utils;

import org.chai.memms.report.IndicatorColorCriterion
import org.chai.memms.report.utils.ExecutorProvider;
public class IndicatorColorCriterionService {

	static transactional = true

	def sessionFactory


	def dataSource  //Auto Injected
	def indicatorService
	public def getIndicatorColorCriteria(){
		return IndicatorColorCriterion.findAll()
	}



	public void indicatorColorCriterionrwiter(){

		if(!IndicatorColorCriterion.count()){

			String indicatorColorCriterionFileContent = new File('web-app/resources/reportData/colorConfig.xml').text

			def colororCroteria = new XmlParser().parseText(indicatorColorCriterionFileContent)

			colororCroteria.indicatorColor.each{

				Indicator indicator=indicatorService.getIndicatorByCode(it.attribute("indicatorCode"))
				if(indicator!=null){

					Double minYellowDh=0
					Double maxYellowDh=0
					if(ExecutorProvider.isDouble(it.minYellowValueDh.text())&&ExecutorProvider.isDouble(it.maxYellowValueDh.text())){
						minYellowDh=Double.parseDouble(it.minYellowValueDh.text())
						maxYellowDh=Double.parseDouble(it.maxYellowValueDh.text())
					}

					def colorCriterion = new IndicatorColorCriterion(code:it.attribute("code"),minYellowHc:it.minYellowValueHc.text(),maxYellowHc:it.maxYellowValueHc.text(),minYellowDh:minYellowDh,maxYellowDh:maxYellowDh,isIncreasing:Boolean.parseBoolean(it.isIncreasing.text()),indicator:indicator)

					colorCriterion.save(failOnError:true)
				}
				else
					println"indicator was null:"+it.attribute("indicatorCode")

			}
		}else{
			println"already there"
		}



	}



	public def searchIndicatorColorCriterion(String textParam,Map<String, String> params) {
		def text = textParam.trim()
		def criteria = IndicatorColorCriterion.createCriteria()
		return criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			or{
				ilike("code","%"+text+"%")
				ilike("names","%"+text+"%")
				ilike("type","%"+text+"%")
			}
		}
	}

}
