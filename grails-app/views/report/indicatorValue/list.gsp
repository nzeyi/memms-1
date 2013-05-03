
<%@ page import="org.chai.memms.report.IndicatorValue" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'indicatorValue.label', default: 'IndicatorValue')}" />
		<title><g:message code="indicatorValue.list"/></title>
	</head>
	<body>
		
		<div class="nav" role="navigation">
		<table><tr><td><div class="nav" role="navigation">
			<ul>
				<g:reportMenus/>
				
			</ul>
		</div></td><td>
				
				<a href="${createLink(controller:'indicatorValue', action:'create')}" class="next medium left push-r">
			<g:message code="indicatorValue.new" />
		</a>
			</td></tr></table>
			
		</div>
		<div id="list-indicatorValue" class="content scaffold-list" role="main">
			<span class="don_titles"> <g:message code="indicatorValue.list"/></span>
			
			<table  class="items spaced">
				<thead>
					<tr>
					<th>&nbsp;</th>
						<g:sortableColumn property="code" title="${message(code: 'indicatorValue.code.label', default: 'Code')}" />
					
						<g:sortableColumn property="value" title="${message(code: 'indicatorValue.value.label', default: 'Value')}" />
					
						<th><g:message code="indicatorValue.indicatorType.label" default="Indicator Type" /></th>
					
						<th><g:message code="indicatorValue.report.label" default="Report" /></th>
					
						<g:sortableColumn property="time" title="${message(code: 'indicatorValue.time.label', default: 'Time')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${indicatorValueInstanceList}" status="i" var="indicatorValueInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<td>
				<ul class="horizontal">
					<li>
						<a href="${createLink(controller:'indicatorValue', action:'edit', params:[id: indicatorValueInstance.id])}" class="edit-button">
							<g:message code="default.link.edit.label" />
						</a>
					</li>
					<!--
					<li>
						<a href="${createLink(controller:'indicatorValue', action:'delete', params:[id: indicatorValueInstance.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button"><g:message code="default.link.delete.label" /></a>
					</li>-->
					
				</ul>
			</td>
						<td><g:link action="show" id="${indicatorValueInstance.id}">${fieldValue(bean: indicatorValueInstance, field: "code")}</g:link></td>
					
						<td>${fieldValue(bean: indicatorValueInstance, field: "value")}</td>
					
						<td>${fieldValue(bean: indicatorValueInstance, field: "indicatorType")}</td>
					
						<td>${fieldValue(bean: indicatorValueInstance, field: "report")}</td>
					
						<td><g:formatDate date="${indicatorValueInstance.time}" /></td>
					
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
