package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DetReqOrdFisBean;
//import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6620RequerimBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReqOrdFisBean;

public interface DetReqOrdFisDAO {
	
	//staype 26/12/2019 [PAS20191U210500291]
	public List<DetReqOrdFisBean> obtenerDatosReqDet(Map<String, Object> mapParametrosBusqueda);
	
	
}