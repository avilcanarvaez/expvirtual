package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.framework.spring.util.dao.SqlMapDAOBase;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.DataCatalogoDAO;

public class SqlMapDataCatalogoDAO extends SqlMapDAOBase implements DataCatalogoDAO {

	@SuppressWarnings("unchecked")
	public Map<String, Object> getDepAduanaByCod(Map<String, Object> params) {
		return (Map<String, Object>) getSqlMapClientTemplate().queryForObject("datacatalogo.getDepByCod", params);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> listarDepAduana() {
		return (List<Map<String, Object>>) this.getSqlMapClientTemplate().queryForList("datacatalogo.listarDependencia");
	}
	
}
