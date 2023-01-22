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

    <?php

    $style = 1;

    // For use a random image in background 

    if ($style){

        $folder = 'simple';
        $limit = 2;

        $random_background = rand(0, $limit);
        $background_image = "background-image/$folder/$random_background.jpg";
        $present_color = "#FFFFFF";

    } else {
  
        $background_image = "background-image/$folder/$random_background.jpg";
        $present_color = "#333";
    }
  
    ?>

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
      background-image: url("<?php echo $background_image; ?>");

      background-size: 100%;
    }

    .present{
      letter-spacing: 4px;
      font-size: 54px;
      font-family: Verdana;
      color: <?php echo $present_color; ?>;
    }

    .present2{
      letter-spacing: 4px;
      font-size: 12px;
      font-family: Verdana;
      background-color: #fff;
      opacity: 0.66;
      padding: 4%;
    }

    .buttonBuy{      
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

    .buttonBuy:hover{
      background-color: #999;
      color: #333; 
      border: 1px solid #AAA;

    }

    .bottom-right{
      position: absolute;
      bottom: 10px;
      right: 10px;
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
      text-decoration: none;

    }
    
  </style>

  <body>

      <table width='100%' class='top'>
        <tr>
          <td>
           &nbsp;
           <a href='index_pt.php'>PT</a> |  <a href='index.php'>ENG</a> &nbsp;&nbsp;
           <a href='doc.html'>documentation</a> | <a href='about.html'>about</a>
 
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           <a href='upload.php' target='_blank'><img src='fatcow/add.png' alt='upload' height='20px'></a>&nbsp;
           <a href='filelist.php' target='_blank'><img src='fatcow/folder.png' alt='filest' height='20px'></a>&nbsp;
           <a href='groups.php' target='_blank'><img src='fatcow/mail_red.png' alt='groups' height='20px'></a>&nbsp;
           <a href='convert.php' target='_blank'><img src='fatcow/add_on.png' alt='convert' height='20px'></a>&nbsp;
           <a href='sourcecode.zip' target='_blank'><img src='fatcow/tux.png' alt='sourcecode' height='20px'></a>&nbsp;
           <a href='zip_hash.php' target='_blank'><img src='fatcow/box_closed.png' alt='hashes' height='20px'></a>&nbsp;
          </td>
          <td>
            <div align='right'>
              <a href='#'><img src='logos/pix_1.png' OnClick="alert('filechainy@gmail.com');" alt='pix' height='25px'></a>
              <a href='https://twitter.com/filechainy' target='_blank'><img src='logos/twitter_1.png' alt='twitter' height='25px'></a>
              <a href='http://t.me/filechainy' target='_blank'><img src='logos/telegram_1.png' alt='telegram' height='25px'></a>
              <a href='https://discord.gg/XqHb9veUvb' target='_blank'><img src='logos/discord_1.png' alt='discord' height='22px'></a>
              <a href='https://www.instagram.com/filechainy/' target='_blank'><img src='logos/instagram_1.png' alt='instagram' height='25px'></a>     
              <a href='https://www.reddit.com/user/filechainy' target='_blank'><img src='logos/reddit_1.png' alt='reddit' height='25px'></a>                    
              <a href='https://github.com/filechainy/filechainy' target='_blank'><img src='logos/github_1.png' alt='github' height='25px'></a>              
              <a href='https://wa.me/5591983608861' target='_blank'><img src='logos/whatsapp_1.png' alt='whatsapp' height='25px'></a>
                
            </div>
          </td>
        </tr>
      </table>

    <br><br><br><br>

    <div align='center'>
     
      <span class='present'>
        Earn transferring files
      </span><br><br><br>

      <table width='80%' style='text-justify: justify;' class='present2'>
        <tr>
          <td>
            <div style='text-align: justify;'>
              Filechainy is the project of a descentralized storage system with rewards.
              Filechainy provides a simple way for transfer files using SHA1 and base64 enconde.
              The files are splitted and each chunk receive a SHA1 name.
              This design provides a simple and eficient way for transfer and check file validity. <a href='about.html'><u>Learn more</u></a>
            </div>
          </td>
        </tr>
      </table>

      <br><br><br><br> 

      <a href='https://www.paypal.com/cgi-bin/webscr?cmd=_xclick&business=aerae4%40gmail%2ecom&lc=US&item_name=Registration%20fee&item_number=2&amount=15%2e00&currency_code=USD&button_subtype=services&no_note=0&bn=PP%2dBuyNowBF%3abtn_buynowCC_LG%2egif%3aNonHostedGuest' target='_blank' class='buttonBuy'>Application fee</a>
   
      <br><br><br><br><br><br>

      <i class='bottom-right'>2022 - filechainy</i>
    </div>
  </body>
</htlml>
