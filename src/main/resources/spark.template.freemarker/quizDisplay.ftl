<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>


    <#list questions as question>
        <p>${question.questionContent}</p>
        <ul>
            <li>${question.optionA}</li>
            <li>${question.optionB}</li>
            <li>${question.optionC}</li>
            <li>${question.optionD}</li>
        </ul>
     </#list>



</body>
</html>