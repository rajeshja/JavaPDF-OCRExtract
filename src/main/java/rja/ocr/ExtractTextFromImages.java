package rja.ocr;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import net.sourceforge.tess4j.Tesseract;
//import net.sourceforge.tess4j.TesseractException;
import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
//import org.bytedeco.opencv.opencv_core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.text.OCRTesseract;

public class ExtractTextFromImages {
    public static void main(String[] args) {
        String imagesDirectoryPath = "E:\\temp\\dnd-player-handbook";
        String outputDirectoryPath = "E:\\temp\\dnd-player-handbook-ocr-extracted";
//        Tesseract tesseract = new Tesseract();
//        tesseract.setDatapath("E:\\software\\tessdata");
//        tesseract.setLanguage("eng");
        //System.setProperty("java.library.path", "C:\\software\\opencv-4.7.0\\opencv\\build\\java\\x64");
        System.out.println("About to load native OpenCV library");
        //OpenCV.loadShared();
        System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);
        System.out.println("About to load tesseract");
        OCRTesseract tesseract = OCRTesseract.create("E:\\software\\tessdata", "eng");
        System.out.println("Loaded tesseract");
        File imagesDirectory = new File(imagesDirectoryPath);
        for (File imageFile : imagesDirectory.listFiles()) {
            if (imageFile.isFile() && imageFile.getName().endsWith(".png")) {
                try {
                    // Load the image using OpenCV
                    Mat image = Imgcodecs.imread(imagesDirectoryPath);
                    // Convert the image to grayscale
                    Mat gray = new Mat();
                    Imgproc.cvtColor(image, gray, Imgproc.COLOR_BGR2GRAY);
                    // Apply adaptive thresholding to separate the columns
                    Mat thresh = new Mat();
                    Imgproc.adaptiveThreshold(gray, thresh, 255, Imgproc.ADAPTIVE_THRESH_MEAN_C, Imgproc.THRESH_BINARY, 15, 10);
                    // Find the contours of the columns
                    List<MatOfPoint> contours = new ArrayList<>();
                    Imgproc.findContours(thresh, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
                    // Extract the text from each column separately
                    String imageFilePath = imageFile.getAbsolutePath();
                    String outputFilePath = outputDirectoryPath + File.separator + imageFile.getName().replace(".png", ".txt");

                    FileWriter writer = new FileWriter(outputFilePath);
                    for (MatOfPoint contour : contours) {
                        Rect rect = Imgproc.boundingRect(contour);
                        Mat column = new Mat(image, rect);
                        String text = tesseract.run(column, 1);
                        writer.write(text + "\n");
                    }
                    writer.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}