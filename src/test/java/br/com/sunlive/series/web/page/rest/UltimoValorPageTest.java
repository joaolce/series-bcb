package br.com.sunlive.series.web.page.rest;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import br.com.sunlive.series.erro.SeriesException;
import br.com.sunlive.series.service.SeriesService;
import br.gov.bcb.pec.sgs.casosdeuso.ws.comum.WSSerieVO;
import org.apache.wicket.PageParameters;
import org.junit.Before;
import org.junit.Test;
import util.SeriesWicketTester;

public class UltimoValorPageTest {

  private UltimoValorPage page;

  @Before
  public void setUp() {
    new SeriesWicketTester();
    page = new UltimoValorPage(new PageParameters());
  }

  @Test(expected = SeriesException.class)
  public void testExecutarSemParametro() {
    page.executar(null);
  }

  @Test
  public void testExecutarOk() {
    SeriesService seriesServiceMock = createMock(SeriesService.class);
    SeriesService.set(seriesServiceMock);
    WSSerieVO voMock = createMock(WSSerieVO.class);
    expect(seriesServiceMock.ultimoValor(new Long(0L))).andReturn(voMock);
    replay(seriesServiceMock, voMock);

    assertEquals(voMock, page.executar("0"));
    verify(seriesServiceMock, voMock);
  }
}
