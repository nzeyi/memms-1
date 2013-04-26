package org.chai.memms.report;

public class InternediateValues {
	String name
	String formula
	String code
	static hasMany = [internediateValues:InternediateValues,indicatorTypes:IndicatorType]
	static mapping ={
		table "memms_intermidiate_values"
		version false
		content type:"text"
		
	}
	static constraints = {
		code (blank:false, nullable:false)
		name (blank:false, nullable:false,unique: true, size:3..100, matches:"[a-zA-Z1-9_]+")
		formula(blank:false, nullable:false)
		
	}
}
