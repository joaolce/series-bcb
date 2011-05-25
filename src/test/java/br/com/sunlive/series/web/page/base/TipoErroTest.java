package br.com.sunlive.series.web.page.base;

import static org.junit.Assert.assertEquals;
import br.com.sunlive.series.erro.TipoErro;
import org.junit.Test;

public class TipoErroTest {

  @Test
  public void testValueOf() {
    assertEquals(TipoErro.RESPOSTA_NAO_IMPLEMENTADA, TipoErro.valueOf("RESPOSTA_NAO_IMPLEMENTADA"));
  }

  @Test
  public void testMensagem() {
    assertEquals("", TipoErro.INTERNO.mensagem(null));
  }
}
