package hellotvxlet;

/**
 * 
 * @author YannisT
 */

import java.awt.Color;
import java.io.IOException;
import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;
import org.davic.resources.ResourceClient;
import org.davic.resources.ResourceProxy;
import org.havi.ui.HBackgroundConfigTemplate;
import org.havi.ui.HBackgroundConfiguration;
import org.havi.ui.HBackgroundDevice;
import org.havi.ui.HBackgroundImage;
import org.havi.ui.HConfigurationException;
import org.havi.ui.HPermissionDeniedException;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HScreen;
import org.havi.ui.HStillImageBackgroundConfiguration;
import org.havi.ui.HVisible;
import org.havi.ui.event.HBackgroundImageEvent;
import org.havi.ui.event.HBackgroundImageListener;


public class HelloTVXlet implements Xlet, ResourceClient, HBackgroundImageListener
{
    private HScreen screen;
    private HBackgroundDevice bgDev;
    private HStillImageBackgroundConfiguration bgConfig;
    private HBackgroundImage bgImg1;
    
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
        MijnKnop btn1 = new MijnKnop("RoomService", new Color(200, 200, 200, 200));
        btn1.setBounds(180, 0, 135, 75);
        btn1.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        MijnKnop btn2 = new MijnKnop("Faciliteiten", new Color(200, 200, 200, 200));
        btn2.setBounds(315, 0, 135, 75);
        btn2.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        MijnKnop btn3 = new MijnKnop("Omgeving", new Color(200, 200, 200, 200));
        btn3.setBounds(450, 0, 135, 75);
        btn3.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        MijnKnop btn4 = new MijnKnop("Events", new Color(200, 200, 200, 200));
        btn4.setBounds(585, 0, 135, 75);
        btn4.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        // SubMenu
        MijnKnop btn5 = new MijnKnop("SubItem1", new Color(200, 200, 200, 200));
        btn5.setBounds(0, 125, 135, 75);
        btn5.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        MijnKnop btn6 = new MijnKnop("SubItem2", new Color(200, 200, 200, 200));
        btn6.setBounds(0, 200, 135, 75);
        btn6.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        MijnKnop btn7 = new MijnKnop("SubItem3", new Color(200, 200, 200, 200));
        btn7.setBounds(0, 275, 135, 75);
        btn7.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        MijnKnop btn8 = new MijnKnop("SubItem4", new Color(200, 200, 200, 200));
        btn8.setBounds(0, 350, 135, 75);
        btn8.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        // Content
        MijnKnop btn9 = new MijnKnop("Content", new Color(200, 200, 200, 200));
        btn9.setBounds(180, 125, 540, 300);
        btn9.setBackgroundMode(HVisible.BACKGROUND_FILL);
        
        HScene scene = HSceneFactory.getInstance().getDefaultHScene();
        scene.add(btn1);
        scene.add(btn2);
        scene.add(btn3);
        scene.add(btn4);
        scene.add(btn5);
        scene.add(btn6);
        scene.add(btn7);
        scene.add(btn8);
        scene.add(btn9);
        
        scene.validate();
        scene.setVisible(true);
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



    
}