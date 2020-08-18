package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;

public interface T6614ExpVirtualDAO {
	
	public T6614ExpVirtualBean obtener(Map<String, Object> parametros);
	
	public List<T6614ExpVirtualBean> listar(Map<String, Object> parametros);
	
	public void insertar(Map<String, Object> parametros);

	public List<T6614ExpVirtualBean> listarDocumentoExpediente(Map<String, Object> mapParametrosBusqueda);
	
	// Inicio [jquispe 27/05/2016] Obtiene el total de expedientes de la consulta.
	public Integer obtenerNumDocumentoExpediente(Map<String, Object> mapParametrosBusqueda);
	// Fin [jquispe 27/05/2016]
		
	public List<T6614ExpVirtualBean> listarRegDocExpediente(Map<String, Object> mapParametrosBusqueda);
	
	public List<T6614ExpVirtualBean> listaExpedientePorRuc(Map<String, Object> mapParametrosBusqueda);
	
	public T6614ExpVirtualBean obtenerDatosExpediente(Map<String, Object> parametros);
	
	public void actualizar(Map<String, Object> parametros);
	
	public List<Map<String, Object>> listarAcumuladosExpVirtual(Map<String, Object> parametros);
	public Integer obtenerNumAcumExpVirtual(Map<String, Object> parametros);
	
	public List<T6614ExpVirtualBean> listarExpedientesDelAcumulado(Map<String, Object> mapParametrosBusqueda);
	
	public List<Map<String, Object>> listarExpedienteTrab(Map<String, Object> parametros);
	
	public List<Map<String, Object>> listarNumExpVirtualAcumCierre(Map<String, Object> parametros);
	
	public void ReaperturaExpVirtual(Map<String, Object> parametros); //Agregado JEFFIO (22/02/2017)
	
	public void EliminarExpVirtual(Map<String, Object> parametros);//Agregado JEFFIO (22/02/2017)
	
	//INICIO LLRB 31012020
	public Map<String, Object>obtenerNroExpedienteVirtual(Map<String, Object> parametros);
	//FIN LLRB 31012020
	
	public List<T6614ExpVirtualBean> listarDocumentoExpedienteReq(Map<String, Object> mapParametrosBusqueda); //PAS20201U210500029 - HHC

}