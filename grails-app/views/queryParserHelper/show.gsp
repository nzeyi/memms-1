
<%@ page import="org.chai.memms.report.utils.QueryParserHelper" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'queryParserHelper.label', default: 'QueryParserHelper')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-queryParserHelper" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-queryParserHelper" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list queryParserHelper">
			
				<g:if test="${queryParserHelperInstance?.excutableScript}">
				<li class="fieldcontain">
					<span id="excutableScript-label" class="property-label"><g:message code="queryParserHelper.excutableScript.label" default="Excutable Script" /></span>
					
						<span class="property-value" aria-labelledby="excutableScript-label"><g:fieldValue bean="${queryParserHelperInstance}" field="excutableScript"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${queryParserHelperInstance?.classDomaine}">
				<li class="fieldcontain">
					<span id="classDomaine-label" class="property-label"><g:message code="queryParserHelper.classDomaine.label" default="Class Domaine" /></span>
					
						<span class="property-value" aria-labelledby="classDomaine-label"><g:fieldValue bean="${queryParserHelperInstance}" field="classDomaine"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${queryParserHelperInstance?.followOperand}">
				<li class="fieldcontain">
					<span id="followOperand-label" class="property-label"><g:message code="queryParserHelper.followOperand.label" default="Follow Operand" /></span>
					
						<span class="property-value" aria-labelledby="followOperand-label"><g:fieldValue bean="${queryParserHelperInstance}" field="followOperand"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${queryParserHelperInstance?.indType}">
				<li class="fieldcontain">
					<span id="indType-label" class="property-label"><g:message code="queryParserHelper.indType.label" default="Ind Type" /></span>
					
						<span class="property-value" aria-labelledby="indType-label"><g:fieldValue bean="${queryParserHelperInstance}" field="indType"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${queryParserHelperInstance?.indicatorType}">
				<li class="fieldcontain">
					<span id="indicatorType-label" class="property-label"><g:message code="queryParserHelper.indicatorType.label" default="Indicator Type" /></span>
					
						<span class="property-value" aria-labelledby="indicatorType-label"><g:link controller="indicatorType" action="show" id="${queryParserHelperInstance?.indicatorType?.id}">${queryParserHelperInstance?.indicatorType?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${queryParserHelperInstance?.isDenominator}">
				<li class="fieldcontain">
					<span id="isDenominator-label" class="property-label"><g:message code="queryParserHelper.isDenominator.label" default="Is Denominator" /></span>
					
						<span class="property-value" aria-labelledby="isDenominator-label"><g:formatBoolean boolean="${queryParserHelperInstance?.isDenominator}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${queryParserHelperInstance?.isDynamicFinder}">
				<li class="fieldcontain">
					<span id="isDynamicFinder-label" class="property-label"><g:message code="queryParserHelper.isDynamicFinder.label" default="Is Dynamic Finder" /></span>
					
						<span class="property-value" aria-labelledby="isDynamicFinder-label"><g:formatBoolean boolean="${queryParserHelperInstance?.isDynamicFinder}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${queryParserHelperInstance?.isIntermidiateVariable}">
				<li class="fieldcontain">
					<span id="isIntermidiateVariable-label" class="property-label"><g:message code="queryParserHelper.isIntermidiateVariable.label" default="Is Intermidiate Variable" /></span>
					
						<span class="property-value" aria-labelledby="isIntermidiateVariable-label"><g:formatBoolean boolean="${queryParserHelperInstance?.isIntermidiateVariable}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${queryParserHelperInstance?.useCountFunction}">
				<li class="fieldcontain">
					<span id="useCountFunction-label" class="property-label"><g:message code="queryParserHelper.useCountFunction.label" default="Use Count Function" /></span>
					
						<span class="property-value" aria-labelledby="useCountFunction-label"><g:formatBoolean boolean="${queryParserHelperInstance?.useCountFunction}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${queryParserHelperInstance?.id}" />
					<g:link class="edit" action="edit" id="${queryParserHelperInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
