package finalproject.diskschedule;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import static java.lang.StrictMath.abs;

public class disk extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disk);
        EditText text=(EditText) findViewById(R.id.text);
        text.setFocusable(false);
    }
    public void fcfs(View v)
    {

        EditText a=(EditText) findViewById(R.id.noq);
        EditText b=(EditText) findViewById(R.id.hs);
        EditText q=(EditText) findViewById(R.id.que);
        EditText noc=(EditText) findViewById(R.id.noc);
        EditText text=(EditText) findViewById(R.id.text);
        text.setText("");

        //text.setEnabled(false);



        int m=new Integer(noc.getText().toString());

        int noq=new Integer(a.getText().toString());
        int hs=new Integer(b.getText().toString());
        String req = q.getText().toString();


        String[] tokens = req.split(" ");
        int[] ary = new int[tokens.length];

        int i = 0;
        for (String token : tokens){
            ary[i++] = Integer.parseInt(token);

        }
        int s=0,y=0;

        for(int j=0;j<noq;j++)
        {
            s=s+abs(hs-ary[j]);

            text.append("Head Movement from "+hs+" to "+ary[j]+" = "+abs(hs-ary[j]));
            text.append("\n");

            Toast.makeText(getApplicationContext(),"Head Movement from "+hs+" to "+ary[j]+" = "+abs(hs-ary[j]),Toast.LENGTH_SHORT
            ).show();

            hs=ary[j];
        }
        text.append("Total seek time is: "+s);
        Toast.makeText(getApplicationContext(),"Total seek time is: "+s,Toast.LENGTH_SHORT
        ).show();


    }
    public void sstf(View v)
    {

        EditText a=(EditText) findViewById(R.id.noq);
        EditText b=(EditText) findViewById(R.id.hs);
        EditText q=(EditText) findViewById(R.id.que);
        EditText text=(EditText) findViewById(R.id.text);
        int noq=new Integer(a.getText().toString());
        int hs=new Integer(b.getText().toString());
        String req = q.getText().toString();

        text.setText("");

        String[] tokens = req.split(" ");
        int[] ary = new int[tokens.length];

        int i = 0;
        for (String token : tokens){
            ary[i++] = Integer.parseInt(token);

        }
        int visit[]=new int[ary.length];
        for(int j=0;j<noq;j++)
        {

            visit[j] = 0;
        }
        int min,s=0,p=0;

        while(true)
        {
            min=999;
            for(int j=0;j<noq;j++) {
                if (visit[j] == 0) {
                    if (min > abs(hs - ary[j])) {
                        min = abs(hs - ary[j]);
                        p = j;
                    }
                }
            }
            if(min == 999)
                break;
            visit[p]=1;
            s=s + min;
            text.append("Head Movement from "+hs+" to "+ary[p]+" = "+abs(hs-ary[p]));
            text.append("\n");
           Toast.makeText(getApplicationContext(),"Head Movement from "+hs+" to "+ary[p]+" = "+abs(hs-ary[p]),Toast.LENGTH_SHORT
            ).show();
            hs = ary[p];

        }
        text.append("Total seek time is: "+s);
        Toast.makeText(getApplicationContext(),"Total seek time is: "+s,Toast.LENGTH_SHORT
        ).show();


    }

