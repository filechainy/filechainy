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

<html>
  <head>
    <title>
      Filechainy
    </title>
    <link rel="icon" href="img/logo_1.png">
  </head>

  <style type="text/css"> 

    .top{
      position: absolute;
      top: 0px;
      left: 0px;
      background-color: #444;
    }

    h1{
      color: #00FF55;
    }
  
    body{
      font-family: Arial;
      letter-spacing: 2px;
      color: #666666;
      background-image: url("background-image/simple/1.jpg");
      background-size: 100%;
    }

    .present{
      letter-spacing: 4px;
      font-size: 54px;
      font-family: Verdana;
      color: #FFFFFF;
    }

    .present2{
      letter-spacing: 4px;
      font-size: 12px;
      font-family: Verdana;
      background-color: #fff;
      opacity: 0.66;
      padding: 4%;
    }

    .button{      
      padding: 20px;
      background-color: #666;
      border: 2px solid #000:
      border-radius: 15px 15px 15px 15px;
      letter-spacing: 4px;
      font-size: 12px;
      font-family: Verdana;
      color: #FFFFFF;
      text-decoration: none;
      
    }

    .button:hover{
      background-color: #999;
      color: #333; 
      border: 1px solid #AAA;
      text-decoration: none;

    }

    .bottom-right{
      background-color: #fff;
      opacity: 0.55;
      padding: 1%;
      border-radius: 5px 5px 5px 5px;    
    }

    a{
      color: #666666; 
      text-decoration: none;
    }

    a:hover{
      color: #777777; 
      text-decoration: underline;
    }

    input{
    padding: 10px;
    }
    
  </style>

  <body>

      <table width='100%' class='top'>
        <tr>
          <td>
           &nbsp;
           <a href='index.php'>Home</a> &nbsp;&nbsp;
           <a href='doc.html'>documentation</a> | <a href='about.html'>about</a> 
            &nbsp;&nbsp;
           <a href='upload.php' target='_blank'><img src='fatcow/add.png' alt='upload' height='20px'></a>&nbsp;
           <a href='files.php' target='_blank'><img src='fatcow/folder.png' alt='filest' height='20px'></a>&nbsp;           
           <a href='urls_lists.php' target='_blank'><img src='fatcow/add_on.png' alt='convert' height='20px'></a>&nbsp;
           <a href='zip_subdirectories.php' target='_blank'><img src='fatcow/box_closed.png' alt='hashes' height='20px'></a>&nbsp;
           <a href='groups.php' target='_blank'><img src='fatcow/mail_red.png' alt='groups' height='20px'></a>&nbsp;    
          </td>
          <td>
            <div align='right'> 
               Freelance :                                         
               <a href='https://www.linkedin.com/in/arthur-sacramento-a55003230' target='_blank'>Linkedin</a> |
               <a href='https://wa.me/5591983608861' target='_blank'>Whatsapp</a> |
               <a href='#' OnClick="alert('filechainy@gmail.com');">E-mail</a> |
               <a href='https://www.paypal.com/donate/?hosted_button_id=WMM623TBQECZC' target='_blank'>Donation</a> : <a href='#' OnClick="alert('filechainy@gmail.com');">Pix</a> | 
               <a href='#' OnClick="alert('filechainy@gmail.com');">Become sponsor</a> 
               &nbsp;
            </div>
          </td>
        </tr>
      </table>

    <br><br><br><br>        

    <div align='center'>
     
      <span class='present'>
        <i> Filechainy</i>
      </span>
    <br><br><br>   
    <form action="search.php" method="POST">       
              <input type="text" placeholder="Name" name="search">    
              <input type="text" placeholder="Category" name="category">   
              <input type="submit" value="Search" />               
            </form>

      <table width='80%' style='text-justify: justify;' class='present2'>
        <tr>
          <td>
            <div style='text-align: justify;'>
            </br>

> <a href='urls_lists.php'>Insert URLs</a><br><br>

> <a href='upload.php'>Upload files</a><br><br>

> <a href='groups.php'>Send a message to a group</a><br><br>

> <a href='url_unique.php'>Insert a link (via GET <i>url, name, category</i> and <i>description</i>) [<a href='urls_categories.php' target='_blank'> view </a>] </i></a><br><br>

> <a href='url.php'>Create a list of URLs</a> (using GET <i>url</i> and <i>name</i>)  [<a href='urls_categories_txt.php' target='_blank'> view </a>] </i></a><br><br>

> <a href='request_send.php'>Send links to URLs</a><br></br>

              <div align='right'>
                <a href='about.html'><u>Learn more</u></a>
              </div>
            </div>
          </td>
        </tr>
      </table>

      <br><br><br><br> 

      <a href='filechainy1.0.1.zip' target='_blank' class='button'>&nbsp; Download &nbsp;</a>
   
      <br><br><br>
      <div align='right'>
        <i class='bottom-right'>2022 - filechainy</i>
      </div>
      <br>
    </div>
  </body>
</htlml>
