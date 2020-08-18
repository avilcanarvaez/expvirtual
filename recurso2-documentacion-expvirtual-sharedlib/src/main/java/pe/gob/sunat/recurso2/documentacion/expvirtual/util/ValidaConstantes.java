package pe.gob.sunat.recurso2.documentacion.expvirtual.util;

import java.nio.charset.Charset;

public class ValidaConstantes {
	
	public static final String CARGA_INVALIDA = "-1";
	public static final String CARGA_INICIAL = "0";
	public static final String CARGA_POSTERIOR = "1";
	public static final String CARGA_SUPERIOR = "2";
	
	public static final String FILTRO_INVALIDO = "-1";
	public static final String FILTRO_CERO = "0";
	public static final String FILTRO_UNO = "1";
	public static final String FILTRO_DOS = "2";
	public static final String FILTRO_TRES = "3";
	public static final String FILTRO_CUATRO = "4";
	
	//public static final String IND_<_EXPE_GENERAL = "0";
	public static final String IND_TIPO_EXPE_DISPONIBLE = "1";
	public static final String IND_TIPO_EXPE_ASOCIADO = "2";
	
	public static final String IND_TIPO_DOCU_DISPONIBLE = "1";
	public static final String IND_TIPO_DOCU_ASOCIADO = "2";
	
	public static final String IND_RESPO_DISPONIBLE = "1";
	public static final String IND_RESPO_ASOCIADO = "2";
	
	public static final String PATRON_CAMBIO_MENSAJES_0 = "{0}";
	public static final String PATRON_CAMBIO_MENSAJES_1 = "{1}";
	public static final String PATRON_CAMBIO_MENSAJES_2 = "{2}";
	
	public static final String CADENA_VACIA = "";
	
	public static final String SEPARADOR_GUION = "-";
	public static final String SEPARADOR_COMA = ",";
	public static final String SEPARADOR_ESPACIO = " ";
	public static final String IND_TIP_SEG_WS = "01";
	public static final String IND_TIP_SEG_EE = "02";
	public static final String IND_TIP_SEG_CC = "03";
	// Inicio [jquispe 27/05/2016] Codigo del tipo de seguimiento para el Usuario Interno
	public static final String IND_TIP_SEG_CI = "04";
	// Fin [jquispe 27/05/2016]
	
	public static final String IND_WS_ECM_CONSULTA = "DES01";
	public static final String IND_WS_ECM_GENERA = "GEN03";
	public static final String IND_WS_ECM_ID_CLASE = "ID_CLASE";
	public static final String IND_WS_ECM_ID_FOLDER = "ID_FOLDER";
	public static final String IND_WS_ECM_REPOSITORY_ID = "ECM";
	public static final String IND_WS_ECM_DESC_CARP = "EXPEDIENTE";
	
	// Clases de Expediente
	
	public static final String IND_CLASE_EXPEDIENTE_ORIGEN = "1";
	public static final String IND_CLASE_EXPEDIENTE_VIRTUAL = "2";
	
	// Estados del Expediente Virtual
	
	public static final String IND_ESTADO_EXP_VIRTUAL_ABIERTO = "01";
	public static final String IND_ESTADO_EXP_VIRTUAL_CERRADO = "02";
	public static final String IND_ESTADO_EXP_VIRTUAL_ELIMINADO = "03";
	
	// CODIGO SERVICIO WS 
	public static final String IND_CODSERV_GENERA_EXP_VIRT = "01";
	public static final String IND_CODSERV_REC_ANEXDOC_EXP_VIRT = "02";
	public static final String IND_CODSERV_SOL_GENDOC_ANEX_EXP_VIRT = "03";
	public static final String IND_CODSERV_CONS_ENCAB_EXP_VIRT = "04";
	public static final String IND_CODSERV_CONS_LIST_DOC_ANEX_EXP_VIRT = "05";
	public static final String IND_CODSERV_LEER_DOC_EXP_VIRT = "06";
	public static final String IND_CODSERV_GEN_REQ = "07";
	public static final String IND_CODSERV_CONS_EXP_VIRT_CRIT = "08";
	public static final String IND_CODSERV_CERRAR_EXP_VIRT = "09";
	public static final String IND_CODSERV_REC_ACT_RESP_EXP_VIRT = "10";
	public static final String IND_CODSERV_CONS_EST_REQ = "11";
	public static final String IND_CODSERV_ACTUALIZA_DOC_VISIBLE = "12";
	public static final String IND_CODSERV_ACTUALIZA_ESTADO_ETAPA_EXP = "13";
	public static final String IND_CODSERV_REAPERTURA_EXP_VIRT = "14";
	public static final String IND_CODSERV_ASIGNAR_RESP = "15";
	
	//Inicio staype 26/12/2019 [PAS20191U210500291] 
	public static final String IND_CODSERV_CONS_ACT_FECVEN = "17";
	//Constante para insertar en tabla detalle requerimiento
	public static final String IND_ESTADO_REQUERIMIENTO_NOPRESENTADO = "01";
	public static final int CARGA_INICIAL_NUM_CORDOC = 0;
	//Fin staype 26/12/2019 [PAS20191U210500291] 
	
	//Inicio CVG
			public static final String IND_CODSERV_VIN_EXP = "18";
		//Fin CVG
	
	// EXTENSIONES DE ARCHIVOS VALIDOS
	public static final String VALID_PATTERN_EXT = "(pdf|doc|docx|xls|xlsx|ppt|pptx|jpg|png|gif|bmp)";
	
	public static final String VALID_EXT_ARCH_PLANO = "(txt)";
	
	// HTTP ACCEPT
	public static final String HTTP_ACCEPT_JSON = "application/json";
	public static final String HTTP_ACCEPT_PDF = "application/pdf";
	
