package ee.asaarnak.library.components;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.PersistentLocale;

import ee.asaarnak.library.commons.LocaleUtil;

/**
 * Layout component for pages of application Library.
 * 
 * @author Allar Saarnak
 */
@Import(stylesheet = { "context:static/css/bootstrap.min.css",
		"context:static/css/layout.css" }, library = {
		"context:static/js/jquery.min.js",
		"context:static/js/jquery.tablesorter.min.js" })
public class Layout {
	/** The page title, for the <title> element and the <h1>element. */
	@Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
	private String title;

	@Property
	private String pageName;

	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private String sidebarTitle;

	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private Block sidebar;

	@Inject
	private ComponentResources resources;

	@Inject
	private Messages messages;

	@Inject
	@Property
	private Locale currentLocale;

	private List<Locale> supportedLocales;

	private Locale supportedLocale;

	@Property
	@SuppressWarnings("unused")
	private DateFormat dateFormat;

	@Property
	@SuppressWarnings("unused")
	private NumberFormat numberFormat;

	@Inject
	private PersistentLocale persistentLocaleService;

	@Inject
	@Symbol("tapestry.supported-locales")
	private String supportedLocalesString;

	void setupRender() {
		supportedLocales = LocaleUtil.convertToLocales(supportedLocalesString);
		dateFormat = DateFormat.getDateInstance(DateFormat.LONG, currentLocale);
		numberFormat = NumberFormat.getInstance(currentLocale);
	}

	void onSwitchLocale(String localeCode) {
		persistentLocaleService.set(LocaleUtil.convertToLocale(localeCode));
	}

	/**
	 * @return "active" if current pageName is active
	 */
	public String getClassForPageName() {
		return resources.getPageName().equalsIgnoreCase(pageName) ? "active" : null;
	}

	public Map<String, String> getPageNames() {
		Map<String, String> result = new HashMap<String, String>();
		result.put("Index", messages.get("Index"));
		result.put("Book/Index", messages.get("Book/Index"));
		result.put("Author/Index", messages.get("Author/Index"));
		return result;
	}

	public Block getSidebar() {
		return sidebar;
	}

	public String getSidebarTitle() {
		return sidebarTitle;
	}

	public String getTitle() {
		return title;
	}

	public void setSidebar(Block sidebar) {
		this.sidebar = sidebar;
	}

	public void setSidebarTitle(String sidebarTitle) {
		this.sidebarTitle = sidebarTitle;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Locale getSupportedLocale() {
		return supportedLocale;
	}

	public void setSupportedLocale(Locale supportedLocale) {
		this.supportedLocale = supportedLocale;
	}

	public List<Locale> getSupportedLocales() {
		return supportedLocales;
	}
}
