package org.chai.memms.report

import java.util.List;
import org.chai.location.DataLocationType;
import org.chai.location.DataLocation;
import org.chai.memms.AbstractEntityController;

import java.util.Set
import java.util.Map

import org.apache.commons.lang.math.NumberUtils
import org.springframework.dao.DataIntegrityViolationException

class DataLocationReportController extends AbstractEntityController{
	def dataLocationReportService

	def getLabel() {
		return "dataLocationReport.label"
	}

	def getEntityClass() {
		return DataLocationReport.class
	}


	def getEntity(def id) {
		return DataLocationReport.get(id)
	}

	def createEntity() {
		return new DataLocationReport()
	}

	def getTemplate() {
		return "/entity/reports/dashboard/dataLocationReport/createDataLocationReport"
	}

	def bindParams(def entity) {
		entity.properties = params
	}

	def getModel(def entity) {
		[
			dataLocationReport: entity
		]
	}

	def model(def entities) {
		return [
			entities: entities,
			entityCount: entities.totalCount,
			entityClass:getEntityClass(),
			code: getLabel()
		]
	}
	def ajaxModel(def entities,def searchTerm) {
		def model = model(entities) << [q:searchTerm]
		def listHtml = g.render(template:"/entity/reports/dashboard/dataLocationReport/dataLocationReportList",model:model)
		render(contentType:"text/json") { results = [listHtml]}
	}
	def search = {
		adaptParamsForList()
		def dataLocationReports  = dataLocationReportService.searchDataLocationReport(params['q'],null,params)

		if(request.xhr)
			this.ajaxModel(dataLocationReports,params['q'])
		else {
			render(view:"/entity/list",model:model(dataLocationReports) << [
				template:"/reports/dashboard/dataLocationReport/dataLocationReportList",
				listTop:"/reports/dashboard/dataLocationReport/listTop",

			])
		}
	}

	def list = {
		
		adaptParamsForList()
		
		def dataLocationReports = DataLocationReport.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc")
		if(request.xhr){
			this.ajaxModel(dataLocationReports,"")
		}
		else{
			render(view:"/entity/list",model:model(dataLocationReports) << [
				template:"/reports/dashboard/dataLocationReport/dataLocationReportList",
				listTop:"/reports/dashboard/dataLocationReport/listTop"
			])
		}
	}
}
