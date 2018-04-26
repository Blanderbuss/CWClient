package com.cw.BattleLogic;

import java.util.Date;
import java.util.List;

/* important
 * on other characters but himself; no setters present because user 
 * shouldn't modify env structure
 *
 *
*/ 
public class GameEnvironment {
	private Date startDatetime;
	private List<? extends FighterI> allies;
	private List<? extends FighterI> enemies;

	public GameEnvironment(Date startDatetime, List<? extends FighterI> allies, List<? extends FighterI> enemies) {
		this.startDatetime = startDatetime;
		this.allies = allies;
		this.enemies = enemies;
	}

	public List<? extends FighterI> getAllies() {
		return allies;
	}
	public List<? extends FighterI> getEnemies() {
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
