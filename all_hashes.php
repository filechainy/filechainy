<?php

// Insert the complete hostname for facilitate file download

$hostname = "http://localhost/filechainy/";
$folder = "hash";
$c = 0;

foreach (glob("$folder/*") as $file){


    echo "<a href='$hostname$file' target='_blank'>$hostname$file</a><br>";
    $c++;

}

?>