package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10372DetRequerimBean;

public interface DetalleRequerimientoService {
	
	public List<T10372DetRequerimBean> obtenerListaItems(Map<String, Object> mapParametrosBusqueda) throws Exception;
	
	public Map<String, Object> obtenerDatosSupervisor(Map<String, Object> mapParametrosBusqueda) throws Exception;
		
}