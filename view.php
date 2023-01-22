<?php

$folder = "hash";

$file = $_GET['hash'];

$chunks = file_get_contents("keys/$file");

$chunk = explode(",", $chunks);

$chunk_data = "";

for ($x = 0; ; $x++){

    if ($chunk[$x] == ""){break;}

    $chunk_data = $chunk_data . file_get_contents("$folder/$chunk[$x]");
}

//Depending on the file type you must change the header
$header = 'data:image/jpg;base64;,';

echo "<img src='$header$chunk_data'>";

?>