package hellotvxlet;

/**
 * 
 * @author YannisT
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;
import org.davic.resources.ResourceClient;
import org.davic.resources.ResourceProxy;
import org.havi.ui.HBackgroundConfigTemplate;
import org.havi.ui.HBackgroundDevice;
import org.havi.ui.HBackgroundImage;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HScreen;
import org.havi.ui.HStillImageBackgroundConfiguration;
import org.havi.ui.HVisible;
import org.havi.ui.event.HActionListener;
import org.havi.ui.event.HBackgroundImageEvent;
import org.havi.ui.event.HBackgroundImageListener;


public class HelloTVXlet implements Xlet, ResourceClient, HBackgroundImageListener, HActionListener
{
    private HScreen screen;
    private HBackgroundDevice bgDev;
    private HStillImageBackgroundConfiguration bgConfig;
    private HBackgroundImage bgImg1;
    private MijnKnop btn1, btn2, btn3, btn4;
    
    private String[] btn1subsNames = {"Dagmenu", "Eten", "Drinken", "Bestellingen"}; 
    private MijnKnop[] btn1subs = new MijnKnop[btn1subsNames.length];
    
    private String[] btn2subsNames = {"Zwembad", "Tennis", "Welness"}; 
    private MijnKnop[] btn2subs = new MijnKnop[btn2subsNames.length];
    
    private String[] btn3subsNames = {"EiffelToren", "Colloseum", "PisaToren"}; 
    private MijnKnop[] btn3subs = new MijnKnop[btn3subsNames.length];
    
    private String[] btn4subsNames = {"Animatie", "Zumba", "Aquagym"}; 
    private MijnKnop[] btn4subs = new MijnKnop[btn4subsNames.length];
    
    public void destroyXlet(boolean unconditional) throws XletStateChangeException {
    }

    public void initXlet(XletContext ctx) throws XletStateChangeException {
        screen=HScreen.getDefaultHScreen();
        bgDev=screen.getDefaultHBackgroundDevice();
        bgDev.reserveDevice(this);
        HBackgroundConfigTemplate bgConfigTemplate =new HBackgroundConfigTemplate();
        bgConfigTemplate.setPreference(HBackgroundConfigTemplate.STILL_IMAGE, 
                HBackgroundConfigTemplate.REQUIRED);
        try {
            bgConfig=(HStillImageBackgroundConfiguration)bgDev.getBestConfiguration(bgConfigTemplate);
            bgDev.setBackgroundConfiguration(bgConfig);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
             
        // NavBar
        btn1 = new MijnKnop("RoomService", Color.GRAY);
        btn1.setBounds(180, 0, 135, 75);
        btn1.setBackgroundMode(HVisible.BACKGROUND_FILL);
        btn1.setActionCommand("btn1click");
        btn1.addHActionListener(this);
        
        btn2 = new MijnKnop("Faciliteiten", Color.GRAY);
        btn2.setBounds(315, 0, 135, 75);
        btn2.setBackgroundMode(HVisible.BACKGROUND_FILL);
        btn2.setActionCommand("btn2click");
        btn2.addHActionListener(this);
        
        btn3 = new MijnKnop("Omgeving", Color.GRAY);
        btn3.setBounds(450, 0, 135, 75);
        btn3.setBackgroundMode(HVisible.BACKGROUND_FILL);
        btn3.setActionCommand("btn3click");
        btn3.addHActionListener(this);
        
        btn4 = new MijnKnop("Events", Color.GRAY);
        btn4.setBounds(585, 0, 135, 75);
        btn4.setBackgroundMode(HVisible.BACKGROUND_FILL);
        btn4.setActionCommand("btn4click");
        btn4.addHActionListener(this);
        
        // SubMenu
        for (int i = 0; i < btn1subs.length; i++)
        {
            btn1subs[i] = new MijnKnop(btn1subsNames[i], new Color(200, 200, 200, 200));
            btn1subs[i].setBounds(0, 125 + (i*75), 135, 75);
            btn1subs[i].setBackgroundMode(HVisible.BACKGROUND_FILL);
            btn1subs[i].setVisible(false);
        }
        for (int i = 0; i < btn2subs.length; i++)
        {
            btn2subs[i] = new MijnKnop(btn2subsNames[i], new Color(200, 200, 200, 200));
            btn2subs[i].setBounds(0, 125 + (i*75), 135, 75);
            btn2subs[i].setBackgroundMode(HVisible.BACKGROUND_FILL);
            btn2subs[i].setVisible(false);
        }
        for (int i = 0; i < btn3subs.length; i++)
        {
            btn3subs[i] = new MijnKnop(btn3subsNames[i], new Color(200, 200, 200, 200));
            btn3subs[i].setBounds(0, 125 + (i*75), 135, 75);
            btn3subs[i].setBackgroundMode(HVisible.BACKGROUND_FILL);
            btn3subs[i].setVisible(false);
        }
        for (int i = 0; i < btn4subs.length; i++)
        {
            btn4subs[i] = new MijnKnop(btn4subsNames[i], new Color(200, 200, 200, 200));
            btn4subs[i].setBounds(0, 125 + (i*75), 135, 75);
            btn4subs[i].setBackgroundMode(HVisible.BACKGROUND_FILL);
            btn4subs[i].setVisible(false);
        }
//        btn11 = new MijnKnop("SubItem1", new Color(200, 200, 200, 200));
//        btn11.setBounds(0, 125, 135, 75);
//        btn11.setBackgroundMode(HVisible.BACKGROUND_FILL);
//        btn11.setVisible(false);
//        
//        btn12 = new MijnKnop("SubItem2", new Color(200, 200, 200, 200));
//        btn12.setBounds(0, 200, 135, 75);
//        btn12.setBackgroundMode(HVisible.BACKGROUND_FILL);
//        btn12.setVisible(false);
//        
//        btn13 = new MijnKnop("SubItem3", new Color(200, 200, 200, 200));
//        btn13.setBounds(0, 275, 135, 75);
//        btn13.setBackgroundMode(HVisible.BACKGROUND_FILL);
//        btn13.setVisible(false);
//        
//        btn14 = new MijnKnop("SubItem4", new Color(200, 200, 200, 200));
//        btn14.setBounds(0, 350, 135, 75);
//        btn14.setBackgroundMode(HVisible.BACKGROUND_FILL);
//        btn14.setVisible(false);
        
        
        // Content
        MijnKnop btn9 = new MijnKnop("Content", new Color(200, 200, 200, 200));
        btn9.setBounds(180, 125, 540, 300);
        btn9.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        // Button Traversals
        btn1.setFocusTraversal(null, btn1subs[0], null, btn2);
        btn2.setFocusTraversal(null, btn1subs[0], btn1, btn3);
        btn3.setFocusTraversal(null, btn1subs[0], btn2, btn4);
        btn4.setFocusTraversal(null, btn1subs[0], btn3, null);
        
        btn1subs[0].setFocusTraversal(btn1, btn1subs[1], null, null);
        btn1subs[1].setFocusTraversal(btn1subs[0], btn1subs[2], null, null);
        btn1subs[2].setFocusTraversal(btn1subs[1], btn1subs[3], null, null);
        btn1subs[3].setFocusTraversal(btn1subs[2], null, null, null);
        
        btn2subs[0].setFocusTraversal(btn2, btn2subs[1], null, null);
        btn2subs[1].setFocusTraversal(btn2subs[0], btn2subs[2], null, null);
        btn2subs[2].setFocusTraversal(btn2subs[1], null, null, null);
        
        btn3subs[0].setFocusTraversal(btn3, btn3subs[1], null, null);
        btn3subs[1].setFocusTraversal(btn3subs[0], btn3subs[2], null, null);
        btn3subs[2].setFocusTraversal(btn3subs[1], null, null, null);
        
        btn4subs[0].setFocusTraversal(btn4, btn4subs[1], null, null);
        btn4subs[1].setFocusTraversal(btn4subs[0], btn4subs[2], null, null);
        btn4subs[2].setFocusTraversal(btn4subs[1], null, null, null);
        
        HScene scene = HSceneFactory.getInstance().getDefaultHScene();
        scene.add(btn1);
        scene.add(btn2);
        scene.add(btn3);
        scene.add(btn4);
        for (int i = 0; i < btn1subs.length; i++)
        {
            scene.add(btn1subs[i]);
        }
        for (int i = 0; i < btn2subs.length; i++)
        {
            scene.add(btn2subs[i]);
        }
        for (int i = 0; i < btn3subs.length; i++)
        {
            scene.add(btn3subs[i]);
        }
        for (int i = 0; i < btn4subs.length; i++)
        {
            scene.add(btn4subs[i]);
        }
        scene.add(btn9);
        
        scene.validate();
        scene.setVisible(true);
        btn1.requestFocus();
    }

    public void startXlet() throws XletStateChangeException {
        bgImg1=new HBackgroundImage("background.jpg");
        bgImg1.load(this);

        
    }
    public void imageLoaded(HBackgroundImageEvent e) {
        System.out.println("Image geladen");
       try {
            bgConfig.displayImage(bgImg1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void imageLoadFailed(HBackgroundImageEvent e) {
        System.out.println("Image mislukt");
    }
    
    public void pauseXlet() {
    }
    
    public boolean requestRelease(ResourceProxy proxy, Object requestData) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void release(ResourceProxy proxy) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void notifyRelease(ResourceProxy proxy) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void actionPerformed(ActionEvent event) 
    {
        if (event.getActionCommand().equals("btn1click"))
        {
            btn1.Select();
            btn2.DeSelect();
            btn3.DeSelect();
            btn4.DeSelect();
            
            for (int i = 0; i < btn1subs.length; i++)
            {
                btn1subs[i].setVisible(true);
            }
            for (int i = 0; i < btn2subs.length; i++)
            {
                btn2subs[i].setVisible(false);
            }
            for (int i = 0; i < btn3subs.length; i++)
            {
                btn3subs[i].setVisible(false);
            }
            for (int i = 0; i < btn4subs.length; i++)
            {
                btn4subs[i].setVisible(false);
            }
            
            btn1subs[0].requestFocus();
        }
        else if (event.getActionCommand().equals("btn2click"))
        {
            btn1.DeSelect();
            btn2.Select();
            btn3.DeSelect();
            btn4.DeSelect();
            
            for (int i = 0; i < btn1subs.length; i++)
            {
                btn1subs[i].setVisible(false);
            }
            for (int i = 0; i < btn2subs.length; i++)
            {
                btn2subs[i].setVisible(true);
            }
            for (int i = 0; i < btn3subs.length; i++)
            {
                btn3subs[i].setVisible(false);
            }
            for (int i = 0; i < btn4subs.length; i++)
            {
                btn4subs[i].setVisible(false);
            }
            
            btn2subs[0].requestFocus();
        }
        else if (event.getActionCommand().equals("btn3click"))
        {
            btn1.DeSelect();
            btn2.DeSelect();
            btn3.Select();
            btn4.DeSelect();
            
            for (int i = 0; i < btn1subs.length; i++)
            {
                btn1subs[i].setVisible(false);
            }
            for (int i = 0; i < btn2subs.length; i++)
            {
                btn2subs[i].setVisible(false);
            }
            for (int i = 0; i < btn3subs.length; i++)
            {
                btn3subs[i].setVisible(true);
            }
            for (int i = 0; i < btn4subs.length; i++)
            {
                btn4subs[i].setVisible(false);
            }
            
            btn3subs[0].requestFocus();
        }
        else if (event.getActionCommand().equals("btn4click"))
        {
            btn1.DeSelect();
            btn2.DeSelect();
            btn3.DeSelect();
            btn4.Select();
            
            for (int i = 0; i < btn1subs.length; i++)
            {
                btn1subs[i].setVisible(false);
            }
            for (int i = 0; i < btn2subs.length; i++)
            {
                btn2subs[i].setVisible(false);
            }
            for (int i = 0; i < btn3subs.length; i++)
            {
                btn3subs[i].setVisible(false);
            }
            for (int i = 0; i < btn4subs.length; i++)
            {
                btn4subs[i].setVisible(true);
            }
            
            btn4subs[0].requestFocus();
        }
    }



    
}