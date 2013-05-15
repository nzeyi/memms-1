package org.chai.memms.report



import org.junit.*
import grails.test.mixin.*

@TestFor(CategoryTypeController)
@Mock(IndicatorCategory)
class CategoryTypeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/categoryType/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.categoryTypeInstanceList.size() == 0
        assert model.categoryTypeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.categoryTypeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.categoryTypeInstance != null
        assert view == '/categoryType/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/categoryType/show/1'
        assert controller.flash.message != null
        assert IndicatorCategory.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/categoryType/list'

        populateValidParams(params)
        def categoryType = new IndicatorCategory(params)

        assert categoryType.save() != null

        params.id = categoryType.id

        def model = controller.show()

        assert model.categoryTypeInstance == categoryType
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/categoryType/list'

        populateValidParams(params)
        def categoryType = new IndicatorCategory(params)

        assert categoryType.save() != null

        params.id = categoryType.id

        def model = controller.edit()

        assert model.categoryTypeInstance == categoryType
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/categoryType/list'

        response.reset()

        populateValidParams(params)
        def categoryType = new IndicatorCategory(params)

        assert categoryType.save() != null

        // test invalid parameters in update
        params.id = categoryType.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/categoryType/edit"
        assert model.categoryTypeInstance != null

        categoryType.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/categoryType/show/$categoryType.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        categoryType.clearErrors()

        populateValidParams(params)
        params.id = categoryType.id
        params.version = -1
        controller.update()

        assert view == "/categoryType/edit"
        assert model.categoryTypeInstance != null
        assert model.categoryTypeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/categoryType/list'

        response.reset()

        populateValidParams(params)
        def categoryType = new IndicatorCategory(params)

        assert categoryType.save() != null
        assert IndicatorCategory.count() == 1

        params.id = categoryType.id

        controller.delete()

        assert IndicatorCategory.count() == 0
        assert IndicatorCategory.get(categoryType.id) == null
        assert response.redirectedUrl == '/categoryType/list'
    }
}
