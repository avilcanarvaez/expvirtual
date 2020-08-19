
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=EDGE" />
<meta name="viewport" content="initial-scale = 1.0, user-scalable = no,  width=device-width">
<title>SISTEMA INTEGRADO DE EXPEDIENTE VIRTUAL - SIEV</title>

<!-- Bootstrap -->
<script src="/a/js/libs/jquery/1.11.2/jquery.min.js"></script>

<script src="/a/js/libs/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script
	src="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/media/js/jquery.dataTables.min.js"
	type="text/javascript" language="javascript"></script>
<script
	src="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Responsive/js/dataTables.responsive.js"
	type="text/javascript" language="javascript"></script>
<script
	src="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Scroller/js/dataTables.scroller.js"
	type="text/javascript" language="javascript"></script>

<link href="/a/js/libs/bootstrap/3.3.2/css/bootstrap.min.css"
	rel="stylesheet">
<link href="/a/js/libs/bootstrap/3.3.2/css/bootstrap-theme.min.css"
	rel="stylesheet">
<link
	href="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/media/css/jquery.dataTables.css"
	type="text/css" rel="stylesheet">
<link
	href="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Responsive/css/dataTables.responsive.css"
	type="text/css" rel="stylesheet">
<link
	href="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/media/css/jquery.dataTables_themeroller.css"
	type="text/css" rel="stylesheet">
<link
	href="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Scroller/css/dataTables.scroller.css"
	rel="stylesheet" type="text/css">

<link
	href="/a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" />
<!--<script src="/a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/js/moment.js" type="text/javascript"></script>-->
<script
	src="/a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/js/moment-with-locales.js"
	type="text/javascript"></script>
<script
	src="/a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/js/bootstrap-datetimepicker.min.js"
	type="text/javascript"></script>
<script src="/a/js/bootstrapvalidator/js/bootstrapValidator.min.js"></script>
<script src="/a/js/libs/bootstrap/3.3.2/plugins/jquery.inputmask-3.1/dist/jquery.inputmask.bundle.min.js" type="text/javascript"></script>
<script src="/a/js/js.js" type="text/javascript"></script>
<script src="/a/js/bootstrap/3.2.0/js/jquery.blockUI.js"
	type="text/javascript"></script>


<!--[if lt IE 10]>
      <script src="/a/js/libs/bootstrap/3.3.2/plugins/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="/a/js/libs/bootstrap/3.3.2/plugins/respond/1.4.2/respond.min.js"></script>
	<![endif]-->

<style type="text/css">
.blockUI h1 {
	font-size: 10px !important;
	font-weight: bold !important;
}

h1 {
	font-size: 1.7em;
}

.header div {
	padding-left: 0 !important;
}

.imgLogo {
	height: 2.5em;
	margin-top: 1em;
}

.text-left-important {
	text-align: left !important;
}

.hiddenDiv {
	display: none !important;
}

/*IE8*/
#codRegistro {
	text-transform: uppercase;
}

table.dataTable td.dataTables_empty {
	text-align: left !important;
}

.input-group-addon {
	padding-bottom: 0px !important;
	padding-top: 0px !important;
	padding-left: 6px !important;
	padding-right: 6px !important;
}

.form-control {
	font-size: 12px !important;
	height: 24px !important;
	padding-top: 3px !important;
	padding-bottom: 3px !important;
	padding-left: 5px !important;
	padding-right: 5px !important;
}

.btn {
	padding-top: 3px !important;
	padding-bottom: 3px !important;
	font-size: 12px !important;
}

body {
	font-size: 12px;
}

table.dataTable thead th {
	padding-bottom: 3px !important;
	padding-top: 3px !important;
	padding-left: 5px !important;
	padding-right: 5px !important;
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
	padding-bottom: 3px !important;
	padding-top: 3px !important;
	border-right: 1px solid gray !important;
}

