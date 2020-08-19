<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="es">
	
	<head>
		
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name = "viewport" content = "initial-scale = 1.0, user-scalable = no, width=device-width">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>ASOCIACI&Oacute;N DE TIPOS DE EXPEDIENTE POR PROCESO</title>
		
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
		</style>
		
	</head>
	
	<body>
		
		<div class="container">
			
			<div class="row">
				
				<div class="panel panel-primary">
					<div class="panel-heading centered">
						<h2 class="panel-title">
							<strong>ASOCIACI&Oacute;N DE TIPOS DE EXPEDIENTE POR PROCESO</strong>
						</h2>
						<form id="frmPrincipal" class="form-horizontal" role="form">
						</form>
					</div>
				</div>
				
				<div class="panel panel-primary">
					<div class="marginedDiv">
						<div class="panel-body">
							<div class="row content-box">
								<div class="col-md-1">
									<label>Proceso: </label>
								</div>
								<div id="pnlSelProceso" class="col-md-5">
									<select id="selProceso" name="selProceso" class="cboProceso form-control" onchange="cargarTiposExpedientes()">
										<option value="">Seleccione</option>
									</select> 
								</div>
								<div class="col-md-3">
									&nbsp;
								</div>
								<div class="col-md-1">
									<label>Fecha: </label>
								</div>
								<div class="col-md-2">
									<input id="txtFechaSistema" name="txtFechaSistema" type="text" class="form-control" readonly="readonly"/> 
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
								<div class="col-md-7">
									&nbsp;
								</div>
							</div>
							<div class="row content-box">
								&nbsp;
							</div>
							<div class="row content-box">
								<div class="col-md-5 centered">
									<label>Tipos de Expedientes Disponibles</label>
								</div>
								<div class="col-md-2">
									&nbsp;
								</div>
								<div class="col-md-5 centered">
									<label>Tipos de Expedientes Asociados al Proceso</label>
								</div>
							</div>
							<div class="row content-box">
								<div class="col-md-5 centered">
								<div style="max-height: 250px; overflow-y: auto; border: 1px solid #337ab7;"> 
									<table id="tblTipoExpeDisponible" class="table display">
										<thead>
											<tr class="active">
												<th width="15%">N&deg;</th>
												<th width="15%">Sel</th>
												<th width="70%">Tipo de Expediente</th>
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
								 <div  style="max-height: 250px; overflow-y: auto; border: 1px solid #337ab7;"> 
									<table id="tblTipoExpeProceso" class="table display">
										<thead>
											<tr class="active">
												<th width="15%">N&deg;</th>
												<th width="15%">Sel</th>
												<th width="70%">Tipo de Expediente</th>
												<th width="0%" style="display:none">cod tipo de Expediente</th>
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
				
				$('#tblTipoExpeDisponible').DataTable({
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
						{data : "desTipoExpediente", sClass: 'centered alinearCentrado'}
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
				
				$('#tblTipoExpeProceso').DataTable({
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
						{data : "desTipoExpediente", sClass: 'centered alinearCentrado'}						
					],
					//"sScrollY"		:	"250px", codTipoExpediente
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
				
				$('#tblTipoExpeDisponible').dataTable().fnClearTable();
				$('#tblTipoExpeDisponible').dataTable().fnDraw();
				
				$('#tblTipoExpeProceso').dataTable().fnClearTable();
				$('#tblTipoExpeProceso').dataTable().fnDraw();
				
			}
			
			function cargarTiposExpedientes() {
				
				var codProceso = $('#selProceso').val();
				
				var dataEnvio = new Object();
				
				if (codProceso == "") {
					
					$('#tblTipoExpeProceso').dataTable().fnClearTable();
					$('#tblTipoExpeProceso').dataTable().fnDraw();
					
					$('#tblTipoExpeDisponible').dataTable().fnClearTable();
					$('#tblTipoExpeDisponible').dataTable().fnDraw();
					
					dataDisponible = [];
					dataAsociada = [];
					dataFinal = [];
					
				} else {
					
					dataEnvio.codProceso = codProceso;
					
					$.ajax({
						
						url : '${pageContext.request.contextPath}/asocTipoExpe.htm?action=cargarAsociacionTipoExpedienteProceso&indCarga=1',
						type : 'POST',
						async : true,
						dataType : "json",
						data : JSON.stringify(dataEnvio),
						contentType : "application/json",
						mimeType : "application/json",
						timeout : 5000,
						success : function(response) {
							
							dataDisponible = response.listadoTiposExpedientesDisponibles;
							dataAsociada = response.listadoTiposExpedientesAsociados;
							
							dataFinal = response.listadoTiposExpedientesFinales;
							
							if (dataDisponible.length > 0) {
								
								$('#tblTipoExpeDisponible').dataTable().fnClearTable();
								$('#tblTipoExpeDisponible').dataTable().fnAddData(dataDisponible);
								$('#tblTipoExpeDisponible').dataTable().fnDraw();
								
							} else {
								
								$('#tblTipoExpeDisponible').dataTable().fnClearTable();
								$('#tblTipoExpeDisponible').dataTable().fnDraw();
								
							}
							
							if (dataAsociada.length > 0) {
								
								$('#tblTipoExpeProceso').dataTable().fnClearTable();
								$('#tblTipoExpeProceso').dataTable().fnAddData(dataAsociada);
								$('#tblTipoExpeProceso').dataTable().fnDraw();
								
							} else {
								
								$('#tblTipoExpeProceso').dataTable().fnClearTable();
								$('#tblTipoExpeProceso').dataTable().fnDraw();
								
							}
							filtrar();
							
						},
						error : function(response) {
							
							mostrarPagError();
							
						}
						
					});
					
				}
				
			}
			
			function filtrar() {
				
				var critBusqueda = $('#txtBusqueda').val();
				
				var oTable = $('#tblTipoExpeDisponible').dataTable();
				
				oTable.fnFilter(critBusqueda, 2, true, false);
				//ini 10-12-15 :miguel ochoa actualizando nro de orden
				oTable.fnDraw();
				actualizarNumeroOrden();
				//fin 10-12-15 :miguel ochoa actualizando nro de orden
			}
			
			function actualizarNumeroOrden(){
				var fila = $('#tblTipoExpeDisponible tr')
				var aux;
				var col;
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
				
				if (contarChecks($('#tblTipoExpeDisponible').dataTable()) > 0) {
					
					var indRelacion = '1';
					
					prepararAsociacion(indRelacion);
					
				} else{ 
					
					// ini: miguel ochoa - add excepciones
					var oTable = $('#tblTipoExpeDisponible').dataTable();
					if(oTable.fnGetData().length > 0){	
						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion01);
					}
// 					else{
// 						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion04);
// 					}
					// fin: miguel ochoa - add excepciones
					
				}
				
			}
			
			/*verificar si puede quitar el docutmenot*/
			function verificarQuitar(arg){
			var rpta = true;
			$.ajax({
					
					url : '${pageContext.request.contextPath}/asocTipoExpe.htm?action=validarQuitarTipoExpediente&codtipexp='+arg,
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
				
				if (contarChecks($('#tblTipoExpeProceso').dataTable()) > 0) {
					//validar si el tipo de expediente no tiene documentos asociados
					// buscamos el id en la tb dataAsociada
					var oTable = $('#tblTipoExpeProceso').dataTable();
					var filas = oTable.$("tr",{"page": "all"});
					var lista = oTable.fnGetData();
					var bChecked = false;					
					var bexisteEnAsociados= false;					
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
							desc = $(filas[i]).find(".centered");
							array[j] = desc[2].innerHTML;
							j++;
						}
					
					});
					// buscamos el codigo en la tabla dataAsociada, para luego consultar si tiene pendientes, de se positivo, se muestra excepcion							
					for(j=0;j<array.length ;j++){
						// obtenmos el valor del array
						desc = array[j];
						for(k=0;k<dataAsociada.length;k++ ){
							descaux = dataAsociada[k].desTipoExpediente;
							bexisteEnAsociados=false;
							if(desc==descaux){
								// si son iguales obtenemos el codigo y realizamos la verificacion
								bexisteEnAsociados=true;
								codtipex = dataAsociada[k].codTipoExpediente;
								if(verificarQuitar(codtipex)){
									var indRelacion = '0';				
									prepararAsociacion(indRelacion);
								}else{
									mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion06);
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
					var oTable = $('#tblTipoExpeProceso').dataTable();
					if(oTable.fnGetData().length > 0)
						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion02);
// 					}else{
// 						//mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion05);
// 					}
					// fin: miguel ochoa - add excepciones
					
				}
				
			}
			
			function asociar() {
				
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
					
					mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion03);
					
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
					
					oTableI = $('#tblTipoExpeDisponible').dataTable();
					oTableF = $('#tblTipoExpeProceso').dataTable();
					
				} else if (indRelacion === '0') {
					
					oTableI = $('#tblTipoExpeProceso').dataTable();
					oTableF = $('#tblTipoExpeDisponible').dataTable();
					
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
				
				oTableF.fnAddData(listaEnvio);
				oTableF.fnDraw();
				
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
			
			function limpiarPagina () {
				
				$('#txtBusqueda').val('');
				
				filtrar();
				
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
			
			function mostrarPagError() {
				
				var formURL = '${pageContext.request.contextPath}/error.htm?action=cargarPaginaError';
				document.forms.frmPrincipal.action = formURL;
				document.forms.frmPrincipal.method = "post";
				document.forms.frmPrincipal.submit();
				
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
					
					var codTipoExpedienteInicial = listaInicial[i].codTipoExpediente;
					
					for (var f = 0; f < listaFinal.length; f++) {
						
						var codTipoExpedienteFinal = listaFinal[f].codTipoExpediente;
						
						if (codTipoExpedienteInicial == codTipoExpedienteFinal) {
							
							verifica = true;
							break;
							
						} else {
							
							verifica = false;
							
						}
						
					}
					
					if (!verifica) {
						
						return false;
						
					}
					
				}
				
				for (var f = 0; f < listaFinal.length; f++) {
					
					var codTipoExpedienteFinal = listaFinal[f].codTipoExpediente;
					
					for (var i = 0; i < listaInicial.length; i++) {
						
						var codTipoExpedienteInicial = listaInicial[i].codTipoExpediente;
						
						if (codTipoExpedienteFinal == codTipoExpedienteInicial) {
							
							verifica = true;
							break;
							
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
				
				var codProceso = $('#selProceso').val();
				
				var dataEnvio = new Object();
				
				dataEnvio.codProceso = codProceso;
				dataEnvio.listadoFinal = dataFinal;
				
				$.ajax({
					
					url : '${pageContext.request.contextPath}/asocTipoExpe.htm?action=realizarAsociacionTipoExpedienteProceso',
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
						
						mostrarDlgInfo(titulos.tituloDefecto, avisos.aviso02);
						
						limpiarPagina ();
						
					},
					error : function(response) {
						
						mostrarPagError();
						
					}
					
				});
				
			}
			
		</script>
		
	</body>
	
</html>