package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10458VersDocAdjBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T10458VersDocAdjDAO;

public class SqlMapT10458VersDocAdjDAO extends SqlMapClientDaoSupport implements T10458VersDocAdjDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> listarCopDocPorExp(Map<String, Object> parametros) {
		
		return (List<Map<String, Object>>)getSqlMapClientTemplate().queryForList("T10458VersDocAdj.listarCopDocPorExp", parametros);
		
	}
	//Inicio LLRB 20012020
		@SuppressWarnings("unchecked")
		@Override
		public Integer obtenerCopia(Map<String, Object> parametros) {			
			
			Integer cantidad = (Integer) getSqlMapClientTemplate().queryForObject("T10458VersDocAdj.obtenerCopia", parametros);
			
			return cantidad;
		}

		@Override
		public void insertar(Map<String, Object> parametros) {
			
			//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).insert("T6613docExpVirt.insertar", parametros);
			getSqlMapClientTemplate().insert("T10458VersDocAdj.insertar", parametros);
			
		}
	//Fin LLRB 20012020
	
}