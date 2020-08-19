
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=EDGE" />
<meta name="viewport"
	content="initial-scale = 1.0, user-scalable = no,  width=device-width">
<title>Detalle de documentos -N&deg; Expediente Origen  ${t6614ExpVirtualBean.numExpedienteOrigen} </title>
<jsp:include page="ExpedienteCommonHeader.jsp" flush="true" />  	
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
						<h3 class="panel-title" align="center">Detalle de documentos -N&deg; Expediente Origen ${t6614ExpVirtualBean.numExpedienteOrigen}</h3>
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
								<div class="col-md-1"><Strong>RUC:</Strong></div>
								<div class="col-md-2">
									<input name="txtRuc" id="txtRuc" type="text" class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.numRuc}' disabled></input>
								</div>
								<div class="col-md-1"><Strong>Raz&oacute;n Social:</Strong></div>
								<div class="col-md-5">
									<input name="txtRazonSocial" id="txtRazonSocial" type="text"  class="form-control tamanoMaximo" value='${razonSocial}' disabled></input>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-1"><Strong>Proceso:</Strong></div>
								<div class="col-md-2">
									<input name="txtProceso" id="txtProceso" type="text" class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.desProceso}' disabled></input>
								</div>
								<div class="col-md-1"><Strong>Tipo de Expediente:</Strong></div>
								<div class="col-md-2">
									<input name="txtTipoExpediente" id="txtTipoExpediente" type="text"  class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.desTipoExpediente}' disabled></input>
								</div>
								<div class="col-md-1"><Strong>Fecha de Registro del Expediente:</Strong></div>
								<div class="col-md-2">
									<input name="txtFechaRegExpediente" id="txtFechaRegExpediente" type="text"  class="form-control tamanoMaximo" value='${fechaRegExpediente}' disabled></input>
								</div>	
								<div class="col-md-1"><Strong>Estado del Expediente:</Strong></div>
								<div class="col-md-2">
									<input name="txtEstExpediente" id="txtEstExpediente" type="text"  class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.desEstado}' disabled></input>
								</div>								
							</div>
						</fieldset>	
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border"> Lista de Documentos </legend>
							<div class="form-group">
								<div class="col-md-12">	
									<table id="tblDocumentos" class="table table-bordered responsive" style="width: 100%;">
										<thead>
											<tr class="active">
												<th width="5%" class="text-center">N&deg;</th>
												<th width="20%" class="text-center">Tipo de Documento</th>
												<th width="20%" class="text-center">N&uacute;mero de Documento</th>
												<th width="15%" class="text-center">Origen registro documento</th>
												<th width="15%" class="text-center">N&deg; requerimiento (Si corresponde)</th>
												<th width="15%" class="text-center">Fecha de Registro documento</th>
												<th width="10%" class="text-center">Ver archivo</th>
												<th></th>
												<th></th>
												<th></th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-5" align="right" id="dvSecBotones01">	
									<button type="button" class="btn btn-primary" intermediateChanges="false" id="btnExportar" onclick="exportarExcelDocumentos()">Exportar a Excel</button>
								</div>	
									<div class="col-md-5" align="left" id="dvSecBotones02">	
									<input type="button" class="btn btn-primary" id="btnRegresar" intermediateChanges="false" onClick="volverPagina()" value="Regresar"></input>
								</div>	
							</div>
						</fieldset>		
					</form>
					<form id="formPrincipal" class="form-horizontal" role="form">	
						<div class="col-md-5" align="right" id="dvSecBotones01">	
							<input id="campoHiddenExp" type="hidden" name="listadoDocumentosCadena"/>
							<input id="hiddenNumExpOrigen" type="hidden" name="hiddenNumExpOrigen" value='${t6614ExpVirtualBean.numExpedienteOrigen}'/>
							<input id="hiddenNumExpVirtual" type="hidden" name="hiddenNumExpVirtual" value='${t6614ExpVirtualBean.numExpedienteVirtual}'/>
							<input id="hiddenNumRuc" type="hidden" name="hiddenNumRuc" value='${t6614ExpVirtualBean.numRuc}'/>
							<input id="hiddenTipoProceso" type="hidden" name="hiddenTipoProceso" value='${t6614ExpVirtualBean.desProceso}'/>
							<input id="hiddenTipoExpediente" type="hidden" name="hiddenTipoExpediente" value='${t6614ExpVirtualBean.desTipoExpediente}'/>
							<input id="hiddenDependencia" type="hidden" name="hiddenDependencia" value='${t6614ExpVirtualBean.desDependencia}'/>
							<input id="hiddenEstExpediente" type="hidden" name="hiddenEstExpediente" value='${t6614ExpVirtualBean.desEstado}'/>
							<input id="hiddenFechaGeneracion" type="hidden" name="hiddenFechaGeneracion" value='${fechaRegExpediente}'/>
							<input id="hiddenNumArchivo" type="hidden" name="hiddenNumArchivo"/>
							<input id="hiddenListaExp" type="hidden" name="hiddenListaExp"/>
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
		
 <script type="text/javascript">
 
	var titulos =  [];
	var listaTipoBusquedaExpediente = ${listaDocumentos};
	
    $(function () {
    	
	construirTablaDocumentos( listaTipoBusquedaExpediente );	
	
	})   
   
	function construirTablaDocumentos(dataGrilla) {
    	
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
						{data :	"numCorDoc", sClass: 'center alinearCentrado'},
						{data :	"desTipdoc", sClass: 'center alinearCentrado'},
						{data :	"numDoc" , sClass: 'center alinearCentrado'},
						{data :	"desOrigenDoc", sClass: 'center alinearCentrado' },
						{data :	"nroRequerim", sClass: 'center alinearCentrado' },
						{data :	"fechaRegistroDoc", sClass: 'center alinearCentrado'},
				        {data :	"codIdentificadorECM" ,sClass: 'center alinearCentrado', 
							render : function(data, type, row){
									var link;
									if(row.estadoDocumentoReq=="01"){
										link = "Solicitado";
									}else {
										link = '<a id='+row.numCorDoc+' href="consultaExpControl.htm?action=descargarArchivoDoc&codIdentificadorECM='+ row.codIdentificadorECM +'&numExpedienteVirtual='+row.numExpedienteVir+'&nombreArchivo='+row.descripcionArchivo+'"role="button" class="cimg glyphicon glyphicon-download-alt" target=_blank></a>';
				                	}
									return link;	
								}
							},
						{data : "estadoDocumentoReq", sClass: 'hidden'},
						{data :	"numExpedienteVir", sClass: 'hidden'},
						{data :	"descripcionArchivo", sClass: 'hidden'}
			],
			data: dataGrilla,
            ordering: true,
            searching: false,
            paging: true,
            bScrollAutoCss: true,
            bStateSave: false,
            bAutoWidth: false,
            bScrollCollapse: false,
            pagingType: "full_numbers",
            iDisplayLength: 15,
            responsive: true,
            bLengthChange: false,
			fnDrawCallback		:	function(oSettings) {
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
				"class": "btn dlgButton", 
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
	function exportarExcelDocumentos(){
		var dataExp = $('#tblDocumentos').dataTable().fnGetData();
		if(dataExp.length > 0){
			var listaCadena = JSON.stringify(dataExp);
		    var formURL = 'consultaExpControl.htm?action=exportarExcelDocumentos';
			document.forms.formPrincipal.action = formURL;
			document.forms.formPrincipal.method = "POST";
			$('#campoHiddenExp').val('');
			$('#campoHiddenExp').val(listaCadena);
			$('html').block({message: '<h1>Procesando</h1>'});
			document.forms.formPrincipal.submit();
			$('html').unblock();
		}
	}
	
	function volverPagina(){
		$('html').block({message: '<h1>Procesando</h1>'});
		var  url = '${pageContext.request.contextPath}/consultaExpControl.htm?action=mostrarView&pageView=ConsultaExpedienteOrigen';
		$(location).prop( 'href', url);
	}
	
	</script>		
	</body>	
</html>
