package shoplinhkienmaytinh.catalog;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Range;
import org.salespointframework.inventory.Inventory;
import org.salespointframework.inventory.InventoryItem;
import org.salespointframework.quantity.Quantity;
import org.salespointframework.time.BusinessTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shoplinhkienmaytinh.catalog.LinhKien.LKType;

@Controller
class CatalogController {

	private static final Quantity NONE = Quantity.of(0);

	private final ShopCatalog catalog;
	private final Inventory<InventoryItem> inventory;
	private final BusinessTime businessTime;

	CatalogController(ShopCatalog shopCatalog, Inventory<InventoryItem> inventory, BusinessTime businessTime) {

		this.catalog = shopCatalog;
		this.inventory = inventory;
		this.businessTime = businessTime;
	}

	@GetMapping("/mouse")
	String mouseCatalog(Model model) {

		model.addAttribute("catalog", catalog.findByType(LKType.MOUSE));
		model.addAttribute("title", "catalog.mouse.title");

		return "catalog";
	}

	
	@GetMapping("/keyboard")
	String keyboardCatalog(Model model) {

		model.addAttribute("catalog", catalog.findByType(LKType.KEYBOARD));
		model.addAttribute("title", "catalog.keyboard.title");

		return "catalog";
	}
	@GetMapping("/linhkien/{linhkien}")
	String detail(@PathVariable LinhKien linhkien, Model model) {

		Optional<InventoryItem> item = inventory.findByProductIdentifier(linhkien.getId());
		Quantity quantity = item.map(InventoryItem::getQuantity).orElse(NONE);

		model.addAttribute("linhkien", linhkien);
		model.addAttribute("quantity", quantity);
		model.addAttribute("orderable", quantity.isGreaterThan(NONE));

		return "detail";
	}
	@PostMapping("/linhkien/{linhkien}/comments")
	public String comment(@PathVariable LinhKien linhkien, @Valid CommentAndRating payload) {

		linhkien.addComment(payload.toComment(businessTime.getTime()));
		catalog.save(linhkien);

		return "redirect:/linhkien/" + linhkien.getId();
	}
	interface CommentAndRating {

		@NotEmpty
		String getComment();

		@Range(min = 1, max = 5)
		int getRating();

		default Comment toComment(LocalDateTime time) {
			return new Comment(getComment(), getRating(), time);
		}
	}
}
