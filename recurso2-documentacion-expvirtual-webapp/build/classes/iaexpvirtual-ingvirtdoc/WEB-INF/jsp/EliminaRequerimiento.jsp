<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name = "viewport" content = "initial-scale = 1.0, user-scalable = no, width=device-width">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>MANTENIMIENTO DE REQUERIMIENTOS </title>
		
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
							<strong>MANTENIMIENTO DE REQUERIMIENTOS</strong>							
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
									<input id="btnBuscar" type="button" class="btn btn-primary" onclick="fnConsultarExpediente()" value="Buscar" />
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
								<div class="col-md-12">

								<fieldset class="fsStyle">
									<legend class="legendStyle">Datos del Expediente</legend>
									<div class="panel-body">
										<div class="row content-box">
											<div class="col-md-2">
												<label>RUC: </label>
											</div>											
											<div class="col-md-3">
												<input id="txtNumRuc" type="text" class="form-control" disabled="disabled"/>
											</div>
											<div class="col-md-2">
												<label>Raz&oacute;n Social: </label>
											</div>
											<div class="col-md-5">
												<input id="txtDesRazonSocial" type="text" class="form-control" disabled="disabled"/>
											</div>
										</div>
										<div class="row content-box">&nbsp;</div>
										<div class="row content-box">
											<div class="col-md-2">
												<label>Proceso:</label>
											</div>
											<div class="col-md-3">
												<input id="txtDesProceso" type="text" class="form-control" disabled="disabled"/>
											</div>
											<div class="col-md-2">
												<label>Tipo de expediente:</label>
											</div>
											<div class="col-md-3">
												<input id="txtDesTipoExpediente" type="text" class="form-control" disabled="disabled"/>
											</div>
											<div class="col-md-2">
											</div>
										</div>
										<div class="row content-box">&nbsp;</div>
										<div class="row content-box">
											<div class="col-md-2">
												<label>N° Expediente Origen:</label>
											</div>
											<div class="col-md-3">
												<input id="txtNumExpeOrigen" type="text" class="form-control" disabled="disabled"/>
											</div>
											<div class="col-md-2">
												<label>N° Expediente Virtual:</label>
											</div>
											<div class="col-md-3">
												<input id="txtNumExpeVirtual" type="text" class="form-control" disabled="disabled"/>
											</div>
											<div class="col-md-2">
											</div>
										</div>
										<div class="row content-box">&nbsp;</div>
										<div class="row content-box">											
											<div class="col-md-2">
												<label>Fecha Documento Origen:</label>
											</div>
											<div class="col-md-3">
												<input id="txtFechaOrigen" type="text" class="form-control" disabled="disabled"/>
											</div>
											<div class="col-md-2">
												<label>Fecha de Notificaci&oacute;n:</label>
											</div>
											<div class="col-md-3">
												<input id="txtFechaNotif" type="text" class="form-control" disabled="disabled"/>
											</div>
											<div class="col-md-2">
											</div>
										</div>
										<div class="row content-box">&nbsp;</div>
										<div class="row content-box">											
											<div class="col-md-2">
												<label>Forma de Notificaci&oacute;n:</label>
											</div>
											<div class="col-md-3">
												<input id="txtFormaNotif" type="text" class="form-control" disabled="disabled"/>
											</div>
											<div class="col-md-2">
												<label>Fecha de Registro:</label>
											</div>
											<div class="col-md-3">
												<input id="txtFechaReg" type="text" class="form-control" disabled="disabled"/>
											</div>
											<div class="col-md-2">
											</div>
										</div>
										<div class="row content-box">&nbsp;</div>
										<div class="row content-box">											
											<div class="col-md-2">
												<label>Responsable:</label>
											</div>
											<div class="col-md-5">
												<input id="txtdesResponsable" type="text" class="form-control" disabled="disabled"/>
											</div>											
											<div class="col-md-5">
											</div>
										</div>
									</div>
								</fieldset>

								</div>
							</div>
							<div class="row content-box">								
								<div class="col-md-12">
									<fieldset class="fsStyle">
									<legend class="legendStyle">Lista de Requerimientos </legend>
										<div class="panel-body">
											<table id="tableRequerimiento" class="table display" cellspacing="0" style="width: 100%;">
												<thead>
													<tr class="active">
														<th width="2%">N°</th>
														<th width="5%">N° Requerimiento</th>
														<th width="5%">Origen</th>
														<th width="5%">Estado del Requerimiento</th>
														<th width="5%">Tipo de Cierre</th>
														<th width="5%">Fecha de Registro</th>
														<th width="5%">Fecha de Vencimiento</th>
														<th width="20%">Usuario del Requerimiento</th>
														<th width="15%">Acciones</th>
													</tr>
												</thead>
											</table>
										</div>
									</fieldset>
								</div>
							</div>
							
							<div class="row content-box">
								<div class="col-md-12 text-center">
									<button type="button" class="btn btn-primary" onclick="limpiar()">Limpiar</button>
								</div>
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
                    <h4 class="modal-title">ELIMINAR REQUERIMIENTO N° <span id="lbl-modal-title"></span></h4>
                    <small class="font-bold"><span id="lbl-modal-subtitle"></span></small>
                </div>
                <div class="modal-body">                    
                    <form accept-charset="UTF-8" class="form-horizontal">                        
                        <div class="form-group">
                        	<input type="hidden" id="hdd-modal-correlativo">
                        	<input type="hidden" id="hdd-modal-numRequerimiento">
                        	<input type="hidden" id="hdd-modal-numRequerimientoOrigen">
                        	<input type="hidden" id="hdd-modal-numExpedienteVirtual">
                        	
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

