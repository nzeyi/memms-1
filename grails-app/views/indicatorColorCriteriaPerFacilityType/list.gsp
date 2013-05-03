
<%@ page import="org.chai.memms.report.IndicatorColorCriteriaPerFacilityType" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'indicatorColorCriteriaPerFacilityType.label', default: 'IndicatorColorCriteriaPerFacilityType')}" />
		<title><g:message code="indicatorColorCriteriaPerFacilityType.list"/></title>
	</head>
	<body>
		
		<div class="nav" role="navigation">
		<table><tr><td><div class="nav" role="navigation">
			<ul>
				<g:reportMenus/>
				
			</ul>
		</div></td><td>
				
				<a href="${createLink(controller:'indicatorColorCriteriaPerFacilityType', action:'create')}" class="next medium left push-r">
			<g:message code="indicatorColorCriteriaPerFacilityType.new" />
		</a>
			</td></tr></table>
			
		</div>
		<div id="list-indicatorColorCriteriaPerFacilityType" class="content scaffold-list" role="main">
			<span class="don_titles"> <g:message code="indicatorColorCriteriaPerFacilityType.list"/></span>
			
			<table  class="items spaced">
				<thead>
					<tr>
					<th>&nbsp;</th>
						<g:sortableColumn property="code" title="${message(code: 'indicatorColorCriteriaPerFacilityType.code.label', default: 'Code')}" />
					
						<th><g:message code="indicatorColorCriteriaPerFacilityType.facilityType.label" default="Facility Type" /></th>
					
						<g:sortableColumn property="increasing" title="${message(code: 'indicatorColorCriteriaPerFacilityType.increasing.label', default: 'Increasing')}" />
					
						<th><g:message code="indicatorColorCriteriaPerFacilityType.indicatorType.label" default="Indicator Type" /></th>
					
						<g:sortableColumn property="maxYellow" title="${message(code: 'indicatorColorCriteriaPerFacilityType.maxYellow.label', default: 'Max Yellow')}" />
					
						<g:sortableColumn property="minYellow" title="${message(code: 'indicatorColorCriteriaPerFacilityType.minYellow.label', default: 'Min Yellow')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${indicatorColorCriteriaPerFacilityTypeInstanceList}" status="i" var="indicatorColorCriteriaPerFacilityTypeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<td>
				<ul class="horizontal">
					<li>
						<a href="${createLink(controller:'indicatorColorCriteriaPerFacilityType', action:'edit', params:[id: indicatorColorCriteriaPerFacilityTypeInstance.id])}" class="edit-button">
							<g:message code="default.link.edit.label" />
						</a>
					</li>
					<!--
					<li>
						<a href="${createLink(controller:'indicatorColorCriteriaPerFacilityType', action:'delete', params:[id: indicatorColorCriteriaPerFacilityTypeInstance.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button"><g:message code="default.link.delete.label" /></a>
					</li>-->
					
				</ul>
			</td>
						<td><g:link action="show" id="${indicatorColorCriteriaPerFacilityTypeInstance.id}">${fieldValue(bean: indicatorColorCriteriaPerFacilityTypeInstance, field: "code")}</g:link></td>
					
						<td>${fieldValue(bean: indicatorColorCriteriaPerFacilityTypeInstance, field: "facilityType")}</td>
					
						<td><g:formatBoolean boolean="${indicatorColorCriteriaPerFacilityTypeInstance.increasing}" /></td>
					
						<td>${fieldValue(bean: indicatorColorCriteriaPerFacilityTypeInstance, field: "indicatorType")}</td>
					
						<td>${fieldValue(bean: indicatorColorCriteriaPerFacilityTypeInstance, field: "maxYellow")}</td>
					
						<td>${fieldValue(bean: indicatorColorCriteriaPerFacilityTypeInstance, field: "minYellow")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${indicatorColorCriteriaPerFacilityTypeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
