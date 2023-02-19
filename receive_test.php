<?php

$hash = $_GET['hash'];
$contents = $_GET['contents'];

// Without a 'hash' the file is not processed as base64
if(!$hash && $contents){

    $hash = sha1($contents);

} else {

    $contents = str_replace(' ', "+", $contents);
    $hash = str_replace('.', "", $hash);

}

if(!file_exists("hash_test/$hash")){

    $file = fopen("hash_test/$hash", "w");
    fwrite($file, $contents);
    fclose($file); 
}

?>