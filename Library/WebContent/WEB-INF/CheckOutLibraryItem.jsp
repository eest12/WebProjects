<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Check Out</title>
<link rel="stylesheet" href="libraryStyle.css?version=4.0">
</head>
<body>
	<header>
		<img id="banner" src="library_banner.png">
		<a href="Library"><h1 id="title">Library</h1></a>
		<a id="log" href="LibraryLogout">Logout</a>
	</header>
	
	<div id="content">
		<p>ID: <b>${param.id}</b></p>
		<p>Name: <b>${param.name}</b></p>
		
		<form action="CheckOutLibraryItem" method="post">
			<table>
				<tr><td>Date Borrowed:</td> <td>${date}</td></tr>
				<tr class="highlight"><td>Due Back By (Optional)</td> <td><input type="text" name="due" /></td></tr>
				<tr><td>CIN:</td> <td><input type="text" name="cin" /></td></tr>
				<tr class="highlight"><td>Name:</td> <td><input type="text" name="name" /></td></tr>
				<tr><td colspan=2><button>Check Out</button></td></tr>
			</table>
			<input type="hidden" name="id" value="${param.id}" />
		</form>
	</div>
</body>
</html>
