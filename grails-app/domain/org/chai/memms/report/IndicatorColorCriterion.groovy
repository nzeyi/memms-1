package org.chai.memms.report

import org.chai.location.DataLocationType

class IndicatorColorCriterion {
	String code
	Double minYellowHc
	Double maxYellowHc
	Double minYellowDh
	Double maxYellowDh
	Boolean isIncreasing
	
	static hasMany = [indicators:Indicator]
	
	static mapping ={
		table "memms_report_indicator_color_criteria"
		version false
		content type:"text"
		
	}
	static constraints = {
		code (blank:false, nullable:false)
		minYellowHc (blank:true, nullable:true)
		maxYellowHc (blank:true, nullable:true)
		minYellowDh (blank:true, nullable:true)
		maxYellowDh (blank:true, nullable:true)
	}
	@Override
	public String toString() {
		return code
	}
}
