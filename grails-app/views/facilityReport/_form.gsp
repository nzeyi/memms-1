<%@ page import="org.chai.memms.report.FacilityReport" %>
<div  class="entity-form-container togglable">
	<div class="heading1-bar">
	<ul>
				<g:reportMenus/>
				
			</ul></div>
		<div class="main">
<table ><tr><td>

<div class="fieldcontain ${hasErrors(bean: facilityReportInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="facilityReport.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	
</div></td><td><g:textField name="code" required="" value="${facilityReportInstance?.code}"/></td></tr>
<tr><td colspan="2">
<div class="fieldcontain ${hasErrors(bean: facilityReportInstance, field: 'facility', 'error')} required">
	
	<g:selectFromList id="facility" label="${message(code:'facility.label')}" name="facility.id"  bean="${facility}"
  			from="${org.chai.location.DataLocation.list()}" value="${facilityReportInstance?.facility?.id}" values="${org.chai.location.DataLocation.list().collect{it.names}}" optionKey="id"/>
</div>

</td></tr>

<tr><td><div class="fieldcontain ${hasErrors(bean: facilityReportInstance, field: 'time', 'error')} required">
	<label for="time">
		<g:message code="facilityReport.time.label" default="Time" />
		<span class="required-indicator">*</span>
	</label>
	
</div></td><td><g:datePicker name="time" precision="day"  value="${facilityReportInstance?.time}"  /></td></tr></table></div></div>

