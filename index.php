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

// Write a file in 'categories' folder

if (isset($_GET['url'])){

    $url = $_GET['url'];

    if(!file_exists("categories/urls")){
        mkdir("categories/urls");
    }
 
    if (isset($_GET['name'])){ 

        $name = $_GET['name'];
        $no_symbol = array ('<', '>');
        $filtered_string = str_replace($no_symbol, "", $name);

    } else {
         // Replace all the non alphanumeric characters of a string by space
         $filtered_string = preg_replace('/[^a-zA-Z0-9]/', ' ', $url);
    }

    if(!file_exists("categories/urls/$filtered_string")){

        $file = fopen("categories/urls/$filtered_string", "w");
        fwrite($file, $url);
        fclose($file); 

        echo "Received.";

    } else {
   
        echo "URL already exists!";

    }

}

?>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <title>Filechainy</title>
    <link rel="icon" href="img/logo_1.png">
    <style>
    *{box-sizing:border-box}html{font-family:Arial, sans-serif}body{letter-spacing:2px;color:#DDD;background-image:url(background-image/simple/1.jpg);background-size:100%}header{display:flex;justify-content:space-between;align-items:center;padding:1rem}nav ul{list-style:none;display:flex;justify-content:space-between}nav li{margin:0 1rem}nav a{color:#DDD;text-decoration:none}nav a:hover{text-decoration:underline}main{display:flex;flex-direction:column;justify-content:center;align-items:center;text-align:center}form{display:flex;flex-direction:column}form label{margin-bottom:0.5rem}form input{padding:10px; width: 400px;}form button{padding:20px;background-color:#666;letter-spacing:4px;font-size:12px;font-family:Verdana;color:#fff;text-decoration:none}form button:hover{background-color:#999;color:#333;}section.info{color: #333;letter-spacing:4px;font-size:12px;font-family:Verdana;background-color:#fff;opacity:0.66;padding:4%}section.info a{color:#666}section.info a:hover{color:#777;text-decoration:underline}
    </style>
  </head>
  <body>  
    <header>
      <h1>Filechainy</h1>
      <nav>
        <ul>
          <li><a href="#">Home</a></li>
          <li><a href="filechainy1.0.2.1.zip">Sourcecode</a></li>
        </ul>
      </nav>
    </header>
    <main>
      <br><br>
      <section class="search">
        <form action="search.php" method="POST">      
          <input type="text" id="search" name="search">   
          <button type="submit">Search</button>   
        </form>
      </section>      
      <br>
      <section class="info">
     Filechainy is a simple project </a><br>
     for search without MySQL. <a href='about.html'>Learn more</a>       
      </section>
    </main>
    <footer align='center'>
      <p>Filechainy - 2022</p>
    </footer>
  </body>
</html>
