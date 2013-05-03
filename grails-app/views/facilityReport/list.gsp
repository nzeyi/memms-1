
<%@ page import="org.chai.memms.report.FacilityReport" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'facilityReport.label', default: 'FacilityReport')}" />
		<title><g:message code="facilityReport.list"/></title>
	</head>
	<body>
		
		<div class="nav" role="navigation">
		<table><tr><td><div class="nav" role="navigation">
			<ul>
				<g:reportMenus/>
				
			</ul>
		</div></td><td>
				
			<a href="${createLink(controller:'facilityReport', action:'create')}" class="next medium left push-r">
			<g:message code="default.new.label" args="[entityName]" />
		</a>
				
			</td></tr></table>
			
		</div>
		<div id="list-facilityReport" class="content scaffold-list" role="main">
			<span class="don_titles"> <g:message code="facilityReport.list"/></span>
			
			<table  class="items spaced">
				<thead>
					<tr>
					<th>&nbsp;</th>
						<g:sortableColumn property="code" title="${message(code: 'facilityReport.code.label', default: 'Code')}" />
					
						<th><g:message code="facilityReport.facility.label" default="Facility" /></th>
					
						<g:sortableColumn property="time" title="${message(code: 'facilityReport.time.label', default: 'Time')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${facilityReportInstanceList}" status="i" var="facilityReportInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<td>
				<ul class="horizontal">
					<li>
						<a href="${createLink(controller:'facilityReport', action:'edit', params:[id: facilityReportInstance.id])}" class="edit-button">
							<g:message code="default.link.edit.label" />
						</a>
					</li>
					<!--
					<li>
						<a href="${createLink(controller:'facilityReport', action:'delete', params:[id: facilityReportInstance.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button"><g:message code="default.link.delete.label" /></a>
					</li>-->
					
				</ul>
			</td>
						<td><g:link action="show" id="${facilityReportInstance.id}">${fieldValue(bean: facilityReportInstance, field: "code")}</g:link></td>
					
						<td>${fieldValue(bean: facilityReportInstance, field: "facility")}</td>
					
						<td><g:formatDate date="${facilityReportInstance.time}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${facilityReportInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
