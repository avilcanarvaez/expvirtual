package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10459AccepExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T10459AccepExpVirtDAO;

public class SqlMapT10459AccepExpVirtDAO extends SqlMapClientDaoSupport implements T10459AccepExpVirtDAO {	

	@Override
	public void insertar(Map<String, Object> parametros) {
		this.getSqlMapClientTemplate().insert("T10459accepexpvirt.insertar", parametros);
	}
	
	@Override
	public void eliminar(Map<String, Object> parametros) {
		this.getSqlMapClientTemplate().update("T10459accepexpvirt.eliminar", parametros);
	}
	
	@Override
	public void actualizar(Map<String, Object> parametros) {
			this.getSqlMapClientTemplate().insert("T10459accepexpvirt.actualizar", parametros);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T10459AccepExpVirtBean> listar(Map<String, Object> parametros) {
		return getSqlMapClientTemplate().queryForList("T10459accepexpvirt.listar", parametros);
	}
	
	@Override
	public void actualizarMasivo(Map<String, Object> parametros) {
				this.getSqlMapClientTemplate().insert("T10459accepexpvirt.actualizarMasivo", parametros);
		
	}

	@Override
	public T10459AccepExpVirtBean obtenerDatosResponsable(Map<String, Object> parametros) {
		return (T10459AccepExpVirtBean) getSqlMapClientTemplate().queryForObject("T10459accepexpvirt.getRespExpVirt", parametros);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Integer verificar(Map<String, Object> parametros) {			
		
		Integer cantidad = (Integer) getSqlMapClientTemplate().queryForObject("T10459accepexpvirt.verificarNumSoli", parametros);
		
		return cantidad;
	}
	
	
	

	@SuppressWarnings("unchecked")
	public List<String> listarCodigosResponsables(Map<String, Object> parametros) {		
		return (List<String>) getSqlMapClientTemplate().queryForList("T10459accepexpvirt.listarCodigosColaboradores", parametros);
	}

	//INICIO LLRB 31012020
		@SuppressWarnings("unchecked")
		@Override
		public Integer obtenerNroExpediente(Map<String, Object> parametros) {			
			
			Integer cantidad = (Integer) getSqlMapClientTemplate().queryForObject("T10459accepexpvirt.obtenerNroExpediente", parametros);
			
			return cantidad;
		}
		//INICIO LLRB 31012020

}