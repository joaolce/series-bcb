package br.com.sunlive.series.util;

import java.util.Locale;
import org.joda.time.DateTimeZone;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DataUtil {

  private static final Locale LOCALE = new Locale("pt", "BR");
  private static final DateTimeZone ZONE = DateTimeZone.forID("America/Sao_Paulo");

  public static String formatar(String dataAFormatar) {
    preCondicao(dataAFormatar);
    String data =
        dataAFormatar.substring(0, 2) + '/' + dataAFormatar.substring(2, 4) + '/' + dataAFormatar.substring(4);
    validarData(data);
    return data;
  }

  private static void preCondicao(String dataAFormatar) {
    if ((dataAFormatar == null) || (dataAFormatar.length() != 8)) {
      throw new IllegalArgumentException("Data deve ter o o formato ddMMaaaa");
    }
  }

  private static void validarData(String dataFormatada) {
    try {
      getDateTimeFormatter().parseDateTime(dataFormatada);
    } catch (IllegalFieldValueException e) {
      throw new IllegalArgumentException("Data deve ter o o formato ddMMaaaa", e);
    }
  }

  private static DateTimeFormatter getDateTimeFormatter() {
    return DateTimeFormat.forPattern("dd/MM/yyyy").withZone(ZONE).withLocale(LOCALE);
  }
}
