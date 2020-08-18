package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;
import org.springframework.http.ResponseEntity;

import pe.gob.sunat.framework.spring.util.dao.SequenceDAO;
import pe.gob.sunat.framework.spring.util.jdbc.datasource.lookup.DataSourceContextHolder;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.RepExpTrabBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.RepExpTrabDocBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.RepExpTrabExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T02PerdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10373DocAdjReqBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10461SolDesBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6611CabPlantiBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T10373DocAdjReqDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T10461SolDesDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6611CabPlantiDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6612DetPlantiDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6613DocExpVirtDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6614ExpVirtualDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6616PedRepDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6617RepGenDocDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6618RepGenExpDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6619RepGenPedDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.DataSourceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExcepcionECM;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExportaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.SequenceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;

import com.lowagie.text.Chapter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.GrayColor;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

public class ExpedienteTrabReporteServiceImpl implements ExpedienteTrabReporteService {
	private static final Log log = LogFactory.getLog(ExpedienteTrabReporteServiceImpl.class);
	
	private T6613DocExpVirtDAO t6613DocExpVirtDAO;
	private T6614ExpVirtualDAO t6614ExpVirtualDAO;
	private T6616PedRepDAO t6616PedRepDAO;
	private T6617RepGenDocDAO t6617RepGenDocDAO; 
	private T6618RepGenExpDAO t6618RepGenExpDAO;
	private T6619RepGenPedDAO t6619RepGenPedDAO;
	private T6611CabPlantiDAO t6611CabPlantiDAO;
	private T6612DetPlantiDAO t6612DetPlantiDAO;
	private SequenceDAO sequenceDAO;
	
	private EcmService ecmService;
	private GeneralService generalService;
	private ConfiguracionExpedienteService configuracionExpedienteService;
	private ValidarParametrosService validarParametrosService;
	private WSContribuyenteService WSContribuyenteService;
	
	private String generaReporteURLService;
	
	private T10461SolDesDAO t10461SolDesDAO;   //PAS20201U210500082 -HHC
	private T10373DocAdjReqDAO  t10373DocAdjReqDAO; //PAS20201U210500082 -HHC
	private PersonaService personaService; //PAS20201U210500082 -HHC
	
