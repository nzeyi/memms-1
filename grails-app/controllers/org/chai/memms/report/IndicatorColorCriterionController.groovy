package org.chai.memms.report
import java.util.Set
import java.util.Map

import org.apache.commons.lang.math.NumberUtils
import org.chai.memms.AbstractEntityController;
import org.springframework.dao.DataIntegrityViolationException

class IndicatorColorCriterionController extends AbstractEntityController{
	def indicatorColorCriterionService

	def getLabel() {
		return "inndicator.color.label"
	}

	def getEntityClass() {
		return IndicatorColorCriterion.class
	}


	def getEntity(def id) {
		return IndicatorColorCriterion.get(id)
	}

	def createEntity() {
		return new IndicatorColorCriterion()
	}

	def getTemplate() {
		return "/entity/reports/dashboard/indicatorColorCriterion/createIndicatorColorCriteria"
	}

	def bindParams(def entity) {
		entity.properties = params
	}

	def getModel(def entity) {
		[
			indicatorColorCriterion: entity
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
		def listHtml = g.render(template:"/entity/reports/dashboard/indicatorColorCriterion/indicatorColorCriteriaList",model:model)
		render(contentType:"text/json") { results = [listHtml]}
	}
	def search = {
		adaptParamsForList()
		def indicatorColorCriterias  = indicatorColorCriterionService.searchIndicatorColorCriteriaPerDataLocationType(params['q'],null,params)

		if(request.xhr)
			this.ajaxModel(indicatorColorCriterias,params['q'])
		else {
			render(view:"/entity/list",model:model(indicatorColorCriterias) << [
				template:"/reports/dashboard/indicatorColorCriterion/indicatorColorCriteriaList",
				listTop:"/reports/dashboard/indicatorColorCriterion/listTop",

			])
		}
	}

	def list = {
		
		println"helooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo"
		adaptParamsForList()
		
		def indicatorColorCriteria = IndicatorColorCriterion.list(offset:params.offset,max:params.max,sort:params.sort ?:"id",order: params.order ?:"desc")
		if(request.xhr){
			this.ajaxModel(indicatorColorCriteria,"")
		}
		else{
			render(view:"/entity/list",model:model(indicatorColorCriteria) << [
				template:"/reports/dashboard/indicatorColorCriterion/indicatorColorCriteriaList",
				listTop:"/reports/dashboard/indicatorColorCriterion/listTop"
			])
		}
	}
}
