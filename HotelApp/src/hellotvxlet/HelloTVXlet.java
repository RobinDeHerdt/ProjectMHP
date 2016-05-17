package hellotvxlet;

/**
 * 
 * @author YannisT
 */

import java.awt.Color;
import java.awt.Image;
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
import org.havi.ui.HTextButton;
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
    
    private String[] btn1subsNames = {"Algemeen", "Dagmenu", "Bestellingen"}; 
    private MijnKnop[] btn1subs = new MijnKnop[btn1subsNames.length];
    
    private String[] btn2subsNames = {"Zwembad", "Tennis", "Welness"}; 
    private MijnKnop[] btn2subs = new MijnKnop[btn2subsNames.length];
    
    private String[] btn3subsNames = {"EiffelToren", "Colloseum", "PisaToren"}; 
    private MijnKnop[] btn3subs = new MijnKnop[btn3subsNames.length];
    
    private String[] btn4subsNames = {"Animatie", "Zumba", "Aquagym"}; 
    private MijnKnop[] btn4subs = new MijnKnop[btn4subsNames.length];
    
    private MijnKnop[] navBar;
    private MijnKnop[][] allSubs;
    
    private String[] txtContent12 = {"Kreeftensoep met balletjes", 
                                     "Foie gras op een bedje van zeewier", 
                                     "Escalope a la primavera",
                                     "Creme brulee"};
    private MijnKnop[] lblContent12 = new MijnKnop[txtContent12.length];
    
    private String[] txtContent13 = {"1 x Fles Martini Royal",
                                     "5 x Portie Kaviaar"};
    private MijnKnop[] lblContent13 = new MijnKnop[txtContent13.length];
    
    private String[] txtContent21 = {"OpeningsUren: 9u - 19u",
                                     "5 x Portie Kaviaar"};
    private MijnKnop[] lblContent21 = new MijnKnop[txtContent21.length];
    
    
    
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
        btn1.addHActionListener(this);
        
        btn2 = new MijnKnop("Faciliteiten", Color.GRAY);
        btn2.setBounds(315, 0, 135, 75);
        btn2.setBackgroundMode(HVisible.BACKGROUND_FILL);
        btn2.addHActionListener(this);
        
        btn3 = new MijnKnop("Omgeving", Color.GRAY);
        btn3.setBounds(450, 0, 135, 75);
        btn3.setBackgroundMode(HVisible.BACKGROUND_FILL);
        btn3.addHActionListener(this);
        
        btn4 = new MijnKnop("Events", Color.GRAY);
        btn4.setBounds(585, 0, 135, 75);
        btn4.setBackgroundMode(HVisible.BACKGROUND_FILL);
        btn4.addHActionListener(this);
        
        navBar = new MijnKnop[]{btn1, btn2, btn3, btn4};
        
        // SubMenu
        for (int i = 0; i < btn1subs.length; i++)
        {
            btn1subs[i] = new MijnKnop(btn1subsNames[i], new Color(200, 200, 200, 200));
            btn1subs[i].setBounds(0, 125 + (i*75), 135, 75);
            btn1subs[i].setBackgroundMode(HVisible.BACKGROUND_FILL);
            btn1subs[i].setVisible(false);
            btn1subs[i].addHActionListener(this);
        }
        for (int i = 0; i < btn2subs.length; i++)
        {
            btn2subs[i] = new MijnKnop(btn2subsNames[i], new Color(200, 200, 200, 200));
            btn2subs[i].setBounds(0, 125 + (i*75), 135, 75);
            btn2subs[i].setBackgroundMode(HVisible.BACKGROUND_FILL);
            btn2subs[i].setVisible(false);
            btn2subs[i].addHActionListener(this);
        }
        for (int i = 0; i < btn3subs.length; i++)
        {
            btn3subs[i] = new MijnKnop(btn3subsNames[i], new Color(200, 200, 200, 200));
            btn3subs[i].setBounds(0, 125 + (i*75), 135, 75);
            btn3subs[i].setBackgroundMode(HVisible.BACKGROUND_FILL);
            btn3subs[i].setVisible(false);
            btn3subs[i].addHActionListener(this);
        }
        for (int i = 0; i < btn4subs.length; i++)
        {
            btn4subs[i] = new MijnKnop(btn4subsNames[i], new Color(200, 200, 200, 200));
            btn4subs[i].setBounds(0, 125 + (i*75), 135, 75);
            btn4subs[i].setBackgroundMode(HVisible.BACKGROUND_FILL);
            btn4subs[i].setVisible(false);
            btn4subs[i].addHActionListener(this);
        }
        
        allSubs = new MijnKnop[][]{btn1subs, btn2subs, btn3subs, btn4subs};
        
        
        // Content
        MijnKnop panelContent = new MijnKnop("", new Color(100, 130, 200, 200));
        panelContent.setBounds(180, 125, 540, 300);
        panelContent.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        MijnKnop txt11 = new MijnKnop("U kan de roomservice bereiken met het nr 6969", new Color(255, 255, 255, 255));
        txt11.setBounds(190, 135, 520, 280);
        txt11.setBordersEnabled(false);
        txt11.setVisible(false);
        
        MijnKnop txt21 = new MijnKnop("OpeningsUren: 9u - 19u", Color.BLACK);
        
        for (int i = 0; i < lblContent12.length; i++)
        {
            lblContent12[i] = new MijnKnop(txtContent12[i], new Color(200, 200, 200, 200));
            lblContent12[i].setBounds(190, 140 + 65*i, 520, 65);
            lblContent12[i].setVisible(false);
            lblContent12[i].setBordersEnabled(false);
        }
        
        // Button Traversals
        btn1.setFocusTraversal(null, btn1subs[0], null, btn2);
        btn2.setFocusTraversal(null, btn2subs[0], btn1, btn3);
        btn3.setFocusTraversal(null, btn3subs[0], btn2, btn4);
        btn4.setFocusTraversal(null, btn4subs[0], btn3, null);
        
        btn1subs[0].setFocusTraversal(btn1, btn1subs[1], null, null);
        btn1subs[1].setFocusTraversal(btn1subs[0], btn1subs[2], null, null);
        btn1subs[2].setFocusTraversal(btn1subs[1], null, null, null);
        
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
        for (int i = 0; i < lblContent12.length; i++)
        {
            scene.add(lblContent12[i]);
        }
        scene.add(txt11); // Z index hoger
        scene.add(panelContent);
        
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
        String btnType = "sub";
        System.out.println(event.getSource());
        MijnKnop pressedBtn = (MijnKnop)event.getSource();
        
        // Is pressedBtn from navbar?
        for (int i = 0; i < navBar.length; i++)
        {
            if (pressedBtn.equals(navBar[i]))
            {
                btnType = "nav";
                hideButtons();
            }
        }
        deselectButtons(btnType);
        pressedBtn.Select();
        System.out.println(pressedBtn.getTextContent(MijnKnop.NORMAL_STATE));
        
        
        if (pressedBtn.equals(btn1))
        {
            showButtons(btn1subs);
        }
        else if (pressedBtn.equals(btn2))
        {
            showButtons(btn2subs);
        }
        else if (pressedBtn.equals(btn3))
        {
            showButtons(btn3subs);
        }
        else if (pressedBtn.equals(btn4))
        {
            showButtons(btn4subs);
        }
        
    }
    
    public void hideButtons()
    {
        for (int i = 0; i < allSubs.length; i++)
        {
            for (int j = 0; j < allSubs[i].length; j++)
            {
                allSubs[i][j].setVisible(false);
            }
        }
    }
    
    public void showButtons(MijnKnop[] subs)
    {
        for (int i = 0; i < subs.length; i++)
        {
            subs[i].setVisible(true);
        }
    }
    
    public void deselectButtons(String type)
    {
        if (type.equals("nav"))
        {
            for (int i = 0; i < navBar.length; i++)
            {
                navBar[i].DeSelect();
            }
        }
        
        for (int i = 0; i < allSubs.length; i++)
        {
            for (int j = 0; j < allSubs[i].length; j++)
            {
                allSubs[i][j].DeSelect();
            }
        }
    }



    
}