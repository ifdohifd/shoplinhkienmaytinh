package shoplinhkienmaytinh.catalog;

import static org.salespointframework.core.Currencies.*;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

import org.javamoney.moneta.Money;
import org.salespointframework.core.DataInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import shoplinhkienmaytinh.catalog.LinhKien.LKType;

@Component
@Order(20)
class CatalogDataInitializer implements DataInitializer {
	

	public static final CurrencyUnit VND = Monetary.getCurrency("VND");

	private static final Logger LOG = LoggerFactory.getLogger(CatalogDataInitializer.class);

	private final ShopCatalog shopCatalog;

	CatalogDataInitializer(ShopCatalog shopCatalog) {

		Assert.notNull(shopCatalog, "ShopCatalog must not be null!");

		this.shopCatalog = shopCatalog;
	}
	@Override
	public void initialize() {

		if (shopCatalog.findAll().iterator().hasNext()) {
			return;
		}

		LOG.info("Creating default catalog entries.");

		shopCatalog.save(new LinhKien("Chuột 1", "chuot1", Money.of(100, VND), "DELL", LKType.MOUSE));
		shopCatalog.save(new LinhKien("Chuột 2", "chuot1", Money.of(9.99, VND), "HP", LKType.MOUSE));
		shopCatalog.save(new LinhKien("Chuột 3", "chuot1", Money.of(9.99, VND), "DELL", LKType.MOUSE));
		shopCatalog.save(new LinhKien("Chuột 4", "chuot1", Money.of(9.99, VND), "HP", LKType.MOUSE));
		shopCatalog.save(new LinhKien("Chuột 5", "chuot1", Money.of(14.99, VND),
				"DELL", LKType.MOUSE));
		shopCatalog.save(new LinhKien("Chuột 6", "chuot1", Money.of(14.99, VND), "DELL", LKType.MOUSE));
		shopCatalog.save(new LinhKien("Chuột 7", "chuot1", Money.of(9999.0, VND), "HP", LKType.MOUSE));
		shopCatalog.save(new LinhKien("Chuột 8", "chuot1", Money.of(19.99, VND), "DELL",
				LKType.MOUSE));

		shopCatalog.save(new LinhKien("Bàn phím 1", "banphim", Money.of(6.99, VND), "HP", LKType.KEYBOARD));
		shopCatalog.save(new LinhKien("Bàn phím 2", "banphim", Money.of(19.99, VND), "DELL", LKType.KEYBOARD));
		shopCatalog
				.save(new LinhKien("Bàn phím 3", "banphim", Money.of(29.99, VND), "HP", LKType.KEYBOARD));
		shopCatalog
				.save(new LinhKien("Bàn phím 4", "banphim", Money.of(39.99, VND), "DELL", LKType.KEYBOARD));
		shopCatalog.save(new LinhKien("Bàn phím 5", "banphim", Money.of(39.99, VND), "HP", LKType.KEYBOARD));
		shopCatalog
				.save(new LinhKien("Bàn phím 6", "banphim", Money.of(34.99, VND), "DELL", LKType.KEYBOARD));
		shopCatalog.save(new LinhKien("Bàn phím 7", "banphim", Money.of(19.99, VND), "HP", LKType.KEYBOARD));
		shopCatalog.save(new LinhKien("Bàn phím 8", "banphim", Money.of(24.99, VND), "DELL", LKType.KEYBOARD));
		shopCatalog.save(new LinhKien("Bàn phím 9", "banphim", Money.of(29.99, VND),
				"HP", LKType.KEYBOARD));
	}
}
