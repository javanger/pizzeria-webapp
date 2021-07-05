package fr.pizzeria.model;

/**
 * 
 * @author Valentin Lefebvre
 *
 */
public enum CategoriePizza {
	/**
	 * Catégorie de pizza avec viande
	 */
	VIANDE("Viande"),
	/**
	 * Catégorie de pizza avec du poisson
	 */
	POISSON("Poisson"),
	/**
	 * Catégorie de pizza sans viande
	 */
	SANS_VIANDE("Sans_Viande");
	
	private String type;
	
	/**
	 * Constructeur
	 * @param type
	 */
	private CategoriePizza(String type){
		this.type = type;
	}
	
	/**
	 * Méthode pour récupérer le type de pizza
	 * @return le type de pizza (String)
	 */
	public String getType(){
		return type;
	}
}
