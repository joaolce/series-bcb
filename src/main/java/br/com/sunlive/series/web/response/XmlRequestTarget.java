package br.com.sunlive.series.web.response;

import net.sf.json.xml.XMLSerializer;

public class XmlRequestTarget extends SeriesRequestTarget {

  public XmlRequestTarget(Object objetoResposta) {
    super("application/xml", getXML(objetoResposta));
  }

  private static String getXML(Object objetoResposta) {
    if (objetoResposta == null) {
      return "";
    }
    XMLSerializer xmlSerializer = new XMLSerializer();
    xmlSerializer.setElementName("o");
    return xmlSerializer.write(getJSONObject(objetoResposta));
  }
}
