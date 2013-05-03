<%@ page import="org.chai.memms.report.QuaterlyFacilityReport" %>



<div class="fieldcontain ${hasErrors(bean: quaterlyFacilityReportInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="quaterlyFacilityReport.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="code" required="" value="${quaterlyFacilityReportInstance?.code}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: quaterlyFacilityReportInstance, field: 'quarter', 'error')} required">
	<label for="quarter">
		<g:message code="quaterlyFacilityReport.quarter.label" default="Quarter" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="quarter" required="" value="${quaterlyFacilityReportInstance?.quarter}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: quaterlyFacilityReportInstance, field: 'month', 'error')} required">
	<label for="month">
		<g:message code="quaterlyFacilityReport.month.label" default="Month" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="month" type="number" value="${quaterlyFacilityReportInstance.month}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: quaterlyFacilityReportInstance, field: 'report', 'error')} required">
	<label for="report">
		<g:message code="quaterlyFacilityReport.report.label" default="Report" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="report" name="report.id" from="${org.chai.memms.report.FacilityReport.list()}" optionKey="id" required="" value="${quaterlyFacilityReportInstance?.report?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: quaterlyFacilityReportInstance, field: 'time', 'error')} required">
	<label for="time">
		<g:message code="quaterlyFacilityReport.time.label" default="Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="time" precision="day"  value="${quaterlyFacilityReportInstance?.time}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: quaterlyFacilityReportInstance, field: 'year', 'error')} required">
	<label for="year">
		<g:message code="quaterlyFacilityReport.year.label" default="Year" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="year" type="number" value="${quaterlyFacilityReportInstance.year}" required=""/>
</div>

