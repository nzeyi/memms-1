package org.chai.memms.report

import java.util.Map;

class QueryParserHelperService {
	public def getQueryParserHelpers(){
		return QueryParserHelper.findAll()
	}
	public def searchQueryParserHelper(String text,Map<String, String> params) {
		text = text.trim()

		def criteria = QueryParserHelper.createCriteria()
		return criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){

			ilike("code","%"+text+"%")
			ilike("classDomaine","%"+text+"%")
			ilike("executableScript","%"+text+"%")
			ilike("type","%"+text+"%")

		}
	}
}
