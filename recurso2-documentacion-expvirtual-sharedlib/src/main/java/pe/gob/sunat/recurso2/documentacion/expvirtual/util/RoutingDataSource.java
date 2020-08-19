package pe.gob.sunat.recurso2.documentacion.expvirtual.util;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import pe.gob.sunat.framework.spring.util.jdbc.datasource.lookup.DataSourceContextHolder;

public class RoutingDataSource extends AbstractRoutingDataSource{
	
	//SIE 13 JUL
	
	protected final Log logger = LogFactory.getLog(getClass());
	protected Object determineCurrentLookupKey() {
		String key = DataSourceContextHolder.getKeyDataSource();
		if (logger.isDebugEnabled()) logger.debug("valor del key del datasource devuelto " + key);
		return key;
	}
	
	public DataSource getDataSource(){
		return determineTargetDataSource();
	}
	
}