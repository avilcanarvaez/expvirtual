
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
	var listadoTipoFecha = ${listadoTipoFecha};
	var excepciones = ${excepciones};
	var titulos = ${titulos};
	
	var numExpedienteConsultar = "";
	var numExpedOrigenConsultar = "";
	var numRequerimientoConsultar = "";
	var numRequerimOrigenConsultar ="";
	var numDocConsultar ="";
	var retorno=false;
	var realizaBusquda=false;
	var tblPage = "${beanParametrosConsultaReq.tblPage}";  //anadir
	var gNumDocBlur = true;
	var ruc="";
	var rSocial="";
	
	$(function() {

		$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
		construirTablaRequerimiento([]);
		var table = $('#tblRequerimientos').DataTable();
		$('#tblRequerimientos').on( 'page.dt', function () {
			var info = table.page.info();
			$('input').filter(':radio').prop('checked',false);
		} );
		inicializarProcesos();

		var table = $('#tblRequerimientos').DataTable();

		$('#tblRequerimientos tbody').on('mouseenter', 'tr', function() {
			$(this).addClass("selected");
			indexDocumentos = table.row(this).index();
		});	
		$('#tblRequerimientos tbody').on( 'mouseleave', 'tr', function () {
			 $(this).removeClass("selected");
			 //$('#tblRequerimientos').dataTable().fnDraw();
		} );

		$('#divFechaDesde').datetimepicker({
			format : 'DD/MM/YYYY',
			useCurrent : false,
			language : 'es',
			autoClose : true,
			forceParse : true,
			pickTime : false
		});

		$('#divFechaHasta').datetimepicker({
			format : 'DD/MM/YYYY',
			useCurrent : false,
			language: 'es',
			autoClose : true,
			forceParse : true,
			pickTime : false
		});

		$("#divFechaDesde").on(
				"dp.hide",
				function(e) {
					$('#divFechaHasta').data("DateTimePicker").setMinDate(
							e.date);
					$('#frmFiltroBusquedaAvanzada').bootstrapValidator(
							'revalidateField', 'fecDesde');
					$('#frmFiltroBusquedaAvanzada').bootstrapValidator(
							'revalidateField', 'fecHasta');
					resetearFormularioConEsp();
				});

		$("#divFechaHasta").on(
				"dp.hide",
				function(e) {
					$('#divFechaDesde').data("DateTimePicker").setMaxDate(
							e.date);
					$('#frmFiltroBusquedaAvanzada').bootstrapValidator(
							'revalidateField', 'fecDesde');
					$('#frmFiltroBusquedaAvanzada').bootstrapValidator(
							'revalidateField', 'fecHasta');
					resetearFormularioConEsp();
				});

		$('#btnLimpiar').on("mouseover", function(e) {
			gNumDocBlur = false;
		});
		$('#btnLimpiar').on("mouseout", function(e) {
			gNumDocBlur = true;
		});
		//HHC
		/* $( "#txtNumeroDocumento" ).on( "click", function() {
			if($("#selCodigoProceso").val()!=""){
				$("#btnLimpiar").click();
				$( "#txtNumeroDocumento" ).focus();
			}
		}); */
		
		
		
		
		$( "#txtNumeroDocumento" ).blur(function() {
			retorno=true;
			$('#frmFiltroBusquedaEspecifica').bootstrapValidator('revalidateField', 'numDoc');
			if(!gNumDocBlur){
				removerDatosBusquedaSession();
			}
		});
		// Validaciones
		//Validaciones del Formulario Busqueda
		$('#frmFiltroBusquedaEspecifica')
				.bootstrapValidator(
						{
							excluded : [ ':disabled' ],
							fields : {
								tipDoc: {
				                    validators: {
				                        notEmpty: {
				                            message: 'Seleccione un Tipo de Documento.'
				                        }
				                    }
				                },
								numDoc : {
									validators : {
										callback : {
											message : 'Ingrese N&uacute;mero .',
											callback : function(value,validator, $field) {
											if(retorno){
												var numDocumento = $('#txtNumeroDocumento').val();
												var codTipexp = $('#selTipDoc').val();
// 												if (numDocumento != "") {
													if (codTipexp==1){
														if (numDocumento.length== 0 || numDocumento.length!=14) {   //escrito Electronico
															$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateMessage','numDoc','callback','Ingrese N&uacute;mero  de Escrito V&aacute;lido.');
															$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateOption','numDoc','callback','Ingrese N&uacute;mero  de Escrito V&aacute;lido.');
															retorno = false;
															realizaBusquda=false;
															return false;
														}	
													}else if (codTipexp==2){  //Número de requerimiento
														if (numDocumento.length== 0 || numDocumento.length!=13) {
															$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateMessage','numDoc','callback','Ingrese N&uacute;mero de Requerimiento V&aacute;lido.');
															$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateOption','numDoc','callback','Ingrese N&uacute;mero de Requerimiento V&aacute;lido.');
															retorno = false;
															realizaBusquda=false;
															return false;
														}
													}else if (codTipexp==3){  //Número de Expediente
														if (numDocumento.length== 0 || numDocumento.length>17) {
															$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateMessage','numDoc','callback','Ingrese N&uacute;mero Expediente V&aacute;lido.');
															$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateOption','numDoc','callback','Ingrese N&uacute;mero Expediente V&aacute;lido.');
															retorno = false;
															realizaBusquda=false;
															return false;
														}
													}
// 												}
												retorno = false;
												realizaBusquda=true;
												return true;
											}
											retorno = false;
											realizaBusquda=false;
											return true;
											}
										}

									}
								}
							}
						}).on('success.form.bv', function(e) {
					e.preventDefault();
					if(realizaBusquda){
						buscar();
					}
					
				}).on('error.form.bv', function(e) {
		            $('#tblRequerimientos').dataTable().fnClearTable();
					$('#tblRequerimientos').dataTable().fnDraw();
				});

		$('#frmFiltroBusquedaAvanzada')
				.bootstrapValidator(
						{
							excluded : [ ':disabled' ],
							fields : {
								fecDesde : {
									validators : {
										notEmpty : {
											message : 'Seleccione Fecha de Presentaci&oacute;n Desde.'
										},
										date : {
											format : 'DD/MM/YYYY',
											message : 'Formato Fecha Incorrecto.'
										},
										callback : {
											message : '',
											callback : function(value,
													validator, $field) {
												var fechaDesde = document
														.getElementById("txtfechaDesde").value;
												var fechaHasta = document
														.getElementById("txtfechaHasta").value;
												if (fechaDesde != ""
														&& fechaHasta != "") {
													if (!validacionFechas()) {
														$(
																'#frmFiltroBusquedaAvanzada')
																.bootstrapValidator(
																		'updateMessage',
																		'fecDesde',
																		'callback',
																		excepciones.excepcion01);
														$(
																'#frmFiltroBusquedaAvanzada')
																.bootstrapValidator(
																		'updateOption',
																		'fecDesde',
																		'callback',
																		excepciones.excepcion01);
														return false;
													}
												}
												return true;
											}
										}
									}
								},
								fecHasta : {
									validators : {
										notEmpty : {
											message : 'Seleccione Fecha de Presentaci&oacute;n Hasta.'
										},
										date : {
											format : 'DD/MM/YYYY',
											message : 'Formato Fecha Incorrecto.'
										},
										callback : {
											message : '',
											callback : function(value,
													validator, $field) {
												var fechaDesde = document
														.getElementById("txtfechaDesde").value;
												var fechaHasta = document
														.getElementById("txtfechaHasta").value;
												if (fechaDesde != ""
														&& fechaHasta != "") {
													if (!validacionFechas()) {
														$(
																'#frmFiltroBusquedaAvanzada')
																.bootstrapValidator(
																		'updateMessage',
																		'fecHasta',
																		'callback',
																		excepciones.excepcion01);
														$(
																'#frmFiltroBusquedaAvanzada')
																.bootstrapValidator(
																		'updateOption',
																		'fecHasta',
																		'callback',
																		excepciones.excepcion01);

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
	
	function cargarTiposExpedientes() {
		$( "#txtNumeroDocumento" ).val("");
		$( "#txtNumeroDocumento" ).focus();
				
	}
	
	function activarCampo(){
		var codProceso = $('#selTipDoc').val();
		if(codProceso==""){
			$('#frmFiltroBusquedaEspecifica').bootstrapValidator('resetForm', true);
			$('#txtNumeroDocumento').prop('disabled', true);
		}else {
			$('#txtNumeroDocumento').prop('disabled', false);
			$('#txtNumeroDocumento').val("");
// 			$("#txtNumeroDocumento").attr('maxlength', 17);		
			$('#frmFiltroBusquedaEspecifica').bootstrapValidator('revalidateField', 'numDoc');
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('resetForm', true);
			$("#txtNumeroDocumento").focus();
			habilitarValidacionConsultaAvanzada(true);
		}
	}
	
	function habilitarValidacionConsultaAvanzada(flagHabilitarValidacion){
		
		if(!flagHabilitarValidacion){			
			if( $('#txtfechaDesde').val() != "" || $('#txtfechaHasta').val() != ""){
				flagHabilitarValidacion = true;
			}
		}
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'fecDesde', flagHabilitarValidacion);
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'fecHasta', flagHabilitarValidacion);
	}
	function cargarFormulario(){
    	
    	var realizarBusqueda = '${beanParametrosConsultaReq.realizarBusqueda}';
    	
    	if(realizarBusqueda=="1"){
			var numDoc = '${beanParametrosConsultaReq.numDoc}';
			if(numDoc!=""){
				$('#selTipDoc').val('${beanParametrosConsultaReq.tipDoc}');
				$('#txtNumeroDocumento').val(numDoc);
			}else{
				$('#txtfechaDesde').val('${beanParametrosConsultaReq.fecDesde}');
				$('#txtfechaHasta').val('${beanParametrosConsultaReq.fecHasta}');
			}
			retorno = true;
			realizarBusqueda = true;
    		revalidarFormulario();
    	}
    }

	function inicializarProcesos() {

// 		makeInputMask( '#txtNumeroDocumento', "(a|9|;){1,17}", 17, '' );
		$("#txtNumeroDocumento").bind('keypress', function(event) {
		  var regex = new RegExp(/^[A-Za-z0-9]+$/g);
		  var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
		  if (!regex.test(key)) {
		    event.preventDefault();
		    return false;
		 }
		});
		
		$('#btnConsulta').prop('disabled', true);
		$('#txtfechaDesde').prop('readonly', true);
		$('#txtfechaHasta').prop('readonly', true);
	}
	
	function getPaginaTbl(idTabla){
		return $('#'+idTabla).DataTable().page.info().page;
	}
			
	function setPagintaTbl(idTabla, idxPagina){
		$('#'+idTabla).DataTable().page(idxPagina).draw(false);
	}
	
	function limpiar() {
		resetearFormularioConEsp();
		$('#tblRequerimientos').dataTable().fnClearTable();
		$('#tblRequerimientos').dataTable().fnDraw();
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('resetForm', true);
// 		$('#txtNumeroDocumento').prop('disabled', false);
		$("#txtNumeroDocumento").prop('disabled', true);
		$('#selTipDoc').prop('selectedIndex',0);
		$( window ).off('resize');
		$("#btnLimpiar").blur();
		//location.reload();
	}

	function construirTablaRequerimiento(dataGrilla) {

		$('#tblRequerimientos')
				.DataTable(
						{
							"language" : {
								"url" : "/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
							},
							oLanguage : {
								sInfo : ' ',
								sInfoEmpty : ' ',
								sEmptyTable : ' ',
								oPaginate : {
									"sFirst" : "&#60;&#60;",
									"sLast" : "&#62;&#62;",
									"sNext" : "&#62;",
									"sPrevious" : "&#60;"
								}
							},
							"fnCreatedCell" : function(nTd, sData, oData, iRow,
									iCol) {

								$(iCol).attr('colClasses', 'tableOddCol');

							},
							fnRowCallback : function(nRow, aData, iDisplayIndex) {
								$(nRow).attr('name', aData[1]);
								$(nRow).attr('align', 'center');
								$(nRow).attr('rowClasses', 'tableOddRow');
								return nRow;
							},
							columns : [
						
							{
								data : "numDoc",
								sClass : 'left alinearCentrado',
								render:function(data,row,full){
									return '<a href="#"  onclick="guardarNumDocEscrito(\''+data+' \' )" >'+data+'</a>';
								}
							},
							{
								data : "fecDoc",
								sClass : 'left alinearCentrado'
							}, {
								data : "desProceso",
								sClass : 'left alinearCentrado'
							}, {
								data : "desTipoExpediente",
								sClass : 'left alinearCentrado'
							}, {
								data : "numExpedienteOrigen",
								sClass : 'left alinearCentrado'
							}, {
								data : "numRequerimOrigen",
								sClass : 'left alinearCentrado'
							}

							],
							data : dataGrilla,
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
						});
		
		
		if(tblPage != ""){
			setPagintaTbl("tblRequerimientos", parseInt(tblPage)); //anadir
			tblPage = "";
		}
	}
	
	function guardarNumDocEscrito(data) {

		var tabla = $('#tblRequerimientos').dataTable().fnGetData();						
		
			numExpedienteConsultar = tabla[indexDocumentos].numExpedienteVirtual;
			numExpedOrigenConsultar = tabla[indexDocumentos].numExpedienteOrigen;
			numRequerimientoConsultar = tabla[indexDocumentos].numRequerimiento;
			numDocConsultar = tabla[indexDocumentos].numDoc;
			numRequerimOrigenConsultar = tabla[indexDocumentos].numRequerimOrigen;
			cargarDatosBusquedaSession();
			var formURL = 'consultaDocEscritoIt.htm?action=consultaEscrito';
			var listaCadena = JSON.stringify(tabla);
			document.forms.formPrincipal.action = formURL;
			document.forms.formPrincipal.method = "POST";
			$('#campoHiddenExp').val('');
			$('#hidTxtNumExpVirt').val('');
			$('#hidTxtNumExpOri').val('');
			$('#hidTxtNumRequerimiento').val('');
			$('#hidTxtNumRequerimOrigen').val('');
			$('#hidTxtNumDoc').val('');
			$('#campoHiddenExp').val(listaCadena);
			$('#hidTxtNumExpVirt').val(numExpedienteConsultar);
			$('#hidTxtNumExpOri').val(numExpedOrigenConsultar);
			$('#hidTxtNumRequerimiento').val(numRequerimientoConsultar);
			$('#hidTxtNumRequerimOrigen').val(numRequerimOrigenConsultar);
			$('#hidTxtNumDoc').val(numDocConsultar);
			document.forms.formPrincipal.submit();
			$('html').unblock();
		
	}

	//mostrar Pâ¨©na de Error
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
		var numeroExpediente = $('#txtNumeroDocumento').val();
		var codTipexp = $('#selTipDoc').val();
		if (numeroExpediente != "" ||codTipexp != "") {
			$('#frmFiltroBusquedaEspecifica').submit();
		} else {
			$('#frmFiltroBusquedaAvanzada').submit();
		}
	}

	function resetearFormularioConEsp() {
		$('#frmFiltroBusquedaEspecifica').bootstrapValidator('resetForm', true);
		$('#selTipDoc').val("");
		
// 		var current = event.target || event.srcElement;
		
		
		$('#txtNumeroDocumento').prop('disabled', true);
	}
	function resetearFormularioAvan() {
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('resetForm', true);
	}
	function buscar() {
        $('#btnConsulta').prop('disabled', true);
		listaRequerimientos = [];
		
		$.ajax({
					url : '${pageContext.request.contextPath}/consultaDocEscritoIt.htm?action=cargarListadoEscritos',
					type : 'POST',
					async : true,
					dataType : "json",
					data : $('#frmFiltroBusquedaEspecifica').serialize() + '&'
							+ $('#frmFiltroBusquedaAvanzada').serialize(),
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
							$('#tblRequerimientos').dataTable().fnAddData(
									listaRequerimientos);
							$('#tblRequerimientos').dataTable().fnDraw();
							ruc=response.numeroRuc;
							rSocial=response.razonSocial;

						} else {

							$('#tblRequerimientos').dataTable().fnClearTable();
							$('#tblRequerimientos').dataTable().fnDraw();
							mostrarDlgInfo(titulos.tituloDefecto,
									excepciones.excepcion05);

						}

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
			//HHC 
			var fechaDesde = $('#txtfechaDesde').val();
			var fechaHasta = $('#txtfechaHasta').val();
			var formURL = 'consultaDocEscritoIt.htm?action=exportarExcelEscritosElectronicos';
			document.forms.formPrincipal.action = formURL;
			document.forms.formPrincipal.method = "POST";
			$('#campoHiddenExp').val('');
			$('#campoHiddenExp').val(listaCadena); 
			$('#hiddenTipoProceso').val('');
			$('#hiddenTipoExpediente').val('');
			$('#hiddenFechaDesde').val('');
			$('#hiddenFechaHasta').val('');
			
			$('#hiddenFechaDesde').val(fechaDesde);
			$('#hiddenFechaHasta').val(fechaHasta);	
			$('#hiddenNumRuc').val(ruc);
			$('#hiddenRazonSocial').val(rSocial);					
			//Fin [gangles 10/08/2016]
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
	
	function cargarDatosBusquedaSession(){
		
		$.ajax({
			
			url : '${pageContext.request.contextPath}/consultaDocEscritoIt.htm?action=cargarDatosBusquedaSession',
			type : 'POST',
			async : false,
			dataType : "json",
			data : $('#frmFiltroBusquedaEspecifica').serialize()+'&'+$('#frmFiltroBusquedaAvanzada').serialize()+"&tblPage="+getPaginaTbl("tblRequerimientos"),
			// contentType : "application/json",
			mimeType : "application/json",
			success : function(response) {
				
// 				consultarDetalleRequerimiento();
				
			},
			error : function(response) {
				
				mostrarPagError();
			}
			
		});
	}
	
	function removerDatosBusquedaSession(){
		
		//removemos los datos guardados en session
		$.ajax({
			
			url : '${pageContext.request.contextPath}/consultaDocEscritoIt.htm?action=removerDatosBusquedaSession',
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
		   var x=document.getElementById("txtNumeroDocumento").value
		   document.getElementById("txtNumeroDocumento").value=x.toUpperCase()
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
						<h3 class="panel-title" align="center">CONSULTA ESCRITOS ELECTR&Oacute;NICOS</h3>
					</div>
				</div>
				<div class="panel panel-primary">
					<form class="form-horizontal" role="form"
						name="frmFiltroBusquedaEspecifica"
						id="frmFiltroBusquedaEspecifica" method="post">
						<fieldset class="scheduler-border"
							style="margin: 15px 15px !important">
							<legend class="scheduler-border"> Consulta Espec&iacute;fica </legend>
							<div class="form-group">
								<div class="col-md-1">
									<Strong>Documento:</Strong></Strong>
								</div>
								<div class="col-md-2">
									<select name="tipDoc"  id="selTipDoc" onchange="activarCampo()"  class="form-control">
										<option value="" selected>Seleccione</option>
										<option value="1">Escrito Electr&oacute;nico</option>
										<option value="2">N&#176; de Requerimiento</option>
										<option value="3">N&#176; de Expediente</option>
									</select>
								</div>
								<div class="col-md-3">
									<input name="numDoc" id="txtNumeroDocumento" type="text" onblur="upperCase()"  maxlength="17"
										class="form-control tamanoMaximo" placeHolder="" onchange="resetearFormularioAvan()"></input>
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
								<div class="col-md-3">
									<Strong>Fecha de Presentaci&oacute;n Desde:</Strong>
								</div>
								<div class="col-md-3">
									<div class='input-group date tamanoMaximo' id="divFechaDesde">
										<input type='text' class="form-control tamanoMaximo" onkeypress="resetearFormularioConEsp()" id='txtfechaDesde' name='fecDesde' placeHolder="dd/mm/aaaa" /> 
										<span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
									</div>
								</div>
								<div class="col-md-1">
									<Strong>Hasta:</Strong>
								</div>
								<div class="col-md-3">
									<div class='input-group date tamanoMaximo' id="divFechaHasta">
										<input type='text' class="form-control tamanoMaximo"
											onkeypress="resetearFormularioConEsp()" id='txtfechaHasta'
											name='fecHasta' placeHolder="dd/mm/aaaa" /> <span
											class="input-group-addon"><i
											class="glyphicon glyphicon-calendar"></i></span>
									</div>
								</div>
							</div>
						</fieldset>
						<div class="form-group" style="margin: 15px 15px !important">
								<div class="col-md-12" align="right" id="dvSecBotones01">
									<input type="button" class="btn btn-primary"
										intermediateChanges="false" data-dismiss="modal"
										value="Limpiar" id="btnLimpiar" onClick="removerDatosBusquedaSession()"></input> 
										<input type="button" class="btn btn-primary" id="btnCancelar"
												intermediateChanges="false" onClick="revalidarFormulario()"
												value="Buscar">
										</input>
								</div>
							</div>
						<fieldset class="scheduler-border"
							style="margin: 15px 15px !important">
							<legend class="scheduler-border"> Listado de
								Escritos </legend>
							<div class="form-group">
								<div class="col-md-12">
									<div class="tab-content">
									<div id="accionesFiscalizacion" class="tab-pane fade in active">
									<div class="border-line panel-scroll">
									<table id="tblRequerimientos" class="table table-bordered" style="width: 100%;">
										<thead>
											<tr>
												<th width="10%" class="text-center">Escrito El&eacute;ctronico</th>
												<th width="10%" class="text-center">Fecha y hora de Presentaci&oacute;n del Escrito</th>
												<th width="10%" class="text-center">Proceso</th>
												<th width="15%" class="text-center">Tipo de Expediente</th>
												<th width="10%" class="text-center">N&#176; de Expediente</th>
												<th width="10%" class="text-center">Requerimiento</th>
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
									<input type="button" class="btn btn-primary" id="btnExportar"
										intermediateChanges="false" onClick="exportar()" value="Exportar a Excel">
									</input>	
								</div>	
							</div>
						</fieldset>	
					</form>
					<form id="formPrincipal" class="form-horizontal" role="form">
						<div class="col-md-5" align="right" id="dvSecBotones02">
							<input id="campoHiddenExp" type="hidden" name="listadoExpedientesCadena" />
							<input id="hidTxtNumExpVirt" type="hidden" name="hidTxtNumExpVirt" />
							<input id="hiddenTipoProceso" type="hidden" name="hiddenTipoProceso"/><!-- Inicio[gangles 11/08/2016] -->
							<input id="hiddenTipoExpediente" type="hidden" name="hiddenTipoExpediente"/>
							<input id="hiddenFechaDesde" type="hidden" name="hiddenFechaDesde"/>							
							<input id="hiddenFechaHasta" type="hidden" name="hiddenFechaHasta"/>
							<input id="hiddenNumRuc" type="hidden" name="hiddenNumRuc"/>
							<input id="hiddenRazonSocial" type="hidden" name="hiddenRazonSocial"/>	<!-- Fin[gangles 11/08/2016] -->
							
							<input id="hidTxtNumRequerimiento" type="hidden" name="hidTxtNumRequerimiento"/>
							<input id="hidTxtNumRequerimOrigen" type="hidden" name="hidTxtNumRequerimOrigen"/>
							<input id="hidTxtNumExpOri" type="hidden" name="hidTxtNumExpOri"/>
							<input id="hidTxtNumDoc" type="hidden" name="hidTxtNumDoc"/>
							
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