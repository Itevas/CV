<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>New CV</title>
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>

        .sidenav {
            height: 100%;
            width: 400px;
            position: fixed;
            z-index: 1;
            top: 0;
            left: 0;
            background-color: #0019ff;
            overflow-x: hidden;
            padding-top: 20px;
        }

        .sidenav input {
            float: left;
            padding: 6px;
            margin-bottom: 12px;
            margin-left: 16px;
            font-size: 17px;
            border: none;
            width: 90%;
        }

        .sidenav input[type=submit] {
             float: left;
             padding: 6px;
             margin-bottom: 12px;
             margin-left: 16px;
             font-size: 17px;
             border: none;
             width: 50%;
             background-color: #0011af;
             color: #fcfcfc;
         }

        .sidenav .drop{
            float: left;
            padding: 6px;
            margin-bottom: 12px;
            margin-left: 16px;
            font-size: 17px;
            border: none;
            width: 90%;
        }

        .sidenav .simpletext {
            color: #fcfcfc;
            font-size: 20px;
            margin-left: 16px;
        }

        .sidenav .smalltext {
            color: #fcfcfc;
            font-size: 14px;
            margin-left: 16px;
        }

        .sidenav .marg {
            margin: 16px;
        }

        .sidenav a {
            color: #fcfcfc;
            font-size: 20px;
            margin-left: 16px;
            margin-top: 16px;
            margin-bottom: 16px;
            text-decoration: none;
            display: block;
        }

        .sidenav .headingtext {
            color: #fcfcfc;
            font-size: 27px;
            margin-left: 16px;
        }

        .main {
            margin-left: 400px;
            padding: 10px;
        }

        .card {
            box-shadow: 2px 4px 8px 2px #bcc4ff;
            transition: 0.3s;
            background-color: #ffffff;
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
    <div class="headingtext">Create CV</div>
    <br>

    <div class="simpletext">fill the form:</div>
    <br>

    <form class="form" id="form" action="${pageContext.request.contextPath}/" method="post">

        <input type="text" name="firstName" placeholder="First name" value="${cvFields.person.firstName}"><br><br>
        <input type="text" name="lastName" placeholder="Last name" value="${cvFields.person.lastName}"><br><br>
        <div class="simpletext">Birthday:</div>
        <input type="date" name="birthday" placeholder="Birthday" value="${cvFields.person.birthday}"><br><br>

        <input type="tel" name="phoneNumber" placeholder="phone" value="${cvFields.contact.phoneNumber}"><br><br>
        <input type="text" name="address" placeholder="address (street, city, country)"
               value="${cvFields.contact.address}"><br><br>
        <input type="email" name="eMail" placeholder="e-mail" value="${cvFields.contact.eMail}"><br><br><br>


        <div class="job_place" id="job0" style="display: none">
            <div class="simpletext">Job Place #1</div>
            <input type="text" name="company0" placeholder="company"><br>
            <input type="text" name="city0" placeholder="city"><br>
            <div class="simpletext">From:</div>
            <input type="date" name="from0"><br><br><br>
            <div class="simpletext">To:</div>
            <input type="date" name="to0"><br>
            <select class="drop" name="position0" form="form">
                <option value="">select position</option>
                <option value="Developer">Developer</option>
                <option value="DevOps">DevOps</option>
                <option value="QAEngineer">QAEngineer</option>
            </select>

        </div>
        <div class="job_place" id="job1" style="display: none">
            <div class="simpletext">Job Place #2</div>
            <input type="text" name="company1" placeholder="company"><br>
            <input type="text" name="city1" placeholder="city"><br>
            <div class="simpletext">From:</div>
            <input type="date" name="from1"><br><br><br>
            <div class="simpletext">To:</div>
            <input type="date" name="to1"><br>
            <select class="drop" name="position1" form="form">
                <option value="">select position</option>
                <option value="Developer">Developer</option>
                <option value="DevOps">DevOps</option>
                <option value="QAEngineer">QAEngineer</option>
            </select>

        </div>
        <div class="job_place" id="job2" style="display: none">
            <div class="simpletext">Job Place #3</div>
            <input type="text" name="company2" placeholder="company"><br>
            <input type="text" name="city2" placeholder="city"><br>
            <div class="simpletext">From:</div>
            <input type="date" name="from2"><br><br><br>
            <div class="simpletext">To:</div>
            <input type="date" name="to2"><br>
            <select class="drop" name="position2" form="form">
                <option value="">select position</option>
                <option value="Developer">Developer</option>
                <option value="DevOps">DevOps</option>
                <option value="QAEngineer">QAEngineer</option>
            </select>
        </div>

        <div class="smalltext" id="three_j" style="display: none">you can add up to three places of work</div>
        <br>
        <a href="#new_job-place" id="butt" style="display: initial"
           onclick=show()>Add job place..</a><br>

        <script>
            function show() {
                var x = document.getElementById("job0");
                var y = document.getElementById("job1");
                var z = document.getElementById("job2");
                var b = document.getElementById("butt");
                var t = document.getElementById("three_j");

                if (window.getComputedStyle(x).display === "none") {
                    x.style.display = "initial";
                    t.style.display = "initial";
                } else if (window.getComputedStyle(y).display === "none") {
                    y.style.display = "initial";
                } else if (window.getComputedStyle(z).display === "none") {
                    z.style.display = "initial";
                    b.style.display = "none";
                }
            }
        </script>

        <div class="marg">
            <input type="submit" value="Preview">
        </div>
    </form>
</div>


<div class="main" align="center">

    <div class="card">
        <div class="row">

            <div class="column" hidden="hidden">
                <img src="${url}" class="avatar" alt="photo" style="width: 50%" align="left">
            </div>

            <div class="column">

                <div>
                    <h1>${cv.person.firstName} ${cv.person.lastName}</h1>
                    <h4>${cv.person.birthday} ${age}</h4>
                </div>

                <div>
                    <h3>${contact}</h3>
                    <p>${cv.contact.phoneNumber}</p>
                    <p>${cv.contact.address}</p>
                    <p>${cv.contact.eMail}</p>
                </div>

                <c:forEach items="${cv.jobPlaces}" var="jobPlace">
                    <tr>
                        <td>
                            <div class="column">
                                <h3>${jobPlaces}</h3>
                                <p>${jobPlace.company}</p>
                                <p>${jobPlace.city}</p>
                                <p>${fromTxt} ${jobPlace.from}</p>
                                <p>${toTxt} ${jobPlace.to}</p>
                                <p>${jobPlace.position}</p>
                            </div>
                        </td>
                    </tr>
                </c:forEach>

            </div>
        </div>
    </div>

</div>


<%//<td><a class="btn btn-info" href="${pageContext.request.contextPath}/person_info?personId=${person.id}" role="button">Full resume</a></td>%>

</body>
</html>
