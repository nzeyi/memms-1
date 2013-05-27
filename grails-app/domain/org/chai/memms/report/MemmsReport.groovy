package org.chai.memms.report

class MemmsReport {
	String code
	Date createdAt
	List<DataLocationReport> correctiveMaintenanceReports=new ArrayList<DataLocationReport>()
	List<DataLocationReport> privantiveMaintenanceReports=new ArrayList<DataLocationReport>()
	List<DataLocationReport> manageMemmEquipmentRreports=new ArrayList<DataLocationReport>()
	List<DataLocationReport> manageMemmsUseReports=new ArrayList<DataLocationReport>()
	List<DataLocationReport> manageSparePartsReports=new ArrayList<DataLocationReport>()
	List<IndicatorCategory> categories=new ArrayList<IndicatorCategory>()


	static hasMany = [dataLocationReports:DataLocationReport]
	static mapping ={
		table "memms_report_memms_report"
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