public void scan(View v)
    {

        EditText a=(EditText) findViewById(R.id.noq);
        EditText b=(EditText) findViewById(R.id.hs);
        EditText q=(EditText) findViewById(R.id.que);
        EditText text=(EditText) findViewById(R.id.text);

        text.setText("");

        int noq=new Integer(a.getText().toString());
        int hs=new Integer(b.getText().toString());
        String req = q.getText().toString();

        String[] tokens = req.split(" ");
        int[] ary = new int[tokens.length];

        int i = 0;
        for (String token : tokens){
            ary[i++] = Integer.parseInt(token);

        }
        int count=0;
        int pos=0,x=0;
        for(int j=0;j<noq;j++) {
            for (int k = 0; k < noq - j - 1; k++) {
                if (ary[k] > ary[k + 1]) {
                    x = ary[k];
                    ary[k] = ary[k + 1];
                    ary[k + 1] = x;
                }
            }
        }


        for(int j=0;j<noq;j++)
            if(ary[j]<hs)
                pos++;

        for(int j=0;j<pos;j++) {
            for (int k = 0; k < pos - j - 1; k++) {
                if (ary[k] < ary[k + 1]) {
                    x = ary[k];
                    ary[k] = ary[k + 1];
                    ary[k + 1] = x;
                }
            }
        }

        x=hs;

        for(int j=0;j<pos;j++)
        {
            count+=abs(ary[j]-x);
            text.append("Head Movement from: "+x+" to "+ary[j]+" = "+abs(ary[j]-x));
            text.append("\n");
            Toast.makeText(getApplicationContext(),"Head Movement from: "+x+" to "+ary[j]+" = "+abs(ary[j]-x),Toast.LENGTH_SHORT
            ).show();
            x=ary[j];

        }
        count+=abs(x-0);
        text.append("Head Movement from: "+x+" to "+" 0 "+" = "+abs(x-0));
        text.append("\n");
        Toast.makeText(getApplicationContext(),"Head Movemnt from: "+x+" to "+" 0 "+" = "+abs(x-0),Toast.LENGTH_SHORT
        ).show();
        x=0;

        for(int j=pos;j<noq;j++)
        {
            count+=abs(ary[j]-x);
            text.append("Head Movement from: "+x+" to "+ary[j]+" = "+abs(ary[j]-x));
            text.append("\n");
            Toast.makeText(getApplicationContext(),"Head Movement from: "+x+" to "+ary[j]+" = "+abs(ary[j]-x),Toast.LENGTH_SHORT
            ).show();
            x=ary[j];


        }

        text.append("Total seek time is: "+count);

        Toast.makeText(getApplicationContext(),"Total seek time is: "+count,Toast.LENGTH_SHORT
        ).show();


    }

    public void cscan(View v)
    {

        EditText a=(EditText) findViewById(R.id.noq);
        EditText b=(EditText) findViewById(R.id.hs);
        EditText q=(EditText) findViewById(R.id.que);
        EditText noc=(EditText) findViewById(R.id.noc);
        EditText text=(EditText) findViewById(R.id.text);


        text.setText("");

        int m=new Integer(noc.getText().toString());
        int noq=new Integer(a.getText().toString());
        int hs=new Integer(b.getText().toString());
        String req = q.getText().toString();

        String[] tokens = req.split(" ");
        int[] ary = new int[tokens.length];

        int i = 0;
        for (String token : tokens){
            ary[i++] = Integer.parseInt(token);

        }
        int count=0;
        int pos=0,x=0;
        for(int j=0;j<noq;j++) {
            for (int k = 0; k < noq - j - 1; k++) {
                if (ary[k] > ary[k + 1]) {
                    x = ary[k];
                    ary[k] = ary[k + 1];
                    ary[k + 1] = x;
                }
            }
        }


        for(int j=0;j<noq;j++)
            if(ary[j]<hs)
                pos++;



        x=hs;

        for(int j=pos;j<noq;j++)
        {
            count+=abs(ary[j]-x);
            text.append("Head Movement from: "+x+" to "+ary[j]+"  "+abs(ary[j]-x));
            text.append("\n");
            Toast.makeText(getApplicationContext(),"Head Movement from: "+x+" to "+ary[j]+" = "+abs(ary[j]-x),Toast.LENGTH_SHORT
            ).show();
            x=ary[j];


        }



        count+=abs((m-1)-x);
        text.append("Head Movement from: "+x+" to "+(m-1)+" = "+abs((m-1)-x));
        text.append("\n");
        Toast.makeText(getApplicationContext(),"Head Movement from: "+x+" to "+(m-1)+" = "+abs((m-1)-x),Toast.LENGTH_SHORT
        ).show();
        x=0;
        for(int j=0;j<pos;j++)
        {
            count+=abs(ary[j]-x);
            text.append("Head Movement from: "+x+" to "+ary[j]+" = "+abs(ary[j]-x));
            text.append("\n");
            Toast.makeText(getApplicationContext(),"Head Movement from: "+x+" to "+ary[j]+" = "+abs(ary[j]-x),Toast.LENGTH_SHORT
            ).show();
            x=ary[j];

        }



        text.append("Total seek time is: "+count);

        Toast.makeText(getApplicationContext(),"Total seek time is: "+count,Toast.LENGTH_SHORT
        ).show();


    }



}
