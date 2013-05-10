<%@ page import="org.chai.memms.report.QueryParserHelper" %>



<div class="fieldcontain ${hasErrors(bean: queryParserHelperInstance, field: 'executableScript', 'error')} ">
	<label for="executableScript">
		<g:message code="queryParserHelper.executableScript.label" default="Executable Script" />
		
	</label>
	<g:textArea name="executableScript" cols="40" rows="5" maxlength="1000" value="${queryParserHelperInstance?.executableScript}"/>
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

<div class="fieldcontain ${hasErrors(bean: queryParserHelperInstance, field: 'indicator', 'error')} required">
	<label for="indicator">
		<g:message code="queryParserHelper.indicator.label" default="Indicator" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="indicator" name="indicator.id" from="${org.chai.memms.report.Indicator.list()}" optionKey="id" required="" value="${queryParserHelperInstance?.indicator?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: queryParserHelperInstance, field: 'indicatorType', 'error')} ">
	<label for="indicatorType">
		<g:message code="queryParserHelper.indicatorType.label" default="Indicator Type" />
		
	</label>
	<g:textField name="indicatorType" value="${queryParserHelperInstance?.indicatorType}"/>
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

