package fr.pizzeria.console;

import java.util.List;

import fr.pizzeria.model.Pizza;

/**
 * Interface PizzaDao
 * @author Valentin Lefebvre
 *
 */
public interface IPizzaDao {
	/**
	 * Méthode renvoyant la liste des pizzas
	 * @return List<Pizza>
	 */
	List<Pizza>findAllPizzas();
	
	/**
	 * Méthode pour ajouter une nouvelle pizza
	 * @param pizza
	 */
	void saveNewPizza(Pizza pizza);
	
	/**
	 * Méthode pour modifier une pizza existante
	 * @param codePizza
	 * @param pizza
	 */
	void updatePizza(String codePizza, Pizza pizza);
	
	/**
	 * Méthode pour supprimer une pizza existante
	 * @param codePizza
	 */
	void deletePizza(String codePizza);
	
	/**
	 * Méthode pour trouver une pizza de la liste selon son code
	 * @param codePizza
	 * @return la pizza dont le code correspond si elle existe sinon null
	 */
	Pizza findPizzaByCode(String codePizza);
	
	/**
	 * Méthode pour savoir si une pizza existe ou non selon le code passé en paramètre
	 * @param codePizza 
	 * @return true si la pizza existe, false dans le cas contraire
	 */
	boolean pizzaExists(String codePizza);
}
