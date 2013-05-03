package org.chai.memms.report

import org.springframework.dao.DataIntegrityViolationException

class IndicatorTypeController {

	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	/*
	 def index() {
	 redirect(action: "list", params: params)
	 }
	 */

	def scaffold = true
	def list(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		[indicatorTypeInstanceList: IndicatorType.list(params), indicatorTypeInstanceTotal: IndicatorType.count()]
	}

	def create() {
		[indicatorTypeInstance: new IndicatorType(params)]
		
	}

	def save() {
		def indicatorTypeInstance = new IndicatorType(params)
		if (!indicatorTypeInstance.save(flush: true)) {
			render(view: "create", model: [indicatorTypeInstance: indicatorTypeInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'indicatorType.label', default: 'IndicatorType'),
			indicatorTypeInstance.id
		])
		redirect(action: "list", id: indicatorTypeInstance.id)
	}
	
	def saveContinue() {
		def indicatorTypeInstance = new IndicatorType(params)
		//indicatorTypeInstance.save()
		if (!indicatorTypeInstance.save(flush: true)) {
			render(view: "create", model: [indicatorTypeInstance: indicatorTypeInstance])
			return
		}

		flash.message = message(code: 'default.created.message', args: [
			message(code: 'indicatorType.label', default: 'IndicatorType'),
			indicatorTypeInstance.id
		])
		redirect(action: "create", id: indicatorTypeInstance.id)
	}

	def show(Long id) {
		def indicatorTypeInstance = IndicatorType.get(id)
		if (!indicatorTypeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'indicatorType.label', default: 'IndicatorType'),
				id
			])
			redirect(action: "list")
			return
		}

		[indicatorTypeInstance: indicatorTypeInstance]
	}

	def edit(Long id) {
		def indicatorTypeInstance = IndicatorType.get(id)
		if (!indicatorTypeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'indicatorType.label', default: 'IndicatorType'),
				id
			])
			redirect(action: "list")
			return
		}

		[indicatorTypeInstance: indicatorTypeInstance]
	}

	def update(Long id, Long version) {
		def indicatorTypeInstance = IndicatorType.get(id)
		if (!indicatorTypeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'indicatorType.label', default: 'IndicatorType'),
				id
			])
			redirect(action: "list")
			return
		}

		if (version != null) {
			if (indicatorTypeInstance.version > version) {
				indicatorTypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
						[
							message(code: 'indicatorType.label', default: 'IndicatorType')] as Object[],
						"Another user has updated this IndicatorType while you were editing")
				render(view: "edit", model: [indicatorTypeInstance: indicatorTypeInstance])
				return
			}
		}

		indicatorTypeInstance.properties = params

		if (!indicatorTypeInstance.save(flush: true)) {
			render(view: "edit", model: [indicatorTypeInstance: indicatorTypeInstance])
			return
		}

		flash.message = message(code: 'default.updated.message', args: [
			message(code: 'indicatorType.label', default: 'IndicatorType'),
			indicatorTypeInstance.id
		])
		redirect(action: "list", id: indicatorTypeInstance.id)
	}

	def delete(Long id) {
		def indicatorTypeInstance = IndicatorType.get(id)
		if (!indicatorTypeInstance) {
			flash.message = message(code: 'default.not.found.message', args: [
				message(code: 'indicatorType.label', default: 'IndicatorType'),
				id
			])
			redirect(action: "list")
			return
		}

		try {
			indicatorTypeInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [
				message(code: 'indicatorType.label', default: 'IndicatorType'),
				id
			])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [
				message(code: 'indicatorType.label', default: 'IndicatorType'),
				id
			])
			redirect(action: "list", id: id)
		}
	}
}
