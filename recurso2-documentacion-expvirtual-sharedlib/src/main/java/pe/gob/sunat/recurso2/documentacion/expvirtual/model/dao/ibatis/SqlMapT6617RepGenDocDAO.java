package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6617RepGenDocDAO;

public class SqlMapT6617RepGenDocDAO extends SqlMapClientDaoSupport implements T6617RepGenDocDAO {
	@Override
	public void insertar(Map<String, Object> parametros) {
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).insert("T6617RepGenDoc.insertar", parametros);
		this.getSqlMapClientTemplate().insert("T6617RepGenDoc.insertar", parametros);
	}
}
