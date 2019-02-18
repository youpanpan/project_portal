$(function(){
	activeMenu("");
	
	$(".terms-tab").delegate(".item", "click", function(){
		var tab = $(this).attr("data-tab");
		
		$(".terms-tab").find(".item").each(function(){
			$(this).removeClass("active");
		});
		$(this).addClass("active");
		
		$(".terms-page").find(".terms-tab-content").each(function(){
			$(this).removeClass("active");
		});
		$(".terms-page").find(".terms-tab-content[data-tab='"+ tab +"']").addClass("active");
	});
	
});