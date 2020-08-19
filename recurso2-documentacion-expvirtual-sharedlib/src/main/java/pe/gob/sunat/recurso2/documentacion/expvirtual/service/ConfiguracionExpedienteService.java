package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DocOrigenBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ExpOrigenBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ResCoaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6623TipDocExpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6624TipExpProcBean;

public interface ConfiguracionExpedienteService {
	
	/**
	 * Método encargado de listar todos los procesos concernientes a los expedientes virtuales
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<T01ParamBean> listarProcesos () throws Exception;
	
	/**
	 * Método encargado de listar los tipos de expedientes devuelta como parametro (disponibles, asociados)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<T6624TipExpProcBean> listarParametrosTiposExpendiente (Map<String, Object> parametros) throws Exception;
	
	/**
	 * Método encargado de listar los tipos de expedientes (todos, disponibles, asociados)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<T6624TipExpProcBean> listarTiposExpendiente (Map<String, Object> parametros) throws Exception;
	
	/**
	 * Método encargado registrar la asociación de los tipos de expedientes por proceso
	 * 
	 * @param parametros
	 * @throws Exception
	 */
	public void realizarAsociacionTipoExpedienteProceso (Map<String, Object> parametros) throws Exception;
	
	/**
	 * Método encargado de listar los tipos de documentos devuelta como parametro (disponibles, asociados)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<T6623TipDocExpBean> listarParametrosTiposDocumento (Map<String, Object> parametros) throws Exception;

	/**
	 * Método encargado de listar los tipos de documentos (todos, disponibles, asociados)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> listarTablasTipDoc3Dig (Map<String, Object> parametros) throws Exception;
	
	/**
	 * Método encargado de listar los tipos de documentos (todos, disponibles, asociados)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<T6623TipDocExpBean> listarTiposDocumentos (Map<String, Object> parametros) throws Exception;
	
	
	/**
	 *  Método encargado registrar la asociación de los tipos de documentos por tipo de expediente
	 * 
	 * @param parametros
	 * @throws Exception
	 */
	public void realizarAsociacionTipoDocumentoTipoExpediente (Map<String, Object> parametros, List<Map<String, Object>> listadoTipoDoc23) throws Exception;
	
	
	/**
	 *  Método encargado registrar la asociación de los tipos de documentos por tipo de expediente
	 * 
	 * @param parametros
	 * @throws Exception
	 */
	public List<T6613DocExpVirtBean> listarDocumentosExpediente (Map<String, Object> parametros) throws Exception;
	
	/**
	 * Método encargado de listar los tipos de dependencias
	 * 
	 * @param parametros
	 * @throws Exception
	 */
	
	public List<T01ParamBean> listarDependencias () throws Exception;
	
	public List<T01ParamBean> listarEstadosExpVirtual () throws Exception;

	public List<T01ParamBean> listarFormatosAdicionales() throws Exception;

	public List<T01ParamBean> listarRepTribuAduaneros() throws Exception;
	// Inicio [eaguilar 26/05/2015]
	//	public ResCoaBean obtenerNumDocumento(Map<String, Object> parametros) throws Exception {
	
	public Map<String, Object> obtenerNumDocumento(Map<String, Object> parametros) throws Exception;
	// Fin [eaguilar 26/05/2015]
	
	// Inicio [nchavez 26/05/2015]
	public ResCoaBean obtenerNumDocumentoRC(Map<String, Object> parametros) throws Exception;
	// Fin [nchavez 26/05/2015]
	public List<T01ParamBean> listarOrigenExpediente () throws Exception;

	public T01ParamBean obtenerProceso(String codProceso) throws Exception;

	public T01ParamBean obtenerTipoExpediente(String codTpoExpdte) throws Exception;

	public T01ParamBean obtenerTipoDoc(String codTpoDoc, String claseTipoDoc) throws Exception;
	
	public List<T6623TipDocExpBean> listarTipDocExpediente(Map<String, Object> mapParametros) throws Exception;
	
	public Map<String, String> listarMapDependencias() throws Exception;
	
	public Map<String, String> listarMapProcesos() throws Exception;
	
	public Map<String, String> listarMapTiposExpediente() throws Exception;
	
	public T01ParamBean obtenerParametro(String codCata, String cod) throws Exception;
	
	public List<Map<String, Object>> listarTipoDocsNoAsocExp(Map<String, Object> parametros) throws Exception;

	public Map<String,Object> obtenerFechaNotificacion(Map<String, Object> parametros) throws Exception;
	// Inicio [eaguilar 26/05/2015]
	public Map<String, Object> obtenerEstadoEtapa(Map<String, Object> parametros) throws Exception;
	// Fin [eaguilar 26/05/2015]
	
	// [jjurado 25/05/2016] obtiene los datos del expediente origen
	public ExpOrigenBean obtenerDatosExOrigen(Map<String, Object> parametros) throws Exception;
	/* Seteo - Spring - Inicio */
	
	public String cantidadTipoDocumentosRelacion(Map<String, Object> parametros) throws Exception;
	
	public T6623TipDocExpBean obtenerTipDocExpediente(Map<String, Object> mapParametros) throws Exception;
	
	public DocOrigenBean obtenerDatosDocOrigen(Map<String, Object> parametros) throws Exception;
}