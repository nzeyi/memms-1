package org.chai.memms.report

import org.chai.location.DataLocation;

class IndicatorValue {
	String code 
	Date time
	Double value
	FacilityReport report
	IndicatorType type
	static mapping ={
		table "memms_indicator_values"
		version false
		content type:"text"
		
	}
	static constraints = {
		code (blank:false, nullable:false)
		value (blank:false, nullable:false)
		
		
	}
}
