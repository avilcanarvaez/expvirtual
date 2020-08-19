<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta charset="utf-8"/>
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=EDGE" />
<meta name="viewport" content="initial-scale = 1.0, user-scalable = no,  width=device-width"/>		
<title>IMPRIMIR REPORTE GENERAL</title>
<!-- Bootstrap -->
  <script src="/a/js/libs/jquery/1.11.2/jquery.min.js"></script> 
  
  <script src="/a/js/libs/bootstrap/3.3.2/js/bootstrap.min.js"></script>		
  <script src="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/media/js/jquery.dataTables.min.js" type="text/javascript" language="javascript" ></script>    
  <script src="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Responsive/js/dataTables.responsive.js" type="text/javascript" language="javascript"></script>
  <script src="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Scroller/js/dataTables.scroller.js" type="text/javascript" language="javascript" ></script>

  <link  href="/a/js/libs/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
  <link  href="/a/js/libs/bootstrap/3.3.2/css/bootstrap-theme.min.css" rel="stylesheet">
  <link  href="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/media/css/jquery.dataTables.css" type="text/css" rel="stylesheet" >
  <link  href="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Responsive/css/dataTables.responsive.css" type="text/css" rel="stylesheet" >
  <link  href="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/media/css/jquery.dataTables_themeroller.css" type="text/css" rel="stylesheet" >
  <link  href="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Scroller/css/dataTables.scroller.css" rel="stylesheet" type="text/css" >
  
  <link href="/a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/css/bootstrap-datetimepicker.min.css" 	rel="stylesheet" />  
  <!--<script src="/a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/js/moment.js" type="text/javascript"></script>-->
  <script src="/a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/js/moment-with-locales.js" type="text/javascript"></script>
  <script src="/a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
  <script src="/a/js/bootstrapvalidator/js/bootstrapValidator.min.js"></script> 	
  <script src="/a/js/libs/bootstrap/3.3.2/plugins/jquery.inputmask-3.1/dist/jquery.inputmask.bundle.min.js" type="text/javascript"></script>
  <script src="/a/js/js.js" type="text/javascript"></script>
  <script src="../a/js/bootstrap/3.2.0/js/jquery.blockUI.js" type="text/javascript"></script>
  <link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Responsive/css/dataTables.responsive.css">
   
<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/css/bootstrap-datetimepicker.min.css">
<script type="text/javascript" src="../a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/js/moment-with-locales.js"></script>
<script type="text/javascript" src="../a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/js/bootstrap-datetimepicker.min.js"></script>
  
	<!--[if lt IE 10]>
      <script src="/a/js/libs/bootstrap/3.3.2/plugins/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="/a/js/libs/bootstrap/3.3.2/plugins/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
	
		<style type="text/css">
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

.dataTables_wrapper .dataTables_paginate {
		float: initial !important;
		text-align: center !important;
		padding-top: 0.25em;
	}
