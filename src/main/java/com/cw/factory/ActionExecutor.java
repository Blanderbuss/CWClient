package com.cw.factory;
import com.cw.BattleLogic.Fighter;
import com.cw.BattleLogic.GameEnvironment;
import org.springframework.stereotype.Service;

@Service
public interface ActionExecutor {
    Fighter.ActTarget doAction(Fighter self, GameEnvironment env);
}
