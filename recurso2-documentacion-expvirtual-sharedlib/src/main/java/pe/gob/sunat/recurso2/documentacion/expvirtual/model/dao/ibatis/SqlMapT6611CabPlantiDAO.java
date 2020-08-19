package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6611CabPlantiBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6611CabPlantiDAO;

public class SqlMapT6611CabPlantiDAO extends SqlMapClientDaoSupport implements T6611CabPlantiDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<T6611CabPlantiBean> listar(Map<String, Object> parametros) {
		return getSqlMapClientTemplate().queryForList("T6611CabPlanti.listar", parametros);
	}

	@Override
	public void actualizar(Map<String, Object> parametros) {
		//13 JUL:
		//ANTES:
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).update("T6611CabPlanti.actualizar", parametros);
		
		//DESPUES:
		this.getSqlMapClientTemplate().update("T6611CabPlanti.actualizar", parametros);
		
	}

	@Override
	public void insertar(HashMap<String, Object> parametros) {
	
		T6611CabPlantiBean t6611CabPlantiBean = (T6611CabPlantiBean)parametros.get("t6611CabPlantiBean");
		
		//13 JUL:
		//ANTES:
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).insert("T6611CabPlanti.insertar", t6611CabPlantiBean);
		
		//DESPUES:
		this.getSqlMapClientTemplate().insert("T6611CabPlanti.insertar", t6611CabPlantiBean);
		
	}

}
