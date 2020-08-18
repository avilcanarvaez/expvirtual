
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=EDGE" />
<meta name="viewport"
	content="initial-scale = 1.0, user-scalable = no,  width=device-width">
<title>Registro de Observaciones - N&deg; Expediente Virtual '${t6614ExpVirtualBean.numExpedienteVirtual}'</title>
<jsp:include page="ExpedienteCommonHeader.jsp" flush="true" />  
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
				
			.alinearCentrado {
				vertical-align: middle !important;
			}
		</style>
</head>
<body>
</br>
<div id="container" class="container" style="width: 95%">
		 <div class="modal fade" id="RegObservacionesForm" role="dialog" >
		 <div class="modal-dialog">
			<div class="row">
				<div class="panel panel-primary">
					<div class="panel-heading align="center">
					 <button type="button" class="close" data-dismiss="modal" aria-hidden="true" id="btnCerrar" onclick="cerrarVentana()">&times;</button>
						<h3 class="panel-title" align="left">Registro de Observaciones - N&deg; Expediente Virtual ${t6614ExpVirtualBean.numExpedienteVirtual}</h3>
					</div>
				</div>	
				<div class="panel panel-primary">
					<form class="form-horizontal" role="form" name="frmRegistrarObservacion" id="frmRegistrarObservacion" method="post" data-toggle="validator">
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
								<legend class="scheduler-border"> Datos de Registro</legend>
								<div class="form-group">
									<div class="col-md-1"><Strong>Observaci&oacute;n</Strong></Strong></div>
									<div class="col-md-12">
										<input class="form-control" name="desObservacion" id="txtDesObservacion" type="text" maxlength="200" class="form-control tamanoMaximo" placeHolder=""></input>
										<span class="help-block">Min. 20, max. 200 caracteres</span>
									</div>									
								</div>
								<div class="form-group">
								<div class="col-md-1" align="right" id="dvSecBotones01">	
									<button type="button" class="btn btn-primary" intermediateChanges="false" id="btnRegistrar" onclick="revalidarFormulario()">Registrar</button>
								</div>	
							</div>
						</fieldset>		
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border"> Listado de Observaciones </legend>
							<div class="form-group">
								<div class="col-md-12">	
									<table id="tblObservaciones"  class="table display" cellspacing="0" style="width: 100%;"><!--table table-striped table-bordered-->
										<thead>
											<tr class="active">
												<th width="5%" class="text-center">N&deg;</th>
												<th width="60%" class="text-center">Observaci&oacute;n </th>
												<th width="25%" class="text-center">Usuario Registro</th>
												<th width="10%" class="text-center">Fecha Registro</th>
											</tr>
										</thead>
									</table>
								</div>
								<div class="form-group">
									<div class="col-md-5" align="right" id="dvSecBotones01">	
										<button type="button" class="btn btn-primary" intermediateChanges="false" id="btnExportar" onclick="exportar()">Exportar a Excel</button>
									</div>	
								</div>
							</div>
						</fieldset>
					</form>
					<form id="formPrincipal" class="form-horizontal" role="form">	
						<div class="col-md-5" align="right" id="dvSecBotones01">	
							<input id="campoHiddenExp" type="hidden" name="listadoObservacionesCadena" />
							<input id="hiddenNumExpOrigen" type="hidden" name="hiddenNumExpOrigen" value='${t6614ExpVirtualBean.numExpedienteOrigen}'/>
							<input id="hiddenNumExpVirtual" type="hidden" name="hiddenNumExpVirtual" value='${t6614ExpVirtualBean.numExpedienteVirtual}'/>
							<input id="hiddenNumRuc" type="hidden" name="hiddenNumRuc" value='${t6614ExpVirtualBean.numRuc}'/>
							<input id="hiddenTipoProceso" type="hidden" name="hiddenTipoProceso" value='${t6614ExpVirtualBean.desProceso}'/>
							<input id="hiddenTipoExpediente" type="hidden" name="hiddenTipoExpediente" value='${t6614ExpVirtualBean.desTipoExpediente}'/>
							<input id="hiddenDependencia" type="hidden" name="hiddenDependencia" value='${t6614ExpVirtualBean.desDependencia}'/>
							<input id="hiddenEstExpediente" type="hidden" name="hiddenEstExpediente" value='${t6614ExpVirtualBean.desEstado}'/>
							<input id="hiddenFechaGeneracion" type="hidden" name="hiddenFechaGeneracion" value='${fechaRegExpediente}'/>
						</div>	
					</form>	
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
	</body>
	<script type="text/javascript">

	var titulos =  ${titulos};
	var excepciones =  ${excepciones};	
	var listaObservaciones = ${listaObservaciones};
	var avisos = ${avisos};
	var numExpedienteVirtual=${t6614ExpVirtualBean.numExpedienteVirtual};
	
	$(function () {
    	
		$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
		makeInputMask( '#desObservacion', "(a|9){20,400}", 400, '' );
		$('#RegObservacionesForm').modal('show');
		construirTablaObservaciones( listaObservaciones );
		$('#tblObservaciones').stickyTableHeaders();
		
		$('#frmRegistrarObservacion').bootstrapValidator({
			excluded: [':disabled'],
			fields: {
				desObservacion: { 
					validators: {				
						callback: {
					                message: 'Ingrese una Observaci&oacute;n.',
					                callback: function (value, validator, $field) {
									var observacion = $('#txtDesObservacion').val();
									if(observacion==""){
										$('#frmRegistrarObservacion').bootstrapValidator('updateMessage', 'desObservacion', 'callback', 'Ingrese una Observaci&oacute;n.');
										$('#frmRegistrarObservacion').bootstrapValidator('updateOption', 'desObservacion', 'callback', 'Ingrese una Observaci&oacute;n.');
										return false;
									}
									if(observacion.length<20){
										$('#frmRegistrarObservacion').bootstrapValidator('updateMessage', 'desObservacion', 'callback',  excepciones.excepcion01);
										$('#frmRegistrarObservacion').bootstrapValidator('updateOption', 'desObservacion', 'callback',  excepciones.excepcion01);
										return false;
									}
								return true;
			                }
			            }
					}
                }
			}	
		}).on('success.form.bv', function(e) {
            e.preventDefault();
            confirmarRegistrar();
    	});
		
		$.ajaxSetup({ scriptCharset: "UTF-8" , contentType: "application/json; charset=utf-8"});
	})  
	
	function construirTablaObservaciones(dataGrilla) {
    	
		$('#tblObservaciones').DataTable({
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
						{data :	"numOrden",sClass: 'left alinearCentrado'},
						{data : "desObservacion", sClass: 'left alinearCentrado'},
						{data : "nomUsuGeneraObs", sClass: 'left alinearCentrado'},
						{data : "fecGeneracionObs", sClass: 'left alinearCentrado'}
			],
				
			tableTools: {
		        "sSwfPath": "../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/TableTools/swf/copy_csv_xls.swf"
		    },	
			data: dataGrilla,			
			"sScrollY"		:	"250px",
			//"scrollX"		:	false,
			ordering			:	false,			
			//bScrollAutoCss		:	false,
			bStateSave			:	false,
			//bAutoWidth			:	false,
			bScrollCollapse		:   true,
			searching			:	false,
			paging				:	true,
			pagingType			:   "full_numbers",
			iDisplayLength		:	10,
			responsive			:	false,
			bLengthChange		: 	false, 
			info 				:	false,
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


function confirmarRegistrar(){ 
	titulo = titulos.tituloDefecto;
	msj = avisos.aviso1;
	botones = [];	

	var aceptar = $("<input/>").attr(
			{
				value: "Aceptar", 
				type: "button", 
				"class": "btn dlgButton", 
				"data-dismiss" : "modal", 
				onclick : "registrarObservacion()"
			}
		);
		
		var cancelar = $("<input/>").attr(
			{
				value: "Cancelar", 
				type: "button", 
				"class": "btn dlgButton", 
				"data-dismiss" : "modal", 
				onclick : "$('#modalDlg').modal('hide')" 
			}
		);
		
		botones.push(aceptar);
		botones.push(cancelar);
		crearDlg(titulo, msj, botones);
}
	
	
function registrarObservacion(){
	nuevalistaObservaciones = [];
	
	var descripcionObs=$('#txtDesObservacion').val();
	$.ajax({
		
		url : '${pageContext.request.contextPath}/regObsControl.htm?action=registrarObservacion',
		type : 'POST',
		async : false,
		dataType : "json",
		data : 'descripcionObs='+descripcionObs+'&numExpedienteVirtual='+numExpedienteVirtual,
		contentType : "application/json",
		mimeType : "application/json",
		success : function(response) {
			var msjError = response.msjError;
			if(msjError!="" && msjError!=undefined){
				$('#tblObservaciones').dataTable().fnClearTable();
				$('#tblObservaciones').dataTable().fnDraw();
				mostrarDlgInfo(titulos.tituloDefecto, msjError);
				return;
			}			
			nuevalistaObservaciones = response.listaObservaciones;
			if (nuevalistaObservaciones.length > 0) {				
				$('#tblObservaciones').dataTable().fnClearTable();
				$('#tblObservaciones').dataTable().fnAddData(nuevalistaObservaciones);
				$('#tblObservaciones').dataTable().fnDraw();
				
			} else {				
				$('#tblObservaciones').dataTable().fnClearTable();
				$('#tblObservaciones').dataTable().fnDraw();
				mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion02);
			}		
			mostrarDlgInfo(titulos.tituloDefecto,avisos.aviso2);
		},
		error : function(response) {
			$('#tblObservaciones').dataTable().fnClearTable();
			$('#tblObservaciones').dataTable().fnDraw();
			mostrarPagError();
		}
	});
	limpiar();
}
function limpiar(){
	$('#txtDesObservacion').val("");
}

function revalidarFormulario(){
	$('#frmRegistrarObservacion').submit();
}	
function mostrarPagError() {
	
	var formURL = '${pageContext.request.contextPath}/error.htm?action=cargarPaginaError';
	document.forms.frmDatosDocumento.action = formURL;
	document.forms.frmDatosDocumento.method = "post";
	document.forms.frmDatosDocumento.submit();
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
				crearDlg(titulo, msj, botones)
}
			

function cerrarVentana(){
	var  url = '${pageContext.request.contextPath}/regObsControl.htm?action=cargarBusqObsExpVirtual';
	$(location).prop( 'href', url);
	
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
	var dataExp = $('#tblObservaciones').dataTable().fnGetData();
	if(dataExp.length > 0){
		var listaCadena = JSON.stringify(dataExp);
		var formURL = 'regObsControl.htm?action=exportarExcelObservaciones';
		document.forms.formPrincipal.action = formURL;
		document.forms.formPrincipal.method = "POST";
		$('#campoHiddenExp').val(listaCadena);
		/*setear campos hdn fin*/
		document.forms.formPrincipal.submit();
	}
}
 </script>	
</html>	 