import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.*;

public class Interfaz_Prim extends JFrame implements ActionListener
{
	Vector <String> lista_origen=new Vector <String>();
	Vector <String> lista_destino=new Vector <String>();
	Vector <String> lista_peso=new Vector<String>();
	Vector <String> lista_nodos=new Vector<String>();
	String ciudad_origen, ciudad_destino, fecha_salida, fecha_arribo;
	String distancia, asiento_disp, codigo;
	String cap_pasajero, cap_carga;
	int flag=0;
	static String grafito="";
	public class GrafMatPeso
	{
	  static final int INFINITO = 0xFFFF;
	  int [][] matPeso;
	  
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
	  int numVerts;
	  static final int maxVerts = 20;            
	  Vertice [] verts;
	                                          
	  public GrafMatPeso(int mx)
	  {
	    matPeso = new int [mx][mx];
	    verts = new Vertice[mx];
	    for (int i = 0; i < mx; i++)
	      for (int j = 0; j < mx; j++)
	        matPeso[i][j] = INFINITO;
	    numVerts = 0;
	  }
	  
	  public GrafMatPeso()
	  {
	    this(maxVerts);
	  }

	  public int pesoArco(int va, int vb) throws Exception
	  { 
	    return matPeso[va][vb];
	  }
	  
	  public int pesoArco(String a, String b) throws Exception
	  { 
	    int va, vb;
	    va = numVertice(a);
	    vb = numVertice(b);
	    return matPeso[va][vb];
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
	  
	  public void nuevoArco(String a, String b, int peso)throws Exception
	  {
	    int va, vb;
	    va = numVertice(a);
	    vb = numVertice(b);
	    if (va < 0 || vb < 0) 
	          throw new Exception ("Vértice no existe");
	    matPeso[va][vb] = peso;
	  }

	  public void nuevoArco(int va, int vb, int peso)throws Exception
	  {
	    if (va < 0 || vb < 0) 
	      throw new Exception ("Vértice no existe");
	    matPeso[va][vb] = peso;
	  }
	  
	  public boolean adyacente(String a, String b)throws Exception
	  {
	    int va, vb;
	    va = numVertice(a);
	    vb = numVertice(b);
	    if (va < 0 || vb < 0) 
	              throw new Exception ("Vértice no existe");
	    return matPeso[va][vb] != INFINITO;
	  }

	  public boolean adyacente(int va, int vb)throws Exception
	  {
	    if (va < 0 || vb < 0) 
	              throw new Exception ("Vértice no existe");
	    return matPeso[va][vb] != INFINITO;
	  }
	  
	  public int numVertice(String vs)
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
	  
	  public void llenar_grafo(GrafMatPeso gra) throws Exception
	  {
	    String v, w;
	    int peso, n;
	    n=lista_nodos.size();
	    //System.out.println("\nLista de " + n + " vertices");
	    for (int i = 0; i < n; i++)
	    {
	      //v = entrada.readLine();
	    	v=lista_nodos.get(i);
	      gra.nuevoVertice(v);
	    }
	      
	    //System.out.println("Arcos, pares de vertices separados por un espacio y peso, "+"(termina con fin)");
	         boolean fin = false;

	         for(int i=0; i<lista_origen.size(); i++)
		        {
		        	v=lista_origen.get(i);
		        	w=lista_destino.get(i);
		        	peso=Integer.parseInt(lista_peso.get(i));
		        	gra.nuevoArco(v,w,peso);
		        	gra.nuevoArco(w,v,peso);
		        }
	    //System.out.println("Vertices del grafo");
	    Vertice [] vs = new Vertice[n];
	    vs = gra.vertices();
	    for (int i = 0; i < n; i++)
	    {
	    	grafito=grafito+"\n"+vs[i];
	      //System.out.print(vs[i] + " ");
	    }
	  }
	  public void llenar_prim(GrafMatPeso gra) throws Exception
	  {
	    String v, w;
	    int peso, n;
	    n=lista_nodos.size();
	    for (int i = 0; i < n; i++)
	    {
	      //v = entrada.readLine();
	    	v=lista_nodos.get(i);
	      gra.nuevoVertice(v);
	    }
	         boolean fin = false;

	         for(int i=0; i<lista_origen.size(); i++)
		        {
		        	v=lista_origen.get(i);
		        	w=lista_destino.get(i);
		        	peso=Integer.parseInt(lista_peso.get(i));
		        	gra.nuevoArco(v,w,peso);
		        	gra.nuevoArco(w,v,peso);
		        }
	    //System.out.println("\nArbol de Expansion Prim");
	    grafito="Arbol de Expancion Prim: ";
	    ArbolExpansionMinimo x=new ArbolExpansionMinimo(gra);
	    int a=0;
	    a=x.arbolExpansionPrim(gra);
	    System.out.println("\n longitud minima"+a);
	  }
	}
	JButton btn_crear=new JButton("Crear");
	JButton btn_volver=new JButton("Volver");
	JButton btn_prim=new JButton("Algoritmo Prim");
	
	JTextArea area_vuelos=new JTextArea();
	JScrollPane scroll=new JScrollPane(area_vuelos);
	public Interfaz_Prim()
	{
		super("TPS_Actividad_VI");
		super.setBounds(180, 0, 280, 500);
		ImageIcon Icono = new ImageIcon("avion.png"); 
		this.setIconImage(Icono.getImage());
		
		btn_crear.setBounds(10,10,120,20);
		btn_volver.setBounds(140,10,120,20);
		btn_prim.setBounds(70,40,120,20);
		scroll.setBounds(10,70,260,400);
		
		this.add(scroll);
		this.add(btn_crear);
		this.add(btn_prim);
		this.add(btn_volver);
		
		this.setLayout(null);
		this.setVisible(true);
		
		btn_crear.addActionListener(this);
		btn_volver.addActionListener(this);
		btn_prim.addActionListener(this);
		
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
					//System.out.println("LLenar las Listas");
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
	public void actionPerformed(ActionEvent evt)
	{
		Object presionado=evt.getSource();
		if(presionado==btn_crear)
		{
			flag=1;
			grafito="Vertices del Grafo: ";
			llenar_lista();
			GrafMatPeso gmp=new GrafMatPeso();
			try 
			{
				gmp.llenar_grafo(gmp);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			area_vuelos.setText(grafito);
		}
		if(presionado==btn_prim)
		{
			if(flag==1)
			{
			GrafMatPeso gmp=new GrafMatPeso();
			try 
			{
				gmp.llenar_prim(gmp);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			area_vuelos.setText(grafito);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "No Has Creado El Grafo");
			}
		}
		if(presionado==btn_volver)
		{
			this.setVisible(false);
			Aviacion avi=new Aviacion();
		}
	}

}
