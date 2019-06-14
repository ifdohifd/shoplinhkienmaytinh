
package shoplinhkienmaytinh.customer;

import java.util.Arrays;

import org.salespointframework.core.DataInitializer;
import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
@Order(10)
class CustomerDataInitializer implements DataInitializer {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerDataInitializer.class);

	private final UserAccountManager userAccountManager;
	private final CustomerRepository customerRepository;

	CustomerDataInitializer(UserAccountManager userAccountManager, CustomerRepository customerRepository) {

		Assert.notNull(customerRepository, "CustomerRepository must not be null!");
		Assert.notNull(userAccountManager, "UserAccountManager must not be null!");

		this.userAccountManager = userAccountManager;
		this.customerRepository = customerRepository;
	}

	@Override
	public void initialize() {
		if (userAccountManager.findByUsername("quanly").isPresent()) {
			return;
		}

		LOG.info("Creating default users and customers.");

		UserAccount bossAccount = userAccountManager.create("quanly", "123456", Role.of("ROLE_BOSS"));
		userAccountManager.save(bossAccount);

		Role customerRole = Role.of("ROLE_CUSTOMER");

		UserAccount ua1 = userAccountManager.create("user1", "123", customerRole);
		UserAccount ua2 = userAccountManager.create("user2", "123", customerRole);
		UserAccount ua3 = userAccountManager.create("user3", "123", customerRole);
		UserAccount ua4 = userAccountManager.create("user4", "123", customerRole);

		Customer c1 = new Customer(ua1, "Cà Mau");
		Customer c2 = new Customer(ua2, "Quy Nhơn");
		Customer c3 = new Customer(ua3, "Nha Trang");
		Customer c4 = new Customer(ua4, "Hà Nội");

		customerRepository.saveAll(Arrays.asList(c1, c2, c3, c4));
	}
}
