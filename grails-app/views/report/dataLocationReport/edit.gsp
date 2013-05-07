<%@ page import="org.chai.memms.report.DataLocationReport" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'dataLocationReport.label', default: 'DataLocationReport')}" />
		<title<g:message code="dataLocationReport.edit"/></title>
	</head>
	<body>
		
		<div id="edit-dataLocationReport" class="content scaffold-edit" role="main">
			<span class="don_titles"> <g:message code="dataLocationReport.edit"/></span>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${dataLocationReportInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${dataLocationReportInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" class="simple-list">
				<g:hiddenField name="id" value="${dataLocationReportInstance?.id}" />
				<g:hiddenField name="version" value="${dataLocationReportInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
