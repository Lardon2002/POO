public class Developpement {
	char type; //c: "chevalier" ; p="point" ; r="route" ; m="monopole" ; i="invention"
	//5 cards point, 14 cards chevalier, 2 cards route, 2 cards monopole, 2 cards invention, for a total of 25.
	public Developpement(char t) {
		if (t=='c' ||t=='p' ||t=='r' ||t=='m' ||t=='i') type=t;
		//else catch error?
	}
	//I don't think we need to override equals and hashCode methods, as two identical cards are still different in regards of the game
	public String toString() {
		switch(type) {
		case 'c': return "CHEVALIER:\nBougez le voleur. La case sur laquelle on le place ne produit pas de ressources et vous pouvez voler une ressource à un des joueurs se trouvant sur cette case.";
		case 'p': return "POINT:\nGagnez un point de victoire.";
		case 'r': return "ROUTE:\nConstruisez 2 routes sans utiliser de ressources.";
		case 'm': return "MONOPOLE:\nNommez une ressource. Tous les joueurs vous donnent toutes les ressources de ce type qu'ils ont en main.";
		case 'i': return "INVENTION:\nPrenez 2 ressources de votre choix à la banque.";
		default: return "Erreur, cette carte ne devrait pas exister...";
		}
	}
	
	/* Fonction action:
	 * 	if type='...'
	 * 		faire telle action
	 * Comment le mettre en place?
	 * S'applique quand on joue la carte
	 * Donc la méthode est appelée depuis la classe joueur, quand le joueur défausse la carte
	 * 
	 * Appelle des sous fonctions spécifiques selon l'action
	 * POINT VICTOIRE : le faire depuis la classe joueur ?
	 * En théorie ce sont des méthodes relatives au cartes, mais qui ont lieu ailleurs...
	 * Une méthode cavalier appellerait une méthode voleur de la classe jouer ?
	 * Une méthode point appellerait une méthode gainPoint de la classe joueur ? (passer le joueur en arg du coup)
	 * Une méthode route appellerait une fonction construireRoute (modifiée pour ne pas payer) dans Joueur...?
	 */
}