.dataTables_wrapper {
	background: Gainsboro !important;
	border: 1px solid gray !important;
	border-collapse: collapse !important;
}

.dataTables_paginate {
	padding-top: 0px !important;
	align: center;
}

.paginate_button {
	padding-bottom: 3px !important;
	padding-top: 3px !important;
	padding-left: 3px !important;
	padding-right: 3px !important;
	border: 1px solid gray !important;
	margin-left: 0px !important;
}

.dataTables_wrapper .dataTables_info {
	padding-top: 3px !important;
}

/* other */
.current {
	background: CornflowerBlue !important;
}

.panel-info>.panel-heading {
	background-color: white;
	border-color: gray;
}

.marginedDiv { /*margin: 15px 15px !important;*/
	/*border: 1px #337ab7;*/
	border: 1px groove #ddd !important;
	border-style: solid;
	padding: 10px !important;
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
<script type="text/javascript">
	var listadoProcesos = ${listadoProcesos};
	var excepciones = ${excepciones};
	var titulos = ${titulos};
	var retorno = false;
	var realizarBusqueda = false;
	var tblPage = "${beanParametrosConsultaReq.tblPage}";

	$(function () {
    	
		$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
		construirTablaRequerimiento([]);
		$('#tblRequerimientos tbody').on('mouseenter', 'tr', function() {
			$(this).addClass("selected");
		});	
		$('#tblRequerimientos tbody').on( 'mouseleave', 'tr', function () {
			 $(this).removeClass("selected");
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
			$('#txtNumeroEscrito').val('');
			$('#txtNumeroExpediente').val('');
		});
		
		$("#divFechaHasta").on("dp.hide", function (e) {
			$('#divFechaDesde').data("DateTimePicker").setMaxDate(e.date);
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecDesde');
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecHasta');
			$('#txtNumeroEscrito').val('');
			$('#txtNumeroExpediente').val('');
		});
		
		$("#txtNumeroEscrito").on("click", function() {
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecDesde');
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecHasta');
			$('#txtfechaDesde').val('');
			$('#txtfechaHasta').val('');
			$('#txtNumeroExpediente').val('');
			$('#txtNumeroEscrito').focus();
		});
		
		$("#txtNumeroEscrito").blur(function() {
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecDesde');
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecHasta');
			$('#txtfechaDesde').val('');
			$('#txtfechaHasta').val('');
			$('#txtNumeroExpediente').val('');
		});
		
		$("#txtNumeroExpediente").on("click", function() {
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecDesde');
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecHasta');
			$('#txtfechaDesde').val('');
			$('#txtfechaHasta').val('');
			$('#txtNumeroEscrito').val('');
			$('#txtNumeroExpediente').focus();
		});
		
		$("#txtNumeroExpediente").blur(function() {
			retorno = true;
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'numExp');
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecDesde');
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecHasta');
			$('#txtfechaDesde').val('');
			$('#txtfechaHasta').val('');
			$('#txtNumeroEscrito').val('');
		});

		// Validaciones
		//Validaciones del Formulario Busqueda
		$('#frmFiltroBusquedaEspecifica').bootstrapValidator({
			excluded: [':disabled'],
			fields: {
				codProceso: {
                    validators: {
                        notEmpty: {
                            message: 'Seleccione un Proceso.'
                        }
                    }
                }
			}
		}).on('success.form.bv', function(e) {
            e.preventDefault();
			//buscar();
    	}).on('error.form.bv', function(e) {
            $('#tblRequerimientos').dataTable().fnClearTable();
			$('#tblRequerimientos').dataTable().fnDraw();
		});
	
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator({
			excluded: [':disabled'],
			fields: {
				numExp: {
					validators: {
						callback: {
                            message: '',
                            callback: function (value, validator, $field) {
								if(retorno) {
									var numExpediente = $('#txtNumeroExpediente').val();
									if(numExpediente != "") {
										if(numExpediente.length >= 17) {
											$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateMessage', 'numExp', 'callback', 'Ingrese N&uacute;mero Expediente V&aacute;lido.');
											$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateOption', 'numExp', 'callback', 'Ingrese N&uacute;mero Expediente V&aacute;lido.');
											retorno = false;
											realizarBusqueda = false;
											return false;
										}
									}
								}
								retorno = false;
								realizarBusqueda = false;
								return true;
                            }
                        }
					}
				},
				fecDesde: {
                    validators: {
                        /*notEmpty: {
                            message: 'Seleccione Fecha Desde.'
                        },*/
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
                        /*notEmpty: {
                            message: 'Seleccione Fecha Hasta.'
                        },*/
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
		$(window).off('resize');
	})
	
	function cargarFormulario() {
    	var realizarBusqueda = '${beanParametrosConsultaReq.realizarBusqueda}';
    	
    	if(realizarBusqueda == "1") {
			$('#selCodigoProceso').val('${beanParametrosConsultaReq.codProceso}');
			$('#txtNumeroEscrito').val('${beanParametrosConsultaReq.numDoc}');
			$('#txtfechaDesde').val('${beanParametrosConsultaReq.fecDesde}');
			$('#txtfechaHasta').val('${beanParametrosConsultaReq.fecHasta}');
			$('#txtNumeroExpediente').val('${beanParametrosConsultaReq.numExp}');
			retorno = true;
			realizarBusqueda = true;
    		revalidarFormulario();
    	}
    }

	function inicializarProcesos() {
		makeInputMask( '#txtNumeroExpediente', "(9){1,17}", 17, '' );
		$('#txtfechaDesde').prop('readonly', true);
		$('#txtfechaHasta').prop('readonly', true);
		//procesos
		var $element = $('#selCodigoProceso');
		$.each(listadoProcesos, function(i, dato) {
			var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
			$element.append($option);
		});
	}

    function limpiar() {
		resetearFormularioConEsp();
		resetearFormularioAvan();
		$('#tblRequerimientos').dataTable().fnClearTable();
		$('#tblRequerimientos').dataTable().fnDraw();
		
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
	
		$( window ).off('resize');
		$("#btnLimpiar").blur();
	}
		
    function getPaginaTbl(idTabla) {
    	return $('#'+idTabla).DataTable().page.info().page;
    }
    		
    function setPagintaTbl(idTabla, idxPagina) {
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
				{data : "numDoc", sClass : 'left alinearCentrado',
					render:function(data,row,full) {
						return '<a href="#" onclick="descargarEscrito(\''+data+'\')">'+data+'</a>';
					}
				 },
				{data : "fecDoc", sClass: 'left alinearCentrado'},
			 	{data : "numExpedienteVirtual", sClass: 'left alinearCentrado'}
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
					$('#tabla_paginate').addClass('hiddenDiv');
				} else {
					$('#tabla_paginate').removeClass('hiddenDiv');
				}
			}
		});
	}
	
	//mostrar Pagina de Error
	function mostrarPagError() {
		var formURL = '${pageContext.request.contextPath}/error.htm?action=cargarPaginaError';
		document.forms.frmFiltroBusquedaEspecifica.action = formURL;
		document.forms.frmFiltroBusquedaEspecifica.method = "post";
		document.forms.frmFiltroBusquedaEspecifica.submit();
	}

	function mostrarDlgInfo(titulo, msj) {
		botones = [];
		
		var aceptar = $("<input/>").attr({
			value : "Aceptar",
			type : "button",
			"class" : "btn dlgButton btn-primary",
			"data-dismiss" : "modal",
			onclick : "$('#modalDlg').modal('hide')"
		});

		botones.push(aceptar);
		crearDlg(titulo, msj, botones);
	}

	function crearDlg(titulo, msj, btns) {
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
		var codProceso = $('#selCodigoProceso').val();
		var numEscrito = $('#txtNumeroEscrito').val();
		var fecDesde = $('#txtfechaDesde').val();
		var fecHasta = $('#txtfechaHasta').val();
		var numExpediente = $('#txtNumeroExpediente').val();
		
		$('#frmFiltroBusquedaEspecifica').submit();
		if(codProceso != "") {
			if (numEscrito != "" || (fecDesde != "" && fecHasta != "") || numExpediente != "") {
				$('#frmFiltroBusquedaAvanzada').submit();
			} else {
				$('#tblRequerimientos').dataTable().fnClearTable();
				$('#tblRequerimientos').dataTable().fnDraw();
			}
		}
	}
	
	function resetearFormularioConEsp() {
		$('#frmFiltroBusquedaEspecifica').bootstrapValidator('resetForm', true);
	}
	
	function resetearFormularioAvan() {
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('resetForm', true);
	}
	
	function buscar() {
		listaRequerimientos = [];
		
		$.ajax({
			url : '${pageContext.request.contextPath}/otrosEscritosElecronicos.htm?action=cargarListadoEscritos',
			type : 'POST',
			async : true,
			dataType : "json",
			data : $('#frmFiltroBusquedaEspecifica').serialize() + '&' + $('#frmFiltroBusquedaAvanzada').serialize(),
			// contentType : "application/json",
			mimeType : "application/json",
			//timeout : 5000,
			success : function(response) {
				var msjError = response.msjError;
				if (msjError != "" && msjError != undefined) {
					$('#tblRequerimientos').dataTable().fnClearTable();
					$('#tblRequerimientos').dataTable().fnDraw();
					mostrarDlgInfo(titulos.tituloDefecto, msjError);
					return;
				}
	
				listaRequerimientos = response.listT6613docexpvirt;
				
				if (listaRequerimientos.length > 0) {
					$('#tblRequerimientos').dataTable().fnClearTable();
					$('#tblRequerimientos').dataTable().fnAddData(listaRequerimientos);
					$('#tblRequerimientos').dataTable().fnDraw();
					ruc = response.numeroRuc;
					rSocial = response.razonSocial;
					
					if(tblPage != "") {
						setPagintaTbl("tblRequerimientos", parseInt(tblPage));
						tblPage = "";
					}
				} else {
					$('#tblRequerimientos').dataTable().fnClearTable();
					$('#tblRequerimientos').dataTable().fnDraw();
					mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion03);
				}
				cargarDatosBusquedaSession();
			},
			error : function(response) {
				$('#tblRequerimientos').dataTable().fnClearTable();
				$('#tblRequerimientos').dataTable().fnDraw();
				mostrarPagError();
			}
		});
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
		if (fecha1x <= fecha2x) {
			return true;
		} else {
			return false;
		}
	}

	function validacionFechas() {
		var fechaDesde = document.getElementById("txtfechaDesde").value;
		var fechaHasta = document.getElementById("txtfechaHasta").value;
		var dd = fechaDesde.substring(0, 2);
		var mm = fechaDesde.substring(3, 5);
		var yyyy = fechaDesde.substring(6, 10);
		var rango = parseInt(excepciones.rangoFecha);
		var fechaCompara = new Date(mm + "/" + dd + "/" + yyyy);
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
		var fechaLimite = diaLimiteStr + "/" + mesLimiteStr + "/"
				+ anhoLimiteStr;
		if (comparafecha(fechaHasta, fechaLimite)) {
			return true;
		} else {
			return false;
		}
	}

	Date.isLeapYear = function(year) {
		return (((year % 4 === 0) && (year % 100 !== 0)) || (year % 400 === 0));
	}

	Date.getDaysInMonth = function(year, month) {
		return [ 31, (Date.isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31,
				30, 31, 30, 31 ][month];
	}

	Date.prototype.isLeapYear = function() {
		return Date.isLeapYear(this.getFullYear());
	}

	Date.prototype.getDaysInMonth = function() {
		return Date.getDaysInMonth(this.getFullYear(), this.getMonth());
	}

	Date.prototype.addMonths = function(value) {
		var n = this.getDate();
		this.setDate(1);
		this.setMonth(this.getMonth() + value);
		this.setDate(Math.min(n, this.getDaysInMonth()));
		return this;
	}
	
	function exportar() {
		var dataExp = $('#tblRequerimientos').dataTable().fnGetData();
		
		if (dataExp.length > 0) {
			var listaCadena = JSON.stringify(dataExp);
			var formURL = 'otrosEscritosElecronicos.htm?action=exportarExcelEscritosElectronicos';
			
			document.forms.formPrincipal.action = formURL;
			document.forms.formPrincipal.method = "POST";
			
			$('#campoHiddenExp').val(listaCadena);
			$('#hiddenNumRuc').val(ruc);
			$('#hiddenRazonSocial').val(rSocial);
			document.forms.formPrincipal.submit();
		} else {
			mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion02);
		}
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
    
 	function cargarDatosBusquedaSession() {		
		$.ajax({
			url : '${pageContext.request.contextPath}/otrosEscritosElecronicos.htm?action=cargarDatosBusquedaSession'+"&tblPage="+getPaginaTbl("tblRequerimientos"),
			type : 'POST',
			async : false,
			dataType : "json",
			data : $('#frmFiltroBusquedaEspecifica').serialize()+'&'+$('#frmFiltroBusquedaAvanzada').serialize(),
			contentType : "application/x-www-form-urlencoded",
			mimeType : "application/json",
			success : function(response) {

			},
			error : function(response) {
				mostrarPagError();
			}
		});
	}
			
	function removerDatosBusquedaSession() {
		$.ajax({
			url : '${pageContext.request.contextPath}/otrosEscritosElecronicos.htm?action=removerDatosBusquedaSession',
			type : 'POST',
			async : false,
			dataType : "json",
			data : '',
			// contentType : "application/json",
			mimeType : "application/json",
			success : function(response) {
				limpiar();
			},
			error : function(response) {
				limpiar();
			}
		});
	}
	
	function upperCase() {
		$('#txtNumeroEscrito').val($('#txtNumeroEscrito').val().toUpperCase());
		$('#txtNumeroExpediente').val($('#txtNumeroExpediente').val().toUpperCase());
	}
	
	function descargarEscrito(numEscrito) {
		$('#iPrint').attr('src', "${pageContext.request.contextPath}/otrosEscritosElecronicos.htm?action=descargarEscrito&numEscrito=" + numEscrito);
		$('#iPrint').attr( 'src', function ( i, val ) { return val; });
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
						<h3 class="panel-title" align="center">CONSULTA OTROS ESCRITOS ELECTR&Oacute;NICOS</h3>
					</div>
				</div>
				<div class="panel panel-primary">
					<iframe id="iPrint" name="iPrint" width="800" height="400" style="display:none"></iframe>
					<form class="form-horizontal" role="form"
						name="frmFiltroBusquedaEspecifica"
						id="frmFiltroBusquedaEspecifica" method="post">
						<fieldset class="scheduler-border" style="margin: 15px 15px !important">
							<legend class="scheduler-border"> Datos Generales </legend>
							<div class="form-group">
								<div class="col-md-2"><Strong>Proceso:</Strong></div>
								<div class="col-md-3">
									<select name="codProceso" id="selCodigoProceso" class="form-control tamanoMaximo"> 
										<option value="">Seleccione</option>
									</select>
								</div>
							</div>
						</fieldset>
					</form>
					<form class="form-horizontal" role="form"
						name="frmFiltroBusquedaAvanzada" id="frmFiltroBusquedaAvanzada"
						method="post">
						<fieldset class="scheduler-border"
							style="margin: 15px 15px !important">
							<legend class="scheduler-border"> Consulta Avanzada </legend>
							<div class="form-group">
								<div class="col-md-2">
									<Strong>N&uacute;mero de Escrito:</Strong></Strong>
								</div>
								<div class="col-md-3">
								<input name="numEscrito" id="txtNumeroEscrito" type="text" onblur="upperCase()" maxlength="17" 
									class="form-control tamanoMaximo" placeHolder="" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"><Strong>Fecha de Escrito desde:</Strong></div>
								<div class="col-md-3">
									<div class='input-group date tamanoMaximo' id="divFechaDesde">
										<input type='text' class="form-control tamanoMaximo" id='txtfechaDesde' name='fecDesde' placeHolder="dd/mm/aaaa" />
										<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
									</div>
								</div>
								<div class="col-md-1"><Strong>Hasta:</Strong></div>
								<div class="col-md-3">
									<div class='input-group date tamanoMaximo' id="divFechaHasta">
										<input type='text' class="form-control tamanoMaximo" id='txtfechaHasta' name='fecHasta' placeHolder="dd/mm/aaaa" />
										<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2">
									<Strong>N&uacute;mero de Expediente:</Strong></Strong>
								</div>
								<div class="col-md-3">
								<input name="numExp" id="txtNumeroExpediente" type="text" onblur="upperCase()" maxlength="17"  
									class="form-control tamanoMaximo" placeHolder="" />
								</div>
							</div>
						</fieldset>
						<div class="form-group" style="margin: 15px 15px !important">
							<div class="col-md-12" align="right" id="dvSecBotones01">
								<input type="button" class="btn btn-primary"
									intermediateChanges="false" data-dismiss="modal"
									value="Limpiar" id="btnLimpiar" onClick="removerDatosBusquedaSession()" /> 
								<input type="button" class="btn btn-primary" id="btnCancelar"
									intermediateChanges="false" onClick="revalidarFormulario()" value="Buscar" />
							</div>
						</div>
					</form>
				</div>
				<div class="panel panel-primary">
					<div class="panel-heading align="center">
						<h3 class="panel-title" align="center">LISTA DE ESCRITOS</h3>
					</div>
				</div>
				<div class="panel panel-primary">
					<fieldset class="scheduler-border" style="margin : 15px 15px !important">
						<legend class="scheduler-border"> Listado de Escritos </legend>
							<div class="form-group">
								<div class="col-md-12">
									<div class="tab-content">
									<div class="tab-pane fade in active">
									<div style="width: 100%; overflow-y: hidden; overflow-x:auto; border: 1px solid #337ab7;"> 
									<table id="tblRequerimientos" class="table display" style="width: 100%;">
										<thead>
											<tr>
												<th width="40%" class="text-center">N&deg; Escrito</th>
												<th width="20%" class="text-center">Fecha de Escrito</th>
												<th width="40%" class="text-center">N&uacute;mero de Expediente</th>
											</tr>
										</thead>
									</table>
									</div>
									</div>
									</div>
								</div>
							</div>
					</fieldset>
					<fieldset style="margin : 15px 15px !important">
						<div class="form-group">
							<div class="col-md-12" align="center" id="dvSecBotones01">	
								<input type="button" class="btn btn-primary" id="btnExportar" intermediateChanges="false" onClick="exportar()" value="Exportar a Excel" />
							</div>
						</div>
					</fieldset>
					<form id="formPrincipal" class="form-horizontal" role="form">
						<div class="col-md-5" align="right" id="dvSecBotones02">
							<input id="campoHiddenExp" type="hidden" name="listadoExpedientesCadena" />
							<input id="hiddenNumRuc" type="hidden" name="hiddenNumRuc"/>
							<input id="hiddenRazonSocial" type="hidden" name="hiddenRazonSocial"/>
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
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div id="dlgTitle">TITULO</div>
				</div>
				<div class="panel-body">
					<div id="dlgMsj" class="modal-body text-center">TEXTO</div>
					<div id="dlgBtns" class="text-center"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>