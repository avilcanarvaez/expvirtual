package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;
import java.util.Map;

public class ReporteIndicadoresExpedienteVirtualBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// Datos de expediente
	private Integer cantRuc;
	private Integer cantExpedientes;
	private Integer cantExpedientesAutomaticos;
	private Integer cantExpedientesManuales;
	private Integer cantExpedientesAbiertos;
	private Integer cantExpedientesCerrados;
	private Integer codTipoExpediente;
	private Integer cantExpedientesConsultUsuIntAbiertos;
	private Integer cantExpedientesConsultUsuIntCerrados;
	private Integer cantExpedientesConsultContribAbiertos;
	private Integer cantExpedientesConsultContribCerrados;

	private Map<String, Map<String, Object>> mapDocs;

	public Map<String, Map<String, Object>> getMapDocs() {
		return mapDocs;
	}

	public void setMapDocs(Map<String, Map<String, Object>> mapDocs) {
		this.mapDocs = mapDocs;
	}

	public Integer getCantRuc() {
		return cantRuc;
	}

	public void setCantRuc(Integer cantRuc) {
		this.cantRuc = cantRuc;
	}

	public Integer getCantExpedientes() {
		return cantExpedientes;
	}

	public void setCantExpedientes(Integer cantExpedientes) {
		this.cantExpedientes = cantExpedientes;
	}

	public Integer getCantExpedientesAutomaticos() {
		return cantExpedientesAutomaticos;
	}

	public void setCantExpedientesAutomaticos(Integer cantExpedientesAutomaticos) {
		this.cantExpedientesAutomaticos = cantExpedientesAutomaticos;
	}

	public Integer getCantExpedientesManuales() {
		return cantExpedientesManuales;
	}

	public void setCantExpedientesManuales(Integer cantExpedientesManuales) {
		this.cantExpedientesManuales = cantExpedientesManuales;
	}

	public Integer getCantExpedientesAbiertos() {
		return cantExpedientesAbiertos;
	}

	public void setCantExpedientesAbiertos(Integer cantExpedientesAbiertos) {
		this.cantExpedientesAbiertos = cantExpedientesAbiertos;
	}

	public Integer getCantExpedientesCerrados() {
		return cantExpedientesCerrados;
	}

	public void setCantExpedientesCerrados(Integer cantExpedientesCerrados) {
		this.cantExpedientesCerrados = cantExpedientesCerrados;
	}

	public Integer getCodTipoExpediente() {
		return codTipoExpediente;
	}

	public void setCodTipoExpediente(Integer codTipoExpediente) {
		this.codTipoExpediente = codTipoExpediente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getCantExpedientesConsultUsuIntAbiertos() {
		return cantExpedientesConsultUsuIntAbiertos;
	}

	public void setCantExpedientesConsultUsuIntAbiertos(Integer cantExpedientesConsultUsuIntAbiertos) {
		this.cantExpedientesConsultUsuIntAbiertos = cantExpedientesConsultUsuIntAbiertos;
	}

	public Integer getCantExpedientesConsultUsuIntCerrados() {
		return cantExpedientesConsultUsuIntCerrados;
	}

	public void setCantExpedientesConsultUsuIntCerrados(Integer cantExpedientesConsultUsuIntCerrados) {
		this.cantExpedientesConsultUsuIntCerrados = cantExpedientesConsultUsuIntCerrados;
	}

	public Integer getCantExpedientesConsultContribAbiertos() {
		return cantExpedientesConsultContribAbiertos;
	}

	public void setCantExpedientesConsultContribAbiertos(Integer cantExpedientesConsultContribAbiertos) {
		this.cantExpedientesConsultContribAbiertos = cantExpedientesConsultContribAbiertos;
	}

	public Integer getCantExpedientesConsultContribCerrados() {
		return cantExpedientesConsultContribCerrados;
	}

	public void setCantExpedientesConsultContribCerrados(Integer cantExpedientesConsultContribCerrados) {
		this.cantExpedientesConsultContribCerrados = cantExpedientesConsultContribCerrados;
	}

}
