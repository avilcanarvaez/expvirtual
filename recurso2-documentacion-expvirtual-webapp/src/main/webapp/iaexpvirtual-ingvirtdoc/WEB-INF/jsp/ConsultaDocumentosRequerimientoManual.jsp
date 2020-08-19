<!DOCTYPE html> 
<html lang="es">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=EDGE" />
	<meta name="viewport" content="initial-scale = 1.0, user-scalable = no,  width=device-width">
	<title>SISTEMA INTEGRADO DE EXPEDIENTE VIRTUAL - SIEV - IU013</title>

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
			width : 100% !important;
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

		.dataTables_wrapper .dataTables_paginate {
			float: initial !important;
			text-align: center !important;
			padding-top: 0.25em;
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
			overflow-x: scroll;
		}	
	</style>

 <script type="text/javascript"> 
		var titulos =  [];
		var listaDocumentos = ${listadocumentos};

	    $(function () {
			construirTableDocumento( listaDocumentos);
			$('#tblDocumentos tbody').on('mouseenter', 'tr', function() {
				$(this).addClass("selected");
			});	
			$('#tblDocumentos tbody').on( 'mouseleave', 'tr', function () {
				 $(this).removeClass("selected");
				 //$('#tblRequerimientos').dataTable().fnDraw();
			});
			
			$('#tblDocumentos').on('page.dt',function(){
	// 			contentDocumentos.hide();
			});
			
			$('#tblDocumentos').on('click','.descargar-documento',function(){
				var objJSON=$('#tblDocumentos').DataTable().row($(this).parents('tr')).data();
				var codIdentificadorECM = $(this).data('id');
				var	descripcionArchivo = $(this).data('desc');
	// 			console.log(objJSON);
				var url = "regReqManualControl.htm?action=descargarArchivo&codIdentificadorECM="+ codIdentificadorECM +"&nombreArchivo="+encodeURI(descripcionArchivo);
				window.open(url);
			});
		})   
   
	    function construirTableDocumento(dataGrilla){
	    	$('#tblDocumentos').DataTable().destroy();
	    	$('#tblDocumentos').DataTable({
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
					{data : "correlativo", sClass: 'center alinearCentrado',"defaultContent":""},
					{data : "desTipdoc", sClass: 'left alinearCentrado',"defaultContent":""},
					{data : "fechaDocumento", sClass: 'center alinearCentrado',"defaultContent":""},
					{data:"desMotsoldoc",sClass:'center alinearCentrado',"defaultContent":""},
					{data : "desEstadoDocumento", sClass: 'left alinearCentrado',"defaultContent":""},
					{data : "codIdentificadorECM", sClass: 'center alinearCentrado',render:function(data,row,full){
						if ( (data=='' || data.length==0 ) ||
							 (full.descripcionArchivo=='-' || full.descripcionArchivo.length==1) ){
							return '<span data-id="'+data+'" style="margin-left:8px;width: 18px;height: 18px;">-</span>';
						}else{
							return '<span class="descargar-documento glyphicon glyphicon-download-alt" style="margin-left:8px;width: 18px;height: 18px;"'+
								   'data-id="'+data+'" data-desc="'+full.descripcionArchivo+'" ></span>';
						}
					  }
					},
					{data : "desEstadoEnvioDoc", sClass: 'left alinearCentrado',"defaultContent":""}
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
				iDisplayLength		:	5,
				//responsive			:	true,
				bLengthChange		: 	false
			} );
	    }


			
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
			
			$('#modalDlg').modal({backdrop: 'static', keyboard: false});
			$('#modalDlg').modal('show');			
		}

		function volverPagina(){
			/*Inicio jquispe [06/07/2016]
			var numExpVirtual = $('#numExpVirtual').val();
			$('html').block({message: '<h1>Regresando...</h1>'});
			var url='${pageContext.request.contextPath}/regReqManualControl.htm?action=consultarDetalleExpedienteVirtualView&numExpedienteVirtual='+numExpVirtual;
			$(location).prop( 'href', url);
			Fin jquispe [06/07/2016]		
			*/		
			
			//Inicio jquispe [06/07/2016] Regresa igual que la pagina de Consulta Requerimientos del Expediente Virtual que ya no se muestra.
			$('html').block({message: '<h1>Regresando...</h1>'});
			var  url = '${pageContext.request.contextPath}/regReqManualControl.htm?action=mostrarView&pageView=ConsultaExpedientesVirtualesIntranet';
			$(location).prop( 'href', url);
			//Fin jquispe [06/07/2016]	
		}

		function ingresarRegistroRequerimiento(){
			$('html').block({message: '<h1>Procesando...</h1>'});
			var numExpVirtual = $('#numExpVirtual').val();
			var  url = '${pageContext.request.contextPath}/regReqManualControl.htm?action=registrarRequerimientoView&numExpedienteVirtual='+numExpVirtual;
			$(location).prop( 'href', url);
		}

		function exportarExcelDocumentos(){
			var dataExp = $('#tblDocumentos').dataTable().fnGetData();
			if(dataExp.length > 0){
				var listaCadena = JSON.stringify(dataExp);
			    var formURL = 'regReqManualControl.htm?action=exportarExcelDocumentos';
				document.forms.formPrincipalDocumentos.action = formURL;
				document.forms.formPrincipalDocumentos.method = "POST";
				$('#campoHiddenExpDoc').val('');
				$('#campoHiddenExpDoc').val(listaCadena);	
				$('html').block({message: '<h1>Procesando</h1>'});
				document.forms.formPrincipalDocumentos.submit();
				$('html').unblock();
			}
		}

	</script>
