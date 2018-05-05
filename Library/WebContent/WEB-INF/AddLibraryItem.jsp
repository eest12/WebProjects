<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Item</title>
<link rel="stylesheet" href="libraryStyle.css?version=4.0">
</head>
<body>
	<header>
		<img id="banner" src="library_banner.png">
		<a href="Library"><h1 id="title">Library</h1></a>
		<a id="log" href="LibraryLogout">Logout</a>
	</header>

	<div id="content">
		<h2>Add Item</h2>
	
		<table>
			<form action="AddLibraryItem" method="post">
				<tr><td>Type:</td> <td><select name="type">
					<option>Book</option>
					<option>Tablet</option>
				</select></td></tr>
				<tr class="highlight"><td>Name:</td> <td><input class="long-input" type="text" name="name" /></td></tr>
				<tr><td>Additional Info:</td> <td><input class="long-input" type="text" name="info" /></td></tr>
				<tr class="highlight"><td># of Copies:</td> <td><input class="short-input" type="text" name="copies" /></td></tr>
				<tr><td colspan=2><button>Add</button></td></tr>
			</form>
		</table>
	</div>
</body>
</html>
