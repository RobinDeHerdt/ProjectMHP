package hellotvxlet;

/**
 * 
 * @author YannisT
 */

import javax.tv.xlet.*;
import java.awt.event.*;
import org.davic.resources.ResourceClient;
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


public class HelloTVXlet implements Xlet, HActionListener {
 
    private HScreen screen;
    private HBackgroundDevice bgDev;
    private HStillImageBackgroundConfiguration bgConfig;
    private HBackgroundImage bgImg;
  
    public HelloTVXlet() {
        
    }

    public void initXlet(XletContext context) throws XletStateChangeException {
//      Observer ob1 = new Observer();
//      Observer ob2 = new Observer();
//      Observer ob3 = new Observer();
//      Subject sub = new Subject();
//      sub.register(ob1); sub.register(ob2); sub.register(ob3);
        
        screen=HScreen.getDefaultHScreen();
        bgDev=screen.getDefaultHBackgroundDevice();
        bgDev.reserveDevice((ResourceClient) this);
        HBackgroundConfigTemplate bgConfigTemplate =new HBackgroundConfigTemplate();
        bgConfigTemplate.setPreference(HBackgroundConfigTemplate.STILL_IMAGE,
                HBackgroundConfigTemplate.REQUIRED);
        try
        {
            bgConfig=(HStillImageBackgroundConfiguration)bgDev.getBestConfiguration(bgConfigTemplate);
            bgDev.setBackgroundConfiguration(bgConfig);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        
        
      HScene scene=HSceneFactory.getInstance().getDefaultHScene();
      
      scene.validate(); scene.setVisible(true);
    }

    public void startXlet() throws XletStateChangeException 
    {
        System.out.println("Start");
        bgImg=new HBackgroundImage("background.jpg");
        bgImg.load((HBackgroundImageListener) this);
    }
    
    public void imageLoaded(HBackgroundImageEvent e)
    {
        System.out.println("Image geladen");
        try
        {
            bgConfig.displayImage(bgImg);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void imageLoadFailed(HBackgroundImageEvent e)
    {
        System.out.println("Image failed");
    }

    public void pauseXlet() {
     
    }

    public void destroyXlet(boolean unconditional) {
     
    }

    public void actionPerformed(ActionEvent e) {
        
    }
}
