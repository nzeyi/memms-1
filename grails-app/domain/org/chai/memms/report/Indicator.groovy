package org.chai.memms.report
import groovy.transform.EqualsAndHashCode;
import i18nfields.I18nFields
@i18nfields.I18nFields
@EqualsAndHashCode(includes="code")
class Indicator {
	String code
	String names
	String formula
	String type
	String unit
	
	static i18nFields = ["names"]
	static belongsTo = [indicatorCategory:IndicatorCategory,indicatorColorCriterion:IndicatorColorCriterion]
	static hasMany = [indicatorValues:IndicatorValue,queryParserHelpers:QueryParserHelper]

	static mapping ={
		table "memms_report_indicator"
		version false
		content type:"text"
		cache true
		
	}
	
	static constraints = {
		code blank:false, nullable:false
		names nullable: true, blank: true
	    formula blank:true, nullable:true,size:3..1000
	}
	@Override
	public String toString() {
	return "Indicator[id="+id+"code=" + code + "]";
	}
}
