package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6616PedRepBean;


public interface T6616PedRepDAO {
	public List<T6616PedRepBean> listarExpedientesPorPedido(Map<String, Object> mapParametrosBusqueda);
	public void actualizar(Map<String, Object> parametros);
	public void insertar(Map<String, Object> parametros);
	public List<Map<String, Object>> listarExpTrabPedido(Map<String, Object> mapParametrosBusqueda);
	public List<T6616PedRepBean> listarPedidos(Map<String, Object> mapParametrosBusqueda);
}
