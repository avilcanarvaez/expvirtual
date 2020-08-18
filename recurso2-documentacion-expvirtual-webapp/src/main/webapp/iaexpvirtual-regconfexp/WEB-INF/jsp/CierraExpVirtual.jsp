<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="es">
	<head>
		
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name = "viewport" content = "initial-scale = 1.0, user-scalable = no, width=device-width">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>CIERRE DEL EXPEDIENTE VIRTUAL</title>
		
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
			.hiddenDiv {
				display: none !important;
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
			body {
				font-size: 12px;
			}
			
		</style>
		
	</head>
	
	<body>
		
		<div class="container">
			
			<div class="row">
				
				<div class="panel panel-primary">
					<div class="panel-heading centered">
						<h2 class="panel-title">
							<strong>CIERRE DEL EXPEDIENTE VIRTUAL</strong>
						</h2>
						<form id="frmAuxiliar" name="frmAuxiliar" class="form-inline" role="form" method="post" enctype="multipart/form-data">
						</form>
						
						<!-- Inicio [jjurado 26/05/2016] se cambia la peticion -->
						<!--<form id="frmPrincipal" name="frmPrincipal" class="form-inline" role="form" method="post" enctype="multipart/form-data">-->
						<form id="frmPrincipal" name="frmPrincipal" class="form-inline" role="form" method="post">
						
						<!-- Fin [jjurado 26/05/2016] -->
							<input id="dataOculta" name="dataOculta" type="hidden" class="form-control form-control-single"/>
							<input id="hdnMotivoCierre" name="hdnMotivoCierre" type="hidden"/>
							<input id="hdnSumilla" name="hdnSumilla" type="hidden"/>
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
									<label>N&deg; Expediente: </label>
								</div>
								<div id="pnlSelTipoNumeroExpediente" class="col-md-2">
									<select id="selTipoNumeroExpediente" name="selTipoNumeroExpediente" class="cboTipoNumeroExpediente form-control form-control-single" onchange="activarNumeroExpediente()">
										<option value="">Seleccione</option>
									</select>
								</div>
								<div class="col-md-2">
									<input id="txtNumeroExpediente" name="txtNumeroExpediente" type="text" class="form-control form-control-single" onkeypress="return permitirNumeros(event);" readonly="readonly"/> 
								</div>
								<div class="col-md-2">
									<input id="btnBuscar" type="button" class="btn btn-primary" onclick="buscar()" value="Buscar" />
								</div>
								<div class="col-md-1">
									&nbsp;
								</div>
								<div class="col-md-1">
									<label>Fecha: </label>
								</div>
								<div class="col-md-2">
									<input id="txtFechaSistema" name="txtFechaSistema" type="text" class="form-control form-control-single" readonly="readonly"/> 
								</div>
							</div>
							
							<div class="row content-box">
								&nbsp;
							</div>
							
							<div class="row content-box">
								
								<div class="col-md-12">
									
									<fieldset class="fsStyle">
										
										<legend class="legendStyle">Datos del Expediente Virtual</legend>
											<div class="row content-box">
												<div class="col-md-2">
													<label>RUC: </label>
												</div>
												<div class="col-md-2">
													<input id="txtNumRuc" name="txtNumRuc" type="text" class="form-control form-control-single" readonly="readonly"/>
												</div>
												<div class="col-md-2">
													&nbsp;
												</div>
												<div class="col-md-2">
													<label>Raz&oacute;n Social: </label>
												</div>
												<div class="col-md-4">
													<input id="txtDesRazonSocial" name="txtDesRazonSocial" type="text" class="form-control form-control-single" readonly="readonly"/> 
												</div>
											</div>
												
											<div class="row content-box">
												&nbsp;
											</div>
												
											<div class="row content-box">
												<div class="col-md-2">
													<label>Proceso: </label>
												</div>
												<div class="col-md-3">
													<input id="txtDesProceso" name="txtDesProceso" type="text" class="form-control form-control-single" readonly="readonly"/>
												</div>
												<div class="col-md-1">
													&nbsp;
												</div>
												<div class="col-md-2">
													<label>Tipo de Expediente: </label>
												</div>
												<div class="col-md-4">
													<input id="txtDesTipoExpediente" name="txtDesTipoExpediente" type="text" class="form-control form-control-single" readonly="readonly"/> 
												</div>
											</div>
											<div class="row content-box">
												&nbsp;
											</div>										
									</fieldset>									
									</div>								
									</div>
									<div class="row content-box">								
									<div class="col-md-12">
										<fieldset class="fsStyle">
										<legend class="legendStyle">Datos del Documento de Cierre</legend>
											<div class="row content-box">
												<div class="col-md-2">
													<label>Tipo de Documento: </label>
												</div>
												<div class="col-md-3">
													<input id="txtTipoDoc" name="txtTipoDoc" type="text" class="form-control form-control-single" readonly="readonly"/>
												</div>
												<div class="col-md-1">
													&nbsp;
												</div>
												<div class="col-md-2">
													<label>N&uacute;mero de Documento: </label>
												</div>
												<div class="col-md-4">
													<input id="txtNroDoc" name="txtNroDoc" type="text" class="form-control form-control-single" readonly="readonly"/> 
												</div>
											</div>
											<div class="row content-box">
												&nbsp;
											</div>
											<div class="row content-box">
												<div class="col-md-2">
													<label>Fecha de Emisi&oacute;n: </label>
												</div>
												<div class="col-md-3">
													<input id="txtFecEmisionDoc" name="txtFecEmisionDoc" type="text" class="form-control form-control-single" readonly="readonly"/>
												</div>
												<div class="col-md-1">
													&nbsp;
												</div>
												<div class="col-md-2">
													<label>Fecha de Notificaci&oacute;n: </label>
												</div>
												<div class="col-md-4">
													<input id="txtFecNotificacionDoc" name="txtFecNotificacionDoc" type="text" class="form-control form-control-single" readonly="readonly"/> 
												</div>
											</div>
											<div class="row content-box">
												&nbsp;
											</div>
										
									</fieldset>
									
								</div>
								
							</div>
							
							<div class="row content-box">
								&nbsp;
							</div>
							
							<div class="row content-box">
								
								<div class="col-md-12">
									
									<fieldset class="fsStyle">
										
										<legend class="legendStyle">Datos del Cierre para Expediente Virtual</legend>
											<!-- Inicio [jjurado 26/05/2016] Adjuntar documentos ahora se realizará por el módulo de adjuntar documentos internos -->
											<!-- 
											<div class="row content-box">
												<div class="col-md-2">
													<label>Tipo de Documento: </label>
												</div>
												<div id="pnlSelTipoDocumento" class="col-md-6">
													<select id="selTipoDocumento" name="selTipoDocumento" class="cboTipoDocumento form-control form-control-single">
														<option value="">Seleccione</option>
													</select>
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
												<div class="col-md-2">
													<label>N&deg; de Documento: </label>
												</div>
												<div class="col-md-5">
													<input id="txtNumDocumento" name="txtNumDocumento" type="text" class="form-control form-control-single" maxlength="50" onkeypress="return permitirNumeros(event);"/>
												</div>
												<div class="col-md-5">
													&nbsp;
												</div>
											</div>
											
											<div class="row content-box">
												<div class="col-md-12">
													&nbsp;
												</div>
											</div>
											
											<div class="row content-box">
												<div class="col-md-2">
													<label>Fecha Documento: </label>
												</div>
												<div class="col-md-2">
													<div>
														<div class='input-group date' id='txtFecDocumentoGrupo'>
															 <input id="txtFecDocumento" name="txtFecDocumento" type='text' class="form-control form-control-single" onkeypress="validarTipoFecha()" maxlength="10"/>
															<input id="txtFecDocumento" name="txtFecDocumento" type='text' class="form-control form-control-single" maxlength="10" readonly="readonly"/>
															<span class="input-group-addon">
																 <span class="glyphicon glyphicon-calendar"></span> 
																<span class="fa fa-calendar"></span>
															</span>
														</div>
													</div>
												</div>
												<div class="col-md-8">
													&nbsp;
												</div>												
											</div>
											
											
											<div class="row content-box">
												<div class="col-md-12">
													&nbsp;
												</div>
											</div>
											-->
											<!-- Fin [jjurado 26/05/2016] -->
											<div class="row content-box">
												<div class="col-md-2">
													<label>Estado de Cierre Exp V: </label>
												</div>
												<div id="pnlSelEstadoCierre" class="col-md-3">
													<select id="selEstadoCierre" name="selEstadoCierre" class="cboEstadoCierre form-control form-control-single">
														<option value="">Seleccione</option>
													</select>
												</div>
												<div class="col-md-5">
													&nbsp;
												</div>
											</div>
											
											<div class="row content-box">
												<div class="col-md-12">
													&nbsp;
												</div>
											</div>
											<div class="row content-box">
												
												<div class="col-md-6">
													
													<div class="row content-box">
														<div class="col-md-4">
															<label>Motivo Cierre: </label>
														</div>
														<div class="col-md-8">
															<textarea name="txtDesMotivoCierre" id="txtDesMotivoCierre" class="form-control form-control-textarea" rows="4" maxlength="100" height="100%"></textarea>
														</div>
													</div>
													
												</div>
												
												<div class="col-md-6">
													
													<div class="row content-box">
														<div class="col-md-4">
															<label>Sumilla: </label>
														</div>
														<div class="col-md-8">
															<textarea name="txtDesSumilla" id="txtDesSumilla" class="form-control form-control-textarea" rows="4" maxlength="100" height="100%"></textarea>
														</div>
													</div>
													
												</div>
												
											</div>
											
											<div class="row content-box">
												<div class="col-md-12">
													&nbsp;
												</div>
											</div>
											<!-- Inicio [jjuradp 26/05/2016] -->
											<!--
											<div class="row content-box">
												
												<div class="col-md-2">
													<label>Archivo Seleccionado: </label>
												</div>
												
												<div class="col-md-4" id="manejadorArchivo">
													<input id="nomDocumentoFisico" name="nomDocumentoFisico" type="hidden"/>
													<input name="docFisico" id="docFisico" style="width: 100%" type="file" onchange='validarArchivoUpload();' class="form-control" maxlength="100">
												</div>
												<div class="col-md-2" id="manejadorArchivo">
													<input name="verDocumento" id="verDocumento"  type="button" class="btn btn-primary" onclick='descargarArchivo();' class="form-control" value="Ver Documento">
													
												</div>
												<div class="col-md-5">
													&nbsp;
												</div>
											</div>
											-->
											<!-- Fin [jjuradp 26/05/2016] -->
										
									</fieldset>
									
								</div>
								
							</div>
							
							<div class="row content-box">
								&nbsp;
							</div>
							<div class="row content-box">
								<div class="col-md-12">
								<!--<b>Nota: Para los Expedientes de Cobranza Coactiva s&oacute;lo podr&aacute; ser cerrado si se encuentra registrado en el SIEV la constancia de notificaci&oacute;n del documento que permite cerrar el Expediente.</b>
								-->
								</div>
							</div>
							<div class="row content-box">
								<div class="col-md-6">
									<p class="legendStyle"><b>Nota: Para los Expedientes de Cobranza Coactiva s&oacute;lo podr&aacute; ser cerrado si se encuentra registrado en el SIEV la constancia de notificaci&oacute;n del documento que permite cerrar el Expediente.</b></p>
									
								</div>
								<div class="col-md-6">
									<div class="marginedDiv">
										<div class="form-group pull-right">
											<input id="btnVerDatos" type="button" class="btn btn-primary" onclick="verDatos()" value="Vista Previa" />
											<input id="btnCerrar" type="button" class="btn btn-primary" onclick="cerrar()" value="Cerrar Expediente Virtual" />
											<input id="btnLimpiar" type="button" class="btn btn-primary" onclick="limpiar()" value="Limpiar" />
										</div>
									</div>
								</div>
							</div>
							
						</div>
						
					</div>
					
				</div>
				
			</div>
		</form>	
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
			var codEstadoExpedientePermitido = ${codEstadoExpedientePermitido};
			var desEstadoExpedientePermitido = ${desEstadoExpedientePermitido};
			var listadoTiposNumeroExpediente = ${listadoTiposNumeroExpediente};
			var numBytesArchivo = ${numBytesArchivo};
			var numMegasArchivo = ${numMegasArchivo};
			// Inicio [jjurado 26/05/2016] Se quitan algunas validaciones
			/*
			var codCampoValidacion = ['#txtNumeroExpediente','#selTipoDocumento', '#txtNumDocumento', '#txtFecDocumento', '#selEstadoCierre', '#txtDesMotivoCierre', '#txtDesSumilla', '#docFisico', '#txtBusquedaClave'];
			var desCampoValidacion = ['Número de Expediente', 'Tipo de Documento', 'Nro. de Documento', 'Fecha Documento', 'Estado de Cierre', 'Motivo Cierre', 'Sumilla', 'Archivo seleccionado', 'Palabras Clave de Búsqueda'];
			*/
			var codCampoValidacion = ['#txtNumeroExpediente','#selEstadoCierre'];
			var desCampoValidacion = ['Número de Expediente', 'Estado de Cierre'];
			// Fin [jjurado 26/05/2016]
			var expedienteVirtual;
			var documentoCierre;
			
			$(document).ready(function() {
				
				$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
				
				setPlaceHolder();
				inicializarCampos();
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
			
			/*$("#txtBusquedaClave").keypress(function(e){
			   var _msgLenght = $(this).val().length;
			   
			   if (_msgLenght > 199) {
					$(this).val($(this).val().substring(0, 199));
			   } else {
				    var txt = String.fromCharCode(e.which);
					
					if (!txt.match(/[A-Za-z0-9\u00F1\u00D1\u00E1\u00E9\u00ED\u00F3\u00FA\u00C1\u00C9\u00CD\u00D3\u00DA;]/)) {
						return false;
					}	
			   }
			});*/
			
			$(window).load(function(){
			alterarDocFisico();
			});
			
			function alterarDocFisico(){
				if(!window.FileReader){
					$('#docFisico').addClass('docFisicoAlt');
				}
			}
			
			function inicializarCampos() {
				
				$('#codArchivosAnchor').on('click', function(e){
					$('#docFisico').click();
					e.preventDefault();
				});
				
				var $element = $('#selTipoNumeroExpediente');
				
				$.each(listadoTiposNumeroExpediente, function(i, dato) {
				
					var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
					$element.append($option);
					
				});
				
				$("#btnCerrar").prop("disabled", true);
				
				$('#txtFechaSistema').attr("value", fecVista);
				
				setearMascara( '#txtNumeroExpediente', "(9){1,14}", null, '' );
				setearMascara( '#txtNumDocumento', "(9){1,50}", 50, '' );
				
				//setearMascara( '#txtBusquedaClave', "(a|9|;){1,200}", 200, '' );
				
				$('#txtFecDocumentoGrupo').datetimepicker({
					format: 'DD/MM/YYYY',
					language: 'es',
					autoclose: true,
					forceParse: false,
					pickTime: false,
					maxDate: ${fecVista}
				});
				
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
			
			function ExaminarClic(){
			
			}
			
			function activarNumeroExpediente() {
				
				expedienteVirtual = null;
				documentoCierre = null;
								
				var tipoNumeroExpediente = $('#selTipoNumeroExpediente').val();
				
				if (tipoNumeroExpediente == "") {
					
					$("#txtNumeroExpediente").attr('readonly', true);
					
				} else {
					
					if (tipoNumeroExpediente == "1") { // origen
						
						$("#txtNumeroExpediente").attr('maxlength', 17);
						setearMascara( '#txtNumeroExpediente', "(9){1,17}", null, '' );
					} else if (tipoNumeroExpediente == "2") { // virtual
						
						$("#txtNumeroExpediente").attr('maxlength', 14);
						setearMascara( '#txtNumeroExpediente', "(9){1,14}", null, '' );
						
					}
					
					$("#txtNumeroExpediente").attr('readonly', false);
					
				}
				
				limpiarCampos();
				setPlaceHolder();
			}
			
			function limpiarCampos(){
				$('#txtNumeroExpediente').val("");
				$("#btnBuscar").prop("disabled", false);
				$("#btnCerrar").prop("disabled", true);
				
				$('#txtNumRuc').val("");
				$('#txtDesRazonSocial').val("");
				$('#txtDesProceso').val("");
				$('#txtDesTipoExpediente').val("");
				$('#txtTipoDoc').val("");
				$('#txtNroDoc').val("");
				$('#txtFecEmisionDoc').val("");
				$('#txtFecNotificacionDoc').val("");
				
				$('#selTipoDocumento').empty();
				
				$('#selTipoDocumento').append($('<option>', {
					value: '',
					text: 'Seleccione'
				}));
				
				$('#selEstadoCierre').empty();
				$('#selEstadoCierre').append($('<option>', {
					value: '',
					text: 'Seleccione'
				}));
				
				$('#txtNumDocumento').val("");
				$('#txtFecDocumento').val("");
				$('#txtDesMotivoCierre').val("");
				$('#txtDesSumilla').val("");
				$('#txtFecDocumento').val('');
				$("#nomDocumentoFisico").val('');
				//$('#txtBusquedaClave').val('');
				
				$("#docFisico").appendTo($('#frmAuxiliar'));
				frmAuxiliar.reset();
				$("#docFisico").appendTo($('#manejadorArchivo'));
			}
			
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
				
				$('#selTipoNumeroExpediente').val('');
				activarNumeroExpediente();
				setPlaceHolder();
			}
			
			function limpiarFormulario() {
				$('#txtNumRuc').val("");
				$('#txtDesRazonSocial').val("");
				$('#txtDesProceso').val("");
				$('#txtDesTipoExpediente').val("");
				$('#txtTipoDoc').val("");
				$('#txtNroDoc').val("");
				$('#txtFecEmisionDoc').val("");
				$('#txtFecNotificacionDoc').val("");
				$('#selTipoDocumento').empty();
				$('#selTipoDocumento').append($('<option>', {
					value: '',
					text: 'Seleccione'
				}));
				$('#selEstadoCierre').empty();
				
				$('#selEstadoCierre').append($('<option>', {
					value: '',
					text: 'Seleccione'
				}));
			}
			
			function buscar() {
				
				var indClaseExpediente = $('#selTipoNumeroExpediente').val();
				var numExpediente = $('#txtNumeroExpediente').val();
				//inicio [gangles 09/08/2016]
				var datosRsirat = null;
				//Fin [gangles 09/08/2016]
				if (indClaseExpediente == '' || numExpediente == '') {
				
					mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion01);
					
				} else {

					if (indClaseExpediente == '1' && numExpediente.length < 1) {
					
						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion02);
						
					} else if (indClaseExpediente == '2' && numExpediente.length < 14) {
						
						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion02);
						
					} else {

						var dataEnvio = new Object();
						
						dataEnvio.indClaseExpediente = indClaseExpediente;
						dataEnvio.numExpediente = numExpediente;
						dataEnvio.codDependenciaBase = codDependenciaBase;//[oachahuic][PAS20165E210400270]
						
						$.ajax({
							
							url : '${pageContext.request.contextPath}/cierExpeVirt.htm?action=cargarCierreExpedienteVirtual&indCarga=1',
							type : 'POST',
							async : true,
							dataType : "json",
							data : JSON.stringify(dataEnvio),
							contentType : "application/json",
							mimeType : "application/json",
							//timeout : 5000,
							success : function(response) {
								//[PAS20191U210500076][OAC] - INICIO
								expedienteVirtual = response.expedienteVirtual;
								if (expedienteVirtual == null) {
									limpiarFormulario();
									var desError = response.desError;
									mostrarDlgInfo(titulos.tituloDefecto, desError);
									return;
								}
								var contribuyente = response.contribuyente;
								$('#txtNumRuc').val(expedienteVirtual.numRuc);
								$('#txtDesRazonSocial').val(contribuyente.desRazonSocial);
								$('#txtDesProceso').val(expedienteVirtual.desProceso);
								$('#txtDesTipoExpediente').val(expedienteVirtual.desTipoExpediente);
								documentoCierre = response.t6613DocExpVirtBean;
								if (documentoCierre != null) {
									$('#txtTipoDoc').val(documentoCierre.desNombreCompuesto);
									$('#txtNroDoc').val(documentoCierre.numDoc);
									$('#txtFecEmisionDoc').val(response.fechaEmisionDoc);										
									$('#txtFecNotificacionDoc').val(response.fechaNotificacionDoc);
								}
								$("#txtNumeroExpediente").attr('readonly', true);
								$("#btnBuscar").prop("disabled", true);
								$("#btnCerrar").prop("disabled", false);
								var $element = $('#selTipoDocumento');
								$.each(response.listadoTiposDocumentos, function(i, dato) {
									var $option = $("<option/>").attr("value", dato.codTipoDocumento).text(dato.desTipoDocumentoCompuesto);
									$element.append($option);														
								});
								var $element = $('#selEstadoCierre');
								$.each(response.listadoEstadoCierreExpediente, function(i, dato) {
									var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
									$element.append($option);
								});
								//[PAS20191U210500076][OAC] - FIN
							},
							error : function(response) {
								mostrarPagError();
							}
						});
					}
				}
			}

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
				formObj.attr('action', '${pageContext.request.contextPath}/cierExpeVirt.htm?action='+metodoInvocar);
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
						
						if (response.tamanoArchivoSuperado) {
							
							$("#nomDocumentoFisico").val("");
							var control = $("#docFisico");
							control.replaceWith( control = control.clone( true ) );
							control.val(null);
							$("#docFisico").val("");
							
							var mensajeExcepcion = excepciones.excepcion08;
							
							var mensajeExcepcion = mensajeExcepcion.replace("{0}", response.tamanoArchivoPermitido);
							
							mostrarDlgInfo(titulos.tituloDefecto, mensajeExcepcion);
							
						} else {
							
							if (!response.extensionPermitida) {
								
								$("#nomDocumentoFisico").val("");
								var control = $("#docFisico");
								control.replaceWith( control = control.clone( true ) );
								control.val(null);
								$("#docFisico").val("");
								
								mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion09);
								
							} else {
								
								$('#nomDocumentoFisico').val($("#docFisico").val());
								
							}
							
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
				
				if (doc) {
					
					return doc;
					
				}
				
				try {
					
					doc = frame.contentDocument ? frame.contentDocument : frame.document;
					
				} catch(err) {
					
					doc = frame.document;
					
				}
				
				return doc;
				
			}
			
			function cerrar() {
				
				if (validarCampos()) {
					
					botones = [];
					
					var aceptar = $("<input/>").attr(
						{
							value: "Si", 
							type: "button", 
							"class": "btn btn-primary dlgButton", 
							"data-dismiss" : "modal", 
							onclick : "ejecutarCierre()"
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
					
					crearDlg(titulos.tituloDefecto, avisos.aviso01, botones);
					
				}
				
			}
			
			function ejecutarCierre() {
				
				prepararEnvioFinal();
				
				//$('html').block({message: '<h1>Procesando</h1>'});
				
				//$.blockUI({ message: '<h1>Procesando</h1>' });
				
				// Inicio [jjurado 26/05/2016] se comentan los campos que ya no se utilizarán
				//$("#docFisico").appendTo($('#frmPrincipal'));
				// Fin [jjurado 26/05/2016] 
				$('#frmPrincipal').submit();
				
				// Inicio [jjurado 26/05/2016] se comentan los campos que ya no se utilizarán
				//$("#docFisico").appendTo($('#manejadorArchivo'));
				// Fin [jjurado 26/05/2016] 
				
				//$('html').unblock();
				
			}
			
			function validarCampos() {
				
				var mensajeExcepcion = excepciones.excepcion10;
				
				for (var i = 0; i < codCampoValidacion.length; i++) {
					
					if ($(codCampoValidacion[i]).val().trim() == '') {
						
						var mensajeExcepcion = mensajeExcepcion.replace("{0}", "<b>"+desCampoValidacion[i]+"</b>");
						
						mostrarDlgInfo(titulos.tituloDefecto, mensajeExcepcion);
						
						return false;											
					} else {
						if (i == 8) {
							/*if($('#txtBusquedaClave').val() == $('#txtBusquedaClave').attr('placeholder')) {
								var mensajeExcepcion = mensajeExcepcion.replace("{0}", "<b>"+desCampoValidacion[i]+"</b>");
								
								mostrarDlgInfo(titulos.tituloDefecto, mensajeExcepcion);
								
								return false;
							}*/
							return true;
						}
					}
				}
				
				return true;
				
			}
			
			function prepararEnvioFinal () {
				var numExpedienteVirtual = expedienteVirtual.numExpedienteVirtual;
				var codTipoExpediente = expedienteVirtual.codTipoExpediente;
				var numRuc = expedienteVirtual.numRuc;
				var codEstadoCierre = $('#selEstadoCierre').val().trim();
				var desMotivoCierre = $('#txtDesMotivoCierre').val().trim();
				$('#hdnMotivoCierre').val(desMotivoCierre);
				var desSumilla = $('#txtDesSumilla').val().trim();
				$('#hdnSumilla').val(desSumilla);
				
				var dataEnvio = new Object();
				dataEnvio.numExpedienteVirtual = numExpedienteVirtual;
				dataEnvio.codTipoExpediente = codTipoExpediente;
				dataEnvio.numRuc = numRuc;
				dataEnvio.codEstadoCierre = codEstadoCierre;
				dataEnvio.desMotivoCierre = desMotivoCierre;
				dataEnvio.desSumilla = desSumilla;
				if (documentoCierre != null) {
					dataEnvio.numCorDocCierre = documentoCierre.numCorDoc;
				}
				
				$("#dataOculta").val(JSON.stringify(dataEnvio));
			}
			
			$("#frmPrincipal").submit(function(e){
				
				var formObj = $(this);
				var iframeId = 'unique' + (new Date().getTime());
				
				var iframe = $('<iframe src="javascript:false;" name="'+iframeId+'" />');
				
				iframe.hide();
				formObj.attr('target', iframeId);
				formObj.attr('action', '${pageContext.request.contextPath}/cierExpeVirt.htm?action=cerrarExpedienteVirtual');
				
				<!-- Inicio [jjurado 26/05/2016] se cambia la peticion -->
				//formObj.attr("enctype", "multipart/form-data");
				//formObj.attr("encoding", "multipart/form-data");
				<!-- Fin [jjurado 26/05/2016]  -->
				
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
							
						//$.unblockUI();
						
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
						
						if (response.numExpedienteVirtual == undefined) {
							
							//$.unblockUI();
							
							mostrarPagError();
							
						} else {
							
							var numExpedienteVirtual = expedienteVirtual.numExpedienteVirtual;
							
							$("#btnCerrar").prop("disabled", true);
							
							var mensajeAviso = avisos.aviso02.replace("{0}", numExpedienteVirtual);
							
							//$.unblockUI();

							mostrarDlgInfo(titulos.tituloDefecto, mensajeAviso, "limpiarPagina");
							
						}
						
					}
					
				});
				
			});
			
			
			function mostrarDlgInfo(titulo, msj, fnAceptar){ 
				
				botones = [];
				
				var aceptar = $("<input/>").attr(
					{
						value: "Aceptar", 
						type: "button", 
						"class": "btn btn-primary dlgButton", 
						"data-dismiss" : "modal", 
						onclick : (typeof fnAceptar == 'undefined') ? "$('#modalDlg').modal('hide')" : fnAceptar
					}
				);
				
				botones.push(aceptar);
				crearDlg(titulo, msj, botones);
				
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
			
			function permitirNumeros(e){
				
				var keynum = window.event ? window.event.keyCode : e.which;
				
				if ((keynum == 8)) {
					
					return true;
					
				}
				
				return /\d/.test(String.fromCharCode(keynum));
				
			}
			
			if (typeof String.prototype.trim !== 'function') {
				
				String.prototype.trim = function() {
					
					return this.replace(/^\s+|\s+$/g, '');
					
				}
				
			}
			// Inicio [jjurado 03/06/2016]
			function descargarArchivo(){
				
				//var tabla = $('#tblDocumentos').DataTable();
				
				
				metodoInvocar = "descargarDocumento";
				var archivo = $('#docFisico');
				$('html').block({message: '<h1>Procesando</h1>'});
				if (archivo != undefined) {
					$('html').block({message: '<h1>Procesando</h1>'});
					$("#docFisico").appendTo($('#frmAuxiliar'));
					$('#frmAuxiliar').submit();
					$("#docFisico").appendTo($('#manejadorArchivo'));
				}
				$('html').unblock();
				metodoInvocar = "validarArchivo";
								
			}
			
			function verDatos(){
				//var input = $('#docFisico');
				//var nombreArchivo =  label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
				var url = "cierExpeVirt.htm?action=verDatosExpedientePDF";
					url+= "&txtDesTipNumExpediente="+$('#selTipoNumeroExpediente option:selected').text();
				    url+= "&desEstadoCierre="+encodeURIComponent($('#selEstadoCierre option:selected').text());
					url+= "&txtDesMotivoCierre="+encodeURIComponent($('#txtDesMotivoCierre').val());
					url+= "&txtDesSumilla="+encodeURIComponent($('#txtDesSumilla').val());
					url+= "&txtTipoDoc="+encodeURIComponent($('#txtTipoDoc').val());
					url+= "&txtNroDoc="+encodeURIComponent($('#txtNroDoc').val());
					url+= "&txtFecEmisionDoc="+encodeURIComponent($('#txtFecEmisionDoc').val());
					url+= "&txtFecNotificacionDoc="+encodeURIComponent($('#txtFecNotificacionDoc').val());
					url+= "&"+$(formDatosExpediente).serialize();
				window.open(url);
			}
			// Fin [jjurado 03/06/2016]
		</script>
		
	</body>
	
</html>