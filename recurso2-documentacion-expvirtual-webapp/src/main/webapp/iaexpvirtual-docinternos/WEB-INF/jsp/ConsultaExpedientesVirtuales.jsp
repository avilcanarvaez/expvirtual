<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="es">

	<head>
		
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=EDGE" />
		<meta name = "viewport" content = "initial-scale = 1.0, user-scalable = no,  width=device-width">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>REGISTRO DE DOCUMENTOS INTERNOS</title>
		
		<!-- Bootstrap -->
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/css/bootstrap-theme.min.css">
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/media/css/jquery.dataTables.css">
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Responsive/css/dataTables.responsive.css">
		<link rel="stylesheet" type="text/css" href="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/media/css/jquery.dataTables_themeroller.css">
		<link rel="stylesheet" type="text/css" href="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Scroller/css/dataTables.scroller.css">
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/css/bootstrap-datetimepicker.min.css"/>
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
			.dataTables_wrapper .dataTables_paginate {
				float: initial !important;
				text-align: center !important;
				padding-top: 0.25em;
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
		
		<div class="container">
			<div>
				<div class="row">
					<div class="panel panel-primary">
						<div class="panel-heading align="center">
							<h3 class="panel-title" align="center">REGISTRO DE DOCUMENTOS INTERNOS</h3>
							<form id="frmPrincipal" class="form-horizontal" role="form">
							</form>
							
						</div>
						
						<form id="formDocInterno" name="formDocInterno" role="form" method="post">
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border">Búsqueda Específica</legend>
								<div class="panel-body">
									<div class="row content-box">
										<div class="col-md-2">
											<label>N° de Expediente: </label>
										</div>
										<div class="col-md-3">
											<input type="hidden" id="tblPage" name="tblPage">
											<input type="hidden" id="recargarBean" name="recargarBean" value="1">
											<select id="selNumeroTipoExpediente" name="selNumeroTipoExpediente" class="cboProceso form-control" onchange="habilitaCampoNumExp()">
												<option value="">Seleccione</option>
											</select> 
										</div>
										<div id="txtCampo" class="col-md-2">
											<input id="numExpediente" name="numExpe" type="text" class="form-control" maxlength="14" onkeypress="return justNumbers(event);"/>
											<input id="numAcumulador" name="numAcumulador" type="hidden"/><!--[gangles 16/08/2016] Se agrega el acumulador -->
										</div>
									</div>
								</div>
								
							</fieldset>
							<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border">Búsqueda Avanzada</legend>
							<div class="panel-body">
								<div class="row content-box">
									<div class="col-md-1">
										<label>Dependencia: </label>
									</div>
									<div id="pnlSelDependencia" class="col-md-3">
										<select id="selDependencia" name="selDependencia" class="cboDependencia form-control">
										</select> 
									</div>
									<div class="col-md-2">
										<label>Proceso: </label>
									</div>
									<div id="pnlSelProceso" class="col-md-2">
										<select id="selProceso" name="selProceso" class="cboProceso form-control" onchange="cargarTiposExpedientes()">
											<option value="">Seleccione</option>
										</select> 
									</div>
									<div class="col-md-2">
										<label>Tipo expediente: </label>
									</div>
									<div id="pnlSelTipoExpe" class="col-md-2">
										<select id="selTipoExpediente" name="selTipoExpediente" class="cboProceso form-control">
											<option value="">Seleccione</option>
										</select> 
									</div>
								</div>
								<div class="row content-box">&nbsp;</div>
								<div class="row content-box">
									<div class="col-md-1">
										<label>Fecha de:</label>
									</div>
									<div class="col-md-3">
										<select id="selTipoFecha" name="selTipoFecha" class="cboProceso form-control" onchange="limpiarFechas(); deshabilitarBusquedaEspecifica();">
												<option value="">Seleccione</option>
										</select> 
									</div>
									<div class="col-md-2">
										<label>Desde:</label>
									</div>
									<div class="col-md-2">
										<div>
											<div class='input-group date col-md-12' id='fechaDesde' onchange="deshabilitarBusquedaEspecifica();">
												<input id="fechaDes" name="fechaDes" type='text' class="form-control form-control-single" onkeypress="datoTipoFecha()" maxlength="10"/>
												<span class="input-group-addon">
<!-- 													GLYPH: &#xe109;  FA: &#xf073; -->
<!-- 													<i class="glyphicon ">&#xe109;</i> -->
                                                        <span class="fa fa-calendar"></span>
												</span>
											</div>
										</div>
									</div>
									<div class="col-md-2">
										<label>Hasta:</label>
									</div>
									<div class="col-md-2">
										<div>
											<div class='input-group date col-md-12' id='fechaHasta' onchange="deshabilitarBusquedaEspecifica();">
												<input id="fechaHas" name="fechaHas" type='text' class="form-control form-control-single" onkeypress="datoTipoFecha()" maxlength="10">
												<span class="input-group-addon">
<!-- 													<i class="glyphicon ">&#xe109;</i> -->
                                                        <span class="fa fa-calendar"></span>
												</span>
											</div>
											
										</div>
									</div>
								</div>
								<div class="row content-box">&nbsp;</div>
								<div class="row content-box">
									
									<div class="col-md-1">
										<label>RUC: </label>
									</div>
									<div id="txtCampo2" class="col-md-3">
										<input id="numRuc" name="txtNumRuc" type="text" class="form-control" maxlength="11" onkeypress="return justNumbersNumRUC(event);" onkeyup="validarRUCFueraFoco();"/>
									</div>
									<div class="col-md-2">
										<label>Razón Social:</label>
									</div>
									<div class="col-md-6">
										<input id="razonSocial" name="txtRazonSocial" type="text" class="form-control"  disabled/>
									</div>
								</div>
							</div>
						</fieldset> 
						<div style="margin : 15px 15px !important">
							<div class="row content-box">
								<div class="col-md-12 text-right">
									<button type="button" class="btn btn-primary" onclick="limpiar()">Limpiar</button>
									<!-- Inicio [jquispe 27/05/2016] Realiza la accion cuando el usuario realiza la consulta.-->
									<button id="btnConsultar" type="button" class="btn btn-primary" onclick="consultarExpediente()">Consultar</button>
									<!-- Fin [jquispe 27/05/2016] -->
								</div>
							</div>
						</div>
					</form>
					<fieldset class="scheduler-border" style="margin : 15px 15px !important">
						<legend class="scheduler-border"> Expedientes virtuales </legend>							
							<div class="form-group" id="Expediente">	
								<div class="col-md-12">
									<div class="tab-content">
										<div class="tab-pane fade in active">
											<div class="border-line panel-scroll">
												<table id="table" class="table display" cellspacing="0" style="width: 100%;"><!--table table-striped table-bordered-->
													<thead>
														<tr class="active">
															<th width="3%" class="text-center">N°</th>
															<th width="15%" class="text-center">N° Expediente Origen</th>
															<th width="15%" class="text-center">N° Expediente Virtual</th>
															<!-- Inicio [gangles 16/08/2016] Se agregan dos columnas del Expediente Acumulador--> 
															<th width="10%" class="text-center">N&deg; de Expediente Acumulador</th>
															<th width="12%" class="text-center">Indicador de Acumulaci&oacute;n</th>
															<!-- Fin [gangles 16/08/2016] -->
															<th width="10%" class="text-center">RUC</th>
															<th width="10%" class="text-center">Proceso</th>
															<th width="12%" class="text-center">Tipo de Expediente</th>
															<th width="15%" class="text-center">Fecha de Generaci&oacute;n</th>
															<th width="13%" class="text-center">Fecha de doc. Origen</th>
															<!-- INICIO[ATORRESCH 20170313] -->
															<th width="13%" class="text-center">Fecha de Notificaci&oacute;n</th>
															<th width="13%" class="text-center">Forma de Notificaci&oacute;n</th>
															<!-- FIN   [ATORRESCH 20170313] -->
															<th width="7%" class="text-center">Origen</th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						    <!-- INICIO[LLRB 09012020] -->
							<div class="form-group" id="ExpedienteFiscalizacion" style="display: none">	
								<div class="col-md-12">
									<div class="tab-content">
										<div class="tab-pane fade in active">
											<div class="border-line panel-scroll">
												<table id="tableFiltro"   class="table display"  cellspacing="0" style="width: 100%;" ><!--table table-striped table-bordered-->
													<thead>
														<tr class="active">
															<th width="3%" class="text-center">N°</th>
															<th width="15%" class="text-center">N° Expediente</th>
															<!--<th width="15%" class="text-center">N° Expediente Virtual</th>
															<!-- Inicio [gangles 16/08/2016] Se agregan dos columnas del Expediente Acumulador--> 
															<!--<th width="10%" class="text-center">N&deg; de Expediente Acumulador</th>
															<th width="12%" class="text-center">Indicador de Acumulaci&oacute;n</th>
															<!-- Fin [gangles 16/08/2016] -->
															<th width="10%" class="text-center">RUC</th>
															<th width="10%" class="text-center">Proceso</th>
															<th width="12%" class="text-center">Tipo de Expediente</th>
															<!--<th width="15%" class="text-center">Fecha de Generaci&oacute;n</th> -->
															<th width="13%" class="text-center">Fecha de doc. Origen</th>
															<!-- INICIO[ATORRESCH 20170313] -->
															<!--<th width="13%" class="text-center">Fecha de Notificaci&oacute;n</th>
															<th width="13%" class="text-center">Forma de Notificaci&oacute;n</th>
															<!-- FIN   [ATORRESCH 20170313] -->
															<th width="7%" class="text-center">Origen</th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- FIN[LLRB 09012020] -->
					</fieldset>

					<div style="margin : 15px 15px !important">
						<div class="row content-box">
							<div class="col-md-12 text-center">
								<button id="exportar" type="button" class="btn btn-primary" onclick="exportar()">Exportar a Excel</button>
							</div>
						</div>
					</div>
					<form id="formPrincipal" class="form-horizontal" role="form">		
						<input id="campoHiddenExp" type="hidden" name="listadoExpedientesCadena"/>
					</form>		
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
		
		var titulos = ${titulos};
		var excepciones = ${excepciones};
		var listadoProcesos = ${listadoProcesos};
		var listDependencias = ${listDependencias};
		var listadoNumeroTipoExpediente = ${listadoNumeroTipoExpediente};
		var listadoTipoFecha = ${listadoTipoFecha};
		var codDepUsuario = ${codDepUsuario};
		var codDependenciaBase = ${codDependenciaBase};
		var mensajeEsepcion = "";
		var gRucValido = false;
		var indicador = false;
		var idxPaginador = -1;
		var tblPage = "${beanParametrosConsultaReq.tblPage}";  //anadir
		//Inicio [jquispe 27/05/2016] Realiza la accion cuando el usuario realiza la consulta.
		var flagBusquedaBoton = false;
		// Fin [jquispe 27/05/2016]
		
		var hoverBuscar=false;
		
		$(document).ready(function() {
			
			$.fn.datetimepicker.defaults.language = 'Es';
			
			$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);

			$('#exportar').attr('disabled', true);
			
			//$.ajaxSetup({ scriptCharset: "UTF-8" , contentType: "application/json; charset=utf-8"});
				jQuery.support.cors = true;
				
				$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);

				/*$('#table').DataTable( {
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
						{data :	"numExpedienteOrigen", render : function(data, row){
							 var aux = data;
							 return $('<a>')
							 	.css('text-decoration','underline')
							 	.attr({onclick : "seleccionaNroExpOrg('"+aux+"');" })
				                .text(data)
				                .wrap('<div></div>')
				                .parent()
				                .html(); 
				        }},
						{data :	"numExpedienteVirtual" },
						// Inicio [gangles 16/08/2016] Se agregan columnas del expediente acumulador.
						{data :	"numExpedienteAcumulador" ,sClass: 'centered alinearCentrado',"defaultContent":"", 
							render : function(data, type, row){								
								var link;
								if(data=="0") {
									return "";
								}else {
									//link = '<a onclick="obtenerDocumentos('+data+')">'+data+'</a>';										
									//link = '<a href="conRepControl.htm?action=cargarDocumentosExpAcumulado&numExpedienteAcumulador=">'+data+'</a>';
									//link=data;
									
									return $('<a>')
										.css('text-decoration','underline')
										.attr({	onclick :'consultarExpedientesAcumulados("'+row.numExpedienteAcumulador+'")'})
										.text(data)
										.wrap('<div></div>')
										.parent()
										.html();
				             	}											
						}},
						{data : "desIndicadorAcumulado", sClass: 'centered alinearCentrado',"defaultContent":""},
						// Fin [gangles 16/08/2016]
						{data :	"numRuc" },
						{data :	"desProceso" },
						{data :	"desTipoExpediente" },
						{data :	"fecRegistro" },
						{data :	"fechaDocumentoOrigen" },
						{data :	"strFecNotifOrigen"},
						{data :	"desForNotifOrigen" },						
						{data :	"desOrigen" }
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
					iDisplayLength		:	10,
					responsive			:	true,
					bLengthChange		: 	false,
					fnDrawCallback		:	function(oSettings) {
					//[ATORRESCH 2017-05-12] CONSIDERAR EL ANCHO DATATABLES									
					$("#table_wrapper").css("min-width", $('#table').width());

					$('#table_paginate').addClass('div100');
						if (oSettings.fnRecordsTotal() == 0) {
							$('#table_paginate').addClass('hiddenDiv'); //el footer de paginacion siempre tiene como ID "miTablaID_paginate"
																				//no se oculta automaticamente
																	//cuando hay 0 registros, por eso se oculta anadiendo el class .hiddenDiv
				        }
						else {
							$('#table_paginate').removeClass('hiddenDiv');
							$('#tblPage').val(getPaginaTbl("table"));
						}
				    }
					        
			} );*/

			//ajustarTextoPaginador('#table');
			
			var $element = $('#selProceso');
			
			$.each(listadoProcesos, function(i, dato) {
			
				var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
				$element.append($option);
				
			});
			
			//var selDependencia = document.getElementById("selDependencia");    
			//selDependencia.selectedIndex=codDepUsuario;
			
			var $element = $('#selDependencia');
			
			$.each(listDependencias, function(i, dato) {
				
				var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
				$element.append($option);
				
			});
			
			//[Inicio - jtejada 13/09/2016]
			if($.trim(codDepUsuario) == ''){
				$("#selDependencia").val($("#selDependencia option:first").val());
			}else{
				$('#selDependencia').val(codDepUsuario);
			}
			//[Fin - jtejada 13/09/2016]
			
			var $element = $('#selNumeroTipoExpediente');
			
			$.each(listadoNumeroTipoExpediente, function(i, dato) {
			
				var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
				$element.append($option);
				
			});
			
			var $element = $('#selTipoFecha');
			
			$.each(listadoTipoFecha, function(i, dato) {
			
				var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
				$element.append($option);
				
			});
			
			//eaguilar: permite pegar solo numeros en el campo numero de expediente:
			$('#numExpediente').on('paste', function(e) {
				setTimeout(
						function(){
							var numDoc =  $.trim($('#numExpediente').val());
							var aux = numDoc.replace(/[a-zA-Z\u00e1\u00e9\u00ed\u00f3\u00fa\u00f1\u00c1\u00c9\u00cd\u00d3\u00da\u00d1 ]/g, '*')
							if(aux.indexOf('*') > -1){
								$('#numExpediente').val("");
								mostrarDlgInfo("Adjuntar Documentos Internos", excepciones.excepcion31);
							}
				}, 90);
			});
			
			$('#selNumeroTipoExpediente').val("");
			$('#numExpediente').val("");
			$("#numExpediente").attr('disabled', true);
			$('#selProceso').val("");
			$('#selTipoFecha').val("");
			$('#selTipoExpediente').val("");
			$('#numRuc').val("");
			$('#fechaDes').val("");
			$('#fechaHas').val("");
			$('#table').dataTable().fnClearTable();
			cargarFormulario();
		});
		
