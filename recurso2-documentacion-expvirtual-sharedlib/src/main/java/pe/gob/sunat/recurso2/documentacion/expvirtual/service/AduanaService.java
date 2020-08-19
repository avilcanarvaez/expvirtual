package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.DdpBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;

public interface AduanaService {

	public Map<String, Object> verificarUoAduana(String codUO) throws Exception;
	public DdpBean obtenerAgenteHabilitado(String numRuc, String codDep) throws Exception;
	public String validarNumDocSolDAM(String numExpVir, String numDam, String numSol) throws Exception;
	public String validarNumDocReqDAM(String numExpVir, String numDam, String numReq) throws Exception;
	public String validarNumReqDAM(String numExpVir, String numDam, String numReq) throws Exception;
	public T6613DocExpVirtBean buscarDocNotificado(String numExpVir, String numDoc) throws Exception;
	public T6613DocExpVirtBean buscarDocSolRectifDAM(String numExpVir, String numDoc) throws Exception;
	public Map<String, Object> obtenerDependencias() throws Exception;
	
}
