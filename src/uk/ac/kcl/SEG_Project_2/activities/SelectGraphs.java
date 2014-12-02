package uk.ac.kcl.SEG_Project_2.activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Spinner;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.utils.ColorTemplate;
import uk.ac.kcl.SEG_Project_2.R;
import uk.ac.kcl.SEG_Project_2.data.Country;
import uk.ac.kcl.SEG_Project_2.data.WorldBankApiRequest;

import java.util.ArrayList;
import java.util.Random;

public class SelectGraphs extends Activity {

	public ArrayList<String> xVals;
	ArrayList<LineDataSet> lineDataSets;
	ArrayList<BarDataSet> barDataSets;
	ArrayList<PieDataSet> pieDataSets;

	Spinner spinner;
	BarChart barchart;
	String[] countryArray;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle extras = getIntent().getExtras();
		ArrayList<Country> selectedCountries = extras.getParcelableArrayList("countries");
		String selectedMetric = extras.getParcelable("metric");
		getCountries(selectedCountries);
		int startMonth = extras.getInt("startMonth");
		int startYear = extras.getInt("startYear");
		int endMonth = extras.getInt("endMonth");
		int endYear = extras.getInt("endYear");

		WorldBankApiRequest request = new WorldBankApiRequest(SelectGraphs.this);
		request.setDateRange(startMonth, startYear, endMonth, endYear);
		request.setCountries(countryArray);
		request.setIndicator(selectedMetric);

		if (selectedMetric.equals("Gas Emissions")) {
			setContentView(R.layout.activity_main_barchart);
			//setContentView(R.layout.activity_main_piechart);
		}
		if (selectedMetric.equals("Population")) {
			setContentView(R.layout.activity_main_linechart);
		}
		if (selectedMetric.equals("Deforestation")) {
			setContentView(R.layout.activity_main_linechart);
		}
		if (selectedMetric.equals("Elec. Use")) {
			setContentView(R.layout.activity_main_piechart);
		}

		barchart = (BarChart) findViewById(R.id.barChart);
		spinner = (Spinner) findViewById(R.id.countries_spinner);

