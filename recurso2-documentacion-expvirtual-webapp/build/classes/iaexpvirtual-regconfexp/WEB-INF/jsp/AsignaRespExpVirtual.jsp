<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="es">
	
	<head>
		
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name = "viewport" content = "initial-scale = 1.0, user-scalable = no, width=device-width">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>ASIGNACI&Oacute;N DE RESPONSABLES</title>
		
		<!-- Bootstrap -->
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/media/css/jquery.dataTables.css">
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Responsive/css/dataTables.responsive.css">
		
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 10]>
			<script src="../a/js/libs/bootstrap/3.3.2/plugins/html5shiv/3.7.2/html5shiv.min.js"></script>
	      <script src="../a/js/libs/bootstrap/3.3.2/plugins/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		<style type="text/css">
			
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
			th {
				border: 1px solid gray;
				border-collapse: collapse;
				background: Gainsboro !important;
			}
			td {
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
			body {
				font-size: 12px;
			}
			
			.alinearCentrado {
				vertical-align: middle !important;
			}
			
			.izquierda {
				text-align: left !important;
			}
			
		</style>
		
	</head>
	
	<body>
		
		<div class="container">
			
			<div class="row">
				
				<div class="panel panel-primary">
					<div class="panel-heading centered">
						<h2 class="panel-title">
							<strong>ASIGNACI&Oacute;N DE RESPONSABLES</strong>
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
							
						</div>
					</div>
				</div>
				
				<div class="panel panel-primary">
					<div class="marginedDiv">
						<div class="panel-body">
							<div class="row content-box">
								<div class="col-md-1">
									<label>Buscar: </label>
								</div>
								<div class="col-md-4">
									<input id="txtBusqueda" name="txtBusqueda" type="text" class="form-control form-control-single" onkeyup="filtrar()"/> 
								</div>
								<div class="col-md-7">
									&nbsp;
								</div>
							</div>
							<div class="row content-box">
								&nbsp;
							</div>
							<div class="row content-box">
								<div class="col-md-5 centered">
									<label>Responsables</label>
								</div>
								<div class="col-md-2">
									&nbsp;
								</div>
								<div class="col-md-5 centered">
									<label>Responsables Asignados al Expediente</label>
								</div>
							</div>

							<div class="row content-box">
								<div class="col-md-5 centered">
								<div style="max-height: 250px; overflow-y: auto; border: 1px solid #337ab7;">
									<table id="tblRespoDisponible" class="table display">
										<thead>
											<tr class="active">
												<th width="20%">N&deg;</th>
												<th width="20%">Sel</th>
												<th width="60%">Responsable</th>
											</tr>
										</thead>
										<tbody>
										
										</tbody>
									</table> 
								</div>
								</div>
								<div class="col-md-2">
									<div class="marginedDiv">
										<div class="form-group centered">
											<input id="btnAgregar" type="button" style="width:90px;" class="btn btn-primary" onclick="agregar()" value="Agregar>>" />
										</div>
										<div class="form-group centered">
											<input id="btnQuitar" type="button" style="width:90px;" class="btn btn-primary" onclick="quitar()" value="<<Quitar" />
										</div>
									</div>
								</div>
								<div class="col-md-5 centered">
								<div style="max-height: 250px; overflow-y: auto; border: 1px solid #337ab7;">
									<table id="tblRespoExpediente" class="table display">
										<thead>
											<tr class="active">
												<th width="15%">N&deg;</th>
												<th width="15%">Sel</th>
												<th width="40%">Responsable</th>
												<th width="30%">Origen</th>
											</tr>
										</thead>
										<tbody>
										
										</tbody>
									</table> 
								</div>
								</div>
							</div>
							<div class="row content-box">
								<div class="col-md-7">
									&nbsp;
								</div>
								<div class="col-md-5">
									<div class="marginedDiv">
										<div class="form-group pull-right">
											<input id="btnAsignar" type="button" class="btn btn-primary" onclick="asignar()" value="Guardar Cambios" />
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
		
		<script src="../a/js/libs/bootstrap/3.3.2/plugins/jquery.inputmask-3.1/dist/jquery.inputmask.bundle.min.js" type="text/javascript"></script>
		
		<script>
			
			var titulos = ${titulos};
			var excepciones = ${excepciones};
			var avisos = ${avisos};
			var fecVista = ${fecVista};
			var codDependenciaBase = ${codDependenciaBase};
			var codEstadoExpedientePermitido = ${codEstadoExpedientePermitido};
			var desEstadoExpedientePermitido = ${desEstadoExpedientePermitido};
			var listadoTiposNumeroExpediente = ${listadoTiposNumeroExpediente};
			
			var expedienteVirtual;
			
			var dataDisponible = [];
			var dataAsociada = [];
			
			var dataFinal = [];
			
			var indOrigen = false;
			
			$(document).ready(function() {
				
				$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
				
				inicializarTiposNumerosExpediente();
				
				inicializarTablas();
				
			});
			
			function inicializarTiposNumerosExpediente() {
				
				var $element = $('#selTipoNumeroExpediente');
				
				$.each(listadoTiposNumeroExpediente, function(i, dato) {
					
					var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
					$element.append($option);
					
				});
				
				$("#btnAsignar").prop("disabled", true);
				
				$('#txtFechaSistema').attr("value", fecVista);
				
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
			
			function inicializarTablas() {
				
				$('#tblRespoDisponible').DataTable({
					"language": {
						"url"		:	"../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
					},
					"dom": 'rt',
					"fnInitComplete": function ( oSettings ) {
						oSettings.oLanguage.sZeroRecords = ""
					},
					oLanguage : {
						sInfo		:	' ',
						sInfoEmpty	:	' ',
						sEmptyTable	:	' ',
						oPaginate	:	' '
					},
					columns : [
						{data : "numOrden", sClass: 'centered alinearCentrado'},
						{data : "indSeleccion", sClass: 'centered alinearCentrado', 
							render : function(data, row){
								
								return jQuery('<input>').css(
									{
										"marginLeft": "8px", 
										"width": "18px", 
										"height": "18px"
									}
								).attr(
									{
										type:'checkbox', 
										checked: data.toString()==='true' ? true : false, 
										"class" : "bChecked"
									}
								).wrap('<div></div>').parent().html();
							}
						},
						{data : "desColaborador", sClass: 'izquierda alinearCentrado'}
					],
					//"scrollY"		:	"250px",
					//"scrollX"		:	true,
					//bScrollCollapse	:	true,
					ordering		:	false,
					//bScrollAutoCss	:	true,
					bStateSave		:	false,
					//bAutoWidth		:	false,
					info			:	false,
					paging			:	false,
					responsive		:	false
					//bLengthChange	:	false
				} );
				
				$('#tblRespoExpediente').DataTable({
					"language": {
						"url"		:	"../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
					},
					oLanguage : {
						sInfo		:	' ',
						sInfoEmpty	:	' ',
						sEmptyTable	:	' ',
						oPaginate	:	' '
					},
					columns : [
						{data : "numOrden", sClass: 'centered alinearCentrado'},
						{data : "indSeleccion", sClass: 'centered alinearCentrado', 
							render : function(data, row){
								
								return jQuery('<input>').css(
									{
										"marginLeft"	:	"8px", 
										"width"			:	"18px", 
										"height"		:	"18px"
									}
								).attr(
									{
										type:'checkbox', 
										checked: data.toString()==='true' ? true : false, 
										"class" : "bChecked"
									}
								).wrap('<div></div>').parent().html();
							}
						},
						{data : "desColaborador", sClass: 'izquierda alinearCentrado'},
						{data : "indResponsable", sClass: 'centered alinearCentrado', 
							
							render : function(data, row){
								
								return jQuery('<select>').css(
									{
										"marginLeft"	:	"8px", 
										"width"			:	"50px", 
										"height"		:	"18px"
									}
								)
								.append(
									jQuery('<option>')
									.attr(
										{
											value		:	'1',
											selected	:	data.toString()==='1' ? true : false
										}
									).text('Si')
								)
								.append(
									jQuery('<option>')
									.attr(
										{
											value		:	'2',
											selected	:	data.toString()==='2' ? true : false
										}
									).text('No')
								)
								.attr(
									{
										"class" : "vChecked",
										"disabled": true
									}
								)
								.wrap('<div></div>').parent().html();
								
							}
							
						}
						
					],
					//"sScrollY"		:	"250px",
					//"scrollX"		:	true,
					//bScrollCollapse	:	true,
					ordering		:	false,
					//bScrollAutoCss	:	true,
					bStateSave		:	false,
					//bAutoWidth		:	false,
					searching		:	false,
					info			:	false,
					paging			:	false,
					responsive		:	false
					//bLengthChange	:	false
				} );
				
				$('#tblRespoDisponible').dataTable().fnClearTable();
				$('#tblRespoDisponible').dataTable().fnDraw();
				
				$('#tblRespoExpediente').dataTable().fnClearTable();
				$('#tblRespoExpediente').dataTable().fnDraw();
				
			}
			
			function activarNumeroExpediente() {
				
				expedienteVirtual = null;
								
				var tipoNumeroExpediente = $('#selTipoNumeroExpediente').val();
				
				$('#txtNumeroExpediente').val("");
				$("#txtBusqueda").val("");
				
				if (tipoNumeroExpediente == "") {
					
					$("#txtNumeroExpediente").attr('readonly', true);
					
				} else {
					
					if (tipoNumeroExpediente == "1") { // origen
						// [jjurado 21/06/2016] Se modifica la longitud del expediente origen a 17 dígitos
						$("#txtNumeroExpediente").attr('maxlength', 17);
						setearMascara( '#txtNumeroExpediente', "(9){1,17}", null, '' );
						
					} else if (tipoNumeroExpediente == "2") { // virtual
						
						$("#txtNumeroExpediente").attr('maxlength', 14);
						setearMascara( '#txtNumeroExpediente', "(9){1,14}", null, '' );
						
					}
					
					$("#txtNumeroExpediente").attr('readonly', false);
					
				}
				
				$("#btnBuscar").prop("disabled", false);
				$("#btnAsignar").prop("disabled", true);
				
				$('#txtNumRuc').val("");
				$('#txtDesRazonSocial').val("");
				
				$('#tblRespoDisponible').dataTable().fnClearTable();
				$('#tblRespoDisponible').dataTable().fnDraw();
				
				$('#tblRespoExpediente').dataTable().fnClearTable();
				$('#tblRespoExpediente').dataTable().fnDraw();
				
			}
			
			function buscar() {
				
				var indClaseExpediente = $('#selTipoNumeroExpediente').val();
				var numExpediente = $('#txtNumeroExpediente').val();
				
				if (indClaseExpediente == '' || numExpediente == '') {
					
					mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion01);
					
				} else {
					// [jjurado 21/06/2016] se reduce el mínimo de dígitos válidos para realizar la búsqueda del expediente origen
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
							
							url : '${pageContext.request.contextPath}/asigRespExpe.htm?action=cargarAsignacionResponsableExpediente&indCarga=1',
							type : 'POST',
							async : true,
							dataType : "json",
							data : JSON.stringify(dataEnvio),
							contentType : "application/json",
							mimeType : "application/json",
							//timeout : 5000,
							success : function(response) {
								
								expedienteVirtual = response.expedienteVirtual;
								var contribuyente = response.contribuyente;
								var indListaUniOrgaDependVacio = response.indListaUniOrgaDependVacio;
								if(indListaUniOrgaDependVacio=="-1"){
									dataFinal = response.responsablesFinales;
									if (dataFinal.length > 0) {
													
										$('#tblRespoExpediente').dataTable().fnClearTable();
										$('#tblRespoExpediente').dataTable().fnAddData(dataFinal);
										$('#tblRespoExpediente').dataTable().fnDraw();
										
									} else {
										
										$('#tblRespoExpediente').dataTable().fnClearTable();
										$('#tblRespoExpediente').dataTable().fnDraw();
										
									}
									var mensajeExcepcion = excepciones.excepcion11;
									mostrarDlgInfo(titulos.tituloDefecto, mensajeExcepcion);
									return;
								}
								if (expedienteVirtual != null) {
									
									if (codEstadoExpedientePermitido == expedienteVirtual.codEstado) {
										
										if (contribuyente != null) {
											//Inicio - [oachahuic][PAS20165E210400270]
											//if (codDependenciaBase.substring(0,3) == contribuyente.codDependencia.substring(0,3)) {
											//Fin - [oachahuic][PAS20165E210400270]
												dataDisponible = response.responsablesDisponibles;
												dataAsociada = response.responsablesAsignados;
												dataFinal = response.responsablesFinales;
												
												$('#txtNumRuc').val(contribuyente.numRuc);
												$('#txtDesRazonSocial').val(contribuyente.desRazonSocial);
												
												$("#txtNumeroExpediente").attr('readonly', true);
												
												$("#btnBuscar").prop("disabled", true);
												$("#btnAsignar").prop("disabled", false);
												
												if (dataDisponible.length > 0) {
													
													$('#tblRespoDisponible').dataTable().fnClearTable();
													$('#tblRespoDisponible').dataTable().fnAddData(dataDisponible);
													$('#tblRespoDisponible').dataTable().fnDraw();
													
												} else {
													
													$('#tblRespoDisponible').dataTable().fnClearTable();
													$('#tblRespoDisponible').dataTable().fnDraw();
													
												}
												
												if (dataFinal.length > 0) {
													
													$('#tblRespoExpediente').dataTable().fnClearTable();
													$('#tblRespoExpediente').dataTable().fnAddData(dataFinal);
													$('#tblRespoExpediente').dataTable().fnDraw();
													
												} else {
													
													$('#tblRespoExpediente').dataTable().fnClearTable();
													$('#tblRespoExpediente').dataTable().fnDraw();
													
												}
												
												filtrar();
											//Inicio - [oachahuic][PAS20165E210400270]
											/*} else {
												
												expedienteVirtual = null;
												
												$('#txtNumRuc').val("");
												$('#txtDesRazonSocial').val("");
												
												$('#tblRespoDisponible').dataTable().fnClearTable();
												$('#tblRespoDisponible').dataTable().fnDraw();
												
												$('#tblRespoExpediente').dataTable().fnClearTable();
												$('#tblRespoExpediente').dataTable().fnDraw();
												
												mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion06);
												
											}*/
											//Fin - [oachahuic][PAS20165E210400270]
										} else {
											
											expedienteVirtual = null;
											
											$('#txtNumRuc').val("");
											$('#txtDesRazonSocial').val("");
											
											$('#tblRespoDisponible').dataTable().fnClearTable();
											$('#tblRespoDisponible').dataTable().fnDraw();
											
											$('#tblRespoExpediente').dataTable().fnClearTable();
											$('#tblRespoExpediente').dataTable().fnDraw();
											
											mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion05);
											
										}
										
									} else {
										
										expedienteVirtual = null;
										
										$('#txtNumRuc').val("");
										$('#txtDesRazonSocial').val("");
										
										$('#tblRespoDisponible').dataTable().fnClearTable();
										$('#tblRespoDisponible').dataTable().fnDraw();
										
										$('#tblRespoExpediente').dataTable().fnClearTable();
										$('#tblRespoExpediente').dataTable().fnDraw();
										
										var mensajeExcepcion = excepciones.excepcion04;
										
										mensajeExcepcion = mensajeExcepcion.replace("{0}", desEstadoExpedientePermitido);
										
										mostrarDlgInfo(titulos.tituloDefecto, mensajeExcepcion);
										
									}
									
								} else {
									
									expedienteVirtual = null;
									
									$('#txtNumRuc').val("");
									$('#txtDesRazonSocial').val("");
									
									$('#tblRespoDisponible').dataTable().fnClearTable();
									$('#tblRespoDisponible').dataTable().fnDraw();
									
									$('#tblRespoExpediente').dataTable().fnClearTable();
									$('#tblRespoExpediente').dataTable().fnDraw();
									
									mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion03);
									
								}
								
							},
							error : function(response) {
								
								mostrarPagError();
								
							}
							
						});
						
					}
					
				}
				
			}
			
			function filtrar() {
				
				var critBusqueda = $('#txtBusqueda').val();
				
				var oTable = $('#tblRespoDisponible').dataTable();
				
				oTable.fnFilter(critBusqueda, 2, true, false);
				//ini 10-12-15 :miguel ochoa actualizando nro de orden
				oTable.fnDraw();
				actualizarNumeroOrden();
				//fin 10-12-15 :miguel ochoa actualizando nro de orden
				
			}
			
			function actualizarNumeroOrden(){
				var fila = $('#tblRespoDisponible tr');
				var cont=0;
				// 1 es la cabecera de la tabla				
				for (var i = 1; i <= fila.length-1; i++) {						
					col = fila[i];
					if(col.cells.length>1){
						aux =  col.cells[0].innerText;
						if(aux != ""){
							col.cells[0].innerText = i;					
						}
					}
					
				}
				
			}	
		
			
			function agregar() {
				
				if (contarChecks($('#tblRespoDisponible').dataTable()) > 0) {
					
					var indRelacion = '1';
					
					prepararAsociacion(indRelacion);
					
				} else{ 
					// ini: miguel ochoa - add excepciones
					var oTable = $('#tblRespoDisponible').dataTable();
					if(oTable.fnGetData().length > 0){	
						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion07);
					}
					// fin: miguel ochoa - add excepciones
				}
				
			}
			
			function quitar() {
				
				if (contarChecks($('#tblRespoExpediente').dataTable()) > 0) {
					
					var indRelacion = '0';
					
					prepararAsociacion(indRelacion);
					
					if (indOrigen) {
						
						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion09);
						
					}
					
				} else{ 
					// ini: miguel ochoa - add excepciones
					var oTable = $('#tblRespoExpediente').dataTable();
					if(oTable.fnGetData().length > 0){	
						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion08);
					}
					// fin: miguel ochoa - add excepciones
					
				}
				
			}
			
			function asignar() {
				
				prepararDataFinal($('#tblRespoExpediente').dataTable());
				
				if (!validarAsignacion()) {
					
					var botones = [];
					
					var aceptar = $("<input/>").attr(
						{
							value: "Aceptar", 
							type: "button", 
							"class": "btn btn-primary dlgButton", 
							"data-dismiss" : "modal", 
							onclick : "efectuarAsignacion()"
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
					
				} else {
					
					mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion10);
					
				}
				
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
			
			function prepararAsociacion(indRelacion) {
				
				var oTableI;
				var oTableF;
				
				var listaActual = [];
				var listaEnvio = [];
				var listaQueda = [];
				
				var nroFilasRecepcion = 0;
				var nroFilasEnvio = 0;
				
				indOrigen = false;
				
				listaActual = dataFinal;
				
				if (indRelacion === '1') {
					
					oTableI = $('#tblRespoDisponible').dataTable();
					oTableF = $('#tblRespoExpediente').dataTable();
					
				} else if (indRelacion === '0') {
					
					oTableI = $('#tblRespoExpediente').dataTable();
					oTableF = $('#tblRespoDisponible').dataTable();
					
				}
				
				nroFilasRecepcion = oTableF.fnGetData().length;
				
				var filas = oTableI.$("tr",{"page": "all"});
				var lista = oTableI.fnGetData();
				var bChecked = false;
				
				var indicador = 0;
				
				$.each(lista, function(i, registro) {
					
					bChecked = $(filas[i]).find(".bChecked").is(":checked");
					
					if (bChecked) {
						
						nroFilasRecepcion++;
						registro.indSeleccion = false;
						
						if (indRelacion === '1') {
							
							registro.indResponsable = '2';
							
							listaActual.push(registro);
							
						} else if (indRelacion === '0') {
							
							if (registro.indResponsable === '1') {
								
								indOrigen = true;
								
							} else {
								
								i = i - indicador;
								
								var elementoRemovido = listaActual.splice(i, 1);
								
								indicador++;
								
							}
							
						}
						
						registro.numOrden = nroFilasRecepcion;
						listaEnvio.push(registro);
						
					} else {
						
						nroFilasEnvio++;
						
						registro.numOrden = nroFilasEnvio;
						listaQueda.push(registro);
						
					}
					
				});
				
				if (!indOrigen) { // Validación si no se ha quitado el responsable que genero el expediente
					
					dataFinal = listaActual;
					
					oTableF.fnAddData(listaEnvio);
					oTableF.fnDraw();
					
					oTableI.fnClearTable();
					
					if (listaQueda.length > 0) {
						
						oTableI.fnAddData(listaQueda);
						
					}
					
					oTableI.fnDraw();
					var valFiltro = $.trim($('#txtBusqueda').val());
					if(valFiltro !== ''){
						filtrar();
					}
				}
				
			}
			
			function contarChecks(oTable){
				
				var filas = oTable.$("tr",{"page": "all"});
				var lista = oTable.fnGetData();
				var bChecked = false;
				var count = 0;
				
				$.each(lista, function(i, registro) {
					
					bChecked = $(filas[i]).find(".bChecked").is(":checked");
					
					if (bChecked) {
						count++;
					}
					
				});
				
				return count;
				
			}
			
			function prepararDataFinal(oTable) {
				
				var filas = oTable.$("tr",{"page": "all"});
				var lista = oTable.fnGetData();
				var bChecked = false;
				var count = 0;
				
				$.each(lista, function(i, registro) {
					
					vChecked = $(filas[i]).find(".vChecked").val();
					
					if (vChecked === '1') {
						
						registro.indResponsable = '1';
						
					} else {
						
						registro.indResponsable = '2';
						
					}
					
				});
				
			}
			
			function validarAsignacion(){
				
				var listaInicial = [];
				var listaFinal = [];
				
				$.each(dataAsociada, function(i, registro) {
					
					registro.numOrden = '';
					listaInicial.push(registro);
					
				});
				
				$.each(dataFinal, function(i, registro) {
					
					registro.numOrden = '';
					listaFinal.push(registro);
					
				});
				
				if (listaInicial.length != listaFinal.length) {
					
					return false;
					
				}
				
				var verifica = true;
				
				for (var i = 0; i < listaInicial.length; i++) {
					
					var codColaboradorInicial = listaInicial[i].codColaborador;
					var indResponsableInicial = listaInicial[i].indResponsable;
					
					for (var f = 0; f < listaFinal.length; f++) {
						
						var codColaboradorFinal = listaFinal[f].codColaborador;
						var indResponsableFinal = listaFinal[f].indResponsable;
						
						if (codColaboradorInicial == codColaboradorFinal) {
							
							if (indResponsableInicial == indResponsableFinal) {
								
								verifica = true;
								break;
								
							} else {
								
								verifica = false;
								
							}
							
						} else {
							
							verifica = false;
							
						}
						
					}
					
					if (!verifica) {
						
						return false;
						
					}
					
				}
				
				for (var f = 0; f < listaFinal.length; f++) {
					
					var codColaboradorFinal = listaFinal[f].codColaborador;
					var indResponsableFinal = listaFinal[f].indResponsable;
					
					for (var i = 0; i < listaInicial.length; i++) {
						
						var codColaboradorInicial = listaInicial[i].codColaborador;
						var indResponsableInicial = listaInicial[i].indResponsable;
						
						if (codColaboradorFinal == codColaboradorInicial) {
							
							if (indResponsableFinal == indResponsableInicial) {
								
								verifica = true;
								break;
								
							} else {
								
								verifica = false;
								
							}
							
						} else {
							
							verifica = false;
							
						}
						
					}
					
					if (!verifica) {
						
						return false;
						
					}
					
				}
				
				return true;
				
			}
			
			function permitirNumeros(e){
				
				var keynum = window.event ? window.event.keyCode : e.which;
				
				if ((keynum == 8)) {
					
					return true;
					
				}
				
				return /\d/.test(String.fromCharCode(keynum));
				
			}
			
			function efectuarAsignacion() {
				
				var numExpedienteVirtual = expedienteVirtual.numExpedienteVirtual;
				
				var dataEnvio = new Object();
				
				dataEnvio.numExpedienteVirtual = numExpedienteVirtual;
				dataEnvio.listadoFinal = dataFinal;
				
				$.ajax({
					
					url : '${pageContext.request.contextPath}/asigRespExpe.htm?action=realizarAsignacionResponsablesExpediente',
					type : 'POST',
					async : true,
					dataType : "json",
					data : JSON.stringify(dataEnvio),
					contentType : "application/json",
					mimeType : "application/json",
					//timeout : 5000,
					success : function(response) {
						
						dataAsociada = response.responsablesAsignados;
						
						dataFinal = response.responsablesFinales;
						
						mostrarDlgInfo(titulos.tituloDefecto, avisos.aviso02);
						
						limpiarPagina ();
						
					},
					error : function(response) {
						
						mostrarPagError();
						
					}
					
				});
				
			}
			
			function limpiarPagina () {
				
				$('#txtBusqueda').val('');
				
				filtrar();
				
				$('#selTipoNumeroExpediente').val('');
				
				activarNumeroExpediente();
				
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
			
		</script>
		
	</body>
	
</html>