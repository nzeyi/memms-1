package org.chai.memms.report

import org.springframework.dao.DataIntegrityViolationException

class QuaterlyFacilityReportController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [quaterlyFacilityReportInstanceList: QuaterlyFacilityReport.list(params), quaterlyFacilityReportInstanceTotal: QuaterlyFacilityReport.count()]
    }

    def create() {
        [quaterlyFacilityReportInstance: new QuaterlyFacilityReport(params)]
    }

    def save() {
        def quaterlyFacilityReportInstance = new QuaterlyFacilityReport(params)
        if (!quaterlyFacilityReportInstance.save(flush: true)) {
            render(view: "create", model: [quaterlyFacilityReportInstance: quaterlyFacilityReportInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'quaterlyFacilityReport.label', default: 'QuaterlyFacilityReport'), quaterlyFacilityReportInstance.id])
        redirect(action: "show", id: quaterlyFacilityReportInstance.id)
    }

    def show(Long id) {
        def quaterlyFacilityReportInstance = QuaterlyFacilityReport.get(id)
        if (!quaterlyFacilityReportInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'quaterlyFacilityReport.label', default: 'QuaterlyFacilityReport'), id])
            redirect(action: "list")
            return
        }

        [quaterlyFacilityReportInstance: quaterlyFacilityReportInstance]
    }

    def edit(Long id) {
        def quaterlyFacilityReportInstance = QuaterlyFacilityReport.get(id)
        if (!quaterlyFacilityReportInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'quaterlyFacilityReport.label', default: 'QuaterlyFacilityReport'), id])
            redirect(action: "list")
            return
        }

        [quaterlyFacilityReportInstance: quaterlyFacilityReportInstance]
    }

    def update(Long id, Long version) {
        def quaterlyFacilityReportInstance = QuaterlyFacilityReport.get(id)
        if (!quaterlyFacilityReportInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'quaterlyFacilityReport.label', default: 'QuaterlyFacilityReport'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (quaterlyFacilityReportInstance.version > version) {
                quaterlyFacilityReportInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'quaterlyFacilityReport.label', default: 'QuaterlyFacilityReport')] as Object[],
                          "Another user has updated this QuaterlyFacilityReport while you were editing")
                render(view: "edit", model: [quaterlyFacilityReportInstance: quaterlyFacilityReportInstance])
                return
            }
        }

        quaterlyFacilityReportInstance.properties = params

        if (!quaterlyFacilityReportInstance.save(flush: true)) {
            render(view: "edit", model: [quaterlyFacilityReportInstance: quaterlyFacilityReportInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'quaterlyFacilityReport.label', default: 'QuaterlyFacilityReport'), quaterlyFacilityReportInstance.id])
        redirect(action: "show", id: quaterlyFacilityReportInstance.id)
    }

    def delete(Long id) {
        def quaterlyFacilityReportInstance = QuaterlyFacilityReport.get(id)
        if (!quaterlyFacilityReportInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'quaterlyFacilityReport.label', default: 'QuaterlyFacilityReport'), id])
            redirect(action: "list")
            return
        }

        try {
            quaterlyFacilityReportInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'quaterlyFacilityReport.label', default: 'QuaterlyFacilityReport'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'quaterlyFacilityReport.label', default: 'QuaterlyFacilityReport'), id])
            redirect(action: "show", id: id)
        }
    }
}
