package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ExpFiscaDAO;

public class SqlMapExpFiscaDAO extends SqlMapClientDaoSupport implements ExpFiscaDAO {

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> spFiValidarExpeOrigen(Map<String, Object> parametros) {
		return (Map<String, Object>) getSqlMapClientTemplate().queryForObject("expfisca.spfivalidarexpeorigen", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> spFivalidarDocExpediente(Map<String, Object> parametros) {
		return (Map<String, Object>) getSqlMapClientTemplate().queryForObject("expfisca.spfivalidardocexpediente", parametros);
	}
    
    //PAS20191U210500144 Inicio 400101 RF04 PSALDARRIAGA
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> spFiverificarCierreExpediente(Map<String, Object> parametros) {
		return (Map<String, Object>) getSqlMapClientTemplate().queryForObject("expfisca.spfiverificarCierreExpediente", parametros);
	}
	//PAS20191U210500144 Fin 400101 RF04 PSALDARRIAGA
}
