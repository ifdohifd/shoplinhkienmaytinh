/*
 * Copyright 2013-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package shoplinhkienmaytinh;

import org.salespointframework.EnableSalespoint;
import org.salespointframework.SalespointSecurityConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableSalespoint
public class ShopLK {

	private static final String LOGIN_ROUTE = "/login";

	public static void main(String[] args) {
		SpringApplication.run(ShopLK.class, args);
	}

	@Configuration
	static class VideoShopWebConfiguration implements WebMvcConfigurer {
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController(LOGIN_ROUTE).setViewName("login");
			registry.addViewController("/").setViewName("index");
		}
	}

	@Configuration
	static class WebSecurityConfiguration extends SalespointSecurityConfiguration {
		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.csrf().disable();

			http.authorizeRequests().antMatchers("/**").permitAll().and().//
					formLogin().loginPage(LOGIN_ROUTE).loginProcessingUrl(LOGIN_ROUTE).and(). //
					logout().logoutUrl("/logout").logoutSuccessUrl("/");
		}
	}
}
