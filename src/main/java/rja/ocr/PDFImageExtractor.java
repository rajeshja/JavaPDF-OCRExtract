package rja.ocr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public class PDFImageExtractor {
    public static void main(String[] args) {
        String pdfFilePath = "";
        try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            for (int pageNumber = 0; pageNumber < document.getNumberOfPages(); pageNumber++) {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(pageNumber, 300);
                String outputFilePath = "E:\\temp\\dnd-player-handbook\\dnd-phb-" + (pageNumber + 1) + ".png";
                ImageIO.write(bim, "png", new File(outputFilePath));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}