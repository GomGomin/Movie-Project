package movieProject;

import java.util.Scanner;

abstract class AbstractMenu implements Menu{
	protected String menuText;
	protected Menu prevMenu;
	protected static final Scanner scanner = new Scanner(System.in);
	
	public AbstractMenu(String menuText, Menu prevMenu) {
		this.menuText = menuText;
		this.prevMenu = prevMenu;
	}
	
	public void print() {
		System.out.println("\n" + menuText);
	}

	public void setPrevMenu(Menu prevMenu) {
		this.prevMenu = prevMenu;
	}	
}