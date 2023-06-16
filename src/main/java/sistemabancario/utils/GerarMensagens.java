/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabancario.utils;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author alunos
 */
public class GerarMensagens {
    
    public static void alerta(Component  parente, Object mensagem){
        JOptionPane.showMessageDialog(parente, mensagem);
    }
    
     public static void erro(Component  parente, Object mensagem){
        JOptionPane.showMessageDialog(parente, mensagem, "Erro!", JOptionPane.ERROR_MESSAGE);
    }
    
     public static boolean confirmar( Component parente, Object mensagem){
         var retorno = JOptionPane.showConfirmDialog(parente, mensagem, "Confirme", JOptionPane.YES_NO_OPTION);
         return JOptionPane.YES_OPTION == retorno;
     }
    
}
