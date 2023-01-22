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
    }

    .present{
      letter-spacing: 4px;
      font-size: 54px;
      font-family: Verdana;
    }

    .present2{
      letter-spacing: 4px;
      font-size: 12px;
      font-family: Verdana;
    }

    a{
      letter-spacing: 4px;
      font-size: 12px;
      font-family: Verdana;
      color: #666666;
    }

    u{
      position: absolute;
      right: 20%;
    }


    .button{
      padding: 20px;
      background-color: #AAA;
      border: 2px solid #000:
      border-radius: 15px 15px 15px 15px;
      letter-spacing: 4px;
      font-size: 12px;
      font-family: Verdana;
      color: #FFFFFF;
      text-decoration: none;      
    }

    .button:hover{
      background-color: #666;
    }
   
    input{
    padding: 10px;
    }

  </style>

  <body>
    <table width='100%' class='top'>
      <tr>
        <td>
          <br>
        </td>
      </tr>
    </table>

    <br><br><br><br>

    <div align='center'>
    
      <span class='present'>
        Files
      </span>

      <table width='80%' class='present2'>
        <tr>
          <td>
            <div> 
              <h1>Files</h1> <br>                        

<?php

$start = 0;

if(isset($_GET['start'])) {
    $start = $_GET['start'];  
}

$c = 0;
$limit = 100;
$ini = $start *  $limit;
$end = $ini + $limit;

$slash_break = 1;

$entry = 0;
$collum = 0;
$search_break = 0;

foreach (glob("files/*") as $picture){
    

        if ($c >= $ini and $c < $end){

            $thumb_dir = '';
            $file_ext = '';
            $item = '';

            $item = explode('/', $picture);
            $item = $item[$slash_break];

            $file_ext = substr($item, -3);
            $file_ext = strtolower($file_ext);

            if($file_ext == 'jpg' or $file_ext == 'png' or $file_ext == 'gif' or $file_ext == 'peg'){

                $thumb_dir = $picture;
            } 

            if(!file_exists("$thumb_dir") && $thumb_dir != $picture){

                $thumb_dir = 'img/nopic.jpg';

            }        

           echo "<a href='$picture' target='_blank'>$item</a> <a href='comment.php?comment_file=$picture' target='_blank'><u>Comment</u></a><br><br>";
      
           $collum++;  

           $search_break++;         
           
       }    

    $c++;

    if($search_break == $limit){break;}

}


?>
           </div>
          </td>
        </tr>
      </table>
<?php



echo "<div align='center'></br></br></br></br>";

if ($start < 19){

    for ($i = 0; $i < 20; $i++){
     
        echo "<a href='filelist.php?start=$i'>$i</a> ";
    }

}else{

    for ($i = $start; $i < $start+ 20; $i++){
     
        echo "<a href='filelist.php?start=$i'>$i</a> ";
    }

} 

?>

      <br><br><br><br>
 
      <a href='index.php' class='button'>&nbsp; Back &nbsp;</a>

      <br><br><br><br>
      <i>2022 - filechainy</i>
    </div>
  </body>
</htlml>