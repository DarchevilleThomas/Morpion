package morpion;

import java.util.Scanner;

public class Plateau {
	private final static int nbColonnes = 3;
	private final static int nbLignes = 3;
	private static int nbTours=1;
	private String[][] tableau;
	private String nom;
	private String joueur1;
	private String joueur2;
	
	public Plateau() {
		this.nom = "Morpion";
		this.tableau = new String[nbColonnes][nbLignes];
		for (int i=0;i<nbColonnes;i++) {
			for (int j=0;j<nbLignes;j++) {
				this.tableau[i][j] = ".";
			}
		}
		this.joueur1 = "X";
		this.joueur2 = "O";
		
	}
	
	public String getNom() {
		return nom;
	}


	public String[][] getTableau() {
		return tableau;
	}

	public void afficherTableau() {
		for (int i=0;i<nbColonnes;i++) {
			System.out.print("|");
			for (int j=0;j<nbLignes;j++) {
				System.out.print(" "+tableau[i][j]+" ");
			}
			System.out.println("|");
		}
		System.out.println(" ");
	}
	
	
	public boolean addJeton(int colonne,int ligne,int joueur) {
		if (colonne>3 || colonne<1) {
			return false;
		}
		else if ( ligne>3 || ligne<1) {
			return false;
		}
		else {
			if (this.tableau[colonne-1][ligne-1]==".") {
				if (joueur==1) {
					this.tableau[colonne-1][ligne-1] = joueur1;
					nbTours++;
					return true;
				}
				else if (joueur==2) {
					this.tableau[colonne-1][ligne-1] = joueur2;
					nbTours++;
					return true;
				}
			}
			else{
				return false;
			}
			
		}
		return false;
		
	}
	
	public void initialiserTableau() {
		for (int i=0;i<nbColonnes;i++) {
			for (int j=0;j<nbLignes;j++) {
				this.tableau[i][j] = ".";
			}
		}
	}
	
	
	// Fonction verification de win et de jeu fini.

	public boolean winHorizontal() {
		boolean win = false;
		for (int i=0;i<nbColonnes;i++) {
			if (this.tableau[i][0]==joueur1) {
				if (this.tableau[i][0]==this.tableau[i][1] && this.tableau[i][1]==this.tableau[i][2]) {
					win = true;
					System.out.println("Joueur 1 a gagné ! ");
				}
			}
			else if (this.tableau[i][0]==joueur2) {
				if (this.tableau[i][0]==this.tableau[i][1] && this.tableau[i][1]==this.tableau[i][2]) {
					win = true;
					System.out.println("Joueur 2 a gagné ! ");
				}
			}
		}
		
		return win;
	}
	
	public boolean winVertical() {
		boolean win = false;
		for (int i=0;i<nbLignes;i++) {
			if (this.tableau[0][i]==joueur1) {
				if (this.tableau[0][i]==this.tableau[1][i] && this.tableau[1][i]==this.tableau[2][i]) {
					win = true;
					System.out.println("Joueur 1 a gagné ! ");
				}
			}
			else if (this.tableau[0][i]==joueur2) {
				if (this.tableau[0][i]==this.tableau[1][i] && this.tableau[1][i]==this.tableau[2][i]) {
					win = true;
					System.out.println("Joueur 2 a gagné ! ");
				}
			}
		}
		return win;
	}
	
	public boolean winDiagonal() {
		boolean win = false;
		if ((this.tableau[0][0]==joueur1 && this.tableau[0][0]!=".") || (this.tableau[0][2]==joueur1) && this.tableau[0][2]!=".") {
			if (this.tableau[0][0]==this.tableau[1][1] && this.tableau[1][1]==this.tableau[2][2] && this.tableau[0][0]!=".") {
				win = true;
				System.out.println("Joueur 1 a gagné ! ");
			}
			else if (this.tableau[0][2]==this.tableau[1][1] && this.tableau[1][1]==this.tableau[2][0] && this.tableau[0][2]!=".") {
				win = true;
				System.out.println("Joueur 1 a gagné ! ");
			}
		}
		else if ((this.tableau[0][0]==joueur2 && this.tableau[0][0]!=".") || (this.tableau[0][2]==joueur2  && this.tableau[0][2]!=".")) {
			if (this.tableau[0][0]==this.tableau[1][1] && this.tableau[1][1]==this.tableau[2][2] && this.tableau[0][0]!=".") {
				win = true;
				System.out.println("Joueur 2 a gagné ! ");
			}
			else if (this.tableau[0][2]==this.tableau[1][1] && this.tableau[1][1]==this.tableau[2][0] && this.tableau[0][2]!=".") {
				win = true;
				System.out.println("Joueur 2 a gagné ! ");
			}
		}
		return win;
	}
	
	public boolean tableauRempli() {
		boolean rempli = true;
		for (int i=0;i<nbColonnes;i++) {
			for (int j=0;j<nbLignes;j++) {
				if(this.tableau[i][j]==".") {
					rempli=false;
				}
			}
		}
		return rempli;
	}
	
	public boolean winVerif() {
		if (this.winDiagonal()==true || this.winVertical()==true || this.winHorizontal()==true) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean jeuFini() {
		boolean fin = false;
		if (this.winVerif()==true || this.tableauRempli()==true) {
			fin = true;
		}
		return fin;
	}
	
	public void remplirJeu() {
		Scanner scanner = new Scanner(System.in);
		if (nbTours%2==0) {
			boolean continuer = true;
			while (continuer) {
			System.out.print("Joueur 2 : Veuillez rentrer un nombre de colonne entre 1 et 3 : ");
			int colonne = scanner.nextInt();
			System.out.println(colonne);
			System.out.print("Joueur 2 : Veuillez rentrer un nombre de ligne entre 1 et 3 : ");
			int ligne = scanner.nextInt();
			System.out.println(ligne);
				if (addJeton(ligne,colonne,2)==true) {
					addJeton(ligne,colonne,2);
					continuer = false;
				}
			}
		}
		else {
			boolean continuer = true;
			while (continuer) {
				System.out.print("Joueur 1 : Veuillez rentrer un nombre de colonne entre 1 et 3 : ");
				int colonne = scanner.nextInt();
				System.out.println(colonne);
				System.out.print("Joueur 1 : Veuillez rentrer un nombre de ligne entre 1 et 3 : ");
				int ligne = scanner.nextInt();
				System.out.println(ligne);
				if (addJeton(ligne,colonne,1)==true) {
					addJeton(ligne,colonne,1);
					continuer = false;
				}
			}
		}
		
	}
	
	public void morpionFinal() {
		this.initialiserTableau();
		boolean continuer = true;
		do {
			this.afficherTableau();
			this.remplirJeu();
			if (this.jeuFini()==true) {
				continuer = false;
			}
		} while (continuer);
		if (this.tableauRempli()==true) {
			this.afficherTableau();
			System.out.println("Plateau rempli ! Fin du jeu !");
		}
		else {
			this.afficherTableau();
			this.winVerif();
		}
	}
	
	public static void main(String[] args) {
		Plateau plateau = new Plateau();
		plateau.morpionFinal();
	}
}
