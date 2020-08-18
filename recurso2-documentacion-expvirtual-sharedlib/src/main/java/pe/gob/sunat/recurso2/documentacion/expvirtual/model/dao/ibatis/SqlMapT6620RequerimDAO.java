package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6620RequerimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6620RequerimDAO;

public class SqlMapT6620RequerimDAO extends SqlMapClientDaoSupport implements T6620RequerimDAO {
	
	@Override
	public void insertar(Map<String, Object> parametros) {
		
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).insert("T6620Requerim.insertar", parametros);
		getSqlMapClientTemplate().insert("T6620Requerim.insertar", parametros);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T6620RequerimBean> listar(Map<String, Object> parametros) {
		return getSqlMapClientTemplate().queryForList("T6620Requerim.listar", parametros);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T6620RequerimBean> listarRequerimientos(Map<String, Object> mapParametrosBusqueda) {
		return getSqlMapClientTemplate().queryForList("T6620Requerim.listarRequerimientos", mapParametrosBusqueda);
	}
	
	@Override
	public void insertarManual(Map<String, Object> mapDatos) {
		
		T6620RequerimBean t6620RequerimBean = (T6620RequerimBean) mapDatos.get("t6620RequerimBean");
		//new SqlMapClientTemplate((DataSource)mapDatos.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).insert("T6620Requerim.insertarManual", t6620RequerimBean);
		getSqlMapClientTemplate().insert("T6620Requerim.insertarManual", t6620RequerimBean);
		
	}
	
	@Override
	public void update(Map<String, Object> mapDatos) {
		
		T6620RequerimBean t6620RequerimBean = (T6620RequerimBean) mapDatos.get("t6620RequerimBean");
		//new SqlMapClientTemplate((DataSource)mapDatos.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).update("T6620Requerim.actualizar", t6620RequerimBean);
		getSqlMapClientTemplate().update("T6620Requerim.actualizar", t6620RequerimBean);
		
	}

	//INICIO [ATORRESCH 2017/03/02]
	@Override
	public void Eliminar(Map<String, Object> parametros) {
		T6620RequerimBean t6620RequerimBean = (T6620RequerimBean)parametros.get("t6620RequerimBean");
		getSqlMapClientTemplate().update("T6620Requerim.anular", t6620RequerimBean);		
	}
	//FIN [ATORRESCH 2017/03/02]
	
	
	//Inicio staype 26/12/2019 [PAS20191U210500291] 
	//Modifica cvalencia 09/01/2020
	@Override
	public T6620RequerimBean obtenerRequerimiento(Map<String, Object> mapParametrosBusqueda) {
		return (T6620RequerimBean) getSqlMapClientTemplate().queryForObject("T6620Requerim.obtenerRequerimiento", mapParametrosBusqueda);
	}
	//fin modifica cvalencia
	
	@Override
	public void actualizar(Map<String, Object> parametros) {
		
		//T6620RequerimBean t6620RequerimBean = (T6620RequerimBean) mapDatos.get("t6620RequerimBean");
		//new SqlMapClientTemplate((DataSource)mapDatos.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).update("T6620Requerim.actualizar", t6620RequerimBean);
		getSqlMapClientTemplate().update("T6620Requerim.actualizarFecVen", parametros);
		
	}
	
	//Fin staype 26/12/2019 obtiene datos de requerimiento
	
	
	
	
}