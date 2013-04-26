
<%@ page import="org.chai.memms.report.IndicatorType" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'indicatorType.label', default: 'IndicatorType')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-indicatorType" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-indicatorType" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="code" title="${message(code: 'indicatorType.code.label', default: 'Code')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'indicatorType.name.label', default: 'Name')}" />
					
						<th><g:message code="indicatorType.categoryType.label" default="Category Type" /></th>
					
						<g:sortableColumn property="formula" title="${message(code: 'indicatorType.formula.label', default: 'Formula')}" />
					
						<g:sortableColumn property="increasing" title="${message(code: 'indicatorType.increasing.label', default: 'Increasing')}" />
					
						<g:sortableColumn property="maxYellowValue" title="${message(code: 'indicatorType.maxYellowValue.label', default: 'Max Yellow Value')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${indicatorTypeInstanceList}" status="i" var="indicatorTypeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${indicatorTypeInstance.id}">${fieldValue(bean: indicatorTypeInstance, field: "code")}</g:link></td>
					
						<td>${fieldValue(bean: indicatorTypeInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: indicatorTypeInstance, field: "categoryType")}</td>
					
						<td>${fieldValue(bean: indicatorTypeInstance, field: "formula")}</td>
					
						<td><g:formatBoolean boolean="${indicatorTypeInstance.increasing}" /></td>
					
						<td>${fieldValue(bean: indicatorTypeInstance, field: "maxYellowValue")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${indicatorTypeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
