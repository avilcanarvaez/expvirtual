package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T1797DepenUOrgaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T1797DepenUOrgaDAO;

public class SqlMapT1797DepenUOrgaDAO extends SqlMapClientDaoSupport implements T1797DepenUOrgaDAO {
	
	@Override
	public T1797DepenUOrgaBean obtener (Map<String, Object> parametros) {
		
		return (T1797DepenUOrgaBean) getSqlMapClientTemplate().queryForObject("T1797DepenUOrga.obtener", parametros);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T1797DepenUOrgaBean> listar (Map<String, Object> parametros) {
		
		return (List<T1797DepenUOrgaBean>)getSqlMapClientTemplate().queryForList("T1797DepenUOrga.listar", parametros);
		
	}
	
}