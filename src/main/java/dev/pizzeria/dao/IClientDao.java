/**
 * 
 */
package dev.pizzeria.dao;

import java.util.List;

import dev.pizzeria.model.Client;;

/**
 * @author Alexis Darcy
 *
 */
public interface IClientDao {

	/**Retour la liste des pizzas
	 * @return lesClients : List<Client>
	 */
	List<Client>findAllClients();
	/**Ajoute une pizza a la liste des Clients
	 * @param pizza
	 */
	void saveNewClient(Client client);
	/**Modifie une client dans la liste des pizzas via sont code
	 * @param codeClient
	 * @param client
	 */
	void updateClient(String codeClient, Client client);
	/**Supprime une client dans la liste des pizzas via sont code
	 * @param codeClient
	 */
	void deleteClient(String codeClient);
	/**Retrouve une client dans la liste
	 * @param codeClient : String
	 * @return une client
	 */
	void close();

}
