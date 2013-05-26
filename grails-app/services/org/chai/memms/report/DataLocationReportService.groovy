package org.chai.memms.report

import java.util.Map;

import org.chai.location.DataLocation;
import org.chai.location.DataLocationType;

class DataLocationReportService {

	static transactional = true

	def sessionFactory


	def dataSource  //Auto Injected



	public def searchDataLocationReport(String text,Map<String, String> params) {
		text = text.trim()
		def criteria = DataLocationReport.createCriteria()
		return criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){
			or{
				ilike("code","%"+text+"%")
				ilike("dataLocation","%"+text+"%")

			}
		}
	}

	public def getCurrentDataLocationReportsByIndicatorCategoryAndMemmsReport(IndicatorCategory category,MemmsReport memmsReport){
		if(category!=null&&memmsReport!=null)
			return DataLocationReport.findAllByIndicatorCategoryAndMemmsReport(category,memmsReport)
		else
			return null
	}
	public def getDataLocationReportsByDataLocation(DataLocation dataLocation){
		def reports=DataLocationReport.findByDataLocation(dataLocation)
		return reports
	}


	public def getDataLocationReports(){
		return DataLocationReport.findAll()
	}
}
