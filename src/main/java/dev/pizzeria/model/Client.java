/**
 * 
 */
package dev.pizzeria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Alexis Darcy
 *
 */
@Entity
@Table(name = "client")
public class Client {

	/**id : int*/
	@Id
	@Column(name = "ID_CLIENT")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	/**nom : String*/
	@Column(name = "NOM", nullable = false)
	private String nom;
	/**prenom : String*/
	@Column(name = "PRENOM", nullable = false)
	private String prenom;
	/**ville : String*/
	@Column(name = "VILLE", nullable = false)
	private String ville;
	/**age : int*/
	@Column(name = "AGE", nullable = false)
	private int age;
	
	/**
	 * Constructeur
	 */
	public Client() {
		
	}

	/**
	 * Constructeur
	 * @param nom
	 * @param prenom
	 * @param age
	 */
	public Client(String nom, String prenom, String ville, int age) {
		this.nom = nom;
		this.prenom = prenom;
		this.ville = ville;
		this.age = age;
	}

	/** Getter
	 * @return the ville
	 */
	public String getVille() {
		return ville;
	}

	/** Setter
	 * @param ville the ville to set
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}

	/** Getter
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/** Setter
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/** Getter
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}

	/** Setter
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/** Getter
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}

	/** Setter
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/** Getter
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/** Setter
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
}
