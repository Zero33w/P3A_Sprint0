<?php

include 'conexion.php';//incluimos el archivo conexion
$idMedicion=$_GET['idMedicion'];

$consulta = "select * from medida where idMedicion = '$idMedicion'";
$resultado = $conexion -> query($consulta);

while($fila=$resultado -> fetch_array()){
    $medida[] = array_map('utf8_encode', $fila);
}

echo json_encode($medida);
$resultado -> close();
?>