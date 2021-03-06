<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>All CV preview</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <style>
        .sidenav {
            height: 100%;
            width: 300px;
            position: fixed;
            z-index: 1;
            top: 0;
            left: 0;
            background-color: #0019ff;
            overflow-x: hidden;
            padding-top: 20px;
        }
        .sidenav .list {
            margin-left: 80px;
            background-color: #0011af;
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
            background: #0011af;
            font-size: 17px;
            border: none;
            cursor: pointer;
        }
        .sidenav .search-container button:hover {
            background: #0019ff;
        }
        .main {
            margin-left: 300px;
            padding: 10px;
        }
        .main .list {
            display: none;
            height: 100%;
        }
        .main .list a {
            font-size: 25px;
            color: #0011af;
            text-decoration: none;
        }
        .card {
            box-shadow: 2px 4px 8px 2px #bcc4ff;
            transition: 0.3s;
            background-color: #ffffff;
            padding: 16px;
            margin-top: 16px;
        }
        .card:hover .list {
            display: inline-block;
        }
        .card:hover .list:hover a:hover {
            color: #bcc4ff;
        }
        .card:hover {
            box-shadow: 4px 8px 16px 4px #bcc4ff;
            background-color: #ffffff;
        }
        .column {
            float: left;
            width: 25%;
            align-items: flex-start;
        }
        .row:after {
            content: "";
            display: table;
            clear: both;
        }
        .avatar {
            padding: 16px;
        }
        #overlay {
            position: fixed;
            display: none;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: rgba(0, 0, 0, 0.5);
            z-index: 2;
            cursor: pointer;
        }
        .show{
            align-items: center;
            font-size: 25px;
        }
    </style>

</head>
<body>

<div class="sidenav">
    <a href="#" class="add">Add new cv</a>
    <%--<div class="search-container">--%>
        <%--<form action="${pageContext.request.contextPath}/">--%>
            <%--<input type="text" placeholder="Search.." name="search">--%>
            <%--<button type="submit"><i class="fa fa-search"></i></button>--%>
        <%--</form>--%>
    <%--</div>--%>
    <%--<a href="#">Sort by:</a>--%>
    <%--<div class="list">--%>
        <%--<a href="#">Name</a>--%>
        <%--<a href="#">Last Name</a>--%>
        <%--<a href="#">Skill</a>--%>
    <%--</div>--%>
</div>


<div class="main" align="center">


    <c:forEach items="${cvList}" var="cv">
    <tr>
        <td>
            <div class="card" id="${cv.id}" align="center" style="width: 85%">
                <div class="row">
                        <%--<div class="column">--%>
                        <%--<img src="${pageContext.request.contextPath}avatar/wanted.png" class="avatar" alt="avatar"--%>
                        <%--style="width: 30%" align="left">--%>
                        <%--</div>--%>
                    <div class="column" style="width: 25%">
                        <h3>${cv.person.firstName} ${cv.person.lastName}</h3>
                        <p> ${cv.person.age} </p>
                    </div>
                    <div class="column" style="width: 25%">
                        <c:forEach items="${cv.skills}" var="Skill">
    <tr>
        <td>
            <h4>${Skill.skill}</h4>
        </td>
    </tr>
    </c:forEach>
</div>
<div class="column" align="center" style="width: 25%">
    <div class="box" align="center">
        <p><b>Tel: ${cv.contact.phoneNumber}</b></p>
        <p><b>E-mail: ${cv.contact.eMail}</b></p>
    </div>
</div>

<div class="column" align="right">
    <div class="list">
        <input class="idHolder" value="${cv.id}" id="holder" hidden="hidden">
        <a href="#" class="deploy" id="${cv.id}">Preview</a><br>
        <a href="#" class="edit" id="${cv.id}">Edit</a><br>
        <a href="#" class="delete" id="${cv.id}">Delete</a><br>
        <a href="#">Export</a><br>
    </div>
</div>

</div>
</div>
</td>
</tr>
</c:forEach>

</div>

<script>
    $("[href].add").click(function () {
        location.replace("/add");
    })
</script>

<script>
    $("[href].edit").click(function () {
        var cv_id = $(this).attr("id");
        location.replace("/edit");
        $.ajax({
            type: "POST",
            url: "/edit",
            data: {id: cv_id}
        })

    })
</script>

<script>
    $("[href].delete").click(function () {
        var cv_id = $(this).attr("id");
        var ok = confirm("Are You really want to delete this CV? ");
        var card = document.getElementById(cv_id);
        if(ok) {
            $(card).hide();
            $.ajax({
                type: "DELETE",
                url: "/list",
                data: {id: cv_id},
                success: function (response) {
                    alert(response);
                }
            })
        }
    })
</script>

<div class="show" id="overlay" align="center">
    <div class="card" align="center" style="margin: 100px">
        <div class="row">
            <div class="column">
                <div id="person"></div>
                <div id="age"></div>
                <div id="phone"></div>
                <div id="address"></div>
                <div id="e-mail"></div>
                <div class="skills" id="skill"></div>
            </div>
        </div>
    </div>
</div>

</div>

<script>
    $("[href].deploy").click(function () {
        var cv_id = $(this).attr("id");
        $.ajax({
            type: "POST",
            url: "/list",
            data: {id : cv_id},
            success: function (response) {
                document.getElementById("overlay").style.display = "block";
                var preview = jQuery.parseJSON(response);
                var skills = preview.skills;
                var jobs = preview.jobPlaces;
                document.getElementById("person").innerHTML = preview.person.firstName +" "+ preview.person.lastName;
                document.getElementById("age").innerHTML = preview.person.age;
                document.getElementById("phone").innerHTML = preview.contact.phoneNumber;
                document.getElementById("address").innerHTML = preview.contact.address;
                document.getElementById("e-mail").innerHTML = preview.contact.eMail;
                document.getElementById("skill").innerHTML = response;
                // $.each(preview.skills, function (key, value) {
                //     document.getElementById("skill").innerHTML = value.skill+" ";
                // })
            }
        });
        $(document).keyup(function(e) {
            if (e.key === "Escape") {
                document.getElementById("overlay").style.display = "none";
            }
        });
    });
</script>

</body>
</html>