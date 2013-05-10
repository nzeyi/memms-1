
<%@ page import="org.chai.memms.report.IndicatorColorCriteriaPerDataLocationType" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'indicatorColorCriteriaPerDataLocationType.label', default: 'IndicatorColorCriteriaPerDataLocationType')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
		<table><tr><td><div class="nav" role="navigation">
			<ul>
				<g:reportMenus/>
				
			</ul>
		</div></td><td>
				
			<a href="${createLink(controller:'indicatorColorCriteriaPerDataLocationType', action:'create')}" class="next medium left push-r">
			<g:message code="indicatorColorCriteriaPerDataLocationType.new" />
		</a>
			</td></tr></table>
			
		</div>
		<div id="list-categoryType" class="content scaffold-list" role="main">
			<span class="don_titles"> <g:message code="indicatorColorCriteriaPerDataLocationType.list"/></span>
			
			<table  class="items spaced">
				<thead>
					<tr>
					<th>&nbsp;</th>
						<g:sortableColumn property="code" title="${message(code: 'indicatorColorCriteriaPerDataLocationType.code.label', default: 'Code')}" />
					
						<th><g:message code="indicatorColorCriteriaPerDataLocationType.dataLocationType.label" default="Data Location Type" /></th>
					
						<th><g:message code="indicatorColorCriteriaPerDataLocationType.indicator.label" default="Indicator" /></th>
					
						<g:sortableColumn property="isIncreasing" title="${message(code: 'indicatorColorCriteriaPerDataLocationType.isIncreasing.label', default: 'Is Increasing')}" />
					
						<g:sortableColumn property="maxYellow" title="${message(code: 'indicatorColorCriteriaPerDataLocationType.maxYellow.label', default: 'Max Yellow')}" />
					
						<g:sortableColumn property="minYellow" title="${message(code: 'indicatorColorCriteriaPerDataLocationType.minYellow.label', default: 'Min Yellow')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${indicatorColorCriteriaPerDataLocationTypeInstanceList}" status="i" var="indicatorColorCriteriaPerDataLocationTypeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<td>
				<ul class="horizontal">
					<li>
						<a href="${createLink(controller:'indicatorColorCriteriaPerDataLocationType', action:'edit', params:[id: indicatorColorCriteriaPerDataLocationTypeInstance.id])}" class="edit-button">
							<g:message code="default.link.edit.label" />
						</a>
					</li>
					<!--
					<li>
						<a href="${createLink(controller:'indicatorColorCriteriaPerDataLocationType', action:'delete', params:[id: indicatorColorCriteriaPerDataLocationTypeInstance.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button"><g:message code="default.link.delete.label" /></a>
					</li>-->
					
				</ul>
			</td>
						<td><g:link action="show" id="${indicatorColorCriteriaPerDataLocationTypeInstance.id}">${fieldValue(bean: indicatorColorCriteriaPerDataLocationTypeInstance, field: "code")}</g:link></td>
					
						<td>${fieldValue(bean: indicatorColorCriteriaPerDataLocationTypeInstance, field: "dataLocationType")}</td>
					
						<td>${fieldValue(bean: indicatorColorCriteriaPerDataLocationTypeInstance, field: "indicator")}</td>
					
						<td><g:formatBoolean boolean="${indicatorColorCriteriaPerDataLocationTypeInstance.isIncreasing}" /></td>
					
						<td>${fieldValue(bean: indicatorColorCriteriaPerDataLocationTypeInstance, field: "maxYellow")}</td>
					
						<td>${fieldValue(bean: indicatorColorCriteriaPerDataLocationTypeInstance, field: "minYellow")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${indicatorColorCriteriaPerDataLocationTypeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
