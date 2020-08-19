<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=EDGE" />
<meta name="viewport"
	content="initial-scale = 1.0, user-scalable = no,  width=device-width">
<title>CONSULTA Y REPORTES DE EXPEDIENTES ASIGNADOS</title>
<jsp:include page="ExpedienteCommonHeader.jsp" flush="true" />  
  
	<!--[if lt IE 10]>
      <script src="/a/js/libs/bootstrap/3.3.2/plugins/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="/a/js/libs/bootstrap/3.3.2/plugins/respond/1.4.2/respond.min.js"></script>
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
			.alinearCentrado {
				vertical-align: middle !important;
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
			<div class="row">
				<div class="panel panel-primary">
					<div class="panel-heading align="center">
						<h3 class="panel-title" align="center">CONSULTA Y REPORTES DE EXPEDIENTES ASIGNADOS</h3>
						<form id="frmPrincipal" class="form-horizontal" role="form">
						</form>
					</div>
				</div>	
				<div class="panel panel-primary">
					<form class="form-horizontal" role="form" name="frmFiltroBusquedaEspecificaEspecifica" id="frmFiltroBusquedaEspecifica" method="post">
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
								<legend class="scheduler-border"> Consulta Espec&iacute;fica </legend>
								<div class="form-group">
									<div class="col-md-1"><Strong>N&deg; de Expediente:</Strong></div>						
									<div class="col-md-4">										
										<input name="numExp" id="txtNumeroExpediente" type="text" class="form-control tamanoMaximo" ></input>										
										<input id="numAcumulador" name="numAcumulador" type="hidden"/>
									</div>
								</div>
						</fieldset>		
					</form>	
					<form class="form-horizontal" role="form" name="frmFiltroBusquedaAvanzada" id="frmFiltroBusquedaAvanzada" method="post">							
						<div style="margin : 15px 15px !important">
							<div class="row content-box">
								<div class="col-md-12 text-right">
									<input type="button" class="btn btn-primary" value="Limpiar" id="btnLimpiar" onClick="limpiar()"></input>
									<input type="button" class="btn btn-primary" id="btnCancelar" intermediateChanges="false" onClick="revalidarFormulario()" value="Consultar"></input>
								</div>
							</div>
						</div>
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border"> Listado de Expedientes </legend>
							<div class="form-group">	
								<div class="col-md-12">
								<div class="tab-content">
								<div id="accionesFiscalizacion" class="tab-pane fade in active">
								<div class="form-group" style="margin : 12px 5px !important" id="expediente">
								<div class="border-line panel-scroll">	
									<table id="tblExpedientes" class="table display" cellspacing="0" style="width: 100%;">
										<thead>
											<tr>
												<th width="5%" class="text-center">N&deg;</th>
												<th width="8%" class="text-center">N&deg; de Expediente</th>
												<th width="8%" class="text-center">Profesional Responsable</th>												
												<th width="8%" class="text-center">RUC</th>														
												<th width="8%" class="text-center">Raz&oacute;n Social</th>													
												<th width="8%" class="text-center">Proceso</th>														
												<th width="4%" class="text-center">Tipo de Expediente</th>
												<th width="6%" class="text-center">Estado Expediente</th>														
												<th width="5%" class="text-center">Fecha de Expediente</th>							
												<th width="5%" class="text-center">Origen</th>														
												<th width="2%" class="text-center">Opci&oacute;n</th>													
											</tr>
										</thead>
									</table>
									</div>
									</div>
									</div>
								</div>		
							</div>
							</div>					
						</fieldset>
						<div class="form-group">
							<div class="col-md-12"  align="center">
									<button type="button" class="btn btn-primary" intermediateChanges="false" id="exportar" onclick="exportarExcel()">Exportar a Excel</button>
								</div>	
						</div>		
					</form>
					<form id="formPrincipal" class="form-horizontal" role="form">	
						<div class="col-md-5" align="right" id="dvSecBotones01">	
							<input id="campoHiddenExp" type="hidden" name="listadoExpedientesCadena"/>
						</div>	
					</form>	
				</div>		
			</div>
	    </div>	
		<!-- 	Inicio LLRB 12/12	MODAL LISTA ARCHIVO  -->		
		<div id="modalExpVinculado" class="modal fade" role="dialog" style="width: 95%">
			<div class="modal-dialog" style="width: 1000px !important;">
			<input type="hidden" name="numExpeVirtualVinculadoModal" id="numExpeVirtualVinculadoModal">
				<div class="panel panel-info">
					<div class="panel-heading">
						<div>
							<div class="panel-heading">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
								</button>
								<div id="dlgTtitleExpVinculadoModal"></div>
							</div>
								<div class="row">
									<div class="panel panel-primary">
										<fieldset class="scheduler-border" style="margin : 15px 15px !important">
											<legend class="scheduler-border">Datos del Expediente</legend>
											<div class="form-group" id="vinculados">	
											<div class="panel-body">
												<div class="row content-box">
													<div class="col-md-3">
														<label>RUC: </label>
													</div>
													<div id="txtCampoRuc" class="col-md-3">
														<input id="numRucExpVinculadosModal" name="numeroRuc" type="text" class="form-control" disabled="disabled"/>
													</div>
													<div id="txtCampoRazonSocial" class="col-md-6">
														<input id="razonSocialExpVinculadosModal" name="razonSocialRuc" type="text" class="form-control" disabled="disabled"/>
													</div>
												</div>
												<div class="row content-box">&nbsp;</div>
												<div class="row content-box">
													<div class="col-md-3">
														<label>Proceso:</label>
													</div>
													<div id="txtCampo4" class="col-md-3">
														<input id="desProcesoExpVinculadosModal" name="descripcionProceso" type="text" class="form-control" disabled="disabled"/>
													</div>
													<div class="col-md-3">
														<label>Tipo de expediente:</label>
													</div>
													<div id="txtCampo5" class="col-md-3">
														<input id="desExpVinculadosModal" name="descripcionExpediente" type="text" class="form-control" disabled="disabled"/>
													</div>													
												</div>
												<div class="row content-box">&nbsp;</div>
												<div class="row content-box">
												<div class="col-md-3">
														<label>N&deg; Expediente :</label>
													</div>
													<div id="txtCampo2" class="col-md-3">
														<input id="numExpeOrigenExpVinculadosModal" name="numeroExpeOrigen" type="text" class="form-control" disabled="disabled"/>
													</div>
													<div class="col-md-3">
														<label>Fecha del expediente:</label>
													</div>
													<div id="txtCampo8" class="col-md-3">														
														<input id="fechaVirtualExpVinculadosModal" name="fechaExpedienteVirtual" type="text" class="form-control" disabled="disabled"/>
													</div>
												</div>
												<div class="row content-box">&nbsp;</div>
												<div class="row content-box">
													<div class="col-md-3">
														<label>Estado del Expediente:</label>
													</div>
													<div id="txtCampo3" class="col-md-3">
														<input id="estExpedienteExpVinculadosModal" name="estadoExpediente" type="text" class="form-control" disabled="disabled"/>
													</div>
												</div>
											</div>
											</div>											
										</fieldset>
										<fieldset class="scheduler-border" style="margin : 15px 15px !important">
											<legend class="scheduler-border">Listado de Expedientes</legend>
												<div class="panel-body">
												<div class="tab-content">
												<div id="accionesFiscalizacion" class="tab-pane fade in active">
												<div class="border-line panel-scroll">	
													<table id="tableExpVinculados" class="table display" cellspacing="0" style="width: 100%;"><!--table table-striped table-bordered-->
														<thead>
															<tr>
																<th width="5%" class="text-center">N&deg;</th>
																<th width="8%" class="text-center">N&deg; de Expediente</th>
																<th width="8%" class="text-center">Profesional Responsable</th>												
																<th width="8%" class="text-center">RUC</th>														
																<th width="8%" class="text-center">Raz&oacute;n Social</th>													
																<th width="8%" class="text-center">Proceso</th>														
																<th width="4%" class="text-center">Tipo de Expediente</th>
																<th width="6%" class="text-center">Estado Expediente</th>														
																<th width="5%" class="text-center">Fecha de Expediente</th>							
																<th width="5%" class="text-center">Origen</th>														
																<th width="2%" class="text-center">Opci&oacute;n</th>													
															</tr>
														</thead>
													</table>
													</div>
													</div>
													</div>
												</div>
										</fieldset>
										<div style="margin : 15px 15px !important">
											<div class="row content-box">
												<div class="col-md-3">
												</div>
												<div class="col-md-2">
													
												</div>
												<div class="col-md-2">
													<button type="button" class="btn btn-primary" id='exportarExpVinculados' onclick="exportarExpVinculadosModal()">Exportar a Excel</button>
												</div>
												<div class="col-md-2">
													
												</div>
												<div class="col-md-3">
												</div>
											</div>
										</div>
										<form id="formExpedienteModal" class="form-horizontal" role="form">
											<input id="hiddenListaExpExpedienteModal" type="hidden" name="hiddenListaExp"/>		
											<input id="hiddenNumRucExpedienteModal" type="hidden" name="hiddenNumRuc"/>
											<input id="hiddenRazonSocialExpedienteModal" type="hidden" name="hiddenRazonSocial"/>
											<input id="hiddenNumExpOrigenExpedienteModal" type="hidden" name="hiddenNumExpOrigen"/>
											<input id="hiddenNumExpVirtualExpedienteModal" type="hidden" name="hiddenNumExpVirtual"/>
											<input id="hiddenEstExpedienteExpedienteModal" type="hidden" name="hiddenEstExpediente"/>
											<input id="hiddenTipoProcesoExpedienteModal" type="hidden" name="hiddenTipoProceso"/>
											<input id="hiddenTipoExpedienteExpedienteModal" type="hidden" name="hiddenTipoExpediente"/>
											<input id="hiddenDescOrigenExpedienteModal" type="hidden" name="hiddenDescOrigen"/>
											<input id="hiddenFechaGeneracionExpedienteModal" type="hidden" name="hiddenFechaGeneracion"/>
											<input id="hiddenFechaOrigenExpedienteModal" type="hidden" name="hiddenFechaOrigen"/>
										</form>		
									</div>
								</div>	
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 		MODAL DOCUMENTOS Detalle de Documentos -->
		<div id="modalDocumentos" class="modal fade" role="dialog" style="width: 100%">
			<div class="modal-dialog" style="width: 1000px !important;">
				<div class="panel panel-info">
					<div class="panel-heading">
						<div>
						<div class="panel-heading">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
							</button>
							<div id="dlgTitleModalDocumentos"></div>
						</div>
							<div class="row">
									<div class="panel panel-primary">
										<fieldset class="scheduler-border" style="margin : 15px 15px !important">
										<legend class="scheduler-border">Datos del Expediente</legend>
											<div class="panel-body">
												<div class="row content-box">
													<div class="col-md-2">
														<label>RUC: </label>
													</div>
													<div id="txtCampoRuc" class="col-md-2">
														<input id="numRucDocumentoModal" name="numeroRuc" type="text" class="form-control" disabled="disabled"/>
													</div>
													<div id="txtCampoRazonSocial" class="col-md-8">
														<input id="razonSocialDocumentoModal" name="razonSocialRuc" type="text" class="form-control" disabled="disabled"/>
													</div>
												</div>
												<div class="row content-box">&nbsp;</div>
												<div class="row content-box">
													<div class="col-md-2">
														<label>N&deg; Expediente:</label>
													</div>
													<div id="txtCampo1" class="col-md-2">
														<input id="numExpeOrigenDocumentoModal" name="numeroExpeOrigen" type="text" class="form-control" disabled="disabled"/>
														<input id="numExpeVirtualDocumentoModal" name="numeroExpeVirtual" type="hidden" class="form-control" disabled="disabled"/>
													</div>								
													<div class="col-md-1">
														<label>Estado expediente:</label>
													</div>
													<div id="txtCampo3" class="col-md-2">
														<input id="estExpedienteDocumentoModal" name="estadoExpediente" type="text" class="form-control" disabled="disabled"/>
													</div>												
												</div>	
												<div class="row content-box">&nbsp;</div>
												<div class="row content-box">												
													<div class="col-md-2">
														<label>Proceso:</label>
													</div>
													<div id="txtCampo4" class="col-md-2">
														<input id="desProcesoDocumentoModal" name="descripcionProceso" type="text" class="form-control" disabled="disabled"/>
													</div>
													<div class="col-md-2">
														<label>Tipo de expediente:</label>
													</div>
													<div id="txtCampo5" class="col-md-3">
														<input id="desExpedienteDocumentoModal" name="descripcionExpediente" type="text" class="form-control" disabled="disabled"/>
													</div>
													<div class="col-md-1">
														<label>Origen Expediente:</label>
													</div>
													<div id="txtCampo6" class="col-md-2">
														<input id="desOrigenDocumentoModal" name="descripcionOrigen" type="text" class="form-control" disabled="disabled"/>
													</div>													
												</div>
												<div class="row content-box">&nbsp;</div>
												<div class="row content-box">
													<div class="col-md-2">
														<label>Fecha de registro del Expediente:</label>
													</div>
													<div id="txtCampo7" class="col-md-2">
														<input id="fechaVirtualDocumentoModal" name="fechaExpedienteVirtual" type="text" class="form-control" disabled="disabled"/>
													</div>
													<div class="col-md-2">
														<label>Fecha de documento origen:</label>
													</div>
													<div id="txtCampo8" class="col-md-2">														
														<input id="fechaOrigenDocumentoModal" name="fechaOrigenExpediente" type="text" class="form-control" disabled="disabled"/>
													</div>
												</div>
												</div>
										</fieldset>
										</div>
										<div class="row">
											<div class="col-md-4"></div>
											<div class="col-md-4"  style="color:#ff0000" id="divErrorExtension"></div>
											<div class="col-md-4"></div>
										</div>
										<div class="panel panel-primary">
										<fieldset class="scheduler-border" style="margin : 15px 15px !important">			
											<legend class="scheduler-border">Listado de Documentos </legend>
												<div class="panel-body">
												<div class="row content-box">
												<div class="tab-content">
												<div id="accionesFiscalizacion" class="tab-pane fade in active">
												<div class="form-group" style="margin : 12px 5px !important" id="DocumentoCompleto">
												<div class="border-line panel-scroll">		
													<table id="tableDocumentoModal" class="table display" cellspacing="0" style="width: 100%;">
														<thead >
															<tr>
														    	<th width="3%">N&deg;</th>														    	
																<th width="20%">Tipo de Documento</th>																
																<th width="10%">N&uacute;mero de Documento</th>															
																<th width="10%">Origen registro documento</th>																
																<th width="10%">Visible al Contrib</th>
																<th width="10%">Con Reserva Tributaria</th>															
																<th width="22%">Responsable registro documento</th>																
																<th width="5%">Fecha registro documento</th>
																<th width="5%">Fecha emisi&oacute;n documento</th>														
																<th width="10%" >Fecha de Notificaci&oacute;n</th>
																<th width="10%" >Forma de Notificaci&oacute;n</th>															
																<th width="5%">N&deg; de Expediente </th>
																<th width="5%">Tipo Doc Relacionado</th>
																<th width="5%">Num Doc Relacionado</th>															
																<th width="5%">Ver archivo</th>	
																<!-- INICIO [LLRB 16/01/2020]-->
																<th width="5%">Copia Personalizada</th>
																<!-- FIN [LLRB 16/01/2020]-->									
																<th width="5%">Representaci&oacute;n <br> Impresa</th>
																<!--<th width="5%">N&deg; de Expediente Acumulador</th>	 -->
																<th></th>
																<th></th>
																<th></th>
															</tr>
														</thead>
														<tbody></tbody>
													</table>	
												</div>
												</div>
													</div>
													</div>
												</div>		
											</div>	
											<!-- INICIO LLRB 16122020 -->
											<div class="form-group" style="margin : 12px 5px !important" id="DocumentoIncompleto">
												<div class="border-line panel-scroll">		
													<table id="tableDocumentoFiltroModal" class="table display" cellspacing="0" style="width: 100%;">
														<thead >
															<tr>
														    	<th width="3%">N&deg;</th>
														    
																<th width="20%">Tipo de Documento</th>
															
																<th width="10%">N&uacute;mero de Documento</th>																
																
																<th width="10%">Origen registro documento</th>
															
																<!--<th width="5%">N&deg; Requerimiento (Si corresponde)</th>-->
																
																<th width="22%">Responsable registro documento</th>
																
																<th width="5%">Fecha registro documento</th>
																<th width="5%">Fecha emisi&oacute;n documento</th>
															
																<th width="10%" >Fecha de Notificaci&oacute;n</th>
																<th width="10%" >Forma de Notificaci&oacute;n</th>
																
																<th width="5%">N&deg; de Expediente </th>																
																<th width="5%">Ver archivo</th>																	
																<th width="5%">Copia Personalizada</th>												
																<th width="5%">Representaci&oacute;n <br> Impresa</th>
																<!-- <th width="5%">N&deg; de Expediente Acumulador</th>	 -->
																<th></th>
																<th></th>
																<th></th>
															</tr>
														</thead>
														<tbody></tbody>
													</table>	
												   </div>
												</div>
											<!-- FIN LLRB 16122020 -->										
										</fieldset>
								
										<div class="form-group">
											<div class="col-md-12" align="center" id="dvSecBotones01">	
												<button type="button" class="btn btn-primary" intermediateChanges="false" id ='exportarDocumentos' onclick="exportarDocumentosModal()">Exportar a Excel</button>
											</div>
										</div>	
									</div>	

										<form id="formDocumentoModal" class="form-horizontal" role="form">
											<input id="hiddenListaExpDocumentoModal" type="hidden" name="hiddenListaExpDocumentoModal"/>		
											<input id="hiddenNumRucDocumentoModal" type="hidden" name="hiddenNumRucDocumentoModal"/>
											<input id="hiddenRazonSocialDocumentoModal" type="hidden" name="hiddenRazonSocialDocumentoModal"/>
											<input id="hiddenNumExpOrigenDocumentoModal" type="hidden" name="hiddenNumExpOrigenDocumentoModal"/>
											<input id="hiddenNumExpVirtualDocumentoModal" type="hidden" name="hiddenNumExpVirtualDocumentoModal"/>
											<input id="hiddenEstExpedienteDocumentoModal" type="hidden" name="hiddenEstExpedienteDocumentoModal"/>
											<input id="hiddenTipoProcesoDocumentoModal" type="hidden" name="hiddenTipoProcesoDocumentoModal"/>
											<input id="hiddenTipoExpedienteDocumentoModal" type="hidden" name="hiddenTipoExpedienteDocumentoModal"/>
											<input id="hiddenDescOrigenDocumentoModal" type="hidden" name="hiddenDescOrigenDocumentoModal"/>
											<input id="hiddenFechaGeneracionDocumentoModal" type="hidden" name="hiddenFechaGeneracionDocumentoModal"/>
											<input id="hiddenFechaOrigenDocumentoModal" type="hidden" name="hiddenFechaOrigenDocumentoModal"/>
											<!-- inicio JEFFIO -->
											<input id="hiddenfechaNotificacionModal" type="hidden" name="hiddenfechaNotificacionModal"/>
											<input id="hiddenforhaNotificacionModal" type="hidden" name="hiddenforhaNotificacionModal"/>										
											<!-- fin JEFFIO -->
											<!-- inicio LROMERO-->
											<input id="hiddenIndVisibleModal" type="hidden" name="hiddenIndVisibleModal"/>
											<input id="hiddenIndReserTribModal" type="hidden" name="hiddenIndReserTribModal"/>
											<input id="hiddenTipoDocRelacionadoModal" type="hidden" name="hiddenTipoDocRelacionadoModal"/>
											<input id="hiddenNumDocRelacionadoModal" type="hidden" name="hiddenNumDocRelacionadoModal"/>
											<!-- fin LROMERO -->
										</form>
								</div>	
							</div>
						</div>
					</div>
			  </div>
		</div>		
	<div id="modalObservaciones" class="modal fade" role="dialog" style="width: 95%">
			<div class="modal-dialog" style="width: 1000px !important;">
				<div class="panel panel-info">
					<div class="panel-heading">
						<div>
						<div class="panel-heading">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
							</button>
							<div id="dlgTitleModalObservaciones"></div>
						</div>
							<div class="row">
								<div class="panel panel-primary">
									<fieldset class="scheduler-border" style="margin : 15px 15px !important">
									<legend class="scheduler-border">Datos del Expediente</legend>
									  <div class="form-group" id="observaciones">
										<div class="panel-body">
											<div class="row content-box">
												<div class="col-md-2">
													<label>RUC: </label>
												</div>
												<div id="txtCampoRuc" class="col-md-2">
													<input id="numRucObservacionModal" name="numeroRuc" type="text" class="form-control" disabled="disabled"/>
												</div>
												<div id="txtCampoRazonSocial" class="col-md-8">
													<input id="razonSocialObservacionModal" name="razonSocialRuc" type="text" class="form-control" disabled="disabled"/>
												</div>
											</div>
											<div class="row content-box">&nbsp;</div>
											<div class="row content-box">
												<div class="col-md-2">
													<label>N&deg; Expediente Origen:</label>
												</div>
												<div id="txtCampo1" class="col-md-2">
													<input id="numExpeOrigenObservacionModal" name="numeroExpeOrigen" type="text" class="form-control" disabled="disabled"/>
												</div>
												<div class="col-md-2">
													<label>N&deg; Expediente Virtual:</label>
												</div>
												<div id="txtCampo2" class="col-md-2">
													<input id="numExpeVirtualObservacionModal" name="numeroExpeVirtual" type="text" class="form-control" disabled="disabled"/>
												</div>
												<div class="col-md-2">
													<label>Estado expediente:</label>
												</div>
												<div id="txtCampo3" class="col-md-2">
													<input id="estExpedienteObservacionModal" name="estadoExpediente" type="text" class="form-control" disabled="disabled"/>
												</div>
											</div>										
											<div class="row content-box">&nbsp;</div>
											<div class="row content-box">
												<div class="col-md-2">
													<label>Proceso:</label>
												</div>
												<div id="txtCampo4" class="col-md-2">
													<input id="desProcesoObservacionModal" name="descripcionProceso" type="text" class="form-control" disabled="disabled"/>
												</div>
												<div class="col-md-2">
													<label>Tipo de expediente:</label>
												</div>
												<div id="txtCampo5" class="col-md-2">
													<input id="desExpedienteObservacionModal" name="descripcionExpediente" type="text" class="form-control" disabled="disabled"/>
												</div>	
												<div class="col-md-2">
													<label>Origen:</label>
												</div>
												<div id="txtCampo6" class="col-md-2">
													<input id="desOrigenObservacionModal" name="descripcionOrigen" type="text" class="form-control" disabled="disabled"/>
												</div>											
											</div>
											<div class="row content-box">&nbsp;</div>
											<div class="row content-box">
												<div class="col-md-2">
													<label>Fecha de registro:</label>
												</div>
												<div id="txtCampo7" class="col-md-2">
													<input id="fechaVirtualObservacionModal" name="fechaExpedienteVirtual" type="text" class="form-control" disabled="disabled"/>
												</div>
												<div class="col-md-2">
													<label>Fecha de documento origen:</label>
												</div>
												<div id="txtCampo8" class="col-md-2">
													<input id="fechaOrigenObservacionModal" name="fechaOrigenExpediente" type="text" class="form-control" disabled="disabled"/>
												</div>
											</div>
											</div>
										</div>
										<!-- INICIO[LLRB 15012020] -->
										  <div class="form-group" id="observacionesFisca">
										<div class="panel-body">
											<div class="row content-box">
												<div class="col-md-2">
													<label>RUC: </label>
												</div>
												<div id="txtCampoRuc" class="col-md-2">
													<input id="numRucObservacionModal_f" name="numeroRuc" type="text" class="form-control" disabled="disabled"/>
												</div>
												<div id="txtCampoRazonSocial" class="col-md-8">
													<input id="razonSocialObservacionModal_f" name="razonSocialRuc" type="text" class="form-control" disabled="disabled"/>
												</div>
											</div>
											<div class="row content-box">&nbsp;</div>
											<div class="row content-box">
												<div class="col-md-2">
													<label>N&deg; Expediente:</label>
												</div>
												<div id="txtCampo1" class="col-md-2">
													<input id="numExpeOrigenObservacionModal_f" name="numeroExpeOrigen" type="text" class="form-control" disabled="disabled"/>
												</div>
												<div class="col-md-2">
													<label>Estado expediente:</label>
												</div>
												<div id="txtCampo3" class="col-md-2">
													<input id="estExpedienteObservacionModal_f" name="estadoExpediente" type="text" class="form-control" disabled="disabled"/>
												</div>
											</div>										
											<div class="row content-box">&nbsp;</div>
											<div class="row content-box">
												<div class="col-md-2">
													<label>Proceso:</label>
												</div>
												<div id="txtCampo4" class="col-md-2">
													<input id="desProcesoObservacionModal_f" name="descripcionProceso" type="text" class="form-control" disabled="disabled"/>
												</div>
												<div class="col-md-2">
													<label>Tipo de expediente:</label>
												</div>
												<div id="txtCampo5" class="col-md-2">
													<input id="desExpedienteObservacionModal_f" name="descripcionExpediente" type="text" class="form-control" disabled="disabled"/>
												</div>	
												<div class="col-md-2">
													<label>Origen:</label>
												</div>
												<div id="txtCampo6" class="col-md-2">
													<input id="desOrigenObservacionModal_f" name="descripcionOrigen" type="text" class="form-control" disabled="disabled"/>
												</div>											
											</div>
											<div class="row content-box">&nbsp;</div>
											<div class="row content-box">
												<div class="col-md-2">
													<label>Fecha de registro:</label>
												</div>
												<div id="txtCampo7" class="col-md-2">
													<input id="fechaVirtualObservacionModal_f" name="fechaExpedienteVirtual" type="text" class="form-control" disabled="disabled"/>
												</div>
												<div class="col-md-2">
													<label>Fecha de documento origen:</label>
												</div>
												<div id="txtCampo8" class="col-md-2">
													<input id="fechaOrigenObservacionModal_f" name="fechaOrigenExpediente" type="text" class="form-control" disabled="disabled"/>
												</div>
											</div>
											</div>
										</div>
										<!-- FIN[LLRB 15012020] -->
									</fieldset>									
									<fieldset class="scheduler-border" style="margin : 15px 15px !important">
										<legend class="scheduler-border">Listado de Observaciones </legend>
											<div class="panel-body">
												<div class="tab-content">
												<div id="accionesFiscalizacion" class="tab-pane fade in active">
												<div class="border-line panel-scroll">	
												<table id="tableObservaciones" class="table display" cellspacing="0" style="width: 100%;">
													<thead>
														<tr class="active">
															<th width="5%">N&deg;</th>
															<th width="40%">Observaci&oacute;n</th>
															<th width="40%">Usuario Registro</th>
															<th width="15%">Fecha de registro</th>
														</tr>
													</thead>
												</table>
												</div>
												</div>
												</div>
											</div>
									</fieldset>
									
									<div style="margin : 15px 15px !important">
										<div class="row content-box">
											<div class="col-md-3">
											</div>
											<div class="col-md-2">
												
											</div>
											<div class="col-md-2">
												<button type="button" class="btn btn-primary" id='exportarObservaciones' onclick="exportarObservacionesModal()">Exportar a Excel</button>
											</div>
											<div class="col-md-2">
												
											</div>
											<div class="col-md-3">
											</div>
										</div>
									</div>
									<form id="formObservacionesModal" class="form-horizontal" role="form">
										<input id="hiddenListaExpObservacionModal" type="hidden" name="hiddenListaExp"/>		
										<input id="hiddenNumRucObservacionModal" type="hidden" name="hiddenNumRuc"/>
										<input id="hiddenRazonSocialObservacionModal" type="hidden" name="hiddenRazonSocial"/>
										<input id="hiddenNumExpOrigenObservacionModal" type="hidden" name="hiddenNumExpOrigen"/>
										<input id="hiddenNumExpVirtualObservacionModal" type="hidden" name="hiddenNumExpVirtual"/>
										<input id="hiddenEstExpedienteObservacionModal" type="hidden" name="hiddenEstExpediente"/>
										<input id="hiddenTipoProcesoObservacionModal" type="hidden" name="hiddenTipoProceso"/>
										<input id="hiddenTipoExpedienteObservacionModal" type="hidden" name="hiddenTipoExpediente"/>
										<input id="hiddenDescOrigenObservacionModal" type="hidden" name="hiddenDescOrigen"/>
										<!-- inicio JEFFIO -->
										<input id="hiddenfechaNotificacion" type="hidden" name="hiddenfecNotificacion"/>
										<input id="hiddenforhaNotificacion" type="hidden" name="hiddenforNotificacion"/>										
										<!-- fin -->
										<input id="hiddenFechaGeneracionObservacionModal" type="hidden" name="hiddenFechaGeneracion"/>
										<input id="hiddenFechaOrigenObservacionModal" type="hidden" name="hiddenFechaOrigen"/>
									</form>		
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="modalArchivos" class="modal fade" role="dialog" style="width: 95%">
			<div class="modal-dialog" style="width: 1000px !important;">
				<div class="panel panel-info">
					<div class="panel-heading">
						<div>
						<div class="panel-heading">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
							</button>
							<div id="dlgTitleModalArchivos"></div>
						</div>
							<div class="row">
								<div class="panel panel-primary">
									<fieldset class="scheduler-border" style="margin : 15px 15px !important">
									<legend class="scheduler-border">Lista de Archivos</legend>
									</fieldset>									
									<fieldset class="scheduler-border" style="margin : 15px 15px !important">
										<div class="panel-body">
												<div class="tab-content">
												<div id="accionesFiscalizacion" class="tab-pane fade in active">
												<div class="border-line panel-scroll">	
												<table id="tableArchivoModal" class="table display" cellspacing="0" style="width: 100%;">
													<thead>
														<tr class="active">
															<th width="20%">N&deg;</th>
															<th width="13%">N&uacute;mero de Archivo</th>
															<th width="20%">Nombre de Archivo</th>
															<th width="5%">Item</th>
															<th width="12%">Tama&ntilde;o de archivo</th>
															<th width="5%">Folios</th>
															<th width="10%">Ver archivo</th>
															<th width="10%">Representaci&oacute;n <br> Impresa</th>
														</tr>
													</thead>
												</table>
												</div>
												</div>
												<div class="row">
												<div class="col-md-4"></div>
												<div class="col-md-4"  style="color:#ff0000" id="divErrorExtensionArchivo"></div>
												<div class="col-md-4"></div>
										</div>
												</div>
											</div>
									</fieldset>
									
									<div style="margin : 15px 15px !important">
										<div class="row content-box">
											<div class="col-md-3">
											</div>
											<div class="col-md-2">
												
											</div>
											<div class="col-md-2">
																						
												<button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
											</div>
											<div class="col-md-2">
												
											</div>
											<div class="col-md-3">
											</div>
										</div>
									</div>
									<form id="formArchivosModal" class="form-horizontal" role="form">
										<input id="hiddenListaArchivosModal" type="hidden" name="hiddenListaArc"/>		
										<input id="hiddenNumDocModal" type="hidden" name="hiddenNumDocVirtual"/>
										<input id="hiddenTipoExpedienteModal" type="hidden" name="hiddenTipoExpediente"/>
									</form>		
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
		<div id="modalExpedienteOrigen" class="modal fade" role="dialog" style="width: 95%">
			<div class="modal-dialog" style="width: 1000px !important;">
			<input type="hidden" name="numExpeVirtualExpedienteModal" id="numExpeVirtualExpedienteModal">
				<div class="panel panel-info">
					<div class="panel-heading">
						<div>
							<div class="panel-heading">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
								</button>
								<div id="titleExpedienteOrigenModal"></div>
							</div>
								<div class="row">
									<div class="panel panel-primary">
										<fieldset class="scheduler-border" style="margin : 15px 15px !important">
											<legend class="scheduler-border">Datos del Expediente Or&iacute;gen</legend>
											<div class="form-group" id="trazabilidad">	
											<div class="panel-body">
												<div class="row content-box">
													<div class="col-md-3">
														<label>RUC: </label>
													</div>
													<div id="txtCampoRuc" class="col-md-3">
														<input id="numRucExpedienteModal" name="numeroRuc" type="text" class="form-control" disabled="disabled"/>
													</div>
													<div id="txtCampoRazonSocial" class="col-md-6">
														<input id="razonSocialExpedienteModal" name="razonSocialRuc" type="text" class="form-control" disabled="disabled"/>
													</div>
												</div>
												<div class="row content-box">&nbsp;</div>
												<div class="row content-box">
													<div class="col-md-3">
														<label>Proceso:</label>
													</div>
													<div id="txtCampo4" class="col-md-3">
														<input id="desProcesoExpedienteModal" name="descripcionProceso" type="text" class="form-control" disabled="disabled"/>
													</div>
													<div class="col-md-3">
														<label>Tipo de expediente:</label>
													</div>
													<div id="txtCampo5" class="col-md-3">
														<input id="desExpedienteExpedienteModal" name="descripcionExpediente" type="text" class="form-control" disabled="disabled"/>
													</div>													
												</div>
												<div class="row content-box">&nbsp;</div>
												<div class="row content-box">
												<div class="col-md-3">
														<label>N&deg; Expediente :</label>
													</div>
													<div id="txtCampo2" class="col-md-3">
														<input id="numExpeOrigenExpedienteModal" name="numeroExpeOrigen" type="text" class="form-control" disabled="disabled"/>
													</div>
													<div class="col-md-3">
														<label>Fecha del expediente:</label>
													</div>
													<div id="txtCampo8" class="col-md-3">														
														<input id="fechaVirtualExpedienteModal" name="fechaExpedienteVirtual" type="text" class="form-control" disabled="disabled"/>
													</div>
												</div>
												<div class="row content-box">&nbsp;</div>
												<div class="row content-box">
													<div class="col-md-3">
														<label>Estado del Expediente:</label>
													</div>
													<div id="txtCampo3" class="col-md-3">
														<input id="estExpedienteExpedienteModal" name="estadoExpediente" type="text" class="form-control" disabled="disabled"/>
													</div>
												</div>
											</div>
											</div>											
										</fieldset>
										<fieldset class="scheduler-border" style="margin : 15px 15px !important">
											<legend class="scheduler-border">Listado de Estados - Etapas del Expediente Origen</legend>
												<div class="panel-body">
												<div class="tab-content">
												<div id="accionesFiscalizacion" class="tab-pane fade in active">
												<div class="border-line panel-scroll">	
													<table id="tableExpediente" class="table display" cellspacing="0" style="width: 100%;"><!--table table-striped table-bordered-->
														<thead>
															<tr class="active">
																<th width="5%">N&deg;</th>
																<th width="55%">Tipo de documento</th>
																<th width="10%">N&deg; documento</th>
																<th width="10%">Estado</th>
																<th width="10%">Etapa</th>
																<th width="10%">Fecha de registro</th>
															</tr>
														</thead>
													</table>
													</div>
													</div>
													</div>
												</div>
										</fieldset>
										<div style="margin : 15px 15px !important">
											<div class="row content-box">
												<div class="col-md-3">
												</div>
												<div class="col-md-2">
													
												</div>
												<div class="col-md-2">
													<button type="button" class="btn btn-primary" id='exportarTrazabilidadModal' onclick="exportarExpedienteModal()">Exportar a Excel</button>
												</div>
												<div class="col-md-2">
													
												</div>
												<div class="col-md-3">
												</div>
											</div>
										</div>
										<form id="formExpedienteModal" class="form-horizontal" role="form">
											<input id="hiddenListaExpExpedienteModal" type="hidden" name="hiddenListaExp"/>		
											<input id="hiddenNumRucExpedienteModal" type="hidden" name="hiddenNumRuc"/>
											<input id="hiddenRazonSocialExpedienteModal" type="hidden" name="hiddenRazonSocial"/>
											<input id="hiddenNumExpOrigenExpedienteModal" type="hidden" name="hiddenNumExpOrigen"/>
											<input id="hiddenNumExpVirtualExpedienteModal" type="hidden" name="hiddenNumExpVirtual"/>
											<input id="hiddenEstExpedienteExpedienteModal" type="hidden" name="hiddenEstExpediente"/>
											<input id="hiddenTipoProcesoExpedienteModal" type="hidden" name="hiddenTipoProceso"/>
											<input id="hiddenTipoExpedienteExpedienteModal" type="hidden" name="hiddenTipoExpediente"/>
											<input id="hiddenDescOrigenExpedienteModal" type="hidden" name="hiddenDescOrigen"/>
											<input id="hiddenFechaGeneracionExpedienteModal" type="hidden" name="hiddenFechaGeneracion"/>
											<input id="hiddenFechaOrigenExpedienteModal" type="hidden" name="hiddenFechaOrigen"/>
										</form>		
									</div>
								</div>	
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="modalCopias" class="modal fade" role="dialog" style="width: 95%">
			<div class="modal-dialog" style="width: 1000px !important;">
				<div class="panel panel-info">
					<div class="panel-heading">
						<div>
						<div class="panel-heading">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
							</button>
							<div id="dlgTitleModalCopias"></div>
						</div>
							<div class="row">
								<div class="panel panel-primary">
									<fieldset class="scheduler-border" style="margin : 15px 15px !important">								
									</fieldset>									
									<fieldset class="scheduler-border" style="margin : 15px 15px !important">
										<div class="panel-body">
												<div class="tab-content">
												<div id="accionesFiscalizacion" class="tab-pane fade in active">
												<div class="border-line panel-scroll">	
												<table id="tableCopiasModal" class="table display" cellspacing="0" style="width: 100%;">
													<thead>
														<tr class="active">
															<th width="8%">N&deg;Copia</th>															
															<th width="30%">Nombre de Archivo</th>														
															<th width="12%">Tama&ntilde;o de archivo</th>	
															<th width="10%">Ver archivo</th>
															<th width="12%">Registro de usuario</th>	
															<th width="12%">Fecha de registro</th>														
															
															
														</tr>
													</thead>
												</table>
												</div>
												</div>
												<div class="row">
												<div class="col-md-4"></div>
												<div class="col-md-4"  style="color:#ff0000" id="divErrorExtensionArchivo"></div>
												<div class="col-md-4"></div>
										</div>
												</div>
											</div>
									</fieldset>
									
									<div style="margin : 15px 15px !important">
										<div class="row content-box">
											<div class="col-md-3">
											</div>
											<div class="col-md-2">
												
											</div>
											<div class="col-md-2">
																						
												<button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
											</div>
											<div class="col-md-2">
												
											</div>
											<div class="col-md-3">
											</div>
										</div>
									</div>
									<form id="formCopiasModal" class="form-horizontal" role="form">
										<input id="hiddenListaCopiasModal" type="hidden" name="hiddenListaCop"/>		
										<input id="hiddenNumDocModal" type="hidden" name="hiddenNumDocVirtual"/>
										<input id="hiddenTipoExpedienteModal" type="hidden" name="hiddenTipoExpediente"/>
									</form>		
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	<!-- 	Fin LLRB 12/12	MODAL LISTA ARCHIVO  -->

	<script>
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
				
	};
	function crearDlg(titulo, msj, btns){
		
		$('#dlgTitle').html(titulo);
		$('#dlgMsj').html(msj);
		$('#dlgBtns').empty();
		
		jQuery.each(btns, function(i, dato) {
			$('#dlgBtns').append(dato);
		});
		
		$('#modalDlg').modal('show');
		
	};
	</script>	
	<script type="text/javascript">
 
	
	//var listadoDependencias = ${listadoDependencias};	
	var listadoNumeroTipoExpediente = ${listadoNumeroTipoExpediente};
	
	//var codDepUsuario = ${codDepUsuario};
	
	var excepciones = ${excepciones};
	var titulos =  ${titulos};
	var numExpedienteConsultar="";
	var flagExcepcion="";
	var retorno=false;
	var realizarBusqueda=false;
	//Inicio jquispe 16/06/2016
	var hoverBuscar=false;
	//Fin jquispe 16/06/2016
		
	$(function () {
		
		construirTablaExpediente( [] );
		construirTablaDocumentos( [] );
		construirTablaObservaciones( [] );
		construirTablaExpedienteModal( [] );
		construirTablaArchivo( [] );	
		construirTablaExpVinculados( [] );
		construirTablaCopia( [] );
	
		$.fn.datetimepicker.defaults.language = 'Es';

		$('#exportar').attr('disabled', true);	
		$('#divFechaDesde').datetimepicker({
            format: 'DD/MM/YYYY',
			useCurrent: false,
			language: 'Es',
			autoClose: true,
			forceParse: true,
			pickTime: false
        });
		
		// Fin [jquispe 02/06/2016]
		$('#divFechaHasta').datetimepicker({
            format: 'DD/MM/YYYY',
			useCurrent: false,
			language: 'Es',
			autoClose: true,
			forceParse: true,
			pickTime: false
        });
		
		

 		// Fin [jquispe 02/06/2016]
		$( "#txtNumeroExpediente" ).blur(function() {
			retorno=true;
			$('#frmFiltroBusquedaEspecifica').bootstrapValidator('revalidateField', 'numExp');
		});
	
		//Inicio jquispe 16/06/2016
		$('#btnCancelar').mouseover(function(){
			hoverBuscar=true;
		});

		$('#btnCancelar').mouseout(function(){
			hoverBuscar=false;
		});
		//Fin jquispe 16/06/2016
					
		// Validaciones
		//Validaciones del Formulario Busqueda
		$('#frmFiltroBusquedaEspecifica').bootstrapValidator({
			excluded: [':disabled'],
			fields: {
				numExp: {
					validators: {
						callback: {
                            message: 'Ingrese un n&uacute;mero de Expediente.',
                            callback: function (value, validator, $field) {
                            	if(retorno){
								var numExpediente = $('#txtNumeroExpediente').val();
								var tipoExpediente = $('#selTipoBusquedaExpediente').val();
								
								if(tipoExpediente!=""){
									if(numExpediente==""){
										$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateMessage', 'numExp', 'callback', 'Ingrese un N&uacute;mero de Expediente.');
										$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateOption', 'numExp', 'callback', 'Ingrese un N&uacute;mero de Expediente.');
										retorno=false;
										realizarBusqueda=false;
										return  false;
									}
									
									if(tipoExpediente=="2"){
										if(numExpediente.length!=14){
											$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateMessage', 'numExp', 'callback', 'Ingrese un N&uacute;mero Expediente V&aacute;lido.');
											$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateOption', 'numExp', 'callback', 'Ingrese un N&uacute;mero Expediente V&aacute;lido.');
											retorno=false;
											realizarBusqueda=false;
											return  false;
										}
									}
									if(tipoExpediente=="1"){
									    // Inicio [jquispe 30/05/2016] Valida que la longitud del numero de expediente de origen no sea mayor a 17.
										if(numExpediente.length > 17){
									    // Fin [jquispe 30/05/2016]
											$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateMessage', 'numExp', 'callback', 'Ingrese un N&uacute;mero Expediente V&aacute;lido.');
											$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateOption', 'numExp', 'callback', 'Ingrese un N&uacute;mero Expediente V&aacute;lido.');
											retorno=false;
											realizarBusqueda=false;
											return  false;
										}
									}									
								}
								retorno=false;
								realizarBusqueda=true;
								return true;								
							}
							retorno=false;
							return true;
                        }
                    },
					regexp: {
						regexp: /^[0-9/]+$/,
						message: 'Debe ingresar solo valores num&eacute;ricos para el N&deg; de Expediente.'
					}
					
				}
			}
		}
	}).on('success.form.bv', function(e) {
        e.preventDefault();
         //consultarExpedientes();
   	}).on('error.validator.bv', function(e, data) {
            // $(e.target)    --> The field element
            // data.bv        --> The BootstrapValidator instance
            // data.field     --> The field name
            // data.element   --> The field element
            // data.validator --> The current validator name

            data.element
                .data('bv.messages')
                // Hide all the messages
                .find('.help-block[data-bv-for="' + data.field + '"]').hide()
                // Show only message associated with current validator
                .filter('[data-bv-validator="' + data.validator + '"]').show();
        });
	
			
		//$('#txtNumeroExpediente').prop('disabled', true);
		$('#btnConsulta').prop('disabled', true);

		
		//expediente
		var $element = $('#selTipoBusquedaExpediente');
		$.each(listadoNumeroTipoExpediente, function(i, dato) {
			var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
			$element.append($option);
		});
		
		var $element = $('#selDependencia');
				
		/*$.each(listadoDependencias, function(i, dato) {		
			var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
			$element.append($option);
		});

		//[Inicio - jtejada 13/09/2016]
		if($.trim(codDepUsuario) == ''){
			$("#selDependencia").val($("#selDependencia option:first").val());
		}else{
			$('#selDependencia').val(codDepUsuario);
		}	*/	
		

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
				{data :	"numOrden",sClass: 'centered alinearCentrado',"defaultContent":""},
				{data :	"desTipdoc",sClass: 'centered alinearCentrado',"defaultContent":""},
				{data :	"numDoc",sClass: 'centered alinearCentrado',"defaultContent":""},
				{data :	"descArchivo",sClass: 'centered alinearCentrado',"defaultContent":"" },
				{data :	"desOrigen",sClass: 'centered alinearCentrado',"defaultContent":""},
				{data :	"nroRequerim",sClass: 'centered alinearCentrado',"defaultContent":"" },
				{data :	"nomPersonaCargo",sClass: 'centered alinearCentrado',"defaultContent":"" },
				{data :	"fechaVista",sClass: 'centered alinearCentrado',"defaultContent":"" },
				{data :	"codIdecm",sClass: 'centered alinearCentrado',"defaultContent":"", 
					render : function(data, type, row){
							link = '<a href="conRepControl.htm?action=descargarArchivo&codIdentificadorECM='+ data +'&nombreArchivo='+row.descArchivo+'"role="button" class="cimg glyphicon glyphicon-download-alt" target=_blank></a>';
						return link;	
					}
				}
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
			iDisplayLength		:	15,
			//responsive			:	true,
			bLengthChange		: 	false,
			fnDrawCallback		:	function(oSettings) {
				$('#tableDocumento_paginate').addClass('div100');
				if (oSettings.fnRecordsTotal() == 0) {
					$('#table_paginate').addClass('hiddenDiv');
		        }
				else {
					$('#table_paginate').removeClass('hiddenDiv');
					
				}
		    }
			        
		});
		$( window ).off('resize');
	})

	//Fin [jquispe 27/05/2016]

	
	 function limpiar(){
		resetearFormularioConEsp(event);		
		limpiarBusquedaEspecifica();
		//Fin [jquispe 02/06/2016]
		$('#selNumeroTipoExpediente').val("");
		$('#selCodigoProceso').val("");
		$('#selCodigoTipoExpendiente').val("");
		//Inicio [jquispe 02/06/2016] Limpia el tipo de expediente.
		$('#selCodigoTipoExpendiente').empty();		
		$('#selCodigoTipoExpendiente').append($('<option>', {
			value: '',
			text: 'Seleccione'
		}));
		//Fin [jquispe 02/06/2016]
		
		$('#selCodigoEstado').val("").change();//eaguilar 04 JUL
		
		$('#txtfechaDesde').val("");
		$('#txtfechaHasta').val("");
		$("#txtfechaDesde").attr('disabled', false);
		$("#txtfechaHasta").attr('disabled', false);
		// Inicio [jquispe 27/05/2016] Inicializar Razon Social.
		$("#txtRazonSocial").val('');
		// Fin [jquispe 27/05/2016]
		$('#tblExpedientes').dataTable().fnClearTable();	
		$('#exportar').attr('disabled', true);
		
		//Inicio [jquispe 02/06/2016] Limpia la fecha.
		$('#divFechaDesde').data("DateTimePicker").destroy();
		$('#divFechaHasta').data("DateTimePicker").destroy();
		
		$('#divFechaDesde').datetimepicker({
            format: 'DD/MM/YYYY',
			useCurrent: false,
			language: 'es',
			autoClose: true,
			forceParse: true,
			pickTime: false
        });
		
		$('#divFechaHasta').datetimepicker({
            format: 'DD/MM/YYYY',
			useCurrent: false,
			language: 'es',
			autoClose: true,
			forceParse: true,
			pickTime: false
        });
        //Fin [jquispe 02/06/2016]
		
		$( window ).off('resize');
	}
	
	//Inicio [jquispe 02/06/2016] Limpia la busqueda especifica.
	function limpiarBusquedaEspecifica(){
	  $('#frmFiltroBusquedaEspecifica').bootstrapValidator('resetForm', true);
	  $('#selTipoBusquedaExpediente').val("");
	  $('#txtNumeroExpediente').val("");
	  $("#txtNumeroExpediente").prop('disabled', true);
	}
	//Fin [jquispe 02/06/2016]

	function revalidarFormulario(){
		var tipoBusqueda = $('#selTipoBusquedaExpediente').val();

		if(tipoBusqueda!=""){
			$('#frmFiltroBusquedaEspecifica').submit();
			validarExpediente();
		}
	}		

	function resetearFormularioConEsp(event){
		$('#frmFiltroBusquedaEspecifica').bootstrapValidator('resetForm', true);
		$('#selTipoBusquedaExpediente').val("");
		
		var current = event.target || event.srcElement;
		
		if(current.id != "selDependencia"){
			if($("#selCodigoProceso").val() == "" && current.id != "txtRuc") $('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'codTipexp');
		}
				
		

	}
	function guardarNumExpediente(numExpediente){
		numExpedienteConsultar = numExpediente;
		
	}

	function mostrarID(id){
		alert(id);
	}

	function construirTablaExpediente(dataGrilla) {
       	var table = $('#tblExpedientes').DataTable({

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
			bLengthChange		: 	false,
			"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
				
				  $(iCol).attr('colClasses','tableOddCol');
				
			 },
            fnDrawCallback: function(oSettings) {
            	var a = $('#tblExpedientes').width()
				$("#tblExpedientes_wrapper").css("min-width", a);	
            	$('#tblExpedientes_paginate').addClass('div100');
                if (oSettings.fnRecordsTotal() == 0) {
                    $('#table_paginate').addClass('hiddenDiv');
                } else {
                    $('#table_paginate').removeClass('hiddenDiv');
                }
            },
			fnRowCallback: function (nRow, aData, iDisplayIndex) {
                    $(nRow).attr('id', aData[2]);
					$(nRow).attr('align', 'center');
					$(nRow).attr('rowClasses','tableOddRow');
                    return nRow;
            },
			language: {
                url: "/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
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
			columns: [	
			          	{data :	"numOrden",sClass: 'centered alinearCentrado'},
			          	{data :	"numExpedienteOrigen", render : function(data,type, row){							
							 return $('<a>')
							 	.css('text-decoration','underline')
				                .attr({	onclick :'cargaModal('+row.numExpedienteVirtual+')'})
				                .text(data)
				                .wrap('<div></div>')
				                .parent()
				                .html();
				     	}, sClass: 'centered alinearCentrado'},
				     	{data : "nombreResponsable", sClass: 'centered alinearCentrado',"defaultContent":""},		
						{data : "numRuc", sClass: 'centered alinearCentrado',"defaultContent":""},						
						{data : "desRazonSocial",sClass:'centered alinearCentrado',"defaultContent":""},						
						{data : "desProceso", sClass: 'centered alinearCentrado',"defaultContent":""},
						{data :	"desTipoExpediente", sClass: 'centered alinearCentrado',"defaultContent":"" },
						{data :	"desEstado", sClass: 'centered alinearCentrado',"defaultContent":"" },						
						{data :	"fechaDocumentoOrigen", sClass: 'centered alinearCentrado',"defaultContent":""},						
						{data : "desOrigen", sClass: 'centered alinearCentrado',"defaultContent":""},					
						{data :	"numExpedienteVirtual","defaultContent":"", render : function(data,type, row){
							var link1 = '<a onclick="obtenerDocumentos('+data+')">Ver Docs</a>';
							var link2 = '<a onclick="obtenerObservaciones('+data+')">Ver Obs</a>';						
							//INICIO LLRB 16012020
							var link3='<a onclick="obtenerExpVinculados('+(parseInt(row.numOrden) - 1)+')">Ver Vinculados</a>';//IMPLEMENTAR LA FUNCION DE SOL DESCARGA
							var link4='<a onclick="validarSolicitud('+(parseInt(row.numOrden) - 1)+')">Sol. Descarga</a>';//IMPLEMENTAR LA FUNCION DE SOL DESCARGA
							//FIN LLRB 16012020
							 var table = '<table><tr><td nowrap="nowrap" style="border-right: 0px !important;">';
							 table += link1;
							 table += '</td></tr><tr><td style="border-right: 0px !important;">';
							 table += link2;							
							 table += '</td></tr><tr><td style="border-right: 0px !important;">';
							 table += link3;
							 table += '</td></tr><tr><td style="border-right: 0px !important;">';
							 table += link4;
							 table += '</td></tr><tr><td style="border-right: 0px !important;">';
						
							 table += '</td></tr></table>';
							 
							 return table;
				        }, sClass: 'centered alinearCentrado'},
				        {data : "numExpedienteVirtual", sClass: 'hidden',"defaultContent":""}
			]
        });
    	//FIN LLRB 16012020
    }
	
	
	//eaguilar 8 AGO:
	function verRepImpresa(idxFila){
		borrarErrorExtension();
		//link = '<a href="conRepControl.htm?action=descargarArchivo&codIdentificadorECM='+ data +'&nombreArchivo='+row.descArchivo+'"role="button" class="cimg glyphicon glyphicon-download-alt" target=_blank></a>';
		var dataEnvio = new Object();
		dataEnvio.docData = JSON.stringify($('#tableDocumentoModal').DataTable().row(idxFila).data());
		dataEnvio.ruc = $("#numRucDocumentoModal").val();  
		dataEnvio.razSoc = $.trim($("#razonSocialDocumentoModal").val());
		
		$.ajax({
            //url: '${pageContext.request.contextPath}/conRepControl.htm?action=validarExtensionDocumento',
            url: '${pageContext.request.contextPath}/conRepControl.htm?action=validarExtension',
           	data: $.param(dataEnvio),//"&codProceso="+codProceso,
            cache: false,
            async: true,
            type: 'POST',
            contentType : "application/x-www-form-urlencoded; charset=utf-8",
            dataType: 'json',
            success: function(response) {
            	
            	if(!response.error){
            		window.open('conRepControl.htm?action=verRepImpresa&' + $.param(dataEnvio), "_blank", "toolbar=yes, scrollbars=yes, resizable=yes, top=20, left=300, width=600, height=650");
            	}else{
            		mostrarErrorExtension(response.extension);
            	}
            	
            },
            error: function(err) {
            	document.write(err.responseText);
            }
        });
	}

	function mostrarErrorExtension(extension){
		$('#divErrorExtension').html('No se puede generar Representaci&oacute;n Impresa del Documento con extensi&oacute;n: "' + extension + '"');
	}
	
	function borrarErrorExtension(){
		$('#divErrorExtension').html('');
	}
	
	function construirTablaObservaciones(data){
		$('#tableObservaciones').DataTable( {
					"language": {
						"url"		:	"../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
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
						{data :	"numOrden",sClass: 'centered alinearCentrado',"defaultContent":""},
						{data :	"desObservacion","defaultContent":""},
						{data :	"nomUsuRegistraObs","defaultContent":"" },
						{data :	"fecRegistroObs","defaultContent":""}
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
					iDisplayLength		:	5,
					//responsive			:	true,
					bLengthChange		: 	false,
					fnDrawCallback		:	function(oSettings) {
						var a = $('#tableObservaciones').width()
						$("#tableObservaciones_wrapper").css("min-width", a);
						$('#tableObservaciones_paginate').addClass('div100');
						if (oSettings.fnRecordsTotal() == 0) {
							$('#table_paginate').addClass('hiddenDiv'); //el footer de paginacion siempre tiene como ID "miTablaID_paginate"
																				//no se oculta automaticamente
																	//cuando hay 0 registros, por eso se oculta anadiendo el class .hiddenDiv
				        }
						else {
							$('#table_paginate').removeClass('hiddenDiv');
							
						}
				    }
					        
			} );
	}
	
	function construirTablaExpedienteModal(){
		$('#tableExpediente').DataTable( {
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
						{data :	"numOrden","defaultContent":""},
						{data :	"desTipoDocumento","defaultContent":""},
						{data :	"numDocumento","defaultContent":""},
						{data :	"desEstado","defaultContent":""},
						{data :	"desEtapa","defaultContent":""},
						{data :	"fecVistaSegui","defaultContent":""}
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
					iDisplayLength		:	5,
					//responsive			:	true,
					bLengthChange		: 	false,
					fnDrawCallback		:	function(oSettings) {
						var a = $('#tblExpediente').width()
						$("#tblExpediente_wrapper").css("min-width", a);
						$('#tableExpediente_paginate').addClass('div100');
						if (oSettings.fnRecordsTotal() == 0) {
							$('#table_paginate').addClass('hiddenDiv'); //el footer de paginacion siempre tiene como ID "miTablaID_paginate"
																				//no se oculta automaticamente
																	//cuando hay 0 registros, por eso se oculta anadiendo el class .hiddenDiv
				        }
						else {
							$('#table_paginate').removeClass('hiddenDiv');
							
						}
				    }
					        
			} );
			
	}

	function obtenerDocumentosAcum(numAcumulador){
		var dataEnvio = new Object();
		
		$.ajax({
			url : '${pageContext.request.contextPath}/conRepControl.htm?action=cargarDocumentosExpVirtualAcum',
			type : 'POST',
			async : false,
			dataType : "json",
			data : "&numAcumulador="+numAcumulador,
			cache: false,            
			//contentType : "application/json",
			mimeType : "application/json",
			//timeout : 5000,
			success : function(response) {				
				var lstDocExp = JSON.parse(response.lstDocExp);
				var datosExpedientes = JSON.parse(response.t6614ExpVirtualBean);
				var fechaOrigenDoc = JSON.parse(response.fechaOrigenDoc);
				var fechaRegistro = JSON.parse(response.fechaRegistro);
				var razonSocial=JSON.parse(response.razonSocial);
				var excepciones = JSON.parse(response.excepciones);					
				
				if (lstDocExp.length > 0) {					
					if(datosExpedientes.codTipoExpediente== '430' ||datosExpedientes.codTipoExpediente== '431' ){
						$('#DocumentoCompleto').show();
						$('#DocumentoIncompleto').hide();
						$('#tableDocumentoModal').dataTable().fnClearTable();
						$('#tableDocumentoModal').dataTable().fnAddData(lstDocExp); //carga registros en grilla					
						$('#exportarDocumentos').attr('disabled', false);
					}else{
						$('#DocumentoCompleto').hide();
						$('#DocumentoIncompleto').show();
						$('#tableDocumentoFiltroModal').dataTable().fnClearTable();
						$('#tableDocumentoFiltroModal').dataTable().fnAddData(lstDocExp); //carga registros en grilla					
						$('#exportarDocumentos').attr('disabled', false);
					}
				}else{
					$('#tableDocumentoModal').dataTable().fnClearTable();
					$('#tableDocumentoModal').dataTable().fnAddData(lstDocExp); 				
					$('#exportarDocumentos').attr('disabled', true);
				}
				$('#numRucDocumentoModal').attr("value", datosExpedientes.numRuc);
				$('#razonSocialDocumentoModal').attr("value", razonSocial);
				$('#numExpeOrigenDocumentoModal').attr("value", datosExpedientes.numExpedienteOrigen);
				$('#numExpeVirtualDocumentoModal').attr("value", datosExpedientes.numExpedienteVirtual);
				$('#estExpedienteDocumentoModal').attr("value", datosExpedientes.desEstado);
				$('#desProcesoDocumentoModal').attr("value", datosExpedientes.desProceso);
				$('#desExpedienteDocumentoModal').attr("value", datosExpedientes.desTipoExpediente);
				$('#desOrigenDocumentoModal').attr("value", datosExpedientes.desOrigen);
				$('#fechaOrigenDocumentoModal').attr("value", fechaOrigenDoc);
				$('#fechaVirtualDocumentoModal').attr("value", fechaRegistro);
				var titulo = "Detalle de Documentos - N&deg; Expediente Virtual " + datosExpedientes.numExpedienteVirtual;
				$('#dlgTitleModalDocumentos').html(titulo);				
			},
			error : function(response) {
				
				mostrarPagError();
				
			}
		});
	}

	function mostrarModal(titulo){
		$('#dlgTitleModalDocumentos').html(titulo);
		$('#modalDocumentos').modal('show');
	}
	
	function mostrarModalObservaciones(titulo){
		$('#dlgTitleModalObservaciones').html(titulo);
		$('#modalObservaciones').modal('show');
	}
	
	function mostrarModalTrazabilidad(titulo){
		$('#titleExpedienteOrigenModal').html(titulo);
		$('#modalExpedienteOrigen').modal('show');
	}

		
	function obtenerObservaciones(numExpedienteVirtual){
		var dataEnvio = new Object();
																		
		dataEnvio.aux = numExpedienteVirtual;
		$.ajax({
			url : '${pageContext.request.contextPath}/conRepControl.htm?action=cargarObservacionesExpVirtual',
			type : 'POST',
			async : true,
			dataType : "json",
			data : JSON.stringify(dataEnvio),
			contentType : "application/json",
			mimeType : "application/json",
			//timeout : 5000,
			success : function(response) {				
				var lstObsExp = JSON.parse(response.lstObsExp);
				var datosExpedientes = JSON.parse(response.datosExpedientes);
				var fechaOrigenDoc = JSON.parse(response.fechaOrigenDoc);
				var fechaRegistro = JSON.parse(response.fechaRegistro);
				if (lstObsExp.length > 0) {
					$('#tableObservaciones').dataTable().fnClearTable();
					$('#tableObservaciones').dataTable().fnAddData(lstObsExp); //carga registros en grilla					
					$('#exportarObservaciones').attr('disabled', false);
					
				}else{
					$('#tableObservaciones').dataTable().fnClearTable();
					$('#tableObservaciones').dataTable().fnAddData(lstObsExp); 		
					$('#exportarObservaciones').attr('disabled', true);					
				}
				//INICIO LLRB 16012020
  				if((datosExpedientes.codTipoExpediente == 430 || datosExpedientes.codTipoExpediente == 431) ) {
  					$('#observacionesFisca').show();
  					$('#observaciones').hide();
					
  					$('#numRucObservacionModal_f').attr("value", datosExpedientes.numRuc);
  					$('#razonSocialObservacionModal_f').attr("value", datosExpedientes.desRazonSocial);
  					$('#numExpeOrigenObservacionModal_f').attr("value", datosExpedientes.numExpedienteOrigen);					
  					$('#estExpedienteObservacionModal_f').attr("value", datosExpedientes.desEstado);
  					$('#desProcesoObservacionModal_f').attr("value", datosExpedientes.desProceso);
  					$('#desExpedienteObservacionModal_f').attr("value", datosExpedientes.desTipoExpediente);
  					$('#desOrigenObservacionModal_f').attr("value", datosExpedientes.desOrigen);
  					$('#fechaOrigenObservacionModal_f').attr("value", fechaOrigenDoc);
  					$('#fechaVirtualObservacionModal_f').attr("value", fechaRegistro);
				
				  }else{
  					$('#observaciones').show();
  					$('#observacionesFisca').hide();
  					
  					$('#numRucObservacionModal').attr("value", datosExpedientes.numRuc);
  					$('#razonSocialObservacionModal').attr("value", datosExpedientes.desRazonSocial);
  					$('#numExpeOrigenObservacionModal').attr("value", datosExpedientes.numExpedienteOrigen);
  					$('#numExpeVirtualObservacionModal').attr("value", datosExpedientes.numExpedienteVirtual);
  					$('#estExpedienteObservacionModal').attr("value", datosExpedientes.desEstado);
  					$('#desProcesoObservacionModal').attr("value", datosExpedientes.desProceso);
  					$('#desExpedienteObservacionModal').attr("value", datosExpedientes.desTipoExpediente);
  					$('#desOrigenObservacionModal').attr("value", datosExpedientes.desOrigen);
  					$('#fechaOrigenObservacionModal').attr("value", fechaOrigenDoc);
  					$('#fechaVirtualObservacionModal').attr("value", fechaRegistro);
			    }
  				//FIN LLRB 16012020
				var titulo = "Detalle de Observaciones - N&deg; Expediente Virtual " + datosExpedientes.numExpedienteVirtual;				
				mostrarModalObservaciones(titulo);
			},
			error : function(response) {
				
				mostrarPagError();
				
			}
		
		});
	}

   //Inicio [jquispe 30/05/2016] Funcion creada para generar el archivo pdf con indice de expediente electronico.
	function obtenerPdf(numExpedienteVirtual,numExpedienteOrigen){
		 
		var dataEnvio = new Object();		
		var url = "conRepControl.htm?action=verDatosExpedienteVirtualPdf";
		url+= "&numExpedienteVirtual="+numExpedienteVirtual+"&numExpedienteOrigen="+numExpedienteOrigen;
		window.open(url);
	}
	// Fin [jquispe 30/05/2016]	
	function consultarObservacionesExpedienteVirtual(){
		var  url = '${pageContext.request.contextPath}/conRepControl.htm?action=cargarObservacionesPorExpVirtual&numExpedienteVirtual='+numExpedienteConsultar;
		$(location).prop( 'href', url);
	}

	function exportarDocumentosModal(){
			var dataExp = $('#tableDocumentoModal').dataTable().fnGetData();
			if(dataExp.length > 0){
				var listaCadena = JSON.stringify(dataExp);
				var formURL = 'conRepControl.htm?action=exportarExcelExpDoc';
				document.forms.formDocumentoModal.action = formURL;
				document.forms.formDocumentoModal.method = "POST";
				/*Obtener Paramtros Inicio*/
				var numRuc = $('#numRucDocumentoModal').val();
				var razonSocial = $('#razonSocialDocumentoModal').val();
				var numExpeOrigen = $('#numExpeOrigenDocumentoModal').val();
				var numExpeVirtual = $('#numExpeVirtualDocumentoModal').val();
				var estExpediente = $('#estExpedienteDocumentoModal').val();
				var desProceso = $('#desProcesoDocumentoModal').val();
				var desExpediente = $('#desExpedienteDocumentoModal').val();
				var desOrigen = $('#desOrigenDocumentoModal').val();
				var fechaOrigen = $('#fechaOrigenDocumentoModal').val();
				var fechaVirtual = $('#fechaVirtualDocumentoModal').val();
				var fechaNotificacion=$('#fechaNotifiDocumentoModal').val();
				var formaNotificacion=$('#formaNotifiDocumentoModal').val();
				/*Obtener Paramtros fin*/
				/*limpiar campos hdn Inicio*/
				$('#hiddenListaExpDocumentoModal').val('');
				$('#hiddenNumRucDocumentoModal').val('');
				$('#hiddenRazonSocialDocumentoModal').val('');
				$('#hiddenNumExpOrigenDocumentoModal').val('');
				$('#hiddenNumExpVirtualDocumentoModal').val('');
				$('#hiddenEstExpedienteDocumentoModal').val('');
				$('#hiddenTipoProcesoDocumentoModal').val('');
				$('#hiddenTipoExpedienteDocumentoModal').val('');
				$('#hiddenDescOrigenDocumentoModal').val('');
				$('#hiddenFechaGeneracionDocumentoModal').val('');
				$('#hiddenFechaOrigenDocumentoModal').val('');
				$('#hiddenfechaNotificacionModal').val('');
				$('#hiddenforhaNotificacionModal').val('');
				$('#hidden')
				/*limpiar campos hdn Inicio*/
				/*setear campos hdn Inicio*/
				$('#hiddenListaExpDocumentoModal').val(listaCadena);
				$('#hiddenNumRucDocumentoModal').val(numRuc);
				$('#hiddenRazonSocialDocumentoModal').val(razonSocial);
				$('#hiddenNumExpOrigenDocumentoModal').val(numExpeOrigen);
				$('#hiddenNumExpVirtualDocumentoModal').val(numExpeVirtual);
				$('#hiddenEstExpedienteDocumentoModal').val(estExpediente);
				$('#hiddenTipoProcesoDocumentoModal').val(desProceso);
				$('#hiddenTipoExpedienteDocumentoModal').val(desExpediente);
				$('#hiddenDescOrigenDocumentoModal').val(desOrigen);
				$('#hiddenFechaGeneracionDocumentoModal').val(fechaVirtual);
				$('#hiddenFechaOrigenDocumentoModal').val(fechaOrigen);
				$('#hiddenfechaNotificacionModal').val(fechaNotificacion);
				$('#hiddenforhaNotificacionModal').val(formaNotificacion);
				
				/*setear campos hdn fin*/
				$('html').block({message: '<h1>Procesando</h1>'});
				document.forms.formDocumentoModal.submit();
				$('html').unblock();
			}
		}
		
	function exportarObservacionesModal(){
		var dataExp = $('#tableObservaciones').dataTable().fnGetData();
			if(dataExp.length > 0){
				
				if (($('#numRucObservacionModal').val())!=''){
			
				var listaCadena = JSON.stringify(dataExp);
				var formURL = 'conRepControl.htm?action=exportarExcelObservaciones';
				document.forms.formObservacionesModal.action = formURL;
				document.forms.formObservacionesModal.method = "POST";
				/*Obtener Paramtros Inicio*/
				var numRuc = $('#numRucObservacionModal').val();
				var razonSocial = $('#razonSocialObservacionModal').val();
				var numExpeOrigen = $('#numExpeOrigenObservacionModal').val();
				var numExpeVirtual = $('#numExpeVirtualObservacionModal').val();
				var estExpediente = $('#estExpedienteObservacionModal').val();
				var desProceso = $('#desProcesoObservacionModal').val();
				var desExpediente = $('#desExpedienteObservacionModal').val();
				var desOrigen = $('#desOrigenObservacionModal').val();
				var fechaOrigen = $('#fechaOrigenObservacionModal').val();
				var fechaVirtual = $('#fechaVirtualObservacionModal').val();
				var fechaNotificacion=$('').val();
				var formaNotificacion=$('').val();
				/*Obtener Paramtros fin*/
				/*limpiar campos hdn Inicio*/
				$('#hiddenListaExpObservacionModal').val('');
				$('#hiddenNumRucObservacionModal').val('');
				$('#hiddenRazonSocialObservacionModal').val('');
				$('#hiddenNumExpOrigenObservacionModal').val('');
				$('#hiddenNumExpVirtualObservacionModal').val('');
				$('#hiddenEstExpedienteObservacionModal').val('');
				$('#hiddenTipoProcesoObservacionModal').val('');
				$('#hiddenTipoExpedienteObservacionModal').val('');
				$('#hiddenDescOrigenObservacionModal').val('');
				$('#hiddenFechaGeneracionObservacionModal').val('');
				$('#hiddenFechaOrigenObservacionModal').val('');
				/*limpiar campos hdn Inicio*/
				/*setear campos hdn Inicio*/
				$('#hiddenListaExpObservacionModal').val(listaCadena);
				$('#hiddenNumRucObservacionModal').val(numRuc);
				$('#hiddenRazonSocialObservacionModal').val(razonSocial);
				$('#hiddenNumExpOrigenObservacionModal').val(numExpeOrigen);
				$('#hiddenNumExpVirtualObservacionModal').val(numExpeVirtual);
				$('#hiddenEstExpedienteObservacionModal').val(estExpediente);
				$('#hiddenTipoProcesoObservacionModal').val(desProceso);
				$('#hiddenTipoExpedienteObservacionModal').val(desExpediente);
				$('#hiddenDescOrigenObservacionModal').val(desOrigen);
				$('#hiddenFechaGeneracionObservacionModal').val(fechaVirtual);
				$('#hiddenFechaOrigenObservacionModal').val(fechaOrigen);
				/*setear campos hdn fin*/
				$('html').block({message: '<h1>Procesando</h1>'});
				document.forms.formObservacionesModal.submit();
				$('html').unblock();
			}else{
				var listaCadena = JSON.stringify(dataExp);
				var formURL = 'conRepControl.htm?action=exportarExcelObservacionesFisca';
				document.forms.formObservacionesModal.action = formURL;
				document.forms.formObservacionesModal.method = "POST";
	
				var numRuc = $('#numRucObservacionModal_f').val();
				var razonSocial = $('#razonSocialObservacionModal_f').val();
				var numExpeOrigen = $('#numExpeOrigenObservacionModal_f').val();				
				var estExpediente = $('#estExpedienteObservacionModal_f').val();
				var desProceso = $('#desProcesoObservacionModal_f').val();
				var desExpediente = $('#desExpedienteObservacionModal_f').val();
				var desOrigen = $('#desOrigenObservacionModal_f').val();
				var fechaOrigen = $('#fechaOrigenObservacionModal_f').val();
				var fechaVirtual = $('#fechaVirtualObservacionModal_f').val();
				var fechaNotificacion=$('').val();
				var formaNotificacion=$('').val();

				$('#hiddenListaExpObservacionModal').val('');
				$('#hiddenNumRucObservacionModal').val('');
				$('#hiddenRazonSocialObservacionModal').val('');
				$('#hiddenNumExpOrigenObservacionModal').val('');			
				$('#hiddenEstExpedienteObservacionModal').val('');
				$('#hiddenTipoProcesoObservacionModal').val('');
				$('#hiddenTipoExpedienteObservacionModal').val('');
				$('#hiddenDescOrigenObservacionModal').val('');
				$('#hiddenFechaGeneracionObservacionModal').val('');
				$('#hiddenFechaOrigenObservacionModal').val('');

				$('#hiddenListaExpObservacionModal').val(listaCadena);
				$('#hiddenNumRucObservacionModal').val(numRuc);
				$('#hiddenRazonSocialObservacionModal').val(razonSocial);
				$('#hiddenNumExpOrigenObservacionModal').val(numExpeOrigen);			
				$('#hiddenEstExpedienteObservacionModal').val(estExpediente);
				$('#hiddenTipoProcesoObservacionModal').val(desProceso);
				$('#hiddenTipoExpedienteObservacionModal').val(desExpediente);
				$('#hiddenDescOrigenObservacionModal').val(desOrigen);
				$('#hiddenFechaGeneracionObservacionModal').val(fechaVirtual);
				$('#hiddenFechaOrigenObservacionModal').val(fechaOrigen);
		
				$('html').block({message: '<h1>Procesando</h1>'});
				document.forms.formObservacionesModal.submit();
				$('html').unblock();
			
				}
			}
	}
	
	function exportarExpedienteModal(){
		var dataExp = $('#tableExpediente').dataTable().fnGetData();
			if(dataExp.length > 0){
				
				var listaCadena = JSON.stringify(dataExp);
				var formURL = 'conRepControl.htm?action=exportarExcelTrazabilidad';
				document.forms.formExpedienteModal.action = formURL;
				document.forms.formExpedienteModal.method = "POST";
				/*Obtener Paramtros Inicio*/
				var numRuc = $('#numRucExpedienteModal').val();
				var razonSocial = $('#razonSocialExpedienteModal').val();
				var numExpeOrigen = $('#numExpeOrigenExpedienteModal').val();
				var numExpeVirtual = $('#numExpeVirtualExpedienteModal').val();
				var estExpediente = $('#estExpedienteExpedienteModal').val();
				var desProceso = $('#desProcesoExpedienteModal').val();
				var desExpediente = $('#desExpedienteExpedienteModal').val();				
				var fechaVirtual = $('#fechaVirtualExpedienteModal').val();
				var desOrigen = $('#desOrigenDocumentoModal').val();
				/*Obtener Paramtros fin*/
				/*limpiar campos hdn Inicio*/
				$('#hiddenListaExpExpedienteModal').val('');
				$('#hiddenNumRucExpedienteModal').val('');
				$('#hiddenRazonSocialExpedienteModal').val('');
				$('#hiddenNumExpOrigenExpedienteModal').val('');
				$('#hiddenNumExpVirtualExpedienteModal').val('');
				$('#hiddenEstExpedienteExpedienteModal').val('');
				$('#hiddenTipoProcesoExpedienteModal').val('');
				$('#hiddenTipoExpedienteExpedienteModal').val('');
				$('#hiddenFechaGeneracionExpedienteModal').val('');
				
				/*limpiar campos hdn Inicio*/
				/*setear campos hdn Inicio*/
				$('#hiddenListaExpExpedienteModal').val(listaCadena);
				$('#hiddenNumRucExpedienteModal').val(numRuc);
				$('#hiddenRazonSocialExpedienteModal').val(razonSocial);
				$('#hiddenNumExpOrigenExpedienteModal').val(numExpeOrigen);
				$('#hiddenNumExpVirtualExpedienteModal').val(numExpeVirtual);
				$('#hiddenEstExpedienteExpedienteModal').val(estExpediente);
				$('#hiddenTipoProcesoExpedienteModal').val(desProceso);
				$('#hiddenTipoExpedienteExpedienteModal').val(desExpediente);
				$('#hiddenDescOrigenExpedienteModal').val(desOrigen);
				$('#hiddenFechaGeneracionExpedienteModal').val(fechaVirtual);
				/*setear campos hdn fin*/
				$('html').block({message: '<h1>Procesando</h1>'});
				document.forms.formExpedienteModal.submit();
				$('html').unblock();
			}
	}
	//mostrar Pagina de Error
	function mostrarPagError() {
		
		var formURL = '${pageContext.request.contextPath}/error.htm?action=cargarPaginaError';
		document.forms.frmFiltroBusquedaEspecifica.action = formURL;
		document.forms.frmFiltroBusquedaEspecifica.method = "post";
		document.forms.frmFiltroBusquedaEspecifica.submit();
		
	}

	
	function exportarExcel(){
		
		var dataExp = $('#tblExpedientes').dataTable().fnGetData();
		
		if(dataExp.length > 0){
			//AQ-FSW
			dataExp = caracterHtml(false,dataExp);	
			var listaCadena = JSON.stringify(dataExp);
		    var formURL = 'conRepControl.htm?action=exportarExcelAccesoExp';
			document.forms.formPrincipal.action = formURL;
			document.forms.formPrincipal.method = "POST";
			$('#campoHiddenExp').val('');
			$('#campoHiddenExp').val(listaCadena);
			$('html').block({message: '<h1>Procesando</h1>'});
			document.forms.formPrincipal.submit();
			$('html').unblock();
		}
	}
	
	function consultarExpedientes(){
		
		listaExpedientes = [];
		var cantExpAbiertos=0;
		var cantExpCerrados=0;
		var cantTotalExp=0;		 
	
		var urlFiltroBusqEspecifica = $('#frmFiltroBusquedaEspecifica').serialize();
		 var $txtNumeroExpediente = $("#txtNumeroExpediente");
		 if ($txtNumeroExpediente.val()==""){			
			 mostrarDlgInfo("Consulta Expediente", "Ingrese Nro de Expediente");
		 }else {			 
			 if($txtNumeroExpediente.prop('disabled')){
				 urlFiltroBusqEspecifica += "&" + $txtNumeroExpediente.attr("name") + "=" + $txtNumeroExpediente.val();
		 }
	
		
		$.ajax({
			
			url : '${pageContext.request.contextPath}/conRepControl.htm?action=obtenerAccesoExpVirt',
			type : 'POST',
			async : true,
			dataType : "json",
			data : urlFiltroBusqEspecifica,
			//data : "&numExpedienteVirtual="+numExpedienteVirtual,			
			//contentType : "application/json",
			mimeType : "application/json",
			//timeout : 5000,
			success : function(response) {
				
				var msjError = response.msjError;
				if(msjError!="" && msjError!=undefined){
					$('#tblExpedientes').dataTable().fnClearTable();
					$('#tblExpedientes').dataTable().fnDraw();
					mostrarDlgInfo(titulos.tituloDefecto, msjError);
					return;
				}
				
				listaExpedientes = response.listaExpedientesVirtuales;
			//INICIO LLRB 16012020
				if (listaExpedientes.length > 0) {					
						listaExpedientes = caracterHtml(true,listaExpedientes);					
						$('#tblExpedientes').dataTable().fnClearTable();
						$('#tblExpedientes').dataTable().fnAddData(listaExpedientes); //carga registros en grilla					
						$('#tblExpedientes').dataTable().fnDraw();							
						$('#exportar').attr('disabled', false);
				}
	
			},
			error : function(response) {
				
					$('#tblExpedientes').dataTable().fnClearTable();
					$('#tblExpedientes').dataTable().fnDraw();
				mostrarPagError();
				
			}
			
		});
	  }
	}	
	
	function consultarExpedientesAcumulados(numAcumulador) {
		limpiar();		
	
		$('#selTipoBusquedaExpediente').val("1");		
		$('#numAcumulador').val(numAcumulador);
		consultarExpedientes();
		
		$('#txtNumeroExpediente').val(numAcumulador);
		$('#numAcumulador').val('');
	}

	function activarCampo(){
		var codProceso = $('#selTipoBusquedaExpediente').val();
		if(codProceso==""){
			$('#frmFiltroBusquedaEspecifica').bootstrapValidator('resetForm', true);
			$('#txtNumeroExpediente').prop('disabled', true);
		}else if(codProceso=="1"){
			$('#txtNumeroExpediente').prop('disabled', false);
			$('#txtNumeroExpediente').val("");
			// Inicio [jquispe 30/05/2016] Valida que la longitud del numero de expediente de origen no sea mayor a 17.
			$("#txtNumeroExpediente").attr('maxlength', 17);			
		
			$('#frmFiltroBusquedaEspecifica').bootstrapValidator('revalidateField', 'numExp');		

		}else if(codProceso=="2"){
			$('#txtNumeroExpediente').prop('disabled', false);
			$('#txtNumeroExpediente').val("");
			$("#txtNumeroExpediente").attr('maxlength', 14);			
			$('#frmFiltroBusquedaEspecifica').bootstrapValidator('revalidateField', 'numExp');			
	
		}
	}
	
	function cargaModal(numExpedienteVirtual){		
		var dataEnvio = new Object();
		$.ajax({
			url : '${pageContext.request.contextPath}/conRepControl.htm?action=cargarTrazabilidadExpOrigen',
			type : 'POST',
			async : true,
			dataType : "json",
			data : "&numExpedienteVirtual="+numExpedienteVirtual,
			cache: false,
            async: true,
			//contentType : "application/json",
			mimeType : "application/json",
			//timeout : 5000,
			success : function(response) {
				
				var lstTrazExp = JSON.parse(response.lstTrazExp);
				var datosExpedientes = JSON.parse(response.datosExpedientes);
				var fechaOrigenDoc = JSON.parse(response.fechaOrigenDoc);
				var fechaRegistro = JSON.parse(response.fechaRegistro);
				var razonSocial=JSON.parse(response.razonSocial);
				//Inicio jquispe 02/07/2016 Estado del expediente origen del ultimo lista.
				var descripcionEstado=JSON.parse(response.descripcionEstado);
				//Fin jquispe 02/07/2016
				if (lstTrazExp.length > 0) {
					$('#tableExpediente').dataTable().fnClearTable();
					$('#tableExpediente').dataTable().fnAddData(lstTrazExp); //carga registros en grilla
					$('#exportarTrazabilidadModal').attr('disabled', false);	
				}else{
					$('#tableExpediente').dataTable().fnClearTable();
					$('#tableExpediente').dataTable().fnDraw();
					$('#exportarTrazabilidadModal').attr('disabled', true);
				}
				//INICIO LLRB 16012020
				
					$('#trazabilidad').show();
			
					
					$('#numRucExpedienteModal').attr("value", datosExpedientes.numRuc);
					$('#razonSocialExpedienteModal').attr("value", razonSocial);
					$('#numExpeOrigenExpedienteModal').attr("value", datosExpedientes.numExpedienteOrigen);
					$('#numExpeVirtualExpedienteModal').attr("value", datosExpedientes.numExpedienteVirtual);					
					$('#estExpedienteExpedienteModal').attr("value", descripcionEstado);
					$('#desProcesoExpedienteModal').attr("value", datosExpedientes.desProceso);
					$('#desExpedienteExpedienteModal').attr("value", datosExpedientes.desTipoExpediente);
					$('#hiddenFechaOrigenExpedienteModal').val(fechaOrigenDoc);			
					$('#fechaVirtualExpedienteModal').attr("value", fechaOrigenDoc);
							
					var titulo = "Trazabilidad de los estados y etapas del Expediente Or&iacute;gen";
					mostrarModalTrazabilidad(titulo);
			},
			error : function(response) {				
				mostrarPagError();				
			}		
		});		
	} 

	//AQ-FSW
	function caracterHtml(valor1,data){
		if(valor1){
			$.each(data, function(i, dato) {
				dato.desRazonSocial = dato.desRazonSocial.replace("<", "&lt;").replace(">", "&gt;");
			});	
			return data;
		}else{
			$.each(data, function(i, dato) {
				dato.desRazonSocial = dato.desRazonSocial.replace("&lt;", "<").replace("&gt;", ">");
			});
			return data;
		}
	
	}
	//Inicio LLRB
	
	function construirTablaDocumentos(data){
		
		var tableDM = $('#tableDocumentoModal').DataTable( {
					"language": {
						"url"		:	"../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
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
						{data :	"numOrden","defaultContent":""},
						{data :	"desTipdoc","defaultContent":""},
						{data :	"numDoc" ,"defaultContent":""},			
						{data :	"desOrigen" ,"defaultContent":""},						
						{data :	"indVisible" ,"defaultContent":""},
						{data :	"indReservTrib" ,"defaultContent":""},
						{data :	"nomPersonaCargo","defaultContent":""},					
						{data :	"fecCargVista","defaultContent":"" },						
						{data :	"fechaVista","defaultContent":"" },					
						{data : "fecNotifVista", "defaultContent":"" },
						{data : "desForNotif", "defaultContent":"" },			
						{data :	"numExpedo","defaultContent":"" },										
						{data :	"tipDocRel" ,"defaultContent":""},
						{data :	"numDocRel" ,"defaultContent":""},				
						
						{data :	"codIdecm","defaultContent":"", sClass: 'center alinearCentrado',
								render : function(data, type, row){								
											var link;
							
											if(row.codIdecm!="0") {		
												link = '<a href="conRepControl.htm?action=descargarArchivo&codIdentificadorECM='+ data +'&nombreArchivo='+row.descArchivo+'"role="button" class="cimg glyphicon glyphicon-download-alt" target=_blank></a>';												

											}else {
												
												link = '<a  role="button" class="cimg glyphicon glyphicon-plus-sign" target=_blank  onclick="listarArchivos	(\''+ 
												row.numCorDoc + '\',\'' + 
												row.desTipdoc + '\',\'' + 
												row.numDoc + '\')"></a>';
											}
											
											return link;										
										}
									},									

							//INICIO LLRB 16012020 						
						{data :	"numCopia","defaultContent":"", sClass: 'center alinearCentrado',
									render : function(data, type, row){								
											var link;
											if(row.numCopia!=0) {		
												link = '<a  role="button" class="cimg glyphicon glyphicon-plus-sign" target=_blank  onclick="listarCopia(\''+ 
												row.numCorDoc + '\',\'' + 
												row.desTipdoc + '\',\'' + 
						    					row.numDoc + '\')"></a>';											
										    }else {
												link = " ";	
										    }
											return link;										
							    }
						},
						{data :	"codIdecm", "defaultContent":"", sClass: 'center alinearCentrado',
							render : function(data, type, row){								
								var link;
								if(row.codIdecm!="0"){
									link = '<a href="javascript:void(0);" class="cimg glyphicon glyphicon-camera" onclick="verRepImpresa(' + (parseInt(row.numOrden) - 1) + ');"></a>';
									
								}
								else if (row.codIdecm="0"){
									link = " ";
								}
								return link;											
								}
							},//Fin LLRB

						{data : "estadoDocumentoReq", sClass: 'hidden',"defaultContent":""},	
						{data :	"codTipExp",sClass: 'hidden',"defaultContent":""},
						{data :	"nroRequerim","defaultContent":"",sClass: 'hidden' },
						{data :	"numAcumuladorVista" ,sClass: 'centered',"defaultContent":"",sClass: 'hidden',
							
								render : function(data, type, row){								
										var link;
										
										if(data == "" || data == "0") {
											return "";
										}else {
										   
											return $('<a>')
												.css('text-decoration','underline')
												.attr({	onclick :'obtenerDocumentosAcum("'+row.numAcumuladorVista+'")'})
												.text(data)
												.wrap('<div></div>')
												.parent()
												.html();
											
						             	}									
							}}
				
					],
					
					tableTools: {
				        "sSwfPath": "../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/TableTools/swf/copy_csv_xls.swf"
				    },
					data: data,
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
					
					bLengthChange		: 	false,
					"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
					$(iCol).attr('colClasses','tableOddCol');
					},
					fnDrawCallback		:	function(oSettings) {

						if (oSettings.fnRecordsTotal() == 0) {
							$('#tableDocumentoModal_paginate').addClass('hiddenDiv'); //el footer de paginacion siempre tiene como ID "miTablaID_paginate"
																				//no se oculta automaticamente
																	//cuando hay 0 registros, por eso se oculta anadiendo el class .hiddenDiv
				        }
						else {
							$('#tableDocumentoModal_paginate').removeClass('hiddenDiv');
							
						}
				    }
					        
		});		
		//Inicio LLRB 
		var tableDC = $('#tableDocumentoFiltroModal').DataTable( {
			"language": {
				"url"		:	"../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
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
				{data :	"numOrden","defaultContent":""},
				{data :	"desTipdoc","defaultContent":""},
				{data :	"numDoc" ,"defaultContent":""},
				{data :	"desOrigen" ,"defaultContent":""},		
				{data :	"nomPersonaCargo","defaultContent":""},		
				{data :	"fecCargVista","defaultContent":"" },		
				{data :	"fechaVista","defaultContent":"" },				
				{data : "fecNotifVista", "defaultContent":"" },
				{data : "desForNotif", "defaultContent":"" },	
				{data :	"numExpedo","defaultContent":"" },							
				{data :	"codIdecm","defaultContent":"", sClass: 'center alinearCentrado',
						render : function(data, type, row){								
									var link;
									
									if(row.codIdecm!="0") {		
										link = '<a href="conRepControl.htm?action=descargarArchivo&codIdentificadorECM='+ data +'&nombreArchivo='+row.descArchivo+'"role="button" class="cimg glyphicon glyphicon-download-alt" target=_blank></a>';												

									}else {
										
										link = '<a  role="button" class="cimg glyphicon glyphicon-plus-sign" target=_blank  onclick="listarArchivos	(\''+ 
										row.numCorDoc + '\',\'' + 
										row.desTipdoc + '\',\'' + 
										row.numDoc + '\')"></a>';
									}
									
									return link;										
								}
							},	
				//INICIO LLRB 16012020 						
				{data :	"numCopia","defaultContent":"", sClass: 'center alinearCentrado',
							render : function(data, type, row){								
									var link;
									if(row.numCopia!=0) {		
										link = '<a  role="button" class="cimg glyphicon glyphicon-plus-sign" target=_blank  onclick="listarCopia(\''+ 
										row.numCorDoc + '\',\'' + 
										row.desTipdoc + '\',\'' + 
				    					row.numDoc + '\')"></a>';											
								   }else {
										link = " ";	
								   }
								return link;										
						     }
				},
				{data :	"codIdecm", "defaultContent":"", sClass: 'center alinearCentrado',
					render : function(data, type, row){								
							var link;
							if(row.codIdecm="0"){
								link = " ";
							}
							else if (row.codIdecm!="0"){
								link = '<a href="javascript:void(0);" class="cimg glyphicon glyphicon-camera" onclick="verRepImpresa(' + (parseInt(row.numOrden) - 1) + ');"></a>';
							}
							return link;										
						}
					},//fin LLRB

				{data : "estadoDocumentoReq", sClass: 'hidden',"defaultContent":""},	
				{data :	"codTipExp",sClass: 'hidden',"defaultContent":""},
				{data :	"numAcumuladorVista" ,sClass: 'centered',"defaultContent":"",sClass: 'hidden',
					
						render : function(data, type, row){								
								var link;
								
								if(data == "" || data == "0") {
									return "";
								}else {
								   
									return $('<a>')
										.css('text-decoration','underline')
										.attr({	onclick :'obtenerDocumentosAcum("'+row.numAcumuladorVista+'")'})
										.text(data)
										.wrap('<div></div>')
										.parent()
										.html();
									
				             	}									
					}}
		
			],
			
			tableTools: {
		        "sSwfPath": "../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/TableTools/swf/copy_csv_xls.swf"
		    },
			data: data,
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
			
			bLengthChange		: 	false,
			"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
			$(iCol).attr('colClasses','tableOddCol');
			},
			fnDrawCallback		:	function(oSettings) {
				if (oSettings.fnRecordsTotal() == 0) {
					$('#tableDocumentoFiltroModal_paginate').addClass('hiddenDiv'); //el footer de paginacion siempre tiene como ID "miTablaID_paginate"
																		//no se oculta automaticamente
															//cuando hay 0 registros, por eso se oculta anadiendo el class .hiddenDiv
		        }
				else {
					$('#tableDocumentoFiltroModal_paginate').removeClass('hiddenDiv');
					
				}
		    }
			        
		} );
	}

	function obtenerDocumentos(numExpedienteVirtual){
		borrarErrorExtension();
		borrarErrorExtensionArchivo();
		var dataEnvio = new Object();
		$.ajax({
			url : '${pageContext.request.contextPath}/conRepControl.htm?action=cargarDocumentosExpVirtual',
			type : 'POST',
			async : true,
			dataType : "json",
			data : "&numExpedienteVirtual="+numExpedienteVirtual,
			cache: false,
            async: true,
	
			mimeType : "application/json",
	
			success : function(response) {	
				
				var lstDocExp = JSON.parse(response.lstDocExp);
				var datosExpedientes = JSON.parse(response.t6614ExpVirtualBean);
				var fechaOrigenDoc = JSON.parse(response.fechaOrigenDoc);
				var fechaRegistro = JSON.parse(response.fechaRegistro);
				var razonSocial=JSON.parse(response.razonSocial);
				var excepciones = JSON.parse(response.excepciones);		
				
				if (lstDocExp.length > 0) {
					//Inicio LLRB 					
					if(datosExpedientes.codTipoExpediente== '430' ||datosExpedientes.codTipoExpediente== '431' ){
						$('#DocumentoCompleto').show();
						$('#DocumentoIncompleto').hide();
						$('#tableDocumentoModal').dataTable().fnClearTable();
						$('#tableDocumentoModal').dataTable().fnAddData(lstDocExp); //carga registros en grilla	
						$('#exportarDocumentos').attr('disabled', false);
					}else{
						$('#DocumentoCompleto').hide();
						$('#DocumentoIncompleto').show();
						$('#tableDocumentoFiltroModal').dataTable().fnClearTable();
						$('#tableDocumentoFiltroModal').dataTable().fnAddData(lstDocExp); //carga registros en grilla					
						$('#exportarDocumentos').attr('disabled', false);
						}

					}else{
						$('#tableDocumentoModal').dataTable().fnClearTable();
						$('#tableDocumentoModal').dataTable().fnAddData(lstDocExp); 	
						$('#exportarDocumentos').attr('disabled', true);
					}
				//Fin LLRB
				
				$('#numRucDocumentoModal').attr("value", datosExpedientes.numRuc);
				$('#razonSocialDocumentoModal').attr("value", razonSocial);
				$('#numExpeOrigenDocumentoModal').attr("value", datosExpedientes.numExpedienteOrigen);
				$('#numExpeVirtualDocumentoModal').attr("value", datosExpedientes.numExpedienteVirtual);
				$('#estExpedienteDocumentoModal').attr("value", datosExpedientes.desEstado);
				$('#desProcesoDocumentoModal').attr("value", datosExpedientes.desProceso);
				$('#desExpedienteDocumentoModal').attr("value", datosExpedientes.desTipoExpediente);
				$('#desOrigenDocumentoModal').attr("value", datosExpedientes.desOrigen);
				$('#fechaOrigenDocumentoModal').attr("value", fechaOrigenDoc);
				$('#fechaVirtualDocumentoModal').attr("value", fechaRegistro);
				var titulo = "Detalle de Documentos - N&deg; Expediente Virtual " + datosExpedientes.numExpedienteVirtual;
				mostrarModal(titulo);
				
				//eaguilar 8 AGO
				setTimeout(
						function(){
							$("#tableDocumentoModal_wrapper").css("min-width", $('#tableDocumentoModal').width());
							$('#tableDocumentoModal_paginate').addClass('div100');
				}, 150);
			},
			error : function(response) {
				
				mostrarPagError();
				
			}
		
		});
	}
	function mostrarModalArchivo(titulo){
		$('#dlgTitleModalArchivos').html(titulo);
		$('#modalArchivos').modal('show');
	}
	function listarArchivos(numCorDoc,desTipoDoc,numDoc){
		var dataEnvio = new Object();
		$.ajax({
				url : '${pageContext.request.contextPath}/conRepControl.htm?action=cargarArchivosEscElec',
				type : 'POST',
				async : true,
				dataType : "json",
				data : "&numCorDoc="+numCorDoc,
				cache: false,
	            async: true,
				mimeType : "application/json",
				success : function(response) {	
					
					var lstArchExp = JSON.parse(response.lstArchExp);
				
					if (lstArchExp.length > 0) {
						$('#tableArchivoModal').dataTable().fnClearTable();
						$('#tableArchivoModal').dataTable().fnAddData(lstArchExp); //carga registros en grilla	
						
					}else{
						$('#tableArchivoModal').dataTable().fnClearTable();
						$('#tableArchivoModal').dataTable().fnDraw();  	
						
					}

					var titulo = "Lista de archivos del documento " +desTipoDoc + "-" +numDoc;
					
					mostrarModalArchivo(titulo);
					
				
					setTimeout(
							function(){
								$("#tableArchivoModal_wrapper").css("min-width", $('#tableArchivoModal').width());
								$('#tableArchivoModal_paginate').addClass('div100');
					}, 150);
				},
				error : function(response) {
					
					mostrarPagError();
					
				}
			
			});
		}
	
	function construirTablaArchivo(data){
		$('#tableArchivoModal').DataTable( {
			"language": {
				"url"		:	"../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
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
					{data :	"numOrden","defaultContent":"",sClass:"hidden"},
					{data :	"numArc","defaultContent":""},
										{data :	"nomArcAdj","defaultContent":"" },
										{data :	"numItem","defaultContent":"" },
										{data :	"cntTamArc","defaultContent":"",
											render:function(data, type, row){								
												var tamano;
												tamano  = (row.cntTamArc)/1024.00;
												 if (tamano >= 1) {
									                    //tamano = Math.round(tamano);
									            	 var tamanoArchivo = parseFloat(tamano).toFixed(2);
									             }
									            
												 return tamanoArchivo+' KB';																
											}
										},
					{data :	"numFolios","defaultContent":""},
					{data :	"codIdecm","defaultContent":"", sClass: 'center alinearCentrado',
						render : function(data, type, row){								
									var link;
										link = '<a href="conRepControl.htm?action=descargarArchivoModal&codIdentificadorECM='+ data +'&nombreArchivo='+row.nomArcAdj+'"role="button" class="cimg glyphicon glyphicon-download-alt" target=_blank></a>';
										
								return link;										
								}
							},
					{data :	"codIdecm", "defaultContent":"", sClass: 'center alinearCentrado',
						render : function(data, type, row){	
								var archivo = row.nomArcAdj
								var tipo = archivo.substr(archivo.length -3)
								if(tipo!="zip"){
									var link;
										link = '<a  href="javascript:void(0);" class="cimg glyphicon glyphicon-camera"  onclick="verRepArchivoImpresa	(\''+ 
										(parseInt(row.numOrden) - 1) + '\',\'' + 
										row.numCorDoc + '\')"></a>';
										
									}else{
	    						    	  link = " ";
	    						      }
	    								return link;	
						}
    				},
					{data :	"codTipExp","defaultContent":"",sClass:"hidden"},
					{data :	"codTipDoc","defaultContent":"",sClass:"hidden"},
					{data :	"numDoc","defaultContent":"",sClass:"hidden"},
					{data :	"numExpedO","defaultContent":"",sClass:"hidden"},
					{data :	"numCorDoc","defaultContent":"",sClass: 'hidden'}
					
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
			iDisplayLength		:	5,
			
			bLengthChange		: 	false,
			fnDrawCallback		:	function(oSettings) {
				var a = $('#tableArchivoModal').width()
				$("#tableArchivoModal_wrapper").css("min-width", a);
				$('#tableArchivoModal_paginate').addClass('div100');
				if (oSettings.fnRecordsTotal() == 0) {
					$('#table_paginate').addClass('hiddenDiv'); //el footer de paginacion siempre tiene como ID "miTablaID_paginate"
																		//no se oculta automaticamente
															//cuando hay 0 registros, por eso se oculta anadiendo el class .hiddenDiv
		        }
				else {
					$('#table_paginate').removeClass('hiddenDiv');
					
				}
		    }
			        
	      } );

	}
		
	function mostrarErrorExtensionArchivo(extension){
		$('#divErrorExtensionArchivo').html('No se puede generar Representaci&oacute;n Impresa del Documento con extensi&oacute;n: "' + extension + '"');
	}

	function borrarErrorExtensionArchivo(){
		$('#divErrorExtensionArchivo').html('');
	}
	 function verRepArchivoImpresa(idxFila,numCorDoc){
		 borrarErrorExtensionArchivo();
			
			var dataEnvio = new Object();
			dataEnvio.docData = JSON.stringify($('#tableArchivoModal').DataTable().row(idxFila).data());
			dataEnvio.ruc = $("#numRucDocumentoModalFis").val();  
			dataEnvio.razSoc = $.trim($("#razonSocialDocumentoModalFis").val());
			
			$.ajax({
	       
	            url: '${pageContext.request.contextPath}/conRepControl.htm?action=validarArchivoExtension',
	           	data: $.param(dataEnvio),//"&codProceso="+codProceso,
	            cache: false,
	            async: true,
	            type: 'POST',
	            contentType : "application/x-www-form-urlencoded; charset=utf-8",
	            dataType: 'json',
	            success: function(response) {
	            	
	            	if(!response.error){
	            		window.open('conRepControl.htm?action=verRepArchivoImpresa&' + $.param(dataEnvio), "_blank", "toolbar=yes, scrollbars=yes, resizable=yes, top=20, left=300, width=600, height=650");
	            	}else{
	            		mostrarErrorExtensionArchivo(response.extension);
	            	}
	            	
	            },
	            error: function(err) {
	            	document.write(err.responseText);
	            }
	        });
		}
	 function validarExpediente(){

			var numExp = $('#txtNumeroExpediente').val();
			var urlFiltroBusqEspecifica = $('#frmFiltroBusquedaEspecifica').serialize();
			 var $txtNumeroExpediente = $("#txtNumeroExpediente");
			 if ($txtNumeroExpediente.val()==""){			
				 mostrarDlgInfo("Consulta Expediente", "Ingrese Nro de Expediente");
			 }else {			 
				 if($txtNumeroExpediente.prop('disabled')){
					 urlFiltroBusqEspecifica += "&" + $txtNumeroExpediente.attr("name") + "=" + $txtNumeroExpediente.val();
			 }
			
			$.ajax({
				
				url : '${pageContext.request.contextPath}/conRepControl.htm?action=validarExpediente',
				type : 'POST',
				async : false,
				dataType : "json",
				data : urlFiltroBusqEspecifica,				
				mimeType : "application/json",
				success : function(response) {
					
					var nroExpediente = response.numExpediente;
					var msjError =  "El usuario no es responsable del expediente";
					var msj =  "El Exp Origen no existe.";
			
					if (nroExpediente >= 1) {							
						consultarExpedientes();	
						
					}else if (nroExpediente == 999){
						mostrarDlgInfo(titulos.tituloDefecto, msj);	
						return;
					}else{
						mostrarDlgInfo(titulos.tituloDefecto, msjError);	
						return;
					}
					
				},
				error : function(response) {
					
					mostrarPagError();
				}
				
			});
		   }
		}
	 function validarSolicitud(idxFila){
		 var dataEnvio = new Object();
			dataEnvio.docData = JSON.stringify($('#tblExpedientes').DataTable().row(idxFila).data());		
					
			$.ajax({
				
				url : '${pageContext.request.contextPath}/conRepControl.htm?action=validarSolicitud',
				type : 'POST',
				async : false,
				dataType : "json",
				data : $.param(dataEnvio),				
				mimeType : "application/json",
				success : function(response) {
					
					var nroSolicitud = response.numSolicitud;
					var msjError =  "Ya ha sido solicitada la descarga del Expediente";
			
					if (nroSolicitud == 0) {							
						solicitaDescarga(idxFila);							
						
					}else{
						mostrarDlgInfo(titulos.tituloDefecto, msjError);	
						return;
					}
					
				},
				error : function(response) {
					
					mostrarPagError();
				}
				
			});
	}
	 function solicitaDescarga(idxFila){		
		 var dataEnvio = new Object();
			dataEnvio.docData = JSON.stringify($('#tblExpedientes').DataTable().row(idxFila).data());			

			$.ajax({
				
				url : '${pageContext.request.contextPath}/conRepControl.htm?action=generarSolicitud',
				type : 'POST',
				async : false,
				dataType : "json",
				data : $.param(dataEnvio),			
				mimeType : "application/json",
				success : function(response) {
					
					var numSolicitud = response.numSolicitud;
					var codProc = response.codProc;
					var codTipExp = response.codTipExp;
					var msjError =  response.msjError;					
					if (numSolicitud !="") {						
						//mostrarDlgInfo("Registrar Solicitud", "N&uacute;mero de Solicitud Generado :N&deg; "+codProc+"-"+codTipExp+"-"+numSolicitud+".Su descarga estar&aacute; habilitada a partir del d&iacutea de ma&ntildeana.");
						mostrarDlgInfo("Registrar Solicitud", "N&uacute;mero de Solicitud Generada : La descarga estar&aacute; disponible al d&iacutea siguiente. N&deg;"+codProc+"-"+codTipExp+"-"+numSolicitud);
						
					}else{
						mostrarDlgInfo(titulos.tituloDefecto, msjError);	
						return;
					}
					
				},
				error : function(response) {
					
					mostrarPagError();
				}
				
			});
		}
	 function obtenerExpVinculados(idxFila){		
		 var dataEnvio = new Object();
			dataEnvio.docData = JSON.stringify($('#tblExpedientes').DataTable().row(idxFila).data());			
			$.ajax({
				url : '${pageContext.request.contextPath}/conRepControl.htm?action=cargarExpVirtVinculados',
				type : 'POST',
				async : true,
				dataType : "json",
				data : $.param(dataEnvio),
				cache: false,
	            async: true,
		
				mimeType : "application/json",
		
				success : function(response) {						
				
					var datosExpedientes = JSON.parse(response.datosExpedientes);		
					var fechaOrigenDoc = JSON.parse(response.fechaOrigenDoc);
					var razonSocial = JSON.parse(response.razonSocial);					
					var lstExpVinc = JSON.parse(response.lstExpVinc);
					var excepciones = JSON.parse(response.excepciones);					
					if (excepciones.excepcion02 != null){
						
						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion02);
						
					}else{
					
					if (lstExpVinc.length > 0) {
						//Inicio LLRB 						
						lstExpVinc = caracterHtml(true,lstExpVinc);					
						$('#tableExpVinculados').dataTable().fnClearTable();
						$('#tableExpVinculados').dataTable().fnAddData(lstExpVinc); //carga registros en grilla					
						$('#tableExpVinculados').dataTable().fnDraw();							
						$('exportarExpVinculadosModal').attr('disabled', false);
						

					}else{
						$('#tableExpVinculados').dataTable().fnClearTable();
						$('#tableExpVinculados').dataTable().fnDraw(); 
						$('exportarExpVinculadosModal').attr('disabled', true);
					}
			
					//Fin LLRB
					
					$('#numRucExpVinculadosModal').attr("value", datosExpedientes.numRuc);
					$('#razonSocialExpVinculadosModal').attr("value", razonSocial);
					$('#numExpeOrigenExpVinculadosModal').attr("value", datosExpedientes.numExpedienteOrigen);
					$('#numExpeVirtualExpVinculadosModal').attr("value", datosExpedientes.numExpedienteVirtual);
					$('#estExpedienteExpVinculadosModal').attr("value", datosExpedientes.desEstado);
					$('#desProcesoExpVinculadosModal').attr("value", datosExpedientes.desProceso);
					$('#desExpVinculadosModal').attr("value", datosExpedientes.desTipoExpediente);				
					$('#fechaVirtualExpVinculadosModal').attr("value", fechaOrigenDoc);				
					
					var titulo = "LISTADO DE EXPEDIENTES VINCULADOS";
					mostrarModalExpVinc(titulo);
					
					//eaguilar 8 AGO
					setTimeout(
							function(){
								$("#tableExpVinculados_wrapper").css("min-width", $('#tableExpVinculados').width());
								$('#tableExpVinculados_paginate').addClass('div100');
					}, 150);
					
				   }
				},
				error : function(response) {
					
					mostrarPagError();
					
				}
			
			});
		}
	function mostrarModalExpVinc(titulo){
			$('#dlgTtitleExpVinculadoModal').html(titulo);
			$('#modalExpVinculado').modal('show');
	}
	
	 function construirTablaExpVinculados(dataGrilla) {
		 var i=1;
	       	var table = $('#tableExpVinculados').DataTable({

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
				bLengthChange		: 	false,
				"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
					
					  $(iCol).attr('colClasses','tableOddCol');
					
				 },
	            fnDrawCallback: function(oSettings) {
	            	var a = $('#tblExpedientes').width()
					$("#tableExpVinculados_wrapper").css("min-width", a);	
	            	$('#tableExpVinculadoss_paginate').addClass('div100');
	                if (oSettings.fnRecordsTotal() == 0) {
	                    $('#table_paginate').addClass('hiddenDiv');
	                } else {
	                    $('#table_paginate').removeClass('hiddenDiv');
	                }
	            },
				fnRowCallback: function (nRow, aData, iDisplayIndex) {
	                    $(nRow).attr('id', aData[2]);
						$(nRow).attr('align', 'center');
						$(nRow).attr('rowClasses','tableOddRow');
	                    return nRow;
	            },
				language: {
	                url: "/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
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
				columns: [	
				          	{data :	"numOrden",sClass: 'centered alinearCentrado',
			          		render : function(data,type, row){
			          		
				          		var numOrden;
				          		if (row.numOrden=1 ){
				          			numOrden = i;
				          			i=i+1;
				          		}
				          		return numOrden;				          		
			    			 }
			          	   },
				          	{data :	"numExpedienteOrigen", render : function(data,type, row){							
								 return $('<a>')
								 	.css('text-decoration','underline')
					                .attr({	onclick :'cargaModal('+row.numExpedienteVirtual+')'})
					                .text(data)
					                .wrap('<div></div>')
					                .parent()
					                .html();
					     	}, sClass: 'centered alinearCentrado'},
					     	{data : "nombreResponsable", sClass: 'centered alinearCentrado',"defaultContent":""},		
							{data : "numRuc", sClass: 'centered alinearCentrado',"defaultContent":""},						
							{data : "desRazonSocial",sClass:'centered alinearCentrado',"defaultContent":""},						
							{data : "desProceso", sClass: 'centered alinearCentrado',"defaultContent":""},
							{data :	"desTipoExpediente", sClass: 'centered alinearCentrado',"defaultContent":"" },
							{data :	"desEstado", sClass: 'centered alinearCentrado',"defaultContent":"" },						
							{data :	"fechaDocumentoOrigen", sClass: 'centered alinearCentrado',"defaultContent":""},						
							{data : "desOrigen", sClass: 'centered alinearCentrado',"defaultContent":""},					
							{data :	"numExpedienteVirtual","defaultContent":"", render : function(data,type, row){
								var link1 = '<a onclick="obtenerDocumentosVinculados('+data+')">Ver Docs</a>';
								var link2 = '<a onclick="obtenerObservaciones('+data+')">Ver Obs</a>';						
								
								 var table = '<table><tr><td nowrap="nowrap" style="border-right: 0px !important;">';
								 table += link1;
								 table += '</td></tr><tr><td style="border-right: 0px !important;">';
								 table += link2;							
								 table += '</td></tr><tr><td style="border-right: 0px !important;">';								
							
								 table += '</td></tr></table>';
								 
								 return table;
					        }, sClass: 'centered alinearCentrado'}
				]
	        });
	    	
	    }
	 function obtenerDocumentosVinculados(numExpedienteVirtual){
		 borrarErrorExtension();
			borrarErrorExtensionArchivo();
			var dataEnvio = new Object();
			$.ajax({
				url : '${pageContext.request.contextPath}/conRepControl.htm?action=cargarDocumentosExpVirtual',
				type : 'POST',
				async : true,
				dataType : "json",
				data : "&numExpedienteVirtual="+numExpedienteVirtual,
				cache: false,
	            async: true,
		
				mimeType : "application/json",
		
				success : function(response) {	
					
					var lstDocExp = JSON.parse(response.lstDocExp);
					var datosExpedientes = JSON.parse(response.t6614ExpVirtualBean);
					var fechaOrigenDoc = JSON.parse(response.fechaOrigenDoc);
					var fechaRegistro = JSON.parse(response.fechaRegistro);
					var razonSocial=JSON.parse(response.razonSocial);
					var excepciones = JSON.parse(response.excepciones);
					var tipoExpediente = JSON.parse(response.tipoExp);
										
						if (lstDocExp.length > 0) {
							//Inicio LLRB 							
							if(datosExpedientes.codTipoExpediente== '430' ||datosExpedientes.codTipoExpediente== '431' ){
								$('#DocumentoCompleto').show();
								$('#DocumentoIncompleto').hide();
								$('#tableDocumentoModal').dataTable().fnClearTable();
								$('#tableDocumentoModal').dataTable().fnAddData(lstDocExp); //carga registros en grilla	
								$('#exportarDocumentos').attr('disabled', false);
							}else{
								$('#DocumentoCompleto').hide();
								$('#DocumentoIncompleto').show();
								$('#tableDocumentoFiltroModal').dataTable().fnClearTable();
								$('#tableDocumentoFiltroModal').dataTable().fnAddData(lstDocExp); //carga registros en grilla					
								$('#exportarDocumentos').attr('disabled', false);
								}
	
							}else{
								$('#tableDocumentoModal').dataTable().fnClearTable();
								$('#tableDocumentoModal').dataTable().fnAddData(lstDocExp); 	
								$('#exportarDocumentos').attr('disabled', true);
							}
					//Fin LLRB
					
					$('#numRucDocumentoModal').attr("value", datosExpedientes.numRuc);
					$('#razonSocialDocumentoModal').attr("value", razonSocial);
					$('#numExpeOrigenDocumentoModal').attr("value", datosExpedientes.numExpedienteOrigen);
					$('#numExpeVirtualDocumentoModal').attr("value", datosExpedientes.numExpedienteVirtual);
					$('#estExpedienteDocumentoModal').attr("value", datosExpedientes.desEstado);
					$('#desProcesoDocumentoModal').attr("value", datosExpedientes.desProceso);
					$('#desExpedienteDocumentoModal').attr("value", datosExpedientes.desTipoExpediente);
					$('#desOrigenDocumentoModal').attr("value", datosExpedientes.desOrigen);
					$('#fechaOrigenDocumentoModal').attr("value", fechaOrigenDoc);
					$('#fechaVirtualDocumentoModal').attr("value", fechaRegistro);
					var titulo = "Detalle de Documentos - N&deg; Expediente Virtual " + datosExpedientes.numExpedienteVirtual;
					mostrarModalVinculados(titulo);
					
					//eaguilar 8 AGO
					setTimeout(
							function(){
								$("#tableDocumentoModal_wrapper").css("min-width", $('#tableDocumentoModal').width());
								$('#tableDocumentoModal_paginate').addClass('div100');
					}, 150);
				  
				},
				error : function(response) {
					
					mostrarPagError();
					
				}
			
			});
		}
	 function mostrarModalVinculados(titulo){
			$('#dlgTitleModalDocumentos').html(titulo);
			$('#modalDocumentos').modal('show');
		}
	 function exportarExpVinculadosModal(){
			var dataExp = $('#tableExpVinculados').dataTable().fnGetData();			
				if(dataExp.length > 0){
					
					var listaCadena = JSON.stringify(dataExp);
					var formURL = 'conRepControl.htm?action=exportarExcelVinculados';
					document.forms.formExpedienteModal.action = formURL;
					document.forms.formExpedienteModal.method = "POST";
					/*Obtener Paramtros Inicio*/
					var numRuc = $('#numRucExpedienteModal').val();
					var razonSocial = $('#razonSocialExpedienteModal').val();
					var numExpeOrigen = $('#numExpeOrigenExpedienteModal').val();
					var numExpeVirtual = $('#numExpeVirtualExpedienteModal').val();
					var estExpediente = $('#estExpedienteExpedienteModal').val();
					var desProceso = $('#desProcesoExpedienteModal').val();
					var desExpediente = $('#desExpedienteExpedienteModal').val();				
					var fechaVirtual = $('#fechaVirtualExpedienteModal').val();
					var desOrigen = $('#desOrigenDocumentoModal').val();
					/*Obtener Paramtros fin*/
					/*limpiar campos hdn Inicio*/
					$('#hiddenListaExpExpedienteModal').val('');
					$('#hiddenNumRucExpedienteModal').val('');
					$('#hiddenRazonSocialExpedienteModal').val('');
					$('#hiddenNumExpOrigenExpedienteModal').val('');
					$('#hiddenNumExpVirtualExpedienteModal').val('');
					$('#hiddenEstExpedienteExpedienteModal').val('');
					$('#hiddenTipoProcesoExpedienteModal').val('');
					$('#hiddenTipoExpedienteExpedienteModal').val('');
					$('#hiddenFechaGeneracionExpedienteModal').val('');
					
					/*limpiar campos hdn Inicio*/
					/*setear campos hdn Inicio*/
					$('#hiddenListaExpExpedienteModal').val(listaCadena);
					$('#hiddenNumRucExpedienteModal').val(numRuc);
					$('#hiddenRazonSocialExpedienteModal').val(razonSocial);
					$('#hiddenNumExpOrigenExpedienteModal').val(numExpeOrigen);
					$('#hiddenNumExpVirtualExpedienteModal').val(numExpeVirtual);
					$('#hiddenEstExpedienteExpedienteModal').val(estExpediente);
					$('#hiddenTipoProcesoExpedienteModal').val(desProceso);
					$('#hiddenTipoExpedienteExpedienteModal').val(desExpediente);
					$('#hiddenDescOrigenExpedienteModal').val(desOrigen);
					$('#hiddenFechaGeneracionExpedienteModal').val(fechaVirtual);
					/*setear campos hdn fin*/
					$('html').block({message: '<h1>Procesando</h1>'});
					document.forms.formExpedienteModal.submit();
					$('html').unblock();
				}
		}
	 function mostrarModalCopia(titulo){
			$('#dlgTitleModalCopias').html(titulo);
			$('#modalCopias').modal('show');
		}
	function listarCopia(numCorDoc,desTipoDoc,numDoc){
		var dataEnvio = new Object();
		$.ajax({
				url : '${pageContext.request.contextPath}/conRepControl.htm?action=cargarCopiasEscElec',
				type : 'POST',
				async : true,
				dataType : "json",
				data : "&numCorDoc="+numCorDoc,
				cache: false,
	            async: true,
				mimeType : "application/json",
				success : function(response) {	
					
					var lstCopExpCopia = JSON.parse(response.lstCopExp);
				
					if (lstCopExpCopia.length > 0) {						
						$('#tableCopiasModal').dataTable().fnClearTable();
						$('#tableCopiasModal').dataTable().fnAddData(lstCopExpCopia); //carga registros en grilla	
						
					}else{
						$('#tableCopiasModal').dataTable().fnClearTable();
						$('#tableCopiasModal').dataTable().fnDraw();  	
						
					}
					var titulo = "Copia Personalizada del documento " +desTipoDoc + "-" +numDoc;					
					mostrarModalCopia(titulo);
					setTimeout(
							function(){
								$("#tableCopiasModal_wrapper").css("min-width", $('#tableCopiasModal').width());
								$('#tableCopiasModal_paginate').addClass('div100');
					}, 150);
				},
				error : function(response) {
					
					mostrarPagError();					
				}			
			});
		}
	
	function construirTablaCopia(data){
		$('#tableCopiasModal').DataTable( {
			"language": {
				"url"		:	"../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
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
					{data :	"numCopia","defaultContent":""},				
					{data :	"nomArcAdj","defaultContent":"" },					
					{data :	"cntTamArc","defaultContent":"",
						render:function(data, type, row){								
							var tamano;
							tamano  = (row.cntTamArc)/1024.00;
				             if (tamano >= 1) {
				                    tamano = Math.round(tamano);
				             }
				             var tamanoArchivo = parseFloat(tamano).toFixed(2);
				             var formato = "KB"
							return tamanoArchivo+formato;
							// return tamanoArchivo;
						}
					},					
					{data :	"codIdecm","defaultContent":"", sClass: 'center alinearCentrado',
						render : function(data, type, row){								
									var link;
										link = '<a href="conRepControl.htm?action=descargarCopiaModal&codIdentificadorECM='+ data +'&nombreArchivo='+row.nomArcAdj+'"role="button" class="cimg glyphicon glyphicon-download-alt" target=_blank></a>';
										
								return link;										
								}
							},

					{data :	"codUsuRegis","defaultContent":""},
					{data :	"fechaVista","defaultContent":""},
					{data :	"indVis","defaultContent":"",sClass:"hidden"},
					{data :	"numExpedO","defaultContent":"",sClass:"hidden"},
					{data :	"numCorDoc","defaultContent":"",sClass: 'hidden'}
					
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
			iDisplayLength		:	5,
			
			bLengthChange		: 	false,
			fnDrawCallback		:	function(oSettings) {
				var a = $('#tableArchivoModal').width()
				$("#tableArchivoModal_wrapper").css("min-width", a);
				$('#tableArchivoModal_paginate').addClass('div100');
				if (oSettings.fnRecordsTotal() == 0) {
					$('#table_paginate').addClass('hiddenDiv'); //el footer de paginacion siempre tiene como ID "miTablaID_paginate"
																		//no se oculta automaticamente
															//cuando hay 0 registros, por eso se oculta anadiendo el class .hiddenDiv
		        }
				else {
					$('#table_paginate').removeClass('hiddenDiv');
					
				}
		    }
			        
	      } );

	}
function mostrarDlgInfoExp(titulo, msj){ 
		
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
		crearDlgExp(titulo, msj, botones);
				
	}
	
	function crearDlgExp(titulo, msj, btns){
				
		$('#dlgTitle').html(titulo);
		$('#dlgMsj').html(msj);
		$('#dlgBtns').empty();
		
		jQuery.each(btns, function(i, dato) {
			$('#dlgBtns').append(dato);
		});
		
		$('#modalDlg').modal('show');
		
	}			
	
	


	 //FIN LLRB
	</script>
  </body>	
</html>	