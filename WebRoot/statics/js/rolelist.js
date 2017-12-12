var is=false; //是否执行修改或者删除操作
var roleId;
$(function(){
	$('#no').click(function(){
		$('#zhezhao').hide();
		$('#removeRole').hide();
		is= false;//用户点击取消
	});
	$('#yes').click(function(){
		$('#zhezhao').hide();
		$('#removeRole').hide();
		is= true;//用户点击确定
		$('#"'+id+'"').click();
		is=false;
	})
	
})
function aClick(){
		roleId=id;
		$('#zhezhao').show();
		$('#removeRole').show();
		return is;
}
