
<%@ page import="org.chai.memms.report.IndicatorValue" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'indicatorValue.label', default: 'IndicatorValue')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-indicatorValue" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-indicatorValue" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="code" title="${message(code: 'indicatorValue.code.label', default: 'Code')}" />
					
						<g:sortableColumn property="value" title="${message(code: 'indicatorValue.value.label', default: 'Value')}" />
					
						<th><g:message code="indicatorValue.dataLocationReport.label" default="Data Location Report" /></th>
					
						<g:sortableColumn property="generatedAt" title="${message(code: 'indicatorValue.generatedAt.label', default: 'Generated At')}" />
					
						<th><g:message code="indicatorValue.indicatorType.label" default="Indicator Type" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${indicatorValueInstanceList}" status="i" var="indicatorValueInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${indicatorValueInstance.id}">${fieldValue(bean: indicatorValueInstance, field: "code")}</g:link></td>
					
						<td>${fieldValue(bean: indicatorValueInstance, field: "value")}</td>
					
						<td>${fieldValue(bean: indicatorValueInstance, field: "dataLocationReport")}</td>
					
						<td><g:formatDate date="${indicatorValueInstance.generatedAt}" /></td>
					
						<td>${fieldValue(bean: indicatorValueInstance, field: "indicatorType")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${indicatorValueInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
