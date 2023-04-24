package main;

import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import dataSTr.MyLinkedTree;
import dataSTr.MyNode;

public class Main {

	public static void main(String[] args) {
		try {
			MyLinkedTree<String> newTree = createMathTree("(((81+23)*25)/5)"); //520
//			newTree.print();

			System.out.println("Tree result: " + executeMathTree(newTree));
//			System.out.println(Arrays.toString(algebraicTaskToStringMassive("(((81+23)*25)/5)")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static MyLinkedTree<String> createMathTree(String algebraicTask) {
		//String "(((81+23)*25)/5)"
		MyLinkedTree<String> mathTree = new MyLinkedTree<>("P");
		String[] algebraicTaskMassive = algebraicTaskToStringMassive(algebraicTask);
		MyNode pointer = mathTree.getRoot(); //pašreizējs mezgls
		for(int i = 0; i < algebraicTaskMassive.length; i++){
			//Ja simbols ir ‘(‘ pašreizējam mezglam pievienot jaunu mezglu kā kreiso bērnu un pārvietoties uz to;
			// ascii ( 40
			if(Objects.equals(algebraicTaskMassive[i], "(")){
				pointer.setLeftChild("x");
				pointer = pointer.getLeftChild();
			}
			//Ja simbols ir kāds no operatoriem (+, -, /, *), tad pašreizējā mezglā ievietot tā vērtību, izveidot labo bērnu un pārvietoties uz to;
			else if(Objects.equals(algebraicTaskMassive[i], "+") ||
					Objects.equals(algebraicTaskMassive[i], "-") ||
					Objects.equals(algebraicTaskMassive[i], "*") ||
					Objects.equals(algebraicTaskMassive[i], "/")){
				switch (algebraicTaskMassive[i]) {
					case "+" -> pointer.setValue("+");
					case "-" -> pointer.setValue("-");
					case "*" -> pointer.setValue("*");
					case "/" -> pointer.setValue("/");
				}
				pointer.setRightChild("x");
				pointer = pointer.getRightChild();
			}
			//Ja simbols ir skaitlis vai mainīgais, pašreizējā mezglā ievietot tā vērtību un pārvietoties uz vecāku;
			//ascii
			else if(Character.isDigit(algebraicTaskMassive[i].charAt(0))){
				pointer.setValue(algebraicTaskMassive[i]);
				pointer = pointer.getParent();
			}
			//Ja simbols ir ‘)’ pārvietoties uz pašreizējā mezgla vecāku.
			else if(Objects.equals(algebraicTaskMassive[i], ")")){
				pointer = pointer.getParent();
			}
		}
		return mathTree;
	}

	public static int executeMathTree(MyLinkedTree<String> mathTree){
		//find the "left"iest
		//execute, until "left"iest == root.getleftchild
		//find the "right"iest
		//execute, until "right"iest == root.getrightchild
		//execute root's right and left child

		MyNode<String> theLeftiest = mathTree.getRoot();
		while(theLeftiest.getLeftChild() != null){
			theLeftiest = theLeftiest.getLeftChild();
		}
		//execute left side of the tree
		int tempResultLeft;
		while(theLeftiest != mathTree.getRoot().getLeftChild()){
			switch(theLeftiest.getParent().getValue()){
				case "+" -> {
					tempResultLeft = Integer.parseInt(theLeftiest.getValue()) + Integer.parseInt( theLeftiest.getParent().getRightChild().getValue());
					theLeftiest.getParent().setValue(String.valueOf(tempResultLeft));
					theLeftiest = theLeftiest.getParent();
					theLeftiest.deleteChildren();
				}
				case "-" -> {
					tempResultLeft = Integer.parseInt(theLeftiest.getValue()) - Integer.parseInt( theLeftiest.getParent().getRightChild().getValue());
					theLeftiest.getParent().setValue(String.valueOf(tempResultLeft));
					theLeftiest = theLeftiest.getParent();
					theLeftiest.deleteChildren();
				}
				case "*" -> {
					tempResultLeft = Integer.parseInt(theLeftiest.getValue()) * Integer.parseInt( theLeftiest.getParent().getRightChild().getValue());
					theLeftiest.getParent().setValue(String.valueOf(tempResultLeft));
					theLeftiest = theLeftiest.getParent();
					theLeftiest.deleteChildren();
				}
				case "/" -> {
					tempResultLeft = Integer.parseInt(theLeftiest.getValue()) / Integer.parseInt( theLeftiest.getParent().getRightChild().getValue());
					theLeftiest.getParent().setValue(String.valueOf(tempResultLeft));
					theLeftiest = theLeftiest.getParent();
					theLeftiest.deleteChildren();
				}
			}
		}

		MyNode<String> theRightiest = mathTree.getRoot();
		while(theRightiest.getRightChild() != null){
			theRightiest = theRightiest.getRightChild();
		}
		//execute right side of the tree
		int tempResultRight;
		while(theRightiest != mathTree.getRoot().getRightChild()){
			switch(theRightiest.getParent().getValue()){
				case "+" -> {
					tempResultRight = Integer.parseInt(theRightiest.getParent().getLeftChild().getValue()) + Integer.parseInt(theRightiest.getValue());
					theRightiest.getParent().setValue(String.valueOf(tempResultRight));
					theRightiest = theRightiest.getParent();
					theRightiest.deleteChildren();
				}
				case "-" -> {
					tempResultRight = Integer.parseInt(theRightiest.getParent().getLeftChild().getValue()) - Integer.parseInt(theRightiest.getValue());
					theRightiest.getParent().setValue(String.valueOf(tempResultRight));
					theRightiest = theRightiest.getParent();
					theRightiest.deleteChildren();
				}
				case "*" -> {
					tempResultRight = Integer.parseInt(theRightiest.getParent().getLeftChild().getValue()) * Integer.parseInt(theRightiest.getValue());
					theRightiest.getParent().setValue(String.valueOf(tempResultRight));
					theRightiest = theRightiest.getParent();
					theRightiest.deleteChildren();
				}
				case "/" -> {
					tempResultRight = Integer.parseInt(theRightiest.getParent().getLeftChild().getValue()) / Integer.parseInt(theRightiest.getValue());
					theRightiest.getParent().setValue(String.valueOf(tempResultRight));
					theRightiest = theRightiest.getParent();
					theRightiest.deleteChildren();
				}
			}
		}
		int result = 0;
		switch (mathTree.getRoot().getValue()) {
			case "+" -> {
				result = Integer.parseInt(mathTree.getRoot().getLeftChild().getValue()) + Integer.parseInt(mathTree.getRoot().getRightChild().getValue());
				mathTree.clear();
			}
			case "-" -> {
				result = Integer.parseInt(mathTree.getRoot().getLeftChild().getValue()) - Integer.parseInt(mathTree.getRoot().getRightChild().getValue());
				mathTree.clear();
			}
			case "*" -> {
				result = Integer.parseInt(mathTree.getRoot().getLeftChild().getValue()) * Integer.parseInt(mathTree.getRoot().getRightChild().getValue());
				mathTree.clear();
			}
			case "/" -> {
				result = Integer.parseInt(mathTree.getRoot().getLeftChild().getValue()) / Integer.parseInt(mathTree.getRoot().getRightChild().getValue());
				mathTree.clear();
			}
		}
		return result;
	}

	public static String[] algebraicTaskToStringMassive(String algebraicTask){
		ArrayList<String> tempMassive = new ArrayList<>();

		for(int i = 0; i < algebraicTask.length(); i++){
			if(algebraicTask.charAt(i) == '('){
				tempMassive.add("(");
			} else if (algebraicTask.charAt(i) == ')'){
				tempMassive.add(")");
			} else if(algebraicTask.charAt(i) == '+' ||
					algebraicTask.charAt(i) == '-' ||
					algebraicTask.charAt(i) == '*' ||
					algebraicTask.charAt(i) == '/'){
				switch (algebraicTask.charAt(i)) {
					case '+' -> tempMassive.add("+");
					case '-' -> tempMassive.add("-");
					case '*' -> tempMassive.add("*");
					case '/' -> tempMassive.add("/");
				}
			} else if(Character.isDigit(algebraicTask.charAt(i))){
				char c1 = algebraicTask.charAt(i);
				String number = String.valueOf(c1);
				while(Character.isDigit(algebraicTask.charAt(i+1))){
					char c2 = algebraicTask.charAt(i+1);
					number += String.valueOf(c2);
					i++;
				}
				tempMassive.add(number);
			}
		}

		return (tempMassive.toArray(new String[tempMassive.size()]));
	}

}
