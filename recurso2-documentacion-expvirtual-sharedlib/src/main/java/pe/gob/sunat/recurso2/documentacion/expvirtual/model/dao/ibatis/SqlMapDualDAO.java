package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import pe.gob.sunat.framework.spring.util.dao.SqlMapDAOBase;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.DualDAO;

public class SqlMapDualDAO extends SqlMapDAOBase implements DualDAO {

	@Override
	public Long getNextSequence() {
		return (Long) getSqlMapClientTemplate().queryForObject("dual.getNextSequence");
	}


}
