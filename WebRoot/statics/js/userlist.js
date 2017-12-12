//初始化函数，页面加载完成后 调用函数
$(function(){
	$.ajax({
		url:path+"/role/getrole.html",
		type:"get",
		dataType:"json",
		success:function(list){
			$(list).each(function(index,item){
				var opt= selectProId==item.id?'<option value="'+item.id+'" selected="selected">'+item.roleName+'</option>':'<option value="'+item.id+'">'+item.roleName+'</option>';
				$('#userRole').append(opt);
			});
		}
	});
	
	var selectProId = $('#pid').val();
});