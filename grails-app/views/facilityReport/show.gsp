
<%@ page import="org.chai.memms.report.FacilityReport" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'facilityReport.label', default: 'FacilityReport')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-facilityReport" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-facilityReport" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list facilityReport">
			
				<g:if test="${facilityReportInstance?.code}">
				<li class="fieldcontain">
					<span id="code-label" class="property-label"><g:message code="facilityReport.code.label" default="Code" /></span>
					
						<span class="property-value" aria-labelledby="code-label"><g:fieldValue bean="${facilityReportInstance}" field="code"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${facilityReportInstance?.facility}">
				<li class="fieldcontain">
					<span id="facility-label" class="property-label"><g:message code="facilityReport.facility.label" default="Facility" /></span>
					
						<span class="property-value" aria-labelledby="facility-label"><g:link controller="dataLocation" action="show" id="${facilityReportInstance?.facility?.id}">${facilityReportInstance?.facility?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${facilityReportInstance?.indicatorValues}">
				<li class="fieldcontain">
					<span id="indicatorValues-label" class="property-label"><g:message code="facilityReport.indicatorValues.label" default="Indicator Values" /></span>
					
						<g:each in="${facilityReportInstance.indicatorValues}" var="i">
						<span class="property-value" aria-labelledby="indicatorValues-label"><g:link controller="indicatorValue" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${facilityReportInstance?.time}">
				<li class="fieldcontain">
					<span id="time-label" class="property-label"><g:message code="facilityReport.time.label" default="Time" /></span>
					
						<span class="property-value" aria-labelledby="time-label"><g:formatDate date="${facilityReportInstance?.time}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${facilityReportInstance?.id}" />
					<g:link class="edit" action="edit" id="${facilityReportInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
