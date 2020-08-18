package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.bean.T6620RequerimBean;

public interface T6620RequerimDAO {
	
	public void insertar(Map<String, Object> parametros);
	
	public List<T6620RequerimBean> listar(Map<String, Object> parametros);
	
	public List<T6620RequerimBean> listarRequerimientos(Map<String, Object> mapParametrosBusqueda);
	
	public void insertarManual(Map<String, Object> mapDatos);

	public void update(Map<String, Object> mapDatos);
	
	// [ATORRESCH 2017/03/02] METODO QUE ELIMINA UN REQUERIMIENTO ACTUALIZA Y PONE ESTADO 02
	public void Eliminar(Map<String, Object> parametros);
	
	//Inicio staype 26/12/2019  [PAS20191U210500291]
	public T6620RequerimBean obtenerRequerimiento(Map<String, Object> mapParametrosBusqueda);
	
	public void actualizar(Map<String, Object> parametros);
	//Fin staype 26/12/2019
	
}