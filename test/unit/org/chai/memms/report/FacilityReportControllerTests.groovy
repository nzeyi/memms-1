package org.chai.memms.report



import org.junit.*
import grails.test.mixin.*

@TestFor(FacilityReportController)
@Mock(FacilityReport)
class FacilityReportControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/facilityReport/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.facilityReportInstanceList.size() == 0
        assert model.facilityReportInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.facilityReportInstance != null
    }

    void testSave() {
        controller.save()

        assert model.facilityReportInstance != null
        assert view == '/facilityReport/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/facilityReport/show/1'
        assert controller.flash.message != null
        assert FacilityReport.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/facilityReport/list'

        populateValidParams(params)
        def facilityReport = new FacilityReport(params)

        assert facilityReport.save() != null

        params.id = facilityReport.id

        def model = controller.show()

        assert model.facilityReportInstance == facilityReport
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/facilityReport/list'

        populateValidParams(params)
        def facilityReport = new FacilityReport(params)

        assert facilityReport.save() != null

        params.id = facilityReport.id

        def model = controller.edit()

        assert model.facilityReportInstance == facilityReport
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/facilityReport/list'

        response.reset()

        populateValidParams(params)
        def facilityReport = new FacilityReport(params)

        assert facilityReport.save() != null

        // test invalid parameters in update
        params.id = facilityReport.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/facilityReport/edit"
        assert model.facilityReportInstance != null

        facilityReport.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/facilityReport/show/$facilityReport.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        facilityReport.clearErrors()

        populateValidParams(params)
        params.id = facilityReport.id
        params.version = -1
        controller.update()

        assert view == "/facilityReport/edit"
        assert model.facilityReportInstance != null
        assert model.facilityReportInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/facilityReport/list'

        response.reset()

        populateValidParams(params)
        def facilityReport = new FacilityReport(params)

        assert facilityReport.save() != null
        assert FacilityReport.count() == 1

        params.id = facilityReport.id

        controller.delete()

        assert FacilityReport.count() == 0
        assert FacilityReport.get(facilityReport.id) == null
        assert response.redirectedUrl == '/facilityReport/list'
    }
}
