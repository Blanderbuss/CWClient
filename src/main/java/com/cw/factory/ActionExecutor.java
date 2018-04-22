package com.cw.factory;
import com.cw.BattleLogic.FighterI;
import com.cw.BattleLogic.GameEnvironment;
import com.cw.entities.Tuple;
import org.springframework.stereotype.Service;

@Service
public interface ActionExecutor {
    Tuple<FighterI.Action,FighterI> selectAction(FighterI self, GameEnvironment env);
}
