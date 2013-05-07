
<%@ page import="org.chai.memms.report.IntermediateVariable" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'intermediateVariable.label', default: 'IntermediateVariable')}" />
		<title><g:message code="intermediateVariable.list"/></title>
	</head>
	<body><div class="nav" role="navigation">
		<table><tr><td><div class="nav" role="navigation">
			<ul>
				<g:reportMenus/>
				
			</ul>
		</div></td><td>
				
				<a href="${createLink(controller:'intermediateVariable', action:'create')}" class="next medium left push-r">
			<g:message code="intermediateVariable.new"  />
		</a></td></tr></table>
			
		</div>
		<div id="list-intermediateVariable" class="content scaffold-list" role="main">
			<span class="don_titles"> <g:message code="intermediateVariable.list"/></span>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table  class="items spaced">
				<thead>
					<tr>
					<th>&nbsp;</th>
						<g:sortableColumn property="code" title="${message(code: 'intermediateVariable.code.label', default: 'Code')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'intermediateVariable.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="formula" title="${message(code: 'intermediateVariable.formula.label', default: 'Formula')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${intermediateVariableInstanceList}" status="i" var="intermediateVariableInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<td>
				<ul class="horizontal">
					<li>
						<a href="${createLink(controller:'intermediateVariable', action:'edit', params:[id: intermediateVariableInstance.id])}" class="edit-button">
							<g:message code="default.link.edit.label" />
						</a>
					</li>
					<!--
					<li>
						<a href="${createLink(controller:'intermediateVariable', action:'delete', params:[id: intermediateVariableInstance.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button"><g:message code="default.link.delete.label" /></a>
					</li>-->
					
				</ul>
			</td>
						<td><g:link action="show" id="${intermediateVariableInstance.id}">${fieldValue(bean: intermediateVariableInstance, field: "code")}</g:link></td>
					
						<td>${fieldValue(bean: intermediateVariableInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: intermediateVariableInstance, field: "formula")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${intermediateVariableInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
