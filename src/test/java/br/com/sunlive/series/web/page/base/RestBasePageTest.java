package br.com.sunlive.series.web.page.base;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeThat;
import br.com.sunlive.series.erro.Erro;
import br.com.sunlive.series.erro.SeriesException;
import br.com.sunlive.series.erro.TipoErro;
import br.com.sunlive.series.web.ParametroREST;
import br.com.sunlive.series.web.response.Resposta;
import java.io.Serializable;
import org.apache.wicket.PageParameters;
import org.apache.wicket.RequestCycle;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Test;
import util.SeriesWicketTester;

public class RestBasePageTest implements Serializable {

  private RestBasePage page;

  @Before
  public void setUp() {
    new SeriesWicketTester();
  }

  @Test
  public void testRestComSeriesException() {
    final Erro erroMock = createMock(Erro.class);
    page = new RestBasePage(new PageParameters()) {
      @Override
      protected Object executar(String parametro) {
        throw new SeriesException(erroMock, null);
      }
    };
    replay(erroMock);
    try {
      page.onRender(null);
      fail();
    } catch (SeriesException e) {
      assertEquals(erroMock, e.erro());
    }
    verify(erroMock);
  }

  @Test
  public void testRestComRuntimeException() {
    page = new RestBasePage(new PageParameters()) {
      @Override
      protected Object executar(String parametro) {
        throw new RuntimeException();
      }
    };
    try {
      page.onRender(null);
      fail();
    } catch (SeriesException e) {
      assertEquals(TipoErro.INTERNO.getCodigo(), e.erro().getCodigo());
    }
  }

  @Test
  public void testRestOk() {
    page = new RestBasePage(new PageParameters()) {
      @Override
      protected Object executar(String parametro) {
        return null;
      }

      @Override
      protected void responder(Object resposta) {
        //stub
      }
    };
    page.onRender(null);
  }

  @Test
  public void testResponder() {
    final Resposta resposta = Resposta.padrao();
    page = new RestBasePage(new PageParameters()) {
      @Override
      protected Object executar(String parametro) {
        return null;
      }

      @Override
      protected Resposta obterTipoResposta() {
        return resposta;
      }
    };
    page.responder(new Object());
    assumeThat(RequestCycle.get().getRequestTarget(), IsInstanceOf.instanceOf(resposta.implementacao()));
  }

  @Test
  public void testObterTipoRespostaPadrao() {
    page = new RestBasePage(new PageParameters()) {
      @Override
      protected Object executar(String parametro) {
        return null;
      }
    };
    assertEquals(Resposta.padrao(), page.obterTipoResposta());
  }

  @Test
  public void testObterTipoRespostaInformada() {
    PageParameters pageParameters = new PageParameters();
    pageParameters.put(ParametroREST.RESPOSTA.getNome(), Resposta.XML.nome());
    page = new RestBasePage(pageParameters) {
      @Override
      protected Object executar(String parametro) {
        return null;
      }
    };
    assertEquals(Resposta.XML, page.obterTipoResposta());
  }

  @Test(expected = SeriesException.class)
  public void testTransformarEmNumericoNumeroNaoNumerico() {
    page = new RestBasePage(new PageParameters()) {
      @Override
      protected Object executar(String parametro) {
        return null;
      }
    };
    page.transformarEmNumerico("a");
  }

  @Test
  public void testTransformarEmNumericoNumeroNumerico() {
    page = new RestBasePage(new PageParameters()) {
      @Override
      protected Object executar(String parametro) {
        return null;
      }
    };
    assertEquals(2L, (long) page.transformarEmNumerico("2"));
  }
}
