package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		model.createGraph(3);
		
		List<String> tmp = new ArrayList<>(model.displayNeighbours("tre"));
		
		for(String s : tmp) {
			System.out.println(s);
		}
		
		System.out.println(model.gradoMax());
		
		/*
		model.createGraph(4);
		System.out.println(String.format("**Grafo creato**\n"));
		
		List<String> vicini = model.displayNeighbours("casa");
		System.out.println("Neighbours di casa: " + vicini + "\n");
		
		System.out.println("Cerco il vertice con grado massimo...");
		System.out.println(model.findMaxDegree());
		
		*/
	}

}
