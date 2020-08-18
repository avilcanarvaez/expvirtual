<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="es">
	
	<head>
		
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name = "viewport" content = "initial-scale = 1.0, user-scalable = no, width=device-width">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>REGISTRO MANUAL DEL EXPEDIENTE VIRTUAL</title>
		
		<!-- Bootstrap -->
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/media/css/jquery.dataTables.css">
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Responsive/css/dataTables.responsive.css">
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/css/bootstrap-datetimepicker.min.css" media="screen">
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/font-awesome/4.4.0/css/font-awesome.min.css">
		
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
			.header div{
				padding-left: 0 !important;
			}
			.centered {
				text-align: center !important;
			}
			.separator {
				border: 1px solid !important;
				border-color: gray !important;
				margin-bottom: 8px !important;
				margin-top: 10px !important;
			}
			.btn {
				line-height: 1 !important; 
			}
			.marginedDiv {
				margin: 15px 15px !important;
			}
			.marginedForm {
				margin: 15px 0px !important;
			}
			.hiddenDiv {
				display: none !important;
			}
			.cboDependencia {
				width: 440px !important;
			}
			.dataTables_wrapper {
				background: Gainsboro !important;
				border: 1px solid gray !important;
				border-collapse: collapse !important;
			}
			.dataTables_paginate {
				padding-top: 0px !important;
			}
			.paginate_button{
				border: 1px solid gray !important;
				margin-left: 0px !important;
			}
			.current {
				background: CornflowerBlue !important;
			}
			select {
				height: 25px !important;
			}
			.chkBoxMaster{
				width: 18px;
				height: 18px;
			}
			.panel-info {
				border-color: gray;
			}
			.panel-info>.panel-heading{
				background-color:white;
				border-color: gray;
			}
			.dlgButton {
				border-color: gray;
				margin-right: 7px;
			}
			.form-control-single {
				font-size:12px !important;
				height:24px !important;
				padding-top:3px !important;
				padding-bottom:3px !important;
				padding-left:5px !important;
				padding-right:5px !important;
			}
			.form-control-textarea {
				font-size:12px !important;
				height:auto !important;
				padding-top:3px !important;
				padding-bottom:3px !important;
				padding-left:5px !important;
				padding-right:5px !important;
				resize: none;
			}
			fieldset.fsStyle {
				font-size: 12px !important;
				font-weight: normal;
				border: 1px solid #999999;
				padding: 4px;
				margin: 5px;
			}
			legend.legendStyle {
				padding-left: 5px;
				padding-right: 5px;
				font-size: 12px !important;
				color: #888888;
				background-color: transparent;
				font-weight: bold;
			}
			legend {
				width: auto;
				border-bottom: 0px;
			}
			.input-group-addon {
				padding-bottom:0px !important;
				padding-top:0px !important;
				padding-left: 6px !important;
				padding-right: 6px !important;
			}
			.upperText{ 
				text-transform: uppercase; 
			}
			body {
				font-size: 12px;
			}
			.uploadStyle {
				border-color: white;
			}
		</style>
		
	</head>
	
	<body>
		
		<div class="container">
			
			<div class="row">
				
				<div class="panel panel-primary">
					<div class="panel-heading centered">
						<h2 class="panel-title">
							<strong>REGISTRO MANUAL DEL EXPEDIENTE VIRTUAL</strong>
						</h2>
						<form id="frmAuxiliar" name="frmAuxiliar" class="form-inline" role="form" method="post" enctype="multipart/form-data">
						</form>
						<form id="frmPrincipal" name="frmPrincipal" class="form-inline" role="form" method="post" enctype="multipart/form-data">
							<input id="dataOculta" name="dataOculta" type="hidden" class="form-control form-control-single"/>
							<input id="hdnDescripcion" name="hdnDescripcion" type="hidden"/>
						</form>
						<form id="frmFinal" name="frmFinal" class="form-inline" role="form" method="post" enctype="multipart/form-data">
						</form>
					</div>
				</div>
				
				<div class="panel panel-primary">
					<form id="formDatosExpediente" name="formDatosExpediente"  role="form" method="post">
					<div class="marginedDiv">
						<div class="panel-body">
							<div class="row content-box">
								<div class="col-md-2">
									<label>N&deg; Expediente Virtual: </label>
								</div>
								<div class="col-md-2">
									<input id="txtNumExpVirtual" name="txtNumExpVirtual" type="text" class="form-control form-control-single" readonly="readonly"/>
								</div>
								<div class="col-md-2">
									&nbsp;
								</div>
								<div class="col-md-4">
									<label>Fecha de Registro del Expediente Virtual: </label>
								</div>
								<div class="col-md-2">
									<input id="txtFechaRegistro" name="txtFechaRegistro" type="text" class="form-control form-control-single" readonly="readonly"/> 
								</div>
							</div>
							<div class="row content-box">
								<div class="col-md-12">
									&nbsp;
								</div>
							</div>
							<div class="row content-box">
								
								<div class="col-md-12">
									
									<fieldset class="fsStyle">
										
										<legend class="legendStyle">Datos del Expediente Virtual</legend>
										
										<div class="marginedDiv">
											
											<div class="row content-box">
												<div class="col-md-3">
													<label>RUC: </label>
												</div>
												<div class="col-md-3">
													<input id="txtNumRuc" name="txtNumRuc" type="text" class="form-control form-control-single" maxlength="11" onkeypress="return permitirNumeros(event);" onblur="validarRUCBlur();" onkeyup="ValidarRucKey();"  />
												</div>
												<div class="col-md-2">
													<label>Raz&oacute;n Social: </label>
												</div>
												<div class="col-md-4">
													<input id="txtDesRazonSocial" name="txtDesRazonSocial" type="text" class="form-control form-control-single" readonly="readonly"/> 
												</div>
											</div>
											
											<div class="row content-box">
												<div class="col-md-12">
													&nbsp;
												</div>
											</div>
											
											<div class="row content-box">
												<div class="col-md-3">
													<label>Dependencia: </label>
												</div>
												<div class="col-md-3">
													<input id="txtDependencia" name="txtDependencia" type="text" class="form-control form-control-single" readonly="readonly"/>
												</div>
												<div class="col-md-6">
													&nbsp;
												</div>
											</div>
											
											<div class="row content-box">
												<div class="col-md-12">
													&nbsp;
												</div>
											</div>
											
											<div class="row content-box">
												<div class="col-md-3">
													<label>Proceso: </label>
												</div>
												<div id="pnlSelProceso" class="col-md-3">
													<select id="selProceso" name="selProceso" class="cboProceso form-control form-control-single" onchange="cargarTiposExpedientes()">
														<option value="">Seleccione</option>
													</select> 
												</div>
												<div class="col-md-2">
													<label>Tipo de Expediente: </label>
												</div>
												<div id="pnlSelTipoExpediente" class="col-md-4">
													<select id="selTipoExpediente" name="selTipoExpediente" class="cboTipoExpediente form-control form-control-single" onchange="cargarTiposDocumentos()">
														<option value="">Seleccione</option>
													</select> 
												</div>
											</div>
											
											<div class="row content-box">
												<div class="col-md-12">
													&nbsp;
												</div>
											</div>
											
											<div class="row content-box">
												
												<div class="form-group">
													
													<div class="col-md-6">
														
														<div class="row content-box">
															<div class="col-md-6">
																<label>N&deg; Expediente Origen: </label>
															</div>
															<div class="col-md-6">
																<input id="txtNumExpOrigen" name="txtNumExpOrigen" type="text" class="form-control form-control-single" maxlength="17" onkeypress="return permitirNumeros(event);" onblur="validarNumeroExpedienteOrigen();"/>
															</div>
														</div>
														<div class="row content-box">
															&nbsp;
														</div>
														<div class="row content-box">
															<div class="col-md-6">
																<label>Fecha Notificaci&oacute;n: </label>
															</div>
															<div class="col-md-6">
																<input id="txtFechaNotificacion" name="txtFechaNotificacion" type="text" class="form-control form-control-single"  readonly="readonly"/>
															</div>
														</div>
														<div class="row content-box">
															&nbsp;
														</div>
														
													</div>
													
													<div class="col-md-6">
														
														<div class="row content-box">
															<div class="col-md-4">
																<label>Descripci&oacute;n: </label>
															</div>
															<div class="col-md-8">
																<textarea name="txtDescripcion" id="txtDescripcion" class="form-control form-control-textarea" rows="4" maxlength="150" height="100%" onchange="actualizarDescripcion()"></textarea>
															</div>
														</div>
														
													</div>
													
												</div>
											</div>
											
											<div class="row content-box">
															&nbsp;
														</div>
											<div class="row content-box">
												<div class="col-md-3">
													<label>N&deg; Registro: </label>
												</div>
												<div class="col-md-2">
													<input id="txtCodResponsable" name="txtCodResponsable" type="text" class="form-control form-control-single upperText" maxlength="4" onkeypress="return permitirAlfaNumerico(event);" onblur="validarPersona();"/>
												</div>
												<div class="col-md-1">
													&nbsp;
												</div>
												<div class="col-md-2">
													<label>Responsable: </label>
												</div>
												<div class="col-md-4">
													<input id="txtDesResponsable" name="txtDesResponsable" type="text" class="form-control form-control-single" readonly="readonly"/> 
												</div>
											</div>
											
											<div class="row content-box">
												<div class="col-md-12">
													&nbsp;
												</div>
											</div>
											
											<div class="separator"></div>
											
											<div class="row content-box">
												<div class="col-md-12">
													&nbsp;
												</div>
											</div>
											
											<div class="row content-box">
												<div class="col-md-3">
													<label>Tipo Documento Origen: </label>
												</div>
												<div id="pnlSelTipoDocumento" class="col-md-4">
													<select id="selTipoDocumento" name="selTipoDocumento" class="cboTipoDocumento form-control form-control-single" onchange="validarTipoDocumento()">
														<option value="">Seleccione</option>
													</select> 
												</div>
												<div class="col-md-3">
													&nbsp;
												</div>
											</div>
											
											<div class="row content-box">
												<div class="col-md-12">
													&nbsp;
												</div>
											</div>
											
											<div class="row content-box">
												<div class="col-md-3">
													<label>N&deg; Documento Origen: </label>
												</div>
												<div class="col-md-4">
													<input id="txtNumDocumento" readonly="readonly" name="txtNumDocumento" type="text" class="form-control form-control-single" maxlength="50" onkeypress="return permitirNumeros(event);"  onblur="validarNrodocumentoOrigen();"  />
												</div>
												<div class="col-md-4">
													&nbsp;
												</div>
											</div>
											
											<div class="row content-box">
												<div class="col-md-12">
													&nbsp;
												</div>
											</div>
											
											<div class="row content-box">
												<div class="col-md-3">
													<label>Fecha Documento Origen: </label>
												</div>
												<div class="col-md-3">
													<div>
														<div class='input-group date col-md-7' id='txtFecDocumentoGrupo'>
															<!-- <input id="txtFecDocumento" name="txtFecDocumento" type='text' class="form-control form-control-single" onkeypress="validarTipoFecha()" maxlength="10"/> -->
															<input id="txtFecDocumento" name="txtFecDocumento" type='text' class="form-control form-control-single" maxlength="10" readonly="readonly" onchange="validarTipoFecha()"/>
															<span class="input-group-addon">
																<!-- <span class="glyphicon glyphicon-calendar"></span> -->
																<span class="fa fa-calendar"></span>
															</span>
														</div>
														<input id="txtFecDocumentoCompleta" name="fechaDocCompleta" type="hidden" value=""/>
													</div>
												</div>
											</div>
											
											<div class="row content-box">
												<div class="col-md-12">
													&nbsp;
												</div>
											</div>
											
											<div class="row content-box">
												<div class="col-md-3">
													<label>Archivo Seleccionado: </label>
												</div>
												
											    <!-- Inicio nchavez 20/06/2016-->
												<div class="col-md-7" id="manejadorArchivo">
													<input id="nomDocumentoFisico" name="nomDocumentoFisico" type="hidden"/>
													<input name="docFisico" id="docFisico" type="file" onchange='validarArchivoUpload();' class="form-control">
													
												</div>
												<div class="col-md-2" align="right">
												<input name="verDocumento" id="verDocumento"  type="button" class="btn btn-primary" onclick='descargarArchivo();' class="form-control" value="Ver Documento">
												</div>
												<!-- <div class="col-md-2" id="manejadorArchivo">
													<input name="verDocumento" id="verDocumento"  type="button" class="btn btn-primary" onclick='descargarArchivo();' class="form-control" value="Ver Documento">
													
												</div> -->
												 <!-- Fin nchavez 20/06/2016-->
												
											</div>
											
										</div>
										<br/><br/>
									</fieldset>
									
								</div>
								
							</div>
							
							<div class="row content-box">
								<div class="col-md-12">
									&nbsp;
								</div>
							</div>
							
							<div class="row content-box">
								
								<div class="col-md-12">
									
									<fieldset class="fsStyle">
										
										<legend class="legendStyle">Palabras Clave de B&uacute;squeda</legend>
										
										<div class="marginedDiv">
											
											<div class="row content-box">
												<div class="col-md-8">
													<textarea name="txtBusquedaClave" id="txtBusquedaClave" placeholder='Ingresar las palabras claves de b&uacute;squeda separados por ";". Ejemplo: etiqueta1; etiqueta2' class="form-control form-control-textarea" rows="4" maxlength="200" height="100%" onblur="validarBusqueda()"></textarea>
												</div>
												<div class="col-md-4">
													&nbsp;
												</div>
											</div>
											
										</div>
										
									</fieldset>
								</div>
							</div>
							
							<div class="row content-box">
								<div class="col-md-5">
									&nbsp;
								</div>
								<div class="col-md-7">
									<div class="marginedDiv">
										<div class="form-group pull-right">
											<input id="btnVerDatos" type="button" class="btn btn-primary" onclick="verDatos()" value="Vista Previa" />
											<input id="btnRegistrar" type="button" class="btn btn-primary" onclick="registrar()" value="Registrar Expediente Virtual" />
											<input id="btnLimpiar" type="button" class="btn btn-primary" onclick="limpiar()" value="Limpiar" />
										</div>
									</div>
								</div>
							</div>
							
						</div>
						
					</div>
					
				</div>
			</form>	
			</div>
			
		</div>
		<input type="hidden" id="coddependencia" name="coddependencia" value"" />
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
		
		<script src="../a/js/libs/jquery/1.11.2/jquery.min.js" type="text/javascript"></script>
		<script src="../a/js/libs/bootstrap/3.3.2/js/bootstrap.min.js" type="text/javascript"></script>
		<script src="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/media/js/jquery.dataTables.min.js" type="text/javascript"></script>    
		<script src="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Responsive/js/dataTables.responsive.js" type="text/javascript"></script>
		<script src="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/TableTools/js/dataTables.tableTools.min.js" type="text/javascript"></script>
		<script src="../a/js/js.js" type="text/javascript"></script>
		<script src="../a/js/bootstrap/3.2.0/js/jquery.blockUI.js" type="text/javascript"></script>
		
		<script src="../a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/js/moment-with-locales.js" type="text/javascript"></script>
		<script src="../a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
		
		<script src="../a/js/libs/bootstrap/3.3.2/plugins/jquery.inputmask-3.1/dist/jquery.inputmask.bundle.min.js" type="text/javascript"></script>
		
		<script>
			
			var metodoInvocar="validarArchivo";
			var titulos = ${titulos};
			var excepciones = ${excepciones};
			var avisos = ${avisos};
			var fecVista = ${fecVista};
			var codDependenciaBase = ${codDependenciaBase};
			var desDependencia = ${desDependencia};
			var listadoProcesos = ${listadoProcesos};
			var codTipoExpedienteEspecial = ${codTipoExpedienteEspecial};
			var codTipoExpedienteEspecial = ${codTipoExpedienteEspecial};
			var numBytesArchivo = ${numBytesArchivo};
			var numMegasArchivo = ${numMegasArchivo};
			var indValidaciones = [false, false, false, false, true, false, false, false, false, false, false];//07/06/2016 gangles
			//var codCampoValidacion = ['#txtNumRuc', '#selProceso', '#selTipoExpediente', '#txtNumExpOrigen', '#txtCodResponsable', '#selTipoDocumento', '#txtDescripcion', '#txtNumDocumento', '#txtFecDocumento', '#txtIndicadorECM', '#docFisico', '#txtBusquedaClave'];
			/*
			0 = 'RUC',
			1 = 'PROCESO',
			2 = 'TIPO EXPEDIENTE',
			3 = 'NUMERO EXPEDIENTE ORIGEN',
			4 = 'DESCRIPCION',
			5 = 'NUM REGISTRO' / 'CODIGO RESPONSABLE',
			6 = 'TIPO DOCUMENTO ORIGEN',
			7 = 'NUMERO DOCUMENTO ORIGEN',
			8 = 'FECHA DOCUMENTO ORIGEN',
			9 = 'ARCHIVO',
			10 = 'PALABRAS CLAVE'
			*/
			var codCampoValidacion = ['#txtNumRuc', '#selProceso', '#selTipoExpediente', '#txtNumExpOrigen','#txtDescripcion', '#txtCodResponsable', '#selTipoDocumento', '#txtNumDocumento', '#txtFecDocumento', '#docFisico', '#txtBusquedaClave'];
			var desCampoValidacion = ['RUC', 'Proceso', 'Tipo de Expediente', 'N° Expediente Origen','Descripci&oacute;n', 'N° Registro', 'Tipo Documento Origen', 'N° Documento Origen', 'Fecha Documento Origen', 'Archivo Seleccionado', 'Palabras Clave de Búsqueda'];
			
			$(document).ready(function() {
				
				$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
				
				setPlaceHolder();
				inicializarCampos();
				
				inicializarProcesos();
				
				$("#txtFecDocumentoGrupo").on("dp.change", function (e) {
					if($('#txtFecDocumento').val()==""){
						indValidaciones[8] = false;
					}else{
						indValidaciones[8] = true;
					}
					
				});
				
			});
			
			function setPlaceHolder() {
				$('textarea[placeholder]').each(function () {
					var obj = $(this);

					if (obj.attr('placeholder') != '') {
						obj.addClass('IePlaceHolder');

						if ($.trim(obj.val()) == '' && obj.attr('type') != 'password') {
							obj.val(obj.attr('placeholder'));
						}
					}
				});

				$('.IePlaceHolder').focus(function () {
					var obj = $(this);
					if (obj.val() == obj.attr('placeholder')) {
						obj.val('');
					}
				});

				$('.IePlaceHolder').blur(function () {
					var obj = $(this);
					if ($.trim(obj.val()) == '') {
						obj.val(obj.attr('placeholder'));
					}
				});	
			}
			
			$("#txtBusquedaClave").keypress(function(e){
				   var _msgLenght = $(this).val().length;
				   
				   if (_msgLenght > 199) {
						$(this).val($(this).val().substring(0, 199));
				   } else {
					    var txt = String.fromCharCode(e.which);
						
						if (!txt.match(/[A-Za-z0-9\u00F1\u00D1\u00E1\u00E9\u00ED\u00F3\u00FA\u00C1\u00C9\u00CD\u00D3\u00DA;]/)) {
							return false;
						}	
				   }
			});
			
			function validarArchivoUpload() {
				
			var input = $('#docFisico');
				
				var nombreArchivo =  label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
				if(nombreArchivo.length>100){
					var control = $("#docFisico");
						control.replaceWith( control = control.clone( true ) );
						control.val(null);
						$("#docFisico").val("");
						mostrarDlgInfo(titulos.tituloDefecto, "El nombre del archivo a cargar debe tener un m&aacute;ximo de 100 car&aacute;cteres.");
					return;
				}
				
				//$('html').block({message: '<h1>Procesando</h1>'});
				$("#docFisico").appendTo($('#frmAuxiliar'));
				$('#frmAuxiliar').submit();
				$("#docFisico").appendTo($('#manejadorArchivo'));
				//$('html').unblock();
				
			}
			
			$("#frmAuxiliar").submit(function(e){
				
				var formObj = $(this);
				var iframeId = 'unique' + (new Date().getTime());
				
				var iframe = $('<iframe src="javascript:false;" name="'+iframeId+'" />');
				iframe.hide();
				formObj.attr('target', iframeId);
				formObj.attr('action', '${pageContext.request.contextPath}/regiExpeVirt.htm?action='+metodoInvocar);
				formObj.attr("enctype", "multipart/form-data");
				formObj.attr("encoding", "multipart/form-data");
				//formObj.attr("accept-charset","utf-8");
				iframe.appendTo('body');
				
				iframe.load(function(e){
					
					var doc = getDoc(iframe[0]);
					var docRoot = doc.body ? doc.body : doc.documentElement;
					var data = docRoot.innerHTML;
					var jsonDataString;
					var response;
					var indError;
					indError = false;
					if (data.toLowerCase().indexOf("error") >= 0) {
						indError = true;
					}
					
					if(indError) {
						
						//document.write(doc.documentElement.innerHTML);
						mostrarPagError();
						
					} else {
						
						if (data.indexOf("<pre style") > -1) {
							
							jsonDataString = data.replace('<pre style="word-wrap: break-word; white-space: pre-wrap;">', '');
							jsonDataString = jsonDataString.replace('</pre>', '');
							
						} else {
							jsonDataString = data.replace('<PRE>', '');
							jsonDataString = jsonDataString.replace('</PRE>', '');						
							jsonDataString = jsonDataString.replace('<pre>', '');
							jsonDataString = jsonDataString.replace('</pre>', '');
						}
						
						response = jQuery.parseJSON(jsonDataString);
						if(metodoInvocar=="descargarDocumento"){
							return true;
						}
						if (response.tamanoArchivoSuperado) {
							
							$("#nomDocumentoFisico").val("");
							var control = $("#docFisico");
							control.replaceWith( control = control.clone( true ) );
							control.val(null);
							$("#docFisico").val("");
							indValidaciones[9] = false;
							var mensajeExcepcion = excepciones.excepcion10;
							
							var mensajeExcepcion = mensajeExcepcion.replace("{0}", response.tamanoArchivoPermitido);
							
							mostrarDlgInfo(titulos.tituloDefecto, mensajeExcepcion);
							
						} else if(!response.extensionPermitida){
							$("#nomDocumentoFisico").val("");
							var control = $("#docFisico");
							control.replaceWith( control = control.clone( true ) );
							control.val(null);
							$("#docFisico").val("");
							indValidaciones[9] = false;
							var mensajeExcepcion = excepciones.excepcion13;						
							
							
							mostrarDlgInfo(titulos.tituloDefecto, mensajeExcepcion);
						} else if(!response.longitudnombrearchivo){
							$("#nomDocumentoFisico").val("");
							var control = $("#docFisico");
							control.replaceWith( control = control.clone( true ) );
							control.val(null);
							$("#docFisico").val("");
							indValidaciones[9] = false;
							var mensajeExcepcion = excepciones.excepcion14;						
							
							
							mostrarDlgInfo(titulos.tituloDefecto, mensajeExcepcion);
						} else {
							indValidaciones[9] = true;
							$('#nomDocumentoFisico').val($("#docFisico").val());
							
						}
						
			    	}
					
			    });
				
			});
			
			 //obtiene el contenido del iFrame
			function getDoc(frame) {
		    	
				var doc = null;
		    	
				try {
		    		
					if (frame.contentWindow) {
						
						doc = frame.contentWindow.document;
						
					}
					
		    	} catch(err) {
		    	
				}
		    	
				if(doc){
		    		
					return doc;
					
		    	}
		    	
				try {
		    		
					doc = frame.contentDocument ? frame.contentDocument : frame.document;
					
		    	} catch(err) {
		    		
					doc = frame.document;
					
		    	}
				
		    	return doc;
				
		    }
			
			function inicializarCampos() {
				
				$('#codArchivosAnchor').on('click', function(e){
					$('#docFisico').click();
					e.preventDefault();
				});
				
				$('#txtFecDocumentoGrupo').datetimepicker({
					format: 'DD/MM/YYYY',
					language: 'es',
					autoclose: true,
					forceParse: false,
					pickTime: false,
					maxDate: ${fecVista}
				});
				
				$('#txtFechaRegistro').attr("value", fecVista);
				
				setearMascara( '#txtNumRuc', "(9){1,11}", 11, '' );
				setearMascara( '#txtNumExpOrigen', "(9){1,17}", 17, '' );
				setearMascara( '#txtCodResponsable', "(a|9){1,4}", 4, '' );
				setearMascara( '#txtNumDocumento', "(9){1,50}", 50, '' );
				
			}

			function setearMascara( controlQuery, mascara, maxlength, valorInicial ) {
				
				var control = $( controlQuery );
				
				control.inputmask( mascara, {placeholder: ''});
				
				if ( maxlength != null ) {
					control.prop('maxlength', maxlength);	
				}
				
				if ( valorInicial != null ) {
					control.val( valorInicial );	
				}
				
			}
			
			function inicializarProcesos() {
				
				var $element = $('#selProceso');
				
				$.each(listadoProcesos, function(i, dato) {
				
					var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
					$element.append($option);
					
				});
				
			}
			
			function cargarTiposExpedientes() {
				
				var codProceso = $('#selProceso').val();
				
				var dataEnvio = new Object();
				
				$('#selTipoExpediente').empty();
				
				$('#selTipoExpediente').append($('<option>', {
					value: '',
					text: 'Seleccione'
				}));
				// Inicio [jjurado 26/05/2016] 
				$('#txtFechaNotificacion').val("");
				$('#txtCodResponsable').val("");
				$('#txtDesResponsable').val("");
				$('#txtCodResponsable').prop( "disabled", false );
				// Fin [jjurado 26/05/2016]
				if (codProceso == "") {
					
					indValidaciones[1] = false;
					
					$('#selTipoExpediente').val('');
					
					cargarTiposDocumentos();
					
				} else {
					indValidaciones[1] = true;
					indValidaciones[2] = false;
					
					dataEnvio.codProceso = codProceso;
					
					$.ajax({
						
						url : '${pageContext.request.contextPath}/regiExpeVirt.htm?action=cargarRegistroExpedienteVirtual&indCarga=1&indFiltro=0',
						type : 'POST',
						async : true,
						dataType : "json",
						data : JSON.stringify(dataEnvio),
						contentType : "application/json",
						mimeType : "application/json",
						timeout : 5000,
						success : function(response) {
							
							var listadoTiposExpendientes = response.listadoTiposExpendientes
							
							if (listadoTiposExpendientes.length > 0) {
								
								var $element = $('#selTipoExpediente');
								
								$.each(listadoTiposExpendientes, function(i, dato) {
								
									var $option = $("<option/>").attr("value", dato.codTipoExpediente).text(dato.desTipoExpediente);
									$element.append($option);
									
								});
								
								cargarTiposDocumentos();
								
							} else {
								
								cargarTiposDocumentos();
								
								mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion04);
								
							}
							
						},
						error : function(response) {
							
							mostrarPagError();
							
						}
						
					});
					
				}
				
			}
			function actualizarDescripcion(){
				if($('#txtDescripcion').val()==""){
					indValidaciones[4] = true;
				}else{
					indValidaciones[4] = true;
				}
			}
			function validarTipoFecha(){				
				if($('#txtFecDocumento').val()==""){					
					indValidaciones[8] = false;
				}else{					
					indValidaciones[8] = true;
				}
			}
			function validarBusqueda(){
				var palabraClave =  $('#txtBusquedaClave').val();
				if(palabraClave==""){
					indValidaciones[10] = true;
				}else{
					indValidaciones[10] = true;
				}
			}
			
			//Campos especiales son los que se habilitan o desabilitan
			//segun se seleccione tipo expediente "cobranza coactiva"
			function habilitarCamposEspeciales(habilitar){
			
				if(habilitar){
					//$('#selTipoDocumento').removeAttr('disabled');
					//$('#selTipoDocumento').removeAttr('readonly');
					$('#selTipoDocumento').attr('disabled', false);
					$("#txtNumDocumento").removeAttr('readonly');
					$("#txtNumDocumento").removeAttr('disabled');
					$("#txtFecDocumento").removeAttr('disabled');
					$("#txtCodResponsable").removeAttr('disabled');
				}else{
					//$('#selTipoDocumento').attr('readonly', true);
					//$('#selTipoDocumento').attr('disabled', 'disabled');
					$('#selTipoDocumento').attr('disabled', true);
					$("#txtNumDocumento").attr('readonly', true);
					$("#txtFecDocumento").val('');
					$("#txtFecDocumentoCompleta").val('');
					$("#txtFecDocumento").attr('disabled', 'disabled');
					$("#txtCodResponsable").attr('disabled', 'disabled');
				}
			
			}
			
			function limpiarCamposEspeciales(){
				$('#selTipoDocumento').val("");
				$('#txtNumDocumento').val("");
				$('#txtFecDocumento').val("");
				$('#txtFecDocumentoCompleta').val("");
			}
			
			function limpiarCampos(){
				//Inicio nchavez 07/07/2016
					$('#txtNumExpOrigen').val('');
					$('#txtCodResponsable').val('');
					$('#txtDescripcion').val('');
					$('#txtFechaNotificacion').val('');	
					$('#txtBusquedaClave').val('');
				//Fin nchavez 07/07/2015
			}

			function cargarTiposDocumentos(){
				
				var codTipoExpediente = $('#selTipoExpediente').val();
				limpiarCamposEspeciales();
				var dataEnvio = new Object();
				
				$('#selTipoDocumento').empty();
				
				$('#selTipoDocumento').append($('<option>', {
					value: '',
					text: 'Seleccione'
				}));
				//Inicio nchavez 07/07/2016
				limpiarCampos();
				///Fin nchavez 07/07/2015
				if (codTipoExpediente == "") {
					 
					indValidaciones[2] = false;
					indValidaciones[5] = false;
					
					$('#selTipoDocumento').val('');

					habilitarCamposEspeciales(true);
					
				} else {
					
					indValidaciones[2] = true;
					indValidaciones[5] = false;
					
					/* Fragmento de Validación - Cobranza Coactiva - Inicio */
					
					if (codTipoExpediente == codTipoExpedienteEspecial) {
						habilitarCamposEspeciales(false);

						var numExpOrigen = $("#txtNumExpOrigen").val();
						$("#txtNumDocumento").val(numExpOrigen);
						$("#txtFecDocumento").val('');
						$("#txtFecDocumentoCompleta").val('');
						
						
					} else {
						habilitarCamposEspeciales(true);
					}
					
					/* Fragmento de Validación - Cobranza Coactiva - Final */
					
					dataEnvio.codTipoExpediente = codTipoExpediente;
					
					$.ajax({
						
						url : '${pageContext.request.contextPath}/regiExpeVirt.htm?action=cargarRegistroExpedienteVirtual&indCarga=1&indFiltro=1',
						type : 'POST',
						async : true,
						dataType : "json",
						data : JSON.stringify(dataEnvio),
						contentType : "application/json",
						mimeType : "application/json",
						timeout : 5000,
						success : function(response) {
							
							var listadoTiposDocumentos = response.listadoTiposDocumentos
							
							if (listadoTiposDocumentos.length > 0) {
								
								var $element = $('#selTipoDocumento');
								
								$.each(listadoTiposDocumentos, function(i, dato) {
								
									var $option = $("<option/>").attr("value", dato.codTipoDocumento).text(dato.desTipoDocumentoCompuesto);
									$element.append($option);
									
								});
								if($('#txtNumExpOrigen').val() != ''){
									validarNumeroExpedienteOrigen();
								}
							} else {
								
								mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion05);
								
							}
							
						},
						error : function(response) {
							
							mostrarPagError();
							
						}
						
					});
					
				}
				
			}
			
			function ValidarRucKey() {
				var numRuc = $("#txtNumRuc").val();
				if(numRuc.length==11){
					validarRUC();
				}
			}
			
			function validarRUCBlur(){
				var numRuc = $("#txtNumRuc").val();
				
				if(numRuc==""){
					$('#txtDesRazonSocial').val("");
					$('#txtDependencia').val("");
					indValidaciones[0] = false;
				}
				if(numRuc.length > 0 && numRuc.length < 11){
					validarRUC();
				}
			}
			
			function validarRUC() {
				
				var numRuc = $("#txtNumRuc").val();
				if ( numRuc == "") {
					$('#txtDesRazonSocial').val("");
					$('#txtDependencia').val("");
					return;
				}
				if ((numRuc.length > 0 && numRuc.length < 11)) {
					
					indValidaciones[0] = false;
					
					//$('#txtDesRazonSocial').attr("value", "");
					//$('#txtDependencia').attr("value", "");
					$('#txtDesRazonSocial').val("");
					$('#txtDependencia').val("");
					//return;
					mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion01);
					
				} else {
					
					if(!valruc(numRuc)) {
						
						indValidaciones[0] = false;
						
						//$('#txtDesRazonSocial').attr("value", "");
						//$('#txtDependencia').attr("value", "");
						$('#txtDesRazonSocial').val("");
						$('#txtDependencia').val("");
						
						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion01);
						
					} else {
						
						verificarRUC(numRuc);
						
					}
					
				}
				
			}
			
			function verificarRUC(numRuc) {
				
				var dataEnvio = new Object();
				
				dataEnvio.numRuc = numRuc;
				dataEnvio.codDependenciaBase = codDependenciaBase;//[oachahuic][PAS20165E210400270]
				
				$.ajax({
					
					url : '${pageContext.request.contextPath}/regiExpeVirt.htm?action=cargarRegistroExpedienteVirtual&indCarga=1&indFiltro=2',
					type : 'POST',
					async : true,
					dataType : "json",
					data : JSON.stringify(dataEnvio),
					contentType : "application/json",
					mimeType : "application/json",
					timeout : 5000,
					success : function(response) {
						
						var contribuyente = response.contribuyente
						if (contribuyente.numRuc != null && contribuyente.numRuc != "") {
							
							var codDependencia = contribuyente.codDependencia;							
							$('#coddependencia').val(codDependencia);
							
							if (codDependenciaBase.substring(0, 3) == codDependencia.substring(0, 3)) {
								
								var desRazonSocial = contribuyente.desRazonSocial;
								
								indValidaciones[0] = true;
								
								$('#txtDesRazonSocial').val(desRazonSocial);
								$('#txtDependencia').val(contribuyente.desDependencia);
								
							} else {
								
								indValidaciones[0] = false;
								
								$('#txtDesRazonSocial').val("");
								$('#txtDependencia').val("");
								mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion03);
								
							}
							
						} else {
							
							indValidaciones[0] = false;
							
							$('#txtDesRazonSocial').val("");
							$('#txtDependencia').val("");
							
							mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion02);
							
						}
						
					},
					error : function(response) {
						
						mostrarPagError();
						
					}
					
				});
				
			}
			
			function validarNumeroExpedienteOrigen() {
				
				var num_ruc = $("#txtNumRuc").val();
				var numExpOrigen = $("#txtNumExpOrigen").val();
				var codTipoExpediente = $('#selTipoExpediente').val();
				var codTiposProceso = $('#selProceso').val();
				var coddependencia = $("#coddependencia").val();
				
				
				
				// si no ha ingresado valor
				if(numExpOrigen==""){					
					indValidaciones[3] = false;
					return;
				}
				if(num_ruc==""){
					mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion15);	
					return;
				}
				
				if(codTiposProceso=="" || codTipoExpediente==""){
					mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion16);	
					return;
				}
				
				
				if (numExpOrigen == '' || (numExpOrigen.length < 1)) {
					
					indValidaciones[3] = false;
					//indValidaciones[7] = false;
					
					mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion06);										
					$('#selTipoDocumento').val("");
					$('#txtNumDocumento').val("");
					$('#txtFecDocumento').val("");
					$('#txtFecDocumentoCompleta').val("");
					return;
				} else {
					
					indValidaciones[3] = true;
					//indValidaciones[7] = true;
					
				}
				
				var url;
				
				url = '${pageContext.request.contextPath}/regiExpeVirt.htm?action=validarNumExpedienteOrigen&numExpedo=' + numExpOrigen + '&coddependencia=' + coddependencia + '&numruc=' + num_ruc + '&codTipoExpediente=' + codTipoExpediente;
				
				$.ajax({
					url : url,
					type : 'POST',
					async : false,
					dataType : "json",
					data : '',
					contentType : "application/json",
					mimeType : "application/json",
					timeout : 5000,
					success : function(response) {
						var expOrigen = response.expOrigen;//[PAS20191U210500076][OAC] - INICIO
						if (expOrigen.codRpta == "0") {//ERROR
							indValidaciones[3] = false;
							habilitarCamposEspeciales(false);
							$('#txtFechaNotificacion').val("");
							$('#txtDescripcion').val("");
							$('#txtCodResponsable').val("");
							$('#txtDesResponsable').val("");
							$('#txtCodResponsable').prop( "disabled", false );
							$('#selTipoDocumento').val("");
							$('#txtNumDocumento').val("");
							$('#txtFecDocumento').val("");
							$('#txtFecDocumentoCompleta').val("");
							mostrarDlgInfo(titulos.tituloDefecto, expOrigen.desError);
						} else if (expOrigen.codRpta == "1") {//EXITO
							habilitarCamposEspeciales(false);
							$('#txtFechaNotificacion').val(expOrigen.fecNotDocApe);
							$('#txtDescripcion').val(expOrigen.desExp);
							$('#txtCodResponsable').val(expOrigen.codRespExp);
							$('#txtDesResponsable').val(expOrigen.desRespExp);
							$('#selTipoDocumento').val(expOrigen.codTipDocApe);
							$('#txtNumDocumento').val(expOrigen.numDocApe);
							$('#txtFecDocumento').val(expOrigen.fecEmiDocApe);
							$('#txtFecDocumentoCompleta').val(expOrigen.strFecEmiDocApe);
							$('#txtBusquedaClave').val(expOrigen.plbsClave);
							indValidaciones[6] = true;
							indValidaciones[7] = true;
							indValidaciones[8] = true;
							if (expOrigen.codRespExp == "") {
								$('#txtCodResponsable').prop("disabled", false);
								indValidaciones[5] = false;
							} else {
								$('#txtCodResponsable').prop("disabled", true);
								indValidaciones[5] = true;
							}
						} else {//NO SE VALIDA EL N° DE EXPEDIENTE ORIGEN
							habilitarCamposEspeciales(true);
							var tipoDoc = $('#selTipoDocumento').val();
							if (tipoDoc == "") {
								indValidaciones[6] = false;
							} else {
								indValidaciones[6] = true;
							}
							var numDoc = $('#txtNumDocumento').val();
							if (numDoc == "") {
								indValidaciones[7] = false;
							} else {
								indValidaciones[7] = true;
							}
							var fecDoc = $('#txtFecDocumento').val();
							if (fecDoc == "") {
								indValidaciones[8] = false;
							} else {
								indValidaciones[8] = true;
							}
							var codResponsable = $('#txtCodResponsable').val();
							if (codResponsable == "") {
								indValidaciones[5] = false;
							} else {
								indValidaciones[5] = true;
							}
						}//[PAS20191U210500076][OAC] - FIN
					},
					error : function(response) {
						mostrarPagError();
					}
				});
			}
			
			function validarPersona() {
				
				var codResponsable = $("#txtCodResponsable").val();
				
				//if (codResponsable == '' || (codResponsable.length < 4)) {
				if (codResponsable == '' || (codResponsable.length > 0 && codResponsable.length < 4)) {
					
					indValidaciones[5] = false;
					
					//$('#txtDesResponsable').attr("value", "");
					$('#txtDesResponsable').val("");
					if(codResponsable.length > 0 && codResponsable.length < 4){
						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion07);
					}
					
					return;
					
				} else {
					
					verificarPersona(codResponsable);
					
				}
				
			}
			
			function verificarPersona(codResponsable) {
				
				var dataEnvio = new Object();
				
				dataEnvio.codResponsable = codResponsable.toUpperCase();
				dataEnvio.codDependenciaBase = codDependenciaBase;
				
				$.ajax({
					
					url : '${pageContext.request.contextPath}/regiExpeVirt.htm?action=cargarRegistroExpedienteVirtual&indCarga=1&indFiltro=3',
					type : 'POST',
					async : true,
					dataType : "json",
					data : JSON.stringify(dataEnvio),
					contentType : "application/json",
					mimeType : "application/json",
					timeout : 5000,
					success : function(response) {
						
						var responsable = response.responsable
						
						if (responsable != null) {
							
							var codDependencia = responsable.codDependencia;
							if (codDependenciaBase.substring(0, 3) == codDependencia.substring(0, 3)) {
								
								indValidaciones[5] = true;
								
								var desNombreCompleto = responsable.desNombreCompleto;
								
								$('#txtDesResponsable').val(desNombreCompleto);
								
								
							} else {
								
								indValidaciones[5] = false;
								
								//$('#txtDesResponsable').attr("value", "");
								$('#txtDesResponsable').val("");
								
								mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion09);
								
							}
							
						} else {
							
							indValidaciones[5] = false;
							
							//$('#txtDesResponsable').attr("value", "");
							$('#txtDesResponsable').val("");
							
							mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion08);
							
						}
						
					},
					error : function(response) {
						
						mostrarPagError();
						
					}
					
				});
				
			}
			
			function validarTipoDocumento() {
				
				var codTipoDocumento = $('#selTipoDocumento').val();
				
				if (codTipoDocumento == "") {
					
					indValidaciones[6] = false;
					
				} else {
					
					indValidaciones[6] = true;
					
				}
				
			}
			
			function validarNrodocumentoOrigen() {
				
				var nroDocumentoOrigen = $('#txtNumDocumento').val();
				
				if (nroDocumentoOrigen == "") {
					
					indValidaciones[7] = false;
					
				} else {
					
					indValidaciones[7] = true;
					
				}
				
			}
			
			function registrar() {
				if (validarCampos()) {
					botones = [];
					
					var aceptar = $("<input/>").attr(
						{
							value: "Aceptar", 
							type: "button", 
							"class": "btn btn-primary dlgButton", 
							"data-dismiss" : "modal", 
							onclick : "ejecutarRegistro()"
						}
					);
					
					var cancelar = $("<input/>").attr(
						{
							value: "Cancelar", 
							type: "button", 
							"class": "btn btn-primary dlgButton", 
							"data-dismiss" : "modal", 
							onclick : "$('#modalDlg').modal('hide')" 
						}
					);
					
					botones.push(aceptar);
					botones.push(cancelar);
					
					crearDlg(titulos.tituloDefecto, avisos.aviso01, botones);
					
				}
				
			}
			
			function ejecutarRegistro() {
				
				prepararEnvioFinal();
				//$.blockUI({ message: '<h1>Procesando</h1>' });				
				
				$("#docFisico").appendTo($('#frmPrincipal'));
				
				$('#frmPrincipal').submit();
				$("#docFisico").appendTo($('#manejadorArchivo'));
				
			}
			
			function validarCampos() {			
				var mensajeExcepcion = excepciones.excepcion11;
				for (var i in indValidaciones) {
					
					if (!indValidaciones[i]) {
						
						if ($(codCampoValidacion[i]).val().trim() == '') {
							
							var mensajeExcepcion = mensajeExcepcion.replace("{0}", desCampoValidacion[i]);
							
							mostrarDlgInfo(titulos.tituloDefecto, mensajeExcepcion);
							
						} else {
							
							if (i == 0) {
								
								validarRUC();
								
							} else  if (i == 3) {
								
								validarNumeroExpedienteOrigen();
								
							} else  if (i == 4 || i == 5) {
								
								validarPersona();
								
							}else  if (i == 7) {
								if($('#txtNumDocumento').attr("readonly")=="readonly"){
									continue;
								}
							}else  if (i == 10) {
								/*if($('#txtBusquedaClave').val() == $('#txtBusquedaClave').attr('placeholder')) {
									var mensajeExcepcion = mensajeExcepcion.replace("{0}", desCampoValidacion[i]);								
									mostrarDlgInfo(titulos.tituloDefecto, mensajeExcepcion);
								}*/
								return true;
							}
							
						}
						
						return false;
						
					}
					
				}
				
				var j = indValidaciones.length;
				
				var ignoraArchivo = true;
				
				for (var i = j; i < codCampoValidacion.length; i++) {
					
					if ($(codCampoValidacion[i]).val() == '') {
						
						/*if (codCampoValidacion[i] === '#txtIndicadorECM') {
							
							ignoraArchivo = false;
							
						} else */
						if (codCampoValidacion[i] === '#docFisico' && ignoraArchivo) {
							
							// Nothing
							
						} else {
							
							var mensajeExcepcion = mensajeExcepcion.replace("{0}", desCampoValidacion[i]);
							
							mostrarDlgInfo(titulos.tituloDefecto, mensajeExcepcion);
							
							return false;
							
						}
						
					}
					
				}
				
				return true;
				
			}
			
			function prepararEnvioFinal () {
				
				var numRuc = $('#txtNumRuc').val();
				var codProceso = $('#selProceso').val();
				var codTipoExpediente = $('#selTipoExpediente').val();
				var numExpedienteOrigen = $('#txtNumExpOrigen').val();
				var codResponsable = $('#txtCodResponsable').val().toUpperCase();
				var codTipoDocumento = $('#selTipoDocumento').val();
				var desDescripcion = $('#txtDescripcion').val();
				$('#hdnDescripcion').val(desDescripcion);
				var numDocumento = $('#txtNumDocumento').val();
				var fecDocumento = $('#txtFecDocumento').val();
				var fecDocumentoCompleta = $('#txtFecDocumentoCompleta').val();
				var busquedaClave = $('#txtBusquedaClave').val();
				
				//var codigoECM = '';
				/*
				if ($('#txtIndicadorECM').val() != '') {
					
					codigoECM = $('#txtIndicadorECM').val();
					
				}
				*/
				var dataEnvio = new Object();
				
				dataEnvio.numRuc = numRuc;
				dataEnvio.codProceso = codProceso;
				dataEnvio.codTipoExpediente = codTipoExpediente;
				dataEnvio.numExpedienteOrigen = numExpedienteOrigen;
				dataEnvio.codResponsable = codResponsable.toUpperCase();
				dataEnvio.codTipoDocumento = codTipoDocumento;
				dataEnvio.desDescripcion = desDescripcion;
				dataEnvio.numDocumento = numDocumento;
				dataEnvio.fecDocumento = fecDocumento;
				dataEnvio.fecDocumentoCompleta = fecDocumentoCompleta;
				dataEnvio.busquedaClave = busquedaClave;
				//dataEnvio.codigoECM = codigoECM;
				dataEnvio.codDependenciaBase = codDependenciaBase;//[oachahuic][PAS20165E210400270]
								
				$("#dataOculta").val(JSON.stringify(dataEnvio));
				
			}
			
			$("#frmPrincipal").submit(function(e){
				
				$('#btnRegistrar').prop('disabled', true);
				var formObj = $(this);
				var iframeId = 'unique' + (new Date().getTime());
				
				var iframe = $('<iframe src="javascript:false;" name="'+iframeId+'" />');
				
				//iframe.hide();
				formObj.attr('target', iframeId);
				formObj.attr('action', '${pageContext.request.contextPath}/regiExpeVirt.htm?action=registrarExpedienteVirtual');
				formObj.attr("enctype", "multipart/form-data");
				formObj.attr("encoding", "multipart/form-data");
				//formObj.attr("accept-charset","utf-8");
				iframe.appendTo('body');
				
				iframe.load(function(e){
					
					var doc = getDoc(iframe[0]);
					var docRoot = doc.body ? doc.body : doc.documentElement;
					var data = docRoot.innerHTML;
					var jsonDataString;
					var response;
					var indError;
					indError = false;
					if (data.toLowerCase().indexOf("error") >= 0) {
						indError = true;
					}
					
					if(indError) {
						
						//document.write(doc.documentElement.innerHTML);
						
						mostrarPagError();
						
					} else {
						
						if (data.indexOf("<pre style") > -1) { 
							
							jsonDataString = data.replace('<pre style="word-wrap: break-word; white-space: pre-wrap;">', '');
							jsonDataString = jsonDataString.replace('</pre>', '');
							
						} else {
							jsonDataString = data.replace('<PRE>', '');
							jsonDataString = jsonDataString.replace('</PRE>', '');
							jsonDataString = jsonDataString.replace('<pre>', '');
							jsonDataString = jsonDataString.replace('</pre>', '');
						}
						
						response = jQuery.parseJSON(jsonDataString);
						
						if (response.noexisterelacion) {
							//$.unblockUI();
							var mensajeAviso = avisos.aviso03;
							mostrarDlgInfo(titulos.tituloDefecto, mensajeAviso);
							
						} else if (response.duplicidad) {
							//$.unblockUI();
							var mensajeAviso = avisos.aviso04;
							mostrarDlgInfo(titulos.tituloDefecto, mensajeAviso);
							
						} else if (response.numExpedienteVirtual == undefined) {
							mostrarPagError();							
						} else {
							
							var numExpedienteVirtual = response.numExpedienteVirtual;
							var fecVista = response.fecVista;
							
							$("#txtNumExpVirtual").val(numExpedienteVirtual);
							$("#txtFechaRegistro").val(fecVista);
							
							$("#btnRegistrarRegistrar").prop("disabled", true);
							
							var mensajeAviso = avisos.aviso02.replace("{0}", numExpedienteVirtual);
							
							//$.unblockUI();
							//mostrarDlgInfo(titulos.tituloDefecto, mensajeAviso);
							mostrarDlgInfoFinal(titulos.tituloDefecto, mensajeAviso);
							
							
						}
						
			    	}
					
			    });
				iframe.hide();
				
			});
			
			function limpiar() {
				
				botones = [];
				
				var aceptar = $("<input/>").attr(
					{
						value: "Si", 
						type: "button", 
						"class": "btn btn-primary dlgButton", 
						"data-dismiss" : "modal", 
						onclick : "limpiarPagina()"
					}
				);
				
				var cancelar = $("<input/>").attr(
					{
						value: "No", 
						type: "button", 
						"class": "btn btn-primary dlgButton", 
						"data-dismiss" : "modal", 
						onclick : "$('#modalDlg').modal('hide')" 
					}
				);
				
				botones.push(aceptar);
				botones.push(cancelar);
				
				crearDlg(titulos.tituloDefecto, avisos.aviso00, botones);
				
			}
			
			function limpiarPagina () {
				
				$('#txtNumExpVirtual').val('');
				
				$("#docFisico").appendTo($('#frmAuxiliar'));
				frmAuxiliar.reset();
				$("#docFisico").appendTo($('#manejadorArchivo'));
				
				$('#txtNumRuc').val('');
				$('#txtDesRazonSocial').val('');
				$('#txtDependencia').val('');
				$('#selProceso').val('');
				$('#selTipoExpediente').val('');
				$('#txtNumExpOrigen').val('');
				$('#txtDescripcion').val('');
				$('#txtCodResponsable').val('');
				$('#txtDesResponsable').val('');
				$('#selTipoDocumento').val('');
				$('#txtNumDocumento').val('');
				$('#txtFecDocumento').val('');
				$('#txtFecDocumentoCompleta').val('');
				//$('#txtIndicadorECM').val('');
				$("#nomDocumentoFisico").val('');

				// Inicio [jjurado 14/06/2016]
				$('#txtFechaNotificacion').val('');
				$('#txtBusquedaClave').val('');
				// Fin [jjurado 14/06/2016]
				
				setPlaceHolder();
				
				frmPrincipal.reset();
				
				//indValidaciones = [false, false, false, false, false, false];
				indValidaciones = [false, false, false, false, true, false, false, false, false, false, false];//07/06/2016 gangles
						
				$("#btnRegistrar").prop("disabled", false);
				
			}
			
			function mostrarDlgInfo(titulo, msj){ 
				
				botones = [];
				
				var aceptar = $("<input/>").attr(
					{
						value: "Aceptar", 
						type: "button", 
						"class": "btn btn-primary dlgButton", 
						"data-dismiss" : "modal", 
						onclick : "$('#modalDlg').modal('hide')"
					}
				);
				
				botones.push(aceptar);
				crearDlg(titulo, msj, botones);
				
			}
			
			function mostrarDlgInfoFinal(titulo, msj){ 
				
				botones = [];
				
				var aceptar = $("<input/>").attr(
					{
						value: "Aceptar", 
						type: "button", 
						"class": "btn btn-primary dlgButton", 
						"data-dismiss" : "modal", 
						onclick : "finalizarCreacionExpediente()"
					}
				);
				
				botones.push(aceptar);
				crearDlg(titulo, msj, botones);
				
			}
			function finalizarCreacionExpediente(){
				$('#modalDlg').modal('hide');
				$('#btnRegistrar').prop('disabled', true);
			}
			
			function crearDlg(titulo, msj, btns){
				
				$('#dlgTitle').html(titulo);
				$('#dlgMsj').html(msj);
				$('#dlgBtns').empty();
				
				jQuery.each(btns, function(i, dato) {
					$('#dlgBtns').append(dato);
				});
				
				$('#modalDlg').modal('show');
				
			}
			
			//mostrar Página de Error
			function mostrarPagError() {
				
				var formURL = '${pageContext.request.contextPath}/error.htm?action=cargarPaginaError';
				document.forms.frmFinal.action = formURL;
				document.forms.frmFinal.method = "post";
				document.forms.frmFinal.submit();
				
			}
			// Inicio [jjurado 03/06/2016]
			function descargarArchivo(){
				
				//var tabla = $('#tblDocumentos').DataTable();
				
				
				metodoInvocar = "descargarDocumento";
				var archivo = $('#docFisico');
				$('#btnRegistrar').prop('disabled', true);	
				if($('#docFisico').val()==""){
					mostrarDlgInfo(titulos.tituloDefecto, "Debe adjuntar un archivo.");
					$('#btnRegistrar').prop('disabled', false);	
					metodoInvocar="validarArchivo";
					return;
				}
				$('html').block({message: '<h1>Procesando</h1>'});
				if (archivo != undefined) {
					$('html').block({message: '<h1>Procesando</h1>'});
					$("#docFisico").appendTo($('#frmAuxiliar'));
					$('#frmAuxiliar').submit();
					$("#docFisico").appendTo($('#manejadorArchivo'));
				}
				$('html').unblock();
				metodoInvocar = "validarArchivo";
				$('#btnRegistrar').prop('disabled', false);				
			}
			
			function verDatos(){
				var input = $('#docFisico');
				var nombreArchivo =  label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
				
				//fsw - AQ encodeURIComponent
				
				var url = "regiExpeVirt.htm?action=verDatosExpedientePDF";
					url+= "&txtCodResponsable="+encodeURIComponent($('#txtCodResponsable').val());
					url+= "&txtNumDocumento="+encodeURIComponent($('#txtNumDocumento').val());
					url+= "&txtFecDocumento="+encodeURIComponent($('#txtFecDocumento').val());
				    url+= "&desProceso="+encodeURIComponent($('#selProceso option:selected').text());
					url+= "&selTipoDocumento="+encodeURIComponent($('#selTipoDocumento option:selected').text());
					url+= "&desTipExpediente="+encodeURIComponent($('#selTipoExpediente option:selected').text());
					url+= "&nombreArchivo="+encodeURIComponent(nombreArchivo);
					url+= "&palBusquedaClave="+encodeURIComponent($('#txtBusquedaClave').val());
					url+= "&"+$(formDatosExpediente).serialize();
				window.open(url);
			}
			// Fin [jjurado 03/06/2016]
			function permitirNumeros(e){
				
				var keynum = window.event ? window.event.keyCode : e.which;
				
				if ((keynum == 8)) {
					
					return true;
					
				}
				
				return /\d/.test(String.fromCharCode(keynum));
				
			}
			
			function permitirAlfaNumerico(e) {
				
				if(((event.keyCode < 48) || (event.keyCode > 57)) && ((event.keyCode != 32) && (event.keyCode < 65) || (event.keyCode > 90) && (event.keyCode < 97) || (event.keyCode > 122))){
					
					event.returnValue = false;
					
				}
				
			}
			
			if (typeof String.prototype.trim !== 'function') {
				
				String.prototype.trim = function() {
					
					return this.replace(/^\s+|\s+$/g, '');
					
				}
				
			}
			
		</script>
		
	</body>
	
</html>