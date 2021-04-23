<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quiz</title>
    <style>
        td {
            border:thin solid black;
            padding-left: 5px;
            padding-right: 15px;
        }
        th {
            text-align: left;
        }
        hr {
            border: 2px solid #F8D7FD;
            width: 100%;
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
        .flexbox {
            display: flex;
            justify-content: space-evenly;
            padding: 10px;
            flex-direction: column;
        }
        h2 {
          text-decoration: underline overline dotted black;
        }
        .nav {
          overflow: hidden;
        }
        .content {
            max-width: 500px;
            margin: auto;
            background: white;
            padding: 10px;
            height: 100%;
        }
        body {
            background-color: #F8D7FD;
        }
    </style>
</head>
<body>
    <div class="content">
        <nav class="nav" id ="myNav">
            <a href="/learner">Dashboard</a>
            <a href="/quizLearner" class="active">Quizzes</a>
            <a href="/discussionL">Discussion Groups</a>
        </nav>
        <h2>Quizzes</h2>

        <!-- Add quiz questions list here-->

        <!-- Display quiz grade  
                change mysql method 
            add link to take quiz? 
            -->
        <div class="quizQuestions">
        <table>
            <tr>
                <th>Quiz ID</th>
                <th>Quiz Name</th>
                <th>Time Limit</th>
                <th>Class Code</th>
                <th>Score</th>
            </tr>
             <#list studentQuizzes as quiz>
                <tr>
                    <td>${quiz.quizID}</td>
                    <td>${quiz.quizName}</td>
                    <td>${quiz.timeLimit}</td>
                    <td>${quiz.classCode}</td>
                    <td>${quiz.score}</td>
                </tr>     
            </#list>
        </table>
        </div>
    </div>
</body>
</html>