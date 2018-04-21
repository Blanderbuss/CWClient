package com.cw.factory;
import com.cw.BattleLogic.Fighter;
import com.cw.BattleLogic.GameEnvironment;
import com.cw.entities.Tuple;
import org.springframework.stereotype.Service;

@Service
public interface ActionExecutor {
    Tuple<Fighter.Action,Fighter> doAction(Fighter self, GameEnvironment env);
}
