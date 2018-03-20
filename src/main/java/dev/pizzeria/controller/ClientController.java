package dev.pizzeria.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.pizzeria.dao.ClientDao;
import dev.pizzeria.dao.IClientDao;
import dev.pizzeria.model.Client;

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
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Contrôleur responsable du traitement de la réquête : POST /clients.
 */
public class ClientController extends HttpServlet {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

	/**
	 * Page HTML de la réponse en cas d'insertion effectuée. Fichier présent
	 * dans le répertoire src/main/resources.
	 */
	public static final String TEMPLATE_CLIENT_INSERE = "templates/client_insere.html";
	public static final String TEMPLATE_LISTE_CLIENT = "templates/liste_client.html";
	IClientDao dao = new ClientDao();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// récupération des paramètres
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String ville = req.getParameter("ville");
		String age = req.getParameter("age");

		LOGGER.info("Paramètre nom reçu " + nom);
		LOGGER.info("Paramètre prenom reçu " + prenom);
		LOGGER.info("Paramètre ville reçu " + ville);
		LOGGER.info("Paramètre age reçu " + age);

		// TODO insérer un nouveau client en base de données
		Client nouveauClient = new Client(nom, prenom, ville, Integer.parseInt(age));
		dao.saveNewClient(nouveauClient);

		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			// récupération du contenu du fichier template
			String template = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_CLIENT_INSERE).toURI()))
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
		List<Client> lesClients = dao.findAllClients();

		String clientData = lesClients.stream()
				.map(c -> String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>", c.getNom(),
						c.getPrenom(), c.getVille(), c.getAge()))
				.collect(Collectors.joining());

		if (lesClients.isEmpty()) {
			LOGGER.error("Liste vide");
		}

		try {
			// réponse au format UTF-8 pour le support des accents
			resp.setCharacterEncoding("UTF-8");

			// récupération du contenu du fichier template
			String template = Files
					.readAllLines(
							Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_LISTE_CLIENT).toURI()))
					.stream().collect(Collectors.joining());
			String superTemplate = String.format(template, clientData);
			// écriture dans le corps de la réponse
			PrintWriter writer = resp.getWriter();
			writer.write(superTemplate);

		} catch (URISyntaxException e) {
			LOGGER.error("Fichier HTML non trouvé", e);
		}
	}
}
