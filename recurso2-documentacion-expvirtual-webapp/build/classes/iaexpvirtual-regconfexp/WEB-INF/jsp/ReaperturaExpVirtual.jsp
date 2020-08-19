<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="es">

	<head>

		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name = "viewport" content = "initial-scale = 1.0, user-scalable = no, width=device-width">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>REAPERTURA DEL EXPEDIENTE VIRTUAL</title>
		
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
								<strong>REAPERTURA DEL EXPEDIENTE VIRTUAL</strong>
							</h2>
							<form id="frmAuxiliar" name="frmAuxiliar" class="form-inline" role="form" method="post" enctype="multipart/form-data">
							</form>
							
							<form id="frmPrincipal" name="frmPrincipal" class="form-inline" role="form" method="post">
								<input id="hdnMotivoCierre" name="hdnMotivoCierre" type="hidden"/>
								<input id="hdnSumilla" name="hdnSumilla" type="hidden"/>
							</form>
							<form id="frmFinal" name="frmFinal" class="form-inline" role="form" method="post" enctype="multipart/form-data">
								<input id="dataOculta" name="dataOculta" type="hidden" class="form-control form-control-single"/>
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
									<div class="row content-box">	
										<div class="col-md-12">
											<fieldset class="fsStyle">
												<legend class="legendStyle">Datos del Expediente Virtual</legend>
													<div class="row content-box">
														<div class="col-md-2">
															<label>RUC: </label>
														</div>
														<div class="col-md-3">
															<input id="txtNumRuc" name="txtNumRuc" type="text" class="form-control form-control-single" readonly="readonly"/>
														</div>
														<div class="col-md-1">
															&nbsp;
														</div>
														<div class="col-md-2">
															<label>Raz&oacute;n Social: </label>
														</div>
														<div class="col-md-3">
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
														<div class="col-md-3">
															<input id="txtDesTipoExpediente" name="txtDesTipoExpediente" type="text" class="form-control form-control-single" readonly="readonly"/> 
														</div>
													</div>
													<div class="row content-box">
														&nbsp;
													</div>	
													<div class="row content-box">
														<div class="col-md-2">
															<label>N&deg; Expediente Origen: </label>
														</div>
														<div class="col-md-3">
															<input id="txtExpedienteOrigen" name="txtExpedienteOrigen" type="text" class="form-control form-control-single" readonly="readonly"/>
														</div>
														<div class="col-md-1">
															&nbsp;
														</div>
														<div class="col-md-2">
															<label>N&deg; Expediente Virtual: </label>
														</div>
														<div class="col-md-3">
															<input id="txtExpedienteVirtual" name="txtExpedienteVirtual" type="text" class="form-control form-control-single" readonly="readonly"/> 
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
														<div class="col-md-3">
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
														<div class="col-md-3">
															<input id="txtFecNotificacionDoc" name="txtFecNotificacionDoc" type="text" class="form-control form-control-single" readonly="readonly"/> 
														</div>
													</div>
													<div class="row content-box">
														<div class="col-md-12">
															&nbsp;
														</div>
													</div>

											</fieldset>
										</div>							
									</div>	
									<div class="row content-box">
										&nbsp;
									</div>
									
									<div class="row content-box" id="divSelectDocGroup">
										<div class="col-md-2">
											<label>codigo tipo de Documento: </label>
										</div>
										<div class="col-md-3">
											<input id="destipodocuemnto" name="destipodocuemnto" type="text" class="form-control form-control-single" readonly="readonly"/> 
											<input id="destipodocuemntoCompuesto" name="destipodocuemntoCompuesto" type="text" class="form-control form-control-single" readonly="readonly"/> 
										</div>
									</div>
									<!-- 
									<div class="row content-box">
										<div class="col-md-12">
											<fieldset class="fsStyle">
												<legend class="legendStyle">Datos de Cierre del Expediente Virtual</legend>
													<div class="row content-box">
														<div class="col-md-2">
															<label>Estado de Cierre : </label>
														</div>
														<div  class="col-md-3">
															<input id="txtEstadoCierre" name="txtEstadoCierre" type="text" class="form-control form-control-single" readonly="readonly"/>
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
															<label>Motivo Cierre: </label>
														</div>
														<div class="col-md-3">
															<textarea name="txtDesMotivoCierre" id="txtDesMotivoCierre" class="form-control form-control-textarea" readonly="readonly" rows="4" maxlength="100" height="100%"></textarea>
														</div>
														
														<div class="col-md-1">
															&nbsp;
														</div>
														
														<div class="col-md-2">
															<label>Sumilla: </label>
														</div>
														<div class="col-md-3">
															<textarea name="txtDesSumilla" id="txtDesSumilla" class="form-control form-control-textarea" readonly="readonly" rows="4" maxlength="100" height="100%"></textarea>
														</div>
													</div>
													<div class="row content-box">
														<div class="col-md-12">
															&nbsp;
														</div>
													</div>
											</fieldset>						
										</div>
									</div>
									<div class="row content-box">
										&nbsp;
									</div>
									 -->
									<!-- Reapertura por Documento -->
									<div class="row content-box">
										<div class="col-md-12">
											<fieldset class="fsStyle">
												<legend class="legendStyle">Datos del Documento de Reapertura</legend>
													<div class="row content-box">
														<div class="col-md-2">
															<label>Tipo de Documento : </label>
														</div>
														<div id="pnlSelTipoDocumento" class="col-md-3">
															<select id="selTipoDocumento" name="selTipoDocumento" class="cboTipoDocumento form-control form-control-single" onchange="habilitarCampos(true)">
																<option value="">Seleccione</option>
															</select> 
														</div>
														<div class="col-md-1">
															&nbsp;
														</div>
														<div class="col-md-2">
																<label>N&uacute;mero de Documento:</label>
														</div>
														<div class="col-md-3">
															<input id="txtNumDocumento" readonly="readonly" name="txtNumDocumento" type="text" class="form-control form-control-single" maxlength="17" onkeypress="return permitirNumeros(event);"  onchange="validaExistenciaDocumento()" readonly="readonly" />
														</div>
													</div>
													<div class="row content-box">
														<div class="col-md-12">
															&nbsp;
														</div>
													</div>
													<div class="row content-box">
														<div class="col-md-2">
															<label>Fecha de Emisi&oacute;n:</label>
														</div>
														<div class="col-md-3">
															<div class='input-group date col-md-12' id='fechaDocumento'>
																<input id="fechaDoc" name="fechaDoc" type='text' class="form-control form-control-single" maxlength="10" readonly="readonly"/>
																<span class="input-group-addon">
																	<!-- <span class="glyphicon glyphicon-calendar"></span> -->
																	<span class="fa fa-calendar"></span>
																</span>
															</div>
															<input id="fechaDocCompleta" name="fechaDocCompleta" type="hidden" value=""/>
														</div>
														<div class="col-md-1">
															&nbsp;
														</div>
														<div class="col-md-2" id="divLabelFecNot">
															<label>Fecha de Notificaci&oacute;n:</label>
														</div>
														<div class="col-md-3" id="divInputFecNot">
															<div class='input-group date col-md-12' id='fechaNotDocumento'>
																<input id="fechaNot" name="fechaNot" type='text' class="form-control form-control-single" maxlength="10"  readonly="readonly"/>
																<span class="input-group-addon">
																	<!-- <span class="glyphicon glyphicon-calendar"></span> -->
																	<span class="fa fa-calendar"></span>
																</span>
															</div>
														</div>			
													</div>
													<div class="row content-box">
														<div class="col-md-12">
															&nbsp;
														</div>
													</div>
													<div class="row content-box">
														<div class="col-md-2">
															<label>Palabras Clave de B&uacute;squeda:</label>
														</div>
														<div id="txtPalabrasBusqueda" class="col-md-4">
															<textarea id="busqueda" name="busqueda" placeholder='Ingresar las palabras claves de b&uacute;squeda separados por ";". Ejemplo: etiqueta1; etiqueta2' class="form-control form-control-textarea" rows="4"  maxlength="200" "></textarea>

														</div>
													</div>
													<div class="row content-box">
														<div class="col-md-12">
															&nbsp;
														</div>
													</div>
													<div class="row content-box">
														<div class="col-md-2">
															<label>Archivo Seleccionado: </label>
														</div>
														<div class="col-md-7" id="manejadorArchivo">
															<input id="nomDocumentoFisico" name="nomDocumentoFisico" type="hidden"/>
															<input name="docFisico" id="docFisico" type="file" onchange='validarArchivoUpload();' class="form-control">
														</div>
														<div class="col-md-2" align="right">
															<input name="verDocumento" id="verDocumento"  type="button" class="btn btn-primary" onclick='descargarArchivo();' class="form-control" value="Ver Documento">
														</div>
													</div>
													<div class="row content-box">
														<div class="col-md-12">
															&nbsp;
														</div>
													</div>
											</fieldset>						
										</div>
									</div>
									<div class="row content-box">
										&nbsp;
									</div>

									<div class="row content-box">
										<div class="col-md-12">
										</div>
									</div>
									<div class="row content-box">
										<div class="col-md-12">
											<div class="marginedDiv">
												<div class="form-group pull-right">
													<input id="btnReabrir" type="button" class="btn btn-primary" onclick="Reabrir()" value="Reaperturar Expediente Virtual" />
													<input id="btnLimpiar" type="button" class="btn btn-primary" onclick="limpiar()" value="Limpiar" />
												</div>
											</div>
										</div>
									</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
		</div>
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
		
		<form id="frmEnvioArchivo" name="frmEnvioArchivo" class="form-inline" role="form" method="post" enctype="multipart/form-data">
			<input id="nombreArchivoTemp" type="hidden" name="nombreArchivoTemp"/>		
		</form>
	
		
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
		var listadoTiposNumeroExpediente = ${listadoTiposNumeroExpediente};
		var codTipoExpedienteEspecial = ${codTipoExpedienteEspecial};
		var indNumRC = true;
		var gIsPastedValid = true;
		var expedienteVirtual;
		
		$(document).ready(function() {
			$('#divSelectDocGroup').addClass('hiddenDiv');
			$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
			setPlaceHolder();
			inicializarCampos();
		});
		
		$("#busqueda").keypress(function(e){
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
		
		function setPlaceHolder() {
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
		
		function inicializarCampos() {
			var $element = $('#selTipoNumeroExpediente');
			$.each(listadoTiposNumeroExpediente, function(i, dato) {
				var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
				$element.append($option);
			});
			$("#btnReabrir").prop("disabled", true);
			$('#txtFechaSistema').attr("value", fecVista);
			setearMascara( '#txtNumeroExpediente', "(9){1,14}", null, '' );
			
			$('#fechaDoc').datetimepicker({
				format: 'DD/MM/YYYY',
				locale: 'es',
				autoclose: true,
				forceParse: false,
				pickTime: false
			});
		}
		
		function limpiarCampos(){
			$('#txtNumeroExpediente').val("");
			$("#docFisico").appendTo($('#frmAuxiliar'));
			frmAuxiliar.reset();
			$("#docFisico").appendTo($('#manejadorArchivo'));

			$("#btnBuscar").prop("disabled", false);
			$("#btnReabrir").prop("disabled", true);
			$('#txtNumRuc').val("");
			$('#txtDesRazonSocial').val("");
			$('#txtDesProceso').val("");
			$('#txtDesTipoExpediente').val("");
			$('#txtExpedienteOrigen').val("");
			$('#txtExpedienteVirtual').val("");
			$('#txtTipoDoc').val("");
			$('#txtNroDoc').val("");
			$('#txtFecEmisionDoc').val("");
			$('#txtFecNotificacionDoc').val("");
			$('#txtEstadoCierre').val("");
			$('#txtDesMotivoCierre').val("");
			$('#txtDesSumilla').val("");
			$('#selTipoDocumento')
		    .find('option')
		    .remove()
		    .end()
		    .append('<option value="">Seleccione</option>')
		    .val('');
			$('#selTipoDocumento').attr('disabled', false);
			$('#txtNumDocumento').val("");
			$('#fechaDoc').val("");
			$('#fechaNot').val("");
			$('#docFisico').val("");
			$('#busqueda').val("");
			$('#txtcodtipDocumento').val("");
		}
		
		function activarNumeroExpediente() {
			
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
			setPlaceHolder();
			limpiarCampos();
		}
		
		function habilitarCampoFechaEmi() {
			if(expedienteVirtual.codTipoExpediente=="340"){
				validarNumDocumento();
			}else{
				var numeroDocumento = $('#txtNumDocumento').val();				
				if (numeroDocumento == "" ){
					$("#fechaDoc").attr('readonly', true);
				}else{

					$("#fechaDoc").attr('readonly', false);	

				}
			}
			setPlaceHolder();
		}
		/*------------------------------HABLITA CAMPOS DE LA SECCION DE DOCUMENTO DE REAPERTURA SEGUN TIPO DE EXPEDIENTE----------------------------*/
		function habilitarCampos(Habilitar){
			if(Habilitar){
				var tipoDocumento = $('#selTipoDocumento').val();
				var numeroDocumento = $('#txtNumDocumento').val();
				
				if (tipoDocumento == ""){
					$("#txtNumDocumento").attr('readonly', true);
					$("#txtNumDocumento").val("");
					$("#fechaDoc").attr('readonly', true);	
					$("#fechaDoc").val("");
				}else{
					$("#txtNumDocumento").attr('readonly', false);
					$("#fechaDoc").attr('readonly', true);
				}
			}else{
				$("#txtNumDocumento").attr('readonly', true);
				$("#fechaDoc").attr('readonly', true);
			}
			setPlaceHolder();
		}
		/*------------------------------LIMPIA TODO EL FORMULARIO----------------------------*/
		function limpiarPagina() {
			$('#selTipoNumeroExpediente').val('');
			activarNumeroExpediente();
			setPlaceHolder();
		}
		
		/*------------------------------CREA LA VENTANA LIMPIAR----------------------------*/
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
		
		/*------------------------------CREA LA VENTANA DE DIALOGO----------------------------*/
		function crearDlg(titulo, msj, btns){
			$('#dlgTitle').html(titulo);
			$('#dlgMsj').html(msj);
			$('#dlgBtns').empty();
			jQuery.each(btns, function(i, dato) {
				$('#dlgBtns').append(dato);
			});
			$('#modalDlg').modal('show');	
		}
		
		/*------------------------------MUESTRA LA VENTANA DE DIALOGO----------------------------*/
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
		

		/*------------------------------DESCARGA ARCHIVO ----------------------------*/
		function descargarArchivo(){
				metodoInvocar = "descargarDocumento";
				var archivo = $('#docFisico');
				$('#btnRegistrar').prop('disabled', true);	
				if($('#docFisico').val()==""){
					var mensajeExcepcion = excepciones.excepcion16;						
					mostrarDlgInfo(titulos.tituloDefecto, mensajeExcepcion);
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
		
		/*------------------------------VALIDA CAMPOS DE LA SECCION DOCUMENTOS DE REAPERTURA----------------------------*/
		function validarCampos(){
			mensajeExcepcion = excepciones.excepcion17;
			var mensaje= null;

			if($('#selTipoDocumento').val()==""){
				mensaje= mensajeExcepcion.replace("{0}", "Tipo de Documento");
                mostrarDlgInfo(titulos.tituloDefecto, mensaje);
			}else if($('#txtNumDocumento').val()==""){
					mensaje= mensajeExcepcion.replace("{0}", "N&uacute;mero de Documento");
	                mostrarDlgInfo(titulos.tituloDefecto, mensaje);
				}else if($('#fechaDoc').val()==""){
						mensaje= mensajeExcepcion.replace("{0}", "Fecha de Emisión");
		                mostrarDlgInfo(titulos.tituloDefecto, mensaje);
					}else if($('#docFisico').val()==""){
							mensaje= mensajeExcepcion.replace("{0}", "Archivo Seleccionado");
			                mostrarDlgInfo(titulos.tituloDefecto, mensaje);
					}else{
						return true;
					}
			return false;
		}
		/*------------------------------BUSCA LOS DATOS DE ACUERDO AL EXPEDIENTE ----------------------------*/
		function buscar(){
			
			var indClaseExpediente = $('#selTipoNumeroExpediente').val(); 
			//indClaseExpediente =  "1" Expediente Origen
			//indClaseExpediente =  "2" Expediente Virtual
			var numExpediente = $('#txtNumeroExpediente').val(); 
			var datosRsirat = null;
			var t6613DocExpVirtBean= null;
			var t6614ExpVirtualBean= null;

			if (indClaseExpediente == '1' && numExpediente == ''){
				mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion09);
				}else{
					if(indClaseExpediente == '2' && numExpediente == ''){
						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion09);
						}else{
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
									dataEnvio.codDependenciaBase = codDependenciaBase;
									
									$.ajax({	
										url : '${pageContext.request.contextPath}/reapExpeVirt.htm?action=cargarReaperturaExpedienteVirtual&indCarga=1',
										type : 'POST',
										async : true,
										dataType : "json",
										data : JSON.stringify(dataEnvio),
										contentType : "application/json",
										mimeType : "application/json",
										success : function(response) {
											contribuyente = response.contribuyente;
											expedienteVirtual = response.expedienteVirtual;
				                            if(response.status === false){
											mostrarDlgInfo(titulos.tituloDefecto, response.message);
											return false; 
											}							
										    t6613DocExpVirtBean= response.t6613DocExpVirtBean;
											$('#txtNumRuc').val(expedienteVirtual.numRuc);
											$('#txtDesRazonSocial').val(contribuyente.desRazonSocial);
											$('#txtDesProceso').val(expedienteVirtual.desProceso);
											$('#txtDesTipoExpediente').val(expedienteVirtual.desTipoExpediente);
											$('#txtExpedienteOrigen').val(expedienteVirtual.numExpedienteOrigen);
											$('#txtExpedienteVirtual').val(expedienteVirtual.numExpedienteVirtual); 
											$('#txtTipoDoc').val(t6613DocExpVirtBean.desNombreCompuesto);
											$('#txtNroDoc').val(t6613DocExpVirtBean.numDoc);
											$('#txtFecEmisionDoc').val(response.fechaEmisionDoc);
											$('#txtFecNotificacionDoc').val(response.fechaNotificacionDoc);
											$('#txtEstadoCierre').val(expedienteVirtual.desEstadoCierre);
											$('#txtDesMotivoCierre').val(expedienteVirtual.desMotivoCierre);
											$('#txtDesSumilla').val(expedienteVirtual.desSumilla);
											$('#txtcodtipDocumento').val(t6613DocExpVirtBean.codTipDoc);
											$("#txtNumeroExpediente").attr('readonly', true);
											$("#btnBuscar").prop("disabled", true);
											$("#btnReabrir").prop("disabled", false);
											
											//Cargar Listado de Documentos 
											listadoTiposDocumentos = response.listadoTiposDocumentos;
											$('#selTipoDocumento')
										    .find('option')
										    .remove()
										    .end()
										    .append('<option value="">Seleccione</option>')
										    .val('');
											$("#selTipoDocumento").attr('disabled', false);
											var $element = $('#selTipoDocumento');
											$.each(listadoTiposDocumentos, function(i, dato) {
												var $option = $("<option/>").attr("value", dato.codTipoDocumento).text(dato.desTipoDocumentoCompuesto);
												$element.append($option);
											});
											
										},
										error : function(response) {
										mostrarPagError();
										}
									});
							}
						}
					}
				}
			}
		
		/*------------------------------EVENTOS DE BOTON REAPERTURA----------------------------*/
		function Reabrir() {
			if(validarCampos()){
				botones = [];
				var aceptar = $("<input/>").attr(
					{
						value: "Si", 
						type: "button", 
						"class": "btn btn-primary dlgButton", 
						"data-dismiss" : "modal", 
						onclick : "ejecutarReapertura()"
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
		/*------------------Prepara el JSON  ------------------------------*/
		function prepararEnvioFinal(){
            var dataEnvio = new Object();                                                                                
            dataEnvio.numExpedienteOrigen = $.trim($("#txtExpedienteOrigen").val());
            dataEnvio.numExpedienteVirtual = $.trim($("#txtExpedienteVirtual").val());
            dataEnvio.numRuc = $.trim($("#txtNumRuc").val());
            dataEnvio.numDoc = $.trim($("#txtNumDocumento").val());
            dataEnvio.codTipexp= expedienteVirtual.codTipoExpediente;
            dataEnvio.codTipdoc = $.trim($("#selTipoDocumento").val());
            dataEnvio.fechaDoc = $.trim($("#fechaDoc").val());
            dataEnvio.fechaNot = $.trim($("#fechaNot").val());
            dataEnvio.nomDocumentoFisico = $.trim($("#nomDocumentoFisico").val());
            dataEnvio.fechaDocCompleta = $.trim($("#fechaDocCompleta").val());
            dataEnvio.numDocuemento = $.trim($("#txtNumDocumento").val());
            var campobusqueda = $.trim($("#busqueda").val());
            if(campobusqueda==""){
            	
            	var str=$.trim($("#selTipoDocumento option:selected").text());
                var strText=str.replace(/ /g, ';');
            	
            	dataEnvio.busqueda = strText.substr((strText.indexOf("-")+2) - strText.length);
            }else{
            	dataEnvio.busqueda = campobusqueda;
            }
            
            
			$("#dataOculta").val(JSON.stringify(dataEnvio));
		}
		
		/************************REAPERTURA********************/
		function ejecutarReapertura(){
			
			prepararEnvioFinal();
			$("#docFisico").appendTo($('#frmFinal'));
			$('#frmFinal').submit();
			$("#docFisico").appendTo($('#manejadorArchivo'));
            
		}
		
		$("#frmFinal").submit(function(e){
			
			var formObj = $(this);
			var iframeId = 'unique' + (new Date().getTime());
			
			var iframe = $('<iframe height="200" width="100" src="javascript:false;" name="'+iframeId+'" />');
			
			iframe.hide();
			formObj.attr('target', iframeId);
			formObj.attr('action', '${pageContext.request.contextPath}/reapExpeVirt.htm?action=reabrirExpedienteVirtual');
			formObj.attr("enctype", "multipart/form-data");
			formObj.attr("encoding", "multipart/form-data");
			iframe.appendTo('body');
			
			iframe.load(function(e){
				
				var doc = getDoc(iframe[0]);
				var docRoot = doc.body ? doc.body : doc.documentElement;
				var data = docRoot.innerHTML;
				var jsonDataString;
				var response;
				var indError;
				indError = false;
				
				if(indError) {

					
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
					var numExpedienteVirtual = expedienteVirtual.numExpedienteVirtual;
	                $("#btnReabrir").prop("disabled", true);
		
					var mensaje= response.message.replace("{0}", numExpedienteVirtual);
	                mostrarDlgInfo(titulos.tituloDefecto, mensaje);

				}
		    });
			iframe.hide();
		});
			
		/*******************CERIFICAR QUE EL DOCUMENTO NO EXISTA**************************/
		function validaExistenciaDocumento(){
	        var dataEnvio = new Object();
	        					
			dataEnvio.numExpeVirtual = $.trim($("#txtExpedienteVirtual").val());
            dataEnvio.codTipoDocumento = $.trim($("#selTipoDocumento").val());
            dataEnvio.numDocuemento = $.trim($("#txtNumDocumento").val());

	        $.ajax({							
				url :  '${pageContext.request.contextPath}/reapExpeVirt.htm?action=validarDocuemntoExistente',
				type : 'POST',
				async : true,
				dataType : "json",
				data : JSON.stringify(dataEnvio),
				contentType : "application/json",
				mimeType : "application/json",
				//timeout : 5000,
				success : function(response) { 
                    if(response.status === false){
						mostrarDlgInfo(titulos.tituloDefecto, response.message);
						$('#txtNumDocumento').val("");
						return false; 
					}	
                    habilitarCampoFechaEmi();
	            },	            
				error : function(response) {
					mostrarPagError();
				}				
			});
        }
		/*------------------------------MUESTRA PAGINA DE ERROR----------------------------*/
		function mostrarPagError() {	
			var formURL = '${pageContext.request.contextPath}/error.htm?action=cargarPaginaError';
			document.forms.frmFinal.action = formURL;
			document.forms.frmFinal.method = "post";
			document.forms.frmFinal.submit();
		}
		
		/*-----------------------------EL CAMPO SOLO PERMITE NUMEROS----------------------------*/
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
		
		/*------------------------------DATOS DE ARCHIVO TEMPORAL----------------------------*/
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
		
		/*------------------------VALIDA EL NUMERO DE DOCUMENTO CON LA RSIRAT----------------------------*/
		function validarNumDocumento(){
			indNumRC = true;
			indNumRC = true;
			var numExpedienteOrigen = $.trim($("#txtExpedienteOrigen").val());
			var numDocumento = $.trim($("#txtNumDocumento").val());
			var numRuc =  $.trim($("#txtNumRuc").val());
			var codTipoDocumento = $.trim($("#selTipoDocumento").val());
			var codTipoDoc3Dig=codTipoDocumento.substring(0, 3);
			var dataEnvio = new Object();
			var mensajeExcepcion = null;

			if(codTipoDoc3Dig!='-' && gIsPastedValid){
				if(numDocumento=="") return;	
				
				dataEnvio.numDocumento = numDocumento;
				dataEnvio.numExpedienteOrigen = numExpedienteOrigen;
				dataEnvio.codTipoDoc3Dig = codTipoDoc3Dig;
				dataEnvio.numRuc = numRuc;
				dataEnvio.codTipoExpediente = expedienteVirtual.codTipoExpediente;
				dataEnvio.codDependencia = expedienteVirtual.codDependencia;
				dataEnvio.codIndicadorAcumulado = expedienteVirtual.indicadorAcumulado;

					
					$.ajax({
						url : '${pageContext.request.contextPath}/reapExpeVirt.htm?action=validarNumDocumento&indCarga=0',
						type : 'POST',
						async : true,
						dataType : "json",
						data : JSON.stringify(dataEnvio),
						contentType : "application/json",
						mimeType : "application/json",
						//timeout : 5000,
						success : function(response) {
							var obtenerNumDocu = response.obtenerNumDocu;
							var flagConsultaSirat = response.flagConsultaSirat;
							var flagAcumuladoCierre =response.flagAcumuladoCierre;						
							var numExpedienteAcumulador=expedienteVirtual.numExpedienteAcumulador;
							listadoTiposDocumentos = response.listadoTiposDocumentos;				
							if (obtenerNumDocu == null && flagConsultaSirat == 'N') {
								indNumRC = false;
								mensajeExcepcion = excepciones.excepcion10;
								var mensaje= mensajeExcepcion.replace("{0}", numDocumento);
				                mostrarDlgInfo(titulos.tituloDefecto, mensaje);
				                $('#selTipoDocumento').val("");
								$('#txtNumDocumento').val("");
								$('#txtNumDocumento').attr('disabled', false);
								$('#fechaDoc').val("");
								$('#fechaNot').val("");
								$('#fechaDocCompleta').val("");
								$('#fechaDoc').attr('disabled', true);
								$('#fechaNot').val("");
								habilitarCampos(false);
							}else if(obtenerNumDocu == null && flagConsultaSirat == 'DNE'){
								indNumRC = false;
								mensajeExcepcion = excepciones.excepcion11;
								var mensaje= mensajeExcepcion.replace("{0}", numDocumento);
				                mostrarDlgInfo(titulos.tituloDefecto, mensaje);
				                $('#selTipoDocumento').val("");
				                $('#txtNumDocumento').val("");
				                $('#txtNumDocumento').attr('disabled', false);
								$('#fechaDoc').val("");
								$('#fechaNot').val("");
								$('#fechaDoc').attr('disabled', true);
								$('#fechaDocCompleta').val("");
								$('#fechaNot').val("");
								habilitarCampos(false);
							}else if(obtenerNumDocu.fechaNotificacion == "-"){
								indNumRC = false;
								mensajeExcepcion = excepciones.excepcion12;
								var mensaje= mensajeExcepcion.replace("{0}", numDocumento);
				                mostrarDlgInfo(titulos.tituloDefecto, mensaje);
				                $('#selTipoDocumento').val("");
				                $('#txtNumDocumento').val("");
				                $('#txtNumDocumento').attr('disabled', false);
								$('#fechaDoc').val("");
								$('#fechaNot').val("");
								$('#fechaDocCompleta').val("");
								$('#fechaDoc').attr('disabled', false);
								$('#fechaNot').val("");
								habilitarCampos(false);
							}else if(obtenerNumDocu.cod_tip_doc != codTipoDocumento){
								indNumRC = false;
								var str=$.trim($("#selTipoDocumento option:selected").text());
								mensajeExcepcion = excepciones.excepcion18;
								var mensaje= mensajeExcepcion.replace("{0}", numDocumento).replace("{1}", str);
				                mostrarDlgInfo(titulos.tituloDefecto, mensaje);
				                $('#selTipoDocumento').val("");
				                $('#txtNumDocumento').val("");
								$('#txtNumDocumento').attr('disabled', false);
								$('#fechaDoc').val("");
								$('#fechaNot').val("");
								$('#fechaDocCompleta').val("");
								$('#fechaDoc').attr('disabled', true);
								$('#fechaNot').val("");
								habilitarCampos(false);
							}else {
								$("#selTipoDocumento").attr('disabled', true);
								$("#selTipoDocumento").val(obtenerNumDocu.cod_tip_doc).change();
								$('#txtNumDocumento').attr('disabled', false);
								$('#fechaNot').val(obtenerNumDocu.fechaNotificacion);
								$('#fechaDoc').val(obtenerNumDocu.fechaEmision);
								$('#fechaDocCompleta').val(obtenerNumDocu.fechaEmisionCompleta);
								$('#fechaDoc').attr('disabled', true);
								habilitarCampos(false);
							}
						},
						error : function(response) {
							mostrarPagError();
						}
					});
				}

		}
		
		/*------------------------------VALIDA EL ARCHIVO SUBIDO----------------------------*/
		function validarArchivoUpload() {
			var input = $('#docFisico');	
				var nombreArchivo =  label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
				$("#docFisico").appendTo($('#frmAuxiliar'));
				$('#frmAuxiliar').submit();
				$("#docFisico").appendTo($('#manejadorArchivo'));
		}
		
		$("#frmAuxiliar").submit(function(e){
			
			var formObj = $(this);
			var iframeId = 'unique' + (new Date().getTime());
			
			var iframe = $('<iframe src="javascript:false;" name="'+iframeId+'" />');
			iframe.hide();
			formObj.attr('target', iframeId);
			formObj.attr('action', '${pageContext.request.contextPath}/reapExpeVirt.htm?action='+metodoInvocar);
			formObj.attr("enctype", "multipart/form-data");
			formObj.attr("encoding", "multipart/form-data");
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
					$("#docFisico").appendTo($('#frmAuxiliar'));
					frmAuxiliar.reset();
					$("#docFisico").appendTo($('#manejadorArchivo'));
					$("#docFisico").val("");
					$("#nomDocumentoFisico").val("");
					var mensajeExcepcion = excepciones.excepcion13;						
					mostrarDlgInfo(titulos.tituloDefecto, mensajeExcepcion);
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
						var mensajeExcepcion = excepciones.excepcion13;
						
						var mensajeExcepcion = mensajeExcepcion.replace("{0}", response.tamanoArchivoPermitido);
						
						mostrarDlgInfo(titulos.tituloDefecto, mensajeExcepcion);
						
					} else if(!response.extensionPermitida){
							$("#nomDocumentoFisico").val("");
							var control = $("#docFisico");
							control.replaceWith( control = control.clone( true ) );
							control.val(null);
							$("#docFisico").val("");
							var mensajeExcepcion = excepciones.excepcion15;						
							mostrarDlgInfo(titulos.tituloDefecto, mensajeExcepcion);
						} else if(!response.longitudnombrearchivo){
								$("#nomDocumentoFisico").val("");
								var control = $("#docFisico");
								control.replaceWith( control = control.clone( true ) );
								control.val(null);
								$("#docFisico").val("");
								var mensajeExcepcion = excepciones.excepcion14;						
								mostrarDlgInfo(titulos.tituloDefecto, mensajeExcepcion);
							} else {								
								
								$('#nomDocumentoFisico').val($('#docFisico').val().replace(/\\/g, '/').replace(/.*\//, ''));
								
							}
							
				    	}
				
		    });
			
		});

		</script>
	</body>
</html>