package com.syntax_institut.whatssyntaximport androidx.appcompat.app.AppCompatActivityimport android.os.Bundleimport android.view.Viewimport androidx.activity.OnBackPressedCallbackimport androidx.navigation.findNavControllerimport androidx.navigation.fragment.NavHostFragmentimport androidx.navigation.ui.setupWithNavControllerimport com.syntax_institut.whatssyntax.data.Datasourceimport com.syntax_institut.whatssyntax.databinding.ActivityMainBindingclass MainActivity : AppCompatActivity() {    private lateinit var binding: ActivityMainBinding    val datasource = Datasource()    override fun onCreate(savedInstanceState: Bundle?) {        super.onCreate(savedInstanceState)        //set binding        binding = ActivityMainBinding.inflate(layoutInflater)        setContentView(binding.root)        //navHostFragment is the fragment that contains the navigation graph        val navHost =            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment        //setupWithNavController is a method of the BottomNavigationView class        binding.bottomNavigationBar.setupWithNavController(navHost.navController)    }}