package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;


import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DetReqOrdFisBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReqOrdFisBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6620RequerimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.DetReqOrdFisDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ReqOrdFisDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6620RequerimDAO;

public class SqlMapDetReqOrdFisDAO extends SqlMapClientDaoSupport implements DetReqOrdFisDAO {
	//Inicio staype 26/12/2019 [PAS20191U210500291]
	@Override
	public List<DetReqOrdFisBean> obtenerDatosReqDet(Map<String, Object> mapParametrosBusqueda) {
		return (List<DetReqOrdFisBean>) getSqlMapClientTemplate().queryForList("detreqordfis.obtenerDatosReqDet", mapParametrosBusqueda);
	}

	
	//Fin staype 26/12/2019

	
}