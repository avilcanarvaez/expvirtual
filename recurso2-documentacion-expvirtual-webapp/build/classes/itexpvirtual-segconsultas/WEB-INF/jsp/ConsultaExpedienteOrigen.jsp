		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name = "viewport" content = "initial-scale = 1.0, user-scalable = no,  width=device-width">
	
    <title>SUNAT - Consulta de Expedientes</title>

    <!-- Bootstrap -->
    <link href="/a/js/libs/bootstrap/3.3.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/media/css/jquery.dataTables.css" rel="stylesheet" type="text/css" >
    <link href="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Responsive/css/dataTables.responsive.css" rel="stylesheet" type="text/css" >

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="/a/js/libs/bootstrap/3.3.2/plugins/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="/a/js/libs/bootstrap/3.3.2/plugins/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
	<style type="text/css">
		/*
		* Desplazamiento para el contenido excedente
		*/
		html, body{
			overflow: auto;
			height: 100%;
    	}
    	
    	/*
		* BlockUI Ajax loading - Tamaño de Fuente
    	*/

    	.blockUI h1{
			font-size: 10px !important;
			font-weight: bold !important;
		}
		
    </style>
  </head>
  <body>
	<!-- div class="container"-->
	<div id="container" class="container" style="width: 97%">
	<!--div class="wrapper wrapper-content animated fadeIn"-->
	    <div class="row">	
	        <!-- div class="col-md-10  col-md-offset-1"-->      
	        <div class="col-xs-12">
				<!--formulario -->
				<div class="panel panel-primary">

					<div class="panel-heading">Consulta Expedientes</div>
						<form id="frmPrincipal" class="form-horizontal" role="form">
						</form>
						<div class="panel-body">

						<form class="form-horizontal" role="form" name="frmFiltroBusquedaEspecificaEspecifica" id="frmFiltroBusquedaEspecifica" method="post">	

							<div class="form-group">
								<div class="col-xs-6 col-sm-3 col-md-3 col-lg-2"><Strong>N&deg; de Expediente:</Strong></div>
								<div class="col-xs-6 col-sm-3 col-md-3 col-lg-4">
									<input type="text" class="form-control" name="numExp" id="txtNumeroExpediente" maxlength="17">
									<input id="numAcumulador" name="numAcumulador" type="hidden">
								</div>
							</div>

						</form>
						
						<form class="form-horizontal" role="form" name="frmFiltroBusquedaAvanzada" id="frmFiltroBusquedaAvanzada" method="post">		

							<div class="form-group">
								<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2"><Strong>Proceso:</Strong></div>
								<div class="col-xs-8 col-sm-3 col-md-9 col-lg-4">
									<select name="codProceso" id="selCodigoProceso" onchange="cargarTiposExpedientes()" class="form-control"> 
										<option value="">Seleccione</option>
									</select>
								</div>
								<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2"><Strong>Tipo:</Strong></div>
								<div class="col-xs-8 col-sm-3 col-md-3 col-lg-4">
									<select name="codTipexp" id="selCodigoTipoExpendiente" class="form-control"> 
										<option value="">Seleccione</option>
									</select>
								</div>
								<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2"><Strong>Estado:</Strong></div>
								<div class="col-xs-8 col-sm-3 col-md-3 col-lg-4">
									<select name="codEstado" id="selCodigoEstado" class="form-control"> 
										<option value="">Todos</option>
									</select>
								</div>		
							</div>

							<div class="form-group">
								<div class="col-xs-4 col-sm-3 col-md-3 col-lg-2"><Strong>Fecha de:</Strong></div>
								<div class="col-xs-8 col-sm-9 col-md-9 col-lg-4">
									<select name="codTipexp" id="selCodigoTipoExpendiente" class="form-control" disabled> 
										<option value="">Generaci&oacute;n del Expediente:</option>
									</select>
								</div>								
								<div class="col-xs-4 col-sm-3 col-md-3 col-lg-1"><Strong>Desde:</Strong></div>
								<div class="col-xs-8 col-sm-3 col-md-3 col-lg-2">
									<div class='input-group date tamanoMaximo' id="divFechaDesde">
											<input type='text' class="form-control "  onkeypress="resetearFormularioConEsp()" id='txtfechaDesde' name='fecDesde' placeHolder="dd/mm/aaaa" maxlength="10" readonly="readonly"/> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
									</div>
								</div>
								<div class="col-xs-4 col-sm-3 col-md-3 col-lg-1"><Strong>Hasta:</Strong></div>
								<div class="col-xs-8 col-sm-3 col-md-3 col-lg-2">
									<div class='input-group date tamanoMaximo' id="divFechaHasta">
											<input type='text' class="form-control " onkeypress="resetearFormularioConEsp()" id='txtfechaHasta' name='fecHasta' placeHolder="dd/mm/aaaa" maxlength="10" readonly="readonly"/> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
									</div>
								</div>
							</div>

							<div class="form-group">
								<div class="col-md-12 text-right">
									<input type="button" class="btn btn-danger" intermediateChanges="false" value="Limpiar" id="btnLimpiar" onClick="limpiarConsulta()"></input>
									<input type="button" class="btn btn-primary" id="btnCancelar" intermediateChanges="false" onClick="revalidarFormulario()" value="Consultar"></input>
								</div>
							</div>


							</form>
						

						</div>
					</div>
				
				<!--fin formulario -->


			    <div class="panel panel-primary">
					<div class="panel-heading">Resultado de la consulta</div>
					<div class="panel-body">
                    
                    <!-- <div class="table-responsive"> -->

					<div class="table-responsive">
					<!-- //PAS20191U210500144 Inicio 400104 RF04 PSALDARRIAGA -->
                    <!-- <table id="tblExpedientesFis" class="table table-striped table-hover dt-responsive display no-wrap" cellspacing="0" width="100%" style="display: none;" > -->
						<table id="tblExpedientesFis" class="table table-striped table-hover dt-responsive display no-wrap" cellspacing="0" width="100%"  style="display: none;">
					    <thead>
					        <tr>
					            <th class="text-center">N&deg;</th>
								<th class="text-center">Expediente</th>
                                <!--  <th class="text-center">Expediente Acumulador</th>  -->
                                <!--  <th class="text-center">Indicador de Acumulaci&oacute;n</th>  -->
								<th class="text-center">Proceso</th>
								<th class="text-center">Tipo</th>
								<th class="text-center">Estado</th>
								<th class="text-center">Fecha del expediente</th>
								<th class="text-center">Detalle</th>
					        </tr>
					    </thead>
						</table>
						<!-- //PAS20191U210500144 Fin 400104 RF04 PSALDARRIAGA -->
					</div>
					
                    <!-- <table id="tblExpedientes" class="table table-striped table-hover dt-responsive display no-wrap" cellspacing="0" width="100%" style="display: none;" > -->						
						<div class="table-responsive">
						<table id="tblExpedientes" class="table table-striped table-hover dt-responsive display no-wrap" cellspacing="0" width="100%">
					    <thead>
					        <tr>
					            <th class="text-center">N&deg;</th>
								<th class="text-center">Expediente</th>
								<th class="text-center">Expediente Acumulador</th>
								<th class="text-center">Indicador de Acumulaci&oacute;n</th>
								<th class="text-center">Proceso</th>
								<th class="text-center">Tipo</th>
								<th class="text-center">Estado</th>
								<th class="text-center">Fecha del expediente</th>
								<th class="text-center">Detalle</th>
					        </tr>
					    </thead>
						</table>
						</div>

						<div class="form-group">
								<div class="col-md-12" align="center" id="dvSecBotones01">	
									<button type="button" class="btn btn-primary" intermediateChanges="false" id="exportar" onclick="exportarExcel()">Exportar a Excel</button>
								</div>
						</div>
                        
                        <div class="form-group">
								<div class="col-md-12" align="center" id="dvSecBotones01">	
									<button type="button" class="btn btn-primary" intermediateChanges="false" id="exportarFis" onclick="exportarExcelFis()">Exportar a Excel</button>
								</div>
						</div>

						<form id="formPrincipal" class="form-horizontal" role="form">	
							<div class="col-md-1" align="right" id="dvSecBotones01">	
								<input id="campoHiddenExp" type="hidden" name="listadoExpedientesCadena"/>		
								<input id="hiddenNumExpVirtual" type="hidden" name="hiddenNumExpVirtual" value='${t6614ExpVirtualBean.numExpedienteVirtual}'/>
								<input id="hiddenNumRuc" type="hidden" name="hiddenNumRuc" value='${t6614ExpVirtualBean.numRuc}'/>					
							</div>	
						</form>	


			
				</div><!--fin panel-->
				
	         </div><!--fin col-->
	    </div><!--fin row-->

	</div><!--fin container--> 


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

	
	<!-- Modal 01 -->
	<div class="modal fade" id="divModal01" tabindex="-1" role="dialog" aria-labelledby="h4ModalLabel01">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Cerrar"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title" id="dlgTitleModalDocumentos">Datos del Expediente:</h4>
				</div>
				<div class="modal-body">
					<!-- modal Form -->
					<fieldset class="scheduler-border">
						<div class="form-group">
							<div class="col-xs-4 col-sm-3 col-md-3 col-lg-3"><Strong>RUC:</Strong></div>
							<div class="col-xs-8 col-sm-9 col-md-9 col-lg-9">
								<input name="txtRuc" id="txtRuc" type="text" class="form-control" disabled>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-12 col-sm-3 col-md-3 col-lg-3"><Strong>Raz&oacute;n Social:</Strong></div>
							<div class="col-xs-12 col-sm-9 col-md-9 col-lg-9">
								<input name="txtRazonSocial" id="txtRazonSocial" type="text"  class="form-control" disabled>
							</div>
						</div>
						<div class="form-group">
							<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4"><Strong>Proceso:</Strong></div>
							<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
								<input name="txtProceso" id="txtProceso" type="text" class="form-control"  disabled>
							</div>
						</div>

						<div class="form-group">
							<div class="col-xs-12 col-sm-4 col-md-4 col-lg-4"><Strong>Tipo Expediente:</Strong></div>
							<div class="col-xs-12 col-sm-8 col-md-8 col-lg-8">
								<input name="txtTipoExpediente" id="txtTipoExpediente" type="text"  class="form-control"  disabled>
							</div>
						</div>

						<div class="form-group">
							<div class="col-xs-4 col-sm-3 col-md-3 col-lg-3"><Strong>F.Registro:</Strong></div>
							<div class="col-xs-8 col-sm-3 col-md-3 col-lg-3">
								<input name="txtFechaRegExpediente" id="txtFechaRegExpediente" type="text"  class="form-control"  disabled>
							</div>
						</div>

						<div class="form-group">
							<div class="col-xs-4 col-sm-3 col-md-3 col-lg-3"><Strong>Estado:</Strong></div>
							<div class="col-xs-8 col-sm-3 col-md-3 col-lg-3">
								<input name="txtEstExpediente" id="txtEstExpediente" type="text"  class="form-control" disabled>
							</div>
						</div>

					</fieldset>
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4"  style="color:#ff0000" id="divErrorExtension"></div>
						<div class="col-md-4"></div>
					</div>
					
					<br>
					<h4> Listado de Documentos </h4>
					
					<!-- fin modal Form -->

					<div class="table-responsive">
						<div class="form-group"  id="DocumentoCompleto">
						<table id="tblDocumentos" class="table table-striped table-hover dt-responsive display nowrap" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>N&deg;</th>
									<th>Tipo</th>
									<th>N&uacute;mero</th>
									<th>Origen registro</th>
									  <!--<th>N&deg; requerimiento</th>-->
									<th>Fecha Registro</th>
									<th>Fecha Emisi&oacute;n</th>	
									<th>Fecha Notificaci&oacute;n</th>
									<th>Forma Notificaci&oacute;n</th>
									<th>N&deg; Expediente Origen</th>
									<!-- <th></th> -->
									<!--<th>N&deg; Expediente Acumulador</th>-->
									<!-- Inicio [lromero 04/12/2019] . -->
									<th>Tipo Doc Relacionado</th>
									<th>Num Doc Relacionado</th>
								    <!-- Fin [lromero 04/12/2019] . -->
									<th width="4%" class="text-center">Ver archivo</th>
									<th>Representaci&oacute;n <br> Impresa</th>
									<th> </th>	
									<th> </th>
									<th> </th>	
								</tr>
							</thead>
							<tbody></tbody>
							<tfoot></tfoot>
						</table>
					</div>
    <!-- Inicio [lromero 04/12/2019] . -->
						<div class="form-group"  id="DocumentoIncompleto">
						<table id="tblDocumentosFiltro" class="table table-striped table-hover dt-responsive display nowrap" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>N&deg;</th>
									<th>Tipo</th>
									<th>N&uacute;mero</th>
									<th>Origen registro</th>
									<th>Fecha Registro</th>
									<th>Fecha Emisi&oacute;n</th>	
									<th>Fecha Notificaci&oacute;n</th>
									<th>Forma Notificaci&oacute;n</th>
									<th>N&deg; Expediente Origen</th>
									<!-- <th></th> -->
									<!--<th>N&deg; Expediente Acumulador</th>-->
									
									<th width="4%" class="text-center">Ver archivo</th>
									<th>Representaci&oacute;n <br> Impresa</th>
									<th> </th>	
									<th> </th>
									<th> </th>	
								</tr>
							</thead>
							<tbody></tbody>
							<tfoot></tfoot>
						</table>
						</div>
		
					</div>
	
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>


	<button type="button" class="btn btn-primary" id = "exportarExcelDocumentos" onclick="exportarExcelDocumentos()"> Exportar a Excel</button>


	<form id="formPrincipalDocumentos" class="form-horizontal" role="form">	
		<div class="col-md-5" align="right" id="dvSecBotones01">	
			<input id="campoHiddenExpDoc" type="hidden" name="listadoDocumentosCadena"/>
			<input id="hiddenNumExpOrigen" type="hidden" name="hiddenNumExpOrigen" />
			<input id="hiddenNumExpVirtualDoc" type="hidden" name="hiddenNumExpVirtualDoc" />
			<input id="hiddenNumRuc" type="hidden" name="hiddenNumRuc"/>
			<input id="hiddenTipoProceso" type="hidden" name="hiddenTipoProceso"/>
			<input id="hiddenTipoExpediente" type="hidden" name="hiddenTipoExpediente"/>
			<input id="hiddenDependencia" type="hidden" name="hiddenDependencia" />
			<input id="hiddenEstExpediente" type="hidden" name="hiddenEstExpediente"/>
			<input id="hiddenFechaGeneracion" type="hidden" name="hiddenFechaGeneracion" />
			<input id="hiddenNumArchivo" type="hidden" name="hiddenNumArchivo"/>
			<input id="hiddenListaExp" type="hidden" name="hiddenListaExp"/>
		</div>	
	</form>	
				</div>
			</div>
		</div>
	</div>
	<!-- FIN [lromero 04/12/2019] . -->
	<!-- Inicio LLRB Modal Archivo -->
	<div class="modal fade" id="modalArchivos" tabindex="-1" role="dialog" aria-labelledby="h4ModalLabel01">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
				
					<button type="button" class="close" data-dismiss="modal" aria-label="Cerrar"><span aria-hidden="true">&times;</span></button>
					<div id="dlgTitleModalArchivos"></div>
					  <div class="table-responsive">
						<div class="form-group"  id="ArchivoModal">
						<table id="tableArchivoModal" class="table table-striped table-hover dt-responsive display nowrap" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>N&deg;</th>
									<th>N&uacute;mero de Archivo</th>
									<th>Nombre de Archivo</th>
									<th>Item</th>
									<th>Tama&ntilde;o de archivo</th>
									<th>Folios</th>	
									<th>Ver archivo</th>
									<th>Representaci&oacute;n <br> Impresa</th>
									<th> </th>	

								</tr>
							</thead>
							<tbody></tbody>
							<tfoot></tfoot>
						</table>
						</div>
					</div>
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4"  style="color:#ff0000" id="divErrorExtensionArchivo"></div>
						<div class="col-md-4"></div>
					</div>
				</div>
					
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>

				</div>
			</div>
		</div>
	</div>
  	<!-- Fin LLRB Modal Archivo -->
	
  
    <script src="/a/js/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="/a/js/libs/bootstrap/3.3.2/js/bootstrap.min.js"></script>
    <script src="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/media/js/jquery.dataTables.min.js" type="text/javascript" language="javascript"></script>    
    <script src="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Responsive/js/dataTables.responsive.js" type="text/javascript" language="javascript" ></script>


    <script src="/a/js/bootstrap/3.2.0/js/jquery.blockUI.js" type="text/javascript"></script>

	<script src="/a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/js/moment-with-locales.js" type="text/javascript"></script>
	<script src="/a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>

	<script src="/a/js/bootstrapvalidator/js/bootstrapValidator.min.js"></script> 


 	<!-- Visor de pdf -->
    <script src="/a/js/apps/workspace/ws.js"></script>
    <script>

    	var strModalExpediente = "";

	    var titulos = ${titulos};
		var excepciones = ${excepciones};
		
		var listadoProcesos = ${listadoProcesos};
		var listadoDependencias = ${listadoDependencias};
		var listadoTipoFecha = ${listadoTipoFecha};
		var listadoNumeroTipoExpediente = ${listadoNumeroTipoExpediente};
		var listadoEstadosExpVirtual = ${listadoEstadosExpVirtual};
		
		var excepciones = ${excepciones};
		var titulos =  ${titulos};
		var numExpedienteConsultar="";
		var nroExpedienteVirtual="";
		var retorno=false;
		var realizarBusqueda = false;
		
		var realizarCargaInicial = true;
		var noRealizaCargaInicial = !realizarCargaInicial;		
	

	    var isMobile = {
			Android: function() {
				return navigator.userAgent.match(/Android/i);
			},
			BlackBerry: function() {
				return navigator.userAgent.match(/BlackBerry/i);
			},
			iOS: function() {
				return navigator.userAgent.match(/iPhone|iPad|iPod/i);
			},
			Opera: function() {
				return navigator.userAgent.match(/Opera Mini/i);
			},
			Windows: function() {
				return navigator.userAgent.match(/IEMobile/i);
			},
			any: function() {
				return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows());
			}
		};
	
		if(isMobile.iOS() ){
			$(".modal").css({"width": window.screen.width});
			$(".container").css({"width": window.screen.width});
		} 
		
		if(isMobile.any() ){
			$("#exportar").hide();
            $("#exportarFis").hide();
			$("#exportarExcelDocumentos").hide();
		}
		

		$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);

    	$(document).ready(function() { 
    		$.ajaxSetup({ scriptCharset: "utf-8" , contentType: "application/json; charset=utf-8"});
    	    jQuery.support.cors = true;

    	   
    	    /*
    	    * CONFIGURACION INICIAL
    	    */
    	    construirTablaExpediente( [] );
			construirTablaDocumentos( [] );
		
			
            //PAS20191U210500144 Inicio 400104 RF04 PSALDARRIAGA
			//construirTablaExpedienteFis( [] );
			//PAS20191U210500144 Fin 400104 RF04 PSALDARRIAGA
			
			//PAS20191U210500291 INICIO LLRB
			construirTablaArchivo([]);
			//PAS20191U210500291 FIN LLRB
			

    	    $('#btnConsulta').prop('disabled', true);
    	    $('#exportar').attr('disabled', true);
            $('#exportarFis').hide();

    	    //CARGA DE DATA
    	    var $element = $('#selCodigoEstado');
		
			$.each(listadoEstadosExpVirtual, function(i, dato) {		
				var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
				$element.append($option);
			});
			
			var $element = $('#selCodigoProceso');
			$.each(listadoProcesos, function(i, dato) {
				var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
				$element.append($option);
			});	



    	    //DATETIMEPICKER
    	    $.fn.datetimepicker.defaults.language = 'Es';
    	    $('#divFechaDesde').datetimepicker({
				format: 'DD/MM/YYYY',
				useCurrent: false,
				language: 'Es',
				autoClose: true,
				forceParse: true,
				pickTime: false	
	        });
    	    $('#divFechaDesde').on("dp.hide", function(e) {		
			$('#divFechaHasta').data("DateTimePicker").setMinDate(e.date);
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecDesde');
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecHasta');		   
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

			$("#divFechaHasta").on("dp.hide", function (e) {	
			$('#divFechaDesde').data("DateTimePicker").setMaxDate(e.date);
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecDesde');
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecHasta');
			resetearFormularioConEsp();
			});
			//VALIDATOR
			$('#txtNumeroExpediente').on('paste', function () {
			  var element = this;
			  setTimeout(function () {
			    var text = $(element).val();	
			    $(element).val(text.substring(0, 17))	
			  }, 10);
			});
		
			$( "#txtNumeroExpediente" ).blur(function() {
				retorno=true;
				$('#frmFiltroBusquedaEspecifica').bootstrapValidator('revalidateField', 'numExp');
			});
			
			$( "#txtNumeroExpediente" ).keypress(function (e) {
				var keynum = window.event ? window.event.keyCode : e.which;
				
				if ((keynum == 8)) {
					
					return true;					
				}
				
				return /\d/.test(String.fromCharCode(keynum));	
			});



			// Validaciones
			//Validaciones del Formulario Busqueda
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
										if(numExpediente==""){										
											$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateMessage', 'numExp', 'callback', 'Ingrese un N&uacute;mero Expediente.');
											$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateOption', 'numExp', 'callback', 'Ingrese un N&uacute;mero Expediente.');
											retorno=false;
											realizarBusqueda=false;
											return false;										
										}
										
										if(numExpediente.length > 17){									
												$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateMessage', 'numExp', 'callback', 'Ingrese un N&uacute;mero Expediente V&aacute;lido.');
												$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateOption', 'numExp', 'callback', 'Ingrese un N&uacute;mero Expediente v&aacute;lido.');
												retorno=false;
												realizarBusqueda=false;
												return false;
										}
									retorno=false;
									realizarBusqueda = true;
									return true;
		                        }
	                			retorno = false;
								return true;
	                           }
	                        },
							regexp: {
								regexp: /^[0-9/]+$/,
								message: 'Debe ingresar solo valores num&eacutericos para el N&#176; de Expediente.'
							},						
						}
					}				
				}
			}).on('success.form.bv', function(e) {
	            e.preventDefault();
                var codProceso = $('#selCodigoProceso').val();
	            if(realizarBusqueda){				
	            	consultarExpedientes(noRealizaCargaInicial);				
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
                    //PAS20191U210500144 Inicio 400104 RF04 PSALDARRIAGA
	                codProceso: {
	                    validators: {
							callback: {						
								callback:   function (value, validator, $field) {
									var proceso = document.getElementById("selCodigoProceso").value;
 									
									if(proceso ==""){
										$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateMessage', 'codProceso', 'callback', 'Seleccione Proceso.');
										$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateOption', 'codProceso', 'callback', 'Seleccione Proceso.');									
										return false;
									} 
 																	
									return true;
								}
							}						
	                    }
	                },
	                codTipexp: {
	                    validators: {
							callback: {	
							
								callback:   function (value, validator, $field) {
									var tipProceso = document.getElementById("selCodigoProceso").value;
									var tipExp = document.getElementById("selCodigoTipoExpendiente").value;
									
									if(tipProceso !=""){
										if(tipExp == ""){
											
											$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateMessage', 'codTipexp', 'callback', 'Seleccione Tipo.');
											$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateOption', 'codTipexp', 'callback', 'Seleccione Tipo.');									
											return false;
											
										}
										
									} 
							
									return true;
								}
							}						
	                    }
	                },	                
	              //PAS20191U210500144 Fin 400104 RF04 PSALDARRIAGA                
                    
					fecDesde: {
	                    validators: {
							date: {
								format: 'DD/MM/YYYY',
								message: 'El formato de la fecha es incorrecto, solo formato: dd/mm/aaaa.'
							},
							callback: {
				
								message: '',
								callback:   function (value, validator, $field) {
									var fechaDesde = document.getElementById("txtfechaDesde").value;
									var fechaHasta = document.getElementById("txtfechaHasta").value;
							
									if(fechaDesde == "" && fechaHasta != ""){
										$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateMessage', 'fecDesde', 'callback', 'Ingrese Fecha Desde.');
										$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateOption', 'fecDesde', 'callback', 'Ingrese Fecha Desde.');								
									     return false;
									}else{
										if(fechaDesde != "" && fechaHasta != ""){
										  if(!validacionFechas()){
											 $('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateMessage', 'fecDesde', 'callback', excepciones.excepcion02);
										     $('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateOption', 'fecDesde', 'callback', excepciones.excepcion02);								
										     return false;
										  }

										}
										
									}								
									return true;							
								}
							}
	                    }
	                },
					fecHasta: {
	                    validators: {
							date: {
								format: 'DD/MM/YYYY',
								message: 'El formato de la fecha es incorrecto, solo formato: dd/mm/aaaa.'
							},
							callback: {	
								message: '',
							
								callback:   function (value, validator, $field) {
									var fechaDesde = document.getElementById("txtfechaDesde").value;
									var fechaHasta = document.getElementById("txtfechaHasta").value;
							
									if(fechaDesde !="" && fechaHasta ==""){
										$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateMessage', 'fecHasta', 'callback', 'Ingrese Fecha Hasta.');
										$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateOption', 'fecHasta', 'callback', 'Ingrese Fecha Hasta.');									
										return false;
									}else{
										if(fechaDesde != "" && fechaHasta != ""){
										  if(!validacionFechas()){
											 $('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateMessage', 'fecHasta', 'callback', excepciones.excepcion02);
										     $('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateOption', 'fecHasta', 'callback', excepciones.excepcion02);								
										     return false;
										  }
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
                //PAS20191U210500144 Inicio 400104 RF04 PSALDARRIAGA
	            var codProceso = $('#selCodigoProceso').val();
	            var codTipexp = $('#selCodigoTipoExpendiente').val();
	            
	            if(codProceso != ""){	            	            	
	            	if(codTipexp == "" && codProceso == "002"){
	            		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'codTipexp');		
	            	}else{
	           //PAS20191U210500144 Fin 400104 RF04 PSALDARRIAGA
                        consultarExpedientes(noRealizaCargaInicial);
                    }   
                }                    
	    	});

			//consultarExpedientes(realizarCargaInicial);

	   	});    	
    	//fin Document ready
    	
    	/*
    	* FUNCIONES 
    	*/


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

		function construirTablaExpediente(dataGrilla) {

			
			
			
// 			if(){
				
			
// 			var paginado ;
// 			var paginadoFis ;
			
			$('#tblExpedientesFis').dataTable( {
  				data: dataGrilla,
  				pageLength: 5,
		        responsive: true,
		        dom: '<"html5buttons"B>rtip',
		        "language": {
	                "url": "/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
	            },
		        oLanguage : {
					sInfo		:	' ',
					sInfoEmpty	:	' ',
					sEmptyTable	:	' '	,
					oPaginate : {
						"sFirst":    "&#60;&#60;",
				        "sLast":     "&#62;&#62;",
				        "sNext":     "&#62;",
				        "sPrevious": "&#60;"
					}

				},
// 				fnDrawCallback : function(oSettings) {
					
// 					if (oSettings.fnRecordsTotal() == 0) {
// 						alert("7");
// // 						paginadoFis = false;
						
// 						paging = false;
						 
						
						
// 			        }
// 					else {
// // 						paginadoFis = true;
// 						alert("8");
						
// 						paging = true;
						
						
// 					}
// 				},
// 				alert("mostrarFis"+paginadoFis);
// 				"paging" : paginadoFis,
		        "columns": [					
				{"data" : "numOrden"},
				{"data" : "numExpedienteOrigen"},
// 				{"data" : "numExpedienteAcumulador", sClass: 'centered alinearCentrado',"defaultContent":"", 
// 					render : function(data, type, row){								
// 						var link;
						
// 						if(data=="0") {
// 							return "";
// 						}else {							
// 							return $('<a>')
// 								.css('text-decoration','underline')
// 								.attr({	onclick :'consultarExpedientesAcumulados("'+row.numExpedienteAcumulador+'")'})
// 								.text(data)
// 								.wrap('<div></div>')
// 								.parent()
// 								.html();
// 						}											
// 				}},				
// 				{"data" : "desIndicadorAcumulado"},				
				{"data" : "desProceso"},
				{"data" : "desTipoExpediente" },
				{"data" : "desEstado"},
				//{"data" : "fechaDocumentoOrigen"},	
				{"data" : "fechaDocumentoOrigen",	
					"render": function (data) {
						var parts = data.toString().split(" ");
						var mes = parts[1];
						var dia = parts[2];
						var anio = parts[5];
						return dia + '/' + formatoMes(mes) + '/' + anio;
					}
				},
				
				{"data" : "numExpedienteVirtual",'sortable': false , 
					render : function(data, row){
						 var link1 = '<a  style="text-decoration:underline"  onclick="fnObtenerDocumentos('+data+')">Ver Documentos</a>';
						 var link2 = '<a style="text-decoration:underline" onclick="obtenerPdf('+data+')">Generar &Iacute;ndice</a>';
						 var table = '<table><tr><td nowrap="nowrap" style="border-right: 0px !important;">';
						 table += link1;
						 table += '</td></tr><tr><td style="border-right: 0px !important;">';
						 table += link2;
						 table += '</td></tr></table>';
						 return table;
					 
					}, sClass: 'centered alinearCentrado'}
				]
// 				,
				
// 				fnDrawCallback		:	function(oSettings) {

// 					if (oSettings.fnRecordsTotal() == 0) {
						
// 						$('#table_paginate').addClass('hiddenDiv');
// // 						$('#table_paginate').addClass('hiddenDiv');
// // 						$('#table_previous').addClass('hiddenDiv');
// // 						$('#table_next').addClass('hiddenDiv');
						
						
// 					}else {
						
// 						$('#table_paginate').removeClass('hiddenDiv');
// // 						$('#table_paginate').removeClass('hiddenDiv');
// // 						$('#table_previous').removeClass('hiddenDiv');
// // 						$('#table_next').removeClass('hiddenDiv');
						
// 					}
// 			    }
				
		    });
			
// 			}else{
				$('#tblExpedientes').dataTable( {
	  				data: dataGrilla,
	  				pageLength: 5,
			        responsive: true,
			        dom: '<"html5buttons"B>rtip',
			        "language": {
		                "url": "/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
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
// 					fnDrawCallback : function(oSettings) {

						
						
// 						if (oSettings.fnRecordsTotal() == 0) {
// 							alert("9");
// // 							paginado = false;
							
							
// 							paging = false;
							
							
// 				        }
// 						else {
// // 							paginado = true;
// 							alert("10");
							
// 							paging = true;
// 						}
// 					},
// 					alert("mostrar"+paginado);
// 					"paging" : paginado,
			        "columns": [					
					{"data" : "numOrden"},
					{"data" : "numExpedienteOrigen"},
					{"data" : "numExpedienteAcumulador", sClass: 'centered alinearCentrado',"defaultContent":"", 
						render : function(data, type, row){								
							var link;
							
							if(data=="0") {
								return "";
							}else {							
								return $('<a>')
									.css('text-decoration','underline')
									.attr({	onclick :'consultarExpedientesAcumulados("'+row.numExpedienteAcumulador+'")'})
									.text(data)
									.wrap('<div></div>')
									.parent()
									.html();
							}											
					}},				
					{"data" : "desIndicadorAcumulado"},				
					{"data" : "desProceso"},
					{"data" : "desTipoExpediente" },
					{"data" : "desEstado"},
					//{"data" : "fechaDocumentoOrigen"},	
					{"data" : "fechaDocumentoOrigen",	
						"render": function (data) {
							var parts = data.toString().split(" ");
							var mes = parts[1];
							var dia = parts[2];
							var anio = parts[5];
							return dia + '/' + formatoMes(mes) + '/' + anio;
						}
					},
					
					{"data" : "numExpedienteVirtual",'sortable': false , 
						render : function(data, row){
							 var link1 = '<a  style="text-decoration:underline"  onclick="fnObtenerDocumentos('+data+')">Ver Documentos</a>';
							 var link2 = '<a style="text-decoration:underline" onclick="obtenerPdf('+data+')">Generar &Iacute;ndice</a>';
							 var table = '<table><tr><td nowrap="nowrap" style="border-right: 0px !important;">';
							 table += link1;
							 table += '</td></tr><tr><td style="border-right: 0px !important;">';
							 table += link2;
							 table += '</td></tr></table>';
							 return table;
						 
						}, sClass: 'centered alinearCentrado'}
					]
// 					,
					
// 					fnDrawCallback		:	function(oSettings) {

// 						if (oSettings.fnRecordsTotal() == 0) {
							
// 							$('#table_paginate').addClass('hiddenDiv'); 
// 							//$('#table_previous').addClass('hiddenDiv');
// 							//$('#table_next').addClass('hiddenDiv');
							
							
// 				        }
// 						else {
							
// 							$('#table_paginate').removeClass('hiddenDiv');
// 							//$('#table_previous').removeClass('hiddenDiv');
// 							//$('#table_next').removeClass('hiddenDiv');
							
// 						}
// 				    }
										
			    }).hide();
				
			

// 			}
			
			
		
	
		}
		//PAS20191U210500144 Inicio 400104 RF04 PSALDARRIAGA
// 		function construirTablaExpedienteFis(dataGrilla) {

// 			$('#tblExpedientesFis').dataTable( {
//   				data: dataGrilla,
//   				pageLength: 5,
// 		        responsive: true,
// 		        dom: '<"html5buttons"B>rtip',
// 		        "language": {
// 	                "url": "/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
// 	            },
// 		        oLanguage : {
// 					sInfo		:	' ',
// 					sInfoEmpty	:	' ',
// 					sEmptyTable	:	' '	,
// 					oPaginate : {
// 						"sFirst":    "&#60;&#60;",
// 				        "sLast":     "&#62;&#62;",
// 				        "sNext":     "&#62;",
// 				        "sPrevious": "&#60;"
// 					}

// 				},
// 		        "columns": [					
// 				{"data" : "numOrden"},
// 				{"data" : "numExpedienteOrigen"},
// // 				{"data" : "numExpedienteAcumulador", sClass: 'centered alinearCentrado',"defaultContent":"", 
// // 					render : function(data, type, row){								
// // 						var link;
						
// // 						if(data=="0") {
// // 							return "";
// // 						}else {							
// // 							return $('<a>')
// // 								.css('text-decoration','underline')
// // 								.attr({	onclick :'consultarExpedientesAcumulados("'+row.numExpedienteAcumulador+'")'})
// // 								.text(data)
// // 								.wrap('<div></div>')
// // 								.parent()
// // 								.html();
// // 						}											
// // 				}},				
// // 				{"data" : "desIndicadorAcumulado"},				
// 				{"data" : "desProceso"},
// 				{"data" : "desTipoExpediente" },
// 				{"data" : "desEstado"},
// 				//{"data" : "fechaDocumentoOrigen"},	
// 				{"data" : "fechaDocumentoOrigen",	
// 					"render": function (data) {
// 						var parts = data.toString().split(" ");
// 						var mes = parts[1];
// 						var dia = parts[2];
// 						var anio = parts[5];
// 						return dia + '/' + formatoMes(mes) + '/' + anio;
// 					}
// 				},
				
// 				{"data" : "numExpedienteVirtual",'sortable': false , 
// 					render : function(data, row){
// 						 var link1 = '<a  style="text-decoration:underline"  onclick="fnObtenerDocumentos('+data+')">Ver Documentos</a>';
// 						 var link2 = '<a style="text-decoration:underline" onclick="obtenerPdf('+data+')">Generar &Iacute;ndice</a>';
// 						 var table = '<table><tr><td nowrap="nowrap" style="border-right: 0px !important;">';
// 						 table += link1;
// 						 table += '</td></tr><tr><td style="border-right: 0px !important;">';
// 						 table += link2;
// 						 table += '</td></tr></table>';
// 						 return table;
					 
// 					}, sClass: 'centered alinearCentrado'}
// 				]
// 				//,
				
// // 				fnDrawCallback		:	function(oSettings) {

// // 					if (oSettings.fnRecordsTotal() == 0) {
						
// // 						//$('#tblExpedientesFis_paginate').addClass('hiddenDiv');
// // 						$('#table_paginate').addClass('hiddenDiv');
// // 						$('#table_previous').addClass('hiddenDiv');
// // 						$('#table_next').addClass('hiddenDiv');
						
// // 					}else {
						
// // 						//$('#tblExpedientesFis_paginate').removeClass('hiddenDiv');
// // 						$('#table_paginate').removeClass('hiddenDiv');
// // 						$('#table_previous').removeClass('hiddenDiv');
// // 						$('#table_next').removeClass('hiddenDiv');
						
// // 					}
// // 			    }
				
// 		    });
	
// 		}
		//PAS20191U210500144 Fin 400104 RF04 PSALDARRIAGA

		
		function formatoMes(mes) {
			var text;
			switch(mes) {
				case "Jan": text = '01'; break;
				case "Feb": text = '02'; break;
				case "Mar": text = '03'; break;
				case "Apr": text = '04'; break;
				case "May": text = '05'; break;
				case "Jun": text = '06'; break;
				case "Jul": text = '07'; break;
				case "Aug": text = '08'; break;
				case "Sep": text = '09'; break;
				case "Oct": text = '10'; break;
				case "Nov": text = '11'; break;
				case "Dec": text = '12'; break;
				default: text = "00";
			}
			return text;
		}
		
		function consultarExpedientes(flagCargaInicial){
			listaExpedientes = [];
		
			var urlObtenerExpedienteVirtual = '${pageContext.request.contextPath}/consulta/obtenerExpedientesVirtuales';
            //PAS20191U210500144 Inicio 400104 RF04 PSALDARRIAGA
 			var codPro = $('#selCodigoProceso').val();
	        var codTipExp = $('#selCodigoTipoExpendiente').val();
	       //PAS20191U210500144 Fin 400104 RF04 PSALDARRIAGA

		
			if(flagCargaInicial){
				urlObtenerExpedienteVirtual += '?flagCargaInicial='+flagCargaInicial;
			}

			$.ajax({
				url : urlObtenerExpedienteVirtual,
				async : true,
				dataType : "json",
				data : $('#frmFiltroBusquedaEspecifica').serialize()+'&'+$('#frmFiltroBusquedaAvanzada').serialize(),
				mimeType : "application/json",
				success : function(response) {

					var msjError = response.msjError;
					if(msjError!="" && msjError!=undefined){
						//PAS20191U210500144 Inicio 400104 RF04 PSALDARRIAGA
						if(codPro == "002" && (codTipExp == "430" || codTipExp == "431") ){
							
							
							$('#tblExpedientesFis').dataTable().fnClearTable();
							$('#tblExpedientesFis').dataTable().fnDraw();
							$('#tblExpedientesFis').dataTable().show();
							$('#tblExpedientes').dataTable().fnClearTable();
							$('#tblExpedientes').dataTable().hide();
							
						}else{
							
							$('#tblExpedientes').dataTable().fnClearTable();
							$('#tblExpedientes').dataTable().fnDraw();
							$('#tblExpedientes').dataTable().show();
							$('#tblExpedientesFis').dataTable().fnClearTable();
							$('#tblExpedientesFis').dataTable().hide();
							
						}
						//PAS20191U210500144 Fin 400104 RF04 PSALDARRIAGA
						mostrarDlgInfo(titulos.tituloDefecto, msjError);
						return;
					}

					listaExpedientes = response.listaExpedientesVirtuales;
                    codTipoExpediente = response.codTipoExpediente;
                    if (listaExpedientes.length > 0) {
						//PAS20191U210500144 Inicio 400104 RF04 PSALDARRIAGA
						if((codPro == "002" && (codTipExp == "430" || codTipExp == "431")) || codTipoExpediente == "430" ||  codTipoExpediente == "431" ){
								
							
							$('#tblExpedientesFis').dataTable().fnClearTable();
							$('#tblExpedientesFis').dataTable().fnAddData(listaExpedientes);
							$('#tblExpedientesFis').dataTable().fnDraw();
							$('#tblExpedientesFis').dataTable().show();
							$('#exportar').hide();
							$('#exportarFis').show();
							$('#exportarFis').attr('disabled', false);
							$('#tblExpedientes').dataTable().fnClearTable();
							$('#tblExpedientes').dataTable().hide();
							
						} else{
						//PAS20191U210500144 Fin 400104 RF04 PSALDARRIAGA	
							
							$('#tblExpedientes').dataTable().fnClearTable();
							$('#tblExpedientes').dataTable().fnAddData(listaExpedientes);
							$('#tblExpedientes').dataTable().fnDraw();
							$('#tblExpedientes').dataTable().show();
							$('#exportarFis').hide();
							$('#exportar').show();
							$('#exportar').attr('disabled', false);
							$('#tblExpedientesFis').dataTable().fnClearTable();
							$('#tblExpedientesFis').dataTable().hide();
							
						}						
						
						
						
					}else{
						//PAS20191U210500144 Inicio 400104 RF04 PSALDARRIAGA
						if(codPro == "002" && (codTipExp == "430" || codTipExp == "431") ){
							
							alert("Expedientes fisca02:"+codTipExp);
							
							$('#tblExpedientesFis').dataTable().fnClearTable();
							$('#tblExpedientesFis').dataTable().fnDraw();
							$('#tblExpedientesFis').dataTable().show();
							$('#exportar').hide();
							$('#exportarFis').show();
							$('#exportarFis').attr('disabled', true);
							
						} else{
						//PAS20191U210500144 Fin 400104 RF04 PSALDARRIAGA	
							$('#tblExpedientes').dataTable().fnClearTable();
							$('#tblExpedientes').dataTable().fnDraw();
							$('#tblExpedientes').dataTable().show();
							$('#exportarFis').hide();
							$('#exportar').show();
							$('#exportar').attr('disabled', true);
							
						}
						
					}
				
				},
				error : function(response) {				
					//PAS20191U210500144 Inicio 400104 RF04 PSALDARRIAGA
					if(codPro == "002" && (codTipExp == "430" || codTipExp == "431") ){
					
						$('#tblExpedientesFis').dataTable().fnClearTable();
						$('#tblExpedientesFis').dataTable().fnDraw();	
						$('#tblExpedientesFis').dataTable().show();
						$('#tblExpedientes').hide();
						
					} else{
					//PAS20191U210500144 Fin 400104 RF04 PSALDARRIAGA	
						$('#tblExpedientes').dataTable().fnClearTable();
						$('#tblExpedientes').dataTable().fnDraw();
						$('#tblExpedientes').dataTable().show();
						$('#tblExpedientesFis').hide();
						
					}
					mostrarPagError();					
				}			
			});	
		}
		
		//Funcion para generar el archivo pdf con indice de expediente electronico.
		function obtenerPdf(numExpedienteVirtual){			
			$.ajax({
				url: '${pageContext.request.contextPath}/consulta/verificarListaDocumentoExpediente',
				type: 'POST',
				async: true,
				dataType: 'json',
				data: "numExpedienteVirtual="+numExpedienteVirtual,
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
                mimeType : "application/json",				
				success: function(response) {
					//PAS20191U210500144 Inicio 400104 RF04 PSALDARRIAGA
					if(response.excepciones != null){
						
						var excepciones = JSON.parse(response.excepciones);
						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion01);
						return;
						
					}else{
					//PAS20191U210500144 Fin 400104 RF04 PSALDARRIAGA	
						var esVacioListaDocumentos = JSON.parse(response.esVacioListaDocumentos);
						if(!esVacioListaDocumentos){
							var dataEnvio = new Object();		
							var url = "${pageContext.request.contextPath}/consulta/verDatosExpedienteVirtualPdf";
							var urlDescarga = url + "?numExpedienteVirtual=" + numExpedienteVirtual;
								
							if( isMobile.any() ){
								visorPdfDescarga(urlDescarga);
							} else{	
								//------- PARA DESCARGAR PDF EN WEB ------------
								window.open(urlDescarga, '_blank');   
							}						
						}else{
							mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion20);
						}	
						
					}							
				},
				error: function(err) {
					
				}
			});			
		}



		function exportarExcelDocumentos(){
		
			var dataExp = $('#tblDocumentos').dataTable().fnGetData();
			if(dataExp.length > 0){
				var listaCadena = JSON.stringify(dataExp);
				//var listaCadena = dataExp;
				var formURL = '${pageContext.request.contextPath}/consulta/exportarExcelDoc';
				document.forms.formPrincipalDocumentos.action = formURL;
				document.forms.formPrincipalDocumentos.method = "POST";
				$('#campoHiddenExpDoc').val('');
				$('#campoHiddenExpDoc').val(listaCadena);	
				//Obtener Paramtros Inicio
				var numExpeOrigen = numExpedienteConsultar;		
				var estExpediente = $('#txtEstExpediente').val();
				var desProceso = $('#txtProceso').val();
				var desTipoExpediente = $('#txtTipoExpediente').val();		
				var fechaRegistro = $('#txtFechaRegExpediente').val();
				//Obtener Paramtros fin
				//limpiar campos hdn Inicio
				$('#hiddenNumExpOrigen').val('');
				$('#hiddenEstExpediente').val('');
				$('#hiddenTipoProceso').val('');
				$('#hiddenTipoExpediente').val('');
				$('#hiddenFechaGeneracion').val('');
				//limpiar campos hdn Inicio
				//setear campos hdn Inicio
				$('#hiddenNumExpOrigen').val(numExpeOrigen);
				$('#hiddenEstExpediente').val(estExpediente);
				$('#hiddenTipoProceso').val(desProceso);
				$('#hiddenTipoExpediente').val(desTipoExpediente);
				$('#hiddenFechaGeneracion').val(fechaRegistro);
				//setear campos hdn fin
				$('html').block({message: '<h1>Procesando</h1>'});
				document.forms.formPrincipalDocumentos.submit();
				$('html').unblock();
				
				
			}
			
		}

		function cargarTiposExpedientes() {
			resetearFormularioConEsp()
			
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
					url: '${pageContext.request.contextPath}/consulta/cargarListaTiposExpediente',
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

	
		function resetearFormularioConEsp(){
			$('#frmFiltroBusquedaEspecifica').bootstrapValidator('resetForm', true);
			$('#txtNumeroExpediente').prop('disabled', true);
		}
	
		function limpiarConsulta(){
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('resetForm', true);
			$('#frmFiltroBusquedaEspecifica').bootstrapValidator('resetForm', true);
			
			$('#selNumeroTipoExpediente').val("");
			$('#txtNumeroExpediente').val("");
			$("#txtNumeroExpediente").attr('disabled', false);
			$('#selCodigoProceso').val("");
			$('#selCodigoTipoExpendiente').val("");		
			$('#selCodigoTipoExpendiente').empty();
			$('#selCodigoTipoExpendiente').append($('<option>', {
				value: '',
				text: 'Seleccione'
			}));
			$('#selCodigoEstado').val("");
			$('#txtfechaDesde').val("");
			$('#txtfechaHasta').val("");
			$("#txtfechaDesde").attr('disabled', false);
			$("#txtfechaHasta").attr('disabled', false);
			$('#tblExpedientes').dataTable().fnClearTable();
            $('#tblExpedientesFis').dataTable().fnClearTable();
			$('#exportar').attr('disabled', true);
            $('#exportarFis').attr('disabled', true);
		}

		function revalidarFormulario(){
			var tipoBusqueda = $('#txtNumeroExpediente').val();
			console.log('0');
			if(tipoBusqueda!=""){
				console.log('1');
				$('#frmFiltroBusquedaEspecifica').submit();
			}else{
				console.log('2');
				$('#frmFiltroBusquedaAvanzada').submit();
			}			
			
		}


		function exportarExcel(){
			
			var dataExp = $('#tblExpedientes').dataTable().fnGetData();
			if(dataExp.length > 0){
				var listaCadena = JSON.stringify(dataExp);
			    var formURL = '${pageContext.request.contextPath}/consulta/exportarExcelExpedientes';
				if( isMobile.any() ){
					document.forms.formPrincipal.action = formURL;
					document.forms.formPrincipal.method = "POST";
					$('#campoHiddenExp').val('');
					$('#campoHiddenExp').val(listaCadena);
					$('html').block({message: '<h1>Procesando</h1>'});
					document.forms.formPrincipal.submit();
					$('html').unblock();
					
				}else{
					document.forms.formPrincipal.action = formURL;
					document.forms.formPrincipal.method = "POST";
					$('#campoHiddenExp').val('');
					$('#campoHiddenExp').val(listaCadena);
					$('html').block({message: '<h1>Procesando</h1>'});
					document.forms.formPrincipal.submit();
					$('html').unblock();
				}			
			}		
		}
        
    //PAS20191U210500144 Inicio 400104 RF04 PSALDARRIAGA
	function exportarExcelFis(){
		
			var dataExp = $('#tblExpedientesFis').dataTable().fnGetData();	
			
			if(dataExp.length > 0){
				
				var listaCadena = JSON.stringify(dataExp);
			    var formURL = '${pageContext.request.contextPath}/consulta/exportarExcelExpedientesFis';
			    
				if( isMobile.any() ){
					
					document.forms.formPrincipal.action = formURL;
					document.forms.formPrincipal.method = "POST";
					$('#campoHiddenExp').val('');
					$('#campoHiddenExp').val(listaCadena);
					$('html').block({message: '<h1>Procesando</h1>'});
					document.forms.formPrincipal.submit();
					$('html').unblock();
					
				}else{
					
					document.forms.formPrincipal.action = formURL;
					document.forms.formPrincipal.method = "POST";
					$('#campoHiddenExp').val('');
					$('#campoHiddenExp').val(listaCadena);
					$('html').block({message: '<h1>Procesando</h1>'});
					document.forms.formPrincipal.submit();
					$('html').unblock();
					
				}			
			}		
	}
	//PAS20191U210500144 Fin 400104 RF04 PSALDARRIAGA

	function verRepImpresa(idxFila){
		borrarErrorExtension();
		var dataEnvio = new Object();
		dataEnvio.docData = JSON.stringify($('#tblDocumentos').DataTable().row(idxFila).data());
		dataEnvio.ruc = $("#txtRuc").val();  
		dataEnvio.razSoc = $.trim($("#txtRazonSocial").val());
		
		$.ajax({
            url: '${pageContext.request.contextPath}/consulta/validarExtension',
           	data: $.param(dataEnvio),
            cache: false,
            async: true,
            type: 'POST',
            contentType : "application/x-www-form-urlencoded; charset=utf-8",
            dataType: 'json',
            success: function(response) {            	
            	if(!response.error){
					if( isMobile.any() ){
						visorPdfDescarga('${pageContext.request.contextPath}/consulta/verRepImpresa?' + $.param(dataEnvio));
					}else{
						//------- PARA DESCARGAR PDF EN WEB ------------
						window.open('${pageContext.request.contextPath}/consulta/verRepImpresa?' + $.param(dataEnvio), "_blank", "toolbar=yes, scrollbars=yes, resizable=yes, top=20, left=300, width=600, height=650");   
					}					
            	}else{
            		mostrarErrorExtension(response.extension);
            	}            	
            },
            error: function(err) {
            	document.write(err.responseText);
            }
        });
		
	}
	
	function mostrarErrorExtension(extension){
		$('#divErrorExtension').html('No se puede generar Representaci&oacute;n Impresa del Documento con extensi&oacute;n: "' + extension + '"');
	}


	function borrarErrorExtension(){
		$('#divErrorExtension').html('');
	}
	
	function guardarNumExpediente(numExpediente){
		numExpedienteConsultar = numExpediente;		
	}
		
	
	function validacionFechas() {
		
		var fechaDesde = document.getElementById("txtfechaDesde").value;
		var fechaHasta = document.getElementById("txtfechaHasta").value;
		var dd = fechaDesde.substring(0, 2);
		var mm = fechaDesde.substring(3, 5);
		var yyyy = fechaDesde.substring(6, 10);
		var rango = parseInt(excepciones.rangoFecha);
		//var fechaCompara = new Date(mm+"/"+dd+"/"+yyyy);
		var fechaCompara = new Date(yyyy,mm-1,dd);
		//var rangoFechaMaxBusqueda = fechaCompara.addMonths(3);
		var rangoFechaMaxBusqueda = add_months(fechaCompara,3);
		
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
	
	function add_months(dt, n) 
	{

		return new Date(dt.setMonth(dt.getMonth() + n));      
	}

		//PAS20191U210500291 INICIO LLRB 

		function construirTablaDocumentos(dataGrilla) {
			$('#tblDocumentos').dataTable( {
				data: dataGrilla,
				responsive: true,
				pageLength: 5,
				dom: '<"html5buttons"B>rtip',
			
				"language": {
					"url": "/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
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


				"columns" : [		
				{"data" :	"correlativo", 'sortable': false ,
					render : function(data, type, row, meta){
					   return meta.settings.aoData.length;
					}
				},
				{"data" :	"desTipdoc",'sortable': false },
				{"data" :	"numDoc",'sortable': false },
				{"data" :	"desOrigenDoc",'sortable': false },
				//{"data" :	"nroRequerim",'sortable': false },
				{"data" :	"fechaRegistroDoc",'sortable': false },
				{"data" :	"fechaDocumento",'sortable': false },
				{"data" : 	"strfecNotif",'sortable': false },
				{"data" : 	"desForNotif",'sortable': false },
				{"data" :	"codOrigenExpe",'sortable': false },
				//{"data" :	"numExpedv"},
				/*{"data" :	"numAcumuladorVista",sClass: 'center alinearCentrado',"defaultContent":"",'sortable': false , 
					// Fin [jquispe 13/06/2016]
					render : function(data, type, row){								
						var link;						
						if(data == "" || data == "0") {
							return "";
						}else {
							return $('<a>')
								.css('text-decoration','underline')
								.attr({	onclick :'obtenerDocumentosAcum("'+row.numAcumuladorVista+'")'})
								.text(data)
								.wrap('<div></div>')
								.parent()
								.html();
						}									
					}
				},*/
				//INICIO LLRB
				{"data" :	"desTipdocRel",'sortable': false },
				{"data" :	"numDocRel",'sortable': false },
				{"data" :	"codIdentificadorECM","defaultContent":"",sClass: 'center alinearCentrado','sortable': false , 
					render : function(data, type, row){								
						var link;
						if(row.codIdentificadorECM !="-"){
							var url = "${pageContext.request.contextPath}/consulta/descargarArchivoDoc?codIdentificadorECM=" + row.codIdentificadorECM +'&numExpedienteVirtual='+row.numExpedienteVir+'&nombreArchivo='+row.descripcionArchivo;	
							console.log(
									    "ECM" +row.codIdentificadorECM);
							if( isMobile.any() ){
								link="<button type='button' class='btn btn-warning' onClick='visorPdfDescarga(" + '"' + url + '"' + ")'><i class='cimg glyphicon glyphicon-download-alt'></i></button>";
							}else{
								link = '<a id='+row.correlativo+' href="' + url + '" role="button" class="btn btn-warning cimg glyphicon glyphicon-download-alt" target=_blank ></a>';								
							}
							
						}
						else if (row.codIdentificadorECM ="-"){			
							link = '<a  role="button" class="cimg glyphicon glyphicon-plus-sign" target=_blank  onclick="listarArchivos	(\''+ 
							row.numCorDoc + '\',\'' + 
							row.desTipdoc + '\',\'' + 
							row.numDoc + '\')"> </a>';
						}
						return link;										
					},'sortable': false //FIN LLRB
				 },	
			
				{"data" :	"codIdentificadorECM", "defaultContent":"", sClass: 'center alinearCentrado',
					render : function(data, type, row){								
						var link;
						if(row.codIdentificadorECM !="-"){
							link="<button type='button' class='btn btn-info' onClick='verRepImpresa(" + '"'  + (parseInt(row.correlativo) - 1)  + '"' + ")'><i class='cimg glyphicon glyphicon-camera'></i></button>";
							
						}
						else if (row.codIdentificadorECM ="-"){			
							link = " ";
						}
						return link;										
					},'sortable': false //FIN LLRB
				 },	
				{"data" : 	"estadoDocumentoReq",'sortable': false, sClass: 'hidden',"defaultContent":"" },
				{"data" :	"numExpedienteVir",'sortable': false, sClass: 'hidden',"defaultContent":"" },
				{"data" :	"descripcionArchivo",'sortable': false, sClass: 'hidden',"defaultContent":"" }
			]

			} );
			var tableDC = $('#tblDocumentosFiltro').dataTable( {
				data: dataGrilla,
				"responsive": true,
				pageLength: 5,
				dom: '<"html5buttons"B>rtip',
				"bDestroy": true,
				"language": {
					"url": "/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
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


				"columns" : [		
				{"data" :	"correlativo", 'sortable': false ,
					render : function(data, type, row, meta){
					   return meta.settings.aoData.length;
					}
				},
				{"data" :	"desTipdoc",'sortable': false },
				{"data" :	"numDoc",'sortable': false },
				{"data" :	"desOrigenDoc",'sortable': false },

				{"data" :	"fechaRegistroDoc",'sortable': false },
				{"data" :	"fechaDocumento",'sortable': false },
				{"data" : 	"strfecNotif",'sortable': false },
				{"data" : 	"desForNotif",'sortable': false },
				{"data" :	"codOrigenExpe",'sortable': false },

				/*{"data" :	"numAcumuladorVista",sClass: 'center alinearCentrado',"defaultContent":"",'sortable': false , 
					// Fin [jquispe 13/06/2016]
					render : function(data, type, row){								
						var link;						
						if(data == "" || data == "0") {
							return "";
						}else {
							return $('<a>')
								.css('text-decoration','underline')
								.attr({	onclick :'obtenerDocumentosAcum("'+row.numAcumuladorVista+'")'})
								.text(data)
								.wrap('<div></div>')
								.parent()
								.html();
						}									
					}
				},*/
				//INICIO LLRB
				
				{"data" :	"codIdentificadorECM","defaultContent":"",sClass: 'center alinearCentrado','sortable': false , 
					render : function(data, type, row){								
						var link;
						if(row.codIdentificadorECM !="-"){
							var url = "${pageContext.request.contextPath}/consulta/descargarArchivoDoc?codIdentificadorECM=" + row.codIdentificadorECM +'&numExpedienteVirtual='+row.numExpedienteVir+'&nombreArchivo='+row.descripcionArchivo;	
							console.log(
									    "ECM" +row.codIdentificadorECM);
							if( isMobile.any() ){
								link="<button type='button' class='btn btn-warning' onClick='visorPdfDescarga(" + '"' + url + '"' + ")'><i class='cimg glyphicon glyphicon-download-alt'></i></button>";
							}else{
								link = '<a id='+row.correlativo+' href="' + url + '" role="button" class="btn btn-warning cimg glyphicon glyphicon-download-alt" target=_blank ></a>';								
							}
							
						}
						else if (row.codIdentificadorECM ="-"){			
							link = '<a  role="button" class="cimg glyphicon glyphicon-plus-sign" target=_blank  onclick="listarArchivos	(\''+ 
							row.numCorDoc + '\',\'' + 
							row.desTipdoc + '\',\'' + 
							row.numDoc + '\')"> </a>';
						}
						return link;										
					},'sortable': false //FIN LLRB
				 },	
				
				{"data" :	"codIdentificadorECM", "defaultContent":"", sClass: 'center alinearCentrado',
					render : function(data, type, row){								
						var link;
						if(row.codIdentificadorECM !="0"){
							link="<button type='button' class='btn btn-info' onClick='verRepImpresa(" + '"'  + (parseInt(row.correlativo) - 1)  + '"' + ")'><i class='cimg glyphicon glyphicon-camera'></i></button>";
							
						}
						else if (row.codIdentificadorECM ="0"){			
							link = " ";
						}
						return link;									
					},'sortable': false 
				 },	//FIN LLRB
				{"data" : 	"estadoDocumentoReq",'sortable': false, sClass: 'hidden',"defaultContent":"" },
				{"data" :	"numExpedienteVir",'sortable': false, sClass: 'hidden',"defaultContent":"" },
				{"data" :	"descripcionArchivo",'sortable': false, sClass: 'hidden',"defaultContent":"" }
			]

			} );
			
		}

    	function fnObtenerDocumentos(numExpedienteVirtual){			
    		borrarErrorExtension()
    		borrarErrorExtensionArchivo()
			/*
			* Consulta ajax para cargar datatable detalle
			*/

			$.ajax({
				url : '${pageContext.request.contextPath}/consulta/cargarDocumentosExpOrigen',
				data : "numExpedienteVirtual="+numExpedienteVirtual,
				async : true,
				dataType : "json",
				mimeType : "application/json",
				success : function(response) {
					if (response.excepciones != null) {
						var excepciones = JSON.parse(response.excepciones);
						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion01);
						return;
					}
					var t6614ExpVirtualBean = JSON.parse(response.t6614ExpVirtualBean);
					var razonSocial=JSON.parse(response.razonSocial);
					var fechaRegistro = JSON.parse(response.fechaRegExpediente);
					var listaDocumentos = JSON.parse(response.listaDocumentos);

					
					$('#txtRuc').attr("value", t6614ExpVirtualBean.numRuc);
					$('#txtRazonSocial').attr("value", razonSocial);
					$('#txtEstExpediente').attr("value", t6614ExpVirtualBean.desEstado);
					$('#txtProceso').attr("value", t6614ExpVirtualBean.desProceso);
					$('#txtTipoExpediente').attr("value", t6614ExpVirtualBean.desTipoExpediente);
					$('#txtFechaRegExpediente').attr("value", fechaRegistro);


					strModalExpediente = t6614ExpVirtualBean.numExpedienteOrigen;				
					$('#hiddenNumExpVirtualDoc').val(t6614ExpVirtualBean.numExpedienteVirtual);	

					
					if (listaDocumentos.length > 0) {
						// PAS20191U210500291 INICIO LLRB 
							console.log("CodTipoExpediente "+t6614ExpVirtualBean.codTipoExpediente);
							if(t6614ExpVirtualBean.codTipoExpediente== '430' ||t6614ExpVirtualBean.codTipoExpediente== '431' ){
								$('#DocumentoCompleto').show();
								$('#DocumentoIncompleto').hide();
								$('#tblDocumentos').dataTable().fnClearTable();
								$('#tblDocumentos').dataTable().fnAddData(listaDocumentos); 
								$('#exportarDocumentos').attr('disabled', false);
							}else{
								$('#DocumentoCompleto').hide();
								$('#DocumentoIncompleto').show();
								$('#tblDocumentosFiltro').dataTable().fnClearTable();
								$('#tblDocumentosFiltro').dataTable().fnAddData(listaDocumentos); 
								$('#exportarDocumentos').attr('disabled', false);
							}

						}else{
							$('#tblDocumentos').dataTable().fnClearTable();
							$('#tblDocumentos').dataTable().fnDraw();
							$('#exportarDocumentos').attr('disabled', true);
						}	
					 //PAS20191U210500291 FIN LLRB			
		    		$('#dlgTitleModalDocumentos').html("N&deg; Expediente " + strModalExpediente);
					$('#divModal01').modal('show');
				},
				error : function(response) {
					$('#tblDocumentos').dataTable().fnClearTable();
					$('#tblDocumentos').dataTable().fnDraw();				
					mostrarPagError();
				}		
			
			});
		}
	  
    	function listarArchivos(numCorDoc,desTipoDoc,numDoc){
    		var dataEnvio = new Object();
    		$.ajax({
    				url : '${pageContext.request.contextPath}/consulta/cargarArchivosExpVirtual',
    				data : "numCorDoc="+numCorDoc,
    				async : true,
    				dataType : "json",
    				type: 'GET',
    				mimeType : "application/json",
    				success : function(response) {
    				
    				if (response.excepciones != null) {
    						var excepciones = JSON.parse(response.excepciones);
    						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion01);
    						return;
    					}

    					var lstArchExp = JSON.parse(response.lstArchExp);
    					console.log("Lista Archivo: "+lstArchExp);
    					
    					if (lstArchExp.length > 0) {
    					console.log("Lista Archivo: "+lstArchExp);
    						$('#tableArchivoModal').dataTable().fnClearTable();
    						var datatable=$('#tableArchivoModal').dataTable();
    						datatable.fnAddData(lstArchExp); 
    						$('#exportarArchivos').attr('disabled', false);
    					
    					}else{
    						$('#tableArchivoModal').dataTable().fnClearTable();
    						$('#tableArchivoModal').dataTable().fnDraw(); 	
    						$('#exportarArchivos').attr('disabled', true);
    					}
    					var titulo = "Lista de archivos del documento " +desTipoDoc + "-" +numDoc;
    					
    					mostrarModalArchivo(titulo);

    					setTimeout(
    							function(){
    								$("#tableArchivoModal_wrapper").css("min-width", $('#tableArchivoModal').width());
    								$('#tableArchivoModal_paginate').addClass('div100');
    					}, 150);
    				},
    				error : function(response) {
    					
    					mostrarPagError();
    					
    				}
    			
    			});
    		}
    		
    		function mostrarModalArchivo(titulo){
    			$('#dlgTitleModalArchivos').html(titulo);
    			$('#modalArchivos').modal('show');
    		}
    		
    		function construirTablaArchivo(data){
    		$('#tableArchivoModal').DataTable( {
    		"language": {
    			"url"		:	"../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
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
    				{data :	"numOrden","defaultContent":""},
    				{data :	"numArc","defaultContent":""},
    				{data :	"nomArcAdj","defaultContent":"" },
    				{data :	"numItem","defaultContent":"" },
    				{data :	"cntTamArc","defaultContent":"",
						render:function(data, type, row){								
							var tamano;
							tamano  = (row.cntTamArc)/1024.00;
				             if (tamano >= 1) {
				                    tamano = Math.round(tamano);
				             }
				             var tamanoArchivo = parseFloat(tamano).toFixed(2);
				             var formato = "KB"
							return tamanoArchivo+formato;
							// return tamanoArchivo;
						}
					},
    				{data :	"numFolios","defaultContent":""},
    				{data :	"codIdecm","defaultContent":"", sClass: 'center alinearCentrado',
    					render : function(data, type, row){		

    							var url = "${pageContext.request.contextPath}/consulta/descargarArchivoModal?codIdentificadorECM=" + data +'&nombreArchivo='+row.nomArcAdj;	

    							if( isMobile.any() ){
    								link="<button type='button' class='btn btn-warning' onClick='visorPdfDescarga(" + '"' + url + '"' + ")'><i class='cimg glyphicon glyphicon-download-alt'></i></button>";
    							}else{
    								link = '<a id='+row.correlativo+' href="' + url + '" role="button" class="btn btn-warning cimg glyphicon glyphicon-download-alt" target=_blank ></a>';								
    							}																			
    						return link;										
    					},'sortable': false 
    				},
    				{data :	"codIdecm", "defaultContent":"", sClass: 'center alinearCentrado',
    					render : function(data, type, row){		
    						var archivo = row.nomArcAdj
							var tipo = archivo.substr(archivo.length -3)
							if(tipo!="zip"){
    								var link;
    									link="<button type='button' class='btn btn-info' onClick='verRepArchivoImpresa(" + '"'  + (parseInt(row.numOrden) - 1)  + '"' + ")'><i class='cimg glyphicon glyphicon-camera'></i></button>";
    									
    						      }	else{
    						    	  link = " ";
    						      }
    								return link;	
    						}
    				},
    				{data :	"codTipExp","defaultContent":"",sClass:"hidden"},
    				{data :	"codTipDoc","defaultContent":"",sClass:"hidden"},
    				{data :	"numDoc","defaultContent":"",sClass:"hidden"},
    				{data :	"numExpedO","defaultContent":"",sClass:"hidden"},
    				{data :	"numCorDoc","defaultContent":"",sClass: 'hidden'}
    				
    		],
    		
    		tableTools: {
    	        "sSwfPath": "../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/TableTools/swf/copy_csv_xls.swf"
    	    },
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
    		bLengthChange		: 	false,
    		fnDrawCallback		:	function(oSettings) {
    			var a = $('#tableArchivoModal').width()
    			$("#tableArchivoModal_wrapper").css("min-width", a);
    			$('#tableArchivoModal_paginate').addClass('div100');
    			if (oSettings.fnRecordsTotal() == 0) {
    				$('#table_paginate').addClass('hiddenDiv'); //el footer de paginacion siempre tiene como ID "miTablaID_paginate"
    																	//no se oculta automaticamente
    														//cuando hay 0 registros, por eso se oculta anadiendo el class .hiddenDiv
    	        }
    			else {
    				$('#table_paginate').removeClass('hiddenDiv');
    				
    			}
    	    }
    		        
          } );

        }
    		
    	function mostrarErrorExtensionArchivo(extension){
    			$('#divErrorExtensionArchivo').html('No se puede generar Representaci&oacute;n Impresa del Documento con extensi&oacute;n: "' + extension + '"');
    	}

    	function borrarErrorExtensionArchivo(){
    			$('#divErrorExtensionArchivo').html('');
    	}
    		
    	function verRepArchivoImpresa(idxFila){
    		borrarErrorExtensionArchivo();
    			var dataEnvio = new Object();
    			dataEnvio.docData = JSON.stringify($('#tableArchivoModal').DataTable().row(idxFila).data());
    			dataEnvio.ruc = $("#txtRuc").val();  
    			dataEnvio.razSoc = $.trim($("#txtRazonSocial").val());
    			
    			$.ajax({
    	            url: '${pageContext.request.contextPath}/consulta/validarExtensionArchivo',
    	           	data: $.param(dataEnvio),
    	            cache: false,
    	            async: true,
    	            type: 'POST',
    	            contentType : "application/x-www-form-urlencoded; charset=utf-8",
    	            dataType: 'json',
    	            success: function(response) {            	
    	            	if(!response.error){
    						if( isMobile.any() ){
    							visorPdfDescarga('${pageContext.request.contextPath}/consulta/verRepArchivoImpresa?' + $.param(dataEnvio));
    						}else{
    							//------- PARA DESCARGAR PDF EN WEB ------------
    							window.open('${pageContext.request.contextPath}/consulta/verRepArchivoImpresa?' + $.param(dataEnvio), "_blank", "toolbar=yes, scrollbars=yes, resizable=yes, top=20, left=300, width=600, height=650");   
    						}					
    	            	}else{
    	            		mostrarErrorExtensionArchivo(response.extension);
    	            	}            	
    	            },
    	            error: function(err) {
    	            	document.write(err.responseText);
    	            }
    	        });
    			
    	}
    		
    	 //fin LLRB

	</script>
	
	
	
  </body>
</html>