package org.chai.memms.report

import org.chai.location.DataLocationType;
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
	def indicatorCategoryService

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
		return "/entity/reports/dashboard/dataLocationReport/dashboard"
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
		def listHtml = g.render(template:"/entity/reports/dashboard/dataLocationReport/dashboard",model:model)
		render(contentType:"text/json") { results = [listHtml]}
	}






	def dashboard ={

		
		DataLocationType thisType=null
		def indicatorCategories=indicatorCategoryService.getIndicatorCategories()
		if(params['q']!=null&&params['q']!="")
			thisType=DataLocationType.findByCode(params['q'])

		//adaptParamsForList()
		MemmsReport memmsReportFilliteled=new MemmsReport()

		MemmsReport memmsReportDefault=new MemmsReport()

		List<DataLocationReport> filiterdReports=new ArrayList<DataLocationReport>()

		memmsReportDefault.correctiveMaintenanceReports.addAll(memmsReportService.getCorrectiveMaintenanceReports())
		memmsReportDefault.privantiveMaintenanceReports.addAll(memmsReportService.getPreventiveMaintenanceReports())
		memmsReportDefault.manageMemmEquipmentRreports.addAll(memmsReportService.getManageMemmsEquipmentReports())
		memmsReportDefault.manageMemmsUseReports.addAll(memmsReportService.getMemmsParePartReports())
		memmsReportDefault.manageSparePartsReports.addAll(memmsReportService.getManageMemmsUseReports())
		memmsReportDefault.categories.addAll(indicatorCategoryService.getIndicatorCategories())
		if(thisType!=null){

			for(DataLocationReport report:memmsReportDefault.correctiveMaintenanceReports){
				if(report.dataLocationType!=null){

					if(report.dataLocationType==thisType){
						filiterdReports.add(report)
					}
				}
			}
			memmsReportFilliteled.categories.addAll(indicatorCategories)
			memmsReportFilliteled.correctiveMaintenanceReports.addAll(filiterdReports)
		}else
			memmsReportFilliteled=memmsReportDefault

		
		if(request.xhr){
			this.ajaxModel(memmsReportFilliteled,"")
		}
		else{
			render(view:"/entity/list",model:model(memmsReportFilliteled) << [
				template:"/reports/dashboard/dataLocationReport/dashboard",
				listTop:"/reports/dashboard/dataLocationReport/listTop"
			])
		}

	}




}
