<%@page import="java.util.*,java.io.*" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>购物车</title>
</head>
<body>

<h1 style="font-family:arial;color:orangered;font-size:50px;"align="center">买水果啦！</h1>
<%
  String name=(String)session.getAttribute("username");
  if(name==null){

    out.println("您还没有登录，请<a href='login.jsp'>登录</a>");

  }else{%>

<p>欢迎 <%=name%>

<p ><a href="mall.jsp">查看商城</a>
<p><a href="car.jsp">我的购物车</a>
<p><a href="logout.jsp">退出</a>
    <%}%>
</body>
</html>