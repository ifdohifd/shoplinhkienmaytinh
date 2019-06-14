
package shoplinhkienmaytinh.order;

import java.util.Optional
;

import org.salespointframework.catalog.Product;
import org.salespointframework.core.AbstractEntity;
import org.salespointframework.order.Cart;
import org.salespointframework.order.Order;
import org.salespointframework.order.OrderManager;
import org.salespointframework.order.OrderStatus;
import org.salespointframework.payment.Cash;
import org.salespointframework.quantity.Quantity;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.web.LoggedIn;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import shoplinhkienmaytinh.catalog.LinhKien;

@Controller
@PreAuthorize("isAuthenticated()")
@SessionAttributes("cart")
class OrderController {

	private final OrderManager<Order> orderManager;
	OrderController(OrderManager<Order> orderManager) {

		Assert.notNull(orderManager, "OrderManager must not be null!");
		this.orderManager = orderManager;
	}
	@ModelAttribute("cart")
	Cart initializeCart() {
		return new Cart();
	}
	@PostMapping("/cart")
	String addLinhKien(@RequestParam("pid") LinhKien linhKien, @RequestParam("number") int number, @ModelAttribute Cart cart) {
		int amount = number <= 0 || number > 10 ? 1 : number;
		cart.addOrUpdateItem(linhKien, Quantity.of(amount));
		switch (linhKien.getType()) {
			case MOUSE:
				return "redirect:mouse";
			case KEYBOARD:
			default:
				return "redirect:keyboard";
		}
	}
	
	@GetMapping("/cart")
	String basket() {
		return "cart";
	}
	@PostMapping("/checkout")
	String buy(@ModelAttribute Cart cart, @LoggedIn Optional<UserAccount> userAccount) {

		return userAccount.map(account -> {
			Order order = new Order(account, Cash.CASH);

			cart.addItemsTo(order);

			orderManager.payOrder(order);
			orderManager.completeOrder(order);

			cart.clear();

			return "redirect:/";
		}).orElse("redirect:/cart");
	}

	@GetMapping("/orders")
	@PreAuthorize("hasRole('BOSS')")
	String orders(Model model) {

		model.addAttribute("ordersCompleted", orderManager.findBy(OrderStatus.COMPLETED));

		return "orders";
	}
}
