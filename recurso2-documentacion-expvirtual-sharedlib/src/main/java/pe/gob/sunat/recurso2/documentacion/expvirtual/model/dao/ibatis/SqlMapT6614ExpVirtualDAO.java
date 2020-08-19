package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6614ExpVirtualDAO;

public class SqlMapT6614ExpVirtualDAO extends SqlMapClientDaoSupport implements T6614ExpVirtualDAO {
	
	@Override
	public T6614ExpVirtualBean obtener(Map<String, Object> parametros) {
		
		return (T6614ExpVirtualBean) getSqlMapClientTemplate().queryForObject("T6614ExpVirtual.obtener", parametros);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T6614ExpVirtualBean> listar(Map<String, Object> parametros) {
		
		return getSqlMapClientTemplate().queryForList("T6614ExpVirtual.listar", parametros);
		
	}
	
	@Override
	public void insertar(Map<String, Object> parametros) {
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).insert("T6614ExpVirtual.insertar", parametros);
		getSqlMapClientTemplate().insert("T6614ExpVirtual.insertar", parametros);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T6614ExpVirtualBean> listarDocumentoExpediente(Map<String, Object> mapParametrosBusqueda) {
		
		return getSqlMapClientTemplate().queryForList("T6614ExpVirtual.listarDocumentoExpediente", mapParametrosBusqueda);
		
	}
	
	// Inicio [jquispe 27/05/2016] Obtiene el total de expedientes de la consulta.
	@Override
	public Integer obtenerNumDocumentoExpediente(Map<String, Object> mapParametrosBusqueda) {
		return (Integer)getSqlMapClientTemplate().queryForObject("T6614ExpVirtual.obtenerNumDocumentoExpediente", mapParametrosBusqueda);
	}
	// Fin [jquispe 27/05/2016]
	@SuppressWarnings("unchecked")
	@Override
	public List<T6614ExpVirtualBean> listaExpedientePorRuc(Map<String, Object> mapParametrosBusqueda) {
		
		return getSqlMapClientTemplate().queryForList("T6614ExpVirtual.listaExpedientePorRuc", mapParametrosBusqueda);
		
	}
	
	@Override
	public T6614ExpVirtualBean obtenerDatosExpediente(Map<String, Object> parametros) {
		
		return (T6614ExpVirtualBean) getSqlMapClientTemplate().queryForObject("T6614ExpVirtual.obtenerDatosExpediente", parametros);
		
	}
	
	@Override
	public void actualizar(Map<String, Object> parametros) {
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).update("T6614ExpVirtual.actualizar", parametros);
		getSqlMapClientTemplate().update("T6614ExpVirtual.actualizar", parametros);
		
	}
	
	@Override
	public Integer obtenerNumAcumExpVirtual(Map<String, Object> parametros) {
		return (Integer)getSqlMapClientTemplate().queryForObject("T6614ExpVirtual.obtenerNumAcumExpVirtual", parametros);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> listarAcumuladosExpVirtual(Map<String, Object> parametros) {
		return getSqlMapClientTemplate().queryForList("T6614ExpVirtual.listarAcumuladosExpVirtual", parametros);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T6614ExpVirtualBean> listarExpedientesDelAcumulado(Map<String, Object> mapParametrosBusqueda) {
		
		return getSqlMapClientTemplate().queryForList("T6614ExpVirtual.listarExpedientesDelAcumulado", mapParametrosBusqueda);
		
	}

	@Override
	public List<T6614ExpVirtualBean> listarRegDocExpediente(Map<String, Object> mapParametrosBusqueda) {
		
		return getSqlMapClientTemplate().queryForList("T6614ExpVirtual.listarRegDocExpediente", mapParametrosBusqueda);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> listarExpedienteTrab(Map<String, Object> parametros) {
		return getSqlMapClientTemplate().queryForList("T6614ExpVirtual.listarExpedienteTrab", parametros);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> listarNumExpVirtualAcumCierre(Map<String, Object> parametros) {
		return getSqlMapClientTemplate().queryForList("T6614ExpVirtual.listarAcumuladosExpVirtualCierre", parametros);
	}
	//Inicio JEFFIO (22/02/2017)
	@Override
	public void ReaperturaExpVirtual(Map<String, Object> parametros){
		T6614ExpVirtualBean t6614ExpVirtualBean = (T6614ExpVirtualBean)parametros.get("t6614ExpVirtualBean");
		getSqlMapClientTemplate().update("T6614ExpVirtual.reapertura",t6614ExpVirtualBean);
	}
	public void EliminarExpVirtual(Map<String, Object> parametros){
		T6614ExpVirtualBean t6614ExpVirtualBean = (T6614ExpVirtualBean)parametros.get("t6614ExpVirtualBean");
		getSqlMapClientTemplate().update("T6614ExpVirtual.eliminarExpediente",t6614ExpVirtualBean);
	//fin
	}
	//INICIO LLRB 31012020
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> obtenerNroExpedienteVirtual(Map<String, Object> parametros) {
		return (Map<String, Object>) getSqlMapClientTemplate().queryForObject("T6614ExpVirtual.obtenerNroExpedienteVirtual", parametros);
	}
	//FIN LLRB 31012020

	// PAS20201U210500029 -HHC - INICIO
	@SuppressWarnings("unchecked")
	@Override
	public List<T6614ExpVirtualBean> listarDocumentoExpedienteReq(Map<String, Object> mapParametrosBusqueda) {
		//
		return getSqlMapClientTemplate().queryForList("T6614ExpVirtual.listarDocExpedienteReq",mapParametrosBusqueda);
	}
	// PAS20201U210500029 -HHC - FIN
}