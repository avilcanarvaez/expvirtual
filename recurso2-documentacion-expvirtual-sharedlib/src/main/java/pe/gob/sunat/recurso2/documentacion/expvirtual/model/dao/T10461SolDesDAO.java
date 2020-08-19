package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10461SolDesBean;

public interface T10461SolDesDAO {

	public void insertar(Map<String, Object> parametros);
	
	public Integer obtenerNroSolicitud(Map<String, Object> parametros);
	
	//PAS20201U210500029 - HHC inicio 
	public List<Map<String, Object>> listarSolicitudDescarga(Map<String, Object> mapParametrosBusqueda); 
	
	public void actualizar(Map<String, Object> parametros);
	
	public List<T10461SolDesBean> listarPedidosSolicitud(Map<String, Object> mapParametrosBusqueda);
	//PAS20201U210500029 - HHC Fin
}