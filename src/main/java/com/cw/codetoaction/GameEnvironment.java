package com.cw.codetoaction;

import java.util.List;

import com.cw.models.FighterA;
/* important TODO: think about how user is prohibited from using setters 
 * on other characters but himself; no setters present because user 
 * shouldn't modify env structure
 * TODO make distinction from certain fighter (self) and abstract fighter (other fighters)
 * TODO rewrite this comment
*/ 
public class GameEnvironment {
	private int playersCount;
	private String startDatetime;
	private List<FighterA> fighters;
	
	public GameEnvironment(int playersCount, String startDatetime, List<FighterA> fighters) {
		this.playersCount = playersCount;
		this.startDatetime = startDatetime;
		this.fighters = fighters;
	}
	public List<FighterA> getAllies(FighterA f) {
		return null;
	}
	public List<FighterA> getRivals(FighterA f) {
		return null;
	}
	public int getPlayersCount() {
		return playersCount;
	}
	public String getStartDatetime() {
		return startDatetime;
	}
	
	@Override
	public String toString() {
		return "GameEnvironment [playersCount=" + playersCount 
				+ ", startDatetime=" + startDatetime + ", fighters="
				+ fighters + "]";
	}

}
