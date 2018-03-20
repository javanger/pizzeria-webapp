package fr.pizzeria.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import fr.pizzeria.model.Pizza;
import java.io.FileOutputStream;
import java.util.List;
import java.io.FileNotFoundException;

/**
 * 
 * @author Valentin Lefebvre
 *
 */
public class PDFGenerator {

	/**
	 * Méthode qui va générer un pdf avec écrit dedans la liste des pizzas
	 * @param pizza
	 */
	public static void genererPDF(List<Pizza> pizza){

        Document document = new Document();

        try {
            PdfWriter.getInstance(document,
                new FileOutputStream("ListePizza.pdf"));

            document.open();
            for(Pizza pizzaTemp:pizza)
            	document.add(new Paragraph(pizzaTemp.toString()));
            document.close(); // no need to close PDFwriter?

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
