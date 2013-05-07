package org.chai.memms.report

class QuaterlyDataLocationReport {
	Date geneartedAt
	String code
	DataLocationReport report
	Integer year
	Integer month
	String quarter
	static mapping ={
		table "memms_report_quatery_data_location"
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