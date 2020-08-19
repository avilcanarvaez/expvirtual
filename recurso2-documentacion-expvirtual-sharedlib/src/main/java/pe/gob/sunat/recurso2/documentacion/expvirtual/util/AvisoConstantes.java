package pe.gob.sunat.recurso2.documentacion.expvirtual.util;

public class AvisoConstantes {
	
	// Mensaje Página de Error
	
	public static final String MENS_PAGINA_ERROR = "Error al ingresar a la opción.";
	
	/* Títulos de Módulos - Mensajes */
	
	/*lestrada 15092016*/
	public static final String FLAG_INTRANET = "IN";
	public static final String FLAG_INTERNET = "EX";
	/*lestrada 15092016*/
	public static final String TITULO_MODULO_01_01_001 = "Asociación de Tipos de Expedientes";
	
	public static final String TITULO_MODULO_01_02_001 = "Asociación de Tipos de Documentos";
	
	//Inicio CVG
	public static final String TITULO_MODULO_01_08_001 = "Asociacion de Accesos al Expediente Virtual";
	//Fin CVG
		
	public static final String TITULO_MODULO_01_03_001 = "Asociación de Responsables al Expediente Virtual";
	
	public static final String TITULO_MODULO_01_04_001 = "Registro de Expediente Virtual";
	
	public static final String TITULO_MODULO_01_05_001 = "Cierre de Expediente Virtual";
	
	public static final String TITULO_MODULO_01_06_001 = "Reapertura del Expediente Virtual";//Agregado Johan 

	public static final String TITULO_MODULO_01_07_001 = "Mantenimiento del Expediente Virtual";//Agregado Johan 

	public static final String TITULO_MODULO_02_01_001 = "Consulta Expedientes Virtuales";
	
	public static final String TITULO_MODULO_02_04_001 = "REGISTRAR REQUERIMIENTO DE DOCUMENTOS";
	
	public static final String TITULO_MODULO_02_06_001 = "SUBIR DOCUMENTOS AL EXPEDIENTE VIRTUAL";
	
	public static final String TITULO_MODULO_03_01_001 = "Registro Documentos Internos";
	
	//INICIO [ATORRESCH 2017-02-22]
	public static final String TITULO_MODULO_03_02_001 = "ELIMINACION DE DOCUMENTOS INTERNOS";
	public static final String TITULO_MODULO_02_05_001 = "ELIMINACION DE REQUERIMIENTOS";
	//FIN [ATORRESCH 2017-02-22]	
	public static final String TITULO_MODULO_03_02_002 = "MODIFICACION DE DOCUMENTOS INTERNOS"; //[PAS20191U210500144] - ETITO
	
	public static final String TITULO_MODULO_02_06_08 = "CONSULTA DETALLADA DEL REQUERIMIENTO";
	
	
	public static final String TITULO_MODULO_04_01_001 = "Registro de Observaciones a Expedientes Virtuales";
	
	public static final String TITULO_MODULO_04_01_002 = "Registro de Observaciones a Expediente Virtual";
	
	public static final String TITULO_MODULO_04_01_003 = "Consulta de Seguimiento de Expediente";
	
	// Inicio [jquispe 31/05/2016] Consulta de Observaciones.
	public static final String TITULO_MODULO_04_01_004 = "Consulta de Observaciones a Expedientes Virtuales";
	// Fin [jquispe 31/05/2016]
	
	public static final String TITULO_MODULO_04_04_001 = "Consulta de Expedientes";
	
	public static final String TITULO_MODULO_04_03_001 = "CONSULTAS Y REPORTES DE EXPEDIENTES";
	
	public static final String TITULO_MODULO_05_01_001 = "Reporte de Plantillas";
	
	public static final String TITULO_MODULO_05_06_001 = "Generar Pedido de Reporte";
	public static final String TITULO_MODULO_05_06_002 = "Plantillas de Reporte de Expedientes de Trabajo";
	
	public static final String TITULO_MODULO_05_07_001 = "Impresión de Reportes Generados";
	
	public static final String TITULO_MODULO_05_08_002 = "Impresión de Expedientes de Trabajo";
	public static final String TITULO_MODULO_05_08_003 = "Impresión de Documentos del Expedientes de Trabajo";
	
	public static final String TITULO_MODULO_05_03_001 = "Nueva Plantilla de Reporte de Expedientes de Trabajo";
	public static final String TITULO_MODULO_05_03_002 = "Modificar Plantilla de Reporte de Expedientes de Trabajo";
	
	/* Excepciones de Módulos */
	
	public static final String EXCEP_MODULO_01_01_001 = "Debe seleccionar al menos un tipo de expediente a asignar.";
	public static final String EXCEP_MODULO_01_01_002 = "Debe seleccionar al menos un tipo de expediente a quitar.";
	public static final String EXCEP_MODULO_01_01_003 = "No se han efectuado cambios con respecto a la lista inicial, no se guardará la asociación.";
	public static final String EXCEP_MODULO_01_01_004 = "No existen datos en la lista para asignar.";
	public static final String EXCEP_MODULO_01_01_005 = "No existen datos en la lista para quitar.";
	public static final String EXCEP_MODULO_01_01_006 = "No se puede desasociar debido a que existen documentos enlazados, favor verificar.";
	
	public static final String EXCEP_MODULO_01_02_001 = "No existen Tipos de Expedientes asociados al proceso seleccionado.";
	public static final String EXCEP_MODULO_01_02_002 = "Debe seleccionar al menos un Tipo de Documento a asignar.";
	public static final String EXCEP_MODULO_01_02_003 = "Debe seleccionar al menos un Tipo de Documento a quitar.";
	public static final String EXCEP_MODULO_01_02_004 = "No se han efectuado cambios con respecto a la lista inicial, no se guardará la asociación.";
	public static final String EXCEP_MODULO_01_02_005 = "No se puede desasociar debido a que existen documentos enlazados, favor verificar.";
	public static final String EXCEP_MODULO_01_02_006 = "No se puede modificar debido a que existen documentos enlazados, favor verificar.";
	
