<%@ page import="org.chai.memms.report.Indicator" %>



<div class="fieldcontain ${hasErrors(bean: indicatorInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="indicator.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="code" required="" value="${indicatorInstance?.code}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorInstance, field: 'formula', 'error')} ">
	<label for="formula">
		<g:message code="indicator.formula.label" default="Formula" />
		
	</label>
	<g:textArea name="formula" cols="40" rows="5" maxlength="1000" value="${indicatorInstance?.formula}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorInstance, field: 'indicatorName_en', 'error')} ">
	<label for="indicatorName_en">
		<g:message code="indicator.indicatorName_en.label" default="Indicator Nameen" />
		
	</label>
	<g:textField name="indicatorName_en" value="${indicatorInstance?.indicatorName_en}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorInstance, field: 'indicatorName_fr', 'error')} ">
	<label for="indicatorName_fr">
		<g:message code="indicator.indicatorName_fr.label" default="Indicator Namefr" />
		
	</label>
	<g:textField name="indicatorName_fr" value="${indicatorInstance?.indicatorName_fr}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorInstance, field: 'categoryType', 'error')} required">
	<label for="categoryType">
		<g:message code="indicator.categoryType.label" default="Category Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="categoryType" name="categoryType.id" from="${org.chai.memms.report.CategoryType.list()}" optionKey="id" required="" value="${indicatorInstance?.categoryType?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorInstance, field: 'indicatorColors', 'error')} ">
	<label for="indicatorColors">
		<g:message code="indicator.indicatorColors.label" default="Indicator Colors" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${indicatorInstance?.indicatorColors?}" var="i">
    <li><g:link controller="indicatorColorCriteriaPerDataLocationType" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="indicatorColorCriteriaPerDataLocationType" action="create" params="['indicator.id': indicatorInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'indicatorColorCriteriaPerDataLocationType.label', default: 'IndicatorColorCriteriaPerDataLocationType')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: indicatorInstance, field: 'indicatorType', 'error')} ">
	<label for="indicatorType">
		<g:message code="indicator.indicatorType.label" default="Indicator Type" />
		
	</label>
	<g:textField name="indicatorType" value="${indicatorInstance?.indicatorType}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorInstance, field: 'indicatorValues', 'error')} ">
	<label for="indicatorValues">
		<g:message code="indicator.indicatorValues.label" default="Indicator Values" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${indicatorInstance?.indicatorValues?}" var="i">
    <li><g:link controller="indicatorValue" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="indicatorValue" action="create" params="['indicator.id': indicatorInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'indicatorValue.label', default: 'IndicatorValue')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: indicatorInstance, field: 'internediateValuess', 'error')} ">
	<label for="internediateValuess">
		<g:message code="indicator.internediateValuess.label" default="Internediate Valuess" />
		
	</label>
	<g:select name="internediateValuess" from="${org.chai.memms.report.IntermediateVariable.list()}" multiple="multiple" optionKey="id" size="5" value="${indicatorInstance?.internediateValuess*.id}" class="many-to-many"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicatorInstance, field: 'queryParserHelpers', 'error')} ">
	<label for="queryParserHelpers">
		<g:message code="indicator.queryParserHelpers.label" default="Query Parser Helpers" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${indicatorInstance?.queryParserHelpers?}" var="q">
    <li><g:link controller="queryParserHelper" action="show" id="${q.id}">${q?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="queryParserHelper" action="create" params="['indicator.id': indicatorInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'queryParserHelper.label', default: 'QueryParserHelper')])}</g:link>
</li>
</ul>

</div>

