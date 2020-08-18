package pe.gob.sunat.recurso2.documentacion.expvirtual.util;

public class CatalogoConstantes {
	
	public static final String CATA_TIPOS_DOCUMENTOS_RC_SEPARACION = "007305";
	public static final String CATA_TIPOS_DOCUMENTOS_3DIG = "A59"; //GRUPO DE DOCUMENTOS
	public static final String CATA_TABLAS_DOCUMENTOS_3DIG = "A65"; //TABLAS DE DOCUMENTOS
	public static final String CATA_DOCUMENTOS_APERTURA = "A60"; //DOCUMENTOS DE APERTURA POR TIPO DE EXPEDIENTE
	public static final String CATA_DOCUMENTOS_CIERRE = "A61"; //DOCUMENTOS DE CIERRE POR TIPO DE EXPEDIENTE
	public static final String CATA_DOCUMENTOS_REQUERIM = "A62"; //DOCUMENTOS DE REQUERIMIENTOS POR TIPO DE EXPEDIENTE
	public static final String CATA_DOCUMENTOS_ESTADO = "A63"; //DOCUMENTOS QUE ACTUALIZAN EL ESTADO DEL EXPEDIENTE ORIGEN
	public static final String CATA_DOCUMENTOS_ETAPA = "A64"; //DOCUMENTOS QUE ACTUALIZAN LA ETAPA DEL EXPEDIENTE ORIGEN
	public static final String CATA_DOCUMENTOS_OTROS = "A58"; //DOCUMENTOS QUE NO TIENEN CODIGO AGRUPADOR
	public static final String CATA_DOCUMENTOS_GRUPOS_OTROS = "-"; //ID DE GRUPO DOCUMENTOS QUE NO TIENEN CODIGO AGRUPADOR
	public static final String CODIGO_MAESTRO_REC = "006";
	public static final String CATA_PROCESOS = "991";
	public static final String CATA_TIPOS_EXPEDIENTES = "992";
	public static final String CATA_TIPOS_DOCUMENTOS_TIPO1 = "993";
	public static final String CATA_TIPOS_DOCUMENTOS_TIPO2 = "A51"; 
	public static final String CATA_TIPOS_DOCUMENTOS_TIPO3 = "A52";
	public static final String CATA_TIPOS_NUM_EXPEDIENTE = "A16";
	public static final String CATA_TIPOS_FECHA_EXPEDIENTE = "A17"; 
	public static final String CATA_TIPOS_DOCUMENTO_REQUERIMIENTO= "A62"; 
	public static final String CATA_ESTADOS_EXPEDIENTE_VIRTUAL = "A01";
	public static final String CATA_ESTADOS_CIERRE_EXPEDIENTE_VIRTUAL = "A02";
	public static final String CATA_ORIGEN_CIERRE_EXPEDIENTE_VIRTUAL = "A04";
	public static final String CATA_ESTADOS_REQUERIMIENTO = "A05";
	public static final String CATA_TIPOS_CIERRE_REQUERIMIENTO = "A99"; //TIPO DE CIERRE DEL REQUERIMIENTO
	public static final String CATA_ESTADOS_DOCUMENTO_REQUERIDO = "A07";
	public static final String CATA_EXTENSIONES_PERMITIDAS = "A18";
	public static final String CATA_TAMANIO_TOTAL_PERMITIDO = "A19";
	public static final String CATA_ESTADOS_REPORTE_PEDIDO = "";
	public static final String CATA_ESTADOS_REPORTE_EXPEDIENTE = "";
	public static final String CATA_ESTADOS_REPORTE_DOCUMENTO = "";
	//public static final String CATA_ESTADOS_PLANTILLA_EXPEDIENTE = "E20";
	public static final String CATA_ESTADOS_PLANTILLA_EXPEDIENTE = "A10";
	public static final String CATA_ESTADOS_EXPEDIENTE_ORIGEN = "A00";
	public static final String CATA_ETAPAS_EXPEDIENTE_ORIGEN = "999";
	public static final String CATA_TIPO_DOCUMENTO_SUSTENTO = "A08";
	public static final String CATA_ORIGEN_DOCUMENTO = "A09";
	public static final String CATA_ORIGEN_REQUERIMIENTO = "A06";
	public static final String CATA_ORIGEN_EXPEDIENTE_VIRTUAL = "A03";
	public static final String CATA_OPCIONES_MENU_INTERNET = "997";
	public static final String CATA_ACCIONES_SISTEMA_INTERNET = "998";
	public static final String CATA_SERVICIO_SISTEMA_INTERNET = "998";
	// Inicio [jquispe 27/05/2016] Parametro del catalogo de acciones para Intranet.
	public static final String CATA_ACCIONES_SISTEMA_INTRANET = "998";
	// Fin [jquispe 27/05/2016]
	public static final String CATA_CATALOGO_WEB_SERVICES = "";
	public static final String CATA_FORMATOS_ADICIONALES = "995";
	public static final String CATA_WS_ECM = "967";
	public static final String CATA_CLASE_DOCUM = "A20";
	public static final String CATA_PROCESO_NO_ALERTAS = "A22";
	
