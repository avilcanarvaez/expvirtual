package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.RepExpTrabBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10461SolDesBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6613DocExpVirtBean;


public interface ExpedienteTrabReporteService {
	public String generarReporte(String numPedido, String codUsuario, RepExpTrabBean repExpTrabBean) throws Exception;
	public void generarReporteRUC(List<RepExpTrabBean> listaRepExpTrabBeans, String numPedido, String codUsuario);
	public void generarReporteMasivo(List<String> listaNumPedido) throws Exception;
	public List<RepExpTrabBean> obtenerDatosGeneraRep(String numPedido) throws Exception;
	public List<String> listarNumPedidoBatch() throws Exception;
	public void actualizarPedido(Map<String, Object> parametros) throws Exception;
	public byte[] generarPDF(Map<String, Object> parametros) throws Exception;
	public byte[] obtenerPrevisualizacionPlantilla(String parametroJSON) throws Exception;
	
	//PAS20201U210500029 - HHC INICIO
	public List<T10461SolDesBean> listarNumSolicitudBatch() throws Exception; 
	public void generarSolicitudDesMasivo(List<T10461SolDesBean>  listaNumSolicitudDes) throws Exception; 
	public String generarArchivo(String numPedido,String nombrePersonaRegistro,List<T6613DocExpVirtBean> listDocExpVir, String numExpOrigen, Date fechaGenini) throws Exception;
	public List<T10461SolDesBean> listarNumSolicitudEliminarBatch() throws Exception;
	public void eliminarSolicitudDesMasivo(List<T10461SolDesBean>  listaNumSolicitudDes) throws Exception;
	public void actualizarSolicitud(Map<String, Object> parametros) throws Exception ;
	//PAS20201U210500029 - HHC FIN
}