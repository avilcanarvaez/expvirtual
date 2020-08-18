package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;

import pe.gob.sunat.framework.spring.util.dao.SequenceDAO;
import pe.gob.sunat.framework.spring.util.jdbc.datasource.lookup.DataSourceContextHolder;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T7731DocAdiExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T7731DocAdiExpVirtDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.DataSourceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExcepcionECM;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExportaConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.SequenceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.Utils;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;

//Inicio [jquispe 30/05/2016] Clase para generar el archivo pdf con indice de expediente electronico.
public class IndiceExpedienteVirtualServiceImpl implements IndiceExpedienteVirtualService{

	private static final Log log = LogFactory.getLog(IndiceExpedienteVirtualServiceImpl.class);	
	
	private EcmService ecmService;	
	private String generaReporteURLService;
	private GeneralService generalService;
	
	private SequenceDAO sequenceDAO;
	private T7731DocAdiExpVirtDAO t7731DocAdiExpVirtDAO;

	@SuppressWarnings("unchecked")	
	public byte[] generarIndiceArchivoPdf(Map<String, Object> mapParametros) throws Exception{
		
		if (log.isDebugEnabled()) log.debug("Inicio -  IndiceExpedienteVirtualServiceImpl.generarIndiceArchivoPdf");
		byte[] binDoc = null;
		Long numCorrelativo;
		JsonSerializer serializer = null;
		List<Map<String, Object>> listaDocumentosEnvio;
		List<Map<String, Object>> listadoDocumentosExpediente;
		String razonSocial;
		String proceso;
		String desTipoExpediente;
		String numRucUsuario;
		String codigoUsuario;

		Map<String, Object> beanEcm;
		String codIdecm;
		T7731DocAdiExpVirtBean t7731DocAdiExpVirtBean;
		Map<String, Object> parametros;
		Integer numCorrDocAdi;
		String fecRegistroExpediente="";
		
		try {			
			serializer = new JsonSerializer();
			Integer idDocumento = ExportaConstantes.PDF_COD_PLANTILLA_01_001;
			
			// Fecha actual
			Date fechaActual = new Date();
			
			listadoDocumentosExpediente = (List<Map<String, Object>>) mapParametros.get("listadoDocumentosExpediente");	
			razonSocial = Utils.toStr(mapParametros.get("razonSocial"));
			proceso = Utils.toStr(mapParametros.get("proceso"));
			desTipoExpediente = Utils.toStr(mapParametros.get("desTipoExpediente"));
//			fecRegistroExpediente = mapParametros.get("fecRegistroExpediente").toString();			
			
			listaDocumentosEnvio = new ArrayList<Map<String, Object>> ();
			
			int numOrden = 0;
			for (Map<String, Object> mapDocumentosExpediente : listadoDocumentosExpediente) {
				
				Map<String, Object> documentoEnvio = new HashMap<String, Object>();
				
				Date fecDoc = (Date) mapDocumentosExpediente.get("fecDocu");
				SimpleDateFormat formatoFechaExporta = new SimpleDateFormat(ValidaConstantes.FORMATO_FECHA_VISTA);
				
				String fecRegistro = ValidaConstantes.CADENA_VACIA;
				String fecVista = ValidaConstantes.CADENA_VACIA;
				try {
					fecVista = formatoFechaExporta.format(fecDoc);
					fecRegistro = fecVista;
				} catch (Exception ex) {
					fecRegistro = ValidaConstantes.CADENA_VACIA;
					fecVista = ValidaConstantes.CADENA_VACIA;
				}
				
				String codTipoDoc = mapDocumentosExpediente.get("codTipDoc").toString().trim();
				String desTipoDoc = mapDocumentosExpediente.get("desTipdoc2").toString();
				String desTipoDocumentoFinal = codTipoDoc + ValidaConstantes.SEPARADOR_GUION + desTipoDoc;
				
				documentoEnvio.put("numDocumento", mapDocumentosExpediente.get("numDoc"));
				documentoEnvio.put("desTipoDocumento", desTipoDocumentoFinal);
				documentoEnvio.put("desDocumento", mapDocumentosExpediente.get("descArchivo"));
				documentoEnvio.put("fecDocumento", fecVista);
				documentoEnvio.put("fecEmision", fecRegistro);
				documentoEnvio.put("desOrigenDocumento", mapDocumentosExpediente.get("desOrigen"));
				documentoEnvio.put("numRequerimiento", mapDocumentosExpediente.get("nroRequerim"));
				documentoEnvio.put("numOrden", " " + (++numOrden));
				
				/*String cod_tipdocsust=Utils.toStr(mapDocumentosExpediente.get("cod_tipdocsust"));
				if(cod_tipdocsust.equals(ValidaConstantes.IND_TIP_DOC_SUST_ORIGEN)){
					fecRegistroExpediente=fecVista;
				}*/
				
				listaDocumentosEnvio.add(documentoEnvio);
			}
			
			//ARMAR JSON PARA GENERAR EL PDF
			StringBuilder sbJson = new StringBuilder();
			sbJson.append("{\"cabecera\":{");
			sbJson.append("\"numExpedienteVirtual\":\"").append(Utils.toStr(mapParametros.get("numExpedienteOrigen")).trim()).append("\",");
			sbJson.append("\"desProceso\":\"").append(proceso).append("\",");
			sbJson.append("\"desTipoExpediente\":\"").append(desTipoExpediente).append("\",");
			sbJson.append("\"numRuc\":\"").append(mapParametros.get("numRuc").toString()).append("\",");
			sbJson.append("\"fecEmisionExp\":\"").append(Utils.toStr(mapParametros.get("fecEmi"))).append("\",");
			sbJson.append("\"desRazonSocial\":\"").append(razonSocial.replace("\"", "''")).append("\"},");
			sbJson.append("\"detalle\":").append((String) serializer.serialize(listaDocumentosEnvio)).append("}");
			
			Service servicioAxis = new Service();
			Call generaPDF = (Call) servicioAxis.createCall();
			
			generaPDF.setTargetEndpointAddress(new URL(generaReporteURLService));
			
			generaPDF.setOperationName("generar");
			generaPDF.addParameter("iddoc", XMLType.XSD_INT, ParameterMode.IN);
			generaPDF.addParameter("datos", XMLType.XSD_STRING, ParameterMode.IN);
			generaPDF.addParameter("tipo", XMLType.XSD_STRING, ParameterMode.IN);
			generaPDF.addParameter("modelo", XMLType.XSD_INT, ParameterMode.IN);
			generaPDF.addParameter("firma", XMLType.XSD_INT, ParameterMode.IN);
			generaPDF.setReturnClass(byte[].class);
			
			binDoc = (byte[]) (generaPDF.invoke(new Object[]{idDocumento, sbJson.toString(), "pdf", 10000, 0}));
			
			numCorrelativo = sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_DOCUM);
			String desArchivoPdf = numCorrelativo.toString() + ".pdf";			
			
			beanEcm = new HashMap<String, Object>();
			
			beanEcm.put("nomArchivoSinExt", numCorrelativo);
			beanEcm.put("numExpeDv", Utils.toStr(mapParametros.get("numExpedienteVirtual")));
			beanEcm.put("numRuc", mapParametros.get("numRuc"));
			beanEcm.put("numDoc", numCorrelativo);
			beanEcm.put("fecEmi", fechaActual);
			beanEcm.put("codTipExpVir", mapParametros.get("codTipoExpediente"));
			beanEcm.put("codTipDocu", "-");
			beanEcm.put("archLength", binDoc.length);
			beanEcm.put("archMimeType", "application/pdf");
			beanEcm.put("archFileName", desArchivoPdf);
			beanEcm.put("archContent", binDoc);
			beanEcm.put("codDep", mapParametros.get("codDep"));
			
			codIdecm = ecmService.guardarDocumentoECM(beanEcm);
			
			if(codIdecm == null || codIdecm.equals(ValidaConstantes.CADENA_VACIA)) {				
				throw new ExcepcionECM(AvisoConstantes.MSG_ECM_NO_CODIDECM);
			}
			
			String paramNumRucUsuario = Utils.toStr(mapParametros.get("numRucUsuario"));
			numRucUsuario = Utils.isEmpty(paramNumRucUsuario)?ValidaConstantes.NUM_RUC_VACIO:paramNumRucUsuario.toString();
			
			String paramCodigoUsuario =  Utils.toStr(mapParametros.get("codigoUsuario"));
			codigoUsuario = Utils.isEmpty(paramCodigoUsuario)?ValidaConstantes.COD_USUARIO_VACIO:paramCodigoUsuario.toString();
				
			t7731DocAdiExpVirtBean = new T7731DocAdiExpVirtBean();
			numCorrDocAdi = Integer.parseInt(Utils.toStr(sequenceDAO.getNextSequence(SequenceConstantes.SEQ_EV_DOCADI)));
			
			t7731DocAdiExpVirtBean.setNumCorrDocAdi(numCorrDocAdi);
			t7731DocAdiExpVirtBean.setNumExpedienteVir(Utils.toStr(mapParametros.get("numExpedienteVirtual")));
			t7731DocAdiExpVirtBean.setNumRUC(numRucUsuario);
			t7731DocAdiExpVirtBean.setCodUsuarioInterno(codigoUsuario);
			t7731DocAdiExpVirtBean.setCodIdentificadorECM(codIdecm);
			t7731DocAdiExpVirtBean.setCodUsuarioRegistro(codigoUsuario);
			t7731DocAdiExpVirtBean.setFechaRegistro(new Date());
			
            Map<String, Object> mapa = new HashMap<String,Object>();			
			mapa.put("indCentral", DataSourceConstantes.IND_BASEDATOS_CENTRAL);
			mapa.put("oriDatos", DataSourceConstantes.DATASOURCE_TRANSACCION_EXPVIRTUAL);
			//DataSource origenDatos = generalService.obtenerOrigenDatos(mapa);
			Map<String, Object> mapaPool = null; //13 JUL: EL MAPA QUE CONTIENE EL NOMBRE DEL POOL Y EL OBJETO DATASOURCE (ESTE ULTIMO EN DESUSO POR EL TEMA DE ROUTING)

			mapaPool = generalService.obtenerOrigenDatosMap(mapa);
			DataSourceContextHolder.setKeyDataSource(mapaPool.get("nombrePool").toString());

			
			parametros = new HashMap<String, Object>();
			//parametros.put("origenDatos", origenDatos);
			parametros.put("t7731DocAdiExpVirtBean", t7731DocAdiExpVirtBean);
			
			t7731DocAdiExpVirtDAO.insertar(parametros);		
			
		} catch (Exception ex) {			
			if (log.isDebugEnabled()) log.debug("Error - IndiceExpedienteVirtualServiceImpl.generarIndiceArchivoPdf");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - IndiceExpedienteVirtualServiceImpl.generarIndiceArchivoPdf");
			
			NDC.pop();
			NDC.remove();
		}
		
		return binDoc;
	}
	
	/* Seteo de Spring - Inicio */

	public void setEcmService(EcmService ecmService) {
		this.ecmService = ecmService;
	}	

	public void setGeneraReporteURLService(String generaReporteURLService) {
		this.generaReporteURLService = generaReporteURLService;
	}
	
	public void setGeneralService(GeneralService generalService) {
		this.generalService = generalService;
	}
	
	public void setSequenceDAO(SequenceDAO sequenceDAO) {
		this.sequenceDAO = sequenceDAO;
	}

	public void setT7731DocAdiExpVirtDAO(T7731DocAdiExpVirtDAO t7731DocAdiExpVirtDAO) {
		this.t7731DocAdiExpVirtDAO = t7731DocAdiExpVirtDAO;
	}   
	/* Seteo de Spring - Final */
}
//Fin [jquispe 30/05/2016]