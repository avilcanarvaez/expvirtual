
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=EDGE" />
<meta name="viewport"
	content="initial-scale = 1.0, user-scalable = no,  width=device-width">
<title>CONSULTA DE SEGUIMIENTO DE EXPEDIENTE</title>
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
/* 			
			
			
			
 */
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
			.panel-scroll {
			max-height: auto;
			min-height: 0px;
			overflow-y: auto;
			overflow-x: auto;
		}	
		.alinearCentrado {
			vertical-align: middle !important;
		}	
		
		</style>
	
</head>
<body>
</br>
<div id="container" class="container" style="width: 95%">
		<div>
			<div class="row">
				<div class="panel panel-primary">
					<div class="panel-heading align="center">
						<h3 class="panel-title" align="center">CONSULTA DE SEGUIMIENTO DE EXPEDIENTE</h3>
						<form id="frmPrincipal" class="form-horizontal" role="form">
						</form>
					</div>
				</div>	
				<div class="panel panel-primary">
					<form class="form-horizontal" role="form" name="frmFiltroBusquedaEspecificaEspecifica" id="frmFiltroBusquedaEspecifica" method="post">
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
								<legend class="scheduler-border"> Consulta Espec&iacute;fica </legend>
								<div class="form-group">
									<div class="col-md-1"><Strong>N&deg; de Expediente</Strong></div>
									<div class="col-md-3">
										<select name="codTipBusquedaExp" id="selTipoBusquedaExpediente" onchange="activarCampo()"class="form-control tamanoMaximo"> 
											<option value="">Seleccione</option>
										</select>
									</div>
									<div class="col-md-4">
										<input name="numExp" id="txtNumeroExpediente" type="text" class="form-control tamanoMaximo" placeHolder="" ></input>
									</div>
								</div>
						</fieldset>		
					</form>	
					<form class="form-horizontal" role="form" name="frmFiltroBusquedaAvanzada" id="frmFiltroBusquedaAvanzada" method="post">
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border" > Consulta Avanzada </legend>
							<div class="form-group">
								<div class="col-md-1"><Strong>Dependencia:</Strong></div>
								<div class="col-md-3">
									<select name="codDependencia" id="selDependencia" onchange="resetearFormularioConEsp(event)" class="form-control tamanoMaximo"> 
									</select>
								</div>
								<div class="col-md-1"><Strong>Proceso:</Strong></div>
								<div class="col-md-3">
									<select name="codProceso" id="selCodigoProceso" onchange="cargarTiposExpedientes()" class="form-control tamanoMaximo"> 
										<option value="">Seleccione</option>
									</select>
								</div>
								<div class="col-md-1"><Strong>Tipo de Expediente:</Strong></div>
								<div class="col-md-3">
									<select name="codTipexp" id="selCodigoTipoExpendiente" class="form-control tamanoMaximo"> 
										<option value="">Seleccione</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-1"><Strong>Fecha de:</Strong></div>
								<div class="col-md-3">
									<select name="codTipBusquedaFecha" id="selTipoBusquedaFecha" onchange="resetearFormularioConEsp(event)" class="form-control tamanoMaximo"> 
										<option value="">Seleccione</option>
									</select>
								</div>
								<div class="col-md-1"><Strong>Desde:</Strong></div>
								<div class="col-md-3">
									<div class='input-group date tamanoMaximo' id="divFechaDesde">
											<input type='text' class="form-control tamanoMaximo"  onkeypress="resetearFormularioConEsp(event)" id='txtfechaDesde' name='fecDesde' placeHolder="dd/mm/aaaa" maxlength="10" readonly="readonly"/> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
									</div>
								</div>
								<div class="col-md-1"><Strong>Hasta:</Strong></div>
								<div class="col-md-3">
									<div class='input-group date tamanoMaximo' id="divFechaHasta">
											<input type='text' class="form-control tamanoMaximo" onkeypress="resetearFormularioConEsp(event)" id='txtfechaHasta' name='fecHasta' placeHolder="dd/mm/aaaa" maxlength="10" readonly="readonly"/> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-1"><Strong>Estado:</Strong></div>
								<div class="col-md-3">
									<select name="codEstado" id="selCodigoEstado" class="form-control tamanoMaximo"> 
										<option value="">Todos</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-1"><Strong>Ruc:</Strong></div>
								<div class="col-md-3">
									<input name="numRuc" id="txtRuc" type="text" class="form-control tamanoMaximo" onkeypress="resetearFormularioConEsp(event)" maxlength="11"></input>
								</div>
								<div class="col-md-1"><Strong>Raz&oacute;n Social:</Strong></div>
								<div class="col-md-7">
									<input name="razonSocial" id="txtRazonSocial" type="text" class="form-control tamanoMaximo" disabled></input>
								</div>
							</div>						
						</fieldset>							
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
							<div class="form-group" id="documentos" >	
								<div class="col-md-12">
								<div class="tab-content">
								<div id="accionesFiscalizacion" class="tab-pane fade in active">
								<div class="border-line panel-scroll">
								<div>
									<table id="tblExpedientes" class="table display" style="width: 100%;">
										<thead>
											<tr>
												<th width="5%" class="text-center">N&deg;</th>
												<!-- Inicio [jquispe 16/06/2016] Modificado por la nueva columna Razon Social. -->
												<th width="8%" class="text-center">N&deg; de Expediente Origen</th>
												<th width="8%" class="text-center">N&deg; de Expediente Virtual</th>
												<!-- Fin [jquispe 16/06/2016] -->
												<th width="10%" class="text-center">Profesional Responsable</th>
												<!-- Inicio [jquispe 16/06/2016] Modificado por la nueva columna Razon Social. -->
												<th width="8%" class="text-center">RUC</th>
												<!-- Fin [jquispe 16/06/2016] -->
												<!-- Inicio [jquispe 16/06/2016] Nueva columna Razon Social. -->
											    <th width="10%" class="text-center">Raz&oacute;n Social</th>
											    <!-- Fin [jquispe 16/06/2016] -->
												<th width="8%" class="text-center">Proceso</th>
												<th width="5%" class="text-center">Tipo de Expediente</th>
												<th width="5%" class="text-center">Estado Expediente</th>
												<th width="5%" class="text-center">Estado Cierre</th>
												<!-- Inicio [jquispe 16/06/2016] Modificado por la nueva columna Razon Social. -->
												<th width="8%" class="text-center">Fecha de Registro del Expediente</th>
												<th width="8%" class="text-center">Fecha del Documento Origen</th>
												<!--Inicio JEFFIO  13/03/2017-->
												<th width="10%" class="text-center">Fecha de Notificaci&oacute;n</th>
												<th width="10%" class="text-center">Forma de Notificaci&oacute;n</th>
												<!--fin JEFFIO  13/03/2017-->
												<!-- Fin [jquispe 16/06/2016] -->
												<th width="5%" class="text-center">Origen</th>
												<!-- Inicio [jquispe 16/06/2016] Modificado por la nueva columna Razon Social. -->
												<th width="7%" class="text-center">Opci&oacute;n</th>
												<!-- Fin [jquispe 16/06/2016] -->
												<th> </th>
												</tr>
										</thead>
									</table>
								</div>
								</div>
								</div>
								</div>
								</div>								
							</div>
							<!-- INICIO[LLRB 15012020] -->
							<div class="form-group" id="documentosFisca" style="display: none">	
								<div class="col-md-12">
								<div class="tab-content">
								<div id="accionesFiscalizacion" class="tab-pane fade in active">
								<div class="border-line panel-scroll">
								<div>
									<table id="tblExpedientesFiltro" class="table display" style="width: 100%;">
										<thead>
											<tr>
												<th width="5%" class="text-center">N&deg;</th>								
												<th width="8%" class="text-center">N&deg; de Expediente 
												<th width="10%" class="text-center">Profesional Responsable</th>												
												<th width="8%" class="text-center">RUC</th>									
											    <th width="10%" class="text-center">Raz&oacute;n Social</th>						
												<th width="8%" class="text-center">Proceso</th>
												<th width="5%" class="text-center">Tipo de Expediente</th>
												<th width="5%" class="text-center">Estado Expediente</th>												
												<th width="8%" class="text-center">Fecha de Expediente</th>									
												<th width="5%" class="text-center">Origen</th>												
												<th width="7%" class="text-center">Opci&oacute;n</th>
												<!-- Fin [jquispe 16/06/2016] -->
												<th> </th>
												</tr>
										</thead>
									</table>
								</div>
								</div>
								</div>
								</div>
								</div>								
							</div>
							<!-- FIN[LLRB 15012020] -->
							<div class="form-group">
								<div class="col-md-2">
									<fieldset style="margin : 5px !important" class="scheduler-border">
										<div class="form-group" style="color:#0000FF" >
											<strong>Resumen de Expedientes </strong>
										</div>
										<div class="form-group" >
											<div class="col-md-6" style="color:#0000FF">
												Abiertos:
											</div>
											<div class="col-md-6" id ="abiertos" align="right">
											</div>
										</div>
										<div class="form-group"  >
											<div class="col-md-6" style="color:#0000FF" >
												Cerrados:
											</div>
											<div class="col-md-6" id ="cerrados" align="right">
											</div>
										</div>
										<div class="form-group" >
											<div class="col-md-6"  style="color:#0000FF">
												Total:
											</div>
											<div class="col-md-6" id ="total" align="right">
											</div>
										</div>
									</fieldset>	
								</div>
								<div class="col-md-8" align="center">									
									<button type="button" class="btn btn-primary" intermediateChanges="false" id="exportar" onclick="exportarExcel()">Exportar a Excel</button>
								</div>	
							</div>	
						</fieldset>		
					</form>
					<form id="formPrincipalGeneral" class="form-horizontal" role="form">	
						<div class="col-md-5" align="right" id="dvSecBotones01">	
							<input id="campoHiddenExp" type="hidden" name="listadoExpedientesCadena"/>
						</div>	
					</form>	
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
		
		<div id="modalForm" class="modal fade" role="dialog">
			<div class="modal-dialog" style="width: 1000px !important;">
				<div class="panel panel-info">
					<div class="panel-heading">
						<div>
						<div class="panel-heading">
							<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
							</button>
							<div id="modalTitle">TITULO</div>
						</div>
							<div class="row">
								<div class="panel panel-primary">									
									<fieldset class="scheduler-border" style="margin : 15px 15px !important">
									<legend class="scheduler-border">Datos del Expediente</legend>
									   <div class="form-group" id="DetalleSeguimiento">	
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
													<label>N&deg; Expediente Origen:</label>
												</div>
												<div id="txtCampo1" class="col-md-2">
													<input id="numExpeOrigen" name="numeroExpeOrigen" type="text" class="form-control" disabled="disabled"/>
												</div>
												<div class="col-md-2">
													<label>N&deg; Expediente Virtual:</label>
												</div>
												<div id="txtCampo2" class="col-md-2">
													<input id="numExpeVirtual" name="numeroExpeVirtual" type="text" class="form-control" disabled="disabled"/>
												</div>
												<div class="col-md-2">
													<label>Estado expediente:</label>
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
												<div class="col-md-2">
													<label>Tipo de expediente:</label>
												</div>
												<div id="txtCampo5" class="col-md-2">
													<input id="desExpediente" name="descripcionExpediente" type="text" class="form-control" disabled="disabled"/>
												</div>
												<div class="col-md-2">
													<label>Origen expediente:</label>
												</div>
												<div id="txtCampo6" class="col-md-2">
													<input id="desOrigen" name="descripcionOrigen" type="text" class="form-control" disabled="disabled"/>
												</div>												
											</div>
											<div class="row content-box">&nbsp;</div>
											<div class="row content-box">
												<div class="col-md-2">
													<label>Fecha de registro :</label>
												</div>
												<div id="txtCampo7" class="col-md-2">
													<input id="fechaVirtual" name="fechaExpedienteVirtual" type="text" class="form-control" disabled="disabled"/>
												</div>
												<div class="col-md-2">
													<label>Fecha de documento origen:</label>
												</div>
												<div id="txtCampo8" class="col-md-2">
													<input id="fechaOrigen" name="fechaOrigenExpediente" type="text" class="form-control" disabled="disabled"/>
												</div>
											</div>
											</div>
										</div>
										 <!-- INICIO[LLRB 15012020] -->
										<div class="form-group" id="DetalleSeguimientoFisca">	
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
													<label>N&deg; Expediente :</label>
												</div>
												<div id="txtCampo1" class="col-md-2">
													<input id="numExpeOrigen_f" name="numeroExpeOrigen" type="text" class="form-control" disabled="disabled"/>
												</div>						
												<div class="col-md-2">
													<label>Estado expediente:</label>
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
												<div class="col-md-2">
													<label>Tipo de expediente:</label>
												</div>
												<div id="txtCampo5" class="col-md-2">
													<input id="desExpediente_f" name="descripcionExpediente" type="text" class="form-control" disabled="disabled"/>
												</div>
												<div class="col-md-2">
													<label>Origen expediente:</label>
												</div>
												<div id="txtCampo6" class="col-md-2">
													<input id="desOrigen_f" name="descripcionOrigen" type="text" class="form-control" disabled="disabled"/>
												</div>												
											</div>
											<div class="row content-box">&nbsp;</div>
											<div class="row content-box">
												<div class="col-md-2">
													<label>Fecha de registro :</label>
												</div>
												<div id="txtCampo7" class="col-md-2">
													<input id="fechaVirtual_f" name="fechaExpedienteVirtual" type="text" class="form-control" disabled="disabled"/>
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
										<legend class="scheduler-border">Listado de Documentos </legend>
											<div class="panel-body">
												<div class="tab-content">
												<div id="documentos" class="tab-pane fade in active">
												<div class="border-line panel-scroll">
												<table id="tableDocumento" class="table display" cellspacing="0" style="width: 100%;"><!--table table-striped table-bordered-->
													<thead>
														<tr class="active">
															<th width="3%">N&deg;</th>
															<th width="17%">Tipo de Documento</th>
															<th width="15%">N&uacute;mero de Documento</th>
															<th width="17%">Origen</th>
															<th width="8%">Estado expediente</th>
															<th width="15%">Fecha de registro</th>
															<th width="15%">Responsable</th>
															<th width="15%">Sumilla</th>												
															<th width="8%">Motivo</th>
														</tr>
													</thead>
												</table>
												</div>
												</div>
												</div>
											</div>
									</fieldset>
									
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
					</div>
				</div>
			</div>
		</div>
		
		<div id="modalExpedienteOrigen" class="modal fade" role="dialog">
			<div class="modal-dialog" style="width: 1000px !important;">
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
											<div class="form-group" id="TrazaEstado">
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
														<label>N&deg; Expediente Origen:</label>
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
											<!-- INICIO[LLRB 15012020] -->
											<div class="form-group" id="TrazaEstadoFisca" style="display: none">
											<div class="panel-body">
												<div class="row content-box">
													<div class="col-md-3">
														<label>RUC: </label>
													</div>
													<div id="txtCampoRuc" class="col-md-3">
														<input id="numRucExpedienteModal_f" name="numeroRuc" type="text" class="form-control" disabled="disabled"/>
													</div>
													<div id="txtCampoRazonSocial" class="col-md-6">
														<input id="razonSocialExpedienteModal_f" name="razonSocialRuc" type="text" class="form-control" disabled="disabled"/>
													</div>
												</div>
												<div class="row content-box">&nbsp;</div>
												<div class="row content-box">
													<div class="col-md-3">
														<label>Proceso:</label>
													</div>
													<div id="txtCampo4" class="col-md-3">
														<input id="desProcesoExpedienteModal_f" name="descripcionProceso" type="text" class="form-control" disabled="disabled"/>
													</div>
													<div class="col-md-3">
														<label>Tipo de expediente:</label>
													</div>
													<div id="txtCampo5" class="col-md-3">
														<input id="desExpedienteExpedienteModal_f" name="descripcionExpediente" type="text" class="form-control" disabled="disabled"/>
													</div>													
												</div>
												<div class="row content-box">&nbsp;</div>												
												<div class="row content-box">
												<div class="col-md-3">
														<label>N&deg; Expediente:</label>
													</div>
													<div id="txtCampo2" class="col-md-3">
														<input id="numExpeOrigenExpedienteModal_f" name="numeroExpeOrigen" type="text" class="form-control" disabled="disabled"/>
													</div>
													<div class="col-md-3">
														<label>Fecha del expediente:</label>
													</div>
													<div id="txtCampo8" class="col-md-3">														
														<input id="fechaVirtualExpedienteModal_f" name="fechaExpedienteVirtual" type="text" class="form-control" disabled="disabled"/>
													</div>
												</div>
												<div class="row content-box">&nbsp;</div>
												<div class="row content-box">													
													<div class="col-md-3">
														<label>Estado del Expediente:</label>
													</div>
													<div id="txtCampo3" class="col-md-3">
														<input id="estExpedienteExpedienteModal_f" name="estadoExpediente" type="text" class="form-control" disabled="disabled"/>
													</div>
												</div>
											</div>
											</div>
											<!-- FIN[LLRB 15012020] -->
										</fieldset>
										<fieldset class="scheduler-border" style="margin : 15px 15px !important">
											<legend class="scheduler-border">Listado de Estados - Etapas del Expediente Origen</legend>
												<div class="panel-body">
													<div class="tab-content">
													<div id="documentos" class="tab-pane fade in active">
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
													<button type="button" class="btn btn-primary" id= "exportarTrazabilidad" onclick="exportarExpedienteModal()">Exportar a Excel</button>
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
		<script type="text/javascript">
 
	var listadoProcesos = ${listadoProcesos};
	var listadoDependencias = ${listadoDependencias};
	var listadoTipoFecha = ${listadoTipoFecha};
	var listadoNumeroTipoExpediente = ${listadoNumeroTipoExpediente};
	var listadoEstadosExpVirtual = ${listadoEstadosExpVirtual};
	var codDepUsuario = ${codDepUsuario};
	
	var excepciones = ${excepciones};
	var titulos =  ${titulos};
	var numExpediente="";
	var flagExcepcion="";
	var retorno=false;
	var realizarBusqueda = false;	
	//Inicio jquispe 16/06/2016
	var hoverBuscar=false;
	//Fin jquispe 16/06/2016
	
	$(function () {
		
		construirTablaExpediente( [] );
		construirTablaExpedienteModal( [] );
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
		
		$('#divFechaDesde').on("dp.hide", function(e) {
			 $('#divFechaHasta').data("DateTimePicker").setMinDate(e.date);
			 if($('#txtfechaDesde').val() != ""){
					habilitarValidacionFecha(true);
					$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecDesde');
					$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecHasta');
					$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'codTipBusquedaFecha');
					resetearFormularioConEsp(event);
			 }
		});
		$('#divFechaHasta').datetimepicker({
            format: 'DD/MM/YYYY',
			useCurrent: false,
			language: 'Es',
			autoClose: true,
			forceParse: true,
			pickTime: false
        });

		$("#divFechaHasta").on("dp.hide", function (e) {
			$('#divFechaDesde').data("DateTimePicker").setMaxDate(e.date);
			if($('#txtfechaHasta').val() != ""){
				habilitarValidacionFecha(true);
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecHasta');
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecDesde');
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'codTipBusquedaFecha');
				resetearFormularioConEsp(event);
			}
		});
		
		$( "#txtNumeroExpediente" ).blur(function() {
			retorno=true;
			$('#frmFiltroBusquedaEspecifica').bootstrapValidator('revalidateField', 'numExp');
		});
		
		$( "#txtRuc" ).blur(function() {
			retorno=true;
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'numRuc');
			//Inicio jquispe 16/06/2016
			if(hoverBuscar){				
				revalidarFormulario();				
			}
			//Fin jquispe 16/06/2016
		});
		
		//Inicio jquispe 16/06/2016
		$('#btnCancelar').mouseover(function(){
			hoverBuscar=true;
		});

		$('#btnCancelar').mouseout(function(){
			hoverBuscar=false;
		});
				
		$('#txtRuc').on('paste', function () {
			limpiarBusquedaEspecifica();		
		});
		//Fin jquispe 16/06/2016
		
		makeInputMask( '#txtRuc', "(9){1,11}", 11, '' );
		//makeInputMask( '#txtNumeroExpediente', "(9){1,14}", 14, '' );
				
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
										return false;
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
										// Inicio [jquispe 16/06/2016] Valida que la longitud del numero de expediente de origen no sea mayor a 17.
										if(numExpediente.length > 17){
										// Fin [jquispe 16/06//2016]
											$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateMessage', 'numExp', 'callback', 'Ingrese un N&uacute;mero Expediente V&aacute;lido.');
											$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateOption', 'numExp', 'callback', 'Ingrese un N&uacute;mero Expediente V&aacute;lido.');
											retorno=false;
											return  false;
											realizarBusqueda=false;
										}
									}									
								}
								retorno=false;
								realizarBusqueda = true;
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
            if(realizarBusqueda){
            	consultarExpedientes();	
            }
            
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
		
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator({		
			excluded: [':disabled'],
			fields: {
				codProceso: {
                    validators: {
                        notEmpty: {
                            message: 'Seleccione un Proceso.'
                        }
                    }
                },
				codTipexp: {
                    validators: {
                        notEmpty: {
                            message: 'Seleccione un Tipo de Expediente.'
                        }
                    }
                },
				codTipBusquedaFecha: {
                    validators: {
                        notEmpty: {
                            message: 'Seleccione un Tipo de Fecha.'
                        }
                    }
                },
                fecDesde: {
                    validators: {
                        notEmpty: {
                            message: 'Ingrese Fecha Desde.'
                        },
						date: {
							format: 'DD/MM/YYYY',
							message: 'El formato de la fecha es incorrecto, solo formato: dd/mm/aaaa.'
						},
						callback: {
							message: excepciones.excepcion02,
							callback:   function (value, validator, $field) {
								var fechaDesde = document.getElementById("txtfechaDesde").value;
								var fechaHasta = document.getElementById("txtfechaHasta").value;
								if(fechaDesde!="" && fechaHasta!=""){
									return validacionFechas();
								}
								return true;
							}
						}
                    }
                },
                fecHasta: {
                    validators: {
                        notEmpty: {
                            message: 'Ingrese Fecha Hasta.'
                        },
						date: {
							format: 'DD/MM/YYYY',
							message: 'El formato de la fecha es incorrecto, solo formato: dd/mm/aaaa.'
						},
						callback: {
							message: excepciones.excepcion02,
							callback:   function (value, validator, $field) {
								var fechaDesde = document.getElementById("txtfechaDesde").value;
								var fechaHasta = document.getElementById("txtfechaHasta").value;
								if(fechaDesde!="" && fechaHasta!=""){
									return validacionFechas();
								}
								return true;
							}
						}						
                    }

                },
				numRuc: {
					validators: {
						callback: {
                            message: '',
                            callback: function (value, validator, $field) {
                            	if(retorno){
								$('#txtRazonSocial').val("");
								var numRuc = $('#txtRuc').val();
								// Inicio [jquispe 16/06/2016] Permite la validacion de algunos campos de la Consulta Avanzada.
								habilitarValidacionConsultaAvanzada(true);
								// Fin [jquispe 16/06/2016]
								if(numRuc!=""){
									if(numRuc.length < 11){
										$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateMessage', 'numRuc', 'callback', 'Debe ingresar 11 d&iacute;gitos para el RUC.');
										$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateOption', 'numRuc', 'callback', 'Debe ingresar 11 d&iacute;gitos para el RUC.');
										return false;
									}
									if(!valruc(numRuc)){
										$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateMessage', 'numRuc', 'callback', 'Ingrese un n&uacute;mero de RUC V&aacute;lido.');
										$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateOption', 'numRuc', 'callback', 'Ingrese un n&uacute;mero de RUC V&aacute;lido.');
										retorno = false;
										return false;
									}else{
										validarContribuyente();
										var razonSocial = $('#txtRazonSocial').val();
										if(razonSocial==""){
											$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateMessage', 'numRuc', 'callback', flagExcepcion);
											$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateOption', 'numRuc', 'callback', flagExcepcion);
											retorno = false;
											return false;
										}else{
										   // Inicio [jquispe 16/06/2016] No permite la validacion de algunos campos de la Consulta Avanzada.
											habilitarValidacionConsultaAvanzada(false);
										   // Fin [jquispe 16/06/2016]
										}
										
									}
								}
								return true;
							}
							return true;
							}
						
                    },
					regexp: {
						regexp: /^[0-9/]+$/,
						message: 'Debe ingresar solo valores num&eacute;ricos para el RUC.'
					}
					
				}
			}
		}
		}).on('success.form.bv', function(e) {
            e.preventDefault();
            consultarExpedientes();
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
		
			
		$('#txtNumeroExpediente').prop('disabled', true);
		$('#btnConsulta').prop('disabled', true);

		var $element = $('#selTipoBusquedaFecha');
		$.each(listadoTipoFecha, function(i, dato) {
			var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
			$element.append($option);
		});
		
		//expediente
		var $element = $('#selTipoBusquedaExpediente');
		$.each(listadoNumeroTipoExpediente, function(i, dato) {
			var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
			$element.append($option);
		});
		
		var $element = $('#selDependencia');
				
		$.each(listadoDependencias, function(i, dato) {		
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
		
		var $element = $('#selCodigoEstado');
		
		$.each(listadoEstadosExpVirtual, function(i, dato) {		
			var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
			$element.append($option);
		});
		
		var $element = $('#selCodigoProceso');
		$.each(listadoProcesos, function(i, dato) {
			var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
			$element.append($option);
		});
		
		//Inicio [jquispe 12/07/2016] Comentado para el envio POST.
		//$.ajaxSetup({ scriptCharset: "UTF-8" , contentType: "application/json; charset=utf-8"});
		//Fin [jquispe 12/07/2016]
		
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
				{data :	"correlativo","defaultContent":""},
				{data :	"desTipdoc","defaultContent":""},
				{data :	"numDoc","defaultContent":"" },
				{data :	"desOrigenExpe","defaultContent":"" },
				{data :	"desEstadoExpe","defaultContent":"" },
				{data :	"fechaDocumento","defaultContent":"" },
				{data :	"responsableExpediente","defaultContent":"" },
				
				{data :	"desSumilla","defaultContent":"" },
				{data :	"desMotCierre","defaultContent":""}
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
				var a = $('#tableDocumento').width()
				$("#tableDocumento_wrapper").css("min-width", a);	
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
	
	//Inicio [jquispe 16/06/2016] Permite la validacion de algunos campos de la Consulta Avanzada.
	function habilitarValidacionConsultaAvanzada(flagHabilitarValidacion){
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'codProceso', flagHabilitarValidacion);
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'codTipexp', flagHabilitarValidacion);
		
		if(!flagHabilitarValidacion){			
			if($('#selTipoBusquedaFecha').val() != "" || $('#txtfechaDesde').val() != "" || $('#txtfechaHasta').val() != ""){
				flagHabilitarValidacion = true;
			}
		}
		
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'codTipBusquedaFecha', flagHabilitarValidacion);
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'fecDesde', flagHabilitarValidacion);
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'fecHasta', flagHabilitarValidacion);
	}
	//Fin [jquispe 16/06/2016]
	
	//Inicio [jquispe 16/06/2016] Permite la validacion de la busqueda por fecha.
	function habilitarValidacionFecha(flagHabilitarValidacion){
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'codTipBusquedaFecha', flagHabilitarValidacion);
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'fecDesde', flagHabilitarValidacion);
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'fecHasta', flagHabilitarValidacion);
	}
	//Fin [jquispe 16/06/2016]
	
	 function limpiar(){
		resetearFormularioConEsp(event);
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('resetForm', true);
		//Inicio [jquispe 16/06/2016] Permite la validacion de algunos campos de la Consulta Avanzada.
		habilitarValidacionConsultaAvanzada(true);		
		limpiarBusquedaEspecifica();
		//Fin [jquispe 16/06/2016]
		$('#selNumeroTipoExpediente').val("");		
		$('#selCodigoProceso').val("");
		$('#selCodigoTipoExpendiente').val("");	
		$('#txtfechaDesde').val("");
		$('#txtfechaHasta').val("");
		$('#txtRuc').val("");
		$('#txtRazonSocial').val("");
		$("#txtfechaDesde").attr('disabled', false);
		$("#txtfechaHasta").attr('disabled', false);
		$('#tblExpedientes').dataTable().fnClearTable();
		document.getElementById("abiertos").innerHTML="";
		document.getElementById("cerrados").innerHTML="";
		document.getElementById("total").innerHTML="";
		$('#exportar').attr('disabled', true);
		
		//Inicio [jquispe 16/06/2016] Limpia la fecha.
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
        //Fin [jquispe 16/06/2016]
        
		$( window ).off('resize');
		

	}
	
	//Inicio [jquispe 16/06/2016] Limpia la busqueda especifica.
	function limpiarBusquedaEspecifica(){
	  $('#frmFiltroBusquedaEspecifica').bootstrapValidator('resetForm', true);
	  $('#selTipoBusquedaExpediente').val("");
	  $('#txtNumeroExpediente').val("");
	  $("#txtNumeroExpediente").prop('disabled', true);
	}
	//Fin [jquispe 16/06/2016]
	
	
	function inicializarProcesos() { 
	}
    
	function inicializarDependencias() {
	}
	
	function cargarTiposExpedientes() {
	resetearFormularioConEsp(event);
	var codProceso = $('#selCodigoProceso').val();
	
	$('#selCodigoTipoExpendiente').empty();
	
	$('#selCodigoTipoExpendiente').append($('<option>', {
		value: '',
		text: 'Seleccione'
	}));
	
	var dataEnvio = new Object();
	
	if (codProceso == "") {
		
		$('#selCodigoTipoExpendiente').val("");
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'codTipexp');
		
	} else {
		
			dataEnvio.codProceso = codProceso;
			
			$.ajax({
				url: '${pageContext.request.contextPath}/conSegControl.htm?action=cargarListaTiposExpediente',
				data: "&codProceso="+codProceso,
				cache: false,
				async: true,
				type: 'POST',
				//contentType : "application/json; charset=utf-8",//[jquispe 12/07/2016] Comentado para el envio POST.
				dataType: 'json',
				success: function(response) {
					
					var listadoTiposExpendientes = response.listadoTiposExpendientes;
					var $element = $('#selCodigoTipoExpendiente');
					
					$.each(listadoTiposExpendientes, function(i, dato) {
						
						var $option = $("<option/>").attr("value", dato.codTipoExpediente).text(dato.desTipoExpediente);
						$element.append($option);
						
					});
					
				},
				error: function(err) {
					document.write(err.responseText);
				}
			});
			
		}
		
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
	
	function construirTablaExpediente(dataGrilla) {
    	var table = $('#tblExpedientes').DataTable({

            data: dataGrilla,
            ordering			:	true,
			bScrollAutoCss		:	true,
			bStateSave			:	false,
			bAutoWidth			:	false,
			bScrollCollapse		:	true,
			searching			:	false,
			paging				:	true,
			pagingType			:   "full_numbers",
			iDisplayLength		:	5,//jquispe 05/07/2016 actualizado a 5
			//responsive			:	true,
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
			columns: [	
			          	{data :	"numOrden",sClass: 'centered alinearCentrado'},
			          	{data :	"numExpedienteOrigen", render : function(data, type, row){
							 var aux = data;
							 return $('<a>')
							 	.css('text-decoration','underline')
				                .attr({	onclick :'cargaModalTrazabilidad("'+row.numExpedienteVirtual+'")'})
				                .text(data)
				                .wrap('<div></div>')
				                .parent()
				                .html();
				        }, sClass: 'centered alinearCentrado'},
				        {data : "numExpedienteVirtual", sClass: 'centered alinearCentrado',"defaultContent":""},
						{data : "nombreResponsable", sClass: 'centered alinearCentrado',"defaultContent":""},
						{data : "numRuc", sClass: 'centered alinearCentrado',"defaultContent":""},
						// Inicio [jquispe 16/06/2016]Nueva columna Razon Social.
						{data : "desRazonSocial",sClass:'centered alinearCentrado',"defaultContent":""},
						// Fin [jquispe 16/06/2016]
						{data : "desProceso", sClass: 'centered alinearCentrado',"defaultContent":""},
						{data :	"desTipoExpediente", sClass: 'centered alinearCentrado',"defaultContent":"" },
						{data :	"desEstado", sClass: 'centered alinearCentrado',"defaultContent":""},
						{data : "desEstadoCierre", sClass: 'centered alinearCentrado',"defaultContent":""},
						{data :	"fecRegistro", sClass: 'centered alinearCentrado',"defaultContent":""},
						{data : "fechaDocumentoOrigen", sClass: 'centered alinearCentrado',"defaultContent":""},
						//inicio Jeffio 
						{data : "strFecNotifOrigen", sClass: 'centered alinearCentrado',"defaultContent":"" },
						{data : "desForNotifOrigen", sClass: 'centered alinearCentrado',"defaultContent":"" },
						//fin
						{data : "desOrigen", sClass: 'centered alinearCentrado',"defaultContent":""},
						{data :	"numExpedienteVirtual","defaultContent":"", render : function(data, row){
							 var aux = data;
							 return $('<a>')
							 	.css('text-decoration','underline')
				                .attr({	onclick :'cargaModal("'+aux+'")'})
				                .text("Mov. Estados")
				                .wrap('<div></div>')
				                .parent()
				                .html();
				        }, sClass: 'centered alinearCentrado'},
				        {data : "numExpedienteVirtual", sClass: 'hidden',"defaultContent":""}
			]
        });
    	//INICIO LLRB 15012020
		var table = $('#tblExpedientesFiltro').DataTable({
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
		iDisplayLength		:	5,//jquispe 05/07/2016 actualizado a 5
		//responsive			:	true,
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
		columns: [	
		          	{data :	"numOrden",sClass: 'centered alinearCentrado'},
		          	{data :	"numExpedienteOrigen", render : function(data, type, row){
						 var aux = data;
						 return $('<a>')
						 	.css('text-decoration','underline')
			                .attr({	onclick :'cargaModalTrazabilidad("'+row.numExpedienteVirtual+'")'})
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
					{data :	"desEstado", sClass: 'centered alinearCentrado',"defaultContent":""},								
					{data : "fechaDocumentoOrigen", sClass: 'centered alinearCentrado',"defaultContent":""},					
					{data : "desOrigen", sClass: 'centered alinearCentrado',"defaultContent":""},
					{data :	"numExpedienteVirtual","defaultContent":"", render : function(data, row){
						 var aux = data;
						 return $('<a>')
						 	.css('text-decoration','underline')
			                .attr({	onclick :'cargaModal("'+aux+'")'})
			                .text("Mov. Estados")
			                .wrap('<div></div>')
			                .parent()
			                .html();
			        }, sClass: 'centered alinearCentrado'},
			        {data : "numExpedienteVirtual", sClass: 'hidden',"defaultContent":""}
		]
    });
	// FIN LLRB 15012020
    }
	
	function guardarNumExpediente(numExpediente){
		numExpedienteConsultar = numExpediente;
		
	}
	function consultarObservacionesExpedienteVirtual(){
		var  url = '${pageContext.request.contextPath}/conSegControl.htm?action=cargarObservacionesPorExpVirtual&numExpedienteVirtual='+numExpedienteConsultar;
		$(location).prop( 'href', url);
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
	
	function validarFormulario(){
		var fechaDesde = $('#txtfechaDesde').val();
		var fechaHasta = $('#txtfechaHasta').val();
		if(fechaDesde != "" || fechaHasta!= ""){
			
		}
	}

	
	function mostrarID(id){
		alert(id);
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
	
	function validarFormulario(){
		var fechaDesde = $('#txtfechaDesde').val();
		var fechaHasta = $('#txtfechaHasta').val();
		if(fechaDesde != "" || fechaHasta!= ""){
			
		}
	}
	//mostrar Pagina de Error
	function mostrarPagError() {
		
		var formURL = '${pageContext.request.contextPath}/error.htm?action=cargarPaginaError';
		document.forms.frmFiltroBusquedaEspecifica.action = formURL;
		document.forms.frmFiltroBusquedaEspecifica.method = "post";
		document.forms.frmFiltroBusquedaEspecifica.submit();
		
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
		var tipoBusqueda = $('#selTipoBusquedaExpediente').val();
		//Inicio jquispe 15/06/2016
		if($('#txtRuc').val()==''){
			habilitarValidacionConsultaAvanzada(true);
		}else{
			habilitarValidacionConsultaAvanzada(false);
		}
		//Fin jquispe 15/06/2016
		if(tipoBusqueda!=""){
			$('#frmFiltroBusquedaEspecifica').submit();
		}else{
			$('#frmFiltroBusquedaAvanzada').submit();
		}
	}		
	
// 	function resetearFormularioConEsp(event){
// 		$('#frmFiltroBusquedaEspecifica').bootstrapValidator('resetForm', true);
// 		$('#selTipoBusquedaExpediente').val("");
// 		if($("#selCodigoProceso").val() == "") $('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'codTipexp');
// 		$('#txtNumeroExpediente').prop('disabled', true);
// 	}
	
	function resetearFormularioConEsp(event){
		$('#frmFiltroBusquedaEspecifica').bootstrapValidator('resetForm', true);
		$('#selTipoBusquedaExpediente').val("");
		
		var current = event.target || event.srcElement;
		
		if(current.id != "selDependencia"){
			if($("#selCodigoProceso").val() == "" && current.id != "txtRuc") $('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'codTipexp');
		}		
		
		$('#txtNumeroExpediente').prop('disabled', true);
		
		// Inicio [jquispe 16/06/2016] Valida las fechas.
		if(current.id == "selTipoBusquedaFecha"){
			if($("#selTipoBusquedaFecha").val() == ""){			
				habilitarValidacionFecha(false);
				$('#txtfechaDesde').val("");
				$('#txtfechaHasta').val("");
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'codTipexp',false);
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'codProceso',false);
			}else{		
				habilitarValidacionFecha(true);
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecDesde');
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecHasta');
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'codTipBusquedaFecha');
				if($("#selCodigoProceso").val() == "" && $('#txtRuc').val()==""){
					$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'codTipexp',true);
					$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'codProceso',true);
					$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'codProceso');
					$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'codTipexp');
				}	
			}
		}
		// Fin [jquispe 16/06/2016]
	}
	
	function validarContribuyente(){

		var numRuc = $('#txtRuc').val();
		
		$.ajax({
			
			url : '${pageContext.request.contextPath}/conSegControl.htm?action=validarContribuyente',
			type : 'POST',
			async : false,
			dataType : "json",
			data : '&numRuc='+numRuc,
			//contentType : "application/json",//[jquispe 12/07/2016] Comentado para el envio POST.
			mimeType : "application/json",
			success : function(response) {
				
				var msjError = response.msjError;
				if(msjError!="" && msjError!=undefined){
					flagExcepcion=msjError;
				}				
				
				if(response.razonSocial!="" && response.razonSocial!=undefined){
					$('#txtRazonSocial').val(response.razonSocial);
				}
				
			},
			error : function(response) {
				
				mostrarPagError();
			}
			
		});
	}
	

	function exportarExcel(){
		var dataExp = $('#tblExpedientes').dataTable().fnGetData();
		var dataExpF = $('#tblExpedientesFiltro').dataTable().fnGetData();
		if(dataExp.length > 0){
			//AQ-FSW
			dataExp = caracterHtml(false,dataExp);	
			var listaCadena = JSON.stringify(dataExp);
		    var formURL = 'conSegControl.htm?action=exportarExcelExpedientes';
			document.forms.formPrincipalGeneral.action = formURL;
			document.forms.formPrincipalGeneral.method = "POST";
			$('#campoHiddenExp').val('');
			$('#campoHiddenExp').val(listaCadena);
			$('html').block({message: '<h1>Procesando</h1>'});
			document.forms.formPrincipalGeneral.submit();
			$('html').unblock();
		}//INICIO LLRB 15012020
		else{
			dataExpF = caracterHtml(false,dataExpF);
			var listaCadena = JSON.stringify(dataExpF);
		    var formURL = 'conSegControl.htm?action=exportarExcelExpedientesFisca';
			document.forms.formPrincipalGeneral.action = formURL;
			document.forms.formPrincipalGeneral.method = "POST";
			$('#campoHiddenExp').val('');
			$('#campoHiddenExp').val(listaCadena);
			//$('html').block({message: '<h1>Procesando</h1>'});
			document.forms.formPrincipalGeneral.submit();
			//$('html').unblock();
		}
		
		//FIN LLRB 15012020
	}
	
	function consultarExpedientes(){
		
		listaExpedientes = [];
		var cantExpAbiertos=0;
		var cantExpCerrados=0;
		var cantTotalExp=0;
		 
		$.ajax({
			
			url : '${pageContext.request.contextPath}/conSegControl.htm?action=obtenerSegExpedientesVirtuales',
			type : 'POST',
			async : true,
			dataType : "json",
			data : $('#frmFiltroBusquedaEspecifica').serialize()+'&'+$('#frmFiltroBusquedaAvanzada').serialize(),
			//contentType : "application/json",//[jquispe 12/07/2016] Comentado para el envio POST.
			mimeType : "application/json",
			//timeout : 5000,
			success : function(response) {
				
				var msjError = response.msjError;
				if(msjError!="" && msjError!=undefined){
					$('#tblExpedientes').dataTable().fnClearTable();
					$('#tblExpedientes').dataTable().fnDraw();
					$('#tblExpedientesFiltro').dataTable().fnClearTable();
					$('#tblExpedientesFiltro').dataTable().fnDraw();
					mostrarDlgInfo(titulos.tituloDefecto, msjError);
					return;
				}
				
				listaExpedientes = response.listaExpedientesVirtuales;
				var codTipoExp = response.codTipoExp;	

				if (listaExpedientes.length > 0) {				
					//INICIO LLRB 15012020
					if(codTipoExp== 430 ||codTipoExp== 431 ){	
						$('#documentosFisca').show();
						$('#documentos').hide();
						listaExpedientes = caracterHtml(true,listaExpedientes);					
						$('#tblExpedientesFiltro').dataTable().fnClearTable();
						$('#tblExpedientesFiltro').dataTable().fnAddData(listaExpedientes); //carga registros en grilla					
						$('#tblExpedientes').dataTable().fnDraw();	
						document.getElementById("abiertos").innerHTML=response.cantExpAbiertos;
						document.getElementById("cerrados").innerHTML=response.cantExpCerrados;
						document.getElementById("total").innerHTML=response.cantTotalExp;
						$('#exportar').attr('disabled', false);
						
					}else{
						$('#documentos').show();
						$('#documentosFisca').hide();
						listaExpedientes = caracterHtml(true,listaExpedientes);		
						$('#tblExpedientes').dataTable().fnClearTable();
						$('#tblExpedientes').dataTable().fnAddData(listaExpedientes);
						$('#tblExpedientes').dataTable().fnDraw();	
						document.getElementById("abiertos").innerHTML=response.cantExpAbiertos;
						document.getElementById("cerrados").innerHTML=response.cantExpCerrados;
						document.getElementById("total").innerHTML=response.cantTotalExp;
						$('#exportar').attr('disabled', false);
					
					}
					//FIN LLRB 15012020
				} else {
					
					$('#tblExpedientes').dataTable().fnClearTable();
					$('#tblExpedientes').dataTable().fnDraw();
					mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion05);
					$('#exportar').attr('disabled', true);
					
				}
			
			},
			error : function(response) {
				
					$('#tblExpedientes').dataTable().fnClearTable();
					$('#tblExpedientes').dataTable().fnDraw();
				mostrarPagError();
				
			}
			
		});
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
			// Fin [jquispe 27/05/2016]
			$('#frmFiltroBusquedaEspecifica').bootstrapValidator('revalidateField', 'numExp');
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('resetForm', true);
			$("#txtNumeroExpediente").focus();
			// Inicio [jquispe 16/06/2016] Permite la validacion de algunos campos de la Consulta Avanzada.
			habilitarValidacionConsultaAvanzada(true);
			// Fin [jquispe 16/06/2016]
		}else if(codProceso=="2"){
			$('#txtNumeroExpediente').prop('disabled', false);
			$('#txtNumeroExpediente').val("");
			$("#txtNumeroExpediente").attr('maxlength', 14);			
			$('#frmFiltroBusquedaEspecifica').bootstrapValidator('revalidateField', 'numExp');
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('resetForm', true);
			$("#txtNumeroExpediente").focus();
			// Inicio [jquispe 27/05/2016] Permite la validacion de algunos campos de la Consulta Avanzada.
			habilitarValidacionConsultaAvanzada(true);
			// Fin [jquispe 27/05/2016]
		}
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

	function cargaModal(numExpedienteVirtual){
		var dataEnvio = new Object();
		$.ajax({			
			url : '${pageContext.request.contextPath}/conSegControl.htm?action=cargarDetalleSeguimiento',
			type : 'POST',
			async : true,
			dataType : "json",
			data : "&numExpedienteVirtual="+numExpedienteVirtual,
			//contentType : "application/json",//[jquispe 12/07/2016] Comentado para el envio POST.
			mimeType : "application/json",
			//timeout : 5000,
			success : function(response) {
				
				var lstDocExp = JSON.parse(response.lstDocExp);
				var datosExpedientes = JSON.parse(response.datosExpedientes);
				var fechaOrigenDoc = JSON.parse(response.fechaOrigenDoc);
				var fechaRegistro = JSON.parse(response.fechaRegistro);
				var razonSocial=JSON.parse(response.razonSocial);
				
				if (lstDocExp.length > 0) {
					$('#tableDocumento').dataTable().fnClearTable();
					$('#tableDocumento').dataTable().fnAddData(lstDocExp);
				} else {					
					$('#tableDocumento').dataTable().fnClearTable();
					$('#tableDocumento').dataTable().fnDraw();
				}
				if((datosExpedientes.codTipoExpediente == 430 || datosExpedientes.codTipoExpediente == 431) ) {
					$('#DetalleSeguimientoFisca').show();
					$('#DetalleSeguimiento').hide();
					
					$('#numRuc_f').attr("value", datosExpedientes.numRuc);
					$('#razonSocial_f').attr("value", razonSocial);
					$('#numExpeOrigen_f').attr("value", datosExpedientes.numExpedienteOrigen);				
					$('#estExpediente_f').attr("value", datosExpedientes.desEstado);
					$('#desProceso_f').attr("value", datosExpedientes.desProceso);
					$('#desExpediente_f').attr("value", datosExpedientes.desTipoExpediente);
					$('#desOrigen_f').attr("value", datosExpedientes.desOrigen);
					$('#fechaOrigen_f').attr("value", fechaOrigenDoc);
					$('#fechaVirtual_f').attr("value", fechaRegistro);
				}else{
					$('#DetalleSeguimiento').show();
					$('#DetalleSeguimientoFisca').hide();
					
					$('#numRuc').attr("value", datosExpedientes.numRuc);
					$('#razonSocial').attr("value", razonSocial);
					$('#numExpeOrigen').attr("value", datosExpedientes.numExpedienteOrigen);
					$('#numExpeVirtual').attr("value", datosExpedientes.numExpedienteVirtual);
					$('#estExpediente').attr("value", datosExpedientes.desEstado);
					$('#desProceso').attr("value", datosExpedientes.desProceso);
					$('#desExpediente').attr("value", datosExpedientes.desTipoExpediente);
					$('#desOrigen').attr("value", datosExpedientes.desOrigen);
					$('#fechaOrigen').attr("value", fechaOrigenDoc);
					$('#fechaVirtual').attr("value", fechaRegistro);
				}
				
				var titulo = "Detalle de Seguimiento de Estados - N&deg; Expediente Virtual " + datosExpedientes.numExpedienteVirtual;
				mostrarModal(titulo);
			
				
			},
			error : function(response) {
				
				mostrarPagError();
				
			}
		
		});
		
	} 
	function cargaModalTrazabilidad(numExpedienteVirtual){
		var dataEnvio = new Object();
		$.ajax({
			url : '${pageContext.request.contextPath}/conSegControl.htm?action=cargarTrazabilidadExpOrigen',
			type : 'POST',
			async : true,
			dataType : "json",
			data : "&numExpedienteVirtual="+numExpedienteVirtual,
			cache: false,
            async: true,
			//contentType : "application/json",//[jquispe 12/07/2016] Comentado para el envio POST.
			mimeType : "application/json",
			//timeout : 5000,
			success : function(response) {
				var lstTrazExp = JSON.parse(response.lstTrazExp);
				var datosExpedientes = JSON.parse(response.datosExpedientes);
				var fechaOrigenDoc = JSON.parse(response.fechaOrigenDoc);
				var fechaRegistro = JSON.parse(response.fechaRegistro);				
				var razonSocial=JSON.parse(response.razonSocial);
				//Inicio jquispe 01/07/2016 Estado del expediente origen del ultimo lista.
				var descripcionEstado=JSON.parse(response.descripcionEstado);
				//Fin jquispe 01/07/2016
				if (lstTrazExp.length > 0) {
					$('#tableExpediente').dataTable().fnClearTable();
					$('#tableExpediente').dataTable().fnAddData(lstTrazExp); //carga registros en grilla
					$('#exportarTrazabilidad').attr('disabled', false);
				}else{
					$('#tableExpediente').dataTable().fnClearTable();
					$('#tableExpediente').dataTable().fnDraw();
					$('#exportarTrazabilidad').attr('disabled', true);
				}
				//INICIO LLRB 15012020
				if((datosExpedientes.codTipoExpediente == 430 || datosExpedientes.codTipoExpediente == 431) ) {
					$('#TrazaEstadoFisca').show();
					$('#TrazaEstado').hide();
					
					$('#numRucExpedienteModal_f').attr("value", datosExpedientes.numRuc);
					$('#razonSocialExpedienteModal_f').attr("value", razonSocial);
					$('#numExpeOrigenExpedienteModal_f').attr("value", datosExpedientes.numExpedienteOrigen);								
					$('#hiddenNumExpVirtualExpedienteModal').attr("value", datosExpedientes.numExpedienteVirtual);					
					$('#estExpedienteExpedienteModal_f').attr("value", descripcionEstado);					
					$('#desProcesoExpedienteModal_f').attr("value", datosExpedientes.desProceso);
					$('#desExpedienteExpedienteModal_f').attr("value", datosExpedientes.desTipoExpediente);
					$('#hiddenFechaOrigenExpedienteModal_f').val(fechaOrigenDoc);
					$('#fechaVirtualExpedienteModal_f').attr("value", fechaOrigenDoc);

				}else{
					$('#TrazaEstado').show();
					$('#TrazaEstadoFisca').hide();
	
					$('#numRucExpedienteModal').attr("value", datosExpedientes.numRuc);
					$('#razonSocialExpedienteModal').attr("value", razonSocial);
					$('#numExpeOrigenExpedienteModal').attr("value", datosExpedientes.numExpedienteOrigen);
					$('#numExpeVirtualExpedienteModal').attr("value", datosExpedientes.numExpedienteVirtual);
					//Inicio jquispe 02/07/2016 Asigna el valor a la variable para mostrar el expediente virtual en el reporte.
					$('#hiddenNumExpVirtualExpedienteModal').attr("value", datosExpedientes.numExpedienteVirtual);
					//Fin jquispe 02/07/2016
					//Inicio jquispe 01/07/2016 Estado del expediente origen del ultimo listado de Estados y Etapas.
					//$('#estExpedienteExpedienteModal').attr("value", datosExpedientes.desEstado);
					$('#estExpedienteExpedienteModal').attr("value", descripcionEstado);
					//Fin jquispe 01/07/2016
					
					$('#desProcesoExpedienteModal').attr("value", datosExpedientes.desProceso);
					$('#desExpedienteExpedienteModal').attr("value", datosExpedientes.desTipoExpediente);
					$('#hiddenFechaOrigenExpedienteModal').val(fechaOrigenDoc);
					$('#fechaVirtualExpedienteModal').attr("value", fechaOrigenDoc);
				}
				//FIN LLRB 15012020
					
					var titulo = "Trazabilidad de los estados y etapas del Expediente Or&iacute;gen";
					mostrarModalTrazabilidad(titulo);				
			},
			error : function(response) {
				
				mostrarPagError();
				
			}
		
		});
		
	}
	
	function exportarExpedienteModal(){
		var dataExp = $('#tableExpediente').dataTable().fnGetData();
			if(dataExp.length > 0){
				var listaCadena = JSON.stringify(dataExp);
				var formURL = 'conSegControl.htm?action=exportarExcelTrazabilidad';
				document.forms.formExpedienteModal.action = formURL;
				document.forms.formExpedienteModal.method = "POST";
				//INICIO LLRB 
				if(($('#numRucExpedienteModal').val())!="" ){
					var numRuc = $('#numRucExpedienteModal').val();
					var razonSocial = $('#razonSocialExpedienteModal').val();
					var numExpeOrigen = $('#numExpeOrigenExpedienteModal').val();
					var numExpeVirtual = $('#numExpeVirtualExpedienteModal').val();
					var estExpediente = $('#estExpedienteExpedienteModal').val();
					var desProceso = $('#desProcesoExpedienteModal').val();
					var desExpediente = $('#desExpedienteExpedienteModal').val();				
					var fechaVirtual = $('#fechaVirtualExpedienteModal').val();
					var desOrigen = $('#desOrigenDocumentoModal').val();
				}else{
					var numRuc = $('#numRucExpedienteModal_f').val();
					var razonSocial = $('#razonSocialExpedienteModal_f').val();
					var numExpeOrigen = $('#numExpeOrigenExpedienteModal_f').val();
					var numExpeVirtual = $('#numExpeVirtualExpedienteModal_f').val();
					var estExpediente = $('#estExpedienteExpedienteModal_f').val();
					var desProceso = $('#desProcesoExpedienteModal_f').val();
					var desExpediente = $('#desExpedienteExpedienteModal_f').val();				
					var fechaVirtual = $('#fechaVirtualExpedienteModal_f').val();
					var desOrigen = $('#desOrigenDocumentoModal_f').val();
				}
				//FIN LLRB 
			
				/*Obtener Paramtros fin*/
				/*limpiar campos hdn Inicio*/
				$('#hiddenListaExpExpedienteModal').val('');
				$('#hiddenNumRucExpedienteModal').val('');
				$('#hiddenRazonSocialExpedienteModal').val('');
				$('#hiddenNumExpOrigenExpedienteModal').val('');
				//Inicio jquispe 01/07/2016 El valor de la variable es asignado al abrir el modal.
				//$('#hiddenNumExpVirtualExpedienteModal').val('');
				//Fin jquispe 01/07/2016
				$('#hiddenEstExpedienteExpedienteModal').val('');
				$('#hiddenTipoProcesoExpedienteModal').val('');
				$('#hiddenTipoExpedienteExpedienteModal').val('');
				$('#hiddenDescOrigenExpedienteModal').val('');
				$('#hiddenFechaGeneracionExpedienteModal').val('');
				$('#hiddenFechaOrigenExpedienteModal').val('');
				/*limpiar campos hdn Inicio*/
				/*setear campos hdn Inicio*/
				$('#hiddenListaExpExpedienteModal').val(listaCadena);
				$('#hiddenNumRucExpedienteModal').val(numRuc);
				$('#hiddenRazonSocialExpedienteModal').val(razonSocial);
				$('#hiddenNumExpOrigenExpedienteModal').val(numExpeOrigen);
				//Inicio jquispe 01/07/2016 La variable numExpeVirtual se comento en la declaracion de variables.
				//$('#hiddenNumExpVirtualExpedienteModal').val(numExpeVirtual);
				//Fin jquispe 01/07/2016
				$('#hiddenEstExpedienteExpedienteModal').val(estExpediente);
				$('#hiddenTipoProcesoExpedienteModal').val(desProceso);
				$('#hiddenTipoExpedienteExpedienteModal').val(desExpediente);
				$('#hiddenDescOrigenExpedienteModal').val(desOrigen);
				$('#hiddenFechaGeneracionExpedienteModal').val(fechaVirtual);
				$('#hiddenFechaOrigenExpedienteModal').val(fechaVirtual);
				/*setear campos hdn fin*/
				$('html').block({message: '<h1>Procesando</h1>'});
				document.forms.formExpedienteModal.submit();
				$('html').unblock();
			}
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
/**/
 </script>
	</body>	
</html>
