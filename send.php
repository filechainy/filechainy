<?php

/*/

Send splits to a URL

/*/

$url_to_send = "http://localhost/test/receive.php";

$root_path = "hash";

foreach (glob("$root_path/*") as $f){



    $get_name = explode("/", $f);

    $name = $get_name[1];   

    $file = file_get_contents($f);

    $send = file_get_contents("$url_to_send?chunk=$name&contents=$file");

}

?>

