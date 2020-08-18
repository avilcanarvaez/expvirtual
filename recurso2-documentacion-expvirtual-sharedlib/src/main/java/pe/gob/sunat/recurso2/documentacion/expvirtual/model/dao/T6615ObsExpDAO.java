package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6615ObsExpBean;

public interface T6615ObsExpDAO {

	public void insertar(Map<String, Object> parametros);

	public List<T6615ObsExpBean> listarObservaciones(
			Map<String, Object> mapParametrosBusqueda);
}
