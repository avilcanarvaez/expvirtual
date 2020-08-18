package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReqOrdFisBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6620RequerimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ReqOrdFisDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6620RequerimDAO;

public class SqlMapReqOrdFisDAO extends SqlMapClientDaoSupport implements ReqOrdFisDAO {
	//staype 26/12/2019 [PAS20191U210500291]
	@Override
	public ReqOrdFisBean obtenerDatosReq(Map<String, Object> mapParametrosBusqueda) {
		return (ReqOrdFisBean) getSqlMapClientTemplate().queryForObject("reqordfis.obtenerDatosReq", mapParametrosBusqueda);
	}
//	@Override
//	public T6620RequerimBean obtenerDatosReq(Map<String, Object> mapParametrosBusqueda) {
//		return (T6620RequerimBean) getSqlMapClientTemplate().queryForObject("T6620Requerim.listarRequerimientos", mapParametrosBusqueda);
//	}

	
}