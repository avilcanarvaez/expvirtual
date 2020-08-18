package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10460VincExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T10460VincExpVirtDAO;

public class SqlMapT10460VincExpVirtDAO extends SqlMapClientDaoSupport implements T10460VincExpVirtDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> obtenerNumExpVinculados(Map<String, Object> mapParametrosBusqueda) {
		return (List<Map<String, Object>>) getSqlMapClientTemplate().queryForList("T10460VincExpVirt.obtenerNroExpedienteVirtual", mapParametrosBusqueda);
	}
	
	
	//Inicio CVG 16/01/2020
	@Override
	public void insertar(T10460VincExpVirtBean parametros) {
		getSqlMapClientTemplate().insert("T10460VincExpVirt.insertar", parametros);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T10460VincExpVirtBean> listar(Map<String, Object> parametros) {
		return getSqlMapClientTemplate().queryForList("T10460VincExpVirt.listar", parametros);
	}
	
	public void actualizar(Map<String, Object> parametros) {
		getSqlMapClientTemplate().update("T10460VincExpVirt.actualizar", parametros);
	}
	
	//fin CVG
	
}