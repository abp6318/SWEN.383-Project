<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
            td {
                border:thin solid black;
                padding-left: 5px;
                padding-right: 15px;
            }
            th {
                text-align: left;
            }
            .nav a:hover {
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
        <h2>Quiz Display</h2>
        <form action = "/quizLearner" method="POST">
            <#list questionsL as question>
                <div class="question" id="${question.questionNum}">
                    <p>${question.questionNum}. ${question.questionContent}</p>
                    <#if question.optionA?has_content>
                        <input type="radio" name="${question.questionNum}" value="${question.optionA}" onclick="mapAnswers(${question.questionNum}, 'a')" required>
                        <label for="${question.questionNum}">a. ${question.optionA}</label><br>
                    </#if>
                    <#if question.optionB?has_content>
                        <input type="radio" name="${question.questionNum}" value="${question.optionB}"  onclick="mapAnswers(${question.questionNum}, 'b')">
                        <label for="${question.questionNum}">b. ${question.optionB}</label><br>
                    </#if>
                    <#if question.optionC?has_content>
                        <input type="radio" name="${question.questionNum}" value="${question.optionC}"  onclick="mapAnswers(${question.questionNum}, 'c')">
                        <label for="${question.questionNum}">c. ${question.optionC}</label><br>
                    </#if>
                    <#if question.optionD?has_content>
                        <input type="radio" name="${question.questionNum}" value="${question.optionD}" onclick="mapAnswers(${question.questionNum}, 'd')">
                        <label for="${question.questionNum}">d. ${question.optionD}</label><br>
                    </#if>
                </div>
            </#list><br>
            <input id="allStudentAnswers" name="allStudentAnswers">
            <input  id="submitQuiz" type="submit" value="Submit">
        </form>
    </div>
</body>
<script>

    var studentAnswersMap = new Map();

    function mapAnswers(questionNum, studentAnswer){
        // update HashMap with answer selected
        studentAnswersMap.set(questionNum, studentAnswer);
    }

    // { unique key : non-unique value }
    // { question number : studentAnswer }

    var sub = document.getElementById("submitQuiz");
    sub.addEventListener("click", function() {
        // return things in POST
        var postingMap = document.getElementById("allStudentAnswers");
        postingMap.value = studentAnswersMap;
    });
</script>
</html>