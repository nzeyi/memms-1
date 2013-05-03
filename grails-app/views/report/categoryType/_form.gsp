<%@ page import="org.chai.memms.report.CategoryType" %>

<div  class="entity-form-container togglable">
	<div class="heading1-bar">
	<ul>
				<g:reportMenus/>
				
			</ul>
		</div>
<div class="main">

<div class="form-aside" ${hasErrors(bean: categoryTypeInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="categoryType.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	
<g:textField name="code" required="" value="${categoryTypeInstance?.code}"/></div>

<div class="form-section ${hasErrors(bean: categoryTypeInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="categoryType.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	
<g:textField name="name" maxlength="10" pattern="${categoryTypeInstance.constraints.name.matches}" required="" value="${categoryTypeInstance?.name}"/></div>

<div class="form-section" ${hasErrors(bean: categoryTypeInstance, field: 'finder', 'error')} ">
	<label for="finder">
		<g:message code="categoryType.finder.label" default="Finder" />
		
	</label>
	
<g:textField name="finder" maxlength="100" value="${categoryTypeInstance?.finder}"/></div>

 

<div class="form-section" ${hasErrors(bean: categoryTypeInstance, field: 'maxYellowValue', 'error')} required">
	<label for="maxYellowValue">
		<g:message code="categoryType.maxYellowValue.label" default="Max Yellow Value" />
		<span class="required-indicator">*</span>
	</label>
	
 <g:field name="maxYellowValue" value="${fieldValue(bean: categoryTypeInstance, field: 'maxYellowValue')}" required=""/></div>

 <div class="form-section" ${hasErrors(bean: categoryTypeInstance, field: 'minYellowValue', 'error')} required">
	<label for="minYellowValue">
		<g:message code="categoryType.minYellowValue.label" default="Min Yellow Value" />
		<span class="required-indicator">*</span>
	</label>
	
 <g:field name="minYellowValue" value="${fieldValue(bean: categoryTypeInstance, field: 'minYellowValue')}" required=""/> 
</div></div>
