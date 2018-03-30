package com.cw.models;

import java.util.Date;
import java.util.List;

/* important TODO: think about how user is prohibited from using setters
 * on other characters but himself; no setters present because user 
 * shouldn't modify env structure
 * TODO make distinction from certain fighter (self) and abstract fighter (other fighters)
 * TODO rewrite this comment
*/ 
public class GameEnvironment {
	private Date startDatetime;
	private List<Fighter> allies;
	private List<Fighter> enemies;

	public GameEnvironment(Date startDatetime, List<Fighter> allies, List<Fighter> enemies) {
		this.startDatetime = startDatetime;
		this.allies = allies;
		this.enemies = enemies;
	}

	public List<Fighter> getAllies() {
		return allies;
	}
	public List<Fighter> getEnemies() {
		return enemies;
	}
	public Date getStartDatetime() {
		return startDatetime;
	}

	@Override
	public String toString() {
		return "GameEnvironment{" +
				"startDatetime='" + startDatetime + '\'' +
				", allies=" + allies +
				", enemies=" + enemies +
				'}';
	}
}
