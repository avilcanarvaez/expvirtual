<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=EDGE" />
<meta name="viewport"
	content="initial-scale = 1.0, user-scalable = no,  width=device-width">
<title>SISTEMA INTEGRADO DE EXPEDIENTE VIRTUAL - SIEV - IU020</title>

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
						<h3 class="panel-title" align="center">PRESENTACI&Oacute;N DE ESCRITOS ELECTR&Oacute;NICOS</h3>
					</div>
				</div>	
				<div class="panel panel-primary">
<!-- 					<form id="frmAux" name="frmAux" method="post" > -->
<!-- 							<input type="hidden" id="hidDocumentosSel" name="hidDocumentosSel"> -->
<!-- 							<input type="hidden" id="hidNombreDoc" name="hidNombreDoc">  -->
<%-- 							<input name="hidTxtNumExpedienteVirt" id="hidTxtNumExpedienteVirt" type="hidden" value='${datosEscritoReq.numExpedienteVirtual}'></input> --%>
<!-- 					</form> -->
					<form name="frmCargaArchivo" id="frmCargaArchivo" method="post" class="form-horizontal">
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border" > Datos del Expediente </legend>
							
							<div class="form-group">
								<div class="col-md-2"><Strong>Proceso:</Strong></div>
								<div class="col-md-2">
									<input name="txtProceso" id="txtProceso" type="text" class="form-control tamanoMaximo" value='${datosEscritoReq.desProceso}' disabled></input>
								</div>
								<div class="col-md-2"><Strong>Tipo de Expediente:</Strong></div>
								<div class="col-md-5">
									<input name="txtTipoExpediente" id="txtTipoExpediente" type="text"  class="form-control tamanoMaximo" value='${datosEscritoReq.desTipoExpediente}' disabled></input>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"><Strong>N&uacute;mero de Expediente:</Strong></div>
								<div class="col-md-2">
									<input name="txtExpedienteOrigen" id="txtExpedienteOrigen" type="text" class="form-control tamanoMaximo" value='${datosEscritoReq.numExpedienteOrigen}' disabled></input>
									
							</div>
							</div>
						</fieldset>
					
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border"> Listado de Requerimientos</legend>
									
								<div class="form-group">
									<div class="col-md-12">	
									<div class="tab-content">
										<div style="width: 100%; overflow-y: hidden; overflow-x:auto; border: 1px solid #337ab7;">											
											<table id="tblDocumentos" name="tblDocumentos"  class="table table-bordered" style="width: 100%;">
												<thead>
													<tr>
														<th width="15%" class="center">N&deg; Requerimiento</th>
														<th width="15%" class="center">Fecha de Notificaci&oacute;n</th>
														<th width="15%" class="center">Fecha de Vencimiento</th>
													</tr>
												</thead>
											</table>
										</div>
									
									</div>
									</div>	
								</div>
					</fieldset>	
					<fieldset  style="margin : 15px 15px !important">						
						<div class="form-group">
							<div class="col-md-6" align="right" id="dvSecBotones01">	
								<input type="button" class="btn btn-primary" id="btnRegresarReq" intermediateChanges="false" onClick="volverPaginaReq()" value="Regresar"></input>
							</div>	
							<div class="col-md-6" align="left" id="dvSecBotones01">	
								<input type="button" class="btn btn-primary" id="btnCancelar" intermediateChanges="false" onClick="exportar()" value="Exportar a Excel"></input>
							</div>	
						</div>
					</fieldset>
				</form>
				<form id="formPrincipal" class="form-horizontal" role="form">	
				<div class="col-md-5" align="right" id="dvSecBotones02">	
					<input id="campoHiddenExp" type="hidden" name="listadoExpedientesCadena"/> 
					<input id="hidTxtNumRequerimiento" type="hidden" name="hidTxtNumRequerimiento"/>    
					<input id="hidTxtNumExpVirt" type="hidden" name="hidTxtNumExpVirt"/>
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

	var lisDocAdjItem =  ${listaDocAdjuntos};
	var numExpVirt = ${numExpVirt};
 	var titulo = "Detalle de Requerimiento";
 	var titulos =  ${titulos};
 	
 	var numDocumento;
	var metodoInvocar;
	var indexDocumentos;
	var indexDocumentosAdj;
