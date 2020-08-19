package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10461SolDesBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;

public interface ExpedienteVirtualService {
	
	public T6614ExpVirtualBean obtenerExpedienteVirtual(Map<String, Object> parametros) throws Exception;
	
	public String registrarExpedienteVirtual(Map<String, Object> parametros) throws Exception;
	
	public Map<String, Object> registrarDocumentosExpediente(Map<String, Object> parametros) throws Exception;
	
	public String registrarDocumentosExpAdjunto(Map<String, Object> parametros) throws Exception;
	
	public T6614ExpVirtualBean obtenerExpVirByNumExpOri(String numExpOri) throws Exception;
	
	public Map<String, Object> obtenerDatosDocumentoOrigen(Map<String, Object> parametros) throws Exception;
	
	public List<Map<String, Object>> obtenerDocumentosExpediente(Map<String, Object> parametros) throws Exception;
	// Inicio [jquispe 27/05/2016] Obtener numero total de expediente virtual de la consulta.
	public Integer obtenerNumExpedienteVirtual(Map<String, Object> mapParametrosBusqueda) throws Exception;
	// Fin [jquispe 27/05/2016]
	public List<T6614ExpVirtualBean> obtenerListaExpedienteVirtual(Map<String, Object> mapParametrosBusqueda) throws Exception;
	
	public List<T6614ExpVirtualBean> listarRegDocExpediente(Map<String, Object> mapParametrosBusqueda) throws Exception;
	
	public List<T6614ExpVirtualBean> listaExpedientePorRuc(Map<String, Object> mapParametrosBusqueda) throws Exception;
	
	public T6614ExpVirtualBean obtenerDatosExpediente(Map<String, Object> parametros) throws Exception;
	
	public List<T6613DocExpVirtBean> obtenerDocumentosEstadoExpediente(Map<String, Object> parametros) throws Exception;
	
	public List<T6613DocExpVirtBean> obtenerListaDocVisContribuyente(Map<String, Object> mapParametrosBusqueda) throws Exception;
	
	public List<Map<String, Object>> obtenerExpedientesVirtuales(Map<String, Object> parametros) throws Exception;
	
	public String cerrarExpedienteVirtual(Map<String, Object> parametros) throws Exception;

	public String adjuntarDocumentosRequerimiento(Map<String, Object> parametros) throws Exception;
	
	public List<T6613DocExpVirtBean> listarTipoDocPorExpe(Map<String, Object> mapParametrosBusqueda) throws Exception;
	
	public List<T6614ExpVirtualBean> obtenerListaExpedienteVirtualAcumulado(Map<String, Object> mapParametrosBusqueda) throws Exception;
	
	public List<Map<String, Object>> obtenerListaDocumentosAcumulado(Map<String, Object> parametros) throws Exception;
	
	public List<T6613DocExpVirtBean> obtenerListaDocVisContribuyenteAcumulado(Map<String, Object> mapParametrosBusqueda) throws Exception;
		
	
	public Integer obtenerIdDocumento(Map<String, Object> parametros) throws Exception;
	public Map<String, Object> actualizarDocumentoVisible(Map<String, Object> parametros) throws Exception;
	public Map<String, Object> reAbrirExpediente(Map<String, Object> parametros) throws Exception;
	public T6613DocExpVirtBean obtenerDatosDocumentoEstado(Map<String, Object> parametros) throws Exception;
	public List<String> listarNumExpAcumCOA (Map<String, Object> parametros) throws Exception;
	
	public Map<String, Object> actualizarDocumentoVisibleRelacionado(Map<String, Object> parametros) throws Exception;
	
	public void cerrarExpedienteVirtualAcum(List<Map<String, Object>> listaParametros) throws Exception;	
	public List<Map<String, Object>> listarNumExpVirtualAcumCierre(Map<String, Object> parametros) throws Exception;
	
	// Inicio [eaguilar 26/05/2016] 
	public boolean desasociarAcum(Map<String, Object> parametros,List<String> listaNumExpAcumCOA) throws Exception;
	// Fin [eaguilar 26/05/2016] 
	
