package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10372DetRequerimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T10372DetRequerimDAO;

public class SqlMapT10372DetRequerimDAO extends SqlMapClientDaoSupport implements T10372DetRequerimDAO {
	//Inicio staype 26/12/2019 [PAS20191U210500291]
	@Override
	public void insertarManualDetReq(T10372DetRequerimBean parametros) {
//		HashMap<String, Object> parametros1 = null;
//		T10372DetRequerimBean t10372DetRequerimBean = (T10372DetRequerimBean)parametros1.get("t10372DetRequerimBean");
		getSqlMapClientTemplate().insert("t10372detrequerim.insertarManualDetRet", parametros);
		
	}
	//Fin staype 26/12/2019

	//[PAS20191U210500291]: JMC - INI
	@SuppressWarnings("unchecked")
	@Override
	public List<T10372DetRequerimBean> listarItems(Map<String, Object> mapParametrosBusqueda) {
		return getSqlMapClientTemplate().queryForList("t10372detrequerim.listarItems", mapParametrosBusqueda);
	}
	
	public void update(Map<String, Object> parametros) {
		getSqlMapClientTemplate().update("t10372detrequerim.update", parametros);
	}
	//[PAS20191U210500291]: JMC - FIN
}