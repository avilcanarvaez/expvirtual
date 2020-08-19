
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=EDGE" />
<meta name="viewport"
	content="initial-scale = 1.0, user-scalable = no,  width=device-width">
<title>INDICADORES Y REPORTES DE EXPEDIENTES</title>
<jsp:include page="ExpedienteCommonHeader.jsp" flush="true" />  
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
			.dataTables_wrapper .dataTables_paginate {
				float: initial !important;
				text-align: center !important;
				padding-top: 0.25em;
			}
			
			.dlgButton {
				border-color: gray;
				margin-right: 7px;
			}
		</style>
	
</head>
<body>
</br>
<div id="container" class="container" style="width: 95%">
			<div class="row">
				<div class="panel panel-primary">
					<div class="panel-heading align="center">
						<h3 class="panel-title" align="center">INDICADORES Y REPORTES DE EXPEDIENTES</h3>
						<form id="frmPrincipal" class="form-horizontal" role="form">
						</form>
					</div>
				</div>	
				<div class="panel panel-primary">
					<form class="form-horizontal" role="form" name="frmFiltroBusquedaAvanzada" id="frmFiltroBusquedaAvanzada" method="post">
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border" > Filtros </legend>
							<div class="form-group">
								<div class="col-md-3"><Strong>Proceso:</Strong></div>
								<div class="col-md-3">
									<select name="codProceso" id="selCodigoProceso" onchange="cargarTiposExpedientes()" class="form-control tamanoMaximo"> 
										<option value="">Seleccione</option>
									</select>
								</div>
								<div class="col-md-3"><Strong>Tipo de Expediente:</Strong></div>
								<div class="col-md-3">
									<select name="codTipexp" id="selCodigoTipoExpendiente" onchange="cargarTiposDocumento()"class="form-control tamanoMaximo"> 
										<option value="">Seleccione</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-3"><Strong>Origen del Expediente:</Strong></div>
								<div class="col-md-3">
									<select name="codOrigenExpediente" id="selCodOrigenExpediente" class="form-control tamanoMaximo"> 
										<option value="">Seleccione</option>
									</select>
								</div>
								<div class="col-md-3"><Strong>Tipo Documento:</Strong></div>
								<div class="col-md-3">
									<select name="codTipDocumento" id="selCodTipoDocumento" class="form-control tamanoMaximo"> 
										<option value="">Seleccione</option>
									</select>
								</div>
							</div>
							<div class="form-group">	
								<div class="col-md-3"><Strong>Desde:</Strong></div>
								<div class="col-md-3">
									<div class='input-group date tamanoMaximo' id="divFechaDesde">
											<input type='text' class="form-control tamanoMaximo"  onkeypress="resetearFormularioConEsp()" id='txtfechaDesde' name='fecDesde' placeHolder="dd/mm/aaaa" maxlength="10" readonly="readonly" /> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
									</div>
								</div>
								<div class="col-md-3"><Strong>Hasta:</Strong></div>
								<div class="col-md-3">
									<div class='input-group date tamanoMaximo' id="divFechaHasta">
											<input type='text' class="form-control tamanoMaximo" onkeypress="resetearFormularioConEsp()" id='txtfechaHasta' name='fecHasta' placeHolder="dd/mm/aaaa" maxlength="10" readonly="readonly"/> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
									</div>
								</div>
							</div>
						</fieldset>						
	
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border"> Listado de Reportes </legend>						
								<div class="col-md-12">	
										<div class="radio">
										  <label>
										    <input type="radio" name="opciones" id="opciones1" value="RPT_EXP_GEN_X_DEP" checked>
										    Reporte de Expedientes Generados por Dependencia
										  </label>
										</div>
										<div class="radio">
										  <label>
										    <input type="radio" name="opciones" id="opciones2" value="RPT_EXP_GEN_X_AMD" >
										    Reporte de Expedientes Generados por A&ntilde;o/Mes/Dia
										  </label>
										</div>
										<div class="radio">
										  <label>
										    <input type="radio" name="opciones" id="opciones3" value="RPT_EXP_GEN_X_DEP_CONS_X_USU_INT_Y_CONTRIB" >
										    <!-- Inicio [jquispe 27/05/2016] Modificado para mostrar informacion del Usuario Interno.-->
										    Reporte de Expedientes Generados por Dependencia Consultados por los Usuarios Internos y Contribuyente
										    <!-- Fin [jquispe 27/05/2016] -->
										  </label>
										</div>
										<div class="radio">
										  <label>
										    <input type="radio" name="opciones" id="opciones4" value="RPT_EXP_GEN_X_AMD_CONS_X_USU_INT_Y_CONTRIB" >
										    <!-- Inicio [jquispe 27/05/2016] Modificado para mostrar informacion del Usuario Interno.-->
										    Reporte de Expedientes Generados por A&ntilde;o/Mes/Dia Consultados por los Usuarios Internos y Contribuyente
										    <!-- Fin [jquispe 27/05/2016] -->
										  </label>
										</div>
										<div class="radio">
										  <label>
										    <input type="radio" name="opciones" id="opciones5" value="RPT_EXP_GEN_X_DEP_CONS_X_USU_INT_Y_CONTRIB_X_RUC" >
										    <!-- Inicio [jquispe 27/05/2016] Modificado para mostrar informacion del Usuario Interno.-->
										    Reporte de Expedientes Generados por Dependencia Consultados por los Usuarios Internos y Contribuyente por RUC
										    <!-- Fin [jquispe 27/05/2016] -->
										  </label>
										</div>
										<div class="radio">
										  <label>
										    <input type="radio" name="opciones" id="opciones6" value="RPT_EXP_GEN_X_DEP_EXP_COBR_ACUM" >
										    <!-- Inicio [jquispe 27/05/2016] Modificado deacuerdo al prototipo.-->
										    Reporte de Expedientes Generados por Dependencia - Expedientes de Cobranza Acumulados
										    <!-- Fin [jquispe 27/05/2016] -->
										  </label>
										</div>
										<div class="radio">
										  <label>
										    <input type="radio" name="opciones" id="opciones7" value="RPT_EXP_GEN_X_DEP_CONT_X_DCTO" >
										    Reporte de Expedientes Generados por Dependencia - Contenido por Documentos
										  </label>
									</div>	
									<div class="radio">
										  <label>
										    <input type="radio" name="opciones" id="opciones8" value="RPT_EXP_GEN_X_RESPONSABLES" >
										    Reporte de Expedientes Generados por Responsables
										  </label>
									</div>	
									<div class="radio">
										  <label>
										    <input type="radio" name="opciones" id="opciones9" value="RPT_DOC_GEN_X_EXP_DEP" >
										    Reporte de Documentos Registrados por Expediente y Dependencia
										  </label>
									</div>
									
									<div class="radio">
										  <label>
										    <input type="radio" name="opciones" id="opciones10" value="RPT_REPRE_IMPR_EXP_VIRT_X_DEP" />
										    Reporte de Representaciones Impresas Generadas por Dependencia: Usuario Interno y Contribuyente
										  </label>
									</div>
									<div class="radio">
										  <label>
										    <input type="radio" name="opciones" id="opciones11" value="RPT_REPRE_IMPR_EXP_VIRT_X_FEC" />
										    Reporte de Representaciones Impresas Generadas por A&ntilde;o/Mes/D&iacute;a: Usuario Interno y Contribuyente
										  </label>
									</div>
									<div class="radio">
										  <label>
										    <input type="radio" name="opciones" id="opciones12" value="RPT_REPRE_IMPR_EXP_VIRT_X_EXPDTE_Y_DEP" />
										    Reporte de Representaciones Impresas Generadas por Expediente: Usuario Interno y Contribuyente. Por Ruc (listado)
										  </label>
									</div>
									
							</div>	
							<div class="form-group">
								<div class="col-md-12"  align="center">	
									<button type="button" class="btn btn-primary" intermediateChanges="false" id="btnGenerar" onclick="revalidarFormulario()">Generar</button>
									<button type="button" class="btn btn-primary" intermediateChanges="false" id="btnLimpiar" onclick="limpiar()">Limpiar</button>
								</div>	
							</div>						
						</fieldset>		
					</form>
					<form id="formPrincipal" class="form-horizontal" role="form">	
						<div class="col-md-5" align="right" id="dvSecBotones01">	
							<input id="campoHiddenExp" type="hidden" name="listadoExpedientesCadena"/>
						</div>	
					</form>	
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
 
	var listadoProcesos = ${listadoProcesos};		
	var listadoNumeroTipoExpediente = ${listadoNumeroTipoExpediente};	
	var excepciones = ${excepciones};
	var titulos =  ${titulos};
	var retorno=false;
	var listadoOrigenExpediente=${listadoOrigenExpediente};
		
	$(function () {
		
		$.fn.datetimepicker.defaults.language = 'Es';

		$('#divFechaDesde').datetimepicker({
            format: 'DD/MM/YYYY',
			useCurrent: false,
			language: 'Es',
			autoClose: true,
			forceParse: true,
			pickTime: false
        });
		
		$('#divFechaDesde').on('dp.change dp.show', function(e) {
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecDesde');
			resetearFormularioConEsp();
		});
		$('#divFechaHasta').datetimepicker({
            format: 'DD/MM/YYYY',
			useCurrent: false,
			language: 'Es',
			autoClose: true,
			forceParse: true,
			pickTime: false
        });		
		
		$('#divFechaHasta').on('dp.change dp.show', function(e) {			
			resetearFormularioConEsp();
		});
		
		$("#divFechaDesde").on("dp.hide", function (e) {
            $('#divFechaHasta').data("DateTimePicker").setMinDate(e.date);
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecDesde');
		});
		$("#divFechaHasta").on("dp.hide", function (e) {
			$('#divFechaDesde').data("DateTimePicker").setMaxDate(e.date);
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecHasta');
		});		
		
			
		// Validaciones
		//Validaciones del Formulario Busqueda
		//Inicio [jquispe 12/07/2016] Comentado para el envio POST.
		//$.ajaxSetup({ scriptCharset: "UTF-8" , contentType: "application/json; charset=utf-8"});
		//Fin [jquispe 12/07/2016]
		jQuery.support.cors = true;
		$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
		
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
                }/*,
                codOrigenExpediente: {
                    validators: {
                        notEmpty: {
                            message: 'Seleccione Origen del Expediente.'
                        }
                    }
                },     
                codTipDocumento: {
                    validators: {
                        notEmpty: {
                            message: 'Seleccione un Tipo de Documento.'
                        }
                    }
                }*/,
				fecDesde: {
                    validators: {
                        notEmpty: {
                            message: 'Ingrese Fecha Desde.'
                        },
						date: {
							format: 'DD/MM/YYYY',
							message: 'El formato de la fecha es incorrecto, solo formato: dd/mm/aaaa.'
						},
						callback: {
							message: excepciones.excepcion02,
							callback:   function (value, validator, $field) {
								var fechaDesde = document.getElementById("txtfechaDesde").value;
								var fechaHasta = document.getElementById("txtfechaHasta").value;
								if(fechaDesde!="" && fechaHasta!=""){
									return validacionFechas();
								}
								return true;
							}
						}
                    }
                },
				fecHasta: {
                    validators: {
                        notEmpty: {
                            message: 'Ingrese Fecha Hasta.'
                        },
						date: {
							format: 'DD/MM/YYYY',
							message: 'El formato de la fecha es incorrecto, solo formato: dd/mm/aaaa.'
						},
						callback: {
							message: excepciones.excepcion02,
							callback:   function (value, validator, $field) {
								var fechaDesde = document.getElementById("txtfechaDesde").value;
								var fechaHasta = document.getElementById("txtfechaHasta").value;
								if(fechaDesde!="" && fechaHasta!=""){
									return validacionFechas();
								}
								return true;
							}
						}
						
                    }

                }

		}
		}).on('success.form.bv', function(e) {
            e.preventDefault();
            generarReporte();          
    	});
	
		
		//expediente
		var $element = $('#selTipoBusquedaExpediente');
		$.each(listadoNumeroTipoExpediente, function(i, dato) {
			var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
			$element.append($option);
		});
				
		var $element = $('#selCodigoProceso');
		$.each(listadoProcesos, function(i, dato) {
			var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
			$element.append($option);
		});	
		var $element = $('#selCodOrigenExpediente');
		$.each(listadoOrigenExpediente, function(i, dato) {
			var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
			$element.append($option);
		});	
	})	

	
	 function limpiar(){
		location.reload();
		/*$('#frmFiltroBusquedaAvanzada')[0].reset();
		cargarTiposExpedientes();
		cargarTiposDocumento();*/
	}	

	function revalidarFormulario(){
	    $('#frmFiltroBusquedaAvanzada').submit();
	}
	
	
	
