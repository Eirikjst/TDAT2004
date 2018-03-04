package exercise2;

import java.util.*;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

/*
 * Klassen Utstyr er mutabel. Antall på lager og nedre grense for
 * bestilling kan endres.
 */
class Utstyr {
	public static final int bestillingsfaktor = 5;
	private int nr; // entydig identifikasjon
	private String betegnelse;
	private String leverandor;
	private int paLager; // mengde på lager
	private int nedreGrense;

	public Utstyr(int startNr, String startBetegnelse, String startLeverandor, int startPaLager, int startNedreGrense) {
		nr = startNr;
		betegnelse = startBetegnelse;
		leverandor = startLeverandor;
		paLager = startPaLager;
		nedreGrense = startNedreGrense;
	}

	public int finnNr() {
		return nr;
	}

	public String finnBetegnelse() {
		return betegnelse;
	}

	public String finnLeverandor() {
		return leverandor;
	}

	public int finnPaLager() {
		return paLager;
	}

	public int finnNedreGrense() {
		return nedreGrense;
	}

	public int finnBestKvantum() {
		if (paLager < nedreGrense)
			return bestillingsfaktor * nedreGrense;
		else
			return 0;
	}

	/*
	 * Endringen kan v�re positiv eller negativ. Men det er ikke mulig � ta ut mer
	 * enn det som fins p� lager. Hvis klienten pr�ver p� det, vil metoden returnere
	 * false, og intet uttak gj�res.
	 */
	public boolean endreLagerbeholdning(int endring) {
		System.out.println("Endrer lagerbeholdning, utstyr nr " + nr + ", endring: " + endring);
		if (paLager + endring < 0)
			return false;
		else {
			paLager += endring;
			return true;
		}
	}

	public void settNedreGrense(int nyNedreGrense) {
		nedreGrense = nyNedreGrense;
	}

	public String toString() {
		String resultat = "Nr: " + nr + ", " + "Betegnelse: " + betegnelse + ", " + "Leverandør: " + leverandor + ", "
				+ "På lager: " + paLager + ", " + "Nedre grense: " + nedreGrense;
		return resultat;
	}
}

/*
 *
 * Et register holder orden på en mengde Utstyrsobjekter. En klient kan legge
 * inn nye Utstyr-objekter i registeret, og også endre varebeholdningen for et
 * allerede registrert objekt. Bestillingsliste for alle varene kan lages.
 */
class RegisterImpl extends UnicastRemoteObject implements Register {

	private static final long serialVersionUID = 1L;
	public static final int ok = -1;
	public static final int ugyldigNr = -2;
	public static final int ikkeNokPaLager = -3;

	private ArrayList<Utstyr> registeret = new ArrayList<Utstyr>();
	
	public RegisterImpl() throws RemoteException {
	}

	public boolean regNyttUtstyr(int startNr, String startBetegnelse, String startLeverandor, int startPaLager,
			int startNedreGrense) throws RemoteException {
		if (finnUtstyrindeks(startNr) < 0) { // fins ikke fra før
			Utstyr nytt = new Utstyr(startNr, startBetegnelse, startLeverandor, startPaLager, startNedreGrense);
			registeret.add(nytt);
			return true;
		} else
			return false;
	}

	public int endreLagerbeholdning(int nr, int mengde) throws RemoteException {
		int indeks = finnUtstyrindeks(nr);
		if (indeks < 0)
			return ugyldigNr;
		else {
			if (!(registeret.get(indeks)).endreLagerbeholdning(mengde)) {
				return ikkeNokPaLager;
			} else
				return ok;
		}
	}

	private int finnUtstyrindeks(int nr) {
		for (int i = 0; i < registeret.size(); i++) {
			int funnetNr = (registeret.get(i)).finnNr();
			if (funnetNr == nr)
				return i;
		}
		return -1;
	}

	public String lagBestillingsliste() throws RemoteException {
		String resultat = "\n\nBestillingsliste:\n";
		for (int i = 0; i < registeret.size(); i++) {
			Utstyr u = registeret.get(i);
			resultat += u.finnNr() + ", " + u.finnBetegnelse() + ": " + u.finnBestKvantum() + "\n";
		}
		return resultat;
	}

	public String lagDatabeskrivelse() throws RemoteException {
		String resultat = "Alle data:\n";
		for (int i = 0; i < registeret.size(); i++) {
			resultat += (registeret.get(i)).toString() + "\n";
		}
		return resultat;
	}
}