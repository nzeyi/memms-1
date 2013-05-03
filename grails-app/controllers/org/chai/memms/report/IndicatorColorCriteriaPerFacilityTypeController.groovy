package org.chai.memms.report

import org.springframework.dao.DataIntegrityViolationException

class IndicatorColorCriteriaPerFacilityTypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
/*
    def index() {
        redirect(action: "list", params: params)
    }
*/
	def scaffold = true
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [indicatorColorCriteriaPerFacilityTypeInstanceList: IndicatorColorCriteriaPerFacilityType.list(params), indicatorColorCriteriaPerFacilityTypeInstanceTotal: IndicatorColorCriteriaPerFacilityType.count()]
    }

    def create() {
        [indicatorColorCriteriaPerFacilityTypeInstance: new IndicatorColorCriteriaPerFacilityType(params)]
    }

    def save() {
        def indicatorColorCriteriaPerFacilityTypeInstance = new IndicatorColorCriteriaPerFacilityType(params)
        if (!indicatorColorCriteriaPerFacilityTypeInstance.save(flush: true)) {
            render(view: "create", model: [indicatorColorCriteriaPerFacilityTypeInstance: indicatorColorCriteriaPerFacilityTypeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'indicatorColorCriteriaPerFacilityType.label', default: 'IndicatorColorCriteriaPerFacilityType'), indicatorColorCriteriaPerFacilityTypeInstance.id])
        redirect(action: "show", id: indicatorColorCriteriaPerFacilityTypeInstance.id)
    }

    def show(Long id) {
        def indicatorColorCriteriaPerFacilityTypeInstance = IndicatorColorCriteriaPerFacilityType.get(id)
        if (!indicatorColorCriteriaPerFacilityTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicatorColorCriteriaPerFacilityType.label', default: 'IndicatorColorCriteriaPerFacilityType'), id])
            redirect(action: "list")
            return
        }

        [indicatorColorCriteriaPerFacilityTypeInstance: indicatorColorCriteriaPerFacilityTypeInstance]
    }

    def edit(Long id) {
        def indicatorColorCriteriaPerFacilityTypeInstance = IndicatorColorCriteriaPerFacilityType.get(id)
        if (!indicatorColorCriteriaPerFacilityTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicatorColorCriteriaPerFacilityType.label', default: 'IndicatorColorCriteriaPerFacilityType'), id])
            redirect(action: "list")
            return
        }

        [indicatorColorCriteriaPerFacilityTypeInstance: indicatorColorCriteriaPerFacilityTypeInstance]
    }

    def update(Long id, Long version) {
        def indicatorColorCriteriaPerFacilityTypeInstance = IndicatorColorCriteriaPerFacilityType.get(id)
        if (!indicatorColorCriteriaPerFacilityTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicatorColorCriteriaPerFacilityType.label', default: 'IndicatorColorCriteriaPerFacilityType'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (indicatorColorCriteriaPerFacilityTypeInstance.version > version) {
                indicatorColorCriteriaPerFacilityTypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'indicatorColorCriteriaPerFacilityType.label', default: 'IndicatorColorCriteriaPerFacilityType')] as Object[],
                          "Another user has updated this IndicatorColorCriteriaPerFacilityType while you were editing")
                render(view: "edit", model: [indicatorColorCriteriaPerFacilityTypeInstance: indicatorColorCriteriaPerFacilityTypeInstance])
                return
            }
        }

        indicatorColorCriteriaPerFacilityTypeInstance.properties = params

        if (!indicatorColorCriteriaPerFacilityTypeInstance.save(flush: true)) {
            render(view: "edit", model: [indicatorColorCriteriaPerFacilityTypeInstance: indicatorColorCriteriaPerFacilityTypeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'indicatorColorCriteriaPerFacilityType.label', default: 'IndicatorColorCriteriaPerFacilityType'), indicatorColorCriteriaPerFacilityTypeInstance.id])
        redirect(action: "show", id: indicatorColorCriteriaPerFacilityTypeInstance.id)
    }

    def delete(Long id) {
        def indicatorColorCriteriaPerFacilityTypeInstance = IndicatorColorCriteriaPerFacilityType.get(id)
        if (!indicatorColorCriteriaPerFacilityTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicatorColorCriteriaPerFacilityType.label', default: 'IndicatorColorCriteriaPerFacilityType'), id])
            redirect(action: "list")
            return
        }

        try {
            indicatorColorCriteriaPerFacilityTypeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'indicatorColorCriteriaPerFacilityType.label', default: 'IndicatorColorCriteriaPerFacilityType'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'indicatorColorCriteriaPerFacilityType.label', default: 'IndicatorColorCriteriaPerFacilityType'), id])
            redirect(action: "show", id: id)
        }
    }
}
