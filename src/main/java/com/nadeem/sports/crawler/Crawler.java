package com.nadeem.sports.crawler;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.nadeem.sports.entity.Sports;
import com.nadeem.sports.service.SportsService;

@PropertySource(ignoreResourceNotFound = true, value = "classpath:application.properties")

@Component
public class Crawler {
	
	@Value("${crawlUrl}")
	String CRAWL_URL;

	@Autowired
	private SportsService sportsService;

	@EventListener(ApplicationReadyEvent.class)
	public void loadAfterStartup() {

		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		chromeOptions.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
		chromeOptions.addArguments("headless");
		ChromeDriver driver = new ChromeDriver(chromeOptions);

		try {

			driver.get(CRAWL_URL);

			Document doc = Jsoup.parse(driver.getPageSource());

			String gameType = doc.getElementsByClass("automated-header").get(0).text();

			String gameDate = doc.getElementsByClass("scoreboards").get(0).text();

			for (Element e : doc.select("article[class=scoreboard basketball final home-winner js-show]")) {
				Sports sports = new Sports();

				sports.setGame(gameType);
				sports.setDate(StringUtils.substringBetween(gameDate, "Scores for ", "Auto"));

				Element e1 = e.getElementsByClass("away").get(0);
				String team1 = e1.getElementsByClass("sb-team-short").get(0).text();
				sports.setTeam1(team1);
				int team1total = Integer.parseInt(e1.getElementsByClass("total").get(0).text());
				sports.setTeam1Score(team1total);

				Element e2 = e.getElementsByClass("home").get(0);
				String team2 = e2.getElementsByClass("sb-team-short").get(0).text();
				sports.setTeam2(team2);
				int team2total = Integer.parseInt(e2.getElementsByClass("total").get(0).text());
				sports.setTeam2Score(team2total);

				String narration = e.getElementsByClass("caption-wrapper").get(0).text();
				sports.setNarration(narration);

				if (team1total < 0) {
					sports.setStatus("Going On");

				} else {
					sports.setStatus("Completed");

				}

				sportsService.saveSports(sports);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}
}
