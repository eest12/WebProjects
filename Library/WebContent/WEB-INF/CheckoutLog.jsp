<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout Log</title>
<link rel="stylesheet" href="libraryStyle.css?version=4.0">
</head>
<body>
	<header>
		<img id="banner" src="library_banner.png">
		<a href="Library"><h1 id="title">Library</h1></a>
		<c:if test="${empty user}"><a id="log" href="LibraryLogin">Login</a></c:if>
		<c:if test="${not empty user}"><a id="log" href="LibraryLogout">Logout</a></c:if>
	</header>
	
	<div id="content">
		<p>ID: <b>${param.id}</b></p>
		<p>Name: <b>${param.name}</b></p>
		<p>
			<a href="Library">Back to Items</a>
			<c:if test="${not empty user && param.available == true}"> | <a href="CheckOutLibraryItem?id=${param.id}&name=${param.name}&available=${param.available}">Check Out</a></c:if>
		</p>
		<table>
			<tr><th>CIN</th> <th>Name</th> <th>Date Borrowed</th> <th>Due Back By</th> <th>Date Returned</th></tr>
			<c:forEach items="${checkoutLog}" var="ch" varStatus="status">
				<c:if test="${status.index % 2 == 0}"><tr></c:if>
				<c:if test="${status.index % 2 != 0}"><tr class="highlight"></c:if>
				<td>${ch.cin}</td> <td>${ch.name}</td> <td>${ch.borrowed}</td> <td>${ch.due}</td>
				<c:if test="${not empty ch.returned}"><td>${ch.returned}</td></tr></c:if>
				<c:if test="${empty ch.returned}"><td><a href="ReturnLibraryItem?checkoutID=${ch.id}&itemID=${param.id}">Return</a></td></tr></c:if>
			</c:forEach>
		</table>
	</div>
</body>
</html>
