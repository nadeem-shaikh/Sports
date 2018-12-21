package com.nadeem.sports.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nadeem.sports.entity.Sports;
import com.nadeem.sports.service.SportsService;

@RestController
public class SportsRestController {

	@Autowired
	private SportsService sportsService;

	public void setSportsService(SportsService sportsService) {
		this.sportsService = sportsService;
	}

	@GetMapping("/api/sports")
	public List<Sports> getSports() {
		List<Sports> sports = sportsService.retrieveSports();
		return sports;
	}

	@GetMapping("/api/sports/{sportsId}")
	public Sports getSports(@PathVariable(name = "sportsId") Long sportsId) {
		return sportsService.getSports(sportsId);
	}

	@PostMapping("/api/sports")
	public void saveSports(Sports sports) {
		sportsService.saveSports(sports);
		System.out.println("Sports Saved Successfully");
	}

	@DeleteMapping("/api/sports/{sportsId}")
	public void deleteSports(@PathVariable(name = "sportsId") Long sportsId) {
		sportsService.deleteSports(sportsId);
		System.out.println("Sports Deleted Successfully");
	}

	public void updateSports(@RequestBody Sports sports, @PathVariable(name = "sportsId") Long sportsId) {
		Sports sp = sportsService.getSports(sportsId);
		if (sp != null) {
			sportsService.updateSports(sports);
		}

	}

}