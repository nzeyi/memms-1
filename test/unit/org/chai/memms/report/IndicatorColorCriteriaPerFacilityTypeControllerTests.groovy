package org.chai.memms.report



import org.junit.*
import grails.test.mixin.*

@TestFor(IndicatorColorCriteriaPerFacilityTypeController)
@Mock(IndicatorColorCriteriaPerFacilityType)
class IndicatorColorCriteriaPerFacilityTypeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/indicatorColorCriteriaPerFacilityType/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.indicatorColorCriteriaPerFacilityTypeInstanceList.size() == 0
        assert model.indicatorColorCriteriaPerFacilityTypeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.indicatorColorCriteriaPerFacilityTypeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.indicatorColorCriteriaPerFacilityTypeInstance != null
        assert view == '/indicatorColorCriteriaPerFacilityType/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/indicatorColorCriteriaPerFacilityType/show/1'
        assert controller.flash.message != null
        assert IndicatorColorCriteriaPerFacilityType.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/indicatorColorCriteriaPerFacilityType/list'

        populateValidParams(params)
        def indicatorColorCriteriaPerFacilityType = new IndicatorColorCriteriaPerFacilityType(params)

        assert indicatorColorCriteriaPerFacilityType.save() != null

        params.id = indicatorColorCriteriaPerFacilityType.id

        def model = controller.show()

        assert model.indicatorColorCriteriaPerFacilityTypeInstance == indicatorColorCriteriaPerFacilityType
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/indicatorColorCriteriaPerFacilityType/list'

        populateValidParams(params)
        def indicatorColorCriteriaPerFacilityType = new IndicatorColorCriteriaPerFacilityType(params)

        assert indicatorColorCriteriaPerFacilityType.save() != null

        params.id = indicatorColorCriteriaPerFacilityType.id

        def model = controller.edit()

        assert model.indicatorColorCriteriaPerFacilityTypeInstance == indicatorColorCriteriaPerFacilityType
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/indicatorColorCriteriaPerFacilityType/list'

        response.reset()

        populateValidParams(params)
        def indicatorColorCriteriaPerFacilityType = new IndicatorColorCriteriaPerFacilityType(params)

        assert indicatorColorCriteriaPerFacilityType.save() != null

        // test invalid parameters in update
        params.id = indicatorColorCriteriaPerFacilityType.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/indicatorColorCriteriaPerFacilityType/edit"
        assert model.indicatorColorCriteriaPerFacilityTypeInstance != null

        indicatorColorCriteriaPerFacilityType.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/indicatorColorCriteriaPerFacilityType/show/$indicatorColorCriteriaPerFacilityType.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        indicatorColorCriteriaPerFacilityType.clearErrors()

        populateValidParams(params)
        params.id = indicatorColorCriteriaPerFacilityType.id
        params.version = -1
        controller.update()

        assert view == "/indicatorColorCriteriaPerFacilityType/edit"
        assert model.indicatorColorCriteriaPerFacilityTypeInstance != null
        assert model.indicatorColorCriteriaPerFacilityTypeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/indicatorColorCriteriaPerFacilityType/list'

        response.reset()

        populateValidParams(params)
        def indicatorColorCriteriaPerFacilityType = new IndicatorColorCriteriaPerFacilityType(params)

        assert indicatorColorCriteriaPerFacilityType.save() != null
        assert IndicatorColorCriteriaPerFacilityType.count() == 1

        params.id = indicatorColorCriteriaPerFacilityType.id

        controller.delete()

        assert IndicatorColorCriteriaPerFacilityType.count() == 0
        assert IndicatorColorCriteriaPerFacilityType.get(indicatorColorCriteriaPerFacilityType.id) == null
        assert response.redirectedUrl == '/indicatorColorCriteriaPerFacilityType/list'
    }
}
