/**
 * 
 */
package pmsi;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.pmsi.DatabaseManager;
import com.pmsi.Utils;



/**
 * Application PMSI Management
 */
public class Application {

	/** Constantes **/
	static final int [] POSSIBLE_CHOICE = {1,2,3,4,5,6,7,8,9};
	
	/** Attributes **/
	static String [] stop_response_value = {"o", "n"};
	static Scanner sc = new Scanner(System.in);
	
	/** Main Program **/
	public static void main(String[] args) {
		Utils.applicationHeader();
		String stop_response = null;
		String query = null;
		ArrayList<Patient> listPatients = new ArrayList<>();
		ArrayList<Hospitalisation> listHospitalisations = new ArrayList<>();
		ArrayList<Acte> listActes = new ArrayList<>();
		ArrayList<Diagnostic> listDiagnostics = new ArrayList<>();
		ArrayList<ThesaurusCCAM> libelleCCAM = new ArrayList<>();
		ArrayList<ThesaurusCIM10> libelleCIM10 = new ArrayList<>();
		Map<Integer, Object> queryParams = new HashMap<Integer, Object>();
		ResultSet rs = null;
		do {
			int userChoice = 0;
			stop_response = stopResponse(stop_response);
			if (stop_response.equals("o")) {
				do {
					try {
						System.out.print(" Quel est votre choix ? ");
						userChoice = sc.nextInt();
						userChoiceResponseErrorMessage(userChoice);
						
						// Different cases
						switch(userChoice) {
						//Scénario 1: Liste des patients et leur date d'entrée à l'hopital
							case 1:
								query = "select distinct tab_patient.ID_PATIENT, NOM , PRENOM , DATE_NAISSANCE ,DATE_ENTREE\n"
										+ "from tab_patient inner join tab_hospitalisation\n"
										+ "on tab_patient.ID_PATIENT = tab_hospitalisation.ID_PATIENT;";
								rs = DatabaseManager.executeUserQuery(query, queryParams);	
								while (rs.next()) {
						        	 Patient patient = new Patient();
						        	 patient.setId(rs.getInt("ID_PATIENT"))
							        	    .setNom(rs.getString("NOM"))
							        	    .setPrenom(rs.getString("PRENOM"))
							        	    .setDateNaissance(rs.getDate("DATE_NAISSANCE"))
							         ;
						        	 listPatients.add(patient);
						        	 
						        	 Hospitalisation hospitalisation = new Hospitalisation();
						        	 hospitalisation.setDateEntree(rs.getDate("DATE_ENTREE"));
						        	 listHospitalisations.add(hospitalisation);
						         }
								System.out.println("\n============================= Listes des patients et leur date d'entrée à l'hôpital============================\n");
								//Affichage des résultats
								Utils.listPatientsPrinting(listPatients, listHospitalisations);
								//Réinitialisation des variables partagées
								query = null;
								listPatients.clear();
								listHospitalisations.clear();
								rs = null;
							break;
						//Scénario 2: Liste des hospitalisations et des actes liée à un patient
							case 2:
								query = "select distinct th.ID_HOSPITALISATION, tp.NOM, tp.PRENOM, ta.ID_CCAM, tc.LIBELLE_CCAM \n"
										+ "from tab_patient tp, tab_hospitalisation th, tab_acte ta, tab_ccam tc \n"
										+ "where tp.ID_PATIENT = th.ID_PATIENT \n"
										+ "and th.ID_HOSPITALISATION = ta.ID_HOSPITALISATION \n"
										+ "and ta.ID_CCAM = tc.ID_CCAM \n"
										+ "and tp.ID_PATIENT = ?;\n";
								System.out.print(" Saisir l'ID du patient : ");
								int ID_PATIENT= sc.nextInt();
								queryParams.put(1, ID_PATIENT);
								rs = DatabaseManager.executeUserQuery(query, queryParams);
							
								while (rs.next()) {	
						        	 Hospitalisation hospitalisation = new Hospitalisation();
						        	 hospitalisation.setId(rs.getInt("ID_HOSPITALISATION"));
						        	 listHospitalisations.add(hospitalisation);
						        	 
						        	 Patient patient = new Patient();
						        	 patient.setNom(rs.getString("NOM"))
						        	 		.setPrenom(rs.getString("PRENOM"));
						        	 listPatients.add(patient);
						        	 
						        	 Acte acte = new Acte();
						        	 acte.setIdCCAM(rs.getString("ID_CCAM"));
						        	 listActes.add(acte);
						        	 
						        	 ThesaurusCCAM thesaurusCCAM = new ThesaurusCCAM();
						        	 thesaurusCCAM.setLibelle(rs.getString("LIBELLE_CCAM"));
						        	 libelleCCAM.add(thesaurusCCAM);
						         }
								System.out.println("\n============================= Listes des Hospitalisations et des actes liées à un patient ============================\n");
								//Affichage des résultats
								Utils.listHospitalisations2Printing(listHospitalisations, listPatients, listActes, libelleCCAM);
								//Reinitialisation des variables partagées
								query = null;
								listPatients.clear();
								listHospitalisations.clear();
								listActes.clear();
								libelleCCAM.clear();
								queryParams.clear();
								rs = null;
							break;
							
						//Scénario 3: Nombre d'hospitalisation selon le type de diagnostic
							case 3: query = "select count(th.ID_HOSPITALISATION) as nombreHospitalisation\n"
									+ "from tab_hospitalisation th\n"
									+ "inner join tab_diagnostic td\n"
									+ "on th.ID_HOSPITALISATION = td.ID_HOSPITALISATION \n"
									+ "where DGTYPE = ? ;\n";
							System.out.print(" Saisir le type de diagnostic recherché : ");
							String typeDG= sc.next();
								queryParams.put(1, typeDG);
								rs = DatabaseManager.executeUserQuery(query, queryParams);
								
								int nombreHospitalisation = 0;
				    	       	  while (rs.next()) {
				    	       		  nombreHospitalisation = rs.getInt("nombreHospitalisation");
						       	  } 
				    	       	System.out.printf("\n Nombre d'hospitalisation : %d \n ", nombreHospitalisation);
				    	      //Réinitialisation des variables communes
								query = null;
								queryParams.clear();
								rs = null;
							break;
								
							//Scénario 4: List des patients qui avaient moins de 60 ans le jour de leur hospitalisation selon le sexe
							case 4: query = "select tp.ID_PATIENT\n"
									+ "from tab_patient tp \n"
									+ "inner join tab_hospitalisation th \n"
									+ "on tp.ID_PATIENT = th.ID_PATIENT \n"
									+ "where datediff(DATE_ENTREE, DATE_NAISSANCE)/365.25 <60 and sexe = ?;\n";
							System.out.print(" Saisir le sexe du patient: ");
							String sexePatient= sc.next();
								queryParams.put(1, sexePatient);
								rs = DatabaseManager.executeUserQuery(query, queryParams);
								
								while (rs.next()) {
				    	       		System.out.println("\n ID PATIENT : " + rs.getString("ID_PATIENT"));
						       	  } 
								//Réinitialisation des variables communes
								query = null;
								queryParams.clear();
								rs = null;
							break;
						//Scénario 5: Pour un patient afficher sa date d'entrée et de sortie ainsi que le diagnostic lié à son hospitalisation
							case 5: 
								query = "select distinct th.ID_HOSPITALISATION, tp.NOM, tp.PRENOM, th.DATE_ENTREE, th.DATE_SORTIE, td.CODE_CIM10, tc.LIBELLE_CIM110 \n"
										+ "from tab_patient tp, tab_hospitalisation th, tab_diagnostic td, tab_cim10 tc \n"
										+ "where tp.ID_PATIENT = th.ID_PATIENT \n"
										+ "and th.ID_HOSPITALISATION = td.ID_HOSPITALISATION \n"
										+ "and td.CODE_CIM10 = tc.ID_CIM10\n"
										+ "and tp.ID_PATIENT = ?;\n";
								System.out.print(" Saisir l'ID du patient : ");
								int ID_PATIENT2= sc.nextInt();
								queryParams.put(1, ID_PATIENT2);
								rs = DatabaseManager.executeUserQuery(query, queryParams);
							
								while (rs.next()) {	
						        	 Hospitalisation hospitalisation = new Hospitalisation();
						        	 hospitalisation.setId(rs.getInt("ID_HOSPITALISATION"));
						        	 listHospitalisations.add(hospitalisation);
						        	 
						        	 Patient patient = new Patient();
						        	 patient.setNom(rs.getString("NOM"))
						        	 		.setPrenom(rs.getString("PRENOM"));
						        	 listPatients.add(patient);
						        	 
						        	 Diagnostic diagnostic = new Diagnostic();
						        	 diagnostic.setCodeCIM10(rs.getString("CODE_CIM10"));
						        	 listDiagnostics.add(diagnostic);
						        	 
						        	 ThesaurusCIM10 thesaurusCIM10 = new ThesaurusCIM10();
						        	 thesaurusCIM10.setLibelle(rs.getString("LIBELLE_CIM10"));
						        	 libelleCIM10.add(thesaurusCIM10);
						         }
								System.out.println("\n============================= Listes des Hospitalisations liées à un patient ============================\n");
								//Affichage des résultats
								Utils.listHospitalisations3Printing(listHospitalisations, listPatients, listDiagnostics, libelleCIM10);
								//Reinitialisation des variables partagées
								query = null;
								listPatients.clear();
								listHospitalisations.clear();
								listDiagnostics.clear();
								libelleCIM10.clear();
								queryParams.clear();
								rs = null;
							break;
							
				    	  
						//Scénario 6: Age moyen des patients  en fonction du sexe
							case 6: query = "select AVG(datediff(DATE_ENTREE, DATE_NAISSANCE)/365.25) as age_moyen\n"
									+ "from tab_patient tp inner join tab_hospitalisation th \n"
									+ "on tp.ID_PATIENT =th.ID_PATIENT\n"
									+ "where sexe=?;\n";
							System.out.print(" Saisir le sexe du patient : ");
							String patientSexe= sc.next();
								queryParams.put(1, patientSexe);
								rs = DatabaseManager.executeUserQuery(query, queryParams);
								
								double ageMoyen = 0;
				    	       	  while (rs.next()) {
				    	       		  ageMoyen = rs.getDouble("age_moyen");
						       	  } 
				    	       	System.out.printf("\n Age_Moyen : %.2f ans\n", ageMoyen);
				    	       	
								//Réinitialisation des variables communes
								query = null;
								queryParams.clear();
								rs = null;
							break;   
						
						
							default: System.out.println("Choix non valide");
							break;
						}
					} catch (Exception e) {
						System.err.println("\n Merci de saisir un entier du menu valide.\n");
						break;
					}
				} while(!contains(POSSIBLE_CHOICE, userChoice));
			}
		} while(!Arrays.asList(stop_response_value).contains(stop_response) || !stop_response.equals("n"));
		
		System.out.println(" Au revoir!");
	}
	
	
	

	/**========================================================== Private Methode =======================================================**/
	/**
	 * 
	 * @param tab
	 * @param value
	 * @return
	 */
	private static boolean contains(final int [] tab, int value) {
		Arrays.sort(tab);
		return Arrays.binarySearch(tab, value) >= 0;
	}
	
	/**
	 * 
	 * @param stop_response
	 * @return
	 */
	private static String stopResponse (String stop_response) {
		System.out.print("\n Voulez-vous continuer (o/n) ? ");
		stop_response = sc.next().toLowerCase();
		if (!Arrays.asList(stop_response_value).contains(stop_response)) {
			System.err.print("\n Merci de saisir (o: oui, n: non).");
		}
		return stop_response;
	}
	
	/**
	 * 
	 * @param userChoice
	 */
	private static void userChoiceResponseErrorMessage (int userChoice) {
		if (!contains(POSSIBLE_CHOICE, userChoice)) {
			System.err.println("\n Merci de saisir un choix du menu valide.\n");
		}
		
	}
}
