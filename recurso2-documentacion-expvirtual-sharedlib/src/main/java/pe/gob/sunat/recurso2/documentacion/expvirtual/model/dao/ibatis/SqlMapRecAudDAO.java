package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.Map;

import pe.gob.sunat.framework.spring.util.dao.SqlMapDAOBase;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.RecAudDAO;

public class SqlMapRecAudDAO extends SqlMapDAOBase implements RecAudDAO {

	@Override
	public String obtenerCodSupervisor(Map<String, Object> parametros) {
		
		return (String) getSqlMapClientTemplate().queryForObject("recAud.obtenerCodSup", parametros);
	}


}
