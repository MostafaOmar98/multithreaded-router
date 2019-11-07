import java.util.Scanner;

public class Network {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Number of connections: ");
        int numberOfConnections = sc.nextInt();
        Router router = new Router(numberOfConnections);

        System.out.print("Number of devices: ");
        int numberOfDevices = sc.nextInt();

        // initializing devices
        Device[] deviceList = new Device[numberOfDevices];
        for (int i = 0; i < numberOfDevices; ++i){
            String name, type;
            System.out.print("Device " + (i + 1) + " name: ");
            name = sc.next();
            System.out.print("Device " + (i + 1) + " type: ");
            type = sc.next();
            deviceList[i] = new Device(name, type, router);
        }

        // starting connections
        Thread[] connections = new Thread[numberOfDevices];
        for (int i = 0; i < numberOfDevices; ++i){
            connections[i] = new Thread(deviceList[i]);
            connections[i].start();
        }


        // waiting for all threads to finish before main thread finishes
        // Not sure if not waiting would cause an issue or not
        for (int i = 0; i < numberOfDevices; ++i) {
            try {
                connections[i].join();
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
