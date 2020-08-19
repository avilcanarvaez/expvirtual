package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.Ccs19DAO;

public class SqlMapCcs19DAO extends SqlMapClientDaoSupport implements Ccs19DAO{

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> obtenerDatosSirat(Map<String, Object> parametros) {
		//return (Map<String, Object>) new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).queryForObject("ccs19.obtenerDatosSirat", parametros);
		return (Map<String, Object>) getSqlMapClientTemplate().queryForObject("ccs19.obtenerDatosSirat", parametros);
	}
	
	// [jjurado 25/05/2015] obtiene los datos del expediente origen 
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> obtenerDatosExpOrigen(Map<String, Object> parametros) {
		//return (Map<String, Object>) new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).queryForObject("ccs19.obtenerDatosExpOrigen", parametros);
		return (Map<String, Object>) getSqlMapClientTemplate().queryForObject("ccs19.obtenerDatosExpOrigen", parametros);
	}
}
