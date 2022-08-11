package listManager;

public class ScoreHelper {

	public static int calcolaPunteggio(int score, String categoria) {
		int newScore=score;
		
		if(categoria.equals("In corso"))
			newScore=score+10;
		else if(categoria.equals("Completato"))
			newScore=score+30;
		else if(categoria.equals("Platinato"))
			newScore=score+50;
		else if(categoria.equals("Sviluppato"))
			newScore=score*2;
		return newScore;
	}
	
}
