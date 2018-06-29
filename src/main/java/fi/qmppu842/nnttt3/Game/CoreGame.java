/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.qmppu842.nnttt3.Game;

import lombok.Getter;

/**
 *
 * @author Qmppu842
 */
public class CoreGame {
    private @Getter GameStateBean state;

    public CoreGame() {
        state = new GameStateBean();
    }
    
    


}
