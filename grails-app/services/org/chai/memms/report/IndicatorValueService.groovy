package org.chai.memms.report

import java.util.Map;

class IndicatorValueService {

	def getIndicatorValues(){
		return IndicatorValue.findAll()
	}
	public def searchIndicatorValue(String text,Map<String, String> params) {
		text = text.trim()

		def criteria = IndicatorValue.createCriteria()
		return criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){

			ilike("code","%"+text+"%")
			ilike("indicatorValue","%"+text+"%")

		}
	}
}
