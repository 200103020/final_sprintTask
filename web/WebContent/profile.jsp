<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/WebContent/stable/head.jsp"%>
<body>
<%@include file="/WebContent/stable/navBar.jsp"%>
<div class="container">
    <%
        if(user!= null){
            %>
            <h2 class="mt-3" style="display: block; text-align: center;">Hello, <%=user.getFullName()%></h2>
            <form style="width: 50%; margin: 0 auto; padding: 30px" class="mt-4 bg-body-secondary" method="post" action="/profile">
                <div class="mb-3" style="cursor: none">
                    <input type="hidden" class="form-control" name="id" value="<%=user.getId()%>">
                </div>
                <div class="mb-3" style="cursor: none">
                    <label for="exampleInputEmail1" class="form-label">Email address</label>
                    <input type="email" class="form-control" style="background: beige; cursor: pointer" id="exampleInputEmail1" aria-describedby="emailHelp" readonly="readonly" name="email" value="<%=user.getEmail()%>">
                </div>
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">FullName</label>
                    <input type="text" class="form-control" id="exampleInputFullname" aria-describedby="emailHelp" name="fullname" value="<%=user.getFullName()%>">
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Password</label>
                    <input type="password" class="form-control" id="exampleInputPassword1" name="password" value="<%=user.getPassword()%>">
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Retype Password</label>
                    <input type="password" class="form-control" id="exampleInputRePassword1" name="repassword">
                </div>
                <div class="mb-3">
                    <input type="hidden" class="form-control" id="exampleInputRole" name="role" value="<%=user.getRoleID()%>">
                </div>
                <button type="submit" class="btn btn-primary bg-success">Update</button>
            </form>
    <%
        }
    %>
</div>
</body>
</html>
