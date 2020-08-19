package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T7731DocAdiExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T7731DocAdiExpVirtDAO;

//Inicio [jquispe 01/06/2016]
public class SqlMapT7731DocAdiExpVirtDAO extends SqlMapClientDaoSupport implements T7731DocAdiExpVirtDAO{

	@Override
	public void insertar(Map<String, Object> parametros) {
		
		T7731DocAdiExpVirtBean t7731DocAdiExpVirtBean = (T7731DocAdiExpVirtBean)parametros.get("t7731DocAdiExpVirtBean");
		//13 JUL:
		//ANTES:
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).insert("T7731DocAdiExpVirt.insertar", t7731DocAdiExpVirtBean);
		
		//DESPUES:
		this.getSqlMapClientTemplate().insert("T7731DocAdiExpVirt.insertar", t7731DocAdiExpVirtBean);
	}
}
//Fin [jquispe 01/06/2016]