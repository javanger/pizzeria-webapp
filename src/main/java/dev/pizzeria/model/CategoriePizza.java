package dev.pizzeria.model;

import java.util.ArrayList;
import java.util.List;

/**
 * enumeration de categorie de pizza
 * @author Alexis Darcy
 *
 */
public enum CategoriePizza {
		VIANDE ("Viande"), POISSON("Poisson"), SANS_VIANDE("Sans Viande");
	
		/**cat : String*/
		private String categorie;
		
		/**listeClient : List<Pizza>*/
		private List<Pizza> listeClient = new ArrayList<Pizza>(0);
		
		/** Constructeur
		 */
		private CategoriePizza() {
		}

		/** Constructeur
		 * @param cat
		 */
		private CategoriePizza(String cat) {
			this.categorie = cat;
		}

		/** Getter
		 * @return the cat
		 */
		public String getCategorie() {
			return categorie;
		}
		
		/** Setter
		 * @param categorie the categorie to set
		 */
		public void setCategorie(String categorie) {
			this.categorie = categorie;
		}

		/** Getter
		 * @return the listeClient
		 */
		public List<Pizza> getListeClient() {
			return listeClient;
		}

		/** Setter
		 * @param listeClient the listeClient to set
		 */
		public void setListeClient(List<Pizza> listeClient) {
			this.listeClient = listeClient;
		}
		
}
