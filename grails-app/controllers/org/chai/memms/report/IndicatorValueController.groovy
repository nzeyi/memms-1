package org.chai.memms.report

import org.springframework.dao.DataIntegrityViolationException

class IndicatorValueController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [indicatorValueInstanceList: IndicatorValue.list(params), indicatorValueInstanceTotal: IndicatorValue.count()]
    }

    def create() {
        [indicatorValueInstance: new IndicatorValue(params)]
    }

    def save() {
        def indicatorValueInstance = new IndicatorValue(params)
        if (!indicatorValueInstance.save(flush: true)) {
            render(view: "create", model: [indicatorValueInstance: indicatorValueInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'indicatorValue.label', default: 'IndicatorValue'), indicatorValueInstance.id])
        redirect(action: "show", id: indicatorValueInstance.id)
    }

    def show(Long id) {
        def indicatorValueInstance = IndicatorValue.get(id)
        if (!indicatorValueInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicatorValue.label', default: 'IndicatorValue'), id])
            redirect(action: "list")
            return
        }

        [indicatorValueInstance: indicatorValueInstance]
    }

    def edit(Long id) {
        def indicatorValueInstance = IndicatorValue.get(id)
        if (!indicatorValueInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicatorValue.label', default: 'IndicatorValue'), id])
            redirect(action: "list")
            return
        }

        [indicatorValueInstance: indicatorValueInstance]
    }

    def update(Long id, Long version) {
        def indicatorValueInstance = IndicatorValue.get(id)
        if (!indicatorValueInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicatorValue.label', default: 'IndicatorValue'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (indicatorValueInstance.version > version) {
                indicatorValueInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'indicatorValue.label', default: 'IndicatorValue')] as Object[],
                          "Another user has updated this IndicatorValue while you were editing")
                render(view: "edit", model: [indicatorValueInstance: indicatorValueInstance])
                return
            }
        }

        indicatorValueInstance.properties = params

        if (!indicatorValueInstance.save(flush: true)) {
            render(view: "edit", model: [indicatorValueInstance: indicatorValueInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'indicatorValue.label', default: 'IndicatorValue'), indicatorValueInstance.id])
        redirect(action: "show", id: indicatorValueInstance.id)
    }

    def delete(Long id) {
        def indicatorValueInstance = IndicatorValue.get(id)
        if (!indicatorValueInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicatorValue.label', default: 'IndicatorValue'), id])
            redirect(action: "list")
            return
        }

        try {
            indicatorValueInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'indicatorValue.label', default: 'IndicatorValue'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'indicatorValue.label', default: 'IndicatorValue'), id])
            redirect(action: "show", id: id)
        }
    }
}
