import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.*;
import java.io.*;

public class Aviacion extends JFrame implements ActionListener 
{
	Vector<String> lista_vuelo=new Vector<String>();
	int flag_fecha, flag_cap, flag_ciu, flag_dis;
	String ciudad_origen, ciudad_destino, fecha_salida, fecha_arribo;
	String distancia;
	String cap_pasajero, cap_carga;
	int contador_0=0;
	String codigo,asiento_disp;
	
	static String[] lista_ordenar={"","Fecha de salida","Capasidad de Pasajeros","Ciudad de Origen","Distancia de Vuelo"};
	JComboBox<String> box_ordenar= new JComboBox<String>(lista_ordenar);
	//JComboBox<String> box_buscar=new JComboBox<String>(lista_ordenar);
	
	JLabel lbl_ciudad_origen=new JLabel("Ciudad de Origen: ");
	JTextField txt_ciudad_origen=new JTextField();
	JLabel lbl_ciudad_destino=new JLabel("Ciudad Destino: ");
	JTextField txt_ciudad_destino=new JTextField();
	JLabel lbl_fecha_salida=new JLabel("Fecha de Salida: ");
	JTextField txt_fecha_salida=new JTextField();
	JLabel lbl_fecha_arribo=new JLabel("Fecha de Arribo: ");
	JTextField txt_fecha_arribo=new JTextField();
	JLabel lbl_dis_vuel=new JLabel("Distancia del Vuelo: ");
	JTextField txt_dis_vuel=new JTextField();
	JLabel lbl_capacidad_pasajero=new JLabel("Capacidad de Pasajeros: ");
	JTextField txt_capacidad_pasajero=new JTextField();
	JLabel lbl_capacidad_carga=new JLabel("Capacidad de Carga: ");
	JTextField txt_capacidad_carga=new JTextField();
	JLabel lbl_asiento_disp=new JLabel("Asientos Disponibles: ");
	JTextField txt_asiento_disp=new JTextField();
	JLabel lbl_codigo=new JLabel("Codigo de Vuelo: ");
	JTextField txt_codigo=new JTextField();
	JTextField txt_modificar_vuelo=new JTextField();
	JLabel lbl_ordenar=new JLabel("Ordenar Por:");
	//JLabel lbl_buscar=new JLabel("Buscar Por:");
	JTextField txt_buscar=new JTextField();
	
	JTextArea area_vuelos=new JTextArea();
	//DefaultTableModel model = new DefaultTableModel();
	//JTable tabla = new JTable(model);
	//JScrollPane scroll = new JScrollPane(pane_vuelos,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	JScrollPane scroll=new JScrollPane(area_vuelos);
	