	// HTTP METHOD
	public static final String HTTP_METHOD_GET = "GET";
	public static final String HTTP_METHOD_POST = "POST";
	
	// LINK HYPERMEDIA
	public static String LINK_HREF_BASE_WS = "http://api.sunat.peru/v1/recurso/documentacion/expvirtual/e/documentosstream";
	public static String LINK_DOC_STREAM_REL = "documentosstream";
	
	// WS BUZON
	public static final String URL_WS_ENVIA_BUZON = "http://api.sunat.peru:10021/cl-ti-iawsbuzon/ws-enviabuzon";
	
	/* Formatos de Fecha */
	public static final String FORMATO_FECHA_VISTA = "dd/MM/yyyy";
	public static final String FORMATO_FECHA_REGISTRO = "yyyy-MM-dd";
	// Inicio [nchavez 26/05/2015]
	public static final String FORMATO_FECHA_VISTA_HORA = "dd/MM/yyyy HH:mm:ss";
	// Fin [nchavez 26/05/2015]
	public static final String FECHA_VACIA = "0001-01-01";
	public static final String COD_USUARIO_VACIO = "-";
	//Inicio [jquispe 01/06/2016] Numero de RUC cuando es vacio.
	public static final String NUM_RUC_VACIO = "-";
	//Fin [jquispe 01/06/2016]
	
	public static final String IND_REGI_NO_ELIMINADO = "0";
	public static final String IND_REGI_SI_ELIMINADO = "1";
	
	public static final String IND_REGI_NO_HABILITADO = "0";
	public static final String IND_REGI_SI_HABILITADO = "1";
	
	public static final String IND_INVISIBLE_CONTRIBUYENTE = "0";
	public static final String IND_VISIBLE_CONTRIBUYENTE = "1";
	
	public static final String IND_SIN_RESERVA_TRIBUTARIA = "0"; //[PAS20191U210500144]
	
	public static final String IND_ESTADO_REQ_ABIERTO = "01";
	public static final String IND_ESTADO_REQ_CERRADO = "02";
	
	public static final String IND_ORIGEN_REQ_MANUAL = "01";
	public static final String IND_ORIGEN_REQ_AUTOMATICO = "02";
	// Inicio [nchavez 27/05/2016]
	public static final String DES_ORIGEN_REQ_DIGITALIZADO = "Digitalizado";
	public static final String DES_ORIGEN_REQ_AUTOMATICO = "Autom&#225;tico";
	// Fin [nchavez 26/05/2015]
	public static final String CANT_REG_CONSULTA = "15";
	
	public static final Integer LIMITE_INFERIOR_DEPENDENCIA = 11;
	public static final Integer LIMITE_SUPERIOR_DEPENDENCIA = 35;
	
	public static final String BUSQUEDA_POR_EXPEDIENTE_VIRTUAL = "2";
	public static final String BUSQUEDA_POR_DOCUMENTO_ORIGEN = "1";
	
	public static final String BUSQUEDA_POR_FECHA_EXPEDIENTE_VIRTUAL = "1";
	public static final String BUSQUEDA_POR_FECHA_DOCUMENTO_ORIGEN = "2";
	
	//Constante tabla T6620requerim
	public static final String IND_ESTADO_REQUERIMIENTO_ABIERTO = "01";
	public static final String IND_ESTADO_REQUERIMIENTO_CERRADO = "02";
	//Inicio - [oachahuic][PAS20175E210400016]
	public static final String IND_ESTADO_REQUERIMIENTO_ATENDIDO = "03";
	public static final String IND_ESTADO_REQUERIMIENTO_ATENDIDO_PARCIAL = "04";
	public static final String IND_ESTADO_REQUERIMIENTO_ELIMINADO = "05";
	//Inicio - [oachahuic][PAS20175E210400016]

	// Inicio - Constante tabla T6613docexpvirt
	public static final String IND_ESTADO_REQUERIMIENTO_SOLICITADO = "01";
	public static final String IND_ESTADO_REQUERIMIENTO_ENTREGADO = "02";
	public static final String DES_ESTADO_REQUERIMIENTO_SOLICITADO = "Solicitado";
	public static final String DES_ESTADO_REQUERIMIENTO_ENTREGADO = "Entregado";
	
	public static final String DES_ESTADO_REQUERIMIENTO_ABIERTO = "Abierto";
	public static final String DES_ESTADO_REQUERIMIENTO_CERRADO = "Cerrado";
	//Inicio - [oachahuic][PAS20175E210400016]
	public static final String DES_ESTADO_REQUERIMIENTO_ATENDIDO = "Atendido";
	public static final String DES_ESTADO_REQUERIMIENTO_ATENDIDO_PARCIAL = "Atendido Parcial";
	public static final String DES_ESTADO_REQUERIMIENTO_ELIMINADO = "Eliminado";
	//Fin - [oachahuic][PAS20175E210400016]
	
	public static final String DES_ESTADO_EXPEDIENTE_ABIERTO = "Abierto";
	public static final String DES_ESTADO_EXPEDIENTE_CERRADO = "Cerrado";
	public static final String DES_ESTADO_EXPEDIENTE_ELIMINADO= "Eliminado";
	
	public static final String IND_TIP_DOC_SUST_ORIGEN = "01";
	public static final String IND_TIP_DOC_SUST_CIERRE = "02";
	public static final String IND_TIP_DOC_SUST_REQUERIM = "03";
	public static final String IND_TIP_DOC_SUST_OTRO = "04";
	
