package org.chai.memms.report

class MonthlyDataLocationReport {
	String code
	Date generatedAt
	DataLocationReport report
	Integer year
	Integer month
	static mapping ={
		table "memms_report_monthly_data_location"
		version false
		content type:"text"
		
	}
	static constraints = {
		code (blank:false, nullable:false)
		month (blank:false, nullable:false)
		
		
	}
	@Override
	public String toString() {
		return code;
	}
}
