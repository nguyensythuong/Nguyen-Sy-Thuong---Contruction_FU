<%-- 
    Document   : about
    Created on : Jan 9, 2019, 9:47:56 PM
    Author     : Vu
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/newcss.css" rel="stylesheet" type="text/css"/>
        <link href="css/about1.css" rel="stylesheet" type="text/css"/>
        <title>About Our Company</title>
    </head>
    <body>
        <div class="center">
            <%@include file="header.jsp" %>

            <div class="main-content">
                <article class="main">
                    <div class="margin">
                        <span class="h2 font-bold">${about.title}</span>
                        <div class="content">
                            <div>
                                <img src="img/${about.imglink}" alt=""/>
                            </div>
                            <p>${about.fullDescription}</p>
                        </div>
                    </div>
                </article>
                <%@include file="aside.jsp" %>
            </div>
            <%@include file="footer.jsp" %>
        </div>
    </body>
</html>

