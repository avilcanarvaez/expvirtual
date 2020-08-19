package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6618RepGenExpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6618RepGenExpDAO;

public class SqlMapT6618RepGenExpDAO extends SqlMapClientDaoSupport implements T6618RepGenExpDAO {
	@Override
	public void insertar(Map<String, Object> parametros) {
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).insert("T6618RepGenExp.insertar", parametros);
		getSqlMapClientTemplate().insert("T6618RepGenExp.insertar", parametros);
	}
	@Override
	public T6618RepGenExpBean datosRepPorExpediente(Map<String, Object> mapParametrosBusqueda){
		return (T6618RepGenExpBean) getSqlMapClientTemplate().queryForObject("T6618RepGenExp.reportePorExpediente", mapParametrosBusqueda);		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> listarPedidoGen(Map<String, Object> parametros) {
		return getSqlMapClientTemplate().queryForList("T6618RepGenExp.listarPedidoGen", parametros);
	}
	
	@Override
	public void actualizar(Map<String, Object> parametros) {
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).insert("T6618RepGenExp.actualizar", parametros);
		getSqlMapClientTemplate().insert("T6618RepGenExp.actualizar", parametros);
	}
	@Override
	public List<T6618RepGenExpBean> listarExpedientesTrabajo(Map<String, Object> parametrosBusqueda) {
		return getSqlMapClientTemplate().queryForList("T6618RepGenExp.listarExpedientesTrabajo", parametrosBusqueda);
	}
}
