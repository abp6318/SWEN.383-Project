<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${classCode} Lessons</title>
    <style>
        td{
            border:thin solid black;
            padding-left: 5px;
            padding-right: 15px;
        }
        th{
            text-align: left;
        }
        .nav a:hover{
            background-color: #F8D7FD;
            color: black;
        }
        .nav a.active {
            background-color: #F8D7FD;
            color: black;
        }
        .nav a {

          float: left;
          display: block;
          color: black;
          text-align: center;
          padding: 14px 16px;
          text-decoration: none;
          font-size: 17px;
        }
    </style>
</head>
<body>
    <div class="content">
        <nav class="nav" id ="myNav">
            <a href="/professor">Dashboard</a>
            <a href="/lecture" class="active">Lectures</a>
            <a href="/multimedia">Multimedia</a>
            <a href="/quiz">Quizzes</a>
            <a href="/messagesDG">Discussion Messages</a>
        </nav>
        <h2><#if classCode??>${classCode}</#if> Lessons</h2>

        <!-- List of lectures that professor has access to -->
        <div class="lectureList">
        <table>
                <tr>
                    <th>Lesson ID</th>
                    <th>Lesson Name</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Link</th>
                </tr>
                <#list allLessons as lesson>
                    <tr>
                            <td>${lesson.lessonID}</td>
                            <td>${lesson.lessonName}</td>
                            <td><#if lesson.startDate??>${lesson.startDate}</#if></td>
                            <td><#if lesson.endDate??>${lesson.endDate}</#if></td>
                            <td><form id="dis" action="/lecture" method="POST">
                                 <button id="toLesson" type="submit" value="${lesson.lessonID}" name="classButton">Materials</button><br>
                            </form><br></td>
                        </tr>
                 </#list>
            </table>
            </div>


        <form id="dis" action="/lecture" method="POST">
                <input id="addLesson" value="Add Lesson" type="button">
                <input id="deleteLesson" value="Delete Lesson" type="button">
                <input id="updateLessonTime" value="Update Lesson Time" type="button">
                <input id="addLessonTime" value="Add Lesson Time" type="button">
                <input id="deleteLessonTime" value="Delete Lesson Time" type="button"><br>
         </form>

        <#if allLectures??>
             <h2>Lectures</h2>
                 <table>
                     <tr>
                         <th>Lecture ID</th>
                         <th>Lecture Name</th>
                         <th>Mulimedia Link</th>
                         <th>LessonID</th>
                     </tr>
                     <#list allLectures as lecture>
                         <tr>
                             <td>${lecture.lectureID}</td>
                             <td>${lecture.lectureName}</td>
                             <td> <form id="lect" action="/lecture" method="POST">
                                   <button id="toLecture" type="submit" value="${lecture.multimedia}" name="lectButton">Link</button><br>
                             </form><br></td>
                             <td>${lecture.lessonID}</td>
                          </tr>
                     </#list>
                 </table>
             </#if>


             <#if multimediaLink??>
                 <h2>Content</h2>
                 <iframe src="${multimediaLink}"" title="Lesson Multimedia"></iframe>
             </#if>


    </div>
    <script>
        var aBtn = document.getElementById("addLesson");
        var dBtn = document.getElementById("deleteLesson");
        var uBtn = document.getElementById("updateLessonTime");
        var atBtn = document.getElementById("addLessonTime");
        var dtBtn = document.getElementById("deleteLessonTime");
        var f = document.getElementById("dis");
        aBtn.addEventListener("click", function() {

            var lessonName = document.createElement("input");
    		lessonName.setAttribute('type', 'text');
    		lessonName.setAttribute('placeholder', 'Enter Lesson Name');
            lessonName.name = "addLessonName";
            lessonName.required = true;
            f.appendChild(lessonName);

            var br = document.createElement("br");
    		f.appendChild(br);

            var start = document.createElement("input");
    		start.setAttribute('type', 'date');
    		start.setAttribute('placeholder', 'Enter Start Date');
            start.name = "addLessonStartTime";
            f.appendChild(start);

            var br1 = document.createElement("br");
    		f.appendChild(br1);

    		var end = document.createElement("input");
            end.setAttribute('type', 'date');
            end.setAttribute('placeholder', 'Enter Email');
            end.name = "addLessonEndTime";
            f.appendChild(end);

            var br2 = document.createElement("br");
            f.appendChild(br2);

            var submit = document.createElement("input");
    		submit.setAttribute('type','submit');
    		submit.className = "input-submit";
            f.appendChild(submit);
        });
        dBtn.addEventListener("click", function() {
            var lessonID = document.createElement("input");
            lessonID.setAttribute('type', 'text');
            lessonID.setAttribute('placeholder', 'Enter Lesson ID');
            lessonID.name = "deleteLessonID";
            lessonID.required = true;
            f.appendChild(lessonID);

            var br = document.createElement("br");
            f.appendChild(br);

            var submit = document.createElement("input");
            submit.setAttribute('type','submit');
            submit.className = "input-submit";
            f.appendChild(submit);
        });

        uBtn.addEventListener("click", function() {
            var lessonID = document.createElement("input");
            lessonID.setAttribute('type', 'text');
            lessonID.setAttribute('placeholder', 'Enter Lesson ID');
            lessonID.name = "updateTimeLessonID";
            lessonID.required = true;
            f.appendChild(lessonID);

            var br = document.createElement("br");
            f.appendChild(br);

            var start = document.createElement("input");
            start.setAttribute('type', 'date');
            start.setAttribute('placeholder', 'Enter New Start Time');
            start.name = "updatedStartTime";
            f.appendChild(start);

            var br1 = document.createElement("br");
            f.appendChild(br1);

            var end = document.createElement("input");
            end.setAttribute('type', 'date');
            end.setAttribute('placeholder', 'Enter New End Time');
            end.name = "updatedEndTime";
            f.appendChild(end);

            var br2 = document.createElement("br");
            f.appendChild(br2);

            var submit = document.createElement("input");
            submit.setAttribute('type','submit');
            submit.className = "input-submit";
            f.appendChild(submit);
        });

        dtBtn.addEventListener("click", function() {
           var lessonID = document.createElement("input");
           lessonID.setAttribute('type', 'text');
           lessonID.setAttribute('placeholder', 'Enter Lesson ID');
           lessonID.name = "deleteTimeLessonID";
           lessonID.required = true;
           f.appendChild(lessonID);

           var br = document.createElement("br");
           f.appendChild(br);

           var start = document.createElement("input");
           start.setAttribute('type', 'date');
           start.setAttribute('placeholder', 'Enter \'Start\' or \'End\'');
           start.name = "deleteTime";
           f.appendChild(start);

           var br1 = document.createElement("br");
           f.appendChild(br1);

           var submit = document.createElement("input");
           submit.setAttribute('type','submit');
           submit.className = "input-submit";
           f.appendChild(submit);
       });

    </script>
</body>
</html>
