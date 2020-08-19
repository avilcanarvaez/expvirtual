package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;

public interface DocInteDAO {
	public Map<String, Object> getByPk(Map<String, Object> params);

	public List<Map<String, Object>> listarDocCircular(Map<String, Object> mapParam);
}
