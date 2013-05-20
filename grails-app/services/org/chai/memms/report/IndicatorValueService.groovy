package org.chai.memms.report

import java.util.Map;
import org.chai.location.DataLocationType
import org.chai.location.DataLocation
class IndicatorValueService {

	def dataLocationReportService

	public def getIndicatorValuesByDataLocation(DataLocation dataLocation){
		List<IndicatorValue> indicatorValues=new ArrayList<IndicatorValue>()
		def reports=dataLocationReportService.getDataLocationReportsByDataLocation(dataLocation)
		for(DataLocationReport dataLocationreport:reports){
			if(dataLocationreport.indicatorValues!=null){
				indicatorValues.addAll(dataLocationreport.indicatorValues)
			}
		}
		return indicatorValues
	}
	public def getIndicatorValuesByDataLocationType(DataLocationType dataLocationType){
		List<IndicatorValue> indicatorValues=new ArrayList<IndicatorValue>()
		def reports=dataLocationReportService.getDataLocationReportsByDataLocationType(dataLocationType)
		for(DataLocationReport dataLocationreport:reports){
			if(dataLocationreport.indicatorValues!=null){
				indicatorValues.addAll(dataLocationreport.indicatorValues)
			}
		}
		return indicatorValues
	}
	public def searchIndicatorValue(String text,Map<String, String> params) {
		text = text.trim()

		def criteria = IndicatorValue.createCriteria()
		return criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){

			ilike("code","%"+text+"%")
			ilike("indicatorValue","%"+text+"%")
		}
	}

	def getIndicatorValues(){
		return IndicatorValue.findAll()
	}
}
