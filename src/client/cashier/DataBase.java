package client.cashier;

import client.tools.Product;
import client.tools.Table;

import java.io.*;
import java.util.*;

/* Banco de Dados em memoria principal do servidor
** Armazena os produtos do restaurante "productMap"
** e as mesas em funcionamento tableMap*/
public class DataBase {
    private HashMap<Integer, Table> tableMap;
    private TreeMap<Integer, Product> productMap;

    public DataBase() {
        tableMap = new HashMap<>();
        productMap = new TreeMap<>();
        try { loadDataProduct(); } catch (Exception e) {System.out.println("deu ruim no banco de dados"); }
    }

    /* Carrega os dados dos produtos armazenados na memoria
    ** secundaria em memoria principal */
    private void loadDataProduct() throws IOException, ClassNotFoundException {
        //VERSÃO DE TESTE
        //FAZER UMA VERSÃO QUE LEIA TODOS OS ARQUIVOS NA PASTA E INCORPORAR NO MAPA
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/client/db/products/test2.bin"));
        Product p = (Product) in.readObject();
        productMap.put(p.getID(),p);
        in.close();
    }
    //SINCRONIZAR
    /* Primeiro verifica se a mesa já esta no mapa.
    ** Se sim apenas retorna a mesa, caso contrario
    ** adiciona a ela ao mapa antes de retornar */
    public Table openTable(Integer tableID) {
        Table table = tableMap.get(tableID);
        if (table == null) {
            table = new Table(tableID);
            tableMap.put(tableID, table);
        }
        return table;
    }

    //SINCRONIZAR
    /* tira a mesa do mapa de mesas abertar e retorna a montante
     ** dos produtos lançados a mesa */
    public float closeTable(Integer tableID) {
        Table table = tableMap.get(tableID);
        float total = table.getTotal();
        tableMap.remove(tableID);
        return total;
    }

    /* Buscar mesa no mapa*/
    public Table getTable(Integer tableID) {
        return tableMap.get(tableID);
    }

    //SINCRONIZAR
    /* Insere um produto (pedido) na mesa
    ** presume-se que a mesa já esta aberta antes de lançar o produto */
    //tratar alguns erros como "se a mesa não estiver aberta"
    public void insertProduct(Integer tableID, Integer productID) {
        Table table = tableMap.get(tableID);
        Product product = productMap.get(productID);
        table.insert(product);
    }

    //SINCRONIZAR
    /* Cancela Produto lançado na mesa */
    public void cancelProduct(Integer tableID, Integer productID) {
        Table table = tableMap.get(tableID);
        Product product = productMap.get(productID);
        table.remove(product);
    }

    public LinkedList<Table> getAllTables() {
        LinkedList<Table> list = new LinkedList<Table>();
        Iterator it = tableMap.values().iterator();
        while(it.hasNext()) {
            Table t = (Table) it.next();
            list.add(t);
        }
        return list;
    }
}