	public static final String CATA_DEPENDENCIAS = "003";
	public static final int CATA_LONGITUD_MAXIMA_ARCHIVOCARGA = 100;
	public static final String CATA_REP_TRIBUT_ADUANERO = "996";

	
	/* Revisar estos códigos */
	
	public static final String TIPO_PARAMETRO_CABECERA = "C";
	public static final String TIPO_PARAMETRO_DETALLE = "D";
	
	/* Límite de Catalogos */
	
	public static final Integer LIMITE_INFERIOR_DEPENDENCIA = 11;
	public static final Integer LIMITE_SUPERIOR_DEPENDENCIA = 35;
	
	public static final long MAX_SIZE_FILE_UPLOAD_BYTES = 10485760;
	public static final int MAX_SIZE_FILE_UPLOAD_MEGAS = 10;
	
	public static final int INT_ONE = 1;
	public static final int INT_TWO = 2;
	public static final int INT_THREE = 3;
	public static final int INT_FOUR = 4;
	public static final int INT_FIVE = 5;
	
	/* Origne del documento*/
	public static final String REG_WS = "01";
	public static final String REG_MANUAL = "02";
	public static final String ORIGEN_WS_01 = "Automático";
	public static final String ORIGEN_MANUAL_02 = "Manual";
	
	/* Limite de Caracteres - T01Param - WS ECM */
	public static final Integer LIMITE_INFERIOR_WS_ECM = 0;
	public static final Integer LIMITE_SUPERIOR_WS_ECM = 119;
	
	/*Carpeta Temporal*/
	public static final String CARPETA_TEMPORAL = "/data0/tempo/";
	
	/*Mensajeria*/
	public static final String ASUNTO_MENSAJE_04_01 = "Resultado de Solicitud";
	
	public static final String CONSTANTE_DIAS = " día(s)";
	
	/*REPORTES INDICADORES*/
	public static final String RPT_EXP_GEN_X_DEP = "RPT_EXP_GEN_X_DEP";
	public static final String RPT_EXP_GEN_X_AMD = "RPT_EXP_GEN_X_AMD";
	public static final String RPT_EXP_GEN_X_DEP_CONS_X_USU_INT_Y_CONTRIB = "RPT_EXP_GEN_X_DEP_CONS_X_USU_INT_Y_CONTRIB";
	public static final String RPT_EXP_GEN_X_AMD_CONS_X_USU_INT_Y_CONTRIB = "RPT_EXP_GEN_X_AMD_CONS_X_USU_INT_Y_CONTRIB";
	public static final String RPT_EXP_GEN_X_DEP_CONS_X_USU_INT_Y_CONTRIB_X_RUC = "RPT_EXP_GEN_X_DEP_CONS_X_USU_INT_Y_CONTRIB_X_RUC";
	public static final String RPT_EXP_GEN_X_DEP_EXP_COBR_ACUM = "RPT_EXP_GEN_X_DEP_EXP_COBR_ACUM";
	public static final String RPT_EXP_GEN_X_DEP_CONT_X_DCTO = "RPT_EXP_GEN_X_DEP_CONT_X_DCTO";
	public static final String RPT_EXP_GEN_X_RESPONSABLES = "RPT_EXP_GEN_X_RESPONSABLES";
	public static final String RPT_DOC_GEN_X_EXP_DEP = "RPT_DOC_GEN_X_EXP_DEP";
	public static final String RPT_REPRE_IMPR_EXP_VIRT_X_DEP = "RPT_REPRE_IMPR_EXP_VIRT_X_DEP";
	public static final String RPT_REPRE_IMPR_EXP_VIRT_X_FEC = "RPT_REPRE_IMPR_EXP_VIRT_X_FEC";
	public static final String RPT_REPRE_IMPR_EXP_VIRT_X_EXPDTE_Y_DEP = "RPT_REPRE_IMPR_EXP_VIRT_X_EXPDTE_Y_DEP";
	
