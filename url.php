<?php

$name = "default";
$folder = "categories";
$category = "default";

if (isset($_GET['url'])) {
    $url = $_GET['url'];
}

if (isset($_GET['URL'])) {
    $url = $_GET['URL'];
}


if (isset($_GET['link'])) {
    $url = $_GET['link'];
}

if (isset($_GET['name'])) {
    $name = $_GET['name'];
}

if(!is_dir("$folder")){
    mkdir("$folder");
}

if(!is_dir("$folder/list")){
    mkdir("$folder/list");
}

if (isset($_GET['url'])) {
    // Avoid create file in the root directory and PHP files
    $name = str_replace('.', "", $name);

    $file = fopen("$folder/list/$name", "a");
    fwrite($file, "$url\n");
    fclose($file); 

    echo "URL inserted with success!";
}
?>