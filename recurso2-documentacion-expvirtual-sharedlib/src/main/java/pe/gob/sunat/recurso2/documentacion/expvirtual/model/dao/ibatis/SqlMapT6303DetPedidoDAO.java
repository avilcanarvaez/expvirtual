package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6303DetPedidoDAO;

public class SqlMapT6303DetPedidoDAO extends SqlMapClientDaoSupport implements T6303DetPedidoDAO {
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> listarDocNotif(Map<String, Object> parametros) {
		return getSqlMapClientTemplate().queryForList("t6303.listarDocNotif", parametros);
	}
}
