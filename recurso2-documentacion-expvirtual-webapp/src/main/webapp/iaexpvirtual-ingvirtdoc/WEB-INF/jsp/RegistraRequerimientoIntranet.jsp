
<!DOCTYPE html>
<html lang="es">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
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
.dataTables_paginate {
	padding-top: 0px !important;
	width : 100% !important;
	}
.div100{
		width : 100% !important;
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
	.panel-scroll {
		max-height: 180px;
		min-height: 0px;
		overflow-y: auto;
		overflow-x: auto;
	}
	
	.alinearCentrado {
		vertical-align: middle !important;
	}
		
</style>

</head>
<body>
</br>
	<div id="container" class="container" style="width: 95%">
		<div>
			<div class="row">
				<div class="panel panel-primary">
					<div class="panel-heading align="center">
						<h3 class="panel-title" align="center">REGISTRAR REQUERIMIENTO DE DOCUMENTOS</h3>
						<form id="frmPrincipal" class="form-horizontal" role="form">
						</form>
					</div>
				</div>	
				<div class="panel panel-primary">
					<form class="form-horizontal" role="form" name="frmDatosDocumento" id="frmDatosDocumento" method="post">
<!-- 						<input type="hidden" name="listaDocumentosSel" id="listaDocumentosSelId"> -->
<!-- 						<input type="hidden" name="numExpedienteVirtual" id="numExpedienteVirtualId"> -->
	 						<input type="hidden" name="hidCodDependencia" id="hidCodDependencia" value='${codDependencia}'> <!-- [jjurado 03/08/2016] -->
					
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border" > Datos del Expediente </legend>
							<div class="form-group">
								<div class="col-md-2"><Strong>RUC:</Strong></div>
								<div class="col-md-3">
									<input name="txtRuc" id="txtRuc" type="text" class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.numRuc}' disabled></input>
								</div>
								<div class="col-md-2"><Strong>Raz&oacute;n Social:</Strong></div>
								<div class="col-md-3">
									<input name="txtRazonSocial" id="txtRazonSocial" type="text"  class="form-control tamanoMaximo" value='${razonSocial}' disabled></input>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"><Strong>Proceso:</Strong></div>
								<div class="col-md-3">
									<input name="txtProceso" id="txtProceso" type="text" class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.desProceso}' disabled></input>
								</div>
								<div class="col-md-2"><Strong>Tipo de Expediente:</Strong></div>
								<div class="col-md-3">
									<input name="txtTipoExpediente" id="txtTipoExpediente" type="text"  class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.desTipoExpediente}' disabled
									 data-codigo="${t6614ExpVirtualBean.codTipoExpediente}" ></input>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"><Strong>N&deg; Expediente Origen:</Strong></div>
								<div class="col-md-3">
									<input name="txtExpedienteOrigen" id="txtExpedienteOrigen" type="text" class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.numExpedienteOrigen}' disabled></input>
								</div>
								<div class="col-md-2"><Strong>N&deg; Expediente Virtual:</Strong></div>
								<div class="col-md-3">
									<input name="numExpedienteVirtual" id="txtExpedienteVirtual" type="text"  class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.numExpedienteVirtual}' disabled></input>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"><Strong>Fecha del Documento Origen:</Strong></div>
								<div class="col-md-3">
									<input name="txtFechaDocOrigen" id="txtFechaDocOrigen" type="text" class="form-control tamanoMaximo" value='${fechaDocOrigen}' disabled></input>
								</div>
								<div class="col-md-2"><Strong>Fecha de Notificaci&oacute;n:</Strong></div>
								<div class="col-md-3">
									<input name="txtFechaNotif" id="txtFechaNotif" type="text"  class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.strFecNotifOrigen}' disabled></input>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"><Strong>Forma de Notificaci&oacute;n:</Strong></div>
								<div class="col-md-3">
									<input name="txtFormaNotif" id="txtFormaNotif" type="text" class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.desForNotifOrigen}' disabled></input>
								</div>
								<div class="col-md-2"><Strong>Fecha de Registro del Expediente:</Strong></div>
								<div class="col-md-3">
									<input name="txtFechaRegExpediente" id="txtFechaRegExpediente" type="text"  class="form-control tamanoMaximo" value='${fechaRegExpediente}' disabled></input>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"><Strong>Responsable del Expediente:</Strong></div>
								<div class="col-md-3">
									<input name="txtRespExpediente" id="txtRespExpediente" type="text" class="form-control tamanoMaximo" value='${t6614ExpVirtualBean.nombreResponsable}' disabled></input>
								</div>
							</div>
							
						</fieldset>	
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border" > Datos del Requerimiento </legend>
							<div class="form-group">
								<div class="col-md-2"><Strong>Fecha de Vencimiento:</Strong></div>
								<div class="col-md-3">
									<div class='input-group date tamanoMaximo' id="divFechaVencimiento">
											<input type='text' class="form-control tamanoMaximo" id='txtFechaVencimiento' name='fecVencimiento' placeHolder="dd/mm/aaaa" /> <span class="input-group-addon"><i class="glyphicon glyphicon-calendar"></i></span>
									</div>
								</div>
								<div class="col-md-2"><Strong>N&uacute;mero de Requerimiento Origen:</Strong></div>
								<div class="col-md-3">
