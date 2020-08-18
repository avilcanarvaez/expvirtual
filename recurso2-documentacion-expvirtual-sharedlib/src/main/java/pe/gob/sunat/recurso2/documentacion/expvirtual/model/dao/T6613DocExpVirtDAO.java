package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;

public interface T6613DocExpVirtDAO {
	
	public void insertar(Map<String, Object> parametros);
	
	public T6613DocExpVirtBean obtenerDocOrigen(Map<String, Object> parametros);
	
	// [ATORRESCH 2017/02/21] METODO CUENTA LA RELACION DE DOCUMENTO INTERNO
	public Integer contarDocumentosRelacion(Map<String, Object> parametros);
	
	public List<T6613DocExpVirtBean> listar(Map<String, Object> parametros);
	
	public List<T6613DocExpVirtBean> listarTipoDocPorExpe(Map<String, Object> parametros);
	
	public List<Map<String, Object>> listarDocumentosPorExp(Map<String, Object> parametros);
	
	public void insertarManual(Map<String, Object> mapDatos);
	
	public List<T6613DocExpVirtBean> listarDocEstadosExp(Map<String, Object> parametros);
	
	public List<T6613DocExpVirtBean> listarDocumentosPorRequerim(Map<String, Object> parametros);	
	
	public List<T6613DocExpVirtBean> listarDocVisiblesContribuyente(Map<String, Object> parametros);
	
	public List<T6613DocExpVirtBean> listarDocumentosAsociados(Map<String, Object> parametros);
	
	public void update(T6613DocExpVirtBean t6613Bean);
	
	// [ATORRESCH 2017/02/21] METODO QUE ELIMINA UN DOCUMENTO INTERNO ACTUALIZA Y PONE ESTADO 02
	public void Eliminar(Map<String, Object> parametros);
	
	public List<Map<String, Object>> listarDocumentosAcumulados(Map<String, Object> parametros);
	
	public List<T6613DocExpVirtBean> listarDocVisiblesContribuyenteAcumulados(Map<String, Object> parametros);
	
	public List<Map<String, Object>> listarDocExpTrab(Map<String, Object> parametros);
	
	// [eaguilar 26/05/2016] metodo que adjunta un rc de separación
	public void desasociarAcu(Map<String, Object> parametros);
	
	// [jjurado 26/05/2016] obtiene los documentos válidos para el cierre del expediente virtual
	public T6613DocExpVirtBean obtenerDocumentoCierre(Map<String, Object> parametroBusqueda);
	// gangles 16/09/2016 Se obtien el documento de cierre del exp virtual
	public T6613DocExpVirtBean obtenerDatosDocumentoCierre(Map<String, Object> parametroBusqueda);
	//[JEFFIO 03/03/2017]
	
	public Integer obtenerNumDocumentoXExpediente(Map<String, Object> ParametrosBusqueda);
	
	// [ATORRESCH 2017/02/21] METODO CUENTA LA RELACION DE TIPO DE DOCUMENTO
	public Integer contarTipoDocumentosRelacion(Map<String, Object> parametros);
	
	public Integer countDocReaperByNumExpVir(String numExpVir);
	
	//[PAS20191U210500144] - INICIO
	public T6613DocExpVirtBean obtenerDocumentoInterno(Map<String, Object> parametros);
	//[PAS20191U210500144] - FIN	
	
	//Inicio staype 26/12/2019 [PAS20191U210500291] verificar que el numero de requerimiento este asociado al exped.
	public T6613DocExpVirtBean obtenerReqExpediente(Map<String, Object> mapParametrosBusqueda);
	//Fin staype 26/12/2019 
	
	//obtener 
	public Map<String, Object> obtenerFecNotif(Map<String, Object> mapParametrosBusqueda);
	
	public List<T6613DocExpVirtBean> listarDocumentosDeEscritos(Map<String, Object> mapParametrosBusqueda);//[PAS20201U210500029] - HHC
	public List<T6613DocExpVirtBean> listDocPorNumExpVir(Map<String, Object> parametros);
	
	// Inicio - [avilcan]
	public List<T6613DocExpVirtBean> obtenerDocumentosExp(Map<String, Object> parametros);
	// Fin - [avilcan]
	
}
