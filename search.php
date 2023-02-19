<?php error_reporting(0); ?>

<html>
  <head>
    <title>
      Home
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
      padding-left: 20px;
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
    padding: 5px;

    }

    input{
    padding: 10px;
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
    
  </style>

  <body>
    <table width='100%' class='top'>
      <tr>
        <td>
          <br>
        </td>
      </tr>
    </table>
<?php

$search = $_POST['search'];
$category = $_POST['category']; 

if (!$search){$search_placeholder = "Name";}
if (!$category){$category_placeholder = "Category";}

?>
           <br><br>
           <h1>Search</h1>
            <form action="search.php" method="POST">       
              <input type="text" placeholder="Name" name="search">    
              <input type="text" placeholder="Category" name="category">   
              <input type="submit" value="Search" />               
            </form>

      <?php echo $lbl_option_get; ?> /

      <a href='categories.php'><?php echo $lbl_u_categories; ?></a>      

    <br><br>

<?php

if(!file_exists("categories")){

    mkdir("categories");
}

if(!file_exists("categories/thumbs")){

    mkdir("categories/thumbs");
}

if(!file_exists("categories/files")){

    mkdir("categories/files");
}

$start = $_GET['start'];  

if ($category == ""){$category = $_GET['category'];}
	
if (!$start){$start = 0;}

$c = 0;
$limit = 20;
$ini = $start *  $limit;
$end = $ini + $limit;

$entry = 0;

$search = strtolower($search);
$search = str_replace(" ", "_", $search); 

$slash_break = 2;

if ($search == ""){$search = $_GET['search'];}

if ($category == "" && $search != ""){

    foreach (glob("categories/*") as $files_path){

        foreach (glob("$files_path/*") as $picture){


            $item = explode('/', $picture);

            $picture_lowercase = strtolower($item[$slash_break]);

            $name = str_replace("$search", "", "$picture_lowercase");
            $name_len = strlen($name);
            $entry_len = strlen($item[$slash_break]);

            if ($entry_len > $name_len){

                if($entry >= $ini and $entry  < $end){

                    $item = $item[$slash_break];

                    $sha1_msg = sha1($item);

                    echo "<a href='comment.php?comment_file=$sha1_msg'>[comment]</a> <a href='$picture' target='_blank'>$item</a><br>";
                    $search_break++; 
                }
 
            $entry++;
            if($search_break == $limit){break;}
            }

        $c++;
        }
   
    }

    if ($entry == 0){echo "<br><br><div align='center'><h1>Not found!</h1></div>";}
}

if ($category != "" && $search != ""){

    $slash_break = 1;

    foreach (glob("$files_path/*") as $picture){


        $item = explode('/', $picture);

        $picture_lowercase = strtolower($item[$slash_break]);

        $name = str_replace("$search", "", "$picture_lowercase");
        $name_len = strlen($name);
        $entry_len = strlen($item[$slash_break]);

        if ($entry_len > $name_len){

            if($entry >= $ini and $entry  < $end){

                $item = $item[$slash_break];

                $sha1_msg = sha1($item);

                echo "<a href='comment.php?comment_file=$sha1_msg'>[comment]</a> <a href='$picture' target='_blank'>$item</a><br>";
                $search_break++; 
            }

        $entry++;
        if($search_break == $limit){break;}
        }

    $c++;
    }

if ($entry == 0){echo "<br><br><div align='center'><h1>Not found!</h1></div>";}   
}


if ($category == "" && $search == ""){

    $slash_break = 2;

    foreach (glob("categories/*") as $files_path){

        foreach (glob("$files_path/*") as $picture){

            if ($c >= $ini and $c < $end){
 
                $thumb_dir = '';

                $item = explode('/', $picture);
                $item = $item[$slash_break];

                $file_ext = substr($item, -3);
                $file_ext = strtolower($file_ext);   

                $sha1_msg = sha1($item);

                echo "<a href='comment.php?comment_file=$sha1_msg'>[comment]</a> <a href='$picture' target='_blank'>$item</a><br>";
                $search_break++;           
            }

        $c++;
        if($search_break == $limit){break;}
        }
    }

}

if ($category != "" && $search == ""){

    $slash_break = 2;

    foreach (glob("categories/$category/*") as $picture){

        if ($c >= $ini and $c < $end){
 
            $thumb_dir = '';

            $item = explode('/', $picture);
            $item = $item[$slash_break];

            $file_ext = substr($item, -3);
            $file_ext = strtolower($file_ext);   

            $sha1_msg = sha1($item);

            echo "<a href='comment.php?comment_file=$sha1_msg'>[comment]</a> <a href='$picture' target='_blank'>$item</a><br>";
            $search_break++;           
        }

    $c++;
    if($search_break == $limit){break;}
    }

}

echo "<div align='center'></br></br></br></br>";

if ($start < 19){

    for ($i = 0; $i < 20; $i++){
     
        echo "<a href='search.php?start=$i&search=$search&category=$category'>$i</a>";
    }

}else{

    for ($i = $start; $i < $start+ 20; $i++){
     
        echo "<a href='search.php?start=$i&search=$search&category=$category'>$i</a>";
    }

} 

echo "</div>";

?>
       <br><br><br>
  <div align='center'>
      <a href='index.php' class='button'>&nbsp; Back &nbsp;</a>  <br><br><br>
      <i>2022 - filechainy</i>
    </div>

  </body>
</html>