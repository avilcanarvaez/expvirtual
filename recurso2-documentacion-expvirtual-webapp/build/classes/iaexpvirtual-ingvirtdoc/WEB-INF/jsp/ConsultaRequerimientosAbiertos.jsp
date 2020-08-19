
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=EDGE" />
<meta name="viewport"
	content="initial-scale = 1.0, user-scalable = no,  width=device-width">
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
	var listaTipoBusquedaExpediente = ${listaRequerimientos};


    $(function () {
    
		construirTablaRequerimiento( listaTipoBusquedaExpediente );
		$('#tblRequerimientos tbody').on('mouseenter', 'tr', function() {
			$(this).addClass("selected");
		});	
		$('#tblRequerimientos tbody').on( 'mouseleave', 'tr', function () {
			 $(this).removeClass("selected");
			 //$('#tblRequerimientos').dataTable().fnDraw();
		} );
		

		$('#tblRequerimientos').on('click','.ver-documento',function(e){
			//nchavez [06/06/2016]
			e.preventDefault ? e.preventDefault() : (e.returnValue = false);
			var nreq=$(this).data('id');
			var numexp=$(this).data('numexp');
			//Inicio nchavez [31/05/2016]
			var numreqorigen=$(this).data('numreqorigen');
			var  url = '${pageContext.request.contextPath}/regReqControl.htm?action=listarDocumentoExpediente&numExpedVirtual='+numexp+'&numRequerimiento='+nreq+'&numRequerimientoOrigen='+numreqorigen;
			//Fin nchavez [31/05/2016]
			$('html').block({message: '<h1>Procesando...</h1>'});
			$(location).prop( 'href', url);

		});

		
	})   
   

	function construirTablaRequerimiento(dataGrilla) {
		var numExpVirtual=$('#numExpVirtual').val();
    	
		$('#tblRequerimientos').DataTable({
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
				// [nchavez 27/05/2016]
				{data : "numRequerimOrigen", sClass: 'left alinearCentrado'},
				{data:"desOrigRequerimiento",sClass:'left alinearCentrado'},
				{data : "desEstado", sClass: 'left alinearCentrado'},
				{data : "fecRequerimiento", sClass: 'left alinearCentrado'},
				{data : "fecVencimiento", sClass: 'left alinearCentrado'},
				{data : "nomUsuReq", sClass: 'left alinearCentrado'},
				{data : 'numRequerimiento',sClass: 'center alinearCentrado',render:function(data,row,full){
					//Inicio nchavez [31/05/2016]
						return '<a href="#" data-id="'+data+'"  data-numreqorigen="'+full.numRequerimOrigen+'" data-numexp="'+numExpVirtual+'" class="ver-documento">Ver Detalle</a>';
				 	//Fin nchavez [31/05/2016]
				 	}
				}
				
				
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
				var a = $('#tblRequerimientos').width()
				$("#tblRequerimientos_wrapper").css("min-width", a);	
				$('#tblRequerimientos_paginate').addClass('div100');
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
		var  url = '${pageContext.request.contextPath}/regReqControl.htm?action=mostrarView&pageView=ConsultaExpedientesVirtualesIntranet';
		$(location).prop( 'href', url);
	}
	function ingresarRegistroRequerimiento(){
		$('html').block({message: '<h1>Procesando...</h1>'});
		var numExpVirtual = $('#numExpVirtual').val();
		var  url = '${pageContext.request.contextPath}/regReqControl.htm?action=registrarRequerimientoView&numExpedienteVirtual='+numExpVirtual;
		$(location).prop( 'href', url);
	}
 </script>

</head>
<body>
</br>
	<div id="container" class="container" style="width: 95%">
		<div>
			<div class="row">
				<div class="panel panel-primary">
					<div class="panel-heading align="center">
						<h3 class="panel-title" align="center">CONSULTA REQUERIMIENTOS DEL EXPEDIENTE VIRTUAL</h3>
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
									<input name="txtFechaNotif" id="txtFechaNotif" type="text"  class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.strFecNotifOrigen}' disabled></input>
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
								<div class="col-md-3">
									<input name="txtRespExpediente" id="txtRespExpediente" type="text" class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.nombreResponsable}' disabled></input>
								</div>
							</div>
							
						</fieldset>	
						<fieldset id="contentRequerimientos" class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border"> Listado de Requerimientos </legend>
							<div class="form-group">
								<div class="col-md-12">	
								<div class="tab-content">
								<div id="accionesFiscalizacion" class="tab-pane fade in active">
								<div class="border-line panel-scroll">
									<table id="tblRequerimientos" class="table display" cellspacing="0" style="width: 100%;">
										<thead>
											<tr>
												<th width="5%" class="text-center">N&deg;</th>
												<th width="15%" class="text-center">N&deg; Requerimiento</th>
												<!-- nchavez [27/05/2016] -->
												<th width="5%" class="text-center">Origen</th>
												<th width="10%" class="text-center">Estado del Requerimiento</th>
												<th width="15%" class="text-center">Fecha del Requerimiento</th>
												<th width="12%" class="text-center">Fecha de Vencimiento</th>
												<th width="30%" class="text-center">Usuario del Requerimiento</th>
												<th width="15%" class="text-center">Ver Detalle</th>
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
									<input type="button" class="btn btn-primary" id="btnRegistrar" intermediateChanges="false" value="Registrar Requerimiento" id="btnGuardar" onClick="ingresarRegistroRequerimiento()"></input>
									
								</div>	
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
