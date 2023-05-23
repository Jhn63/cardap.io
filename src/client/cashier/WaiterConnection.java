package client.cashier;

import client.Protocol;
import client.tools.Table;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;


/* PROTOCOLO DE REQUISIÇÕES: SOLICITACAO/MESA/ ou SOLICITACAO/MESA/PRODUTO*/

public class WaiterConnection implements Runnable, Protocol {
    Socket socket;
    DataBase base;

    public WaiterConnection(Socket socket, DataBase base) {
        this.socket = socket;
        this.base = base;
    }

    @Override
    public void run() {
        try {
            String clientName = Thread.currentThread().getName();
            DataInputStream in = new DataInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            String msg;
            while (true) {
                msg = in.readUTF();
                String data[] = msg.split(",");

                switch (data[0])
                {
                    case SEARCH_TABLE: {
                        int tableID = Integer.parseInt(data[1]);
                        Table table = base.getTable(tableID);
                        out.writeObject(table);

                        System.out.println("procurando mesa: " + tableID);
                        break;
                    }
                    case OPEN_TABLE: {
                        int tableID = Integer.parseInt(data[1]);
                        Table table = base.openTable(tableID);
                        out.writeObject(table);

                        System.out.println(tableID + " aberta");
                        break;
                    }
                    case CLOSE_TABLE: {
                        int tableID = Integer.parseInt(data[1]);
                        base.closeTable(tableID);

                        System.out.println(tableID + " fechada");
                        break;
                    }
                    case LAUNCH_PRODUCT: {
                        int tableID = Integer.parseInt(data[1]);
                        int productID = Integer.parseInt(data[2]);
                        base.insertProduct(tableID, productID);

                        System.out.println("produto " + productID + " lançado na mesa: " + tableID);
                        break;
                    }
                    case CANCEL_PRODUCT: {
                        //AINDA NÃO IMPLEMENTADO
                        int tableID = Integer.parseInt(data[1]);
                        int productID = Integer.parseInt(data[2]);
                        break;
                    }
                }

            }
            //System.out.println("process with "+ clientName + " finished");
            //in.close();
            //out.close();
            //socket.close();
        } catch (IOException ignored) { }
    }
}
