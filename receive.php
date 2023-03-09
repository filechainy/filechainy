<?php

$hash = $_GET['hash'];
$contents = $_GET['contents'];
$user = $_GET['user'];
$file = $_GET['file'];
$url = $_GET['url'];
$data = $_GET['data'];

$folder = 'categories';

if ($file){
    $contents = $file;
    $folder = 'files_urls';
}

if ($url){
    $contents = $url;
    $folder = 'urls';
}

if ($data){
    $contents = $data;
    $folder = 'urls';
}

// Without a 'hash' the file is not processed as base64
if(!$hash && $contents){

    $hash = sha1($contents);

} else {

    $contents = str_replace(' ', "+", $contents);
    $hash = str_replace('.', "", $hash);

}

if(!file_exists("$folder/$hash")){

    $file = fopen("$folder/$hash", "w");
    fwrite($file, $contents);
    fclose($file); 
}

if(!file_exists("user/$hash")){

    $file = fopen("user/$hash", "w");
    fwrite($file, $user);
    fclose($file); 
}

?>