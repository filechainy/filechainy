<?php

// The address of your site
$host = "http://localhost/filechainy/";

$get_received = 0;

/*/

    This first part of the script allows the user to insert data via GET request instead load the page.

    - If insert the id, hashid, userid or urlid will retrieve the path of a file with the respective number inserted
    - If insert the hash and contents variable will write a file in the 'hash' folder
    - If entry the 'url' variable will write the 'url' folder with a SHA1 hash name


/*/

// If the user enter a available GET variable a file path will be shown at a time instead the normal page contents (e.g: http://test.com/index.php?id=0)
  
if (isset($_GET['id'])) {
    $folder = "hash";   
    $id = $_GET['id'];
    $get_received = 1;
}

if (isset($_GET['hashid'])) {
    $folder = "hash";
    $id = $_GET['hashid'];
    $get_received = 1;
}

if (isset($_GET['userid'])) {
    $folder = "user";
    $id = $_GET['userid'];
    $get_received = 1;
}

if (isset($_GET['urlid'])) {
    $folder = "url";
    $id = $_GET['urlid'];
    $get_received = 1;
}

if (isset($_GET['fileid'])) {
    $folder = "files";
    $id = $_GET['fileid'];
    $get_received = 1;
}

// List the files of a folder by ID

if($get_received){
     
    $c = 0;

    foreach (glob("$folder/*") as $file){


        if ($c == $id){

            echo "$host$file";

        }

    $c++;

    }

    die;
}

if (isset($_GET['user'])){
    $user = $_GET['user'];
} else {
    $user = "";
}

// Write a file in 'hash' folder

if (isset($_GET['hash']) && isset($_GET['contents'])) {

    $hash = $_GET['hash'];
    $contents = $_GET['contents'];

    $contents = str_replace(' ', "+", $contents);
    $hash = str_replace('.', "", $hash);

    if(!file_exists("hash/$hash")){

        $file = fopen("hash/$hash", "w");
        fwrite($file, $contents);
        fclose($file); 

        echo "Received.";

    } else {
   
        echo "Hash already exists!";

    }

    if(!file_exists("user/$hash")){

        $file = fopen("user/$hash", "w");
        fwrite($file, $user);
        fclose($file); 
    }

    die;

}

// Write a file in 'url' folder

if (isset($_GET['url'])){

    $url = $_GET['url'];

    $url_hashcode = sha1($url);

    if(!file_exists("url/$url_hashcode")){

        $file = fopen("url/$url_hashcode", "w");
        fwrite($file, $url);
        fclose($file); 

        echo "Received.";

    } else {
   
        echo "URL already exists!";

    }

}


?>