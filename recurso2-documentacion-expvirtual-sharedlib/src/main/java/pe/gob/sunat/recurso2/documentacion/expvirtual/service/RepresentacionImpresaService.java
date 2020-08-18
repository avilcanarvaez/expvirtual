package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6625repimpexpvirtBean;

public interface RepresentacionImpresaService {
	
	public T6625repimpexpvirtBean buscarExpedienteRepreImpr(Map<String, Object> parametros) throws Exception;

	public void grabarRepresentacionImpresa(T6625repimpexpvirtBean t6625repimpexpvirtBean) throws Exception;
	
	
}