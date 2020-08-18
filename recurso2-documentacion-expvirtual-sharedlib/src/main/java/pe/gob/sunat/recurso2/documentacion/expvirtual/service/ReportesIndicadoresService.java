package pe.gob.sunat.recurso2.documentacion.expvirtual.service;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedienteVirtualBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedienteVirtualDependBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedienteVirtualFechaGenBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedientesGeneradosCobranzaAcumDependBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedientesPorResponsableBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedientesVirtualesDocumentosPorDependenciaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedientesVirtualesGeneradosPorDependConsultBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresDocumentosExpedientesPorDepBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresRepresImprPorDepBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresRepresImprPorExpdteYDependenciaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresRepresImprPorFechaBean;

public interface ReportesIndicadoresService {

	public List<ReporteIndicadoresExpedienteVirtualDependBean> reporteIndicadorDepedencias(Map<String, Object> mapParametrosBusqueda) throws Exception;

	public List<ReporteIndicadoresExpedienteVirtualFechaGenBean> reporteIndicadorFechasGeneracion(Map<String, Object> mapParametrosBusqueda) throws Exception;

	public List<ReporteIndicadoresExpedienteVirtualDependBean> reporteIndicadorDepedenciasConsultXUsuIntYContrib(Map<String, Object> mapParametrosBusqueda) throws Exception;

	public List<ReporteIndicadoresExpedienteVirtualFechaGenBean> reporteIndicadorFechasGeneracionConsultXUsuIntYContrib(Map<String, Object> mapParametrosBusqueda) throws Exception;

	public List<ReporteIndicadoresExpedientesVirtualesGeneradosPorDependConsultBean> reporteExpdteGenerPorDependConsultados(Map<String, Object> mapParametrosBusqueda) throws Exception;

	public List<ReporteIndicadoresExpedientesGeneradosCobranzaAcumDependBean> reporteExpdtesCobranzaAcumPorDependencia(Map<String, Object> mapParametrosBusqueda) throws Exception;

	public List<ReporteIndicadoresExpedientesVirtualesDocumentosPorDependenciaBean> reporteCantidadDocumentosPorDependencia(Map<String, Object> mapParametrosBusqueda) throws Exception;

	public List<Map<String, Object>> cantDocsPorDepedencias(Map<String, Object> mapParametrosBusqueda) throws Exception;

	public List<Map<String, Object>> cantDocsPorFechaGeneracion(Map<String, Object> mapParametrosBusqueda) throws Exception;
	
	public List<Map<String, Object>> cantGrupoDocsPorDepedencias(Map<String, Object> mapParametrosBusqueda) throws Exception;
	
	public List<Map<String, Object>> cantDocsPorTipo(Map<String, Object> mapParametrosBusqueda) throws Exception;
	
	public List<Map<String, Object>> cantGrupoDocsPorFecha(Map<String, Object> mapParametrosBusqueda) throws Exception;

	public List<ReporteIndicadoresExpedientesPorResponsableBean> reporteRptExpGeneradosPorResponsables(Map<String, Object> mapParametrosBusqueda) throws Exception;

	public List<ReporteIndicadoresDocumentosExpedientesPorDepBean> reporteRptCantDocumentosPorExpDependencia(Map<String, Object> mapParametrosBusqueda, Map<String, Object> mapaDependencias) throws Exception;

	public List<ReporteIndicadoresRepresImprPorDepBean> reporteIndicadorRepresImprPorDepend(Map<String, Object> mapParametrosBusqueda) throws Exception;

	public List<ReporteIndicadoresRepresImprPorFechaBean> reporteIndicadorRepresImprPorFecha(Map<String, Object> mapParametrosBusqueda) throws Exception;

	public List<ReporteIndicadoresRepresImprPorExpdteYDependenciaBean> reporteIndicadorPorExpdteYDependencia(Map<String, Object> mapParametrosBusqueda) throws Exception;



}
