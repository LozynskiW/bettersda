package com.betterSDA;

import com.betterSDA.model.Country;
import com.betterSDA.model.RoleEnum;
import com.betterSDA.model.dto.Address;
import com.betterSDA.model.dto.Office;
import com.betterSDA.model.dto.Person;
import com.betterSDA.service.AddressService;
import com.betterSDA.service.OfficeService;
import com.betterSDA.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@RestController
@RequiredArgsConstructor
public class DemoApplication {

	private final PersonService personService;
	private final AddressService addressService;
	private final OfficeService officeService;

	@GetMapping("/")
	public ModelAndView displayMainPage() {


		try {
			Office office = officeService.getOffice();
		} catch (IndexOutOfBoundsException ex) {
			createOffice();
		}

		return new ModelAndView("main");
	}

	public void createOffice(){

		Address address = Address.builder()
				.country(Country.POLAND)
				.zipCode("11-500")
				.city("Warszawa")
				.street("Złota")
				.id(0L)
				.build();

		addressService.addAddress(address);

		Person person = Person.builder()
				.phoneNumber("123123123")
				.id(0L)
				.firstName("Software")
				.lastName("Academy")
				.role(RoleEnum.ADMIN)
				.email("sda@academy.pl")
				.address(addressService.getAddressById(0L))
				.teamID("ADMINS")
				.build();

		personService.addPerson(person);

		Office office = Office.builder()
				.id(0L)
				.admins(personService.getPersonById(0L))
				.name("SDA")
				.build();

		officeService.addOffice(office);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);


	}
}