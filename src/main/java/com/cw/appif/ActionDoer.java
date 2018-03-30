package com.cw.appif;
import com.cw.models.Fighter;
import com.cw.models.GameEnvironment;
import org.springframework.stereotype.Service;

@Service
public interface ActionDoer {
    Fighter.ActTarget doAction(Fighter self, GameEnvironment env);
}
