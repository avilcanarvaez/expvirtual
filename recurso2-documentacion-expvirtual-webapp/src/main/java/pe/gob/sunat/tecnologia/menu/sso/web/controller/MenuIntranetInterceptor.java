package pe.gob.sunat.tecnologia.menu.sso.web.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import pe.gob.sunat.framework.security.Coder;
import pe.gob.sunat.framework.security.cifrador.factory.HashFactory;
import pe.gob.sunat.framework.security.cifrador.interfaz.Hash;
import pe.gob.sunat.framework.spring.util.bean.MensajeBean;
import pe.gob.sunat.framework.spring.util.date.FechaBean;
import pe.gob.sunat.framework.spring.util.lang.Hex;
import pe.gob.sunat.tecnologia.menu.bean.UsuarioBean;
import pe.gob.sunat.tecnologia.menu.sso.bean.UsuarioTest;

/**
 * 
 * @author Johnny
 *
 */
public class MenuIntranetInterceptor extends HandlerInterceptorAdapter {
  private Properties testingFile = null;
  private ModelAndView msgView;
  private String jsonView = "jsonView";
  protected final Log log = LogFactory.getLog(getClass());
  private static String AJAX_REQUEST = "XMLHttpRequest";
  

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
   */
  public boolean preHandle(HttpServletRequest request,
      HttpServletResponse response, Object handler) throws Exception {
    // TODO Auto-generated method stub
    
    NDC.push( new FechaBean().getFormatDate("yyyyMMddHHmmssSSS"));
    
    if(log.isDebugEnabled())log.debug( "header:X-Requested-With: " + request.getHeader("X-Requested-With") );
    
    boolean isAjaxRequest =  AJAX_REQUEST.equals(request.getHeader("X-Requested-With"));
    
    try {
      UsuarioBean ubean = null;
      if (testingFile != null){
        if(log.isDebugEnabled())log.debug("Cargando usuarioBean via properties");
        
        // Properties props = (Properties) testingFile.getObject();
        
        ubean = UsuarioTest.loadFromTestingFile(testingFile);
        
        WebUtils.setSessionAttribute(request, "usuarioBean", ubean);
      } else {
        ServletWebRequest webRequest = new ServletWebRequest(request);
        
        String token = webRequest.getParameter("token");
        String hc = webRequest.getParameter("hc");

        ubean = (UsuarioBean) WebUtils.getSessionAttribute(request, "usuarioBean");
        if(ubean == null || (token != null && hc != null && token.length()>0 && hc.length()>0)){
  
          if(log.isDebugEnabled())log.debug("cargar del testing " + hc);
          if(log.isDebugEnabled())log.debug("cargar del testing " + token);
          
          if (token == null || hc == null){
            log.error("El token o el hash no ha sido enviado");
            MensajeBean mensajeBean = new MensajeBean();
            mensajeBean.setMensajeerror("No se ha enviado correctamente los parametros de autenticacion");
            mensajeBean.setMensajesol("Vuelva a intentar ejecutar la aplicacion. Si el problema continua, intente salir del Menu e ingresar nuevamente.");
            
            if(!isAjaxRequest){
	            msgView.addObject("beanM", mensajeBean);
	            throw new ModelAndViewDefiningException(msgView);
            } else {
            	throw new ModelAndViewDefiningException(new ModelAndView(jsonView, "Error", mensajeBean));
            }
          }
  
          
//          ubean = (UsuarioBean) new Coder().decodeBase64(token);
          // Obtiene el token encoded y lo convierte a un arreglo de bytes, que corresponde al usuariobean empaquetado.
 //         byte[] zipObject = (byte[]) new Coder().decodeBase64(token);
//          ZipUtil zip = new ZipUtil();
//          ubean = (UsuarioBean) zip.unzipObject(zipObject);
          ubean = (UsuarioBean) new Coder().decodeBase64(token);
          if (ubean == null){
            log.error("El usuariobean no ha podido ser deserializado");
            MensajeBean mensajeBean = new MensajeBean();
            mensajeBean.setMensajeerror("No se ha enviado correctamente los parametros de autenticacion");
            mensajeBean.setMensajesol("Vuelva a intentar ejecutar la aplicacion. Si el problema continua, intente salir del Menu e ingresar nuevamente.");
            if(!isAjaxRequest){
	            msgView.addObject("beanM", mensajeBean);
	            throw new ModelAndViewDefiningException(msgView);
            } else {
            	throw new ModelAndViewDefiningException(new ModelAndView(jsonView, "Error", mensajeBean));
            }
          }
          if (ubean.getMap() == null){
            log.error("No se ha definido el contenedor de las vigencias de invocacion");
            MensajeBean mensajeBean = new MensajeBean();
            mensajeBean.setMensajeerror("No se ha enviado correctamente los parametros de autenticacion");
            mensajeBean.setMensajesol("Vuelva a intentar ejecutar la aplicacion. Si el problema continua, intente salir del Menu e ingresar nuevamente.");
            if(!isAjaxRequest){
	            msgView.addObject("beanM", mensajeBean);
	            throw new ModelAndViewDefiningException(msgView);
            } else {
            	throw new ModelAndViewDefiningException(new ModelAndView(jsonView, "Error", mensajeBean));
            }
          }
          if (!ubean.getMap().containsKey("vigInvocaDesde") ||
              !ubean.getMap().containsKey("vigInvocaHasta")
          )
          {
            log.error("No se han definido las vigencias de la invocacion");
            MensajeBean mensajeBean = new MensajeBean();
            mensajeBean.setMensajeerror("No se ha enviado correctamente los parametros de autenticacion");
            mensajeBean.setMensajesol("Vuelva a intentar ejecutar la aplicacion. Si el problema continua, intente salir del Menu e ingresar nuevamente.");
            if(!isAjaxRequest){
	            msgView.addObject("beanM", mensajeBean);
	            throw new ModelAndViewDefiningException(msgView);
            } else {
            	throw new ModelAndViewDefiningException(new ModelAndView(jsonView, "Error", mensajeBean));
            }
          }
  
          Hash hash = HashFactory.getHash(HashFactory.HashSha1, token.getBytes());
          String sha = Hex.getByteToHex(hash.get());
  
          if (!sha.equals(hc)){
            log.error("El parametro hash no es igual al generado con los datos enviados");
            MensajeBean mensajeBean = new MensajeBean();
            mensajeBean.setMensajeerror("No se ha enviado correctamente los parametros de autenticacion");
            mensajeBean.setMensajesol("Vuelva a intentar ejecutar la aplicacion. Si el problema continua, intente salir del Menu e ingresar nuevamente.");
            if(!isAjaxRequest){
	            msgView.addObject("beanM", mensajeBean);
	            throw new ModelAndViewDefiningException(msgView);
            } else {
            	throw new ModelAndViewDefiningException(new ModelAndView(jsonView, "Error", mensajeBean));
            }
          }
  
          long current = new FechaBean().getCalendar().getTimeInMillis();
          if(log.isDebugEnabled())log.debug("current:" + current + " desde:" + ((Long)ubean.getMap().get("vigInvocaDesde")).longValue() + " hasta " + ((Long)ubean.getMap().get("vigInvocaHasta")).longValue());
          if (current<((Long)ubean.getMap().get("vigInvocaDesde")).longValue() || 
              current>((Long)ubean.getMap().get("vigInvocaHasta")).longValue())
          {
            log.error("La invocacion ha caducado");
            MensajeBean mensajeBean = new MensajeBean();
            mensajeBean.setMensajeerror("La vigencia de la invocacion ha caducado");
            mensajeBean.setMensajesol("Vuelva a intentar ejecutar la aplicacion. Si el problema continua, intente salir del Menu e ingresar nuevamente.");
            if(!isAjaxRequest){
	            msgView.addObject("beanM", mensajeBean);
	            throw new ModelAndViewDefiningException(msgView);
            } else {
            	throw new ModelAndViewDefiningException(new ModelAndView(jsonView, "Error", mensajeBean));
            }
          }
  
          if(log.isDebugEnabled())log.debug("cargar del testing " + sha);
          
          
          ubean.getMap().put("roles", expandeRoles( (Map) ubean.getMap().get("roles")) );
          if(log.isDebugEnabled())log.debug("roles.." + ubean.getMap().get("roles"));
  
        } else {
          
          log.info("CARGANDO DATOS DEL USUARIO " + ubean.getLogin() + " DESDE LA SESION " );
        }
        
        
        boolean isOK = false;
        Cookie[] cookies = request.getCookies();
        for(int i=0;i<cookies.length;i++){
          if(log.isDebugEnabled())log.debug("cookie.." + cookies[i].getName());
          if (cookies[i].getName().equals(ubean.getLogin())){
            isOK = true;
          }
        }
        if(!isOK){
          MensajeBean mensajeBean = new MensajeBean();
          mensajeBean.setMensajeerror("La vigencia de la invocacion ha caducado");
          mensajeBean.setMensajesol("Vuelva a intentar ejecutar la aplicacion. Si el problema continua, intente salir del Menu e ingresar nuevamente.");
          if(!isAjaxRequest){
	            msgView.addObject("beanM", mensajeBean);
	            throw new ModelAndViewDefiningException(msgView);
          } else {
          	throw new ModelAndViewDefiningException(new ModelAndView(jsonView, "Error", mensajeBean));
          }
        }
        
        
      }
      
      if(log.isDebugEnabled())log.debug("cargar del testing " + ubean.getNombreCompleto() + " " + ubean.getCodUO() + " " + ubean.getCodCate());

      WebUtils.setSessionAttribute(request, "usuarioBean", ubean);
    } finally {
      NDC.pop();
      NDC.remove();       
    }
    
    return true;
  }
  
  /**
   * @return the testingFile
   */
  public Properties getTestingFile() {
    return testingFile;
  }

  /**
   * @param testingFile the testingFile to set
   */
  public void setTestingFile(Properties testingFile) {
    this.testingFile = testingFile;
  }

  /**
   * @return the msgView
   */
  public ModelAndView getMsgView() {
    return msgView;
  }

  /**
   * @param msgView the msgView to set
   */
  public void setMsgView(ModelAndView msgView) {
    this.msgView = msgView;
  }

  public void setJsonView(String jsonView) {
	this.jsonView = jsonView;
  }
  private Map expandeRoles(Map roles) {
      if(log.isDebugEnabled()) log.debug("roles " + roles);
      Map mapped = new HashMap();
      for (int i = 0; i < roles.size(); i++) {
          mapped.put( roles.get(String.valueOf(i)), roles.get(String.valueOf(i)) );
          if(log.isDebugEnabled())log.debug("rol " + roles.get(String.valueOf(i)));
      }
      return mapped;
  }

}