	/*NOMBRE REPORTES INDICADORES*/
	public static final String RPT_EXP_GEN_X_DEP_DESC = "REPORTE DE EXPEDIENTES GENERADOS POR DEPENDENCIA";
	public static final String RPT_EXP_GEN_X_AMD_DESC = "REPORTE DE EXPEDIENTES GENERADOS POR FECHA (AÑO/MES/DIA)";
	// Inicio [jquispe 27/05/2016]Modificacion del titulo del reporte incluyendo al Usuario Interno.
	public static final String RPT_EXP_GEN_X_DEP_CONS_X_USU_INT_Y_CONTRIB_DESC = "REPORTE DE EXPEDIENTES GENERADOS POR DEPENDENCIA CONSULTADOS POR LOS USUARIOS: USUARIO INTERNO Y CONTRIBUYENTE";
	public static final String RPT_EXP_GEN_X_AMD_CONS_X_USU_INT_Y_CONTRIB_DESC = "REPORTE DE EXPEDIENTES GENERADOS POR AÑO/MES/DIA CONSULTADOS POR LOS USUARIOS: USUARIO INTERNO Y CONTRIBUYENTE";
	public static final String RPT_EXP_GEN_X_DEP_CONS_X_USU_INT_Y_CONTRIB_X_RUC_DESC = "REPORTE DE EXPEDIENTES GENERADOS POR DEPENDENCIA CONSULTADOS POR LOS USUARIOS: USUARIO INTERNO Y CONTRIBUYENTE POR RUC";
	// Fin [jquispe 27/05/2016]
	public static final String RPT_EXP_GEN_X_DEP_EXP_COBR_ACUM_DESC = "REPORTE DE EXPEDIENTES GENERADOS POR DEPENDENCIA - EXPEDIENTES DE COBRANZA ACUMULADOS";
	public static final String RPT_EXP_GEN_X_DEP_CONT_X_DCTO_DESC = "REPORTE DE EXPEDIENTES GENERADOS POR DEPENDENCIA - CONTENIDO POR TIPO DE DOCUMENTOS";
	//Inicio [gangles 17/08/2016]
	public static final String RPT_EXP_GEN_X_RESPONSABLES_DESC = "REPORTE DE EXPEDIENTES GENERADOS POR RESPONSABLES";
	public static final String RPT_DOC_GEN_X_EXP_DEP_DESC = "REPORTE DE DOCUMENTOS REGISTRADOS POR EXPEDIENTE Y DEPENDENCIA";
	//Inicio [jtejada 22/08/2016]
	public static final String RPT_REPRE_IMPR_EXP_VIRT_X_DEP_DESC = "SIEV-R. IMP-01: REPORTE DE REPRESENTACIONES IMPRESAS GENERADAS POR DEPENDENCIA: USUARIO INTERNO Y CONTRIBUYENTE";
	public static final String RPT_REPRE_IMPR_EXP_VIRT_X_FEC_DESC = "SIEV-R. IMP-02: REPORTE DE REPRESENTACIONES IMPRESAS GENERADAS POR AÑO/MES/DÍA: USUARIO INTERNO Y CONTRIBUYENTE";
	public static final String RPT_REPRE_IMPR_EXP_VIRT_X_EXPDTE_Y_DEP_DESC = "SIEV-R. IMP-03: REPORTE DE REPRESENTACIONES IMPRESAS GENERADAS POR EXPEDIENTE: USUARIO INTERNO Y CONTRIBUYENTE POR RUC";
	
	
	//Fin [gangles 17/08/2016]
	public static final String RPT_GEN_CONSULTA_REQ_XLS = "rpt_consulta_requerimientos_";
	public static final String RPT_GEN_CONSULTA_EXP_XLS = "rpt_consulta_expedientes_";
	public static final String RPT_GEN_CONSULTA_DOC_REQ_XLS = "rpt_documentos_requerimiento_";
	
