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
            <a href="/learner">Dashboard</a>
            <a href="/lecture" class="active">Lectures</a>
            <a href="/quiz">Quizzes</a>
            <a href="/discussionL">Discussion Groups</a>
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
                            <td><form id="dis1" action="/lectureL" method="POST">
                                 <button id="toLesson" type="submit" value="${lesson.lessonID}" name="classButton">Materials</button><br>
                            </form><br></td>
                        </tr>
                 </#list>
            </table>
            </div><br>

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
                             <td> <form id="lect" action="/lectureL" method="POST">
                                   <button id="toLecture" type="submit" value="${lecture.multimedia}" name="lectButton">Link</button><br>
                             </form><br></td>
                             <td>${lecture.lessonID}</td>
                          </tr>
                     </#list>
                 </table>

             </#if>

             <#if multimediaLink??>
                 <h2>Content</h2>
                 <iframe src="${multimediaLink}" width=100% height=600px title="Lesson Multimedia"></iframe>
             </#if>

    </div>
</body>
</html>
