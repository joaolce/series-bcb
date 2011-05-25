package br.com.sunlive.series.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import br.com.sunlive.series.web.response.Resposta;
import org.junit.Test;

public class RespostaTest {

  @Test
  public void testValueOf() {
    assertEquals(Resposta.JSON, Resposta.valueOf("JSON"));
  }

  @Test
  public void testGetNaoLocalizado() {
    assertNull(Resposta.get("naoExiste"));
  }

  @Test
  public void testGetLocalizado() {
    assertNotNull(Resposta.get("json"));
  }
}
