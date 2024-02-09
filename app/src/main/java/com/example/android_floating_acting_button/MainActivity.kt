package com.example.android_floating_acting_button

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    var itemCountTracker:Int = 1

    var listItem = ArrayList<String>()
    var adapter:ArrayAdapter<String?>? = null

    lateinit var listView: ListView
    lateinit var fab:FloatingActionButton

    lateinit var undoListener: View.OnClickListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        listView = findViewById(R.id.basicList)

        adapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1, listItem as List<String?>
        )

        listView.adapter = adapter

        fab = findViewById(R.id.fab)

        fab.setOnClickListener{
            addListItem()
            Snackbar.make(it, "added it", Snackbar.LENGTH_LONG)
            //Set a action FOR the snack bar
                .setAction("Undo", undoListener)

            //show the snack bar
                .show()
        }

        //Undo Action
        undoListener = View.OnClickListener {
            itemCountTracker--

            listItem.removeAt(listItem.size - 1)
            adapter?.notifyDataSetChanged()
            Snackbar.make(it, "Item Removed", Snackbar.LENGTH_SHORT)
                .setAction("Action Stuff", null)
                .show()

        }

    }

    private fun addListItem() {
        listItem.add("  $itemCountTracker     Item $itemCountTracker") //The item should look similar to one of those fancy multi item lists.
        itemCountTracker++
        adapter?.notifyDataSetChanged()
    }
}