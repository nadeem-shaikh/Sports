package com.nadeem.sports.service;

import java.util.List;

import com.nadeem.sports.entity.Sports;

public interface SportsService {
	public List<Sports> retrieveSports();

	public Sports getSports(Long sportsId);

	public void saveSports(Sports sports);

	public void deleteSports(Long sportsId);

	public void updateSports(Sports sports);
}