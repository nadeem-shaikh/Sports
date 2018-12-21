package com.nadeem.sports.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nadeem.sports.entity.Sports;
import com.nadeem.sports.repository.SportsRepository;
import com.nadeem.sports.service.SportsService;

@Service
public class SportsServiceImpl implements SportsService {

	@Autowired
	private SportsRepository sportsRepository;

	public void setSportsRepository(SportsRepository sportsRepository) {
		this.sportsRepository = sportsRepository;
	}

	public List<Sports> retrieveSports() {
		List<Sports> sports = sportsRepository.findAll();
		return sports;
	}

	public Sports getSports(Long sportsId) {
		Optional<Sports> optSpt = sportsRepository.findById(sportsId);
		return optSpt.get();
	}

	public void saveSports(Sports sports) {
		sportsRepository.save(sports);
	}

	public void deleteSports(Long sportsId) {
		sportsRepository.deleteById(sportsId);
	}

	public void updateSports(Sports sports) {
		sportsRepository.save(sports);
	}
}