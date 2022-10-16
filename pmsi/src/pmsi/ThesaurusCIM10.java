/**
 * 
 */
package pmsi;

import java.util.ArrayList;
import java.util.Objects;

import com.pmsi.Thesaurus;
import com.pmsi.Utils;

/**
 * Entity THS_CIM10
 */
public class ThesaurusCIM10 implements Thesaurus {

	/** Attributes **/
	private String id;
	private String libelleCIM10;
	ArrayList<Diagnostic> listDiagnostics;

	public ThesaurusCIM10 () {
		this.id = null;
		this.libelleCIM10 = null;
		this.listDiagnostics = new ArrayList<>();
	}
	
	/**
	 * 
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}
	
	/**
	 * 
	 * @param idCIM10
	 * @return
	 */
	public ThesaurusCIM10 setId(String idCIM10) {
		this.id = idCIM10;
		return this;
	}
	
	/**
	 * @return the libelleCIM10
	 */
	public String getLibelle() {
		return this.libelleCIM10;
	}
	
	/**
	 * 
	 * @param libelle
	 * @return
	 */
	public ThesaurusCIM10 setLibelle(String libelle) {
		this.libelleCIM10 = libelle;
		return this;
	}
	
	/**
	 * 
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
	public ThesaurusCIM10 addDiagnostic(Diagnostic diagnostic) {
		if(!Objects.isNull(diagnostic)) {
			this.listDiagnostics.add(diagnostic);
		}
		return this;
	}
	
	/**
	 * 
	 * @param diagnostic
	 * @return
	 */
	public ThesaurusCIM10 removeDiagnostic(Diagnostic diagnostic) {
		if (this.listDiagnostics.contains(diagnostic)) {
			this.listDiagnostics.remove(diagnostic);
		}
		if (diagnostic.getCodeCIM10() == this.id) {
			diagnostic.setCodeCIM10("");
		}
		return this;
	}



}
