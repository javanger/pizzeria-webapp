package fr.pizzeria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import fr.pizzeria.utils.StringUtils;
import fr.pizzeria.utils.ToString;

/**
 * 
 * @author Valentin Lefebvre
 *
 */
@Entity
@Table(name="PIZZA")
public class Pizza {
	
	@Id
	@Column(name = "PIZ_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	public int id;
	@ToString(upperCase=true, separateur=" : ", notNull=true)
	@Column(name="PIZ_CODE", length=5, nullable = false)
	public String code;
	@ToString (separateur=" => ", notNull=true)
	@Column(name="PIZ_LIBELLE", length=16, nullable = false)
	public String libelle;
	@ToString(upperCase=true, separateur=" (", notNull=true)
	@Column(name="PIZ_TYPE", length=20, nullable = false)
	@Enumerated(EnumType.STRING)
	public CategoriePizza type;
	@ToString (separateur=" €) ", notNull=true)
	@Column(name="PIZ_PRIX", length=5, nullable = false)
	public double prix;
	/** int pour savoir le nombre de pizza*/
	private static int compteur=0;

	public Pizza(){
		
	}
	
	/**
	 * Constructeur sans id
	 * @param code
	 * @param libelle
	 * @param type
	 * @param prix
	 */
	public Pizza(String code, String libelle, CategoriePizza type, double prix) {
		this.code = code;
		this.libelle = libelle;
		this.type = type;
		this.prix = prix;
	}

	/**
	 * Constructeur avec id
	 * @param id
	 * @param code
	 * @param libelle
	 * @param type
	 * @param prix
	 */
	public Pizza(int id, String code, String libelle, CategoriePizza type, double prix) {
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.type = type;
		this.prix = prix;
		compteur++;
	}

	/**
	 * Getter 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Getter 
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Setter
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Getter 
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Setter
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * Getter 
	 * @return the prix
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * Setter
	 * @param prix the prix to set
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**
	 * Getter 
	 * @return the type
	 */
	public CategoriePizza getType() {
		return type;
	}

	/**
	 * Setter
	 * @param type the type to set
	 */
	public void setType(CategoriePizza type) {
		this.type = type;
	}

	/**
	 * Getter qui retourne le compteur
	 * @return int
	 */
	public static int getCompteur() {
		return compteur;
	}

	/**
	 * Setter modifié du compteur
	 */
	public static void modifCompteur() {
		compteur--;
	}
	
	@Override 
	public String toString(){
		return StringUtils.afficherPizza(this);
		
	}

}
