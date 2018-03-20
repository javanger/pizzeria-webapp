package dev.pizzeria.dao;

import java.util.List;

import dev.pizzeria.model.Pizza;
/**
 * Interface permettent la gestion des pizzas
 * @author Alexis Darcy
 *
 */
public interface IPizzaDao {
	/**
	 * Initialisation des pizzas
	 */
	void init();
	/**Retour la liste des pizzas
	 * @return lesPizzas : List<Pizza>
	 */
	List<Pizza>findAllPizzas();
	/**Ajoute une pizza a la liste des pizzas
	 * @param pizza
	 */
	void saveNewPizza(Pizza pizza);
	/**Modifie une pizza dans la liste des pizzas via sont code
	 * @param codePizza
	 * @param pizza
	 */
	void updatePizza(String codePizza, Pizza pizza);
	/**Supprime une pizza dans la liste des pizzas via sont code
	 * @param codePizza
	 */
	void deletePizza(String codePizza);
	/**Retrouve une pizza dans la liste
	 * @param codePizza : String
	 * @return une pizza
	 */
	Pizza findPizzaByCode(String codePizza);
	/**V�rifie si la pizza existe 
	 * @param codePizza : String
	 * @return un boolean
	 */
	boolean pizzaExists(String codePizza);
	/**V�rifie si la categorie existe 
	 * @param cat : String
	 * @return un boolean
	 */
	boolean categorieExists(String strCat);
	
	void close();
}
