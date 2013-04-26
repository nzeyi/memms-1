
<%@ page import="org.chai.memms.report.IndicatorType" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'indicatorType.label', default: 'IndicatorType')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-indicatorType" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-indicatorType" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list indicatorType">
			
				<g:if test="${indicatorTypeInstance?.code}">
				<li class="fieldcontain">
					<span id="code-label" class="property-label"><g:message code="indicatorType.code.label" default="Code" /></span>
					
						<span class="property-value" aria-labelledby="code-label"><g:fieldValue bean="${indicatorTypeInstance}" field="code"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorTypeInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="indicatorType.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${indicatorTypeInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorTypeInstance?.categoryType}">
				<li class="fieldcontain">
					<span id="categoryType-label" class="property-label"><g:message code="indicatorType.categoryType.label" default="Category Type" /></span>
					
						<span class="property-value" aria-labelledby="categoryType-label"><g:link controller="categoryType" action="show" id="${indicatorTypeInstance?.categoryType?.id}">${indicatorTypeInstance?.categoryType?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorTypeInstance?.formula}">
				<li class="fieldcontain">
					<span id="formula-label" class="property-label"><g:message code="indicatorType.formula.label" default="Formula" /></span>
					
						<span class="property-value" aria-labelledby="formula-label"><g:fieldValue bean="${indicatorTypeInstance}" field="formula"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorTypeInstance?.increasing}">
				<li class="fieldcontain">
					<span id="increasing-label" class="property-label"><g:message code="indicatorType.increasing.label" default="Increasing" /></span>
					
						<span class="property-value" aria-labelledby="increasing-label"><g:formatBoolean boolean="${indicatorTypeInstance?.increasing}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorTypeInstance?.indicatorValues}">
				<li class="fieldcontain">
					<span id="indicatorValues-label" class="property-label"><g:message code="indicatorType.indicatorValues.label" default="Indicator Values" /></span>
					
						<g:each in="${indicatorTypeInstance.indicatorValues}" var="i">
						<span class="property-value" aria-labelledby="indicatorValues-label"><g:link controller="indicatorValue" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorTypeInstance?.internediateValuess}">
				<li class="fieldcontain">
					<span id="internediateValuess-label" class="property-label"><g:message code="indicatorType.internediateValuess.label" default="Internediate Valuess" /></span>
					
						<g:each in="${indicatorTypeInstance.internediateValuess}" var="i">
						<span class="property-value" aria-labelledby="internediateValuess-label"><g:link controller="intermediateValues" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorTypeInstance?.maxYellowValue}">
				<li class="fieldcontain">
					<span id="maxYellowValue-label" class="property-label"><g:message code="indicatorType.maxYellowValue.label" default="Max Yellow Value" /></span>
					
						<span class="property-value" aria-labelledby="maxYellowValue-label"><g:fieldValue bean="${indicatorTypeInstance}" field="maxYellowValue"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorTypeInstance?.minYellowValue}">
				<li class="fieldcontain">
					<span id="minYellowValue-label" class="property-label"><g:message code="indicatorType.minYellowValue.label" default="Min Yellow Value" /></span>
					
						<span class="property-value" aria-labelledby="minYellowValue-label"><g:fieldValue bean="${indicatorTypeInstance}" field="minYellowValue"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${indicatorTypeInstance?.id}" />
					<g:link class="edit" action="edit" id="${indicatorTypeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
