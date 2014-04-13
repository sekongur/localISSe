<?php
session_start();
$relative = ".";
require $relative."./functions/func_db.php";
db_connect();

$result = array();
$method = $_SERVER['REQUEST_METHOD'];

  function parseParams() {
    $params = array();
    $method = $_SERVER['REQUEST_METHOD'];
    if ($method == "PUT" || $method == "DELETE") {
        parse_str(file_get_contents('php://input'), $params);
        $GLOBALS["_{$method}"] = $params;
        // Add these request vars into _REQUEST, mimicing default behavior, PUT/DELETE will override existing COOKIE/GET vars
        $_REQUEST = $params + $_REQUEST;
    } else if ($method == "GET") {
        $params = $_GET;
    } else if ($method == "POST") {
        $params = $_POST;
    }
    return $params;
  }


$params = parseParams();


if($method == 'PUT'){

  // Si el usuario no existe lo creamos
  $sql = mysql_query("SELECT * FROM nasa_usuarios WHERE nusu_phoneid='".$params["id"]."' AND nusu_id > 0");
  if(mysql_num_rows($sql) == 0){
    if(!empty($params["location"])){
      $sql = mysql_query("INSERT INTO nasa_usuarios (nusu_phoneid) values ('".$params["id"]."')");
    }else{
      $result[] = array("status" => "KO", "error" => array("code" => "0004", "text" => "Identificador de usuario no valido"));
      echo json_encode($result[0]);
      return;
    }
  }

  $sql = mysql_query("UPDATE nasa_usuarios SET nusu_location=".$params["location"]." WHERE nusu_phoneid='".$params["id"]."' ");

  if($sql){
    $result[] = array("status" => "OK");
  }else{
    $result[] = array("status" => "KO", "error" => array("code" => "0002", "text" => "No ha sido posible actualizar la posicion"));
  }
}else if($method == 'DELETE'){
  
  $result[] = array("status" => "KO", "error" => array("code" => "0001", "text" => "Esta accion no esta especificada"));

}else if($method == 'GET'){

  $result[] = array("status" => "KO", "error" => array("code" => "0001", "text" => "Esta accion no esta especificada"));

}else if($method == 'POST'){
  $usuarios = array();
  $sql = mysql_query("SELECT nusu_phoneid id, nusu_nombre nombre, nusu_posx/10000 posx, nusu_posy/10000 posy, nusu_location location FROM nasa_usuarios WHERE nusu_id>0");
  while($datos = mysql_fetch_array($sql)){
    $usuarios[] = array("id" => $datos['id'], "nombre" => $datos['nombre'], "location" => $datos['location']);
  }
  $result[] = array("usuarios" => $usuarios);
}

echo json_encode($result[0]);

?>