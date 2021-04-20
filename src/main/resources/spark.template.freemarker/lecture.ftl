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
        hr {
            border: 2px solid #F8D7FD;
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
            width: 60%;
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
            <div id="addLessonButton">
                <input id="addLesson" value="Add Lesson" type="button">
            </div>
            <hr>
            <div id="deleteLessonButton">
                <input id="deleteLesson" value="Delete Lesson" type="button">
            </div>
            <hr>
            <div id="updateLessonTimeButton">
                <input id="updateLessonTime" value="Update Lesson Time" type="button">
            </div>
            <hr>
            <div id="deleteLessonTimeButton">
                <input id="deleteLessonTime" value="Delete Lesson Time" type="button">
            </div>
            <hr>
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
                    <div id="addLectureButton">
                         <input id="addLecture" value="Add Lecture" type="button">
                    </div>
                    <hr>
                    <div id="deleteLectureButton">
                         <input id="deleteLecture" value="Delete Lecture" type="button">
                    </div>
                    <hr>
                    <div id="updateLectureButton">
                         <input id="updateLecture" value="Update Lecture" type="button"><br>
                    </div>
                    <hr>
                  </form>
             </#if>

             <#if multimediaLink??>
                 <h2>Content</h2>
                 <iframe src="${multimediaLink}" width=100% height=600px title="Lesson Multimedia"></iframe>
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
        var addLessonButton = document.getElementById("addLessonButton");
        var deleteLessonButton = document.getElementById("deleteLessonButton");
        var updateLessonTimeButton = document.getElementById("updateLessonTimeButton");
        var deleteLessonTimeButton = document.getElementById("deleteLessonTimeButton");
        var addLectureButton = document.getElementById("addLectureButton");
        var updateLectureButton = document.getElementById("updateLectureButton");
        var deleteLectureButton = document.getElementById("deleteLectureButton");
// Add lessons button
        aBtn.addEventListener("click", function() {

            var br0 = document.createElement("br");
    		addLessonButton.appendChild(br0);

            var lessonName = document.createElement("input");
    		lessonName.setAttribute('type', 'text');
    		lessonName.setAttribute('placeholder', 'Enter Lesson Name');
            lessonName.name = "addLessonName";
            lessonName.required = true;
            addLessonButton.appendChild(lessonName);

            var br = document.createElement("br");
    		addLessonButton.appendChild(br);

            var start = document.createElement("input");
    		start.setAttribute('type', 'date');
    		start.setAttribute('placeholder', 'Enter Start Date');
            start.name = "addLessonStartTime";
            addLessonButton.appendChild(start);

            var br1 = document.createElement("br");
    		addLessonButton.appendChild(br1);

    		var end = document.createElement("input");
            end.setAttribute('type', 'date');
            end.setAttribute('placeholder', 'Enter End Date');
            end.name = "addLessonEndTime";
            addLessonButton.appendChild(end);

            var br2 = document.createElement("br");
            addLessonButton.appendChild(br2);

            var submit = document.createElement("input");
    		submit.setAttribute('type','submit');
    		submit.className = "input-submit";
            addLessonButton.appendChild(submit);
        });
// Delete lessons button
        dBtn.addEventListener("click", function() {

            var br0 = document.createElement("br");
    		deleteLessonButton.appendChild(br0);

            var lessonID = document.createElement("input");
            lessonID.setAttribute('type', 'text');
            lessonID.setAttribute('placeholder', 'Enter Lesson ID');
            lessonID.name = "deleteLessonID";
            lessonID.required = true;
            deleteLessonButton.appendChild(lessonID);

            var br = document.createElement("br");
            deleteLessonButton.appendChild(br);

            var submit = document.createElement("input");
            submit.setAttribute('type','submit');
            submit.className = "input-submit";
            deleteLessonButton.appendChild(submit);
        });
// Update lesson times for lecture button
        uBtn.addEventListener("click", function() {

            var br0 = document.createElement("br");
    		updateLessonTimeButton.appendChild(br0);

            var lessonID = document.createElement("input");
            lessonID.setAttribute('type', 'text');
            lessonID.setAttribute('placeholder', 'Enter Lesson ID');
            lessonID.name = "updateTimeLessonID";
            lessonID.required = true;
            updateLessonTimeButton.appendChild(lessonID);

            var br = document.createElement("br");
            updateLessonTimeButton.appendChild(br);

            var start = document.createElement("input");
            start.setAttribute('type', 'date');
            start.setAttribute('placeholder', 'Enter New Start Time');
            start.name = "updatedStartTime";
            updateLessonTimeButton.appendChild(start);

            var br1 = document.createElement("br");
            updateLessonTimeButton.appendChild(br1);

            var end = document.createElement("input");
            end.setAttribute('type', 'date');
            end.setAttribute('placeholder', 'Enter New End Time');
            end.name = "updatedEndTime";
            updateLessonTimeButton.appendChild(end);

            var br2 = document.createElement("br");
            updateLessonTimeButton.appendChild(br2);

            var submit = document.createElement("input");
            submit.setAttribute('type','submit');
            submit.className = "input-submit";
            updateLessonTimeButton.appendChild(submit);
        });
// Delete lesson time button
        dtBtn.addEventListener("click", function() {

           var br0 = document.createElement("br");
    	    deleteLessonTimeButton.appendChild(br0);

           var lessonID = document.createElement("input");
           lessonID.setAttribute('type', 'text');
           lessonID.setAttribute('placeholder', 'Enter Lesson ID');
           lessonID.name = "deleteTimeLessonID";
           lessonID.required = true;
           deleteLessonTimeButton.appendChild(lessonID);

           var br = document.createElement("br");
           deleteLessonTimeButton.appendChild(br);

           var start = document.createElement("input");
           start.setAttribute('type', 'text');
           start.setAttribute('placeholder', 'Enter \'Start\' or \'End\'');
           start.name = "deleteTime";
           deleteLessonTimeButton.appendChild(start);

           var br1 = document.createElement("br");
           deleteLessonTimeButton.appendChild(br1);

           var submit = document.createElement("input");
           submit.setAttribute('type','submit');
           submit.className = "input-submit";
           deleteLessonTimeButton.appendChild(submit);
       });
// Delete Lecture Button
       if(dlecBtn != null){
           dlecBtn.addEventListener("click", function() {

            var br0 = document.createElement("br");
    		deleteLectureButton.appendChild(br0);

            var lectureID = document.createElement("input");
            lectureID.setAttribute('type', 'text');
            lectureID.setAttribute('placeholder', 'Enter Lecture ID');
            lectureID.name = "deleteLectureID";
            lectureID.required = true;
            deleteLectureButton.appendChild(lectureID);

            var br2 = document.createElement("br");
            deleteLectureButton.appendChild(br2);

            var submit = document.createElement("input");
            submit.setAttribute('type','submit');
            submit.className = "input-submit";
            deleteLectureButton.appendChild(submit);
            });
        }
// Update lecture fields button
       if(ulecBtn != null){
            ulecBtn.addEventListener("click", function() {

            var lectureID = document.createElement("input");
            lectureID.setAttribute('type', 'text');
            lectureID.setAttribute('placeholder', 'Enter Lecture ID');
            lectureID.name = "updateLectureID";
            lectureID.required = true;
            updateLectureButton.appendChild(lectureID);

            var br2 = document.createElement("br");
            updateLectureButton.appendChild(br2);

            var lectureName = document.createElement("input");
            lectureName.setAttribute('type', 'text');
            lectureName.setAttribute('placeholder', 'Enter Lecture Name');
            lectureName.name = "updateLectureName";
            updateLectureButton.appendChild(lectureName);

            var br = document.createElement("br");
            updateLectureButton.appendChild(br);

            var miltimedia = document.createElement("input");
            miltimedia.setAttribute('type', 'text');
            miltimedia.setAttribute('placeholder', 'Enter Multimedia Link');
            miltimedia.name = "updateMultimedia";
            updateLectureButton.appendChild(miltimedia);

            var br1 = document.createElement("br");
            updateLectureButton.appendChild(br1);

            var submit = document.createElement("input");
            submit.setAttribute('type','submit');
            submit.className = "input-submit";
            updateLectureButton.appendChild(submit);
            });
       }
// Add lectures button
       if(alecBtn != null){
           alecBtn.addEventListener("click", function() {

                  var lessonID = document.createElement("input");
                    lessonID.setAttribute('type', 'text');
                    lessonID.setAttribute('placeholder', 'Enter Lesson ID');
                    lessonID.name = "addLessonID";
                    lessonID.required = true;
                    addLectureButton.appendChild(lessonID);

                    var br = document.createElement("br");
                    addLectureButton.appendChild(br);

                  var lectureName = document.createElement("input");
                  lectureName.setAttribute('type', 'text');
                  lectureName.setAttribute('placeholder', 'Enter Lecture Name');
                  lectureName.name = "addLectureName";
                  lectureName.required = true;
                  addLectureButton.appendChild(lectureName);

                  var br = document.createElement("br");
                  addLectureButton.appendChild(br);

                  var miltimedia = document.createElement("input");
                  miltimedia.setAttribute('type', 'text');
                  miltimedia.setAttribute('placeholder', 'Enter Multimedia Link');
                  miltimedia.name = "addMultimedia";
                  miltimedia.required = true;
                  addLectureButton.appendChild(miltimedia);

                  var br1 = document.createElement("br");
                  addLectureButton.appendChild(br1);

                  var submit = document.createElement("input");
                  submit.setAttribute('type','submit');
                  submit.className = "input-submit";
                  addLectureButton.appendChild(submit);
              });
      }

    </script>
</body>
</html>