	public static final String DES_TIP_DOC_SUST_ORIGEN = "Origen";
	public static final String DES_TIP_DOC_SUST_CIERRE = "Cierre";
	public static final String DES_TIP_DOC_SUST_REQUERIM = "Requerimiento";
	public static final String DES_TIP_DOC_SUST_OTRO = "Otro";
	
	public static final String IND_ORIGEN_EXP_VIRT_MANUAL = "01";
	public static final String IND_ORIGEN_EXP_VIRT_AUTOMATICO = "02";
	
	public static final String IND_ORIGEN_DOCUMENTO_MANUAl_INTRANET = "01";
	public static final String IND_ORIGEN_DOCUMENTO_MANUAl_INTERNET = "02";
	public static final String IND_ORIGEN_DOCUMENTO_AUTOMATICO = "03";
	public static final String IND_ORIGEN_DOCUMENTO_INTERNO = "04";
	// Fin - Constante tabla T6613docexpvirt
	
	/* Origen de Cierre Expediente Virtual */
	
	public static final String IND_ORIGEN_CIERRE_EXPVIRT_MANUAL = "01";
	public static final String IND_ORIGEN_CIERRE_EXPVIRT_AUTOMATICO = "02";
	
	/* Constantes por tipo de negocio */
	
	public static final String TIPO_EXPE_COBRANZA_COACTIVA = "340";
	public static final String TIPO_EXPE_CARTA_INDUCTIVA = "441";
	public static final String TIPO_EXPE_ESQUELA = "442";
	//[PAS20191U210500144] Inicio
	public static final String TIPO_EXPE_FISCA_DEFINITIVA_PARCIAL = "430";
	public static final String TIPO_EXPE_CRUCE_INFORMACION = "431";
	//[PAS20191U210500144] Fin 
	public static final String TIPO_EXPE_CRUCE_INFO_OCP = "432";
	
    //PAS20191U210500144 Inicio 400101 RF03, RF04, RF13, RF25 PSALDARRIAGA 	
	public static final String TIPO_EXPE_FISCA_DEF_PAR = "430";
	public static final String TIPO_EXPE_CRUCE_INFO = "431";
	//PAS20191U210500144 Fin 400101 RF03, RF04, RF13, RF25 PSALDARRIAGA 

	/* Constantes Tipo Dependencia */ 
	public static final String IND_TIPO_DEPENDENCIA_INTRANET = "1"; 
	
	// Constantes flag invocacion al servicio
	public static final String IND_INVOC_SERVICIO_AUTOMATICO = "1";	//Automático
	public static final String IND_INVOC_SERVICIO_MANUAL = "2";		//Manual
	
	// Constantes flag tipo de responsable
	public static final String IND_RESP_PRINCIPAL = "1";	//Principal
	public static final String IND_RESP_ASOCIADO = "2";		//Asociado
	 
	//ruta tempo
	public static final String RUTA_TEMP_LOCAL_POR_FIRMAR = "/data0/uploads/";//[PAS20191U210500144] Ruta para firmar documento
	public static final String RUTA_TEMP_LOCAL_SPRING = "/data0/uploads/spring/";
	public static final String FECHA_TEMP_ARCHIVO = "dd-MM-yyyy_hh-mm-ss";
	
	
	public static final String RUTA_SERVIDOR_ARCHIVO = "/data0/uploads/spring/";
	public static final String CARPETA_TEMPORAL = "/data0/uploads/";
	public static final String CARPETA_TEMPORAL_TEMPO = "/data0/tempo/";
	
	//Flag de firma digital
	public static final String IND_FIRMA_DIGITAL = "1";//[PAS20191U210500144]
	
	//Nombre archivo de errores al generar Pedido.
	
	public static final String NOM_ARCH_ERROR = "Errores_Al_Generar_Pedido.txt";
	
	//Constantes Acciones Internet
	
	public static final String ACCION_INTERNET_CONSULTAR ="01";
	public static final String ACCION_INTERNET_SUBIRDOCUMENTOS ="02";
	public static final String ACCION_INTERNET_EXPORTAREXCEL ="03";   
	public static final String ACCION_INTERNET_INGRESAR_OPC_MENU ="04";
	public static final String ACCION_INTERNET_VER_DOCUMENTOS ="05";
	public static final String ACCION_INTERNET_DESCARGAR_DOCUMENTO ="06";
	
	// Inicio [jquispe 27/05/2016] Constantes Acciones Intranet	
	public static final String ACCION_INTRANET_CONSULTAR ="07";
	public static final String ACCION_INTRANET_SUBIRDOCUMENTOS ="08";
	public static final String ACCION_INTRANET_REGISTRAR_EXPEDIENTE ="09";
	public static final String ACCION_INTRANET_ASIGNAR_RESPONSABLE ="10";	
	public static final String ACCION_INTRANET_CERRAR_EXPEDIENTE ="11";
	public static final String ACCION_INTRANET_REGISTRAR_REQUERIMIENTO ="12";	
	public static final String ACCION_INTRANET_REGISTRAR_OBSERVACION ="13";
	public static final String ACCION_INTRANET_VER_OBSERVACIONES ="14";
	public static final String ACCION_INTRANET_VER_EXPEDIENTE_DOCUMENTOS ="15";
	public static final String ACCION_INTRANET_VER_ESTADO_ETAPA_EXPEDIENTE ="16";
	public static final String ACCION_INTRANET_VER_REQUERIMIENTOS ="17";
	public static final String ACCION_INTRANET_VER_REQUERIMIENTO_DOCUMENTOS ="18";
	// Fin [jquispe 27/05/2016]
	public static final String ACCION_INTRANET_MODIFICAR_DOCUMENTO_INTERNO ="19";//[PAS20191U210500144] - ETITO
	
	
	/*
	public static final String DES_ACCION_INTERNET_CONSULTAR ="Consultar";
	public static final String DES_ACCION_INTERNET_SUBIRDOCUMENTOS ="Subir Documentos";
	public static final String DES_ACCION_INTERNET_EXPORTAREXCEL ="Exportar a Excel";  
	public static final String DES_ACCION_INTERNET_EXPORTAREXCELDOCS ="Exportar a Excel";
	public static final String DES_ACCION_INTERNET_INGRESAR_OPC_MENU ="Ingresar a Opción del Menú";
	public static final String DES_ACCION_INTERNET_VER_DOCUMENTOS ="Ver Documentos";
	public static final String DES_ACCION_INTERNET_DESCARGAR_DOCUMENTO ="Descargar Documento";
	*/
	//Respuesta de la acción realizada
	public static final String RESPUESTA_ACCION_OK = "01";
	public static final String RESPUESTA_ACCION_ERROR = "02";
		
		
	//Constantes de opciones del menu Internet
	public static final String OPCION_INTERNET_ATENCION_REQUERIM ="01";
	public static final String OPCION_INTERNET_CONSULTA_REQUERIM ="02";
	public static final String OPCION_INTERNET_CONSULTA_EXPEDIENTES ="03";
	