	public static final String RPT_GEN_CONSULTA_ACCE_XLS = "rpt_consulta_acceso_expedientes_";
	
	//Inicio [gangles 02/06/2016] Se crea variables para capturar errores al generar el pedido de expediente de trabajo
	public static final String CATA_ERROR_GEN_DOC_CABECERA = "A65";
	
	//Documentos del Expediente 
	public static final String CATA_ERROR_GEN_DOC_CAMBIO_FORMATO = "001"; 
	//Formatos Adicionales
	public static final String CATA_ERROR_GEN_ACTA_INSTALACION = "002"; 
	public static final String CATA_ERROR_GEN_ACTA_ARQUEO_INICIAL = "003";//
	public static final String CATA_ERROR_GEN_ACTA_ARQUEO_FIN = "004";
	public static final String CATA_ERROR_GEN_HOJA_ANALISIS_VISUAL = "005";
	public static final String CATA_ERROR_GEN_HOJA_RELEVAMIENTO_DATOS = "006";
	//Reporte Tributarios Aduanero
	public static final String CATA_ERROR_GEN_DECLARACIONES_DETERMINATIVAS = "007";
	public static final String CATA_ERROR_GEN_DECLARACIONES_INFORMATIVAS = "008";
	public static final String CATA_ERROR_GEN_OPERACIONES_COM_EXTERIOR = "009";
	public static final String CATA_ERROR_GEN_DEUDAS_TRIBUTARIAS = "010";
	public static final String CATA_ERROR_GEN_DEVOLUCIONES = "011";	
	public static final String CATA_ERROR_GEN_COMPROBANTES_PAGO = "012";
	public static final String CATA_ERROR_GEN_DATOS_PADRON = "013";
	
	public static final String CATA_ERROR_GEN_FORM_INTERV = "014";
	public static final String CATA_ERROR_GEN_TOMA_DICHI = "015";
	public static final String CATA_ERROR_GEN_ANEX_ACTA_EMB = "016";
	public static final String CATA_ERROR_GEN_ACTA_EMB_DEP_SINEX = "017";
	public static final String SELECTOR_RECURSO_GENERAEXPTRABAJO="SELECTOR.RECURSO_GENERAEXPTRABAJO";
	//Inicio [gangles 09/08/2016]	
	public static final String FECHA_VALIDACION_VIGENCIA_RS="01/05/2016";//Fecha Inicio de Vigencia de la RS
	//Fin [gangles 09/08/2016]
	
	//Inicio [jtejada 18/08/2016]	
	public static final String TIPO_CONSULTA_MANUAL="01";
	public static final String TIPO_CONSULTA_AUTOMATICA="02";
	//Fin [jtejada 18/08/2016]
	
