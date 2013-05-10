package org.chai.memms.report

import org.springframework.dao.DataIntegrityViolationException

class IntermediateVariableController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [intermediateVariableInstanceList: IntermediateVariable.list(params), intermediateVariableInstanceTotal: IntermediateVariable.count()]
    }

    def create() {
        [intermediateVariableInstance: new IntermediateVariable(params)]
    }

    def save() {
        def intermediateVariableInstance = new IntermediateVariable(params)
        if (!intermediateVariableInstance.save(flush: true)) {
            render(view: "create", model: [intermediateVariableInstance: intermediateVariableInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'intermediateVariable.label', default: 'IntermediateVariable'), intermediateVariableInstance.id])
        redirect(action: "show", id: intermediateVariableInstance.id)
    }

    def show(Long id) {
        def intermediateVariableInstance = IntermediateVariable.get(id)
        if (!intermediateVariableInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'intermediateVariable.label', default: 'IntermediateVariable'), id])
            redirect(action: "list")
            return
        }

        [intermediateVariableInstance: intermediateVariableInstance]
    }

    def edit(Long id) {
        def intermediateVariableInstance = IntermediateVariable.get(id)
        if (!intermediateVariableInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'intermediateVariable.label', default: 'IntermediateVariable'), id])
            redirect(action: "list")
            return
        }

        [intermediateVariableInstance: intermediateVariableInstance]
    }

    def update(Long id, Long version) {
        def intermediateVariableInstance = IntermediateVariable.get(id)
        if (!intermediateVariableInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'intermediateVariable.label', default: 'IntermediateVariable'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (intermediateVariableInstance.version > version) {
                intermediateVariableInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'intermediateVariable.label', default: 'IntermediateVariable')] as Object[],
                          "Another user has updated this IntermediateVariable while you were editing")
                render(view: "edit", model: [intermediateVariableInstance: intermediateVariableInstance])
                return
            }
        }

        intermediateVariableInstance.properties = params

        if (!intermediateVariableInstance.save(flush: true)) {
            render(view: "edit", model: [intermediateVariableInstance: intermediateVariableInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'intermediateVariable.label', default: 'IntermediateVariable'), intermediateVariableInstance.id])
        redirect(action: "show", id: intermediateVariableInstance.id)
    }

    def delete(Long id) {
        def intermediateVariableInstance = IntermediateVariable.get(id)
        if (!intermediateVariableInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'intermediateVariable.label', default: 'IntermediateVariable'), id])
            redirect(action: "list")
            return
        }

        try {
            intermediateVariableInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'intermediateVariable.label', default: 'IntermediateVariable'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'intermediateVariable.label', default: 'IntermediateVariable'), id])
            redirect(action: "show", id: id)
        }
    }
}
