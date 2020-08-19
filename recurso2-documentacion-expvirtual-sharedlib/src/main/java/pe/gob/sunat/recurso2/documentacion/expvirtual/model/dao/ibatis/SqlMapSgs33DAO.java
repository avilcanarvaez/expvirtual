package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.Sgs33DAO;
//Inicio [nchavez 26/05/2015]
public class SqlMapSgs33DAO extends SqlMapClientDaoSupport implements Sgs33DAO {

	 @Override
	public Integer validarFechaDiaHabil(Map<String, Object> parametros) {
		//13 JUL:
		//ANTES:
		//return (Integer) new SqlMapClientTemplate((DataSource)parametros.get("origenDatos"), getSqlMapClientTemplate().getSqlMapClient()).queryForObject("sgs33.validarDiaHabil", parametros, Integer.class);
		//return (Integer) this.getSqlMapClientTemplate().queryForObject("sgs33.validarDiaHabil", parametros, Integer.class);
		 
		//DESPUES:
		return (Integer) this.getSqlMapClientTemplate().queryForObject("sgs33.validarDiaHabil", parametros, Integer.class);
	}
	
}
//Fin [nchavez 26/05/2015] 