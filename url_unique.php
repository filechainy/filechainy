<?php

$category = "default";
$folder = "categories";
$description = "";

if (isset($_GET['URL'])) {
    $url = $_GET['URL'];
}


if (isset($_GET['url'])) {
    $url = $_GET['url'];
}

if (isset($_GET['link'])) {
    $url = $_GET['link'];
}

if (isset($_GET['name'])) {
    $name = $_GET['name'];
} else {
    $name = Sha1($url);
}

if (isset($_GET['category'])) {
    $category = $_GET['category'];
}

if (isset($_GET['cat'])) {
    $category = $_GET['cat'];
}

if(!is_dir("$folder/$category")){

    mkdir("$folder/$category");

}

if (isset($_GET['description'])) {
    $description = $_GET['description'];
}

if (isset($_GET['desc'])) {
    $description = $_GET['desc'];
}

// Avoid create file in the root directory and PHP files
$name = str_replace('.', "_", $name);
$description = str_replace('.', "_", $description);
$category = str_replace('.', "", $category);

if(!file_exists("$folder/$category/$name")){
    $file = fopen("$folder/$category/$name", "a");
    fwrite($file, $url);
    fclose($file); 
    echo "URL inserted with success!";
} else {
    echo "File already exists!";

}

if ($description){
if(!file_exists("$folder/$category/$name.des")){
    $file = fopen("$folder/$category/$name.des", "a");
    fwrite($file, $description);
    fclose($file); 
} else {
    echo "Description already exists!";

}
}

?>