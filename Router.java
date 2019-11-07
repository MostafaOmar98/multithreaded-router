public class Router {
    private mySemaphore sem;
    private int numberOfConnections;
    // connected[i] is the name (string) of the connected device at index i
    // connected[i] = null means no device is connected at index i
    private String[] connected;
    private Object[] locks;
    public Router(int numberOfConnections){
        this.numberOfConnections = numberOfConnections;
        sem = new mySemaphore(numberOfConnections);
        connected = new String[numberOfConnections];
        locks = new Object[numberOfConnections];
        for (int i = 0; i < numberOfConnections; ++i)
            locks[i] = new Object();
    }


    public int connect(Device d){
        sem.Wait(d); // makes sure that there is an empty slot for connection

        for (int i = 0; i < numberOfConnections; ++i){
            // synchronized block because the String array connected is accessed and changed from multiple threads
            // Makes sure that when connected[i] is being changed it is changed by only 1 thread
            synchronized (locks[i]) {
                if (connected[i] == null) {
                    System.out.println("Connection " + (i + 1) + ": " + d.name + " connected");
                    connected[i] = d.name;
                    return i;
                }
            }
        }
        assert(false);
        return -1;
    }

    public void performActivity(int i){
        /*
        There is no need for synchronization block here
        Precondition for function peformActivity is that connect was called and finished, thus; I'm sure that connected[i] won't be changed
            from function connect during performActivity
        Precondition for function disconnect is that performActivity was called and finished, thus; I'm sure that connected[i] won't be changed
            from function disconnect during performActivity
        NOTE: function connect might read the value of connected[i] but I'm sure that it won't change it
         */
        System.out.println("Connection " + (i + 1) + ": " + connected[i] + " performs online activity");
    }

    public void disconnect(int i){
        System.out.println("Connection " + (i + 1) + ": " + connected[i] + " disconnected");
        connected[i] = null;
        sem.Signal();
    }


}
