
<%@ page import="org.chai.memms.report.MonthlyFacilityReport" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'monthlyFacilityReport.label', default: 'MonthlyFacilityReport')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-monthlyFacilityReport" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-monthlyFacilityReport" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list monthlyFacilityReport">
			
				<g:if test="${monthlyFacilityReportInstance?.code}">
				<li class="fieldcontain">
					<span id="code-label" class="property-label"><g:message code="monthlyFacilityReport.code.label" default="Code" /></span>
					
						<span class="property-value" aria-labelledby="code-label"><g:fieldValue bean="${monthlyFacilityReportInstance}" field="code"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${monthlyFacilityReportInstance?.month}">
				<li class="fieldcontain">
					<span id="month-label" class="property-label"><g:message code="monthlyFacilityReport.month.label" default="Month" /></span>
					
						<span class="property-value" aria-labelledby="month-label"><g:fieldValue bean="${monthlyFacilityReportInstance}" field="month"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${monthlyFacilityReportInstance?.report}">
				<li class="fieldcontain">
					<span id="report-label" class="property-label"><g:message code="monthlyFacilityReport.report.label" default="Report" /></span>
					
						<span class="property-value" aria-labelledby="report-label"><g:link controller="facilityReport" action="show" id="${monthlyFacilityReportInstance?.report?.id}">${monthlyFacilityReportInstance?.report?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${monthlyFacilityReportInstance?.time}">
				<li class="fieldcontain">
					<span id="time-label" class="property-label"><g:message code="monthlyFacilityReport.time.label" default="Time" /></span>
					
						<span class="property-value" aria-labelledby="time-label"><g:formatDate date="${monthlyFacilityReportInstance?.time}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${monthlyFacilityReportInstance?.year}">
				<li class="fieldcontain">
					<span id="year-label" class="property-label"><g:message code="monthlyFacilityReport.year.label" default="Year" /></span>
					
						<span class="property-value" aria-labelledby="year-label"><g:fieldValue bean="${monthlyFacilityReportInstance}" field="year"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${monthlyFacilityReportInstance?.id}" />
					<g:link class="edit" action="edit" id="${monthlyFacilityReportInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