	public static final String EXCEP_MODULO_01_03_001 = "Debe seleccionar o ingresar algún criterio de búsqueda.";
	public static final String EXCEP_MODULO_01_03_002 = "El N° Expediente ingresado es incorrecto.";
	public static final String EXCEP_MODULO_01_03_003 = "No se ha encontrado el Expediente, por favor verifique el número ingresado.";
	public static final String EXCEP_MODULO_01_03_004 = "El N° Expediente ingresado no se encuentra en estado {0}."; // 0 - Estado Expediente Virtual "Abierto"
	public static final String EXCEP_MODULO_01_03_005 = "No se puede obtener la información del contribuyente.";
	public static final String EXCEP_MODULO_01_03_006 = "El N° Expediente ingresado no corresponde a la dependencia del usuario conectado.";
	public static final String EXCEP_MODULO_01_03_007 = "Debe seleccionar al menos un Responsable a asignar.";
	public static final String EXCEP_MODULO_01_03_008 = "Debe seleccionar al menos un Responsable a quitar.";
	public static final String EXCEP_MODULO_01_03_009 = "No se puede retirar al responsable que emitió el documento origen del expediente.";
	public static final String EXCEP_MODULO_01_03_010 = "No se han efectuado cambios con respecto a la lista inicial, no se guardará la asociación.";
	public static final String EXCEP_MODULO_01_03_011 = "La dependencia del Expediente Virtual ingresado no está asociada a una Unidad Organizacional.";
	public static final String EXCEP_MODULO_01_03_012 = "El N° Expediente ingresado no se encuentra en estado abierto."; // 0 - Estado Expediente Virtual "Abierto"

	
	public static final String EXCEP_MODULO_01_04_001 = "El número de RUC ingresado no es válido, por favor verifíquelo.";
	public static final String EXCEP_MODULO_01_04_002 = "No se ha encontrado el número de RUC ingresado, por favor verifíquelo.";
	public static final String EXCEP_MODULO_01_04_003 = "El número de RUC ingresado no corresponde a la dependencia del usuario conectado, por favor verifíquelo.";
	public static final String EXCEP_MODULO_01_04_004 = "No existen Tipos de Expedientes asociados al proceso seleccionado.";
	public static final String EXCEP_MODULO_01_04_005 = "No existen Tipos de Documentos asociados al tipo de expediente seleccionado.";
	public static final String EXCEP_MODULO_01_04_006 = "El número de Expediente Origen no es válido, por favor verifíquelo.";
	public static final String EXCEP_MODULO_01_04_007 = "El código del responsable ingresado no es válido, por favor verifíquelo.";
	public static final String EXCEP_MODULO_01_04_008 = "No se ha encontrado el código de responsable ingresado, por favor verifíquelo.";
	public static final String EXCEP_MODULO_01_04_009 = "El responsable no se encuentra en la dependencia del usuario conectado, por favor verifíquelo.";
	public static final	String EXCEP_MODULO_01_04_010 = "El tamaño del archivo adjunto no debe exceder los {0} MegaBytes."; // 0 - Megas permitido
	public static final String EXCEP_MODULO_01_04_011 = "Debe ingresar el campo {0} para poder registrar el Expediente Virtual."; // 0 - Campo faltante
	public static final String EXCEP_MODULO_01_04_012 = "El número de Expediente Origen, ya se encuentra asociado a un Expediente Virtual.";
	public static final	String EXCEP_MODULO_01_04_013 = "El formato del archivo adjunto no está soportado, debe ser un documento en Pdf, Word, Excel, Power Point o Imagen."; // 0 - extensiones permitidas
	public static final	String EXCEP_MODULO_01_04_014 = "El nombre del archivo a cargar debe tener un máximo de 50 carácteres."; // tamaña maximo del nombre del archivo
	public static final	String EXCEP_MODULO_01_04_015 = "Debe ingresar el nro de ruc para continuar con el registro, por favor verifíquelo."; // tamaña maximo del nombre del archivo
	public static final	String EXCEP_MODULO_01_04_016 = "Debe seleccionar el tipo de proceso y tipo de expediente para continuar con el registro, por favor verifíquelo."; // tamaña maximo del nombre del archivo
	public static final String EXCEP_MODULO_01_04_017 = "El número de Expediente Origen no existe.";

	// Inicio [jjurado 08/06/2016]
	public static final String EXCEP_MODULO_01_04_018 = "No existe un Ejecutor Coactivo asignado al Expediente Origen.";
	public static final String EXCEP_MODULO_01_04_019 = "El  Número de Expediente Origen no está notificado.";
	// Fin [jjurado 08/06/2016]
	
	public static final String EXCEP_MODULO_01_05_001 = "Debe seleccionar o ingresar algún criterio de búsqueda.";
	public static final String EXCEP_MODULO_01_05_002 = "El N° Expediente ingresado es incorrecto.";
	public static final String EXCEP_MODULO_01_05_003 = "No se ha encontrado el Expediente ingresado.";
	public static final String EXCEP_MODULO_01_05_004 = "El N° Expediente ingresado no se encuentra en estado {0}."; // 0 - Estado Expediente Virtual "Abierto"
	public static final String EXCEP_MODULO_01_05_099 = "El N° Expediente ingresado no se encuentra en estado Abierto";
	public static final String EXCEP_MODULO_01_05_005 = "No se puede obtener la información del contribuyente.";
	public static final String EXCEP_MODULO_01_05_006 = "El N° Expediente ingresado no corresponde a la dependencia del usuario conectado.";
	public static final String EXCEP_MODULO_01_05_007 = "El Expediente Virtual cuenta con Requerimientos pendientes de atender.";
	public static final	String EXCEP_MODULO_01_05_008 = "El tamaño del archivo adjunto no debe exceder los {0} MegaBytes."; // 0 - Megas permitido
	public static final String EXCEP_MODULO_01_05_009 = "El formato del archivo adjunto no está soportado, debe ser un documento en Pdf, Word, Excel, Power Point o Imagen.";
	public static final String EXCEP_MODULO_01_05_010 = "Debe registrar el campo {0} para poder cerrar el Expediente Virtual."; // 0 - Campo faltante
	// Inicio [jjurado 08/06/2016]
	//public static final String EXCEP_MODULO_01_05_011 = "No se ha registrado la Constancia de Notificación asociada al Documento de Cierre."; // 0 - Campo faltante// Inicio [gangles 09/08/2016] Comentado por ajustes solicitados
	//public static final String EXCEP_MODULO_01_05_011 = "No se ha registrado la Constancia de Cierre para el Expediente Virtual."; // 0 - Campo faltante
	public static final String EXCEP_MODULO_01_05_012 = "No se ha Configurado Documentos de Cierre para el Tipo de Expediente {0}."; // 0 - Campo faltante
	public static final String EXCEP_MODULO_01_05_013 = "El Expediente ingresado debe ser Un Expediente Acumulador o Independiente."; // 0 - Campo faltante
	//Fin [jjurado 08/06/2016]
	//Inicio [gangles 09/08/2016]
	public static final String EXCEP_MODULO_01_05_011 = "No existe la Constancia de Notificación asociada con el Documento de Cierre.";
	//Fin [gangles 09/08/2016]
	public static final String EXCEP_MODULO_01_05_014 = "El número de expediente no se encuentra asignado al usuario logeado.";//[oachahuic][PAS20165E210400270]
	public static final String EXCEP_MODULO_01_05_015 = "No existe el Documento de Cierre.";//[oachahuic][PAS20165E210400270]
	public static final String EXCEP_MODULO_01_05_016 = "El N° Expediente ingresado no se encuentra en estado Abierto";
	//Inicio - Agregado por Johan Flores Effio
	//inicio Reapertura de Expediente Virtual 
	public static final String EXCEP_MODULO_01_06_001 = "Debe seleccionar o ingresar algún criterio de búsqueda.";
	public static final String EXCEP_MODULO_01_06_002 = "El N° Expediente ingresado es incorrecto.";
	public static final String EXCEP_MODULO_01_06_003 = "El N° Expediente ingresado no existe";
	public static final String EXCEP_MODULO_01_06_004 = "El N° Expediente ingresado no corresponde a la dependencia del usuario conectado.";
	public static final String EXCEP_MODULO_01_06_005 = "El Expediente Virtual no se encuentra asignado al usuario conectado.";
	public static final String EXCEP_MODULO_01_06_006 = "El Expediente Virtual no se encuentra en estado Cerrado."; 
	public static final String EXCEP_MODULO_01_06_007 = "El Expediente Virtual no es de origen Manual.";
	public static final String EXCEP_MODULO_01_06_008 = "El Expediente Virtual es un acumulado"; // 0 - Campo faltante
	public static final String EXCEP_MODULO_01_06_009 = "Ingrese un Número de Expediente.";	// agregado 16/03/2017 [JEFFIO]
	public static final String EXCEP_MODULO_01_06_010 = "El Número de Documento {0} no existe.";// agregado 25/05/2017 [JEFFIO]
	public static final String EXCEP_MODULO_01_06_011 = "El Número de Documento {0} no pertenece al expediente.";// agregado 25/05/2017 [JEFFIO]
	public static final String EXCEP_MODULO_01_06_012 = "El Número de Documento {0} no se encuentra notificado.";// agregado 25/05/2017 [JEFFIO]
	public static final String EXCEP_MODULO_01_06_013 = "El tamaño del archivo adjunto no debe exceder los {0} MegaBytes.";// agregado 25/05/2017 [JEFFIO]
	public static final String EXCEP_MODULO_01_06_014 = "El nombre del archivo a cargar debe tener un máximo de 100 carácteres.";// agregado 25/05/2017 [JEFFIO]
	public static final String EXCEP_MODULO_01_06_015 = "El formato del archivo adjunto no está soportado, debe ser un documento en Pdf, Word, Excel, Power Point o Imagen.";// agregado 25/05/2017 [JEFFIO]
	public static final String EXCEP_MODULO_01_06_016 = "Debe adjuntar un archivo.";// agregado 25/05/2017 [JEFFIO]	
	public static final String EXCEP_MODULO_01_06_017 = "Debe ingresar el campo {0} para poder Repaerturar el Expediente Virtual.";// agregado 16/03/2017 [JEFFIO]
	public static final String EXCEP_MODULO_01_06_018 = "El Número de Documento {0} no está asociado al Tipo de Documento {1}.";// agregado 16/03/2017 [JEFFIO]
	public static final String EXCEP_MODULO_01_06_019 = "El Tipo y número de documento ya se encuentra adjuntado al Expediente.";// agregado 18/05/2017 [JEFFIO]
	//cambio 13/04-llrb
	public static final String EXCEP_MODULO_01_06_020 = "El documento debe encontrarse en el Expediente Electrónico, si requiere adjuntar una copia personalizada.";// agregado 18/05/2017 [JEFFIO]
	//fin llrb
	//Inicio Mantenimiento de Expediente Virtual 
	public static final String EXCEP_MODULO_01_07_001 = "Debe seleccionar o ingresar algún criterio de búsqueda.";
	public static final String EXCEP_MODULO_01_07_002 = "El N° Expediente ingresado es incorrecto.";
	public static final String EXCEP_MODULO_01_07_003 = "El N° Expediente ingresado no existe";
	public static final String EXCEP_MODULO_01_07_004 = "El Expediente Virtual no corresponde a la dependencia del usuario conectado.";
	public static final String EXCEP_MODULO_01_07_005 = "El Expediente Virtual no se encuentra asignado al usuario conectado.";
	public static final String EXCEP_MODULO_01_07_006 = "El Expediente Virtual no se encuentra en estado Abierto."; 
	public static final String EXCEP_MODULO_01_07_007 = "El Expediente Virtual no es de origen Manual.";
	public static final String EXCEP_MODULO_01_07_008 = "El Expediente Virtual tiene documentos registrados";	
	public static final String EXCEP_MODULO_01_07_009 = "Ingrese un Número de Expediente.";// agregado 16/03/2017 [JEFFIO]
	public static final String EXCEP_MODULO_01_07_010 = "El Expediente Virtual es un acumulado";//agregado 05/05/2017 [JEFFIO
	//Fin - Agregado por Johan Flores Effio
	
