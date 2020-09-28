/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.pkg1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Andy_
 */
public class Ventana extends JFrame implements ActionListener{
    String OracionCorrecta="El niño juega con su pelota"; //ORACION CORRECTA 
    BufferedReader br=null;
    FileReader fr=null;
    JFileChooser fc=null;
    
    JPanel p = new JPanel();
    JButton abrir= new JButton("Abrir Archivo");
    JLabel etiqueta =new JLabel("Ingresa la oracion Correcta (nota: dejar en blanco para el default)");
    JLabel etiqueta1=new JLabel("Da click para abrir el archivo TXT");
    JLabel etiqueta2=new JLabel("Oracion del archivo = ");
    JLabel etiqueta3=new JLabel("Oracion Acomodada = ");
    JTextField inputCorrecto=new JTextField();
  
    public Ventana(){
        this.setSize(400, 300);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("Practica 1");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        agregarPanel();
        this.setVisible(true);
    }
    public void agregarPanel(){
        p.setSize(400, 300);
        p.setBackground(Color.decode("#fca652"));
        p.setLayout(null);
                
        inputCorrecto.setBounds(60, 40, 280, 30);
        
        etiqueta. setFont(new Font(Font.SERIF,Font.ITALIC,12));
        etiqueta1.setFont(new Font(Font.SERIF,Font.BOLD,13));
        etiqueta2.setFont(new Font(Font.SERIF,Font.BOLD,14));
        etiqueta3.setFont(new Font(Font.SERIF,Font.ITALIC,14));
        
        etiqueta .setBounds(30, 15, 350, 30);
        etiqueta1.setBounds(100, 70, 200, 30);
        etiqueta2.setBounds(56, 130, 400, 50);
        etiqueta3.setBounds(56, 170, 400, 50);
        
        abrir.setBounds(130, 100, 120, 30);
        abrir.addActionListener(this);
        abrir.setBackground(Color.decode("#f8efd4"));
        
        p.add(abrir);
        p.add(inputCorrecto);
        p.add(etiqueta);
        p.add(etiqueta1);
        p.add(etiqueta2);
        p.add(etiqueta3);
        this.add(p);
        p.setVisible(true);
    }
    public void ordenar(String OracionCorrecto,String OracionIncorrecto){
        String incorrecto[] = OracionIncorrecto.split(" ");
        String correcto[] = OracionCorrecto.split(" ");
        String resultado[]=new String[incorrecto.length];
             
        String respuesta="";
        
        for (int i = 0; i < incorrecto.length; i++) {
            for (int j = 0; j < incorrecto.length; j++) {
                if(incorrecto[i].equals(correcto[j])){
                    resultado[j]=incorrecto[i];
                }
            }           
        }
        for (String resultado1 : resultado) {
            respuesta += resultado1 + " ";
        }
        
        if(respuesta.length()>=30){
            etiqueta3.setBounds(56, 170, 400, 50);
            etiqueta3.setText("<html><body>Oracion Acomodada =<br>"+respuesta+"</html></body>");
        }else{
            etiqueta3.setText("Oracion Acomodada = "+respuesta);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(abrir)){ 
            if(inputCorrecto.getText().equals("")){ //Checamos si dejo en balnco la nueva oracion correcta o no.
                OracionCorrecta="El niño juega con su pelota"; //dejamos por default
            }else{
                OracionCorrecta=inputCorrecto.getText(); //Cambiamos la oracion por la ingresada
            }
            try {
                fc= new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text"); //Filtro para aceptar puros .txt
                fc.setFileFilter(filter); //Establecemos filtro
                int var=fc.showOpenDialog(this); //Abrimos el cuadro y lo guardamos en un entero
                File txt = fc.getSelectedFile(); //Agregamos el archivo seleccionado a una variable File
                if(JFileChooser.APPROVE_OPTION==var){ //Comparamos si agrego un archivo o no
                    
                    fr=new FileReader(txt);
                    br=new BufferedReader(fr);
                    
                    String texto = br.readLine(); //leemos xD
                    
                        if(texto.length()>=30){
                            etiqueta2.setText("<html><body>Oracion del archivo =<br>"+texto+"</html></body>"); //html para saltos de linea saludos
                        }else{
                            etiqueta2.setText("Oracion del archivo = "+texto);
                        }          
                    ordenar(OracionCorrecta,texto);
                }else{
                    JOptionPane.showMessageDialog(null, "Favor de seleccionar un archivo de texto", "Warning.! -- Seleccionar un archivo", HEIGHT);
                }                               
                
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Favor de seleccionar un archivo de texto", "Warning.! -- Seleccionar un archivo", HEIGHT);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Favor de seleccionar un archivo de texto", "Warning.! -- Seleccionar un archivo", HEIGHT);
            }
            
        }
        
    }
}
