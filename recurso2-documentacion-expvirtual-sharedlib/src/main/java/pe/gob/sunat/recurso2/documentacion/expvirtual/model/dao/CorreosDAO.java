package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.CorreosBean;

public interface CorreosDAO {
	public CorreosBean obtener(Map<String, Object> parametros);
	public List<CorreosBean> listar(Map<String, Object> parametros);
}