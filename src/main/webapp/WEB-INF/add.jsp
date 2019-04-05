<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>New CV</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
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

    <form class="form" id="form" action="add" method="post">

        <input type="number" name="id" hidden="hidden" value="-1">
        <input type="text" name="firstName" placeholder="First name" value="${cv.person.firstName}"><br><br>
        <input type="text" name="lastName" placeholder="Last name" value="${cv.person.lastName}"><br><br>
        <div class="simpletext">Birthday:</div>
        <input type="date" name="birthday" placeholder="Birthday" value="${cv.person.birthday}"><br><br>

        <input type="tel" name="phoneNumber" placeholder="phone" value="${cv.contact.phoneNumber}"><br><br>
        <input type="text" name="address" placeholder="address (street, city, country)"
               value="${cv.contact.address}"><br><br>
        <input type="email" name="eMail" placeholder="e-mail" value="${cv.contact.eMail}"><br><br><br>

        <div class="simpletext">Skills:</div>
        <div class="smalltext">hold ctrl to select multiple items</div>
        <select multiple="multiple" size="4" class="drop" name="skills[]" form="form">
            <option value="Java">Java</option>
            <option value="C#">C#</option>
            <option value="SQL">SQL</option>
            <option value="Python">Python</option>
            <option value="JavaScript">JavaScript</option>
            <option value="PHP">PHP</option>
            <option value="C++">C++</option>
            <option value="TypeScript">TypeScript</option>
            <option value="Swift">Swift</option>
            <option value="Ruby">Ruby</option>
            <option value="Kotlin">Kotlin</option>
            <option value="C">C</option>
            <option value="Scala">Scala</option>
            <option value="HTML">HTML</option>
            <option value="CSS">CSS</option>
        </select>


        <div class="job_place" id="job0" style="display: none">
            <div class="simpletext">Job Place #1</div>
            <input type="text" name="company0" placeholder="company"><br>
            <input type="text" name="city0" placeholder="city"><br>
            <div class="simpletext">From:</div>
            <input type="date" name="from0"><br><br><br>
            <div class="simpletext">To:</div>
            <input type="date" name="to0"><br>
            <div class="simpletext">Position:</div>
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
            <input type="submit" form="form" value="Submit">
        </div>
    </form>
</div>

<div class="main" align="center" hidden="hidden">

    <div class="card">
        <div class="row" align="center">
            <div class="column" hidden="hidden">
                <img src="${url}" class="avatar" alt="photo" style="width: 50%" align="left">
            </div>

            <div class="column">
                <h1>${cv.person.firstName} ${cv.person.lastName}</h1>
                <h4>${cv.person.birthday} ${age}</h4>
            </div>
            <div class="column">
                <h3>Skills: </h3>
                <c:forEach items="${cv.skills}" var="skills">
                    <tr>
                        <td>
                            <p>${skills.skill}</p>
                        </td>
                    </tr>
                </c:forEach>
            </div>

            <div class="column">
                <h3>Contacts: </h3>
                <p>${cv.contact.phoneNumber}</p>
                <p>${cv.contact.address}</p>
                <p>${cv.contact.eMail}</p>
            </div>
            <div class="row" align="center">
                <c:forEach items="${cv.jobPlaces}" var="jobPlace">
                    <tr>
                        <td>
                            <div class="column">
                                <h3>Job place: </h3>
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

</body>
</html>