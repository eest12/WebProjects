<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Manager</title>
</head>
<body>
	<p>
		<c:if test="${param.id != null}">
			<c:if test="${parentID == 0}">
				<a href="FileManager">..</a> \ <b>${param.name}</b> 
			</c:if>
			<c:if test="${parentID != 0}">
				<a href="FileManager?id=${parentID}&name=${parentName}">..</a> \ <b>${param.name}</b> 
			</c:if>
		</c:if>
	
		[<a href="AddFile?name=New Folder&parentID=${param.id}&parentName=${param.name}">New Folder</a>]
	</p>

	<c:if test="${filesSize == 0}"><p>This folder is empty</p></c:if>
	
	<c:if test="${filesSize > 0}">
		<table border=1 cellpadding=3>
			<c:forEach items="${files}" var="f">
				<c:if test="${f.folder == true}">
					<tr><td>\ <a href="FileManager?id=${f.id}&name=${f.name}">${f.name}</a></td>
					<td><a href="RenameFile?id=${f.id}&name=${f.name}&parentID=${f.parentID}&parentName=${param.name}">Rename</a> | 
					<a href="DeleteFile?id=${f.id}&parentID=${f.parentID}&parentName=${param.name}">Delete</a></td></tr>
				</c:if>
				<c:if test="${f.folder == false}">
					<tr><td>${f.name}</td>
					<td><a href="RenameFile?id=${f.id}&name=${f.name}&parentID=${f.parentID}&parentName=${param.name}">Rename</a> | 
					<a href="DeleteFile?id=${f.id}&parentID=${f.parentID}&parentName=${param.name}">Delete</a></td></tr>
				</c:if>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>
