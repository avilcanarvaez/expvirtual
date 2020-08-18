package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;

public interface TabDepDAO {

	public Map<String, Object> getDepAduanaByCodUo(Map<String, Object> params);
	public List<Map<String, Object>> listarDepAduana();
}
