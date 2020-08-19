package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T1797DepenUOrgaBean;

public interface T1797DepenUOrgaDAO {
	
	public T1797DepenUOrgaBean obtener (Map<String, Object> parametros);
	
	public List<T1797DepenUOrgaBean> listar (Map<String, Object> parametros);
	
}