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
            <a href="/professor" class="active">Dashboard</a>
            <a href="/lecture">Lectures</a>
            <a href="/multimedia">Multimedia</a>
            <a href="/quiz">Quizzes</a>
            <a href="/messages">Discussion Messages</a>
        </nav>
        <h1>${fname} ${lname}</h1>
        <h2>All Courses</h2>
        <p>Relevant course materials, and other functionality are stored within the class itself.</p>

        <table>
            <tr>
                <th>Number</th>
                <th>Name</th>
                <th>Professor</th>
                <th>Link</th>
            </tr>
            <#list classes as class>
                <tr>
                        <td>${class.classcode}</th>
                        <td>${class.className}</th>
                        <td>${class.profEmail}</th>
                        <td><a href="#">Link</a></th>
                    </tr>
            </#list>
        </table>


        <h2>Dicussions</h2>
        <p><a href="#">Create a new discussion</a></p>
        <table>
                <tr>
                    <th>Discussion Group ID</th>
                    <th>Name</th>
                    <th>Owner</th>
                    <th>Link</th> <!-- Users can click on the link to view the discussion page, and either join or request for access -->
                </tr>
                <#list discussionGroups as discussionGroup>
                    <tr>
                            <td>${discussionGroup.discussionID}</th>
                            <td>${discussionGroup.groupName}</th>
                            <td>${discussionGroup.userEmail}</th>
                            <td><a href="#">Link</a></th>
                        </tr>
                </#list>
            </table>
    </div>
</body>
</html>