	// Inicio [jquispe 27/05/2016] Constantes de opciones del menu Intranet	
	
	public static final String OPCION_INTRANET_ASIGNACION_RESPONSABLE ="04";
	public static final String OPCION_INTRANET_REGISTRO_EXPEDIENTE ="05";	
	public static final String OPCION_INTRANET_CIERRE_EXPEDIENTE ="06";
	public static final String OPCION_INTRANET_REGISTRO_DOCUMENTO_INTERNO ="07";
	public static final String OPCION_INTRANET_REGISTRO_REQUERIMIENTO ="08";	
	public static final String OPCION_INTRANET_ATENCION_REQUERIMIENTO ="09";	
	public static final String OPCION_INTRANET_REGISTRO_OBSERVACION ="10";
	public static final String OPCION_INTRANET_CONSULTA_SEGUIMIENTO ="11";
	public static final String OPCION_INTRANET_CONSULTA_EXPEDIENTE ="12";
	public static final String OPCION_INTRANET_CONSULTA_OBSERVACION ="13";
	public static final String OPCION_INTRANET_CONSULTA_REQUERIMIENTO ="14";
	public static final String OPCION_INTRANET_MODIFICACION_DOCUMENTO_INTERNO ="15";
	
	// Fin [jquispe 27/05/2016]
	
	//Constantes Tipo Archivo ECM
	
	public static final String TIPO_ARCHIVO_ECM_XLS="VND.MS-EXCEL";
	public static final String TIPO_ARCHIVO_ECM_XLSX="VND.OPENXMLFORMATS-OFFICEDOCUMENT.SPREADSHEETML.SHEET";
	public static final String TIPO_ARCHIVO_ECM_DOC="MSWORD";
	public static final String TIPO_ARCHIVO_ECM_DOCX="VND.OPENXMLFORMATS-OFFICEDOCUMENT.WORDPROCESSINGML.DOCUMENT";
	public static final String TIPO_ARCHIVO_ECM_PPT="VND.MS-POWERPOINT";
	public static final String TIPO_ARCHIVO_ECM_PPTX="VND.OPENXMLFORMATS-OFFICEDOCUMENT.PRESENTATIONML.PRESENTATION";
	public static final String TIPO_ARCHIVO_ECM_JPG_JPEG="PJPEG";
	public static final String TIPO_ARCHIVO_ECM_PNG="X-PNG";
	public static final String TIPO_ARCHIVO_ECM_TIF_TIFF="TIFF";
	
	//Constantes Descarga Archivos
	public static final String TIPO_ARCHIVO_PDF = "PDF";
	public static final String TIPO_ARCHIVO_JPG = "JPG";
	public static final String TIPO_ARCHIVO_PNG = "PNG";
	public static final String TIPO_ARCHIVO_JPEG = "JPEG";
	public static final String TIPO_ARCHIVO_TIF = "TIF";
	public static final String TIPO_ARCHIVO_TIFF = "TIFF";
	public static final String TIPO_ARCHIVO_DOC = "DOC";
	public static final String TIPO_ARCHIVO_DOCX = "DOCX";
	public static final String TIPO_ARCHIVO_XLS = "XLS";
	public static final String TIPO_ARCHIVO_XLSX = "XLSX";
	public static final String TIPO_ARCHIVO_PPT = "PPT";
	public static final String TIPO_ARCHIVO_PPTX = "PPTX";
	
	// Accesos de Aplicativo (Para tamaño maximo de archivo(s))
	public static final String COD_ACCESO_INTERNET = "IT";
	public static final String COD_ACCESO_INTRANET = "IA";
	
	/* Códigos de Documentos Especiales */
	
	public static final String COD_TIPO_DOCU_CONS_PRESENT_DOCUMENTO = "958002"; // Tipo de Documento para el cierre del Expediente
	public static final String COD_TIPO_DOCU_INDICE_CIERRE = "958001"; // Tipo de Documento para el cierre del Expediente
	public static final String COD_TIPO_DOCU_EXP_TRAB_CARATURA = "961002"; // Tipo de Documento para la caratula del exp. de trabajo
	public static final String COD_TIPO_DOCU_REPORTE_ADUANERO = "961003"; // Tipo de Documento para el reporte aduanero
	public static final String COD_TIPO_DOCU_REPORTE_RUBRO = "961004"; // Tipo de Documento para el reporte rubro
	public static final String COD_TIPO_DOCU_REPORTE_FORMATO = "961005"; // Tipo de Documento para el reporte formato
	public static final String COD_TIPO_DOCU_REPORTE_CONSOLIDADO = "961006"; // Tipo de Documento para el reporte formato
	
