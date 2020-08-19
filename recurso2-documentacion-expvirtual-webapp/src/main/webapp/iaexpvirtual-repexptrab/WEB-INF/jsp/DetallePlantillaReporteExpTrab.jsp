
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
	
	.div100{
		width : 100% !important;
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
	.panel-scroll {
		max-height: 220px;
		min-height: 0px;
		overflow-y: auto;
		overflow-x: hidden;
	}
	
	.alinearCentrado {
		vertical-align: middle !important;
	}
	
	.alignCenter { text-align: center; }
</style>

 <script type="text/javascript">
 
	var listadoProcesos = ${listadoProcesos};
	var listadoTiposExpendientes = ${listadoTiposExpendientes};
	var listadoDocumentos = ${listaTipDocExp};
	var listadoFormatosAdicionales = ${listadoFormatosAdicionales};
	var listadoReportesTributariosAduaneros = ${listadoReportesTributariosAduaneros};
	var codigoProceso = '${codigoProceso}';
	var codTipExpediente = '${codTipExpediente}';
	var opcionSeleccionada = '${opcionSeleccionada}';
	var excepciones =  ${excepciones};
	var titulos =  ${titulos};
	var listT6612documentos = ${listT6612documentos};
	var listT6612FormatosAdicionales = ${listT6612FormatosAdicionales};
	var listT6612ReportesTribAdua = ${listT6612ReportesTribAdua};
	var flagReporteTrib= ${flagReporteTrib};
	
	$(function () {
    	
		$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
		
		//Inicio [gangles 24/05/2016]Se comenta por la modificacion de la funcionalidad de mostrar el detalle de una plantilla, cuando el usuario realiza la consulta ahora sólo visualiza los documentos que corresponden a la plantilla seleccionada
		/*construirTablaDocumentos( listadoDocumentos ); 
		construirTablaFormatos( listadoFormatosAdicionales );//GLAM
		construirTablaReportes( listadoReportesTributariosAduaneros );*/
		//Fin [gangles 24/05/2016]
	
		construirTablaDocumentos( listT6612documentos );
		construirTablaFormatos( listT6612FormatosAdicionales );		
		inicializarProcesos();
		//seleccionarDocumentos(); //Inicio [gangles 24/05/2016] Se comenta por la modificacion de la funcionalidad de mostrar el detalle de una plantilla, cuando el usuario realiza la consulta ahora sólo visualiza los documentos que corresponden a la plantilla seleccionada
	
	})   
	
    
	function inicializarProcesos() {
		
		
		//procesos
		var $element = $('#selCodigoProceso');
		$.each(listadoProcesos, function(i, dato) {
			var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
			$element.append($option);
		});
		
		var $element = $('#selCodigoTipoExpendiente');
		$.each(listadoTiposExpendientes, function(i, dato) {
			var $option = $("<option/>").attr("value", dato.codTipoExpediente).text(dato.desTipoExpediente);
			$element.append($option);
		});
		
		$('#selCodigoProceso').val(codigoProceso);
		$('#selCodigoTipoExpendiente').val(codTipExpediente);
		$('#selCodigoProceso').prop('disabled', true);
		$('#selCodigoTipoExpendiente').prop('disabled', true);	
		$('#selOpcionRepTribAdu').val(opcionSeleccionada);
		$('#selOpcionRepTribAdu').prop('disabled', true);
		
		// Inicio [gangles 24/05/2016]  Se modifica la funcionalidad de mostrar el detalle de una plantilla cuando el usuario realiza la consulta ahora sólo visualiza los documentos que corresponden a la plantilla
		if (flagReporteTrib=='1') {
			construirTablaReportes( listT6612ReportesTribAdua );
			document.getElementById('selRepTrib').style.display="block";
			document.getElementById('divTbRepTrib').style.display="block";	
			$('#selOpcionRepTribAdu').prop('disabled', true);
				
		}else {	
			
			$('#selRepTrib').addClass('hiddenDiv');
			$('#divTbRepTrib').addClass('hiddenDiv');
			
		}	
		// Fin [gangles 24/05/2016]
		
		/* Inicio [gangles 24/05/2016] Se comenta estas líneas debido al cambio realizado en la funcionalidad de mostrar al usuarios sólo los documentos que corresponden a la plantilla  
		var dataJson = $('#tblFormatos').DataTable().rows().data();	
		for (var i = 0; i < dataJson.length; i++) {
			$('#'+dataJson[i].codParametro).prop('disabled', true);
		}
		var dataJson = $('#tblDocumentos').DataTable().rows().data();	
		for (var i = 0; i < dataJson.length; i++) {
			$('#'+dataJson[i].codTipoDocumento).prop('disabled', true);
		}		
		
		var dataJson = $('#tblReportes').DataTable().rows().data();	
		for (var i = 0; i < dataJson.length; i++) {
			$('#reporte-'+dataJson[i].codParametro).prop('disabled', true);
		}*/	//Fin [gangles 24/05/2016] 
	}
	
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
			"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
				
				  $(iCol).attr('colClasses','tableOddCol');
				
			 },
			 /*fnRowCallback: function (nRow, aData, iDisplayIndex) {
                  $(nRow).attr('name', aData[1]);
					$(nRow).attr('align', 'center');
					$(nRow).attr('rowClasses','tableOddRow');
                  return nRow;
          	},*/
			columns : [
				{data : "numOrden", sClass: 'alignCenter'},			
				{data : "codTipoDocumento", sClass: 'alignCenter', sClass: 'hidden', //Inicio [gangles 24/05/2016] se agrega esta propiedad al campo, sClass: 'hidden',
					
					render : function(data, row){
						return jQuery('<input>').css(
							{
								"marginLeft"	:	"8px", 
								"width"			:	"18px", 
								"height"		:	"18px"
							}
						).attr(
							{
								type:'checkbox', 
								checked:  true, 
								"class" : "bChecked",
								id: data,
								disabled: true,
								align:'center'
 							}
						).wrap('<div></div>').parent().html();
					}
				}, 
				{data : "codTipoDocumento", sClass: 'alignCenter'},				
				{data : "desTipdoc", sClass: 'left'},
				{data : "codTipoDocumento", sClass: 'hidden'}
				
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
            bLengthChange: false
		} );
    }

	function construirTablaFormatos(dataGrilla) {
    	
		$('#tblFormatos').DataTable({
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
			"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
				
				  $(iCol).attr('colClasses','tableOddCol');
				
			 },
			/* fnRowCallback: function (nRow, aData, iDisplayIndex) {
                  $(nRow).attr('name', aData[1]);
					$(nRow).attr('align', 'center');
					$(nRow).attr('rowClasses','tableOddRow');
                  return nRow;
          	},*/
			columns : [
				{data : "numOrden", sClass: 'alignCenter'},
				{data : "codFormatoAdi", sClass: 'alignCenter', sClass: 'hidden', //Inicio [gangles 24/05/2016] se agrega esta propiedad al campo, sClass: 'hidden',
					render : function(data, row){
						
						return jQuery('<input>').css(
							{
								"marginLeft"	:	"8px", 
								"width"			:	"18px", 
								"height"		:	"18px"
							}
						).attr(
							{
								type:'checkbox', 
								checked:  true, 
								"class" : "bChecked",
								id: data,
								disabled: true
							}
						).wrap('<div></div>').parent().html();
					}
				},      	
				{data : "codFormatoAdi", sClass: 'alignCenter'},
				{data : "desFormato", sClass: 'left'},
				{data : "codFormatoAdi", sClass: 'hidden'}
				
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
            bLengthChange: false
		} );
    }

	function construirTablaReportes(dataGrilla) {
    	
		$('#tblReportes').DataTable({
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
			"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
				
				  $(iCol).attr('colClasses','tableOddCol');
				
			 },
			/* fnRowCallback: function (nRow, aData, iDisplayIndex) {
                  $(nRow).attr('name', aData[1]);
					$(nRow).attr('align', 'center');
					$(nRow).attr('rowClasses','tableOddRow');
                  return nRow;
          	},*/
			columns : [
				{data : "numOrden", sClass: 'alignCenter'},
				{data : "codRepTrbib", sClass: 'alignCenter', sClass: 'hidden', //Inicio [gangles 24/05/2016] se agrega esta propiedad al campo, sClass: 'hidden',
					render : function(data, row){
						
						return jQuery('<input>').css(
							{
								"marginLeft"	:	"8px", 
								"width"			:	"18px", 
								"height"		:	"18px"
							}
						).attr(
							{
								type:'checkbox', 
								checked:  true, 
								"class" : "bChecked",
								id : "reporte-"+data,
								disabled: true
							}
						).wrap('<div></div>').parent().html();
					}
				},      	
				{data : "codRepTrbib", sClass: 'alignCenter'},
				{data : "desRubro", sClass: 'left'},
				{data : "codRepTrbib", sClass: 'hidden'}
				
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
            bLengthChange: false
		} );
    }    
			
	function volverPagina(){
		$('html').block({message: '<h1>Regresando...</h1>'});
		var  url = '${pageContext.request.contextPath}/mantPlantRep.htm?action=cargarBandPlanExpe&indCarga=0';
		$(location).prop( 'href', url);
		
	}
	
	function seleccionarDocumentos(){
		//documentos
		var $element = $('#selCodigoTipoExpendiente');
		$.each(listT6612documentos, function(i, dato) {
			if($('#'+dato.codTipoDocumento).length){
				$('#'+dato.codTipoDocumento).prop("checked", "checked");
				$('#'+dato.codTipoDocumento).prop("value", dato.numCorDetPlanti);
				$('#'+dato.codTipoDocumento).prop("disabled", "disabled");
			}	
		});
		//formato adicional
		$.each(listT6612FormatosAdicionales, function(i, dato) {
			if($('#'+dato.codFormatoAdi).length){
				$('#'+dato.codFormatoAdi).prop("checked", "checked");
				$('#'+dato.codFormatoAdi).prop("value", dato.numCorDetPlanti);
				$('#'+dato.codFormatoAdi).prop("disabled", "disabled");
			}	
		});
		//Reporte tributario
		$.each(listT6612ReportesTribAdua, function(i, dato) {
			if($('#reporte-'+dato.codRepTrbib).length){
				$('#reporte-'+dato.codRepTrbib).prop("checked", "checked");
				$('#reporte-'+dato.codRepTrbib).prop("value", dato.numCorDetPlanti);
				$('#reporte-'+dato.codRepTrbib).prop("disabled", "disabled");
			}	
		});
	
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
						<h3 class="panel-title" align="center">CONSULTA DE PLANTILLA DE REPORTE DE EXPEDIENTES DE TRABAJO</h3>
					</div>
				</div>	
				<div class="panel panel-primary">
					<form class="form-horizontal" role="form" name="frmPlantillas" id="frmPlantillas" method="post">
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<div style="margin : 15px 15px !important">
								<div class="form-group">
									<div class="col-md-2"><Strong>Proceso:</Strong></div>
									<div class="col-md-4">
										<input type="hidden" value=${codigoProceso} name="codigoProceso">
										<select name="codProceso" id="selCodigoProceso" onchange="cargarTiposExpedientes()" class="form-control tamanoMaximo"> 
											<option value="">Seleccione</option>
										</select>
									</div>
									<div class="col-md-3">
									</div>
									<div class="col-md-3">
										<div class="form-group">
											<div class="col-md-2">
											</div>
											<div class="col-md-4">
												<Strong>Fecha:</Strong>
											</div>
											<div class="col-md-6">
												<input type='text' class="form-control tamanoMaximo text-center"  id='fechaActual' name='fechaActual' value='${fechaActual}' disabled/>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-2"><Strong>Tipo Expediente:</Strong></div>
									<div class="col-md-4">
										<input type="hidden" value=${codTipExpediente} name="codigoTipoExpediente">
										<select name="codTipexp" id="selCodigoTipoExpendiente" class="form-control tamanoMaximo" onchange="buscar()"> 
											<option value="">Seleccione</option>
										</select>
									</div>
								</div>	
							</div>
						</fieldset>	
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<div class="form-group">
								<div class="col-md-6">	
								<fieldset class="scheduler-border" style="margin : 15px 15px !important">	
								<legend class="scheduler-border"> 1. Documentos del Expediente Virtual </legend>
									<div class="tab-content">
									<div id="accionesFiscalizacion" class="tab-pane fade in active">
									<div class="border-line panel-scroll">
									<div>
									<table id="tblDocumentos" name="tblDocumentos" class="table table-striped" style="width: 100%;">
										<thead>
											<tr>
												<th width="5%" class="text-center">N&deg;</th>
												<th width="5%" class="text-center">Sel</th>
												<th width="20%" class="text-center">Cod. Tipo de Documento</th>
												<th width="70%" class="text-center">Tipo de Documento</th>
												<th></th>
											</tr>
										</thead>
									</table>
									</div>
									</div>
									</div>
									</div>
								</fieldset>	
								</div>
								<div class="col-md-6">
								<fieldset class="scheduler-border" style="margin : 15px 15px !important">		
								<legend class="scheduler-border"> 2. Formatos Adicionales </legend>
									<div class="tab-content">
									<div id="accionesFiscalizacion" class="tab-pane fade in active">
									<div class="border-line panel-scroll">
									<div>
									<table id="tblFormatos"  name="tblFormatos" class="table table-striped" style="width: 100%;">
										<thead>
											<tr>
												<th width="5%" class="text-center">N&deg;</th>
												<th width="5%" class="text-center">Sel</th>
												<th width="20%" class="text-center">Cod. Formato</th>
												<th width="70%" class="text-center">Descripci&oacute;n del Formato</th>
												<th></th>
											</tr>
										</thead>
									</table>
									</div>
									</div>
									</div>
									</div>
								</fieldset>	
								</div>
							</div>
							<div class="form-group" id="selRepTrib">
								<div class="col-md-4"></div>
								<div class="col-md-3"><Strong>Desea Reporte Tributario Aduanero</Strong></div>
								<div class="col-md-1">
									<select name="codOpcion" id="selOpcionRepTribAdu" onchange="revalidarTablaReportes()" class="form-control tamanoMaximo" readonly="readonly"> 
										<option value="1" >Si</option>
										<option value="2" selected >No</option>
									</select>
								</div>
							</div>
							
							<div class="form-group" id="divTbRepTrib" >
								<div class="col-md-3">	
								</div>
								<div class="col-md-6">	
								<fieldset class="scheduler-border">	
								<legend class="scheduler-border"> 3. Reportes Tributario Aduaneros </legend>
									<div class="tab-content">
									<div id="accionesFiscalizacion" class="tab-pane fade in active">
									<div class="border-line panel-scroll">
									<div>
									<table id="tblReportes" name="tblReportes" class="table table-striped" style="width: 100%;">
										<thead>
											<tr>
												<th width="5%" class="text-center">N&deg;</th>
												<th width="5%" class="text-center">Sel</th>
												<th width="20%" class="text-center">Cod. Rubro</th>
												<th width="70%" class="text-center">Descripci&oacute;n del Rubro</th>
												<th></th>
											</tr>
										</thead>
									</table>
									</div>
									</div>
									</div>
									</div>
								</fieldset>	
								</div>
								
							</div>
							<div class="row content-box">
								<div class="col-md-3"><Strong>N&uacute;m Plantilla:</Strong></div>
								<div class="col-md-2">
									<input type='text' class="form-control tamanoMaximo" id='numPlantilla' name='numPlantilla' value=${numPlantilla} readonly/>
								</div>
							</div>
							<div class="row content-box">
								<div class="col-md-5">
									<Strong>Descripci&oacute;n de la Plantilla de Reporte:</Strong>
									<input type='text' class="form-control tamanoMaximo" id='desPlanReporte' name='desPlanReporte' value='${desPlantilla}' readonly/>
								</div>	
								<div class="col-md-4">
								</div>
								<div class="col-md-3">
									<input type="button" class="btn btn-primary" id="btnExportar" intermediateChanges="false" onClick="volverPagina()" value="Regresar"></input>
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
