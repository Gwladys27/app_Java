package pmsi;

import java.util.ArrayList;
import java.util.Date;

import com.pmsi.DateManager;
import com.pmsi.Utils;

/**
 * Entity Acte
 */
public class Acte extends DateManager {
	
	/** Constantes **/
	private static final String [] ANESTHESIE_TRADUCTION_FR = {"Avec Anesthésie", "Sans Anesthésie"};

	/** Attributes **/
	private int id;
	private String idCCAM;
	private Date date;
	private int idHospitalisation;
	private byte anesth;
	

	public Acte () {
		this.id = 0;
		this.idCCAM = null;
		this.date = null;
		this.idHospitalisation = 0;
		this.anesth = 0;
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
	public Acte setId(int id) {
		this.id = id;
		return this;
	}


	/**
	 * @return the idCCAM
	 */
	public String getIdCCAM() {
		return this.idCCAM;
	}


	/**
	 * 
	 * @param idCCAM
	 * @return
	 */
	public Acte setIdCCAM(String idCCAM) {
		this.idCCAM = idCCAM;
		return this;
	}


	/**
	 * @return the date
	 */
	public String getDate() {
		return this.getDateFormatInstance().format(this.date);
	}


	/**
	 * 
	 * @param date
	 * @return
	 */
	public Acte setDate(Date date) {
		this.date = date;
		return this;
	}


	/**
	 * @return the idHospitalisation
	 */
	public int getIdHospitalisation() {
		return this.idHospitalisation;
	}


	/**
	 * 
	 * @param idHospitalisation
	 * @return
	 */
	public Acte setIdHospitalisation(int idHospitalisation) {
		this.idHospitalisation = idHospitalisation;
		return this;
	}


	/**
	 * @return the anesth
	 */
	public String getAnesth() {
		return this.anesth != 0 ? ANESTHESIE_TRADUCTION_FR[0] : ANESTHESIE_TRADUCTION_FR[1];
	}


	/**
	 * 
	 * @param anesth
	 * @return
	 */
	public Acte setAnesth(byte anesth) {
		this.anesth = anesth;
		return this;
	}


	

	}
