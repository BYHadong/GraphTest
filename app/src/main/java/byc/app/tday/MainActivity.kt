package byc.app.tday

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.LinearLayout
import androidx.core.view.get
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val pieEntry = ArrayList<PieEntry>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getRealMetrics(displayMetrics)

        //Chart Size Setting
        pieChart.layoutParams =
            LinearLayout.LayoutParams(
                displayMetrics.widthPixels / 2,
                displayMetrics.heightPixels / 2
            )
        pieChart2.layoutParams =
            LinearLayout.LayoutParams(
                displayMetrics.widthPixels / 2,
                displayMetrics.heightPixels / 2
            )

        //piChart
        pieEntry.add(PieEntry(18.5F, "Red"))
        pieEntry.add(PieEntry(30.8F, "Blue"))
        pieEntry.add(PieEntry(24F, "Green"))
        pieEntry.add(PieEntry(26.7F, "Yellow"))
        val pieDataSet = CreatePieChart(pieEntry)
        val dataChart3 = PieData(pieDataSet)
        pieChart.data = dataChart3
        pieChart.setTouchEnabled(false)
        pieChart.legend.isEnabled = false
        pieChart2.data = dataChart3
        pieChart2.setTouchEnabled(false)
        pieChart2.legend.isEnabled = false
        val descriptionNull = Description()
        descriptionNull.text = ""
        pieChart.description = descriptionNull
        pieChart2.description = descriptionNull


        //Start Chart
        pieChart.invalidate()
        pieChart2.invalidate()
    }

    private fun CreatePieChart(entry: ArrayList<PieEntry>): PieDataSet {
        val dataSet = PieDataSet(entry, "Chart")
        dataSet.colors = mutableListOf(Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW)
        return dataSet
    }


}