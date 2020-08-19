package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6625repimpexpvirtBean;


public interface T6625repimpexpvirtDAO {

	public T6625repimpexpvirtBean obtenerCodigoRepresentacionImpresa(Map<String, Object> parametros);

	public void grabarRepresentacionImpresa(T6625repimpexpvirtBean t6625repimpexpvirtBean) throws Exception;
	
}