	@Override
	public String generarReporte(String numPedido, String codUsuario, RepExpTrabBean repExpTrabBean) throws Exception {
		Map<String, Object> mapParam = null;
		String codIdEcm = null;
		Long idSequence = null;
		//DataSource origenDatos = null; //se comenta pues ya no se pasa el objeto Datasource a la consulta 
		Map<String, Object> mapaPool = null; //13 JUL: EL MAPA QUE CONTIENE EL NOMBRE DEL POOL Y EL OBJETO DATASOURC (ESTE ULTIMO EN DESUSO POR EL TEMA DE ROUTING)
		List<RepExpTrabExpVirtBean> listaRepExpTrabExpVirtBeans = null;
		List<RepExpTrabDocBean> listaRepExpTrabDocBeans = null;
		List<String> listaRepExpTrabRubro = null;
		List<String> listaRepExpTrabFormatAdic = null;
		Integer idDocumento = null;
		String prefijoNombreArchivoPDF = null;
		FileOutputStream fos = null;
		String nombreArchivoDocPDF = null;
		String nombreArchivoTemp = null;
		String pdfs[] = null;
		Integer correlativoPDF = 0;
		ResponseEntity<byte[]> responseEntity = null;
		File archivoConsolidadoFile = null;
		File archivoFile = null;
		File archivoTempFile = null;
		FileInputStream fileInputStream = null;
		byte[] bytesArchivoConsolidado = null;
		byte[] bytesArchivo = null;
		Map<String, Object>	beanEcm = null;
		Map<String, Object>	mapJSON = null;
		Map<String, Object>	mapCabJSON = null;
		List<Map<String, Object>> mapDetJSON = null;
		String json = null;
		Calendar fechaVacia = null;
		Map<String, Object> mapDummy = null;
		String fechaActualFormat = null;
		String formatoFecha = "dd/MM/yyyy";
		String extension = null;
		Map<String, Object> mapTemp = null;
		Map<String, String> mapExtImagen = null;
		Map<String, String> mapExtDocXls = null;
		Map<String, String> mapExtPPT = null;
		Map<String, Object> mapPrincipal = null;
		List<Map<String, Object>> listaMapPrincipal = null;
		Map<String, Object> mapAdicional = null;
		List<Map<String, Object>> listaMapAdicional = null;
		Map<String, Object> mapDireccion = null;
		List<Map<String, Object>> listaMapDireccion = null;
		List<Map<String, Object>> listaMapTributos = null;
		List<Map<String, Object>> listaMapAnexos = null;
		List<Map<String, Object>> listaMapRepLegales = null;
		List<Map<String, Object>> listaMapDeclaJuradas = null;
		List<Map<String, Object>> listaMapOpeComExt = null;
		List<Map<String, Object>> listaMapDeudaTrib = null;
		List<Map<String, Object>> listaMapTemp = null;
		String referencia = null;
		String direccion = null;
		String direccionContrib = null;
		String temp = null;
		String tipoContribuyente = null;
		String actividad = null;
		String estadoContribuyente = null;
		String condContribuyente = null;
		String telefono = null;
		String fax = null;
		String rutaImageTemp = null;
		String codEstGeneracionDoc = "";
		String codEstGeneracionExp = "";
		boolean errorGeneracion = false;
		int numErrorGeneracion = 0;
		String codEstGeneracionPedido = "";
		String nombreSinExtension = null;	
		StringBuilder sbLstErroresGenPedido= new StringBuilder();// gangles 08/06/2016 Almacenar los tipos de errores al generar el pedido.
		List<Map<String, Object>> listaMapComprobantes = null; //gangles [30/06/2016] Comprobantes de pago
		
		try {
				if (log.isDebugEnabled()) log.debug("Inicio - ExpedienteTrabReporteServiceImpl.generarReporte");

				fechaVacia = Calendar.getInstance();
				fechaVacia.set(1, 0, 1, 0, 0, 0);
				
				fechaActualFormat = Utils.convertirDateToString(new Date(), formatoFecha);
				
				// Origen de datos
				mapParam = new HashMap<String, Object>();
				mapParam.put("indCentral", DataSourceConstantes.IND_BASEDATOS_CENTRAL);
				mapParam.put("oriDatos", DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
				
				//origenDatos = generalService.obtenerOrigenDatos(mapParam);
				mapaPool = generalService.obtenerOrigenDatosMap(mapParam); //recuperar nombre del pool
				DataSourceContextHolder.setKeyDataSource(mapaPool.get("nombrePool").toString()); //setea el Pool via el key declarado en el routing-datasource-data.xml	
				
				if (log.isDebugEnabled()) log.debug("Inicio - Caratula");
				
				try {
					correlativoPDF++;
					idSequence = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_REPDOC);
					prefijoNombreArchivoPDF = UUID.randomUUID() + "_" + numPedido;
					
					// GENERAR CARATULA PDF ANEXO 02
					mapParam = new HashMap<String, Object>();
					
					mapCabJSON = new HashMap<String, Object>();
					mapCabJSON.put("desDependencia", repExpTrabBean.getDesDependencia());
					mapCabJSON.put("desProceso", repExpTrabBean.getDesProceso().toUpperCase());
					mapCabJSON.put("desTipoExpediente", repExpTrabBean.getDesTipoExpediente().toUpperCase());
					mapCabJSON.put("numExpedienteTrabajo", repExpTrabBean.getNumRepExp().toString());				
					mapCabJSON.put("fechaExpedienteTrabajo", fechaActualFormat);
					mapCabJSON.put("numRuc", repExpTrabBean.getNumRUC());
					mapCabJSON.put("razonSocial", repExpTrabBean.getRazonSocial().replace("\"", "\\\""));
					mapCabJSON.put("documentoOrigen", "");
					mapCabJSON.put("fechaDocumentoOrigen", "");
					
					mapJSON = null;
					mapJSON = new HashMap<String, Object>();
					mapJSON.put("cabecera", mapCabJSON);
					mapDetJSON = new ArrayList<Map<String, Object>>();
					
					mapDummy = new HashMap<String, Object>();
					mapDummy.put("key", "value");
					mapDetJSON.add(mapDummy);
					
					mapJSON.put("detalle", mapDetJSON);
					
					json = (String) (new JsonSerializer()).serialize(mapJSON);
					
					mapParam.put("json", json);
					mapParam.put("idDocumento", ExportaConstantes.PDF_EXPTRAB_COD_CARATULA);					
					mapParam.put("nombreArchivo", ValidaConstantes.NOM_ARCHIVO_EXP_TRABAJO_CARATULA + idSequence);		
					mapParam.put("numRUC",  repExpTrabBean.getNumRUC());		
					mapParam.put("codTipDocu", ValidaConstantes.COD_TIPO_DOCU_EXP_TRAB_CARATURA);					
					mapParam.put("metadata", ValidaConstantes.METADATA_EXP_TRABAJO_CARATULA);
					mapParam.put("prefijoNombreArchivoPDF", prefijoNombreArchivoPDF);
					mapParam.put("correlativoPDF", correlativoPDF);
					
					codIdEcm = this.guardarReporteECM(mapParam);// //"AAA"; 
					
					codEstGeneracionDoc = ValidaConstantes.IND_ESTADO_REP_GEN_DOC_GENERADO;
				} catch(Exception ex) {
					//codEstGeneracionDoc = ValidaConstantes.IND_ESTADO_REP_GEN_DOC_ERROR;
					codEstGeneracionDoc = ValidaConstantes.IND_ESTADO_REP_GEN_DOC_PARCIALMENTE;
					//sbLstErroresGenPedido.append(CatalogoConstantes.CATA_ERROR_GEN_DOC_CAMBIO_FORMATO + ".");// 08 JUN eaguilar [consultar el mensaje de error apropiado] 
					errorGeneracion = true;
					numErrorGeneracion++;
					log.error(ex, ex);
				}
				
				mapParam = new HashMap<String, Object>();
				
				//mapParam.put("origenDatos", origenDatos);//se comenta pues ya no se pasa el objeto Datasource a la consulta 
				mapParam.put("numRepDoc", idSequence);
				mapParam.put("numRepPed", repExpTrabBean.getNumRepPed());
				mapParam.put("numRepExp", repExpTrabBean.getNumRepExp());
				mapParam.put("numPedido", numPedido);
				mapParam.put("codIdecm", codIdEcm != null ? codIdEcm : ValidaConstantes.SEPARADOR_GUION);
				mapParam.put("numExpedv", ValidaConstantes.FILTRO_CERO);
				mapParam.put("fecGenera", new Date());
				mapParam.put("codEst", codEstGeneracionDoc);
				mapParam.put("codUsuModif", ValidaConstantes.SEPARADOR_GUION);
				mapParam.put("fecModif", fechaVacia.getTime());
				
				t6617RepGenDocDAO.insertar(mapParam);
				
				if (log.isDebugEnabled()) log.debug("Fin - Caratula");
				
				if (log.isDebugEnabled()) log.debug("Inicio - Documentos");
				// BUSCAR Y CONVERTIR DOCUMENTOS DE PLANTILLA A PDF
				listaRepExpTrabExpVirtBeans = repExpTrabBean.getListaRepExpTrabExpVirtBeans();
				if(listaRepExpTrabExpVirtBeans != null) {
					mapExtImagen = new HashMap<String, String>();
					mapExtDocXls = new HashMap<String, String>();
					mapExtPPT = new HashMap<String, String>();
					
					mapExtImagen.put(ValidaConstantes.TIPO_ARCHIVO_JPG, "1");
					mapExtImagen.put(ValidaConstantes.TIPO_ARCHIVO_PNG, "1");
					mapExtImagen.put(ValidaConstantes.TIPO_ARCHIVO_JPEG, "1");
					mapExtImagen.put(ValidaConstantes.TIPO_ARCHIVO_TIF, "1");
					mapExtImagen.put(ValidaConstantes.TIPO_ARCHIVO_TIFF, "1");
					
					mapExtDocXls.put(ValidaConstantes.TIPO_ARCHIVO_DOC, "1");
					mapExtDocXls.put(ValidaConstantes.TIPO_ARCHIVO_DOCX, "1");
					mapExtDocXls.put(ValidaConstantes.TIPO_ARCHIVO_XLS, "1");
					mapExtDocXls.put(ValidaConstantes.TIPO_ARCHIVO_XLSX, "1");
					
					mapExtPPT.put(ValidaConstantes.TIPO_ARCHIVO_PPT, "1");
					mapExtPPT.put(ValidaConstantes.TIPO_ARCHIVO_PPTX, "1");
					
					for(RepExpTrabExpVirtBean beanExp : listaRepExpTrabExpVirtBeans) {
						listaRepExpTrabDocBeans = beanExp.getListaRepExpTrabDocBeans();
						
						if(listaRepExpTrabDocBeans != null) {
							for(RepExpTrabDocBean beanDoc: listaRepExpTrabDocBeans) {
								try {
									correlativoPDF++;
									codEstGeneracionDoc = ValidaConstantes.IND_ESTADO_REP_GEN_DOC_GENERADO;
									extension = Utils.obtenerExtension(beanDoc.getNombreDocumento());
									codIdEcm = ValidaConstantes.SEPARADOR_GUION;
									
									mapParam = new HashMap<String, Object>();
									
									mapParam.put("inline", "false");
									mapParam.put("codIdecm", beanDoc.getCodIdecm());
									
									responseEntity = ecmService.descargarDocumentoECM(mapParam);
									
									nombreArchivoDocPDF = 	ValidaConstantes.CARPETA_TEMPORAL_TEMPO + prefijoNombreArchivoPDF + "_" + 
											(correlativoPDF) + "." + ValidaConstantes.TIPO_ARCHIVO_PDF;
									
									if(extension.equalsIgnoreCase(ValidaConstantes.TIPO_ARCHIVO_PDF)) {
										fos = new FileOutputStream(nombreArchivoDocPDF);
										fos.write(responseEntity.getBody());
										fos.close();
										
										codIdEcm = beanDoc.getCodIdecm();
									} else {
										// Archivos Imagen
										if(mapExtImagen.get(extension) != null) {
											nombreArchivoTemp = ValidaConstantes.CARPETA_TEMPORAL_TEMPO + prefijoNombreArchivoPDF + "_" + beanDoc.getNombreDocumento();
											fos = new FileOutputStream(nombreArchivoTemp);
											fos.write(responseEntity.getBody());
											fos.close();
									        
									        Utils.convertImagenAPDF(nombreArchivoTemp, nombreArchivoDocPDF);
									        
									        archivoFile = new File(nombreArchivoDocPDF);
									        fileInputStream  = new FileInputStream(archivoFile);
											bytesArchivo = new byte[(int) archivoFile.length()];
											fileInputStream.read(bytesArchivo);
									        fileInputStream.close();
									        
											beanEcm = new HashMap<String, Object>();	
											
											nombreSinExtension = Utils.obtenerNombreSinExtension(beanDoc.getNombreDocumento());
											beanEcm.put("nomArchivoSinExt", nombreSinExtension);
											beanEcm.put("numExpeDv", "0");
											beanEcm.put("numRuc", repExpTrabBean.getNumRUC());
											beanEcm.put("numDoc", "-");
											beanEcm.put("fecEmi", new Date());
											beanEcm.put("codTipExpVir", "000");
											beanEcm.put("codTipDocu", beanDoc.getCodigoTipoDocumento());			
											beanEcm.put("archLength", bytesArchivo.length);
											beanEcm.put("archMimeType", ValidaConstantes.HTTP_ACCEPT_PDF);
											beanEcm.put("archFileName", nombreSinExtension + "." + ValidaConstantes.TIPO_ARCHIVO_PDF);
											beanEcm.put("archContent", bytesArchivo);
											beanEcm.put("metadata", ValidaConstantes.METADATA_EXP_TRABAJO_GENERADO);
											
											codIdEcm = ecmService.guardarDocumentoECM(beanEcm); // "BBB"; //
									        
									        archivoTempFile = new File(nombreArchivoTemp);
									        archivoTempFile.delete();
										} 
										else { throw new Exception();} //eaguilar 12 JUL: se arroja una excepcion cuando el tipo es OFFICE
											
										//QUEDA COMENTADA LA SIGUIENTE SECCION HASTA QUE SE DETERMINE EL METODO DE CONVERSION DE DOCUMENTOS OFFICE A PDF
											/*else {
											// Archivos Word y Excel 
											if(mapExtDocXls.get(extension) != null) {
												nombreArchivoTemp = ValidaConstantes.CARPETA_TEMPORAL_TEMPO + prefijoNombreArchivoPDF + "_" + beanDoc.getNombreDocumento();
												fos = new FileOutputStream(nombreArchivoTemp);
												fos.write(responseEntity.getBody());
												fos.close();
										        
										        Utils.convertWordExcelAPDF(nombreArchivoTemp, nombreArchivoDocPDF, extension, ValidaConstantes.CARPETA_TEMPORAL_TEMPO);
										        
										        archivoFile = new File(nombreArchivoDocPDF);										        
												bytesArchivo = new byte[(int) archivoFile.length()];
										        
												beanEcm = new HashMap<String, Object>();	
												
												nombreSinExtension = Utils.obtenerNombreSinExtension(beanDoc.getNombreDocumento());
												beanEcm.put("nomArchivoSinExt", nombreSinExtension);
												beanEcm.put("numExpeDv", "0");
												beanEcm.put("numRuc", repExpTrabBean.getNumRUC());
												beanEcm.put("numDoc", "-");
												beanEcm.put("fecEmi", new Date());
												beanEcm.put("codTipExpVir", "000");
												beanEcm.put("codTipDocu", beanDoc.getCodigoTipoDocumento());			
												beanEcm.put("archLength", bytesArchivo.length);
												beanEcm.put("archMimeType", ValidaConstantes.HTTP_ACCEPT_PDF);
												beanEcm.put("archFileName", nombreSinExtension + "." + ValidaConstantes.TIPO_ARCHIVO_PDF);
												beanEcm.put("archContent", bytesArchivo);
												beanEcm.put("metadata", ValidaConstantes.METADATA_EXP_TRABAJO_GENERADO);
												
												codIdEcm = ecmService.guardarDocumentoECM(beanEcm); //"CCC"; //
										        
										        archivoTempFile = new File(nombreArchivoTemp);
										        archivoTempFile.delete();
											} else {
												// Archivos PPT 
												if(mapExtPPT.get(extension) != null) {
													nombreArchivoTemp = ValidaConstantes.CARPETA_TEMPORAL_TEMPO + prefijoNombreArchivoPDF + "_" + beanDoc.getNombreDocumento();
													fos = new FileOutputStream(nombreArchivoTemp);
													fos.write(responseEntity.getBody());
													fos.close();
											        
											        rutaImageTemp = ValidaConstantes.CARPETA_TEMPORAL_TEMPO + prefijoNombreArchivoPDF + "_imageppttemp_";
											        Utils.convertPPTAPDF(nombreArchivoTemp, nombreArchivoDocPDF, extension, rutaImageTemp);
											        
													archivoFile = new File(nombreArchivoDocPDF);
											        fileInputStream  = new FileInputStream(archivoFile);
													bytesArchivo = new byte[(int) archivoFile.length()];
													fileInputStream.read(bytesArchivo);
											        fileInputStream.close();
													
													beanEcm = new HashMap<String, Object>();	
													
													nombreSinExtension = Utils.obtenerNombreSinExtension(beanDoc.getNombreDocumento());
													beanEcm.put("nomArchivoSinExt", nombreSinExtension);
													beanEcm.put("numExpeDv", "0");
													beanEcm.put("numRuc", repExpTrabBean.getNumRUC());
													beanEcm.put("numDoc", "-");
													beanEcm.put("fecEmi", new Date());
													beanEcm.put("codTipExpVir", "000");
													beanEcm.put("codTipDocu", beanDoc.getCodigoTipoDocumento());			
													beanEcm.put("archLength", bytesArchivo.length);
													beanEcm.put("archMimeType", ValidaConstantes.HTTP_ACCEPT_PDF);
													beanEcm.put("archFileName", nombreSinExtension + "." + ValidaConstantes.TIPO_ARCHIVO_PDF);
													beanEcm.put("archContent", bytesArchivo);
													beanEcm.put("metadata", ValidaConstantes.METADATA_EXP_TRABAJO_GENERADO);
													
													codIdEcm = ecmService.guardarDocumentoECM(beanEcm); //"DDD"; //
											        
											        archivoTempFile = new File(nombreArchivoTemp);
											        archivoTempFile.delete();
												}
											}
										}	*/
									}
								} catch(Exception ex) {
									codEstGeneracionDoc = ValidaConstantes.IND_ESTADO_REP_GEN_DOC_PARCIALMENTE;//Evaluar si se debe retirar
									sbLstErroresGenPedido.append(CatalogoConstantes.CATA_ERROR_GEN_DOC_CAMBIO_FORMATO + ".");// 08 JUN eaguilar - cod err al convertir a PDF
									errorGeneracion = true;
									correlativoPDF--;
									numErrorGeneracion++;
									log.error(ex, ex);
									codIdEcm = ValidaConstantes.SEPARADOR_GUION;
								}
								
								mapParam = new HashMap<String, Object>();
								//mapParam.put("origenDatos", origenDatos);//se comenta pues ya no se pasa el objeto Datasource a la consulta 
								mapParam.put("numRepDoc", sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_REPDOC));
								mapParam.put("numRepPed", repExpTrabBean.getNumRepPed());
								mapParam.put("numRepExp", repExpTrabBean.getNumRepExp());
								mapParam.put("numPedido", numPedido);
								mapParam.put("codIdecm", codIdEcm);
								mapParam.put("numExpedv", beanExp.getNumExpedienteVirtual());
								//mapParam.put("numExpedv", ValidaConstantes.FILTRO_CERO);
								mapParam.put("fecGenera", new Date());
								mapParam.put("codEst", codEstGeneracionDoc);
								mapParam.put("codUsuModif", ValidaConstantes.SEPARADOR_GUION);
								mapParam.put("fecModif", fechaVacia.getTime());
								
								t6617RepGenDocDAO.insertar(mapParam);								
							}
						}
					}
				}
				if (log.isDebugEnabled()) log.debug("Inicio - Documentos");
				
				// GENERAR PARA CADA EXP ORIGEN UN REP. ADUANERO PDF (ANEXO 01) SI SE SELECCIONO ESA OPCION
				if(	repExpTrabBean.getIndReporteTrib() != null && 
					repExpTrabBean.getIndReporteTrib().equals(ValidaConstantes.IND_SI_REPORTE_TRIB_ADUANERO)) {
					try {
						if (log.isDebugEnabled()) log.debug("Inicio - Reporte Aduanero");
						
						correlativoPDF++;
						idSequence = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_REPDOC);
						codEstGeneracionDoc = ValidaConstantes.IND_ESTADO_REP_GEN_DOC_GENERADO;
						mapParam = new HashMap<String, Object>();
						mapParam.put("numRUC", repExpTrabBean.getNumRUC());
						codIdEcm = null;
						
						listaMapPrincipal = WSContribuyenteService.consultarWSContPrincipal(mapParam);
						if(listaMapPrincipal != null && listaMapPrincipal.size() > 0) {
							mapPrincipal = (Map<String, Object>)listaMapPrincipal.get(0);
							
							temp = (String)mapPrincipal.get("ddpTpoemp");
							temp = temp != null ? temp.trim() : ""; 
							tipoContribuyente = temp + " - "; 
							
							temp = (String)mapPrincipal.get("descTpoemp");
							temp = temp != null ? temp.trim() : "";
							
							tipoContribuyente += temp;
							
							//Inicio [jjurado 03/07/2016] se corrige Observación  P_SNADE006-5286
							//tipoContribuyente = null;
							temp = (String)mapPrincipal.get("codCiiu5");
							temp = temp != null ? temp.trim() : ""; 
							actividad = temp + " - "; 
							
							temp = (String)mapPrincipal.get("desCiiu5");
							temp = temp != null ? temp.trim() : "";
							
							actividad += temp;
							//Fin [jjurado 03/07/2016] P_SNADE006-5286
							
							temp = (String)mapPrincipal.get("descEstado");
							temp = temp != null ? temp.trim() : "";
							estadoContribuyente = temp;
							
							temp = (String)mapPrincipal.get("esHabido");
							temp = temp != null ? temp.trim() : "";
							if(temp.equals("true")) {
								condContribuyente = "HABIDO";
							} else {
								condContribuyente = "NO HABIDO";
							}
						}
						
						listaMapAdicional = WSContribuyenteService.consultarWSContAdicional(mapParam);
						if(listaMapAdicional != null && listaMapAdicional.size() > 0) {
							mapAdicional = (Map<String, Object>)listaMapAdicional.get(0);
							// Inicio [jjurado 03/07/2016] se corrige Observación  P_SNADE006-5286
							temp = (String)mapAdicional.get("ddsTelef1");
							temp = temp != null ? temp.trim() : ""; 
							
							telefono = temp;
							
							temp = (String)mapAdicional.get("ddsNumfax");
							temp = temp != null ? temp.trim() : ""; 
							
							fax = temp; 
							// Inicio [jjurado 03/07/2016]
						}	
						listaMapDireccion = WSContribuyenteService.consultarWSDireccionCont(mapParam);
						direccion="";
						if(listaMapDireccion != null && listaMapDireccion.size() > 0) {
							mapDireccion = (Map<String, Object>)listaMapDireccion.get(0);
							
							direccion = (String)mapDireccion.get("direccion");
							direccionContrib = direccion;
						}
						
						mapCabJSON = new HashMap<String, Object>();
						
						mapCabJSON.put("visPadron", "N");
						mapCabJSON.put("visComprobante", "N");
						mapCabJSON.put("visOtros", "N");
						mapCabJSON.put("visAfectacionTributos", "N");
						mapCabJSON.put("visEstablecimientos", "N");
						mapCabJSON.put("visRepresentantes", "N");
						
						listaRepExpTrabRubro = repExpTrabBean.getListaRepExpTrabRubro();
						if(listaRepExpTrabRubro != null) {
							for(String codRubro : listaRepExpTrabRubro) {
								codRubro  = codRubro.trim();
								
								if(codRubro.equals(ValidaConstantes.PLANT_RUBRO_DATOS_PADRON)) {
									mapCabJSON.put("visPadron", "S");
									
									mapCabJSON.put("numExpediente", "");
									mapCabJSON.put("descProceso", repExpTrabBean.getDesProceso().toUpperCase());
									mapCabJSON.put("descTipoExpediente", repExpTrabBean.getDesTipoExpediente().toUpperCase());
									mapCabJSON.put("fecha", fechaActualFormat);
									mapCabJSON.put("depExpediente", repExpTrabBean.getDesDependencia());
									mapCabJSON.put("tipoContri", tipoContribuyente != null ? tipoContribuyente : "");
									mapCabJSON.put("actContri", actividad != null ? actividad : "");// [jjurado 03/07/2016] se corrige Observación  P_SNADE006-5286
									mapCabJSON.put("estadoContri", estadoContribuyente);
									mapCabJSON.put("depContri", repExpTrabBean.getDesDependencia());
									mapCabJSON.put("condicionContri", condContribuyente);
									mapCabJSON.put("direccion", direccion);
									//Inicio nchavez 07/07/2016
									mapCabJSON.put("telefono",!Utils.isEmpty(telefono)?telefono:ValidaConstantes.SEPARADOR_GUION);
									mapCabJSON.put("fax",!Utils.isEmpty(fax)?fax:ValidaConstantes.SEPARADOR_GUION);//[jjurado 03/07/2016] se corrige Observación  P_SNADE006-5286
									//Fin nchavez 07/07/2016
									listaMapTemp = WSContribuyenteService.consultarWSTribAfectoCont(mapParam);
									if(listaMapTemp != null && listaMapTemp.size() > 0) {
										listaMapTributos = new ArrayList<Map<String, Object>>();
										mapCabJSON.put("visAfectacionTributos", "S");
										for(Map<String, Object> map : listaMapTemp) {
											mapTemp = new HashMap<String, Object>();
											
											mapTemp.put("tributo", map.get("descVfpCodtri"));
											mapTemp.put("afectoDesde", map.get("vfpFecvig") != null ? map.get("vfpFecvig") : "");
											mapTemp.put("fechaAlta", map.get("vfpFecalt") != null ? map.get("vfpFecalt") : "");
											mapTemp.put("marcaExo", map.get("vfpCodEstaExo"));
											mapTemp.put("exoDesde", map.get("vfpCondes"));
											mapTemp.put("exoHasta", map.get("vfpConhas"));
											
											listaMapTributos.add(mapTemp);
											
										}
									} else {
										listaMapTributos = new ArrayList<Map<String, Object>>();
									}
									
									listaMapTemp = WSContribuyenteService.consultarWSEstAnexoCont(mapParam);
									if(listaMapTemp != null && listaMapTemp.size() > 0) {
										listaMapAnexos = new ArrayList<Map<String, Object>>();
										mapCabJSON.put("visEstablecimientos", "S");
										for(Map<String, Object> map : listaMapTemp) {
											mapTemp = new HashMap<String, Object>();
											temp = "";
											
											mapTemp.put("codEstab", map.get("codTipestabl"));
											mapTemp.put("tipoEstab", map.get("descTipest"));
											
											temp = (String)map.get("codTipestabl") + " ";
											temp += (String)map.get("desNomvia") + " ";
											temp += (String)map.get("numVia") + " ";
											temp += (String)map.get("numKilom") + " ";
											temp += (String)map.get("numManza") + " ";
											temp += (String)map.get("numLote") + " ";
											temp += (String)map.get("numDpto") + " ";
											temp += (String)map.get("numInter") + " ";
											temp += (String)map.get("descTipzon") + " ";
											temp += (String)map.get("desNomzona") + " ";
											temp += (String)map.get("desRefer") + " ";
											temp += (String)map.get("descDep") + " ";
											temp += (String)map.get("descProv") + " ";
											temp += (String)map.get("descDist") + " ";
											
											mapTemp.put("direccion", temp);
											
											listaMapAnexos.add(mapTemp);											
										}
									} else {
										listaMapAnexos = new ArrayList<Map<String, Object>>();
									}
									
									listaMapTemp = WSContribuyenteService.consultarWSRepLegalCont(mapParam);
									if(listaMapTemp != null && listaMapTemp.size() > 0) {
										listaMapRepLegales = new ArrayList<Map<String, Object>>();
										mapCabJSON.put("visRepresentantes", "S");
										for(Map<String, Object> map : listaMapTemp) {
											mapTemp = new HashMap<String, Object>();
											
											mapTemp.put("cargo", Utils.toStr(map.get("rsoCargo")).toString());
											mapTemp.put("razonSoc", Utils.toStr(map.get("rsoNombre")).toString());
											mapTemp.put("tipoDoc", Utils.toStr(map.get("descDocide")).toString());
											mapTemp.put("numDoc", Utils.toStr(map.get("rsoNrodoc")).toString());
											mapTemp.put("fechaDesde", Utils.toStr(map.get("rsoVdesde")).toString());
											
											listaMapRepLegales.add(mapTemp);											
										}
									} else {
										listaMapAnexos = new ArrayList<Map<String, Object>>();
									}
									
									mapCabJSON.put("listaTributos", listaMapTributos);
									mapCabJSON.put("listaAnexos", listaMapAnexos);
									mapCabJSON.put("listaRepLegales", listaMapRepLegales);
									
									continue;
								}else{//Inicio [gangles 30/06/2016] Comprobantes de pago										
									if(codRubro.equals(ValidaConstantes.PLANT_RUBRO_COMPROBANTE_PAGO)) {
										listaMapTemp = WSContribuyenteService.consultarWSComprobantesPago(mapParam);
										if(listaMapTemp != null && listaMapTemp.size() > 0) {
											mapCabJSON.put("visComprobante", "S");
											listaMapComprobantes = new ArrayList<Map<String, Object>>();
											
											for(Map<String, Object> map : listaMapTemp) {
												mapTemp = new HashMap<String, Object>();
												temp = "";
												mapTemp.put("codTipoDoc",map.get("codTipoDoc"));
												mapTemp.put("descTipdoc", map.get("desTipoDoc"));//[jjurado 03/07/2016] se corrige Observación  P_SNADE006-5237
												mapTemp.put("numSerie", Utils.toStr(map.get("numSerie")));
												mapTemp.put("numDesde", Utils.toStr(map.get("numDesde")));
												mapTemp.put("numHasta", Utils.toStr(map.get("numHasta")));
												mapTemp.put("desFecActualiza", Utils.toStr(map.get("desFecActualiza")));
												listaMapComprobantes.add(mapTemp);
											}
										} else {
											listaMapComprobantes = new ArrayList<Map<String, Object>>();
										}	
										mapCabJSON.put("listaComprobantesPago", listaMapComprobantes);
										continue;
									}
								}
								//Fin [gangles 30/06/2016]
								
								if(codRubro.equals(ValidaConstantes.PLANT_RUBRO_DJ_INFOR)) {
									listaMapDeclaJuradas = new ArrayList<Map<String, Object>>();
									mapCabJSON.put("listaDeclaJuradas", listaMapDeclaJuradas);
									
									continue;
								}
								
								if(codRubro.equals(ValidaConstantes.PLANT_RUBRO_OPER_COMEX)) {
									listaMapOpeComExt = new ArrayList<Map<String, Object>>();
									mapCabJSON.put("listaOpeComExt", listaMapOpeComExt);
									
									continue;
								}
								
								if(codRubro.equals(ValidaConstantes.PLANT_RUBRO_SOL_DEV)) {
									listaMapDeudaTrib = new ArrayList<Map<String, Object>>();
									mapCabJSON.put("listaDeudaTrib", listaMapDeudaTrib);
								}
							}
						}
						
						mapJSON = new HashMap<String, Object>();
						mapJSON.put("cabecera", mapCabJSON);
						mapDetJSON = new ArrayList<Map<String, Object>>();
						
						mapDummy = new HashMap<String, Object>();
						mapDummy.put("key", "value");
						mapDetJSON.add(mapDummy);
						
						mapJSON.put("detalle", mapDetJSON);
						
						json = (String) (new JsonSerializer()).serialize(mapJSON);
						
						mapParam = new HashMap<String, Object>();
						mapParam.put("json", json);
						mapParam.put("idDocumento", ExportaConstantes.PDF_EXPTRAB_COD_REP_ADUANERO);						
						mapParam.put("nombreArchivo", ValidaConstantes.NOM_ARCHIVO_EXP_TRABAJO_REP_ADUANERO + idSequence);		
						mapParam.put("numRUC",  repExpTrabBean.getNumRUC());		
						mapParam.put("codTipDocu", ValidaConstantes.COD_TIPO_DOCU_REPORTE_ADUANERO);					
						mapParam.put("metadata", ValidaConstantes.METADATA_EXP_TRABAJO_REP_ADU);
						mapParam.put("prefijoNombreArchivoPDF", prefijoNombreArchivoPDF);
						mapParam.put("correlativoPDF", correlativoPDF);
						
						codIdEcm = this.guardarReporteECM(mapParam); //"EEE"; //
					} catch(Exception ex) {
						codEstGeneracionDoc = ValidaConstantes.IND_ESTADO_REP_GEN_DOC_PARCIALMENTE;
						sbLstErroresGenPedido.append(CatalogoConstantes.CATA_ERROR_GEN_DATOS_PADRON + ".");// 08 JUN eaguilar cod err al obtener datos del padron 
						errorGeneracion = true;
						correlativoPDF--;
						numErrorGeneracion++;
						log.error(ex, ex);
					}
					
					mapParam = new HashMap<String, Object>();
					//mapParam.put("origenDatos", origenDatos);//se comenta pues ya no se pasa el objeto Datasource a la consulta 
					mapParam.put("numRepDoc", idSequence);
					mapParam.put("numRepPed", repExpTrabBean.getNumRepPed());
					mapParam.put("numRepExp", repExpTrabBean.getNumRepExp());
					mapParam.put("numPedido", numPedido);
					mapParam.put("codIdecm", codIdEcm != null ? codIdEcm : ValidaConstantes.SEPARADOR_GUION);
					mapParam.put("numExpedv", ValidaConstantes.FILTRO_CERO);
					mapParam.put("fecGenera", new Date());
					mapParam.put("codEst", codEstGeneracionDoc);
					mapParam.put("codUsuModif", ValidaConstantes.SEPARADOR_GUION);
					mapParam.put("fecModif", fechaVacia.getTime());
					
					t6617RepGenDocDAO.insertar(mapParam);
					
					if (log.isDebugEnabled()) log.debug("Fin - Reporte Aduanero");
				}
				
				
				//FORMATOS ADICIONALES
				listaRepExpTrabFormatAdic = repExpTrabBean.getListaRepExpTrabFormatAdic();
				
