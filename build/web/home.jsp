<%-- 
    Document   : home
    Created on : Jan 9, 2019, 8:17:14 PM
    Author     : Vu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <title>Home</title>
    </head>
    <body>
        <div class="center">
            <%@include file="header.jsp" %>
            <div class="main-content">
                <article class="main">
                    <div class="margin">
                        <section class="article">
                            <div>
                                <span class="h2 font-bold">${introarticle.title}</span>
                            </div>
                            <br>
                            <span class="h4 font-bold">${introarticle.shortDescription}</span>
                            <p>${introarticle.fullDescription}</p>
                            <div class="img-intro-border">
                                <img src="img/${introarticle.imglink}" alt=""/>
                            </div>
                        </section>
                        <section class="article">
                            <c:forEach items="${list}" var="o">                     
                                    <div class="inline">
                                        <div>
                                            <img src="img/${o.imglink}" alt=""/>
                                        </div>
                                        <span class="h3 font-bold"><a href="DetailControl?id=${o.id}">${o.title}</a></span>
                                        <p>${o.shortDescription}</p>
                                    </div>                          
                                
                            </c:forEach>
                        </section>
                            
                    </div>
                    <div class="paging">
                        <c:forEach begin="1" end="${end}" var="i">
                            <a href="HomeControl?index=${i}" class="${i==indexPage?"active":""}">${i}</a>
                        </c:forEach>
                    </div>                             
                </article>
                <%@include file="aside.jsp" %>
            </div>

            <%@include file="footer.jsp" %>
        </div>
    </body>
</html>
