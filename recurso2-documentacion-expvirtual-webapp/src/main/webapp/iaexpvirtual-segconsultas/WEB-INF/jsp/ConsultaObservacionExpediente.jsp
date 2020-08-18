<!-- Inicio [jquispe 02/06/2016] Consulta de Observaciones.-->
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=EDGE" />
<meta name="viewport"
	content="initial-scale = 1.0, user-scalable = no,  width=device-width">
<title>CONSULTA DE OBSERVACIONES A EXPEDIENTES</title>
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
			
			.alinearCentrado {
				vertical-align: middle !important;
			}
			.panel-scroll {
			max-height: auto;
			min-height: 0px;
			overflow-y: auto;
			overflow-x: auto;
		}
		</style>
	
</head>
<body>
</br>
<div id="container" class="container" style="width: 95%">
			<div class="row">
				<div class="panel panel-primary">
					<div class="panel-heading align="center">
						<h3 class="panel-title" align="center">CONSULTA DE OBSERVACIONES A EXPEDIENTES</h3>
						<form id="frmPrincipal" class="form-horizontal" role="form">
						</form>
					</div>
				</div>	
				<div class="panel panel-primary">
					<form class="form-horizontal" role="form" name="frmFiltroBusquedaEspecificaEspecifica" id="frmFiltroBusquedaEspecifica" method="post">
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
								<legend class="scheduler-border"> Consulta Espec&iacute;fica </legend>
								<div class="form-group">
									<div class="col-md-1"><Strong>N&deg; de Expediente</Strong></Strong></div>
									<div class="col-md-3">
										<select name="codTipBusquedaExp" id="selTipoBusquedaExpediente" onchange="activarCampo()"class="form-control tamanoMaximo"> 
										<option value="">Seleccione</option>
										</select>
									</div>
									<div class="col-md-4">
										<input name="numExp" id="txtNumeroExpediente" type="text" class="form-control tamanoMaximo" placeHolder="" ></input>
									</div>
								</div>
						</fieldset>		
					</form>	
					<form class="form-horizontal" role="form" name="frmFiltroBusquedaAvanzada" id="frmFiltroBusquedaAvanzada" method="post">
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border" > Consulta Avanzada </legend>
							<div class="form-group">
								<div class="col-md-1"><Strong>Dependencia:</Strong></div>
								<div class="col-md-3">
									<select name="codDependencia" id="selDependencia" onchange="resetearFormularioConEsp(event)" class="form-control tamanoMaximo"> 
									</select>
								</div>
								<div class="col-md-1"><Strong>Proceso:</Strong></div>
								<div class="col-md-3">
									<select name="codProceso" id="selCodigoProceso" onchange="cargarTiposExpedientes()" class="form-control tamanoMaximo"> 
										<option value="">Seleccione</option>
									</select>
								</div>
								<div class="col-md-1"><Strong>Tipo de Expediente:</Strong></div>
								<div class="col-md-3">
									<select name="codTipexp" id="selCodigoTipoExpendiente" class="form-control tamanoMaximo"> 
										<option value="">Seleccione</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-1"><Strong>Fecha de:</Strong></div>
								<div class="col-md-3">
									<select name="codTipBusquedaFecha" id="selTipoBusquedaFecha" onchange="resetearFormularioConEsp(event)" class="form-control tamanoMaximo"> 
										<option value="">Seleccione</option>
									</select>
								</div>
								<div class="col-md-1"><Strong>Desde:</Strong></div>
								<div class="col-md-3">
									<div class='input-group date tamanoMaximo' id="divFechaDesde">
											<input type='text' class="form-control tamanoMaximo"  onkeypress="resetearFormularioConEsp(event)" id='txtfechaDesde' name='fecDesde' placeHolder="dd/mm/aaaa" maxlength="10" readonly="readonly" /> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
									</div>
								</div>
								<div class="col-md-1"><Strong>Hasta:</Strong></div>
								<div class="col-md-3">
									<div class='input-group date tamanoMaximo' id="divFechaHasta">
											<input type='text' class="form-control tamanoMaximo" onkeypress="resetearFormularioConEsp(event)" id='txtfechaHasta' name='fecHasta' placeHolder="dd/mm/aaaa" maxlength="10" readonly="readonly"/> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-1"><Strong>Ruc:</Strong></div>
								<div class="col-md-3">
									<input name="numRuc" id="txtRuc" type="text" class="form-control tamanoMaximo" onkeypress="resetearFormularioConEsp(event)" maxlength="11"></input>
								</div>
								<div class="col-md-1"><Strong>Raz&oacute;n Social:</Strong></div>
								<div class="col-md-7">
									<input name="razonSocial" id="txtRazonSocial" type="text" class="form-control tamanoMaximo" disabled></input>
								</div>
							</div>
						</fieldset>							
						<div style="margin : 15px 15px !important">
							<div class="row content-box">
								<div class="col-md-12 text-right">
									<input type="button" class="btn btn-primary" intermediateChanges="false" data-dismiss="modal" value="Limpiar" id="btnGuardar" onClick="limpiar()"></input>
									<input type="button" class="btn btn-primary" id="btnCancelar" intermediateChanges="false" onClick="revalidarFormulario()" value="Consultar"></input>
								</div>
							</div>
						</div>
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border"> Listado de Expedientes </legend>
							<div class="form-group">
							<div class="col-md-12">	
								<div class="tab-content">
								<div id="documentos" class="tab-pane fade in active">
								<div class="form-group" style="margin : 12px 5px !important" id="expediente">
								<div class="border-line panel-scroll">
								<table id="tblExpedientes" class="table display" cellspacing="0" style="width: 100%;">
									<thead>
										<tr>
											<th width="4%" class="text-center">N&deg;</th>
											<th width="8%" class="text-center">N&deg; de Expediente Origen</th>
											<th width="8%" class="text-center">N&deg; de Expediente Virtual</th>
											<th width="8%" class="text-center">RUC</th>											
											<th width="8%" class="text-center">Raz&oacute;n Social</th>											
											<th width="8%" class="text-center">Proceso</th>
											<th width="5%" class="text-center">Tipo de Expediente</th>
											<th width="5%" class="text-center">Estado Expediente</th>
											<th width="8%" class="text-center">Fecha de Registro del Expediente</th>
											<th width="8%" class="text-center">Fecha del Documento Origen</th>
											<!--Inicio JEFFIO  13/03/2017-->
											<th width="7%" class="text-center">Fecha de Notificaci&oacute;n</th>
											<th width="7%" class="text-center">Forma de Notificaci&oacute;n</th>
											<!--fin JEFFIO  13/03/2017-->
											<th width="8%" class="text-center">Origen</th>
											<th width="8%" class="text-center">Opci&oacute;n</th>
										</tr>
									</thead>
								</table>
								</div>
								</div>
								</div>
								</div>
							</div>
							</div>
							<!-- INICIO LLRB 16012020 -->
							<div class="form-group" id="expedienteFisca" style="display: none">
								<div class="border-line panel-scroll">		
										<table id="tblExpedientesFisca" class="table display" cellspacing="0" style="width: 100%;">
											<thead >
													<tr>
														<th width="4%" class="text-center">N&deg;</th>
														<th width="8%" class="text-center">N&deg; de Expediente</th>														
														<th width="8%" class="text-center">RUC</th>											
														<th width="8%" class="text-center">Raz&oacute;n Social</th>											
														<th width="8%" class="text-center">Proceso</th>
														<th width="5%" class="text-center">Tipo de Expediente</th>
														<th width="5%" class="text-center">Estado Expediente</th>
														<th width="8%" class="text-center">Fecha de Expediente</th>														
														<th width="8%" class="text-center">Origen</th>
														<th width="8%" class="text-center">Opci&oacute;n</th>														
												  </tr>
											</thead>
										<tbody></tbody>
									</table>	
								</div>
							</div>
							<!-- FIN LLRB 16012020 -->							
						</fieldset>
						<div class="form-group">
								<div class="col-md-12"  align="center">	
									<button type="button" class="btn btn-primary"  id='exportar' onclick="exportarExcel()">Exportar a Excel</button>
								</div>	
						</div>		
					</form>
					<form id="formPrincipal" class="form-horizontal" role="form">	
						<div class="col-md-5" align="right">	
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
	<div id="modalObservaciones" class="modal fade" role="dialog" >
		 <div class="modal-dialog" style="width: 1000px !important;">
			<div class="panel panel-info">
				<div class="panel-heading">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">&times;</span>
					</button>
					<div id="dlgTitleModalObservaciones">TITULO</div>
				</div>
				<div class="panel panel-primary">
					<form class="form-horizontal" role="form" name="frmRegistrarObservacion" id="frmRegistrarObservacion" method="post" data-toggle="validator">
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border"> Listado de Observaciones </legend>
							<div class="form-group">
								<div class="col-md-12">	
									<div class="tab-content">
									<div id="accionesFiscalizacion" class="tab-pane fade in active">
									<div class="border-line panel-scroll" id="divContenedorTabla">
									<table id="tblObservaciones"  class="table display" cellspacing="0" style="width: 100%;"><!--table table-striped table-bordered-->
										<thead>
											<tr class="active">
												<th width="5%" class="text-center">N&deg;</th>
												<th width="60%" class="text-left">Observaci&oacute;n </th>
												<th width="25%" class="text-left">Usuario Registro</th>
												<th width="10%" class="text-left">Fecha Registro</th>
											</tr>
										</thead>
									</table>
									</div>
									</div>
									</div>	
								</div>
								</div>								
						</fieldset>
						<div class="form-group">
								<div class="col-md-12" align="center">
										<button type="button" class="btn btn-primary" id='exportarObs' onclick="exportarObservaciones()">Exportar a Excel</button>
								</div>
						</div>
					</form>
					<form id="formExportarObservaciones" class="form-horizontal" role="form">	
						<div class="col-md-5" align="right">	
							<input id="campoHiddenExpObservaciones" type="hidden" name="listadoObservacionesCadena" />
							<input id="hiddenNumExpOrigen" type="hidden" name="hiddenNumExpOrigen" value='${datosExpedientes.numExpedienteOrigen}'/>
							<input id="hiddenNumExpVirtual" type="hidden" name="hiddenNumExpVirtual" value='${datosExpedientes.numExpedienteVirtual}'/>
							<input id="hiddenNumRuc" type="hidden" name="hiddenNumRuc" value='${datosExpedientes.numRuc}'/>
							<input id="hiddenRazonSocial" type="hidden" name="hiddenRazonSocial"value='${datosExpedientes.desRazonSocial}'/>
							<input id="hiddenTipoProceso" type="hidden" name="hiddenTipoProceso" value='${datosExpedientes.desProceso}'/>
							<input id="hiddenTipoExpediente" type="hidden" name="hiddenTipoExpediente" value='${datosExpedientes.desTipoExpediente}'/>
							<input id="hiddenDependencia" type="hidden" name="hiddenDependencia" value='${datosExpedientes.desDependencia}'/>
							<input id="hiddenEstExpediente" type="hidden" name="hiddenEstExpediente" value='${datosExpedientes.desEstado}'/>
							<input id="hiddenFechaGeneracion" type="hidden" name="hiddenFechaGeneracion" value='${fechaRegistro}'/>
							<!--Inico  JEFFIO 13/03/2017-->
							<input id="hiddenFechaNotificacion" type="hidden" name="hiddenEstExpediente" value='${datosExpedientes.strFecNotifOrigen}'/>
							<input id="hiddenFormaNotificacion" type="hidden" name="hiddenFechaGeneracion" value='${datosExpedientes.desForNotifOrigen}'/>
							<!--Fin  JEFFIO 13/03/2017-->
						</div>	
					</form>	
				</div>		
			</div>
		</div>
	</div>
	<script type="text/javascript">
 
	var listadoProcesos = ${listadoProcesos};
	var listadoDependencias = ${listadoDependencias};
	var listadoTipoFecha = ${listadoTipoFecha};
	var listadoNumeroTipoExpediente = ${listadoNumeroTipoExpediente};	
	var excepciones = ${excepciones};
	var titulos =  ${titulos};
	var numExpedienteConsultar="";
	var codDepUsuario = ${codDepUsuario};
	var numExpeVirt = "";
	var tituloObsModal="";
	flagExcepcion="";
	var retorno=false;
	var realizarBusqueda=false;
	//Inicio jquispe 16/06/2016
	var hoverBuscar=false;
	//Fin jquispe 16/06/2016
		
	$(function () {
		
		construirTablaExpediente( [] );
		construirTablaObservaciones( []);
		$.fn.datetimepicker.defaults.language = 'Es';
		$('#exportar').attr('disabled', true);

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
			if($('#txtfechaDesde').val() != ""){
				habilitarValidacionFecha(true);
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecDesde');
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecHasta');
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'codTipBusquedaFecha');
				resetearFormularioConEsp(event);
			}			
		});
		$("#divFechaHasta").on("dp.hide", function (e) {
			$('#divFechaDesde').data("DateTimePicker").setMaxDate(e.date);
			if($('#txtfechaHasta').val() != ""){
				habilitarValidacionFecha(true);
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecDesde');
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecHasta');
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'codTipBusquedaFecha');
				resetearFormularioConEsp(event);
			}
		});
		
		$( "#txtNumeroExpediente" ).blur(function() {
			retorno=true;
			$('#frmFiltroBusquedaEspecifica').bootstrapValidator('revalidateField', 'numExp');
		});
		
		$( "#txtRuc" ).blur(function() {
			retorno=true;
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'numRuc');
			//Inicio jquispe 16/06/2016
			if(hoverBuscar){				
				revalidarFormulario();				
			}
			//Fin jquispe 16/06/2016
		});
		
		//Inicio jquispe 16/06/2016
		$('#btnCancelar').mouseover(function(){
			hoverBuscar=true;
		});

		$('#btnCancelar').mouseout(function(){
			hoverBuscar=false;
		});
		//Fin jquispe 16/06/2016
		
		$('#txtRuc').on('paste', function () {
			limpiarBusquedaEspecifica();		
		});
		
		makeInputMask( '#txtRuc', "(9){1,11}", 11, '' );
						
		// Validaciones
		//Validaciones del Formulario Busqueda
		//Inicio [jquispe 12/07/2016] Comentado para el envio POST.
		//$.ajaxSetup({ scriptCharset: "UTF-8" , contentType: "application/json; charset=utf-8"});
		//Fin [jquispe 12/07/2016]
		jQuery.support.cors = true;
		$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
		
		$('#frmFiltroBusquedaEspecifica').bootstrapValidator({
			excluded: [':disabled'],
			fields: {
				numExp: {
					validators: {
						callback: {
                            message: 'Ingrese un n&uacute;mero de Expediente.',
                            callback: function (value, validator, $field) {
                            	if(retorno){
								var numExpediente = $('#txtNumeroExpediente').val();
								var tipoExpediente = $('#selTipoBusquedaExpediente').val();
								
								if(tipoExpediente!=""){
									if(numExpediente==""){
										$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateMessage', 'numExp', 'callback', 'Ingrese un N&uacute;mero de Expediente.');
										$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateOption', 'numExp', 'callback', 'Ingrese un N&uacute;mero de Expediente.');
										retorno=false;
										realizarBusqueda=false;
										return false;
									}
									
									if(tipoExpediente=="2"){
										if(numExpediente.length!=14){
											$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateMessage', 'numExp', 'callback', 'Ingrese un N&uacute;mero Expediente V&aacute;lido.');
											$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateOption', 'numExp', 'callback', 'Ingrese un N&uacute;mero Expediente V&aacute;lido.');
											retorno=false;
											realizarBusqueda=false;
											return false;
										}
									}
									if(tipoExpediente=="1"){										
										if(numExpediente.length > 17){											
											$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateMessage', 'numExp', 'callback', 'Ingrese un N&uacute;mero Expediente V&aacute;lido.');
											$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateOption', 'numExp', 'callback', 'Ingrese un N&uacute;mero Expediente V&aacute;lido.');
											retorno=false;
											realizarBusqueda=false;
											return false;
										}
									}									
								}				
								retorno=false;
								realizarBusqueda=true;
								return true;								
							}
							retorno=false;
							return true;
                        }
                    },
					regexp: {
						regexp: /^[0-9/]+$/,
						message: 'Debe ingresar solo valores num&eacute;ricos para el N&deg; de Expediente.'
					}
					
				}
			}
		}
		}).on('success.form.bv', function(e) {
            e.preventDefault();
			if(realizarBusqueda){
				consultarExpedientes();	
			}
    	}).on('error.validator.bv', function(e, data) {
            // $(e.target)    --> The field element
            // data.bv        --> The BootstrapValidator instance
            // data.field     --> The field name
            // data.element   --> The field element
            // data.validator --> The current validator name

            data.element
                .data('bv.messages')
                // Hide all the messages
                .find('.help-block[data-bv-for="' + data.field + '"]').hide()
                // Show only message associated with current validator
                .filter('[data-bv-validator="' + data.validator + '"]').show();
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
				codTipBusquedaFecha: {
                    validators: {
                        notEmpty: {
                            message: 'Seleccione un Tipo de Fecha.'
                        }
                    }
                },
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

                },
				numRuc: {
					validators: {
						callback: {
                            message: '',
                            callback: function (value, validator, $field) {
                            	if(retorno){
								$('#txtRazonSocial').val("");
								var numRuc = $('#txtRuc').val();								
								habilitarValidacionConsultaAvanzada(true);								
								if(numRuc!=""){
									if(numRuc.length < 11){
										$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateMessage', 'numRuc', 'callback', 'Debe ingresar 11 d&iacute;gitos para el RUC.');
										$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateOption', 'numRuc', 'callback', 'Debe ingresar 11 d&iacute;gitos para el RUC.');
										return false;
									}
									if(!valruc(numRuc)){
										$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateMessage', 'numRuc', 'callback', 'Ingrese un n&uacute;mero de RUC V&aacute;lido.');
										$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateOption', 'numRuc', 'callback', 'Ingrese un n&uacute;mero de RUC V&aacute;lido.');
										return false;
									}else{
										validarContribuyente();
										var razonSocial = $('#txtRazonSocial').val();
										if(razonSocial==""){
											$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateMessage', 'numRuc', 'callback', flagExcepcion);
											$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateOption', 'numRuc', 'callback', flagExcepcion);
											return false;
										}else{											
											habilitarValidacionConsultaAvanzada(false);										  
										}
										
									}
								}
								return true;
							}
							return true;
							}
						
                    },
					regexp: {
						regexp: /^[0-9/]+$/,
						message: 'Debe ingresar solo valores num&eacute;ricos para el RUC.'
					}
					
				}
			}
		}
		}).on('success.form.bv', function(e) {
            e.preventDefault();
            consultarExpedientes();          
    	}).on('error.validator.bv', function(e, data) {
            // $(e.target)    --> The field element
            // data.bv        --> The BootstrapValidator instance
            // data.field     --> The field name
            // data.element   --> The field element
            // data.validator --> The current validator name

            data.element
                .data('bv.messages')
                // Hide all the messages
                .find('.help-block[data-bv-for="' + data.field + '"]').hide()
                // Show only message associated with current validator
                .filter('[data-bv-validator="' + data.validator + '"]').show();
        });
	
		
		$('#txtNumeroExpediente').prop('disabled', true);
		$('#btnConsulta').prop('disabled', true);

		var $element = $('#selTipoBusquedaFecha');
		$.each(listadoTipoFecha, function(i, dato) {
			var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
			$element.append($option);
		});
		
		//expediente
		var $element = $('#selTipoBusquedaExpediente');
		$.each(listadoNumeroTipoExpediente, function(i, dato) {
			var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
			$element.append($option);
		});
		
		var $element = $('#selDependencia');
				
		$.each(listadoDependencias, function(i, dato) {		
			var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
			$element.append($option);
		});
		
		//[Inicio - jtejada 13/09/2016]
		if($.trim(codDepUsuario) == ''){
			$("#selDependencia").val($("#selDependencia option:first").val());
		}else{
			$('#selDependencia').val(codDepUsuario);
		}
		//[Fin - jtejada 13/09/2016]
				
		var $element = $('#selCodigoProceso');
		$.each(listadoProcesos, function(i, dato) {
			var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
			$element.append($option);
		});
		
		$( window ).off('resize');
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
						{data :	"numOrden", sClass: 'center alinearCentrado', "defaultContent":""},
						{data : "desObservacion", "defaultContent":""},
						{data : "nomUsuGeneraObs", sClass: 'center alinearCentrado',"defaultContent":""},
						{data : "fecGeneracionObs", sClass: 'center alinearCentrado',"defaultContent":""}
			],
				
			tableTools: {
		        "sSwfPath": "../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/TableTools/swf/copy_csv_xls.swf"
		    },	
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
				var a = $('#tblObservaciones').width()
				$("#tblObservaciones_wrapper").css("min-width", a);	
				$('#tblObservaciones_paginate').addClass('div100');
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
		
	function habilitarValidacionConsultaAvanzada(flagHabilitarValidacion){
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'codProceso', flagHabilitarValidacion);
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'codTipexp', flagHabilitarValidacion);
		
		if(!flagHabilitarValidacion){			
			if($('#selTipoBusquedaFecha').val() != "" || $('#txtfechaDesde').val() != "" || $('#txtfechaHasta').val() != ""){
				flagHabilitarValidacion = true;
			}
		}		
		
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'codTipBusquedaFecha', flagHabilitarValidacion);
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'fecDesde', flagHabilitarValidacion);
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'fecHasta', flagHabilitarValidacion);
	}
	
	function habilitarValidacionFecha(flagHabilitarValidacion){
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'codTipBusquedaFecha', flagHabilitarValidacion);
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'fecDesde', flagHabilitarValidacion);
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'fecHasta', flagHabilitarValidacion);
	}
	
	 function limpiar(){
		resetearFormularioConEsp(event);
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('resetForm', true);		
		habilitarValidacionConsultaAvanzada(true);		
		limpiarBusquedaEspecifica();
		$('#selNumeroTipoExpediente').val("");		
		$('#selCodigoProceso').val("");
		$('#selCodigoTipoExpendiente').val("");
		$('#selCodigoTipoExpendiente').append($('<option>', {
			value: '',
			text: 'Seleccione'
		}));
		$('#txtfechaDesde').val("");
		$('#txtfechaHasta').val("");
		$('#txtRazonSocial').val("");
		$("#txtfechaDesde").attr('disabled', false);
		$("#txtfechaHasta").attr('disabled', false);
		$('#tblExpedientes').dataTable().fnClearTable();	
		$('#exportar').attr('disabled', true);
		
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
	}
	 
	function limpiarBusquedaEspecifica(){
		$('#frmFiltroBusquedaEspecifica').bootstrapValidator('resetForm', true);
		$('#selTipoBusquedaExpediente').val("");
		$('#txtNumeroExpediente').val("");
		$("#txtNumeroExpediente").prop('disabled', true);
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
            url: '${pageContext.request.contextPath}/conObsControl.htm?action=cargarListaTiposExpediente',
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
	function construirTablaExpediente(dataGrilla) {
    	var table = $('#tblExpedientes').DataTable({
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
			iDisplayLength		:	5,//jquispe 05/07/2016 actualizado a 5
			//responsive			:	true,
			bLengthChange		: 	false,
			"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
				
				  $(iCol).attr('colClasses','tableOddCol');
				
			 },
            fnDrawCallback: function(oSettings) {
            	var a = $('#tblExpedientes').width()
				$("#tblExpedientes_wrapper").css("min-width", a);	
            	$('#tblExpedientes_paginate').addClass('div100');
                if (oSettings.fnRecordsTotal() == 0) {
                    $('#table_paginate').addClass('hiddenDiv');
                } else {
                    $('#table_paginate').removeClass('hiddenDiv');
                }
            },
			fnRowCallback: function (nRow, aData, iDisplayIndex) {
                    $(nRow).attr('id', aData[2]);
					$(nRow).attr('align', 'center');
					$(nRow).attr('rowClasses','tableOddRow');
                    return nRow;
            },
			language: {
                url: "/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
			},
			oLanguage : {
				//sInfo		:	"_START_ - _END_ de _TOTAL_ elementos",
				//sInfoEmpty	:	"_START_ - _END_ de _TOTAL_ elementos",
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
           // {"class": ""},
						{data :	"numOrden",sClass: 'centered alinearCentrado',"defaultContent":""},
						{data : "numExpedienteOrigen", sClass: 'centered alinearCentrado',"defaultContent":""},
						{data : "numExpedienteVirtual", sClass: 'centered alinearCentrado',"defaultContent":""},
						{data : "numRuc", sClass: 'centered alinearCentrado',"defaultContent":""},
						// Inicio [jquispe 10/06/2016]Nueva columna Razon Social.
						{data : "desRazonSocial",sClass:'centered alinearCentrado',"defaultContent":""},
						// Fin [jquispe 10/06/2016]
						{data : "desProceso", sClass: 'centered alinearCentrado',"defaultContent":""},
						{data :	"desTipoExpediente", sClass: 'centered alinearCentrado',"defaultContent":"" },
						{data :	"desEstado", sClass: 'centered alinearCentrado',"defaultContent":""},
						{data : "fecRegistro", sClass: 'centered alinearCentrado',"defaultContent":""},
						{data :	"fechaDocumentoOrigen", sClass: 'centered alinearCentrado',"defaultContent":"" },
						//inicio Jeffio 
						{data : "strFecNotifOrigen", sClass: 'centered alinearCentrado',"defaultContent":"" },
						{data : "desForNotifOrigen", sClass: 'centered alinearCentrado',"defaultContent":"" },
						//fin
						{data : "desOrigen", sClass: 'centered alinearCentrado',"defaultContent":""},
						{data :	"numOrden", render : function(data, row){
							 var aux = data;
							 return $('<a>')
							 	.css('text-decoration','underline')
								.attr({	id :aux})
				                .attr({	onclick :'obtenerObservaciones(this.id)'})
				                .text("Ver Obs")
				                .wrap('<div></div>')
				                .parent()
				                .html();
				                
				        }, sClass:'centered alinearCentrado'}
			]
        });
    	var table = $('#tblExpedientesFisca').DataTable({
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
			iDisplayLength		:	5,//jquispe 05/07/2016 actualizado a 5
			//responsive			:	true,
			bLengthChange		: 	false,
			"fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
				
				  $(iCol).attr('colClasses','tableOddCol');
				
			 },
            fnDrawCallback: function(oSettings) {
            	var a = $('#tblExpedientes').width()
				$("#tblExpedientes_wrapper").css("min-width", a);	
            	$('#tblExpedientes_paginate').addClass('div100');
                if (oSettings.fnRecordsTotal() == 0) {
                    $('#table_paginate').addClass('hiddenDiv');
                } else {
                    $('#table_paginate').removeClass('hiddenDiv');
                }
            },
			fnRowCallback: function (nRow, aData, iDisplayIndex) {
                    $(nRow).attr('id', aData[2]);
					$(nRow).attr('align', 'center');
					$(nRow).attr('rowClasses','tableOddRow');
                    return nRow;
            },
			language: {
                url: "/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
			},
			oLanguage : {
				//sInfo		:	"_START_ - _END_ de _TOTAL_ elementos",
				//sInfoEmpty	:	"_START_ - _END_ de _TOTAL_ elementos",
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
           // {"class": ""},
						{data :	"numOrden",sClass: 'centered alinearCentrado',"defaultContent":""},
						{data : "numExpedienteOrigen", sClass: 'centered alinearCentrado',"defaultContent":""},						
						{data : "numRuc", sClass: 'centered alinearCentrado',"defaultContent":""},						
						{data : "desRazonSocial",sClass:'centered alinearCentrado',"defaultContent":""},					
						{data : "desProceso", sClass: 'centered alinearCentrado',"defaultContent":""},
						{data :	"desTipoExpediente", sClass: 'centered alinearCentrado',"defaultContent":"" },
						{data :	"desEstado", sClass: 'centered alinearCentrado',"defaultContent":""},						
						{data :	"fechaDocumentoOrigen", sClass: 'centered alinearCentrado',"defaultContent":"" },	
						{data : "desOrigen", sClass: 'centered alinearCentrado',"defaultContent":""},
						{data :	"numOrden", render : function(data, row){
							 var aux = data;
							 return $('<a>')
							 	.css('text-decoration','underline')
								.attr({	id :aux})
				                .attr({	onclick :'obtenerObservaciones(this.id)'})
				                .text("Ver Obs")
				                .wrap('<div></div>')
				                .parent()
				                .html();
				                
				        }, sClass:'centered alinearCentrado'}
			]
        });
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

	//mostrar Pagina de Error
	function mostrarPagError() {
		
		var formURL = '${pageContext.request.contextPath}/error.htm?action=cargarPaginaError';
		document.forms.frmFiltroBusquedaEspecifica.action = formURL;
		document.forms.frmFiltroBusquedaEspecifica.method = "post";
		document.forms.frmFiltroBusquedaEspecifica.submit();
		
	}
	
	function mostrarDlgInfo(titulo, msj, fnAceptar){
		botones = [];
		
		var aceptar = $("<input/>").attr(
			{
				value: "Aceptar", 
				type: "button", 
				"class": "btn dlgButton btn-primary", 
				"data-dismiss" : "modal", 
				onclick : typeof fnAceptar == 'undefined' ? "$('#modalDlg').modal('hide')" : fnAceptar
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
		var tipoBusqueda = $('#selTipoBusquedaExpediente').val();
		//Inicio jquispe 16/06/2016
		if($('#txtRuc').val()==''){
			habilitarValidacionConsultaAvanzada(true);
		}else{
			habilitarValidacionConsultaAvanzada(false);
		}
		//Fin jquispe 16/06/2016
		if(tipoBusqueda!=""){
			$('#frmFiltroBusquedaEspecifica').submit();
		}else{
			$('#frmFiltroBusquedaAvanzada').submit();
		}
	}		
	
	function resetearFormularioConEsp(event){
		$('#frmFiltroBusquedaEspecifica').bootstrapValidator('resetForm', true);
		$('#selTipoBusquedaExpediente').val("");
		
		var current = event.target || event.srcElement;
		
		if(current.id != "selDependencia"){
			if($("#selCodigoProceso").val() == "" && current.id != "txtRuc") $('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'codTipexp');
		}		
		
		$('#txtNumeroExpediente').prop('disabled', true);
		
		if(current.id == "selTipoBusquedaFecha"){ 
			if($("#selTipoBusquedaFecha").val() == ""){
				habilitarValidacionFecha(false);
				$('#txtfechaDesde').val("");
				$('#txtfechaHasta').val("");
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'codTipexp',false);
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'codProceso',false);				  
			 }else{
				habilitarValidacionFecha(true);
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecDesde');
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecHasta');
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'codTipBusquedaFecha');
				if($("#selCodigoProceso").val() == "" && $('#txtRuc').val()==""){
					$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'codTipexp',true);
					$('#frmFiltroBusquedaAvanzada').bootstrapValidator('enableFieldValidators', 'codProceso',true);
					$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'codProceso');
					$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'codTipexp');
				}				
			 }
		}
	}
	
	function validarContribuyente(){

		var numRuc = $('#txtRuc').val();
		
		
		$.ajax({
			
			url : '${pageContext.request.contextPath}/conObsControl.htm?action=validarContribuyente',
			type : 'POST',
			async : false,
			dataType : "json",
			data : '&numRuc='+numRuc,
			//contentType : "application/json",//[jquispe 12/07/2016] Comentado para el envio POST.
			mimeType : "application/json",
			success : function(response) {
				
				var msjError = response.msjError;
				if(msjError!="" && msjError!=undefined){
					flagExcepcion=msjError;
				}
				
				if(response.razonSocial!="" && response.razonSocial!=undefined){
					$('#txtRazonSocial').val(response.razonSocial);
				}
				
			},
			error : function(response) {
				
				mostrarPagError();
			}
			
		});
	}
	
	function exportarExcel(){
		var dataExp = $('#tblExpedientes').dataTable().fnGetData();
		var dataExpF = $('#tblExpedientesFisca').dataTable().fnGetData();
		if(dataExp.length > 0){
			//AQ-FSW
			dataExp = caracterHtml(false,dataExp);
			var listaCadena = JSON.stringify(dataExp);
		    var formURL = 'conObsControl.htm?action=exportarExcelExpedientes';
			document.forms.formPrincipal.action = formURL;
			document.forms.formPrincipal.method = "POST";
			$('#campoHiddenExp').val('');
			$('#campoHiddenExp').val(listaCadena);
			//$('html').block({message: '<h1>Procesando</h1>'});
			document.forms.formPrincipal.submit();
			//$('html').unblock();
		}//INICIO LLRB 15012020
		else{
			dataExpF = caracterHtml(false,dataExpF);
			var listaCadena = JSON.stringify(dataExpF);
			var formURL = 'conObsControl.htm?action=exportarExcelExpedientesFisca';
		    document.forms.formPrincipal.action = formURL;
			document.forms.formPrincipal.method = "POST";
			$('#campoHiddenExp').val('');
			$('#campoHiddenExp').val(listaCadena);
			$('html').block({message: '<h1>Procesando</h1>'});
			document.forms.formPrincipal.submit();
			$('html').unblock();
		}
		
		//FIN LLRB 15012020
	}
	
	
	function consultarExpedientes(){
		
		listaExpedientes = [];
		 
		$.ajax({
			
			url : '${pageContext.request.contextPath}/conObsControl.htm?action=cargarListadoExpedientesVirtuales',
			type : 'POST',
			async : true,
			dataType : "json",
			data : $('#frmFiltroBusquedaEspecifica').serialize()+'&'+$('#frmFiltroBusquedaAvanzada').serialize(),
			//contentType : "application/json",//[jquispe 12/07/2016] Comentado para el envio POST.
			mimeType : "application/json",
			//timeout : 5000,
			success : function(response) {
				
				var msjError = response.msjError;
				if(msjError!="" && msjError!=undefined){
					$('#tblExpedientes').dataTable().fnClearTable();
					$('#tblExpedientes').dataTable().fnDraw();
					$('#tblExpedientesFisca').dataTable().fnClearTable();
					$('#tblExpedientesFisca').dataTable().fnDraw();
					mostrarDlgInfo(titulos.tituloDefecto, msjError);
					return;
				}
				
				listaExpedientes = response.listaExpedientesVirtuales;
				var codTipoExp = response.codTipoExp;	
				//INICIO LLRB 17012020
				if (listaExpedientes.length > 0) {
					if(codTipoExp== 430 ||codTipoExp== 431 ){							
						$('#expedienteFisca').show();
						$('#expediente').hide();
						listaExpedientes = caracterHtml(true,listaExpedientes);							
						$('#tblExpedientesFisca').dataTable().fnClearTable();
						$('#tblExpedientesFisca').dataTable().fnAddData(listaExpedientes);
						$('#tblExpedientesFisca').dataTable().fnDraw();	
						$('#exportar').attr('disabled', false);
						
						
					}else{
						$('#expediente').show();
						$('#expedienteFisca').hide();
						listaExpedientes = caracterHtml(true,listaExpedientes);		
						$('#tblExpedientes').dataTable().fnClearTable();
						$('#tblExpedientes').dataTable().fnAddData(listaExpedientes);
						$('#tblExpedientes').dataTable().fnDraw();
						$('#exportar').attr('disabled', false);
					
						
					}
				//FIN LLRB 16012020
				} else {
					
					$('#tblExpedientes').dataTable().fnClearTable();
					$('#tblExpedientes').dataTable().fnDraw();
					mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion05);
					$('#exportar').attr('disabled', true);
					
				}
			
			},
			error : function(response) {				
					$('#tblExpedientes').dataTable().fnClearTable();
					$('#tblExpedientes').dataTable().fnDraw();
				mostrarPagError();
				
			}
			
		});
	}
	
	function activarCampo(){
		var codProceso = $('#selTipoBusquedaExpediente').val();
		if(codProceso==""){
			$('#frmFiltroBusquedaEspecifica').bootstrapValidator('resetForm', true);
			$('#txtNumeroExpediente').prop('disabled', true);
		}else if(codProceso=="1"){
			$('#txtNumeroExpediente').prop('disabled', false);
			$('#txtNumeroExpediente').val("");
			$("#txtNumeroExpediente").attr('maxlength', 17);
			$('#frmFiltroBusquedaEspecifica').bootstrapValidator('revalidateField', 'numExp');
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('resetForm', true);			
			habilitarValidacionConsultaAvanzada(true);			
			$('#txtNumeroExpediente').focus();
		}else if(codProceso=="2"){
			$('#txtNumeroExpediente').prop('disabled', false);
			$('#txtNumeroExpediente').val("");
			$("#txtNumeroExpediente").attr('maxlength', 14);			
			$('#frmFiltroBusquedaEspecifica').bootstrapValidator('revalidateField', 'numExp');
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('resetForm', true);			
			habilitarValidacionConsultaAvanzada(true);			
			$('#txtNumeroExpediente').focus();
		}
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

function mostrarPagError() {
	
	var formURL = '${pageContext.request.contextPath}/error.htm?action=cargarPaginaError';
	document.forms.frmFiltroBusquedaEspecifica.action = formURL;
	document.forms.frmFiltroBusquedaEspecifica.method = "post";
	document.forms.frmFiltroBusquedaEspecifica.submit();
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

function exportarObservaciones(){
	var dataExpO = $('#tblObservaciones').dataTable().fnGetData();
	var dataExpF = $('#tblExpedientes').dataTable().fnGetData();
	
	if(dataExpO.length > 0){
			if(dataExpF.length > 0){
			var listaCadena = JSON.stringify(dataExpO);
			var formURL = 'conObsControl.htm?action=exportarExcelObservaciones';
			document.forms.formExportarObservaciones.action = formURL;
			document.forms.formExportarObservaciones.method = "POST";
			$('#campoHiddenExpObservaciones').val(listaCadena);
			/*setear campos hdn fin*/		
			//$('html').block({message: '<h1>Procesando</h1>'});
			document.forms.formExportarObservaciones.submit();
			//$('html').unblock();
	   }else{
		   var listaCadena = JSON.stringify(dataExpO);
			var formURL = 'conObsControl.htm?action=exportarExcelObservacionesFisca';
			document.forms.formExportarObservaciones.action = formURL;
			document.forms.formExportarObservaciones.method = "POST";
			$('#campoHiddenExpObservaciones').val(listaCadena);
			/*setear campos hdn fin*/		
			//$('html').block({message: '<h1>Procesando</h1>'});
			document.forms.formExportarObservaciones.submit();
			//$('html').unblock();  
	   }
	}
}

function obtenerObservaciones(id){
	var dataEnvio = new Object();		
	var numExpedienteVirtual;
	var dataJson = $('#tblExpedientes').DataTable().rows().data();	
	//INICIO LLRB 17012020
	var dataFiscaJson = $('#tblExpedientesFisca').DataTable().rows().data();	
	if (dataJson.length >0) {
		for (var i = 0; i < dataJson.length; i++) {
			if(dataJson[i].numOrden==id){
				
				numExpedienteVirtual = dataJson[i].numExpedienteVirtual;
							
			}
		}
	}else{
		for (var i = 0; i < dataFiscaJson.length; i++) {
			if(dataFiscaJson[i].numOrden==id){
				
				numExpedienteVirtual = dataFiscaJson[i].numExpedienteVirtual;
							
			}
		}	
	}
	//FIN LLRB 17012020	
	dataEnvio.aux = numExpedienteVirtual;
	$('#btnRegistrar').attr('disabled', true);
	$.ajax({
		url : '${pageContext.request.contextPath}/conObsControl.htm?action=cargarObservacionesPorExpVirtual',
		type : 'POST',
		async : true,
		dataType : "json",
		data : JSON.stringify(dataEnvio),
		contentType : "application/json",
		mimeType : "application/json",
		//timeout : 5000,
		success : function(response) {
			
			var lstObsExp = JSON.parse(response.lstObsExp);
			var datosExpedientes = JSON.parse(response.datosExpedientes);
			var fechaOrigenDoc = JSON.parse(response.fechaOrigenDoc);
			var fechaRegistro = JSON.parse(response.fechaRegistro);			
						
			$('#tblObservaciones').dataTable().fnClearTable();
			
			if (lstObsExp.length > 0) {				
				$('#exportarObs').attr('disabled', false);
				$('#tblObservaciones').dataTable().fnAddData(lstObsExp); //carga registros en grilla				
			} else {
				$('#exportarObs').attr('disabled', true);
			}
			$('#hiddenNumRuc').attr("value", datosExpedientes.numRuc);
			$('#hiddenRazonSocial').attr("value", datosExpedientes.desRazonSocial);	
			$('#hiddenNumExpOrigen').attr("value", datosExpedientes.numExpedienteOrigen);
			$('#hiddenNumExpVirtual').attr("value", datosExpedientes.numExpedienteVirtual);
			$('#hiddenEstExpediente').attr("value", datosExpedientes.desEstado);
			$('#hiddenTipoProceso').attr("value", datosExpedientes.desProceso);
			$('#hiddenTipoExpediente').attr("value", datosExpedientes.desTipoExpediente);
			$('#hiddenDependencia').attr("value", datosExpedientes.desDependencia);
			$('#hiddenFechaGeneracion').attr("value", fechaRegistro);
			//Inico  JEFFIO 13/03/2017
			$('#hiddenFechaNotificacion').attr("value", datosExpedientes.strFecNotifOrigen); 
			$('#hiddenFormaNotificacion').attr("value", datosExpedientes.desForNotifOrigen);
			//fin  JEFFIO 13/03/2017			
			numExpeVirt = datosExpedientes.numExpedienteVirtual;
			
			tituloObsModal = "Consulta de Observaciones - N&deg; Expediente Virtual " + datosExpedientes.numExpedienteVirtual;
			mostrarModalObservaciones(tituloObsModal);
			
		},
		error : function(response) {
			
			mostrarPagError();
			
		}
	
	});
}

function mostrarModalObservaciones(titulo){
	$('#dlgTitleModalObservaciones').html(titulo);
	$('#modalObservaciones').modal('show');
}

	//AQ-FSW
	function caracterHtml(valor1,data){
		if(valor1){
			$.each(data, function(i, dato) {
				dato.desRazonSocial = dato.desRazonSocial.replace("<", "&lt;").replace(">", "&gt;");
			});	
			return data;
		}else{
			$.each(data, function(i, dato) {
				dato.desRazonSocial = dato.desRazonSocial.replace("&lt;", "<").replace("&gt;", ">");
			});
			return data;
		}
	
	}

	/**/
 	</script>
	</body>	
</html>
<!-- Fin [jquispe 02/06/2016] -->
