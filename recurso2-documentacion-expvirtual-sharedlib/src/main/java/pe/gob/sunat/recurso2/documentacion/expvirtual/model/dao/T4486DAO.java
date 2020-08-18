/*
 
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pe.gob.sunat.framework.core.bean.MensajeBean;
import pe.gob.sunat.framework.core.dao.DAOAbstract;
import pe.gob.sunat.framework.core.dao.DAOException;
import pe.gob.sunat.framework.util.date.FechaBean;
/**
 * DAO de la tabla ddp de la base de datos recauda
 * 
 * @author JVALDEZ
 *
 */
public class T4486DAO extends DAOAbstract {
	private DataSource datasource;

	private Log log = LogFactory.getLog(getClass());

	private static final String SELECT1_SENTENCE  = "SELECT num_serie,cod_app,fec_inivig,"
		.concat("fec_finvig,ind_revocado,ind_del,cod_pin,")	
		.concat("fec_modif,cod_usumodif,fec_reg,cod_usureg ")
		.concat("FROM t4486PadronCertDig ")
		.concat("WHERE cod_app = ? ")
		.concat("AND fec_inivig <= ? ") 
		.concat("AND fec_finvig >= ? ")
	    .concat("AND ind_revocado='0'");

	public T4486DAO() {
		super();
	}
	/**
	 * 
	 * Este constructor del DAO dicierne como crear el datasource
	 * dependiendo del tipo de objeto que recibe. Esto nos ayuda a
	 * mejorar la invocacion del dao.
	 * 
	 * @param datasource Object
	 */
	public T4486DAO(Object datasource) {
		if (datasource instanceof DataSource)
			this.datasource = (DataSource)datasource;
		else if (datasource instanceof String)
			this.datasource = getDataSource((String)datasource);
		else
			throw new DAOException(this, "Datasource no valido");
	}


	public Map findByCodAplic(Map params)throws DAOException 
	{
		Map certificado= null;
		try{
			Object[] o = null;
			FechaBean fecha=new FechaBean();
			String  query = this.SELECT1_SENTENCE;
			if (log.isDebugEnabled())log.debug("query"+query); 
			o = new Object[] {params.get("codApli").toString(),fecha.getTimestamp(),fecha.getTimestamp()};
			certificado = executeQueryUniqueResult( datasource, query.toString(), o);
		} catch(Exception e){
			if (log.isDebugEnabled())log.error("*******ERROR*******", e);
			MensajeBean beanM = new MensajeBean();
			beanM.setError(true);
			beanM.setMensajeerror("Error al buscar el Certificado por codigo de aplicacion");
			beanM.setMensajesol("Por favor intente nuevamente ejecutar la opci�n, en caso de continuar con el problema, comuniquese con nuestro webmaster.");
			throw new DAOException(this, beanM);
		} finally{
		}
		return certificado;
	}

	

}
