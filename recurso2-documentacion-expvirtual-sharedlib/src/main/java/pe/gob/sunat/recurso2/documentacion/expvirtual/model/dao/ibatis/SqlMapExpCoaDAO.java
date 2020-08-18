package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ExpCoaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ExpCoaDAO;

public class SqlMapExpCoaDAO extends SqlMapClientDaoSupport implements ExpCoaDAO {
	
	@SuppressWarnings("unchecked")	
	
	@Override
	public List<ExpCoaBean> listar(Map<String, Object> parametros) {
		
		//return (List<ExpCoaBean>) new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).queryForList("expcoa.listar", parametros);
		return (List<ExpCoaBean>) getSqlMapClientTemplate().queryForList("expcoa.listar", parametros);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> listarAcumuladosDoc(Map<String, Object> parametros) {
		//13 JUL:
		//ANTES:
		//return (List<Map<String, Object>>) new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).queryForList("expcoa.listarAcumuladosDoc", parametros);
		//DESPUES:
		return (List<Map<String, Object>>) this.getSqlMapClientTemplate().queryForList("expcoa.listarAcumuladosDoc", parametros);
	}

	@Override
	public ExpCoaBean obtenerIndicadores(Map<String, Object> parametros) {
		//return (ExpCoaBean) new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).queryForObject("expcoa.obtenerIndicadores", parametros);
		return (ExpCoaBean) getSqlMapClientTemplate().queryForObject("expcoa.obtenerIndicadores", parametros);
	}
}