<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Library</title>
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
		<h2>All Items</h2>
		
		<c:if test="${empty user}">
			<table>
				<tr><th id="id">ID</th><th id="type">Type</th><th id="name">Name</th><th id="info">Additional Info</th><th id="avail">Available</th></tr>
				<c:forEach items="${libItems}" var="li" varStatus="status">
					<c:if test="${status.index % 2 == 0}"><tr></c:if>
					<c:if test="${status.index % 2 != 0}"><tr class="highlight"></c:if>
					<td class="centered">${li.id}</td><td>${li.type}</td><td><a href="CheckoutLog?id=${li.id}&name=${li.name}&available=${li.available}">${li.name}</a></td><td>${li.info}</td>
					<td class="centered">
					<c:if test="${li.available == true}">Yes</c:if>
					<c:if test="${li.available == false}">No</c:if>
					</td></tr>
				</c:forEach>
			</table>
		</c:if>
		
		<c:if test="${not empty user}">
			<table>
				<tr><th id="id">ID</th><th id="type">Type</th><th id="name">Name</th><th id="info">Additional Info</th><th id="avail">Available</th><th id="oper">Operation</th></tr>
				<c:forEach items="${libItems}" var="li" varStatus="status">
					<c:if test="${status.index % 2 == 0}"><tr></c:if>
					<c:if test="${status.index % 2 != 0}"><tr class="highlight"></c:if>
					<td class="centered">${li.id}</td><td>${li.type}</td><td><a href="CheckoutLog?id=${li.id}&name=${li.name}&available=${li.available}">${li.name}</a></td><td>${li.info}</td>
					<td class="centered">
					<c:if test="${li.available == true}">Yes</c:if>
					<c:if test="${li.available == false}">No</c:if>
					</td><td class="centered"><a href="EditLibraryItem?id=${li.id}">Edit</a></td></tr>
				</c:forEach>
			</table>
			<a href="AddLibraryItem">Add Items</a>
		</c:if>
	</div>
</body>
</html>
