/**
 * 
 */
package pmsi;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import com.pmsi.Thesaurus;
import com.pmsi.Utils;

/**
 * Entity THESAURUS_CCAM
 */
public class ThesaurusCCAM implements Thesaurus {

	/** Attributes **/
	private String id;
	private String libelleCCAM;
	ArrayList<Acte> listActes;
	
	
	public ThesaurusCCAM() {
		this.id = null;
		this.libelleCCAM = null;
		this.listActes = new ArrayList<>();
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public ThesaurusCCAM setId(String id) {
		this.id = id;
		return this;
	}

	
	/**
	 * @return the libelleCCAM
	 */
	public String getLibelle() {
		return this.libelleCCAM;
	}


	/**
	 * 
	 * @param libelleCCAM
	 * @return
	 */
	public ThesaurusCCAM setLibelle(String libelle) {
		this.libelleCCAM = libelle;
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
	public ThesaurusCCAM addActe(Acte acte) {
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
	public ThesaurusCCAM removeActe(Acte acte) {
		if (this.listActes.contains(acte)) {
			this.listActes.remove(acte);
		}
		if (acte.getIdCCAM() == this.id) {
			acte.setIdCCAM("");
		}
		return this;
	}


}
