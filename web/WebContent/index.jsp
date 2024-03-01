<%@ page import="java.util.List" %>
<%@ page import="Classes.News" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/WebContent/stable/head.jsp"%>
<body>
    <%@include file="/WebContent/stable/navBar.jsp"%>
    <div class="container">
        <div class="row row-cols-1 row-cols-md-2 g-4">
            <div class="col-sm-6 mb-3 mb-sm-0" style="display: grid; justify-content: space-between;width: 100%; grid-template-columns: repeat(2, 1fr);">
                <%
                    List<News> news = (List<News>) request.getAttribute("novosti");
                    if(news!=null){
                        for (News news1 : news){
                %>
                    <div class="card mt-3" style="width: 95%">
                        <div class="card-header">
                            <%=news1.getCategory().getName()%>
                        </div>
                        <div class="card-body">
                            <h5 class="card-title"><%=news1.getTitle()%></h5>
                            <p class="card-text"><%=news1.getPostDate()%></p>
                            <%if(user!=null){%>
                                <a href="/currentPage?idDetail=<%=news1.getId()%>" class="btn btn-primary">Show More</a>
                            <%} else { %>
                                <a href="/login" class="btn btn-primary">Show More</a>
                            <% } %>
                        </div>
                    </div>
                <%
                        }
                    }
                %>
            </div>
        </div>
    </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</html>
