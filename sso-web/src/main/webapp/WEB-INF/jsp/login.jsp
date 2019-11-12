<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>SSO登录页面</title>
	<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>
</head>
<body>
	<h2 align=center>登录</h2>
	<center>
		<form id="formlogin" method="post" onsubmit="return false;">
			<table border="0">
				<tr>
					<td>用户名：</td>
					<td><input type="text" id="loginname" name="username" tabindex="1" /></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" id="nloginpwd" name="password" tabindex="2" /></td>
				</tr>
				<tr align="center">
					<td colspan="2"><input type="button" id="loginsubmit" value="登录" tabindex="3" /></td>
				</tr>
			</table>
		</form>
		<span><a href="/sso/register">免费注册&gt;&gt;</a></span>
	</center>

	<script type="text/javascript">
		var redirectUrl = "${redirect}";
		var LOGIN = {
			checkInput : function() {
				if ($("#loginname").val() == "") {
					alert("用户名不能为空");
					$("#loginname").focus();
					return false;
				}
				if ($("#nloginpwd").val() == "") {
					alert("密码不能为空");
					$("#nloginpwd").focus();
					return false;
				}
				return true;
			},
			doLogin : function() {
				$.post("/user/login", $("#formlogin").serialize(), function(
						data) {
					if (data.status == 200) {
						alert("登录成功！");
						if (redirectUrl == "") {
							location.href = "http://localhost:8083/index";
						} else {
							location.href = redirectUrl;
						}
					} else {
						alert("登录失败，原因是：" + data.msg);
						$("#loginname").select();
					}
				});
			},
			login : function() {
				if (this.checkInput()) {
					this.doLogin();
				}
			}

		};
		$(function() {
			$("#loginsubmit").click(function() {
				LOGIN.login();
			});
		});
	</script>
</body>
</html>