package org.chai.memms.report

import java.util.List;
import org.chai.location.DataLocationType;
import org.chai.location.DataLocation;

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
		//println"indicators exml parser ok :"
		//println"start"
		indicatorTypeService.reportingEngine()
		//println"end resding"
		//println"testin it==================================== list of indicators :"
		//int i=0
		//for(DataLocation location:indicatorTypeService.getDataLications()){
		//List<IntermediateVariable> intermediateVariables=null
		//DataLocation location=DataLocation.findById(16)
		//Double indicatorvalue=indicatorTypeService.indicatorValueCalculator(intermediateVariables,'[{excutableScript:"select equ.code from Equipment as equ inner join equ.status as equipmentStatus where equipmentStatus.status=\'INSTOCK\' and equ.dataLocation=\'locationidentifier\'",classDomaine:"EquipmentStatus",useCountFunction:"true",followOperand:"add",isDenominator:"false",isIntermidiateVariable:"false",isDynamicFinder:"false"},{excutableScript:"select equ.code from Equipment as equ inner join equ.status as equipmentStatus where equipmentStatus.status=\'OPERATIONAL\' and equ.dataLocation=\'locationidentifier\'",classDomaine:"EquipmentStatus",useCountFunction:"true",followOperand:"add",isDenominator:"false",isIntermidiateVariable:"false",isDynamicFinder:"false"},{excutableScript:"select equ.code from Equipment as equ inner join equ.status as equipmentStatus where equipmentStatus.status=\'INSTOCK\' or equipmentStatus.status=\'OPERATIONAL\' or equipmentStatus.status=\'UNDERMAINTENANCE\' and equ.dataLocation=\'locationidentifier\'",classDomaine:"EquipmentStatus",useCountFunction:"true",followOperand:"add",isDenominator:"true",isIntermidiateVariable:"false",isDynamicFinder:"false"}]');
		//indicatorTypeService.indicatorValueCalculator(intermediateVariables,location);

		//println"===========started ok finished  the indicator value is ok :"
		//indicatorTypeService.getIndicatorFromfile()

	//	println"==================================== finished  the indicator value is ok :"
		//println"Thecation at "+i+" is  :"+location
		//i++
		//}
		params.max = Math.min(max ?: 10, 100)
		[dataLocationReportInstanceList: DataLocationReport.list(params), dataLocationReportInstanceTotal: DataLocationReport.count()]
	}

	def create() {
		
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
