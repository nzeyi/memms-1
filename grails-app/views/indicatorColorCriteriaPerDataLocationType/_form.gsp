<%@ page import="org.chai.memms.report.IndicatorColorCriteriaPerDataLocationType" %>



<div class="fieldcontain ${hasErrors(bean: indicatorColorCriteriaPerDataLocationTypeInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="indicatorColorCriteriaPerDataLocationType.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="code" required="" value="${indicatorColorCriteriaPerDataLocationTypeInstance?.code}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorColorCriteriaPerDataLocationTypeInstance, field: 'dataLocationType', 'error')} required">
	<label for="dataLocationType">
		<g:message code="indicatorColorCriteriaPerDataLocationType.dataLocationType.label" default="Data Location Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="dataLocationType" name="dataLocationType.id" from="${org.chai.location.DataLocationType.list()}" optionKey="id" required="" value="${indicatorColorCriteriaPerDataLocationTypeInstance?.dataLocationType?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorColorCriteriaPerDataLocationTypeInstance, field: 'indicator', 'error')} required">
	<label for="indicator">
		<g:message code="indicatorColorCriteriaPerDataLocationType.indicator.label" default="Indicator" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="indicator" name="indicator.id" from="${org.chai.memms.report.Indicator.list()}" optionKey="id" required="" value="${indicatorColorCriteriaPerDataLocationTypeInstance?.indicator?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorColorCriteriaPerDataLocationTypeInstance, field: 'isIncreasing', 'error')} ">
	<label for="isIncreasing">
		<g:message code="indicatorColorCriteriaPerDataLocationType.isIncreasing.label" default="Is Increasing" />
		
	</label>
	<g:checkBox name="isIncreasing" value="${indicatorColorCriteriaPerDataLocationTypeInstance?.isIncreasing}" />
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorColorCriteriaPerDataLocationTypeInstance, field: 'maxYellow', 'error')} ">
	<label for="maxYellow">
		<g:message code="indicatorColorCriteriaPerDataLocationType.maxYellow.label" default="Max Yellow" />
		
	</label>
	<g:textField name="maxYellow" value="${indicatorColorCriteriaPerDataLocationTypeInstance?.maxYellow}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorColorCriteriaPerDataLocationTypeInstance, field: 'minYellow', 'error')} ">
	<label for="minYellow">
		<g:message code="indicatorColorCriteriaPerDataLocationType.minYellow.label" default="Min Yellow" />
		
	</label>
	<g:textField name="minYellow" value="${indicatorColorCriteriaPerDataLocationTypeInstance?.minYellow}"/>
</div>

