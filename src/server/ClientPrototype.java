package server;

public class ClientPrototype
{
    public String name;
    public int id;
    public int[] data;

    public ClientPrototype (String name, int id, int[] data)
    {
        this.name = name;
        this.id = id;
        this.data = data;
    }
}