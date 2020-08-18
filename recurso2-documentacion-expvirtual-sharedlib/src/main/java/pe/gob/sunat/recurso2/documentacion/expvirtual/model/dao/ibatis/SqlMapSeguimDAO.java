package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.SeguimDAO;

public class SqlMapSeguimDAO extends SqlMapClientDaoSupport implements SeguimDAO {

	@Override
	public void insertar(Map<String, Object> parametros) {
		
		getSqlMapClientTemplate().insert("seguim.insertar", parametros);
	}

}
