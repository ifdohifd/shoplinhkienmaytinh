
package shoplinhkienmaytinh.inventory;

import org.salespointframework.core.DataInitializer;
import org.salespointframework.inventory.Inventory;
import org.salespointframework.inventory.InventoryItem;
import org.salespointframework.quantity.Quantity;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import shoplinhkienmaytinh.catalog.ShopCatalog;

@Component
@Order(20)
class InventoryInitializer implements DataInitializer {

	private final Inventory<InventoryItem> inventory;
	private final ShopCatalog shopCatalog;

	InventoryInitializer(Inventory<InventoryItem> inventory, ShopCatalog shopCatalog) {

		Assert.notNull(inventory, "Inventory must not be null!");
		Assert.notNull(shopCatalog, "ShopCatalog must not be null!");

		this.inventory = inventory;
		this.shopCatalog = shopCatalog;
	}

	@Override
	public void initialize() {

		shopCatalog.findAll().forEach(linhkien -> {
			inventory.findByProduct(linhkien) 
					.orElseGet(() -> inventory.save(new InventoryItem(linhkien, Quantity.of(10))));
		});
	}
}
