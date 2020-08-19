<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">

	<head>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=EDGE" />
		<meta name="viewport" content="initial-scale = 1.0, user-scalable = no,  width=device-width">
		<title>ADJUNTAR DOCUMENTOS INTERNOS</title>
		
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
			
			.input-group-addon {
				padding-bottom:0px !important;
				padding-top:0px !important;
				padding-left: 6px !important;
				padding-right: 6px !important;
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
			.dataTables_wrapper .dataTables_paginate {
				float: initial !important;
				text-align: center !important;
				padding-top: 0.25em;
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
			
			.dlgButton {
				border-color: gray;
				margin-right: 7px;
			}
			
			.uploadStyle {
				border-color: white;
			}
			.botonExaminar{
				    height: 30px !important;
			}
			.vcenter {
				    padding-top: 12px !important;
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
			<div class="row">
				<div class="panel panel-primary">
					<div class="panel-heading align="center">
						<h3 class="panel-title" align="center">ADJUNTAR DOCUMENTOS INTERNOS</h3>
						<form id="frmPrincipal" class="form-horizontal" role="form">
						</form>
						
					</div>
					
					<fieldset class="scheduler-border" style="margin : 15px 15px !important">
					<legend class="scheduler-border">Datos del Expediente</legend>
					  <div class="form-group" id="DocumentoInterno">	
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
						<!-- INICIO[LLRB 15012020] -->
						 <div class="form-group" id="DocumentoInternoFiltro">	
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
									<input id="numExpeVirtual_f" name="numExpeVirtual" type="hidden" value=""/>
								</div>
								<div class="col-md-1">
									<label>Estado:</label>
								</div>
								<div id="txtCampo3" class="col-md-2">
									<input id="estExpediente_f" name="estadoExpediente" type="text" class="form-control" disabled="disabled"/>
								</div>
							</div>
							<div class="row content-box">&nbsp;</div>
							<div class="row content-box">
								<div class="col-md-2">
									<label>Proceso:</label>
								</div>
								<div id="txtCampo4" class="col-md-2">
									<input id="desProceso_f" name="descripcionProceso" type="text" class="form-control" disabled="disabled"/>
								</div>
								<div class="col-md-3">
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
							</div>
							<div class="row content-box">&nbsp;</div>
							<div class="row content-box">
								<div class="col-md-2">
									<label>Fecha de generaci&oacute;n:</label>
								</div>
								<div id="txtCampo7" class="col-md-2">
									<input id="fechaVirtual_f" name="fechaExpedienteVirtual" type="text" class="form-control" disabled="disabled"/>
								</div>
								<div class="col-md-3">
									<label>Fecha de documento origen:</label>
								</div>
								<div id="txtCampo8" class="col-md-2">
									<input id="fechaOrigen_f" name="fechaOrigenExpediente" type="text" class="form-control" disabled="disabled"/>
								</div>
							</div>
							</div>
						</div>
						<!-- FIN[LLRB 15012020] -->
					</fieldset>
					
					<fieldset class="scheduler-border" style="margin : 15px 15px !important">
						<legend class="scheduler-border">Documentos por Adjuntar </legend>
						<div class="panel-body">
							<form id="docsPorAdj">					
								<div class="row content-box" id="divSelectDocGroup">
									<div class="col-md-2">
										<label>Grupo de Documentos:</label>
									</div>
									<div class="col-md-4">
										<select id="selTipoDoc3Dig" name="selTipoDoc3Dig" onchange="activaCampos();" class="cboProceso form-control">
											<option value="">Seleccione</option>
										</select> 
									</div>
								</div>
<!-- 						Inicio - [LLRB][17/01/2020] -->	
								<div class="row content-box" id="divSelectCopia" style="display: none">							
									<div class="col-md-2">									
										<input type="checkbox" id="copiaDoc" name="copiaDoc"  onclick="iniciarCopia();">   
										<!--<input type="checkbox" id="copiaDoc" name="copiaDoc"> -->	
									</div>
									<div class="col-md-2">
										<label>Copia Personalizada</label>
									</div>
								</div>
	<!-- 						Fin - [LLRB][17/01/2020] -->	
								<div class="row content-box">&nbsp;</div>
							
								<div class="row content-box">
									<div class="col-md-2">
										<label>Tipo de Documento:</label>
									</div>
									<div class="col-md-4">
										<select id="selTipoDocumento" name="selTipoDocumento" onchange="fnOnchangeTipExp();" class="cboProceso form-control"  disabled>
											<option value="">Seleccione</option>
										</select> 
									</div>
									<div class="col-md-1">
										<label>Número:</label>
									</div>
									<div id="txtNumDoc" class="col-md-2">
<!-- 									Inicio - [oachahuic][PAS20165E210400270] -->
<!-- 										<input id="numDocumento" name="numDocumento" type="text" class="form-control" onkeypress="return justNumbers(event);"  maxlength="17" onblur="validarNumDocumento()"/> -->
										<input id="numDocumento" name="numDocumento" type="text" class="form-control" maxlength="17" onblur="validarNumDocumento()"/>
<!-- 									Fin - [oachahuic][PAS20165E210400270] -->
									</div>
									
								</div>
								
								<div class="row content-box">&nbsp;</div>
								
								<div class="row content-box">
									<div class="col-md-2">
										<label>Fecha de Emisión:</label>
									</div>
									<div class="col-md-2">
											<div>
												<div class='input-group date col-md-12' id='fechaDocumento'>
													<input id="fechaDoc" type='text' class="form-control" onkeypress="datoTipoFecha()" maxlength="10" />
													<span class="input-group-addon" id="spanFecEmi">
														<i class="glyphicon ">&#xe109;</i>
													</span>
												</div>
											</div>
											<input id="fechaDocCompleta" name="fechaDocCompleta" type="hidden" value=""/>
											<!-- INICIO LLRB 28012020 -->
											<input id="numCorDoc" name="numCorDoc" type="hidden" value=""/>
											<input id="numCopia" name="numCopia" type="hidden" value=""/>
											<!-- FIN LLRB 28012020 -->
									</div>										
									<div class="col-md-2" id="divLabelFecNot">
										<label>Fecha de Notificación:</label>
									</div>
									<div class="col-md-2" id="divInputFecNot">
										<div class='input-group date col-md-12' id='fechaNotDocumento'>
											<input id="fechaNot" type='text' class="form-control" maxlength="10"  readonly="readonly"/>
											<span class="input-group-addon">
												<i class="glyphicon ">&#xe109;</i>
											</span>
										</div>
									</div>
									
								</div>
								
								<!--[PAS20191U210500144] Inicio -->
												<div style="display: none" id="dv1">....</div>
								<div class="row content-box" style="display: none" id="divResTribCab">&nbsp;</div>
								<div class="row content-box" style="display: none" id="divResTrib">
									<div class="col-md-2">
										<label>Visible al Contribuyente:</label>
									</div>
									<div id="txtVisContDocInt" class="col-md-2">
										<select id="selVisContDocInt" name="selVisContDocInt" class="cboProceso form-control">
											<option value="">Seleccione</option>
											<option value="1">Si</option>
											<option value="0">No</option>
										</select> 
									</div>
									
									<div class="col-md-1">
										<label>Reserva Tributaria:</label>
									</div>
									<div id="txtResTri" class="col-md-2">
										<select id="selResTri" name="selResTri" class="cboProceso form-control">
											<option value="">Seleccione</option>
											<option value="1">Si</option>
											<option value="0">No</option>
										</select> 
									</div>
									
								</div>
								<!--[PAS20191U210500144] Fin -->
								
								<div class="row content-box">&nbsp;</div>
								<div class="row content-box">
									<div class="col-md-2">
										<label>Palabras Clave de B&uacute;squeda:</label>
									</div>
									<div id="txtPalabrasBusqueda" class="col-md-10">
										<textarea id="busqueda" name="busqueda" rows="4" class="form-control form-control-textarea" maxlength="200" onpaste="return false;"></textarea>
									</div>
								</div>
								
								<div class="row content-box">&nbsp;</div>
								<div class="row content-box">
									<div class="col-md-2">
										<label>Archivo Seleccionado:</label>
									</div>
									<div class="col-md-10" id="manejadorArchivo">
										<input name="docFisico" id="docFisico" type="file" onchange='validarArchivoUpload();' class="form-control uploadStyle botonExaminar">
									</div>
								</div>
								<div class="row content-box">
									<div class="col-md-2 vcenter">
										<label>Documento Relacionado</label>
									</div>
									<div id="" class="col-md-10 ">
										<hr>
									</div>
								</div>
								<div class="row content-box">&nbsp;</div>
								<div class="row content-box">
									<div class="col-md-2">
										<label>Relacionado a:</label>
									</div>
									<!-- Inicio lestrada SNADE307-1026 -->
									<div id="txtTipDocOrigen" class="col-md-3">
									<!-- fin lestrada SNADE307-1026 -->
										<select id="selTipoDocOrigen" name="selTipoDocOrigen" class="cboProceso form-control" onchange="setearIndVisible();">
											<option value="">Seleccione</option>
										</select> 
									</div>
									<!-- INICIO [ATORRESCH 2017-04-26] -->
									<div class="col-md-2" id="divLabelFecNotRel">
										<label>Fecha de Notificaci&oacute;n:</label>
									</div>
									<div class="col-md-2" id="divInputFecNotRel">
										<div class='input-group date col-md-12' id='divFecNotRel'>
											<input id="txtFecNotRel" type='text' class="form-control" maxlength="10"  readonly="readonly"/>
											<span class="input-group-addon">
												<i class="glyphicon ">&#xe109;</i>
											</span>
										</div>
									</div>
									<!-- FIN -->
									<div class="col-md-3">
										<label>Doc. Relacionado Visible al Contribuyente:</label>
									</div>
									<div id="txtVisCont" class="col-md-1">
										<select id="selVisCont" name="selVisCont" class="cboProceso form-control">
											<option value="">Seleccione</option>
											<option value="1">Si</option>
											<option value="0">No</option>
										</select> 
									</div>
								</div>
								<div class="row content-box">&nbsp;</div>
								<div class="row content-box">
									<div class="col-md-12 text-right">
										<!--[PAS20191U210500144] Inicio -->
										<button id="btnFirmar" type="button" class="btn btn-primary" style="display: none" name="btnFirmar" onclick="adjuntarArchivo()">Firmar y Adjuntar Documento</button>
										<!--[PAS20191U210500144] Fin -->
										<button id="adjArcvhivo" type="button" class="btn btn-primary" onclick="adjuntarArchivo()">Adjuntar Archivo</button>
									</div>
								</div>
							</form>
						</div>
					</fieldset>
					
					<!--[PAS20191U210500144] Inicio -->
					<div id="dvIframe" class="modal fade">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-body">
									<iframe frameborder="0" name="iframeFirma" id="iframeFirma" width="550px" height="320px" ></iframe>
								</div>
							</div>
						</div>
					</div>
					<!--[PAS20191U210500144] Fin -->
					
					<fieldset class="scheduler-border" style="margin : 15px 15px !important">
						<legend class="scheduler-border">Documentos adjuntados </legend>
							<div class="panel-body">
								<table id="tableDocumento" class="table display" cellspacing="0" style="width: 100%;"><!--table table-striped table-bordered-->
									<thead>
										<tr class="active">
											<th width="3%">N°</th>
											<th width="16%">Tipo de Documento</th>
											<th width="15%">Número de Documento</th>
											<th width="10%">Fecha de Documento</th>
											<th width="20%">Archivo seleccionado</th>
											<th width="6%">Ver Archivo</th>
											<th width="6%">Copia</th>												
											<th width="10%">Tamaño del Archivo</th>											
											<th width="6%">Eliminar</th>
										</tr>
									</thead>
								</table>
							</div>
					</fieldset>
											
					<div style="margin : 15px 15px !important">
						<div class="row content-box">
							<div class="col-md-8">
								<label> * El tamaño máximo permitido por el total de documentos es ${tamanoMaximo}MB</label>
							</div>
							<div class="col-md-1 text-right">
								<label>TOTAL:</label>
							</div>
							<div class="col-md-2 text-right">
								<input id="totalPeso" name="totalPeso" type="text" class="form-control" disabled="disabled"/>
							</div>
						</div>
					</div>
					
					<div style="margin : 15px 15px !important">
						<div class="row content-box">
							<div class="col-md-1">
							</div>
							<div class="col-md-2 text-center">
								<button id = "exportar" type="button" class="btn btn-primary" onclick="exportar()">Exportar a Excel</button>
							</div>
							<div class="col-md-3 text-center">
								<button id= "adjuntar" type="button" class="btn btn-primary" onclick="confirmarAdjuntar()">Subir documentos</button>
							</div>
							<div class="col-md-3 text-center">
								<button type="button" class="btn btn-primary" onclick="confirmarRetorno()">Regresar</button>
							</div>
							<div class="col-md-2 text-center">
								<button type="button" class="btn btn-primary" onclick="confirmarCancelar()">Cancelar</button>
							</div>
							<div class="col-md-1">
							</div>
						</div>
					</div>
					
					<input id="hiddNomArchivo" type="hidden" name="hiddNomArchivo"/>
					<input id="hiddPesoArchivo" type="hidden" name="hiddPesoArchivo"/>
					<input id="hiddPesoTotal" type="hidden" name="hiddPesoTotal"/>
					<input id="hiddMimeType" type="hidden" name="hiddMimeType"/>
					<input id="hiddIndAcumulador" type="hidden" name="hiddIndAcumulador"/>
					<input id="codProceso" type="hidden" name="codProceso"/> <!--[PAS20191U210500144] -->
					<input id="hiddFirmaDigital" type="hidden" name="hiddFirmaDigital" value="0"/> <!--[PAS20191U210500144] -->
					
					<input id="hiddIdEcmDoc" type="hidden" name="hiddIdEcmDoc"/>
					<input id="hiddDesTipCons" type="hidden" name="hiddDesTipCons"/>
					<input id="hiddFecCons" type="hidden" name="hiddFecCons"/>
					<input id="hiddFecCompCons" type="hidden" name="hiddFecCompCons"/>
					<input id="hiddDesCons" type="hidden" name="hiddDesCons"/>
					<input id="hiddCntCons" type="hidden" name="hiddCntCons"/>
					<input id="hiddCodTipCons" type="hidden" name="hiddCodTipCons"/>
					<input id="hiddIdEcmCons" type="hidden" name="hiddIdEcmCons"/>
					<input id="hiddIndFlujoNormal" type="hidden" name="hiddIndFlujoNormal"/>
					
					<form id="formPrincipal" class="form-horizontal" role="form">
						<input id="hiddenNumArchivo" type="hidden" name="hiddenNumArchivo"/>
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
						<input id="hiddenPesoTotal" type="hidden" name="hiddenPesoTotal"/>
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
		
		<!--[PAS20191U210500144] Inicio Firma Digital -->
		<form method="post" id="ssoForm" name="ssoForm" role="form">			
				<input name="rutaOrigen" id="rutaOrigen" type="hidden" value="/data0/uploads/" size="40">
				<input name="rutaDestino" id="rutaDestino" type="hidden" value="/data0/uploads/spring/" size="40">
				<!-- input name="rutaOrigen" type="hidden" value="/SignNet/pide/signed" size="40">
				<input name="rutaDestino" type="hidden" value="/SignNet/pide/unsigned" size="40"-->
				<input type="hidden" id="nombreArchivos" name="nombreArchivos" value="" size="40">
				<input type="hidden" id="codApp" name="codApp" value="400103" />
				<input type="hidden" id="tipoFirma" name="tipoFirma" value="1" />
				<!-- Parametro para Firmar documentos en Servidor o escoger en cliente, 0-Cliente, 1-Servidor -->
				<input type="hidden" id="listarArchivos" name="listarArchivos" value="1" />
				<input type="hidden" id="nomarch" name="nomarch" value="" />
				<input type="hidden" id="urlConfigService" name="urlConfigService" value="http://192.168.46.20:800/SignnetSignature/configuracion" />
				<input type="hidden" id="webService" name="webService" value="http://192.168.46.20:800/SignnetSignature/FirmaDigitalWs?wsdl" />				
				<!-- Parametro para Aplicar una firma invisible a documentos PDF, 0-Invisible, 1-Visible -->
				<input type="hidden" id="invisible" name="invisible" value="0" />
				<!-- Parametro para firmar en una pagina Especifica, se debe especificar Ubicacion Pagina en NP -->
				<input type="hidden" id="numeroPagina" name="numeroPagina" value="" />	
				<input type="hidden" id="webServiceGS" name="webServiceGS" value="http://192.168.46.20:800/GSWebService/ServicioFirma?wsdl" />
		</form>
		<!--[PAS20191U210500144] Fin -->
		
		<form id="frmAuxiliar" name="frmAuxiliar" class="form-inline" role="form" method="post" enctype="multipart/form-data">
			<input id="archivosLength" type="hidden" name="archivosLength"/>
			<input id="listaTabla" type="hidden" name="listaTabla"/>
			<input id="pesoTotal" type="hidden" name="pesoTotal"/>
			<input id="numExpVirtual" type="hidden" name="numExpVirtual"/>
			<input id="codTipoExpediente" type="hidden" name="codTipoExpediente"/>			
		</form>
		
		<form id="frmEnvioArchivo" name="frmEnvioArchivo" class="form-inline" role="form" method="post" enctype="multipart/form-data">
			<input id="nombreArchivoTemp" type="hidden" name="nombreArchivoTemp"/>		
		</form>
		
		<script src="../a/js/libs/jquery/1.11.2/jquery.min.js"></script>
		<script src="../a/js/libs/bootstrap/3.3.2/js/bootstrap.min.js"></script>
		<script src="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/media/js/jquery.dataTables.min.js" type="text/javascript" ></script>    
		<script src="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Responsive/js/dataTables.responsive.js" type="text/javascript" ></script>
		<script src="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Scroller/js/dataTables.scroller.js" type="text/javascript" language="javascript" ></script>
		<script src="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/TableTools/js/dataTables.tableTools.min.js" type="text/javascript" ></script>
		<script src="../a/js/js.js" type="text/javascript" ></script>
		
		<script type="text/javascript" src="../a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/js/moment-with-locales.js"></script>
		<script type="text/javascript" src="../a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/js/bootstrap-datetimepicker.min.js"></script>
		<script src="../a/js/bootstrap/3.2.0/js/jquery.blockUI.js" type="text/javascript"></script>
		
		<script>
		var datosExpedientes = ${datosExpedientes};
		var gEsCobranza = datosExpedientes.codProceso=='001'?true:false; //eaguilar: se utiliza para difenciar el proceso de Cobranza (001)
		var gEsAduanas  = datosExpedientes.codProceso=='003'?true:false; //[oachahuic][PAS20165E210400270]: se utiliza para difenciar el proceso de Aduanas (003)
		var gEsFisca    = (datosExpedientes.codProceso=='002' ) ?true:false; //[etito][PAS20191U210500144]: se utiliza para los expedientes de Fiscalizacion Definitiva Parcial y Cruces de Informacion
		var hostServicioSignnet = ${hostServicioSignnet};//[etito][PAS20191U210500144] Host servicio firma digital
		
		var fechaRegistro = ${fechaRegistro};
		var fechaOrigenDoc = ${fechaOrigenDoc};
		var contador = 1;
		var listadoTipoDocumentos = ${listadoTipoDocumentos};
		var listadoTipoDocumentosOtros = ${listaDocsOtros}; //eaguilar: lista para documentos tipo OTROS
		listadoTipoDocumentosOtros.sort(function(a, b){
			 var nameA = a.desTipoDocumentoCompuesto.toLowerCase();
			 var nameB = b.desTipoDocumentoCompuesto.toLowerCase();
			 if (nameA < nameB) //sort string ascending
			  return -1;
			 if (nameA > nameB)
			  return 1;
			 return 0; //default return value (no sorting)
			});
		var listadoTipoDoc3Dig = ${listadoTipoDoc3Dig};
		var listDocAsocExped = ${listDocAsocExped};
		var listaEstDocumento = ${listaEstDocumento};
		var listaEtapaDocumento = ${listaEtapaDocumento};
		var totalMg = 0;
		var listaInputs = [];
		var tamanoMaximo = ${tamanoMaximo};
		var indNumRC = true;
		//INICIO LLRB 28012020
		var copiaPers = false;
		var copiaDoc = false;
		//FIN LLRB 28012020
		var gIsPastedValid = true;
			
		function adjuntarArchivo(){
			var tableData = $('#tableDocumento').dataTable().fnGetData();
			//INICIO LLRB 20012020
			if (($('#numExpeVirtual').val())!=""){
				var codTipoDocumento = $('#selTipoDocumento').val()
				var codProceso = $('#codProceso').val();
				var numDocuemento = $('#numDocumento').val();
				var numExpeVirtual = $('#numExpeVirtual').val();
			}else{
				var codTipoDocumento = $('#selTipoDocumento').val()
				var codProceso = $('#codProceso').val();
				var numDocuemento = $('#numDocumento').val();
				var numExpeVirtual = $('#numExpeVirtual_f').val();
			}
			//FIN LLRB 20012020
			
			//eaguilar 22 JUN: si es cobranza y no se ha seleccionado un grupo al adjuntar:
			if(datosExpedientes.codProceso == '001' && $("#selTipoDoc3Dig").val() == ''){
				mostrarDlgInfo("Adjuntar Documentos Internos", "Debe seleccionar un grupo de documentos.");
				return;
			}

			if(codTipoDocumento != ""){
				var desTipoDocumento = $('#selTipoDocumento option:selected').text()
				if(numDocuemento != ""){
					var codTipoDoc = codTipoDocumento.substring(0, 3);
					if(indNumRC==false){
						mostrarDlgInfo("Adjuntar Documentos Internos", "El número de documento ingresado no es válido.");
					}else{
						var dataEnvio = new Object();
						dataEnvio.codTipoDocumento = codTipoDocumento;
						dataEnvio.numDocuemento = numDocuemento;
						dataEnvio.numExpeVirtual = numExpeVirtual;
						$.ajax({											
							url : '${pageContext.request.contextPath}/regDocInterno.htm?action=validaDocumento&indCarga=0',
							type : 'POST',
							async : true,
							dataType : "json",
							data : JSON.stringify(dataEnvio),
							contentType : "application/json",
							mimeType : "application/json",
							//timeout : 5000,
							success : function(response) {									
								var listDocRepetido = response.listDocRepetido
								
								//INICIO LLRB 17/01/2020
								if (copiaDoc.checked == true){
									if (listDocRepetido.length > 0) {										
										if(tableData.length != 0 ){
											var ind = 0;											
											for(var i=0;i<tableData.length;i++){
												if(tableData[i].numDoc == numDocuemento && tableData[i].codTipoDocumento == codTipoDocumento){
													ind = 1;
													mostrarDlgInfo("Adjuntar Documentos Internos", "El Tipo y número de documento ya se encuentra en la lista por adjuntar.");
													break;
												}
											};											
											if (ind == 0){																								
												$('#txtVisContDocInt').attr("value", listDocRepetido.indVisible);
												$('#txtResTri').attr("value", listDocRepetido.indReservTrib);
												$('#fechaDocCompleta').attr("value", listDocRepetido.fecCarg);
												var desTipdocRel =listDocRepetido.desTipdocRel;
												var numDocRel = listDocRepetido.numDocRel;												
												var tipDocNroRel=desTipdocRel + "-" +numDocRel
												$('#txtTipDocOrigen').attr("value", tipDocNroRel);
												var numCorDoc =listDocRepetido.numCorDoc;
												prepararDataEnviarTablaFisca(numCorDoc);
											}											
										}else{
											
											prepararDataEnviarTablaFisca(numCorDoc);
										}								
									}					
								}else{
									if (listDocRepetido.length > 0) {
										mostrarDlgInfo("Adjuntar Documentos Internos", "El Tipo y número de documento ya se encuentra adjuntado al Expediente.");
									} else {
										if(tableData.length != 0 ){
											var ind = 0;											
											for(var i=0;i<tableData.length;i++){
												if(tableData[i].numDoc == numDocuemento && tableData[i].codTipoDocumento == codTipoDocumento){
													ind = 1;
													mostrarDlgInfo("Adjuntar Documentos Internos", "El Tipo y número de documento ya se encuentra en la lista por adjuntar.");
													break;
												}
											};										
											if (ind == 0){
												
												prepararDataEnviarTabla();
											}											
										}else{
											
											prepararDataEnviarTabla();
										}								
									}				
								}
							//FIN LLRB 20012020
														
							},
							error : function(response) {									
								mostrarPagError();									
							}							
						});
					}

				}else{
					mostrarDlgInfo("Adjuntar Documentos Internos", "Debe ingresar el número de documento.");
				}
			}else{
				mostrarDlgInfo("Adjuntar Documentos Internos", "Debe seleccionar un Tipo de Documento.");
			}
		}
		
		function datoTipoFecha() {
			if (((event.keyCode < 48) || (event.keyCode > 57)) && (event.keyCode != 47)) {  
				event.returnValue = false;
			} 
		}
		
		function formatSizeUnits(bytes){
		  if      (bytes>=1073741824) {bytes=(bytes/1073741824).toFixed(2)+' GB';}
		  else if (bytes>=1048576)    {bytes=(bytes/1048576).toFixed(2)+' MB';}
		  else if (bytes>=1024)       {bytes=(bytes/1024).toFixed(2)+' KB';}
		  else if (bytes>1)           {bytes=bytes+' bytes';}
		  else if (bytes==1)          {bytes=bytes+' byte';}
		  else                        {bytes='0 byte';}
		  return bytes;
		}
		
		function prepararDataEnviarTabla(){
			var ruta = $('#docFisico').val();
			var busqueda = $('#busqueda').val();
			var fechaDoc = document.getElementById("fechaDoc").value;

			if (fechaDoc != "") {
				if (validarFormatoFecha(fechaDoc)) {
					if (existeFecha(fechaDoc)) {
						if (ruta != "") {
							//INICIO[ATORRESCH 2017-04-26]
							if ( $("#selTipoDocumento").val().substring(0, 3) == '803'
								&& $('#selTipoDocOrigen').val() !== ""
								&& $('#txtFecNotRel').val()  == "" ) {
									mostrarDlgInfo("Adjuntar Documentos Internos", "Debe ingresar la fecha de notificaci&oacute;n del documento relacionado.");
							} else {								
								//INICIO LLRB 10/02
								$('#numCopia').val("");	
								$('#numCorDoc').val("");	
								//FIN LLRB 10/02
								enviarArchivoTemp();
								
							}
							//FIN
						} else {//Inicio - [PAS20181U210400241][oachahuic]
							var codPro = $('#codProceso').val();
							var codTipExp = datosExpedientes.codTipoExpediente;
							var codTipDoc = $('#selTipoDocumento').val()
							var idEcmDoc = $('#hiddIdEcmDoc').val();
							//INICIO LLRB 10/02
							$('#numCopia').val("");	
							$('#numCorDoc').val("");	
							//FIN LLRB 10/02
							if (idEcmDoc != "") {//verificar si se recuperó el id el ecm para no solicitar la subida del archivo físico								
								adjuntarDocNotSine();
							} else {//Fin - [PAS20181U210400241][oachahuic]
								mostrarDlgInfo("Adjuntar Documentos Internos", "Debe seleccionar un archivo.");
							}
						}
					} else {
						mostrarDlgInfo("Adjuntar Documentos Internos", "La fecha de emisi&oacute;n es incorrecta.");
					}
				} else {
					mostrarDlgInfo("Adjuntar Documentos Internos", "La fecha de emisi&oacute;n es incorrecta.");
				}
			} else {
				mostrarDlgInfo("Adjuntar Documentos Internos", "Debe ingresar la fecha de emisi&oacute;n.");
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
		
		function abc(){
			
			var tableData = $('#tableDocumento').dataTable().fnGetData();
			var codTipoDocumento = $('#selTipoDocumento').val()
			var numDocuemento = $('#numDocumento').val();
			var numExpeVirtual = $('#numExpeVirtual').val();
			var desTipoDocumento = $('#selTipoDocumento option:selected').text()
			var fechaDoc = document.getElementById("fechaDoc").value;
			var fechaDocCompleta = document.getElementById("fechaDocCompleta").value;			
			var ruta = $('#nomDocumentoFisico').val();
			var nombreArchivo = $('#hiddNomArchivo').val();
			var pesoArchivo = $('#hiddPesoArchivo').val();
			var busqueda = $('#busqueda').val();
			//INICIO LLRB 23/01/2020
			var numCorDoc=document.getElementById("numCorDoc").value;
			var numCopia=document.getElementById("numCopia").value;
			//FIN LLRB 23/01/2020
			//INICIO [ATORRESCH 2017-04-26]
			var valInputFecNotRel=document.getElementById("txtFecNotRel").value; //$('#txtFecNotRel').val();

			if(busqueda == ''){
				busqueda = $('#desExpediente').val() + ";";
				var auxBusqueda = busqueda.replace(/ /g, ";");
				busqueda = auxBusqueda;
			}
			
			var file = $("#docFisico").val();
			var archivoTempo = $('#nombreArchivoTemp').val()
			var mimeType = $('#hiddMimeType').val()
			var indAcumulador = $('#hiddIndAcumulador').val()
			var asociado = $('#selTipoDocOrigen').val()
			var estado = "";//$('#selEstadoDocOrigen').val()
			var etapa = "";//$('#selEtapaDocOrigen').val()
			var indVisDocumento = $('#selVisCont').val();
			
			//[PAS20191U210500144] Inicio 
			var indVisible = $('#selVisContDocInt').val();
			var indReserTrib = $('#selResTri').val();
			//[PAS20191U210500144] Fin 
			
			var fileData = new Object();
			
			fileData.numOrden = contador;
			fileData.desTipdoc = desTipoDocumento;
			fileData.numDoc = numDocuemento;
			fileData.fechaDoc = fechaDoc;
			fileData.fechaDocCompleta = fechaDocCompleta;
			fileData.nombreArchivo = nombreArchivo;
			fileData.tamanoDoc = formatSizeUnits(pesoArchivo);
			fileData.pesoArchivo = pesoArchivo;
			fileData.busqueda = busqueda;
			fileData.codTipoDocumento = codTipoDocumento;
			fileData.nombreArchivoTemp = archivoTempo;
			fileData.mimeType = mimeType;
			fileData.indAcumulador = indAcumulador;
			fileData.file = file;
			fileData.asociado = asociado;
			fileData.estado = estado;
			fileData.etapa = etapa;			
			//INICIO [ATORRESCH 2017-04-26]
			fileData.fecNotRel = valInputFecNotRel;
			fileData.indVisDocumento = indVisDocumento;

			//[PAS20191U210500144] Inicio 
			fileData.indVisible = indVisible;
			fileData.indReserTrib = indReserTrib;
			//[PAS20191U210500144] Fin 
			//INICIO LLRB 23/01/2020
			if(numCorDoc != ""){				
				fileData.numCorDoc = numCorDoc;	
			}else{
				fileData.numCorDoc = 0;
			}
			
			if(numCopia != ""){				
				fileData.numCopia = numCopia;	
			}else{
				fileData.numCopia = 0;
			}
			
			//FIN 23/01/2020

			contador++;
			$('#tableDocumento').dataTable().fnAddData(fileData);
			document.getElementById("docsPorAdj").reset();
			$('#totalPeso').val("");
			$('#totalPeso').val(formatSizeUnits(totalMg));
			
			$('#exportar').attr('disabled', false);
			$('#adjuntar').attr('disabled', false);
			$('#adjArcvhivo').attr('disabled', false);
			
			$('#fechaDoc').val("");
			$('#fechaDocCompleta').val("");
			$('#txtFecNotRel').val("");
		}
		
		function adjuntarDocNotSine() {//[PAS20181U210400241][oachahuic]		
			var busqueda = $('#busqueda').val();
			if (busqueda == '') {
				busqueda = $('#desExpediente').val() + ";";
				var auxBusqueda = busqueda.replace(/ /g, ";");
				busqueda = auxBusqueda;
			}
			
			//AGREGAR DOCUMENTO
			var fileData = new Object();
			fileData.numOrden = contador;
			fileData.desTipdoc = $('#selTipoDocumento option:selected').text();
			fileData.numDoc = $('#numDocumento').val();
			fileData.fechaDoc = document.getElementById("fechaDoc").value;
			fileData.fechaDocCompleta = document.getElementById("fechaDocCompleta").value;
			fileData.nombreArchivo = $('#hiddNomArchivo').val();
			fileData.tamanoDoc = formatSizeUnits($('#hiddPesoArchivo').val());
			fileData.pesoArchivo = $('#hiddPesoArchivo').val();
			fileData.busqueda = busqueda;
			fileData.codTipoDocumento = $('#selTipoDocumento').val();
			fileData.codIdEcm = $('#hiddIdEcmDoc').val();
			totalMg = totalMg + parseInt($('#hiddPesoArchivo').val());
			contador++;
			$('#tableDocumento').dataTable().fnAddData(fileData);
			
			//AGREGAR CONSTANCIA
			fileData = new Object();
			fileData.numOrden = contador;
			fileData.desTipdoc = $('#hiddDesTipCons').val();
			fileData.numDoc = $('#numDocumento').val();
			fileData.fechaDoc = $('#hiddFecCons').val();
			fileData.fechaDocCompleta = $('#hiddFecCompCons').val();
			fileData.nombreArchivo = $('#hiddDesCons').val();
			fileData.tamanoDoc = formatSizeUnits($('#hiddCntCons').val());
			fileData.pesoArchivo = $('#hiddCntCons').val();
			fileData.busqueda = busqueda;
			fileData.codTipoDocumento = $('#hiddCodTipCons').val();
			fileData.asociado = 0;
			fileData.fecNotRel = $('#fechaNot').val();
			fileData.indVisDocumento = "1";
			fileData.codIdEcm = $('#hiddIdEcmCons').val();
			fileData.codTipDocRel = $('#selTipoDocumento').val();
			totalMg = totalMg + parseInt($('#hiddCntCons').val());
			contador++;
			$('#tableDocumento').dataTable().fnAddData(fileData);
			
			document.getElementById("docsPorAdj").reset();
			$('#docFisico').attr('disabled', false);
			$('#totalPeso').val("");
			$('#totalPeso').val(formatSizeUnits(totalMg));//ojo
			
			$('#exportar').attr('disabled', false);
			$('#adjuntar').attr('disabled', false);
			$('#adjArcvhivo').attr('disabled', false);
			
			$('#fechaDoc').val("");
			$('#fechaDocCompleta').val("");
			$('#txtFecNotRel').val("");
			
			$('#hiddIdEcmDoc').val("");//Para los casos que se recuperó el id del ecm
		}
		
		function anadirDoc(){
			
			var tableData = $('#tableDocumento').dataTable().fnGetData();
			var incluye305 = false;
			
			$.each(tableData, function(i, dato) {
				if(dato.codTipoDocumento == '007305') incluye305 = true;
			});
			
			if (incluye305 && datosExpedientes.indicadorAcumulado != '2') {
				mostrarDlgInfo("Adjuntar Documentos Internos", "Solo es posible desasociar Expedientes Virtuales que son acumuladores.");
			} else {
				tableData = $('#tableDocumento').dataTable().fnGetData();
				var listaCadena = JSON.stringify(tableData);
				var codTipoExpediente = datosExpedientes.codTipoExpediente;
				//INICIO LLRB 20012020
				if(($('#numExpeVirtual').val()!="")){
					var numExpeVirtual = $('#numExpeVirtual').val();					
					var formURL = 'regDocInterno.htm?action=adjuntarArchivosExpediente';
					$('html').block({message: '<h1>Procesando</h1>'});
					
					
					if ($('#hiddIndFlujoNormal').val() == "SI") {
						$("#listaTabla").val(listaCadena);
						$("#archivosLength").val(listaInputs.length);
						$("#numExpVirtual").val(numExpeVirtual);
						$("#codTipoExpediente").val(codTipoExpediente);
						$("#listaTabla").appendTo($('#frmAuxiliar'));
						$("#numExpVirtual").appendTo($('#frmAuxiliar'));
						$("#codTipoExpediente").appendTo($('#frmAuxiliar'));
						document.forms.frmAuxiliar.action = formURL;
						document.forms.frmAuxiliar.method = "POST";
						document.forms.frmAuxiliar.submit();
					} else {
						var dataEnvio = new Object();
						dataEnvio.listaTabla = listaCadena;
						dataEnvio.numExpVirtual = numExpeVirtual;
						dataEnvio.codTipoExpediente = codTipoExpediente;
						$.ajax({
							url : formURL,
							type : 'POST',
							async : true,
							dataType : "json",
							data : JSON.stringify(dataEnvio),
							contentType : "application/json",
							mimeType : "application/json",
							success : function(response) {
								var rpta = response.listDocRepetido
								volver();
							}
						});
					}
					
				}else{
					var numExpeVirtual = $('#numExpeVirtual_f').val();
					var formURL = 'regDocInterno.htm?action=adjuntarArchivosExpediente';
					$('html').block({message: '<h1>Procesando</h1>'});
					
					
					if ($('#hiddIndFlujoNormal').val() == "SI") {
						$("#listaTabla").val(listaCadena);
						$("#archivosLength").val(listaInputs.length);
						$("#numExpVirtual").val(numExpeVirtual);
						$("#codTipoExpediente").val(codTipoExpediente);
						$("#listaTabla").appendTo($('#frmAuxiliar'));
						$("#numExpVirtual").appendTo($('#frmAuxiliar'));
						$("#codTipoExpediente").appendTo($('#frmAuxiliar'));
						document.forms.frmAuxiliar.action = formURL;
						document.forms.frmAuxiliar.method = "POST";
						document.forms.frmAuxiliar.submit();
					} else {
						var dataEnvio = new Object();
						dataEnvio.listaTabla = listaCadena;
						dataEnvio.numExpVirtual = numExpeVirtual;
						dataEnvio.codTipoExpediente = codTipoExpediente;
						$.ajax({
							url : formURL,
							type : 'POST',
							async : true,
							dataType : "json",
							data : JSON.stringify(dataEnvio),
							contentType : "application/json",
							mimeType : "application/json",
							success : function(response) {								
								var rpta = response.listDocRepetido
								volver();
							}
						});
					}
				}			
			}
		}
		
		$(document).ready(function() {
			$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
			$("#busqueda").keypress(function(e){
				var lengthF = $(this).val();

				if (lengthF.length > 199){
					e.preventDefault();
				}
			});
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
					{data :	"fechaDoc" },
					{data :	"nombreArchivo" },
					{data :	"numOrden", 
						render : function(data, row){
						return jQuery('<span>').attr(
							{
								//"src" : "../a/imagenes/documents.gif",
								"class" : "cimg glyphicon glyphicon-download-alt",
								onClick : "verArchivo("+data+")"
							}
						).wrap('<div></div>').parent().html();
					}},
					//INCIO LLRB 28022020
					{data :	"numCopia" },
					//FIN LLRB 28022020
					{data :	"tamanoDoc" },
					{data :	"numOrden",
						render : function(data, row){
						return jQuery('<span>').attr(
							{
								//"src" : "../a/imagenes/icon_delete.png",
								"class" : "cimg glyphicon glyphicon-remove",
								onClick : "confirmarEliminar("+data+")"
							}
						).wrap('<div></div>').parent().html();
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
				iDisplayLength		:	10,
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

			//LOAD DATATIMEPICKER
			$.fn.datetimepicker.defaults.language = 'Es';

			$('#fechaDocumento').datetimepicker({
				format: 'DD/MM/YYYY',
				locale: 'es',
				autoclose: true,
				forceParse: false,
				pickTime: false
			});			
			
			$("#fechaDoc").attr("disabled", true); //eaguilar 11 JUL

			//INICIO [ATORRESCH 2017-04-25]
			$('#divFecNotRel').datetimepicker({
				format: 'DD/MM/YYYY',
				locale: 'es',
				autoclose: true,
				forceParse: false,
				pickTime: false
			});
			$("#txtFecNotRel").attr("disabled", true);
			//FIN
		

			$('#selVisCont').attr('disabled', true);
			
			var $element = $('#selTipoDocumento');			
			$.each(listadoTipoDocumentos, function(i, dato) {			
				var $option = $("<option/>").attr("value", dato.codTipoDocumento).text(dato.desTipoDocumentoCompuesto);
				$element.append($option);
				
			});
			
			//lista 3 digitos
			var $element = $('#selTipoDoc3Dig');
			listadoTipoDoc3Dig.sort(function(a, b){
				 var nameA = a.label.toLowerCase();
				 var nameB = b.label.toLowerCase();
				 if (nameA < nameB) //sort string ascending
					return -1;
				 if (nameA > nameB)
					return 1;
				 return 0; //default return value (no sorting)
			});

			$.each(listadoTipoDoc3Dig, function(i, dato) {
				var $option = $("<option/>").attr("value", dato.codigo).text(dato.label);
				$element.append($option);
			});			
			
			var $element = $('#selTipoDocOrigen');			
			$.each(listDocAsocExped, function(i, dato) {			
				var $option = $("<option/>").attr("value", dato.numCorDoc).text(dato.desNombreCompuesto);
				$element.append($option);				
			});
			
			//eaguilar: SI NO ES COBRANZA COACTIVA:
			if(!gEsCobranza){
				//activa combo Tipo
				$('#selTipoDocumento').attr('disabled', false);
				//oculta fecha notif:
				$('#divSelectDocGroup').addClass('hiddenDiv');
				if (!gEsAduanas){//[oachahuic][PAS20165E210400270]
					$('#divLabelFecNot').addClass('hiddenDiv');
					$('#divInputFecNot').addClass('hiddenDiv');
				}
				//activa campos:
				$('#selTipoDocumento').on('change', function(e) {
					$('#numDocumento').val("");
					$('#fechaDoc').val("").change();
					//Inicio - [oachahuic][PAS20165E210400270]
					$('#fechaDoc').attr('disabled', false);
					$('#fechaNot').val("").change();
					$('#busqueda').val("");
					$("#selTipoDocOrigen").val("").change();
					$('#selTipoDocOrigen').attr('disabled', false);
					$("#selVisCont").val($("#target option:first").val());
					$('#selVisCont').attr('disabled', true);
					//Fin - [oachahuic][PAS20165E210400270]
				});
				$("#fechaDoc").attr("disabled", false); //eaguilar 11 JUL
				
			}	
			
			//[PAS20191U210500144] Inicio 
			if(gEsFisca && (datosExpedientes.codTipoExpediente == 430 || datosExpedientes.codTipoExpediente == 431) ){
				$("#divResTribCab").show(); //Para expedientes de Fisca muestra los campos reserva tributaria y visible al contribuyente
				$("#divResTrib").show();
				//Los documentos a cargar son firmados digitalmente
				//$("#btnFirmar").show();//muestro cuando el documento adjunto es pdf 
				$("#btnFirmar").hide();
				$("#adjArcvhivo").hide();
				//INICIO LLRB 14022020
				$("#divSelectCopia").show(); 
				$('#divSelectDocGroup').addClass('hiddenDiv');
				//FIN LLRB 14022020
			}
			//[PAS20191U210500144] Fin
				
			//INICIO FIN[LLRB 15012020]
			if((datosExpedientes.codTipoExpediente == 430 || datosExpedientes.codTipoExpediente == 431) ) {
				iniciarCamposFiltro();
			}else{
				iniciarCampos();
			}
			
			//FIN FIN[LLRB 15012020]	
			
			function iniciarCampos(){
				$('#DocumentoInterno').show();
				$('#DocumentoInternoFiltro').hide();
				
				
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
				$('#codProceso').attr("value", datosExpedientes.codProceso);
				//[ATORRESCH 2017-04-26]
				$('#divLabelFecNotRel').addClass('hiddenDiv');
				$('#divInputFecNotRel').addClass('hiddenDiv');
				//[PAS20191U210500144] Inicio 
				//$('#btnFirmar').addClass('hiddenDiv');
				//[PAS20191U210500144] Fin 
			}
			//INICIO LLRB 15012020
			function iniciarCamposFiltro(){	
				$('#DocumentoInternoFiltro').show();
				$('#DocumentoInterno').hide();
				
				$('#numRuc_f').attr("value", datosExpedientes.numRuc);
				$('#razonSocial_f').attr("value", datosExpedientes.desRazonSocial);
				$('#numExpeOrigen_f').attr("value", datosExpedientes.numExpedienteOrigen);
				$('#numExpeVirtual_f').attr("value", datosExpedientes.numExpedienteVirtual);
				$('#estExpediente_f').attr("value", datosExpedientes.desEstado);
				$('#desProceso_f').attr("value", datosExpedientes.desProceso);
				$('#desExpediente_f').attr("value", datosExpedientes.desTipoExpediente);
				$('#desOrigen_f').attr("value", datosExpedientes.desOrigen);
				$('#fechaOrigen_f').attr("value", fechaOrigenDoc);
				$('#fechaVirtual_f').attr("value", fechaRegistro);
				$('#codProceso_f').attr("value", datosExpedientes.codProceso);
				//[ATORRESCH 2017-04-26]
				$('#divLabelFecNotRel').addClass('hiddenDiv');
				$('#divInputFecNotRel').addClass('hiddenDiv');

			}
			//FIN LLRB 15012020
			
			$('#exportar').attr('disabled', true);
			$('#adjuntar').attr('disabled', true);
		});
		
		function verArchivo(data) {
			var tabla = $('#tableDocumento').dataTable().fnGetData();
			var formURL = 'regDocInterno.htm?action=verArchivo';
			var listaCadena = JSON.stringify(tabla);
			document.forms.formPrincipal.action = formURL;
			document.forms.formPrincipal.method = "POST";
			$('#hiddenListaExp').val('');
			$('#hiddenNumArchivo').val('');
			$('#hiddenListaExp').val(listaCadena);
			$('#hiddenNumArchivo').val(data);
			$('html').block({message: '<h1>Procesando</h1>'});
			document.forms.formPrincipal.submit();
			$('html').unblock();
		}
		
		function eliminar(archivo){
			var tabla = $('#tableDocumento').dataTable().fnGetData();
			$('#tableDocumento').dataTable().fnClearTable();
			var fileData;
			var a=1;
			for(var i=0;i<tabla.length;i++){
				if(tabla[i].numOrden != archivo){					
					fileData = new Object();
					fileData.numOrden = a;
					fileData.desTipdoc = tabla[i].desTipdoc;
					fileData.numDoc = tabla[i].numDoc;
					fileData.fechaDoc = tabla[i].fechaDoc;
					fileData.fechaDocCompleta = tabla[i].fechaDocCompleta;
					fileData.nombreArchivo = tabla[i].nombreArchivo;
					fileData.tamanoDoc = tabla[i].tamanoDoc;
					fileData.pesoArchivo = tabla[i].pesoArchivo;
					fileData.busqueda = tabla[i].busqueda;
					fileData.codTipoDocumento = tabla[i].codTipoDocumento;
					fileData.nombreArchivoTemp = tabla[i].nombreArchivoTemp;
					fileData.mimeType = tabla[i].mimeType;
					fileData.indAcumulador = tabla[i].indAcumulador;
					fileData.file = tabla[i].file;
					fileData.asociado = tabla[i].asociado;
					fileData.estado = tabla[i].estado;
					fileData.etapa = tabla[i].etapa;
					fileData.fecNotRel = tabla[i].fecNotRel;
					fileData.indVisDocumento = tabla[i].indVisDocumento;
					fileData.codIdEcm = tabla[i].codIdEcm;
					fileData.codTipDocRel = tabla[i].codTipDocRel;
					//INICIO LLRB 10/02/2020
					fileData.numCopia = tabla[i].numCopia;
					fileData.numCorDoc = tabla[i].numCorDoc;
					//INICIO LLRB 10/02/2020
					$('#tableDocumento').dataTable().fnAddData(fileData);
					a++;
				}else{
					var temp = parseInt(tabla[i].pesoArchivo);
					totalMg = totalMg - temp;
					$('#totalPeso').val(formatSizeUnits(totalMg));
				}
				contador = a;
			};
			if(a == 1){
				$('#exportar').attr('disabled', true);
				$('#adjuntar').attr('disabled', true);
			}
			
		}

		function volver(){	
			//INICIO LLRB 24012020
			if(($('#numExpeOrigen').val())!=""){
				var numExpeOrigen = $('#numExpeOrigen').val();
				var formURL = 'regDocInterno.htm?action=seleccionaNroExpOrigen&aux='+numExpeOrigen;
				document.forms.formPrincipal.action = formURL;
				document.forms.formPrincipal.method = "POST";
				$('html').block({message: '<h1>Procesando</h1>'});
				document.forms.formPrincipal.submit();
			}else{
				var numExpeOrigen = $('#numExpeOrigen_f').val();
				var formURL = 'regDocInterno.htm?action=seleccionaNroExpOrigen&aux='+numExpeOrigen;
				document.forms.formPrincipal.action = formURL;
				document.forms.formPrincipal.method = "POST";
				$('html').block({message: '<h1>Procesando</h1>'});
				document.forms.formPrincipal.submit();
			}
			//FIN LLRB 24012020
		}
		
		function validarArchivoUpload() {
			
			var input = $('#docFisico');
			var nombreArchivo =  label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
			
			//[PAS20191U210500144] Inicio
			var posicion = nombreArchivo.lastIndexOf(".");
			var longitud = nombreArchivo.lenght;
			var extension = nombreArchivo.substring(posicion+1, longitud);
			if(extension == 'pdf'){
				//Se debe firmar 
				if(gEsFisca && (datosExpedientes.codTipoExpediente == 430 || datosExpedientes.codTipoExpediente == 431) ){
					$("#btnFirmar").show();//muestro cuando el documento adjunto es pdf
					$("#adjArcvhivo").hide();
					$('#hiddFirmaDigital').val('1')
					
				} else {
					$("#btnFirmar").hide();
					$("#adjArcvhivo").show();
					$('#hiddFirmaDigital').val('0')
				}
				
			} else {
				$("#btnFirmar").hide();
				$("#adjArcvhivo").show();
				$('#hiddFirmaDigital').val('0')
			}
			//[PAS20191U210500144] Fin
			
			if(nombreArchivo.length>100){
				var control = $("#docFisico");
				control.replaceWith( control = control.clone( true ) );
				control.val(null);
				$("#docFisico").val("");
				mostrarDlgInfo("Adjuntar Documentos Internos", "El nombre del archivo a cargar debe tener un m&aacute;ximo de 100 car&aacute;cteres.");		
			}
			else{
				$('html').block({message: '<h1>Procesando</h1>'});
				$("#pesoTotal").val(totalMg);
				$("#pesoTotal").appendTo($('#frmAuxiliar'));
				$("#docFisico").appendTo($('#frmAuxiliar'));
				$('#frmAuxiliar').submit();
				$("#docFisico").appendTo($('#manejadorArchivo'));
				$('html').unblock();
			}							
		}
		
		$("#frmAuxiliar").submit(function(e){
			var formObj = $(this);
			var iframeId = 'unique' + (new Date().getTime());
			
			var iframe = $('<iframe height="200" width="100" src="javascript:false;" name="'+iframeId+'" />');
			
			iframe.hide();
			formObj.attr('target', iframeId);
			formObj.attr('action', '${pageContext.request.contextPath}/regDocInterno.htm?action=validaDocumento&indCarga=1');
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
					//document.write(doc.documentElement.innerHTML);
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
					if(jsonDataString){
						response = jQuery.parseJSON(jsonDataString);
						if (response.tamanoArchivoSuperado) {
							var control = $("#docFisico");
							control.replaceWith( control = control.clone( true ) );
							control.val(null);
							$("#docFisico").val("");
							if(response.error == 0){
								mostrarDlgInfo("Adjuntar Documentos Internos", "El archivo seleccionado está vac&iacute;o.");	
							}
							else if(response.error == 1){
								mostrarDlgInfo("Adjuntar Documentos Internos", "El formato del archivo adjunto, no está soportado, debe ser un documento en Pdf, Word, Excel, Power Point o Imagen.");	
							}else if(response.error == 2){
								mostrarDlgInfo("Adjuntar Documentos Internos", "El tamaño total de los archivos supera el tamaño máximo de "+tamanoMaximo+"MB permitido por subida, este archivo no será adjuntado.");
							}else if(response.error == 3){
								mostrarDlgInfo("Adjuntar Documentos Internos", "El archivo seleccionado superará el tamaño máximo de "+tamanoMaximo+"MB permitido por subida, este archivo no será adjuntado.");
							}else if(response.error == 4){
								mostrarDlgInfo("Adjuntar Documentos Internos", "El nombre del archivo a cargar debe tener un máximo de 50 carácteres.");
							}
							
						} else {
							$('#hiddNomArchivo').val("");
							$('#hiddPesoArchivo').val("");
							$('#hiddPesoTotal').val("");
							$('#hiddMimeType').val("");
							
							$('#hiddNomArchivo').val(response.nombreArchivo);
							$('#hiddPesoArchivo').val(response.pesoArchivo);
							$('#hiddPesoTotal').val(response.pesoTotal);
							$('#hiddMimeType').val(response.mimeType);
														
							totalMg = response.pesoTotal;
						}
						if(response.grabadoExitoso){
							volver();
						}
					}
					else{
						//alert('error');
					}
					//volver( );
				}
				
			});
			
		});
		//enviar archivo temporal
		function enviarArchivoTemp() {
			$('#adjArcvhivo').attr('disabled', true);
			//$('html').block({message: '<h1>Procesando</h1>'});
			//[PAS20191U210500144] Inicio 
			if(datosExpedientes.codTipoExpediente == 430 || datosExpedientes.codTipoExpediente == 431){
				//$('#hiddFirmaDigital').val("1");
				$('#hiddFirmaDigital').appendTo($('#frmEnvioArchivo'));
			}
			//[PAS20191U210500144] Fin
			$("#docFisico").appendTo($('#frmEnvioArchivo'));
			$('#frmEnvioArchivo').submit();
			$("#docFisico").appendTo($('#manejadorArchivo'));
			$('#hiddIndFlujoNormal').val("SI");
		}
		
		$("#frmEnvioArchivo").submit(function(e){
				
			var formObj = $(this);
			var iframeId = 'unique' + (new Date().getTime());
			var iframe = $('<iframe height="200" width="100" src="javascript:false;" name="'+iframeId+'" />');
			
			iframe.hide();
			formObj.attr('target', iframeId);
			formObj.attr('action', '${pageContext.request.contextPath}/regDocInterno.htm?action=cargarArchivoTemp&temp=1');
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
				if(indError) {
					//document.write(doc.documentElement.innerHTML);
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
					if(jsonDataString){
						response = jQuery.parseJSON(jsonDataString);
						$('html').unblock();
						$('#nombreArchivoTemp').val(response.nombreArchivoTemp);
						
						//[PAS20191U210500144] Inicio
						if($('#hiddFirmaDigital').val()== '1'){
							firmarDocumento(response.nombreArchivoTemp);	
						} 
						//[PAS20191U210500144] Fin
						
						//Agrega registro(documento) a tableDocumento
						abc();
					}
					else{
					}
				}
			});
			
		});
		
		//[PAS20191U210500144] Inicio		
		function firmarDocumento(nombreArchivoTemp){
			$("#nombreArchivos").val(nombreArchivoTemp);//Nombre de archivo a aplicar firma
			$('input[name=rutaOrigen]').val(function(index, value) {
				   return value.replace(/\\/g, '@');
			});
			$('input[name=rutaDestino]').val(function(index, value) {
			   return value.replace(/\\/g, '@');
			});
			
			//var formURL = hostServicioSignnet + '/SignnetSignature/SignnetFirmaServicio';
			var formURL = hostServicioSignnet;
			document.forms.ssoForm.action = formURL;
			document.forms.ssoForm.method = "post";
			document.forms.ssoForm.target = "iframeFirma";
			document.forms.ssoForm.submit();				
			$("#dvIframe").modal('show');
			
		}
		//[PAS20191U210500144] Fin
		
		
		//obtiene el contenido del iFrame
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
		
		function confirmarAdjuntar(){ 
			titulo = "Adjuntar Documentos Internos";
			msj = "¿Está seguro de Adjuntar los archivos al expediente virtual?";
			botones = [];
			
			var botonSi = $("<input/>").attr(
				{
					value: "Si", 
					type: "button", 
					"class": "btn dlgButton btn-primary", 
					"data-dismiss" : "modal", 
					onclick : "anadirDoc()"
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
		
		function confirmarRetorno(){ 
			titulo = "Adjuntar Documentos Internos";
			msj = "¿Está seguro de regresar a la pantalla anterior?";
			botones = [];
			
			var botonSi = $("<input/>").attr(
				{
					value: "Si", 
					type: "button", 
					"class": "btn dlgButton btn-primary", 
					"data-dismiss" : "modal", 
					onclick : "volver()"
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
		
		function confirmarCancelar(){ 
			titulo = "Adjuntar Documentos Internos";
			msj = "¿Está seguro de Salir de Adjuntar Documentos Internos?";
			botones = [];
			
			var botonSi = $("<input/>").attr(
				{
					value: "Si", 
					type: "button", 
					"class": "btn dlgButton btn-primary", 
					"data-dismiss" : "modal", 
					onclick : "retornarBandeja()"
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
		
		function retornarBandeja(){
			
			var formURL = 'regDocInterno.htm?action=consultarProcesos&indCarga=0';
			document.forms.formPrincipal.action = formURL;
			document.forms.formPrincipal.method = "POST";
			$('html').block({message: '<h1>Procesando</h1>'});
			document.forms.formPrincipal.submit();
			
		}
		
		function justNumbers(e){
			var keynum = window.event ? window.event.keyCode : e.which;
			if ((keynum == 8))
			return true;
			 
			return /\d/.test(String.fromCharCode(keynum));
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
			
		function exportar(){
			var dataExp = $('#tableDocumento').dataTable().fnGetData();
			if(dataExp.length > 0){
				//INICIO LLRB 24/01/2020
				if($('#numExpeOrigen').val()!=''){	
					var listaCadena = JSON.stringify(dataExp);
					var formURL = 'regDocInterno.htm?action=seleccionaExportarExcel';
					document.forms.formPrincipal.action = formURL;
					document.forms.formPrincipal.method = "POST";
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
					var totalPeso = $('#totalPeso').val();
				}else{
					var listaCadena = JSON.stringify(dataExp);
					var formURL = 'regDocInterno.htm?action=seleccionaExportarExcelFisca';
					document.forms.formPrincipal.action = formURL;
					document.forms.formPrincipal.method = "POST";
					/*Obtener Paramtros Inicio*/
					var numRuc = $('#numRuc_f').val();
					var razonSocial = $('#razonSocial_f').val();
					var numExpeOrigen = $('#numExpeOrigen_f').val();
					var numExpeVirtual = $('#numExpeVirtual_f').val();
					var estExpediente = $('#estExpediente_f').val();
					var desProceso = $('#desProceso_f').val();
					var desExpediente = $('#desExpediente_f').val();
					var desOrigen = $('#desOrigen_f').val();
					var fechaOrigen = $('#fechaOrigen_f').val();
					var fechaVirtual = $('#fechaVirtual_f').val();
					var totalPeso = $('#totalPeso').val();
				}
				//FIN LLRB 24/01/2020
				
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
				$('#hiddenPesoTotal').val('');
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
				$('#hiddenPesoTotal').val(totalPeso);
				/*setear campos hdn fin*/
				$('html').block({message: '<h1>Procesando</h1>'});
				document.forms.formPrincipal.submit();
				$('html').unblock();
			}
		}
		
		function mostrarPagError() {
				
			var formURL = '${pageContext.request.contextPath}/error.htm?action=cargarPaginaError';
			document.forms.frmPrincipal.action = formURL;
			document.forms.frmPrincipal.method = "post";
			document.forms.frmPrincipal.submit();
			
		}
		
		function validarFormatoFecha(campo) {
			var RegExPattern = /^\d{1,2}\/\d{1,2}\/\d{2,4}$/;
			if ((campo.match(RegExPattern)) && (campo!='')) {
				return true;
			} else { 
				return false;
			}
		}
		
		function confirmarEliminar(fila) { 
			var tabla = $('#tableDocumento').DataTable();
			var temp = tabla.rows().data();
			nombreArchivo =temp[fila-1][4];
		
			titulo = "El archivo "+nombreArchivo+" Se eliminar&aacute;";
			msj = "&iquest;Esta seguro de Eliminar el Archivo Seleccionado?";
			botones = [];
			var botonSi = $("<input/>").attr(
				{
					value: "Si", 
					type: "button", 
					"class": "btn dlgButton btn-primary", 
					"data-dismiss" : "modal", 
					onclick : "eliminar("+fila+")"
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
		
		function validarDocSINE(codPro, codTipExp, codTipDoc) {//[PAS20181U210400241][oachahuic]
			if (codPro != '002') {
				return false;
			}
			if (!(codTipExp == '441' || codTipExp == '442')) {
				return false;
			}
			var listCodTipDoc = ["938011", "938021", "938012", "937031", "937032","963509","937040","937050"];
			for (var i = 0; i < listCodTipDoc.length; i++) {
				if (listCodTipDoc[i] == codTipDoc) {
					return true;
				}
			}
			return false;
		}
		
		function trim(cadena){
			var retorno = cadena.replace(/^\s+/g,'');
			retorno = retorno.replace(/\s+$/g,'');
			return retorno;
		}
	
		
		//var vent
		function activaCampos(){
			$('#selTipoDocumento')
		    .find('option')
		    .remove()
		    .end()
		    .append('<option value="">Seleccione</option>')
		    .val('');
	
			if($("#selTipoDoc3Dig").val() == "-"){
				$("#selTipoDocumento").attr('disabled', false);
				var $element = $('#selTipoDocumento');
				$("#fechaDoc").prop('disabled', false);
				$.each(listadoTipoDocumentosOtros, function(i, dato) {
					var $option = $("<option/>").attr("value", dato.codTipoDocumento).text(dato.desTipoDocumentoCompuesto);
					$element.append($option);
				});
			}else{
				var $element = $('#selTipoDocumento');
				$.each(listadoTipoDocumentos, function(i, dato) {
					var $option = $("<option/>").attr("value", dato.codTipoDocumento).text(dato.desTipoDocumentoCompuesto);
					$element.append($option);
				});
				$("#fechaDoc").prop('disabled', true); 
	
				$("#selTipoDocumento").attr('disabled', true);
	
				$('#numDocumento').attr('disabled', false);
				$("#numDocumento").attr('maxlength', 50); //se deja el maxlength como 50 para todos los numeros de doc
				$("#numDocumento").val("");
				$('#fechaDoc').val("");
				$('#fechaDocCompleta').val("");
				$("#selTipoDocumento").val("").change();
				//30 JUN eaguilar
				$("#selTipoDocOrigen").val("").change();
				$('#selTipoDocOrigen').attr('disabled', false);
				//[ATORRESCH 2017-04-25]
				$("#txtFecNotRel").prop('disabled', true);
				$("#txtFecNotRel").val("");
			}
		}
		// INICIO [ATORRESCH 2017-04-25]
		function fnOnchangeTipExp(){
			if($("#selTipoDocumento").val().substring(0, 3) == '803'){
			//CUANDO ES CONSTANCIA MUESTRA EL INPUT DE FECHA DE NOTIFICACION DEL DOCUMENTO RELACIONADO
				$('#divLabelFecNotRel').removeClass('hiddenDiv');
				$('#divInputFecNotRel').removeClass('hiddenDiv');
			}else{
				$('#divLabelFecNotRel').addClass('hiddenDiv');
				$('#divInputFecNotRel').addClass('hiddenDiv');
			}
			$("#docFisico").val("");
			$("#txtFecNotRel").val("");
			$('#docFisico').attr('disabled', false);//[oachahuic][PAS20181U210400241]
		}
		//FIN
	
		function setearIndVisible(){			
			var correlativo = $('#selTipoDocOrigen').val();
			var codProceso = $('#codProceso').val();
	
			if(correlativo == ""){ //NO SELECCIONA NINGUN DOCUMENTO RELACION
				$("#selVisCont").val($("#target option:first").val());
				$('#selVisCont').attr('disabled', true);
				//$('#selVisCont').children().remove().end().append('<option selected value="whatever">Seleccione</option>')
			}else{
				if($("#selTipoDocumento").val().substring(0, 3) == '803'){
					//SIEMPRE QUE SEA UNA CONSTANCIA LA VISIBILIDAD ES SI
					$('#selVisCont').val(1);
					if($('#codProceso').val() !='001' || $('#codProceso').val() !='003'){
						//SI ES DIFERENTE A COBRANZA Y ADUANAS
						$('#txtFecNotRel').prop('disabled', false);				
					}
				}
				else{ 
					//RECORRE LA LISTA DE DOCUMENTOS REQUERIDOS AL EXPEDIENTE
					//EN BUSCA DEL NUMERO DE DOCUMENTO PARA EXTRAER SU VISIBILIDAD
					$.each(listDocAsocExped, function(i, dato) {
						var numCorDoc = dato.numCorDoc;
						if(correlativo == numCorDoc){
							$('#selVisCont').val(dato.indVisDocumento);
						}
					});
				}
				//SE PUEDE CAMBIAR LA VISIBILIDAD POSTERIOR A ESTO
				$('#selVisCont').attr('disabled', false);
			}					
		}
		
		
		window.addEventListener("message", function (e) {
			var rptJSON = JSON.parse(e.data);
			$("#dvIframe").modal('hide');
		})
		//[PAS20191U210500144] Fin
		
	//INICIO LLRB 20012020
		function prepararDataEnviarTablaFisca(numCorDoc){
			var ruta = $('#docFisico').val();
			var busqueda = $('#busqueda').val();
			var fechaDoc = document.getElementById("fechaDoc").value;
			

			if (fechaDoc != "") {
				if (validarFormatoFecha(fechaDoc)) {
					if (existeFecha(fechaDoc)) {
						if (ruta != "") {
							//INICIO[ATORRESCH 2017-04-26]
							if ( $("#selTipoDocumento").val().substring(0, 3) == '803'
								&& $('#selTipoDocOrigen').val() !== ""
								&& $('#txtFecNotRel').val()  == "" ) {
									mostrarDlgInfo("Adjuntar Documentos Internos", "Debe ingresar la fecha de notificaci&oacute;n del documento relacionado.");
							} else {								
								devolverCopia();
								enviarArchivoTemp();
							}
							//FIN
						} else {//Inicio - [PAS20181U210400241][oachahuic]
							var codPro = $('#codProceso').val();
							var codTipExp = datosExpedientes.codTipoExpediente;
							var codTipDoc = $('#selTipoDocumento').val()
							var idEcmDoc = $('#hiddIdEcmDoc').val();
							var numCorDoc = numCorDoc;
							if (idEcmDoc != "") {//verificar si se recuperó el id el ecm para no solicitar la subida del archivo físico								
								devolverCopia(numCorDoc);
								adjuntarDocNotSineFisca(numCorDoc);
							} else {//Fin - [PAS20181U210400241][oachahuic]
								mostrarDlgInfo("Adjuntar Documentos Internos", "Debe seleccionar un archivo.");
							}
						}
					} else {
						mostrarDlgInfo("Adjuntar Documentos Internos", "La fecha de emisi&oacute;n es incorrecta.");
					}
				} else {
					mostrarDlgInfo("Adjuntar Documentos Internos", "La fecha de emisi&oacute;n es incorrecta.");
				}
			} else {
				mostrarDlgInfo("Adjuntar Documentos Internos", "Debe ingresar la fecha de emisi&oacute;n.");
			}
		}	
		function validarNumDocumento() {
			$('#hiddIdEcmDoc').val("");//Para los casos que se recuperó el id del ecm
			indNumRC = true;
			var numDocumento = $('#numDocumento').val();
			if (numDocumento == '') {
				return;
			}
			var codProceso = datosExpedientes.codProceso;
			if (codProceso == '001') {//PROCESO DE COBRAZA COACTIVA
				var codTipoDoc3Dig = $('#selTipoDoc3Dig').val();
				if(codTipoDoc3Dig == '' && gIsPastedValid) {
					$('#numDocumento').val("");
					mostrarDlgInfo("Adjuntar Documentos Internos", "Debe seleccionar un grupo de documentos.");
					return;
				}
			} else {
				var codTipoDocumento = $('#selTipoDocumento').val();
				if (codTipoDocumento == '') {
					mostrarDlgInfo("Adjuntar Documentos Internos", "Debe seleccionar un Tipo de Documento.");
					return;
				}
			}
			var dataEnvio = new Object();
			dataEnvio.codDependencia = datosExpedientes.codDependencia;
			dataEnvio.numeroRuc = datosExpedientes.numRuc
			dataEnvio.codTipoExpediente = datosExpedientes.codTipoExpediente;
			dataEnvio.numExpeOrigen = datosExpedientes.numExpedienteOrigen;
			dataEnvio.codTipoDoc = $('#selTipoDocumento').val();
			dataEnvio.numDocumento = trim(numDocumento);
			dataEnvio.codTipoDoc3Dig = $('#selTipoDoc3Dig').val();
			dataEnvio.numExpVirtual = datosExpedientes.numExpedienteVirtual;
			//INICIO LLRB 28012020
			var CopiaDocV=document.getElementById("copiaDoc").checked;			
			if(CopiaDocV==true){
				copiaPers=true;					
				dataEnvio.copiaDoc=	copiaPers;
			}else if(CopiaDocV==false){
				copiaPers=false;				
				dataEnvio.copiaDoc=	copiaPers;
			}
			//FIN LLRB 28012020
			$.ajax({
				url : '${pageContext.request.contextPath}/regDocInterno.htm?action=validarNumDocumento&indCarga=0',
				type : 'POST',
				async : true,
				dataType : "json",
				data : JSON.stringify(dataEnvio),
				contentType : "application/json",
				mimeType : "application/json",
				success : function(response) {
					var docOrigen = response.docOrigen;
					if (docOrigen.codRpta == "0") {
						indNumRC = false;
						mostrarDlgInfo("Adjuntar Documentos Internos", docOrigen.desError);
						
						$('#numDocumento').val("");
						$('#fechaDoc').val("");
						$('#fechaDocCompleta').val("");
						//$('#fechaDoc').attr('disabled', true);							
						if (dataEnvio.codTipoDoc3Dig != '') {
							$("#selTipoDocumento").val("").change();
							$("#selTipoDocumento").attr('disabled', true);
						}
						$('#fechaNot').val("");
						$("#selTipoDocOrigen").val("").change();
						$('#selTipoDocOrigen').attr('disabled', false);
						$("#txtFecNotRel").prop('disabled', true);
						$("#txtFecNotRel").val("");
						$("#selVisCont").val($("#target option:first").val());
						$('#selVisCont').attr('disabled', true);
					} else if (docOrigen.codRpta == "1") {
						if (dataEnvio.codTipoDoc3Dig != '') {
							$("#selTipoDocumento").attr('disabled', true);
							$("#selTipoDocumento").val(docOrigen.codTipDoc).change();
						}
						//$('#hiddIndAcumulador').val(obtenerNumDocu.indAcum)oooooooooo
						if (docOrigen.fecEmiDoc != "01/01/0001") {
							
							if(CopiaDocV == false){
								$('#fechaDocCompleta').val(docOrigen.strFecEmiDoc);	
							}
							else{
								$('#fechaDocCompleta').val("");	
								
							}
							
							$('#fechaDoc').val(docOrigen.fecEmiDoc);	
							$('#fechaDoc').attr('disabled', true);						
							$('#numCorDoc').val(docOrigen.numCorDoc);							
						}
						if (docOrigen.fecNotDoc != "01/01/0001") {
							$('#fechaNot').val(docOrigen.fecNotDoc);
						}
						if (docOrigen.numCorDocRel != "") {
							$('#selTipoDocOrigen').val(docOrigen.numCorDocRel).change();
							$('#selTipoDocOrigen').attr('disabled', true);
							if (docOrigen.indVisDocRel != "") {
								$('#selVisCont').val(docOrigen.indVisDocRel);
								$('#selVisCont').attr('disabled', true);
							}
						}
						var docNotSine = response.docOrigen.docNotSine;
						if (docNotSine != null) {
							$('#docFisico').attr('disabled', true);
							$('#selTipoDocOrigen').attr('disabled', true);
							$('#selVisCont').attr('disabled', true);
							$('#hiddNomArchivo').val(docNotSine.desDoc);
							$('#hiddPesoArchivo').val(docNotSine.cntDoc);
							$('#hiddIdEcmDoc').val(docNotSine.idEcmDoc);
							$('#hiddDesTipCons').val(docNotSine.codTipCons);
							$('#hiddFecCons').val(docNotSine.fecCons);
							$('#hiddFecCompCons').val(docNotSine.fecCompCons);
							$('#hiddDesCons').val(docNotSine.desCons);
							$('#hiddCntCons').val(docNotSine.cntCons);
							$('#hiddCodTipCons').val(docNotSine.codTipCons);
							$('#hiddIdEcmCons').val(docNotSine.idEcmCons);
							//if(gEsFisca){
								mostrarDlgInfo("Adjuntar Documentos Internos", "No puede cargar un archivo para este tipo de documento, debe importarlo desde el SINE. Para hacerlo, debe dar click en el botón \"Adjuntar Documento\".");
								$("#btnFirmar").hide();
								$("#adjArcvhivo").show();
								$('#hiddFirmaDigital').val('0')
							    
							//} else {
							//	mostrarDlgInfo("Adjuntar Documentos Internos", "No puede cargar un archivo para este tipo de documento, debe importarlo desde el SINE. Para hacerlo, debe dar click en el botón \"Adjuntar Documento\".");
							//}
						}
					}

					//[PAS20191U210500144] Inicio - Fisca Fase I, actualiza lista de Doc. a Relacionar según Documento que se va adjuntar
					if (gEsFisca){
						var codTipoDocumento = $('#selTipoDocumento').val();  //tipo de documento
						var selTipoDocOrigen = $('#selTipoDocOrigen');  //Combobox Relacionado a
						if(codTipoDocumento == '937108' ){
							
							var names = $('#selTipoDocOrigen option').clone();

						    var val = codTipoDocumento;  
						    selTipoDocOrigen.empty();
						    names.filter(function(idx, el) {
						        return val === 'ALL' || $(el).text().indexOf('Solicitud de fiscalización') >= 0;
						    }).appendTo('#selTipoDocOrigen');
						    
						    names.filter(function(idx, el) {
						        return val === 'ALL' || $(el).val().indexOf('[' + codTipoDocumento + ']') >= 0;
						    }).appendTo('#selTipoDocOrigen');

							
						} else if (codTipoDocumento == '091070' || codTipoDocumento == '054081' ||codTipoDocumento == '926010' ||
								   codTipoDocumento == '926011' || codTipoDocumento == '054061' ||codTipoDocumento == '054020' ||
								   codTipoDocumento == '054071') {

							var names = $('#selTipoDocOrigen option').clone();
						    var val = codTipoDocumento;  
						    selTipoDocOrigen.empty();
						    names.filter(function(idx, el) {
						        return val === 'ALL' || $(el).text().indexOf('Requerimiento') >= 0;
						    }).appendTo('#selTipoDocOrigen');

						}
						
					}
					//[PAS20191U210500144] Fin 
				},
				error : function(response) {
					mostrarPagError();
				}
			});
		
		}
	
	//INICIO LLRB 22/02/2020	
	function adjuntarDocNotSineFisca(numCorDoc) {		
			var busqueda = $('#busqueda').val();
			
			if (busqueda == '') {
				busqueda = $('#desExpediente').val() + ";";
				var auxBusqueda = busqueda.replace(/ /g, ";");
				busqueda = auxBusqueda;
			}
			
			//AGREGAR DOCUMENTO
			var fileData = new Object();
			fileData.numOrden = contador;
			fileData.desTipdoc = $('#selTipoDocumento option:selected').text();
			fileData.numDoc = $('#numDocumento').val();
			fileData.fechaDoc = document.getElementById("fechaDoc").value;
			fileData.fechaDocCompleta = document.getElementById("fechaDocCompleta").value;
			fileData.nombreArchivo = $('#hiddNomArchivo').val();
			fileData.tamanoDoc = formatSizeUnits($('#hiddPesoArchivo').val());
			fileData.pesoArchivo = $('#hiddPesoArchivo').val();
			fileData.busqueda = busqueda;
			fileData.codTipoDocumento = $('#selTipoDocumento').val();
			fileData.codIdEcm = $('#hiddIdEcmDoc').val();
			//
			if(($('#numCorDoc').val()) != ""){				
				fileData.numCorDoc = $('#numCorDoc').val();	
			}else{
				fileData.numCorDoc = 0;
			}
			if(($('#numCopia').val())!=""){
				fileData.numCopia = $('#numCopia').val();
			}else{
				fileData.numCopia = 0;
			}
			
			totalMg = totalMg + parseInt($('#hiddPesoArchivo').val());
			contador++;
			$('#tableDocumento').dataTable().fnAddData(fileData);
			
			//AGREGAR CONSTANCIA
			fileData = new Object();
			fileData.numOrden = contador;
			fileData.desTipdoc = $('#hiddDesTipCons').val();
			fileData.numDoc = $('#numDocumento').val();
			fileData.fechaDoc = $('#hiddFecCons').val();
			fileData.fechaDocCompleta = $('#hiddFecCompCons').val();
			fileData.nombreArchivo = $('#hiddDesCons').val();
			fileData.tamanoDoc = formatSizeUnits($('#hiddCntCons').val());
			fileData.pesoArchivo = $('#hiddCntCons').val();
			fileData.busqueda = busqueda;
			fileData.codTipoDocumento = $('#hiddCodTipCons').val();
			fileData.asociado = 0;
			fileData.fecNotRel = $('#fechaNot').val();
			fileData.indVisDocumento = "1";
			fileData.codIdEcm = $('#hiddIdEcmCons').val();
			fileData.codTipDocRel = $('#selTipoDocumento').val();
			//
			if(($('#numCorDoc').val()) != ""){				
				fileData.numCorDoc = $('#numCorDoc').val();
			}else{
				fileData.numCorDoc = 0;
			}
			if(($('#numCopia').val())!=""){
				fileData.numCopia = $('#numCopia').val();	
			}else{
				fileData.numCopia = 0;	
			}
			
			totalMg = totalMg + parseInt($('#hiddCntCons').val());
			contador++;
			$('#tableDocumento').dataTable().fnAddData(fileData);
			
			document.getElementById("docsPorAdj").reset();
			$('#docFisico').attr('disabled', false);
			$('#totalPeso').val("");
			$('#totalPeso').val(formatSizeUnits(totalMg));//ojo
			
			$('#exportar').attr('disabled', false);
			$('#adjuntar').attr('disabled', false);
			$('#adjArcvhivo').attr('disabled', false);
			
			$('#fechaDoc').val("");
			$('#fechaDocCompleta').val("");
			$('#txtFecNotRel').val("");
			
			$('#hiddIdEcmDoc').val("");//Para los casos que se recuperó el id del ecm
		}
	function iniciarCopia(){
		 var copiaDoc = document.getElementById("copiaDoc");		
		   if (copiaDoc.checked == true){
			    document.getElementById("docsPorAdj").reset();
			    document.getElementById('copiaDoc').checked = true;
				$('#docFisico').attr('disabled', false);
				$('#totalPeso').val("");
				$('#totalPeso').val(formatSizeUnits(totalMg));//ojo
				
				$('#exportar').attr('disabled', false);
				$('#adjuntar').attr('disabled', false);
				$('#adjArcvhivo').attr('disabled', false);
				
				$('#fechaDoc').val("");
				$('#fechaDocCompleta').val("");	
				$('#numDocumento').val("");
				$("#selTipoDocumento").val("").change();
				$("#selTipoDocOrigen").val("").change();
				$('#selTipoDocOrigen').attr('disabled', false);
				$("#txtFecNotRel").prop('disabled', true);
				$("#txtFecNotRel").val("");
				$("#selVisCont").val("").change();
				$('#selVisCont').attr('disabled', false);
				$('#busqueda').val("");		
				$("#selResTri").val("").change();
				$('#selResTri').attr('disabled', false);
				
				$('#hiddIdEcmDoc').val("");//Para los casos que se recuperó el id del ecm 
		   }
		
		}
	function devolverCopia(){	
		var dataEnvio = new Object();
		dataEnvio.numCorDoc = $('#numCorDoc').val();
		$.ajax({											
			url : '${pageContext.request.contextPath}/regDocInterno.htm?action=seleccionarNroCopia&indCarga=0',
			type : 'POST',
			async : true,
			dataType : "json",
			data : JSON.stringify(dataEnvio),
			contentType : "application/json",
			mimeType : "application/json",
			//timeout : 5000,
			success : function(response) {									
				var copiaDocumento = response.numCopia
				if (copiaDocumento != 1) {					
					$('#numCopia').val(copiaDocumento);	
					
				}else{
					$('#numCopia').val(1);	
				}
			},
			error : function(response) {
				mostrarPagError();
			}
		});
	}
		//FIN LLRB 20012020
	
		</script>		
	</body>
</html>