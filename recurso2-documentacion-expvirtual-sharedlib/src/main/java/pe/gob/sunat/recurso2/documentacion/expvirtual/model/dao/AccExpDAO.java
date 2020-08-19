package pe.gob.sunat.recurso2.documentacion.expvirtual.model.dao;

import java.util.List;
import java.util.Map;

import pe.gob.sunat.recurso2.documentacion.expvirtual.model.AccExp;

public interface AccExpDAO {
	public List<AccExp> listar(Map<String, Object> parametros);
}
