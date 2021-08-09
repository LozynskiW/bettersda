package com.betterSDA;

import com.betterSDA.model.Country;
import com.betterSDA.model.RoleEnum;
import com.betterSDA.model.dto.Address;
import com.betterSDA.model.dto.Office;
import com.betterSDA.model.dto.Person;
import com.betterSDA.model.dto.Team;
import com.betterSDA.service.OfficeService;
import com.betterSDA.service.PersonService;
import com.betterSDA.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@SpringBootApplication
@RestController
@RequiredArgsConstructor
@Component
public class DemoApplication {

	private final PersonService personService;
	private final OfficeService officeService;
	private final TeamService teamService;


	@EventListener
	public void onApplicationLoad(ApplicationReadyEvent event) {

		try {
			Office office = officeService.getOffice();
		} catch (IndexOutOfBoundsException ex) {
			createOffice();
		}

		Team team = new Team();
		teamService.addTeam(team);

	}

	@GetMapping("/")
	public ModelAndView displayMainPage() {

		return new ModelAndView("main");
	}

	public void createOffice(){

		Address address = new Address();
		address.setCity("Warszawa");
		address.setCountry(Country.POLAND);
		address.setZipCode("11-500");
		address.setStreet("Złota 44");

		Person person = Person.builder()
				.id(UUID.randomUUID())
				.phoneNumber("123123123")
				.firstName("Software")
				.lastName("Academy")
				.role(RoleEnum.ADMIN)
				.email("sda@academy.pl")
				.address(address) //źródło problemu - id nie zostało wygenerowane
				.teamID("ADMINS")
				.password("test")
				.build();

		personService.addPerson(person);

		Office office = Office.builder()
				.admins(personService.getAllPerson().get(0))
				.name("SDA")
				.build();

		officeService.addOffice(office);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

	}
}