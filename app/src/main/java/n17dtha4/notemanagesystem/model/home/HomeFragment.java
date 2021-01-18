package n17dtha4.notemanagesystem.model.home;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

import n17dtha4.notemanagesystem.R;
import n17dtha4.notemanagesystem.model.status.StatusViewModel;

public class HomeFragment extends Fragment implements OnChartValueSelectedListener {

    private HomeViewModel homeViewModel;
    private PieChart mChart;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        // final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //    textView.setText(s);
            }
        });


        mChart = (PieChart) root.findViewById(R.id.piechart);
        mChart.setRotationEnabled(true);
        mChart.getDescription().setText("");
        mChart.setHoleRadius(0f);
        mChart.setTransparentCircleAlpha(0);
        mChart.setCenterTextSize(10);
        mChart.setDrawEntryLabels(true);
        addDataSet(mChart,root);
        mChart.setOnChartValueSelectedListener(this);

        return root;
    }

    private static void addDataSet(PieChart pieChart,View view) {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();


        home_db home_db = new home_db(view.getContext());
        int sum = home_db.sumSatatus();
        List<StatusViewModel> listSatus = home_db.GetListStatus();
        float[] data = new float[listSatus.size()];
        String[] lData = new String[listSatus.size()];
        int index = 0 ;
        for (StatusViewModel s:listSatus) {
            float p = home_db.countSatatus(s.getName())*100/sum;
            if(p != 0.0)
                data[index] = p;
            lData[index] = s.getName();
            index++;
        }
        float[] yData =data;
        String[] xData = lData;
        for (int i = 0; i < yData.length;i++){
            yEntrys.add(new PieEntry(yData[i],i));
        }
        for (int i = 0; i < xData.length;i++){
            xEntrys.add(xData[i]);
        }

        PieDataSet pieDataSet=new PieDataSet(yEntrys,"");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);


        ArrayList<Integer> colors=new ArrayList<>();
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.GRAY);
        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);

        Legend legend=pieChart.getLegend();
        legend.setEnabled(true);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);


        List<LegendEntry> entries = new ArrayList<>();

        for (int i = 0; i < xData.length; i++) {
            LegendEntry entry = new LegendEntry();
            entry.formColor = colors.get(i);
            entry.label = xData[i];
            entries.add(entry);
        }
        legend.setCustom(entries);


        PieData pieData=new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
//        Toast.makeText(getContext(), "Value: "
//                + e.getY()
//                + ", index: "
//                + h.getX()
//                + ", DataSet index: "
//                + h.getDataSetIndex(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected() {

    }
}