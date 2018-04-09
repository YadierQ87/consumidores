<?php
/**
 * Created by PhpStorm.
 * User: Yadier
 * Date: 3/31/2018
 * Time: 5:53 PM
 */
include_once "../api_rest/Api_Model_DB.php";

$table = $_POST['table'];
$action = $_POST['action'];
$array =  $_POST;

function arreglo_to_string($array){
    $string = "";
    foreach( $array as $key => $value )
        $string .= "-".$value;
    return $string;
}


if($action == "Insert"){
    $db = new Api_Model_DB();
    $arreglo = array_slice($array, 2);
    $msg = arreglo_to_string($arreglo);
    //guardar un log
    $date = date('Y-m-d-i-s-u');
    $nombre_archivo = 'Insertlog.txt';
    $gestor = fopen($nombre_archivo, 'a+');
    fwrite($gestor, "//user:admin => $date Insert $table : $msg ");
    fclose($gestor);
    //guardando en la bd
    $result = $db->Insert($table,$arreglo);
    if($result > 0)
        $response = array("status" => 200 ,"message"=>"Registro $result guardado");
    else
        $response = array("status" => 405 ,"message"=>"No se pudo Insertar");
    echo json_encode($response,JSON_PRETTY_PRINT);
}


