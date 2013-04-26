package org.chai.memms.report

class IndicatorType {
	String code
	CategoryType categoryType
	String name
	String formula
	Double minYellowValue
	Double maxYellowValue
	Boolean increasing
	static belongsTo = [categoryType:CategoryType,indicatorValue:IndicatorValue]
	static hasMany = [internediateValuess:IntermediateValues,indicatorValues:IndicatorValue]
	static mapping ={
		table "memms_indicator_type"
		version false
		content type:"text"
		
	}
	static constraints = {
		code (blank:false, nullable:false)
		name (blank:false, nullable:false,unique: true, size:3..10, matches:"[a-zA-Z1-9_]+")
		
		
	}
}
