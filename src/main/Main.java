package main;

import java.lang.*;
import dataSTr.MyLinkedTree;
import dataSTr.MyNode;

public class Main {

	public static void main(String[] args) {
		try {
			MyLinkedTree<Character> newTree = createMathTree("(((8+2)*3)/2)");
			newTree.print();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static MyLinkedTree<Character> createMathTree(String algebraicTask) throws Exception {
		//String "(((8+2)*3)/2)"
		MyLinkedTree<Character> mathTree = new MyLinkedTree<>('P');
		char[] algebraicTaskCharacters = algebraicTask.toCharArray(); //['(', '8']
		MyNode pointer = mathTree.getRoot(); //pašreizējs mezgls
		for(int i = 0; i < algebraicTaskCharacters.length; i++){
			//Ja simbols ir ‘(‘ pašreizējam mezglam pievienot jaunu mezglu kā kreiso bērnu un pārvietoties uz to;
			// ascii ( 40
			if(algebraicTaskCharacters[i] == '('){
				pointer.setLeftChild('x');
				pointer = pointer.getLeftChild();
			}
			//Ja simbols ir kāds no operatoriem (+, -, /, *), tad pašreizējā mezglā ievietot tā vērtību, izveidot labo bērnu un pārvietoties uz to;
			else if(algebraicTaskCharacters[i] == '+' ||
					algebraicTaskCharacters[i] == '-' ||
					algebraicTaskCharacters[i] == '*' ||
					algebraicTaskCharacters[i] == '/'){
				switch (algebraicTaskCharacters[i]) {
					case '+' -> pointer.setValue('+');
					case '-' -> pointer.setValue('-');
					case '*' -> pointer.setValue('*');
					case '/' -> pointer.setValue('/');
				}
				pointer.setRightChild('x');
				pointer = pointer.getRightChild();
			}
			//Ja simbols ir skaitlis vai mainīgais, pašreizējā mezglā ievietot tā vērtību un pārvietoties uz vecāku;
			//ascii
			else if(Character.isDigit(algebraicTaskCharacters[i])){
				pointer.setValue(algebraicTaskCharacters[i]);
				pointer = pointer.getParent();
			}
			//Ja simbols ir ‘)’ pārvietoties uz pašreizējā mezgla vecāku.
			else if(algebraicTaskCharacters[i] == ')'){
				pointer = pointer.getParent();
			}
		}
		return mathTree;
	}




}
