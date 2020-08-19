package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10461SolDesBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T10461SolDesDAO;

public class SqlMapT10461SolDesDAO extends SqlMapClientDaoSupport implements T10461SolDesDAO {

    @Override
	public void insertar(Map<String, Object> parametros) {
	getSqlMapClientTemplate().insert("T10461SolDes.insertar", parametros);
			
	}
    @SuppressWarnings("unchecked")
	@Override
	public Integer obtenerNroSolicitud(Map<String, Object> parametros) {			
		
		Integer cantidad = (Integer) getSqlMapClientTemplate().queryForObject("T10461SolDes.obtenerNroSolicitud", parametros);
		
		return cantidad;
	}
	
    //PAS20201U210500029 - HHC INICIO
    @SuppressWarnings("unchecked")
   	@Override
   	public List<Map<String, Object>> listarSolicitudDescarga(Map<String, Object> mapParametrosBusqueda) {		
   		return getSqlMapClientTemplate().queryForList("T10461SolDes.listarSolicitudDescarga", mapParametrosBusqueda);		
   	}
   	
       @Override
   	public void actualizar(Map<String, Object> parametros) {
   		getSqlMapClientTemplate().update("T10461SolDes.actualizar", parametros);
   	}
   	
       @SuppressWarnings("unchecked")
   	@Override
   	public List<T10461SolDesBean> listarPedidosSolicitud(Map<String, Object> mapParametrosBusqueda) {		
   		return getSqlMapClientTemplate().queryForList("T10461SolDes.listarPedidosSolicitud", mapParametrosBusqueda);		
   	}
     //PAS20201U210500029 - HHC FIN
}