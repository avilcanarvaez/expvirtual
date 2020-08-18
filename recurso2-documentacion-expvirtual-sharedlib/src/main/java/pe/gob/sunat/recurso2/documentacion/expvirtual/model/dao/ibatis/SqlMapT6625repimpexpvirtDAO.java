package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6625repimpexpvirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6625repimpexpvirtDAO;

public class SqlMapT6625repimpexpvirtDAO extends SqlMapClientDaoSupport implements T6625repimpexpvirtDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public T6625repimpexpvirtBean obtenerCodigoRepresentacionImpresa(Map<String, Object> parametros) {
		
		return (T6625repimpexpvirtBean)getSqlMapClientTemplate().queryForObject("T6625repimpexpvirt.obtenerCodigoRepresentacionImpresa", parametros);
		
	}

	@Override
	public void grabarRepresentacionImpresa(T6625repimpexpvirtBean t6625repimpexpvirtBean) throws Exception {

		getSqlMapClientTemplate().insert("T6625repimpexpvirt.insertar", t6625repimpexpvirtBean);
		
	}
}