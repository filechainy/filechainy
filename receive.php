<?php

$hash = $_GET['hash'];
$contents = $_GET['contents'];
$user = $_GET['user'];

$contents = str_replace(' ', "+", $contents);
$hash = str_replace('.', "", $hash);

if(!file_exists("hash/$hash")){

    $file = fopen("hash/$hash", "w");
    fwrite($file, $contents);
    fclose($file); 
}

if(!file_exists("user/$hash")){

    $file = fopen("user/$hash", "w");
    fwrite($file, $user);
    fclose($file); 
}

?>