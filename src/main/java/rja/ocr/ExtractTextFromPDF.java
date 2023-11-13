package rja.ocr;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class ExtractTextFromPDF {
    public static void main(String[] args) {
        String pdfFilePath = "E:/GoogleDrive/RPG/AD&D 5e - Player's Handbook - Wizards of the Coast.pdf";
        try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
            PDFTextStripper textStripper = new PDFTextStripper();
            for (int pageNumber = 0; pageNumber < document.getNumberOfPages(); pageNumber++) {
                textStripper.setStartPage(pageNumber + 1);
                textStripper.setEndPage(pageNumber + 1);
                String text = textStripper.getText(document);
                String outputFilePath = "E:\\temp\\dnd-player-handbook\\dnd-phb-" + (pageNumber + 1) + ".txt";
                FileWriter writer = new FileWriter(outputFilePath);
                writer.write(text);
                writer.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}