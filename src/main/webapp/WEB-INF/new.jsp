<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>New CV</title>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>

        .sidenav {
            height: 100%;
            width: 400px;
            position: fixed;
            z-index: 1;
            top: 0;
            left: 0;
            background-color: #004cff;
            overflow-x: hidden;
            padding-top: 20px;
        }

        .sidenav  input{
            float: left;
            padding: 6px;
            margin-bottom: 12px;
            margin-left: 16px;
            font-size: 17px;
            border: none;
            width: 85%;
        }

        .sidenav .simpletext{
            color: #fcfcfc;
            font-size: 20px;
            margin-left: 16px;
        }

        .sidenav .headingtext{
            color: #fcfcfc;
            font-size: 27px;
            margin-left: 16px;
        }

        .main {
            margin-left: 400px;
            padding: 10px;
        }

        .card {
            box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);
            transition: 0.3s;
            background-color: #e2fcff;
            padding: 16px;
            margin-top: 16px;
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

<div class="sidenav">
    <div class="headingtext">Create CV</div><br>

    <div class="simpletext">fill the form:</div><br>

    <form class="form" action="${pageContext.request.contextPath}/" method="post">

        <input type="text" name="firstName" placeholder="First name"><br><br>
        <input type="text" name="lastName" placeholder="Last name"><br><br>
        <div class="simpletext">Birthday:</div>
        <input type="date" name="birthday" placeholder="Birthday"><br><br>

        <input type="tel" name="phoneNumber" placeholder="phone"><br><br>
        <input type="text" name="address" placeholder="address (street, city, country)"><br><br>
        <input type="email" name="eMail" placeholder="e-mail"><br><br>


        <input type="submit" value="Preview">

    </form>
</div>

<%//<td><a class="btn btn-info" href="${pageContext.request.contextPath}/person_info?personId=${person.id}" role="button">Full resume</a></td>%>

</body>
</html>
