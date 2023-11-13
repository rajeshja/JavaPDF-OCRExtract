package rja.ocr;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ExtractTextFromImages {
    public static void main(String[] args) {
        String imagesDirectoryPath = "E:\\temp\\dnd-player-handbook";
        String outputDirectoryPath = "E:\\temp\\dnd-player-handbook-ocr-extracted";
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("E:\\software\\tessdata");
        tesseract.setLanguage("eng");
        File imagesDirectory = new File(imagesDirectoryPath);
        for (File imageFile : imagesDirectory.listFiles()) {
            if (imageFile.isFile() && imageFile.getName().endsWith(".png")) {
                try {
                    String imageFilePath = imageFile.getAbsolutePath();
                    String outputFilePath = outputDirectoryPath + File.separator + imageFile.getName().replace(".png", ".txt");
                    String text = tesseract.doOCR(new File(imageFilePath));
                    FileWriter writer = new FileWriter(outputFilePath);
                    writer.write(text);
                    writer.close();
                } catch (IOException | TesseractException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}