import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.*;
import java.io.*;
public class Arbol_Binario extends JFrame implements ActionListener
{
	class Nodo
	{
	   	//atributos
	   	String info;
	   	Nodo izq,der;
	}
	Nodo raiz;
	Vector<String> lista_vuelo=new Vector<String>();
	String ciudad_origen, ciudad_destino, fecha_salida, fecha_arribo;
	String distancia, asiento_disp, codigo;
	String cap_pasajero, cap_carga;
	String arbolito="", busqueda, duplicacion;
	int num_vuelo=0, flag_buscar=0, flag_duplicacion;
	JLabel lbl_crear_arbol=new JLabel("Crear Arbol: ");
	
	JTextField txt_buscar=new JTextField();
	
	static String[] lista_arbol={"","Ciudad Destino","Capasidad de Carga","Fecha de Arribo","Asientos Disponibles"};
	JComboBox<String> box_arboles= new JComboBox<String>(lista_arbol);
	
	JButton btn_crear=new JButton("Crear");
	JButton btn_volver=new JButton("Volver");
	JButton btn_preorden=new JButton("Pre-Orden");
	JButton btn_inorden=new JButton("In-Orden");
	JButton btn_postorden=new JButton("Post-Orden");
	JButton btn_buscar=new JButton("Buscar");
	
	JTextArea area_vuelos=new JTextArea();
	JScrollPane scroll=new JScrollPane(area_vuelos);
	
