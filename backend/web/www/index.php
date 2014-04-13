<?php
header('Content-type: application/json');

session_start();
$relative = ".";
require $relative."./functions/func_db.php";
db_connect();

$url = $_REQUEST["_url"];
$params = explode('/', $url);
$accion = $params[0];
 
$uri = "http://ghomam.es/nasa/api-rest.php";

if($accion == "locations"){

    $ch = curl_init($uri);
    curl_setopt($ch, CURLOPT_POST, true);
    curl_setopt($ch, CURLOPT_POSTFIELDS, $postdata);
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'POST');
    $result = curl_exec($ch);
    curl_close($ch);
    
}else if ($accion == "user"){
    $postdata = "id=".$params[1]."&location=".$params[2];

    $ch = curl_init($uri);
    curl_setopt($ch, CURLOPT_POST, true);
    curl_setopt($ch, CURLOPT_POSTFIELDS, $postdata);
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, 'PUT');
    $result = curl_exec($ch);
    curl_close($ch);

}else{
  echo json_encode(array("status" => "KO", "error" => array("code" => "0003", "text" => "No existe este servicio")));
}

?>