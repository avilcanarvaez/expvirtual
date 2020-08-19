<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="es">

	<head>
		
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=EDGE" />
		<meta name = "viewport" content = "initial-scale = 1.0, user-scalable = no,  width=device-width">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>DETALLE DE DOCUMENTOS INTERNOS</title>
		
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
		
		.cimg {
			    margin-left: 35% !important;
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
		
		.panel-scroll {
			max-height: auto;
			min-height: 0px;
			overflow-y: auto;
			overflow-x: auto;
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
			
		 @font-face {
		  font-family: 'Glyphicons Halflings';
		  src: url('../a/js/libs/bootstrap/3.3.2/fonts/glyphicons-halflings-regular.eot');
		  src: url('../a/js/libs/bootstrap/3.3.2/fonts/glyphicons-halflings-regular.eot?#iefix') format('embedded-opentype'), url('../a/js/libs/bootstrap/3.3.2/fonts/glyphicons-halflings-regular.woff') format('woff'), url('../a/js/libs/bootstrap/3.3.2/fonts/glyphicons-halflings-regular.ttf') format('truetype'), url('../a/js/libs/bootstrap/3.3.2/fonts/glyphicons-halflings-regular.svg#glyphicons-halflingsregular') format('svg');
		}	
		</style>
		
	</head>
	
	<body>
		
		<div class="container">
			<div>
				<div class="row">
					<div class="panel panel-primary">
						<div class="panel-heading align="center">
							<h3 class="panel-title" align="center">DETALLE DE DOCUMENTOS INTERNOS</h3>
							<form id="frmPrincipal" class="form-horizontal" role="form">
							</form>
							
						</div>
						
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
						<legend class="scheduler-border">Datos del Expediente</legend>
						  <div class="form-group" id="DetalleDocumento">	
							<div class="panel-body">
								<div class="row content-box">
									<div class="col-md-2">
										<label>RUC: </label>
									</div>
									<div id="txtCampoRuc" class="col-md-2">
										<input id="numRuc" name="numeroRuc" type="text" class="form-control" disabled="disabled"/>
									</div>
									<div id="txtCampoRazonSocial" class="col-md-8">
										<input id="razonSocial" name="razonSocialRuc" type="text" class="form-control" disabled="disabled"/>
									</div>
								</div>
								<div class="row content-box">&nbsp;</div>
								<div class="row content-box">
									<div class="col-md-2">
										<label>N° Expediente Origen:</label>
									</div>
									<div id="txtCampo1" class="col-md-2">
										<input id="numExpeOrigen" name="numeroExpeOrigen" type="text" class="form-control" disabled="disabled"/>
									</div>
									<div class="col-md-3">
										<label>N° Expediente Virtual:</label>
									</div>
									<div id="txtCampo2" class="col-md-2">
										<input id="numExpeVirtual" name="numeroExpeVirtual" type="text" class="form-control" disabled="disabled"/>
									</div>
									<div class="col-md-1">
										<label>Estado:</label>
									</div>
									<div id="txtCampo3" class="col-md-2">
										<input id="estExpediente" name="estadoExpediente" type="text" class="form-control" disabled="disabled"/>
									</div>
								</div>
								<div class="row content-box">&nbsp;</div>
								<div class="row content-box">
									<div class="col-md-2">
										<label>Proceso:</label>
									</div>
									<div id="txtCampo4" class="col-md-2">
										<input id="desProceso" name="descripcionProceso" type="text" class="form-control" disabled="disabled"/>
									</div>
									<div class="col-md-3">
										<label>Tipo de expediente:</label>
									</div>
									<div id="txtCampo5" class="col-md-2">
										<input id="desExpediente" name="descripcionExpediente" type="text" class="form-control" disabled="disabled"/>
									</div>
									<div class="col-md-1">
										<label>Origen:</label>
									</div>
									<div id="txtCampo6" class="col-md-2">
										<input id="desOrigen" name="descripcionOrigen" type="text" class="form-control" disabled="disabled"/>
									</div>
								</div>
								<div class="row content-box">&nbsp;</div>
								<div class="row content-box">
									<div class="col-md-2">
										<label>Fecha de generaci&oacute;n:</label>
									</div>
									<div id="txtCampo7" class="col-md-2">
										<input id="fechaVirtual" name="fechaExpedienteVirtual" type="text" class="form-control" disabled="disabled"/>
									</div>
									<div class="col-md-3">
										<label>Fecha de documento origen:</label>
									</div>
									<div id="txtCampo8" class="col-md-2">
										
										<input id="fechaOrigen" name="fechaOrigenExpediente" type="text" class="form-control" disabled="disabled"/>
									</div>
								</div>
								</div>
							</div>
							<!-- INICIO[LLRB 14012020] -->
							<div class="form-group" id="DetalleDocumentoFiltro">	
							<div class="panel-body">
								<div class="row content-box">
									<div class="col-md-2">
										<label>RUC: </label>
									</div>
									<div id="txtCampoRuc" class="col-md-2">
										<input id="numRuc_f" name="numeroRuc" type="text" class="form-control" disabled="disabled"/>
									</div>
									<div id="txtCampoRazonSocial" class="col-md-8">
										<input id="razonSocial_f" name="razonSocialRuc" type="text" class="form-control" disabled="disabled"/>
									</div>
								</div>
								<div class="row content-box">&nbsp;</div>
								<div class="row content-box">
									<div class="col-md-2">
										<label>N° Expediente:</label>
									</div>
									<div id="txtCampo1" class="col-md-2">
										<input id="numExpeOrigen_f" name="numeroExpeOrigen" type="text" class="form-control" disabled="disabled"/>
									</div>

									<div class="col-md-1">
										<label>Estado:</label>
									</div>
									<div id="txtCampo3" class="col-md-2">
										<input id="estExpediente_f" name="estadoExpediente" type="text" class="form-control" disabled="disabled"/>
									</div>
									<div class="col-md-2">
										<label>Proceso:</label>
									</div>
									<div id="txtCampo4" class="col-md-2">
										<input id="desProceso_f" name="descripcionProceso" type="text" class="form-control" disabled="disabled"/>
									</div>
								</div>
								<div class="row content-box">&nbsp;</div>
								<div class="row content-box">
									<div class="col-md-2">
										<label>Tipo de expediente:</label>
									</div>
									<div id="txtCampo5" class="col-md-2">
										<input id="desExpediente_f" name="descripcionExpediente" type="text" class="form-control" disabled="disabled"/>
									</div>
									<div class="col-md-1">
										<label>Origen:</label>
									</div>
									<div id="txtCampo6" class="col-md-2">
										<input id="desOrigen_f" name="descripcionOrigen" type="text" class="form-control" disabled="disabled"/>
									</div>
									<div class="col-md-2">
										<label>Fecha de documento origen:</label>
									</div>
									<div id="txtCampo8" class="col-md-2">
										<input id="fechaOrigen_f" name="fechaOrigenExpediente" type="text" class="form-control" disabled="disabled"/>
									</div>
								</div>
								
								
								</div>
							</div>
						  <!-- FIN[LLRB 14012020] -->
						</fieldset>
						
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border">Documentos adjuntados al expediente</legend>
								<div class="panel-body">
									<div class="border-line panel-scroll">
										<table id="tableDocumento" class="table display" cellspacing="0" style="width: 100%;"><!--table table-striped table-bordered-->
											<thead>
												<tr class="active">
													<th width="3%">N°</th>
													<th width="17%">Tipo de Documento</th>
													<th width="15%">N&uacute;mero de Documento</th>
													<th width="15%">Fecha de Documento</th>
													<!--[PAS20191U210500144] Inicio - Campos solo visibles para Exp de Fiscalizacion-->
													<th width="15%" class="campoExtra">Visible al Contrib.</th>
													<th width="15%" class="campoExtra">Con Reserva Tributaria</th>
													<!--[PAS20191U210500144] Fin -->
													<th width="15%">Fecha de Notificaci&oacute;n</th>
													<th width="15%">Forma de Notificaci&oacute;n</th>
													<th width="17%">Nombre del Archivo</th>
													<th width="8%">Origen</th>
													<!--[PAS20191U210500144] Inicio - Campos solo visibles para Exp de Fiscalizacion-->
													<th width="15%" class="campoExtra">Tipo Doc. Relacionado</th>
													<th width="15%" class="campoExtra">Núm. Doc. Relacionado</th>
													<!--[PAS20191U210500144] Fin -->
													<th width="17%">Profesional Responsable</th>
													<th width="8%">Ver archivo</th>
												</tr>
											</thead>
										</table>
									</div>	
								</div>
						</fieldset>
						
						<div style="margin : 15px 15px !important">
							<div class="row content-box">
								<div class="col-md-3">
								</div>
								<div class="col-md-2 text-center">
									<button type="button" class="btn btn-primary" onclick="exportar()">Exportar a Excel</button>
								</div>
								<div class="col-md-2 text-center">
									<button type="button" class="btn btn-primary" onclick="anadirDoc()">Añadir Doc.</button>
								</div>
								<div class="col-md-2 text-center">
									<button type="button" class="btn btn-primary" onclick="volver()">Regresar</button>
								</div>
								<div class="col-md-3">
								</div>
							</div>
						</div>
						<form id="formPrincipal" class="form-horizontal" role="form">
							<input id="hiddenListaExp" type="hidden" name="hiddenListaExp"/>		
							<input id="hiddenNumRuc" type="hidden" name="hiddenNumRuc"/>
							<input id="hiddenRazonSocial" type="hidden" name="hiddenRazonSocial"/>
							<input id="hiddenNumExpOrigen" type="hidden" name="hiddenNumExpOrigen"/>
							<input id="hiddenNumExpVirtual" type="hidden" name="hiddenNumExpVirtual"/>
							<input id="hiddenEstExpediente" type="hidden" name="hiddenEstExpediente"/>
							<input id="hiddenTipoProceso" type="hidden" name="hiddenTipoProceso"/>
							<input id="hiddenTipoExpediente" type="hidden" name="hiddenTipoExpediente"/>
							<input id="hiddenDescOrigen" type="hidden" name="hiddenDescOrigen"/>
							<input id="hiddenFechaGeneracion" type="hidden" name="hiddenFechaGeneracion"/>
							<input id="hiddenFechaOrigen" type="hidden" name="hiddenFechaOrigen"/>
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
		var datosExpedientes = ${datosExpedientes};
		var fechaRegistro = ${fechaRegistro};
		var fechaOrigenDoc = ${fechaOrigenDoc};
		var lstDocExp = ${lstDocExp};
		$(document).ready(function() {
		
			//$.ajaxSetup({ scriptCharset: "UTF-8" , contentType: "application/json; charset=utf-8"});
				jQuery.support.cors = true;
				$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
				
				$('#tableDocumento').DataTable( {
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
						{data :	"desTipdoc"},
						{data :	"numDoc" },
						{data :	"fechaVista" },
						//[PAS20191U210500144] Inicio 
						{data :	"indVisible" },     
						{data :	"indReserTrib" },
						//[PAS20191U210500144] Fin
						{data :	"fecNotifVista" },
						{data :	"desForNotif" },
						{data :	"descArchivo" },
						{data :	"desOrigen" },
						//[PAS20191U210500144] Inicio
						{data :	"tipDocRel" },     
						{data :	"numDocRel" },
						//[PAS20191U210500144] Fin
						{data :	"nomPersonaCargo" },
						{data :	"codIdecm", 
							render : function(data, type, row){
									link = '<a href="regDocInterno.htm?action=descargarArchivo&codIdentificadorECM='+ data +'&nombreArchivo='+row.descArchivo+'"role="button" class="cimg glyphicon glyphicon-download-alt" target=_blank></a>';
								return link;	
							}
						}
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
						var a = $('#tableDocumento').width();
						$("#tableDocumento_wrapper").css("min-width", a);
					
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
					        
			} );
			//INICIO FIN[LLRB 14012020]
			if((datosExpedientes.codTipoExpediente == 430 || datosExpedientes.codTipoExpediente == 431) ) {
				iniciarCamposFiltro();
			}else{
				iniciarCampos();
			}
			
			//FIN FIN[LLRB 14012020]	
			

			$('#tableDocumento').dataTable().fnClearTable();
			if(lstDocExp.length > 0) $('#tableDocumento').dataTable().fnAddData(lstDocExp); //eaguilar 30 JUN valida q haya documentos para renderizar la tabla
			//[PAS20191U210500144] Inicio
			if(!(datosExpedientes.codTipoExpediente == 430 || datosExpedientes.codTipoExpediente == 431) ) {
				//Si el expediente es diferente a Fiscalizacion Definitiva/Parcial y Cruces oculto campos:
				$(".campoExtra").hide();
				$('td:nth-child(5)').toggle();
				$('td:nth-child(6)').toggle();
				$('td:nth-child(11)').toggle();
				$('td:nth-child(12)').toggle();
			}			
			//[PAS20191U210500144] Fin 
			
		});
		function iniciarCampos(){
			$('#DetalleDocumento').show();
			$('#DetalleDocumentoFiltro').hide();
			
			$('#numRuc').attr("value", datosExpedientes.numRuc);
			$('#razonSocial').attr("value", datosExpedientes.desRazonSocial);
			$('#numExpeOrigen').attr("value", datosExpedientes.numExpedienteOrigen);
			$('#numExpeVirtual').attr("value", datosExpedientes.numExpedienteVirtual);
			$('#estExpediente').attr("value", datosExpedientes.desEstado);
			$('#desProceso').attr("value", datosExpedientes.desProceso);
			$('#desExpediente').attr("value", datosExpedientes.desTipoExpediente);
			$('#desOrigen').attr("value", datosExpedientes.desOrigen);
			$('#fechaOrigen').attr("value", fechaOrigenDoc);
			$('#fechaVirtual').attr("value", fechaRegistro);
		}
		//INICIO FIN[LLRB 14012020]
		function iniciarCamposFiltro(){
			$('#DetalleDocumentoFiltro').show();
			$('#DetalleDocumento').hide();
			
			$('#numRuc_f').attr("value", datosExpedientes.numRuc);
			$('#razonSocial_f').attr("value", datosExpedientes.desRazonSocial);
			$('#numExpeOrigen_f').attr("value", datosExpedientes.numExpedienteOrigen);               
			$('#estExpediente_f').attr("value", datosExpedientes.desEstado);
			$('#desProceso_f').attr("value", datosExpedientes.desProceso);
			$('#desExpediente_f').attr("value", datosExpedientes.desTipoExpediente);
			$('#desOrigen_f').attr("value", datosExpedientes.desOrigen);
			$('#fechaOrigen_f').attr("value", fechaOrigenDoc);			
		}
		//FIN FIN[LLRB 14012020]	
		
		function volver(){
			
			var formURL = 'regDocInterno.htm?action=consultarProcesos&indCarga=0';
			document.forms.formPrincipal.action = formURL;
			document.forms.formPrincipal.method = "POST";
			$('html').block({message: '<h1>Procesando</h1>'});
			document.forms.formPrincipal.submit();
		}
		
		function anadirDoc(){
			
			var numExpeOrigen = $('#numExpeOrigen').val();
			var numExpeOrigenf = $('#numExpeOrigen_f').val();
			if(numExpeOrigen !=''){
			var formURL = 'regDocInterno.htm?action=seleccionaAgregarDocumento&aux='+ numExpeOrigen;
			}else{
				var formURL = 'regDocInterno.htm?action=seleccionaAgregarDocumento&aux='+ numExpeOrigenf;	
			}
			document.forms.formPrincipal.action = formURL;
			document.forms.formPrincipal.method = "POST";
			$('html').block({message: '<h1>Procesando</h1>'});
			document.forms.formPrincipal.submit();
		}
		
		function exportar(){
			var dataExp = $('#tableDocumento').dataTable().fnGetData();
			if(dataExp.length > 0){
				if($('#numExpeOrigen').val()!=''){			
					var listaCadena = JSON.stringify(dataExp);
					var formURL = 'regDocInterno.htm?action=exportarExcelExpDoc';
					document.forms.formPrincipal.action = formURL;
					document.forms.formPrincipal.method = "POST";
				}else{
					var listaCadena = JSON.stringify(dataExp);
					var formURL = 'regDocInterno.htm?action=exportarExcelExpDocFisca';
					document.forms.formPrincipal.action = formURL;
					document.forms.formPrincipal.method = "POST";
				}
				//INICIO LLRB 16/01/2020
				if (($('#numRuc').val())!=''){
				/*Obtener Paramtros Inicio*/
				var numRuc = $('#numRuc').val();
				var razonSocial = $('#razonSocial').val();
				var numExpeOrigen = $('#numExpeOrigen').val();
				var numExpeVirtual = $('#numExpeVirtual').val();
				var estExpediente = $('#estExpediente').val();
				var desProceso = $('#desProceso').val();
				var desExpediente = $('#desExpediente').val();
				var desOrigen = $('#desOrigen').val();
				var fechaOrigen = $('#fechaOrigen').val();
				var fechaVirtual = $('#fechaVirtual').val();
				}else{
					/*Obtener Paramtros Inicio*/
					var numRuc = $('#numRuc_f').val();
					var razonSocial = $('#razonSocial_f').val();
					var numExpeOrigen = $('#numExpeOrigen_f').val();
					var estExpediente = $('#estExpediente_f').val();
					var desProceso = $('#desProceso_f').val();
					var desExpediente = $('#desExpediente_f').val();
					var desOrigen = $('#desOrigen_f').val();
					var fechaOrigen = $('#fechaOrigen_f').val();
				}
				/*Obtener Paramtros fin*/
				/*limpiar campos hdn Inicio*/
				$('#hiddenListaExp').val('');
				$('#hiddenNumRuc').val('');
				$('#hiddenRazonSocial').val('');
				$('#hiddenNumExpOrigen').val('');
				$('#hiddenNumExpVirtual').val('');
				$('#hiddenEstExpediente').val('');
				$('#hiddenTipoProceso').val('');
				$('#hiddenTipoExpediente').val('');
				$('#hiddenDescOrigen').val('');
				$('#hiddenFechaGeneracion').val('');
				$('#hiddenFechaOrigen').val('');
				/*limpiar campos hdn Inicio*/
				/*setear campos hdn Inicio*/
				$('#hiddenListaExp').val(listaCadena);
				$('#hiddenNumRuc').val(numRuc);
				$('#hiddenRazonSocial').val(razonSocial);
				$('#hiddenNumExpOrigen').val(numExpeOrigen);
				$('#hiddenNumExpVirtual').val(numExpeVirtual);
				$('#hiddenEstExpediente').val(estExpediente);
				$('#hiddenTipoProceso').val(desProceso);
				$('#hiddenTipoExpediente').val(desExpediente);
				$('#hiddenDescOrigen').val(desOrigen);
				$('#hiddenFechaGeneracion').val(fechaVirtual);
				$('#hiddenFechaOrigen').val(fechaOrigen);
				/*setear campos hdn fin*/
				

				$('html').block({message: '<h1>Procesando</h1>'});
				document.forms.formPrincipal.submit();
				$('html').unblock();
			}
		 
		}
		</script>
		
	</body>
	
</html>
