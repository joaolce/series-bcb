package br.com.sunlive.series.erro;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ErroTest {

  @Test
  public void testVerificaDados() {
    Erro erro = new Erro(TipoErro.INTERNO, "mensagem");
    assertEquals(TipoErro.INTERNO.getCodigo(), erro.getCodigo());
    assertEquals("mensagem", erro.getMensagem());
  }
}
