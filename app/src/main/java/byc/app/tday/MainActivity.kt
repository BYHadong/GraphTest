package byc.app.tday

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val chartDataArray = ArrayList<BaseChartDataModel>()
    val entry = ArrayList<Entry>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ChartDataAdd()
        ChartDataFormat()
        val dataSet = CreateChart()
        val dataChart = LineData(dataSet)
        lineChart.data = dataChart
        lineChart.invalidate()
    }

    fun ChartDataAdd(){
        for (i in 1..3) {
            for (j in 1..10 step i+1){
                val charDataBasic = BaseChartDataModel("${i}", "${j}")
                chartDataArray.add(charDataBasic)
            }
        }
    }

    fun ChartDataFormat(){
        for(chartData in chartDataArray){
            entry.add(Entry(chartData.time.toFloat(), chartData.work.toFloat()))
        }
    }

    fun CreateChart(): LineDataSet{
        val dataSet = LineDataSet(entry,"Char")
        dataSet.color = Color.BLUE
        dataSet.valueTextColor = Color.BLACK
        return dataSet
    }


}