<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
	<head>		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=EDGE" />
		<meta name="viewport" content="initial-scale = 1.0, user-scalable = no,  width=device-width">
		<title>PEDIDO DE REPORTES DE EXPEDIENTES DE TRABAJO</title>
		
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
		
		.invisible{
			display: none !important;
		}
		
		.botonExaminar{
			height: 30px !important;
		}
		</style>
		
	</head>
	
	<body>
		<div id="container" class="container" style="width: 95%">
			
					<div class="panel panel-primary border-line">
						<div class="panel-heading align="center">
							<h3 class="panel-title" align="center">PEDIDO DE REPORTES DE EXPEDIENTES DE TRABAJO</h3>
						</div>
					</div>
					<div class="panel panel-primary border-lineSub">
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
								<input id="fecVista" name="fecVista" type="text" class="form-control text-center" disabled="disabled"/>
							</div>
						</div>
						<div class="row content-box">&nbsp;</div>
						<div class="row content-box">
							<div class="col-md-2">
								<label>Tipo Expediente: </label>
							</div>
							<div id="pnlSelTipoExpe" class="col-md-5">
								<select id="selTipoExpediente" name="selTipoExpediente" class="cboProceso form-control" onchange="cargarPlantillasAsociadas()">
									<option value="">Seleccione</option>
								</select> 
							</div>
						</div>
						<div class="row content-box">&nbsp;</div>
						<div class="row content-box">
							<div class="col-md-2">
								<label>Plantilla: </label>
							</div>
							<div id="pnlSelPlantillas" class="col-md-5">
								<select id="selPlanAsocExp" name="selPlanAsocExp" class="cboProceso form-control">
									<option value="">Seleccione</option>
								</select> 
							</div>
						</div>
						<div class="row content-box">&nbsp;</div>
						<div class="row content-box">
							<div class="col-md-3">
								<fieldset class="scheduler-border" style="margin : 15px 15px !important">
									<legend class="scheduler-border">Generación de Reporte</legend>
										<div class="row content-box">&nbsp;</div>
										<div class="row content-box">
											<div class="col-md-2">
												<input type="radio" id="genReport" name="genReport" value="01" onclick="generacionPedito()" checked="checked"/>
											</div>
											<div class="col-md-10">
												<label>Por RUC</label>
											</div>
										</div>
										<div class="row content-box">&nbsp;</div>
										<div class="row content-box">
											<div class="col-md-2">
												<input type="radio" id="genReport" name="genReport" value="02" onclick="generacionPedito()"/>
											</div>
											<div class="col-md-10">
												<label>Masivo</label>
											</div>
										</div>
										<div class="row content-box">&nbsp;</div>
								</fieldset>
							</div>
							<div class="col-md-7">
								<fieldset class="scheduler-border" style="margin : 15px 15px !important" id="idRuc">
									<legend class="scheduler-border">Por RUC:</legend>
										<div class="row content-box">&nbsp;</div>
										<div class="row content-box">
											<div class="col-md-4">
												<label>Nro. RUC:</label>
											</div>
											<div class="col-md-8">
												<!--<input id="numRuc" name="txtNumRuc" type="text" class="form-control" onkeypress="return justNumbers(event);" onblur="validarRUCFueraFoco();" maxlength="11"/>-->
												<input id="numRuc" name="txtNumRuc" type="text" class="form-control" onkeypress="return justNumbers(event);" maxlength="11"/>
											</div>
										</div>
										<div class="row content-box">&nbsp;</div>
								</fieldset>
								<fieldset class="scheduler-border" style="margin : 15px 15px !important" id="idMasivo">
									<legend class="scheduler-border">Masivo:</legend>
										<div class="row content-box">&nbsp;</div>
										<div class="row content-box">
											<div class="col-md-12" id="manejadorArchivo">
												<input name="docFisico" id="docFisico" type="file" onchange='validarArchivoUpload();' class="form-control uploadStyle botonExaminar" maxlength="50" onclick='limpiarArchivo();' />
											</div>
										</div>
										<div class="row content-box">&nbsp;</div>
								</fieldset>
							</div>
						</div>
						<div class="row content-box">&nbsp;</div>
						<div class="row content-box">
							<div class="col-md-12 text-right">
								<!--<button type="button" class="btn btn-primary" onclick="registrarPedido()">Registrar Pedido</button>-->
								<button type="button" class="btn btn-primary" onclick="validarRUCFueraFoco()">Registrar Pedido</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<input id="hiddNomArchivo" type="hidden" name="hiddNomArchivo"/>
		<form id="frmArchivoPlano" name="frmArchivoPlano" class="form-inline" role="form" method="post" enctype="multipart/form-data">
		</form>
		<form id="formPrincipal" class="form-horizontal" role="form">
		</form>
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
		var codDependenciaBase = ${codDependenciaBase};
		var mensajeError = "";
		var bool = false;
		var tamanoMaximo = ${tamanoMaximo};
		//Inicio nchavez 20/06/2016
		var hoverRegistrar=false;
		//Fin nchavez 20/06/2016
		
		
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
			//Inicio nchavez 20/06/2016
			$('#btnRegistrar').mouseover(function(){
				hoverRegistrar=true;
			});
			$('#btnRegistrar').mouseout(function(){
				hoverRegistrar=false;
			});
			//Fin nchavez 20/06/2016
			
			$('#idMasivo').addClass("invisible");
			
			//$.ajaxSetup({ scriptCharset: "UTF-8" , contentType: "application/json; charset=utf-8"});
				jQuery.support.cors = true;
				
				$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
				
				$('#tablaRepGeneral').DataTable( {
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
											id: data
											
										}
									).wrap('<div></div>').parent().html();
								}	
						},
						{data :	"numPlantilla", render : function(data, row){
							 var aux = data;
							 return $('<a>')
							 	.css('text-decoration','underline')
				                .attr({href : '${pageContext.request.contextPath}/regDocInterno.htm?action=seleccionaNroExpOrigen&aux=' + aux, onclick : "$('html').block({message: '<h1>Procesando</h1>'});" })
				                .text(data)
				                .wrap('<div></div>')
				                .parent()
				                .html();
								
				        }},
						{data :	"desPlantilla" },
						{data :	"fechaRegistro" },
						{data :	"estadoPlantilla" },
						{data :	"usuRegistro" },
						{data :	"usuRegistro" },
					],
					
					tableTools: {
				        "sSwfPath": "../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/TableTools/swf/copy_csv_xls.swf"
				    },
					ordering			:	false,
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
					$('#tablaRepGeneral_paginate').addClass('div100');
						if (oSettings.fnRecordsTotal() == 0) {
							$('#tablaRepGeneral_paginate').addClass('hiddenDiv'); //el footer de paginacion siempre tiene como ID "miTablaID_paginate"
																				//no se oculta automaticamente
																	//cuando hay 0 registros, por eso se oculta anadiendo el class .hiddenDiv
				        }
						else {
							$('#tablaRepGeneral_paginate').removeClass('hiddenDiv');
							
						}
				    }
					        
			});
			
			
			var $element = $('#selProceso');
			
			$.each(listadoProcesos, function(i, dato) {
			
				var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
				$element.append($option);
				
			});
			
			$('#fecVista').val(fecVista);
		
		});
		
		function cargarTiposExpedientes() {
			
			var codProceso = $('#selProceso').val();
			
			var dataEnvio = new Object();
			
			$('#selTipoExpediente').empty();
			
			$('#selPlanAsocExp').empty();
			
			$('#selTipoExpediente').append($('<option>', {
				value: '',
				text: 'Seleccione'
			}));
			
			$('#selPlanAsocExp').append($('<option>', {
				value: '',
				text: 'Seleccione'
			}));
			
			if (codProceso == "") {
				
				$('#selTipoExpediente').val('');
				$('#selPlanAsocExp').val('');
				
			} else {
				
				dataEnvio.codProceso = codProceso;
				
				$.ajax({
					
					url : '${pageContext.request.contextPath}/genePedidoReporte.htm?action=cargarBandPedidoRep&indCarga=1',
					type : 'POST',
					async : true,
					dataType : "json",
					data : JSON.stringify(dataEnvio),
					contentType : "application/json",
					mimeType : "application/json",
					success : function(response) {
						
						var listadoTiposExpendientes = response.listadoTiposExpendientes
						
						if (listadoTiposExpendientes.length > 0) {
							
							var $element = $('#selTipoExpediente');
							
							$.each(listadoTiposExpendientes, function(i, dato) {
							
								var $option = $("<option/>").attr("value", dato.codTipoExpediente).text(dato.desTipoExpediente);
								$element.append($option);
								
							});
														
						} else {
							
							mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion1);
							$('#selProceso').val("");
							
						}
						
					},
					error : function(response) {
						
						mostrarPagError();
						
					}
					
				});
				
			}
			
		}
				
		function cargarPlantillasAsociadas() {
			
			var codProceso = $('#selProceso').val();
			
			var codTipoExp = $('#selTipoExpediente').val();
			
			var dataEnvio = new Object();
			
			$('#selPlanAsocExp').empty();
			
			$('#selPlanAsocExp').append($('<option>', {
				value: '',
				text: 'Seleccione'
			}));
			
			if (codTipoExp == "") {
				
				$('#selPlanAsocExp').val('');
				
			} else {
				
				dataEnvio.codProceso = codProceso;
				dataEnvio.codTipoExp = codTipoExp;
				
				$.ajax({
					
					url : '${pageContext.request.contextPath}/genePedidoReporte.htm?action=cargarBandPedidoRep&indCarga=2',
					type : 'POST',
					async : true,
					dataType : "json",
					data : JSON.stringify(dataEnvio),
					contentType : "application/json",
					mimeType : "application/json",
					success : function(response) {
						
						var listadoPantillasPorExp = response.listadoPantillasPorExp
						
						if (listadoPantillasPorExp.length > 0) {
							
							var $element = $('#selPlanAsocExp');
							
							$.each(listadoPantillasPorExp, function(i, dato) {
							
								var $option = $("<option/>").attr("value", dato.numPlantilla).text(dato.desPlantilla);
								$element.append($option);
								
							});
														
						} else {
							
							mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion2);
							$('#selTipoExpediente').val("");
							
						}
						
					},
					error : function(response) {
						
						mostrarPagError();
						
					}
					
				});
				
			}
			
		}
		
		function generacionPedito(){
		
			var tipoGenera = $('input:radio[name=genReport]:checked').val();
			
			if(tipoGenera=="01"){
			
				$('#idMasivo').addClass("invisible");
				$('#idRuc').removeClass("invisible");
				$("#nomDocumentoFisico").val("");
				var control = $("#docFisico");
				control.replaceWith( control = control.clone( true ) );
				control.val(null);
				$("#docFisico").val("");
				
			}else if(tipoGenera=="02"){
			
				$('#idRuc').addClass("invisible");
				$('#idMasivo').removeClass("invisible");
				$("#numRuc").val("");
				$("#selNumOrigen").val("");
				$('#selNumOrigen').children().remove().end().append('<option selected value="whatever">Seleccione</option>') 
			}
			
		}
		
		function validarArchivoUpload() {
				
			$('html').block({message: '<h1>Procesando</h1>'});
			$("#docFisico").appendTo($('#frmArchivoPlano'));
			$('#frmArchivoPlano').submit();
			$("#docFisico").appendTo($('#manejadorArchivo'));
			$('html').unblock();
			
		}
		
		function limpiarArchivo() {
			$('#docFisico').wrap('<form>').closest('form').get(0).reset();
			$('#docFisico').unwrap();
		}
		
		$("#frmArchivoPlano").submit(function(e){
				
			var formObj = $(this);
			var iframeId = 'unique' + (new Date().getTime());
			
			var iframe = $('<iframe height="200" width="100" src="javascript:false;" name="'+iframeId+'" />');
			//var iframe = $('<iframe src="javascript:false;" name="'+iframeId+'" />');
			
			iframe.hide();
			formObj.attr('target', iframeId);
			formObj.attr('action', '${pageContext.request.contextPath}/genePedidoReporte.htm?action=validaSubirDocumento&indCarga=0');
			formObj.attr("enctype", "multipart/form-data");
			formObj.attr("encoding", "multipart/form-data");
			//formObj.attr("accept-charset","utf-8");
			iframe.appendTo('body');
			
			iframe.load(function(e){
				
				var doc = getDoc(iframe[0]);
				var docRoot = doc.body ? doc.body : doc.documentElement;
				var data = docRoot.innerHTML;
				var jsonDataString;
				var response;
				var indError;
				indError = false;
				if (data.indexOf("indError") >= 0) {
					indError = true;
				}
				
				if(indError) {
					
					mostrarPagError();
					
				} else {
					
					if (data.indexOf("<pre style") > -1) {
						
						jsonDataString = data.replace('<pre style="word-wrap: break-word; white-space: pre-wrap;">', '');
						jsonDataString = jsonDataString.replace('</pre>', '');
						
					} else if($('html').is('.ie8')) {
						jsonDataString = data.replace('<PRE>', '');
						jsonDataString = jsonDataString.replace('</PRE>', '');
					}else{
						jsonDataString = data.replace('<PRE>', '');
						jsonDataString = jsonDataString.replace('</PRE>', '');						
						jsonDataString = jsonDataString.replace('<pre>', '');
						jsonDataString = jsonDataString.replace('</pre>', '');
					}
					$('#hiddNomArchivo').val("");
					if(jsonDataString){
						response = jQuery.parseJSON(jsonDataString);
						
						if (response.fljError) {
							var control = $("#docFisico");
							control.replaceWith( control = control.clone( true ) );
							control.val(null);
							$("#docFisico").val("");
							if(response.error == 1){
								mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion16);	
							}else if(response.error == 2){
								mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion14);
							}else if(response.error == 3){
								mostrarDlgInfo(titulos.tituloDefecto, "El archivo seleccionado superará el tamaño máximo de "+tamanoMaximo+"MB permitido por subida, este archivo no será adjuntado.");
							}					
						} else {
							
							$('#hiddNomArchivo').val(response.nombreArchivoTemp);
							
						}
					}
				}
				
			});
			
		});
		
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
		
		function mostrarDlgErrores(titulo, msj){ 
			
			botones = [];
			
			var aceptar = $("<input/>").attr(
				{
					value: "Aceptar", 
					type: "button", 
					"class": "btn dlgButton btn-primary", 
					"data-dismiss" : "modal", 
					onclick : "verArchivoConErrroes()"
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
		
		function justNumbers(e){
			var keynum = window.event ? window.event.keyCode : e.which;
			if ((keynum == 8))
			return true;
			 
			return /\d/.test(String.fromCharCode(keynum));
		}
		
		function validarRUCFueraFoco() {
			var numRuc = $("#numRuc").val();
			var tipoGenera = $('input:radio[name=genReport]:checked').val();
			if(tipoGenera=="01"){
				if(numRuc == ""){
				
					mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion6);
					mensajeError = "";
					mensajeError = excepciones.excepcion6;
								
				}else{
				
					if(numRuc != '' && (numRuc.length < 11)) {
						
						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion3);
						$('#selNumOrigen').val("");
						mensajeError = "";
						mensajeError = excepciones.excepcion3;
						$('#selNumOrigen').val("");
						$('#selNumOrigen').children().remove().end().append('<option selected value="whatever">Seleccione</option>') 
																
					}else{
						
						if(!valruc(numRuc)) {
							
							mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion4);
							$('#selNumOrigen').val("");
							bool = false;
							mensajeError = "";
							mensajeError = excepciones.excepcion4;
							
						} else {
							
							verificarRUC(numRuc);
							
						}
						
					}
				
				}
			}else{
				registrarPedido();
			}
			//Inicio nchavez 20/06/2016
			/*if(hoverRegistrar){
				registrarPedido();	
			};*/
			//Fin nchavez 20/06/2016
		}
		
		function verificarRUC(numRuc) {
			var dataEnvio = new Object();
			
			dataEnvio.numRuc = numRuc;
			
			$.ajax({
				
				url : '/ol-ti-iaexpvirtual-repexptrab/genePedidoReporte.htm?action=cargarBandPedidoRep&indCarga=3',
				type : 'POST',
				async : true,
				dataType : "json",
				data : JSON.stringify(dataEnvio),
				contentType : "application/json",
				mimeType : "application/json",
				success : function(response) {
					
					var contribuyente = response.contribuyente;
					if (contribuyente == null || contribuyente.numRuc == "") {

						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion4);
						$('#selNumOrigen').val("");
						mensajeError = "";
						mensajeError = excepciones.excepcion4;
						bool = false;
						
					} else{
						
						 var codDependencia = contribuyente.codDependencia;
						 
						 if (codDependenciaBase.substring(0,3) == codDependencia.substring(0,3)) {
							var listadoExpendientes = response.listadoExpendientes
							if(listadoExpendientes != undefined){
								bool = true;
								registrarPedido();
							}else{
							
								mostrarDlgInfo(titulos.tituloDefecto, "El Nro. RUC ingresado no cuenta con Expedientes Virtuales.");
								mensajeError = "";
								mensajeError = "El Nro. RUC ingresado no cuenta con Expedientes Virtuales.";
								bool = false;
							
							}
						}else{
						
							mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion5);
							mensajeError = "";
							mensajeError = excepciones.excepcion5;
							bool = false;
						
						}
					}
					
				},
				error : function(response) {
					
					mostrarPagError();
					
				}
				
			});
			return bool;
		}
		
		/*function buscarNumExpOrigenRuc(numRuc) {
			$('#selNumOrigen').val("");
			$('#selNumOrigen').children().remove().end().append('<option selected value="whatever">Seleccione</option>') 
			
			var dataEnvio = new Object();
			dataEnvio.numRuc = numRuc;
			
			$.ajax({
				
				url : '/ol-ti-iaexpvirtual-repexptrab/genePedidoReporte.htm?action=cargarBandPedidoRep&indCarga=4',
				type : 'POST',
				async : true,
				dataType : "json",
				data : JSON.stringify(dataEnvio),
				contentType : "application/json",
				mimeType : "application/json",
				success : function(response) {
					
					var listadoExpendientes = response.listadoExpendientes;
					
					if (listadoExpendientes.length > 0) {
							
						var $element = $('#selNumOrigen');
						
						$.each(listadoExpendientes, function(i, dato) {
						
							var $option = $("<option/>").attr("value", dato.numExpedienteVirtual).text(dato.numExpedienteOrigen);
							$element.append($option);
							
						});
													
					} else {
						
						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion7);
						$('#selNumOrigen').val("");
						
						
					}
					
				},
				error : function(response) {
					
					mostrarPagError();
					
				}
				
			});
			
		}*/
		
		function mostrarPagError() {
			
			var formURL = '${pageContext.request.contextPath}/error.htm?action=cargarPaginaError';
			document.forms.formPrincipal.action = formURL;
			document.forms.formPrincipal.method = "post";
			document.forms.formPrincipal.submit();
			
		}
		
		function registrarPedido(){
			var codProceso = $("#selProceso").val();
			var desProceso = "Debe seleccionar el Proceso para poder registrar el Pedido.";
			var codTipoExp = $('#selTipoExpediente').val();
			var desTipoExpediente = "Debe seleccionar el Tipo Expediente para poder registrar el Pedido.";
			var codPlantilla = $('#selPlanAsocExp').val();
			var desPlantilla = "Debe seleccionar una Plantilla para poder registrar el Pedido.";
			
			var tipoGenera = $('input:radio[name=genReport]:checked').val();
			var documento = $("#docFisico").val();
			var numRuc = $('#numRuc').val();
			
			if(codProceso==""){
				mostrarDlgInfo(titulos.tituloDefecto, desProceso);
				return;
			}
			if(codTipoExp==""){
				mostrarDlgInfo(titulos.tituloDefecto, desTipoExpediente);
				return;
			}
			if(codPlantilla==""){
				mostrarDlgInfo(titulos.tituloDefecto, desPlantilla);
				return;
			}
			
			if(tipoGenera=="01"){
				if(numRuc!=""){
					if(bool==true){
						ejecutarRegistro();
					}else{
						mostrarDlgInfo(titulos.tituloDefecto, mensajeError);
						return;
					}
				}else{
					mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion6);
					return;
				}
			}else if(tipoGenera=="02"){
				if(documento!=""){
					ejecutarRegistro();
				}else{
					mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion11);
					return;
				}
			}
		}
		
		function ejecutarRegistro(){
		
			var codProceso = $("#selProceso").val();
			var codTipoExp = $('#selTipoExpediente').val();
			var codPlantilla = $('#selPlanAsocExp').val();
			var tipoGenera = $('input:radio[name=genReport]:checked').val();
			var nomArchivo = $('#hiddNomArchivo').val();
			var numRuc = $('#numRuc').val();
			
			var dataEnvio = new Object();
			dataEnvio.codProceso = codProceso;
			dataEnvio.codTipoExp = codTipoExp;
			dataEnvio.codPlantilla = codPlantilla;
			dataEnvio.tipoGenera = tipoGenera;
						
			if(tipoGenera=='01'){
				
				dataEnvio.numRuc = numRuc;
				$.ajax({
				
					url : '/ol-ti-iaexpvirtual-repexptrab/genePedidoReporte.htm?action=validaDocumento&indCarga=0',
					type : 'POST',
					async : true,
					dataType : "json",
					data : JSON.stringify(dataEnvio),
					contentType : "application/json",
					mimeType : "application/json",
					success : function(response) {
						
						var fljError = response.fljError;
						
						if (fljError) {
					
							mostrarDlgInfo(titulos.tituloDefecto, "Sucedio algún problema al realizar el registro del pedido.");
											
						} else {
							var numPedido = response.numPedido;
							var mensaje = excepciones.excepcion13;
							mensaje = mensaje.replace("{0}", numPedido)
							mostrarDlgInfo(titulos.tituloDefecto, mensaje);
							$("#selProceso").val("");
							$('#selTipoExpediente').val("");
							$('#selPlanAsocExp').val("");
							$('#hiddNomArchivo').val("");
							$('#numRuc').val("");
							$("#docFisico").val("");
						}
						var input = $("#docFisico");
						input.replaceWith(input.val('').clone(true));	
						
					},
					error : function(response) {
						
						mostrarPagError();
						var input = $("#docFisico");
						input.replaceWith(input.val('').clone(true));	
					}
					
				});
			}else if(tipoGenera=='02'){
				
				dataEnvio.nomArchivo = nomArchivo;
				$.ajax({
				
					url : '/ol-ti-iaexpvirtual-repexptrab/genePedidoReporte.htm?action=validaDocumento&indCarga=1',
					type : 'POST',
					async : true,
					dataType : "json",
					data : JSON.stringify(dataEnvio),
					contentType : "application/json",
					mimeType : "application/json",
					success : function(response) {
						
						var fljError = response.fljError;
						
						var indicador = response.indicador;
						
						if (fljError) {
							if(indicador==1){
								mostrarDlgErrores(titulos.tituloDefecto, "El archivo adjuntado contiene errores, para mayor informaci&oacute;n verifique el archivo que se descargara a continuaci&oacute;n.");
								$("#docFisico").val("");
							}else if(indicador==2){
								mostrarDlgInfo(titulos.tituloDefecto, "Sucedio alg&uacute;n problema al realizar el registro del pedido.");
							}
												
						} else {
							var numPedido = response.numPedido;
							var mensaje = excepciones.excepcion13;
							mensaje = mensaje.replace("{0}", numPedido)
							mostrarDlgInfo(titulos.tituloRegistro, mensaje);
							$("#selProceso").val("");
							$('#selTipoExpediente').val("");
							$('#selPlanAsocExp').val("");
							$('#hiddNomArchivo').val("");
							$('#numRuc').val("");
							$("#docFisico").val("");
							var input = $("#docFisico");
							input.replaceWith(input.val('').clone(true));	
						}
						
					},
					error : function(response) {
						
						mostrarPagError();
						var input = $("#docFisico");
						input.replaceWith(input.val('').clone(true));	
					}
					
				});
			}
			
					
		}
		
		function verArchivoConErrroes() {
			$('#modalDlg').modal('hide')
			var formURL = 'genePedidoReporte.htm?action=verArchivo';
			document.forms.formPrincipal.action = formURL;
			document.forms.formPrincipal.method = "POST";
			$('html').block({message: '<h1>Procesando</h1>'});
			document.forms.formPrincipal.submit();
			$('html').unblock();
		}
		
		</script>
		
	</body>
	
</html>