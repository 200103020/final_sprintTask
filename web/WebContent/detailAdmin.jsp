<%@ page import="Classes.News" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/WebContent/stable/head.jsp"%>
<body>
    <%@include file="/WebContent/stable/navBar.jsp"%>
    <div class="container">
        <%@include file="/WebContent/stable/modal.jsp"%>
        <table class="table mt-3">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Title</th>
                <th scope="col">Content</th>
                <th scope="col">Category</th>
                <th scope="col">Date</th>
                <th scope="col">Details</th>
            </tr>
            </thead>
            <tbody>
            <% int count = 0;
                List<News> news = (List<News>) request.getAttribute("novosti");
                if(news!=null){
                    for (News news1 : news){
            %>
            <tr>
                <%count++;%>
                <th><%=count%></th>
                <td><%=news1.getTitle()%></td>
                <td><%=news1.getContent()%></td>
                <td><%=news1.getCategory().getName()%></td>
                <td><%=news1.getPostDate()%></td>
                <td><a class="btn btn-small bg-dark text-light" href="/currentPage?idDetail=<%=news1.getId()%>">Details</a></td>
            </tr>
            <% }
            }%>
            </tbody>
        </table>
    </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</html>
