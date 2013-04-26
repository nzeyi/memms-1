<%@ page import="org.chai.memms.report.IndicatorType" %>



<div class="fieldcontain ${hasErrors(bean: indicatorTypeInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="indicatorType.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="code" required="" value="${indicatorTypeInstance?.code}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorTypeInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="indicatorType.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="10" pattern="${indicatorTypeInstance.constraints.name.matches}" required="" value="${indicatorTypeInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorTypeInstance, field: 'categoryType', 'error')} required">
	<label for="categoryType">
		<g:message code="indicatorType.categoryType.label" default="Category Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="categoryType" name="categoryType.id" from="${org.chai.memms.report.CategoryType.list()}" optionKey="id" required="" value="${indicatorTypeInstance?.categoryType?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorTypeInstance, field: 'formula', 'error')} ">
	<label for="formula">
		<g:message code="indicatorType.formula.label" default="Formula" />
		
	</label>
	<g:textField name="formula" value="${indicatorTypeInstance?.formula}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorTypeInstance, field: 'increasing', 'error')} ">
	<label for="increasing">
		<g:message code="indicatorType.increasing.label" default="Increasing" />
		
	</label>
	<g:checkBox name="increasing" value="${indicatorTypeInstance?.increasing}" />
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorTypeInstance, field: 'indicatorValues', 'error')} ">
	<label for="indicatorValues">
		<g:message code="indicatorType.indicatorValues.label" default="Indicator Values" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${indicatorTypeInstance?.indicatorValues?}" var="i">
    <li><g:link controller="indicatorValue" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="indicatorValue" action="create" params="['indicatorType.id': indicatorTypeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'indicatorValue.label', default: 'IndicatorValue')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: indicatorTypeInstance, field: 'internediateValuess', 'error')} ">
	<label for="internediateValuess">
		<g:message code="indicatorType.internediateValuess.label" default="Internediate Valuess" />
		
	</label>
	<g:select name="internediateValuess" from="${org.chai.memms.report.IntermediateValues.list()}" multiple="multiple" optionKey="id" size="5" value="${indicatorTypeInstance?.internediateValuess*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorTypeInstance, field: 'maxYellowValue', 'error')} required">
	<label for="maxYellowValue">
		<g:message code="indicatorType.maxYellowValue.label" default="Max Yellow Value" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="maxYellowValue" value="${fieldValue(bean: indicatorTypeInstance, field: 'maxYellowValue')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorTypeInstance, field: 'minYellowValue', 'error')} required">
	<label for="minYellowValue">
		<g:message code="indicatorType.minYellowValue.label" default="Min Yellow Value" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="minYellowValue" value="${fieldValue(bean: indicatorTypeInstance, field: 'minYellowValue')}" required=""/>
</div>

