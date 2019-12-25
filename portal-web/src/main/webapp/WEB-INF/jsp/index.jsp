<%@ page language="java" contentType="text/html; charset=utf-8"
       pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="js/jquery-1.6.4.js"></script>
<script type="text/javascript" src="js/jquery.cookie.js"></script>
<title>首页</title>
</head>
<body>
       <h2 align=center>Portal index</h2>
       <ul>
              <li id="loginbar">您好！<a href="javascript:login()">[登录]</a>&nbsp;<a href="javascript:regist()">[免费注册]</a></li>
              <li><s></s><a href="http://localhost:8083/my/mypage">个人中心</a></li>
       </ul>
       <script type="text/javascript">
              function login() {
                     return location.href = "http://localhost:8085/sso/login";
              }
              function regist() {
                     return location.href = "http://localhost:8085/sso/register";
              }

              //页面加载时读取cookie信息，并显示用户信息
              var S = SSO = {
                      checkLogin : function(){
                             var _ticket = $.cookie("SSO_TOKEN"); //获取cookie中存放的token
                             if(!_ticket){
                                    return ;
                             }
                             $.ajax({
                                    url : "http://localhost:8085/user/token/" + _ticket,
                                    dataType : "jsonp",
                                    type : "GET",
                                    success : function(data){
                                           if(data.status == 200){
                                                  var username = data.data.username;
                                                  var html = username + "，欢迎光临！<a href=\"http://localhost:8085/user/logout/" + _ticket + " \" >[退出]</a>";
                                                  $("#loginbar").html(html);
                                           }
                                    }
                             });
                      }
                }
                $(function(){
                      // 查看是否已经登录，如果已经登录查询登录信息
                      S.checkLogin();
                });
       </script>
</body>
</html>