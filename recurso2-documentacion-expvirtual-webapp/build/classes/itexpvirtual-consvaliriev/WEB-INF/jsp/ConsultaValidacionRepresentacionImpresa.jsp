<!DOCTYPE html>
<%@page import="pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes"%>
<html lang="es">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name = "viewport" content = "initial-scale = 1.0, user-scalable = no, width=device-width">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>CONSULTA DE LA REPRESENTACI&Oacute;N IMPRESA DE DOCUMENTOS DEL EXPEDIENTE VIRTUAL</title>
		
		<!-- Bootstrap -->
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/media/css/jquery.dataTables.css">
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Responsive/css/dataTables.responsive.css">
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/css/bootstrap-datetimepicker.min.css" media="screen">
		
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 10]>
			<script src="../a/js/libs/bootstrap/3.3.2/plugins/html5shiv/3.7.2/html5shiv.min.js"></script>
	      <script src="../a/js/libs/bootstrap/3.3.2/plugins/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
		<style type="text/css">
		
		
			.div100{
				width : 100% !important;
			}
			.blockUI h1{
				font-size: 10px !important;
				font-weight: bold !important;
			}
			h1{
				font-size: 1.7em;
			}
			
			.header div{
				padding-left: 0 !important;
			}
			
			.imgLogo{
				height: 2.5em;
				margin-top: 1em;
			}
			
			.text-left-important{
				text-align: left !important;
			}
			
			.hiddenDiv {
				display: none !important;
			}
		
		
			/*IE8*/
			#codRegistro{ 
				text-transform: uppercase; 
			}
			
			table.dataTable td.dataTables_empty {
				text-align: left !important; 
			}
			
			.input-group-addon {
				padding-bottom:0px !important;
				padding-top:0px !important;
				padding-left: 6px !important;
				padding-right: 6px !important;
			}
			
			.form-control {
				font-size:12px !important;
				height:24px !important;
				padding-top:3px !important;
				padding-bottom:3px !important;
				padding-left:5px !important;
				padding-right:5px !important;
			}
			
			.btn{
				padding-top:3px !important;
				padding-bottom:3px !important;
				font-size:12px !important;
			}
			
	
			body {
				font-size: 12px;
			}
			
			table.dataTable thead th {
				padding-bottom:3px !important;
				padding-top:3px !important;
				padding-left:5px !important;
				padding-right:5px !important;
			
			
				border: 1px solid gray;
				border-collapse: collapse;
				background: Gainsboro !important;
			}
			
			table.dataTable tbody tr:hover {
				background: CornflowerBlue !important;
			}
			
			table.dataTable tbody tr {
				cursor: pointer;
			}
			
			table.dataTable tbody td {
				padding-bottom:3px !important;
				padding-top:3px !important;
			
				border-right: 1px solid gray !important;
			}
			
			.dataTables_wrapper {
				background: Gainsboro !important;
				border: 1px solid gray !important;
				border-collapse: collapse !important;
			}
			
			.dataTables_paginate {
				padding-top: 0px !important;
			}
			
			.paginate_button {
				padding-bottom:3px !important;
				padding-top:3px !important;
				padding-left:3px !important;
				padding-right:3px !important;
				
				border: 1px solid gray !important;
				margin-left: 0px !important;
			}
			
			.dataTables_wrapper .dataTables_info {
				padding-top : 3px !important;
			}
			
			/* other */
			.current {
				background: CornflowerBlue !important;
			}
			
			.panel-info>.panel-heading {
			    background-color: white;
			    border-color: gray;
			}
			
			.marginedDiv {
			  /*margin: 15px 15px !important;*/
		      /*border: 1px #337ab7;*/
			  border: 1px groove #ddd !important;
			  border-style: solid;
			  padding:10px !important;
			}
			
			hr {
			    border-top: 1px solid #000 !important;
			}
			
			fieldset.scheduler-border {
				border: 1px groove #ddd !important;
				padding: 0 1.2em 0.4em 1.4em !important;
				margin: 0 0 1.5em 0 !important;
				-webkit-box-shadow: 0px 0px 0px 0px #000;
				box-shadow: 0px 0px 0px 0px #000;
			}
			
			legend.scheduler-border {
			width: inherit; /* Or auto */
			padding: 0 2px; /* To give a bit of padding on the left and right */
			border-bottom: none;
			margin-bottom: 0;
			font-size: 14px;
			}
			.dataTables_wrapper .dataTables_paginate {
				float: initial !important;
				text-align: center !important;
				padding-top: 0.25em;
			}
			
			.dlgButton {
				border-color: gray;
				margin-right: 7px;
			}
			
			.alinearCentrado {
				vertical-align: middle !important;
			}
			.panel-scroll {
				max-height: auto;
				min-height: 0px;
				overflow-y: auto;
				overflow-x: auto;
			}
			
			.glyphicon.glyphicon-camera {
				font-size: 30px;
				text-decoration:none;
			}
			
			#btnRegresar, #btnAnterior{
				width: 150px;
			}
			
			#divResultado{
				display: none;
			}
			#divMsjError, #divMsjErrorExtension{
				color:#FF0000;
				/*display:none;*/
				font-weight: bold;
				margin : 15px 15px !important;
			}
		</style>
	</head>
	<body>
