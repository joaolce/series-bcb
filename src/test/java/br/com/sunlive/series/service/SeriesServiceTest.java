package br.com.sunlive.series.service;

import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;
import br.gov.bcb.pec.sgs.casosdeuso.ws.comum.WSSerieVO;
import br.gov.bcb.www3.wssgs.services.FachadaWSSGS.FachadaWSSGS;
import br.gov.bcb.www3.wssgs.services.FachadaWSSGS.FachadaWSSGSServiceLocator;
import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;
import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class SeriesServiceTest {

  private FachadaWSSGS fachadaMock;
  private SeriesService service;

  @Before
  public void setUp() {
    fachadaMock = createMock(FachadaWSSGS.class);
    service = new SeriesService() {
      @Override
      public FachadaWSSGS getFachada() {
        return fachadaMock;
      }
    };
  }

  @Test
  public void testSingleton() {
    SeriesService service1 = SeriesService.get();
    SeriesService service2 = SeriesService.get();
    assertSame(service1, service2);
  }

  @Test
  public void testUltimoValorLancandoExcecao() throws RemoteException {
    WSSerieVO wsSerieVOMock = createMock(WSSerieVO.class);
    long idSerie = 0l;
    RuntimeException excecao = new RuntimeException();
    expect(fachadaMock.getUltimoValorVO(idSerie)).andThrow(excecao);
    replay(fachadaMock, wsSerieVOMock);

    try {
      service.ultimoValor(idSerie);
      fail();
    } catch (IllegalStateException e) {
      assertEquals(excecao, e.getCause());
    }
    verify(fachadaMock, wsSerieVOMock);
  }

  @Test
  public void testUltimoValorOk() throws RemoteException {
    WSSerieVO wsSerieVOMock = createMock(WSSerieVO.class);
    long idSerie = 0l;
    expect(fachadaMock.getUltimoValorVO(idSerie)).andReturn(wsSerieVOMock);
    replay(fachadaMock, wsSerieVOMock);

    assertEquals(wsSerieVOMock, service.ultimoValor(idSerie));
    verify(fachadaMock, wsSerieVOMock);
  }

  @Test
  public void testValoresLancandoExcecao() throws RemoteException {
    WSSerieVO wsSerieVOMock = createMock(WSSerieVO.class);
    long idSerie = 0l;
    Capture<long[]> captureIdSerie = new Capture<long[]>();
    String dataInicio = "dataInicio";
    String dataFim = "dataFim";
    RuntimeException excecao = new RuntimeException();
    expect(fachadaMock.getValoresSeriesVO(capture(captureIdSerie), eq(dataInicio), eq(dataFim))).andThrow(excecao);
    replay(fachadaMock, wsSerieVOMock);

    try {
      service.valores(idSerie, dataInicio, dataFim);
      fail();
    } catch (IllegalStateException e) {
      assertEquals(excecao, e.getCause());
    }
    assertArrayEquals(new long[] {idSerie}, captureIdSerie.getValue());
    verify(fachadaMock, wsSerieVOMock);
  }

  @Test
  public void testValoresOk() throws RemoteException {
    WSSerieVO wsSerieVOMock = createMock(WSSerieVO.class);
    long idSerie = 0l;
    Capture<long[]> captureIdSerie = new Capture<long[]>();
    String dataInicio = "dataInicio";
    String dataFim = "dataFim";
    expect(fachadaMock.getValoresSeriesVO(capture(captureIdSerie), eq(dataInicio), eq(dataFim))).andReturn(
        new WSSerieVO[] {wsSerieVOMock});
    replay(fachadaMock, wsSerieVOMock);

    assertEquals(wsSerieVOMock, service.valores(idSerie, dataInicio, dataFim));
    assertArrayEquals(new long[] {idSerie}, captureIdSerie.getValue());
    verify(fachadaMock, wsSerieVOMock);
  }

  @Test
  public void testGetFachadaLancandoExcecao() throws ServiceException {
    service = new SeriesService();
    FachadaWSSGSServiceLocator serviceLocatorMock = EasyMock.createMock(FachadaWSSGSServiceLocator.class);
    EasyMock.expect(serviceLocatorMock.getFachadaWSSGS()).andThrow(new ServiceException());
    replay(fachadaMock, serviceLocatorMock);
    service.setServiceLocator(serviceLocatorMock);

    assertNull(service.getFachada());
    verify(fachadaMock, serviceLocatorMock);
  }

  @Test
  public void testGetFachadaOk() throws ServiceException {
    service = new SeriesService();
    FachadaWSSGSServiceLocator serviceLocatorMock = EasyMock.createMock(FachadaWSSGSServiceLocator.class);
    EasyMock.expect(serviceLocatorMock.getFachadaWSSGS()).andReturn(fachadaMock);
    replay(fachadaMock, serviceLocatorMock);
    service.setServiceLocator(serviceLocatorMock);

    FachadaWSSGS fachada1 = service.getFachada();
    FachadaWSSGS fachada2 = service.getFachada();
    assertSame(fachada1, fachada2);
    verify(fachadaMock, serviceLocatorMock);
  }

  @Test
  public void testGetServiceLocator() {
    replay(fachadaMock);
    FachadaWSSGSServiceLocator serviceLocator1 = service.getServiceLocator();
    FachadaWSSGSServiceLocator serviceLocator2 = service.getServiceLocator();
    assertSame(serviceLocator1, serviceLocator2);
    verify(fachadaMock);
  }
}
