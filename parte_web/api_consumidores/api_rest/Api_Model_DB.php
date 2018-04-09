<?php
require_once "core/db_model.php";
class Api_Model_DB extends db_model {

    public $conexion;
    /**
     * Constructor de clase
     */
    public function __construct() {
        parent::__construct();
        $this->conexion = parent::getConexion();
    }
    /** devuelve listado de una tabla
     * @param $table
     * @return array|bool
     */
    public function getListado($table){
        return $this->list_execute_sql("SELECT * from $table");
    }
    /** devuelve entidad de una tabla
     * @param $table
     * @param $id
     * @return array|bool
     */
    public function getEntidad($table,$id){
        return $this->list_execute_sql("SELECT * from $table WHERE id = '$id'");
    }

    public function getLoginState($nickname,$passwd){
        $objpasswd = $this->list_execute_sql("SELECT  passwd from usuario_clasificados WHERE  usuario_nick = '$nickname'");
        if($objpasswd->passwd == $passwd)
            return true;
        return false;
    }

    public function cambia_caracter_extrannos($campo)    {
        $extrannos = array("<", "/", ">", "%", "$","!", "&", "*", "(",")","{","}","+","'\'",";");
        $campo = str_replace($extrannos, "", $campo);
        return $campo;
    }
    //para consultas de listar devuelve un arreglo.
    /**
     * @param $sql
     * @return array|bool
     */
    public function list_execute_sql ($sql)    {
        $this -> resultado = $this -> conexion -> query ($sql) or die("Error $sql");
        $listados = array ();
        if ($this -> resultado -> num_rows > 0 )
            while ($tupla = $this -> resultado -> fetch_object ())
            			$listados [] = $tupla;
        return $listados;
    }
    //ejecutar querys de insert,update or delete return false si no se ejecuto
    /**
     * @param $sql
     * @return bool
     */
    public function execute_query ($sql)    {
        $this -> conexion -> query ($sql) or die ("Error $sql");
        return ($this -> conexion -> affected_rows > 0) ? true : false;
    }
    /**
     * @param $table
     * @return bool
     */
    public function table_exists($table)  {
        $tables_array = ["alta_codigo_dvds","aviso_diario_recibido","compra_dvd","confirmacion_entrega_dvd",
                         "consumo_diario","ubicacion_entrega_dvd","usuario_clasificados"];
        return (in_array ($table, $tables_array)) ? true : false;
    }
    /**
     * @param $table
     * @return array|bool
     */
    public function info_schema_table($table)    {
        $sql =  "SELECT COLUMN_NAME, DATA_TYPE, IS_NULLABLE, COLUMN_DEFAULT ,
                 COLUMN_KEY  FROM INFORMATION_SCHEMA.COLUMNS  WHERE table_name = '$table'";
        return $this->list_execute_sql($sql);
    }
    /**
     * @param $table
     * @return string|bool
     */
    public function get_primary_key($table)    {
        $arreglo = $this->info_schema_table($table);
        $max = count($arreglo);
        for($i = 0; $i < $max; $i++)
            if($arreglo[$i]->COLUMN_KEY == "PRI")
                return $arreglo[$i]->COLUMN_NAME;
        return $arreglo[0]->COLUMN_NAME;
    }
    /**
     * @param $table
     * @return array
    */
    public function tabla_array_update($table)    {
        $arrupdate = array();
        $key = $this->get_primary_key($table);
        $array = $this->info_schema_table($table);		  $max = count($array);
        for($i = 0 ; $i < $max ; $i++){
            if ($array[$i]->COLUMN_NAME != $key)
                $arrupdate[] = 	$array[$i]->COLUMN_NAME;
        }
        return ($arrupdate);
    }
    /* Insertar los datos de una entidad de la BD funcion global */
    public function Insert ($tabla,$arr_atrib) {
        $atribs = "";
        if(count($arr_atrib)>0)   {
            foreach( $arr_atrib as $key => $value ) {
                $value = $this->cambia_caracter_extrannos($value);
                $atribs .= "+'".$value."',off";
            }
        }
        $atribs = str_replace(",off+", ",",$atribs);   $atribs = str_replace(",off", "",$atribs);
        $atribs = str_replace("+", "",$atribs);
        $sql = "INSERT INTO $tabla VALUES (NULL,$atribs);";
        $this -> conexion -> query ($sql) or die ("Error $sql");
        if(($this -> conexion -> affected_rows > 0)){
            return $this -> conexion->insert_id;
        }
        return -1;
    }
    //modificar los datos de una entidad de la BD funcion global
    public function Update($tabla,$id,$arrayCampos,$arrayValores)
    {
        $atribs = "";  $max = count($arrayCampos);
        for($i = 0 ;$i < $max ; $i++)	 {
            $atribs .= "+".$arrayCampos[$i]."='".$arrayValores[$i]."',off";
        }
        $atribs = str_replace(",off+", ",",$atribs);   $atribs = str_replace(",off", "",$atribs);
        $atribs = str_replace("+", "",$atribs);
        $sql = "UPDATE $tabla SET $atribs  WHERE id = '$id' ;";
        return $this->execute_query($sql);
    }
    //eliminar los datos de una entidad de la BD funcion global
    public function Delete ($tabla,$id)    {
       $sql = "DELETE FROM $tabla WHERE id = '$id' ;";
       return $this->execute_query($sql);
    }


}
?>