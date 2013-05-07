
<%@ page import="org.chai.memms.report.IntermediateValues" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'intermediateValues.label', default: 'IntermediateValues')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-intermediateValues" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-intermediateValues" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list intermediateValues">
			
				<g:if test="${intermediateValuesInstance?.code}">
				<li class="fieldcontain">
					<span id="code-label" class="property-label"><g:message code="intermediateValues.code.label" default="Code" /></span>
					
						<span class="property-value" aria-labelledby="code-label"><g:fieldValue bean="${intermediateValuesInstance}" field="code"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${intermediateValuesInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="intermediateValues.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${intermediateValuesInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${intermediateValuesInstance?.formula}">
				<li class="fieldcontain">
					<span id="formula-label" class="property-label"><g:message code="intermediateValues.formula.label" default="Formula" /></span>
					
						<span class="property-value" aria-labelledby="formula-label"><g:fieldValue bean="${intermediateValuesInstance}" field="formula"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${intermediateValuesInstance?.indicatorTypes}">
				<li class="fieldcontain">
					<span id="indicatorTypes-label" class="property-label"><g:message code="intermediateValues.indicatorTypes.label" default="Indicator Types" /></span>
					
						<g:each in="${intermediateValuesInstance.indicatorTypes}" var="i">
						<span class="property-value" aria-labelledby="indicatorTypes-label"><g:link controller="indicatorType" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${intermediateValuesInstance?.id}" />
					<g:link class="edit" action="edit" id="${intermediateValuesInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
