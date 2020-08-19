
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
	
 </style>

 <script type="text/javascript">
 
	var listadoProcesos = ${listadoProcesos};
	//var listaTipoBusquedaFecha =  ${listadoTipoFecha};
	
	var excepciones =  ${excepciones};
	var titulos =  ${titulos};
	var numExpedienteConsultar="";
	var numRequerimientoConsultar="";
	var retorno=false;
	var realizarBusqueda=false;
	
	var tblPage = "${beanParametrosConsultaReq.tblPage}";  //anadir
	
	var gNumExpBlur = true;
	
	$(function () {
    	
		$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
		construirTablaRequerimiento( [] );
		$('#tblRequerimientos tbody').on('mouseenter', 'tr', function() {
			$(this).addClass("selected");
		});	
		$('#tblRequerimientos tbody').on( 'mouseleave', 'tr', function () {
			 $(this).removeClass("selected");
			 //$('#tblRequerimientos').dataTable().fnDraw();
		} );
		inicializarProcesos();
		var table = $('#tblRequerimientos').DataTable();
 
		$('#tblRequerimientos tbody').on( 'mouseenter', 'tr', function () {
			indexDocumentos = table.row( this ).index();
			
		} );
		
		$('#divFechaDesde').datetimepicker({
            format: 'DD/MM/YYYY',
			useCurrent: false,
			language: 'es',
			autoClose: true,
			forceParse: true,
			pickTime: false
        });
		
		$('#divFechaHasta').datetimepicker({
            format: 'DD/MM/YYYY',
			useCurrent: false,
			language: 'es',
			autoClose: true,
			forceParse: true,
			pickTime: false
        });
		
		$("#divFechaDesde").on("dp.hide", function (e) {
            $('#divFechaHasta').data("DateTimePicker").setMinDate(e.date);
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecDesde');
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecHasta');
			resetearFormularioConEsp(event);
		});
		
		$("#divFechaHasta").on("dp.hide", function (e) {
			$('#divFechaDesde').data("DateTimePicker").setMaxDate(e.date);
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecDesde');
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecHasta');
			resetearFormularioConEsp(event);
		});
		
		/*$("#selTipoBusquedaFecha").on("dp.change", function (e) {
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecDesde');
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecHasta');
			resetearFormularioConEsp(event);
		});*/
		
				
		$('#btnLimpiar').on("mouseover", function(e) {
			gNumExpBlur = false;
		});
		$('#btnLimpiar').on("mouseout", function(e) {
			gNumExpBlur = true;
		});
		
		$( "#txtNumeroExpediente" ).on( "click", function() {
			if($("#selCodigoProceso").val()!=""){
				$("#btnLimpiar").click();
				$( "#txtNumeroExpediente" ).focus();
			}
		});
		
		$( "#txtNumeroExpediente" ).blur(function() {
			retorno=true;
			$('#frmFiltroBusquedaEspecifica').bootstrapValidator('revalidateField', 'numExp');
			if(!gNumExpBlur){
				removerDatosBusquedaSession();
			}
		});
						
		//Validaciones
		//Validaciones del Formulario Busqueda
		$('#frmFiltroBusquedaEspecifica').bootstrapValidator({
			excluded: [':disabled'],
			fields: {
				numExp: {
					validators: {
						notEmpty: {
                            message: 'Ingrese N&uacute;mero de Expediente.'
                        },
						callback: {
                            message: '',
                            callback: function (value, validator, $field) {
								if(retorno){
									var numExpediente = $('#txtNumeroExpediente').val();
									if (numExpediente != "") {
										if (numExpediente.length > 13) {
											$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateMessage', 'numExp', 'callback', 'Ingrese N&uacute;mero Expediente V&aacute;lido.');
											$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateOption', 'numExp', 'callback', 'Ingrese N&uacute;mero Expediente V&aacute;lido.');
											retorno = false;
											return false;
											realizarBusqueda=false;
										}
									}
									retorno = false;
									realizarBusqueda=true;
									return true;
								}
								retorno = false;
								realizarBusqueda=false;
								return true;
                            }
                        }
						
					}
				}
			}
		}).on('success.form.bv', function(e) {
            e.preventDefault();
			if(realizarBusqueda){
				buscar();
			}
    	}).on('error.form.bv', function(e) {
            $('#tblRequerimientos').dataTable().fnClearTable();
			$('#tblRequerimientos').dataTable().fnDraw();
		});
		
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator({
			excluded: [':disabled'],
			fields: {
				codProceso: {
                    validators: {
                        notEmpty: {
                            message: 'Seleccione un Proceso.'
                        }
                    }
                },
				codTipexp: {
                    validators: {
                        notEmpty: {
                            message: 'Seleccione un Tipo de Expediente.'
                        }
                    }
                },
				fecDesde: {
                    validators: {
                        notEmpty: {
                            message: 'Seleccione Fecha Desde.'
                        },
						date: {
							format: 'DD/MM/YYYY',
							message: 'Formato Fecha Incorrecto.'
						},
						callback: {
							message: '',
							callback:   function (value, validator, $field) {
								var fechaDesde = document.getElementById("txtfechaDesde").value;
								var fechaHasta = document.getElementById("txtfechaHasta").value;
								
								if(fechaDesde!="" && fechaHasta!=""){
									if(!validacionFechas()){
										$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateMessage', 'fecDesde', 'callback', excepciones.excepcion01);
										$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateOption', 'fecDesde', 'callback', excepciones.excepcion01);
										return false;
									}
								}
								return true;
							}
						}
                    }
                },
				fecHasta: {
                    validators: {
                        notEmpty: {
                            message: 'Seleccione Fecha Hasta.'
                        },
						date: {
							format: 'DD/MM/YYYY',
							message: 'Formato Fecha Incorrecto.'
						},
						callback: {
							message: '',
							callback:   function (value, validator, $field) {
								var fechaDesde = document.getElementById("txtfechaDesde").value;
								var fechaHasta = document.getElementById("txtfechaHasta").value;
								
								if(fechaDesde!="" && fechaHasta!=""){
									if(!validacionFechas()){
										$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateMessage', 'fecHasta', 'callback', excepciones.excepcion01);
										$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateOption', 'fecHasta', 'callback', excepciones.excepcion01);
										
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
			buscar();
    	}).on('error.form.bv', function(e) {
            $('#tblRequerimientos').dataTable().fnClearTable();
			$('#tblRequerimientos').dataTable().fnDraw();
		});
		cargarFormulario();
		$( window ).off('resize');
	})
	
	function cargarFormulario() {
    	var realizarBusqueda = '${beanParametrosConsultaReq.realizarBusqueda}';
    	
    	if(realizarBusqueda=="1"){
			var numExp = '${beanParametrosConsultaReq.numExp}';
			if(numExp!=""){
				$('#txtNumeroExpediente').val(numExp);
			}else{
				$('#selCodigoProceso').val('${beanParametrosConsultaReq.codProceso}');
				cargarTiposExpedientes();
				$('#selCodigoTipoExpendiente').val('${beanParametrosConsultaReq.codTipexp}');
				$('#txtfechaDesde').val('${beanParametrosConsultaReq.fecDesde}');
				$('#txtfechaHasta').val('${beanParametrosConsultaReq.fecHasta}');
			}
			retorno = true;
			realizarBusqueda = true;
    		revalidarFormulario();
    	}
    }
	
	function inicializarProcesos() {
    	//nchavez [08/06/2016]
		makeInputMask( '#txtNumeroExpediente', "(9){1,17}", 17, '' );
		//$('#btnConsulta').prop('disabled', true);
		$('#txtfechaDesde').prop('readonly', true);
		$('#txtfechaHasta').prop('readonly', true);
		//procesos
		var $element = $('#selCodigoProceso');
		$.each(listadoProcesos, function(i, dato) {
			var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
			$element.append($option);
		});
	
		//fecha
		/*var $element = $('#selTipoBusquedaFecha');
		$.each(listaTipoBusquedaFecha, function(i, dato) {
			var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
			$element.append($option);
		});
		$('#selTipoBusquedaFecha').val("1");
		$('#selTipoBusquedaFecha').attr("disabled",true);
		*/
	
	}
	
    function limpiar(){
		resetearFormularioConEsp(event);
		$('#tblRequerimientos').dataTable().fnClearTable();
		$('#tblRequerimientos').dataTable().fnDraw();
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('resetForm', true);
		$('#txtNumeroExpediente').prop('disabled', false);
		
		$('#divFechaDesde').data("DateTimePicker").destroy();
		$('#divFechaHasta').data("DateTimePicker").destroy();

		$('#divFechaDesde').datetimepicker({
            format: 'DD/MM/YYYY',
			useCurrent: false,
			language: 'es',
			autoClose: true,
			forceParse: true,
			pickTime: false
        });
		
		$('#divFechaHasta').datetimepicker({
            format: 'DD/MM/YYYY',
			useCurrent: false,
			language: 'es',
			autoClose: true,
			forceParse: true,
			pickTime: false
        });	
		
		$('#selCodigoTipoExpendiente').empty();
		$('#selCodigoTipoExpendiente').append($('<option>', {
			value: '',
			text: 'Seleccione'
		}));
		$( window ).off('resize');
		$("#btnLimpiar").blur();
		//location.reload();
	}
	
    function cargarTiposExpedientes() {
		resetearFormularioConEsp(event);
		var codProceso = $('#selCodigoProceso').val();
		
		$('#selCodigoTipoExpendiente').empty();
		
		$('#selCodigoTipoExpendiente').append($('<option>', {
			value: '',
			text: 'Seleccione'
		}));
		
		var dataEnvio = new Object();
		
		if (codProceso == "") {			
			$('#selCodigoTipoExpendiente').val("");
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'codTipexp');
		} else {
			
			dataEnvio.codProceso = codProceso;
			
			$.ajax({
	            url: '${pageContext.request.contextPath}/cargarDocumentoIt.htm?action=cargarListaTiposExpediente',
	           	data: "&codProceso="+codProceso,
	            cache: false,
	            async: false,
	            type: 'POST', //eaguilar 12 JUL
	            contentType : "application/x-www-form-urlencoded; charset=utf-8", //eaguilar 12 JUL
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
    
    function getPaginaTbl(idTabla){
    	return $('#'+idTabla).DataTable().page.info().page;
    }
    		
    function setPagintaTbl(idTabla, idxPagina){
    	$('#'+idTabla).DataTable().page(idxPagina).draw(false);
    }
    
	function construirTablaRequerimiento(dataGrilla) {
	
		$('#tblRequerimientos').DataTable({
			"language": {
				"url"		:	"/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
			},
			tableTools: {
		        "sSwfPath": "../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/TableTools/swf/copy_csv_xls.swf"
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
			 fnRowCallback: function (nRow, aData, iDisplayIndex) {
                  $(nRow).attr('name', aData[1]);
					$(nRow).attr('align', 'center');
					$(nRow).attr('rowClasses','tableOddRow');
                  return nRow;
          	},
			columns : [
 				{data : "numExpedienteVirtual", sClass: 'left alinearCentrado'},
				{data : "desProceso", sClass: 'left alinearCentrado'},
				{data : "desTipoExpediente", sClass: 'left alinearCentrado'},
				{data : "fecRegis", sClass: 'left alinearCentrado'},
				{data : "codEstado", sClass: 'left alinearCentrado'}
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

	//Inicio nchavez [31/05/2016]
	function guardarNumRequerimiento(){
		
		// $('#btnConsulta').prop('disabled', false);
		var dataJson = $('#tblRequerimientos').DataTable().rows().data();	
			numExpedienteConsultar = dataJson[indexDocumentos].numExpedienteVirtual;
			numRequerimientoConsultar = dataJson[indexDocumentos].numRequerimiento;
// 		cargarDocumentos();
		cargarDatosBusquedaSession();
	}
	//Fin nchavez [31/05/2016]
	
	function cargarDocumentos(){
		//PAS20201U210500029 - HHC INICIO
// 		var  url = '${pageContext.request.contextPath}/cargarDocumentoIt.htm?action=consultarDetalleRequerimientoView&numExpedienteVirtual='+numExpedienteConsultar+'&numRequerimiento='+numRequerimientoConsultar;
		var  url = '${pageContext.request.contextPath}/cargarDocumentoIt.htm?action=consultarDetalleRequerimientoView&numExpedienteVirtual='+numExpedienteConsultar;
		//PAS20201U210500029 - HHC FIN
		$(location).prop( 'href', url);
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
	
	//mostrar PÃ¢Â¨Â©na de Error
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
			
	function revalidarFormulario() {
		var numExpediente = $('#txtNumeroExpediente').val();
		
		if(numExpediente != "") {
			$('#frmFiltroBusquedaEspecifica').submit();
		} else {
			$('#frmFiltroBusquedaAvanzada').submit();
		}
	}
	
	function resetearFormularioConEsp(event){
		$('#frmFiltroBusquedaEspecifica').bootstrapValidator('resetForm', true);
		$('#selTipoBusquedaExpediente').val("");
		
		var current = event.target || event.srcElement;
		if(current!=null && current!='null' && current!='undefined'){
			if($("#selCodigoProceso").val() == "" && current.id != "txtRuc") $('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'codTipexp');
			//$('#txtNumeroExpediente').prop('disabled', true);
		}
	}
	
	function resetearFormularioAvan(){
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('resetForm', true);
		$('#selTipoBusquedaExpediente').val("");
		$('#selCodigoTipoExpendiente').empty();
		$('#selCodigoTipoExpendiente').append($('<option>', {
			value: '',
			text: 'Seleccione'
		}));
	}	
	
	function buscar() {
			//$('#btnConsulta').prop('disabled', true);
			listaRequerimientos = [];
			
			$.ajax({
				url : '${pageContext.request.contextPath}/otrosEscritosElecronicos.htm?action=cargarListadoExpedientesPendientes',
				type : 'POST', //eaguilar 12 JUL
				async : true,
				dataType : "json",
				data : $('#frmFiltroBusquedaEspecifica').serialize()+'&'+$('#frmFiltroBusquedaAvanzada').serialize(),
				contentType : "application/x-www-form-urlencoded", //eaguilar 12 JUL
				mimeType : "application/json",
				//timeout : 5000,
				success : function(response) {
					
					var msjError = response.msjError;
					if(msjError!="" && msjError!=undefined){
						$('#tblRequerimientos').dataTable().fnClearTable();
						$('#tblRequerimientos').dataTable().fnDraw();
						mostrarDlgInfo(titulos.tituloDefecto, msjError);
						return;
					}
					
					listaRequerimientos = response.listT6620RequerimBean;
					
					
					if (listaRequerimientos.length > 0) {
						
						$('#tblRequerimientos').dataTable().fnClearTable();
						$('#tblRequerimientos').dataTable().fnAddData(listaRequerimientos);
						$('#tblRequerimientos').dataTable().fnDraw();
						
						if(tblPage != "") {
							setPagintaTbl("tblRequerimientos", parseInt(tblPage)); //anadir
							tblPage = "";
						}
						
					} else {
						
						$('#tblRequerimientos').dataTable().fnClearTable();
						$('#tblRequerimientos').dataTable().fnDraw();
						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion05);
						
					}
				
				},
				error : function(response) {
					
						$('#tblRequerimientos').dataTable().fnClearTable();
						$('#tblRequerimientos').dataTable().fnDraw();
					mostrarPagError();
					
				}
				
			});
		}
		
		function validacionFechas() {
	
		var fechaDesde = document.getElementById("txtfechaDesde").value;
		var fechaHasta = document.getElementById("txtfechaHasta").value;
		var dd = fechaDesde.substring(0, 2);
		var mm = fechaDesde.substring(3, 5);
		var yyyy = fechaDesde.substring(6, 10);
		var rango = parseInt(excepciones.rangoFecha);
		var fechaCompara = new Date(mm+"/"+dd+"/"+yyyy);
		var rangoFechaMaxBusqueda = fechaCompara.addMonths(3);
		var anhoLimite = rangoFechaMaxBusqueda.getFullYear();
		var mesLimite = rangoFechaMaxBusqueda.getMonth() + 1;
		var diaLimite = rangoFechaMaxBusqueda.getDate();
		var anhoLimiteStr;
		var mesLimiteStr;
		var diaLimiteStr;
			
		anhoLimiteStr = anhoLimite.toString();
			
		if (mesLimite < 10) {
				
			mesLimiteStr = '0' + mesLimite.toString();
				
		} else {
				
			mesLimiteStr = mesLimite.toString();
				
		}
			
		if (diaLimite < 10) {
				
			diaLimiteStr = '0' + diaLimite.toString();
				
		} else {
				
			diaLimiteStr = diaLimite.toString();
		}			
		var fechaLimite = diaLimiteStr+"/"+mesLimiteStr+"/"+anhoLimiteStr;
		if(comparafecha(fechaHasta, fechaLimite)){
			return true;
		}else{
			return false;
		}
	}
	
	function comparafecha(fecha1, fecha2) {
		
		dia = fecha1.substring(0, 2);
		mes = fecha1.substring(3, 5);
		anho = fecha1.substring(6, 10);
		fecha1x = anho + mes + dia;
		dia = fecha2.substring(0, 2);
		mes = fecha2.substring(3, 5);
		anho = fecha2.substring(6, 10);
		fecha2x = anho + mes + dia;
		if(fecha1x<=fecha2x){
			return true;
		}else{
			return false;
		}
	}
	
	Date.isLeapYear = function (year) { 
			return (((year % 4 === 0) && (year % 100 !== 0)) || (year % 400 === 0)); 
		}

		Date.getDaysInMonth = function (year, month) {
			return [31, (Date.isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month];
		}

		Date.prototype.isLeapYear = function () { 
			return Date.isLeapYear(this.getFullYear()); 
		}

		Date.prototype.getDaysInMonth = function () { 
			return Date.getDaysInMonth(this.getFullYear(), this.getMonth());
		}

		Date.prototype.addMonths = function (value) {
			var n = this.getDate();
			this.setDate(1);
			this.setMonth(this.getMonth() + value);
			this.setDate(Math.min(n, this.getDaysInMonth()));
			return this;
		}
		
		function exportar(){
			var dataExp = $('#tblRequerimientos').dataTable().fnGetData();
			if(dataExp.length > 0){
				var listaCadena = JSON.stringify(dataExp);
				var formURL = 'cargarDocumentoIt.htm?action=exportarExcelExpedientes';
				document.forms.formPrincipal.action = formURL;
				document.forms.formPrincipal.method = "POST";
				$('#campoHiddenExp').val('');
				$('#campoHiddenExp').val(listaCadena);
				document.forms.formPrincipal.submit();
			}else{
				mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion02);
			}
		}
		
		function cargarDatosBusquedaSession(){
			
			$.ajax({
				
				url : '${pageContext.request.contextPath}/cargarDocumentoIt.htm?action=cargarDatosBusquedaSession'+"&tblPage="+getPaginaTbl("tblRequerimientos"),
				type : 'POST',
				async : false,
				dataType : "json",
				data : $('#frmFiltroBusquedaEspecifica').serialize()+'&'+$('#frmFiltroBusquedaAvanzada').serialize(),
				contentType : "application/x-www-form-urlencoded", //eaguilar 12 JUL
				mimeType : "application/json",
				success : function(response) {
					
// 					cargarDocumentos();
					
				},
				error : function(response) {
					
					mostrarPagError();
				}
				
			});
		}
		
		function removerDatosBusquedaSession(){
			
			//removemos los datos guardados en session
			$.ajax({
				
				url : '${pageContext.request.contextPath}/cargarDocumentoIt.htm?action=removerDatosBusquedaSession',
				type : 'POST',
				async : false,
				dataType : "json",
				data : '',
				contentType : "application/x-www-form-urlencoded", //eaguilar 12 JUL
				mimeType : "application/json",
				success : function(response) {
					
					limpiar();
					
				},
				error : function(response) {
					
					limpiar();
				}
				
			});
		}
		
		function regEscritoElec(data) {
			var tabla = $('#tblRequerimientos').dataTable().fnGetData();
			//PAS20201U210500029 - HHC INICIO  - Comentar , las validaciones se realizaran en el requerimiento
			/*var numRequerimiento = tabla[indexDocumentos].numRequerimiento;
			var plazoPresentacion = tabla[indexDocumentos].plazoVigencia;						
			var estadoExp = tabla[indexDocumentos].codEstadoExp;			
			
			if (estadoExp != "01") {
				mostrarDlgInfo(titulos.tituloDefecto, "El expediente electr&oacute;nico debe encontrarse en estado Abierto.");
				
			} else if (plazoPresentacion < 0) {
				mostrarDlgInfo(titulos.tituloDefecto, "Sr. Contribuyente, ha vencido el plazo para cumplir con la presentaci&oacute;n de lo requerido.");
			
			}else{*/
				numExpedienteConsultar = tabla[indexDocumentos].numExpedienteVirtual;
// 				numRequerimientoConsultar = numRequerimiento;
				cargarDatosBusquedaSession();
// 				var formURL = 'cargarDocumentoIt.htm?action=registraEscrito';
				var formURL = 'cargarDocumentoIt.htm?action=consultaRequerimientos'; //HHC - CONSULTA DE REQUERIMIENTOS
				var listaCadena = JSON.stringify(tabla);
				document.forms.formPrincipal.action = formURL;
				document.forms.formPrincipal.method = "POST";
				$('#campoHiddenExp').val('');
// 				$('#hidTxtNumRequerimiento').val('');
				$('#hidTxtNumExpVirt').val('');
				$('#campoHiddenExp').val(listaCadena);
// 				$('#hidTxtNumRequerimiento').val(numRequerimiento);
				$('#hidTxtNumExpVirt').val(numExpedienteConsultar);
				document.forms.formPrincipal.submit();
				$('html').unblock();
// 			}			
			//PAS20201U210500029 - HHC INICIO
		}
 </script>

</head>
<body>
</br>
	<div id="container" class="container" style="width: 95%">
		<div class="row">
			<div class="panel panel-primary">
				<div class="panel-heading align="center">
					<h3 class="panel-title" align="center">PRESENTACI&Oacute;N DE OTROS ESCRITOS</h3>
				</div>
			</div>
			<div class="panel panel-primary">
				<form class="form-horizontal" role="form" name="frmFiltroBusquedaEspecifica" id="frmFiltroBusquedaEspecifica" method="post">
					<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border"> Consulta Espec&iacute;fica </legend>
							<div class="form-group">
								<div class="col-md-2"><Strong>N&uacute;mero de Expediente:</Strong></Strong></div>
								<div class="col-md-3">
									<input name="numExp" id="txtNumeroExpediente" type="text" class="form-control tamanoMaximo" placeHolder="" onchange="resetearFormularioAvan()"></input>
								</div>
							</div>
					</fieldset>		
				</form>	
				<form class="form-horizontal" role="form" name="frmFiltroBusquedaAvanzada" id="frmFiltroBusquedaAvanzada" method="post">
					<fieldset class="scheduler-border" style="margin : 15px 15px !important">
						<legend class="scheduler-border" > Consulta Avanzada </legend>
						<div class="form-group">
							<div class="col-md-2"><Strong>Proceso:</Strong></div>
							<div class="col-md-3">
								<select name="codProceso" id="selCodigoProceso" onchange="cargarTiposExpedientes()" class="form-control tamanoMaximo"> 
									<option value="">Seleccione</option>
								</select>
							</div>
							<div class="col-md-2"><Strong>Tipo de Expediente:</Strong></div>
							<div class="col-md-3">
								<select name="codTipexp" id="selCodigoTipoExpendiente" onchange="resetearFormularioConEsp(event)" class="form-control tamanoMaximo"> 
									<option value="">Seleccione</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-2"><Strong>Fecha de Expediente:</Strong></div>
						</div>
						<div class="form-group">
							<div class="col-md-2"><Strong>Desde:</Strong></div>
							<div class="col-md-3">
								<div class='input-group date tamanoMaximo' id="divFechaDesde">
										<input type='text' class="form-control tamanoMaximo" onkeypress="resetearFormularioConEsp(event)" id='txtfechaDesde' name='fecDesde' placeHolder="dd/mm/aaaa" /> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
							<div class="col-md-2"><Strong>Hasta:</Strong></div>
							<div class="col-md-3">
								<div class='input-group date tamanoMaximo' id="divFechaHasta">
										<input type='text' class="form-control tamanoMaximo" onkeypress="resetearFormularioConEsp(event)" id='txtfechaHasta' name='fecHasta' placeHolder="dd/mm/aaaa" /> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
								</div>
							</div>
						</div>
					</fieldset>
					<div class="form-group" style="margin : 15px 15px !important">
						<div class="col-md-12" align="right" id="dvSecBotones01">	
							<input type="button" class="btn btn-primary" id="btnLimpiar"intermediateChanges="false" data-dismiss="modal" onClick="removerDatosBusquedaSession()" value="Limpiar"></input>
							<input type="button" class="btn btn-primary" id="btnCancelar" intermediateChanges="false" onClick="revalidarFormulario()" value="Buscar"></input>
						</div>	
					</div>
				</form>	
			</div>	
			<div class="panel panel-primary">
				<div class="panel-heading align="center">
					<h3 class="panel-title" align="center">LISTA DE EXPEDIENTES</h3>
				</div>
			</div>
			<div class="panel panel-primary">
				<fieldset class="scheduler-border" style="margin : 15px 15px !important">
					<legend class="scheduler-border"> Listado de Expedientes </legend>
					<div class="form-group">
						<div class="col-md-12">	
							<div class="tab-content">
								<div id="accionesFiscalizacion" class="tab-pane fade in active">
									<div style="width: 100%; overflow-y: hidden; overflow-x:auto; border: 1px solid #337ab7;"> 
										<table id="tblRequerimientos" class="table display" style="width: 100%;">
											<thead>
												<tr>
													<th width="15%" class="text-center">N&deg; Expediente</th>
													<th width="10%" class="text-center">Proceso</th>
													<th width="20%" class="text-center">Tipo de Expediente</th>
													<th width="10%" class="text-center">Fecha del Expediente</th>
													<th width="10%" class="text-center">Etapa</th>
												</tr>
											</thead>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</fieldset>
			<form id="formPrincipal" class="form-horizontal" role="form">	
				<div class="col-md-5" align="right" id="dvSecBotones02">	
					<input id="campoHiddenExp" type="hidden" name="listadoExpedientesCadena"/> 
<!-- 					<input id="hidTxtNumRequerimiento" type="hidden" name="hidTxtNumRequerimiento"/> --><!--[HHC - PAS20201U210500029-->   
					<input id="hidTxtNumExpVirt" type="hidden" name="hidTxtNumExpVirt"/>
				</div>	
			</form>		
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
		</div>	
	</div>
	</body>
</html>