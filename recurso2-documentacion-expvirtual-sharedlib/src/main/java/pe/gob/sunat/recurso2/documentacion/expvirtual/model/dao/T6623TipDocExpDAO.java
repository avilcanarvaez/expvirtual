package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6623TipDocExpBean;

public interface T6623TipDocExpDAO {
	
	public List<T6623TipDocExpBean> listar(Map<String, Object> parametros);
	
	public void insertar(Map<String, Object> parametros);
	
	public void actualizarMasivo(Map<String, Object> parametros);
	
	public void actualizar(Map<String, Object> parametros);
	
	public T6623TipDocExpBean getTipDocExp(Map<String, Object> parametros);
	// Inicio - [avilcan]
	public int verficarDocumentoAsociado(Map<String, Object> parametros);
	// Fin - [avilcan]
	
}