
<%@ page import="org.chai.memms.report.IndicatorType" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'indicatorType.label', default: 'IndicatorType')}" />
		<title><g:message code="indicatorType.list"/></title>
	</head>
	<body>
		
		<div class="nav" role="navigation">
		<table><tr><td><div class="nav" role="navigation">
			<ul>
				<g:reportMenus/>
				
			</ul>
		</div></td><td>
				
				<a href="${createLink(controller:'indicatorType', action:'create')}" class="next medium left push-r">
			<g:message code="indicatorType.new" />
		</a>
			</td></tr></table>
			
		</div>
		<div id="list-indicatorType" class="content scaffold-list" role="main">
			<span class="don_titles"> <g:message code="indicatorType.list"/></span>
			
			<table class="items spaced">
				<thead>
					<tr>
					<th>&nbsp;</th>
						<g:sortableColumn property="code" title="${message(code: 'indicatorType.code.label', default: 'Code')}" />
					
						<g:sortableColumn property="name" title="${message(code: 'indicatorType.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="formula" title="${message(code: 'indicatorType.formula.label', default: 'Formula')}" />
					
						<g:sortableColumn property="finder" title="${message(code: 'indicatorType.finder.label', default: 'Finder')}" />
					
						<th><g:message code="indicatorType.categoryType.label" default="Category Type" /></th>
					
						<g:sortableColumn property="increasing" title="${message(code: 'indicatorType.increasing.label', default: 'Increasing')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${indicatorTypeInstanceList}" status="i" var="indicatorTypeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<td>
				<ul class="horizontal">
					<li>
						<a href="${createLink(controller:'indicatorType', action:'edit', params:[id: indicatorTypeInstance.id])}" class="edit-button">
							<g:message code="default.link.edit.label" />
						</a>
					</li>
					<!--
					<li>
						<a href="${createLink(controller:'indicatorType', action:'delete', params:[id: indicatorTypeInstance.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button"><g:message code="default.link.delete.label" /></a>
					</li>-->
					
				</ul>
			</td>
						<td><g:link action="show" id="${indicatorTypeInstance.id}">${fieldValue(bean: indicatorTypeInstance, field: "code")}</g:link></td>
					
						<td>${fieldValue(bean: indicatorTypeInstance, field: "name")}</td>
					
						<td>${fieldValue(bean: indicatorTypeInstance, field: "formula")}</td>
					
						<td>${fieldValue(bean: indicatorTypeInstance, field: "finder")}</td>
					
						<td>${fieldValue(bean: indicatorTypeInstance, field: "categoryType")}</td>
					
						<td><g:formatBoolean boolean="${indicatorTypeInstance.increasing}" /></td>
					
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
