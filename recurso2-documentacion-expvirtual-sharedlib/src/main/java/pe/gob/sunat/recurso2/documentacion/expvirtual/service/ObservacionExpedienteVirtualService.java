package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6615ObsExpBean;

public interface ObservacionExpedienteVirtualService {
	
public String registrarObservacion(Map<String, Object> parametros) throws Exception;
	
public List<T6615ObsExpBean> obtenerObservacionesExpediente(Map<String, Object> mapParametrosBusqueda) throws Exception;

//Inicio [jquispe 05/07/2016] Obtiene la consulta de observaciones.
public List<T6615ObsExpBean> consultarObservacionesExpediente(String numeroExpedienteVirtual) throws Exception;
//Fin [jquispe 05/07/2016]
	

}
