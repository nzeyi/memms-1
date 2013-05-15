package org.chai.memms.report
import org.chai.location.DataLocationType;



import org.chai.location.DataLocation;

class DataLocationReport {
	String code
	DataLocation dataLocation
	DataLocationType dataLocationType
	Date generatedAt
	static hasMany = [indicatorValues:IndicatorValue]
	static mapping ={
		table "memms_report_data_location"
		version false
		content type:"text"
		
	}
	static constraints = {
		code (blank:false, nullable:false)
		
		
		
	}
	@Override
	public String toString() {
		return code;
	}
}

