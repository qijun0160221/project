<%@page import="java.util.*,java.io.*" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>我的购物车</title>
</head>
<body>

<div id="container" style="width:100%">
<p>欢迎 <%=session.getAttribute("username")%>

    <p style="font-family:arial;color:dodgerblue;font-size:40px;">去<a href="mall.jsp">商城</a></p>

<table border="1"width="80%" align="center">
    <tr>
        <td>商品名称</td>
        <td>单价</td>
        <td>数量</td>
        <td>总价</td>
    </tr>

    <tr>
            <%
				Map<String,Double> fruits=new TreeMap<String,Double>();
				fruits.put("Apple",2.5);
				fruits.put("Orange",3.5);
				fruits.put("Banana",4.5);
				fruits.put("Kiwi",7.5);
				fruits.put("Tomato",5.5);
				Map<String,Integer> car=new TreeMap<String,Integer>();
				if(session.getAttribute("car")!=null){
				car=(Map<String,Integer>)(session.getAttribute("car"));
				}
			double total=0;
			for(String f:car.keySet()){
					total+=fruits.get(f)*car.get(f);
				%>
    <tr>
        <td><%=f%></td>
        <td><%=fruits.get(f)%></td>
        <td><%=car.get(f)%></td>
        <td><%=fruits.get(f)*car.get(f)%></td>

    </tr>
    <%}%>

    </tr>


</table>
</br>
    <p>请复制这条单号</p>
    <form name="my_Form" method="POST">
        单号：<p>32152468517624124</p>
    </form>
    <div id="header" style="background-color:#fff;"align="right">
        <p style="font-family:arial;color:red;font-size:40px;"> 总价<%=total%></p>
        <p style="font-family:arial;color:dodgerblue;font-size:40px;">去<a href="pay.jsp">付款</a></p>
    </div>

</div>
</body>
</html>