/**
 * 
 */
package dev.pizzeria.dao;

import java.util.List;
import dev.pizzeria.model.Client;

/**
 * @author GOBERT Guillaume
 *
 */
public interface IClientDao {

	/**
	 * Retourne tous les clients présents an base
	 * 
	 * @return une liste de type Client
	 */
	List<Client> findAllClients();

	/**
	 * Sauvegarde un nouveau client
	 * 
	 * @param client
	 */
	void saveNewClient(Client client);

	/**
	 * Met à jour un client existant
	 * 
	 * @param idClient
	 * @param client
	 */
	void updateClient(Integer idClient, Client client);

	/**
	 * Supprime une client
	 * 
	 * @param idClient
	 */
	void deleteClient(Integer idClient);

	/**
	 * Retourne un client en fonction de son id
	 * 
	 * @param idClient
	 * @return
	 */
	Client findPizzaByCode(Integer idClient);

	/**
	 * Verifie si la pizza existe
	 * 
	 * @param codePizza
	 * @return
	 */
	boolean pizzaExists(String codePizza);
}
