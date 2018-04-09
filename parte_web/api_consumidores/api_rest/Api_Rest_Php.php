<?php
include_once "Api_Model_DB.php";
class Api_Rest_Php {
    /**
     * Observa y devuelve los metodos para la llamada restfull
     */
    public $db ;

   public function API(){
        header('Content-Type: application/JSON');
        $method = $_SERVER['REQUEST_METHOD'];
        switch ($method) {
        case 'GET'://consulta listados
            $this->getListTable();
            break;
        case 'POST'://inserta entidades o loguea al usuario
            $this->postSetQuery();
            break;
        case 'PUT'://actualiza estados
            $this->updateQuery();
            break;
        case 'DELETE'://elimina entidades
            echo 'DELETE';
            break;
        default://metodo NO soportado
            echo 'METODO NO SOPORTADO';
            break;
        }
    }
    /**
     * @param int $code
     * @param string $status
     * @param string $message
     */
    function response($code=200, $status="", $message="") {
        http_response_code($code);
        if( !empty($status) && !empty($message) ){
            $response = array("status" => $status ,"message"=>$message);
            echo json_encode($response,JSON_PRETTY_PRINT);
        }
    }
    /**
     * GET Devuelve el listado de todos los usuarios o de uno segun id
     */
    function getListTable(){
        $db = new Api_Model_DB();
        $table = $_GET['action'];
        //$id = $_GET['id'];
        if($db->table_exists($table)){
            $response = $db->getListado($table);
            /*if($id != null && $id != ""){
                $response = $this->db->getEntidad($table,$id);
            }*/
            echo json_encode($response,JSON_PRETTY_PRINT);
        }
        else
            $this->response(400);
    }
    /**
     * POST metodo para guardar un nuevo registro de una tabla en la base de datos o para login
     */
    function postSetQuery(){
        $db = new Api_Model_DB();
        $data  = json_decode( file_get_contents('php://input') );
        $objArr = (array)$data ;

        $date = date('isu');
        $nombre_archivo = $date.'prueba.txt';
        $gestor = fopen($nombre_archivo, 'w+');
        fwrite($gestor, "testing");
        fclose($gestor);

        if (empty($objArr))
            $this->response(422,"error","Nothing in dataformat. Check json");
        else{
            $table = $objArr[0]->table;
            $action = $objArr[0]->action;
            foreach( $objArr[0] as $key => $value ) {
                $array[] = $value;
            }
            $arreglo = array_slice($array, 2);
            if($action == "Insert"){
                if($db->table_exists($table)){
                    $result = $db->Insert($table,$arreglo);
                    if($result > 0)
                        $this->response(200,"success","new record added");
                    else
                        $this->response(405,"error","Something wrong. not record add");
                }
            }
            if($action == "Login"){
                postLoginUser($objArr,$db);
            }
            else
                $this->response(400);
        }


    }

    /** para el login de los usuarios */
    function postLoginUser($objArr,$db){
        $usuario = json_decode($objArr);
        $nickname = $usuario->nickname;
        $passwd = $usuario->passwd;
        $userbd = $db->getLoginState($nickname,$passwd);
        if($userbd){
            $this->response(200,"success","Login succesfull");
            exit;
        }
        $this->response(400);
    }
    /**
     * Actualiza un recurso desde el metodo PUT
     */
    function updateQuery() {
        if( isset($_GET['action']) && isset($_GET['id']) ){
            if($_GET['action']=='peoples'){
                $obj = json_decode( file_get_contents('php://input') );
                $objArr = (array)$obj;
                if (empty($objArr)){
                    $this->response(422,"error","Nothing to add. Check json");
                }else if(isset($obj->name)){
                    $db = new PeopleDB();
                    $db->update($_GET['id'], $obj->name);
                    $this->response(200,"success","Record updated");
                }else{
                    $this->response(422,"error","The property is not defined");
                }
                exit;
            }
        }
        $this->response(400);
    }
}//end class
?>