	// Inicio [jjurado 26/05/2016]
	public byte[] verDatosExpedientePDF(Map<String, Object> parametros)throws Exception;
	public T6613DocExpVirtBean buscarDocumentoCierre(Map<String, Object> parametroBusqueda)throws Exception;
	// Fin [jjurado 26/05/2016]

	//28 JUN eaguilar
	public Map<String, Object> registrarSeguimientoAcumulados(Map<String, Object> parametros) throws Exception;

	//9 AGO eaguilar
	public Long generarCorrelativo(String codSeq) throws Exception;
	
	//16/09/2016 gangles
	
	public T6613DocExpVirtBean buscarDatosDocumentoCierre(Map<String, Object> parametroBusqueda)throws Exception;
	public List<T6613DocExpVirtBean> listarDocExp(Map<String, Object> parametroBusqueda)throws Exception;
	// inicio JEFFIO 03/03/2017
	public String ReaperturaExpVirtual (Map<String, Object> parametros,Map<String, Object> parametros2) throws Exception;
	
	public String EliminarExpVirtual (Map<String, Object> parametros) throws Exception;
	
	public T6613DocExpVirtBean buscarDatosDocumentos(Map<String, Object> parametro) throws Exception;
	
	public Integer NumDocRegisPorExpediente (Map<String, Object>parametrosBusqueda) throws Exception;
	
	public String RevertirCierreExpVirtual (Map<String, Object> parametros) throws Exception;
	//fin
	public boolean obviarDocCierreByTipExp(String codTipExp) throws Exception;
	public boolean expReAbiertoByTipExp(String codTipExp, String numExpVir) throws Exception;
	public boolean expAbiertoByTipExp(String codTipExp, String codEstExp) throws Exception;
	public boolean obviarOrigenAutoByTipExp(String codTipExp) throws Exception;
	
	//PAS20191U210500144 Inicio 400101 RF04 PSALDARRIAGA
	public T6614ExpVirtualBean verificarCierre(Map<String, Object> params) throws Exception;
	//PAS20191U210500144 Fin 400101 RF04 PSALDARRIAGA
	//INICIO LLRB //
	public List<Map<String, Object>> obtenerArchivosExpediente(Map<String, Object> parametros) throws Exception;
		
	public List<Map<String, Object>> listarArchivosAdj(Map<String, Object> parametroBusqueda)throws Exception;
		
	public List<Map<String, Object>> obtenerCopiasExpediente(Map<String, Object> parametros) throws Exception;
	
	public Map<String, Object> registrarDocumentosExpedienteFisca(Map<String, Object> parametros) throws Exception;
	
	public Integer obtenerNroCopia(Map<String, Object> parametros) throws Exception;
	
	public Integer obtenerNroExpediente(Map<String, Object> parametros) throws Exception;
	
	public Map<String, Object>  obtenerExpVirtual(Map<String, Object> parametros) throws Exception;
	
	public String registrarSolicitudExpVirt(Map<String, Object> parametros) throws Exception;
	
	public List<Map<String, Object>>  obtenerNumExpVirVinc(Map<String, Object> parametros) throws Exception;
	
	public Integer obtenerNroSolicitud(Map<String, Object> parametros) throws Exception;
	
	//FIN LLRB
	
	//inicio CVG
	public void vincularExpedienteVirtual(Map<String, Object> parametros) throws Exception;
	public void desvincularExpedienteVirtual(Map<String, Object> parametros) throws Exception;
	//fin CVG
	
	//PAS20201U210500029 - HHC INICIO
	public List<T6614ExpVirtualBean> obtenerListaExpedienteVirtualReq(Map<String, Object> mapParametrosBusqueda) throws Exception; 
	public List<T10461SolDesBean> obtenerListaSolicitudDesc(Map<String, Object> mapParametrosBusqueda) throws Exception; 
	//PAS20201U210500029 - HHC FIN
}