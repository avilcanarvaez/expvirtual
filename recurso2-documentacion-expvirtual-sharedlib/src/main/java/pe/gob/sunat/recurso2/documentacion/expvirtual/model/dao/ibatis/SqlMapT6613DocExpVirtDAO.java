package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6613DocExpVirtDAO;

public class SqlMapT6613DocExpVirtDAO extends SqlMapClientDaoSupport implements T6613DocExpVirtDAO {
	
	@Override
	public void insertar(Map<String, Object> parametros) {
		
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).insert("T6613docExpVirt.insertar", parametros);
		getSqlMapClientTemplate().insert("T6613docExpVirt.insertar", parametros);
		
	}
	
	@Override
	public T6613DocExpVirtBean obtenerDocOrigen(Map<String, Object> parametros) {
		
		return (T6613DocExpVirtBean)getSqlMapClientTemplate().queryForObject("T6613docExpVirt.obtenerDocOrigen", parametros);
		
	}
	//[ATORRESCH 2017-02-21]
	@Override
	public Integer contarDocumentosRelacion(Map<String, Object> parametros) {		
		return (Integer) getSqlMapClientTemplate().queryForObject("T6613docExpVirt.contarDocumentosRelacion", parametros);		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T6613DocExpVirtBean> listar(Map<String, Object> parametros) {
		
		return (List<T6613DocExpVirtBean>)getSqlMapClientTemplate().queryForList("T6613docExpVirt.listar", parametros);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T6613DocExpVirtBean> listarDocumentosAsociados(Map<String, Object> parametros) {
		
		return (List<T6613DocExpVirtBean>)getSqlMapClientTemplate().queryForList("T6613docExpVirt.listarDocumentosAsociados", parametros);
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T6613DocExpVirtBean> listarTipoDocPorExpe(Map<String, Object> parametros) {
		
		return (List<T6613DocExpVirtBean>)getSqlMapClientTemplate().queryForList("T6613docExpVirt.listarTipoDocAsocExpe", parametros);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> listarDocumentosPorExp(Map<String, Object> parametros) {
		
		return (List<Map<String, Object>>)getSqlMapClientTemplate().queryForList("T6613docExpVirt.listarDocumentosPorExp", parametros);
		
	}
	
	@Override
	public void insertarManual(Map<String, Object> parametros) {
		
		T6613DocExpVirtBean t6613DocExpVirtBean = (T6613DocExpVirtBean)parametros.get("t6613DocExpVirtBean");
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).insert("T6613docExpVirt.insertarManual", t6613DocExpVirtBean);
		getSqlMapClientTemplate().insert("T6613docExpVirt.insertarManual", t6613DocExpVirtBean);
		
	}
	
	@Override
	public void update(T6613DocExpVirtBean t6613Bean) {
		getSqlMapClientTemplate().update("T6613docExpVirt.actualizar", t6613Bean);
		
	}
	
	
	//INICIO [ATORRESCH 2017/02/21]
	@Override
	public void Eliminar(Map<String, Object> parametros) {
		T6613DocExpVirtBean t6613DocExpVirtBean = (T6613DocExpVirtBean)parametros.get("t6613DocExpVirtBean");
		getSqlMapClientTemplate().update("T6613docExpVirt.anular", t6613DocExpVirtBean);		
	}
	//FIN [ATORRESCH 2017/02/21]
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T6613DocExpVirtBean> listarDocEstadosExp(Map<String, Object> parametros) {
		
		return (List<T6613DocExpVirtBean>)getSqlMapClientTemplate().queryForList("T6613docExpVirt.listarEstadosExp", parametros);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T6613DocExpVirtBean> listarDocumentosPorRequerim(Map<String, Object> parametros) {
		
		return (List<T6613DocExpVirtBean>)getSqlMapClientTemplate().queryForList("T6613docExpVirt.listarDocumentosPorRequerim", parametros);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T6613DocExpVirtBean> listarDocVisiblesContribuyente(Map<String, Object> parametros){
		
		return (List<T6613DocExpVirtBean>)getSqlMapClientTemplate().queryForList("T6613docExpVirt.listarDocVisiblesContribuyente", parametros);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> listarDocumentosAcumulados(Map<String, Object> parametros) {
		
		return (List<Map<String, Object>>)getSqlMapClientTemplate().queryForList("T6613docExpVirt.listarDocumentosAcumulados", parametros);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T6613DocExpVirtBean> listarDocVisiblesContribuyenteAcumulados(Map<String, Object> parametros){
		
		return (List<T6613DocExpVirtBean>)getSqlMapClientTemplate().queryForList("T6613docExpVirt.listarDocVisiblesContribuyenteAcumulados", parametros);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> listarDocExpTrab(Map<String, Object> parametros) {
		return (List<Map<String, Object>>)getSqlMapClientTemplate().queryForList("T6613docExpVirt.listarDocExpTrab", parametros);
	}
	
	// [eaguilar 26/05/2016]
	@Override
	public void desasociarAcu(Map<String, Object> parametros) {
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).update("T6613docExpVirt.desasociarAcu", parametros);
		getSqlMapClientTemplate().update("T6613docExpVirt.desasociarAcu", parametros);
	}
	
	// [jjurado 26/05/2016]
	@Override
	public T6613DocExpVirtBean obtenerDocumentoCierre(Map<String, Object> parametroBusqueda) {
		return (T6613DocExpVirtBean) getSqlMapClientTemplate().queryForObject("T6613docExpVirt.obtenerDocumentoCierre", parametroBusqueda);
	}
	// [jjurado 26/05/2016]
	
	// [gangles 16/09/2016]
		@Override
		public T6613DocExpVirtBean obtenerDatosDocumentoCierre(Map<String, Object> parametroBusqueda) {
			return (T6613DocExpVirtBean) getSqlMapClientTemplate().queryForObject("T6613docExpVirt.obtenerDatosDocumentoCierre", parametroBusqueda);
		}
	// [gangles 16/09/2016]

	//[JEFFIO 03/03/2017]
		
		@Override //obtiene el total de documento diferente de origen 
		public Integer obtenerNumDocumentoXExpediente(Map<String, Object> ParametrosBusqueda) {
			return (Integer)getSqlMapClientTemplate().queryForObject("T6613docExpVirt.NumDocPorExpediente", ParametrosBusqueda);
		}
		
	//[JEFFIO 03/03/2017]
	//[ATORRESCH 2017-05-08]
	@Override
	public Integer contarTipoDocumentosRelacion(Map<String, Object> parametros) {		
		return (Integer) getSqlMapClientTemplate().queryForObject("T6613docExpVirt.contarTipoDocumentosRelacion", parametros);		
	}
	
	public Integer countDocReaperByNumExpVir(String numExpVir) {
		return (Integer) getSqlMapClientTemplate().queryForObject("T6613docExpVirt.countDocReaperByNumExpVir", numExpVir);
	}
	//[PAS20191U210500144] - INICIO
		public T6613DocExpVirtBean obtenerDocumentoInterno(Map<String, Object> parametros) {
			return (T6613DocExpVirtBean) getSqlMapClientTemplate().queryForObject("T6613docExpVirt.obtenerDocInterno", parametros);
		}
		//[PAS20191U210500144] - FIN
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> obtenerFecNotif(Map<String, Object> parametros) {
		return (Map<String, Object>) getSqlMapClientTemplate().queryForObject("T6613docExpVirt.obtenerFecNotif", parametros);
	}
	//Inicio staype 26/12/2019 [PAS20191U210500291] valida que el requerimiento este asociado al un exp.
	@Override
	public T6613DocExpVirtBean obtenerReqExpediente(Map<String, Object> mapParametrosBusqueda) {
		return (T6613DocExpVirtBean) getSqlMapClientTemplate().queryForObject("T6613docExpVirt.obtenerReqExpediente", mapParametrosBusqueda);
	}
	//Fin staype 26/12/2019
	// PAS20201U210500029 - HHC INICIO
	@SuppressWarnings("unchecked")
	@Override
	public List<T6613DocExpVirtBean> listarDocumentosDeEscritos(Map<String, Object> mapParametrosBusqueda) {
		// TODO Auto-generated method stub
		return (List<T6613DocExpVirtBean>) getSqlMapClientTemplate().queryForList("T6613docExpVirt.listarDocumentosDeEscritos", mapParametrosBusqueda);
	}
	// PAS20201U210500029 - HHC FIN

	@SuppressWarnings("unchecked")
	@Override
	public List<T6613DocExpVirtBean> listDocPorNumExpVir(Map<String, Object> parametros) {
		return (List<T6613DocExpVirtBean>) getSqlMapClientTemplate().queryForList("T6613docExpVirt.listDocPorNumExpVir", parametros);
	}
	
	
	// Inicio - [avilcan]
	@SuppressWarnings("unchecked")
	@Override
	public List<T6613DocExpVirtBean> obtenerDocumentosExp(Map<String, Object> parametros) {		
		return (List<T6613DocExpVirtBean>)getSqlMapClientTemplate().queryForList("T6613docExpVirt.obtenerDocumentosXExpediente", parametros);
	}
	// Fin - [avilcan]


}