package com.project.agilugr

import android.os.Build
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.core.view.GestureDetectorCompat
import com.project.agilugr.ui.navigation.NavigationDirector
import com.project.agilugr.ui.navigation.NavigationMapper
import com.project.agilugr.ui.theme.AgilUGRTheme
import kotlin.time.ExperimentalTime
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


@ExperimentalTime
class MainActivity : AppCompatActivity() {

    // APIs que vamos a consumir para tomar los datos del backend
    val focus_api = MockFocusAPI.getMockFocusAPI()

    // Tomamos el director de navegacion para que lance la interfaz grafica
    val navigation_director = NavigationDirector(focus_api = focus_api)

    // Detector de gestor
    private lateinit var mDetector: GestureDetectorCompat

    // Sensores
    private var sensorManager: SensorManager? =null
    private var mProximitySensor: Sensor? = null
    private var accelerometerSensor: Sensor? = null
    private var orientationSensor:Sensor?=null

    // Detector de posicion GPS
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    // Funcion principal
    @ExperimentalAnimationApi
    @RequiresApi(Build.VERSION_CODES.O)
    /** Función dónde inicializamos todos los elementos de la app que usaremos*/
    override fun onCreate(savedInstanceState: Bundle?) {
        // Llamamos al onCreate del parent
        super.onCreate(savedInstanceState)
        //Para que la app no se use en modo nocturno
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // Inicializamos el detector de posicion GPS
        // Lo hacemos aqui porque necesitamos este objeto para el navigation mapper
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Establecemos la UI de la aplicacion
        // Esto tambien crea el director de navegacion
        setContent {
            AgilUGRTheme {

                // Usamos el director de navegacion para lanzar la interfaz grafica
                navigation_director.buildNavigationAndStartUI(fusedLocationClient)
            }
        }

        // Establecemos el detector de gestos
        mDetector = GestureDetectorCompat(this, MyGestureListener(navigation_director))

        //Sensores
        sensorManager = applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager?

        //Sensor de Proximidad
        mProximitySensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        if (mProximitySensor == null) {
            //Toast.makeText(this,"Proximity sensor not available",5)
        } else {
            sensorManager!!.registerListener(MysensorListener(navigation_director), mProximitySensor, SensorManager.SENSOR_DELAY_NORMAL)
        }

        //Sensor Acelerómetro
        accelerometerSensor=sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER).also {
            accelerometerSensor -> sensorManager!!.registerListener(MysensorListener(navigation_director),accelerometerSensor,SensorManager.SENSOR_DELAY_FASTEST,SensorManager.SENSOR_DELAY_FASTEST)
        }

