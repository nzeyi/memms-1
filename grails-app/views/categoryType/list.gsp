
<%@ page import="org.chai.memms.report.CategoryType" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'categoryType.label', default: 'CategoryType')}" />
		<title><g:message code="categoryType.list"/></title>
	</head>
	<body>
		
		<div class="nav" role="navigation">
		<table><tr><td><div class="nav" role="navigation">
			<ul>
				<g:reportMenus/>
				
			</ul>
		</div></td><td>
				
			<a href="${createLink(controller:'categoryType', action:'create')}" class="next medium left push-r">
			<g:message code="categoryType.new" />
		</a>
			</td></tr></table>
			
		</div>
		<div id="list-categoryType" class="content scaffold-list" role="main">
			<span class="don_titles"> <g:message code="categoryType.list"/></span>
			
			<table  class="items spaced">
				<thead>
					<tr>
					<th>&nbsp;</th>
						<g:sortableColumn property="code" title="${message(code: 'categoryType.code.label', default: 'Code')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'categoryType.name.label', default: 'Name')}" />
					
						
					
						<g:sortableColumn property="maxYellowValue" title="${message(code: 'categoryType.maxYellowValue.label', default: 'Max Yellow Value')}" />
					
						<g:sortableColumn property="minYellowValue" title="${message(code: 'categoryType.minYellowValue.label', default: 'Min Yellow Value')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${categoryTypeInstanceList}" status="i" var="categoryTypeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<td>
				<ul class="horizontal">
					<li>
						<a href="${createLink(controller:'categoryType', action:'edit', params:[id: categoryTypeInstance.id])}" class="edit-button">
							<g:message code="default.link.edit.label" />
						</a>
					</li>
					<!--
					<li>
						<a href="${createLink(controller:'categoryType', action:'delete', params:[id: categoryTypeInstance.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button"><g:message code="default.link.delete.label" /></a>
					</li>-->
					
				</ul>
			</td>
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
