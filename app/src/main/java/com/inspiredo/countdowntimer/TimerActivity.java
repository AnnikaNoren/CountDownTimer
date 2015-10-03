package com.inspiredo.countdowntimer;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class TimerActivity extends AppCompatActivity implements View.OnClickListener {

    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    private Button startButton;
    private TextView timerLabel;
    private final long startTime = 5 * 1000;
    private final long interval = 1 * 1000;
    private TextView activityLabel;
    private int pointCounter = 0;
    //private MorningTaskList mMorningTaskList = new MorningTaskList();
    private int counter = 0;
    private TextView earnedPoints;
    private RelativeLayout mRelativeLayout;
    //private ColorWheel mColorWheel = new ColorWheel();

    private Task[] mMorningTasks = {
            new Task("Brush Teeth", 10),
            new Task("Make Bed", 4),
            new Task("Shower", 11)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        startButton = (Button) this.findViewById(R.id.button);
        startButton.setOnClickListener(this);
        timerLabel = (TextView) this.findViewById(R.id.timerTextView);
        countDownTimer = new MyCountDownTimer(startTime, interval);
        //timerLabel.setText(timerLabel.getText() + String.valueOf(startTime / 1000));
        activityLabel = (TextView) this.findViewById(R.id.activityTextView);
        earnedPoints = (TextView)  this.findViewById(R.id.earnedPointsTextView);
        mRelativeLayout = (RelativeLayout) this.findViewById(R.id.backgroundScreen);
    }

        @Override public void onClick (View v){

            if (counter == mMorningTasks.length)
                {
                    activityLabel.setText("Have a great day!");
                    earnedPoints.setText("You earned" + pointCounter + " points!");
                    timerLabel.setText("All Done!");

                    //int color = mColorWheel.getColor();
                    mRelativeLayout.setBackgroundColor(Color.GRAY);
                }
            else {
                    activityLabel.setText(mMorningTasks[counter].getTitle());

                if (!timerHasStarted) {
                    countDownTimer = new MyCountDownTimer(mMorningTasks[counter].getLength() * 1000, interval);
                    countDownTimer.start();
                    timerHasStarted = true;
                    startButton.setText("Stop when done");
                    earnedPoints.setText("");
                    mRelativeLayout.setBackgroundColor(getResources().getColor(R.color.purple));

                } else {
                    activityLabel.setText("Good Job!");
                    countDownTimer.cancel();
                    timerLabel.setText("Press Start for next task");
                    timerHasStarted = false;
                    counter++;  //this is a counter to go thru the task array
                    pointCounter++; //this is a counter to accumulate points if a task is finished early
                    startButton.setText("Start");
                    earnedPoints.setText("You earned a point!");
                    mRelativeLayout.setBackgroundColor(Color.GREEN);
                }
            }
        }

    public class MyCountDownTimer extends CountDownTimer {
            public MyCountDownTimer(long startTime, long interval) {
                super(startTime, interval);
            }

            @Override
            public void onFinish() {
                activityLabel.setText("Press Start for next task");
                counter = counter +1;
                timerHasStarted = false;
                timerLabel.setText("Time's Up!");
                startButton.setText("Start");
                earnedPoints.setText("Sorry, you didn't earn a point");
                mRelativeLayout.setBackgroundColor(Color.RED);
            }

            @Override
            public void onTick(long millisUntilFinished) {
                timerLabel.setText("" + millisUntilFinished / 1000);
            }
    }
}

