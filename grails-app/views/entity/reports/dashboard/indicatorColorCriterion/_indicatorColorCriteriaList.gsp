<table class="items">
	<thead>
		<tr>
			<th/>
			<g:sortableColumn property="code"  title="${message(code: 'color.eval.code.label')}" params="[q:q]" />
			<g:sortableColumn property="minYellowHc"  title="${message(code: 'color.eval.min.yellowHc')}" params="[q:q]" />
			<g:sortableColumn property="maxYellowHc"  title="${message(code: 'color.eval.max.yellowHc')}" params="[q:q]" />
			<g:sortableColumn property="minYellowDh"  title="${message(code: 'color.eval.min.yellowDh')}" params="[q:q]" />
			<g:sortableColumn property="maxYellowDh"  title="${message(code: 'color.eval.max.yellowDh')}" params="[q:q]" />
			<g:sortableColumn property="isIncreasing"  title="${message(code: 'color.eval.is.increasing')}" params="[q:q]" />
			
		    <th>  <g:message code="indicator.label" /></th>
		
		
		</tr>
	</thead>
	
	<tbody>
	<g:each in="${entities}" status="i" var="indicatorColorCriterion">
		
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
				<td>
					<ul class="horizontal">
						<li>
							<shiro:hasPermission permission="indicatorColorCriterion:edit">
								<a href="${createLinkWithTargetURI(controller:'indicatorColorCriterion', action:'edit', params:[id: indicatorColorCriterion.id])}"  class="edit-button">
									<g:message code="default.link.edit.label" />
								</a>
							</shiro:hasPermission>
						</li>
						<li>
							<shiro:hasPermission permission="indicatorColorCriterion:delete">
								<a href="${createLinkWithTargetURI(controller:'indicatorColorCriterion', action:'delete', params:[id: indicatorColorCriterion.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button">
									<g:message code="default.link.delete.label" />
								</a>
							</shiro:hasPermission>
						</li>
					</ul>
				</td>
				<td>${indicatorColorCriterion.code}</td>
				<td>${indicatorColorCriterion.minYellowHc}</td>
				<td>${indicatorColorCriterion.maxYellowHc}</td>
				<td>${indicatorColorCriterion.minYellowDh}</td>
				<td>${indicatorColorCriterion.maxYellowDh}</td>
				<td>${indicatorColorCriterion.isIncreasing}</td>
				
				<td><g:stripHtml field="${indicatorColorCriterion.indicator?.names}" chars="15"/></td>
			</tr>
		</g:each>
</tbody>
	
	
</table>
<g:render template="/templates/pagination" />
