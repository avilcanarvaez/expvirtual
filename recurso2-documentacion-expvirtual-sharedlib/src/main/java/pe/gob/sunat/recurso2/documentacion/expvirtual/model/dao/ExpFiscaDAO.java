package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.Map;

public interface ExpFiscaDAO {
	public Map<String, Object> spFiValidarExpeOrigen(Map<String, Object> parametros);
	public Map<String, Object> spFivalidarDocExpediente(Map<String, Object> parametros);
    //PAS20191U210500144 Inicio 400101 RF04 PSALDARRIAGA
	public Map<String, Object> spFiverificarCierreExpediente(Map<String, Object> parametros);
	//PAS20191U210500144 Fin 400101 RF04 PSALDARRIAGA
}
