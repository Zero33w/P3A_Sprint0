<?php
//creamos variables con los campos que necesitaremos para nuestra conexión a la base de datos y los englobamos en $conexion
$hostname='localhost';
$database='sprint0_bd';
$username='root';
$password='';

$conexion=new mysqli($hostname,$username,$password,$database);
//comprobamos si la conexion con el servidor y la base de datos es correcta
if($conexion->connect_errno){
    echo "el sitio web esta experimentando problemas";
}//if()

?>