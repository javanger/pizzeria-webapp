package fr.pizzeria.service;

import java.util.stream.Stream;

import fr.pizzeria.dao.FactoryDao;
import fr.pizzeria.exception.SaveException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * @author Kevin M.
 *
 */
public class InflateDataPizzaService extends MenuPizzaService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.pizzeria.service.ServicePizzaMenu#executeUC()
	 */
	@Override
	public void executeUC() throws SaveException {

		// Ajouter les categories
		
		FactoryDao.getCategorieDao().saveNew(new CategoriePizza(0, "Vegan", null));
		FactoryDao.getCategorieDao().saveNew(new CategoriePizza(0, "Viande", null));
		FactoryDao.getCategorieDao().saveNew(new CategoriePizza(0, "Poisson", null));
		
		CategoriePizza vegan = FactoryDao.getCategorieDao().findByLabel("Vegan").get(0);
		CategoriePizza viande = FactoryDao.getCategorieDao().findByLabel("Viande").get(0);
		CategoriePizza poisson = FactoryDao.getCategorieDao().findByLabel("Poisson").get(0);
		Pizza[] list = new Pizza[] { 
				new Pizza("FRO", "La 4 formages", 12.00, vegan, "4fromages.jpg"),
				new Pizza("SAI", "4 Saisons", 12.50, vegan, "4saisons.jpg"),
				new Pizza("BOL", "Bolognese", 12.50, viande, "bolognese.jpg"),
				new Pizza("CHO", "Chorizo", 12.50, viande, "chorizo.jpg"),
				new Pizza("NAP", "Napolitaine", 14.00, poisson, "napolitaine.jpg"), 
				new Pizza("REI", "La Reine", 11.00, viande, "reine.jpg")
				};

		Stream.of(list).forEach(p -> FactoryDao.getPizzaDao().saveNew(p));
		

	}

}