	public static final String EXCEP_MODULO_02_01_001 = "El RUC no existe.";
	public static final String EXCEP_MODULO_02_01_002 = "Debe seleccionar algún criterio de búsqueda.";
	public static final String EXCEP_MODULO_02_01_003 = "El rango de fechas seleccionado para la Fecha del Documento de Origen excede los 3 meses permitidos.";
	public static final String EXCEP_MODULO_02_01_004 = "El rango de fechas seleccionado para la Fecha de Generación del Expediente excede los 3 meses permitidos.";
	public static final String EXCEP_MODULO_02_01_005 = "No se han encontrado registros para mostrar con los criterios de búsqueda ingresados.";
	public static final String EXCEP_MODULO_02_01_006 = "No existen registros para exportar.";
	public static final String EXCEP_MODULO_02_01_007 = "Ocurrió un error al registrar requerimiento.";
	public static final String EXCEP_MODULO_02_01_008_01 = "El N° Expediente Origen no existe.";
	public static final String EXCEP_MODULO_02_01_008_02 = "El N° Expediente Virtual no existe.";
	public static final String EXCEP_MODULO_02_01_008_03 = "El N° Expediente Origen no existe.";
	public static final String EXCEP_MODULO_02_01_008_04 = "El N° Expediente ingresado no tiene observaciones registradas.";
	public static final String EXCEP_MODULO_02_01_009 = "La fecha ingresada debe ser mayor a la fecha de registro del requerimiento.";
	public static final String EXCEP_MODULO_02_01_010 = "Debe seleccionar al menos un tipo de documento.";
	
	public static final String EXCEP_MODULO_02_01_011 ="El Número de Requerimiento Origen no existe.";
	public static final String EXCEP_MODULO_02_01_012 ="El Número de Requerimiento Origen no está notificado.";
	//Inicio Lestrada SNADE307-1063
	public static final String EXCEP_MODULO_02_01_013 ="El Número de Requerimiento Origen ya se encuentra asociado a un Requerimiento.";
	//Fin Lestrada SNADE307-1063
	public static final String EXCEP_MODULO_02_01_014 ="El Número de Requerimiento Origen no pertence al expediente.";
	//Inicio [gangles 23/08/2016]
	public static final String EXCEP_MODULO_02_01_015 = "El número de expediente no se encuentra asignado al usuario logeado.";
	//Fin [gangles 23/08/2016]
	public static final String EXCEP_MODULO_02_01_016 = "El Expediente se encuentra CERRADO."; //[ATORRESCH 20170047]
	
	
	// PAS20201U210500029 - HHC INICIO
	public static final String EXCEP_MODULO_02_01_008_05 = "Escrito electrónico no existe.";
	public static final String EXCEP_MODULO_02_01_008_06 = "Número de requerimiento no existe.";
	public static final String EXCEP_MODULO_02_01_008_07 = "Número de expediente no existe.";
	public static final String EXCEP_MODULO_02_01_008_08 = "El N° Expediente ingresado no tiene observaciones registradas.";
	public static final String EXCEP_MODULO_02_01_008_09 = "El N° Expediente ingresado no tiene observaciones registradas.";
	public static final String EXCEP_MODULO_02_01_008_10 = "El expediente se encuentra asignado a otro agente fiscalizador.";
	public static final String TITULO_MODULO_02_01_002    = "Consulta Escritos electrónico";
	// PAS20201U210500029 - HHC FIN
	
	public static final String TITULO_MODULO_02_01_003    = "Presentación de Otros Escritos";
	public static final String TITULO_MODULO_02_01_004    = "Consulta de Otros Escritos";
	public static final String TITULO_MODULO_02_01_005    = "Consulta de Solicitud Electrónica";
	
