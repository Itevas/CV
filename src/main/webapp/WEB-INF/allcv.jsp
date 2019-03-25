<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>All CV preview</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <style>

        .sidenav {
            height: 100%;
            width: 300px;
            position: fixed;
            z-index: 1;
            top: 0;
            left: 0;
            background-color: #004cff;
            overflow-x: hidden;
            padding-top: 20px;
        }

        .sidenav .list {
            margin-left: 80px;
            background-color: #1044bc;
        }

        .sidenav .list a {
            font-size: 17px;
            color: #d5e2e2;
        }

        .sidenav a {
            padding: 6px 8px 6px 16px;
            text-decoration: none;
            font-size: 25px;
            color: #fcfcfc;
            display: block;
        }

        .sidenav a:hover {
            color: #c9c9c9;
        }

        .sidenav .search-container {
            float: left;
            margin-bottom: 20px;
            margin-left: 16px;
        }

        .sidenav input[type=text] {
            padding: 6px;
            margin-top: 8px;
            font-size: 17px;
            border: none;
        }

        .sidenav .search-container button {
            float: right;
            padding: 6px 10px;
            margin-top: 8px;
            margin-right: 16px;
            background: #447dff;
            font-size: 17px;
            border: none;
            cursor: pointer;
        }

        .sidenav .search-container button:hover {
            background: #1044bc;
        }

        .main {
            margin-left: 300px;
            padding: 10px;
        }

        .card {
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            transition: 0.3s;
            background-color: #f2f6ff;
            padding: 16px;
            margin-top: 16px;
        }

        .card:hover {
            box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
            background-color: #e2ebff;
        }

        .column {
            float: left;
            width: 33%;
        }

        .row:after {
            content: "";
            display: table;
            clear: both;
        }

        .avatar {
            padding: 16px;
        }


    </style>

</head>
<body>

<%--<h1>List of candidates</h1>--%>
<%--<p>${cv}--%>

<div class="sidenav">
    <div class="search-container">
        <form action="${pageContext.request.contextPath}/">
            <input type="text" placeholder="Search.." name="search">
            <button type="submit"><i class="fa fa-search"></i></button>
        </form>
    </div>
    <a href="#">Sort by:</a>
    <div class="list">
        <a href="#">Name</a>
        <a href="#">Last Name</a>
        <a href="#">Position</a>
    </div>
</div>


<div class="main" align="center">

    <div class="card" align="center" style="width: 85%">
        <div class="row">
            <div class="column">
                <%--<img src="${pageContext.request.contextPath}avatar/homer.png" class="avatar" alt="avatar" style="width: 30%" align="left">--%>
            </div>
            <div class="column">
                <h2><b>Homer Simpson</b></h2>
                <p>53 years old</p>
            </div>
            <div class="column">
                <h2>Developer</h2>
            </div>
        </div>
    </div>


    <div class="card" align="center" style="width: 85%">
        <div class="row">
            <div class="column">
                <%--<img src="${pageContext.request.contextPath}avatar/neo.png" class="avatar" alt="avatar" style="width: 30%" align="left">--%>
            </div>
            <div class="column">
                <h2><b>Thomas Anderson</b></h2>
                <p>53 years old</p>
            </div>
            <div class="column">
                <h2>Developer</h2>
            </div>
        </div>
    </div>

    <div class="card" align="center" style="width: 85%">
        <div class="row">
            <div class="column">
                <%--<img src="${pageContext.request.contextPath}avatar/wanted.png" class="avatar" alt="avatar" style="width: 30%" align="left">--%>
            </div>
            <div class="column">
                <h2><b>John Golt</b></h2>
                <p>53 years old</p>
            </div>
            <div class="column">
                <h2>Developer</h2>
            </div>
        </div>
    </div>

    <div class="card" align="center" style="width: 85%">
        <div class="row">
            <div class="column">
                <%--<img src="${pageContext.request.contextPath}avatar/wanted.png" class="avatar" alt="avatar" style="width: 30%" align="left">--%>
            </div>
            <div class="column">
                <h2><b>John Golt</b></h2>
                <p>53 years old</p>
            </div>
            <div class="column">
                <h2>Developer</h2>
            </div>
        </div>
    </div>

    <div class="card" align="center" style="width: 85%">
        <div class="row">
            <div class="column">
                <%--<img src="${pageContext.request.contextPath}avatar/wanted.png" class="avatar" alt="avatar" style="width: 30%" align="left">--%>
            </div>
            <div class="column">
                <h2><b>John Golt</b></h2>
                <p>53 years old</p>
            </div>
            <div class="column">
                <h2>Developer</h2>
            </div>
        </div>
    </div>

    <div class="card" align="center" style="width: 85%">
        <div class="row">
            <div class="column">
                <%--<img src="${pageContext.request.contextPath}avatar/wanted.png" class="avatar" alt="avatar" style="width: 30%" align="left">--%>
            </div>
            <div class="column">
                <h2><b>John Golt</b></h2>
                <p>53 years old</p>
            </div>
            <div class="column">
                <h2>Developer</h2>
            </div>
        </div>
    </div>

    <div class="card" align="center" style="width: 85%">
        <div class="row">
            <div class="column">
                <%--<img src="${pageContext.request.contextPath}avatar/wanted.png" class="avatar" alt="avatar" style="width: 30%" align="left">--%>
            </div>
            <div class="column">
                <h2><b>John Golt</b></h2>
                <p>53 years old</p>
            </div>
            <div class="column">
                <h2>Developer</h2>
            </div>
        </div>
    </div>

    <div class="card" align="center" style="width: 85%">
        <div class="row">
            <div class="column">
                <%--<img src="${pageContext.request.contextPath}avatar/wanted.png" class="avatar" alt="avatar" style="width: 30%" align="left">--%>
            </div>
            <div class="column">
                <h2><b>John Golt</b></h2>
                <p>53 years old</p>
            </div>
            <div class="column">
                <h2>Developer</h2>
            </div>
        </div>
    </div>
    <div class="card" align="center" style="width: 85%">
        <div class="row">
            <div class="column">
                <%--<img src="${pageContext.request.contextPath}avatar/wanted.png" class="avatar" alt="avatar" style="width: 30%" align="left">--%>
            </div>
            <div class="column">
                <h2><b>John Golt</b></h2>
                <p>53 years old</p>
            </div>
            <div class="column">
                <h2>Developer</h2>
            </div>
        </div>
    </div>
    <div class="card" align="center" style="width: 85%">
        <div class="row">
            <div class="column">
                <%--<img src="${pageContext.request.contextPath}avatar/wanted.png" class="avatar" alt="avatar" style="width: 30%" align="left">--%>
            </div>
            <div class="column">
                <h2><b>John Golt</b></h2>
                <p>53 years old</p>
            </div>
            <div class="column">
                <h2>Developer</h2>
            </div>
        </div>
    </div>


</div>

</body>
</html>