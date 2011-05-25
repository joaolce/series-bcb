package br.com.sunlive.series.web.page.base;

import br.com.sunlive.series.erro.Erro;
import br.com.sunlive.series.erro.SeriesException;
import br.com.sunlive.series.erro.TipoErro;
import br.com.sunlive.series.web.ParametroREST;
import br.com.sunlive.series.web.response.Resposta;
import net.vidageek.mirror.dsl.Mirror;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebPage;

public abstract class RestBasePage extends WebPage {

  public RestBasePage(PageParameters parameters) {
    super(parameters);
  }

  @Override
  protected void onRender(MarkupStream markupStream) {
    try {
      Object resposta = executar(obterParametro());
      responder(resposta, obterTipoResposta());
    } catch (SeriesException execao) {
      throw execao;
    } catch (RuntimeException execao) {
      lancarErro(TipoErro.INTERNO, execao.getMessage());
    }
  }

  private String obterParametro() {
    return ParametroREST.PARAMETRO.get(getPageParameters());
  }

  protected abstract Object executar(String parametro);

  protected Resposta obterTipoResposta() {
    String parametroResposta = ParametroREST.RESPOSTA.get(getPageParameters());
    if (StringUtils.isBlank(parametroResposta)) {
      return Resposta.JSON;
    }
    return Resposta.get(parametroResposta);
  }

  protected void responder(Object resposta, Resposta tipoResposta) {
    if (tipoResposta == null) {
      lancarErro(TipoErro.RESPOSTA_NAO_IMPLEMENTADA, null);
    }
    getRequestCycle().setRequestTarget(
        new Mirror().on(tipoResposta.implementacao()).invoke().constructor().withArgs(resposta));
  }

  protected void lancarErro(TipoErro tipo, String complemento) {
    throw new SeriesException(new Erro(tipo, tipo.mensagem(complemento)), obterTipoResposta());
  }

  protected Long transformarEmNumerico(String id) {
    if (!NumberUtils.isDigits(id)) {
      lancarErro(TipoErro.PARAMETRO_INCORRETO, "O parâmetro não é um inteiro");
    }
    return Long.parseLong(id);
  }
}
