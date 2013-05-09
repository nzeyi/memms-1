<%@ page import="org.chai.memms.report.IndicatorType" %>



<div class="fieldcontain ${hasErrors(bean: indicatorTypeInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="indicatorType.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="code" required="" value="${indicatorTypeInstance?.code}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorTypeInstance, field: 'formula', 'error')} ">
	<label for="formula">
		<g:message code="indicatorType.formula.label" default="Formula" />
		
	</label>
	<g:textArea name="formula" cols="40" rows="5" maxlength="1000" value="${indicatorTypeInstance?.formula}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorTypeInstance, field: 'finder', 'error')} ">
	<label for="finder">
		<g:message code="indicatorType.finder.label" default="Finder" />
		
	</label>
	<g:textField name="finder" maxlength="100" value="${indicatorTypeInstance?.finder}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorTypeInstance, field: 'indicatorName_en', 'error')} ">
	<label for="indicatorName_en">
		<g:message code="indicatorType.indicatorName_en.label" default="Indicator Nameen" />
		
	</label>
	<g:textField name="indicatorName_en" value="${indicatorTypeInstance?.indicatorName_en}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorTypeInstance, field: 'indicatorName_fr', 'error')} ">
	<label for="indicatorName_fr">
		<g:message code="indicatorType.indicatorName_fr.label" default="Indicator Namefr" />
		
	</label>
	<g:textField name="indicatorName_fr" value="${indicatorTypeInstance?.indicatorName_fr}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorTypeInstance, field: 'categoryType', 'error')} required">
	<label for="categoryType">
		<g:message code="indicatorType.categoryType.label" default="Category Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="categoryType" name="categoryType.id" from="${org.chai.memms.report.CategoryType.list()}" optionKey="id" required="" value="${indicatorTypeInstance?.categoryType?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorTypeInstance, field: 'indicatorColors', 'error')} ">
	<label for="indicatorColors">
		<g:message code="indicatorType.indicatorColors.label" default="Indicator Colors" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${indicatorTypeInstance?.indicatorColors?}" var="i">
    <li><g:link controller="indicatorColorCriteriaPerFacilityType" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="indicatorColorCriteriaPerFacilityType" action="create" params="['indicatorType.id': indicatorTypeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'indicatorColorCriteriaPerFacilityType.label', default: 'IndicatorColorCriteriaPerFacilityType')])}</g:link>
</li>
</ul>

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
	<g:select name="internediateValuess" from="${org.chai.memms.report.IntermediateVariable.list()}" multiple="multiple" optionKey="id" size="5" value="${indicatorTypeInstance?.internediateValuess*.id}" class="many-to-many"/>
</div>

