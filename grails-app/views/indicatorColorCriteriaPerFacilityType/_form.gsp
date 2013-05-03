<%@ page import="org.chai.memms.report.IndicatorColorCriteriaPerFacilityType" %>


<div class="nav" role="navigation">
			<ul>
				<g:reportMenus/>
				
			</ul>
		</div><div class="main">
<table><tr><td>
<div class="fieldcontain ${hasErrors(bean: indicatorColorCriteriaPerFacilityTypeInstance, field: 'code', 'error')} required">
	<label for="code">
		<g:message code="indicatorColorCriteriaPerFacilityType.code.label" default="Code" />
		<span class="required-indicator">*</span>
	</label>
	
</div></td><td><g:textField name="code" required="" value="${indicatorColorCriteriaPerFacilityTypeInstance?.code}"/></td></tr>

<tr><td colspan="2"><div class="fieldcontain ${hasErrors(bean: indicatorColorCriteriaPerFacilityTypeInstance, field: 'facilityType', 'error')} required">
	<g:selectFromList label="${message(code:'facility.type.label')}" id="facilityType" name="facilityType.id"  bean="${facilityType}"
  			from="${org.chai.location.DataLocationType.list()}" value="${indicatorColorCriteriaPerFacilityType?.facilityType?.id}" values="${org.chai.location.DataLocationType.list().collect{it.names}}" optionKey="id"/></td></tr>

<tr><td><div class="fieldcontain ${hasErrors(bean: indicatorColorCriteriaPerFacilityTypeInstance, field: 'increasing', 'error')} ">
	<label for="increasing">
		<g:message code="indicatorColorCriteriaPerFacilityType.increasing.label" default="Increasing" />
		
	</label>
	
</div></td><td><g:checkBox name="increasing" value="${indicatorColorCriteriaPerFacilityTypeInstance?.increasing}" /></td></tr>

<tr><td><div class="fieldcontain ${hasErrors(bean: indicatorColorCriteriaPerFacilityTypeInstance, field: 'indicatorType', 'error')} required">
	<label for="indicatorType">
		<g:message code="indicatorColorCriteriaPerFacilityType.indicatorType.label" default="Indicator Type" />
		<span class="required-indicator">*</span>
	</label>
	
</div></td><td><g:select id="indicatorType" name="indicatorType.id" from="${org.chai.memms.report.IndicatorType.list()}" optionKey="id" required="" value="${indicatorColorCriteriaPerFacilityTypeInstance?.indicatorType?.id}" class="many-to-one"/></td></tr>

<tr><td><div class="fieldcontain ${hasErrors(bean: indicatorColorCriteriaPerFacilityTypeInstance, field: 'maxYellow', 'error')} ">
	<label for="maxYellow">
		<g:message code="indicatorColorCriteriaPerFacilityType.maxYellow.label" default="Max Yellow" />
		
	</label>
	
</div></td><td><g:textField name="maxYellow" value="${indicatorColorCriteriaPerFacilityTypeInstance?.maxYellow}"/></td></tr>

<tr><td><div class="fieldcontain ${hasErrors(bean: indicatorColorCriteriaPerFacilityTypeInstance, field: 'minYellow', 'error')} ">
	<label for="minYellow">
		<g:message code="indicatorColorCriteriaPerFacilityType.minYellow.label" default="Min Yellow" />
		
	</label>
	
</div></td><td>
<g:textField name="minYellow" value="${indicatorColorCriteriaPerFacilityTypeInstance?.minYellow}"/>

</td></tr></table>
</div></div>

