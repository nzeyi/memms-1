
<%@ page import="org.chai.memms.report.DataLocationReport" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'dataLocationReport.label', default: 'DataLocationReport')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
	
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="delete" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-dataLocationReport" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list dataLocationReport">
			
				<g:if test="${dataLocationReportInstance?.code}">
				<li class="fieldcontain">
					<span id="code-label" class="property-label"><g:message code="dataLocationReport.code.label" default="Code" /></span>
					
						<span class="property-value" aria-labelledby="code-label"><g:fieldValue bean="${dataLocationReportInstance}" field="code"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${dataLocationReportInstance?.facility}">
				<li class="fieldcontain">
					<span id="facility-label" class="property-label"><g:message code="dataLocationReport.facility.label" default="Facility" /></span>
					
						<span class="property-value" aria-labelledby="facility-label"><g:link controller="dataLocation" action="show" id="${dataLocationReportInstance?.facility?.id}">${dataLocationReportInstance?.facility?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${dataLocationReportInstance?.indicatorValues}">
				<li class="fieldcontain">
					<span id="indicatorValues-label" class="property-label"><g:message code="dataLocationReport.indicatorValues.label" default="Indicator Values" /></span>
					
						<g:each in="${dataLocationReportInstance.indicatorValues}" var="i">
						<span class="property-value" aria-labelledby="indicatorValues-label"><g:link controller="indicatorValue" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${dataLocationReportInstance?.time}">
				<li class="fieldcontain">
					<span id="time-label" class="property-label"><g:message code="dataLocationReport.time.label" default="Time" /></span>
					
						<span class="property-value" aria-labelledby="time-label"><g:formatDate date="${dataLocationReportInstance?.time}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${dataLocationReportInstance?.id}" />
					<g:link class="edit" action="edit" id="${dataLocationReportInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
