package br.com.sunlive.series.service;

import br.gov.bcb.pec.sgs.casosdeuso.ws.comum.WSSerieVO;
import br.gov.bcb.www3.wssgs.services.FachadaWSSGS.FachadaWSSGS;
import br.gov.bcb.www3.wssgs.services.FachadaWSSGS.FachadaWSSGSServiceLocator;
import javax.xml.rpc.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeriesService {

  private static final Logger LOGGER = LoggerFactory.getLogger(SeriesService.class);
  private static SeriesService instancia;

  private FachadaWSSGSServiceLocator serviceLocator;
  private FachadaWSSGS fachada;

  public static SeriesService get() {
    if (instancia == null) {
      set(new SeriesService());
    }
    return instancia;
  }

  public static void set(SeriesService service) {
    SeriesService.instancia = service;
  }

  public WSSerieVO ultimoValor(long idSerie) {
    try {
      return getFachada().getUltimoValorVO(idSerie);
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  public WSSerieVO valores(long idSerie, String dataInicio, String dataFim) {
    try {
      return getFachada().getValoresSeriesVO(new long[] {idSerie}, dataInicio, dataFim)[0];
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }

  public FachadaWSSGS getFachada() {
    if (fachada == null) {
      try {
        fachada = getServiceLocator().getFachadaWSSGS();
      } catch (ServiceException e) {
        LOGGER.error("Erro ao criar fachada", e);
      }
    }
    return fachada;
  }

  public FachadaWSSGSServiceLocator getServiceLocator() {
    if (serviceLocator == null) {
      serviceLocator = new FachadaWSSGSServiceLocator();
    }
    return serviceLocator;
  }

  public void setServiceLocator(FachadaWSSGSServiceLocator serviceLocator) {
    this.serviceLocator = serviceLocator;
  }
}
