/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.Color;
import org.havi.ui.HTextButton;

/**
 *
 * @author student
 */
public class MijnKnop extends HTextButton implements ObserverInterface {
    Color kleur = Color.RED;
    public void update(int tijd) {
        if (kleur==Color.RED) kleur=Color.GREEN; 
        else if (kleur==Color.GREEN) kleur=Color.BLUE;
        else kleur=Color.RED;
        this.setBackground(kleur);
        this.repaint();
    }

}
