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
    <div id="addClass">
        <input id="add" value="Add Class" type="button">
    </div>

    <!-- button allowing people to update class (with proper fields), if clicked 2+ fields appear to be filled in -->
    <div id="updateClass">
        <input id="update" value="Update Class" type="button">
    </div>

    <!-- button allowing people to delete class via class code, if clicked 1 fields appear to be filled in -->
    <div id="deleteClass">
        <input id="delete" value="Delete Class" type="button">
    </div>

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
<script>
    var aBtn = document.getElementById("add");
    var aDiv = document.getElementById("addClass");
    aBtn.addEventListener("click", function() {
        //add 3 input fields and submit btn
        var br1 = document.createElement("br");
		dDiv.appendChild(br1);

        //class code input
        var id = document.createElement("input");
		id.setAttribute('type', 'text');
		id.setAttribute('placeholder', 'Enter Class Code');
        dDiv.appendChild(id);
		var br = document.createElement("br");
		dDiv.appendChild(br);

        //class name input
        var name = document.createElement("input");
        name.setAttribute('type','text');
        name.setAttribute('placeholder','Enter Class Name');
        dDiv.appendChild(name);
        var br2 = document.createElement("br");
		dDiv.appendChild(br2);

        //prof email input
        var prof = document.createElement("input");
        prof.setAttribute('type','text');
        prof.setAttribute('placeholder','Enter Professor Email');
        dDiv.appendChild(prof);
        var br3 = document.createElement("br");
		dDiv.appendChild(br3);

        //submit btn
		var submit = document.createElement("input");
		submit.setAttribute('type','button');
		submit.setAttribute('value','Submit');
		submit.className = "input-submit";
        dDiv.appendChild(submit);
        //do smthng w the info 
    })

    var uBtn = document.getElementById("update");
    var uDiv = document.getElementById("updateClass");
    uBtn.addEventListener("click", function() {
         //add input fields and submit btn
         var br1 = document.createElement("br");
		dDiv.appendChild(br1);

        //class code input
        var id = document.createElement("input");
		id.setAttribute('type', 'text');
		id.setAttribute('placeholder', 'Enter Class Code');
        dDiv.appendChild(id);
		var br = document.createElement("br");
		dDiv.appendChild(br);

        //class name input
        var name = document.createElement("input");
        name.setAttribute('type','text');
        name.setAttribute('placeholder','Enter Class Name');
        dDiv.appendChild(name);
        var br2 = document.createElement("br");
		dDiv.appendChild(br2);

        //prof email input
        var prof = document.createElement("input");
        prof.setAttribute('type','text');
        prof.setAttribute('placeholder','Enter Professor Email');
        dDiv.appendChild(prof);
        var br3 = document.createElement("br");
		dDiv.appendChild(br3);

        //learningObj input
        var obj = document.createElement("input");
        obj.setAttribute('type','text');
        obj.setAttribute('placeholder','Enter Learning Objective');
        dDiv.appendChild(obj);
        var br4 = document.createElement("br");
		dDiv.appendChild(br4);

        //learningOutcome
        var outcome = document.createElement("input");
        outcome.setAttribute('type','text');
        outcome.setAttribute('placeholder','Enter Learning Outcome');
        dDiv.appendChild(outcome);
        var br5 = document.createElement("br");
		dDiv.appendChild(br5);

        //beginDate
        var start = document.createElement("input");
        start.setAttribute('type','date');
        start.id = "startDate";
        var label = document.createElement("label");
        label.setAttribute('for','startDate');
        label.textContent = "Enter Start Date";
        dDiv.appendChild(label);
        var br8 = document.createElement("br");
		dDiv.appendChild(br8);
        dDiv.appendChild(start);
        var br6 = document.createElement("br");
		dDiv.appendChild(br6);

        //endDate
        var end = document.createElement("input");
        end.setAttribute('type','date');
        end.id = "endDate";
        var label2 = document.createElement("label");
        label2.setAttribute('for','endDate');
        label2.textContent = "Enter End Date";
        dDiv.appendChild(label2);
        var br9 = document.createElement("br");
		dDiv.appendChild(br9);
        dDiv.appendChild(end);
        var br7 = document.createElement("br");
		dDiv.appendChild(br7);

        //submit btn
		var submit = document.createElement("input");
		submit.setAttribute('type','button');
		submit.setAttribute('value','Submit');
		submit.className = "input-submit";
        dDiv.appendChild(submit);
        //do smthng w the info 
    })

    var dBtn = document.getElementById("delete");
    var dDiv = document.getElementById("deleteClass");
    dBtn.addEventListener("click", function() {
        //add input field and submit btn
        var br1 = document.createElement("br");
		dDiv.appendChild(br1);
        var id = document.createElement("input");
		id.setAttribute('type', 'text');
		id.setAttribute('placeholder', 'Enter Class Code');
		id.className = "input-text";
        dDiv.appendChild(id);
		var br = document.createElement("br");
		dDiv.appendChild(br);
		var submit = document.createElement("input");
		submit.setAttribute('type','button');
		submit.setAttribute('value','Submit');
		submit.className = "input-submit";
        dDiv.appendChild(submit);
        //do smthng w the info 
    })
    
</script>
</html>