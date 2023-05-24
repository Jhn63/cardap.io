package client.cashier;

import client.Protocol;
import client.tools.Table;

/** Caixa/Terminal de Gerenciamento
 * Contem todas as funcionalidades do garçom e algumas
 * permissões adicionais no sistema.
 * Tem acesso direto ao servidor (ao contrario do garçom que eh via sockets)
 */
public class Cashier implements Protocol {
    private LocalServer server;
    private DataBase base;

    public Cashier() {
        server = new LocalServer();
        base = server.getBase();
        server.start();
    }

    //funções comuns
    public void searchTable() {

    }

    public void openTable(int tableID) {
        Table table = base.openTable(tableID);
        System.out.println("mesa: " + table.getID() + " aberta");
    }

    public void closeTable(int tableID) {
        base.closeTable(tableID);
        System.out.println("mesa: " + tableID + " fechada");
    }

    public void launchProduct(int tableID, int productID) {
        base.insertProduct(tableID, productID);
        System.out.println("produto: " + productID + " inserido na mesa: " + tableID);
    }

    public void cancelRequest(int tableID, int productID) {
        base.cancelProduct(tableID, productID);
        System.out.println("produto: " + productID + " removido da mesa: " + tableID);
    }

    //funções de administração
    public void registerProduct() {

    }

    //outras funções
    public DataBase getBase() {
        return base;
    }

}
