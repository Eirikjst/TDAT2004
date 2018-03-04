package exercise2;

import java.rmi.*;

interface Register extends Remote {
	boolean regNyttUtstyr(int startNr, String startBetegnelse, String startLeverandor, int startPaLager,
			int startNedreGrense) throws RemoteException;
	
	int endreLagerbeholdning(int nr, int mengde) throws RemoteException;
	
	String lagBestillingsliste() throws RemoteException;
	
	String lagDatabeskrivelse() throws RemoteException;
}
