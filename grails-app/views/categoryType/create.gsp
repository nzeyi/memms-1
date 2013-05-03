<%@ page import="org.chai.memms.report.CategoryType" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'categoryType.label', default: 'CategoryType')}" />
		<title><g:message code="categoryType.new" /></title>
	</head>
	<body>
		
		<div id="create-categoryType" class="content scaffold-create" role="main">
			<span class="don_titles"> <g:message code="categoryType.new"/></span>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${categoryTypeInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${categoryTypeInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save" class="simple-list">
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
