package com.nadeem.sports;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nadeem.sports.entity.Sports;
import com.nadeem.sports.service.SportsService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {

	@Autowired
	public SportsService sportsService;

	@Test
	public void getSports() throws Exception {

		List<Sports> allSports = sportsService.retrieveSports();
		assertThat(allSports.size()).isEqualTo(5);
	}

	@Test
	public void getSportsById() throws Exception {

		assertThat(sportsService.getSports(1l).getId()).isEqualTo(1);

	}

}
