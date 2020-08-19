package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import net.sf.sojo.interchange.json.JsonSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import pe.gob.sunat.framework.spring.util.jdbc.datasource.lookup.DataSourceContextHolder;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T01ParamDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.CatalogoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.DataSourceConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ExcepcionECM;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.ValidaConstantes;

public class EcmServiceImpl implements EcmService {
	private static final Log log = LogFactory.getLog(EcmServiceImpl.class);
	
	private T01ParamDAO t01ParamDAO;
	private GeneralService generalService;
	
	@SuppressWarnings("unchecked")
	@Override
	public String guardarDocumentoECM(Map<String, Object> parametros) throws Exception {
		String codIdecm = null;
		Map<String, Object> mapDataSourceParam = null;
		//DataSource origenDatosParam = null;
		Map<String, Object> mapT01Param = null;
		T01ParamBean t01ParamBean = null;
		String url = null;
		String idClase = null;
		String folderId = null;
		HttpHeaders headers = null;
		RestTemplate restTemplate = null;
		HttpEntity<String> entity = null;
		JsonSerializer serializer = null;
		Map<String, Object> mapParamWS = null;
		Map<String, Object> mapParamTempWS = null;
		List<Map<String, Object>> mapPropertiesWS = null;
		Map<String, Object> mapPropertyWS = null;
		Map<String, Object> mapContentStreamWS = null;
		Map<String, Object> mapContentStreamTempWS = null;
		String jsonRequest = null;
		String jsonResponse = null;
		Map<String, Object> mapResponse = null;
		String stream = null;
		String metadata = null;
		Map<String, Object> mapaPool = null; 
		
		if (log.isDebugEnabled()) log.debug("Inicio - EcmServiceImpl.guardarDocumentoECM");
		try {
			 mapDataSourceParam = new HashMap<String, Object>();
			 
			 mapDataSourceParam.put("indCentral", DataSourceConstantes.IND_BASEDATOS_CENTRAL);
			 mapDataSourceParam.put("oriDatos", DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);

			//origenDatosParam = generalService.obtenerOrigenDatos(mapDataSourceParam);
//			 mapaPool = generalService.obtenerOrigenDatosMap(mapDataSourceParam);
//			 DataSourceContextHolder.setKeyDataSource(mapaPool.get("nombrePool").toString());
			 DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
			 
			 mapT01Param = new HashMap<String, Object>();
			// mapT01Param.put("origenDatos", origenDatosParam);
			 mapT01Param.put("codClase", CatalogoConstantes.CATA_WS_ECM);
			 mapT01Param.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			 mapT01Param.put("codParametro", ValidaConstantes.IND_WS_ECM_GENERA);
			 
			 t01ParamBean = t01ParamDAO.obtener(mapT01Param);
			 url = t01ParamBean.getDesParametro();
			 url = url.substring(CatalogoConstantes.LIMITE_INFERIOR_WS_ECM, CatalogoConstantes.LIMITE_SUPERIOR_WS_ECM);
			 url = url.trim();		
//			 url = "http://192.168.56.13:30000/v1/tecnologia/arquitectura/ecm01/t/ECM/createDocument";
			 
			 mapT01Param.put("codClase", CatalogoConstantes.CATA_CLASE_DOCUM);
			 mapT01Param.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
			 mapT01Param.put("codParametro", ValidaConstantes.IND_WS_ECM_ID_CLASE);
			 
			 t01ParamBean = t01ParamDAO.obtener(mapT01Param);
			 idClase = t01ParamBean.getDesParametro().trim();
			 
			 mapT01Param.put("codParametro", ValidaConstantes.IND_WS_ECM_ID_FOLDER);
			 
			 t01ParamBean = t01ParamDAO.obtener(mapT01Param);
			 folderId = t01ParamBean.getDesParametro().trim();
			 
			 mapParamWS = new HashMap<String, Object>();
			 mapParamWS.put("repositoryId", ValidaConstantes.IND_WS_ECM_REPOSITORY_ID);
			 
			 mapPropertiesWS = new ArrayList<Map<String, Object>>();
			 log.debug("parametrosEcm:"+parametros);
			 mapPropertyWS = new HashMap<String, Object>();
			 mapPropertyWS.put("propertyDefinitionId", "cmis:objectTypeId");
			 mapPropertyWS.put("value", idClase);
			 mapPropertyWS.put("type", "propertyId");
			 mapPropertiesWS.add(mapPropertyWS);
			 
			 mapPropertyWS = new HashMap<String, Object>();
			 mapPropertyWS.put("propertyDefinitionId", "cmis:name");
			 mapPropertyWS.put("value", parametros.get("nomArchivoSinExt"));
			 mapPropertyWS.put("type", "propertyString");
			 mapPropertiesWS.add(mapPropertyWS);
			 
			 mapPropertyWS = new HashMap<String, Object>();
			 mapPropertyWS.put("propertyDefinitionId", "num_exp");
			 mapPropertyWS.put("value", parametros.get("numExpeDv"));
			 mapPropertyWS.put("type", "propertyString");
			 mapPropertiesWS.add(mapPropertyWS);
			 
			 mapPropertyWS = new HashMap<String, Object>();
			 mapPropertyWS.put("propertyDefinitionId", "num_ruc");
			 mapPropertyWS.put("value", parametros.get("numRuc"));
			 mapPropertyWS.put("type", "propertyString");
			 mapPropertiesWS.add(mapPropertyWS);
			 
			 mapPropertyWS = new HashMap<String, Object>();
			 mapPropertyWS.put("propertyDefinitionId", "num_doc");
			 mapPropertyWS.put("value", parametros.get("numDoc"));
			 mapPropertyWS.put("type", "propertyString");
			 mapPropertiesWS.add(mapPropertyWS);
			 
			 mapPropertyWS = new HashMap<String, Object>();
			 mapPropertyWS.put("propertyDefinitionId", "fec_emi");
			 log.debug("FechaEmision:"+parametros.get("fecEmi"));
			 mapPropertyWS.put("value", parametros.get("fecEmi"));
			 mapPropertyWS.put("type", "propertyDateTime");
			 mapPropertiesWS.add(mapPropertyWS);
			 
			 mapPropertyWS = new HashMap<String, Object>();
			 mapPropertyWS.put("propertyDefinitionId", "cod_tip_exp_vir");
			 mapPropertyWS.put("value", parametros.get("codTipExpVir"));
			 mapPropertyWS.put("type", "propertyString");
			 mapPropertiesWS.add(mapPropertyWS);
			 
			 mapPropertyWS = new HashMap<String, Object>();
			 mapPropertyWS.put("propertyDefinitionId", "cod_tip_docu");
			 mapPropertyWS.put("value", parametros.get("codTipDocu"));
			 mapPropertyWS.put("type", "propertyString");
			 mapPropertiesWS.add(mapPropertyWS);
			 
			 if(parametros.get("codDep") != null) {
				 mapPropertyWS = new HashMap<String, Object>();
				 mapPropertyWS.put("propertyDefinitionId", "cod_dep");
				 mapPropertyWS.put("value", parametros.get("codDep"));
				 mapPropertyWS.put("type", "propertyString");
				 mapPropertiesWS.add(mapPropertyWS);
				 
				 mapPropertyWS = new HashMap<String, Object>();
				 mapPropertyWS.put("propertyDefinitionId", "cod_dep_ori");
				 mapPropertyWS.put("value", parametros.get("codDep"));
				 mapPropertyWS.put("type", "propertyString");
				 mapPropertiesWS.add(mapPropertyWS);
			 }
			 
			 metadata = parametros.get("metadata") != null ? (String)parametros.get("metadata") : null;
			  
			 if(metadata != null) {
				 mapPropertyWS = new HashMap<String, Object>();
				 mapPropertyWS.put("propertyDefinitionId", "plbrs_clave");
				 mapPropertyWS.put("value", metadata);
				 mapPropertyWS.put("type", "propertyString");
				 mapPropertiesWS.add(mapPropertyWS);
			 }	 
			 
			 mapPropertyWS = new HashMap<String, Object>();
			 mapPropertyWS.put("propertyDefinitionId", "des_carp");
			 mapPropertyWS.put("value", ValidaConstantes.IND_WS_ECM_DESC_CARP);
			 mapPropertyWS.put("type", "propertyString");
			 mapPropertiesWS.add(mapPropertyWS);
			 
			 mapParamWS.put("properties", mapPropertiesWS);
			 mapParamWS.put("folderId", folderId);//COMENTAR AL UTILIZAR UN FLUJO DE TRABAJO QUE REALICE EL ENCARPETADO
			 
			 mapContentStreamWS = new HashMap<String, Object>();
			 mapContentStreamWS.put("length", parametros.get("archLength"));
			 mapContentStreamWS.put("mimeType", parametros.get("archMimeType"));
			 mapContentStreamWS.put("filename", parametros.get("archFileName"));
			 
			 if(parametros.get("stream") != null) {
				 stream = (String)parametros.get("stream");
			 } else {
				 stream = DatatypeConverter.printBase64Binary((byte[])parametros.get("archContent"));
			 }
			 
			 mapContentStreamWS.put("stream", stream);
			 
			 mapParamWS.put("contentStream", mapContentStreamWS);
			 
			 restTemplate = new RestTemplate();
			 restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
			 headers = new HttpHeaders();
		 	 headers.setContentType(MediaType.APPLICATION_JSON);

		 	 serializer = new JsonSerializer();
		 	 jsonRequest = (String)serializer.serialize(mapParamWS);
		 	 
		 	 if (log.isDebugEnabled()) {
		 		 mapParamTempWS = mapParamWS;
		 		 mapContentStreamTempWS = (HashMap<String, Object>)mapParamTempWS.get("contentStream");
		 		 mapContentStreamTempWS.remove("stream");
		 		 mapParamTempWS.put("contentStream", mapContentStreamTempWS);
		 		 log.debug("jsonRequest:" + (String)serializer.serialize(mapParamTempWS));
		 	 }
			 
		 	 entity = new HttpEntity<String>(jsonRequest, headers);
			 
		 	 jsonResponse = restTemplate.postForObject(url, entity, String.class);
			 
		 	 if (log.isDebugEnabled()) log.debug("jsonResponse:" + jsonResponse);
		 	 
		 	 mapResponse = (HashMap<String, Object>)serializer.deserialize(jsonResponse);
			 codIdecm = (String)mapResponse.get("objectId");
		} catch (Exception ex) {
			if (log.isDebugEnabled()) log.debug("Error - EcmServiceImpl.guardarDocumentoECM");
			log.error(ex, ex);
			throw ex;
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - EcmServiceImpl.guardarDocumentoECM");
			
			NDC.pop();
			NDC.remove();
		}
		
		return codIdecm;
	}

