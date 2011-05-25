package br.com.sunlive.series.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

public class DataUtilTest {

  @Test
  public void testConstrutor() {
    assertNotNull(new DataUtil());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFormatarNull() {
    DataUtil.formatar(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFormatarComTamanhoInvalidoMenor() {
    DataUtil.formatar("1012010");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFormatarComTamanhoInvalidoMaior() {
    DataUtil.formatar("001012010");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFormatarComDiaInvalida() {
    DataUtil.formatar("31022010");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFormatarComMesInvalida() {
    DataUtil.formatar("01312010");
  }

  @Test
  public void testFormatarOk() {
    assertEquals("10/09/2000", DataUtil.formatar("10092000"));
  }
}
