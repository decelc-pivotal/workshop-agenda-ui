package io.spring.pivotal.workshop.ui.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class WorkshopService {

	private static Logger log = LoggerFactory.getLogger(WorkshopService.class);

	@Autowired
	RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "fallbackWorkshop")
	public Workshop workshop() {
		return restTemplate.getForObject("http://workshop/workshop", Workshop.class);
	}

	@HystrixCommand(fallbackMethod = "fallbackWorkshopSessions")
	public List<Session> workshopSessions() {
		return restTemplate.getForObject("http://workshop/workshopSessions", List.class);
	}

	@HystrixCommand(fallbackMethod = "fallbackDailyContent")
	public Session dailyContent(String id) {
		String url = "http://workshop/dailyContent";

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("id", id);

		return restTemplate.getForObject(builder.toUriString(), Session.class);
	}

	@HystrixCommand(fallbackMethod = "fallbackResources")
	public List<Resource> resources() {
		return restTemplate.getForObject("http://workshop/resources", List.class);
	}

	@HystrixCommand(fallbackMethod = "fallbackInstructors")
	public List<Instructor> instructors() {
		return restTemplate.getForObject("http://workshop/instructor", List.class);
	}

	@HystrixCommand(fallbackMethod = "fallbackContent")
	public List<Content> content() {
		return restTemplate.getForObject("http://workshop/content", List.class);
	}

	private Workshop fallbackWorkshop() {
		log.info("fallbackWorkshop");
		return new Workshop();
	}

	private List<Session> fallbackWorkshopSessions() {
		log.info("fallbackWorkshopSessions");
		return new ArrayList<Session>();
	}

	private Session fallbackDailyContent(String id) {
		log.info("fallbackDailyContent");
		return new Session();
	}

	private List<Resource> fallbackResources() {
		log.info("fallbackResources");
		return new ArrayList<Resource>();
	}

	private List<Instructor> fallbackInstructors() {

		log.info("fallbackInstructors");
		return new ArrayList<Instructor>();
	}

	private List<Content> fallbackContent() {
		log.info("fallbackContent");
		return new ArrayList<Content>();
	}
}
