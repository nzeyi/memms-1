/**
 * data element Search
 */
function getDataElement(callback){
	$('.search-form button').bind('click', function(){$(this).submit(); return false;});
	$('.search-form').bind('submit', function() {
		var element = this;
		$.ajax({
			type: 'GET', data: $(element).serialize(), url: $(element).attr('action'), 
			success: function(data, textStatus){
				if (data.result == 'success') {
					var filtered = $(element).parent('div').find('.filtered');
					filtered.html(data.html);
					filtered.find('a.cluetip').cluetip(cluetipOptions);
					filtered.find('li').bind('mousedown', callback);
					filtered.find('li')
				}
			}
		});
		return false;
	});
}
 
/**
 * rich text content retrieve
 */
function getRichTextContent(){
	$('.rich-textarea-form').bind('click',function(){
		$('.toggle-entry textarea').each(function(){
			$(this).val($(this).prev('div').children().html())
		})
	});
}


/**
 * 
 * form-aside loader
 */
function getHtml(htmls,field){
	var html=""
	$.each(htmls, function (i, val) {
		html=html+" "+val.html
	});
	var attrId= "#form-aside-"+field
	$(attrId+" .form-aside-hidden").remove();
	$(attrId).append(html);
	$(".form-aside-hidden").hide();
}
/**
 * Edit donation and obsolete
 */
function updateEquipment(baseUrl){
	$(".ajax-spinner").hide();
	$(".ajax-error").hide()
	$(".list-check-box").change(function(event){
		$(event.target).hide();
	    $(event.target).prev().show();
	    $(event.target).next().hide();
	    var state= event.target.checked
		$.ajax({
			type :'GET',dataType: 'json',data:{"equipment.id":event.target.id,"field":event.target.name},url: baseUrl,
			success: function(data) {
				$(event.target).prev().fadeOut("slow");
				$(event.target).fadeIn("slow");
			}
		});
		$(event.target).ajaxError(function(){
			if(state) $(event.target).attr('checked',false);
			else $(event.target).attr('checked',true);
			$(event.target).prev().fadeOut("slow");
			$(event.target).fadeIn("slow");
			$(event.target).next().show("slow");	
		
		});
	})
}

/**
 * Add maintenance process
 */
function addMaintenanceProcess(baseUrl,order,spinnerImgSrc,errorMsg){
	$(".ajax-spinner").hide();
	$(".ajax-error").hide()
	$('.process').on("click",".add-process-button",function(e){
		e.preventDefault();
		$(this).prevAll(".ajax-spinner").show();
		$(this).hide();
		$.ajax({
			type :'GET',
			dataType: 'json',
			data:{"order":order,"type":$(this).prevAll(".idle-field").attr('name'),"value":$(this).prevAll(".idle-field").attr('value')},
			url:baseUrl,
			success: function(data) {
				$(e.target).prevAll(".ajax-error").fadeOut("slow");
				$(e.target).prevAll(".ajax-spinner").fadeOut("slow");
				$(e.target).prevAll(".idle-field").val("")
				$(e.target).fadeIn("slow");
				refreshList(data.results[1],'.process-list-'+data.results[2])
			}
		});
		$(this).ajaxError(function(){
			$(this).prevAll(".ajax-spinner").fadeOut("slow");
			$(this).prevAll(".ajax-error").fadeIn("slow");	
			$(this).fadeIn("slow");
		});
	})
}

/**
 * Remove Maintenance Process
 */
function removeMaintenanceProcess(baseUrl){
	$(".ajax-spinner").hide();
	$(".ajax-error").hide();
	$('.process').on("click","a.delete-process",function(e){
		e.preventDefault();
		$(this).fadeOut();
		$(this).prevAll(".ajax-spinner").fadeIn();
		$.ajax({
			type :'GET',dataType: 'json',data:{"process":$(this).attr("name")},url:baseUrl,
			success: function(data) {
				$(e.target).prevAll(".ajax-error").fadeOut("slow");
				$(e.target).prevAll(".ajax-spinner").fadeOut("slow");
				$(e.target).fadeIn("slow");
				refreshList(data.results[1],'.process-list-'+data.results[2])
			}
		});
		
		$(this).ajaxError(function(){
			$(this).prevAll(".ajax-spinner").fadeOut("slow");
			$(this).prevAll(".ajax-error").fadeIn("slow");	
			$(this).fadeIn("slow");
		});
		
	});
}

/**
 * Add Comment to a workOrder
 */
function addComment(baseUrl,order){
	$(".comment-section").on("click","#add-comment",function(e){
		e.preventDefault();
		$(this).fadeOut();
		$(this).prevAll(".ajax-spinner").fadeIn();
		$.ajax({
			type :'GET',dataType: 'json',
			data:{"order":order,"content":$("#comment-content").val()},
			url:baseUrl,
			success: function(data) {
				$(e.target).prevAll(".ajax-error").fadeOut("slow");
				$(e.target).prevAll(".ajax-spinner").fadeOut("slow");
				$(e.target).fadeIn("slow");
				$("#comment-content").val("")
				refreshList(data.results[1],'.comment-list')
			}
		});
		$(this).ajaxError(function(){
			$(this).prevAll(".ajax-spinner").fadeOut("slow");
			$(this).prevAll(".ajax-error").fadeIn("slow");	
			$(this).fadeIn("slow");
		});
	})
}

/**
 * Delete a comment from workOrder
 */
function removeComment(baseUrl){
	$(".comment-section").on("click","a.delete-comment",function(e){
		e.preventDefault();
		$(this).fadeOut();
		$(this).prevAll(".ajax-spinner").fadeIn();
		$.ajax({
			type :'GET',dataType: 'json',data:{"comment":$(this).attr("id")},
			url:baseUrl,
			success: function(data) {
				$(e.target).prevAll(".ajax-error").fadeOut("slow");
				$(e.target).prevAll(".ajax-spinner").fadeOut("slow");
				$(e.target).fadeIn("slow");
				refreshList(data.results[1],'.comment-list')
			}
		});
		$(this).ajaxError(function(){
			$(this).prevAll(".ajax-spinner").fadeOut("slow");
			$(this).prevAll(".ajax-error").fadeIn("slow");	
			$(this).fadeIn("slow");	
		});
	})
}
/**
 * Replace (refresh) any list with the provided class and hide ajax-spinner
 */
function refreshList(html,cssClass){
	$(cssClass).slideUp().replaceWith(html).slideDown();
	$(".ajax-spinner").hide();
	$(".ajax-error").hide();	
}

/**
 * Hide set of fields if an option is selected (donation - supplier is same as warranty provider)
 */
function getToHide(donation,sameAsSupplier){
	if(donation=="true")
		$("#purchase-cost").addClass("hidden").hide()
	if(sameAsSupplier=="true")
		$("#address").addClass("hidden").hide()
		
	$(".add-equipment-form :input").change(function(event){
		var currentDiv = $(event.target).parents("div.row");
		if(currentDiv.nextAll("div.can-be-hidden").is(":visible"))
			currentDiv.nextAll("div.can-be-hidden").slideUp()
		else
			currentDiv.nextAll("div.can-be-hidden").slideDown()
	})
}