</head>
<body>
	<div id="container" class="container" style="width: 95%">
		<div>
			<div class="row">
				<div class="panel panel-primary">
					<div class="panel-heading align="center">
						<h3 class="panel-title" align="center">CONSULTA DE DOCUMENTOS DEL REQUERIMIENTO</h3>
						<form id="frmPrincipal" class="form-horizontal" role="form">
						</form>
					</div>
				</div>	
				<div class="panel panel-primary">
					<form class="form-horizontal" role="form" name="frmDatosExpediente" id="frmDatosExpediente" method="post">
						<input type="hidden" id="numExpVirtual" value='${t6614ExpVirtualBean.numExpedienteVirtual}'>
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
									<input name="txtExpedienteVirtual" id="txtExpedienteVirtual" type="text"  class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.numExpedienteVirtual}' disabled></input>
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
									<input name="txtFechaRegExpediente" id="txtFechaRegExpediente" type="text"  class="form-control tamanoMaximo" value='${fechaRegExpediente}' disabled></input>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"><Strong>Responsable del Expediente:</Strong></div>
								<div class="col-md-5">
									<input name="txtRespExpediente" id="txtRespExpediente" type="text" class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.nombreResponsable}' disabled></input>
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
								<div class="col-md-2"><Strong>Origen:</Strong></div>
								<div class="col-md-3">
									<input type="text"  class="form-control tamanoMaximo" value='${t6620RequerimBean.desOrigRequerimiento}' disabled></input>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"><Strong>Fecha de Registro:</Strong></div>
								<div class="col-md-3">									
									<input type="text"  class="form-control tamanoMaximo" value='${t6620RequerimBean.fecRequerimiento}' disabled></input>
								</div>
								<div class="col-md-2"><Strong>Fecha de Vencimiento:</Strong></div>
								<div class="col-md-3">									
									<input type="text"  class="form-control tamanoMaximo" value='${t6620RequerimBean.fecVencimiento}' disabled></input>
								</div>
							</div>
							<div class="form-group">								
								<div class="col-md-2"><Strong>Estado:</Strong></div>
								<div class="col-md-3">
									<input type="text"  class="form-control tamanoMaximo" value='${t6620RequerimBean.desEstado}' disabled></input>
								</div>
								<div class="col-md-2"><Strong>Tipo de Cierre:</Strong></div>
								<div class="col-md-3">
									<input type="text" class="form-control tamanoMaximo" value='${t6620RequerimBean.desTipCierr}' disabled></input>
								</div>
							</div>
						</fieldset>														
					</form>	
					<form id="formPrincipalDocumentos" class="form-horizontal" role="form">	
						<div class="col-md-12" align="right" id="dvSecBotones01">	
							<input id="campoHiddenExpDoc" type="hidden" name="listadoDocumentosCadena"/>
							<input id="hiddenNumExpOrigen" type="hidden" name="hiddenNumExpOrigen" value='${t6614ExpVirtualBean.numExpedienteOrigen}' />
							<input id="hiddenNumExpVirtualDoc" type="hidden" name="hiddenNumExpVirtualDoc" value='${t6614ExpVirtualBean.numExpedienteVirtual}'/>
							<input id="hiddenNumRuc" type="hidden" name="hiddenNumRuc" value='${t6614ExpVirtualBean.numRuc}'/>
							<input id="hiddenRazonSocial" type="hidden" name="hiddenRazonSocial" value='${razonSocial}'/>
							<input id="hiddenTipoProceso" type="hidden" name="hiddenTipoProceso"  value='${t6614ExpVirtualBean.desProceso}'/>
							<input id="hiddenTipoExpediente" type="hidden" name="hiddenTipoExpediente" value='${t6614ExpVirtualBean.desTipoExpediente}'/>
							<input id="hiddenFechaDocOrigen" type="hidden" name="hiddenFechaDocOrigen" value='${fechaDocOrigen}' />
							<input id="hiddenFechaRegExp" type="hidden" name="hiddenFechaRegExp" value='${fechaRegExpediente}'/>
							<input id="hiddenResponsable" type="hidden" name="hiddenResponsable" value='${t6614ExpVirtualBean.nombreResponsable}'/>
							<input id="hiddenNumRequerimiento" type="hidden" name="hiddenNumRequerimiento" value='${numRequerimientoOrigen}'/>
						</div>	
					</form>						
					<!--- body documentos-->
					<fieldset id="contentDocumentos" class="scheduler-border" style="margin : 15px 15px !important">
						<legend class="scheduler-border">Lista de Documentos del Requerimiento</legend>
						<div class="form-group">
							<div class="col-md-12">	
								<div class="tab-content">
									<div id="accionesFiscalizacion" class="tab-pane fade in active">
										<div class="border-line panel-scroll">
											<table id="tblDocumentos" class="table display table table-striped dataTable no-footer" cellspacing="0" style="width: 100%;">
												<thead>
													<tr>
														<th width="5%" class="text-center">N&deg;</th>
														<th width="15%" class="text-center">Nombre Documento</th>
														<th width="15%" class="text-center">Fecha Subida</th>
														<th width="15%" class="text-center">Motivo</th> 
														<th width="15%" class="text-center">Estado del documento</th>
														<th width="15%" class="text-center">Ver Documento</th>
														<th width="20%" class="text-center">Estado del Envio del Doc.</th>
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
							<div class="col-md-4">	
							</div>
							<div class="col-md-2" align="right" id="dvSecBotones01">	
								<input type="button" class="btn btn-primary" id="btnRegresar" intermediateChanges="false" onClick="volverPagina()" value="Regresar"></input>
							</div>	
							<div class="col-md-2" align="left" id="dvSecBotones01"><!--   Inicio [gangles 15/08/2016] -->	
								<input type="button" class="btn btn-primary" id="btnExportar" intermediateChanges="false" onClick="exportarExcelDocumentos()" value="Exportar a Excel"></input>
							</div><!--   Fin [gangles 15/08/2016] -->
							<!-- <div class="col-md-6" align="left" id="dvSecBotones01">	
								<input type="button" class="btn btn-primary" id="btnRegistrar" intermediateChanges="false" value="Registrar Requerimiento" id="btnGuardar" onClick="ingresarRegistroRequerimiento()"></input>									
							</div>-->
							<div class="col-md-4">	
							</div>	
						</div>
					</fieldset>	
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
	</body>	
</html>