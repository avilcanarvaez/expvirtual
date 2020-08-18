package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6612DetPlantiBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6612DetPlantiDAO;

public class SqlMapT6612DetPlantiDAO extends SqlMapClientDaoSupport implements T6612DetPlantiDAO {


	@Override
	public void insertar(HashMap<String, Object> parametros) {
	
		T6612DetPlantiBean t6612DetPlantiBean = (T6612DetPlantiBean)parametros.get("t6612DetPlantiBean");
		
		//13 JUL:
		//ANTES:
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).insert("T6612DetPlanti.insertar", t6612DetPlantiBean);
		
		//DESPUES:
		this.getSqlMapClientTemplate().insert("T6612DetPlanti.insertar", t6612DetPlantiBean);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> listarExpTrab(Map<String, Object> parametros) {
		return getSqlMapClientTemplate().queryForList("T6612DetPlanti.listarExpTrab", parametros);
	}
	@Override
	public List<T6612DetPlantiBean> listar(Map<String, Object> parametros) {
		return getSqlMapClientTemplate().queryForList("T6612DetPlanti.listar", parametros);
	}

	@Override
	public void eliminar(HashMap<String, Object> parametros) {
		T6612DetPlantiBean t6612DetPlantiBean = (T6612DetPlantiBean)parametros.get("t6612DetPlantiBean");
		
		//13 JUL:
		//ANTES:
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).insert("T6612DetPlanti.eliminar", t6612DetPlantiBean);
		
		//DESPUES:
		this.getSqlMapClientTemplate().insert("T6612DetPlanti.eliminar", t6612DetPlantiBean);
	}

}
