package ee.asaarnak.library.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Util for converting locales when user switches page locale.
 * @author Someone Unknown
 * @author Allar Saarnak
 */
public class LocaleUtil {

  public static List<Locale> convertToLocales(String supportedLocaleCodesString) {
    List<Locale> locales = new ArrayList<Locale>();

    String[] localeCodes = supportedLocaleCodesString.split(",");
    for (String localeCode : localeCodes) {
      locales.add(convertToLocale(localeCode));
    }

    return locales;
  }

  public static Locale convertToLocale(String localeCode) {
    Locale locale = null;
    String[] elements = localeCode.split("_");

    switch (elements.length) {
      case 1:
        locale = new Locale(elements[0]);
        break;
      case 2:
        locale = new Locale(elements[0], elements[1]);
        break;
      case 3:
        locale = new Locale(elements[0], elements[1], elements[2]);
        break;
      case 4:
        locale = new Locale(elements[0], elements[1], elements[2] + "_" + elements[3]);
        break;
      default:
        throw new RuntimeException("Can't handle localeCode = \"" + localeCode + "\".  Elements.length = "
            + elements.length);
    }

    return locale;
  }
}