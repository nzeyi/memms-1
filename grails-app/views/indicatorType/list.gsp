
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
					
						<g:sortableColumn property="code" title="${message(code: 'indicatorType.code.label', default: 'Code')}" />
					
						<g:sortableColumn property="formula" title="${message(code: 'indicatorType.formula.label', default: 'Formula')}" />
					
						
						<g:sortableColumn property="indicatorName_en" title="${message(code: 'indicatorType.indicatorName_en.label', default: 'Indicator Nameen')}" />
					
						<g:sortableColumn property="indicatorName_fr" title="${message(code: 'indicatorType.indicatorName_fr.label', default: 'Indicator Namefr')}" />
					
						<th><g:message code="indicatorType.categoryType.label" default="Category Type" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${indicatorTypeInstanceList}" status="i" var="indicatorTypeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${indicatorTypeInstance.id}">${fieldValue(bean: indicatorTypeInstance, field: "code")}</g:link></td>
					
						<td>${fieldValue(bean: indicatorTypeInstance, field: "formula")}</td>
					
						
					
						<td>${fieldValue(bean: indicatorTypeInstance, field: "indicatorName_en")}</td>
					
						<td>${fieldValue(bean: indicatorTypeInstance, field: "indicatorName_fr")}</td>
					
						<td>${fieldValue(bean: indicatorTypeInstance, field: "categoryType")}</td>
					
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
