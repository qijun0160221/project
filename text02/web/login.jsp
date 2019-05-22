<%@page import="java.util.* , java.io.* " pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎登录</title>
</head>
<body>
<center>
<form action="chack.jsp" method="post">
    <p>用户名:<input type="text" name="username" placeholder="Enter you name"></p>
    <p>密码:<input type="userpwd" name="userpwd"></p>
    <input type="submit" value="登录" >
    </form>

    <form>
        <%out.println("如果你还没注册，请<a href='zhuce.jsp'>注册</a>");%>
    </form>


</center>
<%
    if(request.getParameter("username")!=null){
        session.setAttribute("username",request.getParameter("username"));
        session.setAttribute("car",new TreeMap<String,Integer>());
        response.sendRedirect("index.jsp");
    }
%>
</body>
</html>