package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6622SeguimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6622SeguimDAO;

public class SqlMapT6622SeguimDAO extends SqlMapClientDaoSupport implements T6622SeguimDAO{

	@Override
	public void insertar(Map<String, Object> parametros) {
		//13 JUL:
		//ANTES:
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).insert("T6622Seguim.insertar", parametros);
		
		//DESPUES:
		this.getSqlMapClientTemplate().insert("T6622Seguim.insertar", parametros);
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T6622SeguimBean> listarSeguimientos(Map<String, Object> mapParametrosBusqueda) {
		return getSqlMapClientTemplate().queryForList("T6622Seguim.listar", mapParametrosBusqueda);
	}
	// [ATORRESCH 2017/02/21] CONTABILIZA SI UN EXPEDIENTE FUE VISUALIZADO EN LA TABLA DE SEGUIMIENTO
	@Override
	public Integer contarExpedienteSeguimiento(Map<String, Object> parametros) {
		return (Integer) getSqlMapClientTemplate().queryForObject("T6622Seguim.contarExpedienteSeguimiento", parametros);
	}
	
	// [ATORRESCH 2017/05/18] ACTUALIZA EL SEGUIMIENTO DE CAMBIO DE ESTADO Y ETAPA DEL EXPEDIENTE A 99
	@Override
	public void eliminarSeguimientoCambioEstadoEtapa(Map<String, Object> parametros) {		
		getSqlMapClientTemplate().update("T6622Seguim.eliSegCmbEstEta", parametros);
	}
	
}
