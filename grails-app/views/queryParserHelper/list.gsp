
<%@ page import="org.chai.memms.report.QueryParserHelper" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'queryParserHelper.label', default: 'QueryParserHelper')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
		<table><tr><td><div class="nav" role="navigation">
			<ul>
				<g:reportMenus/>
				
			</ul>
		</div></td><td>
				
			<a href="${createLink(controller:'queryParserHelper', action:'create')}" class="next medium left push-r">
			<g:message code="indicator.new" />
		</a>
			</td></tr></table>
			
		</div>
		<div id="list-queryParserHelper" class="content scaffold-list" role="main">
			<span class="don_titles"> <g:message code="queryParserHelper.list"/></span>
			<table  class="items spaced">
				<thead>
					<tr>
					<th>&nbsp;</th>
						<g:sortableColumn property="executableScript" title="${message(code: 'queryParserHelper.executableScript.label', default: 'Executable Script')}" />
					
						<g:sortableColumn property="classDomaine" title="${message(code: 'queryParserHelper.classDomaine.label', default: 'Class Domaine')}" />
					
						<g:sortableColumn property="followOperand" title="${message(code: 'queryParserHelper.followOperand.label', default: 'Follow Operand')}" />
					
						<th><g:message code="queryParserHelper.indicator.label" default="Indicator" /></th>
					
						<g:sortableColumn property="indicatorType" title="${message(code: 'queryParserHelper.indicatorType.label', default: 'Indicator Type')}" />
					
						<g:sortableColumn property="isDenominator" title="${message(code: 'queryParserHelper.isDenominator.label', default: 'Is Denominator')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${queryParserHelperInstanceList}" status="i" var="queryParserHelperInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<td>
				<ul class="horizontal">
					<li>
						<a href="${createLink(controller:'queryParserHelper', action:'edit', params:[id: queryParserHelperInstance.id])}" class="edit-button">
							<g:message code="default.link.edit.label" />
						</a>
					</li>
					<!--
					<li>
						<a href="${createLink(controller:'queryParserHelper', action:'delete', params:[id: queryParserHelperInstance.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button"><g:message code="default.link.delete.label" /></a>
					</li>-->
					
				</ul>
			</td>
				
						<td><g:link action="show" id="${queryParserHelperInstance.id}">${fieldValue(bean: queryParserHelperInstance, field: "executableScript")}</g:link></td>
					
						<td>${fieldValue(bean: queryParserHelperInstance, field: "classDomaine")}</td>
					
						<td>${fieldValue(bean: queryParserHelperInstance, field: "followOperand")}</td>
					
						<td>${fieldValue(bean: queryParserHelperInstance, field: "indicator")}</td>
					
						<td>${fieldValue(bean: queryParserHelperInstance, field: "indicatorType")}</td>
					
						<td><g:formatBoolean boolean="${queryParserHelperInstance.isDenominator}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${queryParserHelperInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
