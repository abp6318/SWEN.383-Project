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
        h2 {
          text-decoration: underline overline dotted black;
        }

        .nav {
          overflow: hidden;
        }
        .content {
            width: 40%;
            margin: auto;
            background: white;
            padding: 10px;
            height: 100%;
        }
        body {
            background-color: #F8D7FD;
        }
        .flexbox {
            display: flex;
            justify-content: space-evenly;
            padding: 10px;
        }
    </style>
</head>
<body>
    <div class="content">
        <nav class="nav" id ="myNav">
            <a href="/professor">Dashboard</a>
            <a href="/lecture" class="active">Lectures</a>
            <a href="/quiz">Quizzes</a>
            <a href="/discussionP">Discussion Groups</a>
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
                            <td><form id="dis1" action="/lecture" method="POST">
                                 <button id="toLesson" type="submit" value="${lesson.lessonID}" name="classButton">Materials</button><br>
                            </form><br></td>
                        </tr>
                 </#list>
            </table>
            </div><br>


        <form id="dis" action="/lecture" method="POST">
                <input id="addLesson" value="Add Lesson" type="button">
                <input id="deleteLesson" value="Delete Lesson" type="button">
                <input id="updateLessonTime" value="Update Lesson Time" type="button">
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

                 <form id="lecForm" action="/lecture" method="POST">
                    <div>
                         <input id="addLecture" value="Add Lecture" type="button">
                    </div>
                    <div>
                         <input id="deleteLecture" value="Delete Lecture" type="button">
                    </div>
                    <div>
                         <input id="updateLecture" value="Update Lecture" type="button"><br>
                    </div>
                  </form>

             </#if>


             <#if multimediaLink??>
                 <h2>Content</h2>
                 <iframe src="${multimediaLink}" width="800" height="300" title="Lesson Multimedia"></iframe>
             </#if>


    </div>
    <script>
        var aBtn = document.getElementById("addLesson");
        var dBtn = document.getElementById("deleteLesson");
        var uBtn = document.getElementById("updateLessonTime");
        var dtBtn = document.getElementById("deleteLessonTime");
        var alecBtn = document.getElementById("addLecture");
        var ulecBtn = document.getElementById("updateLecture");
        var dlecBtn = document.getElementById("deleteLecture");
        var f = document.getElementById("dis");
        var f1 = document.getElementById("lecForm");
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
           start.setAttribute('type', 'text');
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

       if(dlecBtn != null){
           dlecBtn.addEventListener("click", function() {

                 var lectureID = document.createElement("input");
                 lectureID.setAttribute('type', 'text');
                 lectureID.setAttribute('placeholder', 'Enter Lecture ID');
                 lectureID.name = "deleteLectureID";
                 lectureID.required = true;
                 f1.appendChild(lectureID);

                 var br2 = document.createElement("br");
                 f1.appendChild(br2);

                  var submit = document.createElement("input");
                  submit.setAttribute('type','submit');
                  submit.className = "input-submit";
                  f1.appendChild(submit);
              });

        }

       if(ulecBtn != null){
            ulecBtn.addEventListener("click", function() {

                  var lectureID = document.createElement("input");
                  lectureID.setAttribute('type', 'text');
                  lectureID.setAttribute('placeholder', 'Enter Lecture ID');
                  lectureID.name = "updateLectureID";
                  lectureID.required = true;
                  f1.appendChild(lectureID);

                  var br2 = document.createElement("br");
                  f1.appendChild(br2);

                   var lectureName = document.createElement("input");
                   lectureName.setAttribute('type', 'text');
                   lectureName.setAttribute('placeholder', 'Enter Lecture Name');
                   lectureName.name = "updateLectureName";
                   f1.appendChild(lectureName);

                   var br = document.createElement("br");
                   f1.appendChild(br);

                   var miltimedia = document.createElement("input");
                   miltimedia.setAttribute('type', 'text');
                   miltimedia.setAttribute('placeholder', 'Enter Multimedia Link');
                   miltimedia.name = "updateMultimedia";
                   f1.appendChild(miltimedia);

                   var br1 = document.createElement("br");
                   f1.appendChild(br1);

                   var submit = document.createElement("input");
                   submit.setAttribute('type','submit');
                   submit.className = "input-submit";
                   f1.appendChild(submit);
               });

       }

       if(alecBtn != null){
           alecBtn.addEventListener("click", function() {

                  var lessonID = document.createElement("input");
                    lessonID.setAttribute('type', 'text');
                    lessonID.setAttribute('placeholder', 'Enter Lesson ID');
                    lessonID.name = "addLessonID";
                    lessonID.required = true;
                    f1.appendChild(lessonID);

                    var br = document.createElement("br");
                    f1.appendChild(br);

                  var lectureName = document.createElement("input");
                  lectureName.setAttribute('type', 'text');
                  lectureName.setAttribute('placeholder', 'Enter Lecture Name');
                  lectureName.name = "addLectureName";
                  lectureName.required = true;
                  f1.appendChild(lectureName);

                  var br = document.createElement("br");
                  f1.appendChild(br);

                  var miltimedia = document.createElement("input");
                  miltimedia.setAttribute('type', 'text');
                  miltimedia.setAttribute('placeholder', 'Enter Multimedia Link');
                  miltimedia.name = "addMultimedia";
                  miltimedia.required = true;
                  f1.appendChild(miltimedia);

                  var br1 = document.createElement("br");
                  f1.appendChild(br1);

                  var submit = document.createElement("input");
                  submit.setAttribute('type','submit');
                  submit.className = "input-submit";
                  f1.appendChild(submit);
              });


      }

    </script>
</body>
</html>
