<%@ page import="org.chai.memms.spare.part.SparePartStatus.StatusOfSparePart" %>
<%@ page import="org.chai.memms.util.Utils" %>
<table class="items spaced">
	<thead>
		<tr>
			<th/>
			<g:sortableColumn property="code"  title="${message(code: 'spare.part.code.label')}" params="[q:q]" />
			<g:sortableColumn property="categoryType"  title="${message(code: 'spare.part.type.label')}" params="[q:q]" />
			<g:sortableColumn property="indicatorName"  title="${message(code: 'spare.part.status.label')}" params="[q:q]" />
			<g:sortableColumn property="formula"  title="${message(code: 'spare.part.purchase.date.label')}" params="[q:q]" />
			<g:sortableColumn property="indicatorType"  title="${message(code: 'spare.part.purchase.cost.label')}" params="[q:q]" />
		</tr>
	</thead>
	<tbody>
		<g:each in="${entities}" status="i" var="indicator">
			<tr >
				<td>
					<ul class="horizontal">
						<li>
							<shiro:hasPermission permission="indicator:edit">
								<a href="${createLinkWithTargetURI(controller:'indicator', action:'edit', params:[id: indicator.id])}"  class="edit-button">
									<g:message code="default.link.edit.label" />
								</a>
							</shiro:hasPermission>
						</li>
						<li>
							<shiro:hasPermission permission="indicator:delete">
								<a href="${createLinkWithTargetURI(controller:'indicator', action:'delete', params:[id: indicator.id])}" onclick="return confirm('\${message(code: 'default.link.delete.confirm.message')}');" class="delete-button">
									<g:message code="default.link.delete.label" />
								</a>
							</shiro:hasPermission>
						</li>
					</ul>
				</td>
				<td>${indicator.code}</td>
				<td>${indicator.categoryType.name}</td>
				<td>${indicator.indicatorName}</td>
				<td>${indicator.formula}</td>
				<td>${indicator.indicatorType}</td>
			</tr>
		</g:each>
	</tbody>	
</table>
<g:render template="/templates/pagination" />