        // Inicializamos el sensor de orientacion
        orientationSensor=sensorManager!!.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR).also {
                orientationSensor -> sensorManager!!.registerListener(MysensorListener(navigation_director),orientationSensor,SensorManager.SENSOR_DELAY_FASTEST,SensorManager.SENSOR_DELAY_FASTEST)
        }
    }


    /**Detectamos los gestos usando la clase privada que hemos desarrollado */
    override fun onTouchEvent(event: MotionEvent): Boolean {
        mDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }


    /** Clase para la gestión de los eventos generados por gestos*/
    private class MyGestureListener(val navigationDirector: NavigationDirector): GestureDetector.SimpleOnGestureListener() {

        private val SWIPE_THRESHOLD = 50
        private val SWIPE_VELOCITY_THRESHOLD = 100
        private var prevdiffX=0F
        private var prevdiffY=0F
        private var prevVelocityX=0F
        private var prevVelocityY=0F

        override fun onDown(event: MotionEvent): Boolean {
            return true
        }

        /** Función para volver a la vista principal con dos toques sobre la pantalla*/
        override fun onDoubleTap(e: MotionEvent?): Boolean {
            this.navigationDirector.navigate(NavigationMapper.MAIN_VIEW)
            return true
        }

        /** Función para gestionar la navegación deslizando el dedo sobre la pantalla, se basa en ver en valor absoluto la
         * distancia en el eje X que recorremos y en el eje Y (medida en píxeles), si dicha distancia supera un umbral a una velocidad
         * determinada se considera que se ha realizado el gesto. Después se compara la distancia en píxeles del eje X y el eje Y, si
         * la distancia en el eje X es mayor que en el Y se considera un movimiento horizontal, en caso contrario un movimiento vertical.
         * También se define el patrón cruz para salir del modo Focus, para ello se almacena el gesto que se hizo con anterioridad y
         * se combina con el gesto actual.*/
        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            val result = false
            try {
                val diffY = e2!!.y - e1!!.y
                val diffX = e2.x - e1.x

                synchronized(this) {
                    if (this.navigationDirector.getCurrentView()==NavigationMapper.FOCUS_MODE_SESSION) {
                        if (Math.abs(prevdiffX) > Math.abs(prevdiffY) && prevVelocityX>50) {
                            if (Math.abs(diffY)> Math.abs(diffX) && velocityY>50) {
                                this.navigationDirector.navigate(NavigationMapper.FOCUS_MODE_SELECTOR)
                            }
                        }else if (Math.abs(prevdiffX) < Math.abs(prevdiffY) && prevVelocityY>50){
                            if (Math.abs(diffY)< Math.abs(diffX) && velocityX>50) {
                                this.navigationDirector.navigate(NavigationMapper.FOCUS_MODE_SELECTOR)
                            }
                        }
                    }
                    if (this.navigationDirector.getCurrentView() == NavigationMapper.MAIN_VIEW) {
                        if (Math.abs(diffX) > Math.abs(diffY)) {
                            if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                                if (diffX > 0) {
                                    this.navigationDirector.navigate(NavigationMapper.FOCUS_MODE_SELECTOR)
                                } else {
                                    this.navigationDirector.navigate(NavigationMapper.CALENDAR)
                                }
                            }
                        } else {
                            if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                                if (diffY > 0) {
                                    this.navigationDirector.navigate(NavigationMapper.MAIN_VIEW)
                                } else {
                                    this.navigationDirector.navigate(NavigationMapper.PERFIL_MODE)
                                }
                            }
                        }
                    }

                    if (this.navigationDirector.getCurrentView() == NavigationMapper.PERFIL_MODE) {
                        if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffY > 0) {
                                this.navigationDirector.navigate(NavigationMapper.MAIN_VIEW)

                            }
                        }
                    }

                    if (this.navigationDirector.getCurrentView() == NavigationMapper.FOCUS_MODE_SELECTOR) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffY > 0) {
                                this.navigationDirector.navigate(NavigationMapper.MAIN_VIEW)

                            }
                        }
                    }

                    if (this.navigationDirector.getCurrentView() == NavigationMapper.CALENDAR) {
                        if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                            if (diffX > 0) {
                                this.navigationDirector.navigate(NavigationMapper.MAIN_VIEW)

                            }
                        }
                    }
                }

                prevdiffX=diffX
                prevdiffY=diffY
                prevVelocityX=velocityX
                prevVelocityY=velocityY
            }catch (exception: Exception) {
                exception.printStackTrace()
            }

            return result
        }

    }

    /** Clase privada para la gestión de los sensores*/
    private class MysensorListener (val navigationDirector: NavigationDirector): SensorEventListener{

        var prevx=0F
        var prevy=0F
        var prevz=0F
        var axisX: Float = 10.0f
        var axisY: Float = 10.0f
        var axisZ: Float = 10.0f
        // Create a constant to convert nanoseconds to seconds.

        /** Función principal en la cual detectamos el tipo de sensor que realiza el evento y posteriormente se define la acción concreta que realiza
         * en el caso del sesnor de orientación, lo usamos para activar el modo focus, el acelerómetro para ir a la vista de la TUI*/
        override fun onSensorChanged(event: SensorEvent?) {
            synchronized (this) {
                if (event != null) {

                    if (event.sensor.type == Sensor.TYPE_ACCELEROMETER){
                        val x = event.values[0]
                        val y = event.values[1]
                        val z = event.values[2]

                        // extension property to get screen orientation
                        val Xmovement: Double = Math.abs(x - prevx).toDouble()

                        val mAccelCurrent: Double = Math.sqrt((x * x + y * y + z * z).toDouble())

                            if (mAccelCurrent>=30 && Xmovement>=7F){
                                this.navigationDirector.navigate(NavigationMapper.TUI_VIEW)
                            }

                        prevx =x
                        prevy =y
                        prevz =z
                    }

                    if (event.sensor.type == Sensor.TYPE_ROTATION_VECTOR){
                        axisX = event.values[0]
                        axisY = event.values[1]
                        axisZ = event.values[2]

                        if (axisZ >=-0.2  && axisZ<=0.1 && Math.abs(axisY)>=0.6 ){
                            this.navigationDirector.navigate(NavigationMapper.FOCUS_MODE_SESSION)
                        }

                    }


                }



            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

        }

    }

}

