package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	
	List<String> parole;
	WordDAO dao;
	SimpleGraph<String,DefaultEdge> grafo;
	
	public Model() {
		dao = new WordDAO();
		grafo = new SimpleGraph<>(DefaultEdge.class);
	}
	

	public void createGraph(int numeroLettere) {
		parole = new ArrayList<>(dao.getAllWordsFixedLength(numeroLettere));
		//System.out.println(parole.size());
		Graphs.addAllVertices(grafo, parole);
		for(String s1 : parole) {
			for(String s2 : parole) {
				if(!s1.equals(s2) && this.isSimili(s1,s2)) {
					grafo.addEdge(s1, s2);
				}
			}
		}
		
	}

	private boolean isSimili(String s1, String s2) {
		int distanza = 1;
		for (int i = 0; i< s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i))
				distanza --;
		}
		
		if (distanza == 0)
			return true;
		else
			return false;
			
	}

	public List<String> displayNeighbours(String parolaInserita) {
	if(!dao.isCorretta(parolaInserita)) {
		return null;
	}else {
	List<String> paroleVicine = new ArrayList<>(Graphs.neighborListOf(this.grafo, parolaInserita));		
		return paroleVicine;
	}
	}
	
	public String gradoMax(){
		int max=-1;
		String vertice = null;
		for(String s : this.grafo.vertexSet()) {
			if(grafo.degreeOf(s)>max) {
				max=grafo.degreeOf(s);
				vertice = s;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append("Livello Massimo: "+max+"\n");
		sb.append("Vertice : "+vertice+"\n");
		sb.append(this.displayNeighbours(vertice));
		return sb.toString();
	}
	
	
	public boolean presente(String parola) {
		return dao.isCorretta(parola);
	}
	public int findMaxDegree() {
		System.err.println("findMaxDegree -- TODO");
		return -1;
	}
}
