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
    </style>
</head>
<body>
    <h1>${fname} ${lname}</h1>
    <h2>All Classes</h2>
    <p>Relevant course materials, and other functionality are stored within the class itself.</p>

    <table>
        <tr>
            <th>Number</th>
            <th>Name</th>
            <th>Creator</th>
            <th>Link</th>
        </tr>
        <#list classes as class>
            <tr>
                    <td>${class.classcode}</th>
                    <td>${class.className}</th>
                    <td>${class.adminEmail}</th>
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
</body>
</html>