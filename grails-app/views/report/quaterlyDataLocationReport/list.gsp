
<%@ page import="org.chai.memms.report.QuaterlyFacilityReport" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'quaterlyFacilityReport.label', default: 'QuaterlyFacilityReport')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-quaterlyFacilityReport" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-quaterlyFacilityReport" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="code" title="${message(code: 'quaterlyFacilityReport.code.label', default: 'Code')}" />
					
						<g:sortableColumn property="quarter" title="${message(code: 'quaterlyFacilityReport.quarter.label', default: 'Quarter')}" />
					
						<g:sortableColumn property="month" title="${message(code: 'quaterlyFacilityReport.month.label', default: 'Month')}" />
					
						<th><g:message code="quaterlyFacilityReport.report.label" default="Report" /></th>
					
						<g:sortableColumn property="time" title="${message(code: 'quaterlyFacilityReport.time.label', default: 'Time')}" />
					
						<g:sortableColumn property="year" title="${message(code: 'quaterlyFacilityReport.year.label', default: 'Year')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${quaterlyFacilityReportInstanceList}" status="i" var="quaterlyFacilityReportInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${quaterlyFacilityReportInstance.id}">${fieldValue(bean: quaterlyFacilityReportInstance, field: "code")}</g:link></td>
					
						<td>${fieldValue(bean: quaterlyFacilityReportInstance, field: "quarter")}</td>
					
						<td>${fieldValue(bean: quaterlyFacilityReportInstance, field: "month")}</td>
					
						<td>${fieldValue(bean: quaterlyFacilityReportInstance, field: "report")}</td>
					
						<td><g:formatDate date="${quaterlyFacilityReportInstance.time}" /></td>
					
						<td>${fieldValue(bean: quaterlyFacilityReportInstance, field: "year")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${quaterlyFacilityReportInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
