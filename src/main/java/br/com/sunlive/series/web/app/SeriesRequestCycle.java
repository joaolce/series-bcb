package br.com.sunlive.series.web.app;

import br.com.sunlive.series.erro.SeriesException;
import org.apache.wicket.Response;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebRequestCycle;

public class SeriesRequestCycle extends WebRequestCycle {

  public SeriesRequestCycle(WebApplication application, WebRequest request, Response response) {
    super(application, request, response);
  }

  @Override
  protected void logRuntimeException(RuntimeException e) {
    if (!(e instanceof SeriesException)) {
      super.logRuntimeException(e);
    }
  }
}
