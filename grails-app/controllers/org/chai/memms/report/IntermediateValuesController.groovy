package org.chai.memms.report

import org.springframework.dao.DataIntegrityViolationException

class IntermediateValuesController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [intermediateValuesInstanceList: IntermediateValues.list(params), intermediateValuesInstanceTotal: IntermediateValues.count()]
    }

    def create() {
        [intermediateValuesInstance: new IntermediateValues(params)]
    }

    def save() {
        def intermediateValuesInstance = new IntermediateValues(params)
        if (!intermediateValuesInstance.save(flush: true)) {
            render(view: "create", model: [intermediateValuesInstance: intermediateValuesInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'intermediateValues.label', default: 'IntermediateValues'), intermediateValuesInstance.id])
        redirect(action: "show", id: intermediateValuesInstance.id)
    }

    def show(Long id) {
        def intermediateValuesInstance = IntermediateValues.get(id)
        if (!intermediateValuesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'intermediateValues.label', default: 'IntermediateValues'), id])
            redirect(action: "list")
            return
        }

        [intermediateValuesInstance: intermediateValuesInstance]
    }

    def edit(Long id) {
        def intermediateValuesInstance = IntermediateValues.get(id)
        if (!intermediateValuesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'intermediateValues.label', default: 'IntermediateValues'), id])
            redirect(action: "list")
            return
        }

        [intermediateValuesInstance: intermediateValuesInstance]
    }

    def update(Long id, Long version) {
        def intermediateValuesInstance = IntermediateValues.get(id)
        if (!intermediateValuesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'intermediateValues.label', default: 'IntermediateValues'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (intermediateValuesInstance.version > version) {
                intermediateValuesInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'intermediateValues.label', default: 'IntermediateValues')] as Object[],
                          "Another user has updated this IntermediateValues while you were editing")
                render(view: "edit", model: [intermediateValuesInstance: intermediateValuesInstance])
                return
            }
        }

        intermediateValuesInstance.properties = params

        if (!intermediateValuesInstance.save(flush: true)) {
            render(view: "edit", model: [intermediateValuesInstance: intermediateValuesInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'intermediateValues.label', default: 'IntermediateValues'), intermediateValuesInstance.id])
        redirect(action: "show", id: intermediateValuesInstance.id)
    }

    def delete(Long id) {
        def intermediateValuesInstance = IntermediateValues.get(id)
        if (!intermediateValuesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'intermediateValues.label', default: 'IntermediateValues'), id])
            redirect(action: "list")
            return
        }

        try {
            intermediateValuesInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'intermediateValues.label', default: 'IntermediateValues'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'intermediateValues.label', default: 'IntermediateValues'), id])
            redirect(action: "show", id: id)
        }
    }
}
