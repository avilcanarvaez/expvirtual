package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;

public interface DataCatalogoDAO {

	public Map<String, Object> getDepAduanaByCod(Map<String, Object> params);
	public List<Map<String, Object>> listarDepAduana();
	
}
