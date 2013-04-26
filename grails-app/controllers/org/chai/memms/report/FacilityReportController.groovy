package org.chai.memms.report

import org.springframework.dao.DataIntegrityViolationException

class FacilityReportController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [facilityReportInstanceList: FacilityReport.list(params), facilityReportInstanceTotal: FacilityReport.count()]
    }

    def create() {
        [facilityReportInstance: new FacilityReport(params)]
    }

    def save() {
        def facilityReportInstance = new FacilityReport(params)
        if (!facilityReportInstance.save(flush: true)) {
            render(view: "create", model: [facilityReportInstance: facilityReportInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'facilityReport.label', default: 'FacilityReport'), facilityReportInstance.id])
        redirect(action: "show", id: facilityReportInstance.id)
    }

    def show(Long id) {
        def facilityReportInstance = FacilityReport.get(id)
        if (!facilityReportInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'facilityReport.label', default: 'FacilityReport'), id])
            redirect(action: "list")
            return
        }

        [facilityReportInstance: facilityReportInstance]
    }

    def edit(Long id) {
        def facilityReportInstance = FacilityReport.get(id)
        if (!facilityReportInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'facilityReport.label', default: 'FacilityReport'), id])
            redirect(action: "list")
            return
        }

        [facilityReportInstance: facilityReportInstance]
    }

    def update(Long id, Long version) {
        def facilityReportInstance = FacilityReport.get(id)
        if (!facilityReportInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'facilityReport.label', default: 'FacilityReport'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (facilityReportInstance.version > version) {
                facilityReportInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'facilityReport.label', default: 'FacilityReport')] as Object[],
                          "Another user has updated this FacilityReport while you were editing")
                render(view: "edit", model: [facilityReportInstance: facilityReportInstance])
                return
            }
        }

        facilityReportInstance.properties = params

        if (!facilityReportInstance.save(flush: true)) {
            render(view: "edit", model: [facilityReportInstance: facilityReportInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'facilityReport.label', default: 'FacilityReport'), facilityReportInstance.id])
        redirect(action: "show", id: facilityReportInstance.id)
    }

    def delete(Long id) {
        def facilityReportInstance = FacilityReport.get(id)
        if (!facilityReportInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'facilityReport.label', default: 'FacilityReport'), id])
            redirect(action: "list")
            return
        }

        try {
            facilityReportInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'facilityReport.label', default: 'FacilityReport'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'facilityReport.label', default: 'FacilityReport'), id])
            redirect(action: "show", id: id)
        }
    }
}
