<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quiz</title>
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
            <a href="/lecture">Lectures</a>
            <a href="/multimedia">Multimedia</a>
            <a href="/quiz" class="active">Quizzes</a>
            <a href="/messagesDG">Discussion Messages</a>
        </nav>
        <h2>Quizzes</h2>

        <!-- Button to edit quiz -->
        <div id="editQuiz"></div>

        <!-- Button to add quiz -->
        <div id="addQuiz"></div>

        <!-- Button to add a quiz question-->
        <div id="addQuizQuestion"></div>

        <!-- Button to delete quiz -->
        <div id="deleteQuiz"></div>

        <!-- Button to delete a quiz question-->
        <div id="deleteQuizQuestion"></div>

        <!-- Add quiz questions list here-->
        <div class="quizQuestions">
        <table>
            <tr>
                <th>Quiz ID</th>
                <th>Quiz Name</th>
                <th>Time Limit</th>
                <th>Class Code</th>
            </tr>
             <#list quizzes as quiz>
                <tr>
                    <td>${quiz.quizID}</td>
                    <td>${quiz.quizName}</td>
                    <td>${quiz.timeLimit}</td>
                    <td>${quiz.classCode}</td>
                </tr>
                
            </#list>
            
        </table>
       
        </div>

    </div>
</body>
</html>