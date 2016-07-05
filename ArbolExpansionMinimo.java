
public class ArbolExpansionMinimo
  {
	public class Vertice           
	{            
	  String nombre;
	  int numVertice;
	  public Vertice(String x)
	  {
	    nombre = x;
	    numVertice = -1;
	  }

	  public String nomVertice()
	  {
	    return nombre;
	  }

	  public boolean equals(Vertice n)
	  {
	    return nombre.equals(n.nombre);
	  }

	  public void asigVert(int n)
	  {
	    numVertice = n;
	  }

	  public String toString()
	  {
	    return nombre + " (" + numVertice + ")";
	  }
	}
	  private int [][] Pesos;
	  private int n;  // vértice origen y número de vértices

    public ArbolExpansionMinimo(Interfaz_Prim.GrafMatPeso gp)
    {
      n = gp.numeroDeVertices();
      Pesos = gp.matPeso;
    }

    public int arbolExpansionPrim(Interfaz_Prim.GrafMatPeso gra)
    {
      int longMin, menor;
      int z;
      int [] coste = new int [n];
      int [] masCerca = new int [n];
      boolean [] W = new boolean [n];
      for (int i = 0; i < n; i++)
        W[i] = false; // conjunto vacío 
      longMin = 0;
      W[0] = true;    //se parte del vértice 0 
          // inicialmente, coste[i] es la arista (0,i) 
      for (int i = 1; i < n; i++)
      {
        coste[i] = Pesos[0][i];
        masCerca[i] = 0;
      }
      for (int i = 1; i < n; i++)
      { // busca vértice z de V-W mas cercano, 
        // de menor longitud de arista, a algún vértice de W 
        menor = coste[1];
        z = 1;
        for (int j = 2; j < n; j++)
          if (coste[j] < menor)
          {
            menor = coste[j];
            z = j;
          }
        longMin += menor;
        // se escribe el arco incorporado al árbol de expansión
        Interfaz_Prim.GrafMatPeso.Vertice[] vs = gra.vertices(); 
        //System.out.println("Vertice:"+vs[masCerca[z]]+ " -> " + "Vertice:"+ vs[z]);
        Interfaz_Prim.grafito=Interfaz_Prim.grafito+"\n"+vs[masCerca[z]]+" -> "+vs[z];
        W[z] = true; // vértice z se añade al conjunto W
        coste[z] = Interfaz_Prim.GrafMatPeso.INFINITO;
        // debido a la incorporación de z,
        // se ajusta coste[] para el resto de  vértices
        for (int j = 1; j < n; j++)
          if ((Pesos[z][j] < coste[j]) && !W[j])
          {
            coste[j] = Pesos[z][j];
            masCerca[j] = z;
          }     }
      return longMin;
    }
  }

