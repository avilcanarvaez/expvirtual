package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.Map;

import pe.gob.sunat.framework.spring.util.dao.SqlMapDAOBase;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.CircunOceDAO;

public class SqlMapCircunOceDAO extends SqlMapDAOBase implements CircunOceDAO {

	@Override
	public DdpBean getAgenteHabilitado(Map<String, Object> params) {
		return (DdpBean)getSqlMapClientTemplate().queryForObject("circunoce.getAgenteHabilitado", params);
	}

}
