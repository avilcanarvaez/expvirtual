package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6619RepGenPedDAO;

public class SqlMapT6619RepGenPedDAO extends SqlMapClientDaoSupport implements T6619RepGenPedDAO {
	@Override
	public void insertar(Map<String, Object> parametros) {
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).insert("T6619RepGenPed.insertar", parametros);
		getSqlMapClientTemplate().insert("T6619RepGenPed.insertar", parametros);
	}
	
	@Override
	public void actualizar(Map<String, Object> parametros) {
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).insert("T6619RepGenPed.actualizar", parametros);
		getSqlMapClientTemplate().insert("T6619RepGenPed.actualizar", parametros);
	}
}
