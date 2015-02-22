package khosh.nil.scheduleBuilder;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.LinkedList;

import khosh.nil.todolist.R;

/**
 * Created by Nil on 2015-02-21.
 */
public class AddTaskActivity extends ActionBarActivity {

    ArrayList<Task> allTasksUnsorted;
    ArrayList<Task>[] taskList;
    LinkedList<Task> schedule;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addTask() {
        int listIndex;
        String name = ((EditText)findViewById(R.id.taskName)).getText().toString();
        Task.Priority priority;

        RadioButton radioHigh = (RadioButton) findViewById(R.id.pr_radio_high);
        RadioButton radioMid = (RadioButton) findViewById(R.id.pr_radio_mid);
        if( radioHigh.isChecked() ) {
            priority = Task.Priority.HIGH;
            listIndex = 0;
        }
        else if( radioMid.isChecked() ) {
            priority = Task.Priority.MID;
            listIndex = 1;
        }
        else {
            priority = Task.Priority.LOW;
            listIndex = 2;
        }

        int duration = Integer.parseInt(((EditText)findViewById(R.id.taskTime)).getText().toString());
        boolean noBreak = ((CheckBox)findViewById(R.id.noBreak)).isSelected();

        Task t = new Task(name, duration, priority, noBreak);
        taskList[listIndex].add(t);
    }

    public void sortTasks() {
        int breakTime = Integer.parseInt(((EditText) findViewById(R.id.breakTime)).getText().toString());
        Task taskBreak = new Task("Break",breakTime, Task.Priority.HIGH, true);

        for(int i=0; i<3; i++) {
            schedule.addAll(taskList[i]);
        }
        int time = 0;
        int intervalTime = 0;
        int maxTime = Integer.parseInt(((EditText) findViewById(R.id.maxTime)).getText().toString());
        for(int i=0; i<schedule.size(); i++) {
            if( schedule.get(i) == taskBreak ) {
                intervalTime = 0;
                continue;
            }

            if(schedule.get(i).hasNoBreak()) {
                time += schedule.get(i).getDuration();
                intervalTime += schedule.get(i).getDuration();
            }
            else {
                if( intervalTime>maxTime ) {
                    schedule.add(i, taskBreak);
                    i--;
                }
            }
        }
    }
}

