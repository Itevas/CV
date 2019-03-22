<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import = "com.lelek.cv.service.CvFacade" %>
<html>
<head>
    <title>CV</title>
</head>
<body>

    <div>
<h1>${cv.person.firstName} ${cv.person.lastName}</h1>
<h4>${cv.person.birthday} (${age})</h4>
    </div>

    <div>
<h3>Contacts:</h3>
<p>${cv.contact.phoneNumber}
<p>${cv.contact.address}
    <p>${cv.contact.eMail}
    </div>

    <div>
<h3>${jobPlaces}</h3>
<p>${cv.jobPlaces[0].company}
<p>${cv.jobPlaces[0].city}
<p>${fromTxt} ${cv.jobPlaces[0].from}
<p>${toTxt} ${cv.jobPlaces[0].to}
<p>${cv.jobPlaces[0].position}
    </div>

    <%
        CvFacade facade = new CvFacade(); //need to call Servlet with response "Added successfully"
    %>
    <button onclick="window.location.href = '/'">Edit CV</button>
    <button onclick="<%facade.writeCvInTableFromTmpFile();%>">Submit CV</button>


</body>
</html>