<%@ page import="org.chai.memms.report.CategoryType" %>



<div class="fieldcontain ${hasErrors(bean: categoryTypeInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="categoryType.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="code" required="" value="${categoryTypeInstance?.code}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: categoryTypeInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="categoryType.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="10" pattern="${categoryTypeInstance.constraints.name.matches}" required="" value="${categoryTypeInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: categoryTypeInstance, field: 'indicatorTypes', 'error')} ">
	<label for="indicatorTypes">
		<g:message code="categoryType.indicatorTypes.label" default="Indicator Types" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${categoryTypeInstance?.indicatorTypes?}" var="i">
    <li><g:link controller="indicatorType" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="indicatorType" action="create" params="['categoryType.id': categoryTypeInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'indicatorType.label', default: 'IndicatorType')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: categoryTypeInstance, field: 'maxYellowValue', 'error')} required">
	<label for="maxYellowValue">
		<g:message code="categoryType.maxYellowValue.label" default="Max Yellow Value" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="maxYellowValue" value="${fieldValue(bean: categoryTypeInstance, field: 'maxYellowValue')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: categoryTypeInstance, field: 'minYellowValue', 'error')} required">
	<label for="minYellowValue">
		<g:message code="categoryType.minYellowValue.label" default="Min Yellow Value" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="minYellowValue" value="${fieldValue(bean: categoryTypeInstance, field: 'minYellowValue')}" required=""/>
</div>

