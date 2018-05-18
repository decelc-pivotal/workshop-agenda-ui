package io.spring.pivotal.workshop.ui.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.spring.pivotal.workshop.ui.services.Session;
import io.spring.pivotal.workshop.ui.services.WorkshopService;

@Controller
public class WorkshopUIController {

	@Autowired
	WorkshopService workshopService;

	HashMap<String, Session> workshopSessions = new HashMap<String, Session>();

	private final static String INDEX = "index";
	private final static String DAILY_CONTENT = "dailyContent";
	private final static String REQUIREMENTS = "requirements";
	private final static String ENABLEMENT_CONTENT = "enablementContent";

	@RequestMapping("/")
	public String home(Model model) {

		model.addAttribute("workshop", workshopService.workshop());
		model.addAttribute("instructors", workshopService.instructors());
		model.addAttribute("workshopSessions", workshopService.workshopSessions());

		return INDEX;
	}

	@RequestMapping("/dailyContent")
	public String dailyContent(@RequestParam(value = "id", defaultValue = "index") String id, Model model) {

		model.addAttribute("workshopSessions", workshopService.workshopSessions());
		model.addAttribute("dailyContent", workshopService.dailyContent(id));
		model.addAttribute("resources", workshopService.resources());

		return DAILY_CONTENT;
	}

	@RequestMapping("/requirements")
	public String requirements(Model model) {
		model.addAttribute("workshopSessions", workshopService.workshopSessions());
		return REQUIREMENTS;
	}

	@RequestMapping("/content")
	public String content(Model model) {
		model.addAttribute("enablementContent", workshopService.content());
		return ENABLEMENT_CONTENT;
	}

	@RequestMapping("/content2")
	public String content2(Model model) {
		model.addAttribute("enablementContent", workshopService.content());
		return "content2";
	}
}