	public static final String EXCEP_MODULO_02_02_001 = "El rango de fechas seleccionado para la Fecha de Generación del Expediente excede los 3 meses permitidos.";
	public static final String EXCEP_MODULO_02_02_002 = "El rango de fechas seleccionado para la Fecha del Documento de Origen excede los 3 meses permitidos.";
	public static final String EXCEP_MODULO_02_02_003 = "El RUC no existe.";
	public static final String EXCEP_MODULO_02_02_004 = "No se ha ingresado ningún criterio de búsqueda.";
	public static final String EXCEP_MODULO_02_02_005 = "No se han encontrado registros para mostrar con los criterios de búsqueda ingresados.";
	public static final String EXCEP_MODULO_02_02_006_001 = "El N° Expediente Origen no existe.";
	public static final String EXCEP_MODULO_02_02_006_002 = "El N° Expediente Virtual no existe.";
	public static final String EXCEP_MODULO_02_02_007 = "No existen tipos documentos en el requerimiento.";
	public static final String EXCEP_MODULO_02_02_008 = "El tipo de documento esta seleccionado más de una vez, favor de verificar.";
	public static final String EXCEP_MODULO_02_02_009 = "El tamaño de los archivos seleccionados excede el límite permitido de (?) MB.";
	public static final String EXCEP_MODULO_02_02_010 = "El archivo seleccionado no existe en la carpeta indicada.";
	public static final String EXCEP_MODULO_02_02_011 = "No existen registros para exportar.";
	public static final String EXCEP_MODULO_02_02_012 = "Debe ingresar una o más palabras claves de búsqueda que cumplan con el siguiente formato: etiqueta1; etiqueta2.";
	public static final String EXCEP_MODULO_02_02_013 = "Ocurrió un error al subir los documentos.";
	public static final String EXCEP_MODULO_02_02_014 = "No es posible adjuntar un archivo con el mismo nombre más de una vez.";
	//Inicio [gangles 23/08/2016]
	public static final String EXCEP_MODULO_02_02_015 = "El número de expediente no se encuentra asignado al usuario logeado.";
	//Fin [gangles 23/08/2016]
	public static final String EXCEP_MODULO_02_04_001 = "El rango de Fecha del Expediente excede los 3 meses permitido.";
	public static final String EXCEP_MODULO_02_04_002 = "No existen registros para exportar.";
	public static final String EXCEP_MODULO_02_04_003 = "El número de expediente no existe.";
	//Inicio [gangles 23/08/2016]
	public static final String EXCEP_MODULO_02_04_004 = "El número de expediente no se encuentra asignado al usuario logeado.";
	//Fin [gangles 23/08/2016]
	public static final String EXCEP_MODULO_02_04_005 = "El rango de fechas supera los 180 días.";
	public static final String EXCEP_MODULO_02_04_006 = "El número de solicitud no existe.";
	
	public static final String EXCEP_MODULO_02_03_001 = "El Número de Expediente no existe.";
	public static final String EXCEP_MODULO_02_03_002 = "El formato de la fecha es incorrecto, solo formato: DD/MM/YYYY.";
	//Inicio [gangles 23/08/2016]
	public static final String EXCEP_MODULO_02_03_003 = "El número de expediente no se encuentra asignado al usuario logeado.";
	//Fin [gangles 23/08/2016]
	
	
	//INICIO [ATORRESCH 2017-03-01] IU015
	public static final String EXCEP_MODULO_02_05_001 = "Debe seleccionar o ingresar algún criterio de búsqueda";
	public static final String EXCEP_MODULO_02_05_002 = "El N° Expediente ingresado es incorrecto";
	public static final String EXCEP_MODULO_02_05_003 = "El N° Expediente ingresado no existe";
	public static final String EXCEP_MODULO_02_05_004 = "El Expediente Virtual no corresponde a la dependencia del usuario conectado";
	public static final String EXCEP_MODULO_02_05_005 = "El Expediente Virtual no se encuentra asignado al usuario conectado";
	public static final String EXCEP_MODULO_02_05_006 = "El Expediente Virtual no se encuentra en estado Abierto";
	public static final String EXCEP_MODULO_02_05_007 = "El Expediente Virtual no tiene requerimientos registrados";
	public static final String EXCEP_MODULO_02_05_008 = "Ingrese un Número de Expediente";
	//FIN    [ATORRESCH 2017-03-01] IU015
	
	public static final String EXCEP_MODULO_03_01_001 = "Debe ingresar el campo RUC.";
	public static final String EXCEP_MODULO_03_01_002 = "El rango establecido para las fechas de generación no debe ser mayor a un número de meses definido por parámetro.";
	public static final String EXCEP_MODULO_03_01_003 = "La fecha Hasta no debe ser menor que la fecha de Desde.";
	public static final String EXCEP_MODULO_03_01_004 = "Debe seleccionar un tipo de fecha a Buscar.";
	public static final String EXCEP_MODULO_03_01_005 = "El rango establecido para la búsqueda por fecha del documento origen no debe ser mayor a {0} meses.";
	public static final String EXCEP_MODULO_03_01_006 = "La fecha fin del documento origen no debe ser menor que la fecha de inicio de generación.";
	public static final String EXCEP_MODULO_03_01_007 = "El número de RUC no es válido.";
	public static final String EXCEP_MODULO_03_01_008 = "No se han encontrado expedientes con los criterios de búsqueda seleccionados.";
	public static final String EXCEP_MODULO_03_01_009 = "Debe seleccionar un documento.";
	public static final String EXCEP_MODULO_03_01_010 = "Debe ingresar el número de documento.";
	public static final String EXCEP_MODULO_03_01_011 = "Debe seleccionar un archivo con la opción seleccionar archivo.";
	public static final String EXCEP_MODULO_03_01_012 = "Debe ingresar las palabras claves de búsqueda.";
	public static final String EXCEP_MODULO_03_01_013 = "El número de expediente no es válido.";
	public static final String EXCEP_MODULO_03_01_014 = "El archivo seleccionado superará el tamaño máximo de 10MB permitido por subida, este archivo no será adjuntado.";
	public static final String EXCEP_MODULO_03_01_015 = "Debe ingresar la fecha Desde.";
	public static final String EXCEP_MODULO_03_01_016 = "Debe ingresar la fecha Hasta.";
	public static final String EXCEP_MODULO_03_01_017 = "La fecha desde es incorrecta.";
	public static final String EXCEP_MODULO_03_01_018 = "La fecha hasta es incorrecta.";
	public static final String EXCEP_MODULO_03_01_019 = "Debe ingresar un criterio de búsqueda.";
	public static final String EXCEP_MODULO_03_01_020 = "Debe seleccionar el tipo de proceso.";
	public static final String EXCEP_MODULO_03_01_021 = "Debe seleccionar el tipo de expediente.";
	public static final String EXCEP_MODULO_03_01_022 = "Debe ingresar el número de expediente.";
	public static final String EXCEP_MODULO_03_01_023 = "El rango establecido para la búsqueda por fecha de generación del expediente no debe ser mayor a {0} meses.";
	public static final String EXCEP_MODULO_03_01_024 = "El número de RUC ingresado no corresponde a la dependencia del usuario conectado, por favor verifíquelo.";
	public static final String EXCEP_MODULO_03_01_025 = "El número de RUC ingresado no existe.";
	public static final String EXCEP_MODULO_03_01_026 = "El número de Expediente no existe.";
	public static final String EXCEP_MODULO_03_01_031 = "Debe ingresar solo valores numéricos para el Nº de Expediente.";
	//Inicio [gangles 23/08/2016]
	public static final String EXCEP_MODULO_03_01_032 = "El número de expediente no se encuentra asignado al usuario logeado.";
	//Fin [gangles 23/08/2016]
	
