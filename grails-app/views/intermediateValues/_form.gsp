<%@ page import="org.chai.memms.report.IntermediateValues" %>



<div class="fieldcontain ${hasErrors(bean: intermediateValuesInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="intermediateValues.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="code" required="" value="${intermediateValuesInstance?.code}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: intermediateValuesInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="intermediateValues.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="100" pattern="${intermediateValuesInstance.constraints.name.matches}" required="" value="${intermediateValuesInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: intermediateValuesInstance, field: 'formula', 'error')} required">
	<label for="formula">
		<g:message code="intermediateValues.formula.label" default="Formula" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="formula" required="" value="${intermediateValuesInstance?.formula}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: intermediateValuesInstance, field: 'indicatorTypes', 'error')} ">
	<label for="indicatorTypes">
		<g:message code="intermediateValues.indicatorTypes.label" default="Indicator Types" />
		
	</label>
	
</div>

