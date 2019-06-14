
package shoplinhkienmaytinh.customer;

import javax.validation.constraints.NotEmpty;


interface RegistrationForm {

	@NotEmpty(message = "{RegistrationForm.name.NotEmpty}")
	String getName();

	@NotEmpty(message = "{RegistrationForm.password.NotEmpty}")
	String getPassword();

	@NotEmpty(message = "{RegistrationForm.address.NotEmpty}")
	String getAddress();
}
