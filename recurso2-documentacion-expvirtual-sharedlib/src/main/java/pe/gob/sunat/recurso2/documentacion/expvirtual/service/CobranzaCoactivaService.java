package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.List;
import java.util.Map;

public interface CobranzaCoactivaService {
	public List<Map<String, String>> obtenerExpedientesAcumulados(Map<String, Object> mapParamExp) throws Exception;

}
