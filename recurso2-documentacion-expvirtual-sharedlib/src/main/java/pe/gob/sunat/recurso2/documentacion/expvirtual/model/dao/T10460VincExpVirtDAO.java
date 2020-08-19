package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T10460VincExpVirtBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6614ExpVirtualBean;

public interface T10460VincExpVirtDAO {
	
	public  List<Map<String, Object>> obtenerNumExpVinculados(Map<String, Object> parametros);
	
	//Inicio CVG 16/01/2020
	public  void insertar(T10460VincExpVirtBean parametros);

	public List<T10460VincExpVirtBean> listar(Map<String, Object> mapParametrosBusqueda);
	
	public void actualizar (Map<String, Object> parametros);
	//CVG - FIN
}