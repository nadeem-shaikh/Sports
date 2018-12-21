package com.nadeem.sports.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "sports")
public class Sports {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "game")
	private String game;

	@Column(name = "team1")
	private String team1;

	@Column(name = "team1_score")
	private Integer team1Score;

	@Column(name = "team2")
	private String team2;

	@Column(name = "team2_score")
	private Integer team2Score;

	@Column(name = "date")
	private String date;

	@Column(name = "narration")
	private String narration;

	@Column(name = "status")
	private String status;

	@Column(name = "created_at")
	private Date createdAt;

	@PrePersist
	public void addTimestamp() {
		createdAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getTeam1() {
		return team1;
	}

	public void setTeam1(String team1) {
		this.team1 = team1;
	}

	public Integer getTeam1Score() {
		return team1Score;
	}

	public void setTeam1Score(Integer team1Score) {
		this.team1Score = team1Score;
	}

	public String getTeam2() {
		return team2;
	}

	public void setTeam2(String team2) {
		this.team2 = team2;
	}

	public Integer getTeam2Score() {
		return team2Score;
	}

	public void setTeam2Score(Integer team2Score) {
		this.team2Score = team2Score;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}