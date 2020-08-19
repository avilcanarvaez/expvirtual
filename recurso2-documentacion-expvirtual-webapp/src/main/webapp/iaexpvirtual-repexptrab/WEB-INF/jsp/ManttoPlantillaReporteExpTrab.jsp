<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="es">

	<head>
		
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=EDGE" />
		<meta name = "viewport" content = "initial-scale = 1.0, user-scalable = no,  width=device-width">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>PLANTILLAS DE REPORTES DE EXPEDIENTES DE TRABAJO</title>
		
		<!-- Bootstrap -->
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/css/bootstrap-theme.min.css">
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/media/css/jquery.dataTables.css">
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Responsive/css/dataTables.responsive.css">
		<link rel="stylesheet" type="text/css" href="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/media/css/jquery.dataTables_themeroller.css">
		<link rel="stylesheet" type="text/css" href="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Scroller/css/dataTables.scroller.css">
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/css/bootstrap-datetimepicker.min.css"/>
		
		
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
			min-width: 1000px; 
			max-width: 1500px; 
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
		  margin: 10px 10px !important;
		  border: 1px solid black !important;
		  border-radius: 4px;
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
		
		.padded-row{
			padding: 10px !important;
		}
		
		.panelBorde {
			margin-bottom: 10px;
			background-color: #fff;
			border: 1px solid transparent;
			border-radius: 4px;
			-webkit-box-shadow: 0 1px 1px rgba(0,0,0,.05);
			box-shadow: 0 1px 1px rgba(0,0,0,.05);
		}
		
		.marginedSub {
		  margin: 10px 10px !important;
		  border-radius: 4px;
		}
		
		div.border-line {
		  border: 1px #337ab7;
		  border-style: solid;
		  padding:6px !important;
		}
		
		div.border-lineSub {
		  border: 1px #337ab7;
		  border-style: solid;
		  padding:15px !important;
		}
		
		div.border-padding {
		  padding:15px !important;
		  margin-top: -15px;
		}
		
		.dlgButton {
				border-color: gray;
				margin-right: 7px;
			}
			
		.panel-scroll {
		max-height: auto;
		min-height: 0px;
		overflow-y: auto;
		overflow-x: auto;
		}	
		</style>
		
	</head>
	
	<body>
		<div id="container" class="container" style="width: 95%">
			
					<div class="panel panel-primary border-line">
						<div class="panel-heading align="center">
							<h3 class="panel-title" align="center">PLANTILLAS DE REPORTES DE EXPEDIENTES DE TRABAJO</h3>
						</div>
					</div>
					<div class="panel panel-primary border-lineSub">
					<form role="form" name="mantoPLantilla" id="mantoPLantilla" method="post">
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
							</div>
							<div class="col-md-1">
								<label>Fecha:</label>
							</div>
							<div id="pnlFecVista" class="col-md-2">
								<input id="fecVista" name="fecVista" type="text" class="form-control" disabled="disabled"/>
							</div>
						</div>
						<div class="row content-box">&nbsp;</div>
						<div class="row content-box">
							<div class="col-md-2">
								<label>Tipo expediente: </label>
							</div>
							<div id="pnlSelTipoExpe" class="col-md-5">
								<select id="selTipoExpediente" name="selTipoExpediente" class="cboProceso form-control">
									<option value="">Seleccione</option>
								</select> 
							</div>
						</div>
						<div class="row content-box">&nbsp;</div>
						<div class="row content-box">
							<div class="col-md-2">
								<label>Buscar Desde: </label>
							</div>
							<div id="pnlSelTipoExpe" class="col-md-2">
								<div>
									<div class='input-group date col-md-12' id='fechaDesde'>
										<input id="fechaDes" name="fechaDes" type='text' class="form-control" onkeypress="datoTipoFecha()" maxlength="10"  readonly= "readOnly" placeHolder="dd/mm/aaaa"/>
										<span class="input-group-addon">
											<span class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
								</div> 
							</div>
							<div class="col-md-1">
								<label>Hasta: </label>
							</div>
							<div id="pnlSelTipoExpe" class="col-md-2">
								<div>
									<div class='input-group date col-md-12' id='fechaHasta'>
										<input id="fechaHas" name="fechaHas" type='text' class="form-control" onkeypress="datoTipoFecha()" maxlength="10" readonly= "readOnly" placeHolder="dd/mm/aaaa"/>
										<span class="input-group-addon">
											<span class="glyphicon glyphicon-calendar"></span>
										</span>
									</div>
								</div>
							</div>
							<div class="col-md-5">
								<button type="button" class="btn btn-primary" onclick="cargarPlantillas()">Cargar Plantillas de Reportes</button>
							</div>
						</div>
						</form>
						<div class="row content-box">&nbsp;</div>
						<div class="panel panel-primary border-lineSub">
							<div class="row content-box">
								<div class="col-md-12">
									<label>Lista de Plantillas</label>
								</div>
							</div>
							<div class="row content-box border-padding">
								<form id="frmTable">
								<div class="tab-content">
								<div id="accionesFiscalizacion" class="tab-pane fade in active">
								
								<div class="border-line panel-scroll">																
									<table id="tablaPlantillas" class="table display nowrap" cellspacing="0" style="width: 100%;"><!--table table-striped table-bordered-->
										<thead>
											<tr class="active">
												<th width="3%" class="text-center">N°</th>
												<th width="5%" class="text-center">Sel</th>
												<th width="10%" class="text-center">N° Plantilla</th>
												<th width="28%" class="text-center">Descripci&oacute;n de la Plantilla</th>
												<th width="15%" class="text-center">Fecha Creaci&oacute;n Plantilla</th>
												<th width="7%" class="text-center">Estado Plantilla</th>
												<th width="32%" class="text-center">Usuario Creaci&oacute;n</th>
											</tr>
										</thead>
									</table>
								</div>
								
								</div>
								</div>
								</form>
							</div>
							<div class="row content-box">&nbsp;</div>
							<div class="row content-box">
								<div class="col-md-12">
									<button id="darBaja" type="button" class="btn btn-primary pull-right" onclick="darBajaPlantilla()">Dar de Baja la Plantilla</button>
									<button id="modPlant" type="button" class="btn btn-primary pull-right" onclick="modificarPlantilla()" style="margin-right: 5px;">Modificar Plantilla</button>
									<button type="button" class="btn btn-primary pull-right" onclick="crearPlantilla()" style="margin-right: 5px;">Crear Nueva Plantilla</button>
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
		<script src="../a/js/libs/jquery/1.11.2/jquery.min.js"></script>
		<script src="../a/js/libs/bootstrap/3.3.2/js/bootstrap.min.js"></script>
		<script src="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/media/js/jquery.dataTables.min.js" type="text/javascript" ></script>    
		<script src="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Responsive/js/dataTables.responsive.js" type="text/javascript" ></script>
		<script src="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/TableTools/js/dataTables.tableTools.min.js" type="text/javascript" ></script>
		<script src="../a/js/js.js" type="text/javascript" ></script>
		
		<script type="text/javascript" src="../a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/js/moment-with-locales.js"></script>
		<script type="text/javascript" src="../a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/js/bootstrap-datetimepicker.min.js"></script>
		<script src="../a/js/bootstrap/3.2.0/js/jquery.blockUI.js" type="text/javascript"></script>
		
		<script>
		
		var listadoProcesos = ${listadoProcesos};
		var fecVista = ${fecVista};
		var titulos = ${titulos};
		var excepciones = ${excepciones};
		var numPlantilla="";
		var tblPage = "${beanParametrosConsultaReq.tblPage}"; //anadir
		
		$(document).ready(function() {
			
			
			
			$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
			$.fn.datetimepicker.defaults.language = 'Es';
			$('#fechaDesde').datetimepicker({
				format: 'DD/MM/YYYY',
				locale: 'es',
				autoclose: true,
				forceParse: false,
				pickTime: false
			});
			$('#fechaHasta').datetimepicker({
				format: 'DD/MM/YYYY',
				locale: 'es',
				autoclose: true,
				forceParse: false,
				pickTime: false
			});
			
			
			//$.ajaxSetup({ scriptCharset: "UTF-8" , contentType: "application/json; charset=utf-8"});
				jQuery.support.cors = true;
				
				$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
				
				$('#tablaPlantillas').DataTable( {
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
						{data :	"numOrden"},
						{data :	"numPlantilla",
							render : function(data, row){
									return jQuery('<input>').css(
										{
											"marginLeft"	:	"8px", 
											"width"			:	"18px", 
											"height"		:	"18px"
										}
									).attr(
										{
											type:'radio', 
											"class" : "bChecked",
											name: "seleccion",
											id: data,
											value:data,
											display:'none',
											"onclick": 'setearNumPlantilla("'+data+'")' 
											
										}
									).wrap('<div></div>').parent().html();
								/*if(row.estadoPlantilla=="Inactivo"){
									
								}else{
									return jQuery('<input>')
									 	.css({"marginLeft": "8px", "width": "18px", "height": "18px",})
									 	.attr({type:'radio', name:'seleccion', value:data , display:'none'})
					                    .wrap('<div></div>')
					                    .parent()
					                    .html();
								}*/}	
						},
						{data :	"numPlantilla", render : function(data, row){
							 var aux = data;
							 return $('<a>')
							 	.css('text-decoration','underline')
				                .attr({href : '${pageContext.request.contextPath}/mantPlantRep.htm?action=verPlantillaView&numPlantilla=' + aux, onclick : "verDetallePlantilla('"+aux+"')" })
				                .text(data)
				                .wrap('<div></div>')
				                .parent()
				                .html();
								
				        }},
						{data :	"desPlantilla" },
						{data :	"fechaRegistro" },
						{data :	"estadoPlantilla" },
						{data :	"usuRegistro" }
					],
					
					tableTools: {
				        "sSwfPath": "../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/TableTools/swf/copy_csv_xls.swf"
				    },
										
					autoWidth			:   false,
					ordering			:	true,
					bScrollAutoCss		:	true,
					bStateSave			:	false,
					bAutoWidth			:	false,
					bScrollCollapse		:	true,
					searching			:	false,
					paging				:	true,
					pagingType			:   "full_numbers",
					iDisplayLength		:	10,
					//responsive			:	true,
					bLengthChange		: 	false,
					fnDrawCallback		:	function(oSettings) {
						
						$('#tablaPlantillas_paginate').addClass('div100');
						habilitarRadio();
						/*if (oSettings.fnRecordsTotal() == 0) {
							$('#tablaPlantillas_paginate').addClass('hiddenDiv'); //el footer de paginacion siempre tiene como ID "miTablaID_paginate"
																				//no se oculta automaticamente
																	//cuando hay 0 registros, por eso se oculta anadiendo el class .hiddenDiv
				        }
						else {
							$('#tablaPlantillas_paginate').removeClass('hiddenDiv');
							
						}*/
				    }
					        
			});
			
			var table = $('#tablaPlantillas').DataTable();
			$('#tablaPlantillas').on( 'page.dt', function () {
			var info = table.page.info();
			$('input').filter(':radio').prop('checked',false);
			} );
			
			var $element = $('#selProceso');
			
			$.each(listadoProcesos, function(i, dato) {
			
				var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
				$element.append($option);
				
			});
			
			$('#fecVista').val(fecVista);
			$('#darBaja').attr('disabled', true);
			$('#modPlant').attr('disabled', true);
			cargarFormulario();
		});
		
		function getPaginaTbl(idTabla){
			return $('#'+idTabla).DataTable().page.info().page;
		}
				
		function setPagintaTbl(idTabla, idxPagina){
			$('#'+idTabla).DataTable().page(idxPagina).draw(false);
		}
		
		function cargarFormulario(){
	    	
	    	var realizarBusqueda = '${beanParametrosConsultaReq.realizarBusqueda}';
	    	
	    	if(realizarBusqueda=="1"){
				var numExp = '${beanParametrosConsultaReq.numExp}';
				
				var proceso = '${beanParametrosConsultaReq.selProceso}';
				$('#selProceso').val('${beanParametrosConsultaReq.selProceso}');
				cargarTiposExpedientes();
				$('#selTipoExpediente').val('${beanParametrosConsultaReq.selTipoExpediente}');
				$('#fechaDes').val('${beanParametrosConsultaReq.fechaDes}');
				$('#fechaHas').val('${beanParametrosConsultaReq.fechaHas}');
			
				if(proceso != ""){
					cargarPlantillas();
				}
				
	    	}
	    }
		function habilitarRadio(){
			var dataJson = $('#tablaPlantillas').DataTable().rows().data();	
			for (var i = 0; i < dataJson.length; i++) {
				if(dataJson[i].estadoPlantilla=="Inactivo"){
					//document.getElementById(dataJson[i].numPlantilla).style.display='none';
					if($('#'+dataJson[i].numPlantilla).length){
						document.getElementById(dataJson[i].numPlantilla).disabled='disabled';
					}
					
				}
			}
		}

		function setearNumPlantilla(numPlan){

			numPlantilla = numPlan;
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
				
			} else {
				
				dataEnvio.codProceso = codProceso;
				
				$.ajax({
					
					url : '${pageContext.request.contextPath}/mantPlantRep.htm?action=cargarBandPlanExpe&indCarga=1',
					type : 'POST',
					async : false,
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
														
						} else {
							
							mostrarDlgInfo(titulos.tituloDefecto, "No existe Tipos de Expedientes asocioados al proceso seleccionado.");
							$('#selProceso').val("");
							
						}
						
					},
					error : function(response) {
						
						mostrarPagError();
						
					}
					
				});
				
			}
			
		}
		
		function mostrarDlgInfo(titulo, msj){ 
			
			botones = [];
			
			var aceptar = $("<input/>").attr(
				{
					value: "Aceptar", 
					type: "button", 
					"class": "btn dlgButton btn-primary", 
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
		
		function cargarPlantillas() {
			
			var codProceso = $('#selProceso').val();
			var codTipoExp = $('#selTipoExpediente').val();
			var fechaDesde = document.getElementById("fechaDes").value;
			var fechaHasta = document.getElementById("fechaHas").value;
			var dataEnvio = new Object();
			
			if(codProceso == "" && fechaDesde == "" && fechaHasta == ""){
				
				mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion4);
				
			}else{
				if(codProceso == ""){
					
					mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion2);
					
				}else{
					
					if(codTipoExp == ""){
						
						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion3);
						
					}else{
						
						if(fechaDesde == "" || fechaHasta == ""){
							
							dataEnvio.codProceso = codProceso;
							dataEnvio.codTipoExp = codTipoExp;
							
							$.ajax({
								
								url : '${pageContext.request.contextPath}/mantPlantRep.htm?action=cargarListPlantillas&indCarga=0&indFiltro=0',
								type : 'POST',
								async : true,
								dataType : "json",
								data : JSON.stringify(dataEnvio),
								contentType : "application/json",
								mimeType : "application/json",
								timeout : 5000,
								success : function(response) {
									
									var listPlantillas = response.listPlantillas
									
									if (listPlantillas.length > 0) {
										$('#tablaPlantillas').dataTable().fnClearTable();
										$('#tablaPlantillas').dataTable().fnAddData(listPlantillas); //carga registros en grilla
										$('#darBaja').attr('disabled', false);
										$('#modPlant').attr('disabled', false);
										
										if(tblPage != "") {
											setPagintaTbl("tablaPlantillas", parseInt(tblPage)); //anadir
											tblPage = "";
										}
										
									} else {
										
										mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion1);
										$('#tablaPlantillas').dataTable().fnClearTable();
										$('#darBaja').attr('disabled', true);
										$('#modPlant').attr('disabled', true);
									}
									
								},
								error : function(response) {
									
									mostrarPagError();
									
								}
							
							});
							
						}else{
							
							if(validarFormatoFecha(fechaDesde)){
								
								if(existeFecha(fechaDesde)){
									
									if(validarFormatoFecha(fechaHasta)){
										
										if(existeFecha(fechaHasta)){
											
											if(comparafecha(fechaDesde, fechaHasta)){
												
												dataEnvio.codProceso = codProceso;
												dataEnvio.codTipoExp = codTipoExp;
												dataEnvio.fechaDesde = fechaDesde;
												dataEnvio.fechaHasta = fechaHasta;
												
												$.ajax({
													
													url : '${pageContext.request.contextPath}/mantPlantRep.htm?action=cargarListPlantillas&indCarga=0&indFiltro=1',
													type : 'POST',
													async : true,
													dataType : "json",
													data : JSON.stringify(dataEnvio),
													contentType : "application/json",
													mimeType : "application/json",
													timeout : 5000,
													success : function(response) {
														
														var listPlantillas = response.listPlantillas
														
														if (listPlantillas.length > 0) {
															$('#tablaPlantillas').dataTable().fnClearTable();
															$('#tablaPlantillas').dataTable().fnAddData(listPlantillas); //carga registros en grilla
															$('#darBaja').attr('disabled', false);
															$('#modPlant').attr('disabled', false);
															habilitarRadio();
															
															if(tblPage != "") {
																setPagintaTbl("tablaPlantillas", parseInt(tblPage)); //anadir
																tblPage = "";
															}
															
														} else {
															
															mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion1);
															$('#tablaPlantillas').dataTable().fnClearTable();
															$('#darBaja').attr('disabled', true);
															$('#modPlant').attr('disabled', true);
														}
														
													},
													error : function(response) {
														
														mostrarPagError();
														
													}
												
												});
												
											}else{
												
												mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion5);
												
											}
											
										}else{
											
											mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion6);
											
										}
										
									}else{
										
										mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion6);
										
									}
									
								}else{
									
									mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion7);
									
								}
								
							}else{
								
								mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion7);
								
							}

							
						}
						
					}
					
				}
			}
			
		}
		
		function datoTipoFecha() {
			if (((event.keyCode < 48) || (event.keyCode > 57)) && (event.keyCode != 47)) {  
				event.returnValue = false;
			} 
		}
		
		function comparafecha(fecha1, fecha2) {
			
			dia = fecha1.substring(0, 2);
			mes = fecha1.substring(3, 5);
			anho = fecha1.substring(6, 10);
			fecha1x = anho + mes + dia;
			dia = fecha2.substring(0, 2);
			mes = fecha2.substring(3, 5);
			anho = fecha2.substring(6, 10);
			fecha2x = anho + mes + dia;
			if(fecha1x<=fecha2x){
				return true;
			}else{
				return false;
			}
		}
		
		function validarFormatoFecha(campo) {
			var RegExPattern = /^\d{1,2}\/\d{1,2}\/\d{2,4}$/;
			if ((campo.match(RegExPattern)) && (campo!='')) {
				return true;
			} else { 
				return false;
			}
		}
		
		function existeFecha(fecha){
			var bAux = false;
			var fechaf = fecha.split("/");       
			var day = fechaf[0];       
			var month = fechaf[1];       
			var year = fechaf[2];
			
			if(parseInt(day, 10)<1 || parseInt(day, 10)>31){
				bAux = false;
			}
			else if(parseInt(month, 10)<1 || parseInt(month, 10)>12){
				bAux = false;
			}
			else if(parseInt(year, 10)<1900 || parseInt(month, 10)>2115){
				bAux = false;
			}
			else{
				var date = new Date(year,month,'0');
				bAux = (day-0)>(date.getDate()-0) ? false : true;       
			}
			return bAux;
		}
		
		function crearPlantilla() {
			var  url = '${pageContext.request.contextPath}/mantPlantRep.htm?action=crearPlantillaView';
			$.ajax({
				url : '${pageContext.request.contextPath}/mantPlantRep.htm?action=cargarDatosBusquedaSession'+"&tblPage="+getPaginaTbl("tablaPlantillas"),
				type : 'POST',
				async : false,
				dataType : "json",
				data : $('#mantoPLantilla').serialize(),
				//contentType : "application/json",
				mimeType : "application/json",
				success : function(response) {
					
					$(location).prop( 'href', url);
					
				},
				error : function(response) {
					
					mostrarPagError();
				}
				
			});
			
		}
		
		function modificarPlantilla() {
			if(numPlantilla==""){
				mostrarDlgInfo(titulos.tituloDefecto, "Seleccione una Plantilla.");
				return;
			}
			var  url = '${pageContext.request.contextPath}/mantPlantRep.htm?action=modificaPlantillaView&numPlantilla='+numPlantilla;
			$.ajax({
				url : '${pageContext.request.contextPath}/mantPlantRep.htm?action=cargarDatosBusquedaSession'+"&tblPage="+getPaginaTbl("tablaPlantillas"),
				type : 'POST',
				async : false,
				dataType : "json",
				data : $('#mantoPLantilla').serialize(),
				//contentType : "application/json",
				mimeType : "application/json",
				success : function(response) {
					
					$(location).prop( 'href', url);
					
				},
				error : function(response) {
					
					mostrarPagError();
				}
				
			});
		}
		function verDetallePlantilla(numPlant) {
			var  url = '${pageContext.request.contextPath}/mantPlantRep.htm?action=verPlantillaView&numPlantilla='+numPlant;
			$.ajax({
				url : '${pageContext.request.contextPath}/mantPlantRep.htm?action=cargarDatosBusquedaSession'+"&tblPage="+getPaginaTbl("tablaPlantillas"),
				type : 'POST',
				async : false,
				dataType : "json",
				data : $('#mantoPLantilla').serialize(),
				//contentType : "application/json",
				mimeType : "application/json",
				success : function(response) {
					
					$(location).prop( 'href', url);
					
				},
				error : function(response) {
					
					mostrarPagError();
				}
				
			});
		}
		
		function darBajaPlantilla() {
			var valor_radio = $('#frmTable').find('input[name="seleccion"]:checked').val();
			if(valor_radio == undefined || valor_radio == "" || valor_radio == null){
				mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion8);
			}else{
				confirmarDarBaja(valor_radio)
			}
			
		}
		
		function mostrarPagError() {
			
			var formURL = '${pageContext.request.contextPath}/error.htm?action=cargarPaginaError';
			document.forms.frmPrincipal.action = formURL;
			document.forms.frmPrincipal.method = "post";
			document.forms.frmPrincipal.submit();
			
		}
		
		function confirmarDarBaja(numPlant){ 
			titulo = titulos.tituloDefecto;
			msj = "¿Está seguro de dar de baja al a la plantilla Nº "+numPlant+ "?";
			botones = [];
			
			var botonSi = $("<input/>").attr(
				{
					value: "Si", 
					type: "button", 
					"class": "btn dlgButton btn-primary", 
					"data-dismiss" : "modal", 
					onclick : "ejecutarDarBajaPlantilla('"+numPlant+"')"
				}
			);
			var botonNo = $("<input/>").attr(
				{
					value: "No", 
					type: "button", 
					"class": "btn dlgButton btn-primary", 
					"data-dismiss" : "modal", 
					onclick : "$('#modalDlg').modal('hide')"
				}
			);
			
			botones.push(botonSi);
			botones.push(botonNo);
			crearDlg(titulo, msj, botones);
		}
		function ejecutarDarBajaPlantilla(numPlant){
			
			var dataEnvio = new Object();
			dataEnvio.numPlant = numPlant;
												
			$.ajax({
				
				url : '${pageContext.request.contextPath}/mantPlantRep.htm?action=getBajaPlantilla&indCarga=0',
				type : 'POST',
				async : true,
				dataType : "json",
				data : JSON.stringify(dataEnvio),
				contentType : "application/json",
				mimeType : "application/json",
				timeout : 5000,
				success : function(response) {
					
					var flagAccion = response.flagAccion
					
					if (flagAccion == "0") {
						
						mostrarDlgConfirmar(titulos.tituloDefecto, "Se ha dado de baja a la plantilla Nº " + numPlant+".");
						
					} else {
						
						mostrarDlgInfo(titulos.tituloDefecto, "No se pudo realizar la baja de la plantilla Nº " + numPlant+".");
						
					}
					
				},
				error : function(response) {
					
					mostrarPagError();
					
				}
			
			});
		}
		function mostrarDlgConfirmar(titulo, msj){ 
			
			cargarPlantillas();
			botones = [];
			
			var aceptar = $("<input/>").attr(
				{
					value: "Aceptar", 
					type: "button", 
					"class": "btn dlgButton btn-primary", 
					"data-dismiss" : "modal", 
					onclick : "actulizaLista()"
				}
			);
			
			botones.push(aceptar);
			crearDlg(titulo, msj, botones);
			
		}
		function actulizaLista(){
		
			$('#modalDlg').modal('hide');
		
		} 
		
			
		function removerDatosBusquedaSession(){			
			//removemos los datos guardados en session
			$.ajax({
				
				url : '${pageContext.request.contextPath}/mantPlantRep.htm?action=removerDatosBusquedaSession',
				type : 'POST',
				async : false,
				dataType : "json",
				data : '',
				//contentType : "application/json",
				mimeType : "application/json",
				success : function(response) {
					
					limpiar();
					
				},
				error : function(response) {
					
					limpiar();
				}
				
			});
		}
		</script>
		
	</body>
	
</html>