function cargarTiposExpedientes() {
	
	resetearFormularioConEsp();
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
            url: '${pageContext.request.contextPath}/conRepControl.htm?action=cargarListaTiposExpediente',
           	data: "&codProceso="+codProceso,
            cache: false,
            async: true,
            type: 'POST',
            //contentType : "application/json; charset=utf-8",//[jquispe 12/07/2016] Comentado para el envio POST.
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


function cargarTiposDocumento() {
	
	resetearFormularioConEsp();
	var codTipoExpediente = $('#selCodigoTipoExpendiente').val();
	
	$('#selCodTipoDocumento').empty();
	
	$('#selCodTipoDocumento').append($('<option>', {
		value: '',
		text: 'Seleccione'
	}));
	
	var dataEnvio = new Object();
	
	if (codTipoExpediente == "") {
		
		$('#selCodTipoDocumento').val("");
		
	} else {
		
		dataEnvio.codTipoExpediente = codTipoExpediente;
		
		$.ajax({
            url: '${pageContext.request.contextPath}/conRepControl.htm?action=cargarListaTiposDocumentos',
           	data: "&codTipoExpediente="+codTipoExpediente,
            cache: false,
            async: true,
            type: 'POST',
            //contentType : "application/json; charset=utf-8",//[jquispe 12/07/2016] Comentado para el envio POST.
            dataType: 'json',
            success: function(response) {
            	
            	var listadoTiposDocumentosDisponibles = response.listadoTiposDocumentosDisponibles;
				var $element = $('#selCodTipoDocumento');
            	
            	$.each(listadoTiposDocumentosDisponibles, function(i, dato) {
					
					var $option = $("<option/>").attr("value", dato.codTipoDocumento).text(dato.desTipoDocumentoCompuesto);
					$element.append($option);
					
				});
            	
            },
            error: function(err) {
            	document.write(err.responseText);
            }
        });
		
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
	
	function validarFormulario(){
		var fechaDesde = $('#txtfechaDesde').val();
		var fechaHasta = $('#txtfechaHasta').val();
		if(fechaDesde != "" || fechaHasta!= ""){
			
		}
	}

	
	function mostrarID(id){
		alert(id);
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
	
	function validarFormulario(){
		var fechaDesde = $('#txtfechaDesde').val();
		var fechaHasta = $('#txtfechaHasta').val();
		if(fechaDesde != "" || fechaHasta!= ""){
			
		}
	}
	//mostrar Pagina de Error
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
				
				$('#modalDlg').modal('show');
				
			}
			
	
// 	function revalidarFormulario(){
// 		$('#frmFiltroBusquedaAvanzada').submit();
		
// 	}		
	
	function resetearFormularioConEsp(){
		
	}
	
	
	
	/**/
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

function revalidarFormularioObservaciones(){
	$('#frmRegistrarObservacion').submit();
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

function generarReporte(){
	var upper = 100000000;
	var rndm = Math.floor(Math.random()*upper);
	$('#btnGenerar').prop('disabled', true);
	$('#btnLimpiar').prop('disabled', true);		
	$('html').block({message: '<h1>Procesando</h1>'});
	var url = '${pageContext.request.contextPath}/conRepControl.htm?action=generarReportesIndicador&rndm='+rndm+'&'+$('#frmFiltroBusquedaAvanzada').serialize();
	window.open(url,'_self');
	$('html').unblock();
	$('#btnGenerar').prop('disabled', false);
	$('#btnLimpiar').prop('disabled', false);
}	

	/**/
 	</script>
	</body>	
</html>

	 