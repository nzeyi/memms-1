package org.chai.memms.report

import org.springframework.dao.DataIntegrityViolationException

class IndicatorController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [indicatorInstanceList: Indicator.list(params), indicatorInstanceTotal: Indicator.count()]
    }

    def create() {
        [indicatorInstance: new Indicator(params)]
    }

    def save() {
        def indicatorInstance = new Indicator(params)
        if (!indicatorInstance.save(flush: true)) {
            render(view: "create", model: [indicatorInstance: indicatorInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'indicator.label', default: 'Indicator'), indicatorInstance.id])
        redirect(action: "show", id: indicatorInstance.id)
    }

    def show(Long id) {
        def indicatorInstance = Indicator.get(id)
        if (!indicatorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicator.label', default: 'Indicator'), id])
            redirect(action: "list")
            return
        }

        [indicatorInstance: indicatorInstance]
    }

    def edit(Long id) {
        def indicatorInstance = Indicator.get(id)
        if (!indicatorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicator.label', default: 'Indicator'), id])
            redirect(action: "list")
            return
        }

        [indicatorInstance: indicatorInstance]
    }

    def update(Long id, Long version) {
        def indicatorInstance = Indicator.get(id)
        if (!indicatorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicator.label', default: 'Indicator'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (indicatorInstance.version > version) {
                indicatorInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'indicator.label', default: 'Indicator')] as Object[],
                          "Another user has updated this Indicator while you were editing")
                render(view: "edit", model: [indicatorInstance: indicatorInstance])
                return
            }
        }

        indicatorInstance.properties = params

        if (!indicatorInstance.save(flush: true)) {
            render(view: "edit", model: [indicatorInstance: indicatorInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'indicator.label', default: 'Indicator'), indicatorInstance.id])
        redirect(action: "show", id: indicatorInstance.id)
    }

    def delete(Long id) {
        def indicatorInstance = Indicator.get(id)
        if (!indicatorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicator.label', default: 'Indicator'), id])
            redirect(action: "list")
            return
        }

        try {
            indicatorInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'indicator.label', default: 'Indicator'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'indicator.label', default: 'Indicator'), id])
            redirect(action: "show", id: id)
        }
    }
}
