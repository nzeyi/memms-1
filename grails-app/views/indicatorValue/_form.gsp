<%@ page import="org.chai.memms.report.IndicatorValue" %>
<div  class="entity-form-container togglable">
	<div class="heading1-bar">
<ul>
				<g:reportMenus/>
				
			</ul>
			</div>
		<div class="main">
<table><tr><td>

<div class="fieldcontain ${hasErrors(bean: indicatorValueInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="indicatorValue.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	
</div></td><td><g:textField name="code" required="" value="${indicatorValueInstance?.code}"/></td></tr>

<tr><td><div class="fieldcontain ${hasErrors(bean: indicatorValueInstance, field: 'value', 'error')} required">
	<label for="value">
		<g:message code="indicatorValue.value.label" default="Value" />
		<span class="required-indicator">*</span>
	</label>
	
</div></td><td><g:field name="value" value="${fieldValue(bean: indicatorValueInstance, field: 'value')}" required=""/></td></tr>

<tr><td><div class="fieldcontain ${hasErrors(bean: indicatorValueInstance, field: 'indicatorType', 'error')} required">
	<label for="indicatorType">
		<g:message code="indicatorValue.indicatorType.label" default="Indicator Type" />
		<span class="required-indicator">*</span>
	</label>
	
</div></td><td><g:select id="indicatorType" name="indicatorType.id" from="${org.chai.memms.report.IndicatorType.list()}" optionKey="id" required="" value="${indicatorValueInstance?.indicatorType?.id}" class="many-to-one"/></td></tr>

<tr><td><div class="fieldcontain ${hasErrors(bean: indicatorValueInstance, field: 'report', 'error')} required">
	<label for="report">
		<g:message code="indicatorValue.report.label" default="Report" />
		<span class="required-indicator">*</span>
	</label>
	
</div></td><td><g:select id="report" name="report.id" from="${org.chai.memms.report.FacilityReport.list()}" optionKey="id" required="" value="${indicatorValueInstance?.report?.id}" class="many-to-one"/></td></tr>

<tr><td><div class="fieldcontain ${hasErrors(bean: indicatorValueInstance, field: 'time', 'error')} required">
	<label for="time">
		<g:message code="indicatorValue.time.label" default="Time" />
		<span class="required-indicator">*</span>
	</label>
	
</div></td><td><g:datePicker name="time" precision="day"  value="${indicatorValueInstance?.time}"  /></td></tr></table></div></div>

