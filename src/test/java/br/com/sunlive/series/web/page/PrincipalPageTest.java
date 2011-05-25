package br.com.sunlive.series.web.page;

import org.junit.Test;
import util.SeriesWicketTester;

public class PrincipalPageTest {

  @Test
  public void testRenderizacao() {
    SeriesWicketTester tester = new SeriesWicketTester();
    tester.startPage(PrincipalPage.class);
    tester.assertRenderedPage(PrincipalPage.class);
  }
}
