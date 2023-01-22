<?php

/*/

Send chunks of files to multiple URLs

/*/


$urls = array("http://localhost/test/chunks_receive.php", "http://localhost/test/chunks_receive_test.php");

$root_path = "hash";

for ($x=0; $x < count($urls); $x++){

    foreach (glob("$root_path/*") as $f){



        $get_name = explode("/", $f);

        $name = $get_name[1];   

        $file = file_get_contents($f);

        $send = file_get_contents("$urls[$x]?chunk=$name&contents=$file");
    }
}

echo "done";

?>

