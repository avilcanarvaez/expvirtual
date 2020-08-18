package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ExpediDAO;

public class SqlMapExpediDAO extends SqlMapClientDaoSupport implements ExpediDAO {

	@Override
	public void insertar(Map<String, Object> parametros) {
		
		getSqlMapClientTemplate().insert("expedi.insertar", parametros);
	}

}
