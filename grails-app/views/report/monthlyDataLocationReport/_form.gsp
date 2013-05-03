<%@ page import="org.chai.memms.report.MonthlyFacilityReport" %>



<div class="fieldcontain ${hasErrors(bean: monthlyFacilityReportInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="monthlyFacilityReport.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="code" required="" value="${monthlyFacilityReportInstance?.code}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: monthlyFacilityReportInstance, field: 'month', 'error')} required">
	<label for="month">
		<g:message code="monthlyFacilityReport.month.label" default="Month" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="month" type="number" value="${monthlyFacilityReportInstance.month}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: monthlyFacilityReportInstance, field: 'report', 'error')} required">
	<label for="report">
		<g:message code="monthlyFacilityReport.report.label" default="Report" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="report" name="report.id" from="${org.chai.memms.report.FacilityReport.list()}" optionKey="id" required="" value="${monthlyFacilityReportInstance?.report?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: monthlyFacilityReportInstance, field: 'time', 'error')} required">
	<label for="time">
		<g:message code="monthlyFacilityReport.time.label" default="Time" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="time" precision="day"  value="${monthlyFacilityReportInstance?.time}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: monthlyFacilityReportInstance, field: 'year', 'error')} required">
	<label for="year">
		<g:message code="monthlyFacilityReport.year.label" default="Year" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="year" type="number" value="${monthlyFacilityReportInstance.year}" required=""/>
</div>

