package shoplinhkienmaytinh.customer;

import org.salespointframework.useraccount.Role;
import org.salespointframework.useraccount.UserAccount;
import org.salespointframework.useraccount.UserAccountManager;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
@Transactional
public class CustomerManagement {

	private final CustomerRepository customers;
	private final UserAccountManager userAccounts;

	CustomerManagement(CustomerRepository customers, UserAccountManager userAccounts) {

		Assert.notNull(customers, "CustomerRepository must not be null!");
		Assert.notNull(userAccounts, "UserAccountManager must not be null!");

		this.customers = customers;
		this.userAccounts = userAccounts;
	}
	public Customer createCustomer(RegistrationForm form) {

		Assert.notNull(form, "Registration form must not be null!");

		UserAccount userAccount = userAccounts.create(form.getName(), form.getPassword(), Role.of("ROLE_CUSTOMER"));

		return customers.save(new Customer(userAccount, form.getAddress()));
	}
	public Streamable<Customer> findAll() {
		return Streamable.of(customers.findAll());
	}
}
