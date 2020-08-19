
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
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
	var listaDependencias = ${listDependencias};
	var listaTipoBusquedaFecha = ${listadoTipoFecha};
	var listaTipoBusquedaExpediente = ${listadoNumeroTipoExpediente};
	var dependencia = '${dependencia}';
	var excepciones = ${excepciones};
	var titulos =  ${titulos};
	var numExpedienteConsultar="";
	var retorno=false;
	var msjErrorRuc="";
	var llimpiarTabla = true;
	var tblPage = "${beanParametrosConsultaReq.tblPage}";  //anadir
	//Inicio nchavez 15/06/2016
	var hoverBuscar=false;
	//Fin nchavez 15/06/2016
	var flagBusquedaBoton = false;
	
    $(function () {
    	
		
		$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
		construirTablaExpediente( [] );
		var table = $('#tblExpedientes').DataTable();
		$('#tblExpedientes').on( 'page.dt', function () {
			var info = table.page.info();
			$('input').filter(':radio').prop('checked',false);
		} );
		$('#tblExpedientes tbody').on('mouseenter', 'tr', function() {
			$(this).addClass("selected");
		});	
		$('#tblExpedientes tbody').on( 'mouseleave', 'tr', function () {
			 $(this).removeClass("selected");
			 //$('#tblExpedientes').dataTable().fnDraw();
		} );
		inicializarProcesos();
		
		$('#divFechaDesde').datetimepicker({
            format: 'DD/MM/YYYY',
			useCurrent: false,
			language: 'Es',
			autoClose: true,
			forceParse: true,
			pickTime: false
        });
		
		$('#divFechaHasta').datetimepicker({
            format: 'DD/MM/YYYY',
			useCurrent: false,
			language: 'Es',
			autoClose: true,
			forceParse: true,
			pickTime: false
        });
		
		$("#divFechaDesde").on("dp.hide", function (e) {
            $('#divFechaHasta').data("DateTimePicker").setMinDate(e.date);
            //Inicio nchavez 15/06/2016
            if($('#txtfechaDesde').val() != ""){
				habilitarValidacionFecha(true);
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecDesde');
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecHasta');
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'codTipBusquedaFecha');
				resetearFormularioConEsp(event);
		   }
		   //Fin nchavez 15/06/2016
		});
		
		$("#divFechaHasta").on("dp.hide", function (e) {
			$('#divFechaDesde').data("DateTimePicker").setMaxDate(e.date);
			//Inicio nchavez 15/06/2016
			if($('#txtfechaHasta').val() != ""){
				habilitarValidacionFecha(true);
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecDesde');
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecHasta');
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'codTipBusquedaFecha');
				resetearFormularioConEsp(event);
			}
			//Fin nchavez 15/06/2016
		});
		
		$("#selTipoBusquedaFecha").on("dp.change", function (e) {
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecDesde');
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecHasta');
			resetearFormularioConEsp(event);
		});
		
		$( "#txtNumeroExpediente" ).blur(function() {
			retorno=true;
			$('#frmFiltroBusquedaEspecifica').bootstrapValidator('revalidateField', 'numExp');
		});
		$( "#txtRuc" ).blur(function() {
			retorno=true;
			$('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'numRuc');
			//Inicio nchavez 15/06/2016
			if(hoverBuscar){
				//Inicio jquispe 16/06/2016
				revalidarFormularioBusqueda();
				//Fin jquispe 16/06/2016
			}
			//Fin nchavez 15/06/2016
		});

		//Inicio nchavez 15/06/2016
		$('#btnConsultar').mouseover(function(){
			hoverBuscar=true;
// 			console.log(hoverBuscar);
		});

		$('#btnConsultar').mouseout(function(){
			hoverBuscar=false;
// 			console.log(hoverBuscar);
		});
		//Fin nchavez 15/06/2016

		makeInputMask( '#txtRuc', "(9){1,11}", 11, '' );
			
		/*$("body").on("click", function(){
			if($("#txtfechaDesde").val()==""){
				$('#divFechaDesde').data("DateTimePicker").destroy();
				$('#divFechaDesde').datetimepicker({
		            format: 'DD/MM/YYYY',
					useCurrent: false,
					language: 'es',
					autoClose: true,
					forceParse: true,
					pickTime: false
		        });
			}
			if($("#txtfechaHasta").val()==""){
				$('#divFechaHasta').data("DateTimePicker").destroy();
				$('#divFechaHasta').datetimepicker({
		            format: 'DD/MM/YYYY',
					useCurrent: false,
					language: 'es',
					autoClose: true,
					forceParse: true,
					pickTime: false
		        });
			}
		});*/
		
		// Validaciones
		//Validaciones del Formulario Busqueda
		$('#frmFiltroBusquedaEspecifica').bootstrapValidator({
			excluded: [':disabled'],
			fields: {
				numExp: {
					validators: {
						callback: {
                            message: '',
                            callback: function (value, validator, $field) {
								if(retorno){
									var numExpediente = $('#txtNumeroExpediente').val();
									var tipoExpediente = $('#selTipoBusquedaExpediente').val();
									
									if(tipoExpediente!=""){
										if(numExpediente==""){
											if(tipoExpediente=="1"){
												$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateMessage', 'numExp', 'callback', 'Ingrese N&uacute;mero Expediente Origen.');
												$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateOption', 'numExp', 'callback', 'Ingrese n&uacute;mero Expediente Origen.');
												retorno=false;
												llimpiarTabla=false;
												return false;
											}else{
												$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateMessage', 'numExp', 'callback', 'Ingrese N&uacute;mero Expediente Virtual.');
												$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateOption', 'numExp', 'callback', 'Ingrese n&uacute;mero Expediente Virtual.');
												retorno=false;
												llimpiarTabla=false;
												return false;
											}
										}
										if(tipoExpediente=="1"){
												// nchavez[27/05/2016]
											if(numExpediente.length>17){
												$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateMessage', 'numExp', 'callback', 'Ingrese N&uacute;mero Expediente Origen V&aacute;lido.');
												$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateOption', 'numExp', 'callback', 'Ingrese N&uacute;mero Expediente Origen V&aacute;lido');
												retorno=false;
												llimpiarTabla=false;
												return  false;
												
											}
										}
										
										if(tipoExpediente=="2"){
											if(numExpediente.length!=14){
												$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateMessage', 'numExp', 'callback', 'Ingrese N&uacute;mero Expediente Virtual V&aacute;lido.');
												$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateOption', 'numExp', 'callback', 'Ingrese N&uacute;mero Expediente Virtual V&aacute;lido.');
												retorno=false;
												llimpiarTabla=false;
												return  false;
												
											}
										}
									}
									retorno=false;
									llimpiarTabla=false;
									return true;
								
								}
								retorno=false;
								llimpiarTabla=false;
								return true;
                            }
                        }
						
					}
				}
			}
		}).on('success.form.bv', function(e) {
            e.preventDefault();
			buscar();
			llimpiarTabla=true;
    	}).on('error.form.bv', function(e) {
            $('#tblExpedientes').dataTable().fnClearTable();
			$('#tblExpedientes').dataTable().fnDraw();
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
                            message: 'Seleccione Fecha Desde.'
                        },
						date: {
							format: 'DD/MM/YYYY',
							message: 'Formato Fecha Incorrecto.'
						},
						callback: {
							message: '',
							callback:   function (value, validator, $field) {
								var tipoFecha = $('#selTipoBusquedaFecha').val();
								var fechaDesde = document.getElementById("txtfechaDesde").value;
								var fechaHasta = document.getElementById("txtfechaHasta").value;

								if(fechaDesde!="" && fechaHasta!=""){
									if(!validacionFechas()){
										if(tipoFecha=="2"){
											$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateMessage', 'fecDesde', 'callback', excepciones.excepcion03);
											$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateOption', 'fecDesde', 'callback', excepciones.excepcion03);
										}else{
											$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateMessage', 'fecDesde', 'callback', excepciones.excepcion04);
											$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateOption', 'fecDesde', 'callback', excepciones.excepcion04);
										}
										return false;
										llimpiarTabla=false;
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
								var tipoFecha = $('#selTipoBusquedaFecha').val();	
								var fechaDesde = document.getElementById("txtfechaDesde").value;
								var fechaHasta = document.getElementById("txtfechaHasta").value;
								
								if(fechaDesde!="" && fechaHasta!=""){
									if(!validacionFechas()){
										if(tipoFecha==2){
											$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateMessage', 'fecHasta', 'callback', excepciones.excepcion03);
											$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateOption', 'fecHasta', 'callback', excepciones.excepcion03);
										}else{
											$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateMessage', 'fecHasta', 'callback', excepciones.excepcion04);
											$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateOption', 'fecHasta', 'callback', excepciones.excepcion04);
										}
										return false;
										llimpiarTabla=false;
									}
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
									//Inicio nachavez 15/06/2016
									habilitarValidacionConsultaAvanzada(true);	

									if(numRuc!=""){
										if(!valruc(numRuc)){
											$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateMessage', 'numRuc', 'callback', 'Ingrese n&uacute;mero de RUC V&aacute;lido.');
											$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateOption', 'numRuc', 'callback', 'Ingrese n&uacute;mero de RUC V&aacute;lido.');
											retorno = false;
											llimpiarTabla=false;
											return false;
										}else{
											validarContribuyente();
											var razonSocial = $('#txtRazonSocial').val();
											if(razonSocial==""){
												$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateMessage', 'numRuc', 'callback', msjErrorRuc);
												$('#frmFiltroBusquedaAvanzada').bootstrapValidator('updateOption', 'numRuc', 'callback', msjErrorRuc);
												retorno = false;
												llimpiarTabla=false;
												return false;
											}
											//Inicio nchavez 15/06/2016
											else{
											habilitarValidacionConsultaAvanzada(false);	
											}
											//Fin nchavez 15/06/2016
										}
									}
									return true;
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
			llimpiarTabla=true;
    	}).on('error.form.bv', function(e) {
            $('#tblExpedientes').dataTable().fnClearTable();
			$('#tblExpedientes').dataTable().fnDraw();
		});
		cargarFormulario();
		$( window ).off('resize');
	})   
	
	function getPaginaTbl(idTabla){
		return $('#'+idTabla).DataTable().page.info().page;
	}
			
	function setPagintaTbl(idTabla, idxPagina){
		$('#'+idTabla).DataTable().page(idxPagina).draw(false);
	}

	
	function cargarFormulario(){
    	
    	var realizarBusqueda = '${beanParametrosConsultaReq.realizarBusqueda}';
    	
    	if(realizarBusqueda=="1"){
			var numExp = '${beanParametrosConsultaReq.numExp}';
			if(numExp!=""){
				$('#selTipoBusquedaExpediente').val('${beanParametrosConsultaReq.codTipBusquedaExp}');
				$('#txtNumeroExpediente').prop('disabled', false);
				$('#txtNumeroExpediente').val(numExp);
			}else{
				$('#selCodigoProceso').val('${beanParametrosConsultaReq.codProceso}');
				cargarTiposExpedientes();
				$('#selCodigoTipoExpendiente').val('${beanParametrosConsultaReq.codTipexp}');
				$('#selTipoBusquedaFecha').val('${beanParametrosConsultaReq.codTipBusquedaFecha}');
				$('#txtfechaDesde').val('${beanParametrosConsultaReq.fecDesde}');
				$('#txtfechaHasta').val('${beanParametrosConsultaReq.fecHasta}');
				$('#selDependencia').val('${beanParametrosConsultaReq.codDependencia}');
				$('#txtRuc').val('${beanParametrosConsultaReq.numRuc}');
				$('#txtRazonSocial').val('${beanParametrosConsultaReq.razonSolcial}');
			}
			retorno = true;
			realizarBusqueda = true;
			revalidarFormularioRetorno();
    	}
    }
	
	//Inicio jquispe 16/06/2016
	function revalidarFormularioRetorno(){
		flagBusquedaBoton = false;
		revalidarFormulario();
	}
	
	function revalidarFormularioBusqueda(){
		flagBusquedaBoton = true;
		revalidarFormulario();
	}
	//Fin jquispe 16/06/2016
	
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
	
	function inicializarProcesos() {
		// nchavez[27/05/2016]
		makeInputMask( '#txtNumeroExpediente', "(9){1,17}", 17, '' );
		$('#txtfechaDesde').prop('readonly', true);
		$('#txtfechaHasta').prop('readonly', true);
		$('#txtNumeroExpediente').prop('disabled', true);
		$('#btnConsulta').prop('disabled', true);
		//procesos
		var $element = $('#selCodigoProceso');
		$.each(listadoProcesos, function(i, dato) {
			var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
			$element.append($option);
		});
		
		//Dependencias
		var $element = $('#selDependencia');
		$.each(listaDependencias, function(i, dato) {
			var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
			$element.append($option);
		});
		//$('#selDependencia').val(dependencia);
		//[Inicio - jtejada 13/09/2016]
		if($.trim(dependencia) == ''){
			$("#selDependencia").val($("#selDependencia option:first").val());
		}else{
			$('#selDependencia').val(dependencia);
		}
		//[Fin - jtejada 13/09/2016]
		
		//$('#selDependencia').prop('disabled', true);
		//fecha
		var $element = $('#selTipoBusquedaFecha');
		$.each(listaTipoBusquedaFecha, function(i, dato) {
			var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
			$element.append($option);
		});
		
		//expediente
		var $element = $('#selTipoBusquedaExpediente');
		$.each(listaTipoBusquedaExpediente, function(i, dato) {
			var $option = $("<option/>").attr("value", dato.codParametro).text(dato.desParametro);
			$element.append($option);
		});
	}
	
    function limpiar(){
		resetearFormularioConEsp(event);
		//Inicio nchavez 15/06/2016
		habilitarValidacionConsultaAvanzada(true);
		//Fin nchavez 15/06/2016
		$('#tblExpedientes').dataTable().fnClearTable();
		$('#tblExpedientes').dataTable().fnDraw();
		$('#frmFiltroBusquedaAvanzada').bootstrapValidator('resetForm', true);
		$('#selCodigoTipoExpendiente').empty();
		$('#txtRazonSocial').val("");
		$('#selCodigoTipoExpendiente').append($('<option>', {
			value: '',
			text: 'Seleccione'
		}));
		
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
				//Inicio nchavez 12/07/2016
	            url: '${pageContext.request.contextPath}/regReqControl.htm?action=cargarListaTiposExpediente',
	           	data: {"codProceso":codProceso},
	            cache: false,
	            async: false,
	            type: 'POST',
	            // contentType : "application/json; charset=utf-8",
	            //Fin nchavez 12/07/2016
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
    	
		$('#tblExpedientes').DataTable({
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
				// nchavez [31/05/2016]
				// {data : "numExpedienteVirtual", sClass: 'center alinearCentrado', 
				// 	render : function(data, row){
						
				// 		return jQuery('<input>').css(
				// 			{
				// 				"marginLeft"	:	"8px", 
				// 				"width"			:	"18px", 
				// 				"height"		:	"18px"
				// 			}
				// 		).attr(
				// 			{
				// 				type:'radio', 
				// 				checked:  false, 
				// 				"class" : "bChecked",
				// 				name: "seleccione",
				// 				id: data,
				// 				onclick: 'guardarNumExpediente(this.id)'
				// 			}
				// 		).wrap('<div></div>').parent().html();
				// 	}
				// },
				{data : "numExpedienteVirtual", sClass: 'left alinearCentrado',render:function(data,row,full){
					return '<a href="#" data-id="'+full.numExpedienteVirtual+'" onclick="guardarNumExpediente(event,'+full.numExpedienteVirtual+')" >'+data+'</a>';
				}},
				{data : "numExpedienteOrigen", sClass: 'left alinearCentrado'},
				{data : "numRuc", sClass: 'centered alinearCentrado'},
				{data : "desRazonSocial",sClass:'centered alinearCentrado'},
				//[Fin nchavez 26/05/2016]
				{data : "fechaDocumentoOrigen", sClass: 'left alinearCentrado'},				
				// INICIO [ATORRESCH 20170307]
				{data : "strFecNotifOrigen", sClass: 'left alinearCentrado'},
				{data : "desForNotifOrigen", sClass: 'left alinearCentrado'},
				// FIN    [ATORRESCH 20170307]
				{data : "desProceso", sClass: 'left alinearCentrado'},
				{data : "desTipoExpediente", sClass: 'left alinearCentrado'},
				{data : "fecRegistro", sClass: 'left alinearCentrado'},
				{data : "nombreResponsable", sClass: 'left alinearCentrado'}
				
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
			iDisplayLength		:	5,
			//responsive			:	true,
			bLengthChange		: 	false,
			fnDrawCallback		:	function(oSettings) {
				
				var a = $('#tblExpedientes').width()
				$("#tblExpedientes_wrapper").css("min-width", a);
				
				$('#tableDocumento_paginate').addClass('div100');
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
	// nchavez [31/05/2016]
	function guardarNumExpediente(e,numExpediente){
		e.preventDefault ? e.preventDefault() : (e.returnValue = false);
		// $('#btnConsulta').prop('disabled', false);
		numExpedienteConsultar = numExpediente;
		// consultarDetalleExpedienteVirtual();
		cargarDatosBusquedaSession();
		
	}
	function consultarDetalleExpedienteVirtual(){
		//Inicio nchavez 12/07/2016
		// var  url = '${pageContext.request.contextPath}/regReqControl.htm?action=consultarDetalleExpedienteVirtualView';
		var  url = '${pageContext.request.contextPath}/regReqControl.htm?action=consultarDetalleExpedienteVirtualView&numExpedienteVirtual='+numExpedienteConsultar+'&flagPaginaPrincipal=true';
		/*
		var form=document.createElement('form');
		form.action=url;
		form.name='frmDetalle'
		form.method='post';

		var input1=document.createElement('input');
		input1.setAttribute('name','numExpedienteVirtual');
		input1.setAttribute('value',numExpedienteConsultar);

		var input2=document.createElement('input');
		input2.setAttribute('name','flagPaginaPrincipal');
		input2.setAttribute('value',true);

		var send=document.createElement('input');
		send.setAttribute('type','submit');

		form.appendChild(input1);
		form.appendChild(input2);
		form.appendChild(send);
		form.submit();
		*/

		$(location).prop( 'href', url);
		//Fin nchavez 12/07/2016
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
	
	//mostrar P⨩na de Error
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
			
	function revalidarFormulario(){
		var tipoBusqueda = $('#selTipoBusquedaExpediente').val();
		//Inicio nchavez 15/06/2016
		if($('#txtRuc').val()==''){
			habilitarValidacionConsultaAvanzada(true);
		}else{
			habilitarValidacionConsultaAvanzada(false);
		}
		//Fin nchavez 15/06/2016
		if(tipoBusqueda!=""){
			$('#frmFiltroBusquedaEspecifica').submit();
		}else{
			$('#frmFiltroBusquedaAvanzada').submit();
		}
		if(!llimpiarTabla){
			$('#tblExpedientes').dataTable().fnClearTable();
			$('#tblExpedientes').dataTable().fnDraw();
		}
	}	

	 
	
// 	function resetearFormularioConEsp(event){
// 		$('#frmFiltroBusquedaEspecifica').bootstrapValidator('resetForm', true);
// 		$('#selTipoBusquedaExpediente').val("");
// 		if($("#selCodigoProceso").val() == "") $('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'codTipexp');
// 		$('#txtNumeroExpediente').prop('disabled', true);
// 	}
	
	function resetearFormularioConEsp(event){
		$('#frmFiltroBusquedaEspecifica').bootstrapValidator('resetForm', true);
		$('#selTipoBusquedaExpediente').val("");
		
		var current = event.target || event.srcElement;
		
		if(current!=null && current!='null' && current!='undefined'){
			
			if(current.id != "selDependencia"){
			  if($("#selCodigoProceso").val() == "" && current.id != "txtRuc") $('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'codTipexp');
			}
			$('#txtNumeroExpediente').prop('disabled', true);
	
			//Inicio nchavez 15/06/2016
			if(current.id == "selTipoBusquedaFecha"){
				if($("#selTipoBusquedaFecha").val() != ""){
					  habilitarValidacionFecha(true);
					  $('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecDesde');
					  $('#frmFiltroBusquedaAvanzada').bootstrapValidator('revalidateField', 'fecHasta');
					}else{		
						if($('#txtfechaDesde').val() == "" && $('#txtfechaHasta').val() == ""){
							habilitarValidacionFecha(false);
						}				
					}
			}
			//Fin nchavez 15/06/2016
		}
	}
	
	function validarContribuyente(){

		var numRuc = $('#txtRuc').val();
		/*
		$.ajax({
			
			url : '${pageContext.request.contextPath}/regReqControl.htm?action=validarContribuyente',
			type : 'GET',
			async : false,
			dataType : "json",
			data : '&numRuc='+numRuc,
			contentType : "application/json",
			mimeType : "application/json",
			success : function(response) {
				
				var msjError = response.msjError;
				if(msjError!="" && msjError!=undefined){
					msjErrorRuc = msjError;
				}
				
				if(response.razonSocial!="" && response.razonSocial!=undefined){
					$('#txtRazonSocial').val(response.razonSocial);
				}
				
			},
			error : function(response) {
				
				mostrarPagError();
			}
			
		});
		*/
		$.ajax({
			
			url : '${pageContext.request.contextPath}/regReqControl.htm?action=validarContribuyente',
			//Inicio nchavez 12/07/2016
			type : 'POST',
			async : false,
			dataType : "json",
			data : {'numRuc':numRuc},
			// contentType : "application/x-www-form-urlencoded; charset=UTF-8",
			//Fin nchavez 12/07/2016
			mimeType : "application/json",
			success : function(response) {
				
				var msjError = response.msjError;
				if(msjError!="" && msjError!=undefined){
					msjErrorRuc = msjError;
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
	
	function buscar(){
			
			$('#btnConsulta').prop('disabled', true);
			listaExpedientes = [];
			var tipoBusqueda = $('#selTipoBusquedaExpediente').val();
			var numeroExpediente = $('#txtNumeroExpediente').val();
			if(tipoBusqueda!=""){				
				if(numeroExpediente==""){
					return;
				}
			}
			
			$.ajax({
				
				//Inicio nchavez 12/07/2016
				url : '${pageContext.request.contextPath}/regReqControl.htm?action=cargarListadoExpedientesVirtuales',
				type : 'POST',
				async : true,
				dataType : "json",
				data : $('#frmFiltroBusquedaEspecifica').serialize()+'&'+$('#frmFiltroBusquedaAvanzada').serialize()+'&flagBusquedaBoton='+flagBusquedaBoton,
				// contentType : "application/json",
				//FIn nchavez 12/07/2016
				mimeType : "application/json",
				//timeout : 5000,
				success : function(response) {
					
					var msjError = response.msjError;
					if(msjError!="" && msjError!=undefined){
						$('#tblExpedientes').dataTable().fnClearTable();
						$('#tblExpedientes').dataTable().fnDraw();
						mostrarDlgInfo(titulos.tituloDefecto, msjError);
						return;
					}					
					listaExpedientes = response.listaExpedientesVirtuales;

					
					if (listaExpedientes.length > 0) {
						//AQ-FSW
						listaExpedientes = caracterHtml(true,listaExpedientes);
						
						$('#tblExpedientes').dataTable().fnClearTable();
						$('#tblExpedientes').dataTable().fnAddData(listaExpedientes);
						$('#tblExpedientes').dataTable().fnDraw();
						
						if(tblPage != "") {
							setPagintaTbl("tblExpedientes", parseInt(tblPage)); //anadir
							tblPage = "";
						}
						
					} else {
						
						$('#tblExpedientes').dataTable().fnClearTable();
						$('#tblExpedientes').dataTable().fnDraw();
						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion05);
						
					}
				
				},
				error : function(response) {
					
						$('#tblExpedientes').dataTable().fnClearTable();
						$('#tblExpedientes').dataTable().fnDraw();
					mostrarPagError();
					
				}
				
			});
		}	
		function validar(){
		}
		
		function activarCampo(){
			var codProceso = $('#selTipoBusquedaExpediente').val();
			if(codProceso==""){
				$('#frmFiltroBusquedaEspecifica').bootstrapValidator('resetForm', true);
				$('#txtNumeroExpediente').prop('disabled', true);
			}else if(codProceso=="1"){
				$('#txtNumeroExpediente').prop('disabled', false);
				$('#txtNumeroExpediente').val("");
				//nchavez 27/05/2016
				$("#txtNumeroExpediente").attr('maxlength', 17);			
				$('#frmFiltroBusquedaEspecifica').bootstrapValidator('revalidateField', 'numExp');
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('resetForm', true);
				//Inicio nchavez 15/06/2016
				habilitarValidacionConsultaAvanzada(true);	
				//Fin nchavez 15/06/2016
				$('#txtNumeroExpediente').focus();
				$('#selCodigoTipoExpendiente').empty();
				$('#selCodigoTipoExpendiente').append($('<option>', {
					value: '',
					text: 'Seleccione'
				}));
			}else if(codProceso=="2"){
				$('#txtNumeroExpediente').prop('disabled', false);
				$('#txtNumeroExpediente').val("");
				$("#txtNumeroExpediente").attr('maxlength', 14);			
				$('#frmFiltroBusquedaEspecifica').bootstrapValidator('revalidateField', 'numExp');
				$('#frmFiltroBusquedaAvanzada').bootstrapValidator('resetForm', true);
				//Inicio nchavez 15/06/2016
				habilitarValidacionConsultaAvanzada(true);	
				//Fin nchavez 15/06/2016
				$('#txtNumeroExpediente').focus();
				$('#selCodigoTipoExpendiente').empty();
				$('#selCodigoTipoExpendiente').append($('<option>', {
					value: '',
					text: 'Seleccione'
				}));
			}
			$('#txtRazonSocial').val('');
		}
		
		function exportar(){
			var dataExp = $('#tblExpedientes').dataTable().fnGetData();
			if(dataExp.length > 0){
				//AQ-FSW
				dataExp = caracterHtml(false,dataExp);	
				var listaCadena = JSON.stringify(dataExp);
				var formURL = 'regReqControl.htm?action=exportarExcelExpedientes';
				document.forms.formPrincipal.action = formURL;
				document.forms.formPrincipal.method = "POST";
				$('#campoHiddenExp').val('');
				$('#campoHiddenExp').val(listaCadena);
				document.forms.formPrincipal.submit();
			}else{
				mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion06);
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
		
		function onChangeTipoBusqueda(){
			$('#frmFiltroBusquedaEspecifica').bootstrapValidator('resetForm', true);
			$('#selTipoBusquedaExpediente').val("");
			$('#txtNumeroExpediente').prop('disabled', true);

			var tipoBusquedaFecha  =  $('#selTipoBusquedaFecha').val();
			//Inicio nchavez 14/06/2016
			if(tipoBusquedaFecha==""){
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
			//Fin nchavez 14/06/2016
		}
		
		function cargarDatosBusquedaSession(){
					
			$.ajax({
				//Inicio nchavez 12/07/2016
				url : '${pageContext.request.contextPath}/regReqControl.htm?action=cargarDatosBusquedaSession',
				type : 'POST',
				async : false,
				dataType : "json",
				data : $('#frmFiltroBusquedaEspecifica').serialize()+'&'+$('#frmFiltroBusquedaAvanzada').serialize()+'&tblPage='+getPaginaTbl("tblExpedientes"),
				// contentType : "application/json",
				//Inicio nchavez 12/07/2016
				mimeType : "application/json",
				success : function(response) {
					
					consultarDetalleExpedienteVirtual();
					
				},
				error : function(response) {
					
					mostrarPagError();
				}
				
			});
		}
		
		function removerDatosBusquedaSession(){
			
			//removemos los datos guardados en session
			$.ajax({
				//Inicio nchavez 12/07/2016
				url : '${pageContext.request.contextPath}/regReqControl.htm?action=removerDatosBusquedaSession',
				type : 'POST',
				async : false,
				dataType : "json",
				data : '',
				// contentType : "application/json",
				// Inicio nchavez 12/07/2016
				mimeType : "application/json",
				success : function(response) {
					
					limpiar();
					
				},
				error : function(response) {
					
					limpiar();
				}
				
			});
		}


	//Inicio nchavez 15/06/2016
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
	//Fin nchavez 15/06/2016	
	
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
 </script>

</head>
<body>
</br>
	<div id="container" class="container" style="width: 95%">
		<div>
			<div class="row">
				<div class="panel panel-primary">
					<div class="panel-heading align="center">
						<h3 class="panel-title" align="center">CONSULTAR EXPEDIENTES VIRTUALES</h3>
						<form id="frmPrincipal" class="form-horizontal" role="form">
						</form>
					</div>
				</div>	
				<div class="panel panel-primary">
					<form class="form-horizontal" role="form" name="frmFiltroBusquedaEspecifica" id="frmFiltroBusquedaEspecifica" method="post">
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
								<legend class="scheduler-border"> Consulta Espec&iacute;fica </legend>
								<div class="form-group">
									<div class="col-md-1"><Strong>N&uacute;mero de Expediente:</Strong></Strong></div>
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
									<select name="codTipexp" id="selCodigoTipoExpendiente" onchange="resetearFormularioConEsp(event)" class="form-control tamanoMaximo"> 
										<option value="">Seleccione</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-1"><Strong>Fecha de:</Strong></div>
								<div class="col-md-3">
									<select name="codTipBusquedaFecha" id="selTipoBusquedaFecha" onchange="onChangeTipoBusqueda()" class="form-control tamanoMaximo"> 
										<option value="">Seleccione</option>
									</select>
								</div>
								<div class="col-md-1"><Strong>Desde:</Strong></div>
								<div class="col-md-3">
									<div class='input-group date tamanoMaximo' id="divFechaDesde">
											<input type='text' class="form-control tamanoMaximo" onkeypress="resetearFormularioConEsp(event)" id='txtfechaDesde' name='fecDesde' placeHolder="dd/mm/aaaa" /> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
									</div>
								</div>
								<div class="col-md-1"><Strong>Hasta:</Strong></div>
								<div class="col-md-3">
									<div class='input-group date tamanoMaximo' id="divFechaHasta">
											<input type='text' class="form-control tamanoMaximo" onkeypress="resetearFormularioConEsp(event)" id='txtfechaHasta' name='fecHasta' placeHolder="dd/mm/aaaa" /> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-1"><Strong>RUC:</Strong></div>
								<div class="col-md-3">
									<input name="numRuc" id="txtRuc" type="text" onkeypress="resetearFormularioConEsp(event)" class="form-control tamanoMaximo" maxlength="11"></input>
								</div>
								<div class="col-md-1"><Strong>Raz&oacute;n Social:</Strong></div>
								<div class="col-md-7">
									<input name="razonSolcial" id="txtRazonSocial" type="text"  class="form-control tamanoMaximo" disabled></input>
								</div>
							</div>
							
						</fieldset>	
						<div class="form-group" style="margin : 15px 15px !important">
							<div class="col-md-12" align="right" id="dvSecBotones01">	
								<input type="button" class="btn btn-primary" intermediateChanges="false" data-dismiss="modal" value="Limpiar" id="btnLimpiar" onClick="removerDatosBusquedaSession()"></input>
								<input type="button" class="btn btn-primary" id="btnConsultar" intermediateChanges="false" onClick="revalidarFormularioBusqueda()" value="Buscar"></input>
							</div>	
						</div>
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border"> Listado de Expedientes </legend>
							<div class="form-group">
								<div class="col-md-12">	
									<div class="tab-content">
									<div id="accionesFiscalizacion" class="tab-pane fade in active">
									<div class="border-line panel-scroll">
									<table id="tblExpedientes" class="table display" cellspacing="0" style="width: 100%;">
										<thead>
											<tr>
												<!-- <th width="5%" class="text-center">Seleccionar Expediente</th> -->
												<!-- [nchavez 26/05/2016] -->
												<th width="10%" class="text-center">N&deg; Expediente Virtual</th>
												<th width="10%" class="text-center">N&deg; Expediente Origen</th>
												<th width="10%" class="text-center">RUC</th>
												<th width="10%" class="text-center">Raz&oacute;n Social</th>
												<th width="8%" class="text-center">Fecha del Documento Origen</th>
												<!-- INICIO [ATORRESCH 20170307] -->
												<th width="8%" class="text-center">Fecha de Notificaci&oacute;n</th>
												<th width="10%" class="text-center">Forma de Notificaci&oacute;n</th>
												<!-- FIN    [ATORRESCH 20170307] -->
												<th width="8%" class="text-center">Proceso</th>
												<th width="8%" class="text-center">Tipo de Expediente</th>
												<th width="8%" class="text-center">Fecha de Registro del Expediente</th>
												<th width="10%" class="text-center">Responsable del Expediente</th>
											</tr>
										</thead>
									</table>
									</div>
									</div>
									</div>
								</div>
							</div>
							
						</fieldset>	
						<fieldset  style="margin : 15px 15px !important">						
							<div class="form-group">
								<div class="col-md-12" align="center" id="dvSecBotones01">	
									<!-- nchavez [31/05/2016] -->
									<!-- <input type="button" class="btn btn-primary"  id="btnConsulta" intermediateChanges="false" data-dismiss="modal" value="Consultar Requerimientos" id="btnGuardar" onClick="cargarDatosBusquedaSession()"></input> -->
									<input type="button" class="btn btn-primary" id="btnCancelar" intermediateChanges="false" onClick="exportar()" value="Exportar a Excel"></input>
								</div>	
							</div>	
						</fieldset>		
					</form>
					<form id="formPrincipal" class="form-horizontal" role="form">	
						<div class="col-md-5" align="right" id="dvSecBotones02">	
							<input id="campoHiddenExp" type="hidden" name="listadoExpedientesCadena"/>
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
	</body>	
</html>