package org.chai.memms.report

import org.springframework.dao.DataIntegrityViolationException

class MonthlyFacilityReportController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [monthlyFacilityReportInstanceList: MonthlyFacilityReport.list(params), monthlyFacilityReportInstanceTotal: MonthlyFacilityReport.count()]
    }

    def create() {
        [monthlyFacilityReportInstance: new MonthlyFacilityReport(params)]
    }

    def save() {
        def monthlyFacilityReportInstance = new MonthlyFacilityReport(params)
        if (!monthlyFacilityReportInstance.save(flush: true)) {
            render(view: "create", model: [monthlyFacilityReportInstance: monthlyFacilityReportInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'monthlyFacilityReport.label', default: 'MonthlyFacilityReport'), monthlyFacilityReportInstance.id])
        redirect(action: "show", id: monthlyFacilityReportInstance.id)
    }

    def show(Long id) {
        def monthlyFacilityReportInstance = MonthlyFacilityReport.get(id)
        if (!monthlyFacilityReportInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'monthlyFacilityReport.label', default: 'MonthlyFacilityReport'), id])
            redirect(action: "list")
            return
        }

        [monthlyFacilityReportInstance: monthlyFacilityReportInstance]
    }

    def edit(Long id) {
        def monthlyFacilityReportInstance = MonthlyFacilityReport.get(id)
        if (!monthlyFacilityReportInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'monthlyFacilityReport.label', default: 'MonthlyFacilityReport'), id])
            redirect(action: "list")
            return
        }

        [monthlyFacilityReportInstance: monthlyFacilityReportInstance]
    }

    def update(Long id, Long version) {
        def monthlyFacilityReportInstance = MonthlyFacilityReport.get(id)
        if (!monthlyFacilityReportInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'monthlyFacilityReport.label', default: 'MonthlyFacilityReport'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (monthlyFacilityReportInstance.version > version) {
                monthlyFacilityReportInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'monthlyFacilityReport.label', default: 'MonthlyFacilityReport')] as Object[],
                          "Another user has updated this MonthlyFacilityReport while you were editing")
                render(view: "edit", model: [monthlyFacilityReportInstance: monthlyFacilityReportInstance])
                return
            }
        }

        monthlyFacilityReportInstance.properties = params

        if (!monthlyFacilityReportInstance.save(flush: true)) {
            render(view: "edit", model: [monthlyFacilityReportInstance: monthlyFacilityReportInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'monthlyFacilityReport.label', default: 'MonthlyFacilityReport'), monthlyFacilityReportInstance.id])
        redirect(action: "show", id: monthlyFacilityReportInstance.id)
    }

    def delete(Long id) {
        def monthlyFacilityReportInstance = MonthlyFacilityReport.get(id)
        if (!monthlyFacilityReportInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'monthlyFacilityReport.label', default: 'MonthlyFacilityReport'), id])
            redirect(action: "list")
            return
        }

        try {
            monthlyFacilityReportInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'monthlyFacilityReport.label', default: 'MonthlyFacilityReport'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'monthlyFacilityReport.label', default: 'MonthlyFacilityReport'), id])
            redirect(action: "show", id: id)
        }
    }
}
