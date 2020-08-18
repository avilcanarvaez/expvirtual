package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.Map;

import javax.sql.DataSource;

public interface GeneralService {
	
	/**
	 * Método encargado de generar y obtener el valor del datasource necesitado (Central, Dependencia, Consulta, Transaccional)
	 * 
	 * @param parametros
	 * @return
	 */
	public DataSource obtenerOrigenDatos (Map<String, Object> parametros);
	public Map<String, Object> obtenerOrigenDatosMap (Map<String, Object> parametros); //13 JUL: METODO QUE RETORNA TANTO EL NOMBRE DEL POOL COMO EL OBJETO DATASOURCE DISTRIBUIDO
	
}