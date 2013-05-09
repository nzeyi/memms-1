package org.chai.memms.report

import org.chai.location.DataLocation;

class IndicatorValue {
	String code 
	Date generatedAt
	Double value
	//DataLocationReport dataLocationReport
	//IndicatorType type
	static belongsTo = [indicatorType:IndicatorType,dataLocationReport:DataLocationReport]
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
