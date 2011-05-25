package br.com.sunlive.series.web.page.rest;

import br.com.sunlive.series.erro.TipoErro;
import br.com.sunlive.series.service.SeriesService;
import br.com.sunlive.series.web.page.base.RestBasePage;
import br.gov.bcb.pec.sgs.casosdeuso.ws.comum.WSSerieVO;
import org.apache.commons.lang.StringUtils;
import org.apache.wicket.PageParameters;

public class UltimoValorPage extends RestBasePage {

  public UltimoValorPage(PageParameters parametros) {
    super(parametros);
  }

  @Override
  public WSSerieVO executar(String parametro) {
    if (StringUtils.isBlank(parametro)) {
      lancarErro(TipoErro.PARAMETRO_NAO_INFORMADO, null);
    }
    Long idSerie = transformarEmNumerico(parametro);
    return SeriesService.get().ultimoValor(idSerie);
  }
}
