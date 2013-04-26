package org.chai.memms.report

class MonthlyFacilityReport {
	String code
	Date time
	FacilityReport report
	Integer year
	Integer month
	static mapping ={
		table "memms_monthly_facility_report"
		version false
		content type:"text"
		
	}
	static constraints = {
		code (blank:false, nullable:false)
		month (blank:false, nullable:false)
		
		
	}
}
