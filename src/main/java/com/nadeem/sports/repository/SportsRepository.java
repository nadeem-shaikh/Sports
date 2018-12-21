package com.nadeem.sports.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nadeem.sports.entity.Sports;

@Repository
public interface SportsRepository extends JpaRepository<Sports, Long> {

}