<%@ page import="Classes.User" %>
<%@ page import="java.util.ArrayList" %>
<nav class="navbar navbar-expand-lg bg-secondary">
    <div class="container">
        <a class="navbar-brand fw-bold text-light" href="/home">Bitlab News</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active text-light" aria-current="page" href="/home">Home</a>
                </li>
                <%User user = (User) request.getSession().getAttribute("exist");
                    if(user!= null && user.getRoleID().equals("1")){
                %>
                <li class="nav-item">
                    <a class="nav-link text-light" href="/detail">Admin Panel</a>
                </li>

                <%
                    }
                %>
                <%if (user!=null){
                    %>
                    <li class="nav-item">
                        <a class="nav-link text-light" href="/profile">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-light" href="/logout">Log Out</a>
                    </li>
                <%
                    } else {
                    %>
                    <li class="nav-item">
                        <a class="nav-link text-light" href="/login">Sign In</a>
                    </li>
                <%
                    }
                %>
            </ul>
        </div>
    </div>
</nav>