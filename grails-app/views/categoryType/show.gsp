
<%@ page import="org.chai.memms.report.CategoryType" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'categoryType.label', default: 'CategoryType')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-categoryType" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-categoryType" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list categoryType">
			
				<g:if test="${categoryTypeInstance?.code}">
				<li class="fieldcontain">
					<span id="code-label" class="property-label"><g:message code="categoryType.code.label" default="Code" /></span>
					
						<span class="property-value" aria-labelledby="code-label"><g:fieldValue bean="${categoryTypeInstance}" field="code"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${categoryTypeInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="categoryType.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${categoryTypeInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${categoryTypeInstance?.indicatorTypes}">
				<li class="fieldcontain">
					<span id="indicatorTypes-label" class="property-label"><g:message code="categoryType.indicatorTypes.label" default="Indicator Types" /></span>
					
						<g:each in="${categoryTypeInstance.indicatorTypes}" var="i">
						<span class="property-value" aria-labelledby="indicatorTypes-label"><g:link controller="indicatorType" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${categoryTypeInstance?.maxYellowValue}">
				<li class="fieldcontain">
					<span id="maxYellowValue-label" class="property-label"><g:message code="categoryType.maxYellowValue.label" default="Max Yellow Value" /></span>
					
						<span class="property-value" aria-labelledby="maxYellowValue-label"><g:fieldValue bean="${categoryTypeInstance}" field="maxYellowValue"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${categoryTypeInstance?.minYellowValue}">
				<li class="fieldcontain">
					<span id="minYellowValue-label" class="property-label"><g:message code="categoryType.minYellowValue.label" default="Min Yellow Value" /></span>
					
						<span class="property-value" aria-labelledby="minYellowValue-label"><g:fieldValue bean="${categoryTypeInstance}" field="minYellowValue"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${categoryTypeInstance?.id}" />
					<g:link class="edit" action="edit" id="${categoryTypeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