	public static final String USUARIO_EXPEDIENTE_VIRTUAL = "SIEV"; 
	public static final String INDICADOR_ELIMINADO_NO_ASOC_TIPEXP_TIPDOC = "0";
	public static final String INDICADOR_VISIBILE_CONTRIBUYENTE = "1";
	public static final String INDICADOR_NO_VISIBILE_CONTRIBUYENTE = "0";
	
	public static final String NO_ERROR = "000";
	public static final String ERROR_AL_SUBIR_ARCHIVOS = "001";
	
	// Estados del Pedido de Reporte
	public static final String IND_ESTADO_PEDIDO_REP_PENDIENTE = "01";
	public static final String IND_ESTADO_PEDIDO_REP_GENERADO = "02";
	
	// Estados del Reporte Generado por Pedido
	public static final String IND_ESTADO_REP_GEN_PEDIDO_PENDIENTE = "01";
	public static final String IND_ESTADO_REP_GEN_PEDIDO_GENERADO = "02";
	//public static final String IND_ESTADO_REP_GEN_PEDIDO_INCOMPLETO = "03";//Inicio [gangles 12/06/2016] se elimina  el estado incompleto //Fin gangles [gangles 12/06/2016]
	public static final String IND_ESTADO_REP_GEN_PEDIDO_PROCESANDO = "04";
	public static final String IND_ESTADO_REP_GEN_PEDIDO_ERROR = "05";	
	public static final String IND_ESTADO_REP_GEN_PEDIDO_PARCIALMENTE = "03";//Inicio [gangles 02/06/2016] Nuevo estado en la generación de pedidos // Fin [gangles 02/06/2016]
		
	public static final String DES_IND_ESTADO_REP_GEN_PEDIDO_PENDIENTE = "Pendiente";
	public static final String DES_IND_ESTADO_REP_GEN_PEDIDO_GENERADO =  "Generado";
	//public static final String DES_IND_ESTADO_REP_GEN_PEDIDO_INCOMPLETO =  "Incompleto";//Inicio [gangles 12/06/2016] se elimina  el estado incompleto //Fin gangles [gangles 12/06/2016]
	public static final String DES_IND_ESTADO_REP_GEN_PEDIDO_PROCESANDO =  "Procesando";
	public static final String DES_IND_ESTADO_REP_GEN_PEDIDO_ERROR = "Error";
	public static final String DES_IND_ESTADO_REP_GEN_PEDIDO_PARCIALMENTE = "Generado parcialmente";//Inicio [gangles 02/06/2016] Nuevo estado en la generación de pedidos // Fin [gangles 02/06/2016]
		
	// Estados del Reporte Generado por Expediente
	public static final String IND_ESTADO_REP_GEN_EXP_PENDIENTE = "01";
	public static final String IND_ESTADO_REP_GEN_EXP_GENERADO = "02";
	//public static final String IND_ESTADO_REP_GEN_EXP_INCOMPLETO = "03";//Inicio [gangles 12/06/2016] se elimina  el estado incompleto //Fin gangles [gangles 12/06/2016]
	public static final String IND_ESTADO_REP_GEN_EXP_ERROR = "04";
	public static final String IND_ESTADO_REP_GEN_EXP_PARCIALMENTE = "03";//Inicio [gangles 02/06/2016] Nuevo estado en la generación de pedidos // Fin [gangles 02/06/2016]
	
	public static final String DES_IND_ESTADO_REP_GEN_EXP_PENDIENTE =  "Pendiente";
	public static final String DES_IND_ESTADO_REP_GEN_EXP_GENERADO = "Generado";
	//public static final String DES_IND_ESTADO_REP_GEN_EXP_INCOMPLETO = "Incompleto";
	public static final String DES_IND_ESTADO_REP_GEN_EXP_ERROR = "Error";
	public static final String DES_IND_ESTADO_REP_GEN_EXP_PARCIALMENTE = "Generado parcialmente";//Inicio [gangles 02/06/2016] Nuevo estado en la generación de pedidos // Fin [gangles 02/06/2016]
	
	// Estados del Reporte Generado por Documento
	public static final String IND_ESTADO_REP_GEN_DOC_PENDIENTE = "01";
	public static final String IND_ESTADO_REP_GEN_DOC_GENERADO = "02";
	public static final String IND_ESTADO_REP_GEN_DOC_ERROR = "04";
	public static final String IND_ESTADO_REP_GEN_DOC_PARCIALMENTE ="03";//Inicio [gangles 02/06/2016] Nuevo estado en la generación de pedidos // Fin [gangles 02/06/2016]	
	
	public static final String DES_IND_ESTADO_REP_GEN_DOC_PENDIENTE =  "Pendiente";
	public static final String DES_IND_ESTADO_REP_GEN_DOC_GENERADO = "Generado";
	public static final String DES_IND_ESTADO_REP_GEN_DOC_ERROR =  "Error";
	public static final String DES_IND_ESTADO_REP_GEN_DOC_PARCIALMENTE = "Generado parcialmente";//Inicio [gangles 02/06/2016] Nuevo estado en la generación de pedidos // Fin [gangles 02/06/2016]	
		
	public static final String IND_ESTADO_PLANTILLA_ACTIVO = "01";
	public static final String IND_ESTADO_PLANTILLA_INACTIVO = "02";
	
	// Indicador detalle plantilla
	public static final String IND_SEL_PLANTILLA_DET_SEL = "1";
	public static final String IND_SEL_PLANTILLA_DET_NO_SEL = "0";
	// parametro Split
	
