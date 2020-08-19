<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="es">
	
	<head>
		
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name = "viewport" content = "initial-scale = 1.0, user-scalable = no, width=device-width">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>ASOCIACI&Oacute;N DE TIPOS DE DOCUMENTOS POR TIPO DE EXPEDIENTE</title>
		
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
			.marginedForm {
				margin: 15px 0px !important;
			}
			.hiddenDiv {
				display: none !important;
			}
			th {
				border: 1px solid gray;
				border-collapse: collapse;
				background: Gainsboro !important;
			}
			#tblSectoristas tr:hover{
				background: CornflowerBlue !important;
			}
			#tblSectoristas tr {
			 cursor: pointer;
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
			.form-control {
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
							<strong>ASOCIACI&Oacute;N DE TIPOS DE DOCUMENTOS POR TIPO DE EXPEDIENTE</strong>
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
									<label>Proceso: </label>
								</div>
								<div id="pnlSelProceso" class="col-md-5">
									<select id="selProceso" name="selProceso" class="cboProceso form-control" onchange="cargarTiposExpedientes()">
										<option value="">Seleccione</option>
									</select> 
								</div>
								<div class="col-md-2">
									&nbsp;
								</div>
								<div class="col-md-1">
									<label>Fecha: </label>
								</div>
								<div class="col-md-2">
									<input id="txtFechaSistema" name="txtFechaSistema" type="text" class="form-control" readonly="readonly"/> 
								</div>
							</div>
							<div class="row content-box">
								&nbsp;
							</div>
							<div class="row content-box">
								<div class="col-md-2">
									<label>Tipo de Expediente: </label>
								</div>
								<div id="pnlSelTipoExpediente" class="col-md-5">
									<select id="selTipoExpediente" name="selTipoExpediente" class="cboTipoExpediente form-control" onchange="cargarTiposDocumentos()">
										<option value="">Seleccione</option>
									</select> 
								</div>
								<div class="col-md-5">
									&nbsp;
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
									<input id="txtBusqueda" name="txtBusqueda" type="text" class="form-control" onkeyup="filtrar()"/> 
								</div>
								<div class="col-md-2">
									&nbsp;
								</div>
								<div class="col-md-1">
									<label>Buscar: </label>
								</div>
								<div class="col-md-4">
									<input id="txtBusquedas" name="txtBusquedas" type="text" class="form-control" onkeyup="filtras()"/> 
								</div>
							</div>
							<div class="row content-box">
								&nbsp;
							</div>
							<div class="row content-box">
								<div class="col-md-5 centered">
									<label>Tipos de Documentos Disponibles</label>
								</div>
								<div class="col-md-2">
									&nbsp;
								</div>
								<div class="col-md-5 centered">
									<label>Tipos de Documentos Asociados al Tipo de Expediente</label>
								</div>
							</div>
							<div class="row content-box">
								<div class="col-md-5 centered">
									 <div style="max-height: 250px; overflow-y: auto; border: 1px solid #337ab7;"> 
										<table id="tblTipoDocuDisponible" class="table display" >
										<thead>
											<tr class="active">
												<th width="20%">N&deg;</th>
												<th width="20%">Sel</th>
												<th width="60%">Tipo de Documento</th>
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
										<table id="tblTipoDocuTipoExpediente"  class="table display" >
										<thead>
											<tr class="active">
												<th width="10%">N&deg;</th>
												<th width="10%">Sel</th>
												<th width="40%">Tipo de Documento</th>
												<th width="10%">Visible al Contribuyente</th>
												<th width="30%">Finalidad</th>
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
											<input id="btnAsociar" type="button" class="btn btn-primary" onclick="asociar()" value="Guardar Cambios" />
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
		
		<script>
			
			var titulos = ${titulos};
			var excepciones = ${excepciones};
			var avisos = ${avisos};
			var fecVista = ${fecVista};
			var listadoProcesos = ${listadoProcesos};
			
			var dataDisponible = [];
			var dataAsociada = [];
			
			var dataFinal = [];
			
			$(document).ready(function() {
				
				$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
				
				inicializarProcesos();
				
				inicializarTablas();
				
			});
			
			function inicializarProcesos() {
				
				var $element = $('#selProceso');
				
				$.each(listadoProcesos, function(i, dato) {
				
					var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
					$element.append($option);
					
				});
				
				$('#txtFechaSistema').attr("value", fecVista);
				
			}
			
			function inicializarTablas() {
				
				$('#tblTipoDocuDisponible').DataTable({
					"language": {
						"url"		:	"a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
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
								return jQuery('<input>').css({"marginLeft": "8px", "width": "18px", "height": "18px"}
								).attr(
									{
										type:'checkbox', 
										checked: data.toString()==='true' ? true : false, 
										"class" : "bChecked"
									}
								).wrap('<div></div>').parent().html();
							}
						},
						{data : "desTipoDocumentoCompuesto", sClass: 'izquierda alinearCentrado'}
					],
					ordering		:	false,
					bStateSave		:	false,
					info			:	false,
					paging			:	false,
					responsive		:	false
				} );	


				var myDataTable = $('#tblTipoDocuTipoExpediente').DataTable({
					"language": {
						"url"		:	"a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
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
								return jQuery('<input>').css({"marginLeft": "8px", "width": "18px", "height": "18px"}
								).attr(
									{
										type:'checkbox', 
										checked: data.toString()==='true' ? true : false, 
										"class" : "bChecked"
									}
								).wrap('<div></div>').parent().html();
							}
						},
						{data : "desTipoDocumentoCompuesto", sClass: 'izquierda alinearCentrado'},
						{data : "indVisibleContribuyente", sClass: 'izquierda alinearCentrado',
							render : function(data, row){								
								return jQuery('<select>').css(
									{
										"marginLeft"	:	"1px", 
										"width"			:	"50px", 
										"height"		:	"18px"
									}
								)
								.append(
									jQuery('<option>')
									.attr(
										{
											value		:	'0',
											selected	:	data.toString()==='0' ? true : false
										}
									).text('No')
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
								.attr(
									{
										"class" : "vChecked"
									}
								)
								.wrap('<div></div>').parent().html();
								
							}
						},
						{data : "indTipDocumento", sClass: 'izquierda alinearCentrado',
							render : function(data, row){								
								return jQuery('<select>').css(
									{
										"marginLeft"	:	"1px", 
										"width"			:	"90px", 
										"height"		:	"18px"
									}
								)
								.append(
									jQuery('<option>')
									.attr(
										{
											value		:	'01',
											selected	:	data.toString()==='01' ? true : false
										}
									).text('Apertura')
								)
								.append(
									jQuery('<option>')
									.attr(
										{
											value		:	'02',
											selected	:	data.toString()==='02' ? true : false
										}
									).text('Cierre')
								)
								.append(
									jQuery('<option>')
									.attr(
										{
											value		:	'04',
											selected	:	data.toString()==='04' ? true : false
										}
									).text('Doc. Interno')
								)
								.append(
									jQuery('<option>')
									.attr(
										{
											value		:	'05',
											selected	:	data.toString()==='05' ? true : false
										}
									).text('Reapertura')
								)
								.attr(
									{
										"id"	: "cboTipoExpediente",
										"class" : "vSelTipoExpediente",
									}
								)
								.wrap('<div></div>').parent().html();
								
							}
						}
						
					],
					ordering		:	false,
					bStateSave		:	false,
					info			:	false,
					paging			:	false,
					responsive		:	false
				} );
				
				$('#tblTipoDocuDisponible').dataTable().fnClearTable();
				$('#tblTipoDocuDisponible').dataTable().fnDraw();
				
				$('#tblTipoDocuTipoExpediente').dataTable().fnClearTable();
				$('#tblTipoDocuTipoExpediente').dataTable().fnDraw();

				//EVENTO ONCHANGE CBOTIPOEXPEDIENTE				
				$('#tblTipoDocuTipoExpediente tbody').on('change', '#cboTipoExpediente', function () {
					var object =  $(this);				
					var indice = myDataTable.row($(this).closest("tr")).index(); 
					var rowData = $("#tblTipoDocuTipoExpediente").DataTable().row(indice).data();
					//[ATORRESCH 2017-05-08] AJAX CONTROL DE CAMBIOS
					var dataEnvio = new Object();
					dataEnvio.codTipoExpediente = $.trim($('#selTipoExpediente').val());
					dataEnvio.codTipoDocumento = $.trim(rowData.codTipoDocumento);
							
					$.ajax({							
						url : '${pageContext.request.contextPath}/asocTipoDocu.htm?action=cantidadTipoDocumentoRelacion',
						type : 'POST',
						async : true,
						dataType : "json",
						data : JSON.stringify(dataEnvio),
						contentType : "application/json",
						mimeType : "application/json",
						timeout : 5000,
						success : function(response) {							
							if(response.status === false){
								object.val(rowData.indTipDocumento);
	                            mostrarDlgInfo(titulos.tituloDefecto, response.message);
	                            return false;
	                        }
	                    },
						error : function(response) {							
							mostrarPagError();							
						}
					});
					//FIN AJAX
			    });
				
			}
			function fnValidate(d){
				alert(d);
			}
			function cargarTiposExpedientes() {
				
				var codProceso = $('#selProceso').val();
				
				var dataEnvio = new Object();
				
				$('#selTipoExpediente').empty();
				
				$('#selTipoExpediente').append($('<option>', {
					value: '',
					text: 'Seleccione'
				}));
				
				if (codProceso == "") {
					
					$('#selTipoExpediente').val('');
					cargarTiposDocumentos();
					
				} else {
					
					dataEnvio.codProceso = codProceso;
					
					$.ajax({
						
						url : '${pageContext.request.contextPath}/asocTipoDocu.htm?action=cargarAsociacionTipoDocumentoTipoExpediente&indCarga=1&indFiltro=0',
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
								
								mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion01);
								
							}
							
						},
						error : function(response) {
							
							mostrarPagError();
							
						}
						
					});
					
				}
				
			}
			
			function cargarTiposDocumentos() {
				
				var codTipoExpediente = $('#selTipoExpediente').val();
				
				var dataEnvio = new Object();
				
				if (codTipoExpediente == "") {
					
					$('#tblTipoDocuTipoExpediente').dataTable().fnClearTable();
					$('#tblTipoDocuTipoExpediente').dataTable().fnDraw();
					
					$('#tblTipoDocuDisponible').dataTable().fnClearTable();
					$('#tblTipoDocuDisponible').dataTable().fnDraw();
					
					dataDisponible = [];
					dataAsociada = [];
					
					dataFinal = [];
					
				} else {
					
					dataEnvio.codTipoExpediente = codTipoExpediente;
					
					$.ajax({
						
						url : '${pageContext.request.contextPath}/asocTipoDocu.htm?action=cargarAsociacionTipoDocumentoTipoExpediente&indCarga=1&indFiltro=1',
						type : 'POST',
						async : true,
						dataType : "json",
						data : JSON.stringify(dataEnvio),
						contentType : "application/json",
						mimeType : "application/json",
						timeout : 5000,
						success : function(response) {
							
							dataDisponible = response.listadoTiposDocumentosDisponibles;
							dataAsociada = response.listadoTiposDocumentosAsociados;
							
							dataFinal = response.listadoTiposDocumentoFinales;
							
							if (dataDisponible.length > 0) {
								
								$('#tblTipoDocuDisponible').dataTable().fnClearTable();
								$('#tblTipoDocuDisponible').dataTable().fnAddData(dataDisponible);
								$('#tblTipoDocuDisponible').dataTable().fnDraw();
								
							} else {
								
								$('#tblTipoDocuDisponible').dataTable().fnClearTable();
								$('#tblTipoDocuDisponible').dataTable().fnDraw();
								
							}
							
							if (dataFinal.length > 0) {
								
								$('#tblTipoDocuTipoExpediente').dataTable().fnClearTable();
								$('#tblTipoDocuTipoExpediente').dataTable().fnAddData(dataFinal);
								$('#tblTipoDocuTipoExpediente').dataTable().fnDraw();
							} else {
								
								$('#tblTipoDocuTipoExpediente').dataTable().fnClearTable();
								$('#tblTipoDocuTipoExpediente').dataTable().fnDraw();
								
							}
							filtrar();
							filtras();
							
						},
						error : function(response) {
							
							mostrarPagError();
							
						}
						
					});
					
				}
				
			}
			
			function filtrar() {
				$('#tblTipoDocuDisponible').DataTable().column(2).search($('#txtBusqueda').val(),true,false).draw();
				actualizarNumeroOrden($('#tblTipoDocuDisponible tr'));
			}
			function filtras() {
			 	$('#tblTipoDocuTipoExpediente').DataTable().search($('#txtBusquedas').val(),true, false).draw();
			 	actualizarNumeroOrden($('#tblTipoDocuTipoExpediente tr'));
			}
			
			function actualizarNumeroOrden(newObject){
				//var fila = $('#tblTipoDocuDisponible tr');
				var fila =newObject; 
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
				
				if (contarChecks($('#tblTipoDocuDisponible').dataTable()) > 0) {
					
					var indRelacion = '1';
					
					prepararAsociacion(indRelacion);
					
				} else{ 
					// ini: miguel ochoa - add excepciones
					var oTable = $('#tblTipoDocuDisponible').dataTable();
					if(oTable.fnGetData().length > 0){	
						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion02);
					}
					// fin: miguel ochoa - add excepciones
					
				}
				
			}
			
			
			/*verificar si puede quitar el documento seleccionado*/
			function verificarQuitar(arg){
			var cod_tipexp = $('#selTipoExpediente').val();
			var rpta = true;
			$.ajax({
					
					url : '${pageContext.request.contextPath}/asocTipoDocu.htm?action=validarQuitarTipodocumento&codtipdoc='+arg+'&codtipexp='+cod_tipexp,
					type : 'POST', //eaguilar 12 JUL
					async : false,
					dataType : "json",					
					contentType : "application/x-www-form-urlencoded", //eaguilar 12 JUL
					mimeType : "application/json",
					//timeout : 5000,
					success : function(response) {						
						rpta = response.respuesta;						
					},
					error : function(response) {						
						mostrarPagError();						
					}
					
				});
				
				return rpta;
			}
			
			
			
			function quitar() {
				
				if (contarChecks($('#tblTipoDocuTipoExpediente').dataTable()) > 0) {					
					//validar si el tipo de expediente no tiene documentos asociados
					// buscamos el id en la tb dataAsociada
					var oTable = $('#tblTipoDocuTipoExpediente').dataTable();
					var filas = oTable.$("tr",{"page": "all"});
					var lista = oTable.fnGetData();
					var bexisteEnAsociados= false;	
					var bChecked = false;					
					var j = 0;
					var k = 0;
					var desc;
					var descaux;
					var codtipex;
					var array = [];
					// generando arrray con los items marcados
					$.each(lista, function(i, registro) {
					
						bChecked = $(filas[i]).find(".bChecked").is(":checked");
						
						if (bChecked) {
							desc = $(filas[i]).find(".izquierda");							
							array[j] = desc[0].innerHTML;
							j++;
						}
					
					});
					// buscamos el codigo en la tabla dataAsociada, para luego consultar si tiene pendientes, de se positivo, se muestra excepcion							
					for(j=0;j<array.length ;j++){
						// obtenmos el valor del array
						bexisteEnAsociados=false;
						desc = array[j];
						
						for(k=0;k<dataAsociada.length;k++ ){													
							descaux = dataAsociada[k].desTipoDocumentoCompuesto;
							bexisteEnAsociados=true;
							if(desc==descaux){
								// si son iguales obtenemos el codigo y realizamos la verificacion
								codtipex = dataAsociada[k].codTipoDocumento;
								if(verificarQuitar(codtipex)){
									var indRelacion = '0';				
									prepararAsociacion(indRelacion);
								}else{
									mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion05);
									$(filas[k]).find(".bChecked").attr("checked", false);
									return;
								}
							}
						}
					}
					
					if(bexisteEnAsociados==false){
						var indRelacion = '0';				
						prepararAsociacion(indRelacion);
					}
					
				} else{ 
					// ini: miguel ochoa - add excepciones
					var oTable = $('#tblTipoDocuTipoExpediente').dataTable();
					if(oTable.fnGetData().length > 0){	
						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion03);
					}
					// fin: miguel ochoa - add excepciones
					
				}
				
			}
			
			function asociar() {
				$('#txtBusquedas').val('');
				filtras();
				prepararDataFinal($('#tblTipoDocuTipoExpediente').dataTable());
				
				if (!validarAsociacion()) {
					
					var botones = [];
					
					var aceptar = $("<input/>").attr(
						{
							value: "Aceptar", 
							type: "button", 
							"class": "btn btn-primary dlgButton", 
							"data-dismiss" : "modal", 
							onclick : "efectuarAsociacion()"
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
					
					mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion04);
					
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
				
				listaActual = dataFinal;
				
				if (indRelacion === '1') {
					
					oTableI = $('#tblTipoDocuDisponible').dataTable();
					oTableF = $('#tblTipoDocuTipoExpediente').dataTable();
					
				} else if (indRelacion === '0') {
					
					oTableI = $('#tblTipoDocuTipoExpediente').dataTable();
					oTableF = $('#tblTipoDocuDisponible').dataTable();
					
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
							
							registro.indVisibleContribuyente = '0';
							registro.indTipDocumento='04';
							listaActual.push(registro);
							
						} else if (indRelacion === '0') {
							
							i = i - indicador;
							
							var elementoRemovido = listaActual.splice(i, 1);
							
							indicador++;
							
						}
						
						registro.numOrden = nroFilasRecepcion;
						listaEnvio.push(registro);
						
					} else {
						
						nroFilasEnvio++;
						
						registro.numOrden = nroFilasEnvio;
						listaQueda.push(registro);
						
					}
					
				});
				
				dataFinal = listaActual;
				
				if(listaEnvio.length > 0) {
					oTableF.fnAddData(listaEnvio);
					oTableF.fnDraw();
				}	
				
				oTableI.fnClearTable();
				
				if (listaQueda.length > 0) {
					
					oTableI.fnAddData(listaQueda);
					
				}
				
				oTableI.fnDraw();
				
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
					registro.indVisibleContribuyente = $(filas[i]).find(".vChecked").val();
					registro.indTipDocumento = $(filas[i]).find(".vSelTipoExpediente").val();	
				});
				
			}
			
			function validarAsociacion(){				
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
					
					var codTipoDocumentoInicial = listaInicial[i].codTipoDocumento;
					var indVisibleContribuyenteInicial = listaInicial[i].indVisibleContribuyente;
					var indTipDocumentoInicial = listaInicial[i].indTipDocumento;

					for (var f = 0; f < listaFinal.length; f++) {
						
						var codTipoDocumentoFinal = listaFinal[f].codTipoDocumento;
						var indVisibleContribuyenteFinal = listaFinal[f].indVisibleContribuyente;
						var indTipDocumentoFinal = listaFinal[f].indTipDocumento;
						
						if (codTipoDocumentoInicial == codTipoDocumentoFinal) {							
							if (indVisibleContribuyenteInicial == indVisibleContribuyenteFinal  && indTipDocumentoFinal == indTipDocumentoInicial) {
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
					
					var codTipoDocumentoFinal = listaFinal[f].codTipoDocumento;
					var indVisibleContribuyenteFinal = listaFinal[f].indVisibleContribuyente;
					var indTipDocumentoFinal = listaFinal[f].indTipDocumento;
					
					for (var i = 0; i < listaInicial.length; i++) {
						
						var codTipoDocumentoInicial = listaInicial[i].codTipoDocumento;
						var indVisibleContribuyenteInicial = listaInicial[i].indVisibleContribuyente;
						var indTipDocumentoInicial = listaInicial[i].indTipDocumento;
						if (codTipoDocumentoFinal == codTipoDocumentoInicial) {
							
							if (indVisibleContribuyenteFinal == indVisibleContribuyenteInicial &&  indTipDocumentoInicial ==  indTipDocumentoFinal){
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
			
			function efectuarAsociacion() {
				
				var codTipoExpediente = $('#selTipoExpediente').val();
				var codProceso = $('#selProceso').val();
				
				var dataEnvio = new Object();
				
				dataEnvio.codTipoExpediente = codTipoExpediente;
				dataEnvio.codProceso = codProceso;
				dataEnvio.listadoFinal = dataFinal;
				
				$.ajax({					
					url : '${pageContext.request.contextPath}/asocTipoDocu.htm?action=realizarAsociacionTipoDocumentoTipoExpediente',
					type : 'POST',
					async : true,
					dataType : "json",
					data : JSON.stringify(dataEnvio),
					contentType : "application/json",
					mimeType : "application/json",
					timeout : 5000,
					success : function(response) {						
						dataAsociada = response.listadoTiposExpedientesAsociados;						
						dataFinal = response.listadoTiposExpedientesFinales;
						if(dataAsociada==null && dataFinal==null){
							mostrarDlgInfo(titulos.tituloDefecto, avisos.aviso03);
						}else{
							mostrarDlgInfo(titulos.tituloDefecto, avisos.aviso02);
						}
						limpiarPagina ();						
					},
					error : function(response) {						
						mostrarPagError();						
					}					
				});				
			}
			
			function limpiarPagina () {				
				$('#txtBusqueda').val('');
				$('#txtBusquedas').val('');
				filtrar();				
				filtras();
				$('#selProceso').val('');				
				cargarTiposExpedientes();				
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