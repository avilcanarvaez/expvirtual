
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
  <link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Responsive/css/dataTables.responsive.css">
  
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
	/*Inicio [gangles 01/06/2016]*/
	.form-control-single {
		font-size:12px !important;
		height:24px !important;
		padding-top:3px !important;
		padding-bottom:3px !important;
		padding-left:5px !important;
		padding-right:5px !important;
	}/*Fin [gangles 01/06/2016]*/
	
	.alignCenter { text-align: center; }
		
	.text-left-important{
		text-align: left !important;
	}
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
	var flagPrevisualizacion=false; // Inicio [gangles 30/05/2016] Flag para la funcionalidad de la previsualización de la plantilla a crear // Fin [gangles 30/05/2016]
	
	$(function () {
    	
		$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
		construirTablaDocumentos( listadoDocumentos );
		construirTablaFormatos( listadoFormatosAdicionales );
		construirTablaReportes( listadoReportesTributariosAduaneros );
		inicializarProcesos();
		seleccionarDocumentos();
		if(opcionSeleccionada==2){
			$('#selectAllReportes').prop('disabled', true);
		}
		//Inicio [gangles 27/06/2016]
		$('#tblDocumentos tbody').on('change','.bChecked',function(e){
			if($(this).is(':checked')){
				$(this).parents('tr').addClass('seleccionado');
			}else{
				$(this).parents('tr').removeClass('seleccionado');
			}
			$('#frmPlantillas').bootstrapValidator('revalidateField', 'tblDocumentos');	
			
			var oTable = $('#tblDocumentos').DataTable();
			var totalRecords=oTable.rows().data().length;
			if(totalRecords==oTable.rows('.seleccionado').data().length){
				$('#selectAllDocumentos').prop('checked',true);
			}else{
				$('#selectAllDocumentos').prop('checked', false);
			}
			
		});
		$('#tblFormatos tbody').on('change','.bChecked',function(e){
			if($(this).is(':checked')){
				$(this).parents('tr').addClass('seleccionado');
			}else{
				$(this).parents('tr').removeClass('seleccionado');
			}
			var codProceso = $('#selCodigoProceso').val();
			if(codProceso != '002'){
				$('#frmPlantillas').bootstrapValidator('revalidateField', 'tblFormatos');	
			}			
			var oTable = $('#tblFormatos').DataTable();
			var totalRecords=oTable.rows().data().length;
			if(totalRecords==oTable.rows('.seleccionado').data().length){
				$('#selectAllFormatos').prop('checked',true);
			}else{
				$('#selectAllFormatos').prop('checked', false);
			}
		});
		$('#tblReportes tbody').on('change','.bChecked',function(e){
			if($(this).is(':checked')){
				$(this).parents('tr').addClass('seleccionado');
			}else{
				$(this).parents('tr').removeClass('seleccionado');
			}
			$('#frmPlantillas').bootstrapValidator('revalidateField', 'tblReportes');	
			
			var oTable = $('#tblReportes').DataTable();
			var totalRecords=oTable.rows().data().length;
			if(totalRecords==oTable.rows('.seleccionado').data().length){
				$('#selectAllReportes').prop('checked',true);
			}else{
				$('#selectAllReportes').prop('checked', false);
			}
		});
		
		//Fin [gangles 27/06/2016]
	
		//Validaciones del Formulario Busqueda
		$('#frmPlantillas').bootstrapValidator({
			excluded: [':disabled'],
			fields: {
				codProceso: {
                    validators: {
                        notEmpty: {
                            message: excepciones.excepcion01
                        }
                    }
                },
				codTipexp: {
                    validators: {
                        notEmpty: {
                            message: excepciones.excepcion02
                        }
                    }
                },
				dessPlanReporte: {
                    validators: {
						notEmpty: {
                            message: excepciones.excepcion05
                        }
                    }
                },
				tblDocumentos:	{
					validators: {
						callback: {
                            message: excepciones.excepcion03,
                            callback: function (value, validator, $field) {
								if(!validarTablaDocumentos()){
									return false;
								}
								return true;
                            }
                        }
						
					}
				},
				tblFormatos:	{
					validators: {
						callback: {
                            message: excepciones.excepcion04,
                            callback: function (value, validator, $field) {
                            	var codProceso = $('#selCodigoProceso').val();
                            	if(codProceso != '002'){   
									if(!validarTablaFormatos()){
										return false;
									}
                            	}
									return true;
                            }
                        }
						
					}
				},
				tblReportes:	{
					validators: {
						callback: {
                            message: excepciones.excepcion07,
                            callback: function (value, validator, $field) {
								var opcionSeleccionado = $('#selOpcionRepTribAdu').val();
								if(opcionSeleccionado=="1"){
									if(!validarTablaReportes()){
										return false;
									}
								}
								return true;
                            }
                        }
						
					}
				}
			}
		}).on('success.form.bv', function(e) {
            e.preventDefault();
            if(flagPrevisualizacion==false){// Inicio [gangles 30/05/2016] Se agrega la opción de marcar y descarcar todos los chekbox
            	nuevaPlantilla();
            }else{
            	previsualizarPlantilla();
            }	// Fin [gangles 30/05/2016]			
    	});
		
		// Inicio [gangles30/05/2016] Se agrega la opción de marcar y descarcar todos los chekbox
		
		jQuery("input[type='checkbox']").css(
				{
					"marginLeft"	:	"8px", 
					"width"			:	"18px", 
					"height"		:	"18px"
				});	
		jQuery('#selectAllDocumentos').click(function(){			
			jQuery("#tblDocumentos input[type='checkbox']").prop('checked', this.checked);
			if(this.checked){
				$("#tblDocumentos tbody tr").addClass('seleccionado');	
			}else{
				$("#tblDocumentos tbody tr").removeClass('seleccionado');	
			}		
			$('#frmPlantillas').bootstrapValidator('revalidateField', 'tblDocumentos');			
		})	
		
		jQuery('#selectAllFormatos').click(function(){			
			jQuery("#tblFormatos input[type='checkbox']").prop('checked', this.checked);
			if(this.checked){
				$("#tblFormatos tbody tr").addClass('seleccionado');	
			}else{
				$("#tblFormatos tbody tr").removeClass('seleccionado');	
			}	
			var codProceso = $('#selCodigoProceso').val();
        	if(codProceso != '002'){
				$('#frmPlantillas').bootstrapValidator('revalidateField', 'tblFormatos');
        	}
		})
		
		jQuery('#selectAllReportes').click(function(){			
			jQuery("#tblReportes input[type='checkbox']").prop('checked', this.checked);
			if(this.checked){
				$("#tblReportes tbody tr").addClass('seleccionado');	
			}else{
				$("#tblReportes tbody tr").removeClass('seleccionado');	
			}	
			$('#frmPlantillas').bootstrapValidator('revalidateField', 'tblReportes');
		})// fin [gangles 30/05/2016]
		
		// Inicio [gangles 01/06/2016] Se incluye criterios de búsqueda con ayuda asistida (palabra clave que permite resultados asociados a dicha palabra).	
		$('#txtBusqueda').keyup(function() {		
			filtrar();
		});// fin [gangles 01/06/2016]
	})   
	
	function revalidarTablaReportes(){
	
		var opcionSel = $('#selOpcionRepTribAdu').val();
		var dataJson = $('#tblReportes').DataTable().rows().data();	
		if(opcionSel==2){
			for (var i = 0; i < dataJson.length; i++) {
				$('#reporte-'+dataJson[i].codParametro).prop('checked', false);
				$('#reporte-'+dataJson[i].codParametro).prop('disabled', true);
				$('#selectAllReportes').prop('disabled', true);	// Inicio [gangles 30/05/2016] Se agrega la opción de marcar y descarcar todos los chekbox // fin [gangles 30/05/2016]
					}
		}else{
			for (var i = 0; i < dataJson.length; i++) {
				$('#reporte-'+dataJson[i].codParametro).prop('disabled', false);
				$('#selectAllReportes').prop('disabled', false);	// Inicio [gangles 30/05/2016] Se agrega la opción de marcar y descarcar todos los chekbox // fin [gangles 30/05/2016]
			}
		}
		$('#selectAllReportes').attr('checked', false);	// Inicio [gangles 22/06/2016] Se agrega la opción de marcar y descarcar todos los chekbox // fin [gangles 22/06/2016]		
		$('#frmPlantillas').bootstrapValidator('revalidateField', 'tblReportes');
	}
	
	function revalidarTablaDocumentos(){
		$('#selectAllDocumentos').attr('checked', false);	// Inicio [gangles 22/06/2016] Se agrega la opción de marcar y descarcar todos los chekbox // fin [gangles 22/06/2016]		
		$('#frmPlantillas').bootstrapValidator('revalidateField', 'tblDocumentos');
	}
	
	function revalidarTablaFormatos(){
        var codProceso = $('#selCodigoProceso').val();
		$('#selectAllFormatos').attr('checked', false);	// Inicio [gangles 22/06/2016] Se agrega la opción de marcar y descarcar todos los chekbox // fin [gangles 22/06/2016]		
		if(codProceso != '002'){
			$('#frmPlantillas').bootstrapValidator('revalidateField', 'tblFormatos');
		}
	}
	
	function validarTablaDocumentos(){
		var cantidadChecks=0;
			var dataJson = $('#tblDocumentos').DataTable().rows('.seleccionado').data().length;	
			/*for (var i = 0; i < dataJson.length; i++) {
				if($('#'+dataJson[i].codTipoDocumento).prop('checked')){
					cantidadChecks++;
				}
			}			
			if(cantidadChecks>0){
				return true;
			}else{
				return false;
			}	*/
			/*var oTable = $('#tblDocumentos').DataTable();
			var totalRecords=oTable.rows().data().length;
			if(totalRecords==oTable.rows('.seleccionado').data().length){
				$('#selectAllDocumentos').prop('checked',true);
			}else{
				$('#selectAllDocumentos').prop('checked', false);
			} */
			if(dataJson>0){
				return true;
			}else{
				return false;
			}	
	}
	
	function validarTablaFormatos(){
		var codProceso = $('#selCodigoProceso').val();
			var cantidadChecks=0;
			var dataJson = $('#tblFormatos').DataTable().rows('.seleccionado').data().length;	
			/*for (var i = 0; i < dataJson.length; i++) {
				if($('#'+dataJson[i].codParametro).prop('checked')){
					cantidadChecks++;
				}
			}
			if(cantidadChecks>0){
				return true;
			}else{
				return false;
			}*/
			/*var oTable = $('#tblFormatos').DataTable();
			var totalRecords=oTable.rows().data().length;
			if(totalRecords==oTable.rows('.seleccionado').data().length){
				$('#selectAllFormatos').prop('checked',true);
			}else{
				$('#selectAllFormatos').prop('checked', false);
			} */
			if(codProceso == '002'){
				return true;				
			}else{
				if(dataJson>0){
					return true;
				}else{
					return false;
				}
			}	
	}
	
	function validarTablaReportes(){
		// Inicio [gangles 30/05/2016] Se agrega la opción de marcar y descarcar todos los chekbox 
		
			var cantidadChecks=0;
			var dataJson = $('#tblReportes').DataTable().rows('.seleccionado').data().length;	
			/*for (var i = 0; i < dataJson.length; i++) {
				if($('#reporte-'+dataJson[i].codParametro).prop('checked')){
					cantidadChecks++;
				}
			}
			if(cantidadChecks>0){
				return true;
			}else{
				return false;
			}*/
			/*var totalRecords=oTable.rows().data().length;
			if(totalRecords==oTable.rows('.seleccionado').data().length){
				$('#selectAllReportes').prop('checked',true);
			}else{
				$('#selectAllReportes').prop('checked', false);
			} */
			if(dataJson>0){
				return true;
			}else{
				return false;
			}
		
	}
    
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
		
		var opcionSel = $('#selOpcionRepTribAdu').val();
		var dataJson = $('#tblReportes').DataTable().rows().data();	
		if(opcionSel==2){
			for (var i = 0; i < dataJson.length; i++) {
				
				$('#reporte-'+dataJson[i].codParametro).prop('checked', false);
				$('#reporte-'+dataJson[i].codParametro).prop('disabled', true);
			}
		}
		//$('#selectAllDocumentos').attr('checked', false);
		//$('#selectAllDocumentos').attr('disabled', true);
	}
	
    function cargarTiposExpedientes() {
		var codProceso = $('#selCodigoProceso').val();
		
		$('#selCodigoTipoExpendiente').empty();
		
		$('#selCodigoTipoExpendiente').append($('<option>', {
			value: '',
			text: 'Seleccione'
		}));
		
		var dataEnvio = new Object();
		
		if (codProceso == "") {
			
			$('#selCodigoTipoExpendiente').val("");
			
		} else {
			
			dataEnvio.codProceso = codProceso;
			
			$.ajax({
	            url: '${pageContext.request.contextPath}/mantPlantRep.htm?action=cargarListaTiposExpediente',
	           	data: "&codProceso="+codProceso,
	            cache: false,
	            async: true,
	            type: 'POST',
	            contentType : "application/x-www-form-urlencoded; charset=utf-8",
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
    
	function construirTablaDocumentos(dataGrilla) {

		$('#tblDocumentos').DataTable({
			"language": {
				"url"		:	"/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
			},//Inicio [gangles 01/06/2016] 
			"dom": 'rt',
			"fnInitComplete": function ( oSettings ) {
				oSettings.oLanguage.sZeroRecords = ""
			},//Fin [gangles 01/06/2016]
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
				{data : "numOrden", sClass: 'alignCenter '},			
				{data : "codTipoDocumento", sClass: 'alignCenter ', 
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
								checked:  false, 
								"class" : "bChecked",
								id: data,
								"onclick": "revalidarTablaDocumentos()",
								align:'center'
 							}
						).wrap('<div></div>').parent().html();
					}
				},   
				{data : "codTipoDocumento", sClass: 'hidden'},
				{data : "desTipoDocumento", 
					render: function(data, type, row){
						return row.codTipoDocumento+"-"+row.desTipoDocumento;
					}, sClass: 'text-left-important',
				},
				{data : "codTipoDocumento", sClass: 'hidden'}
				
			],
			data: dataGrilla,
            ordering: true,
            searching: true,//Inicio [gangles 01/06/2016] Se activa la búsqueda en la tabla documentos // Fin [gangles 01/06/2016]
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
				{data : "codParametro", sClass: 'alignCenter', 
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
								checked:  false, 
								"class" : "bChecked",
								id: data,
								"onclick" :  "revalidarTablaFormatos()",
								align:'center'
							}
						).wrap('<div></div>').parent().html();
					}
				},      	
				{data : "codParametro", sClass: 'alignCenter '},
				{data : "desParametro", sClass: 'text-left-important'},
				{data : "codParametro", sClass: 'hidden'}
				
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
				{data : "codParametro", sClass: 'alignCenter', 
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
								checked:  false, 
								"class" : "bChecked",
								id : "reporte-"+data,
								"onclick" : "revalidarTablaReportes()",
								align:'center'
							}
						).wrap('<div></div>').parent().html();
					}
				},      	
				{data : "codParametro", sClass: 'alignCenter'},
				{data : "desParametro", sClass: 'text-left-important'},
				{data : "codParametro", sClass: 'hidden'}
				
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
	
	//mostrar P⨩na de Error
	function mostrarPagError() {
		
		var formURL = '${pageContext.request.contextPath}/error.htm?action=cargarPaginaError';
		document.forms.frmPlantillas.action = formURL;
		document.forms.frmPlantillas.method = "post";
		document.forms.frmPlantillas.submit();
		
	}
	
	function mostrarDlgInfo(titulo, msj){ 
				
		botones = [];
		
		var aceptar = $("<input/>").attr(
			{
				value: "Aceptar", 
				type: "button", 
				"class": "btn btn-primary dlgButton", 
				"data-dismiss" : "modal", 
				onclick : "$('#modalDlg').modal('hide');volverPagina();"
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
			
	function revalidarFormulario(){
		$("#txtBusqueda").val('');
		filtrar();
		flagPrevisualizacion=false; // Inicio [gangles 30/05/2016] Se agrega la funcionalidad de la previsualización de la plantilla a modificar //Fin [gangles 30/05/2016]
		$('#frmPlantillas').submit();
	}		
	
	function habilitarTablaRep(){
		var opcion =  $('#selOpcionRepTribAdu').val();
		if(opcion==2){
			$('#tblReportes').disabled="none";
			$('#txtfechaDesde').val("");
			$('#txtfechaHasta').val("");
			
		}else{
			$('#tblReportes').disabled="disabled";
		}
		$('#frmPlantillas').bootstrapValidator('revalidateField', 'fecDesde');
		$('#frmPlantillas').bootstrapValidator('revalidateField', 'fecHasta');
		$('#frmPlantillas').bootstrapValidator('revalidateField', 'hiddFechas');
		
	}
	function buscar(){
		
			listaTipDocExp = [];
						 
			$.ajax({
				
				url : '${pageContext.request.contextPath}/mantPlantRep.htm?action=obtenerDocumentos',
				type : 'POST',
				async : true,
				dataType : "json",
				data : $('#frmPlantillas').serialize(),
				//contentType : "application/json",
				mimeType : "application/json",
				//timeout : 5000,
				success : function(response) {
					
					var msjError = response.msjError;
					if(msjError!="" && msjError!=undefined){
						$('#tblDocumentos').dataTable().fnClearTable();
						$('#tblDocumentos').dataTable().fnDraw();
						return;
					}
					
					listaTipDocExp = response.listaTipDocExp;
					
					if (listaTipDocExp.length > 0) {
						
						$('#tblDocumentos').dataTable().fnClearTable();
						$('#tblDocumentos').dataTable().fnAddData(listaTipDocExp);
						$('#tblDocumentos').dataTable().fnDraw();
						$('#selectAllDocumentos').prop('disabled', false);
					//	filtrar();// Inicio [gangles 01/06/2016] Se incluye criterios de búsqueda con ayuda asistida (palabra clave que permite resultados asociados a dicha palabra)// Fin [gangles 01/06/2016]
					} else {						
						$('#tblDocumentos').dataTable().fnClearTable();
						$('#tblDocumentos').dataTable().fnDraw();						
					}
				
				},
				error : function(response) {
					
						$('#tblDocumentos').dataTable().fnClearTable();
						$('#tblDocumentos').dataTable().fnDraw();
						mostrarPagError();					
				}
				
			});
		}

	function nuevaPlantilla(){
	
		var codProceso = $("#selCodigoProceso").val();
		var codTipExpediente = $("#selCodigoTipoExpendiente").val();
		var desPlanReporte =$("#dessPlanReporte").val();
		
		//obtenemos los documetos seleccionados
		var datos = [];
		var contador=0;
	    var dataJson = $('#tblDocumentos').DataTable().rows('.seleccionado').data();	
		for (var i = 0; i < dataJson.length; i++) {
			
			if($('#'+dataJson[i].codTipoDocumento).prop('checked')){
				var obj = new Object;
				obj.CODDOCUMENTO = dataJson[i].codTipoDocumento;
				obj.CORDETPLANTI = $('#'+dataJson[i].codTipoDocumento).val();
				datos[contador] = obj;
				contador++;
			}
		}
		
		var listaDocumentosSel = JSON.stringify(datos);
		
		//obtenemos los formatos adicionales seleccionados
		var datos = [];
		var contador=0;
	    var dataJson = $('#tblFormatos').DataTable().rows().data();	
		for (var i = 0; i < dataJson.length; i++) {
			if($('#'+dataJson[i].codParametro).prop('checked')){
				var obj = new Object;
				obj.CODDOCUMENTO = dataJson[i].codParametro;
				obj.CORDETPLANTI = $('#'+dataJson[i].codParametro).val();
				datos[contador] = obj;
				contador++;
			}
		}
		
		var listaFormatosSel = JSON.stringify(datos);
		
		//obtenemos los reportes tributarios aduaneros seleccionados
		var opcionSeleccionada = $('#selOpcionRepTribAdu').val();
		var datos = [];
		if(opcionSeleccionada=='1'){
			var contador=0;
		    var dataJson = $('#tblReportes').DataTable().rows().data();	
			for (var i = 0; i < dataJson.length; i++) {
				if($('#reporte-'+dataJson[i].codParametro).prop('checked')){
					var obj = new Object;
					obj.CODDOCUMENTO = dataJson[i].codParametro;
					obj.CORDETPLANTI = $('#reporte-'+dataJson[i].codParametro).val();
					datos[contador] = obj;
					contador++;
				}
			}
		}
		
		var listaReportesSel = JSON.stringify(datos);
		
		/*$('#listaDocumentosSel').val(listaDocumentosSel);
		$('#listaFormatosSel').val(listaFormatosSel);
		$('#listaReportesSel').val(listaReportesSel);
		$('#descProceso').val(descProceso);
		$('#descTipoExpediente').val(descTipoExpediente);
		$('#desPlanReporte').val(desPlanReporte);*/
		var numPlantilla = $('#numPlantilla').val();
		
		var dataEnvio = new Object();
		dataEnvio.listaDocumentosSel = listaDocumentosSel;
		dataEnvio.listaFormatosSel = listaFormatosSel;
		dataEnvio.listaReportesSel = listaReportesSel;
		dataEnvio.codProceso = codProceso;
		dataEnvio.codTipExpediente = codTipExpediente;
		dataEnvio.codOpcion = opcionSeleccionada;
		dataEnvio.numPlantilla = numPlantilla;
		
		$.ajax({
				
				url : '${pageContext.request.contextPath}/mantPlantRep.htm?action=modificarPlantilla&desPlantilla='+encodeURIComponent(desPlanReporte),
				type : 'POST',
				async : true,
				dataType : "json",
				data : JSON.stringify(dataEnvio),
				contentType : "application/json",
				mimeType : "application/json",
				//timeout : 5000,
				success : function(response) {
					
					codigoPlantilla = response.codigoPlantilla;
					var mensae = "Se han guardado los cambios realizados a la plantilla N&deg; "+ codigoPlantilla;
					$('#numPlantilla').val(codigoPlantilla);
					mostrarDlgInfo(titulos.tituloDefecto, mensae);
				},
				error : function(response) {
					
						mostrarPagError();
					
				}
				
			});
	}		
		
	function validarSalida(){
		
	
			titulo = titulos.tituloDefecto;
			msj = "Est&aacute seguro que desea salir?";
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
				$('#'+dato.codTipoDocumento).parents('tr').addClass('seleccionado');
			}	
		});
		var oTable = $('#tblDocumentos').DataTable();
		var totalRecords=oTable.rows().data().length;
		if(totalRecords==oTable.rows('.seleccionado').data().length){
			$('#selectAllDocumentos').prop('checked',true);
		}else{
			$('#selectAllDocumentos').prop('checked', false);
		} 
		
		//formato adicional
		$.each(listT6612FormatosAdicionales, function(i, dato) {
			if($('#'+dato.codFormatoAdi).length){
				$('#'+dato.codFormatoAdi).prop("checked", "checked");
				$('#'+dato.codFormatoAdi).prop("value", dato.numCorDetPlanti);
				$('#'+dato.codFormatoAdi).parents('tr').addClass('seleccionado');
			}	
		});
		
		var oTable = $('#tblFormatos').DataTable();
		var totalRecords=oTable.rows().data().length;
		if(totalRecords==oTable.rows('.seleccionado').data().length){
			$('#selectAllFormatos').prop('checked',true);
		}else{
			$('#selectAllFormatos').prop('checked', false);
		} 
		//Reporte tributario
		$.each(listT6612ReportesTribAdua, function(i, dato) {
			if($('#reporte-'+dato.codRepTrbib).length){
				$('#reporte-'+dato.codRepTrbib).prop("checked", "checked");
				$('#reporte-'+dato.codRepTrbib).prop("value", dato.numCorDetPlanti);
				$('#reporte-'+dato.codRepTrbib).parents('tr').addClass('seleccionado');
			}	
		});
		
		var oTable = $('#tblReportes').DataTable();
		var totalRecords=oTable.rows().data().length;
		if(totalRecords==oTable.rows('.seleccionado').data().length){
			$('#selectAllReportes').prop('checked',true);
		}else{
			$('#selectAllReportes').prop('checked', false);
		} 
		
	}
	
	// Inicio [gangles 31/05/2016] Se agrega la funcionalidad de la previsualización de la plantilla a modificar
	function previsualizarPlantilla(){
		var descProceso = $("#selCodigoProceso option:selected").text();
		var descTipoExpediente = $("#selCodigoTipoExpendiente option:selected").text();
		var desPlanReporte =$("#dessPlanReporte").val();
	
		//obtenemos los documentos seleccionados
		var datos = [];
		var contador=0;
	    var dataJson = $('#tblDocumentos').DataTable().rows('.seleccionado').data();	
		for (var i = 0; i < dataJson.length; i++) {
			
			//if($('#'+dataJson[i].codTipoDocumento).prop('checked')){
				var obj = new Object;
				obj.codTipoDocumento = dataJson[i].codTipoDocumento;
				obj.desTipoDocumento = dataJson[i].desTipoDocumento;
				datos[contador] = obj;
				contador++;
			//}
		}
		
		var listaDocumentosSel = JSON.stringify(datos);
		
		//obtenemos los formatos adicionales seleccionados
		var datos = [];
		var contador=0;
	    var dataJson = $('#tblFormatos').DataTable().rows().data();	
		for (var i = 0; i < dataJson.length; i++) {
			if($('#'+dataJson[i].codParametro).prop('checked')){
				var obj = new Object;
				obj.codFormato = dataJson[i].codParametro;
				obj.desFormato = dataJson[i].desParametro;
				datos[contador] = obj;
				contador++;
			}
		}
		
		var listaFormatosSel = JSON.stringify(datos);
		
		//obtenemos los reportes tributarios aduaneros seleccionados
		var opcionSeleccionada = $('#selOpcionRepTribAdu').val();
		var datos = [];
		if(opcionSeleccionada=='1'){
			var contador=0;
		    var dataJson = $('#tblReportes').DataTable().rows().data();	
			for (var i = 0; i < dataJson.length; i++) {
				if($('#reporte-'+dataJson[i].codParametro).prop('checked')){
					var obj = new Object;
					obj.codRubro = dataJson[i].codParametro;
					obj.desRubro = dataJson[i].desParametro;
					datos[contador] = obj;
					contador++;
				}
			}
		}
			
		var listaReportesSel = JSON.stringify(datos);
		/*var url='${pageContext.request.contextPath}/mantPlantRep.htm?action=previsualizarPlantilla'
			+'&listaDocumentosSel='+listaDocumentosSel+'&listaFormatosSel='+
			listaFormatosSel+'&listaReportesSel='+listaReportesSel+'&descProceso='+descProceso+'&descTipoExpediente='+descTipoExpediente+'&desPlanReporte='+desPlanReporte;
			window.open(url, '_blank');	*/
		// Fin [gangles 31/05/2016]
			
		//Inicio [gangles 23/06/2016]		
		$('#listaDocumentosSel').val(listaDocumentosSel);
		$('#listaFormatosSel').val(listaFormatosSel);
		$('#listaReportesSel').val(listaReportesSel);
		$('#descProceso').val(descProceso);
		$('#descTipoExpediente').val(descTipoExpediente);
		$('#desPlanReporte').val(desPlanReporte);
		
		var formURL = '${pageContext.request.contextPath}/mantPlantRep.htm?action=previsualizarPlantilla';
		document.forms.frmPlantillas.action = formURL;
		document.forms.frmPlantillas.method = "post";
		document.forms.frmPlantillas.target = "_blank";
		document.forms.frmPlantillas.submit();		
		//Fin [gangles 23/06/2016]
	}
	
	function validarFormularioPrevisualizacion(){
		flagPrevisualizacion=true;
		$('#frmPlantillas').submit();
		
	}
	
	
		// Inicio [gangles 01/06/2016] 14) Incluir criterios de búsqueda con ayuda asistida (palabra clave que permite resultados asociados a dicha palabra).
	function filtrar() {
				
		$('#selectAllDocumentos').prop('checked',false);
		$('#selectAllDocumentos').prop('disabled', true);
		
		var critBusqueda = $('#txtBusqueda').val();
		
		var oTable = $('#tblDocumentos').DataTable();
		var totalRecords=oTable.rows().data().length;
		oTable.search(critBusqueda, 3, true, false);	
		oTable.draw();
		
		var totalDisplayRecords=$('#tblDocumentos tbody >tr').length;
	
		if (totalDisplayRecords>0){
			jQuery('#selectAllDocumentos').prop('disabled', false);
		}else{
			jQuery('#selectAllDocumentos').prop('disabled', true);
		}				
						
		if(totalRecords==oTable.rows('.seleccionado').data().length){
			$('#selectAllDocumentos').prop('checked',true);
		}else{
			$('#selectAllDocumentos').prop('checked', false);
		} 				
		actualizarNumeroOrden ();				
		$('#frmPlantillas').bootstrapValidator('revalidateField', 'tblDocumentos');	
		$('#frmPlantillas').bootstrapValidator('revalidateField', 'tblFormatos');        
	}
	
	function actualizarNumeroOrden(){
		var fila = $('#tblDocumentos tr');
		var cont=0;
		// 1 es la cabecera de la tabla				
		for (var i = 1; i <= fila.length-1; i++) {						
			col = fila[i];
			if(col.cells.length>1){
				aux =  col.cells[0].innerText;
				if(aux != ""){
					col.cells[0].innerText = i;					
				}
			}
			
		}
		
	}
	
	function limpiarTextoBusqueda(){
		$('#txtBusqueda').val('');
		filtrar();
	}
	
	//Fin [gangles 01/06/2016]
 </script>
