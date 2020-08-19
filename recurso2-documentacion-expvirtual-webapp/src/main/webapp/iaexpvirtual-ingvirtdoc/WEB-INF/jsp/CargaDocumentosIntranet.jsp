
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=EDGE" />
<meta name="viewport" content="initial-scale = 1.0, user-scalable = no,  width=device-width">
<title>SISTEMA INTEGRADO DE EXPEDIENTE VIRTUAL - SIEV</title>

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
  <script src="/a/js/bootstrap/3.2.0/js/jquery.blockUI.js" type="text/javascript"></script>
 
  
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
	.form-control-textarea {
			font-size:12px !important;
			height:auto !important;
			padding-top:3px !important;
			padding-bottom:3px !important;
			padding-left:5px !important;
			padding-right:5px !important;
			resize: none;
		}
	
	legend.scheduler-border {
	width: inherit; /* Or auto */
	padding: 0 2px; /* To give a bit of padding on the left and right */
	border-bottom: none;
	margin-bottom: 0;
	font-size: 14px;
	}
	
	input[readonly] {
	  background-color: white !important;
	  cursor: text !important;
	}
	
	.dataTables_wrapper .dataTables_paginate {
		float: initial !important;
		text-align: center !important;
		padding-top: 0.25em;
	}
	
	.dlgButton {
		border-color: gray;
		margin-right: 7px;
	}
	
	.uploadStyle {
			border-color: white;
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
						<h3 class="panel-title" align="center">ATENCI&Oacute;N DE REQUERIMIENTOS POR INTRANET</h3>
					</div>
				</div>	
				<div class="panel panel-primary" >
					<form id="frmAux" name="frmAux" method="post" >
						<input type="hidden" id="hidDocumentosSel" name="hidDocumentosSel">
						<input type="hidden" id="hidNombreDoc" name="hidNombreDoc">
						<input type="hidden" name="listaDocumentosSel" id="hidListaDocumentosSel">
						<input name="hidEstadoExpediente" id="hidEstadoExpediente" type="hidden"  class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.desEstado}' ></input>
						<input name="hidTxtRuc" id="hidTxtRuc" type="hidden" class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.numRuc}'></input>	
						<input name="hiddTxtRazonSocial" id="hiddTxtRazonSocial" type="hidden"  class="form-control tamanoMaximo" value='${razonSocial}' ></input>
						<input name="hiddTxtProceso" id="hiddTxtProceso" type="hidden" class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.desProceso}' ></input>
						<input name="hidTipoExpediente" id="hidTipoExpediente" type="hidden"  class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.codTipoExpediente}'></input>
						<input name="hidDesTipoExpediente" id="hidDesTipoExpediente" type="hidden"  class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.desTipoExpediente}'></input>
						<input name="hidTxtExpedienteVirtual" id="hidTxtExpedienteVirtual" type="hidden"  class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.numExpedienteVirtual}' ></input>
						<input name="hidTxtExpedienteOrigen" id="hidTxtExpedienteOrigen" type="hidden"  class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.numExpedienteOrigen}' ></input>
						<input name="txtFechaRegExpediente" id="txtFechaRegExpediente" type="hidden"  class="form-control tamanoMaximo" value='${fechaRegExpediente}' ></input>
						<input name="hidTxtNumRequerimiento" id="hidTxtNumRequerimiento" type="hidden" class="form-control tamanoMaximo" value='${t6620RequerimBean.numRequerimiento}' ></input>
						<input id="hidTxtFecRequerimiento" name="hidTxtFecRequerimiento" type="hidden"  class="form-control tamanoMaximo" value='${fechaRequerimiento}' ></input>
						<input id="hidTxtFecVencimiento" name="hidTxtFecVencimiento" type="hidden"  class="form-control tamanoMaximo" value='${fechaVencimiento}' ></input>
						<input id="hidTxtTipoOpcion" name="hidTxtTipoOpcion" type="hidden"  class="form-control tamanoMaximo" value='IN' ></input>
						
					</form>

					<form name="frmCargaArchivo" id="frmCargaArchivo" method="post" class="form-horizontal">
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border" > Datos del Expediente </legend>
							<div class="form-group">
								<div class="col-md-2"><Strong>RUC:</Strong></div>
								<div class="col-md-3">
									<input name="txtRuc" id="txtRuc" type="text" class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.numRuc}' disabled></input>
								</div>
								<div class="col-md-2"><Strong>Raz&oacute;n Social:</Strong></div>
								<div class="col-md-3">
								<input name="txtRazonSocial" id="txtRazonSocial" type="text"  class="form-control tamanoMaximo" value='${razonSocial}' disabled></input>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"><Strong>Proceso:</Strong></div>
								<div class="col-md-3">
									<input name="txtProceso" id="txtProceso" type="text" class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.desProceso}' disabled></input>
								</div>
								<div class="col-md-2"><Strong>Tipo de Expediente:</Strong></div>
								<div class="col-md-3">
									<input name="txtTipoExpediente" id="txtTipoExpediente" type="text"  class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.desTipoExpediente}' disabled></input>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"><Strong>N&deg; Expediente Origen:</Strong></div>
								<div class="col-md-3">
									<input name="txtExpedienteOrigen" id="txtExpedienteOrigen" type="text" class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.numExpedienteOrigen}' disabled></input>
									
								</div>
								<div class="col-md-2"><Strong>N&deg; Expediente Virtual:</Strong></div>
								<div class="col-md-3">
									<input name="txtEx" id="txtExpedienteVirtual" name="txtExpedienteVirtual" type="text" class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.numExpedienteVirtual}' disabled></input>
								</div>
							</div>	
							<div class="form-group">
								<div class="col-md-2"><Strong>Fecha del Documento Origen:</Strong></div>
								<div class="col-md-3">
									<input name="txtFechaDocOrigen" id="txtFechaDocOrigen" type="text" class="form-control tamanoMaximo" value='${fechaDocOrigen}' disabled></input>
								</div>
								<div class="col-md-2"><Strong>Fecha de Notificaci&oacute;n:</Strong></div>
								<div class="col-md-3">
									<input name="txtFechaNotif" id="txtFechaNotif" type="text" class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.strFecNotifOrigen}' disabled></input>
								</div>
							</div>

							<div class="form-group">
								<div class="col-md-2"><Strong>Forma de Notificaci&oacute;n:</Strong></div>
								<div class="col-md-3">
									<input name="txtFormaNotif" id="txtFormaNotif" type="text" class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.desForNotifOrigen}' disabled></input>
								</div>
								<div class="col-md-2"><Strong>Fecha de Registro del Expediente:</Strong></div>
								<div class="col-md-3">
									<input name="" id="" type="text" class="form-control tamanoMaximo" value='${fechaRegExpediente}' disabled></input>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"><Strong>Responsable del Expediente:</Strong></div>
								<div class="col-md-3">
									<input name="" id="" type="text" class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.nombreResponsable}' disabled></input>
								</div>
							</div>	
						</fieldset>	
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border" > Datos del Requerimiento </legend>
							<div class="form-group">
								<div class="col-md-2"><Strong>N&deg; Requerimiento:</Strong></div>
								<div class="col-md-3">
									<input name="txtNumRequerimiento" id="txtNumRequerimiento" type="text" class="form-control tamanoMaximo" value='${t6620RequerimBean.numRequerimOrigen}' disabled></input>
								</div>
								<div class="col-md-2"><Strong>Estado del Requerimiento:</Strong></div>
								<div class="col-md-3">
									<input id="txtEstRequerimiento" name="txtEstRequerimiento" type="text"  class="form-control tamanoMaximo" value='${t6620RequerimBean.desEstado}' disabled></input>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"><Strong>Fecha del Requerimiento:</Strong></div>
								<div class="col-md-3">
									
									<input id="txtFecRequerimiento" name="txtFecRequerimiento" type="text"  class="form-control tamanoMaximo" value='${fechaRequerimiento}' disabled></input>
								</div>
								<div class="col-md-2"><Strong>Fecha de Vencimiento:</Strong></div>
								<div class="col-md-3">
									
									<input id="txtFecVencimiento" name="txtFecVencimiento" type="text"  class="form-control tamanoMaximo" value='${fechaVencimiento}' disabled></input>
								</div>
							</div>
						</fieldset>	
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border"> Listado de Tipos de Documento </legend>
							<div class="form-group">
								<div class="col-md-12">	
								<div class="tab-content">
								<div id="accionesFiscalizacion" class="tab-pane fade in active">
								<div class="border-line panel-scroll">
									<div>
										<table id="tblTipoDocumentos" name="tblTipoDocumentos"  class="table table-striped" style="width: 100%;">
											<thead>
												<tr>
													<th width="20%" class="center">Seleccionar Tipo de Documento</th>
													<th width="30%" class="center">Tipo de Documento</th>	
													<th width="10%" class="center">Estado del Documento</th>
													<th width="40%" class="center">Motivo</th>
													<th ></th>
													<th ></th>
													<th ></th>
													<th ></th>
												</tr>
											</thead>
										</table>
									</div>
								</div>
								</div>
								</div>
								</div>
								</div>
							<div class="form-group">
								<div class="col-md-12">	
								<p>* S&oacute;lo puede adjuntar un archivo por tipo de documento</>
								</div>
							</div>
						</fieldset>	
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
						<legend class="scheduler-border"> Subir Archivos</legend>
							<div class="form-group">
							<div class="col-md-3"><Strong>Palabras Claves de B&uacute;squeda:</Strong></div>
							<div id="txtPalabrasBusqueda" class="col-md-9">
								<textarea id="txtPalabrasClave" name="txtPalabrasClave" rows="4" class="form-control form-control-textarea" maxlength="200" onpaste="return false;"
								placeholder='Ingresar las palabras claves de b&uacute;squeda separados por ";". Ejemplo: etiqueta1;etiqueta2'></textarea>
							</div>
							</div>	
							<div class="form-group">
								<div class="col-md-3"><Strong>Archivo Seleccionado:</Strong></div>
								<div id="txtArchivoSeleccionado" class="col-md-9">
									  <input type="hidden" id="txtNombreArchivo" name="txtNombreArchivo">
									  <input name="docFisico" id="docFisico" type="file" onchange='validarArchivoUpload();' class="form-control uploadStyle" style="height:30px !important;">
								</div>
								<div class="form-group"></div>	
								<div class="col-md-10 top-features-bg">
									<div class="input-group" id="manejadorArchivo">
										<!--<a id="anchorInputFile" style="position: absolute !important;" class="btn btn-primary" href="javascript:void(0)">Seleccionar Archivo</a>-->
										
									</div>
								</div>		
								
								<div class="col-md-2" align="right" id="dvSecBotones01">	
									<input type="button" id="btnAdjuntarArchivo" class="btn btn-primary" id="addArchivo" intermediateChanges="false" onClick="revalidarFormulario()" value="Adjuntar Archivo"></input>
								</div>	
							</div>	
							<div class="form-group">
							</div>	
							<div class="form-group">
								<div class="col-md-12">	
									<div class="tab-content">
									<div id="accionesFiscalizacion" class="tab-pane fade in active">
									<div class="border-line panel-scroll" id="divContenedorTabla">
									<table id="tblDocumentos" name="tblDocumentos"  class="table display" cellspacing="0" style="width: 100%;">
										<thead>
											<tr>
												<th width="3%" class="center">N&deg;</th>
												<th width="20%" class="center">Tipo de Documento</th>	
												<th width="27%" class="center">Nombre del Archivo</th>
												<th width="10%" class="center">Tama&ntilde;o del Archivo</th>
												<th width="10%" class="center">Ver Archivo</th>
												<th width="25%" class="center">Palabras Claves de B&uacute;squeda</th>
												<th width="10%" class="center">Opci&oacute;n</th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
												<th></th>
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
								<div class="col-md-6">	
								<p >* El tama&ntilde;o m&aacute;ximo permitido por el Total de Documentos es ${tamanoMaximoPermitidoIntranet} MB </p>
								</div>
								<div id="pesoTotal" ="col-md-6">	
								</div>
							</div>
						</fieldset>	
						<fieldset  style="margin : 15px 15px !important">						
						<div class="form-group">
							<div class="col-md-6" align="right" id="dvSecBotones01">	
								<input type="button" class="btn btn-primary" id="btnRegresar" intermediateChanges="false" onClick="validarSalida()" value="Regresar"></input>
							</div>	
							<div class="col-md-6" align="left" id="dvSecBotones01">	
								<input type="button" class="btn btn-primary" id="btnRegistrar" intermediateChanges="false" value="Subir Documentos" onClick="confirmarEnviar()"></input>
							</div>	
						</fieldset>	
						
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
						<button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="volverPagina();" >
								<span aria-hidden="true">&times;</span>
						</button>
						<div id="dlgTitle">TITULO</div>
					</div>
					<div class="panel-body">
						<div id="dlgMsj" class="modal-body text-center" style="word-wrap: break-word">TEXTO</div>
						<div id="dlgBtns" class="text-center">
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>	
	
	<script type="text/javascript">
	
	var titulos = ${titulos};
	var excepciones =${excepciones};
	var listaTipDocExp =${lisT6613DocExpVirtBean};
	var desTipoDocumento;
	var numDocumento;
	var codTipDocumento;
	var motivoDocumento;
	var fecDocumento;
	var extensiones_permitidas = ${listExtensionesPermitidas};
	var tamanoMaximoPermitido = ${tamanoMaximoPermitidoIntranet};
	var peso;
	var mimeType;
	var tamanoSuperado;
	var metodoInvocar;
	var indexDocumentos;
	
    $(function () {
				
		$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
		//makeInputMask( '#txtPalabrasClave', "(a|9|;){1,200}", 200, '' );	
		
		/*$("#txtPalabrasClave").keypress(function(e){
			   var _msgLenght = $(this).val().length;
			   
			   if (_msgLenght > 199) {
					$(this).val($(this).val().substring(0, 199));
			   } else {
					var txt = String.fromCharCode(e.which);
					
					if (!txt.match(/[A-Za-z0-9;αινσϊ]/)) {
						return false;
					}	
			   }
		});*/
		$("#txtPalabrasClave").keypress(function(e){
			var lengthF = $(this).val();

			if (lengthF.length > 199){
				e.preventDefault();
			}
		});
		$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
		$("#busqueda").keypress(function(e){
			var lengthF = $(this).val();

			if (lengthF.length > 199){
				e.preventDefault();
			}
		});
		construirTablaTipoDocumentos( listaTipDocExp );
		construirTablaDocumentos( [] );
		
		
		var table = $('#tblDocumentos').DataTable();
 
		$('#container').css( 'display', 'block' );
		table.columns.adjust().draw();
		$('#tblTipoDocumentos tbody').on('mouseenter', 'tr', function() {
			$(this).addClass("selected");
		});	
		$('#tblTipoDocumentos tbody').on( 'mouseleave', 'tr', function () {
			 $(this).removeClass("selected");
			 //$('#tblTipoDocumentos').dataTable().fnDraw();
		} );
		$('#tblDocumentos tbody').on('mouseenter', 'tr', function() {
			$(this).addClass("selected");
		});	
		$('#tblDocumentos tbody').on( 'mouseleave', 'tr', function () {
			 $(this).removeClass("selected");
			 //$('#tblDocumentos').dataTable().fnDraw();
		} );
		
		habilitarRadio();
		$('#docFisico').prop('disabled', true);
		$('#txtPalabrasClave').prop('disabled', true);
		
		$('#anchorInputFile').prop('disabled', true);
		document.getElementById('pesoTotal').innerHTML="<Strong>TOTAL "+0.0+" MB</Strong>";
		
		$('#btnAdjuntarArchivo').prop('disabled', true);
		
		var table = $('#tblTipoDocumentos').DataTable();
		$('#tblTipoDocumentos tbody').on( 'mouseenter', 'tr', function () {
			indexDocumentos = table.row( this ).index();
			
		} );
		
		$('#tblTipoDocumentos').on( 'page.dt', function () {
			var info = table.page.info();
			$('input').filter(':radio').prop('checked',false);
		} );
	
		$('#frmCargaArchivo').bootstrapValidator({
			excluded: [':disabled'],
			fields: {
				txtPalabrasClave: {
                    validators: {
						//notEmpty: {//07/06/2016 Alexander
                        // message: "Debe Ingresar al menos una Palabra Clave de B&uacute;squeda."
                      	// },
						regexp: {
							regexp: /^[0-9a-zA-Z\u00F1\u00D1\u00E1\u00E9\u00ED\u00F3\u00FA\u00C1\u00C9\u00CD\u00D3\u00DA/';']+$/,
							message: 'Debe ingresar una o m&aacute;s palabras claves de b&uacute;squeda que cumplan con el siguiente formato: etiqueta1;etiqueta2.'
						},
						callback: {
                            message: 'Debe ingresar una o m&aacute;s palabras claves de b&uacute;squeda que cumplan con el siguiente formato: etiqueta1;etiqueta2.',
                            callback: function (value, validator, $field) {
								var palabra = $('#txtPalabrasClave').val();
								return(validarPalabraClaveBusqueda(palabra));
                            }
                        }
						
                    }
				}
			}
		}).on('success.form.bv', function(e) {
            e.preventDefault();
			agregarListaArchivo();
    	});
		
	})  
	
	function subirDocumentos(){
	
	//	$('html').block({message: '<h1>Procesando...</h1>'});
		var datos = [];
		var contador=0;
	    var dataJson = $('#tblDocumentos').DataTable().rows().data();	
		for (var i = 0; i < dataJson.length; i++) {
			var obj = new Object;
			obj.CODDOCUMENTO = dataJson[i][8];
			obj.PALABRASBUSQUEDA =dataJson[i][5];
			obj.CODTIPDOC =dataJson[i][9];
			obj.FECDOC =dataJson[i][10];
			obj.MIMETYPE =dataJson[i][11];
            obj.NOMBREARCHIVO =dataJson[i][2];
            obj.MOTIVO=dataJson[i][12];
            //Inicio LEstrada SNADE307-1031
            obj.CORRELATIVO=dataJson[i][0];
             //Fin LEstrada SNADE307-1031
			datos[contador] = obj;
			contador++;
		}
		
		var listaDocumentosSel = JSON.stringify(datos);
		$('#hidListaDocumentosSel').val(listaDocumentosSel);
		metodoInvocar="subirDocumentos";
		$('html').block({message: '<h1>Procesando</h1>'});

		var form=document.getElementById('frmAux');
		var inputrequerimientoOrigen=document.createElement('input');
		inputrequerimientoOrigen.setAttribute('type','hidden');
		inputrequerimientoOrigen.setAttribute('name','txtNumRequerimiento');
		inputrequerimientoOrigen.setAttribute('value',$('#txtNumRequerimiento').val());

		form.appendChild(inputrequerimientoOrigen);

		$('#frmAux').submit();
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
	function validarArchivoUpload() {
		var input = $('#docFisico');
		var nombreArchivo =  label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
		if(nombreArchivo.length>100){
			var control = $("#docFisico");
			control.replaceWith( control = control.clone( true ) );
			control.val(null);
			$("#docFisico").val("");
			mostrarDlgInfo("Adjuntar Documentos Internos", "El nombre del archivo a cargar debe tener un m&aacute;ximo de 100 car&aacute;cteres.");		
		}
		else{
			$('#btnAdjuntarArchivo').prop('disabled', true);
			$('#hidDocumentosSel').val(numDocumento);
			metodoInvocar = 'obtenerTamano';	
			$('#hidDocumentosSel').val(numDocumento);
			//$('html').block({message: '<h1>Procesando</h1>'});
			$("#docFisico").appendTo($('#frmAux'));
			$('#frmAux').submit();
			$("#docFisico").appendTo($('#txtArchivoSeleccionado'));
		}
	}
	
	function agregarListaArchivo(){
		
		//$('#frmCargaArchivo').bootstrapValidator('revalidateField', 'hidPesoPermitido');
		var numeroFila = obtenerNumeroFila();
		
		var nombreArchivo = $('#txtNombreArchivo').val();
		var mensaje="El tipo de archivo seleccionado no est&aacute permitido subirlo al expediente, favor de verificar.";
		if(!validarExtension(nombreArchivo)){
			mostrarDlgInfo(titulos.tituloDefecto,mensaje);
			return;
		};		
		
		mensaje="El tama&ntilde;o de los archivos seleccionados es 0 MB";
		if(peso == 0) {
		   mostrarDlgInfo(titulos.tituloDefecto,mensaje);
		   return;
		}	
		
		mensaje="El tama&ntilde;o de los archivos seleccionados excede el l&iacute;mite permitido de "+tamanoMaximoPermitido+" MB.";
		
		if(!validarTamanoCarga(peso)){
			mostrarDlgInfo(titulos.tituloDefecto,mensaje);
			return;
		}
		
		
		
		mensaje="Debe ingresar una o m&aacute;s palabras claves de b&uacute;squeda que cumplan con el siguiente formato: etiqueta1;etiqueta2.";
		var palabrasClaveBusqueda = $('#txtPalabrasClave').val();
		/*	if(palabrasClaveBusqueda==""){ 07/06/2016 Alexander 
			mostrarDlgInfo(titulos.tituloDefecto,mensaje);
			return;
		}*/

		var tamanoArchivo = parseFloat(Math.round(peso/1048576*100000)/100000).toFixed(2);
		var nombreArchivo = $('#txtNombreArchivo').val();
		var verArchivo =  "<span class='glyphicon glyphicon-download-alt' aria-hidden='true' onclick='descargarArchivo("+numeroFila+")'></span>";
		var palabasClaves = $('#txtPalabrasClave').val();
		var opcion = "<span name='"+numeroFila+"' class='glyphicon glyphicon-remove' aria-hidden='true' onclick='confirmarEliminar("+numeroFila+")'></span>";
		
		var data = [numeroFila,desTipoDocumento,nombreArchivo,tamanoArchivo+' MB',verArchivo,palabasClaves,opcion,tamanoArchivo,numDocumento,codTipDocumento,fecDocumento,mimeType,motivoDocumento];
		var row = $('#tblDocumentos').DataTable().row;
		
		row.add(data).draw( false );
		
		$('#txtNombreArchivo').val("");
		$('#txtPalabrasClave').val("");
		
		$('#frmCargaArchivo').bootstrapValidator('resetForm', true);
		var pesoTotal = ObtenerPesoTotal();
		document.getElementById('pesoTotal').innerHTML="<Strong>TOTAL "+parseFloat(pesoTotal).toFixed(2)+" MB</Strong>";
		
		$('#docFisico').prop('disabled', true);
		$('#txtPalabrasClave').prop('disabled', true);
		$('#anchorInputFile').prop('disabled', true);
		$('#btnAdjuntarArchivo').prop('disabled', true);
		document.getElementById(numDocumento).style.display='none';
		desTipoDocumento="";
		numDocumento="";
		codTipDocumento="";
		fecDocumento="";
		tamanoSuperado= false;
		var input = $("#docFisico");
		peso=0;
		mimeType = "";
		input.replaceWith(input.val('').clone(true));
		
	}	
	
	function eliminarFila(fila){
		
		var tabla = $('#tblDocumentos').DataTable();
		
		var temp = tabla.rows().data();
		numDocumento =temp[fila-1][8];
		document.getElementById(numDocumento).style.display='inline';
		$('#'+numDocumento).prop('checked', false);
		
		tabla.row('#'+fila).remove().draw( false );
		//Reordenamos la numeracion
		var dataJson = $('#tblDocumentos').DataTable().rows().data();	
		for (var i = 0; i < dataJson.length; i++) {
			fila=i+1;
			dataJson[i][0]=i+1;
			dataJson[i][6] = "<span name='"+dataJson[i][0]+"' class='glyphicon glyphicon-remove' aria-hidden='true' onclick='confirmarEliminar("+fila+")'></span>"
			var data = [dataJson[i][0],dataJson[i][1],dataJson[i][2],dataJson[i][3],dataJson[i][4],dataJson[i][5],dataJson[i][6],dataJson[i][7],dataJson[i][8],dataJson[i][9],dataJson[i][10],dataJson[i][11],dataJson[i][12]];
			$('#tblDocumentos').DataTable().row($('#tblDocumentos').DataTable().row(i)).data( data ).draw();
		}
					
		var pesoTotal = ObtenerPesoTotal();
		document.getElementById('pesoTotal').innerHTML="<Strong>TOTAL "+pesoTotal+" MB</Strong>";
					
		//limpiamos el input file asociado
		var input = $("#docFisico");
		input.replaceWith(input.val('').clone(true));		
		
		var a = $('#divContenedorTabla').width()
		$("#tblDocumentos_wrapper").css("width", a);
		
		var b = $('#tblDocumentos').width();
		$("#tblDocumentos_wrapper").css("width", b);
	}
	function descargarArchivo(fila){
		
		var tabla = $('#tblDocumentos').DataTable();
		
		var temp = tabla.rows().data();
		numDocumento =temp[fila-1][8];
		var nombreArchivo = temp[fila-1][2];
		$('#hidDocumentosSel').val(numDocumento);
		$('#hidNombreDoc').val(nombreArchivo);
		$('#hidTxtNumRequerimiento').val($('#txtNumRequerimiento').val());
		metodoInvocar = "descargarDocumento";
		var archivo = $('#docFisico');
		$('html').block({message: '<h1>Procesando</h1>'});
		if (archivo != undefined) {
			$('html').block({message: '<h1>Procesando</h1>'});
			$('#frmAux').submit();
		}
		$('html').unblock();
						
	}
	function obtenerNumeroFila(){
		var numeroFila=1;
		var dataJson = $('#tblDocumentos').DataTable().rows().data();	
		for (var i = 0; i < dataJson.length; i++) {
			numeroFila++;
		}
		return numeroFila;
	}
	
	function validarTamanoCarga(pesoAdicional){
		var pesoadd = Math.round(pesoAdicional/1048576*100000)/100000;
		var pesoTotal = parseFloat(ObtenerPesoTotal());
		if(pesoTotal+pesoadd>tamanoMaximoPermitido){
			return false;
		}
		return true;
	}
	
	function ObtenerPesoTotal(){
	
		var pesoTotal=0;
		var dataJson = $('#tblDocumentos').DataTable().rows().data();	
		for (var i = 0; i < dataJson.length; i++) {
			pesoTotal= pesoTotal + parseFloat(dataJson[i][7]);
		}
		return pesoTotal;
	}
	
	function setearNombre(id){
		var input = $('#docFisico');
		
		var nombreArchivo =  label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
		 $('#txtNombreArchivo').val(nombreArchivo);
		 $('#hidPesoPermitido').val(nombreArchivo);
		 
		 if(nombreArchivo==""){
			$('#btnAdjuntarArchivo').prop('disabled', true);
		 }else{
			$('#btnAdjuntarArchivo').prop('disabled', false);
		 }
	}
	
	function validarDocumentosPorSubir(){
		var datos = [];
		var contador=0;
	    var dataJson = $('#tblDocumentos').DataTable().rows().data();	
		for (var i = 0; i < dataJson.length; i++) {
			contador++;
		}
		if(contador==0){
			return false;
		}else{
			return true;
		}
	}
	function validarSalida(){
		
		if(validarDocumentosPorSubir()){
			titulo = titulos.tituloDefecto;
			msj = "Existen documentos que fueron seleccionados para subir, &iquest;Desea Salir?";
			botones = [];
			
			var botonSi = $("<input/>").attr(
				{
					value: "Si", 
					type: "button", 
					"class": "btn dlgButton btn-primary", 
					"data-dismiss" : "modal", 
					onclick : "volverPagina()"
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
			volverPagina();
		}
	}
	
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
				{data : "numCorDoc", sClass: 'center alinearCentrado', 
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
								name: "Seleccione",
								id: data,
								onclick: 'habilitarBtnExaminar()'
								
							}
						).wrap('<div></div>').parent().html();
					}
				},
				{data : "desTipdoc", sClass: 'left alinearCentrado'},
				{data : "desEstadoDocumento", sClass: 'left alinearCentrado'},
				{data : "desMotsoldoc", sClass: 'left alinearCentrado'},
				{data : "numCorDoc", sClass: 'hidden'},
				{data : "estadoDocumentoReq", sClass: 'hidden'},
				{data : "fecDoc", sClass: 'hidden'},
				{data : "codTipDoc", sClass: 'hidden'}
			],
			data: dataGrilla,
            ordering: true,
            searching: false,
            paging: false,
			//"sScrollY":"250px",
            bScrollAutoCss: true,
            bStateSave: false,
            bAutoWidth: false,
            bScrollCollapse: true,
            pagingType: "full_numbers",
            //iDisplayLength: 5,
            //responsive: true,
            bLengthChange: false,
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
	function habilitarRadio(){
		var dataJson = $('#tblTipoDocumentos').DataTable().rows().data();	
		for (var i = 0; i < dataJson.length; i++) {
			if(dataJson[i].estadoDocumentoReq=="02"){
				document.getElementById(dataJson[i].numCorDoc).style.display='none';
			}
		}
	}
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
				{sClass: "center alinearCentrado"},
				{sClass: "left alinearCentrado"},
				{sClass: "alinearCentrado"},
				{sClass: "alinearCentrado"},
				{sClass: "center alinearCentrado"},
				{sClass: "alinearCentrado"},
				{sClass: 'center alinearCentrado'},
				{sClass: 'hidden'},
				{sClass: 'hidden'},
				{sClass:'hidden'},
				{sClass:'hidden'},
				{sClass:'hidden'},
				{sClass:'hidden'}
			],
			data: dataGrilla,
            ordering: true,
            searching: false,
            paging: false,
			//"sScrollY":"250px",
            bScrollAutoCss: true,
            bStateSave: false,
            bAutoWidth: false,
            bScrollCollapse: true,
            pagingType: "full_numbers",
            //iDisplayLength: 5,
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
	
	function habilitarBtnExaminar(){
		$('#docFisico').prop('disabled', false);
		$('#txtPalabrasClave').prop('disabled', false);
		$('#anchorInputFile').prop('disabled', false);
		var dataJson = $('#tblTipoDocumentos').DataTable().rows().data();	
		desTipoDocumento = dataJson[indexDocumentos].desTipdoc;
		numDocumento = dataJson[indexDocumentos].numCorDoc;
		codTipDocumento=dataJson[indexDocumentos].codTipDoc;
		fecDocumento=dataJson[indexDocumentos].fecDoc;
		motivoDocumento=dataJson[indexDocumentos].desMotsoldoc;
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
	
	function revalidarFormulario(){
		$('#frmCargaArchivo').submit();
	}		
	
	function mostrarPagError() {
		
		var formURL = '${pageContext.request.contextPath}/error.htm?action=cargarPaginaError';
		document.forms.frmCargaArchivo.action = formURL;
		document.forms.frmCargaArchivo.method = "post";
		document.forms.frmCargaArchivo.submit();
		
	}
	function confirmarEliminar(fila){ 
		var tabla = $('#tblDocumentos').DataTable();
		
		var temp = tabla.rows().data();
		nombreArchivo =temp[fila-1][2];
		
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
	
	function confirmarEnviar(){
		var datos = [];
		var contador=0;
	    var dataJson = $('#tblDocumentos').DataTable().rows().data();	
		for (var i = 0; i < dataJson.length; i++) {
			contador++;
		}
		if(contador==0){
			mostrarDlgInfo(titulos.tituloDefecto, 'Debe adjuntar al menos un archivo.');
			return;
		}
		titulo = "SUBIR DOCUMENTOS AL EXPEDIENTE VIRTUAL";
		msj = "&iquest;Est&aacute; seguro que desea adjuntar los archivos al Requerimiento?";
		botones = [];
		
		var botonSi = $("<input/>").attr(
			{
				value: "Si", 
				type: "button", 
				"class": "btn dlgButton btn-primary", 
				"data-dismiss" : "modal", 
				onclick : "subirDocumentos()"
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

	function mostrarDlgInfoSalir(titulo, msj){ 
		
		botones = [];
		
		var aceptar = $("<input/>").attr(
			{
				value: "Aceptar", 
				type: "button", 
				"class": "btn dlgButton btn-primary", 
				"data-dismiss" : "modal", 
				onclick : "volverPagina()"
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
	
	function volverPagina(){
		$('html').block({message: '<h1>Regresando...</h1>'});
		var numExpedienteConsultar = $('#txtExpedienteVirtual').val();
		var  url = '${pageContext.request.contextPath}/cargarDocumento.htm?action=consultarRequerimientosPendientesView';
		$(location).prop( 'href', url);
	}
	
	function validarExtension(nombreArchivo){
		 extension = (nombreArchivo.substring(nombreArchivo.lastIndexOf("."))).toLowerCase(); 
		 for (var i = 0; i < extensiones_permitidas.length; i++) { 
			if ('.'+extensiones_permitidas[i].desParametro == extension) { 
				return true;
			} 
		}
		return  false;	
	}
	
	$("#frmAux").submit(function(e){
		var formObj = $(this);
		var iframeId = 'unique' + (new Date().getTime());
		var iframe = $('<iframe height="200" width="100" src="javascript:false;" name="'+iframeId+'" />');
	    iframe.hide();
	    formObj.attr('target', iframeId);
	    formObj.attr('action', '${pageContext.request.contextPath}/cargarDocumento.htm?action='+metodoInvocar); //METODO DEL CONTROLLER
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
					
				}else {
	    			jsonDataString = data.replace('<PRE>', '');
					jsonDataString = jsonDataString.replace('</PRE>', '');
					jsonDataString = jsonDataString.replace('<pre>', '');
					jsonDataString = jsonDataString.replace('</pre>', '');
				}
				
					response = jQuery.parseJSON(jsonDataString);
					
					if(metodoInvocar=="obtenerTamano"){
						peso = response.peso;
						mimeType = response.mimeType;
						$('#txtNombreArchivo').val(response.nombreArchivo);	
						$('#anchorInputFile').prop('disabled', false);
						$('#btnAdjuntarArchivo').prop('disabled', false);
					}else if(metodoInvocar="subirDocumentos"){
						var mensaje = response.mensajeRetorno;
						var error = response.error;
						if(error=="000"){
							mostrarDlgInfoSalir(titulos.tituloDefecto,mensaje);
						}else{
						mostrarDlgInfo(titulos.tituloDefecto,mensaje);
						}
						
					}else{
					$('html').unblock();
					var mensaje = "Ocurri&oacute un error al subir los documentos."
					mostrarDlgInfo(titulos.tituloDefecto,mensaje);	
					} 
				$('html').unblock();
			} 

		});
			
	});
	function validarPalabraClaveBusqueda(palabra){
		var pal = palabra.split("");
		if(pal[0]==";"){
			return false;
		}
		var contiene = palabra.indexOf(";;");
		if(contiene>0){
			return false;
		}
		
		return true;
	}
	 
 </script>
</html>