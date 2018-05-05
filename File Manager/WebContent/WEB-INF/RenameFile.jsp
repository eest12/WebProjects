<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rename File</title>
</head>
<body>
	<form action="RenameFile?id=${param.id}&parentID=${param.parentID}&parentName=${param.parentName}" method="post">
		<input type="text" name="name" value="${param.name}" /> <button>Submit</button>
	</form>
</body>
</html>
