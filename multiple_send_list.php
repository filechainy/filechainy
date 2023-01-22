<?php

/*/

Send chunks of files to multiple URLs

/*/


$urls = file_get_contents("urls.txt");

$urls = explode("\r\n", $urls);

$root_path = "hash";

for ($x=0; ; $x++){
 
    if ($urls[$x] == ""){break;}

    foreach (glob("$root_path/*") as $f){


        
        $get_name = explode("/", $f);

        $name = $get_name[2];   

        $file = file_get_contents($f);

        $send = file_get_contents("$urls[$x]?chunk=$name&contents=$file");
    }
}

?>

