<html>
	<head>
		<title>cerrar</title>
	</head>
	<body bgcolor="#ffffff">
		<% session.invalidate();%>
		<script language="JavaScript">
			window.parent.close();
		</script>
	</body>
</html>