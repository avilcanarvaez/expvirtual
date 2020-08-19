package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6624TipExpProcBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6624TipExpProcDAO;

public class SqlMapT6624TipExpProcDAO extends SqlMapClientDaoSupport implements T6624TipExpProcDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T6624TipExpProcBean> listar(Map<String, Object> parametros) {
		
		return getSqlMapClientTemplate().queryForList("T6624TipExpProc.listar", parametros);
		
	}
	
	@Override
	public void insertar(Map<String, Object> parametros) {
		//13 JUL:
		//ANTES:
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).insert("T6624TipExpProc.insertar", parametros);
		
		//DESPUES:
		this.getSqlMapClientTemplate().insert("T6624TipExpProc.insertar", parametros);
		
	}
	
	@Override
	public void actualizarMasivo(Map<String, Object> parametros) {
		//13 JUL:
		//ANTES:
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).update("T6624TipExpProc.actualizarMasivo", parametros);
		
		//DESPUES:
		this.getSqlMapClientTemplate().update("T6624TipExpProc.actualizarMasivo", parametros);
		
	}
	
}