public class mySemaphore {
    int count;

    public mySemaphore(int initial){
        count = initial;
    }

    public synchronized void Wait(Device d){
        count--;
        if (count < 0){
            System.out.println(d.toString() + " arrived and waiting"); // not very clean coding printing this here but to pass assignment's requirements
            try{
                wait();
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }else{
            System.out.println(d.toString() + " arrived");
        }
    }

    public synchronized void Signal(){
        count++;
        if (count <= 0){
            notify();
        }
    }
}
