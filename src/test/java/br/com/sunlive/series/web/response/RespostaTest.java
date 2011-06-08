package br.com.sunlive.series.web.response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import br.com.sunlive.series.erro.SeriesException;
import br.com.sunlive.series.erro.TipoErro;
import br.com.sunlive.series.web.response.Resposta;
import org.junit.Test;

public class RespostaTest {

  @Test
  public void testValueOf() {
    assertEquals(Resposta.JSON, Resposta.valueOf("JSON"));
  }

  @Test
  public void testImplementacao() {
    for (Resposta resposta : Resposta.values()) {
      assertNotNull(resposta.implementacao());
    }
  }

  @Test
  public void testRespostaPadrao() {
    assertEquals(Resposta.JSON, Resposta.padrao());
  }

  @Test
  public void testGetNaoLocalizado() {
    try {
      Resposta.get("naoExiste");
      fail();
    } catch (SeriesException e) {
      assertEquals(TipoErro.RESPOSTA_NAO_IMPLEMENTADA.getCodigo(), e.erro().getCodigo());
    }
  }

  @Test
  public void testGetLocalizado() {
    assertNotNull(Resposta.get("json"));
  }
}
