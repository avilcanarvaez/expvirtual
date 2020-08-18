package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedienteVirtualDependBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedienteVirtualFechaGenBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedientesGeneradosCobranzaAcumDependBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedientesPorResponsableBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedientesVirtualesDocumentosPorDependenciaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresExpedientesVirtualesGeneradosPorDependConsultBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresRepresImprPorDepBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresRepresImprPorExpdteYDependenciaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresRepresImprPorFechaBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao.ReporteIndicadoresDAO;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.ReporteIndicadoresDocumentosExpedientesPorDepBean;

public class SqlMapReportesIndicadoresDAO extends SqlMapClientDaoSupport implements ReporteIndicadoresDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<ReporteIndicadoresExpedienteVirtualDependBean> reportePorDepedencias(Map<String, Object> parametros) {
		return (List<ReporteIndicadoresExpedienteVirtualDependBean>) getSqlMapClientTemplate().queryForList("reportesIndicadores.listasReportePorDependencia", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReporteIndicadoresExpedienteVirtualFechaGenBean> reportePorFechasGeneracion(Map<String, Object> parametros) {
		return (List<ReporteIndicadoresExpedienteVirtualFechaGenBean>) getSqlMapClientTemplate().queryForList("reportesIndicadores.listasReportePorFechasGeneracion", parametros);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ReporteIndicadoresExpedienteVirtualDependBean> reportePorDepedenciasConsultXUsuIntYContrib(Map<String, Object> parametros) {
		return (List<ReporteIndicadoresExpedienteVirtualDependBean>) getSqlMapClientTemplate().queryForList("reportesIndicadores.listasReportePorDependenciaSeguim", parametros);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReporteIndicadoresExpedienteVirtualFechaGenBean> reportePorFechasGeneracionConsultXUsuIntYContrib(Map<String, Object> parametros) {
		return (List<ReporteIndicadoresExpedienteVirtualFechaGenBean>) getSqlMapClientTemplate().queryForList("reportesIndicadores.listasReportePorFechasGeneracionSeguim", parametros);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ReporteIndicadoresExpedientesVirtualesGeneradosPorDependConsultBean> reporteExpdteGenerPorDependConsultados(Map<String, Object> parametros) {
		return (List<ReporteIndicadoresExpedientesVirtualesGeneradosPorDependConsultBean>) getSqlMapClientTemplate().queryForList("reportesIndicadores.listaReporteExpdteGenerPorDependConsultados", parametros);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ReporteIndicadoresExpedientesGeneradosCobranzaAcumDependBean> reporteExpdtesCobranzaAcumPorDependencia(Map<String, Object> parametros) {
		return (List<ReporteIndicadoresExpedientesGeneradosCobranzaAcumDependBean>) getSqlMapClientTemplate().queryForList("reportesIndicadores.listaReporteExpdtesCobranzaAcumPorDependencia", parametros);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ReporteIndicadoresExpedientesVirtualesDocumentosPorDependenciaBean> reporteCantidadDocumentosPorDependencia(Map<String, Object> parametros) {
		return (List<ReporteIndicadoresExpedientesVirtualesDocumentosPorDependenciaBean>) getSqlMapClientTemplate().queryForList("reportesIndicadores.listaReporteCantidadDocumentosPorDependencia", parametros);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> cantDocsPorDepedencias(Map<String, Object> parametros) {
		return (List<Map<String, Object>>) getSqlMapClientTemplate().queryForList("reportesIndicadores.listasCantDocPorDependencia", parametros);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> cantDocsPorFechaGeneracion(Map<String, Object> parametros) {
		return (List<Map<String, Object>>) getSqlMapClientTemplate().queryForList("reportesIndicadores.listasCantDocPorFechaGeneracion", parametros);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> cantGrupoDocsPorDepedencias(Map<String, Object> parametros) {
		return (List<Map<String, Object>>) getSqlMapClientTemplate().queryForList("reportesIndicadores.listasCantGrupoDocPorDependencia", parametros);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> cantDocsPorTipo(Map<String, Object> parametros) {
		return (List<Map<String, Object>>) getSqlMapClientTemplate().queryForList("reportesIndicadores.listaCantDocsPorTipo", parametros);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> cantGrupoDocsPorFecha(Map<String, Object> parametros) {
		return (List<Map<String, Object>>) getSqlMapClientTemplate().queryForList("reportesIndicadores.listasCantGrupoDocPorFechaGeneracion", parametros);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReporteIndicadoresExpedientesPorResponsableBean> reporteRptExpGeneradosPorResponsables(Map<String, Object> parametros) {
		return (List<ReporteIndicadoresExpedientesPorResponsableBean>) getSqlMapClientTemplate().queryForList("reportesIndicadores.listaReporteExpGeneradosPorResponsables", parametros);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<ReporteIndicadoresDocumentosExpedientesPorDepBean> reporteRptCantDocumentosPorExpDependencia(Map<String, Object> parametros) {
		return (List<ReporteIndicadoresDocumentosExpedientesPorDepBean>) getSqlMapClientTemplate().queryForList("reportesIndicadores.listaReporteCantDocumentosPorExpDependencia", parametros);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReporteIndicadoresRepresImprPorDepBean> reporteIndicadorRepresImprPorDepend(Map<String, Object> parametros){
		return (List<ReporteIndicadoresRepresImprPorDepBean>) getSqlMapClientTemplate().queryForList("reportesIndicadores.listaReporteCantRepresImprPorDependencia", parametros);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReporteIndicadoresRepresImprPorFechaBean> reporteIndicadorRepresImprPorFecha(Map<String, Object> parametros){
		return (List<ReporteIndicadoresRepresImprPorFechaBean>) getSqlMapClientTemplate().queryForList("reportesIndicadores.listaReporteCantRepresImprPorFecha", parametros);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReporteIndicadoresRepresImprPorExpdteYDependenciaBean> reporteIndicadorRepresImprPorExpdteYDependenciaBean(Map<String, Object> parametros){
		return (List<ReporteIndicadoresRepresImprPorExpdteYDependenciaBean>) getSqlMapClientTemplate().queryForList("reportesIndicadores.listaReporteCantRepresImprPorExpdteYDependencia", parametros);
	}
}
