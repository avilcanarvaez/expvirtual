package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;


import pe.gob.sunat.framework.spring.util.dao.SqlMapDAOBase;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T01ParamBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.DocInteDAO;

@SuppressWarnings("deprecation")
public class SqlMapDocInteDAO extends SqlMapDAOBase implements DocInteDAO {

	@SuppressWarnings({ "unchecked" })
	@Override
	public Map<String, Object> getByPk(Map<String, Object> params) {
		return (Map<String, Object>) getSqlMapClientTemplate().queryForObject("docinte.getByPk", params);
	}
	
	public List<Map<String, Object>> listarDocCircular(Map<String, Object> params) {
		return (List<Map<String, Object>>) this.getSqlMapClientTemplate().queryForList("docinte.listarDocCircular", params);
	}

}
