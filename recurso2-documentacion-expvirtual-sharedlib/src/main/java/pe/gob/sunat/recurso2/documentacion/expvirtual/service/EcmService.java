package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;


public interface EcmService {
	public String guardarDocumentoECM(Map<String, Object> parametros) throws Exception;
	public ResponseEntity<byte[]> descargarDocumentoECM(Map<String, Object> parametros) throws Exception;
}