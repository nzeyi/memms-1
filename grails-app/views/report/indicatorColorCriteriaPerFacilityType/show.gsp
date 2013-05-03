
<%@ page import="org.chai.memms.report.IndicatorColorCriteriaPerFacilityType" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'indicatorColorCriteriaPerFacilityType.label', default: 'IndicatorColorCriteriaPerFacilityType')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		
		<div class="nav" role="navigation">
			<ul>
				
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-indicatorColorCriteriaPerFacilityType" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list indicatorColorCriteriaPerFacilityType">
			
				<g:if test="${indicatorColorCriteriaPerFacilityTypeInstance?.code}">
				<li class="fieldcontain">
					<span id="code-label" class="property-label"><g:message code="indicatorColorCriteriaPerFacilityType.code.label" default="Code" /></span>
					
						<span class="property-value" aria-labelledby="code-label"><g:fieldValue bean="${indicatorColorCriteriaPerFacilityTypeInstance}" field="code"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorColorCriteriaPerFacilityTypeInstance?.facilityType}">
				<li class="fieldcontain">
					<span id="facilityType-label" class="property-label"><g:message code="indicatorColorCriteriaPerFacilityType.facilityType.label" default="Facility Type" /></span>
					
						<span class="property-value" aria-labelledby="facilityType-label"><g:link controller="dataLocationType" action="show" id="${indicatorColorCriteriaPerFacilityTypeInstance?.facilityType?.id}">${indicatorColorCriteriaPerFacilityTypeInstance?.facilityType?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorColorCriteriaPerFacilityTypeInstance?.increasing}">
				<li class="fieldcontain">
					<span id="increasing-label" class="property-label"><g:message code="indicatorColorCriteriaPerFacilityType.increasing.label" default="Increasing" /></span>
					
						<span class="property-value" aria-labelledby="increasing-label"><g:formatBoolean boolean="${indicatorColorCriteriaPerFacilityTypeInstance?.increasing}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorColorCriteriaPerFacilityTypeInstance?.indicatorType}">
				<li class="fieldcontain">
					<span id="indicatorType-label" class="property-label"><g:message code="indicatorColorCriteriaPerFacilityType.indicatorType.label" default="Indicator Type" /></span>
					
						<span class="property-value" aria-labelledby="indicatorType-label"><g:link controller="indicatorType" action="show" id="${indicatorColorCriteriaPerFacilityTypeInstance?.indicatorType?.id}">${indicatorColorCriteriaPerFacilityTypeInstance?.indicatorType?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorColorCriteriaPerFacilityTypeInstance?.maxYellow}">
				<li class="fieldcontain">
					<span id="maxYellow-label" class="property-label"><g:message code="indicatorColorCriteriaPerFacilityType.maxYellow.label" default="Max Yellow" /></span>
					
						<span class="property-value" aria-labelledby="maxYellow-label"><g:fieldValue bean="${indicatorColorCriteriaPerFacilityTypeInstance}" field="maxYellow"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicatorColorCriteriaPerFacilityTypeInstance?.minYellow}">
				<li class="fieldcontain">
					<span id="minYellow-label" class="property-label"><g:message code="indicatorColorCriteriaPerFacilityType.minYellow.label" default="Min Yellow" /></span>
					
						<span class="property-value" aria-labelledby="minYellow-label"><g:fieldValue bean="${indicatorColorCriteriaPerFacilityTypeInstance}" field="minYellow"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${indicatorColorCriteriaPerFacilityTypeInstance?.id}" />
					<g:link class="edit" action="edit" id="${indicatorColorCriteriaPerFacilityTypeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
