package org.chai.memms.report

import java.util.Set
import java.util.Map

import org.apache.commons.lang.math.NumberUtils

import org.chai.memms.AbstractEntityController;


class IndicatorController  extends AbstractEntityController{
	def indicatorService

	def getLabel() {
		return "indicator.label"
	}

	def getEntityClass() {
		return Indicator.class
	}


	def getEntity(def id) {
		return Indicator.get(id)
	}

	def createEntity() {
		return new Indicator()
	}

	def getTemplate() {
		return "/entity/reports/dashboard/indicator/createIndicator"
	}

	def bindParams(def entity) {
		entity.properties = params
	}

	def getModel(def entity) {
		[
			indicator: entity
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
		def listHtml = g.render(template:"/entity/reports/dashboard/indicator/indicatorList",model:model)
		render(contentType:"text/json") { results = [listHtml]}
	}
	def search = {
		adaptParamsForList()
		def indicators  = indicatorService.searchIndicator(params['q'],null,params)

		if(request.xhr)
			this.ajaxModel(indicators,params['q'])
		else {
			render(view:"/entity/list",model:model(indicators) << [
				template:"/reports/dashboard/indicator/indicatorList",
				listTop:"/reports/dashboard/indicator/listTop",

			])
		}
	}

	def list = {
		println"okkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"
		indicatorService.indicatorWriterFromXml()
		adaptParamsForList()
		
		def indicators = Indicator.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc")
		if(request.xhr){
			this.ajaxModel(indicators,"")
		}
		else{
			render(view:"/entity/list",model:model(indicators) << [
				template:"/reports/dashboard/indicator/indicatorList",
				listTop:"/reports/dashboard/indicator/listTop"
			])
		}
	}
}
