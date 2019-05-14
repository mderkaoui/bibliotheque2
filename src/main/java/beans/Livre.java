package beans;


import java.io.Serializable;
import java.util.Date;

import beans.enums.Genre;

/**
 * Representation d'un livre
 * @author Mohamed
 *
 */
public class Livre implements Serializable{
	/**
	 * Numero unique auto
	 */
	private int id;
	private String titre;
	private String auteur;
	private Date annee;
	private Genre genre;
	
	public Livre() {
		
	}

	public Livre(String titre, String auteur, Date annee, Genre genre) {
		this.titre = titre;
		this.auteur = auteur;
		this.annee = annee;
		this.genre = genre;
	}
	
	

	public Livre(int id, String titre, String auteur, Date annee, Genre genre) {
		super();
		this.id = id;
		this.titre = titre;
		this.auteur = auteur;
		this.annee = annee;
		this.genre = genre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public Date getAnnee() {
		return annee;
	}

	public void setAnnee(Date annee) {
		this.annee = annee;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((annee == null) ? 0 : annee.hashCode());
		result = prime * result + ((auteur == null) ? 0 : auteur.hashCode());
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + id;
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livre other = (Livre) obj;
		if (annee == null) {
			if (other.annee != null)
				return false;
		} else if (!annee.equals(other.annee))
			return false;
		if (auteur == null) {
			if (other.auteur != null)
				return false;
		} else if (!auteur.equals(other.auteur))
			return false;
		if (genre != other.genre)
			return false;
		if (id != other.id)
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}
	
	/**
	 * Transformation d'un livre en chaine csv
	 * @return	csv String
	 */
	public String toCsv() {
		return id+";"+titre+";"+auteur+";"+genre.toString()+"\n";
	}
	
	

	
}
