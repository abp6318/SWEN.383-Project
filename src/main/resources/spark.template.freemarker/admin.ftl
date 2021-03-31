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
    <!-- button allowing people to add class, if clicked 2 fields appear to be filled in -->
    <div id="addClass">
        <input id="add" value="Add Class" type="button">
        <form action="/admin" method="POST" id="AddClassForm"></form>
    </div>

    <!-- button allowing people to update class (with proper fields), if clicked 2+ fields appear to be filled in -->
    <div id="updateClass">
        <input id="update" value="Update Class" type="button">
        <form action="/admin" method="POST" id="UpdateClassForm"></form>
    </div>

    <!-- button allowing people to delete class via class code, if clicked 1 fields appear to be filled in -->
    <div id="deleteClass">
        <input id="delete" value="Delete Class" type="button">
        <form action="/admin" method="POST" id="DeleteClassForm"></form>
    </div>
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
	
    <!-- button adding members -->
    <input id="addMem" value="Add Members" type="button">
    <form action="/admin" method="POST" id="AddMembersForm"></form>
	
    <!-- button deleting members -->
    <input id="deleteMem" value="Delete Members" type="button">
    <form action="/admin" method="POST" id="DeleteMembersForm"></form>
	
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


    <h2>Feedbdack Module</h2>
    <p><a href="rating.ftl">View Feedback Module</a></p>
