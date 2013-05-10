<%@ page import="org.chai.memms.report.utils.QueryParserHelper" %>



<div class="fieldcontain ${hasErrors(bean: queryParserHelperInstance, field: 'excutableScript', 'error')} ">
	<label for="excutableScript">
		<g:message code="queryParserHelper.excutableScript.label" default="Excutable Script" />
		
	</label>
	<g:textArea name="excutableScript" cols="40" rows="5" maxlength="1000" value="${queryParserHelperInstance?.excutableScript}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: queryParserHelperInstance, field: 'classDomaine', 'error')} ">
	<label for="classDomaine">
		<g:message code="queryParserHelper.classDomaine.label" default="Class Domaine" />
		
	</label>
	<g:textField name="classDomaine" value="${queryParserHelperInstance?.classDomaine}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: queryParserHelperInstance, field: 'followOperand', 'error')} ">
	<label for="followOperand">
		<g:message code="queryParserHelper.followOperand.label" default="Follow Operand" />
		
	</label>
	<g:textField name="followOperand" value="${queryParserHelperInstance?.followOperand}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: queryParserHelperInstance, field: 'indType', 'error')} ">
	<label for="indType">
		<g:message code="queryParserHelper.indType.label" default="Ind Type" />
		
	</label>
	<g:textField name="indType" value="${queryParserHelperInstance?.indType}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: queryParserHelperInstance, field: 'indicatorType', 'error')} required">
	<label for="indicatorType">
		<g:message code="queryParserHelper.indicatorType.label" default="Indicator Type" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="indicatorType" name="indicatorType.id" from="${org.chai.memms.report.IndicatorType.list()}" optionKey="id" required="" value="${queryParserHelperInstance?.indicatorType?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: queryParserHelperInstance, field: 'isDenominator', 'error')} ">
	<label for="isDenominator">
		<g:message code="queryParserHelper.isDenominator.label" default="Is Denominator" />
		
	</label>
	<g:checkBox name="isDenominator" value="${queryParserHelperInstance?.isDenominator}" />
</div>

<div class="fieldcontain ${hasErrors(bean: queryParserHelperInstance, field: 'isDynamicFinder', 'error')} ">
	<label for="isDynamicFinder">
		<g:message code="queryParserHelper.isDynamicFinder.label" default="Is Dynamic Finder" />
		
	</label>
	<g:checkBox name="isDynamicFinder" value="${queryParserHelperInstance?.isDynamicFinder}" />
</div>

<div class="fieldcontain ${hasErrors(bean: queryParserHelperInstance, field: 'isIntermidiateVariable', 'error')} ">
	<label for="isIntermidiateVariable">
		<g:message code="queryParserHelper.isIntermidiateVariable.label" default="Is Intermidiate Variable" />
		
	</label>
	<g:checkBox name="isIntermidiateVariable" value="${queryParserHelperInstance?.isIntermidiateVariable}" />
</div>

<div class="fieldcontain ${hasErrors(bean: queryParserHelperInstance, field: 'useCountFunction', 'error')} ">
	<label for="useCountFunction">
		<g:message code="queryParserHelper.useCountFunction.label" default="Use Count Function" />
		
	</label>
	<g:checkBox name="useCountFunction" value="${queryParserHelperInstance?.useCountFunction}" />
</div>

