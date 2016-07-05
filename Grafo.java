import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.*;
import java.io.*;

public class Grafo extends JFrame implements ActionListener
{
	Vector <String> lista_origen=new Vector <String>();
	Vector <String> lista_destino=new Vector <String>();
	Vector <String> lista_peso=new Vector<String>();
	Vector <String> lista_nodos=new Vector<String>();
	String ciudad_origen, ciudad_destino, fecha_salida, fecha_arribo;
	String distancia, asiento_disp, codigo;
	String cap_pasajero, cap_carga;
	static String grafito="";
	
	JLabel lbl_ingresa=new JLabel("Ingresa Ciudad:");
	JTextField txt_ingresa=new JTextField();
	JTextArea area_vuelos=new JTextArea();
	JScrollPane scroll=new JScrollPane(area_vuelos);
	JButton btn_buscar=new JButton("Amplitud");
	JButton btn_profundidad=new JButton("Profundidad");
	JButton btn_actualizar=new JButton("Crear Grafo");
	JButton btn_volver=new JButton("Volver");
	//JButton btn_prim=new JButton("Prim");
	
	public Grafo()
	{
		super("TPS_Actividad_V");
		super.setBounds(180, 0, 500, 540);
		ImageIcon Icono = new ImageIcon("avion.png"); 
		this.setIconImage(Icono.getImage());
		
		lbl_ingresa.setBounds(20, 10, 120, 20);
		txt_ingresa.setBounds(140,10,120,20);
		scroll.setBounds(270,10,210,500);
		btn_buscar.setBounds(10,40,120,20);
		btn_actualizar.setBounds(140,40,120,20);
		btn_profundidad.setBounds(10,70,120,20);
		btn_volver.setBounds(140, 70, 120, 20);
		//btn_prim.setBounds(10,100,102,20);
		
		this.add(lbl_ingresa);
		this.add(txt_ingresa);
		this.add(scroll);
		this.add(btn_actualizar);
		this.add(btn_buscar);
		this.add(btn_volver);
		this.add(btn_profundidad);
		//this.add(btn_prim);
		this.setLayout(null);
		this.setVisible(true);
		
		btn_volver.addActionListener(this);
		btn_buscar.addActionListener(this);
		btn_actualizar.addActionListener(this);
		btn_profundidad.addActionListener(this);
		//btn_prim.addActionListener(this);
		
		txt_ingresa.setText("Ciudad");
	}
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
	public class GrafoMatriz
	{
	  int numVerts;
	  static final int maxVerts = 20;            
	  Vertice [] verts;
	  int [][] matAd;
	                                        
	  public GrafoMatriz(int mx)
	  {
	    matAd = new int [mx][mx];
	    verts = new Vertice[mx];
	    for (int i = 0; i < mx; i++)
	      for (int j = 0; i < mx; i++)
	        matAd[i][j] = 0;
	    numVerts = 0;
	  }
	  
	  public GrafoMatriz()
	  {
	    this(maxVerts);
	  }
	 

	  public int numeroDeVertices()
	  {
	    return numVerts;
	  }
	  public Vertice[] vertices()
	  {
	    return verts;
	  }

	  public void nuevoVertice (String nom)
	  {
	    boolean esta = numVertice(nom) >= 0;
	    if (!esta) 
	    {
	      Vertice v = new Vertice(nom); 
	      v.asigVert(numVerts);
	      verts[numVerts++] = v;
	    }
	  }
	  public void nuevoArco(String a, String b)throws Exception
	  {
	    int va, vb;
	    va = numVertice(a);
	    vb = numVertice(b);
	    if (va < 0 || vb < 0) throw new Exception ("Vértice no existe");
	    matAd[va][vb] = 1;
	  }
	  public void nuevoArco(int va, int vb)throws Exception
	  {
	    if (va < 0 || vb < 0) throw new Exception ("Vértice no existe");
	    matAd[va][vb] = 1;
	  }
	  boolean adyacente(String a, String b)throws Exception
	  {
	    int va, vb;
	    va = numVertice(a);
	    vb = numVertice(b);
	    if (va < 0 || vb < 0) throw new Exception ("Vértice no existe");
	    return matAd[va][vb] == 1;
	  }

	  boolean adyacente(int va, int vb)throws Exception
	  {
	    if (va < 0 || vb < 0) throw new Exception ("Vértice no existe");
	    return matAd[va][vb] == 1;
	  }
	   
