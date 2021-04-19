<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Ratings</title>
	<style>
		h2{
	        display:inline;
	        font-family: Roboto;
	        font-style: normal;
	        font-weight: normal;
	        font-size: 28px;
	        line-height: 33px;
	    }
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
		.flex {
			display: flex;
			justify-content: center;
			align-items: center;
			flex-direction: column;
		}
	</style>
</head>
<body>
<div class="content">
	<nav class="nav" id ="myNav">
        <a href="/admin">Dashboard</a>
        <a href="/discussionA" >Discussion Groups</a>
        <a href="/feedback" class="active">Feedback Module</a>
    </nav>
	<div class="flex">
		<h1>Feedback Module</h1>
		<h2>Course Averages</h2><br>
    	<table>
    		<tr>
    			<th>Course Code</th>
    			<th>Average Rating(1-10)</th>
    			<th>
    		</tr>
    		<body>
                <#list avgs?keys as key>
                <tr>
                    <td>${key}</td>
                    <td>${avgs[key]}</td>
                </tr>
                </#list>
            </body>
    	</table><br>

	<h2>Course Feedback</h2><br>
	<table>
		<tr>
			<th>Course Code</th>
			<th>User Email</th>
			<th>Rating(1-10)</th>
			<th>
		</tr>
		<#list feedback as f>
            <tr>
                <td>${f.classcode}</td>
                <td>${f.userEmail}</td>
                <td>${f.feedback}</td>
            </tr>
         </#list>
	</table><br>

	<h2>Professor Feedback</h2><br>
	<table>
		<tr>
			<th>Professor Name</th>
			<th>Rating(1-10)</th>
			<th>
		</tr>
		<tr>
			<td>Professor 1</td>
			<td>4</td>
		</tr>
		<tr>
			<td>Professor 2</td>
			<td>7</td>
		</tr>
		<tr>
			<td>Professor 3</td>
			<td>10</td>
		</tr>
		<tr>
			<td>Professor 4</td>
			<td>6</td>
		</tr>
	</table>
	</div>
</div>
</body>
</html>