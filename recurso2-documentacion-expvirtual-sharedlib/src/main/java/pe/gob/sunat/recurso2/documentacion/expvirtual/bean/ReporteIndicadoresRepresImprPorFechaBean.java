package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: nchavez
 * @date : 24/08/2016
 * @time : 10:35:40
 */
public class ReporteIndicadoresRepresImprPorFechaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date fecha;
	private Integer cantidadRuc;
	private Integer cantidadExpAbiertos;
	private Integer cantidadExpCerrados;
	private Integer cantidadExpAbiRepImpUsu;
	private Integer cantidadExpCerrRepImpUsu;
	private Integer cantidadExpAbiRepImpContrib;
	private Integer cantidadExpCerrRepImpContrib;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getCantidadRuc() {
		return cantidadRuc;
	}

	public void setCantidadRuc(Integer cantidadRuc) {
		this.cantidadRuc = cantidadRuc;
	}

	public Integer getCantidadExpAbiertos() {
		return cantidadExpAbiertos;
	}

	public void setCantidadExpAbiertos(Integer cantidadExpAbiertos) {
		this.cantidadExpAbiertos = cantidadExpAbiertos;
	}

	public Integer getCantidadExpCerrados() {
		return cantidadExpCerrados;
	}

	public void setCantidadExpCerrados(Integer cantidadExpCerrados) {
		this.cantidadExpCerrados = cantidadExpCerrados;
	}

	public Integer getCantidadExpAbiRepImpUsu() {
		return cantidadExpAbiRepImpUsu;
	}

	public void setCantidadExpAbiRepImpUsu(Integer cantidadExpAbiRepImpUsu) {
		this.cantidadExpAbiRepImpUsu = cantidadExpAbiRepImpUsu;
	}

	public Integer getCantidadExpCerrRepImpUsu() {
		return cantidadExpCerrRepImpUsu;
	}

	public void setCantidadExpCerrRepImpUsu(Integer cantidadExpCerrRepImpUsu) {
		this.cantidadExpCerrRepImpUsu = cantidadExpCerrRepImpUsu;
	}

	public Integer getCantidadExpAbiRepImpContrib() {
		return cantidadExpAbiRepImpContrib;
	}

	public void setCantidadExpAbiRepImpContrib(Integer cantidadExpAbiRepImpContrib) {
		this.cantidadExpAbiRepImpContrib = cantidadExpAbiRepImpContrib;
	}

	public Integer getCantidadExpCerrRepImpContrib() {
		return cantidadExpCerrRepImpContrib;
	}

	public void setCantidadExpCerrRepImpContrib(Integer cantidadExpCerrRepImpContrib) {
		this.cantidadExpCerrRepImpContrib = cantidadExpCerrRepImpContrib;
	}
}
