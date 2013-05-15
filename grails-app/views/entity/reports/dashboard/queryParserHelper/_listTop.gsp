<span class="right">
	<shiro:hasPermission permission="queryParserHelper:create">
		<a href="${createLinkWithTargetURI(controller:'queryParserHelper', action:'create',params:params)}" class="next medium left push-r"> 
			<g:message code="default.new.label" args="[entityName]" />
		</a>
	</shiro:hasPermission>
	<a href="${createLinkWithTargetURI(controller:'queryParserHelper',action:'export',params:params)}" class="next medium gray left export push-r">
		<g:message code="default.export.label" />
	</a>
	<a href="${createLinkWithTargetURI(controller: 'task', action:'taskForm', params:[class: importTask, entityClass: entityClass.name])}" class="next medium gray left import"> 
		<g:message code="default.import.label" />
	</a>
	<g:searchBox action="search"  controller="queryParserHelper"/>
</span>


