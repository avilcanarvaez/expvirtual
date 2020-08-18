package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6626seguirepimpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6626seguirepimpDAO;

public class SqlMapT6626seguirepimpDAO extends SqlMapClientDaoSupport implements T6626seguirepimpDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public T6626seguirepimpBean insertar(Map<String, Object> parametros) {
		return (T6626seguirepimpBean)getSqlMapClientTemplate().insert("T6626seguirepimp.insertar", parametros);
		
	}
}