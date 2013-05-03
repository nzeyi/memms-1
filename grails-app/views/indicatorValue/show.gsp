
<%@ page import="org.chai.memms.report.IndicatorValue" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'indicatorValue.label', default: 'IndicatorValue')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-indicatorValue" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-indicatorValue" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list indicatorValue">
			
				<g:if test="${indicatorValueInstance?.code}">
				<li class="fieldcontain">
					<span id="code-label" class="property-label"><g:message code="indicatorValue.code.label" default="Code" /></span>
					
						<span class="property-value" aria-labelledby="code-label"><g:fieldValue bean="${indicatorValueInstance}" field="code"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorValueInstance?.value}">
				<li class="fieldcontain">
					<span id="value-label" class="property-label"><g:message code="indicatorValue.value.label" default="Value" /></span>
					
						<span class="property-value" aria-labelledby="value-label"><g:fieldValue bean="${indicatorValueInstance}" field="value"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorValueInstance?.indicatorType}">
				<li class="fieldcontain">
					<span id="indicatorType-label" class="property-label"><g:message code="indicatorValue.indicatorType.label" default="Indicator Type" /></span>
					
						<span class="property-value" aria-labelledby="indicatorType-label"><g:link controller="indicatorType" action="show" id="${indicatorValueInstance?.indicatorType?.id}">${indicatorValueInstance?.indicatorType?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorValueInstance?.report}">
				<li class="fieldcontain">
					<span id="report-label" class="property-label"><g:message code="indicatorValue.report.label" default="Report" /></span>
					
						<span class="property-value" aria-labelledby="report-label"><g:link controller="facilityReport" action="show" id="${indicatorValueInstance?.report?.id}">${indicatorValueInstance?.report?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorValueInstance?.time}">
				<li class="fieldcontain">
					<span id="time-label" class="property-label"><g:message code="indicatorValue.time.label" default="Time" /></span>
					
						<span class="property-value" aria-labelledby="time-label"><g:formatDate date="${indicatorValueInstance?.time}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${indicatorValueInstance?.id}" />
					<g:link class="edit" action="edit" id="${indicatorValueInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
