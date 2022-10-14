# P3A_Sprint0_Android
Repositorio para el sprint 0 del proyecto de 3ro semestre A en la carrera de GTI de la aplicación android

Para poder ejecutar el programa correctamente es necesario seguir una serie de pasos que explicaré detalladamente a continuación.

Paso 1: Instalar o tener instaladas las aplicaciones necesarias:
-Android Studio
-XAMPP Control Panel



Paso 2: Configuración del XAMPP:

Tras la instalación de XAMPP se creará una carpeta con el siguiente aspecto:
<img src="/_imgReadme/A1.png" alt="Aspecto carpeta xampp por dentro"/>

Hay que comprobar que esta carpeta se encuentra en la raiz del disco C:/ tal como se ve en la imagen, en caso contrario hay que reubicarla en la ruta indicada.

A continuación, tendremos que coger la carpeta llamada Servidor, que se encuentra en la raiz de este repositorio y tras descargarla, 
hay que renombrarla a sprint0 y ponerla dentro de la carpeta htdocs que se encuentra dentro de la carpeta xampp del paso anterior.

Seguidamente hay una imagen que muestra el resultado esperado:
<img src="/_imgReadme/A2.png" alt="Aspecto carpeta htdocs por dentro"/>



Paso 3: Acceder a phpMyadmin y preparar la estructura de la base de datos

Tras abrir la aplicación XAMPP hay que activar el Apache y el MySQL clicando el boton Start.
Una vez activados, hay que observar la columna de Port(s)  en la fila Apache, donde aparecerá un valor seguido de una coma y otro valor, hay que fijarse en el valor anterior a la coma, este es el puerto por el que está escuchando el servidor.

En la imagen siguiente se observa que en ese caso el numero de puerto es el 80 y que tiene activos el Apache y el MySQL:
<img src="/_imgReadme/A3.png" alt="Interfaz XAMPP"/>

Una vez claro el numero de puerto y con todos los campos mencionados activos, hay que ir a un servidor e insertar una búsqueda con la siguiente estructura: localhost:80   
pero cambiando el 80 por el número de puerto correspondiente.

Si todo ha ido bien habrá una página web con el icono de Xampp bien visible. En esta página se podrá encontrar en la parte superior derecha un botón llamado phpMyAdmin. Hay que pulsarlo.

Una vez clicado estaremos dentro de phpMyAdmin, aquí, habrá que crear una base de datos con el nombre sprint0_bd, y dentro de esta hay que hacer una tabla con los siguientes datos:
<img src="/_imgReadme/A5.png" alt="estructura medida"/>

Si todo ha ido bien, la base de datos debería tener la siguiente estructura
<img src="/_imgReadme/A4.png" alt="estructura bd"/>


Paso 4: abrir y modificar la ip y el puerto en el proyecto de android studio

En estre repositorio puedes encontrar 3 carpetas en la raiz con un proyecto android distinto dentro, el que se llama Arduino tiene tan solo la funcionalidad de recibir y leer beacon, el de Android tiene la 
funcionalidad de buscar en la base de datos por idMedicion una entrada en la tabla medida y puede añadir nuevas medidas indicadas por teclado.

Y finalmente el que interesa es el llamado Todo Junto, ya que tiene ambas funcionalidades y los beacons que recibe los procesa y sube directamente a la base de datos.

En el MainActivity del proyecto hay que ir a las lineas 145, 318 y 326 cambiar la parte de la ip y el puerto por los datos de tu PC siguiendo la estructura, el puerto es el que se ha mencionado varias veces aqui, y 
la ip la puedes obtener entrando en el cmd o Simbolo del sistema e introducir el comando ipconfig, tras eso fijate en su respuesta para ipv4, esa será la ip que ha de sustituir a la mencionada.

A continuación una imágen de como se ven las lineas de codigo a modificar:
<img src="/_imgReadme/A6.png" alt="comando variable"/>

Con eso listo solo queda ejecutar la aplicación con el botón run de android studio.

Dentro de esta encontraremos dos secciones (en la pantalla del movil), una que es para escuchar los beacons y aquellos escuchados serán directamente enviados a la base de datos, y la otra es para introducir a mano los campos de una medida y subirla o para consultar las medidas introduciendo su id.










