package pe.gob.sunat.recurso2.documentacion.expvirtual.web.view;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.view.AbstractView;

public class ImageView extends AbstractView {
	
	protected final Log log = LogFactory.getLog(getClass());
	
	protected void renderMergedOutputModel(Map map, HttpServletRequest request,
		HttpServletResponse response) throws Exception {
		
		try {
			if(StringUtils.isEmpty(getContentType())) {
				setContentType("image/jpeg");
			}			
			response.setContentType(getContentType());
			
			response.getOutputStream().write((byte[])map.get("image"));
			response.getOutputStream().flush();
		}
		catch (Exception e) {
			log.error("error", e);
			throw e;
		}
	}
}