package pe.gob.sunat.recurso2.documentacion.expvirtual.bean;

import java.io.Serializable;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.T6617RepGenDoc;

public class T6617RepGenDocBean extends T6617RepGenDoc implements Serializable{

	private static final long serialVersionUID = 1L;

	/* Descripciones de los campos */
	private String desCodigoNivelReporte;	
	private String desCodEstadoRepPorExpediente;	
	
	/* Adicionales para las tablas */
	private Integer numOrden;
	private String correlativo;
	
}