	public static final String PARAM_SPLIT = "\\|";
	public static final String PARAM_PALOTE = "|";
	

	// Indicadores Visibilidad Documento
	public static final String IND_VISIBLE_DOC = "1";
	public static final String IND_NO_VISIBLE_DOC = "0";
	
	//indicador de expediente acumulado
	public static final String IND_ACUMULADOR_INDEPENDIENTE = "1";
	public static final String IND_ACUMULADOR_ACUMULADO = "3";
	public static final String IND_ACUMULADOR_ACUMULADOR = "2";
	
	//numero RC acumulacion
	public static final String NUMERO_RC_ACUMULADOR = "007301";
	
	//Registro Doc Internos.
	public static final String IND_REG_DOC_INT = "0A0";
	
	//TODO: Falta colocar la descripción de los índices de acumulación en parámetros
	public static final String DES_IND_ACUMULADOR_INDEPENDIENTE = "Independiente";
	public static final String DES_IND_ACUMULADOR_ACUMULADO = "Acumulado";
	public static final String DES_IND_ACUMULADOR_ACUMULADOR = "Acumulador";
	
	// lista de procesos que no envian alertas
	public static final String COD_PROCESO_COBRANZA_NO_ALERTA = "001";
	
	// Indicador de acum de documento
	public static final String IND_ACU_SI = "1";
	public static final String IND_ACU_NO = "0";
	
    //PAS20191U210500144 Inicio 400101 RF06, 400104 RF03 PSALDARRIAGA
	// Indicador de reserva tributaria
	public static final String IND_RESTRI_NO = "0";
	public static final String IND_RESTRI_SI = "1";
	//PAS20191U210500144 Fin 400101 RF06, 400104 RF03 PSALDARRIAGA

	// Inicio [eaguilar 27/05/2016] FLAG PARA INICIAR PROCESO DE DESACUMULACION
	public static final String IND_ACU_DESACU = "2";
	// Fin [eaguilar 27/05/2016]
	// Indicador Report Tributario Aduanero
	public static final String IND_SI_REPORTE_TRIB_ADUANERO = "1";
	public static final String IND_NO_REPORTE_TRIB_ADUANERO = "0";
		
	//NUM EXPEDIENTE ACUMULADOR
	public static final String NUM_EXPEDIENTE_ACUMLADOR = "0";
	
	// Indicador Tipo de Generacion Pedido
	public static final String IND_TIPO_GEN_PEDIDO_RUC = "01";
	public static final String IND_TIPO_GEN_PEDIDO_MASIVO = "02";
	
	// Nombre archivo caratula Exp. Trabajo
	public static final String NOM_ARCHIVO_EXP_TRABAJO_CARATULA = "CaratulaExpTrabajo";
	public static final String NOM_ARCHIVO_EXP_TRABAJO_REP_ADUANERO = "ReporteAduanero";
	public static final String NOM_ARCHIVO_EXP_TRABAJO_REP_RUBRO = "ReporteRubro";
	public static final String NOM_ARCHIVO_EXP_TRABAJO_REP_FORMATO = "ReporteFormato";
	public static final String NOM_ARCHIVO_EXP_TRABAJO_REP_CONSOLIDADO = "ReporteConsolidado";
	public static final String NOM_ARCHIVO_EXP_TRABAJO_REP_GENERADO = "ReporteGen";
	
	// Nombre archivo caratula Exp. Trabajo
	public static final String METADATA_EXP_TRABAJO_CARATULA = "caratula;expediente trabajo;";
	public static final String METADATA_EXP_TRABAJO_REP_ADU = "reporte aduanero;expediente trabajo;";
	public static final String METADATA_EXP_TRABAJO_RUBRO = "reporte rubro;expediente trabajo;";
	public static final String METADATA_EXP_TRABAJO_FORMATO = "reporte formato;expediente trabajo;";
	public static final String METADATA_EXP_TRABAJO_CONSOLIDADO = "reporte consolidado;expediente trabajo;";
	public static final String METADATA_EXP_TRABAJO_GENERADO = "reporte;expediente trabajo;";
	
	// Formatos Adicionales - Plantillas
	public static final String PLANT_FORMATO_ADIC_HOJA_AV = "001";                                                                                                           
	public static final String PLANT_FORMATO_ADIC_ACTA_FOR_INTERV = "002";                                                                                      
	public static final String PLANT_FORMATO_ADIC_ACTA_INSTAL = "003";                                                                                 
	public static final String PLANT_FORMATO_ADIC_ACTA_ARQUEO_INICIAL = "004";                                                                              
	public static final String PLANT_FORMATO_ADIC_ACTA_ARQUEO_FINAL = "005";                                                                     
	public static final String PLANT_FORMATO_ADIC_ACTA_CONTRIBUY = "006";                                                                                                            
	public static final String PLANT_FORMATO_ADIC_HOJA_RELEVA = "007"; 
	// Inicio [gangles 25/05/2016] Se agregan tres formatos adicionales (Módulo 5)
	public static final String PLANT_FORMATO_ADIC_ACTA_TOMA_DICHO = "006"; // Acta de toma de dicho	- Medida cautelar previa de embargo en forma de retención 
	public static final String PLANT_FORMATO_ADIC_ANEXO_ACTA = "007"; // Anexo al acta de embargo
	public static final String PLANT_FORMATO_ADIC_ACTA_EMBARGO = "008"; // Acta de embargo depósito sin extracción
	// Fin [gangles 25/05/2016] 

