<%-- 
    Document   : sale
    Created on : Jan 9, 2019, 9:54:09 PM
    Author     : Vu
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/sales.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="center">
            <%@include file="header.jsp" %>

            <div class="main-content">
                <article class="main">
                    <div class="margin">
                        <div class="article">
                            <span class="h2 font-bold">Machines for Sale</span>
                            <c:forEach items="${listA}" var="o">                                
                                    <section class="info">                                       
                                        <img src="img/${o.imglink}" class="img-sale"/>
                                        <a class="sale-title" href="DetailControl?id=${o.id}">${o.title}</a>
                                        <p>${o.fullDescription}</p>
                                    </section>                              
                            </c:forEach>
                        </div>
                    </div>
                    <div class="paging">
                        <c:forEach begin="1" end="${end}" var="i">
                            <a href="SaleControl?index=${i}" class="${i==indexPage?"active":""}" >
                                ${i}
                            </a>
                        </c:forEach>
                    </div>
                </article>
                <%@include file="aside.jsp" %>
            </div>
            <%@include file="footer.jsp" %>
        </div>
    </body>
</html>

