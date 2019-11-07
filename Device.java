public class Device implements Runnable {
    String name, type;
    Router router;
    public Device(String name, String type, Router router){
        this.name = name;
        this.type = type;
        this.router = router;
    }

    private int connect(){
        return router.connect(this);
    }

    private void performActivity(int i){
        router.performActivity(i);
    }

    private void disconnect(int i){
        router.disconnect(i);
    }

    private void sleepRandTime(){
        try{
            Thread.sleep((long)(Math.random() * 5000.0));
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public void run(){
//        System.out.println(name + "(" + type +") arrived");
        int i = this.connect();
        this.sleepRandTime();
        this.performActivity(i);
        this.sleepRandTime();
        this.disconnect(i);
    }

    public String toString(){
        return name + "(" + type + ")";
    }
}