	// Formatos Rubros - Plantillas
	public static final String PLANT_RUBRO_DATOS_PADRON = "001";      
	public static final String PLANT_RUBRO_COMPROBANTE_PAGO = "002";
	public static final String PLANT_RUBRO_DJ_DETERM = "003";
	public static final String PLANT_RUBRO_DJ_INFOR = "004";
	public static final String PLANT_RUBRO_OPER_COMEX = "005";
	public static final String PLANT_RUBRO_DEUDA_TRIB = "006";
	public static final String PLANT_RUBRO_SOL_DEV = "007";
	
	// Usuario exp. trab batch
	public static final String COD_USUARIO_EXPTRAB_BATCH = "SIEV";
	
	// Maximo tamaño en el campo metadata
	public static final Integer MAX_TAM_METADATA = new Integer(255);
	public static final Integer MAX_TAM_DES_EXPEDIENTE = new Integer(150);
	public static final Integer MAX_TAM_NOM_ARCHIVO = new Integer(100);
	
	// Indicadores Emisión Alerta
	public static final String IND_EMI_ALERTA_SI = "1";
	public static final String IND_EMI_ALERTA_NO = "0";
	
	// Indicador para Documento Relacionado
	public static final String NUM_DEFAULT_DOC_RELACIONADO = "0";
	
	// T6623tipdocexp - Indicador tipo de documento - [oachahuic][PAS20175E210400016]
	public static final String IND_CLASE_TIP_DOC_APERTURA = "01";
	public static final String IND_CLASE_TIP_DOC_CIERRE = "02";
	public static final String IND_CLASE_TIP_DOC_REQUERIMIENTO = "03";
	public static final String IND_CLASE_TIP_DOC_INTERNO = "04";
	public static final String IND_CLASE_TIP_DOC_REAPERTURA = "05";
	public static final String IND_CLASE_TIP_DOC_OTRO = "06";
	
	// Estado Exp. Origen
	public static final String IND_ESTADO_EXP_ORIGEN_ACTIVO = "01";
	// Etapa Exp. Origen
	public static final String IND_ETAPA_EXP_ORIGEN_EMITIDO = "01";
	// Inicio [nchavez 26/05/2015]
	public static final String CODIGO_RC_ITERVENCION_INFORMACION="007002";
	public static final String CODIGO_RC_FORMATO_LIBRE="007900";
	public static final Charset CHARSET_UTF8=Charset.forName("UTF-8");
	public static final String CODIGO_AGRUPACION_DOC_COBRANZA_COACTIVA="007";
	// Fin [nchavez 26/05/2015]
	
	// Inicio [jquispe 05/07/2016] Codigo del tipo de seguimiento para el Usuario Interno
	public static final String PREFIJO_CODIGO_TIPO_NOTIFICACION_SOL="803";	
	//Fin [jquispe 05/07/2016]
	
	// Inicio [gangles 09/08/2016]
	public static final String CODIGO_RC_ITERVENCION="007002";
	// Fin [gangles 09/08/2016]	
		
	public static final String COD_ORIGEN_DESCARGA_DOCUMENTO_INTRANET="01";
	public static final String COD_ORIGEN_DESCARGA_DOCUMENTO_INTERNET="02";
	public static final String COD_ORIGEN_DESCARGA_DOCUMENTO_OTROS="03";
	
	// Inicio [jjurado 18/08/2016]
	public static final String COD_ORIGEN_GEN_REPRESENTACION_IMPRESA_INTRANET="01";
	public static final String COD_ORIGEN_GEN_REPRESENTACION_IMPRESA_INTERNET="02";
	
	//Inicio [jtejada 26/08/2016]
	public static final String SIN_DATA = "-1";
	//Fin [jtejada 26/08/2016]
		
	public static final String URL_SUNAT = "http://www.sunat.gob.pe/";
	
	public static final String COD_PROCESO_COBRANZA = "001";
	public static final String COD_PROCESO_FICALIZACION = "002";
	
	//Inicio - [oachahuic][PAS20165E210400270]
	public static final String CARGA_ADUANAS = "3";
	public static final String TIPO_EXPE_RECTIF_DAM = "934";
	public static final String DAM_TIP_DOC_EXP = "080002";
	public static final String DAM_TIP_DOC_SOL_RECTIF = "080003";
	public static final String DAM_TIP_DOC_REQ_RECTIF = "080004";
	public static final String GRUPO_TIP_DOC_CONST = "803";
	public static final String GRUPO_TIP_DOC_USU_INTERNO = "963";
	public static final int DAM_SIZE_EXP_DAM = 15;
	public static final int DAM_SIZE_SOL_RECTIF = 16;
	public static final int DAM_SIZE_REQ_RECTIF = 17;
	public static final String COD_RPTA_WS_REST_200 = "200";
	public static final String COD_RPTA_WS_REST_9999 = "9999";
	public static final String ERROR_DAM_RECUPERAR_DATOS = "Error al obtener los datos de la DAM, consultar con el administrador.";
	public static final String ERROR_DAM_WS_NO_DISPONIBLE = "El servicio para consultar la DAM no se encuentra Disponible.";
	public static final String COD_PROCESO_ADUANA = "003";
	public static final String NUM_NUEVE = "9";
	public static final String LETRA_A = "A";
	//Fin - [oachahuic][PAS20165E210400270]
	
