/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabancario.view.tables;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alunos
 */
public abstract class AbstractTable<L> extends AbstractTableModel {

    //o L deixa genérico pode ser qualquer letra
    private List<L> registros = new ArrayList<>();
    private final String[] colunas;

    public AbstractTable(String... colunas) {
        this.colunas = colunas;
    }

    //alt insert implement metodos para gerar  getRowCount e getColumnCount
    @Override
    public int getRowCount() {
        return registros.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }
    //get e set para registros

    public List<L> getRegistros() {
        return registros;
    }

    public void setRegistros(List<L> registros) {
        this.registros = registros;
        //atualizar os registros
        fireTableDataChanged();
    }

    //l é a letra q escolhi no topo 
    public void addRegistro(L registro) {
        this.registros.add(registro);
        fireTableDataChanged();
    }

    //alt insert overrride mathodos
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Object.class;
    }

    @Override
    public String getColumnName(int column) {
         return colunas[column];
    }
    
    

}
