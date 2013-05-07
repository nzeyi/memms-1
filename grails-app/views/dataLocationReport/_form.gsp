<%@ page import="org.chai.memms.report.DataLocationReport" %>
<div  class="entity-form-container togglable">
	<div class="heading1-bar">
	<ul>
				<g:reportMenus/>
				
			</ul></div>
		<div class="main">
<table ><tr><td>

<div class="fieldcontain ${hasErrors(bean: dataLocationReportInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="dataLocationReport.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	
</div></td><td><g:textField name="code" required="" value="${dataLocationReportInstance?.code}"/></td></tr>
<tr><td colspan="2">
<div class="fieldcontain ${hasErrors(bean: dataLocationReportInstance, field: 'dataLocation', 'error')} required">
	
	<g:selectFromList id="dataLocation" label="${message(code:'dataLocation.label')}" name="dataLocation.id"  bean="${dataLocation}"
  			from="${org.chai.location.DataLocation.list()}" value="${dataLocationReportInstance?.dataLocation?.id}" values="${org.chai.location.DataLocation.list().collect{it.names}}" optionKey="id"/>
</div>

</td></tr>

<tr><td><div class="fieldcontain ${hasErrors(bean: dataLocationReportInstance, field: 'generatedAt', 'error')} required">
	<label for="generatedAt">
		<g:message code="dataLocationReport.generatedAt.label" default="Time" />
		<span class="required-indicator">*</span>
	</label>
	
</div></td><td><g:datePicker name="generatedAt" precision="day"  value="${dataLocationReportInstance?.generatedAt}"  /></td></tr></table></div></div>

