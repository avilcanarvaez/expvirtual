<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="es">

	<head>
		
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=EDGE" />
		<meta name = "viewport" content = "initial-scale = 1.0, user-scalable = no,  width=device-width">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>ASIGNACION DE ACCESOS</title>
		
		<!-- Bootstrap -->
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/css/bootstrap-theme.min.css">
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/media/css/jquery.dataTables.css">
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Responsive/css/dataTables.responsive.css">
		<link rel="stylesheet" type="text/css" href="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/media/css/jquery.dataTables_themeroller.css">
		<link rel="stylesheet" type="text/css" href="/a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Scroller/css/dataTables.scroller.css">
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/css/bootstrap-datetimepicker.min.css"/>
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/font-awesome/4.4.0/css/font-awesome.min.css">
		
		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 10]>
			<script src="../a/js/libs/bootstrap/3.3.2/plugins/html5shiv/3.7.2/html5shiv.min.js"></script>
	      <script src="../a/js/libs/bootstrap/3.3.2/plugins/respond/1.4.2/respond.min.js"></script>
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
			.panel-scroll {
			max-height: auto;
			min-height: 0px;
			overflow-y: auto;
			overflow-x: auto;
			}
		</style>
		
	</head>
	
	<body>
		
		<div class="container">
			<div>
				<div class="row">
					<div class="panel panel-primary">
						<div class="panel-heading align="center">
							<h3 class="panel-title" align="center">ASIGNACION DE ACCESOS</h3>
							<form id="frmPrincipal" class="form-horizontal" role="form">
							</form>
							
						</div>
						
				<form id="formDocInterno" name="formDocInterno" role="form" method="post">
							
					
					
						<div class="panel-body">
							
							<div class="row content-box">
								<div class="col-md-2">
									<label>Buscar por Número de Expediente: </label>
								</div>
								
								<div class="col-md-2">
									<input id="txtNumeroExpediente" name="txtNumeroExpediente" type="text" class="form-control form-control-single" onkeypress="return permitirNumeros(event);"/> 
								</div>
								<div class="col-md-2">
									<input id="btnBuscar" type="button" class="btn btn-primary" onclick="buscar()" value="Buscar" />
								</div>
								<div class="col-md-1">
									&nbsp;
								</div>
								<div class="col-md-1">
									<label>Fecha: </label>
								</div>
								<div class="col-md-2">
									<input id="txtFechaSistema" name="txtFechaSistema" type="text" class="form-control form-control-single" readonly="readonly"/> 
								</div>
							</div>
							
							<div class="row content-box">
								&nbsp;
							</div>
							
							<div class="row content-box">
								<div class="col-md-2">
									<label>RUC: </label>
								</div>
								<div class="col-md-2">
									<input id="txtNumRuc" name="txtNumRuc" type="text" class="form-control form-control-single" readonly="readonly"/>
								</div>
								<div class="col-md-2">
									&nbsp;
								</div>
								<div class="col-md-2">
									<label>Raz&oacute;n Social: </label>
								</div>
								<div class="col-md-4">
									<input id="txtDesRazonSocial" name="txtDesRazonSocial" type="text" class="form-control form-control-single" readonly="readonly"/> 
								</div>
							</div>
							
						</div>
					
				

						
					</form>
					<fieldset style="margin : 15px 15px !important">
												
							<div class="form-group">	
								<div class="col-md-12">
									<div class="tab-content">
										<div class="tab-pane fade in active">
											<div class="border-line panel-scroll">
												<table id="table" class="table display" cellspacing="0" style="width: 100%;"><!--table table-striped table-bordered-->
													<thead>
														<tr class="active">
															<th width="10%" class="text-center">N&deg; solicitud acceso</th>
															<th width="10%" class="text-center">Fecha de solicitud</th>
															<th width="5%" class="text-center">Motivo</th>
															<th width="5%" class="text-center">Tipo de sensibilidad</th>
															<th width="5%" class="text-center">Codigo de registro</th>
															<th width="20%" class="text-center">Apellidos y Nombres</th>
															<th width="10%" class="text-center">Unidad Organizacional</th>
															<th width="5%" class="text-center">Periodo</th>
															<th width="10%" class="text-center">Fecha de inicio</th>
															<th width="10%" class="text-center">Fecha de fin</th>
															<th width="5%" class="text-center">Estado</th>
															<th width="5%">Eliminar</th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
					</fieldset>
					<div style="margin : 15px 15px !important">
						<div class="row content-box">
							<div class="col-md-12 text-center">
								<input id="btnAsignar" type="button" class="btn btn-primary" onclick="agregar()" value="Agregar" />
								<input id="btnExportar" type="button" class="btn btn-primary" onclick="exportar()" value="Exportar a Excel" />
								<input id="btnLimpiar" type="button" class="btn btn-primary" onclick="limpiar()" value="Limpiar" />

							</div>
						</div>
					</div>
					
					<form id="formPrincipal" class="form-horizontal" role="form">		
						<input id="campoHiddenExp" type="hidden" name="listaCadena"/>
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
		
		
		
		
		
		<script src="../a/js/libs/jquery/1.11.2/jquery.min.js"></script>
		<script src="../a/js/libs/bootstrap/3.3.2/js/bootstrap.min.js"></script>
		<script src="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/media/js/jquery.dataTables.min.js" type="text/javascript" ></script>    
		<script src="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Responsive/js/dataTables.responsive.js" type="text/javascript" ></script>
		<script src="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/TableTools/js/dataTables.tableTools.min.js" type="text/javascript" ></script>
		<script src="../a/js/js.js" type="text/javascript" ></script>
		
		<script type="text/javascript" src="../a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/js/moment-with-locales.js"></script>
		<script src="../a/js/libs/bootstrap/3.3.2/plugins/jquery.inputmask-3.1/dist/jquery.inputmask.bundle.min.js" type="text/javascript"></script>
		<script src="../a/js/bootstrap/3.2.0/js/jquery.blockUI.js" type="text/javascript"></script>
		
		<script>
		
		var titulos = ${titulos};
		var excepciones = ${excepciones};
		var registroInsert = ${registroInsert};
		var registroRegresar = ${registroRegresar};
		
		var mensajeEsepcion = "";
		var gRucValido = false;
		var indicador = false;
		var idxPaginador = -1;
		var tblPage = "${beanParametrosConsultaReq.tblPage}";  //anadir
		//Inicio [jquispe 27/05/2016] Realiza la accion cuando el usuario realiza la consulta.
		var flagBusquedaBoton = false;
		// Fin [jquispe 27/05/2016]
		
			var hoverBuscar=false;
		
			var avisos = ${avisos};
			var fecVista = ${fecVista};
			var codDependenciaBase = ${codDependenciaBase};
			
			var expedienteVirtual;
			
			var dataDisponible = [];
			var dataAsociada = [];
			
			var dataFinal = [];
			
			var indOrigen = false;
			
			if (registroInsert == "1"){
				
				var numExpediente = ${numExpediente};
				consultar(numExpediente);
				mostrarDlgInfo("Registrar Acceso al expediente", "Registro insertado correctamente.");
				
			}
			
			if (registroRegresar == "1"){
				
				var numExpediente = ${numExpediente};
				consultar(numExpediente);
				
			}
		
		$(document).ready(function() {

			$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
			$("#btnAsignar").prop("disabled", true);
			$("#btnExportar").prop("disabled", true);
			jQuery.support.cors = true;
			
			$('#txtFechaSistema').attr("value", fecVista);
			$("#txtNumeroExpediente").attr('maxlength', 17);
			setearMascara( '#txtNumeroExpediente', "(9){1,17}", null, '' );
			
			inicializarTablas();
				
			});
			
			
			function setearMascara( controlQuery, mascara, maxlength, valorInicial ) {
				
				var control = $( controlQuery );
				
				control.inputmask( mascara, {placeholder: ''});
				
				if ( maxlength != null ) {
					control.prop('maxlength', maxlength);	
				}
				
				if ( valorInicial != null ) {
					control.val( valorInicial );	
				}
				
			}
			

			
			function inicializarTablas() {
			
				$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
				
				$('#table').DataTable( {
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
					"order": [[ 1, "desc" ]],
					columns : [
						{data : "numSoli", sClass: 'centered alinearCentrado'},
						{data : "fecSoli", sClass: 'izquierda alinearCentrado'},
						{data : "desMotivo", sClass: 'izquierda alinearCentrado'},
						{data : "desTipo", sClass: 'izquierda alinearCentrado'},
						{data : "codColaborador", sClass: 'izquierda alinearCentrado'},
						{data : "desColaborador", sClass: 'izquierda alinearCentrado'},
						{data : "unidOrganizacional", sClass: 'izquierda alinearCentrado'},
						{data : "desPeriodo", sClass: 'izquierda alinearCentrado'},
						{data : "fecInicio", sClass: 'izquierda alinearCentrado'},
						{data : "fecFinal", sClass: 'izquierda alinearCentrado'},
						{data : "desEstado", sClass: 'izquierda alinearCentrado'},
						{data :	"numSoli",
						render : function(data, row){
						return jQuery('<span>').attr(
							{
								//"src" : "../a/imagenes/icon_delete.png",
								"class" : "cimg glyphicon glyphicon-remove",
								onClick : "confirmarEliminar('" + data +  "')"
							}
						).wrap('<div></div>').parent().html();
					}}
					],
					
					tableTools: {
				        "sSwfPath": "../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/TableTools/swf/copy_csv_xls.swf"
				    },
					ordering			:	true,
					bScrollAutoCss		:	true,
					bStateSave			:	false,
					bAutoWidth			:	false,
					bScrollCollapse		:	false,
					searching			:	false,
					paging				:	true,
					pagingType			:   "full_numbers",
					iDisplayLength		:	10,
					responsive			:	true,
					bLengthChange		: 	false,
					fnDrawCallback		:	function(oSettings) {
					//[ATORRESCH 2017-05-12] CONSIDERAR EL ANCHO DATATABLES									
					$("#table_wrapper").css("min-width", $('#table').width());

					$('#table_paginate').addClass('div100');
						if (oSettings.fnRecordsTotal() == 0) {
							$('#table_paginate').addClass('hiddenDiv'); //el footer de paginacion siempre tiene como ID "miTablaID_paginate"
																				//no se oculta automaticamente
																	//cuando hay 0 registros, por eso se oculta anadiendo el class .hiddenDiv
				        }
						else {
							$('#table_paginate').removeClass('hiddenDiv');
							$('#tblPage').val(getPaginaTbl("table"));
						}
				    }
					        
			} );
		
			}

			function buscar() {
				
				var numExpediente = $('#txtNumeroExpediente').val();
								
				if (numExpediente == '') {
					
					mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion01);
					
				} else {
					// [jjurado 21/06/2016] se reduce el mínimo de dígitos válidos para realizar la búsqueda del expediente origen
					//if (numExpediente.length < 13 || numExpediente.length > 17) {
					if (numExpediente.length > 17) {
						
						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion02);

					} else {
						
						var dataEnvio = new Object();
						
						dataEnvio.numExpediente = numExpediente;
						dataEnvio.codDependenciaBase = codDependenciaBase;//[oachahuic][PAS20165E210400270]
						
						$.ajax({
							
							url : '${pageContext.request.contextPath}/accesoExpe.htm?action=cargarAccesoExpediente&indCarga=1',
							type : 'POST',
							async : true,
							dataType : "json",
							data : JSON.stringify(dataEnvio),
							contentType : "application/json",
							mimeType : "application/json",
							//timeout : 5000,
							success : function(response) {
								
								expedienteVirtual = response.expedienteVirtual;
								var contribuyente = response.contribuyente;
								var indListaUniOrgaDependVacio = response.indListaUniOrgaDependVacio;
								var dependencia = response.dependencia
								dataFinal = response.responsablesAsignados;
								
								
								if (expedienteVirtual != null) {								

										if (expedienteVirtual.codEstado == '01'){
										
										if(indListaUniOrgaDependVacio != "-1"){	
										
										if(dependencia != "-1"){	

										if (contribuyente != null) {
										
										
										
										if (dataFinal != null) {

												$('#txtNumRuc').val(contribuyente.numRuc);
												$('#txtDesRazonSocial').val(contribuyente.desRazonSocial);
												
												$("#txtNumeroExpediente").attr('readonly', true);
												
												$("#btnBuscar").prop("disabled", true);
												$("#btnAsignar").prop("disabled", false);
												$("#btnExportar").prop("disabled", false);
												
												if (dataFinal.length > 0) {
													
													$('#table').dataTable().fnClearTable();
													$('#table').dataTable().fnAddData(dataFinal);
													$('#table').dataTable().fnDraw();
													
												} else {
													
													$('#table').dataTable().fnClearTable();
													$('#table').dataTable().fnDraw();
												}
												

											} else {
												
												$('#txtNumRuc').val(contribuyente.numRuc);
												$('#txtDesRazonSocial').val(contribuyente.desRazonSocial);
												
												$('#table').dataTable().fnClearTable();
												$('#table').dataTable().fnDraw();
												$("#btnExportar").prop("disabled", true);
													//no hay informacion para mostrar del listado de responsables asignados
												$("#btnAsignar").prop("disabled", false);
												mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion07);
												return
										}


											
										} else {
											
											expedienteVirtual = null;
											
											$('#txtNumRuc').val("");
											$('#txtDesRazonSocial').val("");
											
											$('#table').dataTable().fnClearTable();
											$('#table').dataTable().fnDraw();
											$("#btnAsignar").prop("disabled", true);
											$("#btnExportar").prop("disabled", true);
											
											//no se puede tener informacion del contribuyente
											mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion05);
											return
										}

										
										} else {
								
									$('#txtNumRuc').val("");
									$('#txtDesRazonSocial').val("");
									$('#table').dataTable().fnClearTable();
									$('#table').dataTable().fnDraw();
									$("#btnAsignar").prop("disabled", true);
									$("#btnExportar").prop("disabled", true);
									//El N° Expediente ingresado no corresponde a la dependencia del usuario conectado 
									var mensajeExcepcion = excepciones.excepcion06;
									mostrarDlgInfo(titulos.tituloDefecto, mensajeExcepcion);
									return;
								}
										
										} else {
								
									$('#txtNumRuc').val("");
									$('#txtDesRazonSocial').val("");
									$('#table').dataTable().fnClearTable();
									$('#table').dataTable().fnDraw();
									$("#btnAsignar").prop("disabled", true);
									$("#btnExportar").prop("disabled", true);
									//la dependencia del expediente virtual asociada no pertenece a ninguna unidad organizacional
									var mensajeExcepcion = excepciones.excepcion11;
									mostrarDlgInfo(titulos.tituloDefecto, mensajeExcepcion);
									return;
								}
										
									} else {
									
									
									
									$('#txtNumRuc').val("");
									$('#txtDesRazonSocial').val("");
									
									$('#table').dataTable().fnClearTable();
									$('#table').dataTable().fnDraw();
									$("#btnAsignar").prop("disabled", true);
									$("#btnExportar").prop("disabled", true);
									//el numero de expediente no se encuentra en estado abierto
									mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion12);
									return
								}
									
									
								} else {
									
									expedienteVirtual = null;
									
									$('#txtNumRuc').val("");
									$('#txtDesRazonSocial').val("");
									
									$('#table').dataTable().fnClearTable();
									$('#table').dataTable().fnDraw();
									$("#btnAsignar").prop("disabled", true);
									$("#btnExportar").prop("disabled", true);
									//no se encuentra el numero de expediente ingresado. por favor verifique
									mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion03);
									return
								}
	
								
							},
							error : function(response) {
								
								mostrarPagError();
								
							}
							
						});
						
					}
					
				}
				
			}
			
			
			
			
			function consultar(numExpediente) {
				
				var numExpediente = numExpediente;
								
				
						
						var dataEnvio = new Object();
						
						dataEnvio.numExpediente = numExpediente;
						dataEnvio.codDependenciaBase = codDependenciaBase;//[oachahuic][PAS20165E210400270]
						
						$.ajax({
							
							url : '${pageContext.request.contextPath}/accesoExpe.htm?action=cargarAccesoExpediente&indCarga=1',
							type : 'POST',
							async : true,
							dataType : "json",
							data : JSON.stringify(dataEnvio),
							contentType : "application/json",
							mimeType : "application/json",
							//timeout : 5000,
							success : function(response) {
								
								expedienteVirtual = response.expedienteVirtual;
								var contribuyente = response.contribuyente;
								var indListaUniOrgaDependVacio = response.indListaUniOrgaDependVacio;
								var dependencia = response.dependencia
								dataFinal = response.responsablesAsignados;
								
								
								if (expedienteVirtual != null) {								

										if (expedienteVirtual.codEstado == '01'){
										
										if(indListaUniOrgaDependVacio != "-1"){	
										
										if(dependencia != "-1"){	

										if (contribuyente != null) {
										
										
										
										if (dataFinal != null) {
												
												$('#txtNumeroExpediente').val(numExpediente);
												$('#txtNumRuc').val(contribuyente.numRuc);
												$('#txtDesRazonSocial').val(contribuyente.desRazonSocial);
												
												$("#txtNumeroExpediente").attr('readonly', true);
												
												$("#btnBuscar").prop("disabled", true);
												$("#btnAsignar").prop("disabled", false);
												$("#btnExportar").prop("disabled", false);
												
												if (dataFinal.length > 0) {
													
													$('#table').dataTable().fnClearTable();
													$('#table').dataTable().fnAddData(dataFinal);
													$('#table').dataTable().fnDraw();
													
												} else {
													
													$('#table').dataTable().fnClearTable();
													$('#table').dataTable().fnDraw();
												}
												

											} else {
												
												$('#txtNumeroExpediente').val(numExpediente);
												$('#txtNumRuc').val(contribuyente.numRuc);
												$('#txtDesRazonSocial').val(contribuyente.desRazonSocial);
												
												$('#table').dataTable().fnClearTable();
												$('#table').dataTable().fnDraw();
												$("#btnExportar").prop("disabled", true);
													//no hay informacion para mostrar del listado de responsables asignados
												$("#btnAsignar").prop("disabled", false);
												mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion07);
												return
										}


											
										} else {
											
											expedienteVirtual = null;
											
											$('#txtNumRuc').val("");
											$('#txtDesRazonSocial').val("");
											
											$('#table').dataTable().fnClearTable();
											$('#table').dataTable().fnDraw();
											$("#btnAsignar").prop("disabled", true);
											$("#btnExportar").prop("disabled", true);
											
											//no se puede tener informacion del contribuyente
											mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion05);
											return
										}

										
										} else {
								
									$('#txtNumRuc').val("");
									$('#txtDesRazonSocial').val("");
									$('#table').dataTable().fnClearTable();
									$('#table').dataTable().fnDraw();
									$("#btnAsignar").prop("disabled", true);
									$("#btnExportar").prop("disabled", true);
									//El N° Expediente ingresado no corresponde a la dependencia del usuario conectado 
									var mensajeExcepcion = excepciones.excepcion06;
									mostrarDlgInfo(titulos.tituloDefecto, mensajeExcepcion);
									return;
								}
										
										} else {
								
									$('#txtNumRuc').val("");
									$('#txtDesRazonSocial').val("");
									$('#table').dataTable().fnClearTable();
									$('#table').dataTable().fnDraw();
									$("#btnAsignar").prop("disabled", true);
									$("#btnExportar").prop("disabled", true);
									//la dependencia del expediente virtual asociada no pertenece a ninguna unidad organizacional
									var mensajeExcepcion = excepciones.excepcion11;
									mostrarDlgInfo(titulos.tituloDefecto, mensajeExcepcion);
									return;
								}
										
									} else {
									
									
									
									$('#txtNumRuc').val("");
									$('#txtDesRazonSocial').val("");
									
									$('#table').dataTable().fnClearTable();
									$('#table').dataTable().fnDraw();
									$("#btnAsignar").prop("disabled", true);
									$("#btnExportar").prop("disabled", true);
									//el numero de expediente no se encuentra en estado abierto
									mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion12);
									return
								}
									
									
								} else {
									
									expedienteVirtual = null;
									
									$('#txtNumRuc').val("");
									$('#txtDesRazonSocial').val("");
									
									$('#table').dataTable().fnClearTable();
									$('#table').dataTable().fnDraw();
									$("#btnAsignar").prop("disabled", true);
									$("#btnExportar").prop("disabled", true);
									//no se encuentra el numero de expediente ingresado. por favor verifique
									mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion03);
									return
								}
	
								
							},
							error : function(response) {
								
								mostrarPagError();
								
							}
							
						});

			}
			
			

		function getPaginaTbl(idTabla){
			return $('#'+idTabla).DataTable().page.info().page;
		}
				
		function setPagintaTbl(idTabla, idxPagina){
			$('#'+idTabla).DataTable().page(idxPagina).draw(false);
		}
		

		function permitirNumeros(e){
				
				var keynum = window.event ? window.event.keyCode : e.which;
				
				if ((keynum == 8)) {
					
					return true;
					
				}
				
				return /\d/.test(String.fromCharCode(keynum));
				
			}
		
		
		function mostrarPagError() {
				
			var formURL = '${pageContext.request.contextPath}/error.htm?action=cargarPaginaError';
			document.forms.frmPrincipal.action = formURL;
			document.forms.frmPrincipal.method = "post";
			document.forms.frmPrincipal.submit();
			
		}
		
		
		function agregar(){
			 $('html').block({message: '<h1>Procesando</h1>'});
			 window.location.href = '${pageContext.request.contextPath}/accesoExpe.htm?action=agregarAccesoExpVirtual&aux=' + $('#txtNumeroExpediente').val(); +'&'+$('#formDocInterno').serialize();
		}
		
		
		function exportar(){
			var dataExp = $('#table').dataTable().fnGetData();
			if(dataExp.length > 0){
				
				var listaCadena = JSON.stringify(dataExp);
				var formURL = 'accesoExpe.htm?action=exportarExcel';
				document.forms.formPrincipal.action = formURL;
				document.forms.formPrincipal.method = "POST";
				$('#campoHiddenExp').val('');
				$('#campoHiddenExp').val(listaCadena);
				$('html').block({message: '<h1>Procesando</h1>'});
				document.forms.formPrincipal.submit();
				
				}else{
				mostrarDlgInfo(titulos.tituloDefecto, "No hay registros a exportar");
			}
				$('html').unblock();
			
		}
		
		function limpiar(){

			$('#txtNumeroExpediente').val("");
			$("#btnBuscar").attr('disabled', false);
			$('#txtNumRuc').val("");
			$("#txtNumeroExpediente").removeAttr("readonly");
        	$("#txtNumeroExpediente").removeClass("readOnly");	
			$('#txtDesRazonSocial').val("");
			$('#table').dataTable().fnClearTable();
			$("#btnAsignar").prop("disabled", true);
			$("#btnExportar").prop("disabled", true);
		
		}
		
		
		function confirmarEliminar(fila) { 

			titulo = "El registro Se eliminar&aacute;";
			msj = "&iquest;Esta seguro de Eliminar el Acceso Seleccionado?";
			botones = [];
			var botonSi = $("<input/>").attr(
				{
					value: "Si", 
					type: "button", 
					"class": "btn dlgButton btn-primary", 
					"data-dismiss" : "modal", 
					onclick : "eliminar('" + fila + "')"
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
	
		
		function eliminar(fila){
		
				var dataEnvio = new Object();
				dataEnvio.numSoli = fila;
				var numExpediente = $('#txtNumeroExpediente').val();
				dataEnvio.numExpediente = numExpediente;
				
				$.ajax({							
					url : '${pageContext.request.contextPath}/accesoExpe.htm?action=eliminarAccesoExpediente',
					type : 'POST',
					async : true,
					dataType : "json",
					data : JSON.stringify(dataEnvio),
					contentType : "application/json",
					mimeType : "application/json",
					//timeout : 5000,
					success : function(response) { 
                        var msjEli = response.eliminarAcceso;
                        dataFinal = response.responsablesAsignados;
                        
                        if (msjEli == "1"){
                            mostrarDlgInfo(titulos.tituloDefecto, "Registro eliminado correctamente");          
                        }
                        
                        $('#table').dataTable().fnClearTable();
						$('#table').dataTable().fnAddData(dataFinal);
						$('#table').dataTable().fnDraw();
                        
                    },
					

				});
				
				
				
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
		
		
		

		</script>
		
	</body>
	
</html>