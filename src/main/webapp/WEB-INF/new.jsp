<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New CV</title>
</head>
<body>
<h1>Create new CV</h1><br>

<form action="/" method="post">
    First name:
    <input type="text" name="firstName" value="Vasyl"><br><br>
    Last name:
    <input type="text" name="lastName" value="Lelek"><br><br>
    Birthday:
    <input type="date" name="birthday" value="1985-07-26"><br><br>
    Phone Number:
    <input type="tel" name="phoneNumber" value="0953982280"><br><br>
    Address:
    <input type="text" name="address" value="Ukraine"><br><br>
    E-mail:
    <input type="email" name="eMail" value="itevas@ukr.net"><br><br>

    <div id="job_place" hidden="hidden">
    Job Place<br><br>
    Company:
    <input type="text" name="company"><br><br>
    City:
    <input type="text" name="city"><br><br>
    From:
    <input type="date" name="from"><br><br>
    To:
    <input type="date" name="to"><br><br>
    Position:
    <input type="text" name="position"><br><br>
    </div>

    <div id="job_place2" hidden="hidden">
        Job Place<br><br>
        Company:
        <input type="text" name="company2"><br><br>
        City:
        <input type="text" name="city2"><br><br>
        From:
        <input type="date" name="from2"><br><br>
        To:
        <input type="date" name="to2"><br><br>
        Position:
        <input type="text" name="position2"><br><br>
    </div>

    <div id="job_place3" hidden="hidden">
        Job Place<br><br>
        Company:
        <input type="text" name="company3"><br><br>
        City:
        <input type="text" name="city3"><br><br>
        From:
        <input type="date" name="from3"><br><br>
        To:
        <input type="date" name="to3"><br><br>
        Position:
        <input type="text" name="position3"><br><br>
    </div>


    <input type="submit" value="Preview">

</form>

<button id="jp_button"
        onclick="
if (job_place.hidden) {job_place.hidden=false}
else if (job_place2.hidden) {job_place2.hidden=false}
else if (job_place3.hidden) {job_place3.hidden=false; jp_button.hide()}
">Add job place</button><br><br>

</body>
</html>