<!-- JAVASCRIPT -->
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
		var titulos = ${titulos};
		var excepciones = ${excepciones};			
		var avisos = ${avisos};
		var fecVista = ${fecVista};
		var codDependenciaBase = ${codDependenciaBase};
		var listadoTiposNumeroExpediente = ${listadoTiposNumeroExpediente};

		var sUrl = '${pageContext.request.contextPath}/eliReqControl.htm';
			
		$(document).ready(function() {
			$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);	

			$('#tableRequerimiento').DataTable( {
				"language": {
					"url"		:	"../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
				},
				oLanguage : {
					//sInfo		:	"_START_ - _END_ de _TOTAL_ elementos",
					//sInfoEmpty	:	"_START_ - _END_ de _TOTAL_ elementos",
					sInfo		:	' ',
					sInfoEmpty	:	' ',
					sEmptyTable	:	' ',
					oPaginate : {
						"sFirst":    "&#60;&#60;",
				        "sLast":     "&#62;&#62;",
				        "sNext":     "&#62;",
				        "sPrevious": "&#60;"
					}
				},
				columns : [
					{data :	"correlativo"},
					{data :	"numRequerimOrigen" },
					{data :	"desOrigRequerimiento" },
					{data :	"desEstado" },
					{data :	"desTipCierr" },
					{data :	"fecRequerimiento" },
					{data :	"fecVencimiento" },
					{data :	"nomUsuReq" },
					{data :	"numRequerimiento", sClass: 'centered alinearCentrado',
						render : function(data, type, row){
							//01 MANUAL 01 ABIERTO
							var rows = '';
							if(row.codOrigRequerimiento == "01" && row.codEstadoRequerim =="01"){
								rows +='<button type="button" class="btn btn-danger" ';
								rows +='onclick="fnDataTableEliminar(\''+ 
									row.correlativo + '\',\''+ 
									row.numRequerimiento + '\',\'' + 
									row.numRequerimOrigen + '\',\'' + 
									row.numExpedienteVirtual + '\')">';								
								rows +='Eliminar';
								rows +='</button>';								
							}
							return rows;	
					}}
				],
				
				tableTools: {
			        "sSwfPath": "../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/TableTools/swf/copy_csv_xls.swf"
			    },
				ordering			:	true,
				bScrollAutoCss		:	true,
				bStateSave			:	false,
				bAutoWidth			:	false,
				bScrollCollapse		:	false,
				searching			:	false,
				paging				:	true,
				pagingType			:   "full_numbers",
				iDisplayLength		:	5,
				responsive			:	true,
				bLengthChange		: 	false,
				fnDrawCallback		:	function(oSettings) {
					$('#tableDocumento_paginate').addClass('div100');
					if (oSettings.fnRecordsTotal() == 0) {
						$('#tabla_paginate').addClass('hiddenDiv'); //el footer de paginacion siempre tiene como ID "miTablaID_paginate"
						//no se oculta automaticamente
						//cuando hay 0 registros, por eso se oculta anadiendo el class .hiddenDiv
			        }
					else {
						$('#tabla_paginate').removeClass('hiddenDiv');
					}
			    }
			});
											
			inicializarCampos();
		});	

		function inicializarCampos() {				
			//CARGAR TIPO DE EXPEDIENTE
			var $element = $('#selTipoNumeroExpediente');				
			$.each(listadoTiposNumeroExpediente, function(i, dato) {				
				var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
				$element.append($option);					
			});
			//CARGAR FECHA DEL SISTEMA								
			$('#txtFechaSistema').attr("value", fecVista);
			//CONFIGURAR UNA MASCARA DE INGRESO
			setearMascara( '#txtNumeroExpediente', "(9){1,14}", null, '' );	
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
			limpiarCampos();				
		}

		function limpiarCampos(){
			$('#txtNumeroExpediente').val("");
			$("#btnBuscar").prop("disabled", false);				
			$('#txtNumRuc').val("");
			$('#txtDesRazonSocial').val("");
			$('#txtNumExpeOrigen').val("");
			$('#txtNumExpeVirtual').val("");
			$('#txtEstExpediente').val("");
			$('#txtDesProceso').val("");
			$('#txtDesTipoExpediente').val("");
			$('#txtDesOrigen').val("");
			$('#txtFechaOrigen').val("");
			$('#txtFechaReg').val("");
			$('#txtFormaNotif').val("");
			$('#txtFechaNotif').val("");
			$('#txtdesResponsable').val("");
			$('#tableRequerimiento').dataTable().fnClearTable();
			$('#tableRequerimiento').dataTable().fnDraw();
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
			
			crearDlg(titulos.tituloDefecto, avisos.aviso01, botones);				
		}

		function limpiarPagina() {				
			$('#selTipoNumeroExpediente').val('');
			activarNumeroExpediente();
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
			document.forms.frmPrincipal.action = formURL;
			document.forms.frmPrincipal.method = "post";
			document.forms.frmPrincipal.submit();				
		}

		function permitirNumeros(e){				
			var keynum = window.event ? window.event.keyCode : e.which;				
			if ((keynum == 8)) {					
				return true;					
			}				
			return /\d/.test(String.fromCharCode(keynum));				
		}
		
		function OnErrorCall( jqXHR, textStatus, errorThrown ) {
			if (jqXHR.status === 0) {
		        console.log('Not connect: Verify Network.');
		    } else if (jqXHR.status == 404) {
		        console.log('Requested page not found [404]');
		    } else if (jqXHR.status == 500) {
		        console.log('Internal Server Error [500].');
		    } else if (textStatus === 'parsererror') {
		        console.log('Requested JSON parse failed.');
		    } else if (textStatus === 'timeout') {
		        console.log('Time out error.');
		    } else if (textStatus === 'abort') {
		        console.log('Ajax request aborted.');
		    } else {
		        console.log('Uncaught Error: ' + jqXHR.responseText);
		   }
		}
		//--- BEGIN FN CONSULTA EXPEDIENTE
		//----------------------------------------------------------
		function fnConsultarExpediente(){
			var indClaseExpediente = $('#selTipoNumeroExpediente').val();
			var numExpediente = $('#txtNumeroExpediente').val();				

			
			if (indClaseExpediente == '1' && numExpediente == ''){
			mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion08);
			}else{
				if(indClaseExpediente == '2' && numExpediente == ''){
					mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion08);
				}else{	
					if (indClaseExpediente == '' || numExpediente == '') {				
						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion01);					
					} else {
						if (indClaseExpediente == '1' && numExpediente.length < 1) {					
							mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion02);						
						} else if (indClaseExpediente == '2' && numExpediente.length < 14){						
							mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion02);						
						} else {
							var dataEnvio = new Object();						
							dataEnvio.indClaseExpediente = indClaseExpediente;
							dataEnvio.numExpediente = numExpediente;
							dataEnvio.codDependenciaBase = codDependenciaBase;

							$.ajax({							
								url : sUrl + '?action=consultarExpedienteJSON',
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
									var expedienteVirtual = response.expedienteVirtual;
									var contribuyente = response.contribuyente;
									var expedienteVirtualDocOri = response.expedienteVirtualDocOri;
									var expedienteVirtualReqs = JSON.parse(response.expedienteVirtualReqs);
									
									$('#txtNumRuc').val(expedienteVirtual.numRuc);
									$('#txtDesRazonSocial').val(contribuyente.desRazonSocial);
									$('#txtNumExpeOrigen').val(expedienteVirtual.numExpedienteOrigen);
									$('#txtNumExpeVirtual').val(expedienteVirtual.numExpedienteVirtual);
									$('#txtEstExpediente').val(expedienteVirtual.desEstado);
									$('#txtDesProceso').val(expedienteVirtual.desProceso);								
									$('#txtDesTipoExpediente').val(expedienteVirtual.desTipoExpediente);
									$('#txtDesOrigen').val(expedienteVirtual.desOrigen);
									$('#txtFechaOrigen').val(expedienteVirtual.fechaDocumentoOrigen);
									$('#txtFechaReg').val(expedienteVirtual.fecRegistro);
									$('#txtFechaNotif').val(expedienteVirtualDocOri.fecNotif);
									$('#txtFormaNotif').val(expedienteVirtualDocOri.desForNotif);
									$('#txtdesResponsable').val(expedienteVirtual.nombreResponsable);							

									$('#tableRequerimiento').dataTable().fnClearTable();
									if (expedienteVirtualReqs.length > 0) {									
										$('#tableRequerimiento').dataTable().fnAddData(expedienteVirtualReqs); 
									}else{									
										$('#tableRequerimiento').dataTable().fnDraw();
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
		$('#h6-modal-count').html(text_max + ' restante');

		$('#txa-modal-motivo').keyup(function() {
		  var text_length = $('#txa-modal-motivo').val().length;
		  $('#btn-modal-delete').prop("disabled", true);
		  if(text_length >=10){
		  	$('#btn-modal-delete').prop("disabled", false);
		  }
		  var text_remaining = text_max - text_length;		  
		  $('#h6-modal-count').html(text_remaining + ' restante');
		});
		// ---> METODO MAX LENGTH TEXTAREA

		//--- BEGIN TABLE BUTTON DELETE 
		//----------------------------------------------------------
		function fnDataTableEliminar(correlativo,numRequerimiento,numRequerimOrigen,numExpedienteVirtual){
			//--- DATA MODAL
            $("#hdd-modal-correlativo").val(correlativo);
			$("#hdd-modal-numRequerimiento").val(numRequerimiento);
			$("#hdd-modal-numRequerimientoOrigen").val(numRequerimOrigen);			
			$("#hdd-modal-numExpedienteVirtual").val(numExpedienteVirtual);

            $("#lbl-modal-title").html(numRequerimOrigen);
            $("#txa-modal-motivo").val('');
            $('#btn-modal-delete').prop("disabled", true);
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
					onclick : "fneliminarRequerimiento()"
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

			var stringMessage = avisos.aviso02.replace(/\{0\}/g, $.trim($("#hdd-modal-numRequerimientoOrigen").val()));
			stringMessage = stringMessage.replace(/\{1\}/g, $.trim($("#hdd-modal-numExpedienteVirtual").val()));
			
			crearDlg(titulos.tituloDefecto, stringMessage, botones);	
		});
        //--- BEGIN FN ELIMINAR REQUERIMIENTO 
		//----------------------------------------------------------
		function fneliminarRequerimiento(){
	        var dataEnvio = new Object();	        					
			dataEnvio.numRequerimiento = $.trim($("#hdd-modal-numRequerimiento").val()) ;
			dataEnvio.numRequerimientoOrigen = $.trim($("#hdd-modal-numRequerimientoOrigen").val()) ;
			dataEnvio.numExpedienteVirtual = $.trim($("#hdd-modal-numExpedienteVirtual").val());
			dataEnvio.desEliReq = $.trim($('#txa-modal-motivo').val());

	        $.ajax({							
				url : sUrl + '?action=eliminarRequerimientoJSON',
				type : 'POST',
				async : true,
				dataType : "json",
				data : JSON.stringify(dataEnvio),
				contentType : "application/json",
				mimeType : "application/json",
				//timeout : 5000,
				success : function(response) { 
					if(response.status === false){
						mostrarPagError();
	                    return false; 
	                }
	                var tabla = $('#tableRequerimiento').dataTable().fnGetData();
					$('#tableRequerimiento').dataTable().fnClearTable();						
					var fileData;
					var a=1;
					var correlativo = $("#hdd-modal-correlativo").val();
					for(var i=0;i<tabla.length;i++){
						if(tabla[i].correlativo != correlativo){
							fileData = new Object();
							fileData.correlativo = a;
							fileData.numRequerimiento = tabla[i].numRequerimiento;
							fileData.numRequerimOrigen = tabla[i].numRequerimOrigen;
							fileData.desOrigRequerimiento = tabla[i].desOrigRequerimiento;
							fileData.desEstado = tabla[i].desEstado;
							fileData.desTipCierr= tabla[i].desTipCierr;
							fileData.fecRequerimiento = tabla[i].fecRequerimiento;							
							fileData.fecVencimiento = tabla[i].fecVencimiento;
							fileData.nomUsuReq = tabla[i].nomUsuReq;					
							fileData.codOrigRequerimiento = tabla[i].codOrigRequerimiento;
							fileData.codEstadoRequerim = tabla[i].codEstadoRequerim;
							fileData.numExpedienteVirtual = tabla[i].numExpedienteVirtual;
	
							$('#tableRequerimiento').dataTable().fnAddData(fileData);
							a++;
						}
					};
					mostrarDlgInfo(titulos.tituloDefecto, response.message);
	            },	            
				error : function(response) {
					mostrarPagError();
				}				
			});
        }
	</script>
</body>
</html>