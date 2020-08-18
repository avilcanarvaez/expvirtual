package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import pe.gob.sunat.framework.spring.util.dao.SqlMapDAOBase;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.T12UOrga;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.T12UOrgaDAO;

public class SqlMapT12UOrgaDAO extends SqlMapDAOBase  implements T12UOrgaDAO{

	@SuppressWarnings("deprecation")
	@Override
	public T12UOrga getUnidadOrganica(String codUnidadOrganica) {
		return (T12UOrga) getSqlMapClientTemplate().queryForObject("t12uorga.buscarPorCodigo",codUnidadOrganica);
	}

}
 