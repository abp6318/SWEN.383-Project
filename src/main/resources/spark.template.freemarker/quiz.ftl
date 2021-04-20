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
            <a href="/professor">Dashboard</a>
            <a href="/lecture">Lectures</a>
            <a href="/quiz" class="active">Quizzes</a>
            <a href="/discussionP">Discussion Groups</a>
        </nav>
        <h2>Quizzes</h2>

        <div class="flexbox">
            <!-- Button to display quiz -->
            <div id="displayQuiz">
                <input id="display" value="Display Quiz" type="button">
                <form action="/quizDisplay" method="GET" id="DisplayQuizForm"></form>
            </div>

            <!-- Button to add quiz -->
            <div id="addQuiz">
                <input id="add" value="Add Quiz" type="button">
                <form action="/quiz" method="POST" id="AddQuizForm"></form>
            </div>

            <!-- Button to add a quiz question -->
            <div id="addQuizQuestion">
                <input id="addQuestion" value="Add Quiz Question" type="button">
                <form action="/quiz" method="POST" id="AddQuizQuestionForm"></form>
            </div>

            <!-- Button to delete quiz -->
            <div id="deleteQuiz">
                <input id="delete" value="Delete Quiz" type="button">
                <form action="/quiz" method="POST" id="DeleteQuizForm"></form>
            </div>

            <!-- Button to delete a quiz question -->
            <div id="deleteQuizQuestion">
                <input id="deleteQuestion" value="Delete Quiz Question" type="button">
                <form action="/quiz" method="POST" id="DeleteQuizQuestionForm"></form>
            </div>

            <!-- Button to add a quiz grade -->
            <div id="addQuizGrade">
                <input id="addGrade" value="Add Quiz Grade" type="button">
                <form action="/quiz" method="POST" id="AddQuizGradeForm"></form>
            </div>
        </div>

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

    <script>
        // displayQuiz section
        var displayButtonElement = document.getElementById("display");
        var displayQuizFormElement = document.getElementById("DisplayQuizForm");
        displayButtonElement.addEventListener("click", function(){
            while (displayQuizFormElement.firstChild) {
                displayQuizFormElement.removeChild(displayQuizFormElement.firstChild);
            }
            displayQuizFormElement.appendChild(document.createElement("br"));

            // quizID input
            var id = document.createElement("input");
            id.setAttribute('type', 'text');
            id.setAttribute('placeholder', 'Enter Quiz ID');
            id.name = "DisplayQuizID";
            displayQuizFormElement.appendChild(id);
            displayQuizFormElement.appendChild(document.createElement("br"));

            //submit btn
            var submit = document.createElement("input");
            submit.setAttribute('type','submit');
            submit.className = "input-submit";
            displayQuizFormElement.appendChild(submit);
        });

        // addQuiz section
        var addButtonElement = document.getElementById("add");
        var addQuizFormElement = document.getElementById("AddQuizForm");
        addButtonElement.addEventListener("click", function(){
            while (addQuizFormElement.firstChild) {
                addQuizFormElement.removeChild(addQuizFormElement.firstChild);
            }
            addQuizFormElement.appendChild(document.createElement("br"));

            // quiz name input
            var id = document.createElement("input");
            id.setAttribute('type', 'text');
            id.setAttribute('placeholder', 'Enter Quiz Name');
            id.name = "AddQuizName";
            addQuizFormElement.appendChild(id);
            addQuizFormElement.appendChild(document.createElement("br"));

            // quiz time limit input
            var id = document.createElement("input");
            id.setAttribute('type', 'text');
            id.setAttribute('placeholder', 'Enter Quiz Time Limit (Minutes)');
            id.name = "AddQuizTimeLimit";
            addQuizFormElement.appendChild(id);
            addQuizFormElement.appendChild(document.createElement("br"));

            // creator email / the user entering this quiz's email is retrieved from session (request.session().attribute("User"))

            // quiz classCode input
            var id = document.createElement("input");
            id.setAttribute('type', 'text');
            id.setAttribute('placeholder', 'Enter Class Code');
            id.name = "AddQuizClassCode";
            addQuizFormElement.appendChild(id);
            addQuizFormElement.appendChild(document.createElement("br"));

            //submit btn
            var submit = document.createElement("input");
            submit.setAttribute('type','submit');
            submit.className = "input-submit";
            addQuizFormElement.appendChild(submit);
        });

        // addQuizQuestion section
        var addQuestionButtonElement = document.getElementById("addQuestion");
        var addQuizQuestionFormElement = document.getElementById("AddQuizQuestionForm");
        addQuestionButtonElement.addEventListener("click", function(){
            while (addQuizQuestionFormElement.firstChild) {
                addQuizQuestionFormElement.removeChild(addQuizQuestionFormElement.firstChild);
            }
            addQuizQuestionFormElement.appendChild(document.createElement("br"));

            // quizID input
            var id = document.createElement("input");
            id.setAttribute('type', 'text');
            id.setAttribute('placeholder', 'Enter Quiz ID');
            id.name = "AddQuizQuestionQuizID";
            addQuizQuestionFormElement.appendChild(id);
            addQuizQuestionFormElement.appendChild(document.createElement("br"));

            // questionNum input
            var id = document.createElement("input");
            id.setAttribute('type', 'text');
            id.setAttribute('placeholder', 'Enter Question Number (unique)');
            id.name = "AddQuizQuestionQuestionNum";
            addQuizQuestionFormElement.appendChild(id);
            addQuizQuestionFormElement.appendChild(document.createElement("br"));

            // questionContent input
            var id = document.createElement("input");
            id.setAttribute('type', 'text');
            id.setAttribute('placeholder', 'Enter Question Content');
            id.name = "AddQuizQuestionQuestionContent";
            addQuizQuestionFormElement.appendChild(id);
            addQuizQuestionFormElement.appendChild(document.createElement("br"));

            // optionA input
            var id = document.createElement("input");
            id.setAttribute('type', 'text');
            id.setAttribute('placeholder', 'Enter Option A');
            id.name = "AddQuizQuestionOptionA";
            id.required = true;
            addQuizQuestionFormElement.appendChild(id);
            addQuizQuestionFormElement.appendChild(document.createElement("br"));

            // optionB input
            var id = document.createElement("input");
            id.setAttribute('type', 'text');
            id.setAttribute('placeholder', 'Enter Option B');
            id.name = "AddQuizQuestionOptionB";
            addQuizQuestionFormElement.appendChild(id);
            addQuizQuestionFormElement.appendChild(document.createElement("br"));

            // optionC input
            var id = document.createElement("input");
            id.setAttribute('type', 'text');
            id.setAttribute('placeholder', 'Enter Option C');
            id.name = "AddQuizQuestionOptionC";
            addQuizQuestionFormElement.appendChild(id);
            addQuizQuestionFormElement.appendChild(document.createElement("br"));

            // optionD input
            var id = document.createElement("input");
            id.setAttribute('type', 'text');
            id.setAttribute('placeholder', 'Enter Option D');
            id.name = "AddQuizQuestionOptionD";
            addQuizQuestionFormElement.appendChild(id);
            addQuizQuestionFormElement.appendChild(document.createElement("br"));



            // Creates the select list element
            var selectList = document.createElement('select');
            selectList.name = "AddQuizQuestionQuestionAnswer";
            selectList.required = true;
            addQuizQuestionFormElement.appendChild(selectList);

            // Creates null Select option
            var nullOption = document.createElement('option');
            nullOption.text = "Select an Option";
            nullOption.selected = this;
            nullOption.disabled = true;
            selectList.appendChild(nullOption);                

            // Creates optionA
            var newOption1 = document.createElement('option');
            newOption1.value = "a";
            newOption1.text = "A";
            selectList.appendChild(newOption1);

            // Creates optionB
            var newOption2 = document.createElement('option');
            newOption2.value = "b";
            newOption2.text = "B";
            selectList.appendChild(newOption2);

            // Creates optionC
            var newOption3 = document.createElement('option');
            newOption3.value = "c";
            newOption3.text = "C";
            selectList.appendChild(newOption3);

            // Creates optionD
            var newOption4 = document.createElement('option');
            newOption4.value = "d";
            newOption4.text = "D";
            selectList.appendChild(newOption4);

            //submit btn
            var submit = document.createElement("input");
            submit.setAttribute('type','submit');
            submit.className = "input-submit";
            addQuizQuestionFormElement.appendChild(submit);
        });

        // deleteQuiz section
        var deleteButtonElement = document.getElementById("delete");
        var deleteQuizFormElement = document.getElementById("DeleteQuizForm");
        deleteButtonElement.addEventListener("click", function(){
            while (deleteQuizFormElement.firstChild) {
                deleteQuizFormElement.removeChild(deleteQuizFormElement.firstChild);
            }
            deleteQuizFormElement.appendChild(document.createElement("br"));

            // quizID input
            var id = document.createElement("input");
            id.setAttribute('type', 'text');
            id.setAttribute('placeholder', 'Enter Quiz ID');
            id.name = "DeleteQuizQuizID";
            deleteQuizFormElement.appendChild(id);
            deleteQuizFormElement.appendChild(document.createElement("br"));

            // professor email checked in PostQuizRoute with session email

            //submit btn
            var submit = document.createElement("input");
            submit.setAttribute('type','submit');
            submit.className = "input-submit";
            deleteQuizFormElement.appendChild(submit);
        });

        // deleteQuizQuestion section
        var deleteQuestionButtonElement = document.getElementById("deleteQuestion");
        var deleteQuizQuestionFormElement = document.getElementById("DeleteQuizQuestionForm");
        deleteQuestionButtonElement.addEventListener("click", function(){
            while (deleteQuizQuestionFormElement.firstChild) {
                deleteQuizQuestionFormElement.removeChild(deleteQuizQuestionFormElement.firstChild);
            }
            deleteQuizQuestionFormElement.appendChild(document.createElement("br"));

            // quizID input
            var id = document.createElement("input");
            id.setAttribute('type', 'text');
            id.setAttribute('placeholder', 'Enter Quiz ID');
            id.name = "DeleteQuizQuestionQuizID";
            deleteQuizQuestionFormElement.appendChild(id);
            deleteQuizQuestionFormElement.appendChild(document.createElement("br"));

            // questionNum input
            var id = document.createElement("input");
            id.setAttribute('type', 'text');
            id.setAttribute('placeholder', 'Enter Question Number');
            id.name = "DeleteQuizQuestionQuestionNum";
            deleteQuizQuestionFormElement.appendChild(id);
            deleteQuizQuestionFormElement.appendChild(document.createElement("br"));

            //submit btn
            var submit = document.createElement("input");
            submit.setAttribute('type','submit');
            submit.className = "input-submit";
            deleteQuizQuestionFormElement.appendChild(submit);
        });

        // addQuizGrade section
        var addGradeButtonElement = document.getElementById("addGrade");
        var addQuizGradeFormElement = document.getElementById("AddQuizGradeForm");
        addGradeButtonElement.addEventListener("click", function(){
            while (addQuizGradeFormElement.firstChild) {
                addQuizGradeFormElement.removeChild(addQuizGradeFormElement.firstChild);
            }
            addQuizGradeFormElement.appendChild(document.createElement("br"));

            // quizID input
            var id = document.createElement("input");
            id.setAttribute('type', 'text');
            id.setAttribute('placeholder', 'Enter Quiz ID');
            id.name = "AddQuizGradeQuizID";
            addQuizGradeFormElement.appendChild(id);
            addQuizGradeFormElement.appendChild(document.createElement("br"));

            // userEmail input
            var id = document.createElement("input");
            id.setAttribute('type', 'text');
            id.setAttribute('placeholder', 'Enter Learner Email');
            id.name = "AddQuizGradeLearnerEmail";
            addQuizGradeFormElement.appendChild(id);
            addQuizGradeFormElement.appendChild(document.createElement("br"));
            
            // score input
            var id = document.createElement("input");
            id.setAttribute('type', 'text');
            id.setAttribute('placeholder', 'Enter Score (double)');
            id.name = "AddQuizGradeScore";
            addQuizGradeFormElement.appendChild(id);
            addQuizGradeFormElement.appendChild(document.createElement("br"));

            //submit btn
            var submit = document.createElement("input");
            submit.setAttribute('type','submit');
            submit.className = "input-submit";
            addQuizGradeFormElement.appendChild(submit);
        });



    </script>
</body>
</html>