	//Inicio [jtejada 14/09/2016]
	//NOMBRES DE PARAMETROS DE REPRESENTANTE LEGAL
	//DEVUELTO POR WEBSERVICE: wsURLRepLegalCont
	public static final String KEY_REP_LEG_COD_CARGO_OCUPA = "codCargo"; //String: Código del cargo que ocupa
	public static final String KEY_REP_LEG_COD_DEPART = "codDepar"; //String: Código departamento 
	public static final String KEY_REP_LEG__DES_DEPART = "desdepar"; //String: Descripción del departamento
	public static final String KEY_REP_LEG_DESC_DOC_IDENT = "descDocide"; //String: Descripción del documento de identidad
	public static final String KEY_REP_LEG_NUM_ORD_SUCES = "numOrdSuce"; //Short: Numero de Orden de la sucesión  
	public static final String KEY_REP_LEG_DESC_CARGO_OCUPA = "rsoCargo"; //String: Descripción del cargo que ocupa
	public static final String KEY_REP_LEG_TIPO_DOC_IDEN = "rsoDocide"; //String: Tipo de Documento de identidad
	public static final String KEY_REP_LEG_FEC_Y_HOR_ULT_ACTUALI = "rsoFecact"; //String: Fecha y Hora de la última actualización
	public static final String KEY_REP_LEG_FEC_NAC_CONTRIB = "rsoFecnac"; //String: Fecha de Nacimiento del contribuyente
	public static final String KEY_REP_LEG_APE_NOM_O_RAZ_SOC = "rsoNombre"; //String: Apellidos y Nombres y/o razón social
	public static final String KEY_REP_LEG_NUM_DOC= "rsoNrodoc"; //String: Numero de documento 
	public static final String KEY_REP_LEG_NUM_RUC_CONTRIB = "rsoNumruc"; //String: //Número de RUC del contribuyente
	public static final String KEY_REP_LEG_NOM_USU_ULT_ACTUALI = "rsoUserna"; //String: Nombre del usuario que hizo la última actualización
	public static final String KEY_REP_LEG_FEC_DESDE_CARGO_OCUPA = "rsoVdesde"; //String: Fecha desde la que ocupa el cargo
	public static final String KEY_REP_LEG_CORREO = "codCorreo1"; //String: Correo electrónico del Representante Legal
	public static final String KEY_REP_LEG_NUM_TELEF = "numTelef"; //String: Número de teléfono del Representante Legal
	public static final String KEY_REP_LEG_TIP_RELAC = "codTipVin"; //String: Tipo de relación
	public static final String KEY_REP_LEG_DESC_TIP_RELAC = "desTipvin"; //String: Descripción del Tipo de Relación
	public static final String KEY_REP_LEG_DOMICILIO = "domicilioRepresLegal"; //String: Descripción del Tipo de Relación
	public static final String KEY_REP_LEG_NUM_PORC_PARTIC = "numPorpar"; //String: Porcentaje de participación
	public static final String KEY_REP_LEG_COD_PAIS_ORIG = "codPaiori"; //String: Código país de origen
	public static final String KEY_REP_LEG_DESC_PAIS_ORIG = "desPaiori"; //String: Descripción del país origen
	//FIn [jtejada 16/09/2016]
	public static final String CATA_EQUIV_TIP_DOC_SINE = "B36"; //[oachahuic][PAS20181U210400241]
	public static final String CATA_TIP_VAL_TIP_DOC_TIP_EXP = "C44"; //[oachahuic][PAS20191U210500076]
	
	//[PAS20191U210500144] - Inicio
	//Parametro servicio de firma digital signnet
	public static final String  ARGUMENTO_SIGNNET= "INTPWEB120602002";
	public static final Integer LIMITE_INFERIOR_SIGNNET= 1;
	public static final Integer LIMITE_SUPERIOR_SIGNNET= 123;
	//public static final String HOST_SERVICIO_SIGNNET= "http://192.168.46.20:800";
	//public static final String HOST_SERVICIO_SIGNNET= "http://192.168.34.20:90";
	//public static final String HOST_SERVICIO_SIGNNET= "http://intranet";
	
	//[PAS20191U210500144] - Inicio

	//[PAS20191U210500291] - Inicio
	public static final String CATA_PROCESOS_ARGUMENTO= "002";	
	public static final String CATA_TIPOS_EXPEDIENTES_ARGUMENTO = "432";
	public static final String CATA_TIPOS_EXPEDIENTES_DEFPARCIAL = "430";
	public static final String CATA_TIPOS_EXPEDIENTES_CRUCES = "431";
	//[PAS20191U210500291] - Fin
	public static final String RPT_GEN_CONSULTA_EXPE_XLS = "rpt_consulta_expediente_";
	public static final String RPT_GEN_CONSULTA_ESCRITO_XLS = "rpt_consulta_escritos_";
	public static final String CATA_TIPOS_SELLO_TIME = "772";
	public static final String COD_NOTIFICA_BUZON = "711";
	public static final String COD_ARGUMENTO_SELLO = "001";
	public static final String COD_ARGUMENTO_BUZON= "72";
	
    //[PAS20201U210500175] - Inicio
    public static final String CATA_CLASE_CE = "A19";
    public static final String TIPO_PARAMETRO_CE = "D";
    public static final String COD_URL_CE = "URL_CE";
    public static final String COD_USER_CE = "USER_CE";
    public static final String COD_PASS_CE = "PASS_CE";

}