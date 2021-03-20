<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Not Authorized</title>
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Rubik+Mono+One&display=swap" rel="stylesheet">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
	<style>
	        input{
	            margin-top:15px;
	        }

	        input{
	            background: #F8D7FD;
	            border-radius: 90px;


	            /* Sign Up */
	            width: 250px;
	            height: 41px;

	            font-family: Roboto;
	            font-style: normal;
	            font-weight: normal;
	            font-size: 24px;
	            line-height: 41px;
	            /* identical to box height */

	            display: inline;
	            align-items: center;
	            text-align: center;
	            justify-content: center;
	            vertical-align: middle;

	            color: #404040;
	        }

	        h1{
	            /* position: absolute; */
	            width: 303px;
	            height: 102px;

	            font-family: 'Rubik Mono One', sans-serif;
	            font-style: normal;
	            font-weight: normal;
	            font-size: 82px;
	            line-height: 102px;
	            /* identical to box height */
				
	            color: #F8D7FD;

	            /* border: 1px solid #000000; */
	            text-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);

	            display: inline;
	        }

	        h2{
	            display:inline;
	            font-family: Roboto;
	            font-style: normal;
	            font-weight: normal;
	            font-size: 28px;
	            line-height: 33px;
	        }

	        body{
	            margin:auto;
	            width:100%;
	            text-align:center;
	            justify-content: center;
	            align-items: center;
	            position:absolute;
	            top:50px;
	        }
	    </style>
</head>
<body>
	<h1>myPLS</h1>
	<h2>Not Authorized</h2>
	<form action="/failed" method="POST">
		<input type="button" value="Resend Email"/><br>
		<input type="button" value="Sign Up"/>
	</form>
</body>
</html>