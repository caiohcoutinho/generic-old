package gurps.graphics.components;

import gurps.application.Configuration;
import gurps.application.Label;
import net.sf.jmimemagic.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.imageout.ImageOut;
import sun.security.krb5.Config;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Caio on 16/04/2015.
 */
public class IconUploader {

    private Image image;
    private MouseOverArea mouseOverArea;
    private static List<String> mimeTypes;

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public MouseOverArea getMouseOverArea() {
        return this.mouseOverArea;
    }

    public void setMouseOverArea(MouseOverArea mouseOverArea) {
        this.mouseOverArea = mouseOverArea;
    }

    public void addImage() {
        JFileChooser jfc = new JFileChooser();
        jfc.setMultiSelectionEnabled(false);
        int result = jfc.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            File f = jfc.getSelectedFile();
            Magic parser = new Magic();
            try {
                MagicMatch match = parser.getMagicMatch(f, false);
                String mimeType = match.getMimeType();
                if(getValidMimeTypes().contains(mimeType)){
                    BufferedImage bimg = ImageIO.read(f);
                    int imageWidth = bimg.getWidth();
                    int imageHeigth = bimg.getHeight();
                    if(imageWidth > Configuration.ICON_UPLOADER_WIDTH
                            || imageHeigth > Configuration.ICON_UPLOADER_HEIGTH){
                        JOptionPane.showMessageDialog(null, Label.ERROR_IMAGE_TOO_BIG);
                    } else{
                        this.setImage(new Image(f.getAbsolutePath()));
                    }
                }
            } catch (MagicParseException e) {
                e.printStackTrace();
            } catch (MagicMatchNotFoundException e) {
                e.printStackTrace();
            } catch (MagicException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SlickException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<String> getValidMimeTypes() {
        if(mimeTypes == null){
            List<String> list = new ArrayList<String>();
            for(String type : ImageOut.getSupportedFormats()){
                list.add("image/"+type.toLowerCase());
            }
            mimeTypes = list;
        }
        return mimeTypes;
    }
}