<!-- 									<input id="txtNmReqOrigen" name="numReqOrigen" type="text"  maxlength="17" class="form-control tamanoMaximo" value='' onkeypress="return permitirNumeros(event);"></input> -->
									<input id="txtNmReqOrigen" name="numReqOrigen" type="text"  maxlength="17" class="form-control tamanoMaximo" value=''></input>
								</div>
							</div>
						</fieldset>	
						<fieldset class="scheduler-border" style="margin : 15px 15px !important">
							<legend class="scheduler-border"> Listado de Tipos de Documento </legend>
							<div class="form-group">
								<div class="col-md-12">
								<div class="tab-content">
								<div id="accionesFiscalizacion" class="tab-pane fade in active">
								<div class="border-line panel-scroll">
									<div>
										<table id="tblDocumentos" name="tblDocumentos"  class="table table-striped" style="width: 100%;">
											<thead>
												<tr>
													<th width="5%" class="text-center">N&deg;</th>
													<th width="30%" class="text-center">Tipo de Documento</th>
													<th width="15%" class="text-center">Subir Documento</th>
													<th width="50%" class="text-center">Motivo</th>
													<th ></th>
												</tr>
											</thead>
										</table>
									</div>
								<div>
								</div>
								</div>
								</div>
								</div>
							<input type="hidden" name="validaMotivo" value="">
						</fieldset>	
						<fieldset style="margin : 15px 15px !important">
							<div class="form-group">
								<div class="col-md-4" align="right" id="dvSecBotones01">	
									<input type="button" class="btn btn-primary" id="btnRegresar" intermediateChanges="false" onClick="validarSalida()" value="Regresar"></input>
								</div>
								<div class="col-md-4" align="center" id="dvSecBotones01">	
									<input type="button" class="btn btn-primary" id="btnPrevisualizar" intermediateChanges="false" value="Previsualizar Requerimiento"></input>
									
								</div>		
								<div class="col-md-4" align="left" id="dvSecBotones01">	
									<input type="button" class="btn btn-primary" id="btnRegistrar" intermediateChanges="false" value="Registrar Requerimiento" id="btnGuardar" onClick="revalidarFormulario()"></input>
									
								</div>	
							</div>
						</fieldset>		
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
	
	<script type="text/javascript">
 
	var titulos =  ${titulos};
	var excepciones =  ${excepciones};
	var listaTipDocExp = ${listaTipDocExp};
	var esProcesoCobranza = ${esProcesoCobranza};
	var esProcesoAduana = ${esProcesoAduana};//[oachahuic][PAS20165E210400270]
	var fechaSistema = '${fechaSistema}';
	
	var requerimientoCreado = false;
	var numReqOrigenBefore='';
	// Inicio [nchavez 26/05/2015]
	var numReqOrigenValido=false;
	var msgReqOrigenValido="";
	var fechaVencimientoValida=false;
	var onBlurAfter=false;
	var onkeypressNReqOrigen=false;
	var onPasteNReqOrigen=false;
	var formatErrorNReqOrigen=false;
	var onDateChange=false;
	var fechaNotificacion="";
	

	// Fin [nchavez 26/05/2015]

	(function($){
	    $.fn.serializeObject = function(){

	        var self = this,
	            json = {},
	            push_counters = {},
	            patterns = {
	                "validate": /^[a-zA-Z][a-zA-Z0-9_]*(?:\[(?:\d*|[a-zA-Z0-9_]+)\])*$/,
	                "key":      /[a-zA-Z0-9_]+|(?=\[\])/g,
	                "push":     /^$/,
	                "fixed":    /^\d+$/,
	                "named":    /^[a-zA-Z0-9_]+$/
	            };


	        this.build = function(base, key, value){
	            base[key] = value;
	            return base;
	        };

	        this.push_counter = function(key){
	            if(push_counters[key] === undefined){
	                push_counters[key] = 0;
	            }
	            return push_counters[key]++;
	        };

	        $.each($(this).serializeArray(), function(){

	            // skip invalid keys
	            if(!patterns.validate.test(this.name)){
	                return;
	            }

	            var k,
	                keys = this.name.match(patterns.key),
	                merge = this.value,
	                reverse_key = this.name;

	            while((k = keys.pop()) !== undefined){

	                // adjust reverse_key
	                reverse_key = reverse_key.replace(new RegExp("\\[" + k + "\\]$"), '');

	                // push
	                if(k.match(patterns.push)){
	                    merge = self.build([], self.push_counter(reverse_key), merge);
	                }

	                // fixed
	                else if(k.match(patterns.fixed)){
	                    merge = self.build([], k, merge);
	                }

	                // named
	                else if(k.match(patterns.named)){
	                    merge = self.build({}, k, merge);
	                }
	            }

	            json = $.extend(true, json, merge);
	        });

	        return json;
	    };
	})(jQuery);
	
    $(function () {
    	
		$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
		makeInputMask( '#numReqOrigen', "(9){1,17}", 17, '' );
		construirTablaRequerimiento( listaTipDocExp );
		$('#tblDocumentos tbody').on('mouseenter', 'tr', function() {
			$(this).addClass("selected");
		});	
		$('#tblDocumentos tbody').on( 'mouseleave', 'tr', function () {
			 $(this).removeClass("selected");
			 //$('#tblDocumentos').dataTable().fnDraw();
		} );
				
		$('#txtFechaVencimiento').prop('readonly', true);
		$('#divFechaVencimiento').datetimepicker({
            format: 'DD/MM/YYYY',
			minDate: '${fechaSistema}',
			useCurrent: false,
			language: 'es',
			autoClose: true,
			forceParse: true,
			pickTime: false
        });
		

		/*$('#frmDatosDocumento :input').keypress(function(e){
			   var _msgLenght = $(this).val().length;
			   
			   if (_msgLenght > 199) {
					$(this).val($(this).val().substring(0, 199));
			   } else {
					var txt = String.fromCharCode(e.which);
					
					if (!txt.match(/[A-Za-z0-9;]/)) {
						return false;
					}	
			   }
		});*/
		
		$('#txtNmReqOrigen').keypress(function(e){
			   var _msgLenght = $(this).val().length;
			   onkeypressNReqOrigen=true;
			   onBlurAfter=false;
			   formatErrorNReqOrigen=false;
			   onPasteNReqOrigen=false;

  			   if(e.keyCode==13){
					validarNumRequerimientoOrigen();
 	     	   };

			   if (_msgLenght > 199) {
					$(this).val($(this).val().substring(0, 199));
			   } else {
					var txt = String.fromCharCode(e.which);
					
					if (!txt.match(/[A-Za-z0-9;]/)) {
						return false;
					}	
			   }
		});
		

		$('#txtNmReqOrigen').on('blur',function(e){
			//Inicio - [oachahuic][PAS20165E210400270]
			//if( isNumber($(this).val()) ){
				if (!($(this).val()==numReqOrigenBefore) || onPasteNReqOrigen) {
					numReqOrigenBefore=$(this).val();
					validarNumRequerimientoOrigen();
				}
			/*}else{
				formatErrorNReqOrigen=true;
				onBlurAfter=true;
				$('#frmDatosDocumento').bootstrapValidator('revalidateField', 'numReqOrigen');
			}*/
			//Fin - [oachahuic][PAS20165E210400270]
		});
 
 		//nchavez 08/06/2016 IE8
 		//Inicio - [oachahuic][PAS20165E210400270]
		/*$('#txtNmReqOrigen').on('paste',function(e){
			e.preventDefault();
			var text='';
			onPasteNReqOrigen=true;

			if (window.clipboardData) {
    			text = (window).clipboardData.getData('text');
			}else{
    		 	text = (e.originalEvent || e).clipboardData.getData('text/plain');
			}
			
			var numbers='';
			for (var i = 0; i < text.length; i++) {
				if(isNumber(text[i])){
					numbers+=text[i];
				}
			}
			 
			$('#txtNmReqOrigen').val(numbers);
			// $('#frmDatosDocumento').bootstrapValidator('revalidateField', 'numReqOrigen');
		});*/
		//Fin - [oachahuic][PAS20165E210400270]

		//nchavez 08/06/2016
		function isNumber(cadena){
			for (var i = 0; i < cadena.length; i++) {
				var txt =cadena[i];
				if (!txt.match(/[0-9]/)) {
					return false;
				}
			}
			return true;	
		}

		$('#btnPrevisualizar').on('click',function(){

			if(validarCheck()){
				var numExpeVirtual=$('#txtExpedienteVirtual').val();
				var num_reqorigen=$('#txtNmReqOrigen').val();
				var txtFechaVencimiento=$('#txtFechaVencimiento').val();
				var documentos=obtenerDocumentosSeleccionados();

				var url = '${pageContext.request.contextPath}/regReqControl.htm?action=previsualizarArchivo&numExpedVirtual=' + numExpeVirtual +'&numRequerimiento=' + num_reqorigen + '&fechaVencimiento=' + txtFechaVencimiento+'&documentos='+documentos;

			  	window.open(url, '_blank');
	        }else{
	        	$('#frmDatosDocumento').bootstrapValidator('revalidateField', 'tblDocumentos');
	        }

		});


		function obtenerDocumentosSeleccionados(){
			var idsDocumentos='';
			var checkeds=$('#tblDocumentos').find('.bChecked:checked');
			$.each(checkeds,function(k,v){
				var id=$(v).attr('id');
				//Inicio nchavez 24/06/2016
				idsDocumentos+=id+','+encodeURIComponent($('#txt'+id).val())+'|';
				//Fin nchavez 24/06/2016
			});
			return idsDocumentos.substring(0,idsDocumentos.length-1);
		}

		function validarNumRequerimientoOrigen(){
			if(esProcesoCobranza || esProcesoAduana){
				$('#modalDlg').hide();
				var numExpOrigen=$.trim($('#txtExpedienteOrigen').val());
				var numExpVirtual=$.trim($('#txtExpedienteVirtual').val());
				var num_reqorigen=$('#txtNmReqOrigen').val();
				var codTipoExpediente=$('#txtTipoExpediente').data('codigo');
				//Inicio nchavez [03/06/2016]
				var numruc=$('#txtRuc').val();
				// Inicio [jjurado 03/08/2016]
				var codDependencia = $('#hidCodDependencia').val();
				var url = '${pageContext.request.contextPath}/regReqControl.htm?action=validarNumRequerimientoOrigen&numExpeOrigen='+numExpOrigen;
					url+='&numExpeVirtual='+ numExpVirtual;
					url+='&numRequerimiento=' + num_reqorigen;
					url+='&codTipoExpediente=' + codTipoExpediente;
					url+='&numeroRuc='+numruc;
					url+='&codDependencia='+codDependencia;
					url+='&numExpVirtual='+$('#txtExpedienteVirtual').val();//[oachahuic][PAS20165E210400270]
				// Fin [jjurado 03/08/2016]
				//Fin nchavez [03/06/2016]
				$.ajax({					
						url : url,
						type : 'POST',
						async : false,
						dataType : "json",
						data : '',
						contentType : "application/json",
						mimeType : "application/json",
						timeout : 5000,
						success : function(response) {
							var numExpedienteOrigenVal = response.numExpedienteOrigenVal;						
							numReqOrigenValido=response.numExpedienteOrigenVal;
							msgReqOrigenValido=response.message;
							//Inicio [JTejada - 06/09/2016]
							if($.parseJSON(numReqOrigenValido)){
								// numReqOrigenValido=true;
								//Inicio [gangles 10/08/2016]
								var mapFechaNotificacion= response.mapFechaNotificacion;
		 						fechaNotificacion=mapFechaNotificacion.fechaNotificacion;
		 						$('#divFechaVencimiento').data("DateTimePicker").setMinDate(fechaNotificacion);
								//Fin [gangles 10/08/2016]
							}
							//Fin [JTejada - 06/09/2016]
						},
						complete:function(){
							onBlurAfter=true;
							$('#frmDatosDocumento').bootstrapValidator('revalidateField', 'numReqOrigen');
						}
				});
			}else{
				$('#divFechaVencimiento').data("DateTimePicker").setMinDate(fechaSistema);
				return;
			}
		}
		 
// Inicio [nchavez 26/05/2015]
		function validarFechaVencimientoRequerimiento(){
			$('#modalDlg').hide();
			var fechavencimiento=$('#txtFechaVencimiento').val();
			var url = '${pageContext.request.contextPath}/regReqControl.htm?action=validarFechaDiaHabil&fechavencimiento=' + fechavencimiento;

			$.ajax({					
					url : url,
					type : 'POST',
					async : false,
					dataType : "json",
					data : '',
					contentType : "application/json",
					mimeType : "application/json",
					timeout : 5000,
					success : function(response) {
						fechaVencimientoValida=response.fechaValida;
					},
					complete:function(){
						onDateChange=true;
						$('#frmDatosDocumento').bootstrapValidator('revalidateField', 'fecVencimiento');
					}
			});
		} 

		// $('#divFechaVencimiento').on('dp.change dp.show', function(e) {
		// 	// $('#frmDatosDocumento').bootstrapValidator('revalidateField', 'fecVencimiento');
		// 	validarFechaVencimientoRequerimiento();
		// });
		
		$('#divFechaVencimiento').on('dp.change', function(e) {
			// $('#frmDatosDocumento').bootstrapValidator('revalidateField', 'fecVencimiento');
			validarFechaVencimientoRequerimiento();
		});
// Fin [nchavez 26/05/2015]
		$('#frmDatosDocumento').bootstrapValidator({
			excluded: [':disabled'],
			fields: {
				fecVencimiento: {
                    validators: {
						date: {
							format: 'DD/MM/YYYY',
							message: excepciones.excepcion11
						},
						notEmpty: {
                            message: 'Seleccione una Fecha de Vencimiento.'
                        },
                        // Inicio [nchavez 27/05/2015]
                        callback:{
                        	message:' ',
                        	callback:function(value,validator,$field){
                        		if(onDateChange){
	                        			return {
									        valid: fechaVencimientoValida,// false or true
									        message: 'La Fecha debe ser un d&iacute;a h&aacute;bil.'
									    }
                        		}
                        		return {valid:true,message:''}; 
                        	}
                        }
                        // Fin [nchavez 26/05/2015]
                    }
                },
				numReqOrigen: {
				// Inicio [nchavez 26/05/2015]
                    validators: {
                    // Inicio [lestrada SNADE307-1062]
                     maxlength:{},
                     // Fin [lestrada SNADE307-1062]
                        message:' ',
                        callback:{
                        	callback:function(value,validator,$field){
                        		
                        		if (value.length==0) {
									return {
								        valid: false,       
								        message: 'Ingrese N&uacute;mero de Requerimiento Origen.'
								    }
                        		}else{
                        			if(onBlurAfter){
                        				if(formatErrorNReqOrigen){
                        					return {
                        						valid:false,
                        						message:'N&uacute;mero de Requerimiento Origen no v&aacute;lido.'
                        					}
                        				}
	                        			return {
									        valid: numReqOrigenValido,// false or true
									        // message: 'El  N&uacute;mero de Requerimiento Origen no es valido'
									        message:msgReqOrigenValido
									    }
                        			}
                        			if(onkeypressNReqOrigen){
                        				return {
                        					valid:true,
                        					message:' '
                        				}
                        			}
                        			return {
                        				valid:true,
                        				message:' '
                        			};
                        		}
                        	}
                        }
                    }
                  // Fin [nchavez 26/05/2015]  
                },
				tblDocumentos: {
					validators: {
						callback: {
                            message: excepciones.excepcion10,
                            callback: function (value, validator, $field) {
								if(!validarCheck()){
									return false;
								}
								return true;
                            }
                        }
						
					}
				}
			}	
		}).on('success.form.bv', function(e) {
            e.preventDefault();
			confirmarRegistro();
    	});
		
	})   
	
	function confirmarRegistro(){ 
		titulo = titulos.tituloDefecto;
		msj = "&iquest;Est&aacute; seguro que desea registrar el requerimiento?";
		botones = [];
		
		var botonSi = $("<input/>").attr(
			{
				value: "Si", 
				type: "button", 
				"class": "btn dlgButton btn-primary", 
				"data-dismiss" : "modal", 
				onclick : "registrarRequerimiento()"
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
	
	function validarCheck(){
		var cantidadChecks=0;
		var dataJson = $('#tblDocumentos').DataTable().rows().data();	
		for (var i = 0; i < dataJson.length; i++) {
			if($('#'+dataJson[i].codTipoDocumento).prop('checked')){
				cantidadChecks++;
			}
		}
		if(cantidadChecks>0){
			return true;
		}else{
			return false;
		}
		
	}
	
	function construirTablaRequerimiento(dataGrilla) {
    	
		$('#tblDocumentos').DataTable({
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
				{data : "numOrden", sClass: 'center alinearCentrado'},
				{data : "desTipoDocumentoCompuesto", sClass: 'left alinearCentrado'},
				{data : "codTipoDocumento", sClass: 'center alinearCentrado', 
					render : function(data, row){
						
						return jQuery('<input>').css(
							{
								"marginLeft"	:	"8px", 
								"width"			:	"18px", 
								"height"		:	"18px"
							}
						).attr(
							{
								type:'checkbox', 
								checked:  false, 
								"class" : "bChecked",
								id: data,
								onclick: 'habilidarMotivo(this.id)'
							}
						).wrap('<div></div>').parent().html();
					}
				},
				{data : "codTipoDocumento", sClass: 'center alinearCentrado', 
					render : function(data, row){
						
						return jQuery('<input>').css(
							{
								"marginLeft"	:	"8px", 
								"width"			:	"18px", 
								"height"		:	"18px"
								
							}
						).attr(
							{
								type:'txt', 
								"class" : "form-control tamanoMaximo",
								id: 'txt'+data,
								name: 'motivo[]',
								style: 'display:none',
								maxlength: '100',
								onpaste:'return false;'
							}
						).wrap('<div></div>').parent().html();
					}
				},
				{data : "codTipoDocumento", sClass: 'hidden'}
			],
			data: dataGrilla,
            ordering: true,
            searching: false,
            paging: false,
			//"sScrollY":"250px",
            bScrollAutoCss: true,
            bStateSave: false,
            bAutoWidth: false,
            bScrollCollapse: true,
            pagingType: "full_numbers",
            //iDisplayLength: 5,
            //responsive: true,
            bLengthChange: false,
			fnDrawCallback		:	function(oSettings) {
				var a = $('#tblDocumentos').width()
				$("#tblDocumentos_wrapper").css("min-width", a);
				
				$('#tblDocumentos_paginate').addClass('div100');
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
	
	function registrarRequerimiento(){

		$('#modalDlg').modal('hide');
		
		var datos = [];
		var contador=0;
	    var dataJson = $('#tblDocumentos').DataTable().rows().data();	
		for (var i = 0; i < dataJson.length; i++) {
			if($('#'+dataJson[i].codTipoDocumento).prop('checked')){
				var obj = new Object;
				obj.MOTIVO =$('#txt'+dataJson[i].codTipoDocumento).val();
				obj.CODTIPODOC =dataJson[i].codTipoDocumento;
				obj.DESCRIPCION =dataJson[i].desTipoDocumentoCompuesto;
				datos[contador] = obj;
				contador++;
			}
		}
		var listaDocumentosSel = JSON.stringify(datos);
		
		var numExpedienteVirtual = $('#txtExpedienteVirtual').val();
		// Inicio [nchavez 26/05/2015]
		var ruc=$('#txtRuc').val();
		var razonSocial=$('#txtRazonSocial').val();
		// Fin [nchavez 26/05/2015]
		var frmObject = new Object();
		frmObject.numExpedienteVirtual = $('#txtExpedienteVirtual').val();
		frmObject.numReqOrigen = $('#txtNmReqOrigen').val();
		frmObject.fecVencimiento = $('#txtFechaVencimiento').val();
		frmObject.listaDocumentosSel = datos;
		// Inicio [nchavez 26/05/2015]
		frmObject.ruc=ruc;
		frmObject.razonSocial=razonSocial;
		// Fin [nchavez 26/05/2015]
		// Inicio - [oachahuic][PAS20165E210400270]
		frmObject.desTipProceso = $('#txtProceso').val();;
		frmObject.desTipExpediente = $('#txtTipoExpediente').val();;
		// Fin - [oachahuic][PAS20165E210400270]
		
		setTimeout(
                function(){
						$.ajax({
							
							url : '${pageContext.request.contextPath}/regReqControl.htm?action=registrarRequerimiento',
							type : 'POST', //antes GET
							async : false,
							dataType : "json",
							data : JSON.stringify(frmObject),
							// Inicio [nchavez 26/05/2015]
							contentType : "application/json;charset=utf-8",
							// mimeType : "application/json",
							beforeSend:function(xhr){
								xhr.setRequestHeader('Accept', "application/json; charset=utf-8");
							}
							// Fin [nchavez 26/05/2015]
							,success : function(response) {
								
								var msjError = response.msjError;
								if(msjError!="" && msjError!=undefined){
									mostrarDlgInfo(titulos.tituloDefecto, msjError);
									return;
								}
								requerimientoCreado=true;
								mostrarDlgInfo(titulos.tituloDefecto, response.aviso01,requerimientoCreado);
								
							},
							error : function(response) {
								
								mostrarPagError();
							}
							
						});
         }, 100);
	}
	
	function revalidarFormulario(){
		$('#frmDatosDocumento').submit();
	}		
	
	function habilidarMotivo(id){
		
		if($('#'+id).prop('checked')){
			document.getElementById('txt'+id).style.display='inline';
			document.getElementById('txt'+id).focus();
		}else{
			document.getElementById('txt'+id).style.display='none';
			$('#txt'+id).val("");
		}
		$('#frmDatosDocumento').bootstrapValidator('revalidateField', 'tblDocumentos');
	}
	function mostrarPagError() {
		
		var formURL = '${pageContext.request.contextPath}/error.htm?action=cargarPaginaError';
		document.forms.frmDatosDocumento.action = formURL;
		document.forms.frmDatosDocumento.method = "post";
		document.forms.frmDatosDocumento.submit();
		
	}
	
	function mostrarDlgInfo(titulo, msj, requerimientoCreado){ 
				
		botones = [];
		var aceptar;
		if(!requerimientoCreado){
			aceptar = $("<input/>").attr(
				{
					value: "Aceptar", 
					type: "button", 
					"class": "btn dlgButton btn-primary", 
					"data-dismiss" : "modal", 
					onclick : "$('#modalDlg').modal('hide')"
				}
			);
		}else{
			aceptar = $("<input/>").attr(
				{
					value: "Aceptar", 
					type: "button", 
					"class": "btn dlgButton btn-primary", 
					"data-dismiss" : "modal", 
					onclick : "volverPagina()"
				}
			);
		}
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
		
		$('#modalDlg').modal({backdrop: 'static', keyboard: false}) ;
		$('#modalDlg').modal('show');
		
		
	}
	function validarSalida(){
		
		if(validarCheck()){
			titulo = titulos.tituloDefecto;
			msj = "Existen tipos de documentos seleccionados, &iquest;Desea Salir?";
			botones = [];
			
			var botonSi = $("<input/>").attr(
				{
					value: "Si", 
					type: "button", 
					"class": "btn dlgButton btn-primary", 
					"data-dismiss" : "modal", 
					onclick : "volverPagina()"
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
		
		}else{
			volverPagina();
		}
	}
	function volverPagina(){
		$('html').block({message: '<h1>Regresando...</h1>'});
		var numExpedienteConsultar = $('#txtExpedienteVirtual').val();
		var  url = '${pageContext.request.contextPath}/regReqControl.htm?action=consultarDetalleExpedienteVirtualView&numExpedienteVirtual='+numExpedienteConsultar;
		$(location).prop( 'href', url);
		
	}
	
	function permitirNumeros(e){
		
		var keynum = window.event ? window.event.keyCode : e.which;
		
		if ((keynum == 8)) {
			
			return true;
			
		}
		
		return /\d/.test(String.fromCharCode(keynum));
		
	}
 </script>
</html>
