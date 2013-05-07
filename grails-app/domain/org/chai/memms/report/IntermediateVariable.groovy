package org.chai.memms.report;

public class IntermediateVariable {
	String name
	String formula
	String code
	Double value
	//List dependencies
	static belongsTo =IndicatorType
	static hasMany = [indicatorTypes:IndicatorType,dependencies: IntermediateVariable]
	
	static mapping ={
		table "memms_report_int_vble"
		version false
		content type:"text"
	
	}
	static constraints = {
		code (blank:false, nullable:false)
		name (blank:false, nullable:false,size:3..100)
		formula(blank:false, nullable:false,size:3..500)
		dependencies (validator: {value, obj, errors->
			if(value.contains(obj)){
			  errors.rejectValue('dependencies', 'Cannot be a dependencies of its self')
			}})
		
		
	}
	
	@Override
	public String toString() {
		return name;
	}
}
