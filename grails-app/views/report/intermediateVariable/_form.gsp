<%@ page import="org.chai.memms.report.IntermediateValues" %>

<div  class="entity-form-container togglable">
	<div class="heading1-bar">
<div class="nav" role="navigation">
			<ul>
				<g:reportMenus/>
				
			</ul>
		</div>
		<div class="main">
<table><tr><td>


<div class="fieldcontain ${hasErrors(bean: intermediateValuesInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="intermediateValues.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	
</div></td><td><g:textField name="code" required="" value="${intermediateValuesInstance?.code}"/></td></tr>

<tr><td><div class="fieldcontain ${hasErrors(bean: intermediateValuesInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="intermediateValues.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	
</div></td><td><g:textField name="name" maxlength="100" required="" value="${intermediateValuesInstance?.name}"/></td></tr>

<tr><td><div class="fieldcontain ${hasErrors(bean: intermediateValuesInstance, field: 'formula', 'error')} required">
	
		<g:message code="intermediateValues.formula.label" default="Formula" />
		<span class="required-indicator">*</span>
	
	
</div></td><td><g:textArea name="formula" cols="40" rows="5" maxlength="500" required="" value="${intermediateValuesInstance?.formula}"/></td></tr></table>


	
</div></div>

