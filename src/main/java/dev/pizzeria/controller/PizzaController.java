package dev.pizzeria.controller;

import static org.mockito.Matchers.same;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.FactoryDao;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import jnr.ffi.Struct.pid_t;

/**
 * Contrôleur responsable du traitement de la réquête : POST /clients.
 */
public class PizzaController extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaController.class);

	/**
	 * Page HTML de la réponse en cas d'insertion effectuée. Fichier présent
	 * dans le répertoire src/main/resources.
	 */
	public static final String TEMPLATE_PIZZA_INSERE = "templates/pizza_insere.html";
	public static final String TEMPLATE_LISTER_PIZZA = "templates/lister-pizza.html";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// récupération du paramètre nom
		// <input name="nom">
		String code = req.getParameter("reference");
		LOGGER.info("Paramètre nom reçu " + code);
		String libelle = req.getParameter("libelle");
		Double prix = Double.parseDouble(req.getParameter("prix"));
		String photo = req.getParameter("photo");

		CategoriePizza categoriePizza = FactoryDao.getCategorieDao().findByLabel("Vegan").get(0);
		Pizza pizza = new Pizza(code, libelle, prix, categoriePizza);
		pizza.setPathImage(photo);

		FactoryDao.getPizzaDao().saveNew(pizza);

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

			String repere = "{{value}}";

			writer.write(template);

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// récuper la liste de pizza
		
		List<Pizza> list = FactoryDao.getPizzaDao().findAll();
		String formatedList = list.stream().map(p -> 
			String.format("<tr>"
					+ "<td width=\"40\">%d</td>"
					+ "<td width=\"200\">%s</td>"
					+ "<td width=\"200\">%s</td>"
					+ "<td width=\"100\">%2.2f €</td>"
							+ "<td><img alt=\"%s\" src=\"./images/%s\" width=\"200\"></td>"
									+ "</tr>", p.getId(), p.getLabelle(), p.getCode(), p.getPrix(), "image-"+p.getCode(), p.getPathImage())
		).collect(Collectors.joining());
		
		LOGGER.info(formatedList);
		
		
		
		// afficher la liste de pizzas
		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			// récupération du contenu du fichier template
			String template = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_LISTER_PIZZA).toURI()))
					.stream().collect(Collectors.joining());

			// écriture dans le corps de la réponse
			PrintWriter writer = resp.getWriter();
			
			
			
			template = String.format(template, formatedList);			
			writer.write(template);

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
		}
	}
}
