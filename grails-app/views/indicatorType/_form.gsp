<%@ page import="org.chai.memms.report.IndicatorType" %>


<div  class="entity-form-container togglable">
	<div class="heading1-bar">
			<ul>
				<g:reportMenus/>
				
			</ul>
		</div>
<div class="main">
<table><tr><td>
<div class="fieldcontain ${hasErrors(bean: indicatorTypeInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="indicatorType.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	
</div></td><td><g:textField name="code" required="" value="${indicatorTypeInstance?.code}"/></td></tr>

<tr><td><div class="fieldcontain ${hasErrors(bean: indicatorTypeInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="indicatorType.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	
</div></td><td><g:textField name="name" maxlength="100" required="" value="${indicatorTypeInstance?.name}"/></td></tr>

<tr><td><div class="fieldcontain ${hasErrors(bean: indicatorTypeInstance, field: 'formula', 'error')} required">
	<label for="formula">
		<g:message code="indicatorType.formula.label" default="Formula" />
		<span class="required-indicator">*</span>
	</label>
	
</div></td><td><g:textArea name="formula" cols="100" rows="10" maxlength="1000" required="" value="${indicatorTypeInstance?.formula}"/></td></tr>

<tr><td><div class="fieldcontain ${hasErrors(bean: indicatorTypeInstance, field: 'finder', 'error')} ">
	<label for="finder">
		<g:message code="indicatorType.finder.label" default="Finder" />
		
	</label>
	
</div></td><td><g:textField name="finder" maxlength="100" value="${indicatorTypeInstance?.finder}"/></td></tr>

<tr><td><div class="fieldcontain ${hasErrors(bean: indicatorTypeInstance, field: 'categoryType', 'error')} required">
	<label for="categoryType">
		<g:message code="indicatorType.categoryType.label" default="Category Type" />
		<span class="required-indicator">*</span>
	</label>
	
</div></td><td><g:select id="categoryType" name="categoryType.id" from="${org.chai.memms.report.CategoryType.list()}" optionKey="id" required="" value="${indicatorTypeInstance?.categoryType?.id}" class="many-to-one"/></td></tr>

<tr><td><div class="fieldcontain ${hasErrors(bean: indicatorTypeInstance, field: 'increasing', 'error')} ">
	<label for="increasing">
		<g:message code="indicatorType.increasing.label" default="Increasing" />
		
	</label>
	
</div></td><td><g:checkBox name="increasing" value="${indicatorTypeInstance?.increasing}" /><td></tr>





<tr><td><div class="fieldcontain ${hasErrors(bean: indicatorTypeInstance, field: 'internediateValuess', 'error')} ">
	<label for="internediateValuess">
		<g:message code="indicatorType.internediateValuess.label" default="Internediate Valuess" />
		
	</label>
	
</div></td><td><g:select name="internediateValuess" from="${org.chai.memms.report.IntermediateValues.list()}" multiple="multiple" optionKey="id" size="5" value="${indicatorTypeInstance?.internediateValuess*.id}" class="many-to-many"/></td></tr></table>
</div>
</div>

