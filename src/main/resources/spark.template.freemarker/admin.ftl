<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <style>
        td{
            border:thin solid black;
            padding-left: 5px;
            padding-right: 15px;
        }
        th{
            text-align: left;
        }
    </style>
</head>
<body>
    <h1>Admin</h1>
    <h2>All Classes</h2>
    <p>Relevant course materials, and other functionality are stored within the class itself.</p>
    <p><a href="#">Create a new class</a></p>
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

    <h2>Discussions</h2>
     <form action="/discussion" method="GET">
        <input class="input-submit" value="Discussion" type="submit">
     </form>

    <!-- button allowing people to add class, if clicked 2 fields appear to be filled in -->
    <!-- button allowing people to update class (with proper fields), if clicked 2+ fields appear to be filled in -->
    <!-- button allowing people to delete class via class code, if clicked 1 fields appear to be filled in -->

    <table>
        <tr>
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

    <h2>Feedbdack Module</h2>
    <p><a href="rating.ftl">View Feedback Module</a></p>
</body>
</html>