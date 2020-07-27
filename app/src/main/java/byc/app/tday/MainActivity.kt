package byc.app.tday

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.LinearLayout
import androidx.core.view.get
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val chartDataArray = ArrayList<BaseChartDataModel>()
    val chartDataArray2 = ArrayList<BaseChartDataModel>()
    val chartDataArray3 = ArrayList<BaseChartDataModel>()
    val chartDataArray4 = ArrayList<BaseChartDataModel>()
    val entry = ArrayList<Entry>()
    val entry2 = ArrayList<Entry>()
    val entry3 = ArrayList<Entry>()
    val entry4 = ArrayList<Entry>()
    val barentry = ArrayList<BarEntry>()
    val pieEntry = ArrayList<PieEntry>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getRealMetrics(displayMetrics)

        lineChart.layoutParams = LinearLayout.LayoutParams(displayMetrics.widthPixels, displayMetrics.heightPixels)
        barChart.layoutParams = LinearLayout.LayoutParams(displayMetrics.widthPixels, displayMetrics.heightPixels)
        pieChart.layoutParams = LinearLayout.LayoutParams(displayMetrics.widthPixels, displayMetrics.heightPixels)

        //LineChart
        entry.add(Entry(0f, 100000f))
        entry.add(Entry(0f, 130000f))
        entry.add(Entry(0f, 140000f))
        entry2.add(Entry(1f, 110000f))
        entry2.add(Entry(1f, 100000f))
        entry2.add(Entry(1f, 150000f))
        entry3.add(Entry(2f, 90000f))
        entry3.add(Entry(2f, 120000f))
        entry3.add(Entry(2f, 160000f))
        entry4.add(Entry(3f, 11000f))
        entry4.add(Entry(3f, 90000f))
        entry4.add(Entry(3f, 130000f))
        val dataSet = CreateLineChart(entry,Color.BLUE)
        val dataSet2 = CreateLineChart(entry2, Color.GREEN)
        val dataSet3 = CreateLineChart(entry3, Color.YELLOW)
        val dataSet4 = CreateLineChart(entry4, Color.RED)
        val dataChart = ArrayList<ILineDataSet>()
        dataChart.add(dataSet)
        dataChart.add(dataSet2)
        dataChart.add(dataSet3)
        dataChart.add(dataSet4)
        val dataFormat = LineData(dataChart)
        lineChart.data = dataFormat
        lineChart.invalidate()
        val quarters = mutableListOf<String>("W1", "W2", "W3", "W4")
        val formatter = object : ValueFormatter(){
            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                return quarters[value.toInt()]
            }
        }
        val xAxis = lineChart.xAxis
        xAxis.granularity = 1f
        xAxis.valueFormatter = formatter
        //barChart
        barentry.add(BarEntry(0f, 30f))
        barentry.add(BarEntry(1f, 80f))
        barentry.add(BarEntry(2f, 60f))
        barentry.add(BarEntry(3f, 50f))
        barentry.add(BarEntry(5f, 70f))
        barentry.add(BarEntry(6f, 60f))
        val barDataSet = CreateBarChart(barentry, Color.BLUE)
        val dataChart2 = BarData(barDataSet)
        dataChart2.barWidth = 0.9f
        barChart.data = dataChart2
        barChart.setFitBars(true)
        barChart.invalidate()
        //piChart
        pieEntry.add(PieEntry(18.5F, "Red"))
        pieEntry.add(PieEntry(30.8F, "Blue"))
        pieEntry.add(PieEntry(24F, "Green"))
        pieEntry.add(PieEntry(26.7F, "Yellow"))
        val pieDataSet = CreatePieChart(pieEntry)
        val dataChart3 = PieData(pieDataSet)
        pieChart.data = dataChart3
        pieChart.invalidate()

    }

    fun ChartDataAdd(){
        for (i in 1..3) {
            for (j in 1..10 step i+1){
                val charDataBasic = BaseChartDataModel("${i}", "${j}")
                chartDataArray.add(charDataBasic)
                chartDataArray2.add(charDataBasic)
                chartDataArray3.add(charDataBasic)
                chartDataArray4.add(charDataBasic)
            }
        }
    }

    fun ChartDataFormat(){
        for(chartData in chartDataArray){
            entry.add(Entry(chartData.time.toFloat(), chartData.work.toFloat()))
    }
        for(chartData in chartDataArray2){
            entry2.add(Entry(chartData.time.toFloat(), chartData.work.toFloat()))
        }
        for(chartData in chartDataArray2){
            entry3.add(Entry(chartData.time.toFloat(), chartData.work.toFloat()))
        }
        for(chartData in chartDataArray2){
            entry4.add(Entry(chartData.time.toFloat(), chartData.work.toFloat()))
        }
    }

    fun CreateLineChart(entry: ArrayList<Entry>, chartColor: Int): LineDataSet{
        val dataSet = LineDataSet(entry,"Chart")
        dataSet.color = chartColor
        dataSet.valueTextColor = Color.BLACK
        dataSet.axisDependency = YAxis.AxisDependency.LEFT
        return dataSet
    }

    fun CreateBarChart(entry: ArrayList<BarEntry>, chartColor: Int): BarDataSet{
        val dataSet = BarDataSet(entry,"Chart")
        dataSet.color = chartColor
        dataSet.valueTextColor = Color.BLACK
        return dataSet
    }

    fun CreatePieChart(entry: ArrayList<PieEntry>): PieDataSet{
        val dataSet = PieDataSet(entry, "Chart")
        return dataSet
    }


}