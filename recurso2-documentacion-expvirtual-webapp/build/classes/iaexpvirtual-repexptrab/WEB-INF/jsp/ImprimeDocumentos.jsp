<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name = "viewport" content = "initial-scale = 1.0, user-scalable = no,  width=device-width">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		
<title>Insert title here</title>
</head>
<body>
	<APPLET
	  CODE     = "pe.gob.sunat.recurso2.documentacion.util.AppletImpresion.class"	
	  CODEBASE = "./jars/"
	  type="application/x-java-applet;jpi-version=6"
	  archive = "recurso2-documentacion-expvirtual-appletprint.jar"
	  NAME     = "recurso2-documentacion-expvirtual-appletprint"
	  WIDTH    = "400"
	  HEIGHT   = "300"
	>
	
		<param name="listaDocumentos" value='${listaDocumentos}'> 
		<param name="ruta" value='${ruta}'> 
	</APPLET>
</body>
</html>