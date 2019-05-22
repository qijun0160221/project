<%@page import="java.util.* , java.io.* " pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<html>
<head>
    <title>Insert title here</title>
</head>
<body>

<%
    Connection conn=null;
    Statement stat=null;
    ResultSet rs=null;
    try {
       String username = (String) request.getParameter("username");
       String userpwd = (String) request.getParameter("userpwd");
       Class.forName("com.mysql.jdbc.Driver");
       //下面是数据库操作
        conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_9griddiary?user=root&password=root&useUnicode=true");
      stat = conn.createStatement();
       String sql = "select * from th_user where username='" + username + "'";
       //定义一个查询语句
        rs = stat.executeQuery(sql);
       System.out.println(username + " " + userpwd);
        if (rs.next()) {
            out.print("<script language='javaScript'> alert('该用户已被注册');</script>");
            response.setHeader("refresh", "0;url=zhuce.jsp");
        } else {
            out.print("<script language='javaScript'> alert('注册成功');</script>");
            String sql1 = "insert into th_user(username,userpwd)values('" + username + "','" + userpwd + "')  ";
            //定义一个查询语句
            int count = stat.executeUpdate(sql1);
            if(count>0)
            {
                System.out.println("添加成功");
            }else{
                System.out.println("添加失败");
            }
            if (request.getParameter("username") != null) {
                session.setAttribute("username", request.getParameter("username"));
                session.setAttribute("userpwd", request.getParameter("userpwd"));
                session.setAttribute("car", new TreeMap<String, Integer>());
                response.sendRedirect("index.jsp");
            }
        }

   }catch (Exception e)
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
