<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Create a Discussion Group</title>
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
        <a href="/discussionP" class="active">Discussion Groups</a>
        <a href="/feedback">Feedback Module</a>
    </nav>
    <h1>Create a Discussion Group</h1>

    <form id="dis" action="/discussion" method="POST">
        <input id="search" type="text" placeholder="Search..." name="searchBar">
        <button id="searchButton" type="submit"><i class="fa fa-search"></i></button><br>
        <input id="add" value="Add Discussion Group" type="button">
        <input id="delete" value="Delete Discussion Group" type="button"><br>
    </form>
    <#if discussionGroups??>
    <h2>Discussion Groups Search Results</h2>
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
    </#if>


    <h2>All Discussion Groups</h2>

    <table>
        <tr>
            <th>Discussion Group ID</th>
            <th>Name</th>
            <th>Owner</th>
            <th>Link</th> <!-- Users can click on the link to view the discussion page, and either join or request for access -->
        </tr>
        <#list allGroups as discussionGroup>
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
<script>
    var aBtn = document.getElementById("add");
    var dBtn = document.getElementById("delete");
    var f = document.getElementById("dis");
    aBtn.addEventListener("click", function() {
        var groupName = document.createElement("input");
		groupName.setAttribute('type', 'text');
		groupName.setAttribute('placeholder', 'Enter Group Name');
        groupName.name = "addDiscussionName";
        f.appendChild(groupName);

        var br = document.createElement("br");
		f.appendChild(br);

        var email = document.createElement("input");
		email.setAttribute('type', 'text');
		email.setAttribute('placeholder', 'Enter Email');
        email.name = "addDiscussionEmail";
        f.appendChild(email);

        var br1 = document.createElement("br");
		f.appendChild(br1);

        var submit = document.createElement("input");
		submit.setAttribute('type','submit');
		submit.className = "input-submit";
        f.appendChild(submit);
    });
    dBtn.addEventListener("click", function() {
        var groupName = document.createElement("input");
		groupName.setAttribute('type', 'text');
		groupName.setAttribute('placeholder', 'Enter Group ID');
        groupName.name = "DeleteDiscussionID";
        f.appendChild(groupName);

        var br = document.createElement("br");
		f.appendChild(br);

        var submit = document.createElement("input");
		submit.setAttribute('type','submit');
		submit.className = "input-submit";
        f.appendChild(submit);
    });
</script>
</html>