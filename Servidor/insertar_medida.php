<?php

include 'conexion.php';//incluimos el archivo conexion

//creamos las variables que guardarán los datos enviados desde la aplicación android
$idMedicion=$_POST['idMedicion'];
$idSensor=$_POST['idSensor'];
$valorMedicion=$_POST['valorMedicion'];
//$latitud=$_POST['latitud'];
//$longitud=$_POST['longitud'];
//$momentoMedicion=$_POST['momentoMedicion'];

//hacemos la consulta para insertar en la base de datos los datos recibidos
$consulta="insert into medida values('".$idMedicion."','".$idSensor."','".$valorMedicion."')";
mysqli_query($conexion,$consulta) or die (mysqli_error());
mysqli_close($conexion);//cerramos sesión

?>