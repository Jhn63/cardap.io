package client.hall;

import client.Protocol;
import client.tools.Table;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Prototipo de cliente (app garçom)
 * será substituido posteriormente
 */
public class WaiterPrototype implements Protocol {

    //solicita a procura de uma mesa no banco de dados
    public String searchTable(int tableID) {
        return SEARCH_TABLE + "," + tableID;
    }

    //solicita a abertura de uma mesa
    public String openTable(int tableID) {
        return OPEN_TABLE + "," + tableID;
    }

    //solicita fechar mesa
    public void closeTable(int tableID) {

    }

    //solicta o lançamento de produto
    public String launchProduct(int tableID, int productID) {
        return LAUNCH_PRODUCT + "," + tableID + "," + productID;
    }

    //solicita cancelamento de produto
    public void cancelProduct(int tableID, int productID) {

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket socket = new Socket("localhost", 4444);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        WaiterPrototype waiter = new WaiterPrototype();
        Scanner scanner = new Scanner(System.in);
        for (;;)
        {
            //String request = waiter.searchTable(17);
            String request = waiter.openTable(17);
            out.writeUTF(request);

            Table table = (Table) in.readObject();
            if (table == null) {
                System.out.println("null");
            } else {
                System.out.println(table.getID());
            }
            scanner.next();
        }
        
        //scan.close();
        //in.close();
        //out.close();
        //socket.close();
    }
}