	//INICIO [ATORRESCH 2017-02-22] 
	public static final String EXCEP_MODULO_03_02_001 = "Debe seleccionar o ingresar algún criterio de búsqueda";
	public static final String EXCEP_MODULO_03_02_002 = "El N° Expediente ingresado es incorrecto";
	public static final String EXCEP_MODULO_03_02_003 = "El N° Expediente ingresado no existe";
	public static final String EXCEP_MODULO_03_02_004 = "El Expediente Virtual no corresponde a la dependencia del usuario conectado";
	public static final String EXCEP_MODULO_03_02_005 = "El Expediente Virtual no se encuentra asignado al usuario conectado";
	public static final String EXCEP_MODULO_03_02_006 = "El Expediente Virtual no se encuentra en estado Abierto";
	public static final String EXCEP_MODULO_03_02_007 = "El Expediente Virtual no es de origen Manual";
	public static final String EXCEP_MODULO_03_02_008 = "El Documento seleccionado tiene documentos asociados";
	public static final String EXCEP_MODULO_03_02_009 = "Ingrese un Número de Expediente";
	public static final String EXCEP_MODULO_03_02_010 = "Los documentos del Expediente Virtual fueron visualizados por el contribuyente";
	//FIN    [ATORRESCH 2017-02-22]
	
	public static final String EXCEP_MODULO_04_01_001 = "Debe ingresar las dos fechas para el campo Fecha de registro del expediente.";
	public static final String EXCEP_MODULO_04_01_002 = "El rango establecido no debe superar a 3 meses.";
	public static final String EXCEP_MODULO_04_01_003 = "El formato de la fecha es incorrecto, solo formato: dd/mm/aaaa.";
	public static final String EXCEP_MODULO_04_01_004 = "La fecha de registro del expediente Hasta no debe ser menor que la Fecha de registro del expediente Desde.";
	public static final String EXCEP_MODULO_04_01_005 = "Debe ingresar las dos fechas para el campo Fecha de documento origen.";
	public static final String EXCEP_MODULO_04_01_006 = "El rango establecido no debe superar a 3 meses.";
	public static final String EXCEP_MODULO_04_01_007 = "El formato de la fecha es incorrecto, solo formato: dd/mm/aaaa.";
	public static final String EXCEP_MODULO_04_01_008 = "La fecha de documento origen Hasta no debe ser menor que la Fecha de documento origen Desde.";
	public static final String EXCEP_MODULO_04_01_009 = "Debe ingresar solo valores numéricos para el N° de Expediente.";
	public static final String EXCEP_MODULO_04_01_010 = "Debe ingresar solo valores numéricos para el RUC.";
	public static final String EXCEP_MODULO_04_01_011 = "Debe ingresar 11 dígitos para el RUC.";
	public static final String EXCEP_MODULO_04_01_012 = "Ningún registro encontrado con los criterios de búsqueda ingresados.";
	public static final String EXCEP_MODULO_04_01_013 = "Debe ingresar un valor para el campo Observación.";
	public static final String EXCEP_MODULO_04_01_014 = "Debe ingresar como mínimo 20 caracteres y como máximo 200 caracteres para el campo Observación.";
	public static final String EXCEP_MODULO_04_01_015 = "Ocurrió un error al registrar la observación.";
	public static final String EXCEP_MODULO_04_01_016 = "El Número de Expediente no existe.";
	public static final String EXCEP_MODULO_04_01_017 = "No existen documentos para el expediente seleccionado.";
	public static final String EXCEP_MODULO_04_01_018 = "El número de RUC ingresado no corresponde a la dependencia del usuario conectado, por favor verifíquelo.";
	public static final String EXCEP_MODULO_04_01_019 = "El número de RUC ingresado no existe.";
	public static final String EXCEP_MODULO_04_01_020 = "El expediente no contiene ningún documento.";	
	//Inicio [gangles 23/08/2016]
	public static final String EXCEP_MODULO_04_01_021 = "El número de expediente no se encuentra asignado al usuario logeado.";
	//Fin [gangles 23/08/2016]
	//PAS20201U210500082 Inicio 400104 LROMERO
		public static final String EXCEP_MODULO_04_04_023 = "El número de expediente ingresado no pertenece a la dependencia del usuario conectado, por favor verifíquelo.";
		//PAS20201U210500082 Fin 400104 LROMERO
	
	//Inicio [jtejada 26/09/2016]
	public static final String EXCEP_MODULO_04_01_022 = "Usted no tiene acceso, el expediente no está culminado.";
	public static final String EXCEP_MODULO_04_07_001 = "Todos los datos solicitados son obligatorios. Favor completar el formulario.";
	public static final String EXCEP_MODULO_04_07_002 = "Ingrese el código que se muestra en la imagen.";
	public static final String EXCEP_MODULO_04_07_003 = "El código mostrado no es correcto.";
	public static final String EXCEP_MODULO_04_07_004 = "Debe ingresar 11 dígitos para el RUC.";
	public static final String EXCEP_MODULO_04_07_005 = "El Número de RUC ingresado no existe.";
	public static final String EXCEP_MODULO_04_07_006 = "Debe ingresar 14 dígitos para el Número de la Representación Impresa.";
	public static final String EXCEP_MODULO_04_07_007 = "El Número de la Representación Impresa ingresado no existe.";
	//Fin [jtejada 26/09/2016]
	
	//PAS20191U210500144 Inicio 400104 RF03 PSALDARRIAGA
	public static final String EXCEP_MODULO_04_04_001 = "Usted no es responsable del Expediente Virtual.";
	//PAS20191U210500144 Fin 400104 RF03 PSALDARRIAGA
	
	public static final String EXCEP_MODULO_05_03_001 = "Debe seleccionar un Proceso.";
	public static final String EXCEP_MODULO_05_03_002 = "Debe seleccionar un Tipo de Expediente.";
	public static final String EXCEP_MODULO_05_03_003 = "Debe seleccionar al menos un Documento del Expediente.";
	public static final String EXCEP_MODULO_05_03_004 = "Debe seleccionar al menos un Formato Adicional para el Reporte.";
	public static final String EXCEP_MODULO_05_03_005 = "Debe registrar la Descripción de la Plantilla.";
	public static final String EXCEP_MODULO_05_03_006 = "Debe registrar el rango de fechas para generar el Reporte Tributario y Aduanero.";
	public static final String EXCEP_MODULO_05_03_007 = "Debe Seleccionar al menos un Reporte Tributario y Aduanero.";
	public static final String EXCEP_MODULO_05_07_001 = "No se encontraron Pedidos Generados.";
	public static final String EXCEP_MODULO_05_07_002 = "No se encontraron Pedidos Generados en las fechas seleccionadas.";
	public static final String EXCEP_MODULO_05_07_003 = "No se encontró el Número de Pedido ingresado.";	
	public static final String EXCEP_MODULO_05_07_004 = "El rango establecido no debe superar a 3 meses.";
	
	/* Avisos de Módulos */
	
	public static final String AVISO_MODULO_01_00_000 = "¿Desea limpiar todos los campos?";
	
	public static final String AVISO_MODULO_01_01_001 = "Se procederá a guardar los cambios.";
	public static final String AVISO_MODULO_01_01_002 = "Se ha registrado correctamente la Asociación Tipos de Expedientes - Proceso.";
	
	public static final String AVISO_MODULO_01_02_001 = "Se procederá a guardar los cambios.";
	public static final String AVISO_MODULO_01_02_002 = "Se ha registrado correctamente los cambios.";
	public static final String AVISO_MODULO_01_02_003 = "No se ha registrado la Asociación Tipos de Documentos – Tipo de Expediente - Proceso, debido a que no existe asociación.";

