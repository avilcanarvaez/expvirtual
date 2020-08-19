<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=EDGE" />
<meta name="viewport"
	content="initial-scale = 1.0, user-scalable = no,  width=device-width">
<title>SISTEMA INTEGRADO DE EXPEDIENTE VIRTUAL - SIEV - IU008</title>

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

  <script src="/a/js/js.js" type="text/javascript"></script>
  <script src="/a/js/bootstrap/3.2.0/js/jquery.blockUI.js" type="text/javascript"></script>
  <script src="/a/js/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
  
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
	.centered {
		text-align: center !important;
	}
	.separator {
		border: 1px solid !important;
		border-color: gray !important;
		margin-bottom: 8px !important;
		margin-top: 10px !important;
	}
	.imgLogo{
		height: 2.5em;
		margin-top: 1em;
	}
	.marginedDiv {
		border: 1px groove #ddd !important;
		border-style: solid;
		padding:10px !important;
	}
	.marginedForm {
		margin: 15px 0px !important;
	}
	.hiddenDiv {
		display: none !important;
	}
	.cboDependencia {
		width: 440px !important;
	}
	table.dataTable td.dataTables_empty {
		text-align: left !important; 
	}
	.dataTables_wrapper {
		background: Gainsboro !important;
		border: 0px solid gray !important;
		border-collapse: collapse !important;
	}			
	.dataTables_paginate {
		padding-top: 0px !important;
		width : 100% !important;
	}
	.paginate_button{		
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
	.form-control-single {
		font-size:12px !important;
		height:24px !important;
		padding-top:3px !important;
		padding-bottom:3px !important;
		padding-left:5px !important;
		padding-right:5px !important;
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
	fieldset.fsStyle {
		font-size: 11.5px !important;
		font-weight: normal;
		border: 1px solid #999999;
		padding: 4px;
		margin: 5px;
	}
	legend.legendStyle {
		padding-left: 5px;
		padding-right: 5px;
		font-size: 12px !important;
		color: #888888;
		background-color: transparent;
		font-weight: bold;
	}
	legend {
		width: auto;
		border-bottom: 0px;
	}
	.input-group-addon {
		padding-bottom:0px !important;
		padding-top:0px !important;
		padding-left: 6px !important;
		padding-right: 6px !important;
	}
	.upperText{ 
		text-transform: uppercase; 
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
/* 		background: Gainsboro !important;		 */
		background: #337ab7 !important;
		color: white;
	}			
	table.dataTable tbody tr:hover {
	background: CornflowerBlue !important; 
/* 		background: White !important; */
	}			
	table.dataTable tbody tr {
		cursor: pointer;
	}			
	table.dataTable tbody td {
		padding-bottom:3px !important;
		padding-top:3px !important;
	
		border-right: 1px solid gray !important;
		border-top: 1px solid #777
	}				
	.dataTables_wrapper .dataTables_paginate {
		float: initial !important;
		text-align: center !important;
		padding-top: 0.25em;
	}
	table.dataTable.display tbody tr:hover.selected > .sorting_1, 
	table.dataTable.display tbody tr.even:hover.selected > .sorting_1 {
		background: CornflowerBlue !important;
	}
	.hide_column{
  			display : none;
	}
	.panel-scroll {
		max-height: auto;
		min-height: 0px;
		overflow-y: auto;
		overflow-x: auto;
		border: 1px solid #337ab7;
	}
	.text-left-important{
		text-align: left !important;
	}
	/*IE8*/
	#codRegistro{ 
		text-transform: uppercase; 
	}
	.form-control {
		font-size:12px !important;
		height:auto !important;
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
	.amarillo {
		background: yellow !important;
	}
	
	.rojo {
		background: #ff1a1a !important;
		color: #ffffff !important;
	}
	.curso{ 
		width: 20px; 
		height: 20px; 
		border: 2px solid #555;
		
	} 
	.alerta{ 
		width: 20px; 
		height: 20px; 
		border: 2px solid #555;
		background: #ffff00;
	} 
	.vencido{
     width: 20px; 
     height: 20px; 
     border: 2px solid #555;
     background: #ff1a1a;
	}
	.alinearCentrado {
		vertical-align: middle !important;
	}	
	input[readonly] {
	  background-color: white !important;
	  cursor: text !important;
	}
	
</style>

</head>
<body>
</br>
	<div id="modalDetalleReq" class="container" style="width: 95%">
		<div>
			<div class="row">
				<div class="panel panel-primary">
					<div class="panel-heading align="center">
						<h3 class="panel-title" align="center">ESCRITOS ELECTR&Oacute;NICOS</h3>
					</div>
				</div>	
				<div class="panel panel-primary">
					<form class="form-horizontal" role="form" name="frmDatosDocumento" id="frmDatosDocumento">
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border" > Datos del Expediente </legend>							
							<div class="form-group">
								<div class="col-md-2"><Strong>Proceso:</Strong></div>
								<div class="col-md-3">
									<input name="txtProceso" id="txtProceso" type="text" class="form-control tamanoMaximo" value='${datosEscritoReq.desProceso}' disabled></input>
								</div>
								<div class="col-md-2"><Strong>Tipo de Expediente:</Strong></div>
								<div class="col-md-3">
									<input name="txtTipoExpediente" id="txtTipoExpediente" type="text"  class="form-control tamanoMaximo" value='${datosEscritoReq.desTipoExpediente}' disabled></input>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"><Strong>N&deg; Expediente:</Strong></div>
								<div class="col-md-3">
									<input name="txtExpedienteOrigen" id="txtExpedienteOrigen" type="text" class="form-control tamanoMaximo" value='${datosEscritoReq.numExpedienteOrigen}' disabled></input>
								</div>
								<div class="col-md-2"></div>
								<div class="col-md-3"></div>
							</div>
						</fieldset>	
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border" > Datos del Requerimiento </legend>
							<div class="form-group">
								<div class="col-md-2"><Strong>N&deg; Requerimiento:</Strong></div>
								<div class="col-md-3">
									<input name="txtNumRequerimiento" id="txtNumRequerimiento" type="text" class="form-control tamanoMaximo" value='${datosEscritoReq.numRequerimOrigen}' disabled></input>
								</div>
								<div class="col-md-2"><Strong>Fecha de Vencimiento:</Strong></div>
								<div class="col-md-3">
									<input id="txtFecVencimiento" name="txtFecVencimiento" type="text"  class="form-control tamanoMaximo" value='${datosEscritoReq.fecVencimiento}' disabled></input>
								</div>
							</div>
						</fieldset>	
				</div>	
				<div class="panel panel-primary">
					<fieldset class="scheduler-border" style="margin : 15px 15px !important">
						<legend class="scheduler-border"> Items de Requerimiento </legend>
						<div class="form-group">
							<div class="col-md-12">
								<div class="tab-content">
								<div id="accionesFiscalizacion" class="tab-pane fade in active">
								<div style="width: 100%; overflow-y: hidden; overflow-x:auto; border: 1px solid #337ab7;"> 
								<table id="tblTipoDocumentos" name="tblTipoDocumentos"  class="table display" cellspacing="0" style="width: 100%;">
									<thead>
										<tr>
											<th width="10%" class="text-center">Item</th>
											<th width="45%" class="text-center">Descripci&oacute;n de &iacute;tem</th>	
											<th width="25%" class="text-center">Cantidad de archivos adjuntados</th>
											<th width="20%" class="text-center">Adjuntar</th>
										</tr>
									</thead>
								</table>
								</div>
								</div>
								</div>
							</div>
						</div>
					</fieldset>	
					<fieldset  style="margin : 15px 15px !important">						
						<div class="form-group">
							<div class="col-md-6" align="right" id="dvSecBotones01">	
								<input type="button" class="btn btn-primary" id="btnRegresarReq" intermediateChanges="false" onClick="validarSalida()" value="Regresar"></input>
							</div>	
							<div class="col-md-6" align="left" id="dvSecBotones01">	
								<input type="button" class="btn btn-primary" id="btnGrabar" intermediateChanges="false" onClick="grabarCambios()" value="Grabar" ></input>
							</div>	
						</div>
					</fieldset>	
				</div>
				</form>
				<form id="formPrincipal" class="form-horizontal" role="form">	
					<div class="col-md-5" align="right" id="dvSecBotones02">	
						<input id="campoHiddenExp" type="hidden" name="listadoExpedientesCadena"/>
						<input id="hiddenNumItem" type="hidden" name="hiddenNumItem"/> 
						<input id="hidFlgCargaNvosDoc" type="hidden" name="hidFlgCargaNvosDoc"/>
						<input id="hidTxtNumRequerimiento" type="hidden" name="hidTxtNumRequerimiento" value='${datosEscritoReq.numRequerimiento}' ></input>
					</div>	
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
		
		<div id="modalAdjuntarArchivos" class="container" style="width: 95%">
			<div>
				<div class="row">
					<div class="panel panel-primary">
						<div class="panel-heading align="center">
							<h3 class="panel-title" align="center">ESCRITOS ELECTR&Oacute;NICOS - ADJUNTAR ARCHIVOS</h3>
						</div>
					</div>	
					<div class="panel panel-primary">
						<form id="frmAux" name="frmAux" method="post" >
							<input type="hidden" id="hidDocumentosSel" name="hidDocumentosSel">
							<input type="hidden" id="hidNombreDoc" name="hidNombreDoc">
							<input id="hiddenNumItemAdj" type="hidden" name="hiddenNumItemAdj"> 
							<input name="hidTxtNumRequerimientoAdj" id="hidTxtNumRequerimientoAdj" type="hidden" value='${datosEscritoReq.numRequerimiento}'></input>
							<input id="hiddenLstDocAdj" type="hidden" name="hiddenLstDocAdj">
						</form>
	
						<form name="frmCargaArchivo" id="frmCargaArchivo" method="post" class="form-horizontal">
							<fieldset class="scheduler-border" style="margin : 15px 15px !important">
								<legend class="scheduler-border" > Datos del Requerimiento </legend>
								<div class="form-group">
									<div class="col-md-2"><Strong>N&uacute;mero Item:</Strong></div>
									<div class="col-md-3">
										<input name="txtNumItem" id="txtNumItem" type="text" class="form-control tamanoMaximo" disabled></input>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-2"><Strong>Descripci&oacute;n de Item:</Strong></div>
									<div id="txtPalabrasBusqueda" class="col-md-9">
										<textarea id="txtPalabrasClave" name="txtPalabrasClave" rows="6" class="form-control form-control-textarea" maxlength="2000" onpaste="return false;" disabled></textarea>									
									</div>
								</div>
							</fieldset>
							<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border"> Subir Archivos</legend>
								<div class="form-group">
									<div class="col-md-2"><Strong>Archivo Seleccionado:</Strong></div>
									<div id="txtArchivoSeleccionado" class="col-md-10">
										  <input type="hidden" id="txtNombreArchivo" name="txtNombreArchivo">
										  <input name="docFisico" id="docFisico" type="file" onchange='validarArchivoUpload();' class="form-control tamanoMaximo uploadStyle" style="height:30px !important;">
									</div>																
									<div class="col-md-12" align="right" id="dvSecBotones01">	
										<input type="button" id="btnAdjuntarArchivo" class="btn btn-primary" intermediateChanges="false" onClick="agregarListaArchivo()" value="Adjuntar Archivo"></input>
									</div>	
								</div>	
								<div class="form-group">
								</div>	
								<div class="form-group">
									<div class="col-md-12">	
									<div class="tab-content">
									<div id="accionesFiscalizacion" class="tab-pane fade in active">
										<div style="width: 100%; overflow-y: hidden; overflow-x:auto; border: 1px solid #337ab7;">
											<table id="tblDocumentos" name="tblDocumentos"  class="table table-bordered" style="width: 100%;">
												<thead>
													<tr>
														<th width="5%" class="center">N&deg;</th>
														<th width="40%" class="center">Nombre del Archivo</th>
														<th width="20%" class="center">Tama&ntilde;o del Archivo</th>
														<th width="20%" class="center">Ver Archivo</th>
														<th width="15%" class="center">Eliminar</th>
													</tr>
												</thead>
											</table>
										</div>
									</div>
									</div>
									</div>	
								</div>
								<div class="form-group">
									<div class="col-md-12">	
										<p >* Archivos permitidos: PDF, .zip. El archivo .zip solo debe contener archivos Excel (.xls,.xlsx) y/o Archivo de texto .txt.</p>
									</div>
									<div class="col-md-12">	
										<p >* El tama&ntilde;o m&aacute;ximo permitido por archivo es ${tamanoMaximoPermitido} MB. </p>
									</div>
								</div>
							</fieldset>	
														
							<div  style="margin : 15px 15px !important">						
								<div class="form-group">
									<div class="col-md-6" align="right" id="dvSecBotones01">	
										<input type="button" class="btn btn-primary" id="btnRegresar" intermediateChanges="false" onClick="volverPagina()" value="Regresar"></input>
									</div>	
									<div class="col-md-6" align="left" id="dvSecBotones01">	
										<input type="button" class="btn btn-primary" id="btnRegistrar" intermediateChanges="false" onClick="confirmarEnviar()" value="Agregar a item"></input>
									</div>	
								</div>
							</div>						
						</form>
					</div>		
				</div>
				<form id="formPrincipalCargaArchivo" class="form-horizontal" method="post" >	
					<div class="col-md-5" align="right" id="dvSecBotones02">	
						<input id="hidLstDocAdjGrabar" type="hidden" name="hidLstDocAdjGrabar"/>
						<input id="hidLstDetReqGrabar" type="hidden" name="hidLstDetReqGrabar"/>
						<input id="hidTxtNumReqGrabar" type="hidden" name="hidTxtNumReqGrabar" value='${datosEscritoReq.numRequerimiento}'></input>			
					</div>	
				</form>
			</div>
		</div>
		
		<div id="listaDocAdj" class="form-group">
			<div class="col-md-12">	
			<div class="tab-content">
			<div id="accionesFiscalizacion" class="tab-pane fade in active">
				<div style="width: 100%; overflow-y: hidden; overflow-x:auto; border: 1px solid #337ab7;">
					<table id="tblDocumentosHidden" name="tblDocumentosHidden"  class="table table-bordered" style="width: 100%;">
						<thead>
							<tr>
								<th width="5%" class="center">N&deg;</th>
								<th width="40%" class="center">Nombre del Archivo</th>
								<th width="20%" class="center">Tama&ntilde;o del Archivo</th>
								<th width="20%" class="center">Num Item</th>
								<th width="15%" class="center">NumRequerimiento</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			</div>
			</div>	
		</div>
						
	</body>	
	
	<script type="text/javascript">

	var lisDetRequerimItems =  ${listaDetRequerimiento};
	var lisDocAdjItem =  ${listaDocAdjuntos};
	var numExpVirt = ${numExpVirt};
 	var titulo = "Detalle de Requerimiento";
 	
 	var numDocumento;
 	var tamanoMaximoPermitido = ${tamanoMaximoPermitido};
	var peso;
	var mimeType;
	var folios;
	var metodoInvocar;
	var indexDocumentos;
	var indexDocumentosAdj;
	var indexDocumentosAdjHid;
	var numItem;
	var desItem;
	var numRequerimiento;
	var existeDoc;
	var habilitaAgregaItem;
	
    $(function () {
	
		$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
		
		$("#modalDetalleReq").show();
		$("#modalAdjuntarArchivos").hide();
		$("#listaDocAdj").hide();
		
		construirTablaTipoDocumentos( lisDetRequerimItems );
		
		var table = $('#tblTipoDocumentos').DataTable();
		$('#tblTipoDocumentos tbody').on('mouseenter', 'tr', function() {
			$(this).addClass("selected");						
		});	
		$('#tblTipoDocumentos tbody').on( 'mouseleave', 'tr', function () {
			 $(this).removeClass("selected");
		} );
		var table = $('#tblTipoDocumentos').DataTable();
		 
		$('#tblTipoDocumentos tbody').on( 'mouseenter', 'tr', function () {
			indexDocumentos = table.row( this ).index();			
		} );
		
		if (lisDocAdjItem.length > 0) {
			construirTablaDocumentos( [] );
			construirTablaDocumentosHidden( lisDocAdjItem );
		} else {
			construirTablaDocumentos( [] );
			construirTablaDocumentosHidden( [] );
		}		
		
		$('#tblDocumentos tbody').on('mouseenter', 'tr', function() {
			$(this).addClass("selected");
		});	
		$('#tblDocumentos tbody').on( 'mouseleave', 'tr', function () {
			 $(this).removeClass("selected");
		} );
		
		$('#btnRegistrar').prop('disabled', true);
				
		var tableAdj = $('#tblDocumentos').DataTable();
		$('#tblDocumentos tbody').on( 'mouseenter', 'tr', function () {
			indexDocumentosAdj = tableAdj.row( this ).index();			
		} );		
		
		var tableAdjHid = $('#tblDocumentosHidden').DataTable();
		$('#tblDocumentosHidden tbody').on( 'mouseenter', 'tr', function () {
			indexDocumentosAdjHid = tableAdjHid.row( this ).index();			
		} );
	})  
	
	function construirTablaTipoDocumentos(dataGrilla) {
    	
		$('#tblTipoDocumentos').DataTable({
			"language": {
				"url"		:	"/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
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
				{data : "item", sClass: 'center alinearCentrado'},
				{data : "desItemCorta", sClass: 'left alinearCentrado'},	
				{data : "cantArchivos", sClass: 'left alinearCentrado'},
				{data :	"numEscrito", "defaultContent":"", sClass: 'center alinearCentrado',					
					render : function(data, row){
						return jQuery('<span>').attr(
							{
								"class" : "cimg glyphicon glyphicon-open",
								onClick : "adjuntarArchivo()"
							}
						).wrap('<div></div>').parent().html();
					} 
				 },
				{data : "textitem", sClass: 'hidden'},
				{data : "numReq", sClass: 'hidden'}
			],
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
			iDisplayLength		:	10,
			//responsive			:	true,
			bLengthChange		: 	false,
			fnDrawCallback		:	function(oSettings) {
			var a = $('#tblTipoDocumentos').width()
			$("#tblTipoDocumentos_wrapper").css("min-width", a);	
			$('#tblTipoDocumentos_paginate').addClass('div100');
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
    }
	
	function mostrarPagError() {
		
		var formURL = '${pageContext.request.contextPath}/error.htm?action=cargarPaginaError';
		document.forms.frmDatosDocumento.action = formURL;
		document.forms.frmDatosDocumento.method = "post";
		document.forms.frmDatosDocumento.submit();
		
	}
	
	function mostrarDlgInfoSalir(titulo, msj){ 
		
		botones = [];
		
		var aceptar = $("<input/>").attr(
			{
				value: "Aceptar", 
				type: "button", 
				"class": "btn dlgButton btn-primary", 
				"data-dismiss" : "modal", 
				onclick : "volverPaginaReq()"
			}
		);
		
		botones.push(aceptar);
		crearDlg(titulo, msj, botones);
		
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
		
		$('#modalDlg').modal({backdrop: 'static', keyboard: false});
		$('#modalDlg').modal('show');		
	}		
	/**detectar el navegador*/
	
	function adjuntarArchivo() {
		
		var tablaReq = $('#tblTipoDocumentos').dataTable().fnGetData();
		var tablaDocAdj = $('#tblDocumentos').dataTable().fnGetData();
		var tablaDocAdjHid = $('#tblDocumentosHidden').dataTable().fnGetData();
		var fileData;
		
		desItem = tablaReq[indexDocumentos].textitem;
		numItem = tablaReq[indexDocumentos].item;
		numRequerimiento = tablaReq[indexDocumentos].numReq;
		
		$('#txtNumItem').attr("value", numItem);
		$('#txtPalabrasClave').attr("placeholder", desItem);
		
		for (var i = 0; i < tablaDocAdjHid.length; i++) {
			if ((tablaDocAdjHid[i].numItem == numItem) && (tablaDocAdjHid[i].numRequerimiento == numRequerimiento)) {
				
				var tamano = tablaDocAdjHid[i].tamArch/1024.00;
				var tamanoArchivo ;
// 				if (tamano >= 1) {
// 					tamano = Math.round(tamano);
					tamanoArchivo = parseFloat(tamano).toFixed(2);
// 				}
// 				var tamanoArchivo = parseFloat(tamano).toFixed(2);
				
				fileData = new Object();
		 		fileData.numOrden = tablaDocAdjHid[i].numOrden;
		 		fileData.nomArchAdj = tablaDocAdjHid[i].nomArchAdj;
		 		fileData.cntTamArch = tamanoArchivo+' KB';
		 		
		 		
		 		if (tablaDocAdjHid[i].esNuevo == "0") {
// 		 			var url = "${pageContext.request.contextPath}/cargarDocEscritoIt.htm?action=descargarArchivoDoc?codIdentificadorECM=" + tablaDocAdjHid[i].codIdECM +'&numExpedienteVirtual='+numExpVirt+'&nombreArchivo='+tablaDocAdjHid[i].nomArchAdj;
					var url = "http://api.sunat.peru/v1/rest/tecnologia/arquitectura/ecm01/t/ECM/ContentStream/"+tablaDocAdjHid[i].codIdECM;
		 			fileData.verArchAdj = '<a id='+tablaDocAdjHid[i].numOrden+' href="' + url + '" class="glyphicon glyphicon-download-alt" target=_blank ></a>';
		 			fileData.elimArchAdj = "";
		 			habilitaAgregaItem = false;
		 		} else {
		 			fileData.verArchAdj = "<span class='glyphicon glyphicon-download-alt' aria-hidden='true' onclick='descargarArchivo("+tablaDocAdjHid[i].numOrden+")'></span>";
			 		fileData.elimArchAdj = "<span name='"+tablaDocAdjHid[i].numOrden+"' class='glyphicon glyphicon-trash' aria-hidden='true' onclick='confirmarEliminar("+tablaDocAdjHid[i].numOrden+")'></span>";
			 		habilitaAgregaItem = true;
		 		}		 				
		 		fileData.esNuevo = tablaDocAdjHid[i].esNuevo;
		 		fileData.numItem = tablaDocAdjHid[i].numItem;
		 		fileData.numRequerimiento = tablaDocAdjHid[i].numRequerimiento;
		 		fileData.mimeType = tablaDocAdjHid[i].mimeType;
		 		fileData.tamArch = tablaDocAdjHid[i].tamArch;
		 		fileData.folios = tablaDocAdjHid[i].folios;
		 		fileData.codIdECM = tablaDocAdjHid[i].codIdECM;
		 		$('#tblDocumentos').dataTable().fnAddData(fileData);	 		
			}
		}
		mostrarModal();		
	}	

	function validarSalida(){
		var tablaDocAdj = $('#tblDocumentosHidden').dataTable().fnGetData();
		var archNvos = 0;
		for (var i = 0; i < tablaDocAdj.length; i++) {
			if (tablaDocAdj[i].esNuevo == "1") {
				archNvos = archNvos + 1;
			}
		}
		
		if(archNvos > 0){
			msj = "Existen archivos que fueron seleccionados para grabar, &iquest;Desea Salir?";
			botones = [];
			
			var botonSi = $("<input/>").attr(
				{
					value: "Si", 
					type: "button", 
					"class": "btn dlgButton btn-primary", 
					"data-dismiss" : "modal", 
					onclick : "volverPaginaReq()"
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
		
		}else{
			volverPaginaReq();
		}
	}
	
	function volverPaginaReq(){
		$('html').block({message: '<h1>Regresando...</h1>'});
		var  url = '${pageContext.request.contextPath}/cargarDocumentoIt.htm?action=consultarRequerimientosPendientesView';
		$(location).prop( 'href', url);
	}
	
	function grabarCambios() {
		var tabla = $('#tblTipoDocumentos').dataTable().fnGetData();
		var flgCntDocAdj = false;
		
		for (var i = 0; i < tabla.length; i++) {
			if (tabla[i].cantArchivos > 0){
				flgCntDocAdj = true;
			}
		}
		
		if (!flgCntDocAdj) {
			titulo = "Detalle de Requerimiento";
			mostrarDlgInfo(titulo, 'Debe adjuntar al menos un archivo.');
			return;
		}
			
		validarExisteDoc();
		
		titulo = "Grabar Escrito Electr&oacute;nico";
		msj = '&iquest;Est&aacute; seguro que desea grabar?';
		botones = [];
		
		var botonSi = $("<input/>").attr(
			{
				value: "Si", 
				type: "button", 
				"class": "btn dlgButton btn-primary", 
				"data-dismiss" : "modal", 
				onclick : "grabarEscrito()"
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
	
	function mostrarModal(){		
		$("#modalDetalleReq").hide();
		$("#modalAdjuntarArchivos").show();
		$("#listaDocAdj").hide();
				
		if (habilitaAgregaItem) {
			$('#btnRegistrar').prop('disabled', false);
		} else {
			$('#btnRegistrar').prop('disabled', true);
		}
		
	}
	
	function grabarEscrito() {
		var mensaje;
				
		mensaje = 'Estimado(a) Contribuyente: Se ha detectado inconveniente con la conexi&oacute;n de internet, '+
				  'por ello le sugerimos volver a ingresar a la opci&oacute;n "Presentaci&oacute;n de Escritos Electr&oacute;nicos". '+ 
				  'Pedimos disculpas por las molestias ocasionadas y agradecemos su comprensi&oacute;n.'
		
		if (!existeDoc) {
			mostrarDlgInfo(titulo, mensaje);
			return;			
		}	
		
		grabarDocAdj();
	
	}
	
	function validarExisteDoc() {
		var tablaDocAdj = $('#tblDocumentosHidden').dataTable().fnGetData();
		var listaDocAdj  = JSON.stringify(tablaDocAdj);
		
		$('#hiddenLstDocAdj').val(listaDocAdj);
		metodoInvocar = 'validaDocExiste';
		$('#hiddenLstDocAdj').val(listaDocAdj);
		if (listaDocAdj.length > 0) {
			$('#frmAux').submit();
		}		
	}
	
	function grabarDocAdj() {
		var tablaDetReq = $('#tblTipoDocumentos').dataTable().fnGetData();
		var tablaDocAdj = $('#tblDocumentosHidden').dataTable().fnGetData();
		var listaDocAdj  = JSON.stringify(tablaDocAdj);
		var listaDetReq  = JSON.stringify(tablaDetReq);
		
		$('#hidLstDocAdjGrabar').val(listaDocAdj); 
		$('#hidLstDetReqGrabar').val(listaDetReq);		
		$('html').block({message: '<h1>Procesando...</h1>'});
		$('#formPrincipalCargaArchivo').submit();
	}
	
	$("#formPrincipalCargaArchivo").submit(function(e){
		var formObj = $(this);
		var iframeId = 'unique' + (new Date().getTime());
		
		var iframe = $('<iframe height="200" width="100" src="javascript:false;" name="'+iframeId+'" />');
		//var iframe = $('<iframe src="javascript:false;" name="'+iframeId+'" />');
		
		iframe.hide();
		formObj.attr('target', iframeId);
		formObj.attr('action', '${pageContext.request.contextPath}/cargarDocEscritoIt.htm?action=grabarDocAdjDetReq');
		//formObj.attr("enctype", "multipart/form-data");
		//formObj.attr("encoding", "multipart/form-data");
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
					if(response.error == '0'){
						$('html').unblock();						
						mostrarDlgInfo(titulo, "Ocurri&oacute; un error al guardar los cambios.");
					}else{
						$('html').unblock();
						mostrarDlgInfoSalir(titulo, "El escrito electr&oacute;nico fue registrado satisfactoriamente.");	
					}
				}
				else{
					
				}
				
			}
			
		});			
	});
	
	function construirTablaDocumentos(dataGrilla) {
    	
		$('#tblDocumentos').DataTable({
			fnRowCallback: function (nRow, aData, iDisplayIndex) {
                    //$(nRow).attr('align', 'center');
					$(nRow).attr('id', aData[0]);
					 return nRow;
            },
			"language": {
				"url"		:	"/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
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
				{data : "numOrden", sClass: 'center alinearCentrado'},
				{data : "nomArchAdj", sClass: 'left alinearCentrado'},	
				{data : "cntTamArch", sClass: 'left alinearCentrado'},
				{data :	"verArchAdj", "defaultContent":"", sClass: 'center alinearCentrado'},
				{data : "elimArchAdj", "defaultContent":"", sClass: 'center alinearCentrado'},
				{data : "numItem", sClass: 'hidden'},
				{data : "numRequerimiento", sClass: 'hidden'},
				{data : "esNuevo", sClass: 'hidden'},
				{data : "mimeType", sClass: 'hidden'},
				{data : "tamArch", sClass: 'hidden'},
				{data : "folios", sClass: 'hidden'},
				{data : "codIdECM", sClass: 'hidden'}
			],
					
			data: dataGrilla,
            ordering: true,
            searching: false,
            paging: true,
            bScrollAutoCss: true,
			bScrollCollapse: true,
            bStateSave: false,
            bAutoWidth: false,
            bScrollCollapse: true,
            pagingType: "full_numbers",
            iDisplayLength: 10,
            //responsive: true,
            bLengthChange: false,
			fnDrawCallback		:	function(oSettings) {
				var a = $('#tblDocumentos').width()
				$("#tblDocumentos_wrapper").css("min-width", a);
				$('#tblDocumentos_paginate').addClass('div100');
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
    }

	function construirTablaDocumentosHidden(dataGrilla) {
    	
		$('#tblDocumentosHidden').DataTable({
			fnRowCallback: function (nRow, aData, iDisplayIndex) {
                    //$(nRow).attr('align', 'center');
					$(nRow).attr('id', aData[0]);
					 return nRow;
            },
			"language": {
				"url"		:	"/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
			},
			oLanguage : {
				sInfo		:	' ',
				sInfoEmpty	:	' ',
				sEmptyTable	:	' ',
				oPaginate   :   ' ' 
			},
			columns: [
				{data : "numOrden", sClass: 'center alinearCentrado'},
				{data : "nomArchAdj", sClass: 'left alinearCentrado'},	
				{data : "cntTamArch", sClass: 'left alinearCentrado'},
				{data : "numItem", sClass: 'center alinearCentrado'},
				{data : "numRequerimiento", sClass: 'center alinearCentrado'},
				{data : "esNuevo", sClass: 'center alinearCentrado'},
				{data : "mimeType", sClass: 'center alinearCentrado'},
				{data : "tamArch", sClass: 'center alinearCentrado'},
				{data : "folios", sClass: 'center alinearCentrado'},
				{data : "codIdECM", sClass: 'center alinearCentrado'}
			],
					
			data: dataGrilla,
            ordering: true,
            searching: false,
            paging: false,
            bScrollAutoCss: true,
			bScrollCollapse		:	true,
            bStateSave: false,
            bAutoWidth: false,
            bScrollCollapse: true,
            pagingType: "full_numbers",
            iDisplayLength: 5,
            //responsive: true,
            bLengthChange: false,
			fnDrawCallback		:	function(oSettings) {
				var a = $('#tblDocumentosHidden').width()
				$("#tblDocumentosHidden_wrapper").css("min-width", a);
				$('#tblDocumentosHidden_paginate').addClass('div100');
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
    }
    
	function validarArchivoUpload() {
		var input = $('#docFisico');
		var nombreArchivo =  label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
		if(nombreArchivo.length>100){
			var control = $("#docFisico");
			control.replaceWith( control = control.clone( true ) );
			control.val(null);
			$("#docFisico").val("");
			mostrarDlgInfo(titulo, "El nombre del archivo a cargar debe tener un m&aacute;ximo de 100 car&aacute;cteres.");		
		}
		else{	
			$('#hidDocumentosSel').val(numDocumento);
			$('#hiddenNumItemAdj').val(numItem);
			metodoInvocar = 'obtenerTamano';
			$('html').block({message: '<h1>Procesando</h1>'});
			$("#docFisico").appendTo($('#frmAux'));
			$('#frmAux').submit();
			$("#docFisico").appendTo($('#txtArchivoSeleccionado'));
		}			
	}
	
    $("#frmAux").submit(function(e){
		var formObj = $(this);
		var iframeId = 'unique' + (new Date().getTime());
		var iframe = $('<iframe height="200" width="100" src="javascript:false;" name="'+iframeId+'" />');
	    iframe.hide();
	    formObj.attr('target', iframeId);
	    formObj.attr('action', '${pageContext.request.contextPath}/cargarDocEscritoIt.htm?action='+metodoInvocar); //METODO DEL CONTROLLER
	    formObj.attr("enctype", "multipart/form-data");
	    formObj.attr("encoding", "multipart/form-data");
	    iframe.appendTo('body');
	   
		iframe.load(function(e){
			var doc = getDoc(iframe[0]);
			var docRoot = doc.body ? doc.body : doc.documentElement;
			var data = docRoot.innerHTML;
			var jsonDataString;
			var response;
			var indError;
			indError = false;
			if (data.toLowerCase().indexOf("indError") >= 0) {
				indError = true;
			}
			
			if(indError) {//ERROR INESPERADO POSIBLEMENTE POR TAMAÑO UPLOAD				
				$("#docFisico").appendTo($('#frmAuxiliar'));
				frmAuxiliar.reset();						
				$("#docFisico").appendTo($('#txtArchivoSeleccionado'));
				$("#docFisico").val("");
				
				$("#txtNombreArchivo").val("");				
				mostrarDlgInfo(titulo, "Excepci&oacute;n no controlada.");
				
			} else {
				
				if (data.indexOf("<pre style") > -1) {
					
					jsonDataString = data.replace('<pre style="word-wrap: break-word; white-space: pre-wrap;">', '');
					jsonDataString = jsonDataString.replace('</pre>', '');
					
				}else {
	    			jsonDataString = data.replace('<PRE>', '');
					jsonDataString = jsonDataString.replace('</PRE>', '');
					jsonDataString = jsonDataString.replace('<pre>', '');
					jsonDataString = jsonDataString.replace('</pre>', '');
				}
					
					response = jQuery.parseJSON(jsonDataString);
					
					if(metodoInvocar=="obtenerTamano"){						
						
						if(response.archivoExtensionPermitida == false){ //EXTENSION NO PERMITIDA
							$("#txtNombreArchivo").val("");
							var control = $("#docFisico");
							control.replaceWith( control = control.clone( true ) );
							control.val(null);
							$("#docFisico").val("");
							mostrarDlgInfo(titulo, "El tipo de archivo seleccionado no est&aacute; permitido, favor de verificar.");
						} else if(response.archivoOtrosNoExiste == false){ //ARCHIVOS OTROS ENCONTRADOS 
							$("#txtNombreArchivo").val("");
							var control = $("#docFisico");
							control.replaceWith( control = control.clone( true ) );
							control.val(null);
							$("#docFisico").val("");
							mostrarDlgInfo(titulo, "El tipo de archivo comprimido debe ser: .xls o .xlsx o .txt.");
						} else if(response.archivoPdfA == false){ //PDF_A 
							$("#txtNombreArchivo").val("");
							var control = $("#docFisico");
							control.replaceWith( control = control.clone( true ) );
							control.val(null);
							$("#docFisico").val("");
							mostrarDlgInfo(titulo, "El archivo debe ser PDF/A.");
						}else if(response.archivoPdfProtegido == false){ //PDF PROTEGIDO 
							$("#txtNombreArchivo").val("");
							var control = $("#docFisico");
							control.replaceWith( control = control.clone( true ) );
							control.val(null);
							$("#docFisico").val("");
							mostrarDlgInfo(titulo, "No est&aacute; permitido adjuntar PDFs protegidos.");
						} else if(response.archivoTamanoPermitido == false){ //TAMANO ADJUNTO NO PERMITIDO
							$("#txtNombreArchivo").val("");
							var control = $("#docFisico");
							control.replaceWith( control = control.clone( true ) );
							control.val(null);
							$("#docFisico").val("");
							mostrarDlgInfo(titulo, "El tama&ntilde;o del archivo seleccionado excede el l&iacute;mite permitido ("+tamanoMaximoPermitido+" MB).");
						} else {
							peso = response.peso;
							mimeType = response.mimeType;
							folios = response.folios;
							$('#txtNombreArchivo').val(response.nombreArchivo);
						}
						
					} else if(metodoInvocar="validaDocExiste"){
						var existe = response.existe;
						
						if(existe=="1"){
							existeDoc = true;
						}else{
							existeDoc = false;
						}
						
					} else {
						$('html').unblock();
						var mensaje = "Ocurri&oacute un error."
						mostrarDlgInfo(titulo,mensaje);
					}
				$('html').unblock();
			}
			
		});
			
	});
    
	function agregarListaArchivo(){
		var mensaje = "";
		var nombreArchivo = $('#txtNombreArchivo').val();
		titulo = "Detalle de Requerimiento";
		mensaje="Debe seleccionar un archivo.";
		if(nombreArchivo == ""){
			mostrarDlgInfo(titulo,mensaje);
			var input = $("#docFisico");
			peso=0;
			mimeType = "";
			input.replaceWith(input.val('').clone(true));	
			input.val(null);
			$("#docFisico").val("");
			return;
		};
		
		mensaje="El tama&ntilde;o del archivo debe ser mayor a  0 KB";
		if(peso == 0) {
		   mostrarDlgInfo(titulo,mensaje);
		   var input = $("#docFisico");
		   peso=0;
		   mimeType = "";
		   input.replaceWith(input.val('').clone(true));	
		   input.val(null);
		   $("#docFisico").val("");
		   return;
		}
		
		mensaje="El tama&ntilde;o del archivo seleccionado excede el l&iacute;mite permitido ("+tamanoMaximoPermitido+" MB).";
		
		if(!validarTamanoCarga(peso)){
			mostrarDlgInfo(titulo,mensaje);
			var input = $("#docFisico");
			peso=0;
			mimeType = "";
			input.replaceWith(input.val('').clone(true));	
			input.val(null);
			$("#docFisico").val("");
			return;
		}
		
		mensaje = "Existe un archivo con el mismo nombre.";
		if (!existeArchivo()) {
			mostrarDlgInfo(titulo,mensaje);
			var input = $("#docFisico");
			peso="-";
			mimeType = "";
			input.replaceWith(input.val('').clone(true));	
			input.val(null);
			$("#docFisico").val("");
			return;
		}

		var numeroFila;
		var tamanoArchivo;
		var tamano = peso/1024.00;
// 		if (tamano >= 1) {
// 			tamano = Math.round(tamano);
			tamanoArchivo = parseFloat(tamano).toFixed(2);
// 		}
// 		var tamanoArchivo = parseFloat(tamano).toFixed(2);
		var tableData = $('#tblDocumentos').dataTable().fnGetData();
		var tableDocAdjHid = $('#tblDocumentosHidden').dataTable().fnGetData();
		var fileData;
		
		numeroFila = tableData.length + 1;
 		fileData = new Object();
 		fileData.numOrden = tableData.length + 1;
 		fileData.nomArchAdj = nombreArchivo;
 		fileData.cntTamArch = tamanoArchivo+' KB';
 		fileData.verArchAdj = "<span class='glyphicon glyphicon-download-alt' aria-hidden='true' onclick='descargarArchivo("+numeroFila+")'></span>";
 		fileData.elimArchAdj = "<span name='"+numeroFila+"' class='glyphicon glyphicon-trash' aria-hidden='true' onclick='confirmarEliminar("+numeroFila+")'></span>";		
 		fileData.esNuevo = "1";
 		fileData.numItem = numItem;
 		fileData.numRequerimiento = numRequerimiento;
 		fileData.mimeType = mimeType;
 		fileData.tamArch = peso;
 		fileData.folios = folios;
 		fileData.codIdECM = "0";
 		$('#tblDocumentos').dataTable().fnAddData(fileData); 
 		$('#tblDocumentosHidden').dataTable().fnAddData(fileData);
					
		$('#txtNombreArchivo').val("");		
		$('#frmCargaArchivo').bootstrapValidator('resetForm', true);	
		$('#btnRegistrar').prop('disabled', false);
		var input = $("#docFisico");
		peso=0;
		mimeType = "";
		input.replaceWith(input.val('').clone(true));	
		input.val(null);
		$("#docFisico").val("");
	}
	
	function obtenerNumeroFila(){
		var numeroFila=1;
		var dataJson = $('#tblDocumentos').DataTable().rows().data();	
		for (var i = 0; i < dataJson.length; i++) {
			numeroFila++;
		}
		return numeroFila;
	}
	
	function existeArchivo(){		
		var tableData = $('#tblDocumentosHidden').dataTable().fnGetData();
		var nombreArchivo = $('#txtNombreArchivo').val();
		var pesoArchivo = peso;
		
		if(tableData.length != 0 ){
			var ind = 0;
			
			for(var i=0;i<tableData.length;i++){
				if(tableData[i].nomArchAdj == nombreArchivo){
					ind = 1;					
					return false;
				}
			}
		}			
		return true;
	}
	
	function validarTamanoCarga(pesoAdicional){
		var pesoadd = Math.round(pesoAdicional/1048576*100000)/100000;
		if(pesoadd>tamanoMaximoPermitido){
			return false;
		}
		return true;
	}
	
	function confirmarEnviar(){ 
		var datos = [];
		var contador=0;
	    var dataJson = $('#tblDocumentos').DataTable().rows().data();	
		for (var i = 0; i < dataJson.length; i++) {
			contador++;
		}
		if(contador==0){
			mostrarDlgInfo(titulo, 'Debe adjuntar al menos un archivo.');
			return;
		}
		
		if(contador > 99){
			mostrarDlgInfo(titulo, 'Solo puede adjuntar 99 archivos por presentaci&oacute;n.');
			return;
		}
		
		actualizarCantArchivos(contador);		
		
		$("#modalDetalleReq").show();
		$("#modalAdjuntarArchivos").hide();
		$("#listaDocAdj").hide();

	}
	
	function actualizarCantArchivos(contador){
		var datosDetReq
		var tabla = $('#tblTipoDocumentos').dataTable().fnGetData();		
		$('#tblTipoDocumentos').dataTable().fnClearTable();
		
		for (var i=0; i<tabla.length; i++) {
			if (tabla[i].item == numItem) {
				datosDetReq = new Object;
				
				datosDetReq.item = tabla[i].item;
				datosDetReq.desItemCorta = tabla[i].desItemCorta;
				datosDetReq.cantArchivos = contador;
				datosDetReq.numEscrito = "<span class='cimg glyphicon glyphicon-open' aria-hidden='true' onclick='adjuntarArchivo()'></span>";
				datosDetReq.textitem = desItem;
				datosDetReq.numReq = numRequerimiento;
				$('#tblTipoDocumentos').dataTable().fnAddData(datosDetReq);
			} else {
				datosDetReq = new Object;
				
				datosDetReq.item = tabla[i].item;
				datosDetReq.desItemCorta = tabla[i].desItemCorta;
				datosDetReq.cantArchivos = tabla[i].cantArchivos;
				datosDetReq.numEscrito = "<span class='cimg glyphicon glyphicon-open' aria-hidden='true' onclick='adjuntarArchivo()'></span>";
				datosDetReq.textitem = tabla[i].textitem;
				datosDetReq.numReq = tabla[i].numReq;
				$('#tblTipoDocumentos').dataTable().fnAddData(datosDetReq);
			}			
		}
		
		$('#tblDocumentos').dataTable().fnClearTable();
		
		var input = $("#docFisico");
		peso=0;
		mimeType = "";
		input.replaceWith(input.val('').clone(true));	
		input.val(null);
		$("#docFisico").val("");
	}
	
	function descargarArchivo(fila){		
		var tabla = $('#tblDocumentos').dataTable().fnGetData();

		var nombreArchivo = tabla[fila-1].nomArchAdj;
		$('#hidNombreDoc').val(nombreArchivo);
		$('#hiddenNumItemAdj').val(numItem);
		metodoInvocar = "descargarDocumento";
		var archivo = $('#docFisico');
		$('html').block({message: '<h1>Procesando...</h1>'});
		if (archivo != undefined) {
			$('html').block({message: '<h1>Procesando...</h1>'});
			$('#frmAux').submit();
		}
		$('html').unblock();						
	}
	
	function confirmarEliminar(fila){ 
		var tabla = $('#tblDocumentos').dataTable().fnGetData();
		
		nombreArchivo =tabla[fila-1].nomArchAdj;
		
		titulo = "Eliminar Archivo";
		msj = 'El archivo "'+nombreArchivo+'" Se eliminar&aacute;.';
		botones = [];
		
		var botonSi = $("<input/>").attr(
			{
				value: "Eliminar", 
				type: "button", 
				"class": "btn dlgButton btn-primary", 
				"data-dismiss" : "modal", 
				onclick : "eliminarFila("+fila+")"
			}
		);
		var botonNo = $("<input/>").attr(
			{
				value: "Cancelar", 
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
	
	function eliminarFila(fila){
		
		var tabla = $('#tblDocumentos').dataTable().fnGetData();
		var tablaDocAdjHid = $('#tblDocumentosHidden').dataTable().fnGetData();
		
		$('#tblDocumentos').dataTable().fnClearTable();
		$('#tblDocumentosHidden').dataTable().fnClearTable();
		
		var fileData;
		var a=1;
		for(var i=0;i<tabla.length;i++){
			if(tabla[i].numOrden != fila){
				fileData = new Object();
				fileData.numOrden = a;
				fileData.nomArchAdj = tabla[i].nomArchAdj;
				fileData.cntTamArch = tabla[i].cntTamArch;
				
				
				if (tabla[i].esNuevo == "0") {					
					var url = "http://api.sunat.peru/v1/rest/tecnologia/arquitectura/ecm01/t/ECM/ContentStream/" + tabla[i].codIdECM;
		 			fileData.verArchAdj = '<a id='+tabla[i].numOrden+' href="' + url + '" class="glyphicon glyphicon-download-alt" target=_blank ></a>';
		 			fileData.elimArchAdj = "";
		 		} else {
		 			fileData.verArchAdj = "<span class='glyphicon glyphicon-download-alt' aria-hidden='true' onclick='descargarArchivo("+a+")'></span>";
					fileData.elimArchAdj = "<span name='"+a+"' class='glyphicon glyphicon-trash' aria-hidden='true' onclick='confirmarEliminar("+a+")'></span>";
		 		}				
						
				fileData.esNuevo = tabla[i].esNuevo;
				fileData.numItem = tabla[i].numItem;
		 		fileData.numRequerimiento = tabla[i].numRequerimiento;
		 		fileData.mimeType = tabla[i].mimeType;
		 		fileData.tamArch = tabla[i].tamArch;
		 		fileData.folios = tabla[i].folios;
		 		fileData.codIdECM = tabla[i].codIdECM;
				$('#tblDocumentos').dataTable().fnAddData(fileData);
				a++;				
			}			
		}
		
		var tablaDoc = $('#tblDocumentos').dataTable().fnGetData();
		var b = 1;
		for(var i=0;i<tablaDocAdjHid.length;i++){
			if(tablaDocAdjHid[i].numItem == numItem){				
				if(tablaDocAdjHid[i].numOrden != fila) {
					fileData = new Object();
					fileData.numOrden = b;
					fileData.nomArchAdj = tablaDocAdjHid[i].nomArchAdj;
					fileData.cntTamArch = tablaDocAdjHid[i].cntTamArch;
					
					
					if (tablaDocAdjHid[i].esNuevo == "0") {
						var url = "http://api.sunat.peru/v1/rest/tecnologia/arquitectura/ecm01/t/ECM/ContentStream/" + tablaDocAdjHid[i].codIdECM;
			 			fileData.verArchAdj = '<a id='+tablaDocAdjHid[i].numOrden+' href="' + url + '" class="glyphicon glyphicon-download-alt" target=_blank ></a>';
			 			fileData.elimArchAdj = "";
			 		} else {
			 			fileData.verArchAdj = "<span class='glyphicon glyphicon-download-alt' aria-hidden='true' onclick='descargarArchivo("+b+")'></span>";
						fileData.elimArchAdj = "<span name='"+b+"' class='glyphicon glyphicon-trash' aria-hidden='true' onclick='confirmarEliminar("+b+")'></span>";
			 		}
							
					fileData.esNuevo = tablaDocAdjHid[i].esNuevo;
					fileData.numItem = tablaDocAdjHid[i].numItem;
			 		fileData.numRequerimiento = tablaDocAdjHid[i].numRequerimiento;
			 		fileData.mimeType = tablaDocAdjHid[i].mimeType;
			 		fileData.tamArch = tablaDocAdjHid[i].tamArch;
			 		fileData.folios = tablaDocAdjHid[i].folios;
			 		fileData.codIdECM = tablaDocAdjHid[i].codIdECM;
					$('#tblDocumentosHidden').dataTable().fnAddData(fileData);
					b++;
				}
			} else {
					fileData = new Object();
					fileData.numOrden = tablaDocAdjHid[i].numOrden;
					fileData.nomArchAdj = tablaDocAdjHid[i].nomArchAdj;
					fileData.cntTamArch = tablaDocAdjHid[i].cntTamArch;
					
					
					if (tablaDocAdjHid[i].esNuevo == "0") {
						var url = "http://api.sunat.peru/v1/rest/tecnologia/arquitectura/ecm01/t/ECM/ContentStream/" + tablaDocAdjHid[i].codIdECM;
			 			fileData.verArchAdj = '<a id='+tablaDocAdjHid[i].numOrden+' href="' + url + '" class="glyphicon glyphicon-download-alt" target=_blank ></a>';
			 			fileData.elimArchAdj = "";
			 		} else {
			 			fileData.verArchAdj = "<span class='glyphicon glyphicon-download-alt' aria-hidden='true' onclick='descargarArchivo("+tablaDocAdjHid[i].numOrden+")'></span>";
						fileData.elimArchAdj = "<span name='"+tablaDocAdjHid[i].numOrden+"' class='glyphicon glyphicon-trash' aria-hidden='true' onclick='confirmarEliminar("+tablaDocAdjHid[i].numOrden+")'></span>";
			 		}
							
					fileData.esNuevo = tablaDocAdjHid[i].esNuevo;
					fileData.numItem = tablaDocAdjHid[i].numItem;
			 		fileData.numRequerimiento = tablaDocAdjHid[i].numRequerimiento;
			 		fileData.mimeType = tablaDocAdjHid[i].mimeType;
			 		fileData.tamArch = tablaDocAdjHid[i].tamArch;
			 		fileData.folios = tablaDocAdjHid[i].folios;
			 		fileData.codIdECM = tablaDocAdjHid[i].codIdECM;
					$('#tblDocumentosHidden').dataTable().fnAddData(fileData);
				}
		}
		//limpiamos el input file asociado
		var input = $("#docFisico");
		input.replaceWith(input.val('').clone(true));						
	}
	
	//obtiene el contenido del iFrame
	function getDoc(frame) {
		
		var doc = null;
		
		try {
			
			if (frame.contentWindow) {
				
				doc = frame.contentWindow.document;
				
			}
			
		} catch(err) {
		
		}
		
		if (doc) {
			
			return doc;
			
		}
		
		try {
			
			doc = frame.contentDocument ? frame.contentDocument : frame.document;
			
		} catch(err) {
			
			doc = frame.document;
			
		}
		
		return doc;		
	}
	
	function volverPagina(){		
		
		$("#modalDetalleReq").show();
		$("#modalAdjuntarArchivos").hide();
		$("#listaDocAdj").hide();
		$('#tblDocumentos').dataTable().fnClearTable();
	}
	
 </script>
</html>
	