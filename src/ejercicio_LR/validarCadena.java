package ejercicio_LR;

import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaswingdev.message.MessageDialog;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class validarCadena extends javax.swing.JFrame 
{
    MessageDialog OptionPane = new MessageDialog(this);
    Fondo fondo = new Fondo();
    boolean errSint = false; 
    boolean errSem = false;
    Stack<String> pilaSint = new Stack<>();
    Stack<String> pilaSem = new Stack<>();
    List<String[]> tablaSim = new ArrayList<>();
    String not[] = {"$", "P", "Tipo", "V", "A", "S", "E", "T", "F"};
    String columnas[] = {"id", "num", "int", "float", "char", ",", ";", "+", "-", "*", "/", "(", ")", "=", "$", "P", "Tipo", "V", "A", "S", "E", "T", "F"};
    String[][] anSint = {
        {"7", "", "4", "5", "6", "", "", "", "", "", "", "", "", "", "", "1", "2", "", "3", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "P' -> P", "", "", "", "", "", "", "", ""},
        {"8", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "P -> A", "", "", "", "", "", "", "", ""},
        {"Tipo -> int", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"Tipo -> float", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"Tipo -> char", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "9", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "11", "12", "", "", "", "", "", "", "", "", "", "", "10", "", "", "", "", ""},
        {"20", "21", "", "", "", "", "", "14", "15", "", "", "19", "", "", "", "", "", "", "", "13", "16", "17", "18"},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "P -> Tipo id V", "", "", "", "", "", "", "", ""},
        {"22", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"7", "", "4", "5", "6", "", "", "", "", "", "", "", "", "", "", "23", "2", "", "3", "", "", "", ""},
        {"", "", "", "", "", "", "24", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"20", "21", "", "", "", "", "", "", "", "", "", "19", "", "", "", "", "", "", "", "", "25", "17", "18"},
        {"20", "21", "", "", "", "", "", "", "", "", "", "19", "", "", "", "", "", "", "", "", "26", "17", "18"}, //hasta aqui todo  bien
        {"", "", "", "", "", "", "S -> E", "27", "28", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "E -> T", "E -> T", "E -> T", "29", "30", "", "E -> T", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "T -> F", "T -> F", "T -> F", "T -> F", "T -> F", "", "T -> F", "", "", "", "", "", "", "", "", "", ""},
        {"20", "21", "", "", "", "", "", "", "", "", "", "19", "", "", "", "", "", "", "", "", "31", "17", "18"},
        {"", "", "", "", "", "", "F -> id", "F -> id", "F -> id", "F -> id", "F -> id", "", "F -> id", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "F -> num", "F -> num", "F -> num", "F -> num", "F -> num", "", "F -> num", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "11", "12", "", "", "", "", "", "", "", "", "", "", "32", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "V -> ; P", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "A -> id = S ;", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "S -> + E", "27", "28", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "S -> - E", "27", "28", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"20", "21", "", "", "", "", "", "", "", "", "", "19", "", "", "", "", "", "", "", "", "", "33", "18"},
        {"20", "21", "", "", "", "", "", "", "", "", "", "19", "", "", "", "", "", "", "", "", "", "34", "18"},
        {"20", "21", "", "", "", "", "", "", "", "", "", "19", "", "", "", "", "", "", "", "", "", "", "35"},
        {"20", "21", "", "", "", "", "", "", "", "", "", "19", "", "", "", "", "", "", "", "", "", "", "36"},
        {"", "", "", "", "", "", "", "27", "28", "", "", "", "37", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "V -> , id V", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "E -> E + T", "E -> E + T", "E -> E + T", "29", "30", "", "E -> E + T", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "E -> E - T", "E -> E - T", "E -> E - T", "29", "30", "", "E -> E - T", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "T -> T * F", "T -> T * F", "T -> T * F", "T -> T * F", "T -> T * F", "", "T -> T * F", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "T -> T / F", "T -> T / F", "T -> T / F", "T -> T / F", "T -> T / F", "", "T -> T / F", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "F -> ( E )", "F -> ( E )", "F -> ( E )", "F -> ( E )", "F -> ( E )", "", "F -> ( E )", "", "", "", "", "", "", "", "", "", ""}        
    };    
    boolean[][] reglaAsig = {
        {true, false, false},
        {true, true, false},
        {false, false, true}
    };
    int[][] reglaExp = {
        {0, 1, -1},
        {1, 1, -1},
        {-1, -1, -1}
    };

    public validarCadena() 
    {
        setContentPane(fondo);
        initComponents();
        setResizable(false);
        setSize(750, 480);
        setLocationRelativeTo(null);
    }
    
    private void mostrarMensaje(String titulo, String msj, String ruta)
    {
        OptionPane.showMessage(titulo, msj, ruta);
    }
    
    private void Lexico()
    {
        boolean ban = true;
        File archivo = new File("Compilacion.yum");
        PrintWriter escribir;
        try {
            escribir = new PrintWriter(archivo);
            escribir.print(txtCadena.getText());
            escribir.close();
        }
        catch(FileNotFoundException e) {
            Logger.getLogger(validarCadena.class.getName()).log(Level.SEVERE, null, e);            
        }
        try {
            Reader lector = new BufferedReader(new FileReader("Compilacion.yum"));
            Lexer lexer = new Lexer(lector);
            Tokens token = lexer.yylex();

            while(ban) {                
                if(token == null) {
                    for (String[] fila : tablaSim) {
                            for (String dato : fila) {
                                System.out.print(dato + "\t");
                            }
                            System.out.println();
                        }
                    Sintactico("$");
                    ban = false;                         
                    if(errSint)
                        mostrarMensaje("CADENA INVÁLIDA", "La cadena no es aceptada dentro de esta gramática, se esperaba un " + Esperado(), "/img/Close.png");                        
                    return;
                }
                switch(token) {
                    case Error:
                        mostrarMensaje("ERROR", "El lexema " + lexer.lexeme + " es irreconocible", "/img/Close.png");
                        return;
                    case id, idI, idF, idC, num:                        
                        if(token == Tokens.idI || token == Tokens.idF || token == Tokens.idC) {
                            Tabla(String.valueOf(token), String.valueOf(lexer.lexeme));
                            token = Tokens.id;                        
                        }
                        Sintactico(String.valueOf(token));                    
                        break;
                    default:
                        Sintactico(String.valueOf(lexer.lexeme));                        
                        break;
                }
                if(errSint) {
                    System.out.println("Estado en el que se quedó: " + pilaSint.peek());
                    mostrarMensaje("CADENA INVÁLIDA", "La cadena no es aceptada dentro de esta gramática, se esperaba un " + Esperado(), "/img/Close.png");                    
                    return;
                } else
                    if(errSem) {
                        mostrarMensaje("ERROR", "La variable " + lexer.lexeme + " ya se encuentra definida", "/img/Close.png");                                            
                        return;
                    }
                    token = lexer.yylex();
            }  
        }
        catch(FileNotFoundException ex) {
            Logger.getLogger(validarCadena.class.getName()).log(Level.SEVERE, null, ex);            
        } catch(IOException ex) {
            Logger.getLogger(validarCadena.class.getName()).log(Level.SEVERE, null, ex);            
        }
    }
    
    private void Sintactico(String token)
    {
        boolean ban = true;
        int col = buscarColumna(token);
        int renglon = Integer.parseInt(pilaSint.peek());
        String dato = anSint[renglon][col];
        
        if(!dato.isEmpty()) {
            while(!dato.matches("^[1-9][0-9]*$")) {
                if(dato.isEmpty()) { 
                    errSint = true;
                    ban = false;
                    break;
                }
                
                String[] prod = dato.split("->");
                String[] simb = prod[1].trim().split(" ");
                
                for(int i = 0; i < simb.length * 2; i++)
                    pilaSint.pop();

                String nuevo = anSint[Integer.parseInt(pilaSint.peek())][buscarColumna(prod[0].trim())];
                pilaSint.push(prod[0]);
                pilaSint.push(nuevo);
                
                renglon = Integer.parseInt(pilaSint.peek());
                col = buscarColumna(token);
                dato = anSint[renglon][col];               

                if(dato.equals("P' -> P")) {
                    mostrarMensaje("CADENA ACEPTADA", "La cadena es aceptada dentro de esta gramática", "/img/Info.png");
                    ban =  false;
                    break;
                }
            }    
            if(ban) {
                pilaSint.push(token);
                pilaSint.push(dato);
            }
        } else
            errSint = true;        
    }
    
    private int buscarColumna(String token)
    {
        int col = -1;
        for(int i = 0; i < columnas.length; i++)
            if(token.equals(columnas[i])) {
                col = i;
                break;
            }
        return col;
    }
    
    private String Esperado()
    {
        int ren = Integer.parseInt(pilaSint.peek());
        String msj = "";        
        for(int i = 0; i < anSint[ren].length; i++)
            if(!anSint[ren][i].isEmpty() && !Arrays.asList(not).contains(columnas[i]))
                msj += columnas[i] + ", ";  
        return msj.substring(0, msj.length() - 2);
    }
    
    private void Tabla(String token, String lexema)
    {
        if(!tablaSim.isEmpty()) {
            if(!Buscar(lexema))
                Tipo(token, lexema);
        } else
            Tipo(token, lexema);
    }
    
    private boolean Buscar(String lexema)
    {
        for(String[] vars : tablaSim)
            if(vars[0].equals(lexema)) {
                errSem = true;
                return true;
            }
        return false;
    }
    
    private void Tipo(String token, String lexema)
    {
        switch(token) {
            case "idI":
                tablaSim.add(new String[]{lexema, "int"});
                break;
            case "idF":
                tablaSim.add(new String[]{lexema, "float"});
                break;
            case "idC":
                tablaSim.add(new String[]{lexema, "char"});
                break;
        }
    }
            
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCadena = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnValidar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtCadena.setBackground(new java.awt.Color(245, 236, 219, 170));
        txtCadena.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        txtCadena.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        getContentPane().add(txtCadena, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 630, 30));

        jLabel6.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(70, 52, 19));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("EJERCICIO LR");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 158, 750, -1));

        jLabel7.setFont(new java.awt.Font("Consolas", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 38, 14));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Validador de Cadenas");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 750, -1));

        jLabel8.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(70, 52, 19));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("INGRESE LA CADENA A VALIDAR POR LA GRAMÁTICA DEL SEGUNDO");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 740, 30));

        btnValidar.setBackground(new java.awt.Color(70,52,19));
        btnValidar.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        btnValidar.setForeground(new java.awt.Color(255, 255, 255));
        btnValidar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnValidar.setText("Validar Cadena");
        btnValidar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnValidar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnValidar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnValidar.setOpaque(true);
        btnValidar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnValidarMouseClicked(evt);
            }
        });
        getContentPane().add(btnValidar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 340, 270, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnValidarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnValidarMouseClicked
        pilaSint.clear(); 
        pilaSint.push("$");
        pilaSint.push("0");
        pilaSem.clear();
        errSint = false;
        errSem = false;
        tablaSim = new ArrayList<>();
        
        Lexico();
    }//GEN-LAST:event_btnValidarMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(validarCadena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(validarCadena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(validarCadena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(validarCadena.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new validarCadena().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnValidar;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtCadena;
    // End of variables declaration//GEN-END:variables
}

class Fondo extends JPanel 
{
    private Image imagen;
    @Override
    public void paint(Graphics g)
    {
        imagen = new ImageIcon(getClass().getResource("/img/Fondos.jpeg")).getImage();
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        setOpaque(false);
        super.paint(g);
    }
}
