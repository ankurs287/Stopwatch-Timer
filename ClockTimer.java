import java.util.Timer;
import java.util.TimerTask;

public class ClockTimer implements Runnable
{
    private int hours, minutes, seconds, miliSeconds, totaltimeinSecs;
    private static Timer timer = new Timer();

    public ClockTimer(int hours, int minutes, int seconds, int miliSeconds)
    {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.miliSeconds = miliSeconds;
        totaltimeinSecs = (hours * 60 * 60) + (minutes * 60) + seconds + (miliSeconds / 1000);
    }

    @Override
    public void run()
    {
        Timer timer = new Timer();
        timer.schedule(new Task(this, timer), 0, 1000);
    }

    public int getTotaltimeinSecs()
    {
        return totaltimeinSecs;
    }

    public static void main(String[] args)
    {
        ClockTimer ct = new ClockTimer(0, 0, 8, 0);
        System.out.println("Total Time in Seconds:" + ct.getTotaltimeinSecs());
        Thread thread = new Thread(ct);
        thread.start();
    }
}

class Task extends TimerTask implements ThreadListener
{
    private ClockTimer clockTimer;
    private int timeElapsed;
    private Timer timer;

    public Task(ClockTimer clockTimer, Timer timer)
    {
        this.clockTimer = clockTimer;
        this.timer = timer;
        timeElapsed = 0;
    }

    @Override
    public void onSecondsUpdate(ClockTimer remainingTime)
    {
        int temp = timeElapsed + 1;
        System.out.println(temp + " onSecondsUpdate called");
    }

    @Override
    public void onTimerEnd()
    {
        System.out.println("onTimerEnd called");
        timer.cancel();
    }

    @Override
    public void run()
    {
        if (timeElapsed >= clockTimer.getTotaltimeinSecs())
        {
            onTimerEnd();
        }
        else
        {
            onSecondsUpdate(clockTimer);
        }
        timeElapsed++;
    }
}

interface ThreadListener
{
    public void onSecondsUpdate(ClockTimer remainingTime);

    public void onTimerEnd();
}