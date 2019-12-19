package io.miyabi.navigate.ui.list

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import io.miyabi.navigate.R
import kotlin.random.Random

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.list_fragment, container, false)
        val table = view.findViewById<TableLayout>(R.id.list_table)
        table.removeAllViews()
        for (i in 0..99) {
            val row = TableRow(this.context)
            row.setBackgroundColor(Color.LTGRAY)
            row.id = i
            val lp = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT)
            lp.bottomMargin = 1
            row.addView(createInnerLayout(i), lp)
            table.addView(row)
        }
        return view
    }

    private fun createInnerLayout(number: Int):LinearLayout {
        val layout = LinearLayout(this.context)
        layout.orientation = LinearLayout.VERTICAL
        layout.addView(createTitleView(number))
        layout.addView(createDescriptionView())
        return layout
    }

    private fun createTitleView(number: Int):View {
        val view = TextView(this.context)
        view.setPadding(20, 10, 20, 10)
        view.setBackgroundColor(Color.WHITE)
        view.typeface = Typeface.DEFAULT_BOLD
        view.textSize = 24.0f
        view.text = "%d番目の行".format(number)
        return view
    }

    @SuppressLint("SetTextI18n")
    private fun createDescriptionView(): View {
        val view = TextView(this.context)
        view.setPadding(20, 10, 20, 10)
        view.setBackgroundColor(Color.WHITE)
        view.typeface = Typeface.SANS_SERIF
        view.textSize = 16.0f
        view.text = "%20.20f".format(Random.nextDouble() / Random.nextDouble())
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        // TODO: Use the ViewModel
    }
}