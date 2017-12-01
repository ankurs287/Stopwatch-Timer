# Stopwatch-Timer
A basic stopwatch timer

1. Runs in a background thread
2. Can be initialised using constructor: new ClockTimer(int hours, int minutes, int seconds, int miliSeconds)
3.Can be started by calling start() method.
4. Uses call back listeners using interfaces that:
  a) Calls a method: onSecondsUpdate(ClockTimer remainingTime) in the listener after each second.
  b) Calls: onTimerEnd() when the time remaining is 00:00
