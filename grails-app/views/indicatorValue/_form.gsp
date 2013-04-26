<%@ page import="org.chai.memms.report.IndicatorValue" %>



<div class="fieldcontain ${hasErrors(bean: indicatorValueInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="indicatorValue.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="code" required="" value="${indicatorValueInstance?.code}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorValueInstance, field: 'value', 'error')} required">
	<label for="value">
		<g:message code="indicatorValue.value.label" default="Value" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="value" value="${fieldValue(bean: indicatorValueInstance, field: 'value')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorValueInstance, field: 'report', 'error')} required">
	<label for="report">
		<g:message code="indicatorValue.report.label" default="Report" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="report" name="report.id" from="${org.chai.memms.report.FacilityReport.list()}" optionKey="id" required="" value="${indicatorValueInstance?.report?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorValueInstance, field: 'time', 'error')} required">
	<label for="time">
		<g:message code="indicatorValue.time.label" default="Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="time" precision="day"  value="${indicatorValueInstance?.time}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorValueInstance, field: 'type', 'error')} required">
	<label for="type">
		<g:message code="indicatorValue.type.label" default="Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="type" name="type.id" from="${org.chai.memms.report.IndicatorType.list()}" optionKey="id" required="" value="${indicatorValueInstance?.type?.id}" class="many-to-one"/>
</div>

