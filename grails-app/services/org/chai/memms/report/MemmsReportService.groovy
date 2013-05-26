package org.chai.memms.report
import java.util.Set
import java.util.Map

import org.apache.commons.lang.math.NumberUtils
import org.chai.memms.report.utils.ExecutorProvider;
import org.springframework.dao.DataIntegrityViolationException
class MemmsReportService {
	static transactional = true

	def sessionFactory


	def dataSource
	//def indicatorValueService
	def dataLocationReportService
	def indicatorCategoryService

	public def getCorrectiveMaintenanceReports(){
		List<DataLocationReport> correctiveMainDatalocationReports=new ArrayList<DataLocationReport>()
		def reultList=getCurrentDataLocationReportsByIndicatorCategory(ExecutorProvider.CORRECTIVE_MAINTENANCE)
		if(reultList!=null)
			correctiveMainDatalocationReports.addAll(reultList)
		
		return correctiveMainDatalocationReports
	}

	public def getPreventiveMaintenanceReports(){
		List<DataLocationReport> preventiveMaintDatalocationReports=new ArrayList<DataLocationReport>()
		def reultList=getCurrentDataLocationReportsByIndicatorCategory(ExecutorProvider.PRIVENTIVE_MAINTENANCE)
		if(reultList!=null)
			preventiveMaintDatalocationReports.addAll(reultList)

		return preventiveMaintDatalocationReports
	}
	public def getManageMemmsEquipmentReports(){
		List<DataLocationReport> mamageMedicalEquipmentDatalocationReports=new ArrayList<DataLocationReport>()
		def reultList=getCurrentDataLocationReportsByIndicatorCategory(ExecutorProvider.MANAGEMENT_MEDICAL_EQUIPEMENT)
		if(reultList!=null)
			mamageMedicalEquipmentDatalocationReports.addAll(reultList)

		return mamageMedicalEquipmentDatalocationReports
	}

	public def getMemmsParePartReports(){
		List<DataLocationReport> mamageSparepartsDatalocationReports=new ArrayList<DataLocationReport>()
		def reultList=getCurrentDataLocationReportsByIndicatorCategory(ExecutorProvider.MANAGEMENT_OF_SPARE_PARTS)
		if(reultList!=null)
			mamageSparepartsDatalocationReports.addAll(reultList)

		return mamageSparepartsDatalocationReports
	}
	public def getManageMemmsUseReports(){
		List<DataLocationReport> mamageMemmuseDatalocationReports=new ArrayList<DataLocationReport>()
		def reultList=getCurrentDataLocationReportsByIndicatorCategory(ExecutorProvider.MANAGEMENT_MEMMS_USERS)
		if(reultList!=null)
			mamageMemmuseDatalocationReports.addAll(reultList)

		return mamageMemmuseDatalocationReports
	}

	//	public def getDataLocationReportFromIndicatorValueBayIndicatorCategory(String indicatorCategoryCode){
	//		Set<DataLocationReport> dataLocationReports=new HashSet<DataLocationReport>()
	//		def indicatorValues=indicatorValueService.getIndicatorValueByIndIcatorCategory(indicatorCategoryCode)
	//
	//		for(IndicatorValue indicatorValue:indicatorValues){
	//			if(indicatorValue!=null)
	//				dataLocationReports.add(indicatorValue.dataLocationReport)
	//		}
	//
	//		return dataLocationReports.toList()
	//	}

	public def getCurrentDataLocationReportsByIndicatorCategory(String indicatorCategoryCode){
		IndicatorCategory category=indicatorCategoryService.getIndicatorCategoryByCode(indicatorCategoryCode)
		MemmsReport memsReport=getCurrentMemmsReport()
		if(category!=null &&memsReport!=null)
			return dataLocationReportService.getCurrentDataLocationReportsByIndicatorCategoryAndMemmsReport(category, memsReport)
		else
			return null
	}

	public def getCurrentMemmsReport(){


		//		def criteria = MemmsReport.createCriteria()
		//		return criteria.list{
		//			firstResult(1)
		//			order("id","desc")
		//
		//
		//		}


		return MemmsReport.findById(Long.parseLong(""+1))




	}

	public def getMemmsReporByCode(String code){
		return MemmsReport.findByCode(code)
	}
}
