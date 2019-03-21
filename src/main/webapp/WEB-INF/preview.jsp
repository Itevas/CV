<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "com.lelek.cv.service.CvFacade" %>
<html>
<head>
    <title>CV</title>
</head>
<body>
<div>
<h1>${person.firstName} ${person.lastName}</h1>
<h4>${person.birthday} (${age})</h4>
</div>

<div>
<h3>Contacts:</h3>
<p>${contact.phoneNumber}
<p>${contact.address}
<p>${contact.eMail}
</p>

<div>
<h3>${jobPlaces}</h3>

<p>${company2}
<p>${city2}
<p>${fromTxt2} ${from2}
<p>${toTxt2} ${to2}
<div>${position2}
</div>
    ${line2}

<p>${company1}
<p>${city1}
<p>${fromTxt1} ${from1}
<p>${toTxt1} ${to1}
<p>${position1}

    ${line1}

<p>${company0}
<p>${city0}
<p>${fromTxt0} ${from0}
<p>${toTxt0} ${to0}
<p>${position0}

${line0}
    <%
        CvFacade facade = new CvFacade();
    %>
    <button onclick="window.location.href = '/'">Edit CV</button>
    <button onclick="<%facade.writeCvInTableFromTmpFile();%>">Submit CV</button>


</body>
</html>