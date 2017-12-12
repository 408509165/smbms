var backBtn = null;
$(function(){
	backBtn = $("#back");
	backBtn.on("click",function(){
			history.back(-1);
	});
	$('#add').click(function(){
		$('#userForm').submit(); //点击保存 form表单提交
	});
	//下拉选项
		$.ajax({
			url:path+"/role/getrole.html",
			type:"post",
			dataType:"json",
			success:function(list) { //list:字符串格式的对象集合
				$(list).each(function(index,item){ //item : java对象 有id和proName属性
					var select = $('#rid').val();
					var opt= select==item.id?'<option value="'+item.id+'" selected="selected">'+item.roleName+'</option>':'<option value="'+item.id+'">'+item.roleName+'</option>';
					$('#userRole').append(opt);
				});
			}
			
		});
});