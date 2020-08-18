package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6615ObsExpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T6615ObsExpDAO;

public class SqlMapT6615ObsExpDAO  extends SqlMapClientDaoSupport implements T6615ObsExpDAO {
	@Override
	public void insertar(Map<String, Object> parametros) {
		T6615ObsExpBean t6615ObsExpBean = (T6615ObsExpBean) parametros.get("t6615ObsExpBean");
		//new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).insert("T6615ObsExp.insertar", t6615ObsExpBean);
		getSqlMapClientTemplate().insert("T6615ObsExp.insertar", t6615ObsExpBean);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T6615ObsExpBean> listarObservaciones(Map<String, Object> mapParametrosBusqueda) {
		return getSqlMapClientTemplate().queryForList("T6615ObsExp.listar", mapParametrosBusqueda);
	}
}
