package org.chai.memms.report

import java.util.Map;

class IntermediateVariableService {
	def getIntermediateVariables(){
		return IntermediateVariable.findAll()
	}
	public def searchIntermediateVariable(String text,Map<String, String> params) {
		text = text.trim()

		def criteria = IntermediateVariable.createCriteria()
		return criteria.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc"){

			ilike("code","%"+text+"%")
			ilike("indicatorName","%"+text+"%")
			ilike("indicatorType","%"+text+"%")

		}
	}
}
