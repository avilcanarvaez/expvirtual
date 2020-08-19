package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6618RepGenExpBean;

public interface T6618RepGenExpDAO {
	public void insertar(Map<String, Object> parametros);
	
	public T6618RepGenExpBean datosRepPorExpediente(Map<String, Object> mapParametrosBusqueda);
	public List<Map<String, Object>> listarPedidoGen(Map<String, Object> parametros);
	public void actualizar(Map<String, Object> parametros);

	public List<T6618RepGenExpBean> listarExpedientesTrabajo(Map<String, Object> parametrosBusqueda);
	
}
