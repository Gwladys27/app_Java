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
 * Entity Hospitalisation
 */
public class Hospitalisation extends DateManager {

	/** Attributes **/
	private int id;
	private int idPatient;
	private Date dateEntree;
	private Date dateSortie;
	ArrayList<Diagnostic> listDiagnostics;
	ArrayList<Acte> listActes;
	
	
	public Hospitalisation() {
		this.id = 0;
		this.idPatient = 0;
		this.dateEntree = null;
		this.dateSortie = null;
		this.listDiagnostics = new ArrayList<>();
		this.listActes = new ArrayList<>();
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
	public Hospitalisation setId(int id) {
		this.id = id;
		return this;
	}


	/**
	 * @return the idPatient
	 */
	public int getIdPatient() {
		return this.idPatient;
	}


	/**
	 * 
	 * @param idPatient
	 * @return
	 */
	public Hospitalisation setIdPatient(int idPatient) {
		this.idPatient = idPatient;
		return this;
	}


	/**
	 * @return the dateEntree
	 */
	public String getDateEntree() {
		return this.getDateFormatInstance().format(this.dateEntree);
	}


	/**
	 * 
	 * @param dateEntree
	 * @return
	 */
	public Hospitalisation setDateEntree(Date dateEntree) {
		this.dateEntree = dateEntree;
		return this;
	}


	/**
	 * @return the dateSortie
	 */
	public String getDateSortie() {
		return this.getDateFormatInstance().format(this.dateSortie);
	}


	/**
	 * 
	 * @param dateSortie
	 * @return
	 */
	public Hospitalisation setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
		return this;
	}


	/**
	 * @return the listDiagnostics
	 */
	public ArrayList<Diagnostic> getListDiagnostics() {
		return this.listDiagnostics;
	}

	/**
	 * 
	 * @param diagnostic
	 * @return
	 */
	public Hospitalisation addDiagnostic(Diagnostic diagnostic) {
		if (!Objects.isNull(diagnostic)) {
			this.listDiagnostics.add(diagnostic);
		}
		return this;
	}
	
	/**
	 * 
	 * @param diagnostic
	 * @return
	 */
	public Hospitalisation removeDiagnostic(Diagnostic diagnostic) {
		if (this.listDiagnostics.contains(diagnostic)) {
			this.listDiagnostics.remove(diagnostic);
		}
		if (diagnostic.getIdHospitalisation() == this.id) {
			diagnostic.setIdHospitalisation(0);
		}
		return this;
	}


	/**
	 * @return the listActes
	 */
	public ArrayList<Acte> getListActes() {
		return this.listActes;
	}

	/**
	 * 
	 * @param acte
	 * @return
	 */
	public Hospitalisation addActe(Acte acte) {
		if (!Objects.isNull(acte)) {
			this.listActes.add(acte);
		}
		return this;
	}
	
	/**
	 * 
	 * @param acte
	 * @return
	 */
	public Hospitalisation removeActe(Acte acte) {
		if (this.listActes.contains(acte)) {
			this.listActes.remove(acte);
		}
		if (acte.getIdHospitalisation() == this.id) {
			acte.setIdHospitalisation(0);
		}
		return this;
	}
	

}