		createLineChart();
		createBarChart();
		createPieChart();

	}

	public String[] getCountries(ArrayList<Country> list) {

		countryArray = new String[list.size()];
		for (int i = 0; i <= list.size(); i++) {
			countryArray[i] = list.get(i).getId();
		}

		return countryArray;
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

	public void createLineChart() {

		Spinner spinner = (Spinner) findViewById(R.id.countries_spinner);
		//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		//                R.array.countries_array, android.R.layout.simple_spinner_item);
		//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//        spinner.setAdapter(adapter);

		LineChart chart = (LineChart) findViewById(R.id.lineChart);
		ArrayList<Entry> valsComp1 = new ArrayList<Entry>();
		ArrayList<Entry> valsComp2 = new ArrayList<Entry>();

		for (int i = 0; i <= 5; i++) {
			Random random = new Random();
			float entry = random.nextFloat() * 100;
			Entry countryOne = new Entry(entry, i);
			valsComp1.add(countryOne);

		}

		for (int j = 0; j <= 5; j++) {
			Random random = new Random();
			float entry = random.nextFloat() * 100;
			Entry countryTwo = new Entry(entry, j);
			valsComp2.add(countryTwo);

		}

		LineDataSet setComp1 = new LineDataSet(valsComp1, "Country 1");
		setComp1.setColor(Color.BLUE);

		LineDataSet setComp2 = new LineDataSet(valsComp2, "Country 2");
		setComp2.setColor(Color.RED);

		lineDataSets = new ArrayList<LineDataSet>();
		lineDataSets.add(setComp1);
		lineDataSets.add(setComp2);

		xVals = new ArrayList<String>();
		xVals.add("2009");
		xVals.add("2010");
		xVals.add("2011");
		xVals.add("2012");
		xVals.add("2013");
		xVals.add("2014");

		LineData data = new LineData(xVals, lineDataSets);
		chart.setData(data);

	}

	public void createBarChart() {
		if (spinner != null) {
			//            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
			//                    R.array.gases_array, android.R.layout.simple_spinner_item);
			//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			//            spinner.setAdapter(adapter);
			barchart.fitScreen();
		}
		ArrayList<BarEntry> barVals = new ArrayList<BarEntry>();
		ArrayList<BarEntry> barVals2 = new ArrayList<BarEntry>();
		ArrayList<BarEntry> barVals3 = new ArrayList<BarEntry>();

		for (int i = 0; i <= 5; i++) {
			Random random = new Random();
			float entry = random.nextFloat() * 100;
			BarEntry countryOne = new BarEntry(entry, i);
			barVals.add(countryOne);

		}

		for (int j = 0; j <= 5; j++) {
			Random random = new Random();
			float entry = random.nextFloat() * 100;
			BarEntry countryTwo = new BarEntry(entry, j);
			barVals2.add(countryTwo);

		}

		for (int x = 0; x <= 5; x++) {
			Random random = new Random();
			float entry = random.nextFloat() * 100;
			BarEntry countryThree = new BarEntry(entry, x);
			barVals3.add(countryThree);

		}

		BarDataSet setComp1 = new BarDataSet(barVals, "Country 1");
		setComp1.setBarSpacePercent(75f);
		setComp1.setColor(Color.BLUE);

		BarDataSet setComp2 = new BarDataSet(barVals2, "Country 2");
		setComp2.setBarSpacePercent(75f);
		setComp2.setColor(Color.YELLOW);

		BarDataSet setComp3 = new BarDataSet(barVals2, "Country 3");
		setComp3.setBarSpacePercent(75f);
		setComp3.setColor(Color.MAGENTA);

		barDataSets = new ArrayList<BarDataSet>();
		barDataSets.add(setComp1);
		barDataSets.add(setComp2);
		barDataSets.add(setComp3);

		xVals = new ArrayList<String>();
		xVals.add("2009");
		xVals.add("2010");
		xVals.add("2011");
		xVals.add("2012");
		xVals.add("2013");
		xVals.add("2014");

		BarData data = new BarData(xVals, barDataSets);
		barchart.setData(data);
		barchart.fitScreen();
	}

	public void createPieChart() {
		Spinner spinner = (Spinner) findViewById(R.id.countries_spinner);
		//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		//                R.array.gases_array, android.R.layout.simple_spinner_item);
		//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//        spinner.setAdapter(adapter);

		PieChart pieChart = (PieChart) findViewById(R.id.pieChart);
		ArrayList<Entry> pieVals = new ArrayList<Entry>();
		ArrayList<Entry> pieVals2 = new ArrayList<Entry>();

		for (int i = 0; i <= 5; i++) {
			Random random = new Random();
			float entry = random.nextFloat() * 100;
			Entry countryOne = new Entry(entry, i);
			pieVals.add(countryOne);

		}

		for (int j = 0; j <= 5; j++) {
			Random random = new Random();
			float entry = random.nextFloat() * 100;
			Entry countryTwo = new Entry(entry, j);
			pieVals2.add(countryTwo);

		}

		PieDataSet setComp1 = new PieDataSet(pieVals, "Country 1");
		setComp1.setSliceSpace(3f);
		PieDataSet setComp2 = new PieDataSet(pieVals, "Country 2");
		setComp1.setSliceSpace(3f);

		pieDataSets = new ArrayList<PieDataSet>();
		pieDataSets.add(setComp1);
		//dataSets.add(setComp2);

		xVals = new ArrayList<String>();
		xVals.add("2009");
		xVals.add("2010");
		xVals.add("2011");
		xVals.add("2012");
		xVals.add("2013");
		xVals.add("2014");

		ArrayList<Integer> colors = new ArrayList<Integer>();

		for (int c : ColorTemplate.JOYFUL_COLORS) {
			colors.add(c);
		}

		setComp1.setColors(colors);

		PieData data = new PieData(xVals, setComp1);
		pieChart.setData(data);

		pieChart.highlightValues(null);

		pieChart.invalidate();
	}

}
