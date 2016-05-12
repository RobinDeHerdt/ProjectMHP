/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.Color;
import org.havi.ui.HState;
import org.havi.ui.HTextButton;

/**
 *
 * @author student
 */
public class MijnKnop extends HTextButton 
{
    private Color initColor;
    
    public MijnKnop(String text, Color kleur)
    {
        initColor = kleur;
        
        this.setTextContent(text, HState.NORMAL_STATE);
        this.setBackground(initColor);
        this.repaint();
    }
    
    public void Select()
    {
        this.setBackground(Color.RED);
        this.repaint();
    }
    public void DeSelect()
    {
        this.setBackground(initColor);
        this.repaint();
    }
}
