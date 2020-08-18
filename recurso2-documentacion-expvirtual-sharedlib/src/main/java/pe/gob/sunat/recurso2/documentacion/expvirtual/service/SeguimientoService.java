package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6622SeguimBean;

public interface SeguimientoService {

	public void registrarSeguimiento (Map<String, Object> parametros) throws Exception;
	public List<T6622SeguimBean> listarSeguimientos(Map<String, Object> mapParametrosBusqueda)throws Exception;
	
}
