package org.chai.memms.report



import org.junit.*
import grails.test.mixin.*

@TestFor(IndicatorTypeController)
@Mock(IndicatorType)
class IndicatorTypeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/indicatorType/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.indicatorTypeInstanceList.size() == 0
        assert model.indicatorTypeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.indicatorTypeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.indicatorTypeInstance != null
        assert view == '/indicatorType/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/indicatorType/show/1'
        assert controller.flash.message != null
        assert IndicatorType.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/indicatorType/list'

        populateValidParams(params)
        def indicatorType = new IndicatorType(params)

        assert indicatorType.save() != null

        params.id = indicatorType.id

        def model = controller.show()

        assert model.indicatorTypeInstance == indicatorType
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/indicatorType/list'

        populateValidParams(params)
        def indicatorType = new IndicatorType(params)

        assert indicatorType.save() != null

        params.id = indicatorType.id

        def model = controller.edit()

        assert model.indicatorTypeInstance == indicatorType
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/indicatorType/list'

        response.reset()

        populateValidParams(params)
        def indicatorType = new IndicatorType(params)

        assert indicatorType.save() != null

        // test invalid parameters in update
        params.id = indicatorType.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/indicatorType/edit"
        assert model.indicatorTypeInstance != null

        indicatorType.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/indicatorType/show/$indicatorType.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        indicatorType.clearErrors()

        populateValidParams(params)
        params.id = indicatorType.id
        params.version = -1
        controller.update()

        assert view == "/indicatorType/edit"
        assert model.indicatorTypeInstance != null
        assert model.indicatorTypeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/indicatorType/list'

        response.reset()

        populateValidParams(params)
        def indicatorType = new IndicatorType(params)

        assert indicatorType.save() != null
        assert IndicatorType.count() == 1

        params.id = indicatorType.id

        controller.delete()

        assert IndicatorType.count() == 0
        assert IndicatorType.get(indicatorType.id) == null
        assert response.redirectedUrl == '/indicatorType/list'
    }
}
