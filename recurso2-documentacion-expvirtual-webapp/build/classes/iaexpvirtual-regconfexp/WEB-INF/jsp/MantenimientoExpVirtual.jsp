<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>

		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name = "viewport" content = "initial-scale = 1.0, user-scalable = no, width=device-width">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>MANTENIMIENTO DEL EXPEDIENTE VIRTUAL</title>
				
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
			.dataTables_wrapper .dataTables_info {
				padding-top : 3px !important;
			}
			.dataTables_wrapper .dataTables_paginate {
				float: initial !important;
				text-align: center !important;
				padding-top: 0.25em;
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

			/*
			 *
			 *   INS - [ATORRESCH 20170309]
			 *   version 2.4
			 *
			*/
			h1,
			h2,
			h3,
			h4,
			h5,
			h6 {
			  font-weight: 100;
			}
			h1 {
			  font-size: 30px;
			}
			h2 {
			  font-size: 24px;
			}
			h3 {
			  font-size: 16px;
			}
			h4 {
			  font-size: 14px;
			}
			h5 {
			  font-size: 12px;
			}
			h6 {
			  font-size: 10px;
			}
			h3,
			h4,
			h5 {
			  margin-top: 5px;
			  font-weight: 600;
			}
			legend {
				margin-bottom: 2px; /* [ATORRESCH 20170309]*/
			}
			/* <--- MODAL */
			.modal-content {
			  background-clip: padding-box;
			  background-color: #FFFFFF;
			  border: 1px solid rgba(0, 0, 0, 0);
			  border-radius: 4px;
			  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.3);
			  outline: 0 none;
			  position: relative;
			}
			.modal-dialog {
			  z-index: 2200;
			}
			.modal-body {
			  padding: 20px 30px 30px 30px;
			}
			.inmodal .modal-body {
			  background: #f8fafb;
			}
			.inmodal .modal-header {
			  padding: 5px 10px;
			  text-align: center;
			}
			.animated.modal.fade .modal-dialog {
			  -webkit-transform: none;
			  -ms-transform: none;
			  -o-transform: none;
			  transform: none;
			}
			.inmodal .modal-title {
			  font-size: 15px;
			  text-align: center;
			}
			.inmodal .modal-icon {
			  font-size: 44px;
			  color: #e2e3e3;
			}
			.modal-footer {
			  margin-top: 0;
			  text-align:center;
			}

			/* ---> MODAL */			
		</style>

	</head>
	<body>
		<div class="container">
			<div class="row">
				<div class="panel panel-primary" style="margin-bottom: 0px;">
					<div class="panel-heading centered">
						<h2 class="panel-title">
							<strong>MANTENIMIENTO DEL EXPEDIENTE VIRTUAL</strong>
						</h2>
						<form id="frmPrincipal" class="form-horizontal" role="form">
						</form>	

					</div>
				</div>
				
				<div class="panel panel-primary">
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
													<div class="col-md-2">
														<input id="txtNumRuc" name="txtNumRuc" type="text" class="form-control form-control-single" readonly="readonly"/>
													</div>
													<div class="col-md-2">
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
														<label>Dependencia: </label>
													</div>
													<div class="col-md-3">
														<input id="txtDesDependencia" name="txtDesDependencia" type="text" class="form-control form-control-single" readonly="readonly"/>
													</div>
													<div class="col-md-1">
														&nbsp;
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
												<div class="row content-box">
													<div class="col-md-2">
														<label>Responsable: </label>
													</div>
													<div class="col-md-3">
														<input id="txtResponsable" name="txtResponsable" type="text" class="form-control form-control-single" readonly="readonly"/>
													</div>
													<div class="col-md-1">
														&nbsp;
													</div>
													<div class="col-md-2">
														<label>Fecha de Registro: </label>
													</div>
													<div class="col-md-3">
														<input id="txtFechaRegistro" name="txtFechaRegistro" type="text" class="form-control form-control-single" readonly="readonly"/> 
													</div>
												</div>									
										</fieldset>						
									</div>	
								</div>	
								<!-- Invisible Inicio -->
								<div class="row content-box" id="divSelectDocGroup">
									<div class="col-md-2">
										<label>Indicador: </label>
									</div>
									<div class="col-md-3">
										<input id="txtindicador" name="txtindicador" type="text" class="form-control form-control-single" readonly="readonly"/> 
										<input id="txtcodtipodocumento" name="txtcodtipodocumento" type="text" class="form-control form-control-single" readonly="readonly"/> 
									</div>
								</div>
								<!-- Invisible Inicio -->
								<!-- Div Datos Del Documento del Origen Para Eliminar -->	
								<div class="row content-box">								
									<div class="col-md-12" name="DivDocOrigen">
										<fieldset class="fsStyle">
											<legend class="legendStyle">Datos del Documento Origen</legend>
												<div class="row content-box">
													<div class="col-md-2">
														<label>Tipo de Documento: </label>
													</div>
													<div class="col-md-3">
														<input id="txtTipoDocOrigen" name=""txtTipoDocOrigen"" type="text" class="form-control form-control-single" readonly="readonly"/>
													</div>
													<div class="col-md-1">
														&nbsp;
													</div>
													<div class="col-md-2">
														<label>N&uacute;mero de Documento: </label>
													</div>
													<div class="col-md-3">
														<input id="txtNroDocOrigen" name="txtNroDocOrigen" type="text" class="form-control form-control-single" readonly="readonly"/> 
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
														<input id="txtFecEmisionDocOrigen" name="txtFecEmisionDocOrigen" type="text" class="form-control form-control-single" readonly="readonly"/>
													</div>
													<div class="col-md-1">
														&nbsp;
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
								<!-- Div Datos Del Documento del cierre para Reabrir -->
								<div class="row content-box">								
										<div class="col-md-12" name="DivDocCierre">
											<fieldset class="fsStyle">
												<legend class="legendStyle">Datos del Documento de Cierre</legend>
													<div class="row content-box">
														<div class="col-md-2">
															<label>Tipo de Documento: </label>
														</div>
														<div class="col-md-3">
															<input id="txtTipoDocCierre" name="txtTipoDocCierre" type="text" class="form-control form-control-single" readonly="readonly"/>
														</div>
														<div class="col-md-1">
															&nbsp;
														</div>
														<div class="col-md-2">
															<label>N&uacute;mero de Documento: </label>
														</div>
														<div class="col-md-3">
															<input id="txtNroDocCierre" name="txtNroDocCierre" type="text" class="form-control form-control-single" readonly="readonly"/> 
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
															<input id="txtFecEmisionDocCierre" name="txtFecEmisionDocCierre" type="text" class="form-control form-control-single" readonly="readonly"/>
														</div>
														<div class="col-md-1">
															&nbsp;
														</div>
														<div class="col-md-2">
															<label>Fecha de Notificaci&oacute;n: </label>
														</div>
														<div class="col-md-3">
															<input id="txtFecNotificacionDocCierre" name="txtFecNotificacionDocCierre" type="text" class="form-control form-control-single" readonly="readonly"/> 
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
							</div>
							<div class="row content-box">
								<div class="col-md-12">
									<div class="marginedDiv">
										<div class="form-group pull-right">
										    <input id="btnReapertura" type="button" class="btn btn-primary" onclick="validarExpedientesAcumulados()" value="Revertir Cierre Expediente Virtual" readonly="readonly"/>
											<input id="btnEliminar" type="button" class="btn btn-primary" onclick="validarDocumentos()" value="Eliminar Expediente Virtual" readonly="readonly"/>
											<input id="btnLimpiar" type="button" class="btn btn-primary" onclick="limpiar()" value="Limpiar" />
										</div>
									</div>
								</div>
							</div>
							<div class="row content-box">
								&nbsp;
							</div>
						</div>
					</div>		
				</div>			
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
		<!-- Dialogo Observaciones -->
		<div class="modal inmodal in" id="modalObs" tabindex="-1" role="dialog" aria-hidden="true" style="display: none;">
	        <div class="modal-dialog">
	        <div class="modal-content animated bounceInRight">
	                <div class="modal-header">
	                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
	                    <h4 class="modal-title">ELIMINAR EXPEDIENTE VIRTUAL N° <span id="lbl-modal-title"></span></h4>
	                    <small class="font-bold"><span id="lbl-modal-subtitle"></span></small>
	                </div>
	                <div class="modal-body">                    
	                    <form accept-charset="UTF-8" class="form-horizontal">                        
	                        <div class="form-group">
	                        	<input type="hidden" id="#txtExpedienteVirtual">
	                        	
	                        	<label>Motivo de Eliminaci&oacute;n:</label>
	                        	<textarea class="form-control" id="txa-modal-motivo" name="txa-modal-motivo" 
	                        	placeholder="Escriba el motivo" rows="3" maxlength="100">
	                        	</textarea>
	                        	<h6 class="pull-left">Min. 10, max. 100 caracteres</h6>
	        					<h6 class="pull-right" id="h6-modal-count"></h6>
	                        </div>                                                   
	                    </form>                                              
	                </div>
	                <div class="modal-footer">
	                    <button type="button" class="btn btn-primary" data-dismiss="modal">Salir</button>                    
	                    <button type="button" class="btn btn-danger" id="btn-modal-delete" name="btn-modal-delete">Eliminar</button>
	                </div>
	            </div>
	        </div>
	    </div>

		<!-- Importación de Bootstrap -->
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
		
		
		<!-- METODOS -->
		<script>
		var titulos = ${titulos};
		var excepciones = ${excepciones};
		var avisos = ${avisos};
		var fecVista = ${fecVista};
		var codDependenciaBase = ${codDependenciaBase};
		var listadoTiposNumeroExpediente = ${listadoTiposNumeroExpediente};
		var expedienteVirtual;

		$(document).ready(function() {
			$('#divSelectDocGroup').addClass('hiddenDiv');
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
			$("#btnEliminar").prop("disabled", true);
			$("#btnReapertura").prop("disabled",true);
			$('#txtFechaSistema').attr("value", fecVista);
			setearMascara( '#txtNumeroExpediente', "(9){1,14}", null, '' );
			$('#txtFecDocumentoGrupo').datetimepicker({
				format: 'DD/MM/YYYY',
				language: 'es',
				autoclose: true,
				forceParse: false,
				pickTime: false,
				maxDate: ${fecVista}
			});
			
		}

		function limpiarCampos(){
			$('#txtNumeroExpediente').val("");
			$("#btnBuscar").prop("disabled", false);
			$("#btnEliminar").prop("disabled", true);
			$("#btnReapertura").prop("disabled",true);
			$('#txtNumRuc').val("");
			$('#txtDesRazonSocial').val("");
			$('#txtDesDependencia').val("");
			$('#txtDesProceso').val("");
			$('#txtResponsable').val("");
			$('#txtDesTipoExpediente').val("");
			$('#txtExpedienteOrigen').val("");
			$('#txtExpedienteVirtual').val("");
			$('#txtFechaRegistro').val("");
			$('#txtTipoDocOrigen').val("");
			$('#txtNroDocOrigen').val("");
			$('#txtFecEmisionDocOrigen').val("");
			$('#txtTipoDocCierre').val("");
			$('#txtNroDocCierre').val("");
			$('#txtFecEmisionDocCierre').val("");
			$('#txtcodtipodocumento').val("");
			$('#txtFecNotificacionDocCierre').val("");
			
		}
		
		function activarNumeroExpediente() {
			expedienteVirtual = null;
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

		function limpiarPagina() {
			$('#selTipoNumeroExpediente').val('');
			activarNumeroExpediente();
			setPlaceHolder();
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

		function crearDlg(titulo, msj, btns){
			$('#dlgTitle').html(titulo);
			$('#dlgMsj').html(msj);
			$('#dlgBtns').empty();
			jQuery.each(btns, function(i, dato) {
				$('#dlgBtns').append(dato);
			});
			$('#modalDlg').modal('show');	
		}
		
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
		
		
		function buscar(){
			
			var indClaseExpediente = $('#selTipoNumeroExpediente').val(); 
			//indClaseExpediente =  "1" Expediente Origen
			//indClaseExpediente =  "2" Expediente Virtual
			var numExpediente = $('#txtNumeroExpediente').val(); 
			var t6613DocExpVirtBean= null;
			var fechaValida = null;

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
										url : '${pageContext.request.contextPath}/mantExpeVirt.htm?action=cargarMantenimientoExpedienteVirtual&indCarga=1',
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
											$('#txtDesDependencia').val(contribuyente.desDependencia);
											$('#txtDesProceso').val(expedienteVirtual.desProceso);
											$('#txtDesTipoExpediente').val(expedienteVirtual.desTipoExpediente);
											$('#txtExpedienteOrigen').val(expedienteVirtual.numExpedienteOrigen);
											$('#txtExpedienteVirtual').val(expedienteVirtual.numExpedienteVirtual);
											$('#txtResponsable').val(expedienteVirtual.nombreResponsable);
											$('#txtFechaRegistro').val(expedienteVirtual.fecRegistro);
											$('#txtindicador').val(expedienteVirtual.indicadorAcumulado);
											if(expedienteVirtual.codEstado == "01"){
												$('#txtcodtipodocumento').val(t6613DocExpVirtBean.codTipDoc);
												$('#txtTipoDocOrigen').val(t6613DocExpVirtBean.desNombreCompuesto);
												$('#txtNroDocOrigen').val(t6613DocExpVirtBean.numDoc);
												$('#txtFecEmisionDocOrigen').val(response.fechaEmisionDoc);
												$("#btnEliminar").prop("disabled", false);
												$("#btnReapertura").prop("disabled",true);
											}else{
												if(expedienteVirtual.codEstado == "02"){
													$('#txtcodtipodocumento').val(t6613DocExpVirtBean.codTipDoc);
													$('#txtTipoDocCierre').val(t6613DocExpVirtBean.desNombreCompuesto);
													$('#txtNroDocCierre').val(t6613DocExpVirtBean.numDoc);
													$('#txtFecEmisionDocCierre').val(response.fechaEmisionDoc);
													$('#txtFecNotificacionDocCierre').val(response.fechaNotificacionDoc);
													$("#btnEliminar").prop("disabled", true);
													$("#btnReapertura").prop("disabled",false);
												}
											}											
											$("#txtNumeroExpediente").attr('readonly', true);
											$("#btnBuscar").prop("disabled", true);
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

		// <--- METODO MAX LENGTH TEXTAREA
		var text_max = 100;
		$('#h6-modal-count').html(text_max + 'restante');
		$('#txa-modal-motivo').keyup(function() {
		  var text_length = $('#txa-modal-motivo').val().length;
		  $("#btn-modal-delete").prop("disabled", true);
		  if(text_length >=10){
		  	$("#btn-modal-delete").prop("disabled", false);
		  }
		  var text_remaining = text_max - text_length;		  
		  $('#h6-modal-count').html(text_remaining + ' restante');
		});
		
		// <-- METODO MAX LENGTH TEXTAREA
		function fnButtonEliminar() {
			//--- DATA MODAL
            $("#lbl-modal-title").html($("#txtExpedienteVirtual").val());
            $("#txa-modal-motivo").val('');
            $("#btn-modal-delete").prop("disabled", true);
            //--- CARGAR MODAL
			$('#modalObs').modal('show');
			$("#txa-modal-motivo").focus();
		}
		
		//--- BEGIN MODAL BUTTON DELETE 
		//----------------------------------------------------------
        $('body').on('click', '#btn-modal-delete', function (e) {
            e.preventDefault();
            $('#modalObs').modal('hide');
            botones = [];				
			var aceptar = $("<input/>").attr(
				{
					value: "Si", 
					type: "button", 
					"class": "btn btn-primary dlgButton", 
					"data-dismiss" : "modal", 
					onclick : "ejecutarEliminar()"
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
		});


		function ejecutarEliminar(){
	        var dataEnvio = new Object();
	        					
			dataEnvio.numExpediente = $.trim($("#txtExpedienteVirtual").val());
			dataEnvio.numExpedienteOrigen = $.trim($("#txtExpedienteOrigen").val());
			dataEnvio.desEliExp = $.trim($('#txa-modal-motivo').val());
            dataEnvio.codTipoDocumento = $.trim($("#txtcodtipodocumento").val());
            dataEnvio.numDocumento = $.trim($("#txtNroDocOrigen").val());

	        $.ajax({							
				url :  '${pageContext.request.contextPath}/mantExpeVirt.htm?action=eliminarExpedienteVirtual',
				type : 'POST',
				async : true,
				dataType : "json",
				data : JSON.stringify(dataEnvio),
				contentType : "application/json",
				mimeType : "application/json",
				//timeout : 5000,
				success : function(response) { 
					if(response.status === false){

					}	
	                var numExpedienteVirtual = expedienteVirtual.numExpedienteVirtual;
	                $("#btnEliminar").prop("disabled", true);
	                
					var mensaje= response.message.replace("{0}", numExpedienteVirtual);
	                mostrarDlgInfo(titulos.tituloDefecto, mensaje);
                
	            },	            
				error : function(response) {
					mostrarPagError();
				}				
			});
        }
		
		function fnButtonRevertirCierre(){
				botones = [];
				var aceptar = $("<input/>").attr(
					{
						value: "Si", 
						type: "button", 
						"class": "btn btn-primary dlgButton", 
						"data-dismiss" : "modal", 
						onclick : "ejecutarRevertirCierre()"
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
				crearDlg(titulos.tituloDefecto, avisos.aviso03, botones);
			}
		
		function ejecutarRevertirCierre(){
			
            var dataEnvio = new Object();                                                                                
            dataEnvio.numExpedienteOrigen = $.trim($("#txtExpedienteOrigen").val());
            dataEnvio.numExpediente = $.trim($("#txtExpedienteVirtual").val());
            dataEnvio.codTipoDocumento = $.trim($("#txtcodtipodocumento").val());
            dataEnvio.numDocuemento = $.trim($("#txtNroDocCierre").val());

            $.ajax({                                                                                                              
               url : '${pageContext.request.contextPath}/mantExpeVirt.htm?action=revertirCierreExpedienteVirtual',
               type : 'POST',
               async : true,
               dataType : "json",
               data : JSON.stringify(dataEnvio),
               contentType : "application/json",
               mimeType : "application/json",
               //timeout : 5000,
               success : function(response){ 
				if(response.status === false){
					mostrarDlgInfo(titulos.tituloDefecto, response.message);
					return false; 
				}	
                var numExpedienteVirtual = expedienteVirtual.numExpedienteVirtual;
                $("#btnReapertura").prop("disabled", true);
	
				var mensaje= response.message.replace("{0}", numExpedienteVirtual);
                mostrarDlgInfo(titulos.tituloDefecto, mensaje);
                
                },            
				error : function(response) {
					mostrarPagError();
				}
              });
            
        }

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
		function validarExpedientesAcumulados(){
	        var dataEnvio = new Object();
	        					
			dataEnvio.numExpediente = $.trim($("#txtExpedienteVirtual").val());
			dataEnvio.indicadorAcumulado = $.trim($("#txtindicador").val());
	        $.ajax({							
				url :  '${pageContext.request.contextPath}/mantExpeVirt.htm?action=validarExpedientesAcumulado',
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
						return false; 
					}	
					 fnButtonRevertirCierre();
	            },	            
				error : function(response) {
					mostrarPagError();
				}				
			});
        }	
		function validarDocumentos(){
	        var dataEnvio = new Object();
	        					
			dataEnvio.numExpediente = $.trim($("#txtExpedienteVirtual").val());
			dataEnvio.indicadorAcumulado = $.trim($("#txtindicador").val());
	        $.ajax({							
				url :  '${pageContext.request.contextPath}/mantExpeVirt.htm?action=validarDocuemntos',
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
						return false; 
					}
					fnButtonEliminar();
                
	            },	            
				error : function(response) {
					mostrarPagError();
				}				
			});
        }
		</script>
	</body>
</html>