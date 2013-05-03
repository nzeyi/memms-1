
<%@ page import="org.chai.memms.report.IntermediateValues" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'intermediateValues.label', default: 'IntermediateValues')}" />
		<title><g:message code="intermediateValues.list"/></title>
	</head>
	<body><div class="nav" role="navigation">
		<table><tr><td><div class="nav" role="navigation">
			<ul>
				<g:reportMenus/>
				
			</ul>
		</div></td><td>
				
				<a href="${createLink(controller:'intermediateValues', action:'create')}" class="next medium left push-r">
			<g:message code="intermediateValues.new"  />
		</a></td></tr></table>
			
		</div>
		<div id="list-intermediateValues" class="content scaffold-list" role="main">
			<span class="don_titles"> <g:message code="intermediateValues.list"/></span>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table  class="items spaced">
				<thead>
					<tr>
					<th>&nbsp;</th>
						<g:sortableColumn property="code" title="${message(code: 'intermediateValues.code.label', default: 'Code')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'intermediateValues.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="formula" title="${message(code: 'intermediateValues.formula.label', default: 'Formula')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${intermediateValuesInstanceList}" status="i" var="intermediateValuesInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<td>
				<ul class="horizontal">
					<li>
						<a href="${createLink(controller:'intermediateValues', action:'edit', params:[id: intermediateValuesInstance.id])}" class="edit-button">
							<g:message code="default.link.edit.label" />
						</a>
					</li>
					<!--
					<li>
						<a href="${createLink(controller:'intermediateValues', action:'delete', params:[id: intermediateValuesInstance.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button"><g:message code="default.link.delete.label" /></a>
					</li>-->
					
				</ul>
			</td>
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
