 <?php 
$conecta = mysql_connect("localhost", "root", "admin") or print (mysql_error()); 
mysql_select_db("db_Cotidiano", $conecta) or print(mysql_error()); 
$sql = "SELECT Posicao FROM tabela_posicao"; 
$result = mysql_query($sql, $conecta); 
 

/* Escreve resultados até que não haja mais linhas na tabela */ 
 
while($row = mysql_fetch_array($result)) { 
   	/*$data = $row[0];*/
	$out[]=$row;
} 

print(json_encode($out));
mysql_close($conecta); 
?>


