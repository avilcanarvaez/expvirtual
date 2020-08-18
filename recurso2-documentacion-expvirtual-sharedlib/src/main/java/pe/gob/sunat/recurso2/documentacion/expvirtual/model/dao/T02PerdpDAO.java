package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T02PerdpBean;

public interface T02PerdpDAO {
	
	public T02PerdpBean obtener (Map<String, Object> parametros);
	
	public List<T02PerdpBean> listar(Map<String, Object> parametros);
    
    //PAS20191U210500144 Inicio 400101 RF28 PSALDARRIAGA
	public List<T02PerdpBean> listarResponsablesDependencia(
			Map<String, Object> mapParamBusq);
	//PAS20191U210500144 Fin 400101 RF28 PSALDARRIAGA

	//[PAS20191U210500291]: JMC - INI
	public String obtenerUUOOSupervisor (Map<String, Object> parametros);
	//[PAS20191U210500291]: JMC - FIN
}