	public static final String AVISO_MODULO_01_03_001 = "Se procederá a guardar los cambios.";
	public static final String AVISO_MODULO_01_03_002 = "Se han guardado correctamente los cambios.";
	
	public static final String AVISO_MODULO_01_04_001 = "Se procederá a registrar el Expediente Virtual.";
	public static final String AVISO_MODULO_01_04_002 = "Se ha generado el Expediente Virtual Nº {0}."; // Número de expediente virtual
	public static final String AVISO_MODULO_01_04_003 = "No existe relacion entre el proceso y el tipo de Expediente."; // Número de expediente virtual
	public static final String AVISO_MODULO_01_04_004 = "Existe duplicidad con el N° de Expediente origen, favor verificar."; // duplicidad
	
	public static final String AVISO_MODULO_01_05_001 = "Está seguro de realizar el Cierre del Expediente Virtual.";
	public static final String AVISO_MODULO_01_05_002 = "Se ha cerrado el  Expediente Virtual Nº {0}."; // Número de expediente virtual
	
	//creado johan Reapertura y elinacion de Expediente Virtual
	public static final String AVISO_MODULO_01_06_001 = "Está seguro de reaperturar el Expediente Virtual.";
	public static final String AVISO_MODULO_01_06_002 = "Se ha reaperturado el  Expediente Virtual  Nº {0}. "; // Número de expediente virtual
	
	public static final String AVISO_MODULO_01_07_001 = "Está seguro de eliminar el Expediente Virtual.";
	public static final String AVISO_MODULO_01_07_002 = "Se ha eliminado el  Expediente Virtual  Nº {0}. "; // Número de expediente virtual
	public static final String AVISO_MODULO_01_07_003 = "Está seguro de revertir el cierre del Expediente Virtual.";
	public static final String AVISO_MODULO_01_07_004 = "Se ha revertido el  Expediente Virtual  Nº {0}. "; // Número de expediente virtual
	//fin	
	
	public static final String AVISO_MODULO_02_01_001 = "Número de Requerimiento registado: {0}.";
	
	//INICIO [ATORRESCH 2017-03-01] IU015
	public static final String AVISO_MODULO_02_05_001 = "¿Desea limpiar todos los campos?";
	public static final String AVISO_MODULO_02_05_002 = "¿Está seguro de eliminar el requerimiento N° {0} del expediente virtual N° {1}?";
	public static final String AVISO_MODULO_02_05_003 = "Se ha eliminado el Requerimiento Nº {0}.";
	//FIN    [ATORRESCH 2017-03-01] 
	
	//INICIO [ATORRESCH 2017-02-22]  IU005
	public static final String AVISO_MODULO_03_02_001 = "¿Desea limpiar todos los campos?";
	public static final String AVISO_MODULO_03_02_002 = "¿Está seguro de eliminar el tipo de documento {0} con N° {1} del expediente virtual?";
	public static final String AVISO_MODULO_03_02_003 = "Se ha eliminado el documento Nº {0} correctamente";
	//FIN    [ATORRESCH 2017-02-22]
	//[PAS20191U210500144] - INICIO
	public static final String AVISO_MODULO_03_02_004 = "¿Está seguro de modificar los datos del documento?";
	public static final String AVISO_MODULO_03_02_005 = "Se ha modificado el documento Nº {0} correctamente";
	//[PAS20191U210500144] - FIN
	public static final String AVISO_MODULO_04_01_001="¿Está seguro de registrar la observación?";
	public static final String AVISO_MODULO_04_01_002 = "Se ha registrado la observación correctamente.";
	
	// Inicio [jquispe 27/05/2016] Mensaje que muestra el numero totales de registros.
	public static final String AVISO_MODULO_04_04_001 = "Solo se muestran {0} registro(s) de un total de {1}.";
	// Fin [jquispe 27/05/2016]

	public static final String AVISO_MODULO_04_06_001 = "Los tipos de documentos fueron entregados satisfactoriamente de forma extemporánea.";
	public static final String AVISO_MODULO_04_06_002 = "Los tipos de documentos fueron entregados satisfactoriamente.";
	public static final String AVISO_MODULO_04_06_003 = "Ocurrió un error al subir los documentos.";
	
	public static final String AVISO_MODULO_05_01_001 = "No se encontraron plantillas registradas.";
	public static final String AVISO_MODULO_05_01_002 = "Debe seleccionar un Proceso.";
	public static final String AVISO_MODULO_05_01_003 = "Debe seleccionar un Tipo de Expediente.";
	public static final String AVISO_MODULO_05_01_004 = "Debe ingresar un filtro de búsqueda.";
	public static final String AVISO_MODULO_05_01_005 = "La fecha Hasta debe ser mayor a la fecha Desde.";
	public static final String AVISO_MODULO_05_01_006 = "La fecha Hasta es una fecha invalida.";
	public static final String AVISO_MODULO_05_01_007 = "La fecha Desde es una fecha invalida.";
	public static final String AVISO_MODULO_05_04_001 = "Debe seleccionar la Plantilla a dar de baja.";
	
	/*iu006*/
	public static final String AVISO_MODULO_05_06_001 = "No existe Tipos de Expedientes asocioados al proceso seleccionado.";
	public static final String AVISO_MODULO_05_06_002 = "No existe plantillas asociadas al Proceso y al Tipo de Expediente seleccionado.";
	public static final String AVISO_MODULO_05_06_003 = "El RUC ingresado no es válido por favor, verifíquelo.";
	public static final String AVISO_MODULO_05_06_004 = "El RUC ingresado no existe por favor, verifíquelo.";
	public static final String AVISO_MODULO_05_06_005 = "El número de RUC ingresado no corresponde a la dependencia del usuario conectado, por favor verifíquelo.";
	public static final String AVISO_MODULO_05_06_006 = "Debe ingresar el Nro. RUC para poder registrar el Pedido.";
	public static final String AVISO_MODULO_05_06_007 = "No existe Expedientes asocioados al RUC ingresado.";
	public static final String AVISO_MODULO_05_06_008 = "Debe seleccionar un Proceso.";
	public static final String AVISO_MODULO_05_06_009 = "Debe seleccionar un Tipo de Expediente.";
	public static final String AVISO_MODULO_05_06_010 = "Debe seleccionar una Plantilla.";
	public static final String AVISO_MODULO_05_06_011 = "Debe cargar el archivo masivo a ser procesado.";
	public static final String AVISO_MODULO_05_06_012 = "Debe seleccionar un Numero de Expediente Origen.";
	public static final String AVISO_MODULO_05_06_013 = "<b>Número de Pedido Generado:</b><br>N° {0}";
	public static final String AVISO_MODULO_05_06_014 = "El archivo cargado se encuentra vacío.";
	public static final String AVISO_MODULO_05_06_015 = "El archivo cargado no cumple con la estructura definida.";
	public static final String AVISO_MODULO_05_06_016 = "El formato del archivo a cargar, no está soportado, debe ser un archivo .txt.";
	public static final String AVISO_MODULO_05_06_017 = "El Número de RUC no existe.";
	public static final String AVISO_MODULO_05_06_018 = "El Número de RUC no pertenece a la dependencia del usuario conectado, por favor verifíquelo.";
	public static final String AVISO_MODULO_05_06_019 = "El Número de RUC debe contener 11 dígitos.";
	public static final String AVISO_MODULO_05_06_020 = "El Usuario ingresado no existe.";
	public static final String AVISO_MODULO_05_06_021 = "El Usuario ingresado no pertenece a la dependencia del usuario conectado, por favor verifíquelo.";
	public static final String AVISO_MODULO_05_06_022 = "No pertenece a la estructura definida.";
	public static final String AVISO_MODULO_05_06_023 = "El Número de RUC no contiene ningún expediente generado.";
	public static final String AVISO_MODULO_05_06_024 = "No existe dependencia para este responsable.";
	public static final String AVISO_MODULO_05_06_025 = "El codigo de usuario no es válido.";
	public static final String AVISO_MODULO_05_06_026 = "El campo RUC se encuentra en blanco.";
	public static final String AVISO_MODULO_05_06_027 = "El código de usuario se encuentra en blanco.";
	
