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
    
    //////////////////////// BUTTONS VARIABLES \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
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
    ////////////////////////////// END \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    
    ///////////////////// CONTENT LABEL VARIABLES \\\\\\\\\\\\\\\\\\\\\\\\\
    // ----- Content 1 ----- \\
    private String[] txtContent11 = {"U kan de roomservice bereiken met het nr 1234"};
    private MijnKnop[] lblContent11 = new MijnKnop[txtContent11.length];
    private String[] txtContent12 = {"Kreeftensoep met balletjes", 
                                     "Foie gras op een bedje van zeewier", 
                                     "Escalope a la primavera",
                                     "Creme brulee"};
    private MijnKnop[] lblContent12 = new MijnKnop[txtContent12.length];
    private String[] txtContent13 = {"1 x Fles Martini Royal",
                                     "5 x Portie Kaviaar"};
    private MijnKnop[] lblContent13 = new MijnKnop[txtContent13.length];
    private String[][] txtContent1 = {txtContent11, txtContent12, txtContent13};
    private MijnKnop[][] lblContent1 = {lblContent11, lblContent12, lblContent13};
    
    // ----- Content 2 ----- \\
    private String[] txtContent21 = {"OpeningsUren: 9u - 19u",
                                     "Gebruik je sleutelkaart voor de whirlpool"};
    private MijnKnop[] lblContent21 = new MijnKnop[txtContent21.length];
    private String[] txtContent22 = {"OpeningsUren: 10u - 17u",
                                     "Tennisgerei te verkrijgen aan balie"};
    private MijnKnop[] lblContent22 = new MijnKnop[txtContent22.length];
    private String[] txtContent23 = {"OpeningsUren: 8u - 11u30, 13u - 16u30",
                                     "Enkel op afspraak via balie"};
    private MijnKnop[] lblContent23 = new MijnKnop[txtContent23.length];
    private String[][] txtContent2 = {txtContent21, txtContent22, txtContent23};
    private MijnKnop[][] lblContent2 = {lblContent21, lblContent22, lblContent23};
    
    // ----- Content 3 ----- \\
    private String[] txtContent31 = {"NOG IN TE VULLEN",
                                     "NOG iN TE VULLEN"};
    private MijnKnop[] lblContent31 = new MijnKnop[txtContent31.length];
    private String[] txtContent32 = {"NOG IN TE VULLEN",
                                     "NOG IN TE VULLEN"};
    private MijnKnop[] lblContent32 = new MijnKnop[txtContent32.length];
    private String[] txtContent33 = {"NOG IN TE VULLEN",
                                     "NOG IN TE VULLEN"};
    private MijnKnop[] lblContent33 = new MijnKnop[txtContent33.length];
    private String[][] txtContent3 = {txtContent31, txtContent32, txtContent33};
    private MijnKnop[][] lblContent3 = {lblContent31, lblContent32, lblContent33};
    
    // ----- Content 4 ----- \\
    private String[] txtContent41 = {"NOG IN TE VULLEN",
                                     "NOG IN TE VULLEN"};
    private MijnKnop[] lblContent41 = new MijnKnop[txtContent41.length];
    private String[] txtContent42 = {"NOG IN TE VULLEN",
                                     "NOG IN TE VULLEN"};
    private MijnKnop[] lblContent42 = new MijnKnop[txtContent42.length];
    private String[] txtContent43 = {"NOG IN TE VULLEN",
                                     "NOG IN TE VULLEN"};
    private MijnKnop[] lblContent43 = new MijnKnop[txtContent43.length];
    private String[][] txtContent4 = {txtContent41, txtContent42, txtContent43};
    private MijnKnop[][] lblContent4 = {lblContent41, lblContent42, lblContent43};
    
    private MijnKnop[][][] allContent = {lblContent1, lblContent2, lblContent3, lblContent4};
    ////////////////////////////// END \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    
    
    public void destroyXlet(boolean unconditional) throws XletStateChangeException {
    }

    public void initXlet(XletContext ctx) throws XletStateChangeException 
    {
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
          
        /////////////////////// NAVBAR DECLARATION \\\\\\\\\\\\\\\\\\\\\\\\\\\\
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
        ////////////////////////////// END \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        
        /////////////////////// SUBMENU DECLARATION \\\\\\\\\\\\\\\\\\\\\\\\\\\
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
        ////////////////////////////// END \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        
        /////////////////////// CONTENT DECLARATION \\\\\\\\\\\\\\\\\\\\\\\\\\\
        MijnKnop panelContent = new MijnKnop("", new Color(100, 130, 200, 200));
        panelContent.setBounds(180, 125, 540, 300);
        panelContent.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        for (int i = 0; i < lblContent1.length; i++)
        {
            for (int j = 0; j < lblContent1[i].length; j++)
            {
                lblContent1[i][j] = new MijnKnop(txtContent1[i][j], Color.BLACK);
                lblContent1[i][j].setBounds(190, 135 + 280 / lblContent1[i].length * j, 520, 280 / lblContent1[i].length);
                lblContent1[i][j].setVisible(false);
                lblContent1[i][j].setBordersEnabled(false);
            }
        }
        for (int i = 0; i < lblContent2.length; i++)
        {
            for (int j = 0; j < lblContent2[i].length; j++)
            {
                lblContent2[i][j] = new MijnKnop(txtContent2[i][j], Color.BLACK);
                lblContent2[i][j].setBounds(190, 135 + 280 / lblContent2[i].length * j, 520, 280 / lblContent2[i].length);
                lblContent2[i][j].setVisible(false);
                lblContent2[i][j].setBordersEnabled(false);
            }
        }
        for (int i = 0; i < lblContent3.length; i++)
        {
            for (int j = 0; j < lblContent3[i].length; j++)
            {
                lblContent3[i][j] = new MijnKnop(txtContent3[i][j], Color.BLACK);
                lblContent3[i][j].setBounds(190, 135 + 280 / lblContent3[i].length * j, 520, 280 / lblContent3[i].length);
                lblContent3[i][j].setVisible(false);
                lblContent3[i][j].setBordersEnabled(false);
            }
        }
        for (int i = 0; i < lblContent4.length; i++)
        {
            for (int j = 0; j < lblContent4[i].length; j++)
            {
                lblContent4[i][j] = new MijnKnop(txtContent4[i][j], Color.BLACK);
                lblContent4[i][j].setBounds(190, 135 + 280 / lblContent4[i].length * j, 520, 280 / lblContent4[i].length);
                lblContent4[i][j].setVisible(false);
                lblContent4[i][j].setBordersEnabled(false);
            }
        }
        
        ////////////////////////////// END \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        
        /////////////////////// BUTTON TRAVERSALS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\
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
        ////////////////////////////// END \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        
        //////////////////////// SCENE DECLARATION \\\\\\\\\\\\\\\\\\\\\\\\\\\\
        // ----- Navbar ----- \\
        HScene scene = HSceneFactory.getInstance().getDefaultHScene();
        scene.add(btn1);
        scene.add(btn2);
        scene.add(btn3);
        scene.add(btn4);
        
        // ----- Subs ----- \\
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
        
        // ----- Content ----- \\
        for (int i = 0; i < lblContent1.length; i++)
        {
            for (int j = 0; j < lblContent1[i].length; j++)
            {
                scene.add(lblContent1[i][j]);
            }
        }
        for (int i = 0; i < lblContent2.length; i++)
        {
            for (int j = 0; j < lblContent2[i].length; j++)
            {
                scene.add(lblContent2[i][j]);
            }
        }
        for (int i = 0; i < lblContent3.length; i++)
        {
            for (int j = 0; j < lblContent3[i].length; j++)
            {
                scene.add(lblContent3[i][j]);
            }
        }
        for (int i = 0; i < lblContent4.length; i++)
        {
            for (int j = 0; j < lblContent4[i].length; j++)
            {
                scene.add(lblContent4[i][j]);
            }
        }
        scene.add(panelContent);
        
        // ----- Init ----- \\
        scene.validate();
        scene.setVisible(true);
        btn1.requestFocus();
        ////////////////////////////// END \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    }

    public void startXlet() throws XletStateChangeException 
    {
        /////////////////////// BACKGROUND IMAGE \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
        bgImg1=new HBackgroundImage("background.jpg");
        bgImg1.load(this);
    }
    public void imageLoaded(HBackgroundImageEvent e) 
    {
        System.out.println("Image geladen");
        try 
        {
            bgConfig.displayImage(bgImg1);
        } catch (Exception ex) 
        {
            ex.printStackTrace();
        }
    }

    public void imageLoadFailed(HBackgroundImageEvent e) 
    {
        System.out.println("Image mislukt");
        ////////////////////////////// END \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
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
        ////////////////////// ON BUTTON CLICK \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
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
        hideContent();
        deselectButtons(btnType);
        pressedBtn.Select();
        System.out.println(pressedBtn.getTextContent(MijnKnop.NORMAL_STATE));
        
        if (btnType.equals("nav"))
        {
            if (pressedBtn.equals(btn1))
            {
                showObjects(btn1subs);
            }
            else if (pressedBtn.equals(btn2))
            {
                showObjects(btn2subs);
            }
            else if (pressedBtn.equals(btn3))
            {
                showObjects(btn3subs);
            }
            else if (pressedBtn.equals(btn4))
            {
                showObjects(btn4subs);
            }
        }
        else // if sub
        {
            int navIndex = 0;
            int subIndex = 0;
            
            for (int i = 0; i < allSubs.length; i++)
            {
                for (int j = 0; j < allSubs[i].length; j++)
                {
                    if (pressedBtn.equals(allSubs[i][j]))
                    {
                        navIndex = i;
                        subIndex = j;
                        break;
                    }
                }
            }
            showObjects(allContent[navIndex][subIndex]);
        }
        
        ////////////////////////////// END \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
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
    
    public void hideContent()
    {
        for (int i = 0; i < allContent.length; i++)
        {
            for (int j = 0; j < allContent[i].length; j++)
            {
                for (int k = 0; k < allContent[i][j].length; k++)
                {
                    allContent[i][j][k].setVisible(false);
                }
            }
        }
    }
    
    public void showObjects(MijnKnop[] objects)
    {
        for (int i = 0; i < objects.length; i++)
        {
            objects[i].setVisible(true);
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