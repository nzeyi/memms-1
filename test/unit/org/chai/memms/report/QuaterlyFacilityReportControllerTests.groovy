package org.chai.memms.report



import org.junit.*
import grails.test.mixin.*

@TestFor(QuaterlyFacilityReportController)
@Mock(QuaterlyFacilityReport)
class QuaterlyFacilityReportControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/quaterlyFacilityReport/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.quaterlyFacilityReportInstanceList.size() == 0
        assert model.quaterlyFacilityReportInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.quaterlyFacilityReportInstance != null
    }

    void testSave() {
        controller.save()

        assert model.quaterlyFacilityReportInstance != null
        assert view == '/quaterlyFacilityReport/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/quaterlyFacilityReport/show/1'
        assert controller.flash.message != null
        assert QuaterlyFacilityReport.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/quaterlyFacilityReport/list'

        populateValidParams(params)
        def quaterlyFacilityReport = new QuaterlyFacilityReport(params)

        assert quaterlyFacilityReport.save() != null

        params.id = quaterlyFacilityReport.id

        def model = controller.show()

        assert model.quaterlyFacilityReportInstance == quaterlyFacilityReport
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/quaterlyFacilityReport/list'

        populateValidParams(params)
        def quaterlyFacilityReport = new QuaterlyFacilityReport(params)

        assert quaterlyFacilityReport.save() != null

        params.id = quaterlyFacilityReport.id

        def model = controller.edit()

        assert model.quaterlyFacilityReportInstance == quaterlyFacilityReport
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/quaterlyFacilityReport/list'

        response.reset()

        populateValidParams(params)
        def quaterlyFacilityReport = new QuaterlyFacilityReport(params)

        assert quaterlyFacilityReport.save() != null

        // test invalid parameters in update
        params.id = quaterlyFacilityReport.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/quaterlyFacilityReport/edit"
        assert model.quaterlyFacilityReportInstance != null

        quaterlyFacilityReport.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/quaterlyFacilityReport/show/$quaterlyFacilityReport.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        quaterlyFacilityReport.clearErrors()

        populateValidParams(params)
        params.id = quaterlyFacilityReport.id
        params.version = -1
        controller.update()

        assert view == "/quaterlyFacilityReport/edit"
        assert model.quaterlyFacilityReportInstance != null
        assert model.quaterlyFacilityReportInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/quaterlyFacilityReport/list'

        response.reset()

        populateValidParams(params)
        def quaterlyFacilityReport = new QuaterlyFacilityReport(params)

        assert quaterlyFacilityReport.save() != null
        assert QuaterlyFacilityReport.count() == 1

        params.id = quaterlyFacilityReport.id

        controller.delete()

        assert QuaterlyFacilityReport.count() == 0
        assert QuaterlyFacilityReport.get(quaterlyFacilityReport.id) == null
        assert response.redirectedUrl == '/quaterlyFacilityReport/list'
    }
}
