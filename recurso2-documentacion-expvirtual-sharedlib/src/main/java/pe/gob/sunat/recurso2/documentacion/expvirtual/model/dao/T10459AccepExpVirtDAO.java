package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10459AccepExpVirtBean;

public interface T10459AccepExpVirtDAO {
	
	public void insertar(Map<String, Object> parametros);
	
	public void eliminar(Map<String, Object> parametros);
	
	public void actualizar(Map<String, Object> parametros);
	
	public List<T10459AccepExpVirtBean> listar(Map<String, Object> parametros);
	
	public void actualizarMasivo(Map<String, Object> parametros);
	
	public T10459AccepExpVirtBean obtenerDatosResponsable(Map<String, Object> parametros);
	
	public List<String> listarCodigosResponsables(Map<String, Object> parametros);
	
	public Integer verificar(Map<String, Object> parametros);
	
	//INICIO LLRB 31/01/2020
	public Integer obtenerNroExpediente(Map<String, Object> parametros);
	//FIN LLRB 31012020
}