	Arbol_Binario()
	{
		super("TPS_Actividad_V");
		super.setBounds(180, 0, 405, 540);
		ImageIcon Icono = new ImageIcon("avion.png"); 
		this.setIconImage(Icono.getImage());
		
		lbl_crear_arbol.setBounds(10,10,80, 20);
		box_arboles.setBounds(100,10,120,20);
		btn_crear.setBounds(270,10,120,20);
		btn_preorden.setBounds(10,40,120,20);
		btn_inorden.setBounds(140,40,120,20);
		btn_postorden.setBounds(270,40,120,20);
		txt_buscar.setBounds(10,70,120,20);
		btn_buscar.setBounds(140,70,120,20);
		btn_volver.setBounds(270,70,120,20);
		scroll.setBounds(10,100,380,400);
		
		this.add(lbl_crear_arbol);
		this.add(box_arboles);
		this.add(btn_crear);
		this.add(btn_volver);
		this.add(btn_preorden);
		this.add(btn_inorden);
		this.add(btn_postorden);
		this.add(btn_buscar);
		this.add(txt_buscar);
		this.add(scroll);
		
		this.setLayout(null);
		this.setVisible(true);
		
		btn_crear.addActionListener(this);
		btn_volver.addActionListener(this);
		btn_preorden.addActionListener(this);
		btn_inorden.addActionListener(this);
		btn_postorden.addActionListener(this);
		btn_buscar.addActionListener(this);
		
		btn_preorden.setEnabled(false);
		btn_inorden.setEnabled(false);
		btn_postorden.setEnabled(false);
		txt_buscar.setEnabled(false);
		btn_buscar.setEnabled(false);
		txt_buscar.setText("Ingres Busqueda");
		raiz=null;
	}
	private void inicializar(Nodo n)
	{
		if(n!=null)
		{
			inicializar(n.izq);
			inicializar(n.der);
			n=null;
		}
	}
	public void inicializar_arbol()
	{
		inicializar(raiz);
		raiz=null;
	}
	public void insertar_2(String info)
	{    	
    	Nodo nuevo=new Nodo();
    	nuevo.info=info;
    	nuevo.izq=null;
    	nuevo.der=null;
    	if (raiz==null)
    		raiz=nuevo;
    	else{
    		Nodo anterior=null,reco;
    		reco=raiz;
    		while(reco!=null){
    			
    			anterior=reco;
    			if (info.compareTo(reco.info)<0)
    				reco=reco.izq;
    			else
    				reco=reco.der;
    		}//while
    		if(info.compareTo(anterior.info)<0)
    			anterior.izq=nuevo;
    		else
    			anterior.der=nuevo;
    	}
    }
	public void insertar_1()
    {
    	int can;
    	String cad="";
    
    	//System.out.print("Ingresa la cantidad de nodos para crear el arbol: ");
    	can=lista_vuelo.size();
    	for(int i=0;i<can;i++){
    		//System.out.print("Ingrese nodo "+(i+1)+" :");
    		//num=s.next();
    		cad=lista_vuelo.get(i);
    		insertar_2(cad);		
    	}
    }
	public void mostrar_archivo(String info)
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
				if(box_arboles.getSelectedItem().equals(lista_arbol[1])&& info.equals(ciudad_destino))
				{
					num_vuelo++;
					arbolito=arbolito+"\n\tVuelo: "+num_vuelo+"\n\tCiudad de Origen: "+ciudad_origen+"\n\tCiudad Destino: "+ciudad_destino+"\n\tFecha de Salida: "+fecha_salida
							+"\n\tFecha de Arribo:"+fecha_arribo+"\n\tDistancia del Vuelo: "+distancia+"\n\tCapacidad de Pasajeros: "+cap_pasajero
							+"\n\tCapacidad de Carga: "+cap_carga+"\n\tAsientos Disponibles: "+asiento_disp+"\n\tCodigo de Vuelo: "+codigo;
				}
				if(box_arboles.getSelectedItem().equals(lista_arbol[2])&& info.equals(cap_carga))
				{
					num_vuelo++;
					arbolito=arbolito+"\n\tVuelo: "+num_vuelo+"\n\tCiudad de Origen: "+ciudad_origen+"\n\tCiudad Destino: "+ciudad_destino+"\n\tFecha de Salida: "+fecha_salida
							+"\n\tFecha de Arribo:"+fecha_arribo+"\n\tDistancia del Vuelo: "+distancia+"\n\tCapacidad de Pasajeros: "+cap_pasajero
							+"\n\tCapacidad de Carga: "+cap_carga+"\n\tAsientos Disponibles: "+asiento_disp+"\n\tCodigo de Vuelo: "+codigo;
				}
				if(box_arboles.getSelectedItem().equals(lista_arbol[3])&& info.equals(fecha_arribo))
				{
					num_vuelo++;
					arbolito=arbolito+"\n\tVuelo: "+num_vuelo+"\n\tCiudad de Origen: "+ciudad_origen+"\n\tCiudad Destino: "+ciudad_destino+"\n\tFecha de Salida: "+fecha_salida
							+"\n\tFecha de Arribo:"+fecha_arribo+"\n\tDistancia del Vuelo: "+distancia+"\n\tCapacidad de Pasajeros: "+cap_pasajero
							+"\n\tCapacidad de Carga: "+cap_carga+"\n\tAsientos Disponibles: "+asiento_disp+"\n\tCodigo de Vuelo: "+codigo;
				}
				if(box_arboles.getSelectedItem().equals(lista_arbol[4])&& info.equals(asiento_disp))
				{
					num_vuelo++;
					arbolito=arbolito+"\n\tVuelo: "+num_vuelo+"\n\tCiudad de Origen: "+ciudad_origen+"\n\tCiudad Destino: "+ciudad_destino+"\n\tFecha de Salida: "+fecha_salida
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
	private void buscar(Nodo n)
	{
		if(n!=null)
		{
			arbolito=arbolito+"\n\t"+n.info;
			//mostrar_archivo(n.info);
			if(busqueda.compareTo(n.info)==0)
			{
				//JOptionPane.showMessageDialog(null, "Coincidencia Encontrada");
				flag_buscar=1;
			}
			else if(busqueda.compareTo(n.info)<0)
			{
				buscar(n.izq);
			}
			else if(busqueda.compareTo(n.info)>0)
			{
				buscar(n.der);
			}
		}
	}
	private void preorden(Nodo n)
	{
		//arbolito="Preorden: ";
    	if(n!=null)
    	{
    		//System.out.print(n.info +" ");
    		arbolito=arbolito+"\n\t"+ n.info;
    		//mostrar_archivo(n.info);
    		preorden(n.izq);
    		preorden(n.der);
    	}
    }
    public void preorden()
    {
    	preorden(raiz);
    	//System.out.println();
    }
    private void inorden(Nodo n)
    {
    	if(n!=null)
    	{
    		inorden(n.izq);
    		//System.out.print(n.info +" ");
    		arbolito=arbolito+"\n\t"+ n.info;
    		//mostrar_archivo(n.info);
    		inorden(n.der); 
    	}
    }
    public void inorden()
    {
    	inorden(raiz);
    	//System.out.println();
    }
    private void postorden(Nodo n)
    {
    	if(n!=null)
    	{
    		postorden(n.izq);
    		postorden(n.der);
    		//System.out.print(n.info +" ");
    		arbolito=arbolito+"\n\t"+ n.info;
    		//mostrar_archivo(n.info);
    	}
    }
    public void postorden()
    {
    	postorden(raiz);
    	//System.out.println();
    }
	public void llenar_lista(Vector<String> lista_vuelo)
	{
		try
		{
			RandomAccessFile file_vuelo=new RandomAccessFile("aviacion.txt","r");
			if(file_vuelo.length()!=0)
			{
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
					if(box_arboles.getSelectedItem().equals(lista_arbol[1]))
					{
						duplicacion="\n\n\tCiudad Destino Duplicada: ";
						for(int i=0; i<lista_vuelo.size(); i++)
						{
							if(ciudad_destino.equals(lista_vuelo.get(i)))
							{
								flag_duplicacion=1;
								duplicacion=duplicacion+"\n\tCiudad Destino: "+ciudad_destino;
							}
						}
						if(flag_duplicacion!=1)
						{
						lista_vuelo.add(ciudad_destino);
						}
					}
					if(box_arboles.getSelectedItem().equals(lista_arbol[2]))
					{
						duplicacion="\n\n\tCapacidad de Carga Duplicada: ";
						for(int i=0; i<lista_vuelo.size(); i++)
						{
							if(cap_carga.equals(lista_vuelo.get(i)))
							{
								flag_duplicacion=1;
								duplicacion=duplicacion+"\n\tCapacidad de Carga: "+cap_carga;
							}
						}
						if(flag_duplicacion!=1)
						{
						lista_vuelo.add(cap_carga);
						}
					}
					if(box_arboles.getSelectedItem().equals(lista_arbol[3]))
					{
						duplicacion="\n\n\tFecha de Arribo Duplicada: ";
						for(int i=0; i<lista_vuelo.size(); i++)
						{
							if(fecha_arribo.equals(lista_vuelo.get(i)))
							{
								flag_duplicacion=1;
								duplicacion=duplicacion+"\n\tFecha de Arribo:"+fecha_arribo;
							}
						}
						if(flag_duplicacion!=1)
						{
						lista_vuelo.add(fecha_arribo);
						}
					}
					if(box_arboles.getSelectedItem().equals(lista_arbol[4]))
					{
						duplicacion="\n\n\tAsientos Disponibles Duplicados: ";
						for(int i=0; i<lista_vuelo.size(); i++)
						{
							if(asiento_disp.equals(lista_vuelo.get(i)))
							{
								flag_duplicacion=1;
								duplicacion=duplicacion+"\n\tAsientos Disponibles: "+asiento_disp;
							}
						}
						if(flag_duplicacion!=1)
						{
							
							lista_vuelo.add(asiento_disp);
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
			JOptionPane.showMessageDialog(null, "Error al Llenar la Lista");
		}
	}
	public void actionPerformed(ActionEvent evt)
	{
		Object presionado=evt.getSource();
		
		if(presionado==btn_crear)
		{
			flag_duplicacion=0;
			inicializar_arbol();
			llenar_lista(lista_vuelo);
			insertar_1();
			btn_preorden.setEnabled(true);
			btn_inorden.setEnabled(true);
			btn_postorden.setEnabled(true);
			txt_buscar.setEnabled(true);
			btn_buscar.setEnabled(true);
			lista_vuelo.clear();
			if(box_arboles.getSelectedItem().equals(lista_arbol[1]))
			{
				txt_buscar.setText("Ingrese Ciudad");
			}
			if(box_arboles.getSelectedItem().equals(lista_arbol[2]))
			{
				txt_buscar.setText("Ingrese Capacidad");
			}
			if(box_arboles.getSelectedItem().equals(lista_arbol[3]))
			{
				txt_buscar.setText("Ingrese Fecha");
			}
			if(box_arboles.getSelectedItem().equals(lista_arbol[4]))
			{
				txt_buscar.setText("Ingrese Asientos");
			}
		}
		if(presionado==btn_preorden)
		{
			arbolito="\tPre-Orden: ";
			preorden();
			if(flag_duplicacion==1)
			{
				arbolito=arbolito+duplicacion;
			}
			area_vuelos.setText(arbolito);
			arbolito="";
			num_vuelo=0;
			//flag_duplicacion=0;
		}
		if(presionado==btn_inorden)
		{
			arbolito="\tIn-Orden: ";
			inorden();
			if(flag_duplicacion==1)
			{
				arbolito=arbolito+duplicacion;
			}
			area_vuelos.setText(arbolito);
			arbolito="";
			num_vuelo=0;
			//flag_duplicacion=0;
		}
		if(presionado==btn_postorden)
		{
			arbolito="\tPost-Orden: ";
			postorden();
			if(flag_duplicacion==1)
			{
				arbolito=arbolito+duplicacion;
			}
			area_vuelos.setText(arbolito);
			arbolito="";
			//flag_duplicacion=0;
			num_vuelo=0;
		}
		if(presionado==btn_buscar)
		{
			arbolito="\tReccorrido de Busqueda: ";
			busqueda=txt_buscar.getText();
			area_vuelos.setText("");
			if(!busqueda.equals("Ingrese Ciudad")||!busqueda.equals("Ingrese Capasidad")||!busqueda.equals("Ingrese Fecha")||!busqueda.equals("Ingrese Asientos"))
			{
				buscar(raiz);
				if(flag_buscar==1)
				{
					JOptionPane.showMessageDialog(null, "Coincidencia Encontrada");
					area_vuelos.setText(arbolito);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "No Hay Coincidencias");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Ingrese Una Busqueda");
			}
			num_vuelo=0;
			flag_buscar=0;
			if(box_arboles.getSelectedItem().equals(lista_arbol[1]))
			{
				txt_buscar.setText("Ingrese Ciudad");
			}
			if(box_arboles.getSelectedItem().equals(lista_arbol[2]))
			{
				txt_buscar.setText("Ingrese Capacidad");
			}
			if(box_arboles.getSelectedItem().equals(lista_arbol[3]))
			{
				txt_buscar.setText("Ingrese Fecha");
			}
			if(box_arboles.getSelectedItem().equals(lista_arbol[4]))
			{
				txt_buscar.setText("Ingrese Asientos");
			}
		}
		if(presionado==btn_volver)
		{
			this.setVisible(false);
			Aviacion x=new Aviacion();
		}
	}
}
