package pe.gob.sunat.recurso2.documentacion.expvirtual.web.controller.app;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import pe.gob.sunat.framework.spring.util.bean.MensajeBean;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.AvisoConstantes;
import pe.gob.sunat.recurso2.documentacion.expvirtual.util.NavegaConstantes;

@Controller
@RequestMapping("/error")
public class ErrorController extends MultiActionController {
	
	private static final Log log = LogFactory.getLog(ErrorController.class);
	
	@RequestMapping(value="/cargarPaginaError")
	public ModelAndView cargarPaginaError (HttpServletRequest request, HttpServletResponse response) {
		
		if (log.isDebugEnabled()) log.debug("Inicio - ErrorController.cargarPaginaError");
		
		ModelAndView modelo = null;
		
		try {
			
			if (log.isDebugEnabled()) log.debug("Procesa - ErrorController.cargarPaginaError");
			
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			
			MensajeBean msb = new MensajeBean();
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} catch (Exception ex) {
			
			if (log.isDebugEnabled()) log.debug("Error - ErrorController.cargarPaginaError");
			
			log.error(ex, ex);
			MensajeBean msb = new MensajeBean();
			modelo = new ModelAndView(NavegaConstantes.MANT_PAGINA_ERROR);
			msb.setError(true);
			msb.setMensajeerror(AvisoConstantes.MENS_PAGINA_ERROR);
			modelo.addObject("beanErr", msb);
			
		} finally {
			
			if (log.isDebugEnabled()) log.debug("Final - ErrorController.cargarPaginaError");
			
			NDC.pop();
			NDC.remove();
			
		}
		
		return modelo;
		
	}
	
}