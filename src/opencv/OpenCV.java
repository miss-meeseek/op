/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opencv;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

/**
 *
 * @author Olga Gutovskaya
 */
public class OpenCV {


static { 
    try {
    System.load("C:\\Program Files\\opencv\\build\\bin\\opencv_ffmpeg400_64.dll");
    } catch (UnsatisfiedLinkError e) {
        System.err.println("Native code library failed to load.\n" + e);
        System.exit(1);
    }
}

    /**
     *
     * @param m
     * @return
     */
    public static BufferedImage Mat2BufferedImage(Mat m) {
    //Method converts a Mat to a Buffered Image
    int type = BufferedImage.TYPE_BYTE_GRAY;
     if ( m.channels() > 1 ) {
         type = BufferedImage.TYPE_3BYTE_BGR;
     }
     int bufferSize = m.channels()*m.cols()*m.rows();
     byte [] b = new byte[bufferSize];
     m.get(0,0,b); // get all the pixels
     BufferedImage image = new BufferedImage(m.cols(),m.rows(), type);
     final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
     System.arraycopy(b, 0, targetPixels, 0, b.length);  
     return image;
}

    
    /**
     * @param args the command line arguments
     */
public static void main(String[] args)
{
    System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

    //Create new MAT object
    Mat frame = new Mat();

    //Create new VideoCapture object
    VideoCapture camera = new VideoCapture("C:\\Users\\Olga Gutovskaya\\Desktop\\5 semesrt\\zespolowe\\openCV\\src\\opencv\\ex camera 2Mp AHD.mp4");

    //Create new JFrame object
    JFrame jframe = new JFrame("Video Title");

    //Inform jframe what to do in the event that you close the program
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Create a new JLabel object vidpanel
    JLabel vidPanel = new JLabel();

    //assign vidPanel to jframe
    jframe.setContentPane(vidPanel);

    //set frame size
    jframe.setSize(1000, 500);

    //make jframe visible
    jframe.setVisible(true);

    while (true) {
        //If next video frame is available
        if (camera.read(frame)) {
            //Create new image icon object and convert Mat to Buffered Image
            ImageIcon image = new ImageIcon(Mat2BufferedImage(frame));
            //Update the image in the vidPanel
            vidPanel.setIcon(image);
            //Update the vidPanel in the JFrame
            vidPanel.repaint();

        }
    }
}
}