	//Inicio - [oachahuic][PAS20175E210400016]
	public static final String COD_EST_DOC_REGISTRADO = "01";
	public static final String COD_EST_DOC_ELIMINADO ="02";
	public static final String DES_ELI_DOC_REABRIR ="Eliminado por revertir el cierre del expediente.";
	//Fin - [oachahuic][PAS20175E210400016]
	public static final String CARGA_VALIDA_DOC_SINE = "4";
	public static final String COD_FORMA_NOTIF_POR_SOL = "803010";
	public static final String COD_PRO_SINE_MANUAL = "00221";
	public static final String COD_ESTADO_ABIERTO = "06";
	public static final String COD_ESTADO_CERRADO = "07";
	public static final String COD_ETAPA_INICIADO = "04";
	public static final String COD_ETAPA_EN_PROCESO = "05";
	public static final String COD_ETAPA_TERMINADO = "06";
	public static final String COD_ETAPA_REABIERTO = "07";	
	public static final String DES_CCS19 = "ccs19";//[PAS20191U210500076][OAC]
	public static final String DES_EXPFISCA = "expfisca";//[PAS20191U210500076][OAC]
	public static final String FECHA_HORA_DEFAULT = "0001-01-01 00:00:00";//[PAS20191U210500076][OAC]
	public static final String FECHA_DEFAULT = "0001-01-01";//[PAS20191U210500076][OAC]
	public static final String GRUPO_TIP_DOC_OTROS = "-";
	
	//[PAS20191U210500144] - Inicio
	public static final String COD_DOC_SIGAD_CARTA_CIRCULAR = "262"; 
	//[PAS20191U210500144] - Fin
	
	 //PAS20191U210500144 Inicio 400104 RF03 PSALDARRIAGA
	public static final String IND_SI = "SI";
	public static final String IND_NO = "NO";	
	//PAS20191U210500144 Fin 400104 RF03 PSALDARRIAGA
	
	//[PAS20191U210500291]: JMC - INI
	public static final String DESC_URD = "URD000";
	public static final String IND_ESTADO_REQUERIMIENTO_PRESENTADO = "02";
	public static final String COD_TIPO_DOCU_ESCRITO = "926014";
	public static final String COD_TIPO_DOCU_CONST_ESCRITO = "803011";
	public static final String COD_ADUANA_TRAM_DOC = "000";
	public static final String CLASE_TRAM_DOC = "N"; 	
	public static final String COD_PROC_TRAM_DOC = "1124";
	public static final String TIPO_USER_TRAM_DOC = "P";
	public static final Integer TIPO_DOC_TRAM_DOC = 4;
	public static final Integer EST_EXP_TRAM_DOC = 8;
	public static final Integer ACC_EXP_TRAM_DOC = 48;
	public static final Integer NRO_SEGUIM_TRAM_DOC = 1;
	public static final String CARGO_TRAM_DOC = "AGENTE FISCALIZADOR";
	public static final String ASUNTO_SEGUIM_TRAM_DOC = "ANEXADO AL EXPEDIENTE ELECTRONICO";
	public static final String ASUNTO_MENSAJE_RESP = "Presentación de Escrito Electrónico";
	public static final Integer FILTRO_NUM_CORDOCREL = 0;
	public static final String COD_TIPDOC_ALM = "01";
	public static final String ASUNTO_EXPEDI_TRAM_DOC = "PRESENTACION DE ESCRITO ELECTRONICO - EXPEDIENTE:";
	//[PAS20191U210500291]: JMC - FIN	
	
	
	// Inicio CVG - VINCULACION DE EXPEDIENTES
	public static final String VINCULADO = "01";
	public static final String NOVINCULADO_EXPRNOEXISTE = "02";
	public static final String NOVINCULADO_EXPVINOEXISTE = "03";
	public static final String NOVINCULADO_EXPNOCONCLUIDO = "04";
	public static final String DESVINCULADO = "05";
	// Fin CVG
	// PAS20201U210500029 HHC - INI - Estados de Solicitud de Descarga
	public static final String IND_ESTADO_SOL_DES_PENDIENTE = "01";
	public static final String IND_ESTADO_SOL_DES_PROCESANDO = "02";
	public static final String IND_ESTADO_SOL_DES_GENERADO = "03";
	public static final String IND_ESTADO_SOL_DES_CONSULTADO = "04";
	public static final String IND_ESTADO_SOL_DES_ELIMINADO = "05";
	public static final String IND_ESTADO_SOL_DES_ERROR = "99";

	public static final String DES_ESTADO_SOL_DES_PENDIENTE = "Pendiente";
	public static final String DES_ESTADO_SOL_DES_PROCESANDO = "Procesando";
	public static final String DES_ESTADO_SOL_DES_GENERADO = "Culminado";
	public static final String DES_ESTADO_SOL_DES_CONSULTADO = "Descargado";
	public static final String DES_ESTADO_SOL_DES_ELIMINADO = "Eliminado";
	public static final String DES_ESTADO_SOL_DES_ERROR = "Error";

	public static final String IND_NOTIENE_CODIDECM = "0";
	//PAS20201U210500029 HHC - FIN
	
	public static final String FECHA_DEFAULT_DD_MM_YYYY = "01/01/0001";
	
	//LLRB
	//Parametros de Conexion de  FileNet-ECM
	public static final String RUTA_SERVIDOR_ECM = "http://192.168.56.14:50002/wsi/FNCEWS40MTOM/";
	public static final String USERNAME_ECM = "CEAdmin";
	public static final String PASSWORD_ECM = "CEAdmin";
	public static final String JAAS_STANZA_ECM = "FileNetP8WSI";
	public static final String FILE_FOLDER_ECM = "/Sunat/Expedientes/";
	//public static final String RUTA_ARCHIVO_COMPARTIDA = "/recursoms/expvirtual/uploads/spring/";
	public static final String RUTA_ARCHIVO_COMPARTIDA = "/data0/final";
	
	// Inicio - [avilcan]
	public static final String IND_TIP_DOC_SUST = "04";
	public static final String IND_NUM_COR_DOC_REL = "0";
	public static final String IND_TIP_EXP_730 = "730";
	public static final String IND_TIP_EXP_731 = "731";
	// Fin - [avilcan]
	
}