
<%@ page import="org.chai.memms.report.CategoryType" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'categoryType.label', default: 'CategoryType')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-categoryType" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-categoryType" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="code" title="${message(code: 'categoryType.code.label', default: 'Code')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'categoryType.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="maxYellowValue" title="${message(code: 'categoryType.maxYellowValue.label', default: 'Max Yellow Value')}" />
					
						<g:sortableColumn property="minYellowValue" title="${message(code: 'categoryType.minYellowValue.label', default: 'Min Yellow Value')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${categoryTypeInstanceList}" status="i" var="categoryTypeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${categoryTypeInstance.id}">${fieldValue(bean: categoryTypeInstance, field: "code")}</g:link></td>
					
						<td>${fieldValue(bean: categoryTypeInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: categoryTypeInstance, field: "maxYellowValue")}</td>
					
						<td>${fieldValue(bean: categoryTypeInstance, field: "minYellowValue")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${categoryTypeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
