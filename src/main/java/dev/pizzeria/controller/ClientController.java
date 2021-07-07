package dev.pizzeria.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.pizzeria.dao.ClientJpaDao;
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
import java.util.stream.Collectors;

/**
 * Contrôleur responsable du traitement de la réquête : POST /clients.
 */
public class ClientController extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientController.class);

    /**
     * Page HTML de la réponse en cas d'insertion effectuée.
     * Fichier présent dans le répertoire src/main/resources.
     */
    public static final String TEMPLATE_CLIENT_INSERE = "templates/client_insere.html";
    public static final String TEMPLATE_LISTE_CLIENT = "templates/liste_client.html";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // récupération du paramètre nom
        // <input name="nom">
        String nom = req.getParameter("Nom");
        String prenom = req.getParameter("Prenom");
        String ville = req.getParameter("Ville");
        int age = Integer.parseInt(req.getParameter("Age"));
        
        LOGGER.info("Paramètre nom reçu " + nom + " " + prenom + " " + ville + " " + age);

        IClientDao iClientDao = new ClientJpaDao();
        iClientDao.saveNewClient(new Client(nom, prenom, ville, age));
        
        try {
            // réponse au format UTF-8 pour le support des accents
            resp.setCharacterEncoding("UTF-8");

            // récupération du contenu du fichier template
            String template = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_CLIENT_INSERE).toURI())).stream().collect(Collectors.joining());

            // écriture dans le corps de la réponse
            PrintWriter writer = resp.getWriter();
            writer.write(template);

        } catch (URISyntaxException e) {
           LOGGER.error("Fichier HTML non trouvé", e);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        IClientDao iClientDao = new ClientJpaDao();
        
        try {
            // réponse au format UTF-8 pour le support des accents
            resp.setCharacterEncoding("UTF-8");

            //String tableau = iClientDao.findAllClients().stream().map(m -> String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>", m.getNom(), m.getPrenom(), m.getVille(), m.getAge())).collect(Collectors.joining());
            // récupération du contenu du fichier template
            String template = Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource(TEMPLATE_LISTE_CLIENT).toURI())).stream().map(line -> line.contains("%s") ? 
            		iClientDao.findAllClients().stream().map(m -> String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>", m.getNom(), m.getPrenom(), m.getVille(), m.getAge())).collect(Collectors.joining()) : line ).collect(Collectors.joining());
            //String superTemplate = String.format(template, tableau);
            // écriture dans le corps de la réponse
            PrintWriter writer = resp.getWriter();
            writer.write(template);

        } catch (URISyntaxException e) {
           LOGGER.error("Fichier HTML non trouvé", e);
        }
    }
}