// 		$('#numRuc').on('paste', function(e) {
// 			setTimeout(
// 					function(){
// 						var numDoc =  $.trim($('#numRuc').val());
// 						var aux = numDoc.replace(/[a-zA-Z\u00e1\u00e9\u00ed\u00f3\u00fa\u00f1\u00c1\u00c9\u00cd\u00d3\u00da\u00d1 ]/g, '*')
// 						if(aux.indexOf('*') > -1){
// 							$('#numRuc').val("");
// // 							mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion07);
// // 							mensajeEsepcion = excepciones.excepcion07;
// 							$('#razonSocial').attr("value", "");
// 						}
// 			}, 90);
// 		});
		
		$('#btnConsultar').mouseover(function(){
// 			hoverBuscar=true;
			$("#numRuc").blur();
		});

		$('#btnConsultar').mouseout(function(){
// 			hoverBuscar=false;
		});
		
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
		
		function seleccionaNroExpOrg(aux){
			 $('html').block({message: '<h1>Procesando</h1>'});
			 //Inicio [jquispe 27/05/2016] Realiza la accion cuando el usuario realiza la consulta.
			 window.location.href = '${pageContext.request.contextPath}/regDocInterno.htm?action=seleccionaNroExpOrigen&flagPaginaPrincipal=true&aux=' + aux +'&'+$('#formDocInterno').serialize()+'&codDependencia='+$('#selDependencia').val();
			 // Fin [jquispe 27/05/2016]
		}

		function getPaginaTbl(idTabla){
			return $('#'+idTabla).DataTable().page.info().page;
		}
				
		function setPagintaTbl(idTabla, idxPagina){
			$('#'+idTabla).DataTable().page(idxPagina).draw(false);
		}
		
		function cargarFormulario(){
	    	
	    	var realizarBusqueda = '${beanParametrosConsultaReq.realizarBusqueda}';
	    	
	    	if(realizarBusqueda=="1"){
				var numExp = '${beanParametrosConsultaReq.numExpe}';
				if(numExp!=""){
					$('#selNumeroTipoExpediente').val('${beanParametrosConsultaReq.selNumeroTipoExpediente}');
					$('#numExpediente').prop('disabled', false);
					$('#numExpediente').val(numExp);
				}else{
					$('#selProceso').val('${beanParametrosConsultaReq.selProceso}');
					cargarTiposExpedientes();
					$('#selTipoExpediente').val('${beanParametrosConsultaReq.selTipoExpediente}');
					$('#selTipoFecha').val('${beanParametrosConsultaReq.selTipoFecha}');
					$('#fechaDes').val('${beanParametrosConsultaReq.fechaDes}');
					$('#fechaHas').val('${beanParametrosConsultaReq.fechaHas}');
					$('#numRuc').val('${beanParametrosConsultaReq.txtNumRuc}');
					$('#razonSocial').val('${beanParametrosConsultaReq.txtRazonSocial}');						
					$('#selDependencia').val('${beanParametrosConsultaReq.codDependencia}');					
				}
				gRucValido=true;
				//Inicio [jquispe 27/05/2016] Realiza la accion cuando el usuario realiza la consulta.				
				consultarRetorno();
				// Fin [jquispe 27/05/2016]
				if($('#numRuc').val()!='') validarRUCFueraFoco(); //eaguilar 23 jun
	    	}

	    }
		
		//Inicio [jquispe 27/05/2016] Realiza la accion cuando el usuario realiza la consulta.
		
		function consultarRetorno(){
			consultar(false);			
		}
		
		function consultarExpediente(){
			consultar(true);
		}
		
		function consultar(realizaClickBoton){
			flagBusquedaBoton = realizaClickBoton;
		// Fin [jquispe 27/05/2016]
			var codNumTipoExp = $('#selNumeroTipoExpediente').val();
			var numExpediente = $('#numExpediente').val();
			var numRuc = $('#numRuc').val();
			var fechaDesde = document.getElementById("fechaDes").value;
			var fechaHasta = document.getElementById("fechaHas").value;
			var codProceso = $('#selProceso').val();
			var codDependencia = $('#selDependencia').val();
			var codTipoFecha = $('#selTipoFecha').val();
			var codTipoExpe = $('#selTipoExpediente').val();
			$('#table').dataTable().fnClearTable();		
			$('#tableFiltro').dataTable().fnClearTable();
			if(codNumTipoExp == "" && numExpediente == "" && numRuc == "" && fechaDesde == "" && fechaHasta == "" && codProceso == "" && codTipoExpe == "" && codTipoFecha ==""){
				
				mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion19);
				
			}
			else if(numRuc != "" && !gRucValido && numRuc.length == 11){
				validarRUCFueraFoco();
			}

			else if(numRuc != "" && gRucValido){
// 			else if(numRuc != "" && gRucValido && (codProceso == "" && codTipoExpe == "" && codTipoFecha == "" && fechaDesde == "" && fechaHasta == "")){
				var bFechas = false;
				if(codTipoFecha != ""){
					if(fechaDesde == ""){
						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion15); return false;
					}else{
						if(validarFormatoFecha(fechaDesde)){
							if(existeFecha(fechaDesde)){
								if(fechaHasta==""){
									mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion16); return false;
								}else{
									if(validarFormatoFecha(fechaHasta)){
										if(existeFecha(fechaHasta)){
											if(comparafecha(fechaDesde, fechaHasta)){
												/*Suma meses inicio*/
												var dd = fechaDesde.substring(0, 2);
												var mm = fechaDesde.substring(3, 5);
												var yyyy = fechaDesde.substring(6, 10);
												var rango = parseInt(excepciones.rangoFecha);
												var fechaCompara = new Date(mm+"/"+dd+"/"+yyyy);
												var rangoFechaMaxBusqueda = fechaCompara.addMonths(rango);
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
												/*Suma meses fin*/
												if(comparafecha(fechaHasta, fechaLimite)){
													bFechas = true;
												}else{
													if(codTipoFecha == "1"){
														mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion23); return false;
													}else if (codTipoFecha == "2"){
														mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion05); return false;
													}
												}
											}else{
												mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion03); return false;
											}
										}else{
											mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion18); return false;
										}
									}else{
										mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion18); return false;
									}
								}
							}else{
								mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion17); return false;
							}
						}else{
							mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion17); return false;
						}
					}
				}
				var dataEnvio = new Object();
				if(bFechas){
					dataEnvio.codTipoFecha = codTipoFecha;
					dataEnvio.fechaDesde = fechaDesde;
					dataEnvio.fechaHasta = fechaHasta;
				}
				dataEnvio.codProceso = codProceso==''?null:codProceso;
				dataEnvio.codTipoExpe = codTipoExpe==''?null:codTipoExpe;
				dataEnvio.numRuc = numRuc;
				dataEnvio.codDependencia = codDependencia;
				dataEnvio.flagPorRUC = 1;
				$.ajax({
					//Inicio [jquispe 27/05/2016] Realiza la accion cuando el usuario realiza la consulta.
					url : '${pageContext.request.contextPath}/regDocInterno.htm?action=cargarListaExpedientes&indCarga=1&flagBusquedaBoton='+flagBusquedaBoton,
					// Fin [jquispe 27/05/2016]
					type : 'POST',
					async : true,
					dataType : "json",
					data : JSON.stringify(dataEnvio),
					contentType : "application/json",
					mimeType : "application/json",
					success : function(response) {
						var listadoExpendientes = response.listadoExpendientes
						if (listadoExpendientes.length > 0) {
													
							//INICIO LLRB
							if(listadoExpendientes.codTipoExpediente== '430' ||listadoExpendientes.codTipoExpediente== '431' ){
								$('#Expediente').hide();
								$('#ExpedienteFiscalizacion').show();
								$('#tableFiltro').dataTable().fnClearTable();
								$('#tableFiltro').dataTable().fnAddData(listadoExpendientes); //carga registros en grilla	
								$('#tableFiltro').dataTable().fnDraw();
								$('#exportar').attr('disabled', false);
							
								
							
							}else{

								$('#Expediente').show();
								$('#ExpedienteFiscalizacion').hide();
								$('#table').dataTable().fnClearTable();
								$('#table').dataTable().fnAddData(listadoExpendientes); //carga registros en grilla	
								$('#table').dataTable().fnDraw();
								$('#exportar').attr('disabled', false);
								if(tblPage != "") {
									setPagintaTbl("table", parseInt(tblPage)); //anadir
									tblPage = "";
								}
							}//FIN LLRB



						}else {
							mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion08);
							$('#table').dataTable().fnClearTable();
							$('#exportar').attr('disabled', true);
						}
					},
					error : function(response) {
						mostrarPagError();
					}
				});
			}
			else{
			
				if(codNumTipoExp == ""){
				
					if(codProceso == ""){
					
						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion20);
					
					}else{
					
						if(codTipoExpe == ""){
						
							mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion21);
						
						}else{
						
							if(codTipoFecha == ""){
						
								mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion04);
						
							}else{
							
								if(fechaDesde == ""){
						
									mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion15);
						
								}else{
								
									if(validarFormatoFecha(fechaDesde)){
									
										if(existeFecha(fechaDesde)){
										
											if(fechaHasta==""){
											
												mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion16);
											
											}else{
											
												if(validarFormatoFecha(fechaHasta)){
												
													if(existeFecha(fechaHasta)){
													
														if(comparafecha(fechaDesde, fechaHasta)){
															
															/*Suma meses inicio*/
															var dd = fechaDesde.substring(0, 2);
															var mm = fechaDesde.substring(3, 5);
															var yyyy = fechaDesde.substring(6, 10);
															var rango = parseInt(excepciones.rangoFecha);
															var fechaCompara = new Date(mm+"/"+dd+"/"+yyyy);
															var rangoFechaMaxBusqueda = fechaCompara.addMonths(rango);
															
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
															/*Suma meses fin*/
															
															if(comparafecha(fechaHasta, fechaLimite)){
															
																if(numRuc == ""){
																			
																	var dataEnvio = new Object();
																		
																	dataEnvio.codProceso = codProceso;
																	dataEnvio.codTipoExpe = codTipoExpe;
																	dataEnvio.codTipoFecha = codTipoFecha;
																	dataEnvio.fechaDesde = fechaDesde;
																	dataEnvio.fechaHasta = fechaHasta;
																	dataEnvio.numRuc = numRuc;
																	dataEnvio.codDependencia = codDependencia;
																	$.ajax({
																		//Inicio [jquispe 27/05/2016] Realiza la accion cuando el usuario realiza la consulta.
																		url : '${pageContext.request.contextPath}/regDocInterno.htm?action=cargarListaExpedientes&indCarga=1&flagBusquedaBoton='+flagBusquedaBoton,
																		// Fin [jquispe 27/05/2016]
																		type : 'POST',
																		async : true,
																		dataType : "json",
																		data : JSON.stringify(dataEnvio),
																		contentType : "application/json",
																		mimeType : "application/json",
																		success : function(response) {
																			
																			var listadoExpendientes = response.listadoExpendientes
																			
																			if (listadoExpendientes.length > 0) {
																				if(listadoExpendientes.codTipoExpediente== '430' ||listadoExpendientes.codTipoExpediente== '431' ){
																					$('#Expediente').show();
																					$('#ExpedienteFiscalizacion').hide();
																					$('#table').dataTable().fnClearTable();
																					$('#table').dataTable().fnAddData(listadoExpendientes); //carga registros en grilla	
																					$('#table').dataTable().fnDraw();
																					$('#exportar').attr('disabled', false);
																					
																				
																				}else{
																					$('#Expediente').hide();
																					$('#ExpedienteFiscalizacion').show();
																					$('#tableFiltro').dataTable().fnClearTable();
																					$('#tableFiltro').dataTable().fnAddData(listadoExpendientes); //carga registros en grilla	
																					$('#tableFiltro').dataTable().fnDraw();
																					$('#exportar').attr('disabled', false);
																				}//FIN LLRB
																			} else {
																				
																				mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion08);
																				$('#table').dataTable().fnClearTable();
																				$('#exportar').attr('disabled', true);
																			}
																			
																		},
																		error : function(response) {
																			
																			mostrarPagError();
																			
																		}
																	
																	});
															
																}else{
																
																	if(numRuc.length < 11 ){
																	
																		mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion07);
																	
																	}else if(gRucValido==true){
																	
																		var dataEnvio = new Object();
																		
																		dataEnvio.codProceso = codProceso;
																		dataEnvio.codTipoExpe = codTipoExpe;
																		dataEnvio.codTipoFecha = codTipoFecha;
																		dataEnvio.fechaDesde = fechaDesde;
																		dataEnvio.fechaHasta = fechaHasta;
																		dataEnvio.numRuc = numRuc;
																		dataEnvio.codDependencia = codDependencia;
																		$.ajax({
																			//Inicio [jquispe 27/05/2016] Realiza la accion cuando el usuario realiza la consulta.
																			url : '${pageContext.request.contextPath}/regDocInterno.htm?action=cargarListaExpedientes&indCarga=1&flagBusquedaBoton='+flagBusquedaBoton,
																			// Fin [jquispe 27/05/2016]
																			type : 'POST',
																			async : true,
																			dataType : "json",
																			data : JSON.stringify(dataEnvio),
																			contentType : "application/json",
																			mimeType : "application/json",
																			success : function(response) {
																				
																				var listadoExpendientes = response.listadoExpendientes
																				
																				if (listadoExpendientes.length > 0) {
																					if(listadoExpendientes.codTipoExpediente== '430' ||listadoExpendientes.codTipoExpediente== '431' ){
																						$('#Expediente').show();
																						$('#ExpedienteFiscalizacion').hide();
																						$('#table').dataTable().fnClearTable();
																						$('#table').dataTable().fnAddData(listadoExpendientes); //carga registros en grilla	
																						$('#table').dataTable().fnDraw();
																						$('#exportar').attr('disabled', false);
																						if(tblPage != "") {
																							setPagintaTbl("table", parseInt(tblPage)); //anadir
																							tblPage = "";
																						}
																						
																					
																					}else{
																						$('#Expediente').hide();
																						$('#ExpedienteFiscalizacion').show();
																						$('#tableFiltro').dataTable().fnClearTable();
																						$('#tableFiltro').dataTable().fnAddData(listadoExpendientes); //carga registros en grilla	
																						$('#tableFiltro').dataTable().fnDraw();
																						$('#exportar').attr('disabled', false);
																					}//FIN LLRB
																					if(tblPage != "") {
																						setPagintaTbl("table", parseInt(tblPage)); //anadir
																						tblPage = "";
																					}
																					
																				} else {
																					
																					mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion08);
																					$('#table').dataTable().fnClearTable();
																					$('#exportar').attr('disabled', true);
																				}
																				
																			},
																			error : function(response) {
																				
																				mostrarPagError();
																				
																			}
																		
																		});
																	
																	}else{
																		mostrarDlgInfo(titulos.tituloDefecto, mensajeEsepcion);
																	}
																
																}
															
															}else{
																if(codTipoFecha == "1"){
																	mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion23);
																}else if (codTipoFecha == "2"){
																	mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion05);
																}
															}
																													
														}else{
														
															mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion03);
														
														}
													
													}else{
													
														mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion18);
													
													}
												
												}else{
												
													mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion18);
												
												}
											
											}
										
										}else{
										
											mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion17);
										
										}
									
									}else{
									
										mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion17);
									
									}
								
								}
							
							}
						
						}
					
					}
				
				}else{
					var dataEnvio = new Object();
					
					dataEnvio.codNumTipoExp = codNumTipoExp;
					dataEnvio.numExpediente = numExpediente;
					dataEnvio.codDependencia = codDependencia;
					dataEnvio.codDependenciaBase = codDependenciaBase;//[oachahuic][PAS20165E210400270]
					
					indicador = false; //eaguilar: reinicializa a false de manera que tome el valor devuelto por validaNumeroExp()
					
					if(codNumTipoExp == "1"){
					
						if(numExpediente == ""){
						
							mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion22);
							
						}else{
								
								validaNumeroExp();
								if(indicador==true){
									$.ajax({
								       //Inicio [jquispe 27/05/2016] Realiza la accion cuando el usuario realiza la consulta.
										url : '${pageContext.request.contextPath}/regDocInterno.htm?action=cargarListaExpedientes&indCarga=0&flagBusquedaBoton='+flagBusquedaBoton,
									   // Fin [jquispe 27/05/2016]
										type : 'POST',
										async : true,
										dataType : "json",
										data : JSON.stringify(dataEnvio),
										contentType : "application/json",
										mimeType : "application/json",
														
										success : function(response) {
											if(response.status === false){
					                            mostrarDlgInfo(titulos.tituloDefecto, response.message);
					                            $('#table').dataTable().fnClearTable();
					                            $('#tableFiltro').dataTable().fnClearTable();
												$('#exportar').attr('disabled', true);
					                            return false; 
					                        }	
											
											var listadoExpendientes = response.listadoExpendientes;
											//INICIO LLRB 14022020
											var codTipoExp = response.codTipoExp;
											if (listadoExpendientes.length > 0) {										

												if(codTipoExp == 430 ||codTipoExp == 431 ){
													$('#Expediente').hide();
													$('#ExpedienteFiscalizacion').show();
													$('#tableFiltro').dataTable().fnClearTable();
													$('#tableFiltro').dataTable().fnAddData(listadoExpendientes); //carga registros en grilla	
													$('#tableFiltro').dataTable().fnDraw();
													$('#exportar').attr('disabled', false);
													
												
												}else{

													$('#Expediente').show();
													$('#ExpedienteFiscalizacion').hide();
													$('#table').dataTable().fnClearTable();
													$('#table').dataTable().fnAddData(listadoExpendientes); //carga registros en grilla	
													$('#table').dataTable().fnDraw();
													$('#exportar').attr('disabled', false);
												}//FIN LLRB
												
												if(tblPage != "") {
													setPagintaTbl("table", parseInt(tblPage)); //anadir
													tblPage = "";
												}
												
											}
											
										},
										error : function(response) {
											
											mostrarPagError();
											
										}
									
									});
								}else{
									mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion26);
								}
						}
					
					}else if (codNumTipoExp == "2"){
					
						if(numExpediente == ""){
							
							mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion22);
							
						}else{
							//eaguilar 6 JUN se quito validacion de longitud de numero
							if(numExpediente.length < 14){
								
								mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion13);
								
							}else{
								validaNumeroExp();
								if(indicador==true){
									$.ajax({
							            //Inicio [jquispe 27/05/2016] Realiza la accion cuando el usuario realiza la consulta.
										url : '${pageContext.request.contextPath}/regDocInterno.htm?action=cargarListaExpedientes&indCarga=0&flagBusquedaBoton='+flagBusquedaBoton,
										// Fin [jquispe 27/05/2016]
										type : 'POST',
										async : true,
										dataType : "json",
										data : JSON.stringify(dataEnvio),
										contentType : "application/json",
										mimeType : "application/json",
										success : function(response) {
											if(response.status === false){
					                            mostrarDlgInfo(titulos.tituloDefecto, response.message);
					                            $('#table').dataTable().fnClearTable();
												$('#exportar').attr('disabled', true);
					                            return false; 
					                        }
											var listadoExpendientes = response.listadoExpendientes
											
											if (listadoExpendientes.length > 0) {
												if(listadoExpendientes.codTipoExpediente== '430' ||listadoExpendientes.codTipoExpediente== '431' ){
													$('#Expediente').show();
													$('#ExpedienteFiscalizacion').hide();
													$('#table').dataTable().fnClearTable();
													$('#table').dataTable().fnAddData(listadoExpendientes); //carga registros en grilla	
													$('#table').dataTable().fnDraw();
													$('#exportar').attr('disabled', false);
													
												
												}else{
													$('#Expediente').hide();
													$('#ExpedienteFiscalizacion').show();
													$('#tableFiltro').dataTable().fnClearTable();
													$('#tableFiltro').dataTable().fnAddData(listadoExpendientes); //carga registros en grilla	
													$('#tableFiltro').dataTable().fnDraw();
													$('#exportar').attr('disabled', false);
												}//FIN LLRB
												
											} else {												
												//mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion08);
												mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion32);
												$('#table').dataTable().fnClearTable();
												$('#exportar').attr('disabled', true);
											}
											
										},
										error : function(response) {
											
											mostrarPagError();
											
										}
									
									});
								}else{
									mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion26);
								}
							}
							
							
						}
					
					}
				
				}
			
			}
		
		}
		
		function validaNumeroExp(){
		
			var codNumTipoExp = $('#selNumeroTipoExpediente').val();
			var numExpediente = $('#numExpediente').val();
			
			var dataEnvio = new Object();

			dataEnvio.codNumTipoExp = codNumTipoExp;
			dataEnvio.numExpediente = numExpediente;
			$.ajax({		
				url : '${pageContext.request.contextPath}/regDocInterno.htm?action=validaExpediente&indCarga=0',
				type : 'POST',
				async : false,
				dataType : "json",
				data : JSON.stringify(dataEnvio),
				contentType : "application/json",
				mimeType : "application/json",
				success : function(response) {
					
					var listadoExpendientes = response.listadoExpendientes
					
					if (listadoExpendientes.length > 0) {
						indicador = true;
					} 
					
				},
				error : function(response) {
					
					mostrarPagError();
					
				}
			
			});
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
		
		function mostrarPagError() {
				
			var formURL = '${pageContext.request.contextPath}/error.htm?action=cargarPaginaError';
			document.forms.frmPrincipal.action = formURL;
			document.forms.frmPrincipal.method = "post";
			document.forms.frmPrincipal.submit();
			
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
		
		function validarFormatoFecha(campo) {
			var RegExPattern = /^\d{1,2}\/\d{1,2}\/\d{2,4}$/;
			if ((campo.match(RegExPattern)) && (campo!='')) {
				return true;
			} else { 
				return false;
			}
		}
		
		function exportar(){
			var dataExp = $('#table').dataTable().fnGetData();
			//INICIO LLRB 24/01/2020
			var dataExpF=$('#tableFiltro').dataTable().fnGetData();
			if(dataExp.length > 0){				
				var listaCadena = JSON.stringify(dataExp);
				var formURL = 'regDocInterno.htm?action=exportarExcelExpedientes';
				document.forms.formPrincipal.action = formURL;
				document.forms.formPrincipal.method = "POST";
				$('#campoHiddenExp').val('');
				$('#campoHiddenExp').val(listaCadena);
				$('html').block({message: '<h1>Procesando</h1>'});
				document.forms.formPrincipal.submit();
				$('html').unblock();
			}else{
				var listaCadena = JSON.stringify(dataExpF);
				var formURL = 'regDocInterno.htm?action=exportarExcelExpedientesFisca';
				document.forms.formPrincipal.action = formURL;
				document.forms.formPrincipal.method = "POST";
				$('#campoHiddenExp').val('');
				$('#campoHiddenExp').val(listaCadena);
				$('html').block({message: '<h1>Procesando</h1>'});
				document.forms.formPrincipal.submit();
				$('html').unblock();
				}
			//FIN  LLRB 24022020
			}

		function limpiar(){
			
			$('#selNumeroTipoExpediente').val("");
			$("#selNumeroTipoExpediente").attr('disabled', false);
			$('#numExpediente').val("");
			$("#numExpediente").attr('disabled', true);
			$('#selProceso').val("");
			$('#selTipoFecha').val("");
			$('#selTipoExpediente').val("");
			$('#numRuc').val("");
			$('#fechaDes').val("");
			$('#fechaHas').val("");
			$('#table').dataTable().fnClearTable();
			$('#tableFiltro').dataTable().fnClearTable();
			$('#exportar').attr('disabled', true);
			$('#razonSocial').attr("value", "");
			$('#razonSocial').val("");
			gRucValido = false;			
		}
		
		function justNumbers(e){
			var keynum = window.event ? window.event.keyCode : e.which;
			if ((keynum == 8))
			return true;
			 
			return /\d/.test(String.fromCharCode(keynum));
		}
		
		function justNumbersNumRUC(e) {
			gRucValido = false;
			$('#razonSocial').val("");
			deshabilitarBusquedaEspecifica();
			
			var keynum = window.event ? window.event.keyCode : e.which;
			if ((keynum == 8))
			return true;
			 
			return /\d/.test(String.fromCharCode(keynum));
		}
				
		function datoTipoFecha() {
			deshabilitarBusquedaEspecifica();
		
			if (((event.keyCode < 48) || (event.keyCode > 57)) && (event.keyCode != 47)) {  
				event.returnValue = false;
			} 
		}
		
		$('#fechaDes').on('paste', function(){
			  setTimeout(
				function(){
			    	var text = $('#fechaDesGenera').val();
			    	patron = /\d{2}\/\d{2}\/\d{4}/;
					if(!patron.test(text)){
						$('#fechaDesGenera').val("");
					}
			  }, 1);
		});
		
		$('#fechaHas').on('paste', function(){
			  setTimeout(
				function(){
			    	var text = $('#fechaHasGenere').val();
			    	patron = /\d{2}\/\d{2}\/\d{4}/;
					if(!patron.test(text)){
						$('#fechaHasGenere').val("");
					}
			  }, 1);
		});
		
		
		
		function habilitaCampoNumExp() {
			var numeroTipoExpediente = $('#selNumeroTipoExpediente').val();
			
			if(numeroTipoExpediente == ""){
				$("#numExpediente").attr('disabled', true);
				$('#numExpediente').val("");
			}else if(numeroTipoExpediente == "1"){
				$('#numExpediente').val("");
				$("#numExpediente").attr('maxlength', 17);//[eaguilar] (pto 53 de obs GENERALES)
				$("#numExpediente").attr('disabled', false);
			}else if(numeroTipoExpediente == "2"){
				$('#numExpediente').val("");
				$("#numExpediente").attr('maxlength', 14);
				$("#numExpediente").attr('disabled', false);
			}
		}
		
		function habilitaCampoFecha(){
			var tipoFecha = $('#selTipoFecha').val();
			if(tipoFecha == ""){
				$("#fechaDes").attr('disabled', true);
				$("#fechaHas").attr('disabled', true);
				$('#fechaDes').val("");
				$('#fechaHas').val("");
			}else{
				$("#fechaDes").attr('disabled', false);
				$("#fechaHas").attr('disabled', false);
			}
				
		}
		
		function cargarTiposExpedientes() {
			
			var codProceso = $('#selProceso').val();
			
			var dataEnvio = new Object();
			
			deshabilitarBusquedaEspecifica();
			
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
					
					url : '${pageContext.request.contextPath}/regDocInterno.htm?action=consultarProcesos&indCarga=1',
					type : 'POST',
					async : false,
					dataType : "json",
					data : JSON.stringify(dataEnvio),
					contentType : "application/json",
					mimeType : "application/json",
					//timeout : 5000,
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
		
		function  deshabilitarBusquedaEspecifica() {
			$('#numExpediente').val("");
			$("#numExpediente").attr('disabled', true);
			$('#selNumeroTipoExpediente').val("");
			$("#selNumeroTipoExpediente").attr('disabled', true);
		}
		
		function limpiarFechas(){
			if($('#selTipoFecha').val() == ''){
				$('#fechaDes').val("");
				$('#fechaHas').val("");
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
		
		//eaguilar 22 JUN se modifica para q responda al keyup y no al blur
		function validarRUCFueraFoco() {
			setTimeout(
					function(){
						var aux = $("#numRuc").val().replace(/[a-zA-Z\u00e1\u00e9\u00ed\u00f3\u00fa\u00f1\u00c1\u00c9\u00cd\u00d3\u00da\u00d1 ]/g, '*');
						
						if($("#numRuc").val().length == 11){
							gRucValido = false;
							numRuc = $("#numRuc").val();
							if(numRuc == ""){
								mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion01);
								mensajeEsepcion = ""
								mensajeEsepcion = excepciones.excepcion01;
							}else{
								if((numRuc != '' && (numRuc.length < 11)) || aux.indexOf('*') > -1) {
									mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion07);
									mensajeEsepcion = ""
									mensajeEsepcion = excepciones.excepcion07;
									$('#razonSocial').val("");
								}
								else{
									if(!valruc(numRuc)) {
										mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion07);
										gRucValido = false;
										mensajeEsepcion = ""
										mensajeEsepcion = excepciones.excepcion07;
										$('#razonSocial').val("");
									} else {
										verificarRUC(numRuc);
									}
								}
							}
						}
						else if(aux.indexOf('*') > -1){
							mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion07);
							mensajeEsepcion = ""
							mensajeEsepcion = excepciones.excepcion07;
							$('#razonSocial').attr("value", "");
						}
						else if($("#numRuc").val().length <11 && gRucValido && $('#razonSocial').val!=""){
							$('#razonSocial').val("");
						}
			}, 50);
		}
		
		function verificarRUC(numRuc) {
			var dataEnvio = new Object();
			
			dataEnvio.numRuc = numRuc;
			dataEnvio.codDependenciaBase = codDependenciaBase;//[oachahuic][PAS20165E210400270]
			
			$.ajax({
				
				url : '/ol-ti-iaexpvirtual-docinternos/regDocInterno.htm?action=cargarListaExpedientes&indCarga=2',
				type : 'POST',
				async : true,
				dataType : "json",
				data : JSON.stringify(dataEnvio),
				contentType : "application/json",
				mimeType : "application/json",
				//timeout : 5000,
				success : function(response) {
					
					var contribuyente = response.contribuyente;
					if (contribuyente.numRuc == null || contribuyente.numRuc == "" ) {

						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion25);
						gRucValido = false;
						mensajeEsepcion = ""
						mensajeEsepcion = excepciones.excepcion25;
						$('#razonSocial').attr("value", "");
						
					} else{
						
						 var codDependencia = contribuyente.codDependencia;
						 
						 if ( codDependenciaBase!="" &&  !(codDependenciaBase.substring(0,3) == codDependencia.substring(0,3))) {
							mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion24);
							gRucValido = false;
							mensajeEsepcion = ""
							mensajeEsepcion = excepciones.excepcion24;
							$('#razonSocial').attr("value", "");
							
							
						}else{
						var desRazonSocial = contribuyente.desRazonSocial;
							$('#razonSocial').val(desRazonSocial);//.change();
							gRucValido = true;
							
						
						}
					}
					
				},
				error : function(response) {
					
					mostrarPagError();
					
				}
				
			});
			return gRucValido;
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
		
		//Inicio [gangles 16/08/2016] Se agrega la columna del Expediente acumulador
		function consultarExpedientes(){
			
			listaExpedientes = [];
			var codDependencia = $('#selDependencia').val();
			var numAcumulador=$('#numAcumulador').val();
			
			var dataEnvio = new Object();
			
			dataEnvio.codDependencia = codDependencia;
			dataEnvio.numAcumulador = numAcumulador;
			
			 $.ajax({
				
				url : '${pageContext.request.contextPath}/regDocInterno.htm?action=cargarListaExpedientes&indCarga=3&flagBusquedaBoton='+flagBusquedaBoton,
				
				type : 'POST',
				async : true,
				dataType : "json",
				//data : $('#formDocInterno').serialize(),
				data : JSON.stringify(dataEnvio),
				contentType : "application/json",
				mimeType : "application/json",
				//timeout : 5000,
				success : function(response) {
					
					var msjError = response.msjError;
					if(msjError!="" && msjError!=undefined){
						$('#table').dataTable().fnClearTable();
						$('#table').dataTable().fnDraw();
						mostrarDlgInfo(titulos.tituloDefecto, msjError);
						return;
					}
					
					listaExpedientes = response.listadoExpendientes;
								
					if (listaExpedientes.length > 0) {						
						//INICIO LLRB
						if(listaExpedientes.codTipoExpediente== '430' ||listaExpedientes.codTipoExpediente== '431' ){
							$('#Expediente').show();
							$('#ExpedienteFiscalizacion').hide();
							$('#Expediente').dataTable().fnClearTable();
							$('#Expediente').dataTable().fnAddData(listaExpedientes); //carga registros en grilla	
							$('#Expediente').dataTable().fnDraw();
							$('#exportar').attr('disabled', false);
							
						
						}else{
							$('#Expediente').hide();
							$('#ExpedienteFiscalizacion').show();
							$('#ExpedienteFiscalizacion').dataTable().fnClearTable();
							$('#ExpedienteFiscalizacion').dataTable().fnAddData(listaExpedientes); //carga registros en grilla	
							$('#ExpedienteFiscalizacion').dataTable().fnDraw();
							$('#exportar').attr('disabled', false);
						}//FIN LLRB
			
					} else {
						
						$('#table').dataTable().fnClearTable();
						$('#table').dataTable().fnDraw();
						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion08);
						$('#exportar').attr('disabled', true);					
					}

				  },
				error : function(response) {
					
						$('#table').dataTable().fnClearTable();
						$('#table').dataTable().fnDraw();
						mostrarPagError();

				}

			});
		}	
			
			function consultarExpedientesAcumulados(numAcumulador) {
				limpiar();		
			
				$('#selTipoBusquedaExpediente').val("1");		
				$('#numAcumulador').val(numAcumulador);
				consultarExpedientes();
				
				$('#txtNumeroExpediente').val(numAcumulador);
				$('#numAcumulador').val('');
			}
			$('#table').DataTable( {
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
					{data :	"numExpedienteOrigen", render : function(data, row){
						 var aux = data;
						 return $('<a>')
						 	.css('text-decoration','underline')
						 	.attr({onclick : "seleccionaNroExpOrg('"+aux+"');" })
			                .text(data)
			                .wrap('<div></div>')
			                .parent()
			                .html(); 
			        }},
					{data :	"numExpedienteVirtual" },
					// Inicio [gangles 16/08/2016] Se agregan columnas del expediente acumulador.
					{data :	"numExpedienteAcumulador" ,sClass: 'centered alinearCentrado',"defaultContent":"", 
						render : function(data, type, row){								
							var link;
							if(data=="0") {
								return "";
							}else {
								//link = '<a onclick="obtenerDocumentos('+data+')">'+data+'</a>';										
								//link = '<a href="conRepControl.htm?action=cargarDocumentosExpAcumulado&numExpedienteAcumulador=">'+data+'</a>';
								//link=data;
								
								return $('<a>')
									.css('text-decoration','underline')
									.attr({	onclick :'consultarExpedientesAcumulados("'+row.numExpedienteAcumulador+'")'})
									.text(data)
									.wrap('<div></div>')
									.parent()
									.html();
			             	}											
					}},
					{data : "desIndicadorAcumulado", sClass: 'centered alinearCentrado',"defaultContent":""},
					// Fin [gangles 16/08/2016]
					{data :	"numRuc" },
					{data :	"desProceso" },
					{data :	"desTipoExpediente" },
					{data :	"fecRegistro" },
					{data :	"fechaDocumentoOrigen" },
					{data :	"strFecNotifOrigen"},
					{data :	"desForNotifOrigen" },						
					{data :	"desOrigen" }
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
				iDisplayLength		:	10,
				responsive			:	true,
				bLengthChange		: 	false,
				fnDrawCallback		:	function(oSettings) {
				//[ATORRESCH 2017-05-12] CONSIDERAR EL ANCHO DATATABLES									
				$("#table_wrapper").css("min-width", $('#table').width());

				$('#table_paginate').addClass('div100');
					if (oSettings.fnRecordsTotal() == 0) {
						$('#table_paginate').addClass('hiddenDiv'); //el footer de paginacion siempre tiene como ID "miTablaID_paginate"
																			//no se oculta automaticamente
																//cuando hay 0 registros, por eso se oculta anadiendo el class .hiddenDiv
			        }
					else {
						$('#table_paginate').removeClass('hiddenDiv');
						$('#tblPage').val(getPaginaTbl("table"));
					}
			    }
				        
		} );
			
			//INICIO LLRB 09012020
			$('#tableFiltro').DataTable( {
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
						{data :	"numExpedienteOrigen", render : function(data, row){
							 var aux = data;
							 return $('<a>')
							 	.css('text-decoration','underline')
							 	.attr({onclick : "seleccionaNroExpOrg('"+aux+"');" })
				                .text(data)
				                .wrap('<div></div>')
				                .parent()
				                .html(); 
				        }},
						/*{data :	"numExpedienteVirtual" },
						// Inicio [gangles 16/08/2016] Se agregan columnas del expediente acumulador.
						{data :	"numExpedienteAcumulador" ,sClass: 'centered alinearCentrado',"defaultContent":"", 
							render : function(data, type, row){								
								var link;
								if(data=="0") {
									return "";
								}else {
									//link = '<a onclick="obtenerDocumentos('+data+')">'+data+'</a>';										
									//link = '<a href="conRepControl.htm?action=cargarDocumentosExpAcumulado&numExpedienteAcumulador=">'+data+'</a>';
									//link=data;
									
									return $('<a>')
										.css('text-decoration','underline')
										.attr({	onclick :'consultarExpedientesAcumulados("'+row.numExpedienteAcumulador+'")'})
										.text(data)
										.wrap('<div></div>')
										.parent()
										.html();
				             	}											
						}},
						{data : "desIndicadorAcumulado", sClass: 'centered alinearCentrado',"defaultContent":""},
						// Fin [gangles 16/08/2016]*/
						{data :	"numRuc" },
						{data :	"desProceso" },
						{data :	"desTipoExpediente" },
						//{data :	"fecRegistro" },
						{data :	"fechaDocumentoOrigen" },
						/*{data :	"strFecNotifOrigen"},
						{data :	"desForNotifOrigen" },		*/				
						{data :	"desOrigen" }
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
					iDisplayLength		:	10,
					responsive			:	true,
					bLengthChange		: 	false,
					fnDrawCallback		:	function(oSettings) {
					//[ATORRESCH 2017-05-12] CONSIDERAR EL ANCHO DATATABLES									
					$("#table_wrapper").css("min-width", $('#table').width());

					$('#table_paginate').addClass('div100');
						if (oSettings.fnRecordsTotal() == 0) {
							$('#table_paginate').addClass('hiddenDiv'); //el footer de paginacion siempre tiene como ID "miTablaID_paginate"
																				//no se oculta automaticamente
																	//cuando hay 0 registros, por eso se oculta anadiendo el class .hiddenDiv
				        }
						else {
							$('#table_paginate').removeClass('hiddenDiv');
							$('#tblPage').val(getPaginaTbl("table"));
						}
				    }
					        
			} );
			//FIN LLRB

		/**/
		
		</script>
		
	</body>
	
</html>