	public ResponseEntity<byte[]> descargarDocumentoECM(Map<String, Object> parametros) throws Exception {
		ResponseEntity<byte[]> response = null;
		RestTemplate restTemplate = null;
		String url = null;
		HttpHeaders headers = null;
		HttpEntity<String> entity = null;
		String codIdecm = null;
		Map<String, Object> mapDataSourceParam = null;
		//DataSource origenDatosParam = null;
		Map<String, Object> mapT01Param = null;
		T01ParamBean t01ParamBean = null;
		Map<String, Object> mapaPool = null;
		
		if (log.isDebugEnabled()) log.debug("Inicio - ExpedienteVirtualRestController.descargarDocumentoECM");
		
		 mapDataSourceParam = new HashMap<String, Object>();
		 
		 mapDataSourceParam.put("indCentral", DataSourceConstantes.IND_BASEDATOS_CENTRAL);
		 mapDataSourceParam.put("oriDatos", DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);

		 //origenDatosParam = generalService.obtenerOrigenDatos(mapDataSourceParam);
		 
//		 mapaPool = generalService.obtenerOrigenDatosMap(mapDataSourceParam);
//		 DataSourceContextHolder.setKeyDataSource(mapaPool.get("nombrePool").toString());
		 DataSourceContextHolder.setKeyDataSource(DataSourceConstantes.DATASOURCE_CONSULTA_RECAUDA);
		 
		 mapT01Param = new HashMap<String, Object>();
		 //mapT01Param.put("origenDatos", origenDatosParam); 
		 mapT01Param.put("codClase", CatalogoConstantes.CATA_WS_ECM);
		 mapT01Param.put("indTipo", CatalogoConstantes.TIPO_PARAMETRO_DETALLE);
		 mapT01Param.put("codParametro", ValidaConstantes.IND_WS_ECM_CONSULTA);
		 
		 t01ParamBean = t01ParamDAO.obtener(mapT01Param);
		 url = t01ParamBean.getDesParametro();
		 url = url.substring(CatalogoConstantes.LIMITE_INFERIOR_WS_ECM, CatalogoConstantes.LIMITE_SUPERIOR_WS_ECM);
		 url = url.trim();
		 url += "{objectId}?inline=" + (String)parametros.get("inline");

		 restTemplate = new RestTemplate(); 
		 
		 restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter()); 
		 headers = new HttpHeaders();
		 headers.setAccept(Arrays.asList(MediaType.ALL));
		 entity = new HttpEntity<String>(headers);
		
		 codIdecm = (String)parametros.get("codIdecm");
		 response = restTemplate.exchange(url, HttpMethod.GET, entity, byte[].class, codIdecm);

		 if(response == null || !response.getStatusCode().equals(HttpStatus.OK)) {
			 if (log.isDebugEnabled() && response != null) log.debug("Error: " + new String(response.getBody()));
			 throw new ExcepcionECM(AvisoConstantes.MSG_ECM_NO_SERVICIO);
		 }
		 
		 if (log.isDebugEnabled()) log.debug("Final - ExpedienteVirtualRestController.descargarDocumentoECM");
		
		return response;
	}
	
	/* Seteo - Spring - Inicio */
	public void setT01ParamDAO(T01ParamDAO t01ParamDAO) {
		this.t01ParamDAO = t01ParamDAO;
	}

	public void setGeneralService(GeneralService generalService) {
		this.generalService = generalService;
	}
	/* Seteo - Spring - Final */
}