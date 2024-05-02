package fr.sae.game.caractere;

import org.newdawn.slick.Image;

import fr.sae.game.DialogueBox;

public class NPC {
	
	
	private Image sprite;
	private String name;
	private DialogueBox dialogue;

	public NPC(String name, int level, Image sprite,DialogueBox dialogue) {
				
		this.name=name;
		this.sprite=sprite;
		this.dialogue=dialogue;
	}

}