<br />
 	<div id="container" class="container" style="width: 95%">
			<div class="row">
				<div class="panel panel-primary">
					<div class="panel-heading align="center">
						<h3 class="panel-title" align="center">CONSULTA DE LA REPRESENTACI&Oacute;N IMPRESA DE DOCUMENTOS DEL EXPEDIENTE VIRTUAL</h3>
						<form id="frmPrincipal" name="frmPrincipal" class="form-horizontal" role="form">
						</form>
					</div>
				</div>
				<div class="panel panel-primary" id="divBusqueda">
					<form class="form-horizontal" role="form" name="frmFiltroBusquedaEspecifica" id="frmFiltroBusquedaEspecifica" method="post">
						<input type="hidden" id="hddTipoConsulta" />
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
								<legend class="scheduler-border"> Ingrese los siguientes datos que figuran en la Representaci&oacute;n Impresa </legend>
								<br />
								<div class="form-group">
									<div class="col-md-3"><Strong>N&uacute;mero de RUC del Deudor Tributario: </Strong></div>
									<div class="col-md-9">
										<input name="numRUC" id="txtNumRUC" type="text" class="form-control tamanoMaximo" maxlength="11" placeHolder="" onkeypress="return permitirNumeros(event);" />
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-md-3"><Strong>N&uacute;mero de Representaci&oacute;n Impresa: </Strong></div>
									<div class="col-md-9">
										<input name="numRepreImp" id="txtNumRepreImp" type="text" class="form-control tamanoMaximo" placeHolder="" onkeypress="return permitirNumeros(event);" maxlength='14'/>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-md-12">Los campos a ingresar son obligatorios</div>
								</div>
								
								<div class="form-group">
									<div class="col-md-3"><Strong>Ingrese el c&oacute;digo que se muestra en la imagen: </Strong></div>
									<div class="col-md-2">
										<img width="100px" height="50px" id="imgCaptcha" name="imgCaptcha" src="images/loading.gif"/>
									</div>
									<div class="col-md-1">
										<input id="txtCaptcha" name="txtCaptcha" type="text" maxlength="4" onblur="mayuscula(this)" class="form-control" size="5" />
									</div>
									<div class="col-md-2">
										<a href="javascript:fnGoRefresh()">Refrescar c&oacute;digo</a>
									</div>
									<div class="col-md-3">&nbsp;</div>
								</div>
								
								<div class="form-group">
									<div class="col-md-3">&nbsp;</div>
									<div class="col-md-9">
										<input id="btnBuscar" type="button" class="btn btn-primary" onclick="buscar()" value="Buscar" />
										&nbsp;
										<input id="btnCancelar" type="reset" class="btn btn-primary" onclick="fnGoRefresh()" value="Cancelar" />
									</div>
								</div>
						</fieldset>
					</form>
					<div id="divMsjError" class="form-group"></div>
				</div>
				
				<!-- DIV RESULTADO (INICIALMENTE OCULTO) -->
				<div class="panel panel-primary" id="divResultado">
					<form class="form-horizontal" role="form" name="frmResultadoEncontrado" id="frmResultadoEncontrado" method="post">
					
						<!-- ENCONTRADO -->
						<fieldset class="scheduler-border" style="margin : 15px 15px !important"  id="fsEncontrado">
								<legend class="scheduler-border"> Resultado de la consulta </legend>
								<br />
								<div class="form-group">
									<div class="col-md-6"><Strong id="msjEncontrado">El documento se encuentra registrado en el Expediente N&deg; ${numCorDoc} </Strong></div>
									<div class="col-md-6">
										&nbsp;
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-md-6">
										<Strong>Para descargar el documento presionar en el siguiente enlace: &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</Strong>
										<a id="lnkEncontrado" href="#" role="button" class="cimg glyphicon glyphicon-camera" onclick="return validarExtension()" ></a>
										<input type="hidden" id="hddNumCorDoc" name="hddNumCorDoc" />
									</div>
									<div class="col-md-6">
										&nbsp;
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-md-5">&nbsp;</div>
									<div class="col-md-7">
										<input id="btnRegresar" type="button" class="btn btn-primary" onclick="regresar()" value="Regresar" />
									</div>
								</div>
								
						</fieldset>
						
						<!-- NO ENCONTRADO -->
						<fieldset class="scheduler-border" style="margin : 15px 15px !important" id="fsNoEncontrado">
								<legend class="scheduler-border"> Resultado de la consulta </legend>
								<br />
								<div class="form-group">
									<div class="col-md-6"><Strong>El documento consultado NO se encuentra registrado en el Sistema Integrado de Expediente Virtual </Strong></div>
									<div class="col-md-6">
										&nbsp;
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-md-5">&nbsp;</div>
									<div class="col-md-7">
										<!-- Anteriormente decía "Anterior", se solicitó cambiar a "Regresar". Se cambia solo texto para no alterar lógica -->
										<input id="btnAnterior" type="button" class="btn btn-primary" onclick="regresar()" value="Regresar" />
									</div>
								</div>
								
						</fieldset>
					</form>
					<div id="divMsjErrorExtension" class="form-group"></div>
				</div>
				<!-- FIN DIV RESULTADO -->
				
			</div>		
	 	</div>
	<!-- Dialogo -->
	<div id="modalDlg" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<div class="panel panel-info">
				<div class="panel-heading">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
					</button>
					<div id="dlgTitle">TITULO</div>
				</div>
				<div class="panel-body">
					<div id="dlgMsj" class="modal-body text-center">TEXTO</div>
					<div id="dlgBtns" class="text-center">
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script src="/a/js/libs/jquery/1.11.2/jquery.min.js"></script> 
  
  	<script src="/a/js/libs/bootstrap/3.3.2/js/bootstrap.min.js"></script>		
  	<script src="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/media/js/jquery.dataTables.min.js" type="text/javascript" language="javascript" ></script>    
  	<script src="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Responsive/js/dataTables.responsive.js" type="text/javascript" language="javascript"></script>
  	<script src="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Scroller/js/dataTables.scroller.js" type="text/javascript" language="javascript" ></script>
	
	<script src="/a/js/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
  	<script src="/a/js/libs/bootstrap/3.3.2/plugins/jquery.inputmask-3.1/dist/jquery.inputmask.bundle.min.js" type="text/javascript"></script>
	
	<script type="text/javascript">
		var gCount = 0;
		var esBusqAutomatica = ${esBusqAutomatica};
		var excepciones = ${excepciones};
		
		/**====================================================
		* @description: Función que inicia al cargar la página.
		*/
		$(document).ready(function() {
			borrarErrorExtension();
			$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);

			inicializarCampos();

			fnGoRefresh();
			
		});
		
		/**=====================================================================================
		* @description: Función que inicializa los valores de los campos.
		*/
		function inicializarCampos(){
			$('#txtNumRUC').val('${numRUC}');
			$('#txtNumRepreImp').val('${numRepreImp}');
			$('#hddTipoConsulta').val('${codTipConsul}');
			if(esBusqAutomatica){
				$('#btnRegresar').prop('disabled', true);
				$('#btnAnterior').prop('disabled', true);
			}
		}
		
		
		/**=====================================================================================
		* @description: Función que trae la imagen con el código CAPTCHA del servidor y refresca.
		*/
		var fnGoRefresh = function () {
			ocultarMensajeError();
			$('#txtCaptcha').val('').focus();
			document.getElementById("imgCaptcha").src = '${pageContext.request.contextPath}/ConsValiRiev.htm?action=generaCaptcha&nmagic=' + gCount;
			gCount++;
		}
		
		
		/**===============================================================================================================
		* @description: Función que valida la coincidencia entre el código captcha generado y el ingresado por e usuario.
		*/	
		var fnValidarTextoCaptcha = function(txtCaptchaInput){
			var txtCaptcha = txtCaptchaInput.toString();
			var captchaValido = false;
			
			if(txtCaptcha.length==4){
				
				var url = "${pageContext.request.contextPath}/ConsValiRiev.htm?action=validarTextoCaptcha&txtCaptcha="+txtCaptcha+"&ms="+(new Date()).getTime();

				$.ajax({
					type: "POST",
					url: url,
					dataType: "json",
					async: false,
					timeout : 2500000,
					contentType: "application/x-www-form-urlencoded",
					success: function(response) {
						var mensaje = response.mensaje;
						if(mensaje.error){
							mostrarMensajeError(excepciones.excepcion03);
						}else{
							captchaValido = true;
						}
					},
					error : function (xhr, ajaxOptions, thrownError) {
						mostrarMensajeError('Error de Captcha. '+thrownError);
					}
				});
			}
			else{
				mostrarMensajeError(excepciones.excepcion02);
				
			}
			return captchaValido;
		};
		
		function validarExtension(){
			
			$.ajax({
	            url: '${pageContext.request.contextPath}/ConsValiRiev.htm?action=validarExtension&numCorDoc='+$('#hddNumCorDoc').val(),
	           	data: [],
	            cache: false,
	            async: true,
	            type: 'POST',
	            contentType : "application/x-www-form-urlencoded; charset=utf-8",
	            dataType: 'json',
	            success: function(response) {
	            	
	            	if(!response.error){
	            		window.open('ConsValiRiev.htm?action=verPDFRepImpresa&codCorDoc=' + $('#hddNumCorDoc').val(), "_blank", "toolbar=yes, scrollbars=yes, resizable=yes, top=20, left=300, width=600, height=650");
	            	}else{
	            		mostrarErrorExtension(response.extension);
	            	}
	            	
	            },
	            error: function(err) {
	            	document.write(err.responseText);
	            }
	        });
			return false;
		}
		
		function mostrarErrorExtension(extension){
			$('#divMsjErrorExtension').html('No se puede generar Representaci&oacute;n Impresa del Documento con extensi&oacute;n: "' + extension + '"');
		}
		function borrarErrorExtension(){
			$('#divMsjErrorExtension').html('');
		}
		
		/**===================================================================================================
		* @description: Función que va al servidor a validar y buscar la Constancia de Representación Impresa.
		*/
		function buscar(){
			//Se quitan mensajes de error si existiesen
			ocultarMensajeError();
			borrarErrorExtension();

			if(validarCampos()){
				
				var objData = new Object();
				objData.numRUC = $('#txtNumRUC').val();
				objData.numRepreImp = $('#txtNumRepreImp').val();
				objData.codTipConsul = $('#hddTipoConsulta').val();
				
				$.ajax({
					url : '${pageContext.request.contextPath}/ConsValiRiev.htm?action=consultarValiRiev',
					type : 'POST',
					async : true,
					dataType : "json",
					data : JSON.stringify(objData),
					contentType : "application/json",
					mimeType : "application/json",
					success : function(response) {
						
						if(response.error){
							mostrarMensajeError(response.msjError);
						} else {
							$('#divBusqueda').hide();
							$('#divResultado').show();
							$('#hddTipoConsulta').val(response.codTipConsul);
							if(parseInt(response.numCorDoc) == -1){
								$('#fsNoEncontrado').show();
								$('#fsEncontrado').hide();
							}else{
								$('#hddNumCorDoc').val(response.numCorDoc);
								$('#msjEncontrado').html('El documento se encuentra registrado en el Expediente N&deg; ' + response.numExpedo);
// 								$('#lnkEncontrado').prop('href','ConsValiRiev.htm?action=verPDFRepImpresa&codCorDoc=' + response.numCorDoc);
								$('#fsEncontrado').show();
								$('#fsNoEncontrado').hide();
							}
						}
						
					},
					error : function(response) {
						mostrarPagError();
					}
				
				});
			}			
			
		}
		
		/**==================================================================
		* @description: Función que valida que todos los campos estén llenos correctamente.
		*/
		function validarCampos(){
			var camposValidos = true;
			
			var numRUC = $.trim($('#txtNumRUC').val());
			var numRepreImp = $.trim($('#txtNumRepreImp').val());
			
			if( numRUC.length == 0 || numRepreImp.length == 0 ){
				camposValidos = false;
				mostrarMensajeError(excepciones.excepcion01);
			}else if(!fnValidarTextoCaptcha($('#txtCaptcha').val())){
				camposValidos = false;
			}else if(numRUC.length != 11){
				camposValidos = false;
				mostrarMensajeError(excepciones.excepcion04);
			}else if(numRepreImp.length != 14){
				camposValidos = false;
				mostrarMensajeError(excepciones.excepcion06);
			}
			return camposValidos;
		}
	
		/**==================================================================
		* @description: Función que valida solo ingresos de números en un campo de texto.
		*/
		function permitirNumeros(e){
			var keynum = window.event ? window.event.keyCode : e.which;
			
			if ((keynum == 8)) {
				
				return true;
				
			}
			
			return /\d/.test(String.fromCharCode(keynum));
			
		}
		
		/**==================================================================
		* @description: Función que para a mayusculas el contenido de un campo de texto.
		*/
		function mayuscula(cadena){
			cadena.value = cadena.value.toUpperCase();
		}
		
		/**==================================================================
		* @description: Función que permite regresar a la pantalla de búsqueda.
		*/
		function regresar(){
			$('#divBusqueda').show();
			$('#divResultado').hide();
			$('#frmFiltroBusquedaEspecifica').trigger("reset");
			fnGoRefresh();
		}

		function mostrarMensajeError(msjError){
			$('#divMsjError').show();
			$('#divMsjError').html(msjError);
		}
		
		function ocultarMensajeError(){
			$('#divMsjError').hide();
			$('#divMsjError').html('');
		}
		

		function mostrarPagError() {
			var formURL = '${pageContext.request.contextPath}/error.htm?action=cargarPaginaError';
			document.forms.frmPrincipal.action = formURL;
			document.forms.frmPrincipal.method = "post";
			document.forms.frmPrincipal.submit();
		}
		
 	</script>
	</body>	
</html>
