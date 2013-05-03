
<%@ page import="org.chai.memms.report.QuaterlyFacilityReport" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'quaterlyFacilityReport.label', default: 'QuaterlyFacilityReport')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-quaterlyFacilityReport" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-quaterlyFacilityReport" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list quaterlyFacilityReport">
			
				<g:if test="${quaterlyFacilityReportInstance?.code}">
				<li class="fieldcontain">
					<span id="code-label" class="property-label"><g:message code="quaterlyFacilityReport.code.label" default="Code" /></span>
					
						<span class="property-value" aria-labelledby="code-label"><g:fieldValue bean="${quaterlyFacilityReportInstance}" field="code"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${quaterlyFacilityReportInstance?.quarter}">
				<li class="fieldcontain">
					<span id="quarter-label" class="property-label"><g:message code="quaterlyFacilityReport.quarter.label" default="Quarter" /></span>
					
						<span class="property-value" aria-labelledby="quarter-label"><g:fieldValue bean="${quaterlyFacilityReportInstance}" field="quarter"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${quaterlyFacilityReportInstance?.month}">
				<li class="fieldcontain">
					<span id="month-label" class="property-label"><g:message code="quaterlyFacilityReport.month.label" default="Month" /></span>
					
						<span class="property-value" aria-labelledby="month-label"><g:fieldValue bean="${quaterlyFacilityReportInstance}" field="month"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${quaterlyFacilityReportInstance?.report}">
				<li class="fieldcontain">
					<span id="report-label" class="property-label"><g:message code="quaterlyFacilityReport.report.label" default="Report" /></span>
					
						<span class="property-value" aria-labelledby="report-label"><g:link controller="facilityReport" action="show" id="${quaterlyFacilityReportInstance?.report?.id}">${quaterlyFacilityReportInstance?.report?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${quaterlyFacilityReportInstance?.time}">
				<li class="fieldcontain">
					<span id="time-label" class="property-label"><g:message code="quaterlyFacilityReport.time.label" default="Time" /></span>
					
						<span class="property-value" aria-labelledby="time-label"><g:formatDate date="${quaterlyFacilityReportInstance?.time}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${quaterlyFacilityReportInstance?.year}">
				<li class="fieldcontain">
					<span id="year-label" class="property-label"><g:message code="quaterlyFacilityReport.year.label" default="Year" /></span>
					
						<span class="property-value" aria-labelledby="year-label"><g:fieldValue bean="${quaterlyFacilityReportInstance}" field="year"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${quaterlyFacilityReportInstance?.id}" />
					<g:link class="edit" action="edit" id="${quaterlyFacilityReportInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
