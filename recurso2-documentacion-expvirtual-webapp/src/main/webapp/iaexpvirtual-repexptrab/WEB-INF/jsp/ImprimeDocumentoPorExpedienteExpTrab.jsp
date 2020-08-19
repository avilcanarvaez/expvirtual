<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="es">

	<head>
		
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name = "viewport" content = "initial-scale = 1.0, user-scalable = no,  width=device-width">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>IMPRESI&Oacute;N DE DOCUMENTOS DEL EXPEDIENTES DE TRABAJO</title>
		
		<!-- Bootstrap -->
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/media/css/jquery.dataTables.css">
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Responsive/css/dataTables.responsive.css">
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
		  margin: 10px 10px !important;
		  border: 1px solid black !important;
		  border-radius: 4px;
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
		
		.padded-row{
			padding: 10px !important;
		}
		
		.panelBorde {
			margin-bottom: 10px;
			background-color: #fff;
			border: 1px solid transparent;
			border-radius: 4px;
			-webkit-box-shadow: 0 1px 1px rgba(0,0,0,.05);
			box-shadow: 0 1px 1px rgba(0,0,0,.05);
		}
		
		.marginedSub {
		  margin: 10px 10px !important;
		  border-radius: 4px;
		}
		
		div.border-line {
		  border: 1px #337ab7;
		  border-style: solid;
		  padding:6px !important;
		}
		
		div.border-lineSub {
		  border: 1px #337ab7;
		  border-style: solid;
		  padding:15px !important;
		}
		
		div.border-padding {
		  padding:15px !important;
		  margin-top: -15px;
		}
		
		.alinearCentrado {
				vertical-align: middle !important;
			}
		</style>
		
	</head>
	
	<body>
		<div class="container">
			<div class="marginedDiv">
				<div class="padded-row">
					<div class="panel panel-primary border-line">
						<div class="panel-heading align="center">
							<h3 class="panel-title" align="center">IMPRESI&Oacute;N DE DOCUMENTOS DEL EXPEDIENTES DE TRABAJO</h3>
						</div>
					</div>
					<div class="panel panel-primary border-lineSub">
						<div class="row content-box">&nbsp;</div>
						<div class="row content-box">
							<div class="col-md-2"><Strong>Proceso:</Strong></div>
								<div id="pnlSelProceso" class="col-md-5">
								<select id="selProceso" name="selProceso" class="cboProceso form-control" onchange="cargarTiposExpedientes()">
									<option value="">Seleccione</option>
								</select> 
							</div>
							<div class="col-md-2"></div>
							<div class="col-md-1"><Strong>Fecha:</Strong></div>
							<div id="pnlFecVista" class="col-md-2" align="right">
								<input id="fecVista" align="right" name="fecVista" type="text" class="form-control" disabled="disabled"/>
							</div>
						</div>
						<div class="row content-box">&nbsp;</div>
						<div class="row content-box">
							<div class="col-md-2"><Strong>Tipo de Expediente:</Strong></div>
								<div id="pnlSelTipoExpe" class="col-md-5">
								<select id="selTipoExpediente" name="selTipoExpediente" class="cboProceso form-control">
									<option value="">Seleccione</option>
								</select> 
							</div>
						</div>
						<div class="row content-box">&nbsp;</div>
						<div class="row content-box">
							<div class="col-md-2"><Strong>N&deg; Pedido:</Strong></div>
								<div class="col-md-3">
									<input name="numPedido" id="txtNumPedido" type="text" class="form-control tamanoMaximo" value='${codPedido}' disabled></input>	
								</div>	
						</div>	
						<div class="row content-box">&nbsp;</div>
						<div class="row content-box">
							<div class="col-md-2"><Strong>N&deg; Expediente:</Strong></div>
								<div class="col-md-3">
									<input name="numExpedienteVirtual" id="numExpedienteVirtual" type="text" class="form-control tamanoMaximo"  ></input>	
								</div>	
						</div>													
						<div class="row content-box">&nbsp;</div>
						<div class="panel panel-primary border-lineSub">
							<div class="row content-box">
								<div class="col-md-12">
									<label>Lista de Tipo de Documentos Generados por</label>
								</div>
							</div>
							<div class="row content-box border-padding">
								<form id="frmTable">
								<table id="tablaExpedientesPedido" class="table display" cellspacing="0" style="width: 100%;"><!--table table-striped table-bordered-->
									<thead>
										<tr class="active">
											<th width="3%" class="text-center">N&deg;</th>
											<th width="5%" class="text-center">Sel</th>
											<th width="35%" class="text-center">Tipo de Documento</th>
											<th width="30%" class="text-center">Archivo</th>
											<th width="15%" class="text-center">Fecha de Generaci&oacute;n</th>
											<th width="12%" class="text-center">Estado</th>
										</tr>
									</thead>
								</table>
								</form>
							</div>
							<div class="row content-box">&nbsp;</div>
							<div class="form-group">
								<div class="col-md-12" align="right">
									<button type="button" class="btn btn-primary" onclick="darBajaPlantilla()" style="margin-right: 5px;">Imprimir Documentos</button>
									<button type="button" class="btn btn-primary" onclick="modificarPlantilla()" style="margin-right: 5px;" >Regresar</button>
								</div>						
							</div>
						</div>
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
		<script src="../a/js/libs/jquery/1.11.2/jquery.min.js"></script>
		<script src="../a/js/libs/bootstrap/3.3.2/js/bootstrap.min.js"></script>
		<script src="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/media/js/jquery.dataTables.min.js" type="text/javascript" ></script>    
		<script src="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Responsive/js/dataTables.responsive.js" type="text/javascript" ></script>
		<script src="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/TableTools/js/dataTables.tableTools.min.js" type="text/javascript" ></script>
		<script src="../a/js/js.js" type="text/javascript" ></script>
		
		<script type="text/javascript" src="../a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/js/moment-with-locales.js"></script>
		<script type="text/javascript" src="../a/js/libs/bootstrap/3.3.2/plugins/bootstrap-datetimepicker-3.1.3/js/bootstrap-datetimepicker.min.js"></script>
		<script src="../a/js/bootstrap/3.2.0/js/jquery.blockUI.js" type="text/javascript"></script>
		
		<script>
		
		var fecVista = ${fecVista};
		var titulos = ${titulos};
		var excepciones = ${excepciones};		
		var listadoProcesos = ${listadoProcesos};
		var listadoTiposExpendientes = ${listadoTiposExpendientes};
		var codProceso = ${codProceso};
		var codTipoExpediente = ${codTipoExpediente};	
		var codPedido = ${codPedido};
		var listaDocumentos = ${listaDocumentos};
		var numExpedienteVirtual = ${numExpedienteVirtual};
		
		$(function () {
			
			$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
			construirTablaExpediente(listaDocumentos);			
						
			$.ajaxSetup({ scriptCharset: "UTF-8" , contentType: "application/json; charset=utf-8"});
				jQuery.support.cors = true;
				
				$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);	
			
				var $element = $('#selProceso');
				
				$.each(listadoProcesos, function(i, dato) {
				
					var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
					$element.append($option);				
				});
				
				$('#selProceso').val(codProceso);
				$('#selProceso').prop('disabled', true);
				$('#fecVista').val(fecVista);
		
				var $element = $('#selTipoExpediente');
					
				$.each(listadoTiposExpendientes, function(i, dato) {
					
					var $option = $("<option/>").attr("value", dato.codTipoExpediente).text(dato.desTipoExpediente);
					$element.append($option);						
				});
				$('#selTipoExpediente').val(codTipoExpediente);
				$('#selTipoExpediente').prop('disabled', true);		
		});
		
		function construirTablaExpediente(dataGrilla) {
			
			$('#tablaExpedientesPedido').DataTable( {
				"language": {
					"url"		:	"../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/plug-ins/1.10.7/i18n/Spanish.json"
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
				columns : [
					{data :	"numOrden",sClass: 'center alinearCentrado',"defaultContent":""},
					{data :	"codTipoDocumento",
						render : function(data, row){
							 return jQuery('<input>')
								 	.css({"marginLeft": "8px", "width": "18px", "height": "18px",})
								 	.attr({type:'checkbox', name:'seleccion', value:data})
				                    .wrap('<div></div>')
				                    .parent()
				                    .html();}	
					},
					{data :	"tipoDocumento", sClass: 'center alinearCentrado',"defaultContent":"" },
					{data :	"codTipoDocumento",sClass: 'center alinearCentrado',"defaultContent":"" ,render : function(data, row){
						 var aux = data;
						 return $('<a>')
						 	.css('text-decoration','underline')
			                .attr({href : '${pageContext.request.contextPath}/regDocInterno.htm?action=seleccionaNroExpOrigen&aux=' + aux, onclick : "$('html').block({message: '<h1>Procesando</h1>'});" })
			                .text("Ver Documento")
			                .wrap('<div></div>')
			                .parent()
			                .html();								
			        }},					
					{data :	"fecRegistro",sClass: 'center alinearCentrado',"defaultContent":"" },
					{data :	"desEstado",sClass: 'center alinearCentrado',"defaultContent":"" }
				],
				
				tableTools: {
			        "sSwfPath": "../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/TableTools/swf/copy_csv_xls.swf"
			    },
			    data				:  dataGrilla,
				ordering			:	true,
				bScrollAutoCss		:	true,
				bStateSave			:	false,
				bAutoWidth			:	false,
				bScrollCollapse		:	false,
				searching			:	false,
				paging				:	true,
				pagingType			:   "full_numbers",
				iDisplayLength		:	15,
				//responsive			:	true,
				bLengthChange		: 	false,
				fnDrawCallback		:	function(oSettings) {
				$('#tablaExpedientesPedido_paginate').addClass('div100');
					if (oSettings.fnRecordsTotal() == 0) {
						$('#tablaExpedientesPedido_paginate').addClass('hiddenDiv'); //el footer de paginacion siempre tiene como ID "miTablaID_paginate"
																			//no se oculta automaticamente
																//cuando hay 0 registros, por eso se oculta anadiendo el class .hiddenDiv
			        }
					else {
						$('#tablaExpedientesPedido_paginate').removeClass('hiddenDiv');
						
					}
			    }
				        
		});
			
		}
			
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

		}
		
		function mostrarPagError() {
			
			var formURL = '${pageContext.request.contextPath}/error.htm?action=cargarPaginaError';
			document.forms.frmPrincipal.action = formURL;
			document.forms.frmPrincipal.method = "post";
			document.forms.frmPrincipal.submit();
			
		}
		</script>
		
	</body>
	
</html>	