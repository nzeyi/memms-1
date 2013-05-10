
<%@ page import="org.chai.memms.report.IntermediateVariable" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'intermediateVariable.label', default: 'IntermediateVariable')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-intermediateVariable" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-intermediateVariable" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list intermediateVariable">
			
				<g:if test="${intermediateVariableInstance?.code}">
				<li class="fieldcontain">
					<span id="code-label" class="property-label"><g:message code="intermediateVariable.code.label" default="Code" /></span>
					
						<span class="property-value" aria-labelledby="code-label"><g:fieldValue bean="${intermediateVariableInstance}" field="code"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${intermediateVariableInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="intermediateVariable.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${intermediateVariableInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${intermediateVariableInstance?.executableScript}">
				<li class="fieldcontain">
					<span id="executableScript-label" class="property-label"><g:message code="intermediateVariable.executableScript.label" default="Executable Script" /></span>
					
						<span class="property-value" aria-labelledby="executableScript-label"><g:fieldValue bean="${intermediateVariableInstance}" field="executableScript"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${intermediateVariableInstance?.intermediateVariables}">
				<li class="fieldcontain">
					<span id="intermediateVariables-label" class="property-label"><g:message code="intermediateVariable.intermediateVariables.label" default="Intermediate Variables" /></span>
					
						<g:each in="${intermediateVariableInstance.intermediateVariables}" var="i">
						<span class="property-value" aria-labelledby="intermediateVariables-label"><g:link controller="intermediateVariable" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${intermediateVariableInstance?.executionResult}">
				<li class="fieldcontain">
					<span id="executionResult-label" class="property-label"><g:message code="intermediateVariable.executionResult.label" default="Execution Result" /></span>
					
						<span class="property-value" aria-labelledby="executionResult-label"><g:fieldValue bean="${intermediateVariableInstance}" field="executionResult"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${intermediateVariableInstance?.indicators}">
				<li class="fieldcontain">
					<span id="indicators-label" class="property-label"><g:message code="intermediateVariable.indicators.label" default="Indicators" /></span>
					
						<g:each in="${intermediateVariableInstance.indicators}" var="i">
						<span class="property-value" aria-labelledby="indicators-label"><g:link controller="indicator" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${intermediateVariableInstance?.id}" />
					<g:link class="edit" action="edit" id="${intermediateVariableInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
