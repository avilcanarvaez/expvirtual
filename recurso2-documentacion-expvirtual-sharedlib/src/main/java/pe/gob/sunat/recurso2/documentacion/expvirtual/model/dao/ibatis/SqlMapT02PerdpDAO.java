package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T02PerdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T02PerdpDAO;

public class SqlMapT02PerdpDAO extends SqlMapClientDaoSupport implements T02PerdpDAO {
	
	 
	public T02PerdpBean obtener (Map<String, Object> parametros) {
		
		return (T02PerdpBean) getSqlMapClientTemplate().queryForObject("T02Perdp.obtener", parametros);
		
	}
	
	@SuppressWarnings("unchecked")
	 
	public List<T02PerdpBean> listar(Map<String, Object> parametros) {
		
		return (List<T02PerdpBean>)getSqlMapClientTemplate().queryForList("T02Perdp.listar", parametros);
		
	}
    
    //PAS20191U210500144 Inicio 400101 RF28 PSALDARRIAGA
	@SuppressWarnings("unchecked")
	
	public List<T02PerdpBean> listarResponsablesDependencia(
			Map<String, Object> mapParamBusq) {

		return (List<T02PerdpBean>)getSqlMapClientTemplate().queryForList("T02Perdp.listarResponsablesDependencia", mapParamBusq);
	}
	//PAS20191U210500144 Fin 400101 RF28 PSALDARRIAGA

	//[PAS20191U210500291]: JMC - INI
	public String obtenerUUOOSupervisor (Map<String, Object> parametros) {
		
		return (String) getSqlMapClientTemplate().queryForObject("T02Perdp.obtenerUUOOSupervisor", parametros);
		
	}
	//[PAS20191U210500291]: JMC - FIN
	
}