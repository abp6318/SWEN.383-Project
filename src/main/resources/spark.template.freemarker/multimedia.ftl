<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - ${fname} ${lname}</title>
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
            <a href="/multimedia" class="active">Multimedia</a>
            <a href="/quiz">Quizzes</a>
            <a href="/messages">Discussion Messages</a>
        </nav>
        <h2>Multimedia</h2>

        <!-- Lecture that contains the multimedia -->
        <div class="lecture">
            <h3>Some lecture</h3>
            <!-- Button to edit multimedia -->
            <div id="editMM"></div>

            <!-- Button to add multmedia -->
            <div id="addMM"></div>

            <!-- Button to delete multimedia -->
            <div id="deleteMM"></div>
        </div>


    </div>
</body>
</html>