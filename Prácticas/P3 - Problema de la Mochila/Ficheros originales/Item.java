
public class Item implements Comparable<Item>{
	int index;
	int peso;
	int valor;
	int unidades;
	
	public Item(int index, int peso, int valor, int unidades) {
		this.index = index;
		this.peso = peso;
		this.valor = valor;
		this.unidades=unidades;
	}
	
	//Si ordenamos una colecci�n de tuplas, lo haremos por el valor 
	public int compareTo (Item otro){
		if (valor==otro.valor) return 0;
		else if (valor<otro.valor) return -1;
		return 1;  
	  }
}
	
