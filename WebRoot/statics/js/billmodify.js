//修改订单
$(function(){
	//下拉选项
	$.ajax({
		url:path+"/provider/getProNameAndProId.html",
		type:"post",
		dataType:"json",
		success:function(list) { //list:字符串格式的对象集合
			
			$(list).each(function(index,item){ //item : java对象 有id和proName属性
				var selectProId = $('#pid').val();
				var opt= selectProId==item.id?'<option value="'+item.id+'" selected="selected">'+item.proName+'</option>':'<option value="'+item.id+'">'+item.proName+'</option>';
				$('#providerId').append(opt);
			});
		}
		
	});
	$('#save').click(function(){
		$('#billForm').submit();
	});
});