package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6616PedRepBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6616PedRepDAO;

public class SqlMapT6616PedRepDAO extends SqlMapClientDaoSupport implements T6616PedRepDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T6616PedRepBean> listarExpedientesPorPedido(Map<String, Object> mapParametrosBusqueda) {		
		return getSqlMapClientTemplate().queryForList("T6616PedRep.listarExpedientesPorPedido", mapParametrosBusqueda);		
	}
	
	@Override
	public void actualizar(Map<String, Object> parametros) {
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).update("T6616PedRep.actualizar", parametros);
		getSqlMapClientTemplate().update("T6616PedRep.actualizar", parametros);
	}
	
	@Override
	public void insertar(Map<String, Object> parametros) {
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).update("T6616PedRep.insertar", parametros);
		getSqlMapClientTemplate().update("T6616PedRep.insertar", parametros);
		
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> listarExpTrabPedido(Map<String, Object> mapParametrosBusqueda) {		
		return getSqlMapClientTemplate().queryForList("T6616PedRep.listarExpTrabPedido", mapParametrosBusqueda);		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T6616PedRepBean> listarPedidos(Map<String, Object> mapParametrosBusqueda) {		
		return getSqlMapClientTemplate().queryForList("T6616PedRep.listarPedidos", mapParametrosBusqueda);		
	}
}
