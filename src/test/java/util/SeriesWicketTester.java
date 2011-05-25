package util;

import br.com.sunlive.series.web.app.SeriesApplication;
import java.io.Serializable;
import org.apache.wicket.util.tester.WicketTester;

public class SeriesWicketTester extends WicketTester implements Serializable {

  public SeriesWicketTester() {
    super(new SeriesApplication());
  }
}
