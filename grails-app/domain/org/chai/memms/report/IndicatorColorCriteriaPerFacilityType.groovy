package org.chai.memms.report
import org.chai.location.DataLocationType;
class IndicatorColorCriteriaPerFacilityType {
	String minYellow
	String maxYellow
	Boolean increasing
	String code
	DataLocationType facilityType
	IndicatorType indicatorType
	static mapping ={
		table "memms_indicator_color_criteria"
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
