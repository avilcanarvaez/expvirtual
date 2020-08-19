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
  <script src="/a/js/bootstrap/3.2.0/js/jquery.blockUI.js"
	type="text/javascript"></script>
  
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
	
	.dataTables_paginate {
		padding-top: 0px !important;
		width : 100% !important;
	}
	
	.div100{
		width : 100% !important;
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

</head>
<body>
</br>
	<div id="container" class="container" style="width: 95%">
		<div>
			<div class="row">
				<div class="panel panel-primary">
					<div class="panel-heading align="center">
						<h3 class="panel-title" align="center">CONSULTA DE DOCUMENTOS DEL REQUERIMIENTO</h3>
					</div>
				</div>	
				<div class="panel panel-primary">
					<form class="form-horizontal" role="form" name="frmDatosDocumento" id="frmDatosDocumento">
					<iframe name="contenedor_subir_archivo"  id="contenedor_subir_archivo" style="display:none"></iframe>
						<input type="hidden" name="listaDocumentosSel" id="hidListaDocumentosSel">
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
								<div class="col-md-2"><Strong>N&deg; Expediente:</Strong></div>
								<div class="col-md-3">
									<input name="txtExpedienteOrigen" id="txtExpedienteOrigen" type="text" class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.numExpedienteOrigen}' disabled></input>
								</div>
								<div class="col-md-2"><Strong>Fecha del Expediente:</Strong></div>
								<div class="col-md-3">
									<input name="fechaExpediente" id="txtFechaExpediente" type="text"  class="form-control tamanoMaximo" value='${fechaRegExpediente}' disabled></input>
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
				</div>	
				<div class="panel panel-primary">
					<div class="panel-heading align="center">
						<h3 class="panel-title" align="center">DOCUMENTOS DEL REQUERIMIENTO</h3>
					</div>
				</div>	
				<div class="panel panel-primary">
					<fieldset class="scheduler-border" style="margin : 15px 15px !important">
						<legend class="scheduler-border"> Lista de Documentos del Requerimiento </legend>
						<div class="form-group">
							<div class="col-md-12">
								<div class="tab-content">
								<div id="accionesFiscalizacion" class="tab-pane fade in active">
								<div class="border-line panel-scroll">	
								<table id="tblTipoDocumentos" name="tblTipoDocumentos"  class="table display" cellspacing="0" style="width: 100%;">
									<thead>
										<tr>
											<th width="05%" class="text-center">N&deg;</th>
											<th width="35%" class="text-center">Nombre del Documento</th>	
											<th width="20%" class="text-center">Fecha de Subida</th>
											<th width="30%" class="text-center">Estado del Documento</th>
											<th width="10%" class="text-center">Ver Documento</th>
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
					</fieldset>	
					<fieldset  style="margin : 15px 15px !important">						
						<div class="form-group">
							<div class="col-md-6" align="right" id="dvSecBotones01">	
								<input type="button" class="btn btn-primary" id="btnRegresar" intermediateChanges="false" onClick="volverPagina()" value="Regresar"></input>
							</div>	
							<div class="col-md-6" align="left" id="dvSecBotones01">	
								<input type="button" class="btn btn-primary" id="btnRegistrar" intermediateChanges="false" value="Exportar Excel" onClick="exportar()"></input>
							</div>	
					</fieldset>	
				</form>
				<form id="formPrincipal" class="form-horizontal" role="form">	
					<div class="col-md-5" align="right" id="dvSecBotones02">	
						<input id="campoHiddenExp" type="hidden" name="listadoExpedientesCadena"/>
						<input id="hiddenNumExpOrigen" type="hidden" name="hiddenNumExpOrigen" value='${t6614ExpVirtualBean.numExpedienteOrigen}' />
						<input id="hiddenNumExpVirtualDoc" type="hidden" name="hiddenNumExpVirtualDoc" value='${t6614ExpVirtualBean.numExpedienteVirtual}'/>
						<input id="hiddenNumRuc" type="hidden" name="hiddenNumRuc" value='${t6614ExpVirtualBean.numRuc}'/>
						<input id="hiddenRazonSocial" type="hidden" name="hiddenRazonSocial" value='${razonSocial}'/>
						<input id="hiddenTipoProceso" type="hidden" name="hiddenTipoProceso"  value='${t6614ExpVirtualBean.desProceso}'/>
						<input id="hiddenTipoExpediente" type="hidden" name="hiddenTipoExpediente" value='${t6614ExpVirtualBean.desTipoExpediente}'/>
						<input id="hiddenFechaVencimiento" type="hidden" name="hiddenFechaVencimiento" value='${fechaVencimiento}' />
						<input id="hiddenFechaRegExp" type="hidden" name="hiddenFechaRegExp" value='${fechaRegExpediente}'/>
						<input id="hiddenFechaRequerimiento" type="hidden" name="hiddenFechaRequerimiento" value='${fechaRequerimiento}'/>
						<input id="hiddenNumRequerimiento" type="hidden" name="hiddenNumRequerimiento" value='${t6620RequerimBean.numRequerimOrigen}'/>
						<input id="hiddenEstRequerimiento" type="hidden" name="hiddenEstRequerimiento" value='${t6620RequerimBean.desEstado}'/>
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
	</body>	
	
	<script type="text/javascript">
	
	var titulos = ${titulos};
	var desTipoDocumento;
	var numDocumento;
	var lisDocumentos =  ${lisT6613DocExpVirtBean};
	
    $(function () {
	
		$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
		construirTablaTipoDocumentos( lisDocumentos );
		habilitarDescarga();
		var table = $('#tblTipoDocumentos').DataTable();
		$('#tblTipoDocumentos tbody').on('mouseenter', 'tr', function() {
			$(this).addClass("selected");
						
		});	
		$('#tblTipoDocumentos tbody').on( 'mouseleave', 'tr', function () {
			 $(this).removeClass("selected");
			 //$('#tblExpedientes').dataTable().fnDraw();
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
				{data : "correlativo", sClass: 'center alinearCentrado'},
				{data : "desTipdoc", sClass: 'left alinearCentrado'},
				{data : "strFechaCarga", sClass: 'left alinearCentrado'},
				{data : "desEstadoDocumento", sClass: 'left alinearCentrado'},
				{data : "numCorDoc", sClass: 'center alinearCentrado', 
					render : function(data, row){
						
						return jQuery('<span>').css(
							{
								"marginLeft"	:	"8px", 
								"width"			:	"18px", 
								"height"		:	"18px"
							}
						).attr(
							{
								"class" : "glyphicon glyphicon-download-alt",
								id: data,
								onclick: 'descargarDocumento(this.id)'
								
							}
						).wrap('<div></div>').parent().html();
					}
				},
				{data : "numCorDoc", sClass: 'hidden'},
				{data : "descripcionArchivo", sClass: 'hidden'},
				{data : "codIdentificadorECM", sClass: 'hidden'},
				{data : "estadoDocumentoReq", sClass: 'hidden'}

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
			bLengthChange		: 	false,
			fnDrawCallback		:	function(oSettings) {
			var a = $('#tblTipoDocumentos').width()
			$("#tblTipoDocumentos_wrapper").css("min-width", a);		
			habilitarDescarga();
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
	
	function habilitarDescarga(){
		var dataJson = $('#tblTipoDocumentos').DataTable().rows().data();	
		for (var i = 0; i < dataJson.length; i++) {
			if(dataJson[i].estadoDocumentoReq=="01"){
				if($('#'+dataJson[i].numCorDoc).length){
					document.getElementById(dataJson[i].numCorDoc).style.display='none';
				}
			}
		}
	}
	
	function descargarDocumento(id){
		var codIdentificadorECM;
		var descripcionArchivo;
		$('#btnExaminar').prop('disabled', false);
		var dataJson = $('#tblTipoDocumentos').DataTable().rows().data();	
		for (var i = 0; i < dataJson.length; i++) {
			if(dataJson[i].numCorDoc==id){
				
				codIdentificadorECM = dataJson[i].codIdentificadorECM;
				descripcionArchivo = dataJson[i].descripcionArchivo;
							
			}
		}
		var url = "consultaRequerim.htm?action=descargarArchivo&codIdentificadorECM="+ codIdentificadorECM +"&nombreArchivo="+encodeURI(descripcionArchivo);
		window.open(url);
	}
	
	
	function mostrarPagError() {
		
		var formURL = '${pageContext.request.contextPath}/error.htm?action=cargarPaginaError';
		document.forms.frmDatosDocumento.action = formURL;
		document.forms.frmDatosDocumento.method = "post";
		document.forms.frmDatosDocumento.submit();
		
	}
	
	function exportar(){
			var dataExp = $('#tblTipoDocumentos').dataTable().fnGetData();
			if(dataExp.length > 0){
				var listaCadena = JSON.stringify(dataExp);
				var formURL = 'consultaRequerim.htm?action=exportarExcelDocumentos';
				document.forms.formPrincipal.action = formURL;
				document.forms.formPrincipal.method = "POST";
				$('#campoHiddenExp').val('');
				$('#campoHiddenExp').val(listaCadena);
				document.forms.formPrincipal.submit();
			}else{
				mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion02);
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
		
		$('#modalDlg').modal({backdrop: 'static', keyboard: false});
		$('#modalDlg').modal('show');
		
	}
	
	function volverPagina(){
		$('html').block({message: '<h1>Regresando...</h1>'});
		var numExpedienteConsultar = $('#txtExpedienteVirtual').val();
		var  url = '${pageContext.request.contextPath}/consultaRequerim.htm?action=consultarRequerimientosContribuyenteView';
		$(location).prop( 'href', url);
	}
	
	/**detectar el navegador*/
	 
 </script>
</html>
	