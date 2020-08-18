package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.CorreosBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.CorreosDAO;

public class SqlMapCorreosDAO extends SqlMapClientDaoSupport implements CorreosDAO {
	
	@Override
	public CorreosBean obtener(Map<String, Object> parametros) {
		return (CorreosBean)getSqlMapClientTemplate().queryForObject("Correos.obtener", parametros);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CorreosBean> listar(Map<String, Object> parametros) {
		return (List<CorreosBean>)getSqlMapClientTemplate().queryForList("Correos.listar", parametros);
	}
}