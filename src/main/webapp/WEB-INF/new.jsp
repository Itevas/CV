<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New CV</title>
</head>
<body>
<h1>Create new CV</h1><br>

<form action="/" method="post">
    First name:<br>
    <input type="text" name="firstName" value="Vasyl"><br><br>
    Last name:<br>
    <input type="text" name="lastName" value="Lelek"><br><br>
    Birthday:<br>
    <input type="date" name="birthday" value="1985-07-26"><br><br><hr><br>
    Phone Number:<br>
    <input type="tel" name="phoneNumber" value="0953982280"><br><br>
    Address:<br>
    <input type="text" name="address" value="Ukraine"><br><br>
    E-mail:<br>
    <input type="email" name="eMail" value="itevas@ukr.net"><br><br><hr><br>
    Job Place<br><br>
    Company:<br>
    <input type="text" name="company"><br><br>
    City:<br>
    <input type="text" name="city"><br><br>
    From:<br>
    <input type="date" name="from"><br><br>
    To:<br>
    <input type="date" name="to"><br><br>
    Position:<br>
    <input type="text" name="position"><br><br>
    <input type="submit" value="Preview">

</form>



</body>
</html>
