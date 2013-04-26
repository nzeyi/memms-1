package org.chai.memms.report

import org.chai.location.DataLocation;

class FacilityReport {
	Date time
	DataLocation facility
	String code
	
	static hasMany = [indicatorValues:IndicatorValue]
	static mapping ={
		table "memms_facility_report"
		version false
		content type:"text"
		
	}
	static constraints = {
		code (blank:false, nullable:false)
		
		
		
	}
}

