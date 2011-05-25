package br.com.sunlive.series.erro;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import br.com.sunlive.series.web.response.Resposta;
import org.junit.Test;

public class SeriesExceptionTest {

  @Test
  public void testVerificaDados() {
    Erro erroMock = createMock(Erro.class);
    replay(erroMock);
    SeriesException exception = new SeriesException(erroMock, Resposta.JSON);
    assertEquals(erroMock, exception.erro());
    assertEquals(Resposta.JSON, exception.tipoResposta());
    verify(erroMock);
  }
}
