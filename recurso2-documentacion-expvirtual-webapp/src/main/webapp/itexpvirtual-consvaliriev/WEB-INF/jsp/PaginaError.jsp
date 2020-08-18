<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	
	<jsp:useBean id="beanErr" scope="request" class="pe.gob.sunat.framework.spring.util.bean.MensajeBean" />
	<%@ page import="java.util.*" %>
	<head>
	
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
		<title>.:: P&aacute;gina de Error ::.</title>
		<style>
			
			BODY {
				font-style:normal;
				font-size:10pt;
				font-family:Verdana,Arial,Helvetica,sans-serif;
			}
			
			H1 {
				font-size:16pt;
				color:Navy;
			}
			
			A {
				color:Navy;
			}
			
			.msg {
				font-weight : bold;
				font-size:12pt;
				color:Black;
				background-image : url(/a/imagenes/msg/error.gif);
				background-repeat : no-repeat;
				background-position : left;
				padding-left : 50px;
				height : 50px;
				padding-top : 10px;
			}
			
			.msg2 {
				font-weight : bold;
				font-size:12pt;
				color:Black;
			}
			
			.error {
				font-style:bold;
				font-size:12pt;
				color:Red;
				text-indent: 50px;
				text-align : justify;
			}
			
			.datos {
				font-size:10pt;
			}
			
			.soluc {
				font-size:12pt;
			}
			
			.cuerpo {
				border : 1px solid Blue;
				padding : 10px 10px 10px 10px;
			}
			
			.form-button {
				border-color:#69C;
				border-style:solid;
				border-width:1px;
				cursor:hand
			}
		</style>
	</head>
	
	<body  bgcolor="#ffffff">
		
		<div class="cuerpo">
			<div align="left" class="msg">La aplicaci&oacute;n ha retornado el siguiente problema :</div>
			<p class="error"><jsp:getProperty name="beanErr" property="mensajeerror" /></p>
			<HR size="1px">
			<div align="left"  class="msg2">Acci&oacute;n a realizar :</div>
			<div align="left" class="soluc">
				<% if (beanErr.getMensajesol()!=null && beanErr.getMensajesol().length()>0) { %>
					<jsp:getProperty name="beanErr" property="mensajesol" />
				<% } else { %>
					Por favor intentente nuevamente realizar la operaci&oacute;n. Si el problema persiste, comunicarse con nuestra Central de Consultas (<strong>0-801-12-100</strong> a nivel nacional o <strong>315-0730</strong> s&oacute;lo para Lima).

				<% } %>
			</div>
			<br>
		</div>
		
	</body>
	
</html>