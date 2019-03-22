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
    </div>

    <div>
<h3>${jobPlaces}</h3>
<p>${jobPlace2.company2}
<p>${jobPlace2.city2}
<p>${fromTxt2} ${jobPlace2.from2}
<p>${toTxt2} ${jobPlace2.to2}
${jobPlace2.position2}
    </div>

    <div>
<p>${jobPlace1.company1}
<p>${jobPlace1.city1}
<p>${fromTxt1} ${jobPlace1.from1}
<p>${toTxt1} ${jobPlace1.to1}
<p>${jobPlace1.position1}
    </div>

    <div>
<p>${jobPlace0.company0}
<p>${jobPlace0.city0}
<p>${fromTxt0} ${jobPlace0.from0}
<p>${toTxt0} ${jobPlace0.to0}
<p>${jobPlace0.position0}
        </div>

    <%
        CvFacade facade = new CvFacade(); //need to call Servlet with response "Added successfully"
    %>
    <button class="preview" onclick="window.location.href = '/'">Edit CV</button>
    <button onclick="<%facade.writeCvInTableFromTmpFile();%>">Submit CV</button>


</body>
</html>