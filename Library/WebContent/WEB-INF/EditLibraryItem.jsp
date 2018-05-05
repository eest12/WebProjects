<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Item</title>
<link rel="stylesheet" href="libraryStyle.css?version=4.0">
</head>
<body>
	<header>
		<img id="banner" src="library_banner.png">
		<a href="Library"><h1 id="title">Library</h1></a>
		<a id="log" href="LibraryLogout">Logout</a>
	</header>

	<div id="content">
		<h2>Edit Item</h2>
	
		<table>
			<form action="EditLibraryItem?id=${select.id}" method="post">
				<tr><td>ID:</td> <td>${select.id}</td></tr>
				<tr class="highlight"><td>Type:</td> <td><select name="type">
				<c:choose>
					<c:when test="${select.type == 'Book'}">
						<option selected>Book</option>
						<option>Tablet</option>
					</c:when>
					<c:otherwise>
						<option>Book</option>
						<option selected>Tablet</option>
					</c:otherwise>
				</c:choose>
				</select></td></tr>
				<tr><td>Name:</td> <td><input class="long-input" type="text" name="name" value="${select.name}" /></td></tr>
				<tr class="highlight"><td>Additional Info:</td> <td><input class="long-input" type="text" name="info" value="${select.info}" /></td></tr>
				<tr><td colspan=2><button>Save</button></td></tr>
			</form>
		</table>
	</div>
</body>
</html>
