<%@ page import="org.chai.memms.report.FacilityReport" %>



<div class="fieldcontain ${hasErrors(bean: facilityReportInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="facilityReport.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="code" required="" value="${facilityReportInstance?.code}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facilityReportInstance, field: 'facility', 'error')} required">
	<label for="facility">
		<g:message code="facilityReport.facility.label" default="Facility" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="facility" name="facility.id" from="${org.chai.location.DataLocation.list()}" optionKey="id" required="" value="${facilityReportInstance?.facility?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: facilityReportInstance, field: 'indicatorValues', 'error')} ">
	<label for="indicatorValues">
		<g:message code="facilityReport.indicatorValues.label" default="Indicator Values" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${facilityReportInstance?.indicatorValues?}" var="i">
    <li><g:link controller="indicatorValue" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="indicatorValue" action="create" params="['facilityReport.id': facilityReportInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'indicatorValue.label', default: 'IndicatorValue')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: facilityReportInstance, field: 'time', 'error')} required">
	<label for="time">
		<g:message code="facilityReport.time.label" default="Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="time" precision="day"  value="${facilityReportInstance?.time}"  />
</div>

