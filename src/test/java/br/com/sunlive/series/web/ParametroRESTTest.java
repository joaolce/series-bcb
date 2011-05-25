package br.com.sunlive.series.web;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;
import org.apache.wicket.PageParameters;
import org.junit.Test;

public class ParametroRESTTest {

  @Test
  public void testValueOf() {
    assertEquals(ParametroREST.PARAMETRO, ParametroREST.valueOf("PARAMETRO"));
  }

  @Test
  public void testGetValorParametro() {
    Map<String, String> map = new HashMap<String, String>();
    map.put(ParametroREST.RESPOSTA.getNome(), "valor");
    assertEquals("valor", ParametroREST.RESPOSTA.get(new PageParameters(map)));
  }
}
