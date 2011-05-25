package br.com.sunlive.series.web.page.rest;

import br.com.sunlive.series.erro.TipoErro;
import br.com.sunlive.series.service.SeriesService;
import br.com.sunlive.series.util.DataUtil;
import br.com.sunlive.series.web.page.base.RestBasePage;
import br.gov.bcb.pec.sgs.casosdeuso.ws.comum.WSSerieVO;
import org.apache.commons.lang.StringUtils;
import org.apache.wicket.PageParameters;

public class ValoresPage extends RestBasePage {

  public ValoresPage(PageParameters parametros) {
    super(parametros);
  }

  @Override
  public WSSerieVO executar(String parametro) {
    if (StringUtils.isBlank(parametro)) {
      lancarErro(TipoErro.PARAMETRO_NAO_INFORMADO, null);
    }
    String[] parametros = quebrarParametro(parametro);
    Long idSerie = transformarEmNumerico(parametros[0]);
    String dataInicio = DataUtil.formatar(parametros[1]);
    String dataFim = DataUtil.formatar(parametros[2]);
    return SeriesService.get().valores(idSerie, dataInicio, dataFim);
  }

  protected String[] quebrarParametro(String parametro) {
    String[] parametros = parametro.split("\\|");
    if (parametros.length != 3) {
      lancarErro(TipoErro.PARAMETRO_INCORRETO, "Devem ser informado 3 parâmetros separados por |");
    }
    return parametros;
  }
}
