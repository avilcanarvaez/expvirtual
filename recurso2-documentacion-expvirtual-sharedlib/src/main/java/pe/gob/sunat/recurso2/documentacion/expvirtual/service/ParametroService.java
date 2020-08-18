package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;

public interface ParametroService {
	
	/**
	 *  Método encargado de listar los tipos de origenes del numero del expediente
	 * 
	 * @param parametros
	 * @throws Exception
	 */
	public List<T01ParamBean> listarNumeroTipoExpediente () throws Exception;
	
	/**
	 *  Método encargado de listar FECHA EXPEDIENTE GENERACION / ORIGEN                                                                                                
	 * 
	 * @param parametros
	 * @throws Exception
	 */
	public List<T01ParamBean> listarTipoFecha () throws Exception;
	
	/**
	 *  Método encargado de listar ESTADOS DEL DOCUMENTO                                                                                                
	 * 
	 * @param parametros
	 * @throws Exception
	 */
	
	public List<T01ParamBean> listaEstDocumento() throws Exception;
	
	/**
	 *  Método encargado de listar ESTAPAS DEL DOCUMENTO                                                                                                
	 * 
	 * @param parametros
	 * @throws Exception
	 */
	
	public List<T01ParamBean> listaEtapaDocumento() throws Exception;
	/**
	 * Método encargado de listar los estados del cierre del expediente
	 * 
	 * @return
	 * @throws Exception
	 */
	
	
	public List<T01ParamBean> listarEstadosCierreExpediente() throws Exception;
	
	public List<T01ParamBean> listarParametros(Map<String, Object> parametros) throws Exception;

	public T01ParamBean obtenerServicioSignnet() throws Exception;

	
}