	JButton btn_capturar=new JButton("Capturar Vuelos");
	JButton btn_modificar=new JButton("Modificar Vuelos");
	JButton btn_mostrar=new JButton("Mostrar Vuelos");
	JButton btn_salir=new JButton("Salir");
	JButton btn_borrar_todo=new JButton("Borrar Todo");
	JButton btn_limpiar=new JButton("Limpiar");
	JButton btn_ok=new JButton("OK");
	JButton btn_ordenar=new JButton("Ordenar");
	JButton btn_buscar=new JButton("Buscar");
	JButton btn_arbol=new JButton("Crear Arbol");
	JButton btn_grafo=new JButton("Grafo");
	JButton btn_prim=new JButton("Alg. Prim");
	public Aviacion()
	{
		super("TPS_Actividad_VI");
		super.setBounds(180, 0, 630, 540);
		ImageIcon Icono = new ImageIcon("avion.png"); 
		this.setIconImage(Icono.getImage());
		
		lbl_ciudad_origen.setBounds(10,10,120,20);
		txt_ciudad_origen.setBounds(160,10,120,20);
		lbl_ciudad_destino.setBounds(10,30,120,20);
		txt_ciudad_destino.setBounds(160,30,120,20);
		lbl_fecha_salida.setBounds(10,50,120,20);
		txt_fecha_salida.setBounds(160,50,120,20);
		lbl_fecha_arribo.setBounds(10,70,120,20);
		txt_fecha_arribo.setBounds(160,70,120,20);
		lbl_dis_vuel.setBounds(10,90,120,20);
		txt_dis_vuel.setBounds(160,90,120,20);
		lbl_capacidad_pasajero.setBounds(10,110,150,20);
		txt_capacidad_pasajero.setBounds(160,110,120,20);
		lbl_capacidad_carga.setBounds(10,130,120,20);
		txt_capacidad_carga.setBounds(160,130,120,20);
		lbl_asiento_disp.setBounds(10,150,150,20);
		txt_asiento_disp.setBounds(160,150,120,20);
		lbl_codigo.setBounds(10,170,120,20);
		txt_codigo.setBounds(160,170,120,20);
		lbl_ordenar.setBounds(10,350 , 130, 20);
		box_ordenar.setBounds(10, 370, 140, 20);
		//lbl_buscar.setBounds(10, 388, 130, 20);
		txt_buscar.setBounds(10, 405, 140, 20);
		//txt_buscar.setBounds(160, 405, 120, 20);
		area_vuelos.setLineWrap(true);
        area_vuelos.setBackground(Color.LIGHT_GRAY);
		scroll.setBounds(300, 10, 300, 490);
		
		btn_capturar.setBounds(10,210,130,20);
		btn_modificar.setBounds(10,330,130,20);
		btn_mostrar.setBounds(10,250,130,20);
		btn_salir.setBounds(150,250,120,20);
		btn_borrar_todo.setBounds(10,290,130,20);
		btn_limpiar.setBounds(150, 210, 120, 20);
		btn_ok.setBounds(150,290,120,20);
		btn_ordenar.setBounds(160, 370, 120, 20);
		btn_buscar.setBounds(160, 405, 120, 20);
		btn_arbol.setBounds(10,440,130,20);
		btn_grafo.setBounds(150,440,120,20);
		btn_prim.setBounds(10,470,120,20);
		txt_modificar_vuelo.setBounds(150, 330, 120, 20);
		txt_modificar_vuelo.setText("Ingrese Codigo");
		
		this.add(lbl_ciudad_origen);
		this.add(txt_ciudad_origen);
		this.add(lbl_ciudad_destino);
		this.add(txt_ciudad_destino);
		this.add(lbl_fecha_salida);
		this.add(txt_fecha_salida);
		this.add(lbl_fecha_arribo);
		this.add(txt_fecha_arribo);
		this.add(lbl_dis_vuel);
		this.add(txt_dis_vuel);
		this.add(lbl_capacidad_pasajero);
		this.add(txt_capacidad_pasajero);
		this.add(lbl_capacidad_carga);
		this.add(txt_capacidad_carga);
		this.add(lbl_asiento_disp);
		this.add(txt_asiento_disp);
		this.add(lbl_codigo);
		this.add(txt_codigo);
		this.add(txt_modificar_vuelo);
		this.add(lbl_ordenar);
		this.add(box_ordenar);
		//this.add(lbl_buscar);
		//this.add(box_buscar);
		this.add(txt_buscar);
		//this.add(pane_vuelos);
		this.add(btn_capturar);
		this.add(btn_modificar);
		this.add(btn_mostrar);
		this.add(btn_salir);
		this.add(btn_borrar_todo);
		this.add(btn_limpiar);
		this.add(btn_ok);
		this.add(btn_arbol);
		this.add( scroll, BorderLayout.CENTER );
		this.add(btn_ordenar);
		this.add(btn_buscar);
		this.add(btn_grafo);
		this.add(btn_prim);
		
		this.setLayout(null);
		this.setVisible(true);
		btn_capturar.addActionListener(this);
    	btn_modificar.addActionListener(this);
    	btn_mostrar.addActionListener(this);
    	btn_salir.addActionListener(this);
    	btn_borrar_todo.addActionListener(this);
    	btn_limpiar.addActionListener(this);
    	btn_ok.addActionListener(this);
    	btn_ordenar.addActionListener(this);
    	btn_buscar.addActionListener(this);
    	btn_arbol.addActionListener(this);
    	btn_grafo.addActionListener(this);
    	btn_prim.addActionListener(this);
    	
    	txt_buscar.setText("Ingrese Busqueda");
    	txt_buscar.setEnabled(false);
    	//box_buscar.setEnabled(false);
    	//lbl_buscar.setEnabled(false);
    	btn_buscar.setEnabled(false);
	}
	public boolean campo_vacio()
	{
		if(txt_ciudad_origen.getText().equals("")|txt_ciudad_destino.getText().equals("")|txt_fecha_salida.getText().equals("")|
				txt_fecha_arribo.getText().equals("")|txt_asiento_disp.getText().equals("")|txt_capacidad_carga.getText().equals("")|
				txt_capacidad_pasajero.getText().equals("")|txt_dis_vuel.getText().equals("")|txt_codigo.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Falta llenar un campo");
			return false;
		}
		else
			return true;
	}
	public boolean codigo_duplicado()
	{
		int bandera=0;
		long a=0;
		String cod_bus="";
		cod_bus=txt_codigo.getText();
		try
		{
			RandomAccessFile file_vuelo=new RandomAccessFile("aviacion.txt","rw");
			if(file_vuelo.length()!= a)
			{
			while(file_vuelo.getFilePointer()!= file_vuelo.length())
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
				if(codigo.equals(cod_bus))
				{
					bandera=1;
					break;
				}
				else
				{
					bandera=0;
				}
			}
			}
			file_vuelo.close();
		}
		catch(IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "Codigo Duplicado");
		}
		if(bandera==1)
		{
			JOptionPane.showMessageDialog(null,"Codigo Duplicado");
			return false;
		}
		else
		{
			return true;
		}
	}
	public void capturar_vuelo()
	{
		if(campo_vacio()==true && codigo_duplicado()==true)
		{
		ciudad_origen=txt_ciudad_origen.getText();
		ciudad_destino=txt_ciudad_destino.getText();
		fecha_salida=txt_fecha_salida.getText();
		fecha_arribo=txt_fecha_arribo.getText();
		distancia=txt_dis_vuel.getText();
		cap_pasajero=txt_capacidad_pasajero.getText();
		cap_carga=txt_capacidad_carga.getText();
		asiento_disp=txt_asiento_disp.getText();
		codigo=txt_codigo.getText();
		try
		{
			RandomAccessFile  file_vuelo=new RandomAccessFile("aviacion.txt","rw");
			
			file_vuelo.seek(file_vuelo.length());
			file_vuelo.writeUTF(ciudad_origen);
			file_vuelo.writeUTF(ciudad_destino);
			file_vuelo.writeUTF(fecha_salida);
			file_vuelo.writeUTF(fecha_arribo);
			file_vuelo.writeUTF(distancia);
			file_vuelo.writeUTF(cap_pasajero);
			file_vuelo.writeUTF(cap_carga);
			file_vuelo.writeUTF(asiento_disp);
			file_vuelo.writeUTF(codigo);
			contador_0++;
			file_vuelo.close();
			JOptionPane.showMessageDialog(null, "Vuelo Guardado");
		}
		catch(IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "Error: Captura de vuelo Fallida");
		}
		}
	}
	public void mostrar_vuelo()
	{
		int bandera=0;
		String impresion="", numero_vuelo="\n\tVuelo: ";
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
				bandera++;
				impresion=impresion+numero_vuelo+bandera+"\nCiudad de Origen: "+ciudad_origen+"\nCiudad Destino: "+ciudad_destino+"\nFecha de Salida: "+fecha_salida
						+"\nFecha de Arribo:"+fecha_arribo+"\nDistancia del Vuelo: "+distancia+"Km"+"\nCapacidad de Pasajeros: "+cap_pasajero
						+"\nCapacidad de Carga: "+cap_carga+"Kg"+"\nAsientos Disponibles: "+asiento_disp+"\nCodigo de Vuelo: "+codigo;
				
			}
			file_vuelo.close();
			area_vuelos.setText(impresion);
		}
		catch(IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "Error: No Hay Archivo Existente");
		}
	}
	public void borrar_todo()
	{
		File file_vuelo=new File("aviacion.txt");
		file_vuelo.delete();
		contador_0=0;
	}
	public void limpiar_vuelo()
	{
		txt_ciudad_origen.setText("");
		txt_ciudad_destino.setText("");
		txt_fecha_salida.setText("");
		txt_fecha_arribo.setText("");
		txt_dis_vuel.setText("");
		txt_capacidad_pasajero.setText("");
		txt_capacidad_carga.setText("");
		txt_asiento_disp.setText("");
		txt_codigo.setText("");
		//txt_modificar_vuelo.setText("Ingrese Codigo");
	}
	public void limpiar_area()
	{
		area_vuelos.setText("");
	}
	public void busqueda_vuelo()
	{
		limpiar_vuelo();
		String codigo_mod, cadena="\tCoincidencia Encontrada:";
		int i=1;
		codigo_mod=txt_modificar_vuelo.getText();
		if(!codigo_mod.equals("Ingrese Codigo"))
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
				if(codigo.equals(codigo_mod))
				{
					cadena=cadena+"\nCiudad de Origen: "+ciudad_origen+"\nCiudad Destino: "+ciudad_destino+"\nFecha de Salida: "+fecha_salida
							+"\nFecha de Arribo:"+fecha_arribo+"\nDistancia del Vuelo: "+distancia+"\nCapacidad de Pasajeros: "+cap_pasajero
							+"\nCapacidad de Carga: "+cap_carga+"\nAsientos Disponibles: "+asiento_disp+"\nCodigo de Vuelo: "+codigo;
					txt_ciudad_origen.setText(ciudad_origen);
					txt_ciudad_destino.setText(ciudad_destino);
					txt_fecha_salida.setText(fecha_salida);
					txt_fecha_arribo.setText(fecha_arribo);
					txt_dis_vuel.setText(distancia);
					txt_capacidad_pasajero.setText(cap_pasajero);
					txt_capacidad_carga.setText(cap_carga);
					txt_asiento_disp.setText(asiento_disp);
					txt_codigo.setText(codigo);
					area_vuelos.setText(cadena);
					i=JOptionPane.showConfirmDialog(null, "Deseas Modificar");
				}
			}
			file_vuelo.close();
		}
		catch(IOException ioe)
		{
			txt_modificar_vuelo.setText("Ingresa Codigo");
			JOptionPane.showMessageDialog(null, "Error al modificar");
		}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "No Has Ingresado Codigo");
		}
		if(i==(JOptionPane.YES_OPTION))
		{
			limpiar_area();
			//limpiar_vuelo();
			cadena="Llene los Campos con los Nuevos Datos\nPulse OK para guardarlos";
			area_vuelos.setText(cadena);
		}
		else
		{
			limpiar_vuelo();
			limpiar_area();
		}
	}
	public boolean codigo_duplicado_mod()
	{
		int bandera=0;
		long a=0;
		String cod_bus="";
		cod_bus=txt_codigo.getText();
		try
		{
			RandomAccessFile file_vuelo=new RandomAccessFile("aviacion.txt","rw");
			if(file_vuelo.length()!= a)
			{
			while(file_vuelo.getFilePointer()!= file_vuelo.length())
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
				if(codigo.equals(cod_bus))
				{
					bandera++;
					break;
				}
				else
				{
					bandera=0;
				}
			}
			}
			file_vuelo.close();
		}
		catch(IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "Codigo Duplicado");
		}
		if(bandera>1)
		{
			JOptionPane.showMessageDialog(null,"Codigo Duplicado");
			return false;
		}
		else
		{
			return true;
		}
	}
	public void modificar_vuelo()
	{
		String cod_mod;
		cod_mod=txt_modificar_vuelo.getText();
		if(codigo_duplicado_mod()==true && campo_vacio()==true)
		{
			try
			{
				RandomAccessFile file_vuelo=new RandomAccessFile("aviacion.txt", "r");
				RandomAccessFile file_temporal=new RandomAccessFile("temporal.txt","rw");
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
					if(codigo.equals(cod_mod))
					{
						ciudad_origen=txt_ciudad_origen.getText();
						ciudad_destino=txt_ciudad_destino.getText();
						fecha_salida=txt_fecha_salida.getText();
						fecha_arribo=txt_fecha_arribo.getText();
						distancia=txt_dis_vuel.getText();
						cap_pasajero=txt_capacidad_pasajero.getText();
						cap_carga=txt_capacidad_carga.getText();
						asiento_disp=txt_asiento_disp.getText();
						codigo=txt_codigo.getText();
					}
					file_temporal.seek(file_temporal.length());
					file_temporal.writeUTF(ciudad_origen);
					file_temporal.writeUTF(ciudad_destino);
					file_temporal.writeUTF(fecha_salida);
					file_temporal.writeUTF(fecha_arribo);
					file_temporal.writeUTF(distancia);
					file_temporal.writeUTF(cap_pasajero);
					file_temporal.writeUTF(cap_carga);
					file_temporal.writeUTF(asiento_disp);
					file_temporal.writeUTF(codigo);
				}
				file_vuelo.close();
				file_temporal.close();
			}
			catch(IOException ioe)
			{
				JOptionPane.showMessageDialog(null, "Error: ");
			}
		File file_vuelo=new File("aviacion.txt");
		File file_temporal=new File("temporal.txt");
		file_vuelo.delete();
		file_temporal.renameTo(file_vuelo);
		JOptionPane.showMessageDialog(null, "Vuelo Modificado");
		}
	}
	public boolean seleccion_vacio()
	{
		if(box_ordenar.getSelectedItem().equals(""))
		{
			return false;
		}
		else
			return true;
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
					if(box_ordenar.getSelectedItem().equals(lista_ordenar[1]))
					{
						lista_vuelo.add(fecha_salida);
					}
					if(box_ordenar.getSelectedItem().equals(lista_ordenar[2]))
					{
						lista_vuelo.add(cap_pasajero);
					}
					if(box_ordenar.getSelectedItem().equals(lista_ordenar[3]))
					{
						lista_vuelo.add(ciudad_origen);
					}
					if(box_ordenar.getSelectedItem().equals(lista_ordenar[4]))
					{
						lista_vuelo.add(distancia);
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
			JOptionPane.showMessageDialog(null, "Error al Ordenar");
		}
	}
	public void QuickSort(int p, int u)
	{
		{
			String aux;
			
		int i=p,j=u, pivote;
		pivote=(i+j)/2;
		do
		{
		 while (lista_vuelo.get(i).compareTo(lista_vuelo.get(pivote))<0) i++;
		 while (lista_vuelo.get(j).compareTo(lista_vuelo.get(pivote))>0) j--;
		 if (i<=j){
		    aux=lista_vuelo.get(i);
		    lista_vuelo.set(i, lista_vuelo.get(j));
		    lista_vuelo.set(j, aux);
		    
		    i++;
		    j--;
		}
		}while (i<=j);
		    if (p<j)
		      QuickSort(p,j);
		    if (i<u)
		       QuickSort(i,u);
		}
	}
	public void ordena_archivo(Vector<String> lista_vuelo)
	{
		for(int i=0; i<lista_vuelo.size(); i++)
		{
				try
				{
					RandomAccessFile file_vuelo=new RandomAccessFile("aviacion.txt", "r");
					RandomAccessFile file_temporal=new RandomAccessFile("temporal.txt","rw");
					file_vuelo.seek(0);
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
						if(box_ordenar.getSelectedItem().equals(lista_ordenar[1]))
						{
							if(fecha_salida.equals(lista_vuelo.get(i)))
							{
								file_temporal.seek(file_temporal.length());
								file_temporal.writeUTF(ciudad_origen);
								file_temporal.writeUTF(ciudad_destino);
								file_temporal.writeUTF(fecha_salida);
								file_temporal.writeUTF(fecha_arribo);
								file_temporal.writeUTF(distancia);
								file_temporal.writeUTF(cap_pasajero);
								file_temporal.writeUTF(cap_carga);
								file_temporal.writeUTF(asiento_disp);
								file_temporal.writeUTF(codigo);
							}
						}
						if(box_ordenar.getSelectedItem().equals(lista_ordenar[2]))
						{
							if(cap_pasajero.equals(lista_vuelo.get(i)))
							{
								file_temporal.seek(file_temporal.length());
								file_temporal.writeUTF(ciudad_origen);
								file_temporal.writeUTF(ciudad_destino);
								file_temporal.writeUTF(fecha_salida);
								file_temporal.writeUTF(fecha_arribo);
								file_temporal.writeUTF(distancia);
								file_temporal.writeUTF(cap_pasajero);
								file_temporal.writeUTF(cap_carga);
								file_temporal.writeUTF(asiento_disp);
								file_temporal.writeUTF(codigo);
							}
						}
						if(box_ordenar.getSelectedItem().equals(lista_ordenar[3]))
						{
							if(ciudad_origen.equals(lista_vuelo.get(i)))
							{
								file_temporal.seek(file_temporal.length());
								file_temporal.writeUTF(ciudad_origen);
								file_temporal.writeUTF(ciudad_destino);
								file_temporal.writeUTF(fecha_salida);
								file_temporal.writeUTF(fecha_arribo);
								file_temporal.writeUTF(distancia);
								file_temporal.writeUTF(cap_pasajero);
								file_temporal.writeUTF(cap_carga);
								file_temporal.writeUTF(asiento_disp);
								file_temporal.writeUTF(codigo);
							}
						}
						if(box_ordenar.getSelectedItem().equals(lista_ordenar[4]))
						{
							if(distancia.equals(lista_vuelo.get(i)))
							{
								file_temporal.seek(file_temporal.length());
								file_temporal.writeUTF(ciudad_origen);
								file_temporal.writeUTF(ciudad_destino);
								file_temporal.writeUTF(fecha_salida);
								file_temporal.writeUTF(fecha_arribo);
								file_temporal.writeUTF(distancia);
								file_temporal.writeUTF(cap_pasajero);
								file_temporal.writeUTF(cap_carga);
								file_temporal.writeUTF(asiento_disp);
								file_temporal.writeUTF(codigo);
							}
						}
					}
					file_vuelo.close();
					file_temporal.close();
				}
				catch(IOException ioe)
				{
					JOptionPane.showMessageDialog(null, "Error al Ordenar");
				}
		}
		File file_vuelo=new File("aviacion.txt");
		File file_temporal=new File("temporal.txt");
		file_vuelo.delete();
		file_temporal.renameTo(file_vuelo);
	}
	public void ordenar_vuelos()
	{
		if(seleccion_vacio()==true)
		{
			//System.out.println("\nEste es el Box: "+box_ordenar.getSelectedItem());
			if(box_ordenar.getSelectedItem().equals(lista_ordenar[1]))
			{
				llenar_lista(lista_vuelo);
				int w=lista_vuelo.size();
				QuickSort(0 , w-1);
				ordena_archivo(lista_vuelo);
				lista_vuelo.clear();
				flag_fecha=1; flag_cap=0; flag_ciu=0; flag_dis=0;
				txt_buscar.setText("Ingrese Fecha");
			}
			if(box_ordenar.getSelectedItem().equals(lista_ordenar[2]))
			{
				llenar_lista(lista_vuelo);
				int w=lista_vuelo.size();
				QuickSort(0 , w-1);
				ordena_archivo(lista_vuelo);
				lista_vuelo.clear();
				flag_fecha=0; flag_cap=1; flag_ciu=0; flag_dis=0;
				txt_buscar.setText("Ingrese Capacidad");
			}
			if(box_ordenar.getSelectedItem().equals(lista_ordenar[3]))
			{
				llenar_lista(lista_vuelo);
				int w=lista_vuelo.size();
				QuickSort(0 , w-1);
				ordena_archivo(lista_vuelo);
				lista_vuelo.clear();
				flag_fecha= 0; flag_cap=0; flag_ciu=1; flag_dis=0;
				txt_buscar.setText("Ingrese Ciudad");
			}
			if(box_ordenar.getSelectedItem().equals(lista_ordenar[4]))
			{
				llenar_lista(lista_vuelo);
				int w=lista_vuelo.size();
				QuickSort(0 , w-1);
				ordena_archivo(lista_vuelo);
				lista_vuelo.clear();
				flag_fecha=0; flag_cap=0; flag_ciu=0; flag_dis=1;
				txt_buscar.setText("Ingrese Distancia");
			}
			txt_buscar.setEnabled(true);
			btn_buscar.setEnabled(true);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Selecciona un Parametro");
		}
	}
	public void llenar_listadebusqueda()
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
					if(flag_fecha==1)
					{
						lista_vuelo.add(fecha_salida);
					}
					if(flag_cap==1)
					{
						lista_vuelo.add(cap_pasajero);
					}
					if(flag_ciu==1)
					{
						lista_vuelo.add(ciudad_origen);
					}
					if(flag_dis==1)
					{
						lista_vuelo.add(distancia);
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
			JOptionPane.showMessageDialog(null, "Error al Buscar");
		}
	}
	public int busqueda_binaria(String cadena_abuscar)// Como la lista es global no tengo que enviarla como parametro
	{
		int n=lista_vuelo.size();
		int centro, inf=0, sup=n-1;
		while(inf<=sup)
		{
			centro=(sup+inf)/2;
			if(lista_vuelo.get(centro).equals(cadena_abuscar))
			{
				return centro;
			}
			else if(cadena_abuscar.compareTo(lista_vuelo.get(centro))<0)
			{
				sup=centro-1;
			}
			else
			{
				inf=centro+1;
			}
		}
		return -1;
	}
	public void mostrar_resultado_busqueda(int resultado)
	{
		String cadena="\tVuelo: "+(resultado+1);
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
				if(lista_vuelo.get(resultado).equals(fecha_salida)&& flag_fecha==1)
				{
					cadena=cadena+"\nCiudad de Origen: "+ciudad_origen+"\nCiudad Destino: "+ciudad_destino+"\nFecha de Salida: "+fecha_salida
							+"\nFecha de Arribo:"+fecha_arribo+"\nDistancia del Vuelo: "+distancia+"\nCapacidad de Pasajeros: "+cap_pasajero
							+"\nCapacidad de Carga: "+cap_carga+"\nAsientos Disponibles: "+asiento_disp+"\nCodigo de Vuelo: "+codigo;
					area_vuelos.setText(cadena);
				}
				if(lista_vuelo.get(resultado).equals(cap_pasajero)&& flag_cap==1)
				{
					cadena=cadena+"\nCiudad de Origen: "+ciudad_origen+"\nCiudad Destino: "+ciudad_destino+"\nFecha de Salida: "+fecha_salida
							+"\nFecha de Arribo:"+fecha_arribo+"\nDistancia del Vuelo: "+distancia+"\nCapacidad de Pasajeros: "+cap_pasajero
							+"\nCapacidad de Carga: "+cap_carga+"\nAsientos Disponibles: "+asiento_disp+"\nCodigo de Vuelo: "+codigo;
					area_vuelos.setText(cadena);
				}
				if(lista_vuelo.get(resultado).equals(ciudad_origen)&& flag_ciu==1)
				{
					cadena=cadena+"\nCiudad de Origen: "+ciudad_origen+"\nCiudad Destino: "+ciudad_destino+"\nFecha de Salida: "+fecha_salida
							+"\nFecha de Arribo:"+fecha_arribo+"\nDistancia del Vuelo: "+distancia+"\nCapacidad de Pasajeros: "+cap_pasajero
							+"\nCapacidad de Carga: "+cap_carga+"\nAsientos Disponibles: "+asiento_disp+"\nCodigo de Vuelo: "+codigo;
					area_vuelos.setText(cadena);
				}
				if(lista_vuelo.get(resultado).equals(distancia)&& flag_dis==1)
				{
					cadena=cadena+"\nCiudad de Origen: "+ciudad_origen+"\nCiudad Destino: "+ciudad_destino+"\nFecha de Salida: "+fecha_salida
							+"\nFecha de Arribo:"+fecha_arribo+"\nDistancia del Vuelo: "+distancia+"\nCapacidad de Pasajeros: "+cap_pasajero
							+"\nCapacidad de Carga: "+cap_carga+"\nAsientos Disponibles: "+asiento_disp+"\nCodigo de Vuelo: "+codigo;
					area_vuelos.setText(cadena);
				}
			}
			file_vuelo.close();
		}
		catch(IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "Error al mostrar el resultado de la busqueda");
		}
	}
	public void buscar_vuelos()
	{
		int resultado=0, j=0, i=0;
		if(txt_buscar.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, "Ingrese Busqueda");
		}
		else
		{
			String vuelo_a_buscar;
			vuelo_a_buscar=txt_buscar.getText();
				llenar_listadebusqueda();
				resultado=busqueda_binaria(vuelo_a_buscar);
				j=resultado+1;
				if(resultado==-1)
				{
					JOptionPane.showMessageDialog(null, "No se Encontraron Coincidencias");
				}
				else
				{
					i=JOptionPane.showConfirmDialog(null, "Coincidencia Encontrada en el Vuelo: "+j+"\n Ver Vuelo:");
					if(i==JOptionPane.YES_OPTION)
					{
						mostrar_resultado_busqueda(resultado);
					}
				}
				lista_vuelo.clear();
			}
	}
	public static void main(String[] args) 
	{
		Aviacion x=new Aviacion();
	}
	public void actionPerformed(ActionEvent evt)
	{
		Object presionado=evt.getSource();
		if(presionado==btn_capturar)
		{
			capturar_vuelo();
			limpiar_vuelo();
			txt_buscar.setEnabled(false);
			btn_buscar.setEnabled(false);
		}
		if(presionado==btn_modificar)
		{
			busqueda_vuelo();
		}
		if(presionado==btn_ok)
		{
			modificar_vuelo();
			txt_modificar_vuelo.setText("Ingrese Codigo:");
			limpiar_vuelo();
			limpiar_area();
			txt_buscar.setEnabled(false);
			btn_buscar.setEnabled(false);
		}
		if(presionado==btn_mostrar)
		{
			limpiar_area();
			mostrar_vuelo();
		}
		if(presionado==btn_borrar_todo)
		{
			borrar_todo();
			limpiar_area();
			txt_buscar.setEnabled(false);
			btn_buscar.setEnabled(false);
		}
		if(presionado==btn_limpiar)
		{
			limpiar_vuelo();
			limpiar_area();
		}
		if(presionado==btn_ordenar)
		{
			ordenar_vuelos();
			limpiar_area();
		}
		if(presionado==btn_buscar)
		{
			buscar_vuelos();
			if(flag_fecha==1)
			{
				txt_buscar.setText("Ingrese Fecha");
			}
			else if(flag_cap==1)
			{
				txt_buscar.setText("Ingrese Capacidad");
			}
			else if(flag_ciu==1)
			{
				txt_buscar.setText("Ingrese Ciudad");
			}
			else if(flag_dis==1)
			{
				txt_buscar.setText("Ingrese Distancia");
			}
		}
		if(presionado==btn_arbol)
		{
			this.setVisible(false);
			Arbol_Binario y=new Arbol_Binario();
		}
		if(presionado==btn_grafo)
		{
			this.setVisible(false);
			Grafo z=new Grafo();
		}
		if(presionado==btn_prim)
		{
			this.setVisible(false);
			Interfaz_Prim gmp=new Interfaz_Prim();
		}
		if(presionado==btn_salir)
		{
			System.exit(0);
		}
	}
}