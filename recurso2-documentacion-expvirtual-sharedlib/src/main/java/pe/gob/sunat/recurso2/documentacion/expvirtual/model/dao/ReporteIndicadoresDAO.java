package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

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


public interface ReporteIndicadoresDAO {
	public List<ReporteIndicadoresExpedienteVirtualDependBean> reportePorDepedencias(Map<String, Object> parametros);

	public List<ReporteIndicadoresExpedienteVirtualFechaGenBean> reportePorFechasGeneracion(Map<String, Object> mapParametrosBusqueda);

	public List<ReporteIndicadoresExpedienteVirtualDependBean> reportePorDepedenciasConsultXUsuIntYContrib(Map<String, Object> parametros);

	public List<ReporteIndicadoresExpedienteVirtualFechaGenBean> reportePorFechasGeneracionConsultXUsuIntYContrib(Map<String, Object> parametros);

	public List<ReporteIndicadoresExpedientesVirtualesGeneradosPorDependConsultBean> reporteExpdteGenerPorDependConsultados(Map<String, Object> parametros);

	public List<ReporteIndicadoresExpedientesGeneradosCobranzaAcumDependBean> reporteExpdtesCobranzaAcumPorDependencia(Map<String, Object> parametros);

	public List<ReporteIndicadoresExpedientesVirtualesDocumentosPorDependenciaBean> reporteCantidadDocumentosPorDependencia(Map<String, Object> parametros);

	public List<Map<String, Object>> cantDocsPorDepedencias(Map<String, Object> parametros);

	public List<Map<String, Object>> cantDocsPorFechaGeneracion(Map<String, Object> mapParametrosBusqueda);
	
	public List<Map<String, Object>> cantGrupoDocsPorDepedencias(Map<String, Object> parametros);

	public List<Map<String, Object>> cantDocsPorTipo(Map<String, Object> mapParametrosBusqueda);
	
	public List<Map<String, Object>> cantGrupoDocsPorFecha(Map<String, Object> parametros);
	
	public List<ReporteIndicadoresExpedientesPorResponsableBean> reporteRptExpGeneradosPorResponsables(Map<String, Object> parametros);

	public List<ReporteIndicadoresDocumentosExpedientesPorDepBean> reporteRptCantDocumentosPorExpDependencia(Map<String, Object> parametros);

	public List<ReporteIndicadoresRepresImprPorDepBean> reporteIndicadorRepresImprPorDepend(Map<String, Object> parametros);

	public List<ReporteIndicadoresRepresImprPorFechaBean> reporteIndicadorRepresImprPorFecha(Map<String, Object> parametros);

	public List<ReporteIndicadoresRepresImprPorExpdteYDependenciaBean> reporteIndicadorRepresImprPorExpdteYDependenciaBean(Map<String, Object> parametros);

}
