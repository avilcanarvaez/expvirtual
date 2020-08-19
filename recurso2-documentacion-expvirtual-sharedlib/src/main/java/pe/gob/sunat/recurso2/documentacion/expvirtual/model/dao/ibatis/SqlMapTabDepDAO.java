package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.framework.spring.util.dao.SqlMapDAOBase;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.TabDepDAO;

public class SqlMapTabDepDAO extends SqlMapDAOBase implements TabDepDAO {

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getDepAduanaByCodUo(Map<String, Object> params) {
		return (Map<String, Object>) getSqlMapClientTemplate().queryForObject("tabdep.getByCodigo", params);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> listarDepAduana() {
		return (List<Map<String, Object>>) this.getSqlMapClientTemplate().queryForList("tabdep.listarDependencia");
	}

}
