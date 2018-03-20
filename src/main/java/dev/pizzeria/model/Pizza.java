package dev.pizzeria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import dev.pizzeria.utils.StringUtils;
import dev.pizzeria.utils.ToString;

/**
 * Objet pizza
 * @author Alexis Darcy
 */
@Entity
@Table(name = "pizza")
public class Pizza {
	
	/**id : int*/
	@Id
	@Column(name = "ID_PIZZA")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	/**code : String*/
	@Column(name = "CODE", nullable = false, unique = true)
	@ToString(separateur = " -> ", upperCase = true )
	private String code;
	
	/**libelle : String*/
	@Column(name = "LIBELLE", nullable = false)
	@ToString (separateur = " (", upperCase = true )
	private String libelle;
	
	/**prix : double*/
	@Column(name = "PRIX", nullable = false)
	@ToString(separateur = " €) -> ", upperCase = false)
	private double prix;
	
	/**categorie : CategoriePizza*/
	@Column(name = "CATEGORIE", nullable = false)
	@Enumerated(EnumType.STRING)
	@ToString(separateur = ".",  upperCase = false)
	private CategoriePizza categorie;
	
	/**compteur : int*/
	@Transient
	private static int compteur = 0;
	
	/**
	 * Constructeur
	 */
	public Pizza() {
	}

	/**
	 * Constructeur
	 * @param code chaine de characteur
	 * @param libelle chaine de characteur
	 * @param prix reel
	 */
	public Pizza(String code, String libelle, double prix, CategoriePizza categorie) {
		super();
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		this.categorie = categorie;
		compteur++;
	}
	
	/**
	 * Constructeur
	 * @param id entier
	 * @param code chaine de characteur
	 * @param libelle chaine de characteur
	 * @param prix reel
	 */
	public Pizza(int compteur, String code, String libelle, double prix,  CategoriePizza categorie) {
		super();
		this.id = compteur;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		this.categorie = categorie;
		compteur++;
	}
	
	/** Getter
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/** Getter
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/** Setter
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/** Getter
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/** Setter
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/** Getter
	 * @return the prix
	 */
	public double getPrix() {
		return prix;
	}

	/** Setter
	 * @param prix the prix to set
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/** Getter
	 * @return the compteur
	 */
	public static int getCompteur() {
		return compteur;
	}

	/** Getter
	 * @return the categorie
	 */
	public CategoriePizza getCategorie() {
		return categorie;
	}

	/** Setter
	 * @param categorie the categorie to set
	 */
	public void setCategorie(CategoriePizza categorie) {
		this.categorie = categorie;
	}
	
	/**
	 * Methode toString Redefini
	 */
	@Override
	public String toString() {
		return StringUtils.getStringValue(this);
	}
	
}
