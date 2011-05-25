package br.com.sunlive.series.web.page.rest;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import br.com.sunlive.series.erro.SeriesException;
import br.com.sunlive.series.service.SeriesService;
import br.gov.bcb.pec.sgs.casosdeuso.ws.comum.WSSerieVO;
import org.apache.wicket.PageParameters;
import org.junit.Before;
import org.junit.Test;
import util.SeriesWicketTester;

public class ValoresPageTest {

  private ValoresPage page;

  @Before
  public void setUp() {
    new SeriesWicketTester();
    page = new ValoresPage(new PageParameters());
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
    expect(seriesServiceMock.valores(new Long(0L), "01/01/2010", "01/01/2011")).andReturn(voMock);
    replay(seriesServiceMock, voMock);

    assertEquals(voMock, page.executar("0|01012010|01012011"));
    verify(seriesServiceMock, voMock);
  }

  @Test(expected = SeriesException.class)
  public void testQuebrarValoresComParametrosIncorretos() {
    page.quebrarParametro("12|321");
  }

  @Test
  public void testQuebrarValoresComParametrosCorretos() {
    String[] parametro = page.quebrarParametro("12|321|123");
    assertArrayEquals(new String[] {"12", "321", "123"}, parametro);
  }
}