	  int numVertice(String vs)
	  {
	    Vertice v = new Vertice(vs);
	    boolean encontrado = false;
	    int i = 0;
	    for (; (i < numVerts) && !encontrado;)
	    {
	      encontrado = verts[i].equals(v);
	      if (!encontrado) i++ ; 
	    }
	    return (i < numVerts) ? i : -1 ;
	  }
	}
	public static int[]recorrerAmplitud(GrafoMatriz g, String org) throws Exception
	  {
	    int w, v;
	    int [] m;
	    grafito="Recorrido en Amplitud:";
	    v = g.numVertice(org);
	    if(v<0)
	    {
	    	grafito="Ciudad de Origen Inexistente";
	    }
	    if (v < 0) throw new Exception(" Vértice origen no existe");
	    LinkedList<Integer> cola=new LinkedList<Integer>();
	    //PriorityQueue<Integer> cola = new PriorityQueue<Integer>();  
	    m = new int[g.numeroDeVertices()];
	    // inicializa los vértices como no marcados 
	    for (int i = 0; i < g.numeroDeVertices(); i++)
	      m[i] = -5;
	    m[v] = 0; // vértice origen queda marcado   
	    
	  cola.addLast(new Integer(v));   
	    while (! cola.isEmpty())
	    {
	    Integer cw;
	    cw = (Integer) cola.removeFirst();
	      w = cw.intValue();
	      System.out.println("Vértice " + g.verts[w] + " visitado"); 
	      // inserta en la cola los adyacentes de w no marcados 
	      grafito=grafito+"\nCiudad: " + g.verts[w];
	      for (int u = 0; u < g.numeroDeVertices(); u++)
	        if ((g.matAd[w][u] == 1) && (m[u] == -5))
	        {
	          // se marca vertice u con número de arcos hasta el 
	          m[u] = m[w] +1;
	          cola.addLast(new Integer(u));
	        }
	    }
	    return m;
	  }
	public void mostrar_archivo(String info,int num)
	{
		try
		{
			RandomAccessFile file_vuelo=new RandomAccessFile("aviacion.txt","r");
			while(file_vuelo.getFilePointer()!=file_vuelo.length())
			{
				ciudad_origen=file_vuelo.readUTF();
				ciudad_destino=file_vuelo.readUTF();
				fecha_salida=file_vuelo.readUTF();
				fecha_arribo=file_vuelo.readUTF();
				distancia=file_vuelo.readUTF();
				cap_pasajero=file_vuelo.readUTF();
				cap_carga=file_vuelo.readUTF();
				asiento_disp=file_vuelo.readUTF();
				codigo=file_vuelo.readUTF();
				if(info.equals(ciudad_origen))
				{
					grafito=grafito+"\n\tVuelo: "+num+"\n\tCiudad de Origen: "+ciudad_origen+"\n\tCiudad Destino: "+ciudad_destino+"\n\tFecha de Salida: "+fecha_salida
							+"\n\tFecha de Arribo:"+fecha_arribo+"\n\tDistancia del Vuelo: "+distancia+"\n\tCapacidad de Pasajeros: "+cap_pasajero
							+"\n\tCapacidad de Carga: "+cap_carga+"\n\tAsientos Disponibles: "+asiento_disp+"\n\tCodigo de Vuelo: "+codigo;
				}
			}
			file_vuelo.close();
		}
		catch(IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "Error al Mostrar");
		}
	}
	public void llenar_lista()
	{
		int bandera=0, bandera_1=0;
		try
		{
			RandomAccessFile file_vuelo=new RandomAccessFile("aviacion.txt","r");
			if(file_vuelo.length()!=0)
			{
				while(file_vuelo.getFilePointer()!=file_vuelo.length())
				{
					bandera=0; bandera_1=0;
					ciudad_origen=file_vuelo.readUTF();
					ciudad_destino=file_vuelo.readUTF();
					fecha_salida=file_vuelo.readUTF();
					fecha_arribo=file_vuelo.readUTF();
					distancia=file_vuelo.readUTF();
					cap_pasajero=file_vuelo.readUTF();
					cap_carga=file_vuelo.readUTF();
					asiento_disp=file_vuelo.readUTF();
					codigo=file_vuelo.readUTF();
					System.out.println("LLenar las Listas");
					lista_origen.add(ciudad_origen);
					lista_destino.add(ciudad_destino);
					lista_peso.add(distancia);
					if(lista_nodos.isEmpty())
					{
						if(!ciudad_origen.equals(ciudad_destino))
						{
							lista_nodos.add(ciudad_origen);
							lista_nodos.add(ciudad_destino);
						}
					}
					else
					{
						for(int i=0; i<lista_nodos.size(); i++)
						{
							if(ciudad_origen.equals(lista_nodos.get(i)))
							{
								bandera=1;
							}
							if(ciudad_destino.equals(lista_nodos.get(i)))
							{
								bandera_1=1;
							}
						}
						if(bandera==0)
						{
							lista_nodos.add(ciudad_origen);
						}
						if(bandera_1==0)
						{
							lista_nodos.add(ciudad_destino);
						}
					}
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Archivo Inexistente");
			}
			file_vuelo.close();
		}
		catch(IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "Error al crear Grafo");
		}
	}
	static public  int[] recorrerProf(GrafoMatriz g, String org) throws Exception
	{
		grafito="Recorrido en Profundidad:";
	    int v, w;
	    Stack<Integer> pila = new Stack<Integer>();
	    int [] m;
	    m = new int[g.numeroDeVertices()];
	    // inicializa los vértices como no marcados 
	    v = g.numVertice(org);
	    if(v<0)
	    {
	    	grafito="Ciudad de Origen Inexistente";
	    }
	    if (v < 0) throw new Exception(" Vértice origen no existe");
	    for (int i = 0; i < g.numeroDeVertices(); i++)
	      m[i] = -5;
	    m[v] = 0;  // vértice origen queda marcado   

	    pila.add(new Integer(v));
	    while (!pila.isEmpty())
	    {
	    Integer cw;
	    cw = (Integer) pila.pop();
	      w = cw.intValue();
	      System.out.println("\nCiudad: " + g.verts[w] + " visitado"); 
	      // inserta en la pila los adyacentes de w no marcados
	      //mostrar_archivo(g.verts[w],w);
	      grafito=grafito+"\nCiudad: "+g.verts[w];
	      for (int u = 0; u < g.numeroDeVertices(); u++)
	        // adyacente y no marcado
	        if ((g.matAd[w][u] == 1) && (m[u] == -5))        
	        {
	          pila.add(new Integer(u));
	          m[u] = 1;
	        }
	    }
	    return m;
	  }
	
	public void actionPerformed(ActionEvent evt)
	{
		Object presionado=evt.getSource();
		
		if(presionado==btn_actualizar)
		{
			llenar_lista();
			//BufferedReader entrada = new BufferedReader (new InputStreamReader (System.in));
		      String v, w;
		      try 
		      {
		        int n;
		        //System.out.println("Número de nodos: ");
		        //n = Integer.parseInt(entrada.readLine());
		        n=lista_nodos.size();
		        GrafoMatriz gra = new GrafoMatriz(n);
		      
		        System.out.println("Lista de " + n + " vertices");
		        for (int i = 0; i <lista_nodos.size(); i++)
		        {
		          //v = entrada.readLine();
		        	v=lista_nodos.get(i);
		          gra.nuevoVertice(v);
		        }
		      
		        System.out.println("Arcos, pares de vertices separados por un espacio");
		        boolean fin = false;
		        /*do {
		          StringTokenizer r;
		          r = new StringTokenizer(entrada.readLine());
		          v = r.nextToken();
		          if (! v.equalsIgnoreCase("fin"))
		          {
		            w = r.nextToken();
		            gra.nuevoArco(v, w);
		            gra.nuevoArco(w, v);  // al ser grafo no dirigido
		          }
		          else fin = true;
		        } while (!fin);*/
		        for(int i=0; i<lista_origen.size(); i++)
		        {
		        	v=lista_origen.get(i);
		        	w=lista_destino.get(i);
		        	gra.nuevoArco(v,w);
		        	gra.nuevoArco(w,v);
		        }
		      
		        System.out.println("Vertices del grafo");
		        grafito="Grafo Realizado\nVertices del Grafo:";
		        Vertice [] vs = new Vertice[n];
		        vs = gra.vertices();
		        for (int i = 0; i < n; i++)
		        {
		          System.out.print(vs[i] + " ");
		          grafito=grafito+"\n"+vs[i];
		        }
		      area_vuelos.setText(grafito);
		      }
		      catch(Exception e)
		      {
		        System.out.println(" Error en la entrada del grafo ");
		      }
			btn_actualizar.setText("Actualizar");
		}
		if(presionado==btn_buscar)
		{
			String v, w;
		      try 
		      {
		        int n;
		        //System.out.println("Número de nodos: ");
		        //n = Integer.parseInt(entrada.readLine());
		        n=lista_nodos.size();
		        GrafoMatriz gra = new GrafoMatriz(n);
		      
		        //System.out.println("Lista de " + n + " vertices");
		        for (int i = 0; i <lista_nodos.size(); i++)
		        {
		          //v = entrada.readLine();
		        	v=lista_nodos.get(i);
		          gra.nuevoVertice(v);
		        }
		      
		        //System.out.println("Arcos, pares de vertices separados por un espacio");
		        boolean fin = false;
		        /*do {
		          StringTokenizer r;
		          r = new StringTokenizer(entrada.readLine());
		          v = r.nextToken();
		          if (! v.equalsIgnoreCase("fin"))
		          {
		            w = r.nextToken();
		            gra.nuevoArco(v, w);
		            gra.nuevoArco(w, v);  // al ser grafo no dirigido
		          }
		          else fin = true;
		        } while (!fin);*/
		        for(int i=0; i<lista_origen.size(); i++)
		        {
		        	v=lista_origen.get(i);
		        	w=lista_destino.get(i);
		        	gra.nuevoArco(v,w);
		        	gra.nuevoArco(w,v);
		        }
		      
		        String cad_1="";
		        cad_1=txt_ingresa.getText();
		        int[] n_1=new int[10];
		        System.out.println("\nRecorrido en Anchura:");
		        n_1=recorrerAmplitud(gra, cad_1);
		        //System.out.println("\nRecorrido en Profundidad: ");
		        //n_1=recorrerProf(gra, cad_1);
		        area_vuelos.setText(grafito);
		        grafito="";
		      }
		      catch(Exception e)
		      {
		    	  area_vuelos.setText(grafito);System.out.println(" Error en la entrada del grafo ");
		      }
		}
		if(presionado==btn_profundidad)
		{
			String v, w;
		      try 
		      {
		        int n;
		        //System.out.println("Número de nodos: ");
		        //n = Integer.parseInt(entrada.readLine());
		        n=lista_nodos.size();
		        GrafoMatriz gra = new GrafoMatriz(n);
		      
		        //System.out.println("Lista de " + n + " vertices");
		        for (int i = 0; i <lista_nodos.size(); i++)
		        {
		          //v = entrada.readLine();
		        	v=lista_nodos.get(i);
		          gra.nuevoVertice(v);
		        }
		      
		        //System.out.println("Arcos, pares de vertices separados por un espacio");
		        boolean fin = false;
		        /*do {
		          StringTokenizer r;
		          r = new StringTokenizer(entrada.readLine());
		          v = r.nextToken();
		          if (! v.equalsIgnoreCase("fin"))
		          {
		            w = r.nextToken();
		            gra.nuevoArco(v, w);
		            gra.nuevoArco(w, v);  // al ser grafo no dirigido
		          }
		          else fin = true;
		        } while (!fin);*/
		        for(int i=0; i<lista_origen.size(); i++)
		        {
		        	v=lista_origen.get(i);
		        	w=lista_destino.get(i);
		        	gra.nuevoArco(v,w);
		        	gra.nuevoArco(w,v);
		        }
		      
		        String cad_1="", cad_busqueda="";
		        //cad_1=lista_origen.get(0);
		        cad_1=txt_ingresa.getText();
		        int[] n_1=new int[10];
		        //System.out.println("\nRecorrido en Anchura:");
		        //n_1=recorrerAnchura(gra, cad_1);
		        System.out.println("\nRecorrido en Profundidad: ");
		        n_1=recorrerProf(gra, cad_1);
		        area_vuelos.setText(grafito);
		        grafito="";
		      }
		      catch(Exception e)
		      {
		    	  area_vuelos.setText(grafito);System.out.println(" Error en la entrada del grafo ");
		      }
		}
		if(presionado==btn_volver)
		{
			this.setVisible(false);
			Aviacion x=new Aviacion();
		}
	}
	
}
