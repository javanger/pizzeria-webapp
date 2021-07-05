package fr.pizzeria.console;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJpa implements IPizzaDao {
	
	public static EntityManagerFactory entityManagerFactory;
	
	public PizzaDaoJpa() {
		entityManagerFactory = Persistence.createEntityManagerFactory("pizzeria");
	}

	@Override
	public List<Pizza> findAllPizzas() {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		List<Pizza> pizza = new ArrayList<Pizza>();
		TypedQuery<Pizza> queryEmprunt = em.createQuery("SELECT p FROM Pizza p", Pizza.class);
		pizza = queryEmprunt.getResultList();
		
		et.commit();
		em.close();
		return pizza;
	}

	@Override
	public void saveNewPizza(Pizza pizza) {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		
		em.persist(pizza);
		
		et.commit();
		em.close();
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		TypedQuery<Pizza> queryPizza = em.createQuery("SELECT p FROM Pizza p WHERE p.code=:param1", Pizza.class);
		queryPizza.setParameter("param1", codePizza);
		
		Pizza pizzaModif = queryPizza.getResultList().get(0);
		pizzaModif.setCode(pizza.getCode());
		pizzaModif.setLibelle(pizza.getLibelle());
		pizzaModif.setPrix(pizza.getPrix());
		pizzaModif.setType(pizza.getType());
		
		et.commit();
		em.close();
		
	}

	@Override
	public void deletePizza(String codePizza) {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		TypedQuery<Pizza> queryEmprunt = em.createQuery("SELECT p FROM Pizza p WHERE p.code=:param1", Pizza.class);
		queryEmprunt.setParameter("param1", codePizza);
		
		em.remove(queryEmprunt.getResultList().get(0));
		
		et.commit();
		em.close();	
	}

	/**
	 * Inutile avec un système de BDD
	 */
	@Override
	public Pizza findPizzaByCode(String codePizza) {
		return null;
	}

	@Override
	public boolean pizzaExists(String codePizza) {
		boolean existe = false;
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();
		TypedQuery<Pizza> queryEmprunt = em.createQuery("SELECT p FROM Pizza p WHERE p.code=:param1", Pizza.class);
		queryEmprunt.setParameter("param1", codePizza);
		Pizza pizza = queryEmprunt.getResultList().get(0);
		
		
		et.commit();
		if(pizza != null)
			existe = true;
		em.close();
		return existe;
	}

}