				if(listaRepExpTrabFormatAdic != null) {
					if (log.isDebugEnabled()) log.debug("Inicio - Formatos Adicionales");
					direccion = "";
					try {
						mapParam = new HashMap<String, Object>();
						mapParam.put("numRUC", repExpTrabBean.getNumRUC());
						
						listaMapDireccion = WSContribuyenteService.consultarWSDireccionCont(mapParam);
						if(listaMapDireccion != null && listaMapDireccion.size() > 0) {
							mapDireccion = (Map<String, Object>)listaMapDireccion.get(0);
							
							referencia = (String)mapDireccion.get("desRefer");
							direccion = (String)mapDireccion.get("direccion");
							direccion = direccion != null ? direccion.trim() : "";
							
							if(direccion.equalsIgnoreCase("null")) {
								direccion = "";
							}
						}
					} catch(Exception ex) {
						referencia = "";
						direccion = "";
						log.error(ex, ex);
					}
					
					//GENERAR PDF PARA CADA FORMATO ADICIONAL DECLARADO EN PLANTILLA:
					
					//eaguilar: la lista de errores empieza completa:
					Map<Integer, String> lstErrAdi = new HashMap<Integer, String>();
					lstErrAdi.put(ExportaConstantes.PDF_EXPTRAB_COD_FORMATO_001, CatalogoConstantes.CATA_ERROR_GEN_HOJA_ANALISIS_VISUAL);
					lstErrAdi.put(ExportaConstantes.PDF_EXPTRAB_COD_FORMATO_002, CatalogoConstantes.CATA_ERROR_GEN_FORM_INTERV);
					lstErrAdi.put(ExportaConstantes.PDF_EXPTRAB_COD_FORMATO_003, CatalogoConstantes.CATA_ERROR_GEN_ACTA_INSTALACION);
					lstErrAdi.put(ExportaConstantes.PDF_EXPTRAB_COD_FORMATO_004, CatalogoConstantes.CATA_ERROR_GEN_ACTA_ARQUEO_INICIAL);
					lstErrAdi.put(ExportaConstantes.PDF_EXPTRAB_COD_FORMATO_005, CatalogoConstantes.CATA_ERROR_GEN_ACTA_ARQUEO_FIN);
					lstErrAdi.put(ExportaConstantes.PDF_EXPTRAB_COD_FORMATO_006, CatalogoConstantes.CATA_ERROR_GEN_TOMA_DICHI);
					lstErrAdi.put(ExportaConstantes.PDF_EXPTRAB_COD_FORMATO_007, CatalogoConstantes.CATA_ERROR_GEN_ANEX_ACTA_EMB);
					lstErrAdi.put(ExportaConstantes.PDF_EXPTRAB_COD_FORMATO_008, CatalogoConstantes.CATA_ERROR_GEN_ACTA_EMB_DEP_SINEX);
					
					for(String codFormato : listaRepExpTrabFormatAdic) {
						try {
							correlativoPDF++;
							idSequence = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_REPDOC);
							codEstGeneracionDoc = ValidaConstantes.IND_ESTADO_REP_GEN_DOC_GENERADO;
							codFormato = codFormato.trim();
							idDocumento = null;
							//001
							if(codFormato.equals(ValidaConstantes.PLANT_FORMATO_ADIC_HOJA_AV)) {
								//HOJA DE ANALISIS VISUAL
								idDocumento = ExportaConstantes.PDF_EXPTRAB_COD_FORMATO_001;
								mapCabJSON = new HashMap<String, Object>();
								
								mapCabJSON.put("desDependencia", repExpTrabBean.getDesDependencia().toUpperCase());
								mapCabJSON.put("desTipoExpediente", repExpTrabBean.getDesTipoExpediente().toUpperCase());
								mapCabJSON.put("referencia", referencia);
								mapCabJSON.put("numRuc", repExpTrabBean.getNumRUC());
								mapCabJSON.put("direccion", direccion);
								mapCabJSON.put("numExpedienteTrabajo", repExpTrabBean.getNumRepExp().toString());				
								mapCabJSON.put("razonSocial", repExpTrabBean.getRazonSocial());
							} 
							//002
							else {
								if(codFormato.equals(ValidaConstantes.PLANT_FORMATO_ADIC_ACTA_FOR_INTERV)) {
									idDocumento = ExportaConstantes.PDF_EXPTRAB_COD_FORMATO_002;
									
									mapCabJSON.put("desDependencia", repExpTrabBean.getDesDependencia());
									mapCabJSON.put("desTipoExpediente", repExpTrabBean.getDesTipoExpediente().toUpperCase());
									mapCabJSON.put("desDependencia", repExpTrabBean.getDesDependencia());
									//mapCabJSON.put("desTipoExpediente", repExpTrabBean.getDesTipoExpediente());
									mapCabJSON.put("deudorTributario", repExpTrabBean.getRazonSocial());
									mapCabJSON.put("numRuc", repExpTrabBean.getNumRUC());
									mapCabJSON.put("domicilioFiscal", direccion);				
									mapCabJSON.put("referenciaUbicacion", referencia);
									mapCabJSON.put("numExpediente", "");
									mapCabJSON.put("diaActa", ".......");
									mapCabJSON.put("mesActa", "................");
									mapCabJSON.put("anhoActa", (Calendar.getInstance()).get(Calendar.YEAR) + "");
									mapCabJSON.put("horaActa", "..........");
									mapCabJSON.put("numResolucionCoactiva", ".............................");
									mapCabJSON.put("nomEjecutorCoactivo", "...............................................................................");
									mapCabJSON.put("diaResolucion", ".........");
									mapCabJSON.put("anhoResolucion", ".......");
									mapCabJSON.put("mesResolucion", ".................");
									mapCabJSON.put("nombreEjecutador", ".........................................................................................................................................");
									mapCabJSON.put("domicilioEjecutado", "......................................................................................................................................................................................................................");
									mapCabJSON.put("parteEjecutada", "..........................................................................................................");
									mapCabJSON.put("dniParteEjecutada", "............................");
									mapCabJSON.put("calidadParteEjecutada", "............................");
								} else { //003
									if(codFormato.equals(ValidaConstantes.PLANT_FORMATO_ADIC_ACTA_INSTAL)) {
										idDocumento = ExportaConstantes.PDF_EXPTRAB_COD_FORMATO_003;
										
										mapCabJSON.put("desTipoExpediente", repExpTrabBean.getDesTipoExpediente().toUpperCase());
										mapCabJSON.put("desDependencia", repExpTrabBean.getDesDependencia());
										mapCabJSON.put("desTipoExpediente", repExpTrabBean.getDesTipoExpediente());
										mapCabJSON.put("desEjecutado", repExpTrabBean.getRazonSocial());
										mapCabJSON.put("numRuc", repExpTrabBean.getNumRUC());
										mapCabJSON.put("numExpediente", "");
										mapCabJSON.put("desDistrito", "..................................");
										mapCabJSON.put("diaActa", ".......");
										mapCabJSON.put("mesActa", "....................");
										mapCabJSON.put("anhoActa", "..........");
										mapCabJSON.put("horaActa", "..........");
										mapCabJSON.put("nomEjecutorCoactivo", "..............................................................................................................");
										mapCabJSON.put("numResolucionCoactiva", ".....................");
										mapCabJSON.put("domicilioFiscal", ".....................................................................................................................................................................................................");
										mapCabJSON.put("nombreEjecutado", "...................................................................................................");
										mapCabJSON.put("dniEjecutado", "............................");
										mapCabJSON.put("calidadEjecutado", ".................................");
								} else { //004
										if(codFormato.equals(ValidaConstantes.PLANT_FORMATO_ADIC_ACTA_ARQUEO_INICIAL)) {
											idDocumento = ExportaConstantes.PDF_EXPTRAB_COD_FORMATO_004;
											
											mapCabJSON = new HashMap<String, Object>();
											
											mapCabJSON.put("desDependencia", repExpTrabBean.getDesDependencia().toUpperCase());
											mapCabJSON.put("desTipoExpediente", repExpTrabBean.getDesTipoExpediente().toUpperCase());
											mapCabJSON.put("numRuc", repExpTrabBean.getNumRUC());
											mapCabJSON.put("numExpedienteTrabajo", repExpTrabBean.getNumRepExp().toString());				
											mapCabJSON.put("razonSocial", repExpTrabBean.getRazonSocial());
										} else { //005
												if(codFormato.equals(ValidaConstantes.PLANT_FORMATO_ADIC_ACTA_ARQUEO_FINAL)) {
													idDocumento = ExportaConstantes.PDF_EXPTRAB_COD_FORMATO_005;
													
													mapCabJSON = new HashMap<String, Object>();
													
													mapCabJSON.put("desDependencia", repExpTrabBean.getDesDependencia().toUpperCase());
													mapCabJSON.put("desTipoExpediente", repExpTrabBean.getDesTipoExpediente().toUpperCase());
													mapCabJSON.put("numRuc", repExpTrabBean.getNumRUC());
													mapCabJSON.put("numExpedienteTrabajo", repExpTrabBean.getNumRepExp().toString());				
													mapCabJSON.put("razonSocial", repExpTrabBean.getRazonSocial());
												} /*else {
														if(codFormato.equals(ValidaConstantes.PLANT_FORMATO_ADIC_ACTA_CONTRIBUY)) {
															idDocumento = null;
														} else {
															if(codFormato.equals(ValidaConstantes.PLANT_FORMATO_ADIC_HOJA_RELEVA)) {
																idDocumento = null;
															}
														}
													}*/
												// Inicio [gangles 25/05/2016] Se agregan tres formatos adicionales (MOdulo 5)												 
												else{ //006									
													if(codFormato.equals(ValidaConstantes.PLANT_FORMATO_ADIC_ACTA_TOMA_DICHO)) {
														idDocumento = ExportaConstantes.PDF_EXPTRAB_COD_FORMATO_006;
														
														mapCabJSON = new HashMap<String, Object>();														
														mapCabJSON.put("desDependencia", repExpTrabBean.getDesDependencia().toUpperCase());
														mapCabJSON.put("desTipoExpediente", repExpTrabBean.getDesTipoExpediente().toUpperCase());
														mapCabJSON.put("referencia", referencia);
														mapCabJSON.put("numRuc", repExpTrabBean.getNumRUC());
														mapCabJSON.put("direccion", direccion);
														mapCabJSON.put("numExpedienteTrabajo", repExpTrabBean.getNumRepExp().toString());				
														mapCabJSON.put("razonSocial", repExpTrabBean.getRazonSocial());
													}else { //007
														if(codFormato.equals(ValidaConstantes.PLANT_FORMATO_ADIC_ANEXO_ACTA)) {
															idDocumento = ExportaConstantes.PDF_EXPTRAB_COD_FORMATO_007;
															
															mapCabJSON = new HashMap<String, Object>();
															
															mapCabJSON.put("desDependencia", repExpTrabBean.getDesDependencia().toUpperCase());
															mapCabJSON.put("desTipoExpediente", repExpTrabBean.getDesTipoExpediente().toUpperCase());
															mapCabJSON.put("numRuc", repExpTrabBean.getNumRUC());
															mapCabJSON.put("numExpedienteTrabajo", repExpTrabBean.getNumRepExp().toString());				
															mapCabJSON.put("razonSocial", repExpTrabBean.getRazonSocial());
														}else { //008
															if(codFormato.equals(ValidaConstantes.PLANT_FORMATO_ADIC_ACTA_EMBARGO)) {
																idDocumento = ExportaConstantes.PDF_EXPTRAB_COD_FORMATO_008;
																
																mapCabJSON = new HashMap<String, Object>();
																
																mapCabJSON.put("desDependencia", repExpTrabBean.getDesDependencia().toUpperCase());
																mapCabJSON.put("desTipoExpediente", repExpTrabBean.getDesTipoExpediente().toUpperCase());
																mapCabJSON.put("numRuc", repExpTrabBean.getNumRUC());
																mapCabJSON.put("numExpedienteTrabajo", repExpTrabBean.getNumRepExp().toString());				
																mapCabJSON.put("razonSocial", repExpTrabBean.getRazonSocial());
																if(Utils.isEmpty(direccion)){
																	direccion = "..........................................";
																}
																mapCabJSON.put("domFiscal", direccion );
															}
													}
												}
													
											}
										    // Fin [gangles 25/05/2016]												
										}
									}
								}	
							}
							
							if(idDocumento != null) {
								mapJSON = null;
								mapJSON = new HashMap<String, Object>();
								mapJSON.put("cabecera", mapCabJSON);
								mapDetJSON = new ArrayList<Map<String, Object>>();
								
								mapDummy = new HashMap<String, Object>();
								mapDummy.put("key", "value");
								mapDetJSON.add(mapDummy);
								
								mapJSON.put("detalle", mapDetJSON);
								
								json = (String) (new JsonSerializer()).serialize(mapJSON);
								
								mapParam = new HashMap<String, Object>();
								
								mapParam.put("json", json);
								mapParam.put("idDocumento", idDocumento);						
								mapParam.put("nombreArchivo", ValidaConstantes.NOM_ARCHIVO_EXP_TRABAJO_REP_FORMATO + idSequence);		
								mapParam.put("numRUC",  repExpTrabBean.getNumRUC());		
								mapParam.put("codTipDocu", ValidaConstantes.COD_TIPO_DOCU_REPORTE_FORMATO);					
								mapParam.put("metadata", ValidaConstantes.METADATA_EXP_TRABAJO_FORMATO);
								mapParam.put("prefijoNombreArchivoPDF", prefijoNombreArchivoPDF);
								mapParam.put("correlativoPDF", correlativoPDF);								
								codIdEcm = this.guardarReporteECM(mapParam); //"FFF"+idDocumento; //
							} else {
								correlativoPDF--;
							}
						} catch(Exception ex) {
							log.error(ex, ex);							
							codEstGeneracionDoc = ValidaConstantes.IND_ESTADO_REP_GEN_DOC_PARCIALMENTE; //eaguilar : ante un error, estado es "Parcialmente", no "Incompleto" 
							errorGeneracion = true;
							correlativoPDF--;
							numErrorGeneracion++;
							//eaguilar: 27 JUN
							for (Map.Entry<Integer, String> sError : lstErrAdi.entrySet()){
								if (sError.getKey().equals(idDocumento)){
									sbLstErroresGenPedido.append(sError.getValue().trim() + ".");// 08 JUN EAGUILAR - acumulado de errores form-adicionales
									break;
								}
							}
						}
						
						if(idDocumento != null) {
							mapParam = new HashMap<String, Object>();
							//mapParam.put("origenDatos", origenDatos);//se comenta pues ya no se pasa el objeto Datasource a la consulta 
							mapParam.put("numRepDoc", idSequence);
							mapParam.put("numRepPed", repExpTrabBean.getNumRepPed());
							mapParam.put("numRepExp", repExpTrabBean.getNumRepExp());
							mapParam.put("numPedido", numPedido);
							mapParam.put("codIdecm", codIdEcm != null ? codIdEcm : ValidaConstantes.SEPARADOR_GUION);
							mapParam.put("numExpedv", ValidaConstantes.FILTRO_CERO);
							mapParam.put("fecGenera", new Date());
							mapParam.put("codEst", codEstGeneracionDoc);
							mapParam.put("codUsuModif", ValidaConstantes.SEPARADOR_GUION);
							mapParam.put("fecModif", fechaVacia.getTime());
							
							t6617RepGenDocDAO.insertar(mapParam);
						}
						
				}
					
