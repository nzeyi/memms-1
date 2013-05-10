package org.chai.memms.report

import org.springframework.dao.DataIntegrityViolationException

class QueryParserHelperController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [queryParserHelperInstanceList: QueryParserHelper.list(params), queryParserHelperInstanceTotal: QueryParserHelper.count()]
    }

    def create() {
        [queryParserHelperInstance: new QueryParserHelper(params)]
    }

    def save() {
        def queryParserHelperInstance = new QueryParserHelper(params)
        if (!queryParserHelperInstance.save(flush: true)) {
            render(view: "create", model: [queryParserHelperInstance: queryParserHelperInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'queryParserHelper.label', default: 'QueryParserHelper'), queryParserHelperInstance.id])
        redirect(action: "show", id: queryParserHelperInstance.id)
    }

    def show(Long id) {
        def queryParserHelperInstance = QueryParserHelper.get(id)
        if (!queryParserHelperInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'queryParserHelper.label', default: 'QueryParserHelper'), id])
            redirect(action: "list")
            return
        }

        [queryParserHelperInstance: queryParserHelperInstance]
    }

    def edit(Long id) {
        def queryParserHelperInstance = QueryParserHelper.get(id)
        if (!queryParserHelperInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'queryParserHelper.label', default: 'QueryParserHelper'), id])
            redirect(action: "list")
            return
        }

        [queryParserHelperInstance: queryParserHelperInstance]
    }

    def update(Long id, Long version) {
        def queryParserHelperInstance = QueryParserHelper.get(id)
        if (!queryParserHelperInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'queryParserHelper.label', default: 'QueryParserHelper'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (queryParserHelperInstance.version > version) {
                queryParserHelperInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'queryParserHelper.label', default: 'QueryParserHelper')] as Object[],
                          "Another user has updated this QueryParserHelper while you were editing")
                render(view: "edit", model: [queryParserHelperInstance: queryParserHelperInstance])
                return
            }
        }

        queryParserHelperInstance.properties = params

        if (!queryParserHelperInstance.save(flush: true)) {
            render(view: "edit", model: [queryParserHelperInstance: queryParserHelperInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'queryParserHelper.label', default: 'QueryParserHelper'), queryParserHelperInstance.id])
        redirect(action: "show", id: queryParserHelperInstance.id)
    }

    def delete(Long id) {
        def queryParserHelperInstance = QueryParserHelper.get(id)
        if (!queryParserHelperInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'queryParserHelper.label', default: 'QueryParserHelper'), id])
            redirect(action: "list")
            return
        }

        try {
            queryParserHelperInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'queryParserHelper.label', default: 'QueryParserHelper'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'queryParserHelper.label', default: 'QueryParserHelper'), id])
            redirect(action: "show", id: id)
        }
    }
}
