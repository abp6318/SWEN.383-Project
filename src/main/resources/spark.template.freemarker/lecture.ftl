<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lecture</title>
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
        <h2>Lectures</h2>

        <!-- List of lectures that professor has access to -->
        <div class="lectureList"></div>

    </div>
</body>
</html>