				if (log.isDebugEnabled()) log.debug("Fin - Formatos Adicionales");	
			}

			
			//[eaguilar 3 JUN] - INI (10 JUN: se comenta hasta que se pueda probar el servicio RENIEC)
			try{
				Map<String, Map<String, Object>> mapRepresentantes = new HashMap<String, Map<String,Object>>();
				//lista de DNIS:
				List<String> listaDni = new ArrayList<String>();
				//si RUC es Nat y tiene DNI
				Map<String, Object> mapPersonaNatural = null;//Se agrega en la lista de los representantes para también mostrar su foto en el PDF
				if(repExpTrabBean.getTipoPersona().equals("01") && repExpTrabBean.getTipoDoc().equals("1")){
					listaDni.add(repExpTrabBean.getNumDoc().trim());
					
					mapPersonaNatural = new HashMap<String, Object>();
					mapPersonaNatural.put(CatalogoConstantes.KEY_REP_LEG_NUM_DOC, repExpTrabBean.getNumDoc());
					mapPersonaNatural.put(CatalogoConstantes.KEY_REP_LEG_APE_NOM_O_RAZ_SOC, repExpTrabBean.getRazonSocial());
					mapPersonaNatural.put(CatalogoConstantes.KEY_REP_LEG_DESC_TIP_RELAC, "REPRESENTANTE LEGAL");
					mapRepresentantes.put(repExpTrabBean.getNumDoc().trim(), mapPersonaNatural);
				}

				//obtener la lista de rep legales:
				Map<String, Object> paramLegal = new HashMap<String, Object>();
				paramLegal.put("numRUC", repExpTrabBean.getNumRUC());
				List<Map<String, Object>> listaRepLegales = WSContribuyenteService.consultarWSRepLegalCont(paramLegal);
				for(Map<String, Object> replegal : listaRepLegales){
					listaDni.add(Utils.toStr(replegal.get(CatalogoConstantes.KEY_REP_LEG_NUM_DOC)));//armar lista de DNIs
					mapRepresentantes.put(Utils.toStr(replegal.get(CatalogoConstantes.KEY_REP_LEG_NUM_DOC)), replegal);
				}
				//obtener las fichas RENIEC usando el DNI relacionado al RUC (si es Nat) y sus rep legales (Nat o Jur)
				List<Map<String, Object>> fichaReniec = null;
				
				List<byte[]> fotos = new ArrayList<byte[]>();
				Blob oBlob = null;
				byte[] imagenBytes = null;
				Image imagen = null;
				String domicilioRepresLegal = ValidaConstantes.CADENA_VACIA;
				for(String dni : listaDni){
					//para cada ficha convertir a PDF las fotografias y anexar al archivo Original
					
					//PARA PRODUCCION:
					//fichaReniec = Utils.consultarWS("");
					//falta implementar...
					
					//PARA CALIDAD Y DESARROLLO:
					Map<String, Object> mapa733 = validarParametrosService.obtenerFoto(dni); 
					if(mapa733 != null){
						domicilioRepresLegal = Utils.toStr(mapa733.get("desDomicilio"));
						oBlob = (Blob) mapa733.get("imgFoto");
						if(oBlob != null){
							imagenBytes = oBlob.getBytes(1, (int) oBlob.length());
							//imagen = Image.getInstance(imagenBytes);
							fotos.add(imagenBytes);
						}
					}
					mapRepresentantes.get(dni).put("fotoReniec", imagenBytes);
					mapRepresentantes.get(dni).put(CatalogoConstantes.KEY_REP_LEG_DOMICILIO, domicilioRepresLegal);
				}
				
				Iterator<String> itDnis = mapRepresentantes.keySet().iterator();
				String dniRepLegal;
				String nameId = ValidaConstantes.CADENA_VACIA;
				Map<String, Object> mapRepLegal;
				while(itDnis.hasNext()){
					dniRepLegal = itDnis.next();
					mapRepLegal = mapRepresentantes.get(dniRepLegal);
					if( mapRepLegal.get("fotoReniec")!=null){
						byte[] foto = (byte[])mapRepLegal.get("fotoReniec");
			    		nameId = "" + new Date().getTime();
			    		
			    		
			    		correlativoPDF++;
						codEstGeneracionDoc = ValidaConstantes.IND_ESTADO_REP_GEN_DOC_GENERADO;
						codIdEcm = ValidaConstantes.SEPARADOR_GUION;
						nombreArchivoDocPDF = ValidaConstantes.CARPETA_TEMPORAL_TEMPO + prefijoNombreArchivoPDF + "_" + (correlativoPDF) + "." + ValidaConstantes.TIPO_ARCHIVO_PDF;
	
						//archivo foto temp:
						nombreArchivoTemp = ValidaConstantes.CARPETA_TEMPORAL_TEMPO + prefijoNombreArchivoPDF + "_" + nameId + ".jpg";
						fos = new FileOutputStream(nombreArchivoTemp);
						fos.write(foto);
						fos.close();
				        
						String[] listaDatos = {
								Utils.toStr(mapRepLegal.get(CatalogoConstantes.KEY_REP_LEG_NUM_DOC)),//DNI
								Utils.toStr(mapRepLegal.get(CatalogoConstantes.KEY_REP_LEG_APE_NOM_O_RAZ_SOC)),//NOMBRE Y APELLIDOS
								//direccionContrib,//"Mz. A2 Lte. 2 Calle 3 Urb. Sol de Chaclacayo - Lurigancho Chosica",//DIRECCIÓN
								Utils.toStr(mapRepLegal.get(CatalogoConstantes.KEY_REP_LEG_DOMICILIO)),
								Utils.toStr(mapRepLegal.get(CatalogoConstantes.KEY_REP_LEG_DESC_TIP_RELAC))//RELACIÓN CON CONTRIBUYENTE
								};
				        Utils.convertFotoYDatosRepresLegalAPDF(nombreArchivoTemp, nombreArchivoDocPDF, listaDatos);
				        
	//			        Utils.agregarDatosRepresentanteLegal(nombreArchivoDocPDF);
				        
				        archivoFile = new File(nombreArchivoDocPDF);
				        fileInputStream  = new FileInputStream(archivoFile);
						bytesArchivo = new byte[(int) archivoFile.length()];
						fileInputStream.read(bytesArchivo);
				        fileInputStream.close();
				        
						beanEcm = new HashMap<String, Object>();	
						beanEcm.put("nomArchivoSinExt", "foto_repLegal_" + nameId);
						beanEcm.put("numExpeDv", "0");
						beanEcm.put("numRuc", repExpTrabBean.getNumRUC());
						beanEcm.put("numDoc", "-");
						beanEcm.put("fecEmi", new Date());
						beanEcm.put("codTipExpVir", "000");
						beanEcm.put("codTipDocu", ValidaConstantes.IND_CLASE_TIP_DOC_REQUERIMIENTO); //doc de contrib		
						beanEcm.put("archLength", bytesArchivo.length);
						beanEcm.put("archMimeType", ValidaConstantes.HTTP_ACCEPT_PDF);
						beanEcm.put("archFileName", "foto_repLegal_" + nameId + "." + ValidaConstantes.TIPO_ARCHIVO_PDF);
						beanEcm.put("archContent", bytesArchivo);
						beanEcm.put("metadata", ValidaConstantes.METADATA_EXP_TRABAJO_GENERADO);
						
						codIdEcm = ecmService.guardarDocumentoECM(beanEcm); //"GGG"; //
				        
				        archivoTempFile = new File(nombreArchivoTemp);
				        archivoTempFile.delete();
					}
		    	}
			}
			catch(Exception ex){
				log.error(ex, ex);							
				codEstGeneracionDoc = ValidaConstantes.IND_ESTADO_REP_GEN_DOC_PARCIALMENTE; //eaguilar : ante un error, estado es "Parcialmente", no "Incompleto"
				sbLstErroresGenPedido.append(CatalogoConstantes.CATA_ERROR_GEN_DATOS_PADRON + ".");// 08 JUN eaguilar cod err al obtener datos del padron
				errorGeneracion = true;
				correlativoPDF--;
				numErrorGeneracion++;
				log.error(ex, ex);
				codIdEcm = ValidaConstantes.SEPARADOR_GUION;
			}
			//[eaguilar 3 JUN] - FIN
			
				
			// Generar un PDF con todos los pdfs
			codEstGeneracionExp = ValidaConstantes.IND_ESTADO_REP_GEN_EXP_GENERADO;
			String fechaGeneracion = ValidaConstantes.FILTRO_UNO;
			if(errorGeneracion && numErrorGeneracion == correlativoPDF) {
				codEstGeneracionExp = ValidaConstantes.IND_ESTADO_REP_GEN_EXP_PARCIALMENTE; //eaguilar : ante un erro, estado es "Parcialmente", no "Incompleto"
				fechaGeneracion = ValidaConstantes.FILTRO_CERO;
			} else {
				try {
					if (log.isDebugEnabled()) log.debug("Inicio - Consolidado");	
					
					pdfs = new String[correlativoPDF];
					for(int i = 0; i < correlativoPDF; i++) {
						pdfs[i] = prefijoNombreArchivoPDF + "_" + (i + 1) + "." + ValidaConstantes.TIPO_ARCHIVO_PDF;
					}
					
					Utils.mergePDF(ValidaConstantes.CARPETA_TEMPORAL_TEMPO, pdfs, prefijoNombreArchivoPDF + "_consolidado." + ValidaConstantes.TIPO_ARCHIVO_PDF);
					
					archivoConsolidadoFile = new File(ValidaConstantes.CARPETA_TEMPORAL_TEMPO + prefijoNombreArchivoPDF + "_consolidado." + ValidaConstantes.TIPO_ARCHIVO_PDF);
					fileInputStream  = new FileInputStream(archivoConsolidadoFile);
					bytesArchivoConsolidado = new byte[(int) archivoConsolidadoFile.length()];
			        fileInputStream.read(bytesArchivoConsolidado);
			        fileInputStream.close();
			        
					beanEcm = new HashMap<String, Object>();	
					
					beanEcm.put("nomArchivoSinExt", ValidaConstantes.NOM_ARCHIVO_EXP_TRABAJO_REP_CONSOLIDADO);
					beanEcm.put("numExpeDv", "0");
					beanEcm.put("numRuc", repExpTrabBean.getNumRUC());
					beanEcm.put("numDoc", "-");
					beanEcm.put("fecEmi", new Date());
					beanEcm.put("codTipExpVir", "000");
					beanEcm.put("codTipDocu", ValidaConstantes.COD_TIPO_DOCU_REPORTE_CONSOLIDADO);			
					beanEcm.put("archLength", bytesArchivoConsolidado.length);
					beanEcm.put("archMimeType", ValidaConstantes.HTTP_ACCEPT_PDF);
					beanEcm.put("archFileName", ValidaConstantes.NOM_ARCHIVO_EXP_TRABAJO_REP_CONSOLIDADO + "." + ValidaConstantes.TIPO_ARCHIVO_PDF);
					beanEcm.put("archContent", bytesArchivoConsolidado);
					beanEcm.put("metadata", ValidaConstantes.METADATA_EXP_TRABAJO_CONSOLIDADO);
					
					codIdEcm = ecmService.guardarDocumentoECM(beanEcm); //ultimo "HHH"; //
					correlativoPDF++;
					
					if (log.isDebugEnabled()) log.debug("Fin - Consolidado");
				} catch(Exception ex) {
					log.error(ex, ex);
					
					if(numErrorGeneracion == correlativoPDF) {
						codEstGeneracionExp = ValidaConstantes.IND_ESTADO_REP_GEN_EXP_ERROR;
						//codEstGeneracionExp = ValidaConstantes.IND_ESTADO_REP_GEN_EXP_PENDIENTE; << consultar
					} else {
						codEstGeneracionExp = ValidaConstantes.IND_ESTADO_REP_GEN_EXP_PARCIALMENTE;
					}
					sbLstErroresGenPedido.append(CatalogoConstantes.CATA_ERROR_GEN_DOC_CAMBIO_FORMATO + ".");// 08 JUN eaguilar - cod err al generar pdf contenedor
					fechaGeneracion = ValidaConstantes.FILTRO_CERO;
					errorGeneracion = true;
					numErrorGeneracion++;
					log.error(ex, ex);
				}		
			}
				
			// Actualizar en T6619
			mapParam = new HashMap<String, Object>();
			//mapParam.put("origenDatos", origenDatos);//se comenta pues ya no se pasa el objeto Datasource a la consulta 
			mapParam.put("numRepPed", repExpTrabBean.getNumRepPed());
			mapParam.put("codEstado", codEstGeneracionExp);
			mapParam.put("codUsuModif", codUsuario);
			if(Utils.equiv(fechaGeneracion, ValidaConstantes.FILTRO_UNO)){
				mapParam.put("fecGenera", new Date());
			}
			mapParam.put("fecModif", new Date());
			
			t6619RepGenPedDAO.actualizar(mapParam);
			
			// Actualizar en T6618
			mapParam = new HashMap<String, Object>();
			//mapParam.put("origenDatos", origenDatos);//se comenta pues ya no se pasa el objeto Datasource a la consulta 
			mapParam.put("numRepExp", repExpTrabBean.getNumRepExp());
			mapParam.put("codIdEcm", codIdEcm != null ? codIdEcm : ValidaConstantes.SEPARADOR_GUION);
			mapParam.put("codEstado", codEstGeneracionExp);
			mapParam.put("codUsuModif", codUsuario);
			if(Utils.equiv(fechaGeneracion, ValidaConstantes.FILTRO_UNO)){
				mapParam.put("fecGenera", new Date());
			}
			mapParam.put("fecModif", new Date());
			
			//se anade lista de errores:
			mapParam.put("codError", ValidaConstantes.SEPARADOR_GUION); //eaguilar 04 JUL por defecto guion
			if (sbLstErroresGenPedido.length() > 0) {
				String codError = sbLstErroresGenPedido.substring(0, sbLstErroresGenPedido.length()-1);
				mapParam.put("codError", codError);
			}
			
			t6618RepGenExpDAO.actualizar(mapParam);
			
			if(errorGeneracion) {
				if(numErrorGeneracion == correlativoPDF) {
					codEstGeneracionPedido = ValidaConstantes.IND_ESTADO_REP_GEN_PEDIDO_ERROR;
				} else {
					codEstGeneracionPedido = ValidaConstantes.IND_ESTADO_REP_GEN_PEDIDO_PARCIALMENTE;
				}
			} else {
				codEstGeneracionPedido = ValidaConstantes.IND_ESTADO_REP_GEN_PEDIDO_GENERADO;
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ExpedienteTrabReporteServiceImpl.generarReporte");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ExpedienteTrabReporteServiceImpl.generarReporte");
			
			NDC.pop();
			NDC.remove();
		}
		
		return codEstGeneracionPedido;
	}
	
	@Override
	public void generarReporteRUC(List<RepExpTrabBean> listaRepExpTrabBeans, String numPedido, String codUsuario) {
		Map<String, Object> mapParam = null;
		RepExpTrabBean repExpTrabBean = null;
		String codEstGeneracionPedido = "";
		
		try {
			if (log.isDebugEnabled()) log.debug("Inicio - ExpedienteTrabReporteServiceImpl.generarReporteRUC");		
			
			if(listaRepExpTrabBeans != null && listaRepExpTrabBeans.size() > 0) {
				repExpTrabBean = listaRepExpTrabBeans.get(0);
				
				mapParam = new HashMap<String, Object>();
				mapParam.put("codEstado", ValidaConstantes.IND_ESTADO_REP_GEN_PEDIDO_PROCESANDO);
				mapParam.put("numPedido", numPedido);
				mapParam.put("codUsuModif", codUsuario);
				mapParam.put("fecModif", new Date());
				
				this.actualizarPedido(mapParam);
				
				codEstGeneracionPedido = this.generarReporte(numPedido, codUsuario, repExpTrabBean);
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ExpedienteTrabReporteServiceImpl.generarReporteRUC");
			log.error(ex, ex);
			codEstGeneracionPedido = ValidaConstantes.IND_ESTADO_REP_GEN_PEDIDO_ERROR;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ExpedienteTrabReporteServiceImpl.generarReporteRUC");
			
			mapParam = new HashMap<String, Object>();
			mapParam.put("codEstado", codEstGeneracionPedido);
			mapParam.put("numPedido", numPedido);
			mapParam.put("codUsuModif", codUsuario);
			mapParam.put("fecModif", new Date());
			
			try {
				this.actualizarPedido(mapParam);
			} catch(Exception ex) {
				log.error(ex, ex);
			}
			
			NDC.pop();
			NDC.remove();
		}
	}
	
	@Override
	public void generarReporteMasivo(List<String> listaNumPedido) throws Exception {
		List<RepExpTrabBean> listaRepExpTrabBeans = null;
		Map<String, Object> mapParam = null;
		String codEstGeneracionPedido = "";
		String codEstGenRespuesta = "";
		boolean flgIncompleto = false;
		
		try {
			if (log.isDebugEnabled()) log.debug("Inicio - ExpedienteTrabReporteServiceImpl.generarReporteMasivo");
			
			if(listaNumPedido != null && listaNumPedido.size() > 0) {
				for(String numPedido : listaNumPedido) {
					listaRepExpTrabBeans = this.obtenerDatosGeneraRep(numPedido);
					
					if(listaRepExpTrabBeans != null) {
						mapParam = new HashMap<String, Object>();
						mapParam.put("codEstado", ValidaConstantes.IND_ESTADO_REP_GEN_PEDIDO_PROCESANDO);
						mapParam.put("numPedido", numPedido);
						mapParam.put("codUsuModif", ValidaConstantes.COD_USUARIO_EXPTRAB_BATCH);
						mapParam.put("fecModif", new Date());
						
						this.actualizarPedido(mapParam);
						
						try {
							codEstGeneracionPedido = ValidaConstantes.IND_ESTADO_PEDIDO_REP_GENERADO;
							
							for(RepExpTrabBean repExpTrabBean : listaRepExpTrabBeans) {
								codEstGenRespuesta = this.generarReporte(numPedido, ValidaConstantes.COD_USUARIO_EXPTRAB_BATCH, repExpTrabBean);
								
								if(	codEstGenRespuesta.equals(ValidaConstantes.IND_ESTADO_REP_GEN_PEDIDO_ERROR) ) {
									//flgIncompleto = true;
									flgIncompleto = false;
								}
							}
						} catch(Exception e) {
							codEstGeneracionPedido = ValidaConstantes.IND_ESTADO_REP_GEN_PEDIDO_ERROR;
						}
						
						/*if(flgIncompleto) {
							codEstGeneracionPedido = ValidaConstantes.IND_ESTADO_REP_GEN_PEDIDO_INCOMPLETO;
						}*/
						
						mapParam = new HashMap<String, Object>();
						mapParam.put("codEstado", codEstGeneracionPedido);
						mapParam.put("numPedido", numPedido);
						mapParam.put("codUsuModif", ValidaConstantes.COD_USUARIO_EXPTRAB_BATCH);
						mapParam.put("fecModif", new Date());
						
						this.actualizarPedido(mapParam);
					}
				}
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ExpedienteTrabReporteServiceImpl.generarReporteMasivo");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ExpedienteTrabReporteServiceImpl.generarReporteMasivo");
			
			NDC.pop();
			NDC.remove();
		}
	}
	
	@Override
	public List<RepExpTrabBean> obtenerDatosGeneraRep(String numPedido) throws Exception {
		List<RepExpTrabBean> listaRepExpTrabBeans = null;
		List<Map<String, Object>> listaPedido = null;
		List<Map<String, Object>> listaNumExps = null;
		List<Map<String, Object>> listaDocs = null;
		List<Map<String, Object>> listaDetPlantillas = null;
		RepExpTrabBean repExpTrabBean = null;
		Map<String, Object> mapParam = null;
		String numRUC = null;
		List<RepExpTrabExpVirtBean> listaRepExpTrabExpVirtBeans = null;
		RepExpTrabExpVirtBean repExpTrabExpVirtBean = null;
		List<RepExpTrabDocBean> listaRepExpTrabDocBeans = null;
		RepExpTrabDocBean repExpTrabDocBean = null;
		String codTipDocSust = null;
		List<T6611CabPlantiBean> listaCabPlantilla = null;
		T6611CabPlantiBean t6611CabPlantiBean = null;
		List<String> listaRepExpTrabRubro = null;
		List<String> listaRepExpTrabFormatAdic = null;
		List<String> listaCodTipDocs = null;
		String indSelDoc = null;
		String indSelRubro = null;
		String indSelForma = null;
		String codTipDoc = null;
		String codFormadi = null;
		String codRubr = null;
		Map<String, String> mapDependencias = null;
		Map<String, String> mapProcesos = null;
		Map<String, String> mapTiposExpediente = null;
		DdpBean ddpBean = null;
		
		try {
			if (log.isDebugEnabled()) log.debug("Inicio - ExpedienteTrabReporteServiceImpl.obtenerDatosGeneraRep");		
			
			mapParam = new HashMap<String, Object>();
			mapParam.put("numPedido", numPedido);
			mapParam.put("codEstadoPedRep", ValidaConstantes.IND_ESTADO_PEDIDO_REP_PENDIENTE);
			mapParam.put("codEstadoRepGenPed", ValidaConstantes.IND_ESTADO_REP_GEN_PEDIDO_PENDIENTE);
			mapParam.put("codEstadoRepGenExp", ValidaConstantes.IND_ESTADO_REP_GEN_EXP_PENDIENTE);
			
			listaPedido = t6618RepGenExpDAO.listarPedidoGen(mapParam);

			if(listaPedido != null && listaPedido.size() > 0) {
				listaRepExpTrabBeans = new ArrayList<RepExpTrabBean>();
				
				for(Map<String, Object> mapPedido : listaPedido) {
					repExpTrabBean = new RepExpTrabBean();
					
					mapDependencias = configuracionExpedienteService.listarMapDependencias();
					mapProcesos = configuracionExpedienteService.listarMapProcesos();
					mapTiposExpediente = configuracionExpedienteService.listarMapTiposExpediente();
					
					numRUC = (String)mapPedido.get("numRUC");
					ddpBean = validarParametrosService.obtenerContribuyente(numRUC);
					
					repExpTrabBean.setNumRepPed((Integer)mapPedido.get("numRepPed"));
					repExpTrabBean.setNumRepExp((Integer)mapPedido.get("numRepExp"));
					
					repExpTrabBean.setNumRUC(numRUC);
					repExpTrabBean.setRazonSocial(ddpBean.getDesRazonSocial() != null ? ddpBean.getDesRazonSocial().trim() : "");									
					repExpTrabBean.setNumPlantilla((String)mapPedido.get("numPlantilla"));
					repExpTrabBean.setDesDependencia(mapDependencias.get(ddpBean.getCodDependencia()));
					repExpTrabBean.setDesDependencia(repExpTrabBean.getDesDependencia() != null ? repExpTrabBean.getDesDependencia().trim() : "");
					repExpTrabBean.setCodProceso((String)mapPedido.get("codProceso"));
					repExpTrabBean.setCodTipoExpediente((String)mapPedido.get("codTipoExpediente"));
					repExpTrabBean.setDesProceso(mapProcesos.get(repExpTrabBean.getCodProceso()));
					repExpTrabBean.setDesProceso(repExpTrabBean.getDesProceso() != null ? repExpTrabBean.getDesProceso().trim() : "");
					repExpTrabBean.setDesTipoExpediente(mapTiposExpediente.get(repExpTrabBean.getCodTipoExpediente()));
					repExpTrabBean.setDesTipoExpediente(repExpTrabBean.getDesTipoExpediente() != null ? repExpTrabBean.getDesTipoExpediente().trim() : "");
					
					//[eaguilar 03 JUN] campos extra para obtencion de ficha reniec
					repExpTrabBean.setTipoPersona(ddpBean.getTipoPersona());
					repExpTrabBean.setTipoDoc(Utils.isEmpty(ddpBean.getTipoDoc())?ValidaConstantes.CADENA_VACIA:ddpBean.getTipoDoc());
					repExpTrabBean.setNumDoc(Utils.isEmpty(ddpBean.getNumDoc())?ValidaConstantes.CADENA_VACIA:ddpBean.getNumDoc());
					
					mapParam = new HashMap<String, Object>();
					mapParam.put("numPlantilla", repExpTrabBean.getNumPlantilla());
					mapParam.put("indEstado", ValidaConstantes.IND_ESTADO_PLANTILLA_ACTIVO);
					listaCabPlantilla = t6611CabPlantiDAO.listar(mapParam);
					
					if(listaCabPlantilla != null && listaCabPlantilla.size() > 0) {
						t6611CabPlantiBean = listaCabPlantilla.get(0);
						
						repExpTrabBean.setIndReporteTrib(t6611CabPlantiBean.getIndRepTrib());
						
						mapParam = new HashMap<String, Object>();
						mapParam.put("numPlantilla", repExpTrabBean.getNumPlantilla());
						
						listaDetPlantillas = t6612DetPlantiDAO.listarExpTrab(mapParam);
						if(listaDetPlantillas != null && listaDetPlantillas.size() > 0) {
							listaCodTipDocs = new ArrayList<String>();
							listaRepExpTrabRubro = new ArrayList<String>();
							listaRepExpTrabFormatAdic = new ArrayList<String>();
							
							for(Map<String, Object> mapDetPlanti : listaDetPlantillas) {
								indSelDoc = (String)mapDetPlanti.get("indSelDoc");
								indSelRubro = (String)mapDetPlanti.get("indSelRubro");
								indSelForma = (String)mapDetPlanti.get("indSelForma");
								
								if(indSelDoc != null && indSelDoc.equals("1")) {
									codTipDoc = (String)mapDetPlanti.get("codTipDoc");
									listaCodTipDocs.add(codTipDoc);
								}
								
								if(indSelRubro != null && indSelRubro.equals("1")) {
									codRubr = (String)mapDetPlanti.get("codRubr");
									listaRepExpTrabRubro.add(codRubr);
								}
								
								if(indSelForma != null && indSelForma.equals("1")) {
									codFormadi = (String)mapDetPlanti.get("codFormadi");
									listaRepExpTrabFormatAdic.add(codFormadi);
								}
							}
							
							repExpTrabBean.setListaRepExpTrabFormatAdic(listaRepExpTrabFormatAdic);
							repExpTrabBean.setListaRepExpTrabRubro(listaRepExpTrabRubro);
							
							mapParam = new HashMap<String, Object>();
							mapParam.put("numRUC", numRUC);
							mapParam.put("codTipExpediente", repExpTrabBean.getCodTipoExpediente());
							mapParam.put("codProceso", repExpTrabBean.getCodProceso());
							listaNumExps = t6614ExpVirtualDAO.listarExpedienteTrab(mapParam);
							
							if(listaNumExps != null && listaNumExps.size() > 0) {
								listaRepExpTrabExpVirtBeans = new ArrayList<RepExpTrabExpVirtBean>();
								
								for(Map<String, Object> mapNumExp: listaNumExps) {
									mapParam = new HashMap<String, Object>();
									mapParam.put("numExpedv", (String)mapNumExp.get("numExpedv"));
									mapParam.put("codIdEcm", ValidaConstantes.SEPARADOR_GUION);		
									mapParam.put("listaCodTipDocs", listaCodTipDocs);
									listaDocs = t6613DocExpVirtDAO.listarDocExpTrab(mapParam);
									
									if(listaDocs != null && listaDocs.size() > 0) {
										repExpTrabExpVirtBean = new RepExpTrabExpVirtBean();
										
										repExpTrabExpVirtBean.setNumExpedienteVirtual((String)mapNumExp.get("numExpedv"));
										repExpTrabExpVirtBean.setNumExpedienteOrigen((String)mapNumExp.get("numExpedo"));
										repExpTrabExpVirtBean.setCodigoProceso((String)mapNumExp.get("codProc"));
										repExpTrabExpVirtBean.setCodigoTipExp((String)mapNumExp.get("codTipExp"));
										repExpTrabExpVirtBean.setCodigoDependencia((String)mapNumExp.get("codDep"));
										
										listaRepExpTrabDocBeans = new ArrayList<RepExpTrabDocBean>();
										for(Map<String, Object> mapDocs: listaDocs) {
											repExpTrabDocBean = new RepExpTrabDocBean();
											
											repExpTrabDocBean.setNombreDocumento((String)mapDocs.get("desArch"));
											repExpTrabDocBean.setCodigoTipoDocumento((String)mapDocs.get("codTipDoc"));
											repExpTrabDocBean.setNumDocumento((String)mapDocs.get("numDoc"));
											repExpTrabDocBean.setCodIdecm((String)mapDocs.get("codIdEcm"));
											
											codTipDocSust = (String)mapDocs.get("codTipDocSust");
											if(codTipDocSust != null && codTipDocSust.equals(ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN)) {
												repExpTrabExpVirtBean.setNumDocumentoOrigen((String)mapDocs.get("numDoc"));
												repExpTrabExpVirtBean.setFechaDocOrigen((Date)mapDocs.get("fecDoc"));
											}
											
											listaRepExpTrabDocBeans.add(repExpTrabDocBean);
										}
										
										repExpTrabExpVirtBean.setListaRepExpTrabDocBeans(listaRepExpTrabDocBeans);
										listaRepExpTrabExpVirtBeans.add(repExpTrabExpVirtBean);
									}
								}
								
								repExpTrabBean.setListaRepExpTrabExpVirtBeans(listaRepExpTrabExpVirtBeans);
							}	
						}						
					}
					
					listaRepExpTrabBeans.add(repExpTrabBean);
				}
			}
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ExpedienteTrabReporteServiceImpl.obtenerDatosGeneraRep");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ExpedienteTrabReporteServiceImpl.obtenerDatosGeneraRep");
			
			NDC.pop();
			NDC.remove();
		}
		
		return listaRepExpTrabBeans;
	}
	
	public List<String> listarNumPedidoBatch() throws Exception {
		log.debug("Inicio - ExpedienteTrabReporteServiceImpl.listarNumPedidoBatch");
		List<String> listaNumPedido = null;
		List<Map<String, Object>> listaExpTrabPedidos = null;
		Map<String, Object> mapParam;
		String numPedido = null;
		
		try {
			mapParam = new HashMap<String, Object>();
			mapParam.put("codTipGenera", ValidaConstantes.IND_TIPO_GEN_PEDIDO_MASIVO);
			mapParam.put("codEstado", ValidaConstantes.IND_ESTADO_PEDIDO_REP_PENDIENTE);
			
			listaExpTrabPedidos = t6616PedRepDAO.listarExpTrabPedido(mapParam);
			if(listaExpTrabPedidos != null) {
				listaNumPedido = new ArrayList<String>();
				
				for(Map<String, Object> map : listaExpTrabPedidos) {
					numPedido = (String)map.get("numPedido");
					numPedido = numPedido.trim();
					
					listaNumPedido.add(numPedido);
				}
			}
		} catch (Exception ex) {
			log.debug("Error - ExpedienteTrabReporteServiceImpl.listarNumPedidoBatch");
			log.error(ex, ex);
			throw ex;
		} finally {
			log.debug("Final - ExpedienteTrabReporteServiceImpl.listarNumPedidoBatch");
		}
		return listaNumPedido;
	}
	
	public byte[] generarPDF(Map<String, Object> parametros) throws Exception {
		String 	json = null;
		int firmaPdf = 0;
		int modeloPdf = 10000;
		Integer idDocumento = null;		
		Service servicioAxis = null;
		Call generaPDF = null;
		byte[] archivo = null; 
		
		try {
			if (log.isDebugEnabled()) log.debug("Inicio - ExpedienteTrabReporteServiceImpl.generarPDF");
			json = (String)parametros.get("json");
			idDocumento = (Integer)parametros.get("idDocumento");
			
			servicioAxis = new Service();
			generaPDF = (Call) servicioAxis.createCall();
			generaPDF.setTargetEndpointAddress(new URL(generaReporteURLService));
			
			generaPDF.setOperationName("generar");
			generaPDF.addParameter("iddoc", XMLType.XSD_INT, ParameterMode.IN);
			generaPDF.addParameter("datos", XMLType.XSD_STRING, ParameterMode.IN);
			generaPDF.addParameter("tipo", XMLType.XSD_STRING, ParameterMode.IN);
			generaPDF.addParameter("modelo", XMLType.XSD_INT, ParameterMode.IN);
			generaPDF.addParameter("firma", XMLType.XSD_INT, ParameterMode.IN);
			generaPDF.setReturnClass(byte[].class);
						
			if (log.isDebugEnabled()) log.debug("ExpedienteTrabReporteServiceImpl.generarPDF:json: " + json);
			
			archivo = (byte[]) (generaPDF.invoke(new Object[]{idDocumento, json, "pdf", modeloPdf, firmaPdf}));			
			generaPDF.setReturnClass(int.class);			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ExpedienteTrabReporteServiceImpl.generarPDF");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ExpedienteTrabReporteServiceImpl.generarPDF");
			
			NDC.pop();
			NDC.remove();
		}
		
		return archivo;
	}
	
	private String guardarReporteECM(Map<String, Object> parametros) throws Exception {
		Map<String, Object> mapParamGenPDF = null;
		byte[] bytes = null;
		Map<String, Object>	beanEcm = null;
		String codIdEcm = null;
		String prefijoNombreArchivoPDF = null;
		Integer correlativoPDF = null;
		FileOutputStream fos = null;
		
		try {
			if (log.isDebugEnabled()) log.debug("Inicio - ExpedienteTrabReporteServiceImpl.guardarReporteECM");
			
			mapParamGenPDF = new HashMap<String, Object>();
			mapParamGenPDF.put("json", parametros.get("json"));
			mapParamGenPDF.put("idDocumento", parametros.get("idDocumento"));
			
			bytes = this.generarPDF(mapParamGenPDF);			
			beanEcm = new HashMap<String, Object>();				
			beanEcm.put("nomArchivoSinExt", parametros.get("nombreArchivo"));
			beanEcm.put("numRuc", parametros.get("numRUC"));
			beanEcm.put("numExpeDv", "0");
			beanEcm.put("numDoc", "-");
			beanEcm.put("fecEmi", new Date());
			beanEcm.put("codTipExpVir", "000");
			beanEcm.put("codTipDocu", parametros.get("codTipDocu"));			
			beanEcm.put("archLength", bytes.length);
			beanEcm.put("archMimeType", ValidaConstantes.HTTP_ACCEPT_PDF);
			beanEcm.put("archFileName", (String)parametros.get("nombreArchivo") + "." + ValidaConstantes.TIPO_ARCHIVO_PDF);
			beanEcm.put("archContent", bytes);
			beanEcm.put("metadata", parametros.get("metadata"));
			
			codIdEcm = ecmService.guardarDocumentoECM(beanEcm);
			if(codIdEcm == null || codIdEcm.equals(ValidaConstantes.CADENA_VACIA)) {
				throw new ExcepcionECM(AvisoConstantes.MSG_ECM_NO_CODIDECM);
			} 
			
			prefijoNombreArchivoPDF = (String)parametros.get("prefijoNombreArchivoPDF");
			correlativoPDF = (Integer)parametros.get("correlativoPDF");
			
			prefijoNombreArchivoPDF = 	ValidaConstantes.CARPETA_TEMPORAL_TEMPO +
										prefijoNombreArchivoPDF + "_" + correlativoPDF + 
										"." + ValidaConstantes.TIPO_ARCHIVO_PDF;
			
			fos = new FileOutputStream(prefijoNombreArchivoPDF);
			fos.write(bytes);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ExpedienteTrabReporteServiceImpl.guardarReporteECM");
			log.error(ex, ex);
			throw ex;
		} finally {
			if(fos != null) {
				fos.close();
			}
			
			if (log.isDebugEnabled()) log.debug("Final - ExpedienteTrabReporteServiceImpl.guardarReporteECM");
			
			NDC.pop();
			NDC.remove();
		}
		
		return codIdEcm;
	}

	public void actualizarPedido(Map<String, Object> parametros) throws Exception {
		Map<String, Object> mapParam = null;
		Map<String, Object> mapDataSource = null;
		//DataSource origenDatos = null;//se comenta pues ya no se pasa el objeto Datasource a la consulta 
		Map<String, Object> mapaPool = null; //13 JUL: EL MAPA QUE CONTIENE EL NOMBRE DEL POOL Y EL OBJETO DATASOURC (ESTE ULTIMO EN DESUSO POR EL TEMA DE ROUTING)
		try {
			if (log.isDebugEnabled()) log.debug("Inicio - ExpedienteTrabReporteServiceImpl.actualizarPedido");
			
			mapDataSource = new HashMap<String, Object>();
			mapDataSource.put("indCentral", DataSourceConstantes.IND_BASEDATOS_CENTRAL);
			mapDataSource.put("oriDatos", DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			
			//origenDatos = generalService.obtenerOrigenDatos(mapDataSource);//se comenta pues ya no se pasa el objeto Datasource a la consulta 
			mapaPool = generalService.obtenerOrigenDatosMap(mapDataSource); //recuperar nombre del pool
			DataSourceContextHolder.setKeyDataSource(mapaPool.get("nombrePool").toString()); //setea el Pool via el key declarado en el routing-datasource-data.xml
			
			mapParam = new HashMap<String, Object>();
			
			//mapParam.put("origenDatos", origenDatos);//se comenta pues ya no se pasa el objeto Datasource a la consulta 
			mapParam.put("codEstado", parametros.get("codEstado"));
			mapParam.put("numPedido", parametros.get("numPedido"));
			mapParam.put("codUsuModif", parametros.get("codUsuModif"));
			mapParam.put("fecModif", parametros.get("fecModif"));
			
			t6616PedRepDAO.actualizar(mapParam);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ExpedienteTrabReporteServiceImpl.actualizarPedido");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ExpedienteTrabReporteServiceImpl.actualizarPedido");
			
			NDC.pop();
			NDC.remove();
		}
	}
		
	public byte[] obtenerPrevisualizacionPlantilla(String parametroJSON) throws Exception {
				
		int firmaPdf = 0;
		int modeloPdf = 543;
		Integer idDocumento = ExportaConstantes.PDF_PREVISUALIZACIONPLANTILLA_CD_FORMATO_543;
		/* Generar el Pdf */
		
		Service servicioAxis = new Service();
		Call generaPDF = (Call) servicioAxis.createCall();
		generaPDF.setTargetEndpointAddress(new URL(generaReporteURLService));
		
//		generaPDF.setOperationName("genera0"); //Retorna entero
		generaPDF.setOperationName("generar"); //Retorna bytes[]
		generaPDF.addParameter("iddoc", XMLType.XSD_INT, ParameterMode.IN);
		generaPDF.addParameter("datos", XMLType.XSD_STRING, ParameterMode.IN);
		generaPDF.addParameter("tipo", XMLType.XSD_STRING, ParameterMode.IN);
		generaPDF.addParameter("modelo", XMLType.XSD_INT, ParameterMode.IN);
		generaPDF.addParameter("firma", XMLType.XSD_INT, ParameterMode.IN);		
		
		generaPDF.setReturnClass(byte[].class);
//		generaPDF.setReturnClass(int.class);
		
		byte[] previsualizacionPlantilla =null; 
	try {
		previsualizacionPlantilla =(byte[]) (generaPDF.invoke(new Object[]{idDocumento,parametroJSON, "pdf", modeloPdf, firmaPdf}));
//		Integer numeroPdf = Utils.toInteger(generaPDF.invoke(new Object[] { idDocumento, parametroJSON, "pdf", modeloPdf, firmaPdf }));
	} catch (Exception e) {
		e.printStackTrace();
	}		
		return previsualizacionPlantilla;
	}
	
	/* Seteo de Spring - Inicio */
	
	//PAS20201U210500029 - HHC FIN
	@Override
	public List<T10461SolDesBean> listarNumSolicitudBatch() throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ExpedienteTrabReporteServiceImpl.listarNumSolicitudBatch");
		List<T10461SolDesBean> listaNumSolicitud = null;
		Map<String, Object> mapParam;
		Map<String, Object> parametros;
		try {
			
			mapParam = new HashMap<String, Object>();
			mapParam.put("codEstadoSol", ValidaConstantes.IND_ESTADO_SOL_DES_PENDIENTE);
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_EXPVIRTUAL);
			listaNumSolicitud = t10461SolDesDAO.listarPedidosSolicitud(mapParam);
			
			if(!Utils.isEmpty(listaNumSolicitud)){
				for (T10461SolDesBean t10461SolDesBean : listaNumSolicitud) {
					
					parametros = new HashMap<String, Object>();
					parametros.put("codPersona", t10461SolDesBean.getCodUsuarioReg());
					parametros.put("indHabilitado", ValidaConstantes.IND_REGI_SI_HABILITADO);
					
					T02PerdpBean responsable = personaService.validarPersona(parametros);
					if(!Utils.isEmpty(responsable)){
						responsable = personaService.completarInformacionPersona(responsable);					
						t10461SolDesBean.setNombrePersonaRegistro(responsable.getDesNombreCompleto());	
					}else{
						t10461SolDesBean.setNombrePersonaRegistro("");	
					}
				}
			}
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ExpedienteTrabReporteServiceImpl.listarNumSolicitudBatch");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ExpedienteTrabReporteServiceImpl.listarNumSolicitudBatch");
		}
		return listaNumSolicitud;
	}

	@Override
	public List<T10461SolDesBean> listarNumSolicitudEliminarBatch() throws Exception {
		if (log.isDebugEnabled()) log.debug("Inicio - ExpedienteTrabReporteServiceImpl.listarNumSolicitudEliminarBatch");
		List<T10461SolDesBean> listaNumSolicitud = null;
		Map<String, Object> mapParam;
		Map<String, Object> parametros;
		try {
			
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_YEAR, -5); // 5 dias para eliminar
			
			if (log.isDebugEnabled()) log.debug("calendar:"+calendar);
			Date fecha=calendar.getTime();
			if (log.isDebugEnabled()) log.debug("fecha:"+fecha);
			String fechaFinal =Utils.dateUtilToStringDDMMYYYY(fecha);
			if (log.isDebugEnabled()) log.debug("fechaFinal:"+fechaFinal);
			
			Date fechaParam=Utils.stringToDate(fechaFinal, 2);
			if (log.isDebugEnabled()) log.debug("fechaParam:"+fechaParam);
			List<String> listIndEstado = new ArrayList<String>();
			listIndEstado.add(ValidaConstantes.IND_ESTADO_SOL_DES_GENERADO);
			listIndEstado.add(ValidaConstantes.IND_ESTADO_SOL_DES_CONSULTADO);
			
			mapParam = new HashMap<String, Object>();
			mapParam.put("listIndEstado", listIndEstado);
			mapParam.put("fecModif", fechaParam);
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_EXPVIRTUAL);
			listaNumSolicitud = t10461SolDesDAO.listarPedidosSolicitud(mapParam);
			
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ExpedienteTrabReporteServiceImpl.listarNumSolicitudEliminarBatch");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ExpedienteTrabReporteServiceImpl.listarNumSolicitudEliminarBatch");
		}
		return listaNumSolicitud;
	}

	
	@Override
	public void generarSolicitudDesMasivo(List<T10461SolDesBean> listSolDesBean) throws Exception {
		log.debug("Inicio - ExpedienteTrabReporteServiceImpl.generarSolicitudDesMasivo");
		Map<String, Object> mapParam = null;
		List<T6613DocExpVirtBean> listDocExpVir = null;
		List<T10373DocAdjReqBean> listDocAdjReq = null;
		String codEstGenRespuesta = "";
		log.debug("Inicio - listSolDesBean:"+ listSolDesBean);
		for (T10461SolDesBean solDesBean : listSolDesBean) {
			try {
				log.debug("Inicio - numExpVir:"+ solDesBean.getNumExpdVirtual());
				mapParam = new HashMap<String, Object>();
				mapParam.put("numExpVir", solDesBean.getNumExpdVirtual());
				DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_EXPVIRTUAL);
				listDocExpVir = t6613DocExpVirtDAO.listDocPorNumExpVir(mapParam);
				if (!listDocExpVir.isEmpty()) {
					for (T6613DocExpVirtBean t6613Bean : listDocExpVir) {
						if (ValidaConstantes.IND_NOTIENE_CODIDECM.equals(t6613Bean.getCodIdentificadorECM().trim())) {
							mapParam = new HashMap<String, Object>();
							mapParam.put("numCorDoc", t6613Bean.getNumCorDoc());
							DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_EXPVIRTUAL);
							listDocAdjReq = t10373DocAdjReqDAO.obtenerListaDocAdj(mapParam);
							t6613Bean.setListDocAdj(listDocAdjReq);
						}
					}
					//VERIFICAR SI LA SOLICITUD ESTÁ SIENDO PROCESADA
					StringBuilder sbPathNumSol = new StringBuilder(ValidaConstantes.CARPETA_TEMPORAL_TEMPO);
					sbPathNumSol.append(solDesBean.getNumSolDescarga()).append("/");
					File directorio = new File(sbPathNumSol.toString());
					if (directorio.exists()) {
						log.info("La solicitud " + solDesBean.getNumSolDescarga() + " está siendo procesada por otro MDB.");
					} else {
						mapParam = new HashMap<String, Object>();
						mapParam.put("codEstadoSol", ValidaConstantes.IND_ESTADO_SOL_DES_PROCESANDO);
						mapParam.put("numSolDescarga", solDesBean.getNumSolDescarga());
						mapParam.put("codUsuarioModif", ValidaConstantes.COD_USUARIO_EXPTRAB_BATCH);
						mapParam.put("fechaModif", new Date());
						this.actualizarSolicitud(mapParam);
						log.info("La getNombrePersonaRegistro " + solDesBean.getNombrePersonaRegistro());
						codEstGenRespuesta = this.generarArchivo(solDesBean.getNumSolDescarga(), solDesBean.getNombrePersonaRegistro(),listDocExpVir,solDesBean.getNumExpedienteOrigen(),solDesBean.getFecGeneracionIni());
						log.info("La codEstGenRespuesta: " + codEstGenRespuesta);
						mapParam.put("codEstadoSol", codEstGenRespuesta);
						mapParam.put("numSolDescarga", solDesBean.getNumSolDescarga());
						mapParam.put("codUsuarioModif", ValidaConstantes.COD_USUARIO_EXPTRAB_BATCH);
						mapParam.put("fechaModif", new Date());
						this.actualizarSolicitud(mapParam);
					}
				} else {
					log.error("No se pudo obtener los documentos del expediente " + solDesBean.getNumExpdVirtual());
					mapParam = new HashMap<String, Object>();
					mapParam.put("codEstadoSol", ValidaConstantes.IND_ESTADO_SOL_DES_ERROR);
					mapParam.put("numSolDescarga", solDesBean.getNumSolDescarga());
					mapParam.put("codUsuarioModif", ValidaConstantes.COD_USUARIO_EXPTRAB_BATCH);
					mapParam.put("fechaModif", new Date());
					this.actualizarSolicitud(mapParam);
				}
			} catch (Exception ex) {
				log.debug("Error - ExpedienteTrabReporteServiceImpl.generarSolicitudDesMasivo");
				log.error(ex, ex);
				throw ex;
			}
		}
		log.debug("Final - ExpedienteTrabReporteServiceImpl.generarSolicitudDesMasivo");
	}

	@Override
	public void eliminarSolicitudDesMasivo(List<T10461SolDesBean> listSolDesBean) throws Exception {
		log.debug("Inicio - ExpedienteTrabReporteServiceImpl.eliminarSolicitudDesMasivo");
		Map<String, Object> mapParam = null;
		String codEstGenRespuesta = "";
		log.debug("Inicio - listSolDesBean:"+ listSolDesBean);
		for (T10461SolDesBean solDesBean : listSolDesBean) {
			try {
				log.debug("Inicio - numExpVir:"+ solDesBean.getNumExpdVirtual());	
					//VERIFICAR SI LA SOLICITUD ESTÁ SIENDO PROCESADA
					StringBuilder sbPathNumSol = new StringBuilder(ValidaConstantes.CARPETA_TEMPORAL_TEMPO);
					sbPathNumSol.append(solDesBean.getNumSolDescarga()).append("/");
					File directorio = new File(sbPathNumSol.toString());
					if (directorio.exists()) {
						log.info("La solicitud " + solDesBean.getNumSolDescarga() + " se eliminará.");
						
						this.eliminarDir(directorio);
						mapParam = new HashMap<String, Object>();
						mapParam.put("codEstadoSol", ValidaConstantes.IND_ESTADO_SOL_DES_ELIMINADO);
						mapParam.put("numSolDescarga", solDesBean.getNumSolDescarga());
						mapParam.put("codUsuarioModif", ValidaConstantes.COD_USUARIO_EXPTRAB_BATCH);
						mapParam.put("fechaModif", new Date());
						this.actualizarSolicitud(mapParam);
					} else {
						
					}
			
			} catch (Exception ex) {
				log.debug("Error - ExpedienteTrabReporteServiceImpl.eliminarSolicitudDesMasivo");
				log.error(ex, ex);
				throw ex;
			}
		}
		log.debug("Final - ExpedienteTrabReporteServiceImpl.eliminarSolicitudDesMasivo");
	}
	@Override
	public void actualizarSolicitud(Map<String, Object> parametros) throws Exception {
		try {
			if (log.isDebugEnabled()) log.debug("Inicio - ExpedienteTrabReporteServiceImpl.actualizarSolicitud");
			DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			t10461SolDesDAO.actualizar(parametros);
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - ExpedienteTrabReporteServiceImpl.actualizarSolicitud");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - ExpedienteTrabReporteServiceImpl.actualizarSolicitud");
		}
	}
		
	@Override
	public String generarArchivo(String numSolDescarga, String nombrePersonaRegistro,List<T6613DocExpVirtBean> listDocExpVir, String numExpOrigen, Date fechaGenini) throws Exception {
		log.debug("Inicio - ExpedienteTrabReporteServiceImpl.generarArchivo");
		Integer correlativoPDF = 0;
		StringBuilder sbPathNumSol = null;
		StringBuilder sbPathFile = null;
		String extension = null;
		ResponseEntity<byte[]> responseEntity = null;
		FileOutputStream fos = null;
		int numDocError = 0;
		Map<String, Object> mapParam = null;
		String pdfs[] = null;
//		String codEstGeneracionPedido = ValidaConstantes.IND_ESTADO_SOL_DES_GENERADO;
		String codEstGeneracionPedido = ValidaConstantes.IND_ESTADO_SOL_DES_ERROR;

		try {
			//CREAR CARPETA DE LA SOLICITUD DE DESCARGA
			sbPathNumSol = new StringBuilder(ValidaConstantes.CARPETA_TEMPORAL_TEMPO);
			sbPathNumSol.append(numSolDescarga).append("/");
			File directorio = new File(sbPathNumSol.toString());
			directorio.mkdir();
			
			//DESCARGAR DOC DEL EXPEDIENTE
			for (T6613DocExpVirtBean docExpVir: listDocExpVir) {
				try {
					if (numDocError > 0) {
						break;
					}
					if (log.isDebugEnabled())log.debug("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					if (log.isDebugEnabled())log.debug("docExpVir.getCodIdentificadorECM(): " + docExpVir.getCodIdentificadorECM().trim());
					if (log.isDebugEnabled())log.debug("docExpVir.getDescripcionArchivo(): " + docExpVir.getDescripcionArchivo().trim());
					if (ValidaConstantes.IND_NOTIENE_CODIDECM.equals(docExpVir.getCodIdentificadorECM().trim())) {
						for (T10373DocAdjReqBean t10373Bean : docExpVir.getListDocAdj()) {
							try {
								if (log.isDebugEnabled())log.debug("----------------------------------------------------------------");
								if (log.isDebugEnabled())log.debug("t10373Bean.getCodIdECM(): " + t10373Bean.getCodIdECM().trim());
								if (log.isDebugEnabled())log.debug("t10373Bean.getNomArchAdj(): " + t10373Bean.getNomArchAdj().trim());
								correlativoPDF++;
								if (log.isDebugEnabled())log.debug("correlativoPDF: " + correlativoPDF);
								sbPathFile = new StringBuilder(sbPathNumSol);
								sbPathFile.append(correlativoPDF).append(".pdf");
								//OBTENER LA EXTENSIÓN DEL ARCHIVO
								extension = Utils.obtenerExtension(t10373Bean.getNomArchAdj());
								//VERIFICAR SI EL ARCHIVO ES UN PDF PARA DESCARGARLO O CASO CONTRARIO GENERAR UN PDF
								if (extension.equalsIgnoreCase(ValidaConstantes.TIPO_ARCHIVO_PDF)) {
									//DESCARGAR PDF
									mapParam = new HashMap<String, Object>();
									mapParam.put("inline", "false");
									mapParam.put("codIdecm", t10373Bean.getCodIdECM().trim());
									responseEntity = ecmService.descargarDocumentoECM(mapParam);
									//COPIAR EN LA RUTA TEMPORAL
									fos = new FileOutputStream(sbPathFile.toString());
									fos.write(responseEntity.getBody());
									fos.close();
								} else {//GENERAR PDF
									this.crearPagPDF(sbPathFile.toString(), t10373Bean.getNomArchAdj());
								}
							} catch (Exception ex) {
								log.error("Error - generarSolicitudDesMasivo.t10373Bean.getNumArchAdj(): " + t10373Bean.getNumArchAdj());
								log.error(ex, ex);
								numDocError++;
								break;
							}
						}
					} else {
						correlativoPDF++;
						if (log.isDebugEnabled())log.debug("correlativoPDF: " + correlativoPDF);
						sbPathFile = new StringBuilder(sbPathNumSol);
						sbPathFile.append(correlativoPDF).append(".pdf");
						//OBTENER LA EXTENSIÓN DEL ARCHIVO
						extension = Utils.obtenerExtension(docExpVir.getDescripcionArchivo());
						//VERIFICAR SI EL ARCHIVO ES UN PDF PARA DESCARGARLO O CASO CONTRARIO GENERAR UN PDF
						if (extension.equalsIgnoreCase(ValidaConstantes.TIPO_ARCHIVO_PDF)) {
							//DESCARGAR PDF
							mapParam = new HashMap<String, Object>();
							mapParam.put("inline", "false");
							mapParam.put("codIdecm", docExpVir.getCodIdentificadorECM().trim());
							responseEntity = ecmService.descargarDocumentoECM(mapParam);
							//COPIAR EN LA RUTA TEMPORAL
							fos = new FileOutputStream(sbPathFile.toString());
							fos.write(responseEntity.getBody());
							fos.close();
						} else {//GENERAR PDF
							this.crearPagPDF(sbPathFile.toString(), docExpVir.getDescripcionArchivo());
						}
					}
				} catch (Exception ex) {
					log.error("Error - generarSolicitudDesMasivo.docExpVir.getNumCorDoc(): " + docExpVir.getNumCorDoc());
					log.error(ex, ex);
					numDocError++;
					break;
				}
			}
			if (log.isDebugEnabled())log.debug("numDocError: " + numDocError);
			if (numDocError == 0) {
				//CONCATENAR PDF
				pdfs = new String[correlativoPDF];
				for (int i = 0; i < correlativoPDF; i++) {
					pdfs[i] = (i + 1) + ".pdf";
				}
				if (log.isDebugEnabled())log.debug("pdfs: " + pdfs);
				boolean mergeValido= Utils.mergePDFVal(sbPathNumSol.toString(), pdfs, numSolDescarga + ".pdf");
				//AGREGAR FOLIACIÓN}
				if(mergeValido){
					this.agregarFoliacion(sbPathNumSol.toString(), numSolDescarga,nombrePersonaRegistro, numExpOrigen,fechaGenini);
					codEstGeneracionPedido = ValidaConstantes.IND_ESTADO_SOL_DES_GENERADO;
					this.eliminarPdfsSoli(sbPathNumSol.toString(), pdfs, numSolDescarga + ".pdf");
				}else{
					if (log.isDebugEnabled())log.debug("ELIMINAR CARPETA");
					codEstGeneracionPedido = ValidaConstantes.IND_ESTADO_SOL_DES_ERROR;
					//ELIMINAR CARPETA
					this.eliminarDir(directorio);
				}
			} else {
				codEstGeneracionPedido = ValidaConstantes.IND_ESTADO_SOL_DES_ERROR;
				//ELIMINAR CARPETA
				this.eliminarDir(directorio);
			}
		} catch (Exception ex) {
			log.error("Error - ExpedienteTrabReporteServiceImpl.generarArchivo");
			log.error(ex, ex);
		} finally {
			log.debug("Fin - ExpedienteTrabReporteServiceImpl.generarArchivo");
		}
		return codEstGeneracionPedido;
	}
	
	private void crearPagPDF(String pathFile, String contenido) throws IOException, DocumentException {
		log.debug("Inicio - ExpedienteTrabReporteServiceImpl.crearPagPDF");		
		Document docPDF = new Document();
		PdfWriter.getInstance(docPDF, new FileOutputStream(pathFile));
		docPDF.open();
		Chunk chunk = new Chunk(contenido, FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD));
		Chapter chapter = new Chapter(new Paragraph(chunk), 1);
		chapter.setNumberDepth(0);
		docPDF.add(chapter);		
		docPDF.close();
		log.debug("Fin - ExpedienteTrabReporteServiceImpl.crearPagPDF");
	}
	
	private void agregarFoliacion(String carpetaBase, String numSolDescarga, String nombrePersonaRegistro , String numExpOrigen, Date fechaGenini) throws IOException, DocumentException {
		log.debug("Inicio - ExpedienteTrabReporteServiceImpl.agregarFoliacion");
    	File filePdf = new File(carpetaBase + numSolDescarga + ".pdf");//ORIGINAL
		FileInputStream ficheroStream = new FileInputStream(filePdf);
		byte contenido[] = new byte[(int)filePdf.length()];
		ficheroStream.read(contenido);	
		PdfReader reader = new PdfReader(contenido);
		int totalPags = reader.getNumberOfPages();
//		String rutaArchivo=carpetaBase + numSolDescarga + "F.pdf";
		String fechaGeneracionIni=Utils.dateUtilToStringOnlyDDMMYYYY(fechaGenini);
		String rutaArchivo=carpetaBase +numExpOrigen.trim() + "_SIEV_descarga_"+fechaGeneracionIni+".pdf";
		PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(rutaArchivo));//PAGINADO
		Map<String, Object> mapParam = null;
	     
		int pagina = 0;
		int folio = 0;
		PdfContentByte over;
		
		
		Font FONT = new Font(Font.HELVETICA, 50, Font.BOLD, new GrayColor(0.5f));
        Phrase marcaAgua = new Phrase("CONFIDENCIAL", FONT);

        Font fontNombre = new Font(Font.UNDEFINED, 14, Font.ITALIC, Color.black);
        Phrase header = new Phrase(nombrePersonaRegistro, fontNombre);
        
        Font fontFolio = new Font(Font.UNDEFINED, 25, Font.ITALIC, Color.black);
        
        Rectangle pagesize;
        float x, y;
        
		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.WINANSI, BaseFont.EMBEDDED);
		while (pagina < totalPags) {
			folio = totalPags - pagina;
			
			Phrase folioPrint = new Phrase(Utils.toStr(folio), fontFolio);
			
			pagina++;
			Rectangle pageW = reader.getPageSize(pagina);
			over = stamp.getOverContent(pagina);
			
			 // obtenemos las posiciones de las paginas
            pagesize = reader.getPageSizeWithRotation(pagina);
            x = (pagesize.getLeft() + pagesize.getRight()) / 2;
            y = (pagesize.getTop() + pagesize.getBottom()) / 2;
            over = stamp.getOverContent(pagina);
            over.saveState();

            // transparencia
            PdfGState state = new PdfGState();
            state.setFillOpacity(0.2f);
            over.setGState(state);

            // agregar marca de agua
            ColumnText.showTextAligned(over, Element.ALIGN_CENTER, marcaAgua, x, y, 45f);

            // header persona responsable
            ColumnText.showTextAligned(over, Element.ALIGN_CENTER,header,pagesize.getLeft() +150,
                    pagesize.getTop() -30, 0);		
			
            //agregar el folio
            ColumnText.showTextAligned(over, Element.ALIGN_CENTER,folioPrint,pagesize.getRight() - 50,
                    pagesize.getTop()-30, 0);	
            /*over.beginText();
			over.setFontAndSize(bf, 15);
			over.setTextMatrix(pageW.getWidth() - 50, pageW.getHeight() - 30);
			over.showText("" + folio);
			over.setFontAndSize(bf, 32);
			over.endText();*/
		}
		
		
		
		stamp.close();
		reader.close();
		ficheroStream.close();
		
		
		PdfReader readerFileEnd = new PdfReader(rutaArchivo);
		
		mapParam = new HashMap<String, Object>();
		mapParam.put("numSolDescarga", numSolDescarga);
		mapParam.put("desRuta", rutaArchivo);
		mapParam.put("cantFolio", totalPags);
		log.debug("tamanio"+readerFileEnd.getFileLength());
		mapParam.put("numTamanioArch",readerFileEnd.getFileLength());
		try {
			this.actualizarSolicitud(mapParam);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		readerFileEnd.close();
		log.debug("Fin - ExpedienteTrabReporteServiceImpl.agregarFoliacion");
	}
	
	private void eliminarDir(File f) throws FileNotFoundException {
	  if (f.isDirectory()) {
		  for (File c : f.listFiles())
			  eliminarDir(c);
	  }
	  if (!f.delete())
		  throw new FileNotFoundException("Failed to delete file: " + f);
	}

	private void eliminarPdfsSoli(String carpetaBase, String[] pdfs, String pdfSinFolio) throws FileNotFoundException {
		File pdf = new File(carpetaBase + pdfSinFolio);
		log.debug("DELETE: " + carpetaBase + pdfSinFolio);
		pdf.delete();
		for(int i = 0; i < pdfs.length; i++) {
			pdf = new File(carpetaBase + pdfs[i]);
			log.debug("DELETE: " + carpetaBase + pdfs[i]);
			pdf.delete();
		}
	}
		
	public void setT6616PedRepDAO(T6616PedRepDAO t6616PedRepDAO) {
		this.t6616PedRepDAO = t6616PedRepDAO;
	}

	public void setT6611CabPlantiDAO(T6611CabPlantiDAO t6611CabPlantiDAO) {
		this.t6611CabPlantiDAO = t6611CabPlantiDAO;
	}

	public void setT6617RepGenDocDAO(T6617RepGenDocDAO t6617RepGenDocDAO) {
		this.t6617RepGenDocDAO = t6617RepGenDocDAO;
	}

	public void setT6618RepGenExpDAO(T6618RepGenExpDAO t6618RepGenExpDAO) {
		this.t6618RepGenExpDAO = t6618RepGenExpDAO;
	}
	
	public void setT6619RepGenPedDAO(T6619RepGenPedDAO t6619RepGenPedDAO) {
		this.t6619RepGenPedDAO = t6619RepGenPedDAO;
	}
	
	public void setT6613DocExpVirtDAO(T6613DocExpVirtDAO t6613DocExpVirtDAO) {
		this.t6613DocExpVirtDAO = t6613DocExpVirtDAO;
	}
	
	public void setT6614ExpVirtualDAO(T6614ExpVirtualDAO t6614ExpVirtualDAO) {
		this.t6614ExpVirtualDAO = t6614ExpVirtualDAO;
	}
	
	public void setT6612DetPlantiDAO(T6612DetPlantiDAO t6612DetPlantiDAO) {
		this.t6612DetPlantiDAO = t6612DetPlantiDAO;
	}

	public void setSequenceDAO(SequenceDAO sequenceDAO) {
		this.sequenceDAO = sequenceDAO;
	}
	
	public void setEcmService(EcmService ecmService) {
		this.ecmService = ecmService;
	}

	public void setGeneralService(GeneralService generalService) {
		this.generalService = generalService;
	}

	public void setGeneraReporteURLService(String generaReporteURLService) {
		this.generaReporteURLService = generaReporteURLService;
	}

	public void setConfiguracionExpedienteService(
			ConfiguracionExpedienteService configuracionExpedienteService) {
		this.configuracionExpedienteService = configuracionExpedienteService;
	}

	public void setValidarParametrosService(
			ValidarParametrosService validarParametrosService) {
		this.validarParametrosService = validarParametrosService;
	}

	public void setWSContribuyenteService(
			WSContribuyenteService wSContribuyenteService) {
		WSContribuyenteService = wSContribuyenteService;
	}

	public void setT10461SolDesDAO(T10461SolDesDAO t10461SolDesDAO) {
		this.t10461SolDesDAO = t10461SolDesDAO;
	}

	public void setT10373DocAdjReqDAO(T10373DocAdjReqDAO t10373DocAdjReqDAO) {
		this.t10373DocAdjReqDAO = t10373DocAdjReqDAO;
	}

	public void setPersonaService(PersonaService personaService) {
		this.personaService = personaService;
	}
    
	/* Seteo de Spring - Fin */
	
}