// 	var numRequerimiento;
	
    $(function () {
	
		$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
		
		$("#modalDetalleReq").show();
		
// 		console.log("datos de item:" +lisDocAdjItem);
		construirTablaDocumentos( lisDocAdjItem );
		
		$('#tblDocumentos tbody').on('mouseenter', 'tr', function() {
			$(this).addClass("selected");
		});	
		$('#tblDocumentos tbody').on( 'mouseleave', 'tr', function () {
			 $(this).removeClass("selected");
		} );
				
		var tableAdj = $('#tblDocumentos').DataTable();
		$('#tblDocumentos tbody').on( 'mouseenter', 'tr', function () {
			indexDocumentosAdj = tableAdj.row( this ).index();			
		} );		
		
	})  
	
	
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
	
	
	function volverPaginaReq(){
		$('html').block({message: '<h1>Regresando...</h1>'});
		var  url = '${pageContext.request.contextPath}/cargarDocumentoIt.htm?action=consultarRequerimientosPendientesView';
		$(location).prop( 'href', url);
	}
	
	
	function mostrarModal(){		
		$("#modalDetalleReq").hide();
		
	}
	
	function construirTablaDocumentos(dataGrilla) {
    	
		$('#tblDocumentos').DataTable({
			fnRowCallback: function (nRow, aData, iDisplayIndex) {
//                     $(nRow).attr('align', 'center');
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
				{data : "numRequerimOrigen", sClass : 'left alinearCentrado',
					render:function(data,row,full){
						return '<a href="#"  onclick="regEscritoElec('+data+')" >'+data+'</a>';
					}
				},
				{data : "strfecNotifOrigen", sClass: 'left alinearCentrado'},	
				{data : "fecVencimiento", sClass: 'left alinearCentrado'},
				{data : "numRequerimiento", sClass: 'hidden alinearCentrado'},
				{data : "plazoVigencia", sClass: 'hidden alinearCentrado'},
				{data : "codEstadoExp", sClass: 'hidden alinearCentrado'}
				
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

	function regEscritoElec(data) {
// 		console.log("ingresando"+data);
		var tabla = $('#tblDocumentos').dataTable().fnGetData();
		
		var numRequerimiento = tabla[indexDocumentosAdj].numRequerimiento; 
		var plazoPresentacion = tabla[indexDocumentosAdj].plazoVigencia;						
		var estadoExp = tabla[indexDocumentosAdj].codEstadoExp;			
		
		if (estadoExp != "01") {
			mostrarDlgInfo(titulos.tituloDefecto, "El expediente electr&oacute;nico debe encontrarse en estado Abierto.");
			
		} else if (plazoPresentacion < 0) {
			mostrarDlgInfo(titulos.tituloDefecto, "Sr. Contribuyente, ha vencido el plazo para cumplir con la presentaci&oacute;n de lo requerido.");
		
		}else{
			numExpedienteConsultar = tabla[indexDocumentosAdj].numExpedienteVirtual;
			numRequerimientoConsultar = numRequerimiento; 
// 			cargarDatosBusquedaSession();
// 			var formURL = 'cargarDocumentoIt.htm?action=registraEscrito';
			var formURL = 'cargarDocumentoIt.htm?action=registraEscritoReq';  
			var listaCadena = JSON.stringify(tabla);
			document.forms.formPrincipal.action = formURL;
			document.forms.formPrincipal.method = "POST";
			$('#campoHiddenExp').val('');
			$('#hidTxtNumRequerimiento').val('');   
			$('#hidTxtNumExpVirt').val('');
			$('#campoHiddenExp').val(listaCadena);
        	$('#hidTxtNumRequerimiento').val(numRequerimiento);   
			$('#hidTxtNumExpVirt').val(numExpedienteConsultar);
			document.forms.formPrincipal.submit();
			$('html').unblock();
		}			
		
	}
	
	function exportar(){
		var dataExp = $('#tblDocumentos').dataTable().fnGetData();
		if(dataExp.length > 0){
			var listaCadena = JSON.stringify(dataExp);
			var formURL = 'cargarDocumentoIt.htm?action=exportarExcelRequerimientos';
			document.forms.formPrincipal.action = formURL;
			document.forms.formPrincipal.method = "POST";
			$('#campoHiddenExp').val('');
			$('#campoHiddenExp').val(listaCadena);
			document.forms.formPrincipal.submit();
		}else{
			mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion02);
		}
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
 </script>
</html>