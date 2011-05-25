package br.com.sunlive.series.web.app;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import br.com.sunlive.series.erro.SeriesException;
import org.junit.Before;
import org.junit.Test;
import util.SeriesWicketTester;

public class SeriesRequestCycleTest {

  private SeriesRequestCycle requestCycle;

  @Before
  public void setUp() {
    requestCycle = (SeriesRequestCycle) new SeriesWicketTester().createRequestCycle();
  }

  @Test
  public void testLogRuntimeExceptionComSeriesException() {
    SeriesException exceptionMock = createMock(SeriesException.class);
    replay(exceptionMock);
    requestCycle.logRuntimeException(exceptionMock);
    verify(exceptionMock);
  }

  @Test
  public void testLogRuntimeExceptionComNullPointerException() {
    requestCycle.logRuntimeException(new RuntimeException());
  }
}
