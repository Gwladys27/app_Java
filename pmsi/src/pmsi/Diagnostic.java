/**
 * 
 */
package pmsi;

import java.util.ArrayList;

import com.pmsi.Utils;

/**
 * Entity Diagnostic
 */
public class Diagnostic {
	
	/** Constantes **/
	private static final String [] DIAGNOSTIC_TYPE_TRADUCTION_FR = {"Associé", "Principal", "Relié"};

	/** Attributes **/
	private int id;
	private int idHospitalisation;
	private String codeCIM10;
	private int drang;
	private String dgType;
	
	
	public Diagnostic () {
		this.id = 0;
		this.idHospitalisation = 0;
		this.codeCIM10 = null;
		this.drang = 0;
		this.dgType = "";
	}

	
	/**
	 * 
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
	public Diagnostic setId(int id) {
		this.id = id;
		return this;
	}

	/**
	 * 
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
	public Diagnostic setIdHospitalisation(int idHospitalisation) {
		this.idHospitalisation = idHospitalisation;
		return this;
	}

	/**
	 * 
	 * @return the codeCIM10
	 */
	public String getCodeCIM10() {
		return this.codeCIM10;
	}

	/**
	 * 
	 * @param codeCIM10
	 * @return
	 */
	public Diagnostic setCodeCIM10(String codeCIM10) {
		this.codeCIM10 = codeCIM10;
		return this;
	}

	/**
	 * 
	 * @return the drang
	 */
	public int getDrang() {
		return this.drang;
	}

	/**
	 * 
	 * @param drang
	 * @return
	 */
	public Diagnostic setDrang(int drang) {
		this.drang = drang;
		return this;
	}

	/**
	 * 
	 * @return the dgType
	 */
	public String getDgType() {
		switch (this.dgType) {
			case "S": return DIAGNOSTIC_TYPE_TRADUCTION_FR[0];
			case "D": return DIAGNOSTIC_TYPE_TRADUCTION_FR[1];
			case "R": return DIAGNOSTIC_TYPE_TRADUCTION_FR[2];
			default: return "";
		}
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	public Diagnostic setDgType(String dgtype) {
		this.dgType = dgtype;
		return this;
	}


	}

