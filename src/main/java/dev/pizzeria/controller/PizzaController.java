package dev.pizzeria.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.console.IPizzaDao;
import fr.pizzeria.console.PizzaDaoJpa;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Contrôleur responsable du traitement de la réquête : POST /pizzas.
 */
public class PizzaController extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaController.class);
	private IPizzaDao pizzaDao = new PizzaDaoJpa();

	/**
	 * Page HTML de la réponse en cas de demande de listage Fichier présent
	 * dans le répertoire src/main/resources.
	 */
	public static final String TEMPLATE_PIZZA_INSERE = "templates/pizza_insere.html";
	public static final String TEMPLATE_PIZZA_LIST = "templates/pizza_liste.html";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		

		// récupération du paramètre code
		// <input name="code">
		String code = req.getParameter("code");

		LOGGER.info("Paramètre code reçu " + code);

		// récupération du paramètre libelle
		// <input name="libelle">
		String libelle = req.getParameter("libelle");

		LOGGER.info("Paramètre libelle reçu " + libelle);

		// récupération du paramètre prix
		// <input name="code">
		double prix = Double.parseDouble(req.getParameter("prix"));

		LOGGER.info("Paramètre prix reçu " + prix);

		// récupération du paramètre catégorie
		// <input name="catégorie">
		String type = req.getParameter("categorie");
		CategoriePizza categorie = CategoriePizza.valueOf(type);

		LOGGER.info("Paramètre categorie reçu " + categorie);

		Pizza pizza = new Pizza(code, libelle, categorie, prix);

		pizzaDao.saveNewPizza(pizza);

		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			// récupération du contenu du fichier template
			String template = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_PIZZA_INSERE).toURI()))
					.stream().collect(Collectors.joining());

			// écriture dans le corps de la réponse
			PrintWriter writer = resp.getWriter();
			writer.write(template);

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Pizza> pizzas = pizzaDao.findAllPizzas();
		
		String pizzaList = pizzas.stream().map(p -> String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>", 
				p.getCode(), p.getLibelle(), p.getType(), p.getPrix())).collect(Collectors.joining());
		
		try {
			
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			// récupération du contenu du fichier template
			String template = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_PIZZA_LIST).toURI()))
					.stream().collect(Collectors.joining());

			String superTemplate = String.format(template, pizzaList);
			// écriture dans le corps de la réponse
			PrintWriter writer = resp.getWriter();
			writer.write(superTemplate);

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
		}
	}

}
