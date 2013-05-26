<%@ page import="org.chai.memms.report.IndicatorColorCriterion" %>
<%@ page import="org.chai.memms.report.Indicator" %>
<%@ page import="org.chai.location.DataLocationType" %>
<div  class="entity-form-container togglable">
	<div class="heading1-bar">
		<g:locales/>
		<h1>
		  <g:if test="${indicatorColorCriterion.id != null}">
				<g:message code="default.edit.label" args="[message(code:'indicator.color.criteria.per.data.location.type.label')]" />
			</g:if>
			<g:else>
				<g:message code="default.new.label" args="[message(code:'indicator.color.criteria.per.data.location.type.label')]" />
			</g:else>
		</h1>
		
	</div>
	
	<div class="main">
  	<g:form url="[controller:'indicatorColorCriterion', action:'save', params:[targetURI: targetURI]]" useToken="true" class="simple-list">
	
	    <g:input name="code" label="${message(code:'entity.code.label')}" bean="${indicatorColorCriterion}" field="code"/>
  		<g:input name="minYellowHc" label="${message(code:'entity.minYellowHc.label')}" bean="${indicatorColorCriterion}" field="minYellowHc"/>
  		<g:input name="maxYellowHc" label="${message(code:'entity.maxYellowHc.label')}" bean="${indicatorColorCriterion}" field="maxYellowHc"/>
  		<g:input name="minYellowDh" label="${message(code:'entity.minYellowDh.label')}" bean="${indicatorColorCriterion}" field="minYellowDh"/>
  		<g:input name="maxYellowDh" label="${message(code:'entity.maxYellowDh.label')}" bean="${indicatorColorCriterion}" field="maxYellowDh"/>
  		
  		<g:input name="isIncreasing" label="${message(code:'entity.isIncreasing.label')}" bean="${indicatorColorCriterion}" field="isIncreasing"/>
  	
  			<g:selectFromList id="indicator" label="${message(code:'indicator.label')}" name="indicator.id"  bean="${indicator}"
  			from="${org.chai.memms.report.Indicator.list()}" value="${indicator?.id}" values="${org.chai.memms.report.Indicator.list().collect{it.names}}" optionKey="id"/>
  		
  		
  		<g:if test="${indicatorColorCriterion.id != null}">
  			<input type="hidden" name="id" value="${indicatorColorCriterion.id}"></input>
  		</g:if>
  		<br />
  		<div class="buttons">
  			<button type="submit"><g:message code="default.button.save.label"/></button>
  			<a href="${createLink(uri: targetURI)}"><g:message code="default.link.cancel.label"/></a>
  		</div>
  	</g:form>
  </div>
</div>
