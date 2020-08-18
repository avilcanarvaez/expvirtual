package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ResCoaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ResCoaDAO;

public class SqlMapResCoaDAO extends SqlMapClientDaoSupport implements ResCoaDAO{

	@Override
	public ResCoaBean obtener(Map<String, Object> parametros) {
		//return (ResCoaBean) new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).queryForObject("rescoa.obtener", parametros);
		return (ResCoaBean) getSqlMapClientTemplate().queryForObject("rescoa.obtener", parametros);
	}

	// Inicio [nchavez 24/05/2015] Obtiene el numero de Documento de Requerimiento del RSIRAT
	@Override
	@SuppressWarnings("unchecked")
	public ResCoaBean obtenerNumeroDocumentoRequerimiento(Map<String, Object> parametros) {
		//return (ResCoaBean) new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).queryForObject("rescoa.obtener", parametros);
		return (ResCoaBean) getSqlMapClientTemplate().queryForObject("rescoa.obtener", parametros);
	}
	// Fin [nchavez 24/05/2015]
	
	@Override
	public Map<String, Object> obtenerFechaModificacion(Map<String, Object> parametros) {
		 return (Map<String, Object>) this.getSqlMapClientTemplate().queryForObject("rescoa.obtenerFechaNotificacion", parametros);
	}
}
