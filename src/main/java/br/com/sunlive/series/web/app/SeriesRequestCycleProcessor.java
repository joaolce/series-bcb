package br.com.sunlive.series.web.app;

import br.com.sunlive.series.erro.Erro;
import br.com.sunlive.series.erro.SeriesException;
import javax.servlet.http.HttpServletResponse;
import net.vidageek.mirror.dsl.Mirror;
import org.apache.wicket.IRequestTarget;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.protocol.http.WebRequestCycle;
import org.apache.wicket.protocol.http.WebRequestCycleProcessor;

public class SeriesRequestCycleProcessor extends WebRequestCycleProcessor {

  @Override
  public void respond(RuntimeException execao, RequestCycle requestCycle) {
    if (execao instanceof SeriesException) {
      ((WebRequestCycle) requestCycle).getWebResponse().getHttpServletResponse()
          .setStatus(HttpServletResponse.SC_BAD_REQUEST);
      Class<IRequestTarget> clazzResponse = ((SeriesException) execao).tipoResposta().implementacao();
      Erro erro = ((SeriesException) execao).erro();
      requestCycle.setRequestTarget(new Mirror().on(clazzResponse).invoke().constructor().withArgs(erro));
    } else {
      super.respond(execao, requestCycle);
    }
  }
}
