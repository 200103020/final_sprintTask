<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@include file="/WebContent/stable/head.jsp"%>
<body>
<%@include file="/WebContent/stable/navBar.jsp"%>
<div class="container">
    <%

        if (request.getParameter("error")!=null){
    %>

    <div class="alert alert-danger d-flex align-items-center mt-3" role="alert" style="width: 50%; margin: 0 auto; padding: 15px">
        <div>
            This user already exist!
        </div>
    </div>
    <%
        }
    %>
    <form style="width: 50%; margin: 0 auto; padding: 30px" class="mt-4 bg-body-secondary" method="post" action="/register">
        <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">Email address</label>
            <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email">
        </div>
        <div class="mb-3">
            <label for="exampleInputEmail1" class="form-label">FullName</label>
            <input type="text" class="form-control" id="exampleInputFullname" aria-describedby="emailHelp" name="fullname">
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Password</label>
            <input type="password" class="form-control" id="exampleInputPassword1" name="password">
        </div>
        <div class="mb-3">
            <label for="exampleInputPassword1" class="form-label">Retype Password</label>
            <input type="password" class="form-control" id="exampleInputRePassword1" name="repassword">
        </div>
        <button type="submit" class="btn btn-primary bg-success">Sign Up</button>
    </form>
    <a href="/login" class="mt-3" style="text-align: center; text-decoration: underline; color: #000;display: block;">
        Have an account?
    </a>
</div>
</body>
</html>
