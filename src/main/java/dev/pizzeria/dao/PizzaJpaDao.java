/**
 * 
 */
package dev.pizzeria.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import dev.pizzeria.model.CategoriePizza;
import dev.pizzeria.model.Pizza;

/**
 * @author Alexis Darcy
 *
 */
public class PizzaJpaDao implements IPizzaDao{

	private EntityManagerFactory entityManagerFactory;
	private EntityManager em;

	public PizzaJpaDao() {
		entityManagerFactory = Persistence.createEntityManagerFactory("local-pizzeria");
		em = entityManagerFactory.createEntityManager();
	}


	@Override
	public void init() {
		if(pizzaIsEmpty()){
			initPizza();
		}
	}

	public void initPizza() {
		List <Pizza> newPizzas = new ArrayList<Pizza>();
		newPizzas.add(new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		newPizzas.add(new Pizza("MAR", "Margherita", 14.00, CategoriePizza.SANS_VIANDE));
		newPizzas.add(new Pizza("REIN", "La Reine", 11.50, CategoriePizza.VIANDE));
		newPizzas.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE));
		newPizzas.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		newPizzas.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		newPizzas.add(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
		newPizzas.add(new Pizza("IND", "L'indienne", 14.00, CategoriePizza.POISSON));
		for(Pizza  newPizza : newPizzas){
			saveNewPizza(newPizza);
		}
	}
	
	public boolean pizzaIsEmpty(){
		boolean retour = true;
		Query pizzaQuery = em.createQuery("Select count(p) from Pizza p");
		long p = (long)pizzaQuery.getSingleResult();
		if(p != 0){
			retour = false;
		}
		return retour;
	}

	@Override
	public List<Pizza> findAllPizzas() {
		List<Pizza> listePizza = new ArrayList<Pizza>();
		TypedQuery<Pizza> pizzaQuery = em.createQuery("select p from Pizza p", Pizza.class);
		listePizza = pizzaQuery.getResultList();
		return listePizza;
	}


	@Override
	public void saveNewPizza(Pizza pizza) {
		EntityTransaction et = em.getTransaction();		
		et.begin();
		em.persist(pizza);
		et.commit();
	}


	@Override
	public void updatePizza(String codePizza, Pizza pizza) {
		EntityTransaction et = em.getTransaction();	
		TypedQuery<Pizza> pizzaQuery = em.createQuery("from Pizza where code = :code", Pizza.class);
		pizzaQuery.setParameter("code", codePizza);
		Pizza p = pizzaQuery.getSingleResult();
		if(p != null){
			p.setCode(pizza.getCode());
			p.setLibelle(pizza.getLibelle());
			p.setPrix(pizza.getPrix());
			p.setCategorie(pizza.getCategorie());
		}
		et.begin();
		em.merge(p);
		et.commit();
	}


	@Override
	public void deletePizza(String codePizza) {
		EntityTransaction et = em.getTransaction();		
		TypedQuery<Pizza> pizzaQuery = em.createQuery("from Pizza where code = :code", Pizza.class);
		pizzaQuery.setParameter("code", codePizza);
		Pizza p = pizzaQuery.getSingleResult();
		if(p != null){
			et.begin();
			em.remove(p);
			et.commit();
		}
	}


	@Override
	public Pizza findPizzaByCode(String codePizza) {
		TypedQuery<Pizza> pizzaQuery = em.createQuery("from Pizza where code = :code", Pizza.class);
		pizzaQuery.setParameter("code", codePizza);
		Pizza p = pizzaQuery.getSingleResult();
		return p;
	}


	@Override
	public boolean pizzaExists(String codePizza) {
		boolean retour;
		Query pizzaQuery = em.createQuery("Select count(p) from Pizza p where p.code = :code");
		pizzaQuery.setParameter("code", codePizza);
		long p = (long)pizzaQuery.getSingleResult();
		if(p == 0){
			retour = false;
		}else{
			retour = true;
		}
		return retour;
	}


	@Override
	public boolean categorieExists(String strCat) {
		boolean trouver = false;
		CategoriePizza[] tabCat = CategoriePizza.values(); 
		List<CategoriePizza> listCat = new ArrayList<CategoriePizza>(Arrays.asList(tabCat));
		Iterator<CategoriePizza> iterator = listCat.iterator(); 
		while (iterator.hasNext()){
			CategoriePizza cat = iterator.next();
			if(cat.name().equals(strCat)){
				trouver = true;
			}
		}
		return trouver;
	}


	/* (non-Javadoc)
	 * @see fr.pizzeria.dao.IPizzaDao#close()
	 */
	@Override
	public void close() {
		em.close();
		entityManagerFactory.close();
		
	}

}