	/* Errores WS */
	
	public static String CAMPO_HTTP_STATUS_COD = "cod";
	public static String CAMPO_HTTP_STATUS_MSG = "msg";
	
	public static String COD_HTTP_STATUS_50000 = "50000";
	public static String MSG_HTTP_STATUS_50000 = "Faltan los siguientes parámetros de entrada: ";
	
	public static String COD_HTTP_STATUS_50001 = "50001";
	public static String MSG_HTTP_STATUS_50001 = "No existe la asociación entre el Proceso y el Tipo de Expediente recibido. Comuníquese con el Administrador del Sistema.";
	
	public static String COD_HTTP_STATUS_50002 = "50002";
	public static String MSG_HTTP_STATUS_50002 = "El RUC {0} no es válido.";
	
	public static String COD_HTTP_STATUS_50003 = "50003";
	public static String MSG_HTTP_STATUS_50003 = "No existe la asociación entre el Tipo de Expediente y el Tipo de Documento recibido. Comuníquese con el Administrador del Sistema.";
	
	public static String COD_HTTP_STATUS_50004 = "50004";
	public static String MSG_HTTP_STATUS_50004 = "El número de Expediente Origen {0}, ya se encuentra asociado al Expediente Virtual {1}.";
	
	public static String COD_HTTP_STATUS_50005 = "50005";
	public static String MSG_HTTP_STATUS_50005 = "No existe un Expediente Virtual asociado al Expediente Origen enviado {0}.";
	
	public static String COD_HTTP_STATUS_50006 = "50006";
	public static String MSG_HTTP_STATUS_50006 = "El estado del Expediente Virtual asociado al Expediente Origen enviado {0}, se encuentra Cerrado.";
	
	public static String COD_HTTP_STATUS_50007 = "50007";
	public static String MSG_HTTP_STATUS_50007 = "El formato del archivo adjunto, no está soportado, debe ser un documento en Pdf, Word, Excel, Power Point o Imagen.";
	
	public static String COD_HTTP_STATUS_50008 = "50008";
	public static String MSG_HTTP_STATUS_50008 = "El tamaño del archivo adjunto no debe exceder los {0} MegaBytes.";
	
	public static String COD_HTTP_STATUS_50009 = "50009";
	public static String MSG_HTTP_STATUS_50009 = "El tipo y número de documento enviado no existe.";
	
	public static String COD_HTTP_STATUS_50010 = "50010";
	public static String MSG_HTTP_STATUS_50010 = "La fecha de vencimiento debe ser mayor a la fecha actual, por favor verifíquelo.";
	
	public static String COD_HTTP_STATUS_50011 = "50011";
	public static String MSG_HTTP_STATUS_50011 = "Los campos para consultar un expediente virtual son: Dependencia, Proceso, Rango de Fecha, RUC o Tipo de Expediente, por favor verifíquelo.";
	
	public static String COD_HTTP_STATUS_50012 = "50012";
	public static String MSG_HTTP_STATUS_50012 = "El expediente no pudo ser cerrado, porque tiene requerimientos pendientes de atender.";
	
	public static String COD_HTTP_STATUS_50013 = "50013";
	public static String MSG_HTTP_STATUS_50013 = "No se puede cerrar un expediente que fue creado de manera manual.";
	
	public static String COD_HTTP_STATUS_50014 = "50014";
	public static String MSG_HTTP_STATUS_50014 = "El Responsable a ser asignado, no pertenece a la Dependencia que registró el Expediente Virtual.";
	
	public static String COD_HTTP_STATUS_50015 = "50015";
	public static String MSG_HTTP_STATUS_50015 = "El rango de la fecha para la consulta de los expedientes virtuales, no debe exceder los 2 meses.";
	
	public static String COD_HTTP_STATUS_50016 = "50016";
	public static String MSG_HTTP_STATUS_50016 = "El número de requerimiento no puede estar vacío.";
	
	public static String COD_HTTP_STATUS_50017 = "50017";
	public static String MSG_HTTP_STATUS_50017 = "El número de requerimiento no existe.";
	
	public static String COD_HTTP_STATUS_50018 = "50018";
	public static String MSG_HTTP_STATUS_50018 = "Solo puede enviar uno de los campos: código del idecm o el archivo a adjuntar.";
	
	public static String COD_HTTP_STATUS_50019 = "50019";
	public static String MSG_HTTP_STATUS_50019 = "El tipo y número de documento que intenta adjuntar al Expediente ya existe, por favor verifiquelo.";
	
	public static String COD_HTTP_STATUS_50020 = "50020";//[PAS]
	public static String MSG_HTTP_STATUS_50020 = "El tamaño del nombre de archivo excedió lo máximo permitido.";//[PAS]

	public static String COD_HTTP_STATUS_50022 = "50022";
	public static String MSG_HTTP_STATUS_50022 = "El usuario de registro no puede estar vacío.";
	
	public static String COD_HTTP_STATUS_50025 = "50025";
	public static String MSG_HTTP_STATUS_50025 = "El tipo y número de documento relacionado no existe.";
	
	public static String COD_HTTP_STATUS_50026 = "50026";
	public static String MSG_HTTP_STATUS_50026 = "El estado del Expediente Virtual asociado al Expediente Origen enviado {0}, se encuentra Abierto.";
	
	public static String COD_HTTP_STATUS_50027 = "50027";
	public static String MSG_HTTP_STATUS_50027 = "No se puede ingresar un valor que exceda el tamaño {0} en el campo metadata.";
	
	public static String COD_HTTP_STATUS_50028 = "50028";
	public static String MSG_HTTP_STATUS_50028 = "El estado del Expediente Origen no es válido.";
	
	public static String COD_HTTP_STATUS_50029 = "50029";
	public static String MSG_HTTP_STATUS_50029 = "La etapa del Expediente Origen no es válido.";
	
	public static String COD_HTTP_STATUS_50030 = "50030";
	public static String MSG_HTTP_STATUS_50030 = "El Código de Dependencia no es válido.";
	
	public static String COD_HTTP_STATUS_50031 = "50031";
	public static String MSG_HTTP_STATUS_50031 = "El Código de Proceso no es válido.";
	
	public static String COD_HTTP_STATUS_50032 = "50032";
	public static String MSG_HTTP_STATUS_50032 = "El Código de Tipo de Expediente no es válido.";
	
	public static String COD_HTTP_STATUS_50033 = "50033";
	public static String MSG_HTTP_STATUS_50033 = "No se puede ingresar un valor que exceda el tamaño {0} en el campo desExpedv.";
	
	public static String COD_HTTP_STATUS_50034 = "50034";
	public static String MSG_HTTP_STATUS_50034 = "No se ha registrado el Documento de Cierre.";
	
