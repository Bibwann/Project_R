package fr.sae.game;

public interface Spell {
	
	void AttaqueMelee();
	
	void InvocationDeSatan();
	
	void Foudre();
	void Glace();
	void Feu();
	void Terre();

	void Soins();
	
	void Fuite();

	void CoupDeGriffe();
	void Miaulement();
	
	void Berserk(); // Se met a 5% de ses pv actuel de maniere definitive dans le combat 
	
}
