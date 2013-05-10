package org.chai.memms.report
import groovy.transform.EqualsAndHashCode;
import i18nfields.I18nFields
@i18nfields.I18nFields
@EqualsAndHashCode(includes="code")
class Indicator {
	String code
	CategoryType categoryType
	String indicatorName
	String formula
	String indicatorType
	
	static i18nFields = ["indicatorName",]
	static belongsTo = [categoryType:CategoryType]
	static hasMany = [internediateValuess:IntermediateVariable,indicatorValues:IndicatorValue,indicatorColors:IndicatorColorCriteriaPerDataLocationType,queryParserHelpers:QueryParserHelper]
	static mapping ={
		table "memms_report_indicator"
		version false
		content type:"text"
		cache true
		
	}
	static constraints = {
		code blank:false, nullable:false
		indicatorName nullable: true, blank: true
	    formula blank:true, nullable:true,size:3..1000
		
		
		
	}
	@Override
	public String toString() {
		return indicatorName;
	}
}
