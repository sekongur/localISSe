<?
function db_connect() {
	$db_host = "";
	$db_user = "";
	$db_pass = "";
	$db_name = "";
	$cont = 0;
	$conectado = false;
	while((!$conectado)&&($cont<10)){
		if($db_link = mysql_connect($db_host, $db_user, $db_pass)){
			if(	$db_conn = mysql_select_db($db_name, $db_link)){
				$conectado=true;
			}
		}
		$cont ++;
	}			
	if(!$conectado) die("<CENTER>No ha sdo posible conectar con la base de datos <b>'$db_name'</b></CENTER>");
	return $db_link;
}
?>