	public static String COD_HTTP_STATUS_50035 = "50035";
	public static String MSG_HTTP_STATUS_50035 = "No se ha registrado la Constancia de Notificación asociada al Documento de Cierre.";
	
	public static String COD_HTTP_STATUS_50036 = "50036";
	public static String MSG_HTTP_STATUS_50036 = "El Expediente debe ser un Expediente Acumulador o Independiente.";
	
	public static String COD_HTTP_STATUS_50037 = "50037";
	public static String MSG_HTTP_STATUS_50037 = "Valor inválido para el parámetro {0}: ";
	
	public static String COD_HTTP_STATUS_50038 = "50038";
	public static String MSG_HTTP_STATUS_50038 = "El responsable a modificar no existe en el Expediente Virtual.";

	public static String COD_HTTP_STATUS_50039 = "50039";
	public static String MSG_HTTP_STATUS_50039 = "El responsable a modificar es responsable principal del Expediente Virtual.";
	
	public static String COD_HTTP_STATUS_50040 = "50040";
	public static String MSG_HTTP_STATUS_50040 = "Los datos enviados no existen en el Expediente Virtual.";

	public static String COD_HTTP_STATUS_50041 = "50041";
	public static String MSG_HTTP_STATUS_50041 = "El Código del Tipo de Documento no pertenece al Grupo de Notificaciones.";
	
	public static String COD_HTTP_STATUS_50042 = "50042";
	public static String MSG_HTTP_STATUS_50042 = "El Expediente fue reabierto.";

	public static String COD_HTTP_STATUS_200 = "200";
	
	public static String MSG_ECM_NO_CODIDECM = "No se generó el código id ECM.";
	public static String MSG_ECM_NO_SERVICIO = "El servicio ECM no está disponible.";
	
	/**/
	public static final String RANGO_FECHA_COMPARA = "3";

	// Inicio [gangles 10/08/2016]
	public static final String ENVIO_EN_PLAZO="Dentro del Plazo";
	public static final String ENVIO_FUERA_DE_PLAZO="Fuera del Plazo";
	public static final String NO_ENVIADO="No Enviado";	
	
	// Fin [gangles 10/08/2016]
	//Inicio - [oachahuic][PAS20165E210400270]
	public static final String MSJ_MODULO_03_01_EX_34 = "El Número de Documento {num_doc} no pertenece al expediente.";
	public static final String MSJ_MODULO_03_01_EX_35 = "El documento {num_doc} notificado no existe en el Expediente.";
	public static final String MSJ_MODULO_03_01_EX_38 = "Debe ingresar el documento subsiguiente al {sol_ant}.";
	public static final String MSJ_MODULO_03_01_EX_39 = "No existe el documento de Solicitud de Rectificación de la DAM asociado para este Requerimiento Origen.";
	public static final String MSJ_MODULO_03_01_EX_40 = "Debe ingresar el requerimiento subsiguiente al {req_ant}.";
	public static final String MSJ_MODULO_03_01_EX_41 = "El número de documento debe ser de {tam_doc} dígitos.";
	public static final String MSJ_MODULO_03_01_EX_42 = "No se puede adjuntar el Tipo de Documento seleccionado.";
	public static final String MSJ_CONSULTA_RUC_ERROR = "Error al validar el número de RUC.";
	//Fin - [oachahuic][PAS20165E210400270]
	public static final String MSJ_MODULO_03_01_EX_43 = "El número de documento {num_doc} no existe.";//[oachahuic][PAS20181U210400241]
	public static final String MSJ_MODULO_03_01_EX_44 = "El número de documento debe tener el formato {0}.";//[oachahuic][PAS20191U210500076]
	public static final String MSJ_MODULO_03_01_EX_45 = "La validación del número de documento no exite.";//[oachahuic][PAS20191U210500076]
	public static final String MSJ_MODULO_03_01_EX_46 = "El número de documento debe tener el formato NNNNNNNAAAAUUUUUU.";//[etito][PAS20191U210500144]
	public static final String MSJ_MODULO_03_01_EX_47 = "El número de documento {num_doc} no pertenece al RUC {num_ruc}.";//[etito][PAS20191U210500144]
	//PAS20191U210500144 Inicio 400101 RF28 PSALDARRIAGA
	public static String COD_HTTP_STATUS_50043 = "50043";
	public static String MSG_HTTP_STATUS_50043 = "Los responsables a reasignar, no pertenecen a la dependencia donde se registró el expediente virtual.";
	public static String COD_HTTP_STATUS_50044 = "50044";
	//modifica cvalencia 09/01/2020
	public static String MSG_HTTP_STATUS_50044 = "El número de requerimiento del negocio no se encuentra asociado al expediente.";
  	//JBC - Inicio
	public static String TIPO_DEPENDENCIA_UNO = "1";
	//JBC - Fin
	//PAS20191U210500144 Fin 400101 RF28 PSALDARRIAGA
	//Inicio staype 26/12/2019 [PAS20191U210500291]
	public static String COD_HTTP_STATUS_50045 = "50045";
	//modifica CVG 09/01/2020
	public static String MSG_HTTP_STATUS_50045 = "El número de requerimiento se encuentra registrado con estado Eliminado.";
	//Fin staype 26/12/2019 [PAS20191U210500291]
	
	//Inicio CVG 14/01/2020
	public static String COD_HTTP_STATUS_50046 = "50046";
	public static String MSG_HTTP_STATUS_50046 = "El número de requerimiento ya se encuentra registrado en la tabla del siev.";
	
	public static String COD_HTTP_STATUS_50047 = "50047";
	public static String MSG_HTTP_STATUS_50047 = "Falta parámetro Tipo de vinculacion o los datos no son correctos";
	
	
	public static String COD_HTTP_STATUS_50048 = "50048";
	public static String MSG_HTTP_STATUS_50048 = "NO VINCULADO - No existe expediente principal";
	
	
	public static String COD_HTTP_STATUS_50049 = "50049";
	public static String MSG_HTTP_STATUS_50049 = "NO VINCULADO - No existe expediente a vincular";
	
	public static String COD_HTTP_STATUS_50050 = "50050";
	public static String MSG_HTTP_STATUS_50050 = "NO VINCULADO - El expediente a vincular no esta concluido";
	
	public static String COD_HTTP_STATUS_50051 = "50051";
	public static String MSG_HTTP_STATUS_50051 = "Falta parámetro numero de expediente principal";
	
	
	public static String COD_HTTP_STATUS_50052 = "50052";
	public static String MSG_HTTP_STATUS_50052 = "Falta parámetro numero de expediente a vincular";
	
	public static String COD_HTTP_STATUS_50053 = "50053";
	public static String MSG_HTTP_STATUS_50053 = "Falta parámetro - fecha de vinculacion";
	
	public static String COD_HTTP_STATUS_50054 = "50054";
	public static String MSG_HTTP_STATUS_50054 = "Falta parámetro - usuario solicitante";
	
	

	//Fin CVG 14/01/2020 
	
	// PAS20201U210500082 - HHC INICIO
	public static final String EXCEP_MODULO_05_08_001 = "No se encontraron Solicitudes Generadas.";
	public static final String EXCEP_MODULO_05_08_002 = "No se encontró el Número de Solicitud ingresada.";
	public static final String TITULO_MODULO_05_08_001 = "Descarga de Expedientes";
	
	// PAS20201U210500082 - HHC INICIO
}
