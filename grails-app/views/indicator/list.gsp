
<%@ page import="org.chai.memms.report.Indicator" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'indicator.label', default: 'Indicator')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
	<div class="nav" role="navigation">
		<table><tr><td><div class="nav" role="navigation">
			<ul>
				<g:reportMenus/>
				
			</ul>
		</div></td><td>
				
			<a href="${createLink(controller:'indicator', action:'create')}" class="next medium left push-r">
			<g:message code="indicator.new" />
		</a>
			</td></tr></table>
			
		</div>
		<div id="list-indicator" class="content scaffold-list" role="main">
			<span class="don_titles"> <g:message code="indicator.list"/></span>
			<table  class="items spaced">
				<thead>
					<tr>
					<th>&nbsp;</th>
						<g:sortableColumn property="code" title="${message(code: 'indicator.code.label', default: 'Code')}" />
					
						<g:sortableColumn property="formula" title="${message(code: 'indicator.formula.label', default: 'Formula')}" />
					
						<g:sortableColumn property="indicatorName_en" title="${message(code: 'indicator.indicatorName_en.label', default: 'Indicator Nameen')}" />
					
						<g:sortableColumn property="indicatorName_fr" title="${message(code: 'indicator.indicatorName_fr.label', default: 'Indicator Namefr')}" />
					
						<th><g:message code="indicator.categoryType.label" default="Category Type" /></th>
					
						<g:sortableColumn property="indicatorType" title="${message(code: 'indicator.indicatorType.label', default: 'Indicator Type')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${indicatorInstanceList}" status="i" var="indicatorInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<td>
				<ul class="horizontal">
					<li>
						<a href="${createLink(controller:'indicator', action:'edit', params:[id: indicatorInstance.id])}" class="edit-button">
							<g:message code="default.link.edit.label" />
						</a>
					</li>
					<!--
					<li>
						<a href="${createLink(controller:'indicator', action:'delete', params:[id: indicatorInstance.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button"><g:message code="default.link.delete.label" /></a>
					</li>-->
					
				</ul>
			</td>
					
						<td><g:link action="show" id="${indicatorInstance.id}">${fieldValue(bean: indicatorInstance, field: "code")}</g:link></td>
					
						<td>${fieldValue(bean: indicatorInstance, field: "formula")}</td>
					
						<td>${fieldValue(bean: indicatorInstance, field: "indicatorName_en")}</td>
					
						<td>${fieldValue(bean: indicatorInstance, field: "indicatorName_fr")}</td>
					
						<td>${fieldValue(bean: indicatorInstance, field: "categoryType")}</td>
					
						<td>${fieldValue(bean: indicatorInstance, field: "indicatorType")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${indicatorInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
