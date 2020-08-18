
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=EDGE" />
<meta name="viewport"
	content="initial-scale = 1.0, user-scalable = no,  width=device-width">
<title>SISTEMA INTEGRADO DE EXPEDIENTE VIRTUAL - SIEV</title>


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

.dataTables_wrapper .dataTables_paginate {
		float: initial !important;
		text-align: center !important;
		padding-top: 0.25em;
	}
.div100{
		width : 100% !important;
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
	
	.dataTables_paginate {
	padding-top: 0px !important;
	width : 100% !important;
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
		overflow-x: scroll; /*antes: overflow-x: auto;*/
	}
	
</style>
<!-- --[PAS20201U210500029]  HHC - INICIO -->
 <script type="text/javascript">
 
	var listadoProcesos = ${listadoProcesos};
	
	var excepciones = ${excepciones};
	var titulos =  ${titulos};
	var numExpedienteConsultar="";
	var retorno=false;
	var msjErrorRuc="";
	var llimpiarTabla=true;
	
	var expedienteVirtual;
	
	var tblPage = "${beanParametrosConsultaReq.tblPage}";  //anadir
	var hoverBuscar=false;

	//Inicio Realiza la accion cuando el usuario realiza la consulta.
	var flagBusquedaBoton = false;
	$(function () {
    	
		$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
		construirTablaExpediente( [] );
		$('#tblRequerimientos tbody').on('mouseenter', 'tr', function() {
			$(this).addClass("selected");
		});	
		$('#tblRequerimientos tbody').on( 'mouseleave', 'tr', function () {
			 $(this).removeClass("selected");
		} );
		inicializarProcesos();
		
		var table = $('#tblRequerimientos').DataTable();

		$('#tblRequerimientos').on( 'page.dt', function () {
			var info = table.page.info();
			$('input').filter(':radio').prop('checked',false);
		} );
		
		$('#tblRequerimientos tbody').on( 'mouseenter', 'tr', function () {
			indexDocumentos = table.row( this ).index();
			
		} );

		$( "#txtNumeroExpediente" ).blur(function() {
			retorno=true;
			$('#frmFiltroBusquedaEspecifica').bootstrapValidator('revalidateField', 'numExp');
		});

		$('#btnConsultar').mouseover(function(){
			hoverBuscar=true;
			// console.log(hoverBuscar);
		});

		$('#btnConsultar').mouseout(function(){
			hoverBuscar=false;
			// console.log(hoverBuscar);
		});
		
		makeInputMask( '#txtNumRuc', "(9){1,11}", 11, '' );
				
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

									if(numExpediente==""){
											$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateMessage', 'numExp', 'callback', 'Ingrese N&uacute;mero Expediente V&aacute;lido.');
											$('#frmFiltroBusquedaEspecifica').bootstrapValidator('updateOption', 'numExp', 'callback', 'Ingrese n&uacute;mero Expediente V&aacute;lido.');
											retorno=false;
											llimpiarTabla=false;
											return false;
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
    	}).on('error.form.bv', function(e) {
            $('#tblRequerimientos').dataTable().fnClearTable();
			$('#tblRequerimientos').dataTable().fnDraw();
		});
		
		
		cargarFormulario();
		$( window ).off('resize');
	})   
    
	function cargarFormulario(){
    	
    	var realizarBusqueda = '${beanParametrosConsultaReq.realizarBusqueda}';
    	
    	if(realizarBusqueda=="1"){
			var numExp = '${beanParametrosConsultaReq.numExp}';
			if(numExp!=""){
				$('#txtNumeroExpediente').prop('disabled', false);
				$('#txtNumeroExpediente').val(numExp);
			}
			retorno = true;
			realizarBusqueda = true;
			//Inicio Realiza la accion cuando el usuario realiza la consulta.
    		revalidarFormularioRetorno();
    	}
    }
	
	function getPaginaTbl(idTabla){
		return $('#'+idTabla).DataTable().page.info().page;
	}
			
	function setPagintaTbl(idTabla, idxPagina){
		$('#'+idTabla).DataTable().page(idxPagina).draw(false);
	}
	
	function inicializarProcesos() {
		// nchavez [27/05/2016]
		makeInputMask( '#txtNumeroExpediente', "(9){1,17}", 17, '' );
// 		$('#txtNumeroExpediente').prop('disabled', true);
// 		$('#btnConsulta').prop('disabled', true);
		
	}
	
    function limpiar(){
		resetearFormularioConEsp(event);
		$('#tblRequerimientos').dataTable().fnClearTable();
		$('#tblRequerimientos').dataTable().fnDraw();
		
		$("#txtDesRazonSocial").val("");
		
		$( window ).off('resize');
		$("#btnLimpiar").blur();
		//location.reload();
	}
	
	 
	function construirTablaExpediente(dataGrilla) {
    	
		$('#tblRequerimientos').DataTable({
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
				{data : "numExpedienteOrigen", sClass: 'left alinearCentrado',render:function(data,row,full){
					return '<a href="#"  onClick= "regEscritoElec('+data+')" >'+data+'</a>';
				}},
				{data : "desOrigen", sClass: 'left alinearCentrado'},
				{data : "desProceso", sClass: 'left alinearCentrado'},
				{data : "desTipoExpediente", sClass: 'left alinearCentrado'},
				{data : "fecRegistro", sClass: 'left alinearCentrado'},
				{data : "codTipoExpediente", sClass: 'hidden alinearCentrado'},
				{data : "numExpedienteVirtual", sClass: 'hidden alinearCentrado'}
				
			],
			data: dataGrilla,
            ordering			:	true,
			bScrollAutoCss		:	true,
			bStateSave			:	false,
			bAutoWidth			:	false,
			bScrollCollapse		:	true,
			searching			:	false,
			paging				:	false,
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
		} );
		
    }
    
	function regEscritoElec(data) {
		var tabla = $('#tblRequerimientos').dataTable().fnGetData();						
		var estadoExp = tabla[indexDocumentos].codEstado;
		if (estadoExp != "01") {
			mostrarDlgInfo(titulos.tituloDefecto, "El expediente electr&oacute;nico debe encontrarse en estado Abierto.");
			
		}else{
			numExpedienteConsultar = tabla[indexDocumentos].numExpedienteVirtual;
			cargarDatosBusquedaSession();
			var formURL = 'cargarDocumento.htm?action=registraEscrito';
			var listaCadena = JSON.stringify(tabla);
			document.forms.formPrincipal.action = formURL;
			document.forms.formPrincipal.method = "POST";
			$('#campoHiddenExp').val('');
			$('#hidTxtNumExpVirt').val('');
			$('#campoHiddenExp').val(listaCadena);
			$('#hidTxtNumExpVirt').val(numExpedienteConsultar);
			document.forms.formPrincipal.submit();
			$('html').unblock();
			
		}			
		
	}
	
	function cargarDocumentosAlExpediente(){
		
		var  url = '${pageContext.request.contextPath}/cargarDocumento.htm?action=consultarDetalleExpedienteView&numExpedienteVirtual='+numExpedienteConsultar;
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
	
	function validarFormulario(){
	
	}
	
	//mostrar PÃÂ¢ÃÂ¨ÃÂ©na de Error
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
    //Inicio  Realiza la accion cuando el usuario realiza la consulta.
    
    function revalidarFormularioRetorno(){
    	revalidarFormulario(false);
    }
    
    function revalidarFormularioConsulta(){
    	var txtNumeroExpediente = $('#txtNumeroExpediente');
		 if (txtNumeroExpediente.val()==""){	
			 document.getElementById("txtNumeroExpediente").focus();
			 retorno=true;
			  $('#frmFiltroBusquedaEspecifica').bootstrapValidator('revalidateField', 'numExp');
			 
		 }else{
    	     revalidarFormulario(true);
		 }
    }
    
	function revalidarFormulario(realizaClickBoton){
		flagBusquedaBoton = realizaClickBoton;
    	
		$('#frmFiltroBusquedaEspecifica').submit();
		
		if(!llimpiarTabla){
			$('#tblExpedientes').dataTable().fnClearTable();
			$('#tblExpedientes').dataTable().fnDraw();
		}
	}		
	
	
	function resetearFormularioConEsp(event){
		$('#frmFiltroBusquedaEspecifica').bootstrapValidator('resetForm', true);
		
// 		var current = event.target || event.srcElement;
// 		if(current!=null && current!='null' && current!='undefined'){
			
// 			$('#txtNumeroExpediente').prop('disabled', true);
	
// 		}
	}
	
	function validarContribuyente(){

		var numRuc = $('#txtNumRuc').val();
		
		$.ajax({
			
			url : '${pageContext.request.contextPath}/cargarDocumento.htm?action=validarContribuyente',
			type : 'POST',
			async : false,
			dataType : "json",
			data : {'numRuc':numRuc},
			// contentType : "application/json",
			mimeType : "application/json",
			success : function(response) {
				
				var msjError = response.msjError;
				if(msjError!="" && msjError!=undefined){
					msjErrorRuc = msjError;
				}
				
				if(response.razonSocial!="" && response.razonSocial!=undefined){
					$('#txtDesRazonSocial').val(response.razonSocial);
				}
				
			},
			error : function(response) {
				
				mostrarPagError();
			}
			
		});
	}
	
	function buscar(){
			
			$('#btnConsulta').prop('disabled', true);
			listaRequerimientos = [];
			var numeroExpediente = $('#txtNumeroExpediente').val();
			
			if(numeroExpediente=="")return;
			
			$.ajax({
				//Inicio Realiza la accion cuando el usuario realiza la consulta.
				url : '${pageContext.request.contextPath}/cargarDocumento.htm?action=cargarListadoExpedientesPendientes',
				type : 'POST',
				async : true,
				dataType : "json",
// 				data : $('#frmFiltroBusquedaEspecifica').serialize()+'&'+$('#frmFiltroBusquedaAvanzada').serialize()+'&flagBusquedaBoton='+flagBusquedaBoton,
				data : $('#frmFiltroBusquedaEspecifica').serialize(),
				// contentType : "application/json",
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
					
					expedienteVirtual = response.listT6614ExpVirtualBean;
					if (expedienteVirtual!=null) {						
						//AQ-FSW
						$('#txtNumRuc').val(expedienteVirtual.numRuc);
						$('#txtDesRazonSocial').val(expedienteVirtual.desRazonSocial);
						
						$('#tblRequerimientos').dataTable().fnClearTable();
						$('#tblRequerimientos').dataTable().fnAddData(expedienteVirtual);
						$('#tblRequerimientos').dataTable().fnDraw();
						
						if(tblPage != "") {
							setPagintaTbl("tblRequerimientos", parseInt(tblPage)); //anadir
							tblPage = "";
						}
						
					} else {
						
						$('#tblRequerimientos').dataTable().fnClearTable();
						$('#tblRequerimientos').dataTable().fnDraw();
// 						mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion05);
						mostrarDlgInfo(titulos.tituloDefecto, listaRequerimientos);
						
					}
				
				},
				error : function(response) {
					
						$('#tblRequerimientos').dataTable().fnClearTable();
						$('#tblRequerimientos').dataTable().fnDraw();
					mostrarPagError();
					
				}
				
			});
		}

		
		function exportar(){
			$('html').block({message: '<h1>Procesando...</h1>'});
			var dataExp = $('#tblRequerimientos').dataTable().fnGetData();
			if(dataExp.length > 0){
				//AQ-FSW
				dataExp = caracterHtml(false,dataExp);
				var listaCadena = JSON.stringify(dataExp);
				var formURL = 'cargarDocumento.htm?action=exportarExcelExpedientes';
				document.forms.formPrincipal.action = formURL;
				document.forms.formPrincipal.method = "POST";
				$('#campoHiddenExp').val('');
				$('#campoHiddenExp').val(listaCadena);
				document.forms.formPrincipal.submit();
			}else{
				mostrarDlgInfo(titulos.tituloDefecto, excepciones.excepcion02);
			}
			$('html').unblock();
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
		
		function cargarDatosBusquedaSession(){
			
			$.ajax({
				url : '${pageContext.request.contextPath}/cargarDocumento.htm?action=cargarDatosBusquedaSession'+"&tblPage="+getPaginaTbl("tblRequerimientos"),
				type : 'POST',
				async : false,
				dataType : "json",
				data : $('#frmFiltroBusquedaEspecifica').serialize(),
				// contentType : "application/json",
				mimeType : "application/json",
				success : function(response) {
					
					cargarDocumentosAlExpediente();
					
				},
				error : function(response) {
					
					mostrarPagError();
				}
				
			});
		}
		
		function removerDatosBusquedaSession(){
			
			//removemos los datos guardados en session
			$.ajax({
				
				url : '${pageContext.request.contextPath}/cargarDocumento.htm?action=removerDatosBusquedaSession',
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
						<h3 class="panel-title" align="center">PRESENTACI&Oacute;N DE ESCRITOS ELECTR&Oacute;NICOS</h3>
						<form id="frmPrincipal" class="form-horizontal" role="form">
						</form>
					</div>
				</div>	
				<div class="panel panel-primary">
					<form class="form-horizontal" role="form" name="frmFiltroBusquedaEspecifica" id="frmFiltroBusquedaEspecifica" method="post">
						<fieldset class="scheduler-border" style="margin: 15px 15px !important">
							<legend class="scheduler-border"> Consulta Espec&iacute;fica </legend>
							<div class="form-group">
							    </br>
								<div class="col-md-2">
									<Strong>N&uacute;mero de Expediente:</Strong></Strong>
								</div>
								<div class="col-md-2">
									<input name="numExp" id="txtNumeroExpediente" type="text" class="form-control tamanoMaximo" placeHolder=""></input>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"> <Strong>N&uacute;mero de RUC:</Strong> </div>
								<div class="col-md-2">
									<input name="numRuc" id="txtNumRuc" type="text" class="form-control tamanoMaximo" maxlength="11" disabled="disabled"></input>
								</div>
								<div class="col-md-2"><Strong>Raz&oacute;n Social:</Strong></div>
								<div class="col-md-6">
									<input name="razonSolcial" id="txtDesRazonSocial" type="text" class="form-control tamanoMaximo" disabled></input>
								</div>
							</div>
						</fieldset>
					</form>		
					<div class="form-group" style="margin : 15px 15px !important">
								<div class="col-md-12" align="right" id="dvSecBotones01">	
									<input type="button" class="btn btn-primary" intermediateChanges="false" data-dismiss="modal" value="Limpiar" id="btnLimpiar" onClick="removerDatosBusquedaSession()"></input>
									<input type="button" class="btn btn-primary" id="btnConsultar" intermediateChanges="false" onClick="revalidarFormularioConsulta()" value="Buscar"></input>
									
								</div>	
					</div>
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border"> Listado de Expediente </legend>
							<div class="form-group">
								<div class="col-md-12">	
									<div class="tab-content">
									<div id="accionesFiscalizacion" class="tab-pane fade in active">
									<div class="border-line panel-scroll">
									<table id="tblRequerimientos" class="table display" cellspacing="0" style="width: 100%;">
										<thead>
											<tr class="active">
												<th width="10%" class="text-center">N&deg; Expediente</th>
												<th width="8%" class="text-center">Origen</th>
												<th width="10%" class="text-center">Proceso</th>
												<th width="10%" class="text-center">Tipo de Expediente</th>
												<th width="8%" class="text-center">Fecha de Expediente</th>
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
									<input type="button" class="btn btn-primary" id="btnCancelar" intermediateChanges="false" onClick="exportar()" value="Exportar a Excel"></input>
								</div>	
							</div>
						</fieldset>		
					<form id="formPrincipal" class="form-horizontal" role="form">	
						<div class="col-md-5" align="right" id="dvSecBotones02">	
							<input id="campoHiddenExp" type="hidden" name="listadoExpedientesCadena"/>
							<input id="hidTxtNumExpVirt" type="hidden" name="hidTxtNumExpVirt"/>
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
<!-- --[PAS20201U210500029]  HHC - FIN -->
