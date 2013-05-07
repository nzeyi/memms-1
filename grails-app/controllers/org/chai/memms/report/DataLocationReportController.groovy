package org.chai.memms.report

import java.util.List;


import org.springframework.dao.DataIntegrityViolationException

class DataLocationReportController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	IndicatorTypeService indicatorTypeService
	/*
	 def index() {
	 redirect(action: "list", params: params)
	 }*/
	def scaffold = true

	def list(Integer max) {
		println"testin it==================================== list of indicators :"
		List<IntermediateVariable> intermediateVariable=null
		Double indicatorvalue=indicatorTypeService.indicatorValueCalculator(intermediateVariable,'[{excutableScript:"from EquipmentStatus where Status=\'INSTOCK\'",domainclass:"EquipmentStatus",useCountFunction:"true",followOperand:"add",isDenominator:"false",isIntermidiateVariable:"false",isDynamicFinder:"false"},{excutableScript:"from EquipmentStatus where Status=\'OPERATIONAL\'",domainclass="EquipmentStatus",useCountFunction:"true",followOperand:"add",isDenominator:"false",isIntermidiateVariable:"false",isDynamicFinder:"false"},{excutableScript:"from EquipmentStatus where Status=\'INSTOCK\' or Status=\'OPERATIONAL\' or Status=\'UNDERMAINTENANCE\'",domainclass:"EquipmentStatus",useCountFunction:"true",followOperand:"add",isDenominator:"true",isIntermidiateVariable:"false",isDynamicFinder:"false"}]');
		println"==================================== finished  the indicator value is :"+indicatorvalue
		params.max = Math.min(max ?: 10, 100)
		[dataLocationReportInstanceList: DataLocationReport.list(params), dataLocationReportInstanceTotal: DataLocationReport.count()]
	}

	def create() {
		//List<Object> indicators=indicatorTypeService.getIndicatorTypes("select category_type_id from memms_report_indicator_type");
		//println"==================================== list of indicators :"+indicators
		//println"Total number of indicators is ok:"+indicators.size();
		//int thenumber=indicatorTypeService.getIndicatorTypeValue("select count(category_type_id) from memms_indicator_type group by category_type_id")
		//println"The returned number is :"+thenumber;
		[dataLocationReportInstance: new DataLocationReport(params)]
	}

	def save() {
		def dataLocationReportInstance = new DataLocationReport(params)
		if (!dataLocationReportInstance.save(flush: true)) {
			render(view: "create", model: [dataLocationReportInstance: dataLocationReportInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'dataLocationReport.label', default: 'DataLocationReport'),
			dataLocationReportInstance.code
		])


		indicatorTypeService.getIndicatorTypes()
		redirect(action: "list", id: dataLocationReportInstance.id, model: [dataLocationReportInstance: dataLocationReportInstance])
	}

	def show(Long id) {
		def dataLocationReportInstance = DataLocationReport.get(id)
		if (!dataLocationReportInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'dataLocationReport.label', default: 'DataLocationReport'),
				id
			])
			redirect(action: "list")
			return
		}

		[dataLocationReportInstance: dataLocationReportInstance]
	}

	def edit(Long id) {
		def dataLocationReportInstance = DataLocationReport.get(id)
		if (!dataLocationReportInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'dataLocationReport.label', default: 'DataLocationReport'),
				id
			])
			redirect(action: "list")
			return
		}

		[dataLocationReportInstance: dataLocationReportInstance]
	}

	def update(Long id, Long version) {
		def dataLocationReportInstance = DataLocationReport.get(id)
		if (!dataLocationReportInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'dataLocationReport.label', default: 'DataLocationReport'),
				id
			])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (dataLocationReportInstance.version > version) {
				dataLocationReportInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'dataLocationReport.label', default: 'DataLocationReport')] as Object[],
						"Another user has updated this DataLocationReport while you were editing")
				render(view: "edit", model: [dataLocationReportInstance: dataLocationReportInstance])
				return
			}
		}

		dataLocationReportInstance.properties = params

		if (!dataLocationReportInstance.save(flush: true)) {
			render(view: "edit", model: [dataLocationReportInstance: dataLocationReportInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [
			message(code: 'dataLocationReport.label', default: 'DataLocationReport'),
			dataLocationReportInstance.id
		])
		redirect(action: "list", id: dataLocationReportInstance.id)
	}

	def delete(Long id) {
		def dataLocationReportInstance = DataLocationReport.get(id)
		if (!dataLocationReportInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'dataLocationReport.label', default: 'DataLocationReport'),
				id
			])
			redirect(action: "list")
			return
		}

		try {
			dataLocationReportInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'dataLocationReport.label', default: 'DataLocationReport'),
				id
			])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'dataLocationReport.label', default: 'DataLocationReport'),
				id
			])
			redirect(action: "list", id: id)
		}
	}
}
