/**
 * 
 */
package com.pmsi;

import java.util.ArrayList;

import pmsi.Acte;
import pmsi.Diagnostic;
import pmsi.Hospitalisation;
import pmsi.Patient;
import pmsi.ThesaurusCCAM;
import pmsi.ThesaurusCIM10;

/**
 *
 */
public final class Utils {
	
	
	/** Patient Printing: Scénario 1 **/
	public static void listPatientsHeader () {
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("|   ID   |   Nom   |    Prénom    |      Date de naissance          |  Date d'entrée           |");
		System.out.println("------------------------------------------------------------------------------------------------");
	}
	
	public static void listPatientsFooter () {
		System.out.println("------------------------------------------------------------------------------------------------\n\n");
	}
	
	public static void listPatientsPrinting(ArrayList<Patient> listPatients, ArrayList<Hospitalisation> listHospitalisations) {
		listPatientsHeader();
		for (Patient patient : listPatients) {
			System.out.printf("| %5d | %7s  | %10s   | %23s    | %23s      |\n", 
					patient.getId(),
					patient.getNom(),
					patient.getPrenom(),
					patient.getDateNaissance(),
					listHospitalisations.get(listPatients.indexOf(patient)).getDateEntree()
				);
		}
		listPatientsFooter();
	}
	
	
	/**Scénario 2 Printing**/
	public static void listHospitalisations2Header () {
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("|   ID   |   Nom   |    Prénom  |    ID CCAM               |      LIBELLE CCAM                                                                                                 |");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}
	
	public static void listHospitalisations2Footer () {
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n");
	}
	
	public static void listHospitalisations2Printing(ArrayList<Hospitalisation> listHospitalisations, ArrayList<Patient> listPatients, ArrayList<Acte> listActes, ArrayList<ThesaurusCCAM> libelleCCAM) {
		listHospitalisations2Header();
		for (Hospitalisation hospitalisation :listHospitalisations ) {
				System.out.printf("| %5d | %7s | %10s | %23s  | %60s   \n",
						hospitalisation.getId(),
						listPatients.get(listHospitalisations.indexOf(hospitalisation)).getNom(),
						listPatients.get(listHospitalisations.indexOf(hospitalisation)).getPrenom(),
						listActes.get(listHospitalisations.indexOf(hospitalisation)).getIdCCAM(),
						libelleCCAM.get(listHospitalisations.indexOf(hospitalisation)).getLibelle()
						
						
					);
		
			
		}
		listHospitalisations2Footer();
	}
	
	
	/**Scénario 5 Printing**/
	public static void listHospitalisations3Header () {
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("|   ID   |   Nom   |    Prénom  |    CODE CIM10               |      LIBELLE CIM10                                                                                                |");
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	}
	
	public static void listHospitalisations3Footer () {
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n");
	}
	
	public static void listHospitalisations3Printing(ArrayList<Hospitalisation> listHospitalisations, ArrayList<Patient> listPatients, ArrayList<Diagnostic> listDiagnostics, ArrayList<ThesaurusCIM10> libelleCIM10) {
		listHospitalisations3Header();
		for (Hospitalisation hospitalisation :listHospitalisations ) {
				System.out.printf("| %5d | %7s | %10s | %23s  | %60s   \n",
						hospitalisation.getId(),
						listPatients.get(listHospitalisations.indexOf(hospitalisation)).getNom(),
						listPatients.get(listHospitalisations.indexOf(hospitalisation)).getPrenom(),
						listDiagnostics.get(listHospitalisations.indexOf(hospitalisation)).getCodeCIM10(),
						libelleCIM10.get(listHospitalisations.indexOf(hospitalisation)).getLibelle()
						
					);
		
		}
		listHospitalisations3Footer();
	}
	
	
	
	
	/** Application Printing **/
	public static void applicationHeader () {
		System.out.println("============================= Bienvenue dans votre application de gestion PMSI =============================\n");
		System.out.println(" Menu");
		System.out.println(" 1- Liste des patients et leur date d'entrée à l'hopital \n"
				+ " 2- Liste des hospitalisations et des actes liée à un patient\n"
				+ " 3- Nombre d'hospitalisation selon le type de diagnostic \n"
				+ " 4- Liste des patients qui avaient moins de 60 ans le jour de leur hospitalisation selon le sexe \n"
				+ " 5- Liste des hospitalisations et des actes liée à un patient\n"
				+ " 6- Afficher l'âge moyen des patients selon le sexe \n");
		System.out.println("============================================================================================================\n");
	}
}
