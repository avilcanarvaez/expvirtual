package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.Map;

// Inicio [jquispe 30/05/2016] Clase para generar el archivo pdf con indice de expediente electronico.
public interface IndiceExpedienteVirtualService {

    /**
     * Metodo encargado de generar el archivo PDF con Indice de Expediente Electronico.
     * 
     * @param parametros :Parametros para generar el archivo PDF con Indice de Expediente Electronico.
     * @return Arreglo de bytes del archivo PDF.
     */
	public byte[] generarIndiceArchivoPdf(Map<String, Object> parametros) throws Exception;

}
// Fin [jquispe 30/05/2016]