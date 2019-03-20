<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New CV</title>
</head>
<body>
<h1>Create new CV</h1><br>

<form action="/action_page.php" target="_blank" method="get">
    First name:<br>
    <input type="text" name="firstName"><br><br>
    Last name:<br>
    <input type="text" name="lastName"><br><br>
    Birthday:<br>
    <input type="date" name="birthday"><br><br><hr><br>
    Phone Number:<br>
    <input type="tel" name="phoneNumber"><br><br>
    Address:<br>
    <input type="text" name="address"><br><br>
    E-mail:<br>
    <input type="email" name="eMail"><br><br><hr><br>
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
