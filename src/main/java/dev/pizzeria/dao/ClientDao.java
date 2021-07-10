/**
 * 
 */
package dev.pizzeria.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dev.pizzeria.model.Client;

/**
 * @author GOBERT Guillaume
 *
 */
public class ClientDao implements IClientDao {

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pizzeria");

	Client client = null;

	@Override
	public List<Client> findAllClients() {
		EntityManager em = entityManagerFactory.createEntityManager();
		List<Client> lesClients = null;
		TypedQuery<Client> clientQuery = em.createQuery("select c from Client c ", Client.class);

		lesClients = clientQuery.getResultList();
		return lesClients;
	}

	@Override
	public void saveNewClient(Client client) {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(client);
		et.commit();

	}

	@Override
	public void updateClient(Integer idClient, Client client) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteClient(Integer idClient) {
		// TODO Auto-generated method stub

	}

	@Override
	public Client findPizzaByCode(Integer idClient) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean pizzaExists(String codePizza) {
		// TODO Auto-generated method stub
		return false;
	}

}
