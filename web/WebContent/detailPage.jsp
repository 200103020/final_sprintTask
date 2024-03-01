<%@ page import="Classes.News" %>
<%@ page import="java.util.List" %>
<%@ page import="Classes.Comment" %>
<%@ page import="Classes.Categories" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/WebContent/stable/head.jsp"%>
<body>
<%@include file="/WebContent/stable/navBar.jsp"%>
    <div class="container">
        <%
            News news1 = (News) request.getAttribute("novosti");
            if(news1!=null){
                if (user!=null){
                    if(user.getRoleID().equals("1")){
        %>
        <form action="/update" method="post">
            <h1 class="modal-title fs-5 mt-3" id="staticBackdropLabel">News Detail</h1>
            <div class="mb-3">
                <input type="hidden" class="form-control" name="id" value="<%=news1.getId()%>">
            </div>
            <div class="mb-3">
                <label class="form-label">Title</label>
                <input type="text" class="form-control" name="title" placeholder="Title" value="<%=news1.getTitle()%>">
            </div>
            <div class="mb-3">
                <label class="form-label">Content</label>
                <textarea class="form-control" name="content" rows="3" ><%=news1.getContent()%></textarea>
            </div>
            <div class="mb-3">
                <label class="form-label">Categories</label>
                <select name="category" class="form-select">
                    <%List<Categories> categories = (List<Categories>) request.getAttribute("categories");
                        if(categories!=null){
                            for (Categories categories1 : categories){
                    %>
                    <option value="<%=categories1.getId()%>"><%=categories1.getName()%></option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>
            <div class="mb-3">
                <label class="form-label">Post Date: <%=news1.getPostDate()%></label>
                <input type="hidden" class="form-control"  style="background: beige; cursor: pointer" name="postdate" placeholder="Number of Category">
            </div>
            <div style="display: flex; align-items: center;">
                <button type="submit" class="btn btn-success" style="margin-right: 10px">Update</button>
                <form action="/delete" method="post">
                    <div class="mb-3">
                        <input type="hidden" class="form-control" name="id" value="<%=news1.getId()%>">
                    </div>
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
            <%
            } else if(user.getRoleID().equals("2")) {
                %>
                <div class="col-12 mt-2">
                    <div class="card" style="width: 66%; display: block; margin: 0 auto;">
                        <div class="card-body">
                            <p style="font-weight: bold"><%=news1.getCategory().getName()%></p>
                            <h3 class="mb-3"><%=news1.getTitle()%></h3>
                            <p><%=news1.getContent()%></p>
                            <p><%=news1.getPostDate()%></p>
                        </div>
                    </div>
                </div>
            <%
                    }
                       %>
            <%List<Comment> comments = (List<Comment>) request.getAttribute("comment");
                if(comments!=null){
                    for (Comment comment : comments){
            %>
            <hr style="width: 66%;display: block; margin: 0 auto;">
                <div class="card mb-2" style="width: 66%; display: block; margin: 0 auto; background: none; border: none">
                    <div class="card-body">
                        <p><%=comment.getUser().getEmail()%></p>
                        <h4 class="mb-3" style="font-weight: bold; font-size: 22px"><%=comment.getComment()%></h4>
                        <p style="font-size: 13px">at <%=comment.getPostDate()%></p>
                    </div>
                </div>
            <%
                    }
                }
            %>
                <form action="/comment" method="post">
                    <div class="mb-3">
                        <label class="form-label">Comments</label>
                        <textarea type="text" class="form-control"  name="comment" placeholder="Your comment..." ></textarea>
                    </div>
                    <div class="mb-3">
                        <input type="hidden" class="form-control"  name="user_id" value="<%=user.getId()%>" >
                        <input type="hidden" class="form-control"  name="news_id" value="<%=news1.getId()%>" >
                    </div>
                    <div class="mb-3">
                        <button type="submit" class="btn btn-primary">Send</button>
                    </div>
                </form>
            <%
                }
            }
            %>
        </form>
    </div>
</body>
</html>
