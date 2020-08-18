package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6621RespExpVirtBean;

public interface T6621RespExpVirtDAO {
	
	public void insertar(Map<String, Object> parametros);
	
	public void actualizar(Map<String, Object> parametros);
	
	public List<T6621RespExpVirtBean> listar(Map<String, Object> parametros);
	
	public void actualizarMasivo(Map<String, Object> parametros);
	
	public T6621RespExpVirtBean obtenerDatosResponsable(Map<String, Object> parametros);//[oachahuic][PAS20165E210400270]
	
	public List<String> listarCodigosResponsables(Map<String, Object> parametros); //INICIO [ATORRESCH 20170224]
	
}