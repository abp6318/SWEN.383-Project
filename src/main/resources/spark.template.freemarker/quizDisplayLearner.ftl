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
        <form>
            <#list questions as question>
                <p>${question.questionNum}. ${question.questionContent}</p>
                <#if question.optionA?has_content>
                    <input type="radio" id="TBD" name="TBD" value="${question.optionA}">
                    <label for="TBD">a. ${question.optionA}</label><br>
                </#if>
                <#if question.optionB?has_content>
                    <input type="radio" id="TBD" name="TBD" value="${question.optionB}">
                    <label for="TBD">b. ${question.optionB}</label><br>
                </#if>
                <#if question.optionC?has_content>
                    <input type="radio" id="TBD" name="TBD" value="${question.optionC}">
                    <label for="TBD">c. ${question.optionC}</label><br>
                </#if>
                <#if question.optionD?has_content>
                    <input type="radio" id="TBD" name="TBD" value="${question.optionD}">
                    <label for="TBD">d. ${question.optionD}</label><br>
                </#if>
            </#list>
            <!-- add submit button here -->
        </form>
    </div>
</body>
</html>