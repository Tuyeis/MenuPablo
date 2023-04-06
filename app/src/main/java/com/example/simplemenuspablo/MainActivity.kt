package com.example.simplemenuspablo

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast

import androidx.appcompat.widget.Toolbar
import com.example.simplemenuspablo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //Spanish:
        //Esta linea quita la barra de accion antigua.
        //Deutsch:
        // Mit dieser Zeile wird die alte Aktionsleiste entfernt.
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(binding.root)
        //Spanish:
        //El siguiente paso es crear la toolbar como un en la carpeta layout e inicializarla.
        //Der nächste Schritt besteht darin, die Symbolleiste als Symbolleiste im Layoutordner zu erstellen und zu initialisieren.
        toolbar = binding.mytoolbar.root
        toolbar.title = "My App with Menu"
        setSupportActionBar(toolbar)
        //Spanish:
        //El siguiente paso es crear un menu personalizado en res/ menu.
        //Deutsch:
        //Nächster Schritt ist die Erstellung eines benutzerdefinierten Menüs in res/menu.


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //Spanish:
        // Inflamos el menu
        //Deutsch:
        // Wir blasen das Menü auf
        menuInflater.inflate(R.menu.menu_main, menu)
        //Spanish:
        // Creamos la opcion de busqueda en el menu.
        //Deutsch:
        // Wir erstellen die Suchoption im Menü.
        val search = menu!!.findItem(R.id.app_bar_search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "What must i search?"
        searchView.setOnQueryTextListener(

            object : OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Toast.makeText(this@MainActivity, "Search for $query", Toast.LENGTH_SHORT)
                        .show()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }

            }
        )

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Spanish:
        //Es importante acordarte de cambiar parent="Theme.AppCompat.Light.DarkActionBar" en res/themes para que se puedan leer las letras.
        //Deutsch:
        //Es ist wichtig, daran zu denken, parent="Theme.AppCompat.Light.DarkActionBar" in res/themes zu ändern, damit die Buchstaben gelesen werden können.

        return when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(this, "Settings wurde getippt", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.action_next -> {
                //Spanish:
                //Continúa con la siguiente actividad.
                //Deutsch:
                //Weiter zur nächsten Activity.
                val intent = Intent(this, SiguienteActividad::class.java)
                startActivity(intent)
                true
            }
            R.id.action_send -> {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    //Spanish:
                    //La clase Uri es una clase que representa una cadena que identifica un recurso. En este caso, el recurso es el correo electrónico.
                    //Deutsch:
                    //Die Klasse Uri ist eine Klasse, die eine Zeichenkette darstellt, die eine Ressource identifiziert. In diesem Fall ist die Ressource die E-Mail Adresse
                    data = Uri.parse("mailto:")
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("pablonp67@gmail.com"))
                    putExtra(Intent.EXTRA_SUBJECT, "Asunto")
                    putExtra(Intent.EXTRA_TEXT, "Este es el contenido del correo electrónico")
                    //Spanish:
                    //El correo solo se abre con doble click he intentado varias cosas pero no parecen funcionar
                    //Deutsch:
                    //Die Mail öffnet sich nur mit einem Doppelklick. Ich habe schon einiges versucht, aber es scheint nicht zu funktionieren.
                    setPackage("com.google.android.gm")
                }
                //Spanish:
                //No olvidar anadir las querys en tu manifest!!!
                //Deutsch:
                //Vergessen Sie nicht, die "querys" in Ihr Manifest aufzunehmen: !!!!

                if (intent.resolveActivity(packageManager) != null)
                    startActivity(intent)
                else
                    Toast.makeText(
                        this@MainActivity,
                        "Kein E-Mail-Programm vorhanden",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }
}

