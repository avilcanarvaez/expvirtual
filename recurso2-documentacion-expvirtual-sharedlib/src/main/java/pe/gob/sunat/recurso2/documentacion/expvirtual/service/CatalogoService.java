package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.List;
import java.util.Map;

public interface CatalogoService {
	
	public Map<String, Object> obtenerCatalogo (Map<String, Object> parametros) throws Exception;
	//Inicio [nchavez 26/05/2015]
	public Map<String, Object> obtenerCatalogoConDetalles(Map<String, Object> parametros) throws Exception;
	//Fin [nchavez 26/05/2015]
	public List<String> listaTipDocCierreByTipExp(Map<String, Object> parametros) throws Exception;//[oachahuic][PAS20165E210400270]
	public List<String> listaTipDocNotif(Map<String, Object> parametros) throws Exception;//[oachahuic][PAS20165E210400270]
}