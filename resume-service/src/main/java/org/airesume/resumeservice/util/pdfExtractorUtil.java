package org.airesume.resumeservice.util;

import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;

@Component
//create object of this class automatically
//manages its lifecycle
//allows dependency injection
public class pdfExtractorUtil {
    public String extractedText(String filePath) throws IOException{
     File file=new File(filePath);
     PDDocument document=PDDocument.load(file);
     PDFTextStripper pdfTextStripper=new PDFTextStripper();
     String Text=pdfTextStripper.getText(document);
     document.close();
     return Text;
    }
}
