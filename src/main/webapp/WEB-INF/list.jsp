<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>All CV preview</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/ext-core/3.1.0/ext-core.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
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

        .show p{
            color: #ffffff;
        }

    </style>

</head>
<body>

<div class="sidenav">
</div>

<div class="main" align="center">
    <c:forEach items="${cvList}" var="cv">
        <tr>
            <td>
                <div class="card" id="${cv.id}" align="center" style="width: 85%">
                    <div class="row">
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
                                <a href="#" class="delete" id="${cv.id}">Delete</a><br>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
    </c:forEach>

</div>

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

</body>
</html>