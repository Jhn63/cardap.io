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

    public void closeTable() {

    }

    public void launchProduct() {

    }

    public void cancelProduct() {

    }

    //funções de administração
    public void registerProduct() {

    }

}
