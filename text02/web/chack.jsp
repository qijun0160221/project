<%@page import="java.util.* , java.io.* " pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<html>
<head>
    <title>检查</title>
</head>
<body>

<%
    Connection conn=null;
    Statement stat=null;
    ResultSet rs=null;
    try {
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("userpwd");

        Class.forName("com.mysql.jdbc.Driver");
        //下面是数据库操作
         conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_9griddiary?user=root&password=root&useUnicode=true");
         stat = conn.createStatement();
        String sql = "select * from th_user where username='" + username + "'";
        //定义一个查询语句
         rs = stat.executeQuery(sql);
        System.out.println(username + " " + password);
        if (rs.next()) {

            if (password.equals(rs.getString("userpwd"))) {
                if (request.getParameter("username") != null) {
                    session.setAttribute("username", request.getParameter("username"));
                    session.setAttribute("car", new TreeMap<String, Integer>());
                    response.sendRedirect("index.jsp");
                }
            } else {
                out.print("<script language='javaScript'> alert('密码错误');</script>");
                response.setHeader("refresh", "0;url=login.jsp");
            }
        } else {
            out.print("<script language='javaScript'> alert('账号错误——else');</script>");
            response.setHeader("refresh", "0;url=login.jsp");
        }
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
    finally {
        rs.close();
        stat.close();
        conn.close();
    }
%>
</body>
</html>
