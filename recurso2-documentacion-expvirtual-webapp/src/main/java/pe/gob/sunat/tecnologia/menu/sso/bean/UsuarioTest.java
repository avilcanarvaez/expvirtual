package pe.gob.sunat.tecnologia.menu.sso.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import pe.gob.sunat.framework.spring.util.date.FechaBean;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;

/**
 * Esta clase permite cargar propiedades a la clase UsuarioBean para el manejo de 
 * pruebas.
 *  
 * @author JVALDEZ
 *
 */
public class UsuarioTest {

	public final static UsuarioBean loadFromTestingFile(Properties propiedades){
		
		UsuarioBean usuarioBean = new UsuarioBean();
		usuarioBean.setLogin(propiedades.getProperty("usuario.login"));
		usuarioBean.setCorreo(propiedades.getProperty("usuario.correo"));
		usuarioBean.setNombres(propiedades.getProperty("usuario.nombres"));
		usuarioBean.setApePaterno(propiedades.getProperty("usuario.apePaterno"));
		usuarioBean.setApeMaterno(propiedades.getProperty("usuario.apeMaterno"));
		usuarioBean.setNombreCompleto(propiedades.getProperty("usuario.nombreCompleto"));
		usuarioBean.setNroRegistro(propiedades.getProperty("usuario.nroRegistro"));
		usuarioBean.setCodUO(propiedades.getProperty("usuario.codUO"));
		usuarioBean.setCodCate(propiedades.getProperty("usuario.codCate"));
		usuarioBean.setNivelUO(new Short(propiedades.getProperty("usuario.nivelUO")).shortValue());
		usuarioBean.setNumRUC(propiedades.getProperty("usuario.numRUC"));
		usuarioBean.setUsuarioSOL(propiedades.getProperty("usuario.usuarioSOL"));
		usuarioBean.setCodDepend(propiedades.getProperty("usuario.codDepend"));
		usuarioBean.setIdCelular(propiedades.getProperty("usuario.idCelular"));
		usuarioBean.setCodTOpeComer(propiedades.getProperty("usuario.codTOpeComer"));
		String tipo_menu = propiedades.getProperty("tipo.menu");
		
		String origen = "IT";
		
		if (tipo_menu.equals("2")) origen = "IA";
		
		else if (tipo_menu.equals("3")) origen = "ET";
		
		usuarioBean.setTicket(new FechaBean().getFormatDate(tipo_menu.concat("DHHmmssSSS")));
		usuarioBean.getMap().put("idMenu", usuarioBean.getTicket());
		usuarioBean.getMap().put("tipOrigen", origen);
		
		String sroles = propiedades.getProperty("usuario.roles");
		
		if (sroles != null) {
			
			String[] split = sroles.split("\\,");
			Map mroles = new HashMap();
			
			for (int i=0;i<split.length;i++) {
				
				mroles.put(split[i], split[i]);
				
			}
			
			usuarioBean.getMap().put("roles", mroles);
			
		}
		
		return usuarioBean;
		
	}
	
}