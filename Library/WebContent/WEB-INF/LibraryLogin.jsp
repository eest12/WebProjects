<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="libraryStyle.css?version=4.0">
</head>
<body>
	<header>
		<img id="banner" src="library_banner.png">
		<a href="Library"><h1 id="title">Library</h1></a>
	</header>

	<div id="content">
		<h2>Login</h2>
		
		<form action="LibraryLogin" method="post">
			<p>Username: <input type="text" name="user"></p>
			<p>Password: <input type="password" name="password"></p>
			<button>Login</button>
		</form>
	</div>
</body>
</html>
