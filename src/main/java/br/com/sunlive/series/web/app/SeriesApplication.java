package br.com.sunlive.series.web.app;

import br.com.sunlive.series.web.ParametroREST;
import br.com.sunlive.series.web.page.PrincipalPage;
import br.com.sunlive.series.web.page.rest.UltimoValorPage;
import br.com.sunlive.series.web.page.rest.ValoresPage;
import org.apache.wicket.Page;
import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Response;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebResponse;
import org.apache.wicket.request.IRequestCycleProcessor;
import org.apache.wicket.request.target.coding.MixedParamUrlCodingStrategy;

public class SeriesApplication extends WebApplication {

  @Override
  public Class<? extends Page> getHomePage() {
    return PrincipalPage.class;
  }

  @Override
  protected void init() {
    super.init();
    adicionarUrlsREST();
  }

  private void adicionarUrlsREST() {
    ParametroREST[] parametrosEnum = ParametroREST.values();
    String[] parametros = new String[parametrosEnum.length];
    for (int aux = 0; aux < parametros.length; aux++) {
      parametros[aux] = parametrosEnum[aux].getNome();
    }
    mount(new MixedParamUrlCodingStrategy("ultimoValor", UltimoValorPage.class, parametros));
    mount(new MixedParamUrlCodingStrategy("valores", ValoresPage.class, parametros));
  }

  @Override
  protected IRequestCycleProcessor newRequestCycleProcessor() {
    return new SeriesRequestCycleProcessor();
  }

  @Override
  public RequestCycle newRequestCycle(Request request, Response response) {
    return new SeriesRequestCycle(this, (WebRequest) request, (WebResponse) response);
  }
}
