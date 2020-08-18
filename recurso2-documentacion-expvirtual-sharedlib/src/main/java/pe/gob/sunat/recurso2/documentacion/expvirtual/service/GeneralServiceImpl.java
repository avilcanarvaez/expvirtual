package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;

import org.springframework.jndi.JndiTemplate;

import pe.gob.sunat.recurso2.documentacion.expvirtual.util.DataSourceConstantes;

public class GeneralServiceImpl implements GeneralService {
	
	private static final Log log = LogFactory.getLog(GeneralServiceImpl.class);
	
	@Override
	public DataSource obtenerOrigenDatos(Map<String, Object> parametros) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - GeneralServiceImpl.obtenerOrigenDatos");
		
		DataSource origenDatos = null;
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - GeneralServiceImpl.obtenerOrigenDatos");
			
			String indCentral = parametros.get("indCentral").toString();
			
			String oriDatos = "";
			
			if (DataSourceConstantes.IND_BASEDATOS_CENTRAL.equals(indCentral)) {
				
				oriDatos = parametros.get("oriDatos").toString();
				
			} else if (DataSourceConstantes.IND_BASEDATOS_NO_CENTRAL.equals(indCentral)) {
				
				String preDatos = parametros.get("preDatos").toString();
				String sufDatos = parametros.get("sufDatos").toString();
				
				oriDatos = preDatos + sufDatos;
				
			}
			
			String jdbcDatos = DataSourceConstantes.PREFIJO_JDBC + oriDatos;
			if (log.isDebugEnabled()) log.debug("GeneralServiceImpl.obtenerOrigenDatos jdbcDatos: "+jdbcDatos);
			origenDatos = (DataSource)(new JndiTemplate()).lookup(jdbcDatos);
			
		} catch (NamingException ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - GeneralServiceImpl.obtenerOrigenDatos");
			
			log.error(ex, ex);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - GeneralServiceImpl.obtenerOrigenDatos");
			
			NDC.pop();
			NDC.remove();
			
		}
		if (log.isDebugEnabled()) log.debug("Error - GeneralServiceImpl.obtenerOrigenDatos - Origen de Datos: "+origenDatos.toString());
		return origenDatos;
		
	}
	
	
	/**
     * 13 JUL: Metodo que retorna tanto el nombre del pool como el objeto datasource distribuido
     * Recuperar el nombre del pool con el KEY "nombrePool", ejemplo:  mapaResultado.get("nombrePool").toString()
     */
	@Override
	public Map<String, Object> obtenerOrigenDatosMap(Map<String, Object> parametros) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - GeneralServiceImpl.obtenerOrigenDatos");
		Map<String, Object> resultado = null;
		DataSource origenDatos = null;
		try {
			resultado = new HashMap<String, Object>(); //mapa a retornar
			if (log.isDebugEnabled()) log.debug("Procesa - GeneralServiceImpl.obtenerOrigenDatos");
			String indCentral = parametros.get("indCentral").toString();
			String oriDatos = "";
			if (DataSourceConstantes.IND_BASEDATOS_CENTRAL.equals(indCentral)) {
				oriDatos = parametros.get("oriDatos").toString();
			} else if (DataSourceConstantes.IND_BASEDATOS_NO_CENTRAL.equals(indCentral)) {
				String preDatos = parametros.get("preDatos").toString();
				String sufDatos = parametros.get("sufDatos").toString();
				oriDatos = preDatos + sufDatos;
			}
			resultado.put("nombrePool", oriDatos); //nombre del pool
			String jdbcDatos = DataSourceConstantes.PREFIJO_JDBC + oriDatos;
			if (log.isDebugEnabled()) log.debug("GeneralServiceImpl.obtenerOrigenDatos jdbcDatos: "+jdbcDatos);
			origenDatos = (DataSource)(new JndiTemplate()).lookup(jdbcDatos);
			resultado.put("oDataSource", oriDatos); //el objeto DataSource
		} catch (NamingException ex) {
			if (log.isDebugEnabled()) log.debug("Error - GeneralServiceImpl.obtenerOrigenDatos");
			log.error(ex, ex);
		} finally {
			if (log.isDebugEnabled()) log.debug("Final - GeneralServiceImpl.obtenerOrigenDatos");
			NDC.pop();
			NDC.remove();
		}
		if (log.isDebugEnabled()) log.debug("Error - GeneralServiceImpl.obtenerOrigenDatos - Origen de Datos: "+origenDatos.toString());
		//return origenDatos;
		return resultado;
	}
	
}