</body>
<script>
    var aBtn = document.getElementById("add");
    var aDiv = document.getElementById("AddClassForm");
    aBtn.addEventListener("click", function() {
        while (aDiv.firstChild) {
            aDiv.removeChild(aDiv.firstChild);
        }
        //add 3 input fields and submit btn
        var br1 = document.createElement("br");
		aDiv.appendChild(br1);

        //class code input
        var id = document.createElement("input");
		id.setAttribute('type', 'text');
		id.setAttribute('placeholder', 'Enter Class Code');
        id.name = "AddClassID";
        aDiv.appendChild(id);
		var br = document.createElement("br");
		aDiv.appendChild(br);

        //class name input
        var name = document.createElement("input");
        name.setAttribute('type','text');
        name.setAttribute('placeholder','Enter Class Name');
        name.name = "AddClassName";
        aDiv.appendChild(name);
        var br2 = document.createElement("br");
		aDiv.appendChild(br2);

        //prof email input
        var prof = document.createElement("input");
        prof.setAttribute('type','text');
        prof.setAttribute('placeholder','Enter Professor Email');
        prof.name = "AddClassProfessor";
        aDiv.appendChild(prof);
        var br3 = document.createElement("br");
		aDiv.appendChild(br3);

        // String className, String learningObj, String learningOutcome, String beginDate, String endDate
        var className = document.createElement("input");
        className.setAttribute('type', 'text');
        className.setAttribute('placeholder', 'Enter Class Name');
        className.name = "AddClassName";
        aDiv.appendChild(className);
        var br4 = document.createElement("br");
		aDiv.appendChild(br4);

        var learningObj = document.createElement("input");
        learningObj.setAttribute('type', 'text');
        learningObj.setAttribute('placeholder', 'Enter Class Learning Objective');
        learningObj.name = "AddClassLearningObjective";
        aDiv.appendChild(learningObj);
        var br5 = document.createElement("br");
		aDiv.appendChild(br5);

        var start = document.createElement("input");
        start.setAttribute('type','date');
        start.id = "startDate";
        start.name = "AddClassStartDate";
        var label = document.createElement("label");
        label.setAttribute('for','startDate');
        label.textContent = "Enter Start Date";
        aDiv.appendChild(label);
        var br6 = document.createElement("br");
		aDiv.appendChild(br6);
        aDiv.appendChild(start);
        var br7 = document.createElement("br");
		aDiv.appendChild(br7);

        var end = document.createElement("input");
        end.setAttribute('type','date');
        end.id = "endDate";
        end.name = "AddClassEndDate";
        var label2 = document.createElement("label");
        label2.setAttribute('for','endDate');
        label2.textContent = "Enter End Date";
        aDiv.appendChild(label2);
        var br8 = document.createElement("br");
		aDiv.appendChild(br8);
        aDiv.appendChild(end);
        var br9 = document.createElement("br");
		aDiv.appendChild(br9);


        //submit btn
		var submit = document.createElement("input");
		submit.setAttribute('type','submit');
		submit.className = "input-submit";
        aDiv.appendChild(submit);
        //do smthng w the info
    });

    var uBtn = document.getElementById("update");
    var uDiv = document.getElementById("UpdateClassForm");
    uBtn.addEventListener("click", function() {
        while (uDiv.firstChild) {
            uDiv.removeChild(uDiv.firstChild);
        }

         //add input fields and submit btn
         var br1 = document.createElement("br");
		uDiv.appendChild(br1);

        //class code input
        var id = document.createElement("input");
		id.setAttribute('type', 'text');
		id.setAttribute('placeholder', 'Enter Class Code');
        id.name = "UpdateClassID";
        uDiv.appendChild(id);
		var br = document.createElement("br");
		uDiv.appendChild(br);

        //class name input
        var name = document.createElement("input");
        name.setAttribute('type','text');
        name.setAttribute('placeholder','Enter Class Name');
        name.name = "UpdateClassID";
        uDiv.appendChild(name);
        var br2 = document.createElement("br");
		uDiv.appendChild(br2);

        //prof email input
        var prof = document.createElement("input");
        prof.setAttribute('type','text');
        prof.setAttribute('placeholder','Enter Professor Email');
        prof.name = "UpdateClassProfessor";
        uDiv.appendChild(prof);
        var br3 = document.createElement("br");
		uDiv.appendChild(br3);

        //learningObj input
        var obj = document.createElement("input");
        obj.setAttribute('type','text');
        obj.setAttribute('placeholder','Enter Learning Objective');
        obj.name = "UpdateClassLearningObjective";
        uDiv.appendChild(obj);
        var br4 = document.createElement("br");
		uDiv.appendChild(br4);

        //learningOutcome
        var outcome = document.createElement("input");
        outcome.setAttribute('type','text');
        outcome.setAttribute('placeholder','Enter Learning Outcome');
        outcome.name = "UpdateClassLearningOutcome";
        uDiv.appendChild(outcome);
        var br5 = document.createElement("br");
		uDiv.appendChild(br5);

        //beginDate
        var start = document.createElement("input");
        start.setAttribute('type','date');
        start.id = "startDate";
        start.name = "UpdateClassStartDate";
        var label = document.createElement("label");
        label.setAttribute('for','startDate');
        label.textContent = "Enter Start Date";
        uDiv.appendChild(label);
        var br8 = document.createElement("br");
		uDiv.appendChild(br8);
        uDiv.appendChild(start);
        var br6 = document.createElement("br");
		uDiv.appendChild(br6);

        //endDate
        var end = document.createElement("input");
        end.setAttribute('type','date');
        end.id = "endDate";
        end.name = "UpdateClassEndDate";
        var label2 = document.createElement("label");
        label2.setAttribute('for','endDate');
        label2.textContent = "Enter End Date";
        uDiv.appendChild(label2);
        var br9 = document.createElement("br");
		uDiv.appendChild(br9);
        uDiv.appendChild(end);
        var br7 = document.createElement("br");
		uDiv.appendChild(br7);

        //submit btn
		var submit = document.createElement("input");
		submit.setAttribute('type','submit');
		submit.className = "input-submit";
        uDiv.appendChild(submit);
        //do smthng w the info
    });

    var dBtn = document.getElementById("delete");
    var dDiv = document.getElementById("DeleteClassForm");
    dBtn.addEventListener("click", function() {
        while (dDiv.firstChild) {
            dDiv.removeChild(dDiv.firstChild);
        }

        //add input field and submit btn
        var br1 = document.createElement("br");
		dDiv.appendChild(br1);
        var id = document.createElement("input");
		id.setAttribute('type', 'text');
		id.setAttribute('placeholder', 'Enter Class Code');
		id.className = "input-text";
        id.name = "DeleteClassCode";
        dDiv.appendChild(id);
		var br = document.createElement("br");
		dDiv.appendChild(br);
		var submit = document.createElement("input");
		submit.setAttribute('type','submit');
		submit.className = "input-submit";
        dDiv.appendChild(submit);
        //do smthng w the info
    });
	
	//add discussion group members 
	var aMem = document.getElementById("addMem");
	var aForm = document.getElementById("AddMembersForm");
	aMem.addEventListener("click", function() {
		//add email and submit
        var br1 = document.createElement("br");
		aForm.appendChild(br1);
        var mem = document.createElement("input");
		mem.setAttribute('type', 'text');
		mem.setAttribute('placeholder', 'Enter Member Email');
		mem.className = "input-text";
        mem.name = "AddMemberEmail";
        dForm.appendChild(mem);
		var br = document.createElement("br");
		aForm.appendChild(br);
		var submit = document.createElement("input");
		submit.setAttribute('type','submit');
		submit.className = "input-submit";
        aForm.appendChild(submit);
	})
	
	//delete discussion group members
	var dMem = document.getElementById("deleteMem");
	var dForm = document.getElementById("DeleteMembersForm");
	dMem.addEventListener("click", function() {
		//add email and submit
        var br1 = document.createElement("br");
		dForm.appendChild(br1);
        var mem = document.createElement("input");
		mem.setAttribute('type', 'text');
		mem.setAttribute('placeholder', 'Enter Member Email');
		mem.className = "input-text";
        mem.name = "DeleteMemberEmail";
        dForm.appendChild(mem);
		var br = document.createElement("br");
		dForm.appendChild(br);
		var submit = document.createElement("input");
		submit.setAttribute('type','submit');
		submit.className = "input-submit";
        dForm.appendChild(submit);
	})
    
</script>
</html>