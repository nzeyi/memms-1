package org.chai.memms.report;

public class IntermediateValues {
	String name
	String formula
	String  code
	Double value
	IntermediateValues intermediateValues
	static belongsTo =IndicatorType
	static hasMany = [indicatorTypes:IndicatorType]
	static mapping ={
		table "memms_intermidiate_values"
		version false
		content type:"text"
		
	}
	static constraints = {
		code (blank:false, nullable:false)
		name (blank:false, nullable:false,size:3..100)
		formula(blank:false, nullable:false,size:3..500)
		
	}
	@Override
	public String toString() {
		return name;
	}
}
