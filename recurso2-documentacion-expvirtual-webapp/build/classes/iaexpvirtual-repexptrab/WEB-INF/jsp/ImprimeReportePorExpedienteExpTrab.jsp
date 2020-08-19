<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="es">

	<head>
		
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name = "viewport" content = "initial-scale = 1.0, user-scalable = no,  width=device-width">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>IMPRESI&Oacute;N DE EXPEDIENTES DE TRABAJO</title>
		
		<!-- Bootstrap -->
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/media/css/jquery.dataTables.css">
		<link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/Responsive/css/dataTables.responsive.css">
		<!--link rel="stylesheet" type="text/css" href="../a/js/libs/bootstrap/3.3.2/plugins/font-awesome/4.4.0/css/font-awesome.min.css">-->
		
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
	  <script src="../a/js/bootstrap/3.2.0/js/jquery.blockUI.js" type="text/javascript"></script>
			
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
			padding-top : 25px !important;			
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
		.panel-scroll {
		max-height: auto;
		min-height: 0px;
		overflow-y: auto;
		overflow-x: auto;
		}	
		</style>
		
	</head>
	
	<body>
		<div id="container" class="container" style="width: 95%">
			<div>
				
					<div class="panel panel-primary border-line">
						<div class="panel-heading align="center">
							<h3 class="panel-title" align="center">IMPRESI&Oacute;N DE EXPEDIENTES DE TRABAJO</h3>
						</div>
					</div>
					<div class="panel panel-primary border-lineSub">
						<div class="row content-box">&nbsp;</div>
						<div class="row content-box">
							<div class="col-md-2"><Strong>Proceso:</Strong></div>
								<div id="pnlSelProceso" class="col-md-5">
								<select id="selProceso" name="selProceso" class="cboProceso form-control"  disabled>
									<option value=""></option>
								</select> 
							</div>
							<div class="col-md-2"></div>
							<div class="col-md-1"><Strong>Fecha:</Strong></div>
							<div id="pnlFecVista" class="col-md-2" align="right">
								<input id="fecVista" align="right" name="fecVista" type="text" class="form-control text-center" disabled="disabled"/>
							</div>
						</div>
						<div class="row content-box">&nbsp;</div>
						<div class="row content-box">
							<div class="col-md-2"><Strong>Tipo de Expediente:</Strong></div>
								<div id="pnlSelTipoExpe" class="col-md-5">
								<select id="selTipoExpediente" name="selTipoExpediente" class="cboProceso form-control" disabled>
									<option value=""></option>
								</select> 
							</div>
						</div>
						<div class="row content-box">&nbsp;</div>
						<div class="row content-box">
							<div class="col-md-2"><Strong>N&deg; Pedido:</Strong></div>
								<div class="col-md-3">
									<input name="numPedido" id="txtNumPedido" type="text" class="form-control tamanoMaximo" value='${numeroPedido}' disabled></input>	
								</div>	
						</div>													
						<div class="row content-box">&nbsp;</div>
						<div class="panel panel-primary border-lineSub">
							<div class="row content-box">
								<div class="col-md-12">
									<label>Lista de Expedientes Generados por Pedido</label>
								</div>
							</div>
							<div class="row content-box border-padding">
								<form id="frmTable">
									<div class="tab-content">
										<div id="accionesFiscalizacion" class="tab-pane fade in active">
											<div class="border-line panel-scroll">		
											<table id="tablaExpedientes" class="table display nowrap" cellspacing="0" style="width: 100%;"><!--table table-striped table-bordered-->
												<thead>
													<tr class="active">
														<th width="3%" class="text-center">N&deg;</th>
														<th width="5%" class="text-center">Sel</th>
														<th width="8%" class="text-center">N&deg; RUC</th>
														<th width="14%" class="text-center">Responsable</th>
														<th width="20%" class="text-center">Archivo</th>
														<th width="15%" class="text-center">Fecha de Generaci&oacute;n</th>
														<th width="15%" class="text-center">Estado</th>
														<th width="15%" class="text-center">Lista de Errores</th>
													</tr>
												</thead>
											</table>
											</div>
										</div>
									</div>	
								</form>
							</div>
							</div>
							<div class="row content-box">&nbsp;</div>
							<div class="form-group">
								<div class="col-md-12" align="right">
									<!-- <button type="button" class="btn btn-primary" style="margin-right: 5px;" id='imprimir' onclick="imprimirExpedienteTrabajo()">Imprimir Expediente de Trabajo</button> -->							
									<button type="button" class="btn btn-primary" style="margin-right: 5px;" id='regresarAnterior' onclick="volverPagina()" >Regresar</button>
								</div>											
							</div>
					</div>
			</div>
		</div>
		<iframe   id="iPrint" name="iPrint" width="800" height="400" style="display:none" >  		
		</iframe>			
		<div name = "contiene" id="contiene"></div>
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
						<div id="dlgMsj" class="modal-body text-left">TEXTO</div>
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
		var listaExpedientesPorPedidos = ${listaExpedientesPorPedidos};
		var listadoProcesos = ${listadoProcesos};
		var listadoTiposExpendientes = ${listadoTiposExpendientes};
		var codProceso = ${codProceso};
		var codTipoExpediente = ${codTipoExpediente};	
		var numeroPedido ="";	
		var numRuc = "";
		var numReportePedido="";
		var indexExpedientes="";
		$(function () {
			
			
			construirTablaExpediente(listaExpedientesPorPedidos);
			$('#imprimir').attr('disabled', true);	
			$('#tablaExpedientes tbody').on('mouseenter', 'tr', function() {
				$(this).addClass("selected");
			});	
			$('#tablaExpedientes tbody').on( 'mouseleave', 'tr', function () {
				 $(this).removeClass("selected");
				 //$('#tablaExpedientes').dataTable().fnDraw();
			} );
			$.ajaxSetup({ cache: false });
			habilitarRadio();			
			// Validaciones
			//Validaciones del Formulario Busqueda
			//$.ajaxSetup({ scriptCharset: "UTF-8" , contentType: "application/json; charset=utf-8"});
			jQuery.support.cors = true;
			$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
			
			var table = $('#tablaExpedientes').DataTable();
			 
			$('#tablaExpedientes tbody').on( 'mouseenter', 'tr', function () {
				indexExpedientes = table.row( this ).index();
		
			} );
			
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
			$('#imprimir').attr('disabled', true);	
		})
				
		function construirTablaExpediente(dataGrilla) {
						
    	$('#tablaExpedientes').DataTable( {
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
					{data :	"numReportePedido","defaultContent":"",
						render : function(data, row){
								
									return jQuery('<input>')
								 	.css({"marginLeft": "8px", "width": "18px", "height": "18px",})
								 	.attr({type:'radio', name:'seleccion', value:row.numRuc, onclick: 'guardarDatos()', id:data})
				                    .wrap('<div></div>')
				                    .parent()
				                    .html();
															 
						}, sClass: 'center alinearCentrado',"defaultContent":"" 	
					},					
					{data :	"numRuc",sClass: 'center alinearCentrado',"defaultContent":"" ,/*render : function(data,type, row){
						 var aux = data;
						 return $('<a>')
						 	.css('text-decoration','underline')
			                .attr({href : '${pageContext.request.contextPath}/impReporteExpTrab.htm?action=cargarDocumentosExpTrab&numeroPedido='+row.numeroPedido+'&numRuc='+row.numRuc, onclick : "$('html').block({message: '<h1>Procesando</h1>'});" })
			                .text(data)
			                .wrap('<div></div>')
			                .parent()
			                .html();								
			        }*/},					
					{data :	"nombrePersonaRegistro",sClass: 'center alinearCentrado',"defaultContent":"" },
					{data :	"numRuc",sClass: 'center alinearCentrado',"defaultContent":"" ,render : function(data,type, row){						 
						 var uri = ''; 
						 var link = '';
						 
						 if(row.codEstadoReporte=="02" || row.codEstadoReporte=="03") {
				             uri = '${pageContext.request.contextPath}/impReporteExpTrab.htm?action=previsualizacionExpediente&numeroPedido=' + row.numeroPedido + '&numReportePedido=' + row.numReportePedido;   
				             link = "<div><a target='_blank' href='" + uri + "'>Ver Expediente</div>";
						 } else {
							 link = '';
						 }
			                
			             return link;   
					}},
					{data :	"fechaGenera",sClass: 'center alinearCentrado',"defaultContent":"" },
					{data :	"desEstadoReporte",sClass: 'center alinearCentrado',"defaultContent":"" },
					//eaguilar: columna ver errores
					{data : "lstErrors",
						sClass: 'center alinearCentrado',
						defaultContent: "",
						render : function(data, type, row){
							if(data!='' && row.codEstadoReporte=="03"){ //eaguilar 27 JUN, solo muestrar lista cuando es GENERADO PARCIALMENTE
								var mensaje = '<ul>'; 
								$.each(data, function(k, sError) {
							 		mensaje += '<li>'+sError+'</li>';
								});
								mensaje += '</ul>';
								return $('<a>')
									.css('text-decoration','underline')
					                .attr({href : 'javascript:mostrarDlgInfo("Impresi&oacute;n de Expedientes de Trabajo","' + mensaje + '");'})
					                .text("Ver Lista de Errores")
					                .wrap('<div></div>')
					                .parent()
					                .html();
							}
			        	}
					}
				],
				
				tableTools: {
			        "sSwfPath": "../a/js/libs/bootstrap/3.3.2/plugins/datatables-1.10.7/extensions/TableTools/swf/copy_csv_xls.swf"
			    },
			    data: dataGrilla,
				autoWidth:   false,
				ordering: true,
				searching: false,
				paging: true,
				bScrollAutoCss: true,
				bStateSave: false,
				bAutoWidth: false,
				bScrollCollapse: true,
				pagingType: "full_numbers",
				iDisplayLength: 5,
				//responsive: true,
				bLengthChange: false,
				fnDrawCallback		:	function(oSettings) {
				var a = $('#tablaExpedientes').width()
				$("#tablaExpedientes_wrapper").css("min-width", a);	
				
				$('#tablaExpedientes_paginate').addClass('div100');
					habilitarRadio();
					if (oSettings.fnRecordsTotal() == 0) {
						$('#tablaExpedientes_paginate').addClass('hiddenDiv'); //el footer de paginacion siempre tiene como ID "miTablaID_paginate"
																			//no se oculta automaticamente
																//cuando hay 0 registros, por eso se oculta anadiendo el class .hiddenDiv
			        }
					else {
						$('#tablaExpedientes_paginate').removeClass('hiddenDiv');
						
					}
			    }
				        
		});
    	}
		
		function habilitarRadio(){
			var dataJson = $('#tablaExpedientes').DataTable().rows().data();	
			
			for (var i = 0; i < dataJson.length; i++) {				
				if(dataJson[i].codEstadoReporte!="02" && dataJson[i].codEstadoReporte!="03"){
					if($('#'+dataJson[i].numReportePedido).length) {
						document.getElementById(dataJson[i].numReportePedido).style.display='none';
					}
				}
			}
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
		
		function volverPagina(){
			$('html').block({message: '<h1>Regresando...</h1>'});
			var  url = '${pageContext.request.contextPath}/impReporteExpTrab.htm?action=mostrarView&pageView=ImprimeReporteGeneradoExpTrab';
			$(location).prop( 'href', url);
		}
		function mostrarPagError() {
			
			var formURL = '${pageContext.request.contextPath}/error.htm?action=cargarPaginaError';
			document.forms.frmTable.action = formURL;
			document.forms.frmTable.method = "post";
			document.forms.frmTable.submit();			
		}		
		
		function guardarDatos(){			
			var dataJson = $('#tablaExpedientes').DataTable().rows().data();		
				numRuc = dataJson[indexExpedientes].numRuc;
				numeroPedido = dataJson[indexExpedientes].numeroPedido;	
				numReportePedido=dataJson[indexExpedientes].numReportePedido;
			$('#imprimir').attr('disabled', false);		
		}		
		
		function imprimirExpedienteTrabajo(){
			$('#iPrint').attr('src', "/ol-ti-iaexpvirtual-repexptrab/impReporteExpTrab.htm?action=imprimirDocumentosPorExpNumPedido&numeroPedido="+numeroPedido+"&numReportePedido="+numReportePedido);
			$( '#iPrint' ).attr( 'src', function ( i, val ) { return val; });
			
		} 
				
		</script>
	</body>
	
</html>