</head>
<body>
</br>
	<div id="container" class="container" style="width: 95%">
		<div>
			<div class="row">
				<div class="panel panel-primary">
					<div class="panel-heading align="center">
						<h3 class="panel-title" align="center">MODIFICAR PLANTILLA DE REPORTE DE EXPEDIENTES DE TRABAJO</h3>
					</div>
				</div>	
				<div class="panel panel-primary">
					<form class="form-horizontal" role="form" name="frmPlantillas" id="frmPlantillas" method="post">
					<input type="hidden" id="listaDocumentosSel" name="listaDocumentosSel">
						<input type="hidden" id="listaFormatosSel" name="listaFormatosSel">
						<input type="hidden" name="listaReportesSel" id="listaReportesSel">
						<input type="hidden" name="descProceso" id="descProceso">
						<input type="hidden" name="descTipoExpediente" id="descTipoExpediente">
						<input type="hidden" name="desPlanReporte" id="desPlanReporte">		
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
						<div class="form-group"><!-- Inicio [gangles 01/06/2016] Se añade el botón marcar y desmarcar todos -->
								<div class="col-md-12" >
									&nbsp;
								</div>
								<div class="col-md-12">
									<div class="col-md-1">
											<label>Buscar: </label>
									</div>
									<div class="col-md-4">
											<input id="txtBusqueda" name="txtBusqueda" type="text" class="form-control form-control-single"/> 
									</div>
									<div class="col-md-1">
										<!--<button type="button" class="btn btn-primary" id="btnLimpiarTexto" intermediateChanges="false" onClick="limpiarTextoBusqueda()">
											<span class="glyphicon glyphicon-remove"></span>
										</button>
										<input type="button" class="btn btn-primary" id="btnLimpiarTexto" intermediateChanges="false" onClick="limpiarTextoBusqueda()"><span class="glyphicon glyphicon-remove"></span></input>-->
									</div>
									<div class="col-md-6" >
										&nbsp;
									</div>
								</div>
							</div><!-- Fin [gangles 01/06/2016]-->
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
												<th width="5%" class="center"><input type="checkbox" id="selectAllDocumentos"></th><!-- Inicio [gangles 30/05/2016] Se añade el botón marcar y desmarcar todos --><!-- Fin [gangles 30/05/2016]-->
												<th width="10%" class="text-center">Cod. Tipo de Documento</th>
												<th width="80%" class="text-center">Tipo de Documento</th>
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
												<th width="5%" class="center"><input type="checkbox" id="selectAllFormatos"></th><!-- Inicio [gangles 30/05/2016] Se añade el botón marcar y desmarcar todos --><!-- Fin [gangles 30/05/2016]-->
												<th width="10%" class="text-center">Cod. Formato</th>
												<th width="80%" class="text-center">Descripci&oacute;n del Formato</th>
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
							<div class="form-group">
								<div class="col-md-4"></div>
								<div class="col-md-3"><Strong>Desea Reporte Tributario Aduanero</Strong></div>
								<div class="col-md-1">
									<select name="codOpcion" id="selOpcionRepTribAdu" onchange="revalidarTablaReportes()" class="form-control tamanoMaximo"> 
										<option value="1" >Si</option>
										<option value="2" selected >No</option>
									</select>
								</div>
							</div>							
							<div class="form-group">
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
												<th width="10%" class="center"><input type="checkbox" id="selectAllReportes"></th><!-- Inicio [gangles 30/05/2016] Se añade el botón marcar y desmarcar todos --><!-- Fin [gangles 30/05/2016]-->
												<th width="15%" class="text-center">Cod. Rubro</th>
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
								<div class="col-md-3"><Strong>N&uacute;m. Plantilla:</Strong></div>
								<div class="col-md-2">
									<input type='text' class="form-control tamanoMaximo" id='numPlantilla' name='numPlantilla' value=${numPlantilla} readonly/>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-5">
									<Strong>Descripci&oacute;n de la Plantilla de Reporte:</Strong>
									<input type='text' class="form-control tamanoMaximo" id='dessPlanReporte' name='dessPlanReporte' maxlength="100" value = '${desPlantilla}' />
								</div>	
								<div class="col-md-7">
								</div>
							</div>
							<div class="form-group">
							<div class="col-md-5">
							</div>
							<div class="col-md-7">
									<input type="button" class="btn btn-primary"  id="btnConsulta" intermediateChanges="false"  onClick="revalidarFormulario()" value="Guardar" id="btnCargarr"></input>
									<input type="button" class="btn btn-primary"  id="btnPrevisualizar" intermediateChanges="false" onClick="validarFormularioPrevisualizacion()" value="Previsualización Plantilla"></input> <!-- Inicio [gangles 30/05/2016] Se añade el botón para la previsualización de la plantilla a modificar --><!-- Fin [gangles 30/05/2016]-->
									<input type="button" class="btn btn-primary" id="btnExportar" intermediateChanges="false" onClick="validarSalida()" value="Cancelar"></input>
							</div>
							</div>
						</fieldset>		
					</form>
				</div>		
			</div>
		</div>
	</div>	
	<!-- Dialogo -->
		<div id="modalDlg" class="modal fade" role="dialog" data-backdrop='static' data-keyboard='false'>
			<div class="modal-dialog">
				<div class="panel panel-info">
					<div class="panel-heading">
						<button type="button" onClick= volverPagina(); class="close" data-dismiss="modal" aria-label="Close">
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

