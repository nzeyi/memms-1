package org.chai.memms.report
import org.chai.location.DataLocationType;
class IndicatorColorCriteriaPerDataLocationType {
	String minYellow
	String maxYellow
	Boolean isIncreasing
	String code
	DataLocationType dataLocationType
	Indicator indicator
	static mapping ={
		table "memms_report_indicator_color_criteria"
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
