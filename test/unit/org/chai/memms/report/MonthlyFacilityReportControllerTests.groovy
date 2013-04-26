package org.chai.memms.report



import org.junit.*
import grails.test.mixin.*

@TestFor(MonthlyFacilityReportController)
@Mock(MonthlyFacilityReport)
class MonthlyFacilityReportControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/monthlyFacilityReport/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.monthlyFacilityReportInstanceList.size() == 0
        assert model.monthlyFacilityReportInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.monthlyFacilityReportInstance != null
    }

    void testSave() {
        controller.save()

        assert model.monthlyFacilityReportInstance != null
        assert view == '/monthlyFacilityReport/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/monthlyFacilityReport/show/1'
        assert controller.flash.message != null
        assert MonthlyFacilityReport.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/monthlyFacilityReport/list'

        populateValidParams(params)
        def monthlyFacilityReport = new MonthlyFacilityReport(params)

        assert monthlyFacilityReport.save() != null

        params.id = monthlyFacilityReport.id

        def model = controller.show()

        assert model.monthlyFacilityReportInstance == monthlyFacilityReport
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/monthlyFacilityReport/list'

        populateValidParams(params)
        def monthlyFacilityReport = new MonthlyFacilityReport(params)

        assert monthlyFacilityReport.save() != null

        params.id = monthlyFacilityReport.id

        def model = controller.edit()

        assert model.monthlyFacilityReportInstance == monthlyFacilityReport
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/monthlyFacilityReport/list'

        response.reset()

        populateValidParams(params)
        def monthlyFacilityReport = new MonthlyFacilityReport(params)

        assert monthlyFacilityReport.save() != null

        // test invalid parameters in update
        params.id = monthlyFacilityReport.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/monthlyFacilityReport/edit"
        assert model.monthlyFacilityReportInstance != null

        monthlyFacilityReport.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/monthlyFacilityReport/show/$monthlyFacilityReport.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        monthlyFacilityReport.clearErrors()

        populateValidParams(params)
        params.id = monthlyFacilityReport.id
        params.version = -1
        controller.update()

        assert view == "/monthlyFacilityReport/edit"
        assert model.monthlyFacilityReportInstance != null
        assert model.monthlyFacilityReportInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/monthlyFacilityReport/list'

        response.reset()

        populateValidParams(params)
        def monthlyFacilityReport = new MonthlyFacilityReport(params)

        assert monthlyFacilityReport.save() != null
        assert MonthlyFacilityReport.count() == 1

        params.id = monthlyFacilityReport.id

        controller.delete()

        assert MonthlyFacilityReport.count() == 0
        assert MonthlyFacilityReport.get(monthlyFacilityReport.id) == null
        assert response.redirectedUrl == '/monthlyFacilityReport/list'
    }
}
