package mohammadsharif.com.hang;

//TODO(@steve) add both interrupt methods for this
public class TimerHelper implements Runnable {

    static int[] timers = {15*60, 15*60, 15*60, 15*60, 15*60, 30*60};

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < timers.length; i++) {
                timers[i]--;
                MapsActivity.options[i].snippet("updated");
            }
        }
    }

    public int getSecondsLeft(int index) {
        return timers[index];
    }
}
