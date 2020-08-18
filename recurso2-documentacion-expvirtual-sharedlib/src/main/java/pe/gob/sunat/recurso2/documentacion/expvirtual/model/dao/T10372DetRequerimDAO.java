package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;


import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10372DetRequerimBean;

public interface T10372DetRequerimDAO {
	
	//Inicio staype 26/12/2019 [PAS20191U210500291]
	public  void insertarManualDetReq(T10372DetRequerimBean parametros);
	//Fin staype 26/12/2019
	
	//[PAS20191U210500291]: JMC - INI
	public List<T10372DetRequerimBean> listarItems(Map<String, Object> mapParametrosBusqueda);
	
	public void update (Map<String, Object> parametros);
	//[PAS20191U210500291]: JMC - FIN
}