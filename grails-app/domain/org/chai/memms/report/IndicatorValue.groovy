package org.chai.memms.report

import org.chai.location.DataLocation;

class IndicatorValue {
	String code 
	Date time
	Double value
	DataLocationReport report
	//IndicatorType type
	static belongsTo = [indicatorType:IndicatorType]
	static mapping ={
		table "memms_report_indicator_value"
		version false
		content type:"text"
		
	}
	static constraints = {
		code (blank:false, nullable:false)
		value (blank:false, nullable:false)
		
		
	}
	@Override
	public String toString() {
		return code;
	}
}
