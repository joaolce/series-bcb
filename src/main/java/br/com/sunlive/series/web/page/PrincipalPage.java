package br.com.sunlive.series.web.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.protocol.http.RequestUtils;

public class PrincipalPage extends WebPage {

  @Override
  protected void onInitialize() {
    super.onInitialize();
    add(new Label("server", RequestUtils.toAbsolutePath("")));
  }
}
