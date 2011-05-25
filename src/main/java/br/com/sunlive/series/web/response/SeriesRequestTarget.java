package br.com.sunlive.series.web.response;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.apache.wicket.Application;
import org.apache.wicket.request.target.basic.StringRequestTarget;

public class SeriesRequestTarget extends StringRequestTarget {

  public SeriesRequestTarget(String contentType, String conteudo) {
    super(contentType, getEncoding(), conteudo);
  }

  private static String getEncoding() {
    return Application.get().getRequestCycleSettings().getResponseRequestEncoding();
  }

  protected static JSON getJSONObject(Object objetoResposta) {
    if (JSONUtils.isArray(objetoResposta)) {
      return JSONArray.fromObject(objetoResposta);
    }
    return JSONObject.fromObject(objetoResposta);
  }
}
