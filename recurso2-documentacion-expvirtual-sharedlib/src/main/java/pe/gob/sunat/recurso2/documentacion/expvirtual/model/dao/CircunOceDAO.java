package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;

public interface CircunOceDAO {

	public DdpBean getAgenteHabilitado(Map<String, Object> params);
}
