package org.chai.memms.report;

public class IntermediateVariable {
	String name
	String executableScript
	String code
	Double executionResult
	
	
	static belongsTo =Indicator
	static hasMany = [indicators:Indicator,intermediateVariables: IntermediateVariable]
	
	static mapping ={
		table "memms_report_int_vble"
		version false
		content type:"text"
	
	}
	static constraints = {
		code (blank:false, nullable:false)
		name (blank:false, nullable:false,size:3..100)
		executableScript(blank:false, nullable:false,size:3..500)
		intermediateVariables (validator: {value, obj, errors->
			if(value.contains(obj)){
			  errors.rejectValue('intermediateVariables', 'Cannot be a dependencies of its self')
			}})
		
		
	}
	
	@Override
	public String toString() {
		return name;
	}
}
