package org.chai.memms.report

class QuaterlyFacilityReport {
	Date time
	String code
	FacilityReport report
	Integer year
	Integer month
	String quarter
	static mapping ={
		table "memms_quatery_facility_report"
		version false
		content type:"text"
	}
	static constraints = {
		code (blank:false, nullable:false)
		quarter (blank:false, nullable:false)
	}
	@Override
	public String toString() {
		return code;
	}
}