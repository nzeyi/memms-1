
<%@ page import="org.chai.memms.report.IndicatorColorCriteriaPerDataLocationType" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'indicatorColorCriteriaPerDataLocationType.label', default: 'IndicatorColorCriteriaPerDataLocationType')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-indicatorColorCriteriaPerDataLocationType" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-indicatorColorCriteriaPerDataLocationType" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list indicatorColorCriteriaPerDataLocationType">
			
				<g:if test="${indicatorColorCriteriaPerDataLocationTypeInstance?.code}">
				<li class="fieldcontain">
					<span id="code-label" class="property-label"><g:message code="indicatorColorCriteriaPerDataLocationType.code.label" default="Code" /></span>
					
						<span class="property-value" aria-labelledby="code-label"><g:fieldValue bean="${indicatorColorCriteriaPerDataLocationTypeInstance}" field="code"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorColorCriteriaPerDataLocationTypeInstance?.dataLocationType}">
				<li class="fieldcontain">
					<span id="dataLocationType-label" class="property-label"><g:message code="indicatorColorCriteriaPerDataLocationType.dataLocationType.label" default="Data Location Type" /></span>
					
						<span class="property-value" aria-labelledby="dataLocationType-label"><g:link controller="dataLocationType" action="show" id="${indicatorColorCriteriaPerDataLocationTypeInstance?.dataLocationType?.id}">${indicatorColorCriteriaPerDataLocationTypeInstance?.dataLocationType?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorColorCriteriaPerDataLocationTypeInstance?.indicator}">
				<li class="fieldcontain">
					<span id="indicator-label" class="property-label"><g:message code="indicatorColorCriteriaPerDataLocationType.indicator.label" default="Indicator" /></span>
					
						<span class="property-value" aria-labelledby="indicator-label"><g:link controller="indicator" action="show" id="${indicatorColorCriteriaPerDataLocationTypeInstance?.indicator?.id}">${indicatorColorCriteriaPerDataLocationTypeInstance?.indicator?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorColorCriteriaPerDataLocationTypeInstance?.isIncreasing}">
				<li class="fieldcontain">
					<span id="isIncreasing-label" class="property-label"><g:message code="indicatorColorCriteriaPerDataLocationType.isIncreasing.label" default="Is Increasing" /></span>
					
						<span class="property-value" aria-labelledby="isIncreasing-label"><g:formatBoolean boolean="${indicatorColorCriteriaPerDataLocationTypeInstance?.isIncreasing}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorColorCriteriaPerDataLocationTypeInstance?.maxYellow}">
				<li class="fieldcontain">
					<span id="maxYellow-label" class="property-label"><g:message code="indicatorColorCriteriaPerDataLocationType.maxYellow.label" default="Max Yellow" /></span>
					
						<span class="property-value" aria-labelledby="maxYellow-label"><g:fieldValue bean="${indicatorColorCriteriaPerDataLocationTypeInstance}" field="maxYellow"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorColorCriteriaPerDataLocationTypeInstance?.minYellow}">
				<li class="fieldcontain">
					<span id="minYellow-label" class="property-label"><g:message code="indicatorColorCriteriaPerDataLocationType.minYellow.label" default="Min Yellow" /></span>
					
						<span class="property-value" aria-labelledby="minYellow-label"><g:fieldValue bean="${indicatorColorCriteriaPerDataLocationTypeInstance}" field="minYellow"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${indicatorColorCriteriaPerDataLocationTypeInstance?.id}" />
					<g:link class="edit" action="edit" id="${indicatorColorCriteriaPerDataLocationTypeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
