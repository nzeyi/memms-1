
<%@ page import="org.chai.memms.report.IntermediateValues" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'intermediateValues.label', default: 'IntermediateValues')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-intermediateValues" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-intermediateValues" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="code" title="${message(code: 'intermediateValues.code.label', default: 'Code')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'intermediateValues.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="formula" title="${message(code: 'intermediateValues.formula.label', default: 'Formula')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${intermediateValuesInstanceList}" status="i" var="intermediateValuesInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${intermediateValuesInstance.id}">${fieldValue(bean: intermediateValuesInstance, field: "code")}</g:link></td>
					
						<td>${fieldValue(bean: intermediateValuesInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: intermediateValuesInstance, field: "formula")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${intermediateValuesInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
