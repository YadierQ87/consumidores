<?php
class db_model
{
    public $conexion;

    public function __construct(){
        if (!isset($this->conexion)) {
            try {
                $matriz_ini = parse_ini_file("config_api.ini");
                $host = $matriz_ini["host"];
				$user = $matriz_ini["user"];
                $port = $matriz_ini["port"];
				$pasw = $matriz_ini["pasw"];
                $database = $matriz_ini["dbname"];
                $this->conexion = new mysqli($host,$user,$pasw,$database,$port); 
            } catch (Exception $e) {
                echo 'Fall&oacute; la conexion con la base de datos' . $e;
            }
        }
    }
    /**
     * @return PDO
     */
    public function getConexion (){
        return $this->conexion;
    }

    public function ultimaInsercion(){
        return $this->conexion->lastInsertId();
    }
    //Api_functions for models
    /**
     * @param $sql
     * @return array|bool
     * Lee la cadena SQL recibida y ejecuta la consulta
     */
    function get_query($sql) {
        $result = $this->conexion->query($sql);
        if($result) { return $result->fetchAll(); } return false;
    }
    /**
     * @param $sql
     * @return PDOStatement
     * Solo se usara para las consultas de tipo INSERT, UPDATE Y DELETE
     */
    function set_query($sql) {
        // Lee la cadena SQL recibida y ejecuta la consulta
       return $this->conexion->query($sql);
    }

}