package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6623TipDocExpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6623TipDocExpDAO;

public class SqlMapT6623TipDocExpDAO extends SqlMapClientDaoSupport implements T6623TipDocExpDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T6623TipDocExpBean> listar(Map<String, Object> parametros) {
		
		return getSqlMapClientTemplate().queryForList("T6623TipDocExp.listar", parametros);
		
	}
	
	@Override
	public void insertar(Map<String, Object> parametros) {
		//13 JUL:
		//ANTES:
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).insert("T6623TipDocExp.insertar", parametros);
		
		//DESPUES:
		this.getSqlMapClientTemplate().insert("T6623TipDocExp.insertar", parametros);
		
	}
	
	@Override
	public void actualizarMasivo(Map<String, Object> parametros) {
		//13 JUL:
		//ANTES:
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).update("T6623TipDocExp.actualizarMasivo", parametros);
		
		//DESPUES:
		this.getSqlMapClientTemplate().update("T6623TipDocExp.actualizarMasivo", parametros);
		
	}
	
	@Override
	public void actualizar(Map<String, Object> parametros) {	
		this.getSqlMapClientTemplate().update("T6623TipDocExp.actualizar", parametros);		
	}

	@Override
	public T6623TipDocExpBean getTipDocExp(Map<String, Object> parametros) {
		return (T6623TipDocExpBean) getSqlMapClientTemplate().queryForObject("T6623TipDocExp.getTipDocExp", parametros);
	}
	
}