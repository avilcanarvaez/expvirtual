package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.CorreosBean;


public interface CorreosService {
	public String obtenerCorreo(Map<String, Object> parametros) throws Exception;
	public void enviarCorreo(Map<String, Object> parametros);
	public List<CorreosBean> listarCorreo(Map<String, Object> parametros) throws Exception;
}
