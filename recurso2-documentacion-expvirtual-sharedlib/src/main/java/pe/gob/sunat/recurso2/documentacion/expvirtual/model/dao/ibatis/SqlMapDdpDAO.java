package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.DdpDAO;

public class SqlMapDdpDAO extends SqlMapClientDaoSupport implements DdpDAO {
	
	@Override
	public DdpBean obtener(Map<String, Object> parametros) {
		
		//return (DdpBean) new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).queryForObject("Ddp.obtener", parametros);
		return (DdpBean) getSqlMapClientTemplate().queryForObject("Ddp.obtener", parametros);
		
	}
	
	@Override
	public Map<String, Object> obtenerFoto(Map<String, Object> parametros) {
		//Map<String, Object> parametros = new HashMap<String, Object>();
		//parametros.put("numDoc", dni);
		//return (Map<String, Object>) new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).queryForObject("Ddp.obtenerFoto", parametros);
		return (Map<String, Object>) getSqlMapClientTemplate().queryForObject("Ddp.obtenerFoto", parametros);
		
	}
	
}