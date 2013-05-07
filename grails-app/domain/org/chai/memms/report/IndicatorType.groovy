package org.chai.memms.report
import groovy.transform.EqualsAndHashCode;
import i18nfields.I18nFields
@i18nfields.I18nFields
@EqualsAndHashCode(includes="code")
class IndicatorType {
	String code
	CategoryType categoryType
	String name
	String formula
	Boolean increasing
	String finder
	static i18nFields = ["name"]
	static belongsTo = [categoryType:CategoryType]
	static hasMany = [internediateValuess:IntermediateVariable,indicatorValues:IndicatorValue,indicatorColors:IndicatorColorCriteriaPerFacilityType]
	static mapping ={
		table "memms_report_indicator_type"
		version false
		content type:"text"
		
	}
	static constraints = {
		code (blank:false, nullable:false)
		name (blank:false, nullable:false,unique: true, size:3..100)
	    formula(blank:false, nullable:false,size:3..1000)
		finder(blank:true, nullable:true,size:3..100)
		
		
	}
	@Override
	public String toString() {
		return name;
	}
}
