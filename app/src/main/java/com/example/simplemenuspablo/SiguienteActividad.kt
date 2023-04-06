package com.example.simplemenuspablo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import androidx.appcompat.widget.Toolbar
import com.example.simplemenuspablo.databinding.ActivitySiguienteActividadBinding

class SiguienteActividad : AppCompatActivity() {

    private lateinit var binding: ActivitySiguienteActividadBinding
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySiguienteActividadBinding.inflate(layoutInflater)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(binding.root)


        toolbar = binding.mytoolbar.root
        toolbar.title = "My App with Menu"
        setSupportActionBar(toolbar)

        //  binding.tvOutput.setOnCreateContextMenuListener { menu, view, menuInfo ->
        //    menuInflater.inflate(R.menu.menu_color,menu)
        //  }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_color, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_red -> binding.root.setBackgroundColor(Color.RED)
            R.id.action_green -> binding.root.setBackgroundColor(Color.GREEN)
        }

        return true
    }
}