/**
 * 
 */
package pmsi;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import com.pmsi.DateManager;
import com.pmsi.Utils;

/**
 * Entity Patient
 */
public class Patient extends DateManager {

	/** Constantes **/
	private static final String [] SEXE_TRADUCTION_FR = {"Masculin", "Féminin "};

	/** Attributes **/
	private int id;
	private int sexe;
	private Date dateNaissance;
	private String prenom;
	private String nom;
	ArrayList<Hospitalisation> hospitalisations;
	
	

	public Patient() {
		this.id = 0;
		this.sexe = 0;
		this.dateNaissance = null;
		this.prenom = null;
		this.nom = null;
		this.hospitalisations = new ArrayList<>();
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public Patient setId(int id) {
		this.id = id;
		return this;
	}

	/**
	 * @return the sexe
	 */
	public String getSexe() {
		switch (this.sexe) {
			case 1: return SEXE_TRADUCTION_FR[0];
			case 2: return SEXE_TRADUCTION_FR[1];
			default: return "";
		}
	}

	/**
	 * 
	 * @param sexe
	 * @return
	 */
	public Patient setSexe(int sexe) {
		this.sexe = sexe;
		return this;
	}

	/**
	 * @return the dateNaissance
	 */
	public String getDateNaissance() {
		return this.getDateFormatInstance().format(this.dateNaissance);
	}

	/**
	 * 
	 * @param dateNaissance
	 * @return
	 */
	public Patient setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
		return this; 
	}

	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return this.prenom;
	}

	/**
	 * 
	 * @param prenom
	 * @return
	 */
	public Patient setPrenom(String prenom) {
		this.prenom = prenom;
		return this;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * 
	 * @param nom
	 * @return
	 */
	public Patient setNom(String nom) {
		this.nom = nom;
		return this;
	}


	/**
	 * @return the hospitalisations
	 */
	public ArrayList<Hospitalisation> getHospitalisations() {
		return this.hospitalisations;
	}

	/**
	 * 
	 * @param hospitalisation
	 * @return
	 */
	public Patient addHospitalisation(Hospitalisation hospitalisation) {
		if (!Objects.isNull(hospitalisation)) {
			this.hospitalisations.add(hospitalisation);
		}
		return this;
	}
	
	
	public Patient removeHospitalisation(Hospitalisation hospitalisation) {
		if (this.hospitalisations.contains(hospitalisation)) {
			this.hospitalisations.remove(hospitalisation);
		}
		if (hospitalisation.getIdPatient() == this.id) {
			hospitalisation.setIdPatient(0);
		}
		return this;
	}


}
