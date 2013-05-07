
<%@ page import="org.chai.memms.report.DataLocationReport" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'dataLocationReport.label', default: 'DataLocationReport')}" />
		<title><g:message code="dataLocationReport.list"/></title>
	</head>
	<body>
		
		<div class="nav" role="navigation">
		<table><tr><td><div class="nav" role="navigation">
			<ul>
				<g:reportMenus/>
				
			</ul>
		</div></td><td>
				
			<a href="${createLink(controller:'dataLocationReport', action:'create')}" class="next medium left push-r">
			<g:message code="default.new.label" args="[entityName]" />
		</a>
				
			</td></tr></table>
			
		</div>
		<div id="list-dataLocationReport" class="content scaffold-list" role="main">
			<span class="don_titles"> <g:message code="dataLocationReport.list"/></span>
			
			<table  class="items spaced">
				<thead>
					<tr>
					<th>&nbsp;</th>
						<g:sortableColumn property="code" title="${message(code: 'dataLocationReport.code.label', default: 'Code')}" />
					
						<th><g:message code="dataLocationReport.facility.label" default="Facility" /></th>
					
						<g:sortableColumn property="time" title="${message(code: 'dataLocationReport.time.label', default: 'Time')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${dataLocationReportInstanceList}" status="i" var="dataLocationReportInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					<td>
				<ul class="horizontal">
					<li>
						<a href="${createLink(controller:'dataLocationReport', action:'edit', params:[id: dataLocationReportInstance.id])}" class="edit-button">
							<g:message code="default.link.edit.label" />
						</a>
					</li>
					<!--
					<li>
						<a href="${createLink(controller:'dataLocationReport', action:'delete', params:[id: dataLocationReportInstance.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button"><g:message code="default.link.delete.label" /></a>
					</li>-->
					
				</ul>
			</td>
						<td><g:link action="show" id="${dataLocationReportInstance.id}">${fieldValue(bean: dataLocationReportInstance, field: "code")}</g:link></td>
					
						<td>${fieldValue(bean: dataLocationReportInstance, field: "facility")}</td>
					
						<td><g:formatDate date="${dataLocationReportInstance.time}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${dataLocationReportInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
