<%@ page import="org.chai.memms.report.IntermediateVariable" %>



<div class="fieldcontain ${hasErrors(bean: intermediateVariableInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="intermediateVariable.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="code" required="" value="${intermediateVariableInstance?.code}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: intermediateVariableInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="intermediateVariable.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="100" required="" value="${intermediateVariableInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: intermediateVariableInstance, field: 'executableScript', 'error')} required">
	<label for="executableScript">
		<g:message code="intermediateVariable.executableScript.label" default="Executable Script" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="executableScript" cols="40" rows="5" maxlength="500" required="" value="${intermediateVariableInstance?.executableScript}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: intermediateVariableInstance, field: 'intermediateVariables', 'error')} ">
	<label for="intermediateVariables">
		<g:message code="intermediateVariable.intermediateVariables.label" default="Intermediate Variables" />
		
	</label>
	<g:select name="intermediateVariables" from="${org.chai.memms.report.IntermediateVariable.list()}" multiple="multiple" optionKey="id" size="5" value="${intermediateVariableInstance?.intermediateVariables*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: intermediateVariableInstance, field: 'executionResult', 'error')} required">
	<label for="executionResult">
		<g:message code="intermediateVariable.executionResult.label" default="Execution Result" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="executionResult" value="${fieldValue(bean: intermediateVariableInstance, field: 'executionResult')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: intermediateVariableInstance, field: 'indicators', 'error')} ">
	<label for="indicators">
		<g:message code="intermediateVariable.indicators.label" default="Indicators" />
		
	</label>
	
</div>

