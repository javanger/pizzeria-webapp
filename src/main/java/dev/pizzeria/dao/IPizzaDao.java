package dev.pizzeria.dao;

//import
import java.util.List;
import dev.pizzeria.model.Pizza;

/**
 * @author GOBERT Guillaume
 *
 */
public interface IPizzaDao {
	/**
	 * Stocke dans une liste toutes les pizzas
	 * 
	 * @return
	 */
	List<Pizza> findAllPizzas();

	/**
	 * Sauvegarde une nouvelle pizza
	 * 
	 * @param pizza
	 */
	void saveNewPizza(Pizza pizza);

	/**
	 * Met � jour une pizza d�j� existante
	 * 
	 * @param codePizza
	 * @param pizza
	 */
	void updatePizza(String codePizza, Pizza pizza);

	/**
	 * Supprime une pizza
	 * 
	 * @param codePizza
	 */
	void deletePizza(String codePizza);

	/**
	 * Retourne un pizza en fonction de son code
	 * 
	 * @param codePizza
	 * @return
	 */
	Pizza findPizzaByCode(String codePizza);

	/**
	 * Verifie si la pizza existe
	 * 
	 * @param codePizza
	 * @return
	 */
	boolean pizzaExists(String codePizza);
}
