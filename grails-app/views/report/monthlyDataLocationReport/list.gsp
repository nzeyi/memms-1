
<%@ page import="org.chai.memms.report.MonthlyFacilityReport" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'monthlyFacilityReport.label', default: 'MonthlyFacilityReport')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-monthlyFacilityReport" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-monthlyFacilityReport" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="code" title="${message(code: 'monthlyFacilityReport.code.label', default: 'Code')}" />
					
						<g:sortableColumn property="month" title="${message(code: 'monthlyFacilityReport.month.label', default: 'Month')}" />
					
						<th><g:message code="monthlyFacilityReport.report.label" default="Report" /></th>
					
						<g:sortableColumn property="time" title="${message(code: 'monthlyFacilityReport.time.label', default: 'Time')}" />
					
						<g:sortableColumn property="year" title="${message(code: 'monthlyFacilityReport.year.label', default: 'Year')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${monthlyFacilityReportInstanceList}" status="i" var="monthlyFacilityReportInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${monthlyFacilityReportInstance.id}">${fieldValue(bean: monthlyFacilityReportInstance, field: "code")}</g:link></td>
					
						<td>${fieldValue(bean: monthlyFacilityReportInstance, field: "month")}</td>
					
						<td>${fieldValue(bean: monthlyFacilityReportInstance, field: "report")}</td>
					
						<td><g:formatDate date="${monthlyFacilityReportInstance.time}" /></td>
					
						<td>${fieldValue(bean: monthlyFacilityReportInstance, field: "year")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${monthlyFacilityReportInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
