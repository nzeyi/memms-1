package org.chai.memms.report

import java.util.Map;

class DataLocationReportService {
	public def getDataLocationReports(){
		return DataLocationReport.findAll()
	}

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
}
