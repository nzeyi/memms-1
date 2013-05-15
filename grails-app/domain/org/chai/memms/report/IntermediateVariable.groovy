package org.chai.memms.report;
import groovy.transform.EqualsAndHashCode;
import i18nfields.I18nFields
@i18nfields.I18nFields
@EqualsAndHashCode(includes="code")
public class IntermediateVariable {
	String code
	String names
	String executableScript

	Double executionResult

	static i18nFields = ["names"]
	static belongsTo =Indicator
	static hasMany = [indicators:Indicator]

	static mapping ={
		table "memms_report_int_vble"
		version false
		content type:"text"
	}
	static constraints = {
		code (blank:false, nullable:true)
		names (blank:true, nullable:true,size:3..100)
		executableScript(blank:true, nullable:true,size:3..500)
		/*intermediateVariables (validator: {value, obj, errors->
			if(value!=null)
				if(value.contains(obj)){
					errors.rejectValue('intermediateVariables', 'Cannot be a dependencies of its self')
				}
		})*/
	}

	@Override
	public String toString() {
		return "IntermediateVariable[id="+id+"code=" + code + "]";
	}
}
