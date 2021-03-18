<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rubik+Mono+One&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
    <style>
        /* *{
            border:thin solid red;
        } */

        input{
            margin-top:15px;
        }

        .input-text{
            /* position: absolute; */
            width: 398px;
            height: 70px;
            /* left: 521px; */
            /* top: 432px; */

            background: #FAFAFA;
            border: 5px solid #E5E5E5;
            box-sizing: border-box;
            border-radius: 90px;

            font-family: Roboto;
            font-style: normal;
            font-weight: normal;
            font-size: 35px;
            line-height: 41px;

            padding-left:20px;
        }

        .input-submit{
            /* Rectangle 5 */
            /* position: absolute; */
            width: 200px;
            height: 70px;
            /* left: 620px;
            top: 612px; */

            background: #F8D7FD;
            border-radius: 90px;


            /* Sign Up */
            /* position: absolute; */
            width: 120px;
            height: 41px;
            /* left: 660px;
            top: 626px; */

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
            /* left: 518px;
            top: 246px; */

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
    <h2>Login</h2>

    <form id="f" action="/login" method="POST">
        <input class="input-text" placeholder="Email" name="email" type="email"><br>
        <input class="input-text" placeholder="Password" name="password" type="password"><br>
        <input class="input-submit" value="Login" type="submit"><br>
		<input id="verify" class="input-submit" value="Verify" type="button"><br>
    </form>
</body>
<script>
	var form = document.getElementById("f");
	var btn = document.getElementById("verify");
	btn.addEventListener("click", function() {
		var code = document.createElement("input");
		code.setAttribute('type', 'text');
		code.setAttribute('placeholder', 'Input Code');
		code.className = "input-text";
		code.name="";
		var br = document.createElement("br");
		form.appendChild(br);
		var send = document.createElement("input");
		send.setAttribute('type','button');
		send.setAttribute('value','Verify Code');
		send.className = "input-submit";
		send.style.width = "220px";
		form.appendChild(code);
		var br2 = document.createElement("br");
		form.appendChild(br2);
		form.appendChild(send);
		send.addEventListener("click", function() {
			code.name="entered";
		})
	})

	</script>
</html>