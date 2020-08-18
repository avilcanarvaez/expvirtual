package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6621RespExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6621RespExpVirtDAO;

public class SqlMapT6621RespExpVirtDAO extends SqlMapClientDaoSupport implements T6621RespExpVirtDAO {	

	@Override
	public void insertar(Map<String, Object> parametros) {
		//13 JUL:
		//ANTES:
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).insert("T6621RespExpVirt.insertar", parametros);
		
		//DESPUES:
		this.getSqlMapClientTemplate().insert("T6621RespExpVirt.insertar", parametros);
	}
	
	@Override
	public void actualizar(Map<String, Object> parametros) {
		//13 JUL:
		//ANTES:
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).insert("T6621RespExpVirt.actualizar", parametros);
		
		//DESPUES:
		this.getSqlMapClientTemplate().insert("T6621RespExpVirt.actualizar", parametros);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T6621RespExpVirtBean> listar(Map<String, Object> parametros) {
		return getSqlMapClientTemplate().queryForList("T6621RespExpVirt.listar", parametros);
	}
	
	@Override
	public void actualizarMasivo(Map<String, Object> parametros) {
		//13 JUL:
		//ANTES:
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).insert("T6621RespExpVirt.actualizarMasivo", parametros);
		
		//DESPUES:
		this.getSqlMapClientTemplate().insert("T6621RespExpVirt.actualizarMasivo", parametros);
		
	}

	//Inicio - [oachahuic][PAS20165E210400270]
	@Override
	public T6621RespExpVirtBean obtenerDatosResponsable(Map<String, Object> parametros) {
		return (T6621RespExpVirtBean) getSqlMapClientTemplate().queryForObject("T6621RespExpVirt.getRespExpVirt", parametros);
	}
	//Fin - [oachahuic][PAS20165E210400270]
	
	//INICIO [ATORRESCH 20170224]
	@SuppressWarnings("unchecked")
	public List<String> listarCodigosResponsables(Map<String, Object> parametros) {		
		return (List<String>) getSqlMapClientTemplate().queryForList("T6621RespExpVirt.listarCodigosColaboradores", parametros);
	}
	//FIN    [ATORRESCH 20170224]
}