.div100{
		width : 100% !important;
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
	
	.dataTables_paginate {
	padding-top: 0px !important;
	width : 100% !important;
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
	#tablaRepGeneral_wrapper {
		min-width: 850px !important;
	}
		</style>
	
</head>
<body>
</br>
<div id="container" class="container" style="width: 95%">
			<div class="row">
				<div class="panel panel-primary">
					<div class="panel-heading align="center">
						<h3 class="panel-title" align="center">IMPRESI&Oacute;N DE REPORTES GENERADOS</h3>
						<form id="frmPrincipal" class="form-horizontal" role="form">
						</form>
					</div>
				</div>	
				<div class="panel panel-primary border-line">
					<form class="form-horizontal" role="form" name="frmFiltroBusquedaAvanzada" id="frmFiltroBusquedaAvanzada" method="post">
					<fieldset class="scheduler-border" style="margin : 15px 15px !important">
						<div class="form-group"><div class="col-md-12"></div></div>							
							<div class="form-group">								
								<div class="col-md-2"><Strong>Proceso:</Strong></div>
								<div class="col-md-5">
									<select name="codProceso" id="codProceso" onchange="cargarTiposExpedientes()" class="form-control tamanoMaximo"> 
										<option value="">Seleccione</option>
									</select>
								</div>
								<div class="col-md-1"></div>
								<div class="col-md-2"><Strong>Fecha:</Strong></div>
								<div id="pnlFecVista" class="col-md-2">
									<input id="fecVista" name="fecVista" type="text" class="form-control text-center" disabled="disabled"/>
								</div>
							</div>
							<div class="form-group">	
								<div class="col-md-2"><Strong>Tipo Expediente:</Strong></div>
								<div class="col-md-5">
									<select name="codTipexp" id="codTipexp" class="form-control tamanoMaximo"> 
										<option value="">Seleccione</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"><Strong>Buscar Desde:</Strong></div>
								<div class="col-md-2">
									<div class='input-group date tamanoMaximo' id="divFechaDesde">
											<input type='text' class="form-control tamanoMaximo"  onkeypress="resetearFormularioConEsp()" id='txtfechaDesde' name='fecDesde' placeHolder="dd/mm/aaaa" maxlength="10" readonly="readonly"/> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
									</div>
								</div>
								<div class="col-md-1"><Strong>Hasta:</Strong></div>
								<div class="col-md-2">
									<div class='input-group date tamanoMaximo' id="divFechaHasta">
											<input type='text' class="form-control tamanoMaximo" onkeypress="resetearFormularioConEsp()" id='txtfechaHasta' name='fecHasta' placeHolder="dd/mm/aaaa" maxlength="10" readonly="readonly"/> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
									</div>
								</div>
									<div class="col-md-1"><Strong> N&deg; Pedido :</Strong></div>							
									<div id="pnlNumPedido" class="col-md-2">
										<input id="numPedido" name="numPedido" type="text" maxlength = "13" class="form-control"/>
									</div>	
								
								<div class="col-md-2 text-right">
									<button type="button" class="btn btn-primary" onclick="revalidarFormulario()">Cargar Reportes</button>
								</div>		
						</div>
						</fieldset>
						</form>	
						<div class="row content-box">&nbsp;</div>
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border"> Lista de Pedidos Generados </legend>
							<div class="form-group">	
								<div class="col-md-12">
									<div class="tab-content">
									<div id="accionesFiscalizacion" class="tab-pane fade in active">
									<div class="border-line panel-scroll">
									<table id="tablaRepGeneral" class="table display"cellspacing="0" style="width: 100%;"><!--table table-striped table-bordered-->
										<thead>
											<tr>
												<th width="5%">N&#176;</th>
												<th width="5%">Sel</th>
												<th width="15%">N&#176; Pedido</th>
												<th width="30%">Descripci&oacute;n de la Plantilla</th>
												<th width="10%">Fecha de Generaci&oacute;n</th>
												<th width="15%">Estado</th>
												<th width="20%">Usuario</th>
												<th></th>
											</tr>
										</thead>
									</table>
									</div>
									</div>
									</div>
								</div>
								</div>
						<div class="form-group">
								<div class="col-md-12" align="right">&nbsp;</div>											
						</div>		
						<div class="form-group">
								<div class="col-md-12" align="right">
									<!-- <button type="button" class="btn btn-primary" id='imprimir' onclick="imprimirExpedienteTrabajo()">Imprimir Reportes de Expedientes de Trabajo</button>	 -->					
								</div>											
						</div>		
						</fieldset>	
						
						</div>
						
						<form id="formPrincipalGeneral" class="form-horizontal" role="form">	
						<div class="col-md-5" align="right" id="dvSecBotones01">	
							<input id="campoHiddenExp" type="hidden" name="listadoExpedientesCadena"/>
						</div>	
						</form>	
					</div>						
				</div>		
			</div>
			<iframe   id="iPrint" name="iPrint" width="800" height="400" style="display:none" >  		
			</iframe>	
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
		
		<script type="text/javascript">
 
	var listadoProcesos = ${listadoProcesos};
	var fecVista = ${fecVista};
	var titulos = ${titulos};
	var excepciones = ${excepciones};	
	var listaPedidos = [];	
	var numExpediente="";
	var flagExcepcion="";
	var retorno=false;
	var numeroPedido ="";	
	var indexExpedientes="";
	
	jQuery.extend( jQuery.fn.dataTableExt.oSort, {
				"date-eu-pre": function ( date ) {
					var date = date != null ? date.replace(" ", "") : "";
					if(date.length > 0){
						if(date == "-") {
							year = 0;
							month = 0;
							day = 0;
						} else {
							if (date.indexOf('-') > 0) {
				                /*date a, format dd.mn.(yyyy) ; (year is optional)*/
				                var eu_date = date.split('-');
				            } else {
				            	if (date.indexOf('/') > 0) {
				                	var eu_date = date.split('/');
				                }
				            }
							
							/*year (optional)*/
							if (eu_date[2]) {
								var year = eu_date[2];
							} else {
								var year = 0;
							}
							/*month*/
							var month = eu_date[1];
							if (month.length == 1) {
								month = 0+month;
							}
							/*day*/
							var day = eu_date[0];
							if (day.length == 1) {
								day = 0+day;
							}
						}
					} else {
						year = 0;
						month = 0;
						day = 0;
					}  
					
					return (year + month + day) * 1;
				},
				"date-eu-asc": function ( a, b ) {
					return ((a < b) ? -1 : ((a > b) ? 1 : 0));
				},
				"date-eu-desc": function ( a, b ) {
					return ((a < b) ? 1 : ((a > b) ? -1 : 0));
				}
			});
	
	$(function () {
		
		construirTablaPedidos( [] );
		habilitarRadio();
		$('#imprimir').attr('disabled', true);	
		$('#tablaRepGeneral tbody').on('mouseenter', 'tr', function() {
			$(this).addClass("selected");
		});	
		$('#tablaRepGeneral tbody').on( 'mouseleave', 'tr', function () {
			 $(this).removeClass("selected");
			 //$('#tablaRepGeneral').dataTable().fnDraw();
		} );
		
		var table = $('#tablaRepGeneral').DataTable();
		$('#tablaRepGeneral tbody').on( 'mouseenter', 'tr', function () {
			indexExpedientes = table.row( this ).index();
			
		} );
		$('#exportar').attr('disabled', true);
		$.fn.datetimepicker.defaults.language = 'Es';
		
		$('#divFechaDesde').datetimepicker({
            format: 'DD/MM/YYYY',
			useCurrent: false,
			language: 'Es',
			autoClose: true,
			forceParse: true,
			pickTime: false
        });
		
		$('#divFechaDesde').on("dp.change", function(e) {
			 $('#divFechaHasta').data("DateTimePicker").setMinDate(e.date);
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecDesde');
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecHasta');		   
			resetearFormularioConEsp();
		});
		$('#divFechaHasta').datetimepicker({
            format: 'DD/MM/YYYY',
			useCurrent: false,
			language: 'Es',
			autoClose: true,
			forceParse: true,
			pickTime: false
        });

		$("#divFechaHasta").on("dp.change", function (e) {
			$('#divFechaDesde').data("DateTimePicker").setMaxDate(e.date);
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecHasta');
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecDesde');
			resetearFormularioConEsp();
		});
		
		$("#selTipoBusquedaFecha").on("dp.change", function (e) {
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecDesde');
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecHasta');
			resetearFormularioConEsp();
		});
		makeInputMask( '#numPedido', "(9){1,13}", 13, '' );
			
		// Validaciones
		//Validaciones del Formulario Busqueda		
		
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator({		
			excluded: [':disabled'],
			fields: {
				codProceso: {
                    validators: {
                        notEmpty: {
                            message: 'Debe seleccionar un Proceso.'
                        }
                    }
                },
				codTipexp: {
                    validators: {
                        notEmpty: {
                            message: 'Debe seleccionar un Tipo de Expediente.'
                        }
                    }
                },
                fecDesde: {
                    validators: {                        
						date: {
							format: 'DD/MM/YYYY',
							message: 'El formato de la fecha es incorrecto, solo formato: dd/mm/aaaa.'
						},
						callback: {
							message: ' ',
							callback:   function (value, validator, $field) {
								var fechaDesde = document.getElementById("txtfechaDesde").value;
								var fechaHasta = document.getElementById("txtfechaHasta").value;
								
								if(fechaDesde == "" && fechaHasta != "") {
									return {
	                                    valid: false,
	                                    message: 'Seleccione Fecha Desde.'
	                                };
								}
								
								if(fechaDesde!="" && fechaHasta!=""){
									return {
										valid: validacionFechas(),
										message: ' '
									};
								}
								
								return true;
							}
						}
                    }
                },
                fecHasta: {
                    validators: {
						date: {
							format: 'DD/MM/YYYY',
							message: 'El formato de la fecha es incorrecto, solo formato: dd/mm/aaaa.'
						},
						callback: {
							message: excepciones.excepcion9,
							callback:   function (value, validator, $field) {
								var fechaDesde = document.getElementById("txtfechaDesde").value;
								var fechaHasta = document.getElementById("txtfechaHasta").value;
								
								if(fechaDesde != "" && fechaHasta == "") {
									return {
	                                    valid: false,
	                                    message: 'Seleccione Fecha Hasta.'
	                                };
								}
								
								if(fechaDesde!="" && fechaHasta!=""){
									return {
										valid: validacionFechas(),
										message: 'El rango establecido no debe superar a 3 meses.'
									}
								}								
								
								return true;
							}
						}						
                    }

                }
		}
		}).on('success.form.bv', function(e) {
            e.preventDefault();
            consultarReportes();
    	});
		
			
		$('#numPedido').prop('disabled', false);
		$('#btnImprimir').prop('disabled', true);
				

		var $element = $('#codProceso');
		
		$.each(listadoProcesos, function(i, dato) {
		
			var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
			$element.append($option);
			
		});
		
		$('#fecVista').val(fecVista);
		
		//$.ajaxSetup({ scriptCharset: "UTF-8" , contentType: "application/json; charset=utf-8"});
		jQuery.support.cors = true;
		$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
		
		cargarFormulario();
	})
	
	function cargarFormulario(){
    	
    	var realizarBusqueda = '${beanParametrosConsultaReq.realizarBusqueda}';
    	
    	if(realizarBusqueda=="1"){
    		
				$('#codProceso').val('${beanParametrosConsultaReq.codProceso}');
				cargarTiposExpedientes();
				$('#codTipexp').val('${beanParametrosConsultaReq.codTipexp}');
				$('#txtfechaDesde').val('${beanParametrosConsultaReq.fecDesde}');
				$('#txtfechaHasta').val('${beanParametrosConsultaReq.fecHasta}');
				$('#numPedido').val('${beanParametrosConsultaReq.numPedido}');
				
			retorno = true;
			realizarBusqueda = true;
    		revalidarFormulario();
    	}
    }
	
	 function limpiar(){
		resetearFormularioConEsp();
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('resetForm', true);
	}
		
	function cargarTiposExpedientes() {
		
		var codProceso = $('#codProceso').val();
		var dataEnvio = new Object();
		
		$('#codTipexp').empty();
		
		$('#codTipexp').append($('<option>', {
			value: '',
			text: 'Seleccione'
		}));
		
		if (codProceso == "") {
			
			$('#codTipexp').val('');
			
		} else {
			
			dataEnvio.codProceso = codProceso;
			
			$.ajax({
				
				url : '${pageContext.request.contextPath}/impReporteExpTrab.htm?action=cargarListaTiposExpediente',				
				type : 'POST',
				async : false,
				dataType : "json",
				data : "&codProceso="+codProceso,
				//contentType : "application/json",
				mimeType : "application/json",
				success : function(response) {
					
					var listadoTiposExpendientes = response.listadoTiposExpendientes
					
					if (listadoTiposExpendientes.length > 0) {
						
						var $element = $('#codTipexp');
						
						$.each(listadoTiposExpendientes, function(i, dato) {
						
							var $option = $("<option/>").attr("value", dato.codTipoExpediente).text(dato.desTipoExpediente);
							$element.append($option);
							
						});
													
					} else {
						
						mostrarDlgInfo(titulos.tituloDefecto, "No existe Tipos de Expedientes asociados al proceso seleccionado.");
						$('#selProceso').val("");
						
					}
					
				},
				error : function(response) {
					
					mostrarPagError();
					
				}
				
			});
			
		}
		
	}
	
	function consultarReportes(){			
		$('#imprimir').attr('disabled', true);
		$.ajax({
			
			url : '${pageContext.request.contextPath}/impReporteExpTrab.htm?action=cargarListadoPedidos',
			type : 'POST',
			async : true,
			dataType : "json",
			data : $('#frmFiltroBusquedaAvanzada').serialize(),
			//contentType : "application/json",
			mimeType : "application/json",
			//timeout : 5000,
			success : function(response) {
				
				var msjError = response.msjError;
				if(msjError!="" && msjError!=undefined){
					$('#tablaRepGeneral').dataTable().fnClearTable();
					$('#tablaRepGeneral').dataTable().fnDraw();
					mostrarDlgInfo(titulos.tituloDefecto, msjError);
					return;
				}
				
				listaPedidos = response.listaPedidos;
							
				if (listaPedidos.length > 0) {
					
					$('#tablaRepGeneral').dataTable().fnClearTable();
					$('#tablaRepGeneral').dataTable().fnAddData(listaPedidos);
					$('#tablaRepGeneral').dataTable().fnDraw();	
				} else {
					
					$('#tablaRepGeneral').dataTable().fnClearTable();
					$('#tablaRepGeneral').dataTable().fnDraw();
					mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion05);				
				}
				numeroPedido="";
			
			},
			error : function(response) {
				
					$('#tablaRepGeneral').dataTable().fnClearTable();
					$('#tablaRepGeneral').dataTable().fnDraw();
					mostrarPagError();
				
			}
			
		});
	}	
	
	function construirTablaPedidos(dataGrilla) {
    	var table = $('#tablaRepGeneral').DataTable({
			
			"language": {
                "url": "/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
			},
			oLanguage : {
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
					{data :	"numOrden",sClass: 'center alinearCentrado',"defaultContent":""},
					{data :	"numeroPedido",sClass: 'center alinearCentrado',"defaultContent":"",
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
										//"class" : "bChecked",
										name: "seleccion",
										id: data,
										onclick: 'guardarDatos()'
										
									}
								).wrap('<div></div>').parent().html();
							}	
					},
					{data :	"numeroPedido",sClass: 'center alinearCentrado',"defaultContent":"", render : function(data, row){
						 var aux = data;
						 return $('<a>')
							.css('text-decoration','underline')
							.attr({href : '${pageContext.request.contextPath}/impReporteExpTrab.htm?action=cargarReportePorPedido&numeroPedido=' + aux+'&'+$('#frmFiltroBusquedaAvanzada').serialize(), onclick : "$('html').block({message: '<h1>Procesando</h1>'});" })
							.text(data)
							.wrap('<div></div>')
							.parent()
							.html();								
					}},
					{data :	"descripcionPlantilla",sClass: 'center alinearCentrado',"defaultContent":"" },
					{data :	"fechaRegistro",sClass: 'center alinearCentrado',"defaultContent":"","sType": "date-eu" },
					{data :	"desEstadoReporte",sClass: 'center alinearCentrado',"defaultContent":"" },
					{data :	"nombrePersonaRegistro",sClass: 'center alinearCentrado',"defaultContent":"" },
					{data :	"codEstadoReporte",sClass: 'hidden',"defaultContent":"" }
				],
				tableTools: {
			        "sSwfPath": "../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/TableTools/swf/copy_csv_xls.swf"
			    },
				data: dataGrilla,
				autoWidth			:   false,
				ordering			:	true,
				bScrollAutoCss		:	true,
				bStateSave			:	false,
				bAutoWidth			:	false,
				bScrollCollapse		:	true,
				searching			:	false,
				paging				:	true,
				pagingType			:   "full_numbers",
				iDisplayLength		:	5,
				//responsive			:	true,
				bLengthChange		: 	false,
				fnDrawCallback: function(oSettings) {
					habilitarRadio();
					$('#tblExpedientes_paginate').addClass('div100');
					if (oSettings.fnRecordsTotal() == 0) {
						$('#table_paginate').addClass('hiddenDiv');
					} else {
						$('#table_paginate').removeClass('hiddenDiv');
					}
				}
			
        });
    }
	
	function habilitarRadio(){
		
		var dataJson = $('#tablaRepGeneral').DataTable().rows().data();	
		for (var i = 0; i < dataJson.length; i++) {
			if(dataJson[i].codEstadoReporte!="02" && dataJson[i].codEstadoReporte!="03"){
				if($('#'+dataJson[i].numeroPedido).length){
					document.getElementById(dataJson[i].numeroPedido).style.display='none';
				}
			}
		}
	}
	
	function guardarNumExpediente(numExpediente){
		numExpedienteConsultar = numExpediente;
		
	}
	
	function makeInputMask( controlQuery, mask, maxlength, valorInicial ) {
    	var control = $( controlQuery );
    	control.inputmask( mask, {placeholder: ''});
		if ( maxlength != null ) {
			control.prop('maxlength', maxlength);	
		}
		if ( valorInicial != null ) {
			control.val( valorInicial );	
		}
    }
	
	//mostrar Pagina de Error
	function mostrarPagError() {
		
		var formURL = '${pageContext.request.contextPath}/error.htm?action=cargarPaginaError';
		document.forms.frmFiltroBusquedaAvanzada.action = formURL;
		document.forms.frmFiltroBusquedaAvanzada.method = "post";
		document.forms.frmFiltroBusquedaAvanzada.submit();
		
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
	
	function mostrarModal(titulo){
		$('#modalTitle').html(titulo);
		$('#modalForm').modal('show');
	}
	
	function mostrarModalTrazabilidad(titulo){
		$('#titleExpedienteOrigenModal').html(titulo);
		$('#modalExpedienteOrigen').modal('show');
	}
			
	
	function revalidarFormulario(){
		var numPedido =  $('#numPedido').val();
		
		if(numPedido == "" || jQuery.type(numPedido) === "undefined"){			
			$('#frmFiltroBusquedaAvanzada').submit();		
		}else{
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('resetForm', true);
			$('#numPedido').val(numPedido);
			consultarReportes();
		}
	}		
	
	function resetearFormularioConEsp(){
		$('#numPedido').prop('disabled', false);
		$('#imprimir').attr('disabled', true);
	}
		
	function imprimirExpedienteTrabajo(){
		$('#iPrint').attr('src', "/ol-ti-iaexpvirtual-repexptrab/impReporteExpTrab.htm?action=imprimirDocumentosPorExpNumPedido&numeroPedido="+numeroPedido);
		$( '#iPrint' ).attr( 'src', function ( i, val ) { return val; });
		
	}	
	function guardarDatos(){			
		var dataJson = $('#tablaRepGeneral').DataTable().rows().data();		
		numeroPedido = dataJson[indexExpedientes].numeroPedido;
		$('#imprimir').attr('disabled', false);	
	}
	
	function validacionFechas() {
		
		var fechaDesde = document.getElementById("txtfechaDesde").value;
		var fechaHasta = document.getElementById("txtfechaHasta").value;
		var dd = fechaDesde.substring(0, 2);
		var mm = fechaDesde.substring(3, 5);
		var yyyy = fechaDesde.substring(6, 10);
		var rango = parseInt(excepciones.rangoFecha);
		var fechaCompara = new Date(mm+"/"+dd+"/"+yyyy);
		var rangoFechaMaxBusqueda = fechaCompara.addMonths(3);
		var anhoLimite = rangoFechaMaxBusqueda.getFullYear();
		var mesLimite = rangoFechaMaxBusqueda.getMonth() + 1;
		var diaLimite = rangoFechaMaxBusqueda.getDate();
		var anhoLimiteStr;
		var mesLimiteStr;
		var diaLimiteStr;
			
		anhoLimiteStr = anhoLimite.toString();
			
		if (mesLimite < 10) {
				
			mesLimiteStr = '0' + mesLimite.toString();
				
		} else {
				
			mesLimiteStr = mesLimite.toString();
				
		}
			
		if (diaLimite < 10) {
				
			diaLimiteStr = '0' + diaLimite.toString();
				
		} else {
				
			diaLimiteStr = diaLimite.toString();
		}			
		var fechaLimite = diaLimiteStr+"/"+mesLimiteStr+"/"+anhoLimiteStr;
		if(comparafecha(fechaHasta, fechaLimite)){
			return true;
		}else{
			return false;
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
	/**/
	Date.isLeapYear = function (year) { 
		return (((year % 4 === 0) && (year % 100 !== 0)) || (year % 400 === 0)); 
	}

	Date.getDaysInMonth = function (year, month) {
		return [31, (Date.isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month];
	}

	Date.prototype.isLeapYear = function () { 
		return Date.isLeapYear(this.getFullYear()); 
	}

	Date.prototype.getDaysInMonth = function () { 
		return Date.getDaysInMonth(this.getFullYear(), this.getMonth());
	}

	Date.prototype.addMonths = function (value) {
		var n = this.getDate();
		this.setDate(1);
		this.setMonth(this.getMonth() + value);
		this.setDate(Math.min(n, this.getDaysInMonth()));
		return this;
	}
/**/
 </script>
	</body>	
</html>
	