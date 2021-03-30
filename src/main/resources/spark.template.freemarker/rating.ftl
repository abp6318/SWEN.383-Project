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
	</style>
</head>
<body>
	<h1>Ratings</h1>
	<h2>Course Feedback</h2><br>
	<table>
		<tr>
			<th>Course Code</th>
			<th>User Email</th>
			<th>Rating(1-10)</th>
			<th>
		</tr>
		<#list feedbackList as feedback>
            <tr>
                <td>${feedback.classCode}</td>
                <td>${feedback.userEmail}</td>
                <td>${feedback.feedback}</td>
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
</body>
</html>