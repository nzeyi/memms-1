
<%@ page import="org.chai.memms.report.Indicator" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'indicator.label', default: 'Indicator')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-indicator" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-indicator" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list indicator">
			
				<g:if test="${indicatorInstance?.code}">
				<li class="fieldcontain">
					<span id="code-label" class="property-label"><g:message code="indicator.code.label" default="Code" /></span>
					
						<span class="property-value" aria-labelledby="code-label"><g:fieldValue bean="${indicatorInstance}" field="code"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorInstance?.formula}">
				<li class="fieldcontain">
					<span id="formula-label" class="property-label"><g:message code="indicator.formula.label" default="Formula" /></span>
					
						<span class="property-value" aria-labelledby="formula-label"><g:fieldValue bean="${indicatorInstance}" field="formula"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorInstance?.indicatorName_en}">
				<li class="fieldcontain">
					<span id="indicatorName_en-label" class="property-label"><g:message code="indicator.indicatorName_en.label" default="Indicator Nameen" /></span>
					
						<span class="property-value" aria-labelledby="indicatorName_en-label"><g:fieldValue bean="${indicatorInstance}" field="indicatorName_en"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorInstance?.indicatorName_fr}">
				<li class="fieldcontain">
					<span id="indicatorName_fr-label" class="property-label"><g:message code="indicator.indicatorName_fr.label" default="Indicator Namefr" /></span>
					
						<span class="property-value" aria-labelledby="indicatorName_fr-label"><g:fieldValue bean="${indicatorInstance}" field="indicatorName_fr"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorInstance?.categoryType}">
				<li class="fieldcontain">
					<span id="categoryType-label" class="property-label"><g:message code="indicator.categoryType.label" default="Category Type" /></span>
					
						<span class="property-value" aria-labelledby="categoryType-label"><g:link controller="categoryType" action="show" id="${indicatorInstance?.categoryType?.id}">${indicatorInstance?.categoryType?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorInstance?.indicatorColors}">
				<li class="fieldcontain">
					<span id="indicatorColors-label" class="property-label"><g:message code="indicator.indicatorColors.label" default="Indicator Colors" /></span>
					
						<g:each in="${indicatorInstance.indicatorColors}" var="i">
						<span class="property-value" aria-labelledby="indicatorColors-label"><g:link controller="indicatorColorCriteriaPerDataLocationType" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorInstance?.indicatorType}">
				<li class="fieldcontain">
					<span id="indicatorType-label" class="property-label"><g:message code="indicator.indicatorType.label" default="Indicator Type" /></span>
					
						<span class="property-value" aria-labelledby="indicatorType-label"><g:fieldValue bean="${indicatorInstance}" field="indicatorType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorInstance?.indicatorValues}">
				<li class="fieldcontain">
					<span id="indicatorValues-label" class="property-label"><g:message code="indicator.indicatorValues.label" default="Indicator Values" /></span>
					
						<g:each in="${indicatorInstance.indicatorValues}" var="i">
						<span class="property-value" aria-labelledby="indicatorValues-label"><g:link controller="indicatorValue" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorInstance?.internediateValuess}">
				<li class="fieldcontain">
					<span id="internediateValuess-label" class="property-label"><g:message code="indicator.internediateValuess.label" default="Internediate Valuess" /></span>
					
						<g:each in="${indicatorInstance.internediateValuess}" var="i">
						<span class="property-value" aria-labelledby="internediateValuess-label"><g:link controller="intermediateVariable" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorInstance?.queryParserHelpers}">
				<li class="fieldcontain">
					<span id="queryParserHelpers-label" class="property-label"><g:message code="indicator.queryParserHelpers.label" default="Query Parser Helpers" /></span>
					
						<g:each in="${indicatorInstance.queryParserHelpers}" var="q">
						<span class="property-value" aria-labelledby="queryParserHelpers-label"><g:link controller="queryParserHelper" action="show" id="${q.id}">${q?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${indicatorInstance?.id}" />
					<g:link class="edit" action="edit" id="${indicatorInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
