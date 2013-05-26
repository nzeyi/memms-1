package org.chai.memms.report

import org.chai.memms.AbstractEntityController
import java.util.Set
import java.util.Map

import org.apache.commons.lang.math.NumberUtils
import org.springframework.dao.DataIntegrityViolationException
class MemmsReportController  extends AbstractEntityController {
	static transactional = true

	def sessionFactory
	def dataSource  //Aut
	def memmsReportService
	def indicatorValueService

	def getLabel() {
		return "memmsReport.label"
	}

	def getEntityClass() {
		return MemmsReport.class
	}


	def getEntity(def id) {
		return MemmsReport.get(id)
	}

	def createEntity() {
		return new MemmsReport()
	}

	def getTemplate() {
		return "/entity/reports/dashboard/dataLocationReport/createDataLocationReportt"
	}

	def bindParams(def entity) {
		entity.properties = params
	}

	def getModel(def entity) {
		[
			memmsReport: entity
		]
	}

	def model(def entities) {
		return [
			entities: entities,
			//entityCount: entities.totalCount,
			entityClass:getEntityClass(),
			code: getLabel()
		]
	}
	def ajaxModel(def entities,def searchTerm) {
		def model = model(entities) << [q:searchTerm]
		def listHtml = g.render(template:"/entity/reports/dashboard/dataLocationReport/dataLocationReportList",model:model)
		render(contentType:"text/json") { results = [listHtml]}
	}



	//	def search = {
	//		adaptParamsForList()
	//		def dataLocationReports  =AggregatedDataLocationReport.findAll()
	//
	//		if(request.xhr)
	//			this.ajaxModel(dataLocationReports,params['q'])
	//		else {
	//			render(view:"/entity/list",model:model(dataLocationReports) << [
	//				template:"/reports/dashboard/dataLocationReport/dataLocationReportList",
	//				listTop:"/reports/dashboard/dataLocationReport/listTop",
	//
	//			])
	//		}
	//	}
	//
	//	def list = {
	//
	//		adaptParamsForList()
	//
	//		def dataLocationReports = AggregatedDataLocationReport.findAll()
	//		if(request.xhr){
	//			this.ajaxModel(dataLocationReports,"")
	//		}
	//		else{
	//			render(view:"/entity/list",model:model(dataLocationReports) << [
	//				template:"/reports/dashboard/dataLocationReport/dataLocationReportList",
	//				listTop:"/reports/dashboard/dataLocationReport/listTop"
	//			])
	//		}
	//	}


	def dashboard ={


		adaptParamsForList()

		MemmsReport memmsReport=new MemmsReport()

		memmsReport.correctiveMaintenanceReports.addAll(memmsReportService.getCorrectiveMaintenanceReports())
		memmsReport.privantiveMaintenanceReports.addAll(memmsReportService.getPreventiveMaintenanceReports())
		memmsReport.manageMemmEquipmentRreports.addAll(memmsReportService.getManageMemmsEquipmentReports())
		memmsReport.manageMemmsUseReports.addAll(memmsReportService.getMemmsParePartReports())
		memmsReport.manageSparePartsReports.addAll(memmsReportService.getManageMemmsUseReports())


		println"beforer corre :"+memmsReportService.getCorrectiveMaintenanceReports().size()
		println"corre :"+memmsReport.correctiveMaintenanceReports.size()
		println"maintenance :"+memmsReport.privantiveMaintenanceReports.size()
		//List<AggregatedDataLocationReport> repors=new ArrayList<AggregatedDataLocationReport>()
		//def aggretions=AggregatedDataLocationReport.findAll ()
		//repors.add(memmsReport)
		//aggretions.addAll(memmsReportService.getCorrectiveMaintenanceReports())
		if(request.xhr){
			this.ajaxModel(memmsReport,"")
		}
		else{
			render(view:"/entity/list",model:model(memmsReport) << [
				template:"/reports/dashboard/dataLocationReport/dashboard",
				listTop:"/reports/dashboard/dataLocationReport/listTop"
			])
		}

	}




}
