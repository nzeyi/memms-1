package org.chai.memms.report

import org.springframework.dao.DataIntegrityViolationException

class IndicatorColorCriteriaPerDataLocationTypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [indicatorColorCriteriaPerDataLocationTypeInstanceList: IndicatorColorCriteriaPerDataLocationType.list(params), indicatorColorCriteriaPerDataLocationTypeInstanceTotal: IndicatorColorCriteriaPerDataLocationType.count()]
    }

    def create() {
        [indicatorColorCriteriaPerDataLocationTypeInstance: new IndicatorColorCriteriaPerDataLocationType(params)]
    }

    def save() {
        def indicatorColorCriteriaPerDataLocationTypeInstance = new IndicatorColorCriteriaPerDataLocationType(params)
        if (!indicatorColorCriteriaPerDataLocationTypeInstance.save(flush: true)) {
            render(view: "create", model: [indicatorColorCriteriaPerDataLocationTypeInstance: indicatorColorCriteriaPerDataLocationTypeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'indicatorColorCriteriaPerDataLocationType.label', default: 'IndicatorColorCriteriaPerDataLocationType'), indicatorColorCriteriaPerDataLocationTypeInstance.id])
        redirect(action: "show", id: indicatorColorCriteriaPerDataLocationTypeInstance.id)
    }

    def show(Long id) {
        def indicatorColorCriteriaPerDataLocationTypeInstance = IndicatorColorCriteriaPerDataLocationType.get(id)
        if (!indicatorColorCriteriaPerDataLocationTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicatorColorCriteriaPerDataLocationType.label', default: 'IndicatorColorCriteriaPerDataLocationType'), id])
            redirect(action: "list")
            return
        }

        [indicatorColorCriteriaPerDataLocationTypeInstance: indicatorColorCriteriaPerDataLocationTypeInstance]
    }

    def edit(Long id) {
        def indicatorColorCriteriaPerDataLocationTypeInstance = IndicatorColorCriteriaPerDataLocationType.get(id)
        if (!indicatorColorCriteriaPerDataLocationTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicatorColorCriteriaPerDataLocationType.label', default: 'IndicatorColorCriteriaPerDataLocationType'), id])
            redirect(action: "list")
            return
        }

        [indicatorColorCriteriaPerDataLocationTypeInstance: indicatorColorCriteriaPerDataLocationTypeInstance]
    }

    def update(Long id, Long version) {
        def indicatorColorCriteriaPerDataLocationTypeInstance = IndicatorColorCriteriaPerDataLocationType.get(id)
        if (!indicatorColorCriteriaPerDataLocationTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicatorColorCriteriaPerDataLocationType.label', default: 'IndicatorColorCriteriaPerDataLocationType'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (indicatorColorCriteriaPerDataLocationTypeInstance.version > version) {
                indicatorColorCriteriaPerDataLocationTypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'indicatorColorCriteriaPerDataLocationType.label', default: 'IndicatorColorCriteriaPerDataLocationType')] as Object[],
                          "Another user has updated this IndicatorColorCriteriaPerDataLocationType while you were editing")
                render(view: "edit", model: [indicatorColorCriteriaPerDataLocationTypeInstance: indicatorColorCriteriaPerDataLocationTypeInstance])
                return
            }
        }

        indicatorColorCriteriaPerDataLocationTypeInstance.properties = params

        if (!indicatorColorCriteriaPerDataLocationTypeInstance.save(flush: true)) {
            render(view: "edit", model: [indicatorColorCriteriaPerDataLocationTypeInstance: indicatorColorCriteriaPerDataLocationTypeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'indicatorColorCriteriaPerDataLocationType.label', default: 'IndicatorColorCriteriaPerDataLocationType'), indicatorColorCriteriaPerDataLocationTypeInstance.id])
        redirect(action: "show", id: indicatorColorCriteriaPerDataLocationTypeInstance.id)
    }

    def delete(Long id) {
        def indicatorColorCriteriaPerDataLocationTypeInstance = IndicatorColorCriteriaPerDataLocationType.get(id)
        if (!indicatorColorCriteriaPerDataLocationTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicatorColorCriteriaPerDataLocationType.label', default: 'IndicatorColorCriteriaPerDataLocationType'), id])
            redirect(action: "list")
            return
        }

        try {
            indicatorColorCriteriaPerDataLocationTypeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'indicatorColorCriteriaPerDataLocationType.label', default: 'IndicatorColorCriteriaPerDataLocationType'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'indicatorColorCriteriaPerDataLocationType.label', default: 'IndicatorColorCriteriaPerDataLocationType'), id])
            redirect(action: "show", id: id)
        }
    }
}
