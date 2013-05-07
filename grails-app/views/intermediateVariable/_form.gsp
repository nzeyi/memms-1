<%@ page import="org.chai.memms.report.IntermediateVariable" %>

<div  class="entity-form-container togglable">
	<div class="heading1-bar">
<div class="nav" role="navigation">
			<ul>
				<g:reportMenus/>
				
			</ul>
		</div>
		<div class="main">
<table><tr><td>


<div class="fieldcontain ${hasErrors(bean: intermediateVariableInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="intermediateVariable.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	
</div></td><td><g:textField name="code" required="" value="${intermediateVariableInstance?.code}"/></td></tr>

<tr><td><div class="fieldcontain ${hasErrors(bean: intermediateVariableInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="intermediateVariable.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	
</div></td><td><g:textField name="name" maxlength="100" required="" value="${intermediateVariableInstance?.name}"/></td></tr>

<tr><td><div class="fieldcontain ${hasErrors(bean: intermediateVariableInstance, field: 'formula', 'error')} required">
	
		<g:message code="intermediateVariable.formula.label" default="Formula" />
		<span class="required-indicator">*</span>
	
	
</div></td><td><g:textArea name="formula" cols="40" rows="5" maxlength="500" required="" value="${intermediateVariableInstance?.formula}"/></td></tr></table>


	
</div></div>

