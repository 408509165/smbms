$(function(){

	$('#oldpassword').blur(function(){
		var id=$('#loginUserId').val();
		var pwd=$(this).val();
		if(pwd=="" || pwd == null){
			alert('请输入旧密码！')
			return;
		}
		check(id,pwd);
	});
	$('#newpassword').blur(function(){
		var newpassword=$(this).val();
		if(newpassword==null || newpassword==""){
			$('#newpassword').next().html('×');
			return;
		}else{
			$('#newpassword').next().html('√');
		}
	});
	$('#rnewpassword').blur(function(){
		var rnewpassword=$(this).val();
		var newpassword=$(this).val();
		if(rnewpassword==null || rnewpassword=="" || newpassword != rnewpassword){
			$('#rnewpassword').next().html('×');
			return;
		}else{
			$('#newpassword').next().html('√');
		}
	});
	
	$('#save').click(function(){
		var o=$('#oldpassword').val();
		var newpassword=$('#newpassword').val();
		var rnewpassword=$('#rnewpassword').val();
		if(o != "" && o != null && newpassword != "" && newpassword != null && rnewpassword != "" && rnewpassword != null)
			$(userForm).submit();
	});
	function check(p,pwd){
		$.ajax({
			url:path+"/user/passsure/"+p+"/"+pwd+"",
			type:"post",
			dataType:'text',
			success:function(result){
				$('#oldpassword').next().html(result);
				if(result== '×')
					$('#oldpassword').focus();
			}
		});
	}
});