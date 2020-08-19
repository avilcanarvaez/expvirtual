<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="es">

	<head>
		
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=EDGE" />
		<meta name = "viewport" content = "initial-scale = 1.0, user-scalable = no,  width=device-width">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>AGREGAR ACCESOS AL EXPEDIENTE</title>
		
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
		
		<div class="container" id="containerRegistro">
			<div>
				<div class="row">
					<div class="panel panel-primary">
						<div class="panel-heading align="center">
							<h3 class="panel-title" align="center">AGREGAR ACCESOS AL EXPEDIENTE</h3>
							
							<form id="frmPrincipal" class="form-horizontal" role="form">
							<input id="dataOculta" name="dataOculta" type="hidden" class="form-control form-control-single"/>
							<input id="hdnDescripcion" name="hdnDescripcion" type="hidden"/>
							</form>
							
						</div>
						
						
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
						<legend class="scheduler-border">Datos del Expediente</legend>
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
										<label>N° Expediente:</label>
									</div>
									<div id="txtCampo1" class="col-md-2">
										<input id="numExpeOrigen" name="numeroExpeOrigen" type="text" class="form-control" disabled="disabled"/>
									</div>
								
									
									
								</div>

								</div>
						</fieldset>
						
						
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
						<legend class="scheduler-border">Datos de la Solicitud</legend>
							<div class="panel-body">
							
							
								<div class="row content-box">
									<div class="col-md-2">
										<label>Número de Solicitud de Acceso: </label>
									</div>
									<div id="txtnumSoli" class="col-md-2">
										<input id="numSoli" name="numSoli" type="text" class="form-control" onkeypress="return permitirAlfaNumerico(event);"/>
									</div>
									<div class="col-md-2">
										<label>Fecha de solicitud:</label>
									</div>
									<div id="txtFechaSoli" class="input-group date col-md-2">
										<input id="fechaSoli" name="fechaSoli" type="text" class="form-control form-control-single" maxlength="10" readonly="readonly" onchange="validarTipoFecha()"/>
										<span class="input-group-addon">
										<span class="fa fa-calendar"></span>
										</span>
									</div>
									<div class="col-md-1">
										
									</div>
									<div id="txtCampo3" class="col-md-2">
									</div>
								</div>
								
								
								<div class="row content-box">&nbsp;</div>
								
								<div class="row content-box">
									<div class="col-md-2">
										<label>Motivo:</label>
									</div>
									<div id="txtCampo1" class="col-md-2">
										<select id="selMotivo" name="selMotivo" class="cboProceso form-control">
											<option value="">Seleccione</option>
					<option value="01">Ley de Transparencia</option>
					<option value="02">Tribunal Fiscal</option>
					<option value="03">Policía Nacional</option>
					<option value="04">Poder Judicial</option>
					<option value="05">Fiscalía</option>
					<option value="06">Indecopi</option>
					<option value="07">Procuraduría</option>
					<option value="08">Otros</option>
										</select>
									</div>
									<div class="col-md-2">
										<label>Tipo de sensibilidad:</label>
									</div>
									<div id="txtCampo2" class="col-md-2">
										<select id="selTipoSens" name="selTipoSens" class="cboProceso form-control">
											<option value="">Seleccione</option>
											
					<option value="01">Público</option>
					<option value="02">Interno</option>
					<option value="03">Confidencial</option>
					<option value="04">Restringido</option>
										</select>
									</div>
									<div class="col-md-1">
										
									</div>
									<div id="txtCampo3" class="col-md-2">
									</div>
								</div>
								
								
								<div class="row content-box">&nbsp;</div>
								<div class="row content-box">
									<div class="col-md-2">
										<label>Código de Registro:</label>
									</div>
									<div id="txtCampo4" class="col-md-2">
										<input id="codigoReg" name="codigoReg" type="text" class="form-control"/>
										<input id="btnBuscar" type="button" class="btn btn-primary" onclick="buscar()" value="Buscar" />
									</div>
									
									<div class="col-md-2">
										<label>Apellidos y Nombres:</label>
									</div>
									<div id="txtCampo5" class="col-md-6">
										<input id="desColab" name="desColab" type="text" class="form-control" disabled="disabled"/>
									</div>

								</div>
								
								<div class="row content-box">&nbsp;</div>
								
								<div class="row content-box">
								
									<div class="col-md-2">
										<label></label>
									</div>
									<div id="txtCampo4" class="col-md-2">
										<input id="campoOculto" name="campoOculto" type="text" class="form-control" disabled="disabled"/>
										
									</div>
									
									<div class="col-md-2">
										<label>Unidad organizacional:</label>
									</div>
									<div id="txtCampo8" class="col-md-6">
										
										<input id="unidadOrg" name="unidadOrg" type="text" class="form-control" disabled="disabled"/>
									</div>
								</div>
								
								
								
								<div class="row content-box">&nbsp;</div>
								
								<div class="row content-box">
									<div class="col-md-2">
										<label>Periodo: </label>
									</div>
									
					<div id="txtPeriodo" class="col-md-2">
					<select id="selPeriodo" name="selPeriodo" class="cboProceso form-control">
					<option value="">Seleccione</option>				
					<option value="01">Indeterminado</option>
					<option value="02">Determinado</option>
					</select>
									</div>
									<div class="col-md-2">
										<label>Fecha de Inicio:</label>
									</div>
									<div id="txtFechaInicio" class="input-group date col-md-2">
										<input id="fechaInicio" name="fechaInicio" type="text" class="form-control form-control-single" maxlength="10" readonly="readonly" onchange="validarTipoFecha()"/>
										<span class="input-group-addon">
										<span class="fa fa-calendar"></span>
										</span>
									</div>
									<div class="col-md-1">
										
									</div>
									<div id="txtCampo3" class="col-md-2">
									</div>
								</div>
								
								
								<div class="row content-box">&nbsp;</div>
								
								<div class="row content-box">
									<div class="col-md-2">
										<label></label>
									</div>
									<div id="txtCampo7" class="col-md-2">
									</div>
									<div class="col-md-2">
										<label>Fecha Fin:</label>
									</div>
									<div id="txtFechaFin" class="input-group date col-md-2">
										
										<input id="fechaFin" name="fechaFin" type="text" class="form-control form-control-single" maxlength="10" readonly="readonly" onchange="validarTipoFecha()"/>
										<span class="input-group-addon">
										<span class="fa fa-calendar"></span>
										</span>
									</div>
									<div class="col-md-1">
										
									</div>
									<div id="txtCampo3" class="col-md-2">
									</div>
								</div>
								
								</div>
						</fieldset>
						
						
						
						<div style="margin : 15px 15px !important">
							<div class="row content-box">
								
								<div class="col-md-1 text-center">
									<button type="button" class="btn btn-primary" onclick="registrar()">Aceptar</button>
								</div>
								<div class="col-md-1 text-center">
									<button type="button" class="btn btn-primary" onclick="volver()">Regresar</button>
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
		var datosExpedientes = ${datosExpedientes};
		var fechaRegistro = ${fechaRegistro};
		
		$(document).ready(function() {
				
		$('#fechaInicio').attr('disabled', true);
		$('#fechaFin').attr('disabled', true);
		
		$('#numRuc').val(datosExpedientes.numRuc);
		$('#razonSocial').val(datosExpedientes.desRazonSocial);
		$('#numExpeOrigen').val(datosExpedientes.numExpedienteOrigen);
		
			
	
$('#selPeriodo').change(function() {
if ($('#selPeriodo').val().trim() === '02') {
	$('#fechaInicio').attr('disabled', false);
	$('#fechaFin').attr('disabled', false);

}else{
	$('#fechaInicio').attr('disabled', true);
	$('#fechaFin').attr('disabled', true);
	
}
});


			//$.ajaxSetup({ scriptCharset: "UTF-8" , contentType: "application/json; charset=utf-8"});
				jQuery.support.cors = true;
				$('#campoOculto').hide();
				$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
			
		});
		
		
		function validarTipoFecha(){				
				if($('#txtFechaSoli').val()==""){					
					indValidaciones[8] = false;
				}else{					
					indValidaciones[8] = true;
				}
				
				if($('#txtFechaInicio').val()==""){					
					indValidaciones[8] = false;
				}else{					
					indValidaciones[8] = true;
				}
				
				
				if($('#txtFechaFin').val()==""){					
					indValidaciones[8] = false;
				}else{					
					indValidaciones[8] = true;
				}

			}
		
		$('#txtFechaSoli').datetimepicker({
					format: 'DD/MM/YYYY',
					language: 'es',
					autoclose: true,
					forceParse: false,
					pickTime: false
					//maxDate: ${fechaRegistro}
				});
		
		
		$('#txtFechaInicio').datetimepicker({
					format: 'DD/MM/YYYY',
					language: 'es',
					autoclose: true,
					forceParse: false,
					pickTime: false,
					startDate: '+0d',
    				endDate: '+364d',
				});
				
				
		$('#txtFechaFin').datetimepicker({
					format: 'DD/MM/YYYY',
					language: 'es',
					autoclose: true,
					forceParse: false,
					pickTime: false,
					startDate: '+1d',
    				endDate: '+365d',
				});
				
		
		
		function volver(){		
			$('html').block({message: '<h1>Procesando</h1>'});
			 window.location.href = '${pageContext.request.contextPath}/accesoExpe.htm?action=regresarAccesoExpVirtual&aux=' + $('#numExpeOrigen').val();
			 
			/* var formURL = 'accesoExpe.htm?action=cargarAccesoExpediente&indCarga=0';
			document.forms.frmPrincipal.action = formURL;
			document.forms.frmPrincipal.method = "POST";
			$('html').block({message: '<h1>Procesando</h1>'});
			document.forms.frmPrincipal.submit();	 */	 
			 
		}
		

		function registrar() {
				if (validar()) {
					botones = [];
					
					
					var aceptar = $("<input/>").attr(
						{
							value: "Si", 
							type: "button", 
							"class": "btn btn-primary dlgButton", 
							"data-dismiss" : "modal", 
							onclick : "ejecutarRegistro()"
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
					
					crearDlg("Registrar accesos", "¿Está seguro de Registrar la Solicitud de Acceso?", botones);
				}	
			}
			
			
			function addZero(i) {
			    if (i < 10) {
			        i = '0' + i;
			    }
			    return i;
			}
			
			function validar(){
				var numSoli = $('#numSoli').val();
				var numSoli = numSoli.toUpperCase();
				var selMotivo = $('#selMotivo').val();
				var selPeriodo = $('#selPeriodo').val();
				var selTipoSens = $('#selTipoSens').val();
				var fechaInicio = $('#fechaInicio').val();
				var fechaFin = $('#fechaFin').val();
				var fechaSoli = $('#fechaSoli').val();
				var codigoReg = $('#codigoReg').val();
				var codigoReg = codigoReg.toUpperCase();
				var numExpeOrigen = $('#numExpeOrigen').val();
				var f = new Date();
				var fechaActual = addZero(f.getDate()) + "/" + (addZero(f.getMonth()+1)) + "/" + f.getFullYear();
				
				var tmp = fechaInicio.split('/');
				var fini = tmp[2]+tmp[1]+tmp[0];
				var tmp2 = fechaFin.split('/');
				var ffin = tmp2[2]+tmp2[1]+tmp2[0];
				var tmp3 = fechaActual.split('/');
				var fhoy = tmp3[2]+tmp3[1]+tmp3[0];
				
				verificar();
					
				
				if (numSoli == '' || selMotivo == '' || selPeriodo == '' || selTipoSens == '' || fechaSoli == '' || codigoReg == '' || numExpeOrigen == ''){

					mostrarDlgInfo("Registrar acceso al expediente", "Debe ingresar los campos requeridos.");
					return false
				}
				
				else  {
						
						if (selPeriodo =='02'){
						
						if (fechaInicio == ''){
						mostrarDlgInfo("Registrar acceso al expediente", "Debe ingresar la fecha inicio.");
						return false
						}
						else if (fechaFin == ''){
							mostrarDlgInfo("Registrar acceso al expediente", "Debe ingresar la fecha fin.");
							return false
						}
						//else if (fechaInicio > fechaFin){
						else if (fini > ffin){
							mostrarDlgInfo("Registrar acceso al expediente", "La fecha de inicio no puede ser mayor a la fecha final.");
							return false
						}
							else if (ffin < fhoy){
								mostrarDlgInfo("Registrar acceso al expediente", "La fecha de fin no puede ser menor a la fecha actual.");
								return false
							}	
						
						}
				
				
					return true
		
				}
				
			}
			
			
			function ejecutarRegistro() {
				
				var numSoli = $('#numSoli').val();
				var numSoli = numSoli.toUpperCase();
				var selMotivo = $('#selMotivo').val();
				var selPeriodo = $('#selPeriodo').val();
				var selTipoSens = $('#selTipoSens').val();
				var fechaInicio = $('#fechaInicio').val();
				var fechaFin = $('#fechaFin').val();
				var fechaSoli = $('#fechaSoli').val();
				var codigoReg = $('#codigoReg').val();
				var codigoReg = codigoReg.toUpperCase();
				var numExpeOrigen = $('#numExpeOrigen').val();
				var url = '&selMotivo='+selMotivo+'&codigoReg='+codigoReg+'&numExpeOrigen='+numExpeOrigen+'&numSoli='+numSoli+'&fechaSoli='+fechaSoli+'&selPeriodo='+selPeriodo+'&selTipoSens='+selTipoSens+'&fecInicio='+fechaInicio+'&fecFin='+fechaFin
				
				 $('html').block({message: '<h1>Procesando</h1>'});
			 		window.location.href = '${pageContext.request.contextPath}/accesoExpe.htm?action=registrarAccesoExpediente' + url;

			}
			
			
			
			function verificar() {

						var dataEnvio = new Object();
						var numExpediente = $('#numExpeOrigen').val();
						var numSoli = $('#numSoli').val();
						var numSoli = numSoli.toUpperCase();
						dataEnvio.numSoli = numSoli;
						dataEnvio.numExpediente = numExpediente;
				
						
						$.ajax({
							
							url : '${pageContext.request.contextPath}/accesoExpe.htm?action=verificarAcceso',
							type : 'POST',
							async : true,
							dataType : "json",
							data : JSON.stringify(dataEnvio),
							contentType : "application/json",
							mimeType : "application/json",
							//timeout : 5000,
							success : function(response) {
								
								var existe = response.existe;
								

										if (existe > 0) {
												mostrarDlgInfo("Registrar acceso a expedientes", "El numero SIGED ya existe. Por favor pruebe con otro.");
												return false;
												
											
										} 
										
								
							},
							error : function(response) {
								
								mostrarPagError();
								
							}
							
						});
						
				
			}
			

		function buscar() {
				
				var codigoReg = $('#codigoReg').val();
				codigoReg = codigoReg.toUpperCase();
								
				if (codigoReg == '') {
					
					mostrarDlgInfo("Registrar acceso a expedientes", "Debe ingresar el codigo de registro");
					
				} else {
					// [jjurado 21/06/2016] se reduce el mínimo de dígitos válidos para realizar la búsqueda del expediente origen
					if (codigoReg.length < 4 || codigoReg.length > 4) {
						
						mostrarDlgInfo("Registrar acceso a expedientes", "El codigo de registro es de 4 digitos");

					} else {
						
						var dataEnvio = new Object();
						
				
						
						$.ajax({
							
							url : '${pageContext.request.contextPath}/accesoExpe.htm?action=cargarEmpleado&codigoReg=' + codigoReg,
							type : 'POST',
							async : true,
							dataType : "json",
							data : JSON.stringify(dataEnvio),
							contentType : "application/json",
							mimeType : "application/json",
							//timeout : 5000,
							success : function(response) {
								
								var empleado = response.empleado;

										if (empleado != null) {

												$('#desColab').val(empleado.desNombreCompleto);
												$('#unidadOrg').val(empleado.desUnidadOrganizacional);
																								
												//$("#btnBuscar").prop("disabled", true);
												//$("#btnAsignar").prop("disabled", false);
												
											
										} else {
											
											
											$('#desColab').val("");
											$('#unidadOrg').val("");
											
											mostrarDlgInfo("Registrar acceso a expedientes", "No existe el usuario buscado.");
											
										}
										
									
								
							},
							error : function(response) {
								
								mostrarPagError();
								
							}
							
						});
						
					}
					
				}
				
			}
		
		
		function mostrarPagError() {
				
			var formURL = '${pageContext.request.contextPath}/error.htm?action=cargarPaginaError';
			document.forms.frmPrincipal.action = formURL;
			document.forms.frmPrincipal.method = "post";
			document.forms.frmPrincipal.submit();
			
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
		
		
		
		function permitirAlfaNumerico(e) {
				
				if(((event.keyCode < 48) || (event.keyCode > 57)) && ((event.keyCode != 32) && (event.keyCode < 65) || (event.keyCode > 90) && (event.keyCode < 97) || (event.keyCode > 122))){
					
					event.returnValue = false;
					
				}
				
			}
			
			if (typeof String.prototype.trim !== 'function') {
				
				String.prototype.trim = function() {
					
					return this.replace(/^\s+|\s+$/g, '');
					
				}
				
			}
			
			
function compare_dates(fecha, fecha2)  
  {  
    var xMonth=fecha.substring(3, 5);  
    var xDay=fecha.substring(0, 2);  
    var xYear=fecha.substring(6,10);  
    var yMonth=fecha2.substring(3, 5);  
    var yDay=fecha2.substring(0, 2);  
    var yYear=fecha2.substring(6,10);  
    if (xYear> yYear)  
    {  
        return(true)  
    }  
    else  
    {  
      if (xYear == yYear)  
      {   
        if (xMonth> yMonth)  
        {  
            return(true)  
        }  
        else  
        {   
          if (xMonth == yMonth)  
          {  
            if (xDay> yDay)  
              return(true);  
            else  
              return(false);  
          }  
          else  
            return(false);  
        }  
      }  
      else  
        return(false);  
    }  
}  
		
		</script>
		
	</body>
	
</html>