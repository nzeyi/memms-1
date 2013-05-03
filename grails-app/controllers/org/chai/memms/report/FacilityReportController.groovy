package org.chai.memms.report

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException

class FacilityReportController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	@Autowired
	IndicatorTypeService indicatorTypeService
/*
    def index() {
        redirect(action: "list", params: params)
    }*/
	def scaffold = true

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [facilityReportInstanceList: FacilityReport.list(params), facilityReportInstanceTotal: FacilityReport.count()]
    }

    def create() {
		List<Object> indicators=indicatorTypeService.getIndicatorTypes("select category_type_id from memms_indicator_type");
		println"==================================== list of indicators :"+indicators
		println"Total number of indicators is ok:"+indicators.size();
		int thenumber=indicatorTypeService.getIndicatorTypeValue("select count(category_type_id) from memms_indicator_type group by category_type_id")
		println"The returned number is :"+thenumber;
		 [facilityReportInstance: new FacilityReport(params)]
    }

    def save() {
        def facilityReportInstance = new FacilityReport(params)
        if (!facilityReportInstance.save(flush: true)) {
            render(view: "create", model: [facilityReportInstance: facilityReportInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'facilityReport.label', default: 'FacilityReport'), facilityReportInstance.code])
       
		
		indicatorTypeService.getIndicatorTypes()
		 redirect(action: "list", id: facilityReportInstance.id, model: [facilityReportInstance: facilityReportInstance])
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
        redirect(action: "list", id: facilityReportInstance.id)
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
            redirect(action: "list", id: id)
        }
    }
}
