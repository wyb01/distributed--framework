<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>SSO注册页面</title>
<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
</head>
<body>
	<h2 align=center>注册</h2>
	<center>
		<form id="personRegForm" method="post" onsubmit="return false;">
			<table border="0">
				<tr>
					<td>用户名：</td>
					<td><input type="text" id="regName" name="username" /></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" id="pwd" name="password" /></td>
				</tr>
				<tr>
					<td>重复密码：</td>
					<td><input type="password" id="pwdRepeat" name="pwdRepeat" /></td>
				</tr>
				<tr>
					<td>手机：</td>
					<td><input type="text" id="phone" maxlength="11" name="phone" /></td>
				</tr>
				<tr align="center">
					<td colspan="2"><input type="button" id="registsubmit"
						value="立即注册" onclick="REGISTER.reg();" /></td>
				</tr>
			</table>
		</form>
		您有账号？<a href="/sso/login"><front color="GREEN">点击直接登录</front></a>
	</center>
	<script type="text/javascript">
	var REGISTER={
		param:{
			//单点登录系统的url
			surl:""
		},
		inputcheck:function(){
				//不能为空检查
				if ($("#regName").val() == "") {
					alert("用户名不能为空");
					$("#regName").focus();
					return false;
				}
				if ($("#pwd").val() == "") {
					alert("密码不能为空");
					$("#pwd").focus();
					return false;
				}
				if ($("#phone").val() == "") {
					alert("手机号不能为空");
					$("#phone").focus();
					return false;
				}
				//密码检查
				if ($("#pwd").val() != $("#pwdRepeat").val()) {
					alert("确认密码和密码不一致，请重新输入！");
					$("#pwdRepeat").select();
					$("#pwdRepeat").focus();
					return false;
				}
				return true;
		},
		beforeSubmit:function() {
				//检查用户是否已经被占用
				$.ajax({
	            	url : REGISTER.param.surl + "/user/check/"+escape($("#regName").val())+"/1?r=" + Math.random(),
	            	success : function(data) {
	            		if (data.data) {
	            			//检查手机号是否存在
	            			$.ajax({
	            				url : REGISTER.param.surl + "/user/check/"+$("#phone").val()+"/2?r=" + Math.random(),
				            	success : function(data) {
				            		if (data.data) {
					            		REGISTER.doSubmit();
				            		} else {
				            			alert("此手机号已经被注册！");
				            			$("#phone").select();
				            		}
				            	}
	            			});
	            		} else {
	            			alert("此用户名已经被占用，请选择其他用户名");
	            			$("#regName").select();
	            		}	
	            	}
				});
	            	
		},
		doSubmit:function() {
			$.post("/user/register",$("#personRegForm").serialize(), function(data){
				if(data.status == 200){
					alert('用户注册成功，请登录！');
					REGISTER.login();
				} else {
					alert("注册失败！");
				}
			});
		},
		login:function() {
			 location.href = "/sso/login";
			 return false;
		},
		reg:function() {
			if (this.inputcheck()) {
				this.beforeSubmit();
			}
		}
	};
</script>
</body>
</html>
