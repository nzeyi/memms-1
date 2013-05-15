package org.chai.memms.report

import java.util.Map;

class IndicatorCategoryService {
	
	def getIndicatorCategories(){
		return IndicatorCategory.findAll()
	}
	public def searchIndicatorCategory(String text,Map<String, String> params) {
		text = text.trim()

		def criteria = IndicatorCategory.createCriteria()
		return criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){

			ilike("code","%"+text+"%")
			ilike("name","%"+text+"%")
			

		}
	}
}
