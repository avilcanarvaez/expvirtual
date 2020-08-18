package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.AccExp;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.AccExpDAO;

public class SqlMapAccExpDAO extends SqlMapClientDaoSupport implements AccExpDAO {

	@SuppressWarnings("unchecked")
	public List<AccExp> listar(Map<String, Object> parametros) {
		return (List<AccExp>) getSqlMapClientTemplate().queryForList("acc_exp.listar", parametros);
	}

}
