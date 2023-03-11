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
            <input type="text" placeholder="Insert a link or URL" name="link">
            <input type="text" placeholder="Name" name="name">
            <input type="text" placeholder="Category" name="category">
            <input type="submit" name="submit">
            &nbsp; <a href='index.php'>Home</a>
            &nbsp; <a href='search_op.php'>Search by category</a>
            &nbsp; <a href='zip_full.php' target='_blank'>Download</a>
            &nbsp; <a href='upload.php' target='_blank'>Upload</a>
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

if(!$name){
    $name = preg_replace('/[^a-zA-Z0-9]/', ' ', $link);
}

if(!$category){
    $category = "others";
}

// Avoid write files in not allowed directories
$name = str_replace('.', "", $name);
$category = str_replace('.', "", $category);

$category = strtolower($category);

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
        $result = "<br><a href='categories/$category/$name' target='_blank'>Sent with success!</a>";

    } else {
        
        $result = "<br>File already exists!";
    }
}

$start = $_GET['start'];  
	
if (!$start){$start = 0;}

$search = $_POST['search'];

if ($search == ""){$search = $_GET['search'];}	

// Avoid accessing the above directories
$search = str_replace('.', "", $search);

$c = 0;
$limit = 20;
$ini = $start *  $limit;
$end = $ini + $limit;

$entry = 0;

$search = strtolower($search);

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
                        $file_l = strtolower($file);

                        //if ($subdir == $search){echo 'ok';}
                        
                        // Check if the filename contains the string 
                        // If there is a category with the searched name all the files within that category will be displayed
                        if (strpos($file_l, $search) !== false || $subdir == $search) {

                            // Pagination
                            if($entry >= $ini and $entry  < $end){
                                
                                if ($file == "." || $file == ".."){continue;}

                                // Display the filename
                                //echo $dir.'/'.$subdir.'/'.$file . "<br>";

                                $td_color = $entry % 2 == 0 ? '#EEE' : '#FFF';

                                $file_path = $dir.'/'.$subdir.'/'.$file;

                                $filesize = filesize($file_path);

                                // Checks if is a binary file or text content
                                if($filesize > 500){

                                    $contents = $file_path;

                                } else {

                                    $contents = file_get_contents($file_path);

                                }

                                $lastDotIndex = strrpos($contents, ".");

                                if ($lastDotIndex) {

                                    $filetype = substr($contents, $lastDotIndex + 1);         
                                }

                                $filetype = strtolower($filetype);

                                // Don't show characters or variables after the file extension
                                $filetype = substr($filetype, 0, 3);

                                echo "<tr style='background-color: $td_color;'><td><a href='$contents' target='_blank'>$filename_written</a></td><td>$subdir</td><td>$filetype</td><td><a href='comment.php?comment_file=$file' target='_blank'>Comment</a></td>";                  

                                // Show case be a picture extension
                                if($filetype == "png" || $filetype == "jpg" || $filetype == "jpeg" || $filetype == "gif"){

                                   echo "<td><div align='center'><a href='$contents' target='_blank'><img src='$contents' width='184px'></a></div></td>";                   
                                }

                                $search_break++;
                            
                            }

                            $entry++;
                        
                            // Skip the files of the directory when reached the total results
                            if($search_break == $end){break;}
                        }
                   
                    }

                    if($search_break == $end){break;}
          
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