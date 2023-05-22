package client.cashier;

import client.tools.Product;
import client.tools.Table;

import java.io.*;
import java.util.HashMap;
import java.util.TreeMap;

/* Banco de Dados em memoria principal do servidor
** Armazena os produtos do restaurante "productMap"
** e as mesas em funcionamento tableMap*/
public class DataBase {
    HashMap<Integer, Table> tableMap;
    TreeMap<Integer, Product> productMap;

    public DataBase() {
        tableMap = new HashMap<>();
        productMap = new TreeMap<>();
        try { loadDataProduct(); } catch (Exception e) {System.out.println("erro"); }
    }

    /* Carrega os dados dos produtos armazenados na memoria
    ** secundaria em memoria principal */
    private void loadDataProduct() throws IOException, ClassNotFoundException {
        //VERSÃO DE TESTE
        //FAZER UMA VERSÃO QUE LEIA TODOS OS ARQUIVOS NA PASTA E INCORPORAR NO MAPA
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("save/product_data/teste.bin"));
        Product p = (Product) in.readObject();
        productMap.put(p.getID(), p);
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

    /* Insere um produto (pedido) na mesa
    ** presume-se que a mesa já esta aberta antes de lançar o produto */
    public void insertProduct(Integer tableID, Integer productID) {
        Table table = tableMap.get(tableID);
        Product product = productMap.get(productID);
        table.insert(product);
    }

    /* Cancela Produto lançado na mesa */
    public void cancelProduct(Integer tableID, Integer productID) {

    }

    //testing
    public static void main(String[] args) throws IOException {
        DataBase d = new DataBase();
        Product p = d.productMap.get(1010);
        System.out.println(p.getName());
    }
}
