<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ForgotPassword</title>
</head>
<body>
	<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Forgot Password</title>
<link rel="stylesheet" href="styles.css">

<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.container {
	width: 100%;
	max-width: 400px;
	margin: 0 auto;
}

.forgot-password-box {
	background-color: white;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	text-align: center;
}

h2 {
	margin-bottom: 20px;
	color: #333;
}

.input-group {
	margin-bottom: 15px;
	text-align: left;
}

.input-group label {
	display: block;
	margin-bottom: 5px;
	color: #555;
}

.input-group input {
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

button {
	width: 100%;
	padding: 10px;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

button:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<div class="container">
		<div class="forgot-password-box">
			<h2>Forgot Password</h2>
			<form action="forgotpassword" method="POST">
				<c:if test="${alert !=null}">
					<h3 class="alert alert-danger">${alert}</h3>
				</c:if>

				<div class="input-group">
					<label for="username">Username</label> <input type="text"
						id="username" name="uname" required>
				</div>
				<div class="input-group">
					<label for="email">Email</label> <input type="text" id="email"
						name="email" required>
				</div>
				<button type="submit">Submit</button>
			</form>
		</div>
	</div>
</body>
</html>

</body>
</html>