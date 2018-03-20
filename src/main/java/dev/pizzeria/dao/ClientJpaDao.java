/**
 * 
 */
package dev.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import dev.pizzeria.model.Client;
import dev.pizzeria.model.Pizza;

/**
 * @author Alexis Darcy
 *
 */
public class ClientJpaDao implements IClientDao {

	private EntityManagerFactory entityManagerFactory;
	private EntityManager em;

	
	public ClientJpaDao() {
		entityManagerFactory = Persistence.createEntityManagerFactory("local-pizzeria");
		em = entityManagerFactory.createEntityManager();
	}

	/* (non-Javadoc)
	 * @see dev.pizzeria.dao.IClientDao#findAllClients()
	 */
	@Override
	public List<Client> findAllClients() {
		List<Client> listeClient = new ArrayList<Client>();
		TypedQuery<Client> clientQuery = em.createQuery("select c from Client c", Client.class);
		listeClient = clientQuery.getResultList();
		return listeClient;
	}

	/* (non-Javadoc)
	 * @see dev.pizzeria.dao.IClientDao#saveNewPizza(dev.pizzeria.model.Client)
	 */
	@Override
	public void saveNewClient(Client client) {
		EntityTransaction et = em.getTransaction();		
		et.begin();
		em.persist(client);
		et.commit();
	}

	/* (non-Javadoc)
	 * @see dev.pizzeria.dao.IClientDao#updatePizza(java.lang.String, dev.pizzeria.model.Client)
	 */
	@Override
	public void updateClient(String codeClient, Client client) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see dev.pizzeria.dao.IClientDao#deletePizza(java.lang.String)
	 */
	@Override
	public void deleteClient(String codeClient) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see dev.pizzeria.dao.IClientDao#close()
	 */
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
