
<%@ page import="org.chai.memms.report.utils.QueryParserHelper" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'queryParserHelper.label', default: 'QueryParserHelper')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-queryParserHelper" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-queryParserHelper" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="excutableScript" title="${message(code: 'queryParserHelper.excutableScript.label', default: 'Excutable Script')}" />
					
						<g:sortableColumn property="classDomaine" title="${message(code: 'queryParserHelper.classDomaine.label', default: 'Class Domaine')}" />
					
						<g:sortableColumn property="followOperand" title="${message(code: 'queryParserHelper.followOperand.label', default: 'Follow Operand')}" />
					
						<g:sortableColumn property="indType" title="${message(code: 'queryParserHelper.indType.label', default: 'Ind Type')}" />
					
						<th><g:message code="queryParserHelper.indicatorType.label" default="Indicator Type" /></th>
					
						<g:sortableColumn property="isDenominator" title="${message(code: 'queryParserHelper.isDenominator.label', default: 'Is Denominator')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${queryParserHelperInstanceList}" status="i" var="queryParserHelperInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${queryParserHelperInstance.id}">${fieldValue(bean: queryParserHelperInstance, field: "excutableScript")}</g:link></td>
					
						<td>${fieldValue(bean: queryParserHelperInstance, field: "classDomaine")}</td>
					
						<td>${fieldValue(bean: queryParserHelperInstance, field: "followOperand")}</td>
					
						<td>${fieldValue(bean: queryParserHelperInstance, field: "indType")}</td>
					
						<td>${fieldValue(bean: queryParserHelperInstance, field: "indicatorType")}</td>
					
						<td><g:formatBoolean boolean="${queryParserHelperInstance.isDenominator}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${queryParserHelperInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
