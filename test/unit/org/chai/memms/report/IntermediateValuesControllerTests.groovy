package org.chai.memms.report



import org.junit.*
import grails.test.mixin.*

@TestFor(IntermediateValuesController)
@Mock(IntermediateValues)
class IntermediateValuesControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/intermediateValues/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.intermediateValuesInstanceList.size() == 0
        assert model.intermediateValuesInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.intermediateValuesInstance != null
    }

    void testSave() {
        controller.save()

        assert model.intermediateValuesInstance != null
        assert view == '/intermediateValues/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/intermediateValues/show/1'
        assert controller.flash.message != null
        assert IntermediateValues.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/intermediateValues/list'

        populateValidParams(params)
        def intermediateValues = new IntermediateValues(params)

        assert intermediateValues.save() != null

        params.id = intermediateValues.id

        def model = controller.show()

        assert model.intermediateValuesInstance == intermediateValues
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/intermediateValues/list'

        populateValidParams(params)
        def intermediateValues = new IntermediateValues(params)

        assert intermediateValues.save() != null

        params.id = intermediateValues.id

        def model = controller.edit()

        assert model.intermediateValuesInstance == intermediateValues
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/intermediateValues/list'

        response.reset()

        populateValidParams(params)
        def intermediateValues = new IntermediateValues(params)

        assert intermediateValues.save() != null

        // test invalid parameters in update
        params.id = intermediateValues.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/intermediateValues/edit"
        assert model.intermediateValuesInstance != null

        intermediateValues.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/intermediateValues/show/$intermediateValues.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        intermediateValues.clearErrors()

        populateValidParams(params)
        params.id = intermediateValues.id
        params.version = -1
        controller.update()

        assert view == "/intermediateValues/edit"
        assert model.intermediateValuesInstance != null
        assert model.intermediateValuesInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/intermediateValues/list'

        response.reset()

        populateValidParams(params)
        def intermediateValues = new IntermediateValues(params)

        assert intermediateValues.save() != null
        assert IntermediateValues.count() == 1

        params.id = intermediateValues.id

        controller.delete()

        assert IntermediateValues.count() == 0
        assert IntermediateValues.get(intermediateValues.id) == null
        assert response.redirectedUrl == '/intermediateValues/list'
    }
}
