<?php
/**
* conversor.php
* Convierte una tabla CSV a una sentencia SQL. Muy flojo. No mueve datos.
* @author pello altadill
* @version 0.1
*/
if (!isset($argv[1])) {
	echo "Jambo, dame un argumento.";
}

$newfile = $argv[1];



$table = (isset($argv[2]))?$argv[2]:basename($argv[1],".csv");

$contenidos = array();
$i=0;

$SQLRESULT = "DROP TABLE `$table`;\n";
$SQLRESULT .= "CREATE TABLE `$table` ( \n";
//$SQLRESULT .= "  `id` int(11) NOT NULL AUTO_INCREMENT, \n";
	      
$FIELDS = array();
$FIELDS_LONG = array();

if (($handle = @fopen($newfile, "r"))) {

        $contenidos = fgets($handle, 4096);
        $l = split(";",$contenidos);
        for ($i=0;$i<count($l);$i++) {
        	$campo = limpiar($l[$i]);
        	//echo "--".$i ."-". $l[$i]." => " . $campo ." ,\n--";
		//$SQLRESULT .=  limpiar($l[$i]) . " text  COLLATE utf8_unicode_ci NOT NULL, \n";
			$FIELDS[$campo] = "text COLLATE utf8_unicode_ci  NOT NULL";
			$FIELDS_LONG[$i] = 0;
		}

    while (!feof($handle)) {
        $contenidos = fgets($handle, 4096);
        $l = split(";",$contenidos);
        for ($i=0;$i<count($l);$i++) {
        	 $FIELDS_LONG[$i] = ($FIELDS_LONG[$i]< strlen($l[$i]))?strlen($l[$i]):$FIELDS_LONG[$i];
		  }
    }
    fclose($handle);
}

$i = 0;
foreach ($FIELDS as $field => $type ) {
		$SQLRESULT .= $field." VARCHAR(".($FIELDS_LONG[$i]+10)."),\n";
		$i++;
}

$SQLRESULT = rtrim($SQLRESULT,",\n");

//$SQLRESULT .= "  PRIMARY KEY (`id`) \n";
$SQLRESULT .="\n		) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;\n\n";


echo "----------- resulting SQL ------------------------------------\n";
echo $SQLRESULT;

	function limpiar($str)
	{
		$str = quitarAcentos($str);
		$str = strtoLower($str);
		$str = preg_replace('/[\s]+/','_',$str); 
		$str = preg_replace('/[\"]+/','',$str); 
		$str = preg_replace('/[\-]+/','_',$str); 
		$str = preg_replace('/[\/]+/','_',$str); 
		$str = preg_replace('/[\__]{2}/','_',$str); 
	return $str;
	}

	/*
	* quitarAcentos
	* gracias Thyng
	*/
	function quitarAcentos($str)   
	{    
		if(is_utf($str))   
		{        
			$str = utf8_decode($str);    
		}         
		 	$str = htmlentities($str);     
		 	$str = preg_replace('/&([a-zA-Z0-9])(uml|acute|grave|circ|tilde);/','$1',$str);     
		 	return html_entity_decode($str);   
	}    

   function is_utf ($t)
   {
       if ( @preg_match ('/.+/u', $t) )       
            return 1;
	}

?>
