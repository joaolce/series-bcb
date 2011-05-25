package br.com.sunlive.series.web.response;

public class JSONRequestTarget extends SeriesRequestTarget {

  public JSONRequestTarget(Object objetoResposta) {
    super("application/json", getJSON(objetoResposta));
  }

  private static String getJSON(Object objetoResposta) {
    if (objetoResposta == null) {
      return "";
    }
    return getJSONObject(objetoResposta).toString();
  }
}
