<?php error_reporting(0); ?>

<html>
  <head>
    <style>

      body {
        font-family: Arial;
      }
		
      .main {
        background-color: #EEE;
        padding: 5px;        
        top: 0px;
        right: 0px;
      }         
      
      td {
        padding-left: 10px;     
      }
 
    </style>
  </head>

  <body>

    <table class='main' width='100%'>
      <tr>
        <td>
          <form action="search.php" method="POST">
            <input type="text" placeholder="Link" name="link">
            <input type="text" placeholder="Name" name="name">
            <input type="text" placeholder="Category" name="category">
            <input type="submit" name="submit">
            &nbsp; <a href='index.php'>Home</a>
            &nbsp; <a href='search.php'>Refresh</a>
          </form>
        </td>
        <td>
          <div align='right'>
            <form action="search.php" method="POST">
              <input type="text" placeholder="Search" name="search">
              <input type="submit" value="Search">    
            </form>
          </div>
        </td>
      </tr>
    </table>

<?php


$link = $_POST['link'];
$name = $_POST['name'];
$category = $_POST['category'];

// Avoid write files in not allowed directories
$name = str_replace('.', "", $name);
$category = str_replace('.', "", $category);

$no_symbol = array ('<', '>');

// Avoid write javascript in the files
$link = str_replace($no_symbol, "", $link);
$name = str_replace($no_symbol, "", $name);

// Get the last occurrence of '.' and the remaining text
$lastDotIndex = strrpos($link, ".");

if ($lastDotIndex) {

    $filetype = substr($link, $lastDotIndex + 1);

} else {

    $filetype = "others";

}

$filetype = str_replace('.', "", $filetype);
$filetype = strtolower($filetype);

if(!file_exists("categories")){
    mkdir("categories");
}

if(!file_exists("categories/$category")){
    mkdir("categories/$category");
}

if (isset($_POST['submit'])) {
    if(!file_exists("categories/$category/$name")){
        $file = fopen("categories/$category/$name", "w");
        fwrite($file, $link);
        fclose($file); 
        $result = "<br><a href='categories/$category/$name' target='_blank'>Success!</a>";

    } else {
        
        $result = "<br>File already exists!";
    }
}

$start = $_GET['start'];  
	
if (!$start){$start = 0;}

$search = $_POST['search'];

if ($search == ""){$search = $_GET['search'];}	

$c = 0;
$limit = 20;
$ini = $start *  $limit;
$end = $ini + $limit;

$entry = 0;

$search = strtolower($search);

$slash_break = 3;

echo "<hr>$result <table width='100%'>";

if ($search != "" ){

    $dir = 'categories';

    // Open the directory
    if ($handle = opendir($dir)) {

        // Loop through each subdirectory
        while (false !== ($subdir = readdir($handle))) {

            if ($subdir != "." && $subdir != ".." && is_dir($dir.'/'.$subdir)) {

                // Open the subdirectory
                if ($subhandle = opendir($dir.'/'.$subdir)) {

                    // Loop through each file in the subdirectory
                    while (false !== ($file = readdir($subhandle))) { 
                        
                        $filename_written = $file;  
 
                        // Find the occurrence in lower or uppercase                     
                        $file = strtolower($file);
                        
                        // Check if the filename contains the string 
                        if (strpos($file, $search) !== false) {

                            // Pagination
                            if($entry >= $ini and $entry  < $end){

                                // Display the filename
                                //echo $dir.'/'.$subdir.'/'.$file . "<br>";

                                $td_color = $entry % 2 == 0 ? '#EEE' : '#FFF';

                                $file_path = $dir.'/'.$subdir.'/'.$file;

                                $contents = file_get_contents($file_path);

                                $lastDotIndex = strrpos($contents, ".");

                                if ($lastDotIndex) {

                                    $filetype = substr($contents, $lastDotIndex + 1);         
                                }

                                $filetype = strtolower($filetype);

                                echo "<tr style='background-color: $td_color;'><td><a href='$contents' target='_blank'>$filename_written</a></td><td>$subdir</td><td>$filetype</td><td><a href='comment.php?comment_file=$file' target='_blank'>Comment</a></td>";                  

                                if($filetype == "png" || $filetype == "jpg" || $filetype == "jpeg" || $filetype == "gif"){

                                   echo "<td><div align='center'><a href='$contents' target='_blank'><img src='$contents' width='184px'></a></div></td>";                   
                                }

                            
                           }

                        $entry++;
                        if($entry == $limit){break;}
                        }
                   
                    }

                // Close the subdirectory
                closedir($subhandle); 
                }
            
            }

        }
    // Close the directory
    closedir($handle);
    }

    if ($entry == 0){
        echo "<br>Not found.";
    }

} 

echo "</table>";

if (!$search && !$start && !$result){
  echo "Hello!";
} 

echo "<br><br><div align='center'>";

if ($entry){
    for ($i = $start; $i < $start + 20; $i++) {
        echo "<a href='search.php?start=$i&search=$search'>$i </a>";
    }
} 

echo "</div>";

?>