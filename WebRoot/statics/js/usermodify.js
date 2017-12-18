$(function(){
	//下拉选项
	$.ajax({
		url:path+"/role/view.json",
		type:"post",
		dataType:"json",
		success:function(list) { //list:字符串格式的对象集合
			$(list).each(function(index,item){
				var select = $('#rid').val();
				var opt= select==item.id?'<option value="'+item.id+'" selected="selected">'+item.roleName+'</option>':'<option value="'+item.id+'">'+item.roleName+'</option>';
				$('#userRole').append(opt);
			});
		}
		
		
	});
	$('#save').click(function(){
		$('#userForm').submit